package org.ertuo.douche.biz.nineteen.test;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.ertuo.douche.biz.nineteen.NineTeenManager;
import org.ertuo.douche.dao.domain.WebProxyDo;
import org.ertuo.douche.dao.opration.ProxyCnDao;
import org.ertuo.douche.db.hsql.HSQLServer;
import org.ertuo.douche.engine.htmlutil.webclient.WebClientLocal;
import org.ertuo.douche.proxy.proxycn.CnProxyManager;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.transaction.annotation.Transactional;

import com.gargoylesoftware.htmlunit.html.HtmlPage;



/**
 * 19楼集成测试
 * @author mo.duanm
 *
 */
@ContextConfiguration(locations={"classpath:test.xml","classpath:proxy-cn-dao.xml"})
public class NineTeenManagerTest extends AbstractTransactionalJUnit4SpringContextTests {
	
	@Autowired
	public NineTeenManager nineTeenManager;
	
	@Autowired
	WebClientLocal webClientLocal;
	
	@Autowired
	public ProxyCnDao proxyCnDao;
	
	@Autowired
	public CnProxyManager cnProxyManager;
	
	
	 
	
	public void setDataSource(DataSource dataSource) {
		this.simpleJdbcTemplate = new SimpleJdbcTemplate(dataSource);
	}

	@Test
	@Rollback(value=false)
	public void login(){
		//登陆
		nineTeenManager.login();
		//获得所有楼层
		List<String> floors=nineTeenManager.getFloors();
		for (String floor : floors) {
			//获得楼层下帖子
			Map<String,String> newsList=nineTeenManager.getFloorList(floor);
			for(String key:newsList.keySet()){
				//恢复帖子
				nineTeenManager.answer(newsList.get(key), key);
			}
		}
	}
	
	@Test
	@Rollback(value=false)
	public void createCnProxy(){
		WebProxyDo webProxyDo=new WebProxyDo();
		webProxyDo.setId("202.103.24.68:80");
		webProxyDo.setCheckDate(new Date());
		webProxyDo.setPort(80);
		webProxyDo.setUrl("202.103.24.68");
		proxyCnDao.createProxy(webProxyDo);
		//server.shutdown();
	}
	
	
   @Test
   @Rollback(value=false)
   public void createProxy(){
	   cnProxyManager.createCanUseProxy();
   }
   @Test
   public void getInvailProxys(){
	   proxyCnDao.getInvailProxys();
   }
   
   @Test
   public void testTaobao(){
	  HtmlPage page= webClientLocal.getHtmlPageByUrl("http://www.19lou.com/post.php?action=reply&fid=269&tid=13543385&extra=page%3D1");
	  logger.debug(page.asText());
   }

}
