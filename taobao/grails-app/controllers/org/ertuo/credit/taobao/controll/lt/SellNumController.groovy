package org.ertuo.credit.taobao.controll.lt



import java.util.logging.Logger;

import javax.persistence.EntityManagerFactory;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.ertuo.credit.taobao.domain.CronProcess;
import org.ertuo.credit.taobao.domain.SellNum;
import org.ertuo.credit.taobao.lt.codex.XyzCodexService 
import org.ertuo.credit.taobao.lt.codex.XyzCodexCronService 

import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

/**
 * 定时任务
 * @author mo.duanm
 *
 */
class SellNumController {
	
	private static final Logger logger = Logger.getLogger(SellNumController.class.getName())
	
	EntityManagerFactory entityManagerFactory
	
	
	XyzCodexCronService xyzCodexCronService
	
	static defaultAction = "list"
	
	
	
	final UserService userService = UserServiceFactory.getUserService();
	
	def list={
		def sellNums = SellNum.list()
		return [sellNums:sellNums]
	}
	
	
	def cron={
		//异步执行
		//new Thread(new CronRun()).start()
		xyzCodexCronService.asynCron()
	}
	
	
	
	
	private List getCronList(){
		def maxs=CronProcess.findWhere(codex:"xyz")
	}
	
	
	def delAll={
		def phoneNums= SellNum.list()
		phoneNums.each {
			logger.info(ToStringBuilder.reflectionToString(it,ToStringStyle.MULTI_LINE_STYLE))
			it.delete()
		}
		redirect(action:"list")
	}
	
	class CronRun implements Runnable {
		public void run() {
		}
	}
}
