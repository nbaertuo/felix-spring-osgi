import java.io.IOException;

import junit.framework.TestCase;

import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HostConfiguration;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.UsernamePasswordCredentials;
import org.apache.commons.httpclient.cookie.CookiePolicy;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;

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
		client.getParams().setParameter(
				HttpMethodParams.HTTP_CONTENT_CHARSET, "gb2312");
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
}
