import java.util.List;
import java.util.Map;

import junit.framework.TestCase;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.ertuo.htmlutil.nineteen.BiteSup;
import org.ertuo.htmlutil.webclient.WebClientLocal;

import com.gargoylesoftware.htmlunit.html.HtmlForm;
import com.gargoylesoftware.htmlunit.html.HtmlHiddenInput;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlPasswordInput;
import com.gargoylesoftware.htmlunit.html.HtmlSubmitInput;
import com.gargoylesoftware.htmlunit.html.HtmlTextInput;

/**
 * 测试19lou
 * @author mo.duanm
 *
 */
public class WebUtilTest extends TestCase {
	
	private final Log log=LogFactory.getLog(WebUtilTest.class);
	
	private static final WebClientLocal webClient = new WebClientLocal(false);
	//登陆地址
	private static String login_url = "http://www.19lou.com/passportlogin.php?action=login";
	
	//登陆action
	private static String login_action = "http://www.19lou.com/passportlogin.php?action=login&referer=http%3A%2F%2Fwww.19lou.com%2F";
	
	//首页
	private static String index_url = "http://www.19lou.com/";
	
	
		//"<span style='font-weight: bold;'>桔子手机厂家直销<br><br><font size='4'><span style='color: Red;'>http://item.taobao.com/auction/item_detail-db1-02e27c331e299008cb3f672e190d4953.htm</span><br><br></font></span>";
		
		
    //"桔子手机厂家直销http://item.taobao.com/auction/item_detail-db1-02e27c331e299008cb3f672e190d4953.htm";
 
	

	public void test_19lou() {
		// 登录
		this.login();
		// 获得热门列表
		this.getNewsTitles();
		// 回复热门话题
		//this.answer();
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
	
	private List<String> getNewsTitles(){
		
		BiteSup biteSup=new BiteSup();
		List<String> floorList=biteSup.getFloor();
		for (String floor : floorList) {
			Map<String,String> newsMap=biteSup.getNewsList(floor);
			 
			for (String id : newsMap.keySet()) {
				biteSup.answer(newsMap.get(id),id);
			}
		}
		
		return null;
		
	}


	
 

}
