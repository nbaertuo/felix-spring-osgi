package org.ertuo.linliqin.biz.excel;

import org.ertuo.linliqin.dao.persistence.db.HsqlServerManager;

import junit.framework.TestCase;

public class ExcelTest extends TestCase {
	public void test_hsqldb() {
		String[] strs = new String[] { "-database.0", "file:mydb", "-dbname.0",
				"xdb" };
		HsqlServerManager.start(strs);
	}
}
