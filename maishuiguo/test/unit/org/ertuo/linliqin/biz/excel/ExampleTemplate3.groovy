package org.ertuo.linliqin.biz.excel;



import groovy.text.SimpleTemplateEngine
import java.io.File
import junit.framework.TestCase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import groovy.sql.Sql
import org.ertuo.linliqin.dao.persistence.db.HsqlServerManager;
import org.hsqldb.Server;

class ExampleTemplate3 extends TestCase{

	private final static Logger log = LoggerFactory.getLogger(ExampleTemplate3.class);


	 void test_1(){
		    def filePath="D:/develop/sources/groovy/j-pg02155-source/事业单位分行业专业技术人员基本情况.xls"
		    def destFilePath="D:/develop/sources/groovy/j-pg02155-source/new_事业单位分行业专业技术人员基本情况.xls"	
		 	def fle = new File(filePath)		
			
		 	ExcelTemplateOperation excel=new ExcelTemplateOperation(filePath,destFilePath)

		 	Map rs= excel.readExcel(fle)
		 	(0..<rs.size()).each{
		    	def list=rs.get(it)
		    	list.each{xy->
		    		excel.insert(it,xy[1], xy[0], "fuck");
		    	}
		    	
		    }
		    excel.close()
		 
	 }
	 

}