package org.maziarz.hbn.model01;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class M01Project {

	private Long id;

	@Id
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	private String description;

	private List<M01DbConn> connections;

	@OneToMany(targetEntity = M01DbConn.class)
	public List<M01DbConn> getConnections() {
		return connections;
	}

	public void setConnections(List<M01DbConn> connections) {
		this.connections = connections;
	}

}
