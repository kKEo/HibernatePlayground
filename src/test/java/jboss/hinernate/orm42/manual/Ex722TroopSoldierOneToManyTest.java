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

public class Ex722TroopSoldierOneToManyTest extends HibernateBaseTestUsingProgrammableConfig {

	@Entity
	public static class Troop {

		private String id;

		@Id
		@GeneratedValue
		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}

		private List<Soldier> soldiers = new ArrayList<Soldier>(0);

		@OneToMany
		@JoinColumn(name = "troop_fk")
		public List<Soldier> getSoldiers() {
			return soldiers;
		}

		public void setSoldiers(List<Soldier> soliders) {
			this.soldiers = soliders;
		}
	}

	@Entity
	public static class Soldier {

		private String id;

		private String name;

		public Soldier(String name) {
			this.setName(name);
		}

		@SuppressWarnings("unused" /* for sake of hbn */)
		private Soldier() {
		}

		@Id
		@GeneratedValue
		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}

		private Troop troop;

		@ManyToOne
		@JoinColumn(name = "troop_fk", insertable = false, updatable = false)
		public Troop getTroop() {
			return troop;
		}

		public void setTroop(Troop troop) {
			this.troop = troop;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}
	}

	@Override
	protected void test() {

		EntityTransaction tx = em.getTransaction();
		tx.begin();

		Soldier s1 = new Soldier("S1");
		Soldier s2 = new Soldier("S2");
		Soldier s3 = new Soldier("S3");
		Troop t = new Troop();

		em.persist(t);

		em.flush();

		Troop troop = em.find(Troop.class, "1");
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

		Soldier soldier = em.find(Soldier.class, "1");
		Assert.assertNotNull(soldier);
		Assert.assertEquals("1", soldier.getId());
		Assert.assertEquals(troop, soldier.getTroop());

		Assert.assertEquals(3, troop.getSoldiers().size());

		em.clear();

		Troop loadedTroop = em.find(Troop.class, "1");

		Assert.assertEquals(3, loadedTroop.getSoldiers().size());

	}

	@Override
	protected void onInit() {
		registerModelClasses(Troop.class, Soldier.class);

	}

}
