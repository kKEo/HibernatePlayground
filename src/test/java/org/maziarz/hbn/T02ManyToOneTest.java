package org.maziarz.hbn;

import java.util.Set;

import javax.persistence.EntityTransaction;
import javax.persistence.metamodel.EntityType;

import org.junit.Assert;
import org.maziarz.hbn.example02.T02Part;
import org.maziarz.hbn.example02.T02Product;


public class T02ManyToOneTest extends HibernateBaseTestUsingProgrammableConfig {

	@Override
	protected void test() {

		EntityTransaction tx = em.getTransaction();
		tx.begin();

		T02Product p = new T02Product();
		p.setName("aaa");

		final T02Part e = new T02Part();
		e.setName("Part1");

		final T02Part e2 = new T02Part();
		e2.setName("Part2");

		p.getParts().add(e);
		p.getParts().add(e2);

		getEnitityManager().persist(e);
		getEnitityManager().persist(e2);
		getEnitityManager().persist(p);

		tx.commit();

		/**
		 * Find table metadata
		 */
		Set<EntityType<?>> entities = getEnitityManager().getMetamodel().getEntities();
		String name = entities.iterator().next().getName();
		Assert.assertEquals("T02Part", name);

		/**
		 * Find object with SQL query
		 */
		Object o = getEnitityManager().createQuery("Select name from T02Part where id = 1").getSingleResult();
		Assert.assertEquals("Part1", o);

		/**
		 * Find object with specific id
		 */
		T02Part part2 = getEnitityManager().find(T02Part.class, "1");
		Assert.assertEquals("Part1", part2.getName());

	}

	@Override
	protected void onInit() {
		addClasses(T02Product.class, T02Part.class);
	}
}
