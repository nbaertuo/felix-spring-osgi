import junit.framework.TestCase;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.ertuo.htmlutil.webclient.WebClientLocal;

import com.gargoylesoftware.htmlunit.html.HtmlForm;
import com.gargoylesoftware.htmlunit.html.HtmlHiddenInput;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlPasswordInput;
import com.gargoylesoftware.htmlunit.html.HtmlSubmitInput;
import com.gargoylesoftware.htmlunit.html.HtmlTextArea;
import com.gargoylesoftware.htmlunit.html.HtmlTextInput;

/**
 * 测试19lou
 * @author mo.duanm
 *
 */
public class WebUtilTest extends TestCase {
	private static final WebClientLocal webClient = new WebClientLocal();
	//登陆地址
	private static String login_url = "http://www.19lou.com/passportlogin.php?action=login";
	
	//登陆action
	private static String login_action = "http://www.19lou.com/passportlogin.php?action=login&referer=http%3A%2F%2Fwww.19lou.com%2F";
	
	//帖子列表
	private static String news_list_url = "http://www.19lou.com/";
	
	//回帖内容
	private static String guanggao="顶！支持楼主！";
		//"<span style='font-weight: bold;'>桔子手机厂家直销<br><br><font size='4'><span style='color: Red;'>http://item.taobao.com/auction/item_detail-db1-02e27c331e299008cb3f672e190d4953.htm</span><br><br></font></span>";
		
		
    //"桔子手机厂家直销http://item.taobao.com/auction/item_detail-db1-02e27c331e299008cb3f672e190d4953.htm";
 
	

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
		HtmlPage page1 = webClient.getHtmlPageByUrl(login_url);
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
		webClient.getClickHtmlPage(button);
	}

	private void answer() {
		HtmlPage loginAfterPagee=webClient.getHtmlPageByUrl("http://www.19lou.com/post.php?action=reply&fid=132&tid=20808073&extra=page%3D1");
		 
		HtmlForm postform = (HtmlForm) loginAfterPagee
				.getElementById("postform");
		// 标题
		HtmlTextInput subject = (HtmlTextInput) postform.getInputByName("subject");
		// 内容
		HtmlTextArea message = postform.getTextAreaByName("message");

		// 设置value
		message.focus();
		message.setText(guanggao);
		message.blur();

		System.out.println(message.getOnBlurAttribute());

		//subject.setValueAttribute("此帖不能沉!");

		final HtmlSubmitInput replysubmit = (HtmlSubmitInput) postform
				.getInputByName("replysubmit");
		webClient.getClickHtmlPage(replysubmit);
	}
	
	

}
