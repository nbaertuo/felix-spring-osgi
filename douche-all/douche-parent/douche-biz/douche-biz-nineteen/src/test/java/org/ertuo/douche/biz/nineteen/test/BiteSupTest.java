package org.ertuo.douche.biz.nineteen.test;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.ertuo.douche.biz.nineteen.BiteSup;
import org.ertuo.douche.dao.domain.WebProxyDo;
import org.ertuo.douche.dao.opration.ProxyCnDao;
import org.ertuo.douche.proxy.proxycn.LastProxy;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;



/**
 * 19楼集成
 * @author mo.duanm
 *
 */
@ContextConfiguration(locations={"classpath:test.xml","classpath:proxy-cn-dao.xml"})
public class BiteSupTest extends AbstractTransactionalJUnit4SpringContextTests {
	
	@Autowired
	public BiteSup biteSup;
	
	@Autowired
	public ProxyCnDao proxyCnDao;
	
	@Autowired
	public LastProxy lastProxy;
	
	 
	
	public void setDataSource(DataSource dataSource) {
		this.simpleJdbcTemplate = new SimpleJdbcTemplate(dataSource);
	}

	@Test
	public void login(){
		//登陆
		biteSup.login();
		//获得所有楼层
		List<String> floors=biteSup.getFloors();
		for (String floor : floors) {
			//获得楼层下帖子
			Map<String,String> newsList=biteSup.getFloorList(floor);
			for(String key:newsList.keySet()){
				//恢复帖子
				biteSup.answer(newsList.get(key), key);
			}
		}
	}
	
	@Test
	public void proxyCnDao_create(){
		WebProxyDo webProxyDo=new WebProxyDo();
		webProxyDo.setId(1);
		webProxyDo.setCheckDate(new Date());
		webProxyDo.setPort(80);
		webProxyDo.setUrl("202.103.24.68");
		proxyCnDao.createProxy(webProxyDo);
	}
	
	
   @Test
   public void createProxy(){
	   lastProxy.createCanUseProxy();
   }
   @Test
   public void getInvailProxys(){
	   proxyCnDao.getInvailProxys();
   }

}
