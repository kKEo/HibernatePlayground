package jboss.hibernate.orm42.manual.example708;

import java.util.List;
import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OrderColumn;

@Entity
public class Customer08 {
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
	@OrderColumn(name = "orders_index")
	public List<Order08> getOrders() {
		return orders;
	}

	public void setOrders(List<Order08> orders) {
		this.orders = orders;
	}

	private List<Order08> orders;

	@ElementCollection
	@CollectionTable(name = "Nicknames", joinColumns = @JoinColumn(name = "customerId"))
	@Column(name = "nickname")
	public Set<String> getNicknames() {
		return nicknames;
	}

	private Set<String> nicknames;

	public void setNicknames(Set<String> nicknames) {
		this.nicknames = nicknames;
	}
}