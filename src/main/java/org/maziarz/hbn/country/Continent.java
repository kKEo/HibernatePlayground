package org.maziarz.hbn.country;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Continent {

	@Id
	@GeneratedValue
	@Column(name = "CONT_ID")
	int id;

	String name;

	@OneToMany(mappedBy = "continent", cascade = { CascadeType.ALL })
	Set<Country> countries =  new HashSet<Country>();

}
