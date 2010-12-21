package org.ertuo.taoplugin.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.logging.Logger;

import javax.cache.Cache;
import javax.cache.CacheException;
import javax.cache.CacheManager;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.GaeCache;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.WebResponse;
import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
import com.gargoylesoftware.htmlunit.html.HtmlDivision;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.javascript.JavaScriptEngine;

/**
 * 
 * 
 * @author mo.duanm
 * 
 */
@Controller
public class SearchController {

    private static final Logger logger = Logger.getLogger(SearchController.class.getName());

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public String view(ModelMap modelAndView, HttpServletResponse res) {

        return "search/search";
    }

    private static Cache cache;
    static {
        try {
            cache = CacheManager.getInstance().getCacheFactory()
                .createCache(Collections.emptyMap());
        } catch (CacheException e) {
            logger.warning("初始化cache错误" + e.getMessage());
        }
    }

    @RequestMapping(value = "/search/xpath", method = RequestMethod.GET)
    public void search(String url, String xpath, ModelMap modelAndView, HttpServletResponse res,
                       HttpServletRequest req) {
        res.setContentType("application/text");
        res.setCharacterEncoding("GBK");

        PrintWriter out = null;
        try {
            out = res.getWriter();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        if (StringUtils.isNotBlank(url)) {
            logger.info("开始分析地址" + url);

            WebClient client = this.getClient();
            HtmlPage page = null;
            try {
                page = client.getPage(url);
                List<HtmlAnchor> o = (List<HtmlAnchor>) page
                    .getByXPath("//a[@class=\"EventCanSelect\"]");

                HtmlAnchor anchor = this.getHtmlAnchor(xpath, page);
                if (anchor != null) {
                    String key = UUID.randomUUID().toString();
                    cache.put(key, page.getWebResponse());
                    //String div = ((HtmlPage) anchor.click()).getTitleText();
                    //.getFirstByXPath("//div[@class='tb-detail-hd']");
                    out.print(key);
                    //out.print(anchor.getAttribute("href"));
                    return;
                }
            } catch (Exception e) {
                logger.warning("访问url[" + url + "]失败" + e.getCause());
                out.print("没有找到商品");
                //throw new RuntimeException(e);
            }
        }

    }

    @RequestMapping(value = "/search/click", method = RequestMethod.GET)
    public void click(String xpath, String url, String key, ModelMap modelAndView,
                      HttpServletResponse res, HttpServletRequest req) {
        res.setContentType("application/text");
        res.setCharacterEncoding("GBK");
        PrintWriter out = null;
        try {
            out = res.getWriter();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        WebClient client = this.getNoProxyClient();
        HtmlPage page = null;
        try {
            page = (HtmlPage) client.loadWebResponseInto((WebResponse) cache.get(key), client
                .getCurrentWindow().getTopWindow());
            List<HtmlAnchor> o = (List<HtmlAnchor>) page
                .getByXPath("//a[@class=\"EventCanSelect\"]");
            HtmlAnchor anchor = this.getHtmlAnchor(xpath, page);
            if (anchor != null) {
                HtmlDivision div = ((HtmlPage) anchor.click())
                    .getFirstByXPath("//div[@class='tb-detail-hd']");
                out.print(div.asText());
                return;
            }
        } catch (Exception e) {
            logger.warning("访问url[" + url + "]失败" + e.getCause());
            out.print("访问url[" + url + "]失败" + e);
            throw new RuntimeException(e);
        }

    }

    private WebClient getNoProxyClient() {
        WebClient client = new WebClient(BrowserVersion.FIREFOX_3_6);
        client.setJavaScriptEnabled(true);
        client.setJavaScriptEngine(new JavaScriptEngine(client));
        client.setThrowExceptionOnScriptError(false);
        client.setCssEnabled(false);
        client.getCookieManager().setCookiesEnabled(false);
        client.setCache(new GaeCache());
        return client;
    }

    private WebClient getClient() {
        WebClient client = new WebClient(BrowserVersion.FIREFOX_3_6);
        client.setJavaScriptEnabled(false);
        client.setJavaScriptEngine(new JavaScriptEngine(client));
        client.setThrowExceptionOnScriptError(false);
        client.setCssEnabled(false);
        client.getCookieManager().setCookiesEnabled(false);
        return client;
    }

    private HtmlAnchor getHtmlAnchor(String xpath, HtmlPage page) {
        List<HtmlAnchor> o = (List<HtmlAnchor>) page.getByXPath("//a[@class=\"EventCanSelect\"]");
        for (HtmlAnchor htmlAnchor : o) {
            String title = htmlAnchor.getAttribute("title");
            String onclick = htmlAnchor.getAttribute("onclick");
            String href = htmlAnchor.getAttribute("href");
            String stat = htmlAnchor.getAttribute("stat");
            if (title.contains(xpath)) {
                /*if (StringUtils.isNotBlank(onclick)) {
                    //直接执行js
                    page.executeJavaScript(onclick);
                }
                if (StringUtils.isNotBlank(stat)) {
                    href = href + "&" + stat + "www.taobao.com";
                }*/
                return htmlAnchor;
            }

        }
        return null;

    }

}
