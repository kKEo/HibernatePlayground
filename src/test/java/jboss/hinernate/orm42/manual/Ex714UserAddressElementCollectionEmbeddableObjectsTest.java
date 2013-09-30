package jboss.hinernate.orm42.manual;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.EntityTransaction;
import javax.persistence.Id;
import javax.persistence.JoinColumn;

import jboss.hinernate.orm42.manual.Ex714UserAddressElementCollectionEmbeddableObjectsTest.Address;

import org.maziarz.hbn.HibernateBaseTestUsingProgrammableConfig;

public class Ex714UserAddressElementCollectionEmbeddableObjectsTest extends HibernateBaseTestUsingProgrammableConfig {

	@Entity
	public class User {

		private String id;

		@Id
		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}

		private String lastName;

		private Set<Address> addresses;

		@ElementCollection
		@CollectionTable(name = "Address", joinColumns = @JoinColumn(name = "userId"))
		@AttributeOverrides({//
		//
				@AttributeOverride(name = "street", column = @Column(name = "fld_street")), //
				@AttributeOverride(name = "houseNum", column = @Column(name = "fld_house")) })
		public Set<Address> getAddresses() {
			if (addresses == null) {
				addresses = new HashSet<Address>();
			}
			return addresses;
		}

		public void setAddresses(Set<Address> addresses) {
			this.addresses = addresses;
		}

		public void setLastName(String lastName) {
			this.lastName = lastName;
		}

		public String getLastName() {
			return lastName;
		}

	}

	@Embeddable
	public interface Address {

		public abstract String getStreet();

		public abstract String getHouseNum();

		public abstract void setHouseNum(String houseNum);

		public abstract void setStreet(String street);

	}

	public class AddressImpl implements Address {

		private String street;
		private String houseNum;

		@Override
		public String getStreet() {
			return street;
		}

		@Override
		public String getHouseNum() {
			return houseNum;
		}

		@Override
		public void setHouseNum(String houseNum) {
			this.houseNum = houseNum;
		}

		@Override
		public void setStreet(String street) {
			this.street = street;
		}
	}

	@Override
	protected void onInit() {
		registerModelClasses(Address.class, User.class);
	}

	@Override
	protected void test() {
		EntityTransaction tx = em.getTransaction();
		tx.begin();

		User u = new User();
		u.setLastName("Username");

		addAddress(u, "Krótka", "31");
		addAddress(u, "Długa", "13");

		tx.commit();

	}

	private void addAddress(User u, String street, String houseNum) {
		Address a = new AddressImpl();
		a.setStreet(street);
		a.setHouseNum(houseNum);
		u.getAddresses().add(a);
	}

}
