package jboss.hinernate.orm42.manual;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EntityTransaction;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Persistence;
import javax.persistence.PersistenceUtil;

import org.junit.Assert;
import org.maziarz.hbn.HibernateBaseTestUsingProgrammableConfig;


public class Ex700SimpleDomesticCatTest extends HibernateBaseTestUsingProgrammableConfig {

	public interface Cat {
		String getName();

		void setName(String string);
	}

	@Entity (name = "DomesticCat")
	public static class DomesticCat implements Cat {

		private Long id;

		@Id
		@GeneratedValue
		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		private String name;

		@Override
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

		DomesticCat fritz = new DomesticCat();
		fritz.setName("Fritz");
		em.persist(fritz);

		tx.commit();

		boolean isIn = em.contains(fritz);
		Assert.assertTrue(isIn);

		PersistenceUtil jpaUtil = Persistence.getPersistenceUtil();
		Assert.assertTrue(jpaUtil.isLoaded(fritz));

		List<?> kittens = em.createQuery("from DomesticCat").getResultList();
		Assert.assertEquals(1, kittens.size());

		Cat cat = em.find(DomesticCat.class, new Long(1));
		Assert.assertEquals("Fritz", cat.getName());
	}

	@Override
	protected void onInit() {
		registerModelClasses(DomesticCat.class);

	}

}
