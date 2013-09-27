package org.maziarz.hbn.example10;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Orders10 {
	@Id
	@GeneratedValue
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	private Integer id;

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	private String number;

	@ManyToOne
	public Customer10 getCustomer() {
		return customer;
	}

	public void setCustomer(Customer10 customer) {
		this.customer = customer;
	}

	private Customer10 customer;
}