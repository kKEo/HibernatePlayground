package org.maziarz.hbn;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.FlushModeType;
import javax.persistence.Persistence;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Ignore
public abstract class HibernateBaseTestUsingPersistenceXmlConfig {

	private EntityManagerFactory emf;

	protected EntityManager em;

	protected EntityManager getEnitityManager() {
		return em;
	}

	private static Logger log = LoggerFactory.getLogger(HibernateBaseTestUsingPersistenceXmlConfig.class);

	@Before
	public void setUp() {
		Map<String, Object> configOverrides = overrideConfig();
		log.info("Overrides: " + configOverrides);
		emf = Persistence.createEntityManagerFactory("persistence-config", configOverrides);
		em = emf.createEntityManager();
		em.setFlushMode(FlushModeType.AUTO);
	}

	@After
	public void tearDown() {
		emf.close();
	}

	@Test
	public final void actualTest() throws Exception {
		test();
	}

	protected Map<String, Object> overrideConfig() {
		return new HashMap<String, Object>();
	}

	protected abstract void test();

}
