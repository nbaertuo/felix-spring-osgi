
package org.ertuo.credit.taobao.lt.utils

import java.util.List;
import java.util.logging.Logger;
import java.util.regex.Matcher 
import java.util.regex.Pattern 
import org.apache.commons.lang.StringUtils 

/**
 * 拆分工具类
 * @author mo.duanm
 *
 */
class SpitUtils {
	private static final Logger log = Logger.getLogger(SpitUtils.class.getName())
	/**
	 * 将指定字符串拆分成指定长度的字符串
	 * @param pnum
	 * @param arrayLength
	 * @param arrs
	 * @return
	 */
	static void  splitLengthStr(String pnum,int arrayLength,Map<String,String> arrs){
		if(StringUtils.isBlank(pnum)||pnum.length()<arrayLength){
			return null;
		}
		def nest={str->
			if(str.length()==arrayLength){
				// log.info "添加"+str
				arrs.put str, str
			}else{
				this.splitLengthStr str, arrayLength, arrs
			}
		}
		for(i in 1..<pnum.length()){
			String a=pnum.substring (0,i)
			nest(a)
			String b=pnum.substring (i)
			nest(b)
		}
	}
	
	/**
	 * 计算X秒能处理的个数
	 * @return
	 */
	public static int genMaxResults(int min,String pnum,int arrayLength){
		Date d1=new Date()
		def map =[:]
		splitLengthStr(pnum, 3, map)
		Date d2=new Date()
		def withMin=(d2.getTime()-d1.getTime())/1000
		def rs=Math.floor((min)/withMin)
		log.info ("analyse $pnum use $withMin min. in $min min, can process $rs ")
		return rs;
	}
	
	/**
	 * 在指定字符串中查找指定字符串出现的次数
	 * @param str
	 * @param reg
	 * @return
	 */
	private static List<String> nestSpitStr(String str,String reg){
		def rs=[]
		Pattern p = Pattern.compile(reg);
		Matcher m = p.matcher(str);
		while(m.find()){
			rs.add m.group()
		}
		return rs;
	}
}
