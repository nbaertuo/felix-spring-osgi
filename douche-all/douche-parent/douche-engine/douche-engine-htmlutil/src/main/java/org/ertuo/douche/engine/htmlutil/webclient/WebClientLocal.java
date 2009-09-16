package org.ertuo.douche.engine.htmlutil.webclient;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.ertuo.douche.dao.domain.WebProxyDo;
import org.ertuo.douche.db.hsql.HSQLServer;
import org.ertuo.douche.proxy.proxycn.CnProxyManager;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.RefreshHandler;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlSubmitInput;
import com.gargoylesoftware.htmlunit.javascript.JavaScriptEngine;

@Service
public class WebClientLocal implements InitializingBean{
	//这里添加一个依赖没有业务意义
	//因为在HSQLServer初始化的时候和自己的初始化的顺序冲突了
	//这里添加依赖后可以保证HSQLServer优先启动
	@Autowired
	private HSQLServer server;

	private final  Logger log= Logger.getLogger(WebClientLocal.class);

	private static  WebClient webClient = new WebClient(BrowserVersion.FIREFOX_3);

	@Autowired
	private CnProxyManager cnProxyManager ;

	static {
		webClient.setJavaScriptEnabled(false);
		//webClient.setThrowExceptionOnScriptError(false);
		//webClient.setThrowExceptionOnFailingStatusCode(false);
		webClient.setCssEnabled(false);
		webClient.setActiveXNative(false);
		webClient.setAppletEnabled(false);
		webClient.setCookiesEnabled(false);
		webClient.setPopupBlockerEnabled(false);
		webClient.setRedirectEnabled(false);
		webClient.setIgnoreOutsideContent(false);
		webClient.setJavaScriptEngine(new JavaScriptEngine(webClient));
	}

	 
	
	/**
	 * 附带代理的webclient
	 * @param withProxy
	 */
	public WebClientLocal(){
	}

	/**
	 * 获得一个带有代理的webclient
	 * 
	 * @return
	 */
	public WebClient getProxyWebClient() {
		
		if (cnProxyManager.getCurrentInvaidProxy() != null) {
			webClient = new WebClient(BrowserVersion.INTERNET_EXPLORER_6,
					cnProxyManager.getCurrentInvaidProxy().getUrl(),cnProxyManager.getCurrentInvaidProxy().getPort());
			webClient.setJavaScriptEnabled(false);
		}
		return webClient;
	}

	/**
	 * @param url
	 * @return 如果失败 返回null
	 */
	public HtmlPage getHtmlPageByUrl(String url) {
		HtmlPage htmlPage =null;

		try {
			htmlPage = (HtmlPage) webClient.getPage(url);
		} catch (Exception e) {
			log.error("采集页面[" + url + "]失败", e);
		}
		return htmlPage;

	}

	/**
	 * @param submit
	 * @return
	 */
	public HtmlPage getClickHtmlPage(HtmlSubmitInput submit) {
		HtmlPage replys = null;
		try {
			replys = (HtmlPage) submit.click();
		} catch (IOException e) {
			log.error("点击失败！", e);
		}

		return replys;
	}

	public void afterPropertiesSet() throws Exception {
		WebProxyDo webProxyDo=cnProxyManager.getCurrentInvaidProxy();
		if ( webProxyDo!= null) {
			webClient = new WebClient(BrowserVersion.INTERNET_EXPLORER_6,
					webProxyDo.getUrl(), webProxyDo.getPort());
			webClient.setJavaScriptEnabled(false);
		}
	}

 

	 

}
