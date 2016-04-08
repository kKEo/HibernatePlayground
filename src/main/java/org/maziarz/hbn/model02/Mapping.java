package org.maziarz.hbn.model02;

import javax.annotation.Generated;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Mapping {

	@SuppressWarnings("unused")
	private Mapping() {}
	
	public Mapping(String string, Item i1, Item i2) {
		this.name = "m1";
		this.source = i1;
		this.target = i2;
	}

	@Id
	@GeneratedValue
	Integer id;
	
	String name;
	
	@ManyToOne
	Item source;
	
	@ManyToOne
	Item target;
	
}
