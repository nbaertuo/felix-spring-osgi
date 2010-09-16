
package taobao
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.util.regex.Matcher 
import java.util.regex.Pattern 

import org.apache.commons.lang.StringUtils;
import org.ertuo.credit.taobao.lt.codex.XyzCodexService;
import org.ertuo.credit.taobao.lt.utils.SpitUtils 

import nu.validator.htmlparser.dom.HtmlDocumentBuilder;


import junit.framework.TestCase;

class UrlFetchTest extends TestCase{
	
	void test_taobao_index(){
		try {
			URL url = new URL("http://shop.10010.com:80/number/3g/search3gnumber.jsp");
			HtmlDocumentBuilder builder=new HtmlDocumentBuilder();
			
			BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
			StringBuffer buff=new StringBuffer();
			String line=null
			while ((line = reader.readLine()) != null) {
				buff.append(line+"\n")
			}
			reader.close();
			//Document doc=builder.parse(new InputSource("http://www.taobao.com"))
			
			println(buff.toString())
		} catch (MalformedURLException e) {
			e.printStackTrace()
		} catch (IOException e) {
			e.println()
		}
	}
	
	void test_liantong(){
		
		(0..121).each{
			def params_bak=["searchNumberInfo.cityno":"017001","searchNumberInfo.processType":"numbercard",
						"searchNumberInfo.netType":"01","pageInfo.currPage":"$it","pageInfo.totalPager":"121","formname":"defaultnumber"]
			
			def params=['searchNumberInfo.cityno':'017001',
						'searchNumberInfo.order':'0',
						'pageInfo.currPage':"$it",
						'pageInfo.totalPager':'326',
						'formname':'defaultnumber',
						'searchNumberInfo.currPage2g':'0',
						'searchNumberInfo.totalPager2g':'57',
						'searchNumberInfo.oldnumberCardId':'',
						'searchNumberInfo.oldnumberCardId2g':'',
						'formname':'defaultnumber',
						'searchNumberInfo.cardID':'',
						'searchNumberInfo.isreserve':'',
						'searchNumberInfo.saleCost':'',
						'searchNumberInfo.numbercountryId':'',
						'searchNumberInfo.numbercityId':'',
						'searchNumberInfo.ruleID':'',
						'searchNumberInfo.startCost':'',
						'searchNumberInfo.onnetlong':'',
						'searchNumberInfo.lowestcost':'',
						'searchNumberInfo.nCardcost':'',
						'searchNumberInfo.payType':'',
						'searchNumberInfo.processType':'numbercard',
						'searchNumberInfo.netType':'01',
						'searchNumberInfo.searchRuleID':'',
						'searchNumberInfo.numberStartCost':'',
						'searchNumberInfo.numberCardId':'%C8%E7%C9%FA%C8%D5%2C%BC%CD%C4%EE%C8%D5%B5%C8',
						'searchNumberInfo.searchRuleID2g':'',
						'searchNumberInfo.numberStartCost2g':'',
						'searchNumberInfo.numberCardId2g':'%C8%E7%C9%FA%C8%D5%2C%BC%CD%C4%EE%C8%D5%B5%C8'
					]
			println("当前页"+"$it")
			HttpURLConnection connection=this.getConnectionWithParams("http://shop.10010.com/number/searchNumber.action", params)
			BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			Map:def numbers = [:] 
			def line=null
			while ((line= reader.readLine()) != null) {
				String temp=this.getStrings(line, "(186)[0-9]{8}")
				if(StringUtils.isNotBlank(temp)){
					numbers.put(temp,temp)
				}
			}
			reader.close();
			numbers.each { println(it) }
		}
	}
	
	private HttpURLConnection getConnectionWithParams(String conn_url,Map<String,String> params){
		String message = URLEncoder.encode("my message", "UTF-8");
		URL url = new URL(conn_url);
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		connection.setDoOutput(true);
		connection.setRequestMethod("POST");
		for (String key  : params.keySet()) {
			connection.setRequestProperty(key,params.get(key))
		}
		OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream());
		writer.write("message=" + message);
		writer.close();
		if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
			return connection
		} 
		throw new RuntimeException("链接异常["+connection.getResponseCode()+"]");
	}
	
	void test_reg(){
		this.getStrings "xx18702702748dd", "[0-9]{11}"
	}
	
	public String getStrings(String str,String reg) {
		StringBuffer sb=new StringBuffer();
		Pattern p = Pattern.compile(reg);
		Matcher m = p.matcher(str);
		if(m.find()){
			//println(m.group())
			sb.append(m.group());
		}
		return sb.toString();
	}
	
	
	void test_process(){
		XyzCodexService xyzCodex=new XyzCodexService()
		xyzCodex.process '18602702748'
	}
	
	void test_maxResults(){
		SpitUtils.genMaxResults (60, "18602702748", 3)
	}
}
