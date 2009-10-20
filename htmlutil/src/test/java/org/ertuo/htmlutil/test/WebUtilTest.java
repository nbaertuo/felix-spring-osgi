package org.ertuo.htmlutil.test;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;

import org.apache.commons.httpclient.Cookie;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.CookieManager;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
import com.gargoylesoftware.htmlunit.html.HtmlForm;
import com.gargoylesoftware.htmlunit.html.HtmlObject;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlSubmitInput;
import com.gargoylesoftware.htmlunit.html.HtmlTextArea;
import com.gargoylesoftware.htmlunit.html.HtmlTextInput;
import com.gargoylesoftware.htmlunit.javascript.JavaScriptEngine;
import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.ComThread;
import com.jacob.com.Dispatch;
import com.jacob.com.Variant;

/**
 * 测试19lou
 * 
 * @author mo.duanm
 * 
 */
public class WebUtilTest extends TestCase {

    private final Logger log = Logger.getLogger(WebUtilTest.class);

    /*
    // 登陆地址
    private static String               login_url    = "http://www.19lou.com/passportlogin.php?action=login";

    // 登陆action
    private static String               login_action = "http://www.19lou.com/passportlogin.php?action=login&referer=http%3A%2F%2Fwww.19lou.com%2F";

    public void test_19lou() {
        this.javaeyeAdmin();
         
    }

     
       private void login() {
           HtmlPage page1 = webClient.getHtmlPageByUrl(login_url);
           final HtmlForm form = page1.getFormByName("login");
           form.setActionAttribute(this.login_action);

           final HtmlSubmitInput button = (HtmlSubmitInput) form.getInputByName("loginsubmit");
           final HtmlTextInput username = (HtmlTextInput) form.getInputByName("username");
           final HtmlPasswordInput password = (HtmlPasswordInput) form.getInputByName("password");
           final HtmlHiddenInput formhash = (HtmlHiddenInput) form.getInputByName("formhash");
           formhash.setValueAttribute("a31eb5c8");
           username.setValueAttribute("summersnow8");
           password.setValueAttribute("keyidaxie");
           webClient.getClickHtmlPage(button);
       }

       private List<String> getNewsTitles() {

           BiteSup biteSup = new BiteSup();
           List<String> floorList = biteSup.getFloor();
           for (String floor : floorList) {
               Map<String, String> newsMap = biteSup.getNewsList(floor);

               for (String id : newsMap.keySet()) {
                   biteSup.answer(newsMap.get(id), id);
               }
           }

           return null;

       }*/

    private void javaeyeAdmin() {
        WebClient webClient = new WebClient();
        webClient.setCookieManager(new CookieManager());
        this.setJavaeyeCookies(webClient);
        webClient.setJavaScriptEnabled(true);
        webClient.setThrowExceptionOnScriptError(false);
        HtmlPage htmlPage = null;
        try {
            htmlPage = webClient.getPage("http://www.javaeye.com/forums/board/Java");

        } catch (Exception e) {
            e.printStackTrace();
        }

        //获得帖子列表
        List<String> threads = this.getThreadsByUrl(htmlPage);
        for (String thread : threads) {
            log.debug(thread);
            try {
                HtmlPage page = webClient.getPage(thread);
                if (page != null) {
                    HtmlForm postform = (HtmlForm) page.getElementById("quick_reply_form");
                    // 内容
                    HtmlTextArea message = postform.getElementById("post_body");
                    // 设置value
                    message.focus();
                    message.setText("测试是否可以灌水!");
                    message.blur();

                    HtmlSubmitInput replysubmit = (HtmlSubmitInput) postform
                        .getElementById("quick_reply_button");
                    HtmlPage replays = replysubmit.click();
                    log.debug(replays.asText());
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

    /* Public Sub QueryInterface(riid, ppvObj)
     Public Function AddRef() As UInt32
     Public Function Release() As UInt32
     Public Sub GetTypeInfoCount(pctinfo As UInt) As UInt32
     Public Sub GetTypeInfo(ByVal itinfo As UInt, ByVal lcid As UInt32, pptinfo) As UInt32
     Public Sub GetIDsOfNames(riid, rgszNames As Byte, ByVal cNames As UInt, ByVal lcid As UInt32, rgdispid As Long) As UInt32
     Public Sub Invoke(ByVal dispidMember As Long, riid, ByVal lcid As UInt32, ByVal wFlags As UInt16, pdispparams, pvarResult As Variant, pexcepinfo, puArgErr As UInt) As UInt32
     Public Property Get PasswordMode() As Boolean ' property PasswordMode
     Public Propety Let PasswordMode() As Boolean ' property PasswordMode
     Public Property Get SecurityMode() As Boolean ' property SecurityMode
     Public Propety Let SecurityMode() As Boolean ' property SecurityMode
     Public Property Get TextValue() As String ' property TextValue
     Public Propety Let TextValue() As String ' property TextValue
     Public Property Get MaxLength() As UInt32 ' property MaxLength
     Public Propety Let MaxLength() As UInt32 ' property MaxLength
     Public Property Get LogMode() As Boolean ' property LogMode
     Public Propety Let LogMode() As Boolean ' property LogMode
     Public Property Get TextData() As String ' property TextData
     Public Propety Let TextData() As String ' property TextData
     Public Function EchoTest() As Boolean ' method EchoTest
     Public Property Get readonly() As Boolean ' property Readonly
     Public Propety Let Readonly() As Boolean ' property Readonly
     Public Property Get MACAddress() As String ' property MACAddress
     Public Function Crypto(ByVal bEncrypt As Boolean, ByVal nIndex As UInt32, ByVal pInput As String) As String ' method Crypto
     Public Property Get CryptoMode() As UInt16 ' property CryptoMode
     Public Propety Let CryptoMode() As UInt16 ' property CryptoMode
     Public Property Get Intension() As UInt32 ' property Intension
     Public Property Get Identity() As String ' property Identity
     Public Property Get UseP() As Boolean ' property UseP
     Public Property Get PInfo() As String ' property PInfo
     Public Function ci1() As String ' method ci1
     Public Function ci2() As String ' method ci2
     Public Function ci3() As String ' method ci3
     Public Propety Let cm5ts() As String ' property cm5ts
     Public Property Get cm5ts() As String ' property cm5ts
     Public Propety Let cm5pk() As String ' property cm5pk
     Public Property Get cm5pk() As String ' property cm5pk
     
    */
    public void test_aliEdit() {
        ActiveXComponent xl = new ActiveXComponent("Aliedit.EditCtrl");
        xl.setProperty("TextData", "sdfsdf");
        xl.setProperty("TextValue", "sdfsdf");
        xl.setProperty("Readonly", true);
        xl.invoke("Crypto", new Variant(true), new Variant(0), new Variant("111111a"));
        log.debug(xl.getProperty("TextData"));
        log.debug(xl.getProperty("TextValue"));
        log.debug(xl.getProperty("Intension"));
    }

    public void test_taobao() {

        ActiveXComponent xl = new ActiveXComponent("Aliedit.EditCtrl");
        xl.setProperty("TextData", "sdsds");
        xl.setProperty("TextValue", "sdsds");
        /*log.debug(xl.invoke("EchoTest")
                  + "测试"
                  + );*/
        xl.invoke("Crypto", new Variant(true), new Variant(0), new Variant("111111a")).getString();
        String loginUrl = "http://member1.taobao.com/member/login.jhtml";
        WebClient client = new WebClient(BrowserVersion.INTERNET_EXPLORER_7);

        client.setJavaScriptEnabled(true);
        client.setJavaScriptEngine(new JavaScriptEngine(client));
        client.setThrowExceptionOnScriptError(false);
        client.setCssEnabled(false);
        client.setActiveXNative(true);

        try {

            HtmlPage page = client.getPage(loginUrl);
            HtmlObject element = (HtmlObject) page.getElementById("Password_Edit");
            element.click();
            element.type('a');
            element.blur();
            log.debug("结果" + xl.getPropertyAsString("TextData"));
            HtmlForm form = (HtmlForm) page.getElementById("J_SecureForm");
            HtmlTextInput htmlTextInput = form.getInputByName("TPL_username");
            htmlTextInput.setAttribute("value", "sdfsd");
            //HtmlPage pageRs = form.getButtonByName("").click();
            log.debug(element.asXml());
        } catch (Exception e) {
            log.error("", e);
        }

    }

    /*Public Sub QueryInterface(riid, ppvObj)
    Public Function AddRef() As UInt32
    Public Function Release() As UInt32
    Public Sub GetTypeInfoCount(pctinfo As UInt) As UInt32
    Public Sub GetTypeInfo(ByVal itinfo As UInt, ByVal lcid As UInt32, pptinfo) As UInt32
    Public Sub GetIDsOfNames(riid, rgszNames As Byte, ByVal cNames As UInt, ByVal lcid As UInt32, rgdispid As Long) As UInt32
    Public Sub Invoke(ByVal dispidMember As Long, riid, ByVal lcid As UInt32, ByVal wFlags As UInt16, pdispparams, pvarResult As Variant, pexcepinfo, puArgErr As UInt) As UInt32
    Public Property Get Window() As Long
    Public Propety Let Text() As String
    Public Property Get Text() As String
    Public Property Get PasswdCtrl() As Long ' property PasswdCtrl
    Public Propety Let PasswdCtrl() As Long ' property PasswdCtrl
    Public Property Get MaxLength() As Long ' property MaxLength
    Public Propety Let MaxLength() As Long ' property MaxLength
    Public Property Get Value() As String ' property Value
    Public Propety Let Value() As String ' property Value
    Public Sub Option(ByVal inData As String) As String ' method Option
    Public Property Get Info() As String ' property Info
    Public Propety Let Info() As String ' property Info
    Public Property Get IValue() As String ' property IValue
    Public Propety Let IValue() As String ' property IValue
    Public Propety Let Mask() As String ' property Mask
    Public Property Get Lic() As String ' property lic
    Public Propety Let Lic() As String ' property lic
    Public Propety Let SessionInfo() As String ' property SessionInfo
    Public Property Get LicEx() As String ' property licex
    */
    public void test_cmb() {
        ActiveXComponent cmbControl = new ActiveXComponent("CMBHtmlControl.Edit");
        cmbControl.setProperty("Text", "111111a");
        log.debug("加密" + cmbControl.getProperty("Value"));
    }

    public void test_active() {
        ActiveXComponent xl = new ActiveXComponent("Excel.Application");
        try {
            log.debug("version=" + xl.getProperty("Version"));
            log.debug("version=" + Dispatch.get(xl, "Version"));
            Dispatch.put(xl, "Visible", new Variant(true));
            Dispatch workbooks = xl.getProperty("Workbooks").toDispatch();
            Dispatch workbook = Dispatch.get(workbooks, "Add").toDispatch();
            Dispatch sheet = Dispatch.get(workbook, "ActiveSheet").toDispatch();
            Dispatch a1 = Dispatch.invoke(sheet, "Range", Dispatch.Get, new Object[] { "A1" },
                new int[1]).toDispatch();
            Dispatch a2 = Dispatch.invoke(sheet, "Range", Dispatch.Get, new Object[] { "A2" },
                new int[1]).toDispatch();
            Dispatch.put(a1, "Value", "123.456");
            Dispatch.put(a2, "Formula", "=A1*2");
            log.debug("a1 from excel:" + Dispatch.get(a1, "Value"));
            log.debug("a2 from excel:" + Dispatch.get(a2, "Value"));
            Variant f = new Variant(false);
            Dispatch.call(workbook, "Close", f);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            xl.invoke("Quit", new Variant[] {});
            ComThread.Release();
        }
    }

    public void test_StringBuffer() {
        StringBuffer strbuf = new StringBuffer();
        try {
            FileInputStream in = new FileInputStream("d:\\test.txt");
            int size = 0;
            byte[] buf = new byte[1024];
            while ((size = in.read(buf)) != -1) {
                strbuf.append(new String(buf, 0, size, "UTF-8"));
            }
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        log.debug("StringBuffer:" + strbuf.toString());
    }

    public void test_String() {
        List<String> list = new ArrayList<String>();
        list.add("1");
        list.add("2");
        list.add("3");
        list.add("4");
        list.add("5");
        String[] a = new String[] {};
        a = list.toArray(new String[] {});
        for (String string : a) {
            System.out.println(string);
        }

    }

}
