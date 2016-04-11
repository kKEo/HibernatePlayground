package org.maziarz.hbn.country;

import javax.persistence.EntityTransaction;

import org.assertj.core.api.Assertions;
import org.junit.Assert;
import org.maziarz.hbn.HibernateBaseTestUsingProgrammableConfig;

public class M03ContinentsCounties extends HibernateBaseTestUsingProgrammableConfig {

	private static final String SERBIA = "Serbia";
	private static final String CROATIA = "Croatia";
	private static final String EUROPE = "Europe";

	@Override
	protected void onInit() {
		registerModelClasses(Continent.class, Country.class);
	}
	
	@Override
	public void test() {

		EntityTransaction tx = em.getTransaction();
		tx.begin();
		
		Continent europe = new Continent();
		europe.name = EUROPE;

		addCountry(europe, SERBIA);
		addCountry(europe, CROATIA);
		
		em.persist(europe);

		em.flush();
		em.clear();

		Continent persistedContinent = em.find(Continent.class, 1);
		
		Assert.assertEquals(EUROPE, persistedContinent.name);
		
		Assert.assertEquals(2, persistedContinent.countries.size());
		
		Country c = persistedContinent.countries.iterator().next();
		Assert.assertEquals(EUROPE, c.continent.name);

		em.flush();
		em.clear();
		
		Country persistedCountry = em.find(Country.class, 1);
		
		Assertions.assertThat(persistedCountry.name).isIn(SERBIA, CROATIA);
		Assertions.assertThat(persistedCountry.continent.name).as("Continent is not valid").isEqualTo(EUROPE);
		
		tx.rollback();
	}

	private void addCountry(Continent cont, String name) {
		Country country = new Country();
		country.name = name;
		cont.countries.add(country);
		country.continent = cont;
	}

}
