package jboss.hinernate.orm42.manual;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.EntityTransaction;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.metamodel.EntityType;

import org.junit.Assert;
import org.maziarz.hbn.HibernateBaseTestUsingProgrammableConfig;

/**
 * Product describes a unidirectional relationship with Part using the join column "partId"
 */
public class Ex703ProductPartOneToManyUnidirectionalTest extends HibernateBaseTestUsingProgrammableConfig {

	@Entity
	public class Part {

		@Id
		@GeneratedValue
		private String id;

		private String name;

		public void setName(String name) {
			this.name = name;
		}

		public String getName() {
			return name;
		}

	}

	@Entity
	public class Product {

		private String serialNumber;
		private Set<Part> parts = new HashSet<Part>();

		private String name;

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		@Id
		@GeneratedValue
		public String getSerialNumber() {
			return serialNumber;
		}

		void setSerialNumber(String sn) {
			serialNumber = sn;
		}

		@OneToMany
		@JoinTable( //
				joinColumns = @JoinColumn(name = "productId"), //
				inverseJoinColumns = @JoinColumn(name = "partId"))
		public Set<Part> getParts() {
			return parts;
		}

		void setParts(Set<Part> parts) {
			this.parts = parts;
		}

	}

	@Override
	protected void test() {

		EntityTransaction tx = em.getTransaction();
		tx.begin();

		Product p = new Product();
		p.setName("aaa");

		final Part e = new Part();
		e.setName("Part1");

		final Part e2 = new Part();
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
		Assert.assertEquals(2, entities.size());

		EntityType<?> part03Entity = getEnitityManager().getMetamodel().entity(Part.class);
		Assert.assertNotNull(part03Entity);

		/**
		 * Find object with SQL query
		 */
		Object o = getEnitityManager().createQuery("Select name from Part03 where id = 1").getSingleResult();
		Assert.assertEquals("Part1", o);

		/**
		 * Find object with specific id
		 */
		Part part2 = getEnitityManager().find(Part.class, "1");
		Assert.assertEquals("Part1", part2.getName());

	}

	@Override
	protected void onInit() {
		registerModelClasses(Product.class, Part.class);
	}
}
