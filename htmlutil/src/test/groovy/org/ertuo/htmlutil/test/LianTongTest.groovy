package org.ertuo.htmlutil.test

import org.apache.log4j.Logger
import junit.framework.TestCase 
import java.util.List;
import java.util.regex.Matcher 
import java.util.regex.Pattern 


import org.apache.commons.httpclient.Cookie;
import org.apache.commons.httpclient.util.DateUtil;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.w3c.dom.Node;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlButtonInput 
import com.gargoylesoftware.htmlunit.html.HtmlForm 
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlTextInput 
import com.gargoylesoftware.htmlunit.javascript.JavaScriptEngine;

class LianTongTest extends junit.framework.TestCase {
	
	
	private final Logger log = Logger.getLogger(LianTongTest.class);
	
	
	private static Map<String,String> allPhoneNums=new HashMap<String, String>();
	
	
	private static city="武汉"
	
	private cityCookies="017%7C017001"
	
	static{
		System.setProperty("logFile.name",city)
	}
	
	
	
	
	void test_serarch(){
		StringBuffer sb=new StringBuffer();
		try {
			String loginUrl = "http://shop.10010.com/number/searchNumber.action";
			WebClient client = new WebClient(BrowserVersion.FIREFOX_3);
			client.setJavaScriptEnabled(true);
			client.setJavaScriptEngine(new JavaScriptEngine(client));
			client.setThrowExceptionOnScriptError(false);
			client.setCssEnabled(false);
			client.getCookieManager().setCookiesEnabled true
			//client.setCookiesEnabled(true)
			this.setCookies(client)
			HtmlPage page = client.getPage(loginUrl);
			
			
			//获得总页数
			int totalPages=this.getTotalPages(page.asText())
			
			log.debug ("总页数"+totalPages)
			(1..totalPages).each {
				HtmlTextInput inputpage=(HtmlTextInput)page.getElementById("inputpage")
				HtmlTextInput inputpageh=(HtmlTextInput)page.getElementById("inputpageh")
				HtmlForm form = (HtmlForm) page.getElementById("defaultnumber");
				//HtmlTextInput htmlTextInput = form.getInputByName("inputpage");
				inputpage.setAttribute("value", String.valueOf(it));
				inputpageh.setAttribute("value", String.valueOf(it));
				HtmlPage pageRs = ((HtmlButtonInput)form.getInputByValue("确定")).click()
				String numbers=this.getStrByReg(pageRs.asText(), "[0-9]{11}");
				this.getTr pageRs
				log.info("当前第  $it 页，总页数$totalPages")
				sb.append(numbers+"\r")
				//String[] num=numbers.split("\r")
				//allPhoneNums.putAll(ArrayUtils.toMap())
				/*client.getCookieManager().getCookies().each { 
				 log.debug("cookies:"+it)
				 }*/
				//log.debug(pageRs.asXml())
			}
		} catch (Exception e) {
			log.error("查询号码错误",e)
		}finally{
			//用日志方式替换啦
			//this.genFile(sb.toString())
		}
	}
	
	
	/**
	 * 查询所有的tr对象
	 * @param page
	 */
	private void getTr(HtmlPage page){
		org.w3c.dom.NodeList br=page.getElementsByTagName("tr");
		for (i in 0..<br.length){
			Node node = br.item(i);
			String context = node.getTextContent();
			def phoneNum=this.getStrByReg(context,"(186)[0-9]{8}")
			def prise=this.getStrByReg(context,"[0-9]{0,4}(元)")
			if(StringUtils.isNotBlank(prise)&&StringUtils.isNotBlank(phoneNum)){
				prise=prise.replaceAll("元", "")
				log.info("$phoneNum ${prise}元  $city")
				this.pullToServer (phoneNum, prise, city, DateUtil.formatDate(new Date(), "yyyyMMdd"))
			}
		}
	}
	
	private void pullToServer(String num,String prise,String city,String date){
		try {
			def pullUrl="http://taohaoma.appspot.com/phoneNum/add?num=$num&date=$date&prise=$prise&city=$city"
			//def pullUrl="http://localhost:8080/phoneNum/add?num=$num&date=$date&prise=$prise&city=$city"
			String loginUrl = "http://shop.10010.com/number/searchNumber.action";
			WebClient client = new WebClient(BrowserVersion.FIREFOX_3);
			client.setJavaScriptEnabled(false);
			client.setJavaScriptEngine(new JavaScriptEngine(client));
			client.setThrowExceptionOnScriptError(false);
			//client.setTimeout(30*1000)
			client.getPage(pullUrl);
		} catch (Exception e) {
			log.error("上传数据到服务器失败",e)
		}
	}
	
	
	/**
	 * 将号码生成文本
	 * @param content
	 */
	private void genFile(String content){
		FileUtils.writeByteArrayToFile(new File("d:\\$city ltPhoneNumber.txt"),content.getBytes())
	}
	
	
	
	/**
	 * 设置cookies
	 * @param webClient
	 */
	private void setCookies(WebClient webClient) {
		Cookie cookie = new Cookie();
		cookie.setDomain("shop.10010.com");
		cookie.setPath("/");
		cookie.setValue("WT_FPC=id=250615e673ddc59d4a71281433959141:lv=1283823692317:ss=1283823224528; city=$cityCookies; PackageInfo=002%2C002001; AddressInfo=002%2C%2C011; city=002%7C002001; JSESSIONID=hxjGMFWBZLPgQpxDn185NpQ1M2bFZ0PFBj1qLpYNTlpLWLwTmvdL!250258817; CheckCode=0998");
		webClient.getCookieManager().addCookie(cookie);
	}
	
	
	/**
	 * 通过正则返回一个字符串,如果有多个符合正则的中间用"\n"隔开
	 * @param str 需要处理的字符串
	 * @param reg 正则表达式
	 * @param nest 是否嵌套查询
	 * @return 被正则处理完的字符串
	 */
	private String getStrByReg(String str,String reg) {
		StringBuffer sb=new StringBuffer();
		Pattern p = Pattern.compile(reg);
		Matcher m = p.matcher(str);
		if(m.find()){
			sb.append(m.group());
		}
		return sb.toString();
	}
	/**
	 * 通过正则返回一个字符串,如果有多个符合正则的中间用"\n"隔开
	 * @param str 需要处理的字符串
	 * @param reg 正则表达式
	 * @param nest 是否嵌套查询
	 * @return 被正则处理完的字符串
	 */
	private String getNestStrByReg(String str,String reg) {
		StringBuffer sb=new StringBuffer();
		Pattern p = Pattern.compile(reg);
		Matcher m = p.matcher(str);
		while(m.find()){
			sb.append(m.group()+"\r");
		}
		return sb.toString();
	}
	
	/**
	 * 从html中获得总页数
	 * @param content
	 * @return
	 */
	private int getTotalPages(String content){
		//这里预设10000页
		String temp=this.getStrByReg(content, '(共)[0-9]{0,5}(页)')
		String totalStr=temp.replaceAll("(共)|(页)", "")
		if(StringUtils.isNumeric(totalStr)){
			return Integer.valueOf(totalStr);
		}else{
			return 0;
		}
	}
	
	void test_total(){
		this.getTotalPages "共325页\r共57页\r";
	}
	
	void test_insert_by_file_data(){
		
		BufferedReader reader = new BufferedReader(new InputStreamReader(FileUtils.openInputStream(new File("D:\\ltnums.txt"))));
		def line=null
		while ((line= reader.readLine()) != null) {
			log.info "开始插入 $line"
			this.pullToServer (line, "100", "武汉", DateUtil.formatDate (new Date(), "yyyMMdd"))
		}
		//"http://localhost:8080/phoneNum/add?num=18602702748&viewNum=186%3Cb%3E027027%3C/b%3E48&date=20100907&prise=100"
		
	}
	/*void test_one_num(){
	 def pnum="18602702748";
	 AABBcodex aabb=new AABBcodex(pnum)
	 def aa =[:]
	 def rs=aabb.splitLengthStr(pnum, 3,  aa)
	 rs.each {
	 def spitRs=this.nestSpitStr (pnum, "($it.key)")
	 if(spitRs.size()>1){
	 log.info "发现目标"+spitRs.get(0)
	 }
	 }
	 }*/
	void test_pull_one(){
		pullToServer("18602702748", "100", "武汉", "20100911")
	}
	
	/**
	 * 在指定字符串中查找指定字符串出现的次数
	 * @param str
	 * @param reg
	 * @return
	 */
	private List<String> nestSpitStr(String str,String reg){
		def rs=[]
		Pattern p = Pattern.compile(reg);
		Matcher m = p.matcher(str);
		while(m.find()){
			rs.add m.group()
		}
		return rs;
	}
	
	
	
	void test_dikerji(){
		def a=[0,4,5] 
		def b=[6,7,8,9] 
		def d=[1,2,3]
		def e=[1,2,3]
		def f=[1,2,3]
		def g=[1,2,3]
		def h=[1,2,3]
		def c=[]
		c.add a
		c.add b
		c.add d
		c.add e
		Dikaerji0(c)
		c.add f
		c.add g
		c.add h
	}
	/**
	 * 笛卡尔积
	 * @param al0
	 */
	void Dikaerji0(ArrayList al0) {  
		ArrayList a0 = (ArrayList) al0.get(2);  
		for (int i = 3; i < al0.size(); i++) {  
			ArrayList a1 = (ArrayList) al0.get(i);  
			ArrayList temp = new ArrayList();  
			//每次先计算两个集合的笛卡尔积，然后用其结果再与下一个计算  
			for (int j = 0; j < a0.size(); j++) {  
				for (int k = 0; k < a1.size(); k++) {  
					ArrayList cut = new ArrayList();  
					
					if (a0.get(j) instanceof ArrayList) {  
						cut.addAll((ArrayList) a0.get(j));
					} else {  
						cut.add(a0.get(j));
					}  
					if (a1.get(k) instanceof ArrayList) {  
						cut.addAll((ArrayList) a1.get(k));
					} else {  
						cut.add(a1.get(k));
					}  
					temp.add(cut);
				}
			}  
			a0 = temp;  
			for (int j = 0; j < a0.size(); j++) {  
				System.out.println(a0.get(j));
			}
		}
	}
}