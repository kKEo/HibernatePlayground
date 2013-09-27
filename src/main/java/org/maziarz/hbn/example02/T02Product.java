package org.maziarz.hbn.example02;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

@Entity
public class T02Product {

	private String serialNumber;
	private Set<T02Part> parts = new HashSet<T02Part>();

	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Id
	@GeneratedValue
	public String getSerialNumber() {
		return serialNumber;
	}

	void setSerialNumber(String sn) {
		serialNumber = sn;
	}

	@OneToMany
	@JoinColumn(name = "partId")
	public Set<T02Part> getParts() {
		return parts;
	}

	void setParts(Set<T02Part> parts) {
		this.parts = parts;
	}

}
