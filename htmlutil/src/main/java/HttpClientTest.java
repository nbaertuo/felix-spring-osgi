
import org.apache.commons.httpclient.HostConfiguration;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpConnectionManager;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.MultiThreadedHttpConnectionManager;
import org.apache.commons.httpclient.URI;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.params.HttpConnectionManagerParams;

public class HttpClientTest {
	HttpClient httpClient;
	HostConfiguration hostConf;

	public HttpClientTest() {
		HttpConnectionManager conMan = new MultiThreadedHttpConnectionManager();
		HttpConnectionManagerParams params = new HttpConnectionManagerParams();
		params.setConnectionTimeout(2000);
		params.setSoTimeout(2000);
		params.setDefaultMaxConnectionsPerHost(1);
		params.setMaxTotalConnections(4);
		conMan.setParams(params);
		httpClient = new HttpClient(conMan);
		httpClient.getParams().setConnectionManagerTimeout(2000);
		hostConf = httpClient.getHostConfiguration();
		hostConf.setProxy("localhost", 11054);
	}

	public static void main(String args[]) throws Exception {
		System.setProperty("org.apache.commons.logging.Log",
				"org.apache.commons.logging.impl.SimpleLog");
		System.setProperty("org.apache.commons.logging.simplelog.showdatetime",
				"true");
		System.setProperty(
				"org.apache.commons.logging.simplelog.log.httpclient.wire",
				"debug");
		System
				.setProperty(
						"org.apache.commons.logging.simplelog.log.org.apache.commons.httpclient",
						"debug");
		HttpClientTest clientTest = new HttpClientTest();
		clientTest.testSSLCoonection();
		clientTest.testSSLCoonection();
		clientTest.testSSLCoonection();
		clientTest.testSSLCoonection();
	}

	public void testSSLCoonection() throws Exception {
		HttpMethod method = new GetMethod();
		try {
			method.setFollowRedirects(true);
			method.setURI(new URI("http://www.sina.com/", true));
			// method.setURI(new URI(
			// "https://untitled-882801.herokugarden.com/test/status?status=403"
			// , true));

			httpClient.executeMethod(hostConf, method);

			System.out.println("Status Line :" + method.getStatusLine());
		} catch (Exception e) {
			e.getMessage();
			e.printStackTrace();
		} finally {
			method.releaseConnection();
		}
	}

}
