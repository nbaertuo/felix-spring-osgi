package org.ertuo.htmlutil.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.gargoylesoftware.htmlunit.javascript.host.Event;
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

    public void test_login() {

        ActiveXComponent xl = new ActiveXComponent("Aliedit.EditCtrl");
        //xl.setProperty("TextData", "sdfd");
        log.debug(xl.invoke("EchoTest") + "测试" + xl.m_pDispatch
                  + xl.getPropertyAsString("TextData"));

        String loginUrl = "http://member1.taobao.com/member/login.jhtml";
        WebClient client = new WebClient(BrowserVersion.INTERNET_EXPLORER_7);

        client.setJavaScriptEnabled(true);
        client.setJavaScriptEngine(new JavaScriptEngine(client));
        client.setThrowExceptionOnScriptError(false);
        client.setCssEnabled(false);
        client.setActiveXNative(true);

        Map<String, String> mapIn = new HashMap<String, String>();

        //mapIn.put("Aliedit.EditCtrl", "com.jacob.activeX.ActiveXComponent");
        //client.setActiveXObjectMap(mapIn);

        /* client.setAlertHandler(new AlertHandler() {
             public void handleAlert(final Page page, final String message) {
                 fail("The active x object did not bind to the object.");
             }

         });*/

        try {
            HtmlPage page = client.getPage(loginUrl);
            HtmlObject element = (HtmlObject) page.getElementById("Password_Edit");
            element.focus();
            element.fireEvent(new Event(element, "keydown", 65, false, false, false));
            element.fireEvent(new Event(element, "keydown", 66, false, false, false));
            element.fireEvent(new Event(element, "keydown", 67, false, false, false));
            element.blur();
            log.debug(xl.invoke("EchoTest") + "结果" + xl.m_pDispatch
                      + xl.getPropertyAsString("TextData"));
            HtmlForm form = (HtmlForm) page.getElementById("J_SecureForm");
            HtmlTextInput htmlTextInput = form.getInputByName("TPL_username");

            htmlTextInput.click(new Event(element, Event.TYPE_KEY_DOWN, 65, false, false, false));
            htmlTextInput.click(new Event(element, Event.TYPE_KEY_PRESS, 66, false, false, false));
            htmlTextInput.click(new Event(element, Event.TYPE_CHANGE, 67, false, false, false));
            // htmlTextInput.blur();
            //htmlTextInput.setAttribute("value", "sdfsd");
            log.debug(htmlTextInput.getText());
            //HtmlPage pageRs = form.getButtonByName("").click();
            //log.debug(pageRs.asXml());
        } catch (Exception e) {
            log.error("", e);
        }

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

}
