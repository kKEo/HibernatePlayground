package org.maziarz.hbn.model02;

import javax.persistence.EntityTransaction;

import org.junit.Assert;
import org.maziarz.hbn.HibernateBaseTestUsingProgrammableConfig;


public class M02MappingItems extends HibernateBaseTestUsingProgrammableConfig {

	@Override
	protected void onInit() {
		registerModelClasses(Mapping.class, Item.class);
	}
	
	@Override
	public void test() {

		Item i1 = new Item("i1");
		Item i2 = new Item("i2");
		
		Mapping m = new Mapping("m1", i1, i2);

		EntityTransaction tx = em.getTransaction();
		tx.begin();

		em.persist(i1);
		em.persist(i2);
		em.persist(m);

		em.flush();
		em.clear();
		
		Mapping fetched = em.find(Mapping.class, m.id);
		
		Assert.assertEquals(m.id, fetched.id);
		Assert.assertEquals("m1", fetched.name);
		Assert.assertEquals("i1", fetched.source.name);
		Assert.assertEquals("i2", fetched.target.name);
		
		tx.rollback();
	}

}
