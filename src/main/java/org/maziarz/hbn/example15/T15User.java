package org.maziarz.hbn.example15;

import java.util.Set;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;

@Entity
public class T15User {

	private String id;

	@Id
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	private String lastName;

	private Set<T15IAddress> addresses;

	@ElementCollection
	@CollectionTable(name = "T15Address", joinColumns = @JoinColumn(name = "userId"))
	@AttributeOverrides({ @AttributeOverride(name = "street", column = @Column(name = "fld_street")),
			@AttributeOverride(name = "houseNum", column = @Column(name = "fld_house")) })
	public Set<T15IAddress> getAddresses() {
		return addresses;
	}

	public void setAddresses(Set<T15IAddress> addresses) {
		this.addresses = addresses;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getLastName() {
		return lastName;
	}

}
