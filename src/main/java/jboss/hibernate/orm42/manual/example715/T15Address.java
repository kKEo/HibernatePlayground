package jboss.hibernate.orm42.manual.example715;

public class T15Address implements T15IAddress {

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