package org.ertuo.taoplugin.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegUtil {
	/**
	 * 通过正则返回一个字符串,如果有多个符合正则的中间用"\n"隔开
	 * 
	 * @param str
	 *            需要处理的字符串
	 * @param reg
	 *            正则表达式
	 * @param nest
	 *            是否嵌套查询
	 * @return 被正则处理完的字符串
	 */
	public static String getStrByReg(String str, String reg, boolean nest) {
		StringBuffer sb = new StringBuffer();
		Pattern p = Pattern.compile(reg);
		Matcher m = p.matcher(str);
		if (nest) {
			while (m.find()) {
				sb.append(m.group() + "\n");
			}
		} else {
			if (m.find()) {
				sb.append(m.group());
			}
		}
		return sb.toString();
	}

}
