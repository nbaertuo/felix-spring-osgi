package org.ertuo.douche.proxy.proxycn;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.ertuo.douche.dao.domain.WebProxyDo;
import org.ertuo.douche.dao.opration.ProxyCnDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.w3c.dom.DOMException;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlSubmitInput;
import com.thoughtworks.xstream.XStream;

@Service("lastProxy")
public class LastProxyImpl implements LastProxy {

	private final Log log = LogFactory.getLog(LastProxyImpl.class);

	@Autowired
	private ProxyCnDao proxyCnDao;

	/**
	 * 获得代理的web页面
	 */
	private static final String[] proxyCnUrl = new String[] {
			"http://www.proxycn.com/html_proxy/30fastproxy-1.html",
			"http://www.proxycn.com/html_proxy/http-1.html",
			"http://fast.proxycn.com/proxy30/page1.htm",
			"http://www.proxycn.com/html_proxy/countryDX-1.html" };

	private static final String testUrl = "http://www.19lou.com/passportlogin.php?action=login";

	private final static String ip_reg = "[0-9]\\d{0,2}\\.[0-9]\\d{0,2}\\.[0-9]\\d{0,2}\\.[0-9]\\d{0,2}";

	/**
	 * 可用的代理集合 key=ip:port
	 */
	private static Map<String, WebProxyDo> canUseProxy = new HashMap<String, WebProxyDo>();

	/**
	 * 当前有效的代理,系统内都使用这个代理连接
	 */
	private WebProxyDo currentWebProxy;

	/**
	 * 当前在使用中的代理序列,每次使用后，都自增一个
	 */
	//private static int useId = 0;

	private static final WebClient webClient = new WebClient();

	static {
		webClient.setJavaScriptEnabled(true);
		webClient.setThrowExceptionOnScriptError(false);
	}

	public void createCanUseProxy() {
		for (String proxy_url : proxyCnUrl) {

			HtmlPage htmlPage = this.getHtmlPageByUrl(proxy_url);
			if(htmlPage==null){
				continue;
			}
			NodeList tds = htmlPage.getElementsByTagName("td");
			for (int i = 0; i < tds.getLength(); i++) {
				Node node = tds.item(i);
				String context = node.getTextContent();
				String ip = this.getIpByReg(context, ip_reg);

				if (StringUtils.isNotBlank(ip)) {
					WebProxyDo webProxy = new WebProxyDo();
					// 第一个节点id
					/*Node firstNode = node.getPreviousSibling();
					try {
						webProxy.setId(useId
								+ Integer.parseInt(firstNode.getTextContent()));
					} catch (NumberFormatException e) {
						continue;
					}*/

					// 第二个节点
					webProxy.setUrl(ip);

					// 第三个节点port
					Node threeNode = node.getNextSibling();
					String port=threeNode.getTextContent();
					if(StringUtils.isNotBlank(port)&&StringUtils.isNumeric(port)){
						webProxy.setPort(Integer.parseInt(port));
					}else{
						continue;
					}
					
					/*// 第六个节点checkDate
					Node sixNode = threeNode.getNextSibling().getNextSibling()
							.getNextSibling();
					try {
						// 拼接当前年份+检查时间
						Date date = DateUtils.parseDate(
								(Calendar.getInstance()).get(Calendar.YEAR)
										+ "-" + sixNode.getTextContent(),
								new String[] { "yyyy-MM-dd HH:SS" });
						webProxy.setCheckDate(date);
					} catch (DOMException e) {
						e.printStackTrace();
					} catch (ParseException e) {
						e.printStackTrace();
					}*/
					// 测试是否可用
					if (this.getCanUseWebProxy(webProxy) != null) {
						canUseProxy.put(webProxy.getUrl() + ":"
								+ webProxy.getPort(), webProxy);
					}

				}
			}
		}
		if (canUseProxy != null) {
			proxyCnDao.createProxy(canUseProxy);
		}
	}

	/**
	 * 测试传入的代理是否可用
	 * 
	 * @param webProxy
	 * @return
	 */
	private WebProxyDo getCanUseWebProxy(WebProxyDo webProxy) {
		WebClient webClient = new WebClient(BrowserVersion.INTERNET_EXPLORER_6,
				webProxy.getUrl(), webProxy.getPort());
		try {
			webClient.setJavaScriptEnabled(false);
			webClient.setTimeout(1000);
			webClient.getPage(testUrl);
			log.info("代理[" + webProxy.getUrl() + ":" + webProxy.getPort()
					+ "]可用");

		} catch (Exception e) {
			log.error("代理[" + webProxy.getUrl() + ":" + webProxy.getPort()
					+ "]" + e.getMessage());
			return null;
		}
		return webProxy;

	}

	public WebProxyDo getCurrentInvaidProxy() {
		// 循环超过了当前存储的代理总和，清零
		Map<String,WebProxyDo> webProxys=this.getCanUseProxy();
		if(webProxys.size()==0){
			return null;
		}
		int size=webProxys.size();
		Random random=new Random();
		//随机数
		int select=random.nextInt(size); 
		int i=0;
		Iterator<Entry<String, WebProxyDo>> it=webProxys.entrySet().iterator();
		//从当前map中随机取一个代理
		while(it.hasNext()){
			currentWebProxy=it.next().getValue();
			if(i==select){
				break;
			}
			i++;
		}
        
		 
		if (currentWebProxy == null) {
			// 循环回调
			this.getCurrentInvaidProxy();
		}
		// 当前代理不可用
		if (this.getCanUseWebProxy(currentWebProxy) == null) {
			//当前这个已经失效了，清除掉
			proxyCnDao.removePeoxy(currentWebProxy);
			log.info(currentWebProxy.toString()+"已经失效,清除掉");
			// 循环回调
			this.getCurrentInvaidProxy();
		}
		return currentWebProxy;
	}

	private Map<String,WebProxyDo> getCanUseProxy() {
		return proxyCnDao.getInvailProxys();
	}

	/**
	 * @param url
	 * @return
	 */
	private HtmlPage getHtmlPageByUrl(String url) {
		HtmlPage htmlPage = null;

		try {
			htmlPage = (HtmlPage) webClient.getPage(url);
		} catch (Exception e) {
			log.error("采集页面[" + url + "]失败", e);
			return null;
		}
		return htmlPage;

	}

	/**
	 * @param submit
	 * @return
	 */
	@SuppressWarnings("unused")
	private HtmlPage getClickHtmlPage(HtmlSubmitInput submit) {
		HtmlPage replys = null;
		try {
			replys = (HtmlPage) submit.click();
		} catch (IOException e) {
			log.error("点击失败！", e);
		}

		return replys;
	}

	/**
	 * 遍历一段字符中和传入正则匹配的字符
	 * 
	 * @param text
	 *            需要检查的字符
	 * @param reg
	 *            正则表达式
	 * @return 匹配的字符
	 */
	private String getIpByReg(String text, String reg) {
		String ip = null;
		Pattern p = Pattern.compile(reg, Pattern.DOTALL);
		Matcher m = p.matcher(text);
		while (m.find()) {
			ip = m.group();
		}
		return ip;
	}

}
