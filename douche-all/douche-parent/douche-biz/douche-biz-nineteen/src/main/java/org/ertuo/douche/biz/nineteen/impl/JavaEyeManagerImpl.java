package org.ertuo.douche.biz.nineteen.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.httpclient.Cookie;
import org.apache.commons.lang.StringUtils;
import org.ertuo.douche.biz.nineteen.JavaEyeManager;

import com.gargoylesoftware.htmlunit.CookieManager;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
import com.gargoylesoftware.htmlunit.html.HtmlForm;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlSubmitInput;
import com.gargoylesoftware.htmlunit.html.HtmlTextArea;

/**
 * @author mo.duanm
 *
 */
public class JavaEyeManagerImpl implements JavaEyeManager {

	private void javaeyeAdmin() {
		WebClient webClient = new WebClient();
		//webClient.setCookiesEnabled(true);
		webClient.setCookieManager(new CookieManager());
		this.setJavaeyeCookies(webClient);
		webClient.setJavaScriptEnabled(true);
		webClient.setThrowExceptionOnScriptError(false);
		HtmlPage htmlPage = null;
		try {
			htmlPage = webClient
					.getPage("http://www.javaeye.com/forums/board/Java");

		} catch (Exception e) {
			e.printStackTrace();
		}

		//获得帖子列表
		List<String> threads = this.getThreadsByUrl(htmlPage);
		for (String thread : threads) {
            System.out.println(thread);
			try {
				HtmlPage page = webClient.getPage(thread);
				if (page != null) {
					HtmlForm postform = (HtmlForm) page
							.getElementById("quick_reply_form");
					// 内容
					HtmlTextArea message = postform.getElementById("post_body");
					// 设置value
					message.focus();
					message.setText("测试是否可以灌水!");
					message.blur();
					
					HtmlSubmitInput replysubmit = (HtmlSubmitInput) postform
							.getElementById("quick_reply_button");
					HtmlPage replays = replysubmit.click();
					System.out.println(replays.asText());
				}
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
	}

	private void setJavaeyeCookies(WebClient webClient) {
		Cookie cookie = new Cookie();
		cookie.setDomain(".javaeye.com");
		cookie.setPath("/");
		cookie
				.setValue("remember_me=yes; login_token=62849_d03518dbca13952327d166077c55dac5; _javaeye3_session_=BAh7BzoMdXNlcl9pZGkCgfUiCmZsYXNoSUM6J0FjdGlvbkNvbnRyb2xsZXI6OkZsYXNoOjpGbGFzaEhhc2h7AAY6CkB1c2VkewA%3D--397a4de4760bd5f3b01076f9b94b28a9affbe4cc");
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
				// String regex = ".*(forum)-[1-9]\\d{0,4}-[1][.](html)";
				String regex = ".*(topic)\\/[0-9]\\d{0,9}";
				if (href.matches(regex)) {
					threads.add("http://www.javaeye.com" + href);

				}
			}
		}

		return threads;
	}
}
