package org.ertuo.htmlutil.proxycn;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import org.apache.commons.lang.time.DateUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.ertuo.htmlutil.proxycn.domain.WebProxy;
import org.nuxeo.common.xmap.XMap;
import org.w3c.dom.DOMException;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlSubmitInput;

/**
 * 代理存储类
 * <p>
 * <li>提供代理存储，提供当前有效代理
 * </p>
 * @author mo.duanm
 *
 * 
 */
public class LastProxy  {
	
	private final Log log=LogFactory.getLog(LastProxy.class);

	/**
	 * 获得代理的web页面
	 */
	private static final String proxyCnUrl = "http://www.proxycn.com/html_proxy/http-1.html";
	
	
	private static final String testUrl="http://www.19lou.com/";


	/**
	 * 可用的代理集合
	 */
	private static List<WebProxy> canUseProxy = new ArrayList<WebProxy>();

	/**
	 * 当前有效的代理,系统内都使用这个代理连接
	 */
	private WebProxy currentWebProxy;
	
	

	/**
	 * 当前在使用中的代理序列,每次使用后，都自增一个
	 */
	private static int useId = 0;
	
	
    private static final WebClient webClient = new WebClient();
    
    private XMap xmap=new XMap();
	
	
	static {
		webClient.setJavaScriptEnabled(false);
		webClient.setThrowExceptionOnScriptError(false);
	}

	public void test() {
		this.getCurrentWebProxy();
	}

	private void createCanUseProxy() {
		HtmlPage htmlPage = this.getHtmlPageByUrl(proxyCnUrl);
		NodeList br = htmlPage.getElementsByTagName("tr");
		for (int i = 0; i < br.getLength(); i++) {
			Node node = br.item(i);
			String context = node.getTextContent();
			if (context.contains("whois")) {
				WebProxy webProxy = new WebProxy();
				// 第一个节点id
				Node firstNode = node.getFirstChild();
				try {
					webProxy.setId(useId
							+ Integer.parseInt(firstNode.getTextContent()));
				} catch (NumberFormatException e) {
					continue;
				}

				// 第二个节点ip
				Node secondNode = firstNode.getNextSibling();
				// JavaScriptEngine engine=new JavaScriptEngine(new
				// WebClient());
				// Object o=engine.execute(htmlPage,secondNode.getTextContent(),
				// "aaa", 0);

				// 使用一个正则来取IP 取出的是所以点和数字
				String[] ips = secondNode.getTextContent().split("[^1-9||^.]");
				String ip = "";

				for (String _ip : ips) {
					// 拼接数字
					ip = ip + _ip;
				}
				// 去掉拼接中.重复和.开头
				ip = ip.replace("..", ".").replaceFirst(".", "");
				webProxy.setUrl(ip);

				// 第三个节点port
				Node threeNode = secondNode.getNextSibling();
				webProxy.setPort(threeNode.getTextContent());
				// 第六个节点checkDate
				Node sixNode = threeNode.getNextSibling().getNextSibling()
						.getNextSibling();
				try {
					// 拼接当前年份+检查时间
					Date date = DateUtils.parseDate((Calendar.getInstance())
							.get(Calendar.YEAR)
							+ "-" + sixNode.getTextContent(),
							new String[] { "yyyy-MM-dd HH:SS" });
					webProxy.setCheckDate(date);
				} catch (DOMException e) {
					e.printStackTrace();
				} catch (ParseException e) {
					e.printStackTrace();
				}
				//测试是否可用
				if(this.getCanUseWebProxy(webProxy)!=null){
					canUseProxy.add(webProxy);
				}
				
			}
		}
        /*if(canUseProxy!=null){
        	WebProxy proxy=new WebProxy();
        	try {
				String webProxyXml=xmap.asXmlString(proxy, "utf-8", null);
				System.out.println(webProxyXml);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }*/
	}

	
	/**
	 * 测试传入的代理是否可用
	 * @param webProxy
	 * @return
	 */
	private WebProxy getCanUseWebProxy(WebProxy webProxy){
		WebClient webClient = new WebClient(BrowserVersion.INTERNET_EXPLORER_6,
				webProxy.getUrl(), Integer.parseInt(webProxy
						.getPort()));
		try {
			webClient.setJavaScriptEnabled(false);
			webClient.setTimeout(1000);
			webClient.getPage(testUrl);
			log.info("代理["+webProxy.getUrl()+";"+webProxy.getPort()+"]可用");
			
		} catch (Exception e) {
			log.error("代理["+webProxy.getUrl()+";"+webProxy.getPort()+"]"+e.getMessage());
			return null;
		}
		return webProxy;
		
	}

	public void setCurrentWebProxy(WebProxy currentWebProxy) {
		this.currentWebProxy = currentWebProxy;
	}

	public WebProxy getCurrentWebProxy() {
		//循环超过了当前存储的代理总和，清零
		if(useId>=this.getCanUseProxy().size()){
			useId=0;
		}
		currentWebProxy = this.getCanUseProxy().get(useId);
		useId++;
		if (currentWebProxy == null) {
			//循环回调
			this.getCurrentWebProxy();
		}
		//当前代理不可用
		if(this.getCanUseWebProxy(currentWebProxy)==null){
			//循环回调
			this.getCurrentWebProxy();
		}
		return currentWebProxy;
	}

	public List<WebProxy> getCanUseProxy() {
		if (canUseProxy.size() == 0) {
			this.createCanUseProxy();
		}
		return canUseProxy;
	}

	public void setCanUseProxy(List<WebProxy> canUseProxy) {
		LastProxy.canUseProxy = canUseProxy;
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
