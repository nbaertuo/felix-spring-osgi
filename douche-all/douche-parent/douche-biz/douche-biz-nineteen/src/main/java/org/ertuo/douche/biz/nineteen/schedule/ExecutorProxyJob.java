package org.ertuo.douche.biz.nineteen.schedule;

import org.apache.log4j.Logger;
import org.ertuo.douche.proxy.proxycn.CnProxyManager;

/**
 */
public class ExecutorProxyJob implements Runnable {

	private static Logger logger = Logger.getLogger(ExecutorProxyJob.class);

	private CnProxyManager cnProxyManager;

	/**
	 *十九楼回帖
	 */
	public void run() {
		logger.debug("开始创建代理");
		cnProxyManager.createCanUseProxy();
	}
}
