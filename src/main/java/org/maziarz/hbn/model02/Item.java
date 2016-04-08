package org.maziarz.hbn.model02;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Item {

	@Id
	@GeneratedValue
	Integer id;
	
	String name;
	
	@SuppressWarnings("unused")
	private Item() {}
	
	public Item(String name) {
		this.name = name;
	}
	
	
}
