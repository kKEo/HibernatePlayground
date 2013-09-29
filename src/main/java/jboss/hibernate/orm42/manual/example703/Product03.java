package jboss.hibernate.orm42.manual.example703;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;

@Entity
public class Product03 {


	private String serialNumber;
	private Set<Part03> parts = new HashSet<Part03>();

	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Id
	@GeneratedValue
	public String getSerialNumber() {
		return serialNumber;
	}

	void setSerialNumber(String sn) {
		serialNumber = sn;
	}

	@OneToMany
	@JoinTable(
			joinColumns = @JoinColumn(name = "productId"), 
			inverseJoinColumns = @JoinColumn(name = "partId"))
	public Set<Part03> getParts() {
		return parts;
	}

	void setParts(Set<Part03> parts) {
		this.parts = parts;
	}

}
