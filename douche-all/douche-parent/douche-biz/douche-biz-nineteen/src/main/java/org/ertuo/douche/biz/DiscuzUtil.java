package org.ertuo.douche.biz;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

/**
 * Discuz论坛工具类
 * 
 * @author Administrator
 * 
 */
public class DiscuzUtil {

	private final static Logger log = Logger.getLogger(DiscuzUtil.class);

	/**
	 * 取得链接中长度大于2的楼层id
	 * 
	 * @param href
	 * @return
	 */
	public static String getFid(String href) {

		if (StringUtils.isBlank(href)) {
			throw new IllegalArgumentException("getTid参数为空");
		}

		String fidReg = ".*(forum)-[0-9]\\d{1,9}-[0-9].(html)";
		String fidNumReg = "(forum)-[0-9]\\d{1,9}-";

		if (!href.matches(fidReg)) {
			log.warn("传入链接[" + href + "]不满足正则[" + fidReg + "]");
			return null;
		}

		String rs = null;
		Pattern p = Pattern.compile(fidNumReg, Pattern.DOTALL);
		Matcher m = p.matcher(href);
		while (m.find()) {
			rs = m.group();
		}
		if (StringUtils.equals(rs, href)) {
			log.warn("传入链接[" + href + "]不满足正则[" + fidNumReg + "],找不到tid");
			return null;
		}
		return rs.replaceAll("-", "").replaceAll("forum", "");

	}

	/**
	 * 取得链接中长度大于7的帖子id
	 * 
	 * @param href
	 * @return 如果链接中没有满足条件的返回null
	 */

	public static String getTid(String href) {

		if (StringUtils.isBlank(href)) {
			throw new IllegalArgumentException("getTid参数为空");
		}

		// 是否是Discuz链接的正则
		String tidReg = ".*(thread)-[0-9]\\d{6,9}-[0-9]-[0-9].(html)";
		// 取链接中tid的正则
		String tidNumReg = "[0-9]\\d{6,9}";

		if (!href.matches(tidReg)) {
			log.warn("传入链接[" + href + "]不满足正则[" + tidReg + "]");
			return null;
		}

		String rs = null;
		Pattern p = Pattern.compile(tidNumReg, Pattern.DOTALL);
		Matcher m = p.matcher(href);
		while (m.find()) {
			rs = m.group();
		}
		if (StringUtils.equals(rs, href)) {
			log.warn("传入链接[" + href + "]不满足正则[" + tidNumReg + "],找不到tid");
			return null;
		}
		return rs;
	}

	public void test() {
		String f_href = "http://74.55.54.148/forum-96-2.html";
		String t_href = "http://74.55.54.148/thread-1005189-1-2.html";
		String tid = DiscuzUtil.getTid(t_href);
		String fid = DiscuzUtil.getFid(f_href);
		log.debug(tid + " " + fid);
	}

}
