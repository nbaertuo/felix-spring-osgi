package org.ertuo.douche.proxy.proxycn;

import org.ertuo.douche.dao.domain.WebProxyDo;


/**
 * 代理管理
 * <p>
 * <li>提供当前有效的代理
 * </p>
 * 
 * @author mo.duanm
 * 
 * 
 */
public interface CnProxyManager {

	/**
	 * 获得当前有效代理
	 * @return
	 */
	WebProxyDo getCurrentInvaidProxy();
	

	/**
	 * 创建有效代理
	 */
	void createCanUseProxy();
	
	
	


}
