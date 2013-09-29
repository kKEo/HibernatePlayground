package jboss.hibernate.orm42.manual.example721;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class T21Troop {

	private String id;

	@Id
	@GeneratedValue
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	private List<T21Soldier> soldiers = new ArrayList<T21Soldier>(0);

	@OneToMany(mappedBy = "troop")
	public List<T21Soldier> getSoldiers() {
		return soldiers;
	}

	public void setSoldiers(List<T21Soldier> soliders) {
		this.soldiers = soliders;
	}
}