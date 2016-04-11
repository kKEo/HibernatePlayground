package org.maziarz.hbn.country;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Country {

	@Id
	@GeneratedValue
	@Column(name = "CTRY_ID")
	Integer id;

	@Column(name = "CTRY_NAME")
	String name;

	@ManyToOne
	@JoinColumn(name = "CONT_ID")
	Continent continent;

}