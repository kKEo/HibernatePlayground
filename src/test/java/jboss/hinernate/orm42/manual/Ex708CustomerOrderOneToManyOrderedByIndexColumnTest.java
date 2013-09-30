package jboss.hinernate.orm42.manual;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EntityTransaction;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderColumn;

import org.junit.Assert;
import org.maziarz.hbn.HibernateBaseTestUsingProgrammableConfig;

public class Ex708CustomerOrderOneToManyOrderedByIndexColumnTest extends HibernateBaseTestUsingProgrammableConfig {

	@Entity(name = "Customers")
	public static class Customer {

		private Long id;
		private List<Order> orders;

		@Id
		@GeneratedValue
		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		@OneToMany(mappedBy = "customer")
		@OrderColumn(name="orders_idx")
		public List<Order> getOrders() {
			if (orders == null) {
				orders = new ArrayList<Order>();
			}
			return orders;
		}

		public void setOrders(List<Order> orders) {
			this.orders = orders;
		}

		public Customer() {
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((id == null) ? 0 : id.hashCode());
			result = prime * result + ((orders == null) ? 0 : orders.hashCode());
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj) {
				return true;
			}
			if (obj == null || getClass() != obj.getClass()) {
				return false;
			}
			Customer other = (Customer) obj;
			if (id == null && other.id != null) {
					return false;
			} else if (!id.equals(other.id)) {
				return false;
			}
			return true;
		}
	}

	@Entity(name = "Orders")
	public static class Order {

		private Long id;
		private String number;
		private Customer customer;

		public Order() {
		}

		@Id
		@GeneratedValue
		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public String getNumber() {
			return number;
		}

		public void setNumber(String number) {
			this.number = number;
		}

		@ManyToOne
		public Customer getCustomer() {
			return customer;
		}

		public void setCustomer(Customer customer) {
			this.customer = customer;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((customer == null) ? 0 : customer.hashCode());
			result = prime * result + ((id == null) ? 0 : id.hashCode());
			result = prime * result + ((number == null) ? 0 : number.hashCode());
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Order other = (Order) obj;
			if (customer == null) {
				if (other.customer != null)
					return false;
			} else if (!customer.getId().equals(other.customer.getId()))
				return false;
			if (id == null) {
				if (other.id != null)
					return false;
			} else if (!id.equals(other.id))
				return false;
			if (number == null) {
				if (other.number != null)
					return false;
			} else if (!number.equals(other.number))
				return false;
			return true;
		}

	}

	@Override
	protected void onInit() {
		registerModelClasses(Customer.class, Order.class);
	}

	@Override
	protected void test() {

		EntityTransaction tx = em.getTransaction();
		tx.begin();

		Order o1 = new Order();
		o1.setNumber("n1");

		Order o2 = new Order();
		o2.setNumber("n3");
		
		Order o3 = new Order();
		o3.setNumber("n2");

		getEnitityManager().persist(o1);
		getEnitityManager().persist(o2);
		getEnitityManager().persist(o3);

		Customer originalCustomer = new Customer();
		originalCustomer.getOrders().add(o1);
		originalCustomer.getOrders().add(o2);
		originalCustomer.getOrders().add(o3);
		o1.setCustomer(originalCustomer);
		o2.setCustomer(originalCustomer);
		o3.setCustomer(originalCustomer);

		getEnitityManager().persist(originalCustomer);

		tx.commit();

		getEnitityManager().clear();

		Order o = getEnitityManager().find(Order.class, 1L);

		Assert.assertTrue(o != o1);
		Assert.assertTrue(o.equals(o1));
		Assert.assertEquals(o, o1);

		Customer loadedCustomer = getEnitityManager().find(Customer.class, 1L);

		Assert.assertTrue(loadedCustomer != originalCustomer);
		Assert.assertTrue(loadedCustomer.equals(originalCustomer));
		
		Assert.assertEquals(3, originalCustomer.getOrders().size());
		Assert.assertEquals(3, loadedCustomer.getOrders().size());
		
		Order order3 = loadedCustomer.getOrders().get(2);
		Assert.assertEquals(new Long(3), order3.getId());
		Assert.assertEquals("n2", order3.getNumber());

		Object number = getEnitityManager().createQuery("Select number from Orders where id = 3").getSingleResult();
		Assert.assertEquals("n2", number);
	}

}
