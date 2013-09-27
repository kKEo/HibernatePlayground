package org.maziarz.hbn.model01;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;

@Entity
@Inheritance
public abstract class M01DbConn {

	@Id
	private String id;

	private String name;

}
