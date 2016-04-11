package org.maziarz.hbn.model02;

import javax.persistence.EntityTransaction;

import org.junit.Assert;
import org.maziarz.hbn.HibernateBaseTestUsingProgrammableConfig;

public class M02MappingWithMultipleSourceItems extends HibernateBaseTestUsingProgrammableConfig {

	@Override
	protected void onInit() {
		registerModelClasses(MultipleSourceMapping.class, Item.class);
	}

	@Override
	public void test() {

		Item t1 = new Item("t1");
		Item s1 = new Item("s1");
		Item s2 = new Item("s2");

		MultipleSourceMapping m = new MultipleSourceMapping("m2", t1, s1, s2);

		EntityTransaction tx = em.getTransaction();
		tx.begin();
		em.persist(t1);
		em.persist(s1);
		em.persist(s2);
		em.persist(m);

		em.flush();
		em.clear();

		MultipleSourceMapping fetched = em.find(MultipleSourceMapping.class, m.id);

		Assert.assertEquals(m.id, fetched.id);
		Assert.assertEquals("m2", fetched.name);
		Assert.assertEquals("t1", fetched.target.name);
		Assert.assertEquals(2, fetched.sources.size());
		Assert.assertEquals("s1", fetched.sources.get(0).name);
		Assert.assertEquals("s2", fetched.sources.get(1).name);

		tx.rollback();
	}

}
