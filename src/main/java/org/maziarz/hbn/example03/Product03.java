package org.maziarz.hbn.example03;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;

@Entity
public class Product03 {

	private String serialNumber;
	private Set<Part03> parts = new HashSet<Part03>();

	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Id
	public String getSerialNumber() {
		return serialNumber;
	}

	void setSerialNumber(String sn) {
		serialNumber = sn;
	}

	@OneToMany
	@JoinTable(name = "product_part", joinColumns = @JoinColumn(name = "productId"), inverseJoinColumns = @JoinColumn(name = "partId"))
	public Set<Part03> getParts() {
		return parts;
	}

	void setParts(Set<Part03> parts) {
		this.parts = parts;
	}

}
