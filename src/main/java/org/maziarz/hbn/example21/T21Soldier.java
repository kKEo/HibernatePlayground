package org.maziarz.hbn.example21;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class T21Soldier {

	private String id;

	private String name;

	public T21Soldier(String name) {
		this.name = name;
	}

	@Id
	@GeneratedValue
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	private T21Troop troop;

	@ManyToOne
	@JoinColumn(name = "troop_fk", insertable = false, updatable = false)
	public T21Troop getTroop() {
		return troop;
	}

	public void setTroop(T21Troop troop) {
		this.troop = troop;
	}
}