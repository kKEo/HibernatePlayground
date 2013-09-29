package jboss.hibernate.orm42.manual.example724;

import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;

@Entity
public class Employee {
	
    private Collection<Employer> employers;

	@ManyToMany(
        cascade = {CascadeType.PERSIST, CascadeType.MERGE},
        mappedBy = "employees",
        targetEntity = Employer.class
    )
    public Collection<Employer> getEmployers() {
        return employers;
    }
} 