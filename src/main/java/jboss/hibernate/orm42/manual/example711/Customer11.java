package jboss.hibernate.orm42.manual.example711;

import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.MapKeyColumn;
import javax.persistence.OneToMany;

@Entity
public class Customer11 {
	@Id
	@GeneratedValue
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	private Integer id;

	@OneToMany
	@JoinTable(name = "Cust_Order")
	@MapKeyColumn(name = "orders_number")
	public Map<String, Order11> getOrders() {
		return orders;
	}

	public void setOrders(Map<String, Order11> orders) {
		this.orders = orders;
	}

	private Map<String, Order11> orders;
}