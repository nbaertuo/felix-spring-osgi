package org.ertuo.number.codex.engine

import org.apache.log4j.Logger;
import org.apache.log4j.spi.LoggerFactory;

interface LtCodexEngine {
	
	/**
	 * 过滤号码
	 * @param phoneNumber 需要被过滤的号码
	 * @return 过滤后的号码,如果规则不通过，返回null
	 */
	String filterNum(String phoneNumber)
	
	

}
