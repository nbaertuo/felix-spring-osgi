import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import junit.framework.TestCase;

import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.UsernamePasswordCredentials;
import org.apache.commons.httpclient.cookie.CookiePolicy;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.commons.lang.time.DateUtils;
import org.ertuo.htmlutil.proxycn.domain.WebProxy;
import org.w3c.dom.DOMException;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.Dom4JReader;

public class Test extends TestCase {
	public void test_19lou_post() {
		HttpClient httpClient = new HttpClient();
		PostMethod method = new PostMethod(
				"http://www.19lou.com/passportlogin.php?action=login&referer=http%3A%2F%2Fwww.19lou.com%2F");
		method.addRequestHeader("Content-Type",
				"application/x-www-form-urlencoded;charset=GBK");
		method.addParameter("username", "summersnow88888");
		method.addParameter("password", "19854171985");
		method.addParameter("formhash", "a31eb5c8");
		// method.getParams().setCookiePolicy(CookiePolicy.RFC_2109);
		method.getParams().setCookiePolicy(CookiePolicy.IGNORE_COOKIES);
		method.setDoAuthentication(true);

		method.setRequestHeader("Cookie", "special_cookie=value");
		try {
			// int i=httpClient.executeMethod(hostConfiguration, method);
			int i = httpClient.executeMethod(method);
			System.out.println(i + method.getResponseBodyAsString());
		} catch (HttpException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void test_19lou_get() {
		HttpClient httpClient = new HttpClient();

		httpClient.getParams().setParameter(
				HttpMethodParams.HTTP_CONTENT_CHARSET, "gb2312");
		httpClient.getHostConfiguration().setProxy("¡Á¡Á¡Á.¡Á¡Á¡Á.¡Á¡Á¡Á.¡Á¡Á¡Á", 8080);
		GetMethod method = new GetMethod(
				"http://news.sina.com.cn/c/2009-08-31/231318549902.shtml");
		method.addRequestHeader("Content-Type",
				"application/x-www-form-urlencoded;charset=gb2312");
		// method.addParameter("username", "summersnow88888");
		// method.addParameter("password", "19854171985");
		// method.addParameter("formhash", "a31eb5c8");
		// method.getParams().setCookiePolicy(CookiePolicy.RFC_2109);
		// method.getParams().setCookiePolicy(CookiePolicy.IGNORE_COOKIES);
		// method.setDoAuthentication(true);

		// method.setRequestHeader("Cookie", "special_cookie=value");
		try {
			// int i=httpClient.executeMethod(hostConfiguration, method);
			int i = httpClient.executeMethod(method);

			System.out.println(i + method.getResponseBodyAsString());
		} catch (HttpException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * jsµÇÂ¼·½Ê½ÊÚÈ¨
	 */
	public void test_alipay_inner() {
		HttpClient client = new HttpClient();
		client.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET,
				"gb2312");
		GetMethod get = new GetMethod("http://www.cn.alipay-inc.com/Index.html");
		UsernamePasswordCredentials upc = new UsernamePasswordCredentials(
				"mo.duanm", "ertuo521$");
		client.getState().setCredentials(null, null, upc);
		get.setDoAuthentication(true);
		client.setConnectionTimeout(60000);
		try {
			client.executeMethod(get);
			Header[] headers = get.getResponseHeaders();
			System.out.println(get.getStatusCode());
			for (int i = 0; i < headers.length; i++) {
				System.out.println(headers[i].getName() + "="
						+ headers[i].getValue() + "\n");
			}
			// System.out.println(get.getResponseHeaders().toString());
			System.out.println(get.getResponseBodyAsString());
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void test_javaeye() {
		HttpClient httpClient = new HttpClient();
		PostMethod method = new PostMethod("http://www.javaeye.com/login");
		method.addRequestHeader("Content-Type",
				"application/x-www-form-urlencoded;charset=utf-8");
		method.addParameter("name", "summersnow88888");
		method.addParameter("password", "19854171985");
		// method.addParameter("formhash", "a31eb5c8");

		try {
			// int i=httpClient.executeMethod(hostConfiguration, method);
			int i = httpClient.executeMethod(method);
			HttpStatus l;
			System.out.println(i + method.getResponseBodyAsString());
		} catch (HttpException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}
	}

	public void test_string() {
		String a = "http://www.19lou.com/forum-1-1.html";
		String regex = ".*(forum)-[1-9]\\d{0,4}-[1][.](html)";
		
		System.out.println(a.matches(regex));
		String[] aaa = a.split(regex);
		System.out.println(aaa);

		 
		/*
		 * Date today=new Date(); Calendar calendar=Calendar.getInstance(); int
		 * year = (Calendar.getInstance()).get(Calendar.YEAR);
		 * 
		 * try { Date date=DateUtils.parseDate("09-01 21:30", new
		 * String[]{"MM-dd HH:SS"}); System.out.println(year);; } catch
		 * (DOMException e) { // TODO Auto-generated catch block
		 * e.printStackTrace(); } catch (ParseException e) { // TODO
		 * Auto-generated catch block e.printStackTrace(); }
		 */
	}
	public void test_xmap() throws Exception{
		List<WebProxy> list=new ArrayList<WebProxy>();
		WebProxy proxy=new WebProxy();
		proxy.setCheckDate(new Date());
		proxy.setId(1);
		proxy.setPort("80");
		proxy.setUrl("10.2.4.36");
		proxy.setUseDate(new Date());
		list.add(proxy);
		
		
		XStream stream=new XStream();
		OutputStream out=new FileOutputStream("d:/test.xml");
		stream.toXML(list, out);
		
		 
		
		InputStream in=new FileInputStream("d:/test.xml");
		Object o=stream.fromXML(in);
		System.out.println(o);
	}
}
