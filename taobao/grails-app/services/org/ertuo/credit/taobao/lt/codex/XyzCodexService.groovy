package org.ertuo.credit.taobao.lt.codex

import java.util.Date;
import java.util.logging.Logger;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.ertuo.credit.taobao.lt.utils.SpitUtils 

class XyzCodexService {
	
	
	private static final Logger log = Logger.getLogger(XyzCodexService.class.getName())
	
	
	/**
	 * 对数组号码进行处理
	 * @param numPhone
	 */
	 String process(String pnum){
		def map=[:]
		Date d1=new Date()
		SpitUtils.splitLengthStr (pnum, 3, map)
		Date d2=new Date()
		log.info ("analyse  $pnum "+(d2.getTime()-d1.getTime())/1000+" min .result size $map.size()")
		def spitStr=null;
		map.each {
			def arrs=SpitUtils.nestSpitStr( pnum, "$it.key")
			if(arrs.size()>1){
				spitStr= arrs.get(0)
			}
		}
		return spitStr
	}
}
