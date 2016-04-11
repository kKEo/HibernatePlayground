package org.maziarz.hbn.model02;

import java.util.Arrays;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EntityTransaction;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.junit.Assert;
import org.maziarz.hbn.HibernateBaseTestUsingProgrammableConfig;

public class M02MappingWithMultipleSourceItemsBidirectional extends HibernateBaseTestUsingProgrammableConfig {

	@Entity
	public class BiItem {

		@Id
		@GeneratedValue
		Integer id;
		
		String name;
		
		@SuppressWarnings("unused")
		private BiItem() {}
		
		public BiItem(String name) {
			this.name = name;
		}
		
		@OneToMany(mappedBy = "target")
		List<BiMultipleSourceMapping> sourceMappings;
		
		@ManyToMany(mappedBy = "sources")
		List<BiMultipleSourceMapping> targetMappings;
	}
	
	@Entity
	public class BiMultipleSourceMapping {

		@SuppressWarnings("unused")
		private BiMultipleSourceMapping() {
		}

		public BiMultipleSourceMapping(String name, BiItem i1, BiItem... i2) {
			this.name = name;
			this.target = i1;
			this.sources = Arrays.asList(i2);
		}

		@Id
		@GeneratedValue
		Integer id;

		String name;
		
		@ManyToOne
		BiItem target;

		@ManyToMany
		@JoinTable(name = "Sources",
				joinColumns = @JoinColumn(name = "mapping_id"),
				inverseJoinColumns = @JoinColumn(name = "source_id"))
		List<BiItem> sources;
	}
	
	@Override
	protected void onInit() {
		registerModelClasses(BiMultipleSourceMapping.class, BiItem.class);
	}

	@Override
	public void test() {

		BiItem t1 = new BiItem("t1");
		BiItem s1 = new BiItem("s1");
		BiItem s2 = new BiItem("s2");

		BiMultipleSourceMapping m = new BiMultipleSourceMapping("m2", t1, s1, s2);

		EntityTransaction tx = em.getTransaction();
		tx.begin();
		em.persist(t1);
		em.persist(s1);
		em.persist(s2);
		em.persist(m);

		em.flush();
		em.clear();

		BiMultipleSourceMapping fetched = em.find(BiMultipleSourceMapping.class, m.id);

		Assert.assertEquals(m.id, fetched.id);
		Assert.assertEquals("m2", fetched.name);
		Assert.assertEquals("t1", fetched.target.name);
		Assert.assertEquals(2, fetched.sources.size());
		Assert.assertEquals("s1", fetched.sources.get(0).name);
		Assert.assertEquals("s2", fetched.sources.get(1).name);

		em.flush();
		em.clear();

		{
			BiItem sourceItem = em.find(BiItem.class, s1.id);

			List<BiMultipleSourceMapping> targetMappings = sourceItem.targetMappings;
			Assert.assertEquals(1, targetMappings.size());
			
			List<BiMultipleSourceMapping> sourceMappings = sourceItem.sourceMappings;
			Assert.assertEquals(0, sourceMappings.size());
		}

		em.flush();
		em.clear();
		{
			BiItem sourceItem = em.find(BiItem.class, t1.id);

			List<BiMultipleSourceMapping> targetMappings = sourceItem.targetMappings;
			Assert.assertEquals(0, targetMappings.size());
			
			List<BiMultipleSourceMapping> sourceMappings = sourceItem.sourceMappings;
			Assert.assertEquals(1, sourceMappings.size());
		}
		tx.rollback();
	}

}
