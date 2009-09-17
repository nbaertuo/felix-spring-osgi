package org.ertuo.douche.engine.htmlutil.webclient;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlSubmitInput;



/**
 * 
 * 本地连接，带有代理
 * @author mo.duanm
 *
 */
public interface WebClientLocal  {
	 
 
	/**
	 * 获得一个带有代理的webclient
	 * 
	 * @return
	 */
	public WebClient getProxyWebClient();
	
	/**
	 * @param url
	 * @return 如果失败 返回null
	 */
	public HtmlPage getHtmlPageByUrl(String url);
	
	
	/**
	 * @param submit
	 * @return
	 */
	public HtmlPage getClickHtmlPage(HtmlSubmitInput submit);
	 

}
