package jboss.hinernate.orm42.manual;

import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EntityTransaction;
import javax.persistence.Id;
import javax.persistence.JoinColumn;

import org.maziarz.hbn.HibernateBaseTestUsingProgrammableConfig;

public class Ex713UserNicknamesElementCollectionTest extends HibernateBaseTestUsingProgrammableConfig {

	@Entity
	public static class User {

		private String lastName;
		private Set<String> nicknames;

		@Id
		public String getLastname() {
			return lastName;
		}

		public void setLastname(String lastName) {
			this.lastName = lastName;
		}

		@ElementCollection
		@CollectionTable(name = "Nicknames", joinColumns = @JoinColumn(name = "userLastname"))
		@Column(name = "nickname")
		public Set<String> getNicknames() {
			return nicknames;
		}

		public void setNicknames(Set<String> nicknames) {
			this.nicknames = nicknames;
		}

	}

	@Override
	protected void onInit() {
		registerModelClass(User.class);
	}

	@Override
	protected void test() {
		EntityTransaction tx = em.getTransaction();
		tx.begin();

		User u = new User();
		u.setLastname("Username");

		tx.commit();
	}

}
