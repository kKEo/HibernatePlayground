package jboss.hinernate.orm42.manual;

import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import org.maziarz.hbn.HibernateBaseTestUsingProgrammableConfig;

public class Ex723EmployeeEmployerManyToManyBidirectionalTest extends HibernateBaseTestUsingProgrammableConfig {

	@Entity(name = "Employee")
	public static class Employee {

		private Long id;

		@Id
		@GeneratedValue
		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		private Collection<Employer> employers;

		@ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, mappedBy = "employees")
		public Collection<Employer> getEmployers() {
			return employers;
		}

		public void setEmployers(Collection<Employer> employers) {
			this.employers = employers;
		}
	}

	@Entity(name = "Employer")
	public static class Employer {

		private Long id;

		@Id
		@GeneratedValue
		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		private Collection<Employee> employees;

		@ManyToMany(cascade = { CascadeType.ALL })
		@JoinTable(name = "EMPLOYER_EMPLOYEE", //
				//
				joinColumns = @JoinColumn(name = "EMPER_ID"), //
				inverseJoinColumns = @JoinColumn(name = "EMPEE_ID"))
		public Collection<Employee> getEmployees() {
			return employees;
		}

		public void setEmployees(Collection<Employee> employees) {
			this.employees = employees;
		}

	}

	@Override
	protected void test() {

	}

	@Override
	protected void onInit() {
		registerModelClasses(Employer.class, Employee.class);
	}

}
