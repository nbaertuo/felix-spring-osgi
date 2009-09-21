package org.ertuo.douche.engine.htmlutil.webclient;

import com.gargoylesoftware.htmlunit.BrowserVersion;
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
	static  WebClient webClient = new WebClient(BrowserVersion.INTERNET_EXPLORER_7);
	 
 
	/**
	 * 获得一个带有代理的webclient
	 * 
	 * @return
	 */
	public WebClient getProxyWebClient();
	
	/**
	 * 根据url返回html页面
	 * @param url
	 * @return 如果失败 返回null
	 */
	public HtmlPage getHtmlPageByUrl(String url);
	
	
	/**
	 * 返回一个按钮提交后的页面
	 * @param submit 提交动作的按钮
	 * @return 如果失败 返回null
	 */
	public HtmlPage getClickHtmlPage(HtmlSubmitInput submit);
	 

}
