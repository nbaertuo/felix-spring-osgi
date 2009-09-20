package org.ertuo.douche.engine.htmlutil.webclient.impl;

import java.io.IOException;
import java.net.MalformedURLException;

import org.apache.commons.httpclient.Cookie;
import org.apache.log4j.Logger;
import org.ertuo.douche.dao.domain.WebProxyDo;
import org.ertuo.douche.db.hsql.HSQLServer;
import org.ertuo.douche.engine.htmlutil.webclient.WebClientLocal;
import org.ertuo.douche.proxy.proxycn.CnProxyManager;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.CookieManager;
import com.gargoylesoftware.htmlunit.FormEncodingType;
import com.gargoylesoftware.htmlunit.HttpMethod;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.WebRequestSettings;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlSubmitInput;
import com.gargoylesoftware.htmlunit.util.UrlUtils;

@Service("webClientLocal")
public class WebClientLocalImpl implements WebClientLocal,InitializingBean{
	//��������һ������û��ҵ������
	//��Ϊ��HSQLServer��ʼ����ʱ����Լ��ĳ�ʼ����˳���ͻ��
	//����������������Ա�֤HSQLServer��������
	@Autowired
	private HSQLServer server;

	private final  Logger log= Logger.getLogger(WebClientLocalImpl.class);

	private static  WebClient webClient = new WebClient(BrowserVersion.INTERNET_EXPLORER_7);
	
	@Autowired
	private CnProxyManager cnProxyManager ;

	static {
		webClient.setJavaScriptEnabled(false);
		webClient.setCookieManager(new CookieManager());
		//webClient.setRedirectEnabled(true);
		
	}

	 
	
	 
	/* (non-Javadoc)
	 * @see org.ertuo.douche.engine.htmlutil.webclient.WebClientLocal#getProxyWebClient()
	 */
	public WebClient getProxyWebClient() {
		
		if (cnProxyManager.getCurrentInvaidProxy() != null) {
			webClient = new WebClient(BrowserVersion.INTERNET_EXPLORER_7,
					cnProxyManager.getCurrentInvaidProxy().getUrl(),cnProxyManager.getCurrentInvaidProxy().getPort());
			webClient.setJavaScriptEnabled(false);
		}
		return webClient;
	}

	/* (non-Javadoc)
	 * @see org.ertuo.douche.engine.htmlutil.webclient.WebClientLocal#getHtmlPageByUrl(java.lang.String)
	 */
	public HtmlPage getHtmlPageByUrl(String url) {
		HtmlPage htmlPage =null;

		try {
			htmlPage = webClient.getPage(this.getWebRequest(url));
			//htmlPage = webClient.getPage(url);
		} catch (Exception e) {
			log.error("�ɼ�ҳ��[" + url + "]ʧ��", e);
			try {
				//��������
				this.afterPropertiesSet();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			 
		}
		return htmlPage;

	}

	 
	/* (non-Javadoc)
	 * @see org.ertuo.douche.engine.htmlutil.webclient.WebClientLocal#getClickHtmlPage(com.gargoylesoftware.htmlunit.html.HtmlSubmitInput)
	 */
	public HtmlPage getClickHtmlPage(HtmlSubmitInput submit) {
		HtmlPage replys = null;
		try {
			replys = submit.click();
		} catch (IOException e) {
			log.error("���ʧ�ܣ�", e);
		}

		return replys;
	}

	/* (non-Javadoc)
	 * @see org.springframework.beans.factory.InitializingBean#afterPropertiesSet()
	 */
	public void afterPropertiesSet() throws Exception {
		WebProxyDo webProxyDo=cnProxyManager.getCurrentInvaidProxy();
		if ( webProxyDo!= null) {
			webClient = new WebClient(BrowserVersion.INTERNET_EXPLORER_7,
					webProxyDo.getUrl(), webProxyDo.getPort());
			webClient.setJavaScriptEnabled(false);
		}
		this.setCookies();
	}
	
	
	/**
	 * ����cookies
	 */
	private void setCookies(){
		
		Cookie cookie=new Cookie();
		cookie.setDomain(".19lou.com");
		cookie.setPath("/");
		
		
		cookie.setName("__utmz");
		cookie.setValue("39183075.1253168222.1.1.utmccn=(direct)|utmcsr=(direct)|utmcmd=(none)");
		
		cookie.setName("cdb_sid");
		cookie.setValue("fbd9b4871e81d458505f38158db4b884");
		
		cookie.setName("__utma");
		cookie.setValue("39183075.1866417099.1253021153.1253158239.1253161366.3");
		
		cookie.setName("cdb_cookietime");
		cookie.setValue("2592000");
		
		cookie.setName("cdb_auth");
		cookie.setValue("V19LOU_1.0_%2FG7fY7RlldSy%2F0tDGptBqmwCIIrlOFgpjf6RksQxJ%2B0qxB6z7S8A4%2FgDZINxIYTGh8f2vQ");
		
		cookie.setName("cdb_cookietime");
		cookie.setValue("2592000");
		
		cookie.setName("cdb_visitedfid");
		cookie.setValue("11D");
		
		cookie.setName("visited");
		cookie.setValue("1");
		
		cookie.setName("dm_ui");
		cookie.setValue("15078429_20090903");
		
		cookie.setName("dm_sid");
		cookie.setValue("121.0.29.231.1253158261219517");
		
		cookie.setName("__utmc");
		cookie.setValue("39183075");
		
		cookie.setName("__utmb");
		cookie.setValue("39183075");
		//summersnow88
		//cookie.setValue("__utma=39183075.1866417099.1253021153.1253169527.1253172170.6; __utmz=39183075.1253021153.1.1.utmccn=(direct)|utmcsr=(direct)|utmcmd=(none); cdb_cookietime=2592000; visited=1; dm_sid=121.0.29.231.1253158261219517; __utmc=39183075; cdb_sid=df6662c605380372b2b1b022d432f56c; cdb_auth=V19LOU_1.0_pTnVYb5mxYGy%2F0sRTchHr29YId62OQ593qXFkJhqcb4skEzl7i4Cu%2FIGZINxIYHEiMT2tg; dm_ui=15557722_20090917; __utmb=39183075; cdb_visitedfid=9D");
		//summersnow8
		cookie.setValue("__utma=39183075.1866417099.1253021153.1253255909.1253255985.13; __utmz=39183075.1253021153.1.1.utmccn=(direct)|utmcsr=(direct)|utmcmd=(none); cdb_cookietime=2592000; visited=1; cdb_oldtopics=D15689239D18531987D15525992D15894421D; cdb_fid481=1253256015; __utmb=39183075; __utmc=39183075; dm_sid=121.0.29.231.1253255996527606; cdb_fid1415=1253238714; cdb_sid=5f3ab03cba4c8f5874ac6d0601a89dd3; cdb_visitedfid=481D; cdb_auth=V19LOU_1.0_%2FG7fY7RlldSy%2F0tDGptBqmwCIIrlOFgpjf6RksQxJ%2B0qxB6z7S8A4%2FgDZINxIYTGh8f2vQ; dm_ui=15078429_20090903");
		webClient.getCookieManager().addCookie(cookie);
	}
	
	/**
	 * ���һ�����úò�����webrequest
	 * @param url
	 * @return
	 * @throws MalformedURLException
	 */
	private WebRequestSettings getWebRequest(String url) throws MalformedURLException{
		
		WebRequestSettings request=new WebRequestSettings(UrlUtils.toUrlUnsafe(url));
		request.setCharset("gb2312");
		request.setHttpMethod(HttpMethod.POST);
		request.setAdditionalHeader("Accept-Language", "zh-cn");
		request.setAdditionalHeader("Accept-Encoding", "gzip, deflate");
		//request.setAdditionalHeader("Host", "www.19lou.com");
		//request.setAdditionalHeader("User-Agent", "Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 5.1; .NET CLR 2.0.50727; CIBA)");
		request.setEncodingType(FormEncodingType.URL_ENCODED); 
		
		return request;
	}

 

	 

}