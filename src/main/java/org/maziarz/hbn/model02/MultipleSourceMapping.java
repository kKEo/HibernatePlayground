package org.maziarz.hbn.model02;

import java.util.Arrays;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
public class MultipleSourceMapping {

	@SuppressWarnings("unused")
	private MultipleSourceMapping() {}
	
	public MultipleSourceMapping(String name, Item i1, Item... i2) {
		this.name = name;
		this.target = i1;
		this.sources = Arrays.asList(i2);
	}

	@Id
	@GeneratedValue
	Integer id;
	
	String name;
	
	@ManyToMany
	List<Item> sources;
	
	@ManyToOne
	Item target;
	
}
