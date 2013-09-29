package jboss.hibernate.orm42.manual.example703;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Part03 {

	@Id
	@GeneratedValue
	private String id;

	private String name;

	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
}
