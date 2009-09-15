package org.ertuo.douche.biz.nineteen.schedule;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.ertuo.douche.biz.nineteen.NineTeenManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 */
@Service
public class NineteenQuartzJob {

	private static Logger logger = Logger.getLogger(NineteenQuartzJob.class);

	@Autowired
	private NineTeenManager nineTeenManager;

	/**
	 *十九楼回帖
	 */
	public void cronExecuteLog() {
		logger.debug("回帖任务开始调度");
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

	 
}
