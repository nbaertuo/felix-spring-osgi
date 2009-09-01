package org.ertuo.htmlutil.proxycn;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import junit.framework.TestCase;

import org.apache.commons.httpclient.ConnectTimeoutException;
import org.apache.commons.lang.time.DateUtils;
import org.ertuo.htmlutil.proxycn.domain.WebProxy;
import org.ertuo.htmlutil.webclient.WebClientLocal;
import org.w3c.dom.DOMException;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

/**
 * @author mo.duanm
 * 
 */
public class LastProxy extends TestCase {

	private static final String proxyCnUrl = "http://www.proxycn.com/html_proxy/30fastproxy-1.html";

	private static final WebClientLocal webClient = new WebClientLocal();

	private static List<WebProxy> canUseProxy = new ArrayList<WebProxy>();

	private WebProxy currentWebProxy;

	private static int useId = 1;

	public void test() {
		this.webProxyCanUse();
	}

	private void createCanUseProxy() {
		HtmlPage htmlPage = webClient.getHtmlPageByUrl(proxyCnUrl);
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
				canUseProxy.add(webProxy);
			}
		}

	}

	private boolean webProxyCanUse() {
		currentWebProxy = this.getCanUseProxy().get(useId);
		if (currentWebProxy == null) {
			useId++;
			this.webProxyCanUse();
		}
		WebClient webClient = new WebClient(BrowserVersion.INTERNET_EXPLORER_6,
				currentWebProxy.getUrl(), Integer.parseInt(currentWebProxy
						.getPort()));
		try {
			webClient.setJavaScriptEnabled(false);
			webClient.setTimeout(1000);
			HtmlPage htmlPage = (HtmlPage) webClient
					.getPage("http://www.google.cn");
			System.out.println(htmlPage.getEndLineNumber() + "  "
					+ htmlPage.asText());
		} catch (FailingHttpStatusCodeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ConnectTimeoutException e) {
			useId++;
			this.webProxyCanUse();
		} catch (SocketTimeoutException e) {
			useId++;
			this.webProxyCanUse();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return false;

	}

	public void setCurrentWebProxy(WebProxy currentWebProxy) {
		this.currentWebProxy = currentWebProxy;
	}

	public WebProxy getCurrentWebProxy() {
		currentWebProxy = this.getCanUseProxy().get(useId);
		// 测试当前代理是否可用
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

}
