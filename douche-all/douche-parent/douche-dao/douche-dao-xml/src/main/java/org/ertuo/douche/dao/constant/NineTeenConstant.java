/**
 * 
 */
package org.ertuo.douche.dao.constant;

/**
 * 19楼常量
 * @author mo.duanm
 *
 */
public class NineTeenConstant extends DoucheConstant {
	
	/**
	 * 域
	 */
	public static String domain=".19lou.com";
	
	/**
	 * cookies采集的开始路径
	 */
	public static String cookiesPath="/";
	
	/**
	 * 主机地址
	 */
	public final static String host="http://www.19lou.com";
	
	/**
	 * 不是第一页的标志
	 */
	public static String isNotFirstPageToken="下一页";
	
	/**
	 * 19楼栏目url的正则
	 */
	public static String categoryReg="(http)://[a-z]{2,9}.(19lou).(com)$";
	
	/**
	 * 登录地址
	 */
	public static String login_url = host+"/passportlogin.php?action=login";
	
	/**
	 * 登录form中action
	 */
	public static String login_action = host+"/passportlogin.php?action=login&referer=http%3A%2F%2Fwww.19lou.com%2F";
	
	/**
	 * 用户名集合
	 */
	public static String[] userNames=new String[]{
		                                       "summersnow88",
		                                       "summersnow888",
		                                       "summersnow8888",
		                                       "summersnow88888",
		                                       "summersnow888888",
		                                       "summersnow8888888",
		                                       "summersnow88888888"};
	
	/**
	 * 密码
	 */
	public static String password="keyidaxie";

}
