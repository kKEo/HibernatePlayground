package org.maziarz.hbn.example07;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;

@Entity
public class Customer07 {
	@Id
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	private Long id;

	@OneToMany(mappedBy = "customer")
	@OrderBy("number")
	public List<Order07> getOrders() {
		return orders;
	}

	public void setOrders(List<Order07> orders) {
		this.orders = orders;
	}

	private List<Order07> orders;
}