package org.maziarz.hbn;

import java.util.List;

import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.PersistenceUtil;

import org.junit.Assert;
import org.maziarz.hbn.example00.T00DomesticCat;


public class T00SimpleDomesticCatTest extends HibernateBaseTestUsingProgrammableConfig {

	@Override
	protected void test() {

		EntityTransaction tx = em.getTransaction();
		tx.begin();

		T00DomesticCat fritz = new T00DomesticCat();
		fritz.setName("Fritz");
		em.persist(fritz);

		tx.commit();

		boolean isIn = em.contains(fritz);
		Assert.assertTrue(isIn);

		PersistenceUtil jpaUtil = Persistence.getPersistenceUtil();
		Assert.assertTrue(jpaUtil.isLoaded(fritz));

		List<?> kittens = em.createQuery("from T00DomesticCat").getResultList();
		Assert.assertEquals(1, kittens.size());

		T00DomesticCat cat = em.find(T00DomesticCat.class, new Long(1));
		Assert.assertEquals("Fritz", cat.getName());

	}

	@Override
	protected void onInit() {
		addClasses(T00DomesticCat.class);

	}

}
