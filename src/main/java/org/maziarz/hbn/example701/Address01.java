package org.maziarz.hbn.example701;

import javax.persistence.*;

@Embeddable
@Access(AccessType.FIELD)
public class Address01 {
	private String street1;

	private Integer numbera;
	private Integer flat;

	public String getStreet1() {
		return street1;
	}

	public void setStreet1(String street1) {
		this.street1 = street1;
	}

}