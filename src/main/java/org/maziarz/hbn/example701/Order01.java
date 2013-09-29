package org.maziarz.hbn.example701;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Order01 {
	@Id
	private Long id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Embedded
	private Address01 address;

	public Address01 getAddress() {
		return address;
	}

	public void setAddress() {
		this.address = address;
	}
}