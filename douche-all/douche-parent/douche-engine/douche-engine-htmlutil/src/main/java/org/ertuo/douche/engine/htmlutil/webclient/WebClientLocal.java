package org.ertuo.douche.engine.htmlutil.webclient;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.ertuo.douche.dao.domain.WebProxyDo;
import org.ertuo.douche.proxy.proxycn.LastProxy;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlSubmitInput;

@Service
public class WebClientLocal implements InitializingBean{

	private final  Logger log= Logger.getLogger(WebClientLocal.class);

	private static  WebClient webClient = new WebClient();

	@Autowired
	private LastProxy lastProxy ;

	static {
		webClient.setJavaScriptEnabled(false);
		webClient.setThrowExceptionOnScriptError(false);
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
		
		if (lastProxy.getCurrentInvaidProxy() != null) {
			webClient = new WebClient(BrowserVersion.INTERNET_EXPLORER_6,
					lastProxy.getCurrentInvaidProxy().getUrl(),lastProxy.getCurrentInvaidProxy().getPort());
			webClient.setJavaScriptEnabled(false);
		}
		return webClient;
	}

	/**
	 * @param url
	 * @return
	 */
	public HtmlPage getHtmlPageByUrl(String url) {
		HtmlPage htmlPage = null;

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
		WebProxyDo webProxyDo=lastProxy.getCurrentInvaidProxy();
		if ( webProxyDo!= null) {
			webClient = new WebClient(BrowserVersion.INTERNET_EXPLORER_6,
					webProxyDo.getUrl(), webProxyDo.getPort());
			webClient.setJavaScriptEnabled(false);
		}
	}

 

	 

}
