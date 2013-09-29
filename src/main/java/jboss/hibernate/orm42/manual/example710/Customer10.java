package jboss.hibernate.orm42.manual.example710;

import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MapKey;
import javax.persistence.OneToMany;

@Entity
public class Customer10 {
	@Id
	@GeneratedValue
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	private Integer id;

	@OneToMany(mappedBy = "customer")
	@MapKey(name = "number")
	public Map<String, Orders10> getOrders() {
		return orders;
	}

	public void setOrders(Map<String, Orders10> order) {
		this.orders = order;
	}

	private Map<String, Orders10> orders;
}