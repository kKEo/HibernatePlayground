package jboss.hinernate.orm42.manual;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EntityTransaction;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.junit.Assert;
import org.maziarz.hbn.HibernateBaseTestUsingProgrammableConfig;

public class Ex721TroopSoldierOneToManyBidirectionalTest extends HibernateBaseTestUsingProgrammableConfig {

	@Entity
	public class T21Troop {

		private String id;

		@Id
		@GeneratedValue
		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}

		private List<T21Soldier> soldiers = new ArrayList<T21Soldier>(0);

		@OneToMany(mappedBy = "troop")
		public List<T21Soldier> getSoldiers() {
			return soldiers;
		}

		public void setSoldiers(List<T21Soldier> soliders) {
			this.soldiers = soliders;
		}
	}
	
	@Entity
	public class T21Soldier {

		private String id;

		private String name;

		public T21Soldier(String name) {
			this.name = name;
		}

		@Id
		@GeneratedValue
		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}

		private T21Troop troop;

		@ManyToOne
		@JoinColumn(name = "troop_fk", insertable = false, updatable = false)
		public T21Troop getTroop() {
			return troop;
		}

		public void setTroop(T21Troop troop) {
			this.troop = troop;
		}
	}
	
	@Override
	protected void test() {

		EntityTransaction tx = em.getTransaction();
		tx.begin();

		T21Soldier s1 = new T21Soldier("S1");
		T21Soldier s2 = new T21Soldier("S2");
		T21Soldier s3 = new T21Soldier("S3");
		T21Troop t = new T21Troop();

		em.persist(t);

		em.flush();

		T21Troop troop = em.find(T21Troop.class, "1");
		Assert.assertNotNull(troop);

		Assert.assertEquals(0, troop.getSoldiers().size());

		s1.setTroop(t);
		s2.setTroop(t);
		s3.setTroop(t);
		t.setSoldiers(Arrays.asList(s1, s2, s3));

		Assert.assertEquals(3, troop.getSoldiers().size());

		em.persist(s1);
		em.persist(s2);
		em.persist(s3);
		em.flush();

		tx.commit();

		T21Soldier soldier = em.find(T21Soldier.class, "1");
		Assert.assertNotNull(soldier);
		Assert.assertEquals("1", soldier.getId());
		Assert.assertEquals(troop, soldier.getTroop());

		Assert.assertEquals(3, troop.getSoldiers().size());

	}

	@Override
	protected void onInit() {
		registerModelClasses(T21Troop.class, T21Soldier.class);

	}

}
