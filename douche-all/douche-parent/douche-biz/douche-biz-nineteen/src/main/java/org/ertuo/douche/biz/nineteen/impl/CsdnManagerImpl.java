package org.ertuo.douche.biz.nineteen.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.httpclient.Cookie;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.gargoylesoftware.htmlunit.CookieManager;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.DomNodeList;
import com.gargoylesoftware.htmlunit.html.FrameWindow;
import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlForm;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlSubmitInput;
import com.gargoylesoftware.htmlunit.html.HtmlTextArea;

/**
 * @author mo.duanm
 * 
 */
public class CsdnManagerImpl {

	private final Logger log = Logger.getLogger(CsdnManagerImpl.class);

	private final String csdnBBS = "http://forum.csdn.net/SList/ASPDotNET";

	private final String csdnPostReg = ".*(http://topic.csdn.net)/(u)/[0-9]\\d{7,7}/[0-9]\\d{0,9}/.*.(html)";

	private final String csdnCookies = "cnt_uid_www=c06135a2aaa3c2054b74cb556e770e88; __utma=17226283.2036924918.1249615133.1253287412.1253289685.8; __utmz=17226283.1252771086.6.5.utmccn=(organic)|utmcsr=google|utmctr=org.springframework.aop.framework.Cglib2AopProxy+-+Unable+to+proxy+method|utmcmd=organic; __message_sys_msg_id=160; __message_gu_msg_id=0; __message_cnel_msg_id=0; __gads=ID=53d587279d06074a:T=1249715747:S=ALNI_MaoZ-VpJ1vUX-6SGhKIG5-KM248Eg; UN=nbaertuo; _user_sid=556d31510a748aa91054608843429233; LastVisitedForum=8b327318-2c42-4a49-8aed-ef8c33697e7f*3931c03e-b66c-4189-a4f0-210fd9501803; __message_district_code=330000; ABCDEF=hbWhkUhCWfQiA271xLkFuJGGWovixtojJRI%252bwS%252bosuKqnOwM%252fzLQqieaBO3kwVk0cjchWx%252fV17DuZ4OxMcmmogMc453HGrvfZdD2spTRozoPlKdjAPk3ANsNprPHK1CH%252flIjvSTWfWqDBodiLDuDA98qb2ALiosVc4vZKua5OvoOJc8%252bODcqC%252fft%252fSs0xGSuRcGX%252fa4svJCqpG4xW0W5Duu9UWH0%252bfVONc8TWzusXMATHA59YRAI9A%253d%253d; QWERTOP=6546; activeUserName=nbaertuo; UserName=nbaertuo; PName=952ae09b0c19d00669f814681026a37f22341c1f105d2132e129a3; userid=1067784; uchome_auth=18c6p%2BviWKdSu4kl%2Bl32sEHPXGaADfEPpKB961ZGI0eEG7nlIlBwSCZJrF50hoNg26yW2bN3F2ICL8brbe5%2B%2B4CXauym; uchome_loginuser=nbaertuo; __utmb=17226283; __utmc=17226283";

	// private final String xingBaHost="http://www.19lou.com/";

	// private final String
	// xingBaCooies="pdA_auth=5670blQo026aYcNct1MBHyG3vjkrNniVShvl%2BPf%2BnSu1XL9B%2B6uu6%2FetKu8qtv8Fm7IYpmoBrR4dsqvqkILPYpyiXgdFSw; pdA_sid=Zrpg6Q; pdA_oldtopics=D975385D; pdA_visitedfid=96; __utma=145057586.1009059440.1253295059.1253295059.1253295078.2; __utmb=145057586.1.10.1253295078; __utmz=145057586.1253295059.1.1.utmcsr=(direct)|utmccn=(direct)|utmcmd=(none); checkpm=1; __utmc=145057586";

	// private final String xingBaBBS="http://74.55.54.148/forum-96-2.html";

	// private final String
	// xingBaReg=".*(thread)-[0-9]\\d{0,9}-[0-9]-[0-9].(html)";
	public void csdn() {
		String a = "##";
		String b = "javascript:;";
		String submitHrefReg = "(javascript).*|#.*";
		log.debug(a.matches(submitHrefReg) + " " + b.matches(submitHrefReg));
		// this.csdnAdmin();
	}

	private void csdnAdmin() {
		WebClient webClient = new WebClient();
		// webClient.setCookiesEnabled(true);
		webClient.setCookieManager(new CookieManager());
		this.setJavaeyeCookies(webClient);
		webClient.setJavaScriptEnabled(true);
		webClient.setThrowExceptionOnScriptError(false);
		HtmlPage htmlPage = null;
		try {
			htmlPage = webClient.getPage(csdnBBS);
		} catch (Exception e) {
			e.printStackTrace();
		}

		// 获得帖子列表
		List<String> threads = this.getThreadsByUrl(htmlPage);
		for (String thread : threads) {
			log.debug(thread);
			HtmlPage page;
			try {
				page = webClient.getPage(thread);
				this.queryIframePage(page);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}

	private void setJavaeyeCookies(WebClient webClient) {
		Cookie cookie = new Cookie();
		cookie.setDomain(".csdn.net");
		cookie.setPath("/");
		cookie.setValue(csdnCookies);
		webClient.getCookieManager().addCookie(cookie);
	}

	private List<String> getThreadsByUrl(HtmlPage page) {
		List<String> threads = new ArrayList<String>();
		if (page == null) {
			return threads;
		}
		List<HtmlAnchor> auchors = page.getAnchors();
		for (HtmlAnchor htmlAnchor : auchors) {
			String href = htmlAnchor.getHrefAttribute();
			if (StringUtils.isNotBlank(href)) {
				if (href.matches(csdnPostReg)) {
					/*
					 * String fid=DiscuzUtil.getFid(xingBaBBS); String
					 * tid=DiscuzUtil.getTid(href);
					 * if(StringUtils.isBlank(tid)||StringUtils.isBlank(fid)){
					 * continue; }
					 */
					threads.add(href);

				}
			}
		}

		return threads;
	}

	/**
	 * 查询所有iframe的page
	 * 
	 * @param htmlPage
	 */
	private void queryIframePage(HtmlPage htmlPage) {
		if (htmlPage == null) {
			return;
		}

		List<FrameWindow> frames = htmlPage.getFrames();
		if (frames != null) {
			for (FrameWindow frameWindow : frames) {
				this.submitPage((HtmlPage) frameWindow.getEnclosedPage());
			}
		}

	}

	private void submitPage(HtmlPage page) {
		try {

			if (page != null) {

				// 查找textarea
				DomNodeList<HtmlElement> textareas = page
						.getElementsByTagName("textarea");

				if (textareas == null || textareas.size() != 1) {
					// log.error("页面["+page+"]中回复字段个数不等于一");
					return;
				}

				HtmlTextArea message = (HtmlTextArea) textareas.get(0);
				// 设置value
				message.setText("不让我下载，只能灌水赚积分啦!");

				List<HtmlForm> forms = page.getForms();

				if (forms == null || forms.size() != 1) {
					// log.debug("页面["+page+"]中form个数不等于一");
					return;
				}

				HtmlForm htmlForm = forms.get(0);
				// 查找submit
				List<HtmlElement> submits = htmlForm.getElementsByAttribute(
						"input", "type", "submit");

				if (submits == null || submits.size() != 1) {
					// log.error("页面["+page+"]中提交按钮个数不等于一");
					return;
				}
				HtmlSubmitInput replysubmit = (HtmlSubmitInput) submits.get(0);
				HtmlPage replays = replysubmit.click();
				log.debug(replays.asText());
				Thread.sleep(10 * 1000);
			}

		} catch (Exception e) {
			log.error("回复异常", e);
		}
	}
}
