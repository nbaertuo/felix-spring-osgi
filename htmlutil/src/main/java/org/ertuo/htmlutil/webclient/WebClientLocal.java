package org.ertuo.htmlutil.webclient;


import java.io.IOException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlSubmitInput;

public class WebClientLocal {
	
	private final Log log = LogFactory.getLog(WebClientLocal.class);
	
	private static final WebClient webClient = new WebClient();
	
	
	static {
		webClient.setJavaScriptEnabled(false);
		webClient.setThrowExceptionOnScriptError(false);
	}
	
	/**
	 * @param url
	 * @return
	 */
	public HtmlPage getHtmlPageByUrl(String url){
		HtmlPage htmlPage=null;
	 
			try {
				htmlPage = (HtmlPage) webClient
						.getPage(url);
			} catch (Exception e) {
				 log.error("采集页面["+url+"]失败",e);
			}
		return htmlPage;	
			
	}
	
	/**
	 * @param submit
	 * @return
	 */
	public HtmlPage getClickHtmlPage(HtmlSubmitInput submit){
		HtmlPage replys = null;
		try {
			replys = (HtmlPage) submit.click();
		} catch (IOException e) {
			log.error("点击失败！",e);
		}
		
		return replys;
	}
	
	

}

