/**
 * 
 */
package org.ertuo.douche.biz.nineteen;

/**
 * 登录管理，登录功能，cookies功能
 * @author mo.duanm
 *
 */
public interface LoginManager {
	
	
	/**
	 * 登录
	 * @return
	 */
	boolean login();
	
	/**
	 * 通过cookies登录
	 * @return
	 */
	boolean loginOnCookies();
	
	/**
	 * 获得某个cookies
	 * @param userName
	 */
	void getCookies(String userName);
	
	/**
	 * 获得一个随机的cookies
	 */
	void getRandomCookies();

}
