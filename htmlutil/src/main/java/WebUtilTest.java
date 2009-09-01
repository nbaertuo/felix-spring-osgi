import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;
import java.util.logging.Logger;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import junit.framework.TestCase;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
import com.gargoylesoftware.htmlunit.html.HtmlDivision;
import com.gargoylesoftware.htmlunit.html.HtmlForm;
import com.gargoylesoftware.htmlunit.html.HtmlHiddenInput;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlPasswordInput;
import com.gargoylesoftware.htmlunit.html.HtmlSubmitInput;
import com.gargoylesoftware.htmlunit.html.HtmlTextArea;
import com.gargoylesoftware.htmlunit.html.HtmlTextInput;

public class WebUtilTest extends TestCase {
	private final Log log = LogFactory.getLog(WebUtilTest.class);
	private static String login_url = "http://www.19lou.com/passportlogin.php?action=login";
	private static String login_action = "http://www.19lou.com/passportlogin.php?action=login&referer=http%3A%2F%2Fwww.19lou.com%2F";
	private static final WebClient webClient = new WebClient();
	private static String news_list_url = "http://www.19lou.com/";
	private static String guanggao="<span style='font-weight: bold;'>桔子手机厂家直销<br><br><font size='4'><span style='color: Red;'>http://item.taobao.com/auction/item_detail-db1-02e27c331e299008cb3f672e190d4953.htm</span><br><br></font></span>";
		
		
    //"桔子手机厂家直销http://item.taobao.com/auction/item_detail-db1-02e27c331e299008cb3f672e190d4953.htm";
 
	static {
		webClient.setJavaScriptEnabled(false);
		webClient.setThrowExceptionOnScriptError(false);
	}

	public void test_19lou() {
		// 登录
		this.login();
		// 获得热门列表
		// 回复热门话题
		this.answer();
	}

	/**
	 * 登录
	 */
	private void login() {
		HtmlPage page1 = this.getHtmlPageByUrl(login_url);
		final HtmlForm form = page1.getFormByName("login");
		form.setActionAttribute(this.login_action);

		final HtmlSubmitInput button = (HtmlSubmitInput) form
				.getInputByName("loginsubmit");
		final HtmlTextInput username = (HtmlTextInput) form
				.getInputByName("username");
		final HtmlPasswordInput password = (HtmlPasswordInput) form
				.getInputByName("password");
		final HtmlHiddenInput formhash = (HtmlHiddenInput) form
				.getInputByName("formhash");
		formhash.setValueAttribute("a31eb5c8");
		username.setValueAttribute("summersnow88888");
		password.setValueAttribute("19854171985");
		this.getClickHtmlPage(button);
	}

	private void answer() {
		HtmlPage loginAfterPagee=this.getHtmlPageByUrl("http://www.19lou.com/post.php?action=reply&fid=132&tid=20808073&extra=page%3D1");
		 
		HtmlForm postform = (HtmlForm) loginAfterPagee
				.getElementById("postform");
		// 标题
		HtmlTextInput subject = postform.getInputByName("subject");
		// 内容
		HtmlTextArea message = postform.getTextAreaByName("message");

		// 设置value
		message.focus();
		message.setText(guanggao);
		message.blur();

		System.out.println(message.getOnBlurAttribute());

		subject.setValueAttribute("此帖不能沉!");

		final HtmlSubmitInput replysubmit = (HtmlSubmitInput) postform
				.getInputByName("replysubmit");
		this.getClickHtmlPage(replysubmit);
	}
	
	/**
	 * @param url
	 * @return
	 */
	private HtmlPage getHtmlPageByUrl(String url){
		HtmlPage htmlPage=null;
	 
			try {
				htmlPage = webClient
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
	private HtmlPage getClickHtmlPage(HtmlSubmitInput submit){
		HtmlPage replys = null;
		try {
			replys = (HtmlPage) submit.click();
		} catch (IOException e) {
			log.error("点击失败！",e);
		}
		
		return replys;
	}

}
