package org.maziarz.hbn.example15;

import javax.persistence.Embeddable;

@Embeddable
public interface T15IAddress {

	public abstract String getStreet();

	public abstract String getHouseNum();

	public abstract void setHouseNum(String houseNum);

	public abstract void setStreet(String street);

}