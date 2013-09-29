package jboss.hinernate.orm42.manual;

import jboss.hibernate.orm42.manual.example724.Employee;
import jboss.hibernate.orm42.manual.example724.Employer;

import org.maziarz.hbn.HibernateBaseTestUsingProgrammableConfig;

public class Ex723EmployeeEmployerManyToManyBidirectionalTest extends HibernateBaseTestUsingProgrammableConfig {

	@Override
	protected void test() {

	}

	@Override
	protected void onInit() {
		registerModelClasses(Employer.class, Employee.class);

	}

}
