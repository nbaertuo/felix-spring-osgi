package org.ertuo.taoplugin.web.test;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.http.impl.cookie.DateParseException;
import org.apache.http.impl.cookie.DateUtils;
import org.junit.Test;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.HttpMethod;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.WebRequest;
import com.gargoylesoftware.htmlunit.html.HtmlButtonInput;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlTableDataCell;
import com.gargoylesoftware.htmlunit.util.Cookie;
import com.gargoylesoftware.htmlunit.util.NameValuePair;

public class LianTongTest {
    /**
     * @param args
     */
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        LianTongTest test = new LianTongTest();
        test.test_serarch();
    }

    private final Logger log         = Logger.getLogger(LianTongTest.class.getName());

    private String       city        = "武汉";

    private String       cityCookies = "017%7C017001";

    public void test_serarch() {
        StringBuffer sb = new StringBuffer();
        try {
            String loginUrl = "http://shop.10010.com/number/searchNumber.action";
            WebClient client = new WebClient(BrowserVersion.FIREFOX_3_6);
            //client.setJavaScriptEnabled(true);
            //client.setJavaScriptEngine(new JavaScriptEngine(client));
            client.setThrowExceptionOnScriptError(false);
            client.setCssEnabled(false);
            //client.getCookieManager().setCookiesEnabled(true);
            client.setTimeout(1000 * 60);
            this.setCookies(client);
            HtmlPage page = client.getPage(loginUrl);

            //获得总页数
            int totalPages = this.getTotalPages(page.asText());

            log.info("总页数" + totalPages);
            for (int i = 311; i < totalPages; i++) {
                try {
                    //HtmlTextInput inputpage = (HtmlTextInput) page.getElementById("inputpage");
                    //HtmlTextInput inputpageh = (HtmlTextInput) page.getElementById("inputpageh");
                    HtmlTableDataCell totalPageCell = (HtmlTableDataCell) page
                        .getElementById("totalnumpageh");
                    log.info("当前页是" + totalPageCell.getTextContent());
                    //HtmlForm form = (HtmlForm) page.getElementById("defaultnumber");
                    //HtmlTextInput htmlTextInput = form.getInputByName("inputpage");
                    //inputpage.setAttribute("value", String.valueOf(i));
                    //inputpageh.setAttribute("value", String.valueOf(i));
                    //HtmlPage pageRs = ((HtmlButtonInput) form.getInputByValue("确定")).click();
                    //page = ((HtmlButtonInput) page.getFirstByXPath("//input[@class=\"page_btnbg\"]")).click();
                    HtmlButtonInput bt = this.getNextButton(page, "//input[@class=\"page_btnbg\"]",
                        "下一页");
                    page = bt.click();
                    //log.info(pageRs.asXml());

                    String numbers = this.getStrByRegs(page.asText(), "[0-9]{11}");
                    this.getTr(page);
                    log.info("当前第  " + i + " 页，总页数" + totalPages);
                    sb.append(numbers + "\r");
                } catch (Exception e) {
                    log.warning("查询页[" + i + "]错误" + e);
                }

            }
        } catch (Exception e) {
            log.warning("查询号码错误" + e);
        } finally {
            this.genFile(sb.toString());
        }
    }

    @Test
    public void test_chongfu() {
        String num = "186555186";
        String rs = this.getStrByRegs(num, "(\\d)\\1+");
        log.info("" + rs);
    }

    @Test
    public void test_abcabc() {
        String num = "123123145";
        String rs = this.getStrByRegs(num, "(\\d{3,})\\1+");
        log.info("" + rs);
    }

    private HtmlButtonInput getNextButton(HtmlPage page, String xpath, String value) {
        List<HtmlButtonInput> bts = (List<HtmlButtonInput>) page.getByXPath(xpath);
        for (HtmlButtonInput bt : bts) {
            if (StringUtils.equals(bt.getAttribute("value"), value)) {
                return bt;
            }
        }
        return null;

    }

    /**
     * 查询所有的tr对象
     * @param page
     */
    private void getTr(HtmlPage page) {
        org.w3c.dom.NodeList br = page.getElementsByTagName("tr");
        for (int i = 0; i < br.getLength(); i++) {
            org.w3c.dom.Node node = br.item(i);
            String context = node.getTextContent();
            String phoneNum = this.getStrByReg(context, "(186)[0-9]{8}");
            String prise = this.getStrByReg(context, "[0-9]{0,4}(元)");
            if (StringUtils.isNotBlank(prise) && StringUtils.isNotBlank(phoneNum)) {
                prise = prise.replaceAll("元", "");
                log.info(phoneNum + " " + prise + "元");
                this.pullToServer(phoneNum, prise, DateUtils.formatDate(new Date(), "yyyyMMdd"));
            }
        }
    }

    private void pullToServer(String num, String prise, String date) {
        try {
            String domainName = "http://taohaoma.appspot.com/app/phone/add";
            //String domainName = "http://localhost:8080/app/phone/add";
            //String pullUrl = "http://" + domainName + "/app/phone/add?num=" + num + "&date=" + date+ "&prise=" + prise + "&city=" + city;
            //这里必须使用ie，firefox中浏览器会做自动转码

            WebRequest request = new WebRequest(new URL(domainName));
            List<NameValuePair> requestParameters = new ArrayList<NameValuePair>();
            requestParameters.add(new NameValuePair("date", date));
            requestParameters.add(new NameValuePair("city", city));
            requestParameters.add(new NameValuePair("prise", prise));
            requestParameters.add(new NameValuePair("num", num));
            request.setRequestParameters(requestParameters);
            request.setHttpMethod(HttpMethod.POST);
            request.setCharset("GBK");
            WebClient client = new WebClient(BrowserVersion.INTERNET_EXPLORER_6);
            client.getPage(client.getCurrentWindow().getTopWindow(), request);
            client.setJavaScriptEnabled(false);
            client.setThrowExceptionOnScriptError(false);
            client.setCssEnabled(false);
            //client.setTimeout(30*1000)
            // client.getPage(pullUrl);
            client.getPage(client.getCurrentWindow().getTopWindow(), request);
        } catch (Exception e) {
            log.warning("上传数据到服务器失败" + e);
        }
    }

    /**
     * 将号码生成文本
     * @param content
     */
    private void genFile(String content) {
        try {
            FileUtils.writeByteArrayToFile(new File("d:\\" + city + "_ltPhoneNumber.txt"),
                content.getBytes());
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /**
     * 设置cookies
     * @param webClient
     */
    private void setCookies(WebClient webClient) {
        Cookie city = null;
        try {
            city = new Cookie("10010.com", "city", cityCookies, "/", DateUtils.parseDate(
                "20551212", new String[] { "yyyyMMdd" }), false);
        } catch (DateParseException e) {
            log.warning("添加cookies是异常，" + e.getMessage());
        }
        webClient.getCookieManager().addCookie(city);
    }

    /**
     * 通过正则返回一个字符串,如果有多个符合正则的中间用"\n"隔开
     * @param str 需要处理的字符串
     * @param reg 正则表达式
     * @param nest 是否嵌套查询
     * @return 被正则处理完的字符串
     */
    private String getStrByReg(String str, String reg) {
        StringBuffer sb = new StringBuffer();
        Pattern p = Pattern.compile(reg);
        Matcher m = p.matcher(str);
        if (m.find()) {
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
    private String getStrByRegs(String str, String reg) {
        StringBuffer sb = new StringBuffer();
        Pattern p = Pattern.compile(reg);
        Matcher m = p.matcher(str);
        while (m.find()) {
            sb.append(m.group() + "\n");
        }
        return sb.toString();
    }

    /**
     * 从html中获得总页数
     * @param content
     * @return
     */
    private int getTotalPages(String content) {
        //这里预设10000页
        String temp = this.getStrByReg(content, "(共)[0-9]{0,5}(页)");
        String totalStr = temp.replaceAll("(共)|(页)", "");
        if (StringUtils.isNumeric(totalStr)) {
            return Integer.valueOf(totalStr);
        } else {
            return 0;
        }
    }

    void test_total() {
        this.getTotalPages("共325页\r共57页\r");
    }

    void test_insert_by_file_data() {

        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(
                FileUtils.openInputStream(new File("D:\\ltnums.txt"))));
            String line = null;
            while ((line = reader.readLine()) != null) {
                log.info("开始插入 " + line);
                this.pullToServer(line, "100", DateUtils.formatDate(new Date(), "yyyMMdd"));
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
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
    void test_pull_one() {
        pullToServer("18602702748", "100", "20100911");
        pullToServer("18602702749", "100", "20100912");
        pullToServer("18602702750", "100", "20100913");
    }

    /**
     * 在指定字符串中查找指定字符串出现的次数
     * @param str
     * @param reg
     * @return
     */
    private List<String> nestSpitStr(String str, String reg) {
        List<String> rs = new ArrayList<String>();
        Pattern p = Pattern.compile(reg);
        Matcher m = p.matcher(str);
        while (m.find()) {
            rs.add(m.group());
        }
        return rs;
    }

    public void test_verify() {
        String queryParams = "searchNumberInfo.order=0&pageInfo.currPage=0&pageInfo.totalPager=139&formname=defaultnumber&searchNumberInfo.currPage2g=0&searchNumberInfo.totalPager2g=1023&searchNumberInfo.oldnumberCardId=&searchNumberInfo.oldnumberCardId2g=&formname=defaultnumber&searchNumberInfo.cardID=&searchNumberInfo.isreserve=&searchNumberInfo.saleCost=&searchNumberInfo.numbercountryId=&searchNumberInfo.numbercityId=&searchNumberInfo.ruleID=&searchNumberInfo.startCost=&searchNumberInfo.onnetlong=&searchNumberInfo.lowestcost=&searchNumberInfo.nCardcost=&searchNumberInfo.payType=&searchNumberInfo.processType=numbercard&searchNumberInfo.netType=01&searchNumberInfo.searchRuleID=&searchNumberInfo.numberStartCost=&searchNumberInfo.numberCardId=18601135063&searchNumberInfo.searchRuleID2g=&searchNumberInfo.numberStartCost2g=&searchNumberInfo.numberCardId2g=%C8%E7%C9%FA%C8%D5%2C%BC%CD%C4%EE%C8%D5%B5%C8";
        try {
            String queryUrl = "http://shop.10010.com/number/searchNumber.action?searchNumberInfo.netType=01&${queryParams}}";
            URL url = new URL(queryUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoOutput(true);
            connection.setRequestMethod("POST");

            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
                StringBuffer response = new StringBuffer();
                String line;

                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
                reader.close();
                log.info(response.toString());
            } else {
                // Server returned HTTP error code.
            }
        } catch (MalformedURLException e) {
            // ...
        } catch (IOException e) {
            // ...
        }
    }
}
