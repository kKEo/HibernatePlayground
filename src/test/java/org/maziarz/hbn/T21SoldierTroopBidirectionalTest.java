package org.maziarz.hbn;

import java.util.Arrays;

import javax.persistence.EntityTransaction;

import org.junit.Assert;
import org.maziarz.hbn.example21.T21Soldier;
import org.maziarz.hbn.example21.T21Troop;


public class T21SoldierTroopBidirectionalTest extends HibernateBaseTestUsingProgrammableConfig {

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
		addClasses(T21Troop.class, T21Soldier.class);

	}

}
