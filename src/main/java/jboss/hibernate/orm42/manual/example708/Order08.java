package jboss.hibernate.orm42.manual.example708;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Order08 {
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
	public Customer08 getCustomer() {
		return customer;
	}

	public void setCustomer(Customer08 customer) {
		this.customer = customer;
	}

	private Customer08 customer;
}