package org.maziarz.hbn;

import org.junit.Test;
import org.maziarz.hbn.model01.M01AiDbcConn;
import org.maziarz.hbn.model01.M01DbConn;
import org.maziarz.hbn.model01.M01JdbcConn;
import org.maziarz.hbn.model01.M01JdbcOracleConn;
import org.maziarz.hbn.model01.M01JdbcMysqlConn;
import org.maziarz.hbn.model01.M01Project;


public class M01ProjectWithDbConnectionsInheritence extends HibernateBaseTestUsingProgrammableConfig {

	@Override
	@Test
	public void test() {

	}

	@Override
	protected void onInit() {
		registerModelClasses(M01Project.class, M01DbConn.class, M01JdbcConn.class, M01AiDbcConn.class, M01JdbcOracleConn.class,
				M01JdbcMysqlConn.class);
	}
}
