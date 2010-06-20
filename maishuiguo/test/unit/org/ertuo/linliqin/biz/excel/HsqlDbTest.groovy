package org.ertuo.linliqin.biz.excel;

import junit.framework.TestCase;
import groovy.sql.Sql

public class HsqlDbTest extends TestCase {

	void test_hsqldb(){
		  def sql = Sql.newInstance("jdbc:hsqldb:hsql://localhost/xdb;ifexists=true", "sa",
		          "", "org.hsqldb.jdbcDriver")
		          sql.eachRow("select * from test"){ row ->
		       println row.id + " " + row.name + " " + row.remark
		    }
	 }
}
