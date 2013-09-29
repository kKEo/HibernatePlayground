package jboss.hibernate.orm42.manual.example707;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Order07 {
	@Id
	@GeneratedValue
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	private Long id;

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	private String number;

	@ManyToOne
	public Customer07 getCustomer() {
		return customer;
	}

	public void setCustomer(Customer07 customer) {
		this.customer = customer;
	}

	private Customer07 customer;
}