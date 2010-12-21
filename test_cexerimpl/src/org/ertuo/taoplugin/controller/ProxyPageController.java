package org.ertuo.taoplugin.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.ertuo.taoplugin.bean.ProxyIp;
import org.ertuo.taoplugin.bean.ProxyPage;
import org.ertuo.taoplugin.dao.ProxyIpDao;
import org.ertuo.taoplugin.dao.ProxyPageDao;
import org.ertuo.taoplugin.util.RegUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.WebClient;
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
public class ProxyPageController {

    private static final Logger logger = Logger.getLogger(ProxyPageController.class.getName());

    @Autowired
    ProxyPageDao                proxyPageDao;
    @Autowired
    ProxyIpDao                  proxyIpDao;

    @RequestMapping(value = "/proxy/add", method = RequestMethod.GET)
    public String add(String url, String reg, String spit, ModelMap modelAndView) {
        if (StringUtils.isNotBlank(reg) && StringUtils.isNotBlank(url)) {
            ProxyPage page = new ProxyPage(url, reg, spit);
            logger.info("添加" + url + " " + reg);
            proxyPageDao.createProxyPage(page);
            // this.get_proxy_ip(page);
            modelAndView.put("rs", this.get_proxy_ip(page));
            // return "redirect:/app/proxy/analyze?url=" + url;
        }
        return "proxyPage/add";
    }

    @RequestMapping(value = "/proxy/analyze", method = RequestMethod.GET)
    public String analyze(String url, ModelMap modelAndView) {
        if (StringUtils.isNotBlank(url)) {
            logger.info("需要分析的地址是" + url);
            return "redirect:/app/proxy/list?url=" + url;
        }
        return "proxyPage/ips";
    }

    @RequestMapping(value = "/proxy/viewUrl", method = RequestMethod.GET)
    public void viewUrl(String url, String ip, ModelMap modelAndView, HttpServletResponse res) {
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
            WebClient client = null;
            ProxyIp proxyIp = null;
            if (StringUtils.isBlank(ip)) {
                client = this.getNoProxyClient();
            } else {
                proxyIp = proxyIpDao.get(ip);
                client = this.getProxyClient(proxyIp.getIp(), Integer.parseInt(proxyIp.getPort()));
            }

            HtmlPage page = null;
            try {
                page = client.getPage(url);
                logger.info("代理[" + ToStringBuilder.reflectionToString(proxyIp) + "]访问url[" + url
                            + "]完毕");
                HtmlDivision o = page.getFirstByXPath("//div[@class='tb-detail-hd']");
                out.print(o.asText());
            } catch (Exception e) {
                logger.warning("代理[" + ToStringBuilder.reflectionToString(proxyIp) + "]访问url["
                               + url + "]失败" + e);
                out.print("代理[" + ToStringBuilder.reflectionToString(proxyIp) + "]访问url[" + url
                          + "]失败" + e);
            }
        }

    }

    @RequestMapping(value = "/proxy/list", method = RequestMethod.GET)
    public String analyze(ModelMap modelAndView, String url) {

        List<ProxyIp> ips = proxyIpDao.getAll();
        modelAndView.put("ips", ips);
        modelAndView.put("url", url);
        return "proxyPage/list";
    }

    private String get_proxy_ip(ProxyPage proxyPage) {
        logger.info("需要解析的是:" + ToStringBuilder.reflectionToString(proxyPage));

        HtmlPage page = null;
        try {
            page = this.getClient().getPage(proxyPage.getUrl());
        } catch (FailingHttpStatusCodeException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (Exception e) {
            logger.warning("解析地址" + proxyPage.getUrl() + "失败:" + e);
            return "解析地址[" + proxyPage.getUrl() + "]失败";
        }
        String pageStr = page.asText();
        logger.info("源代码：" + pageStr);
        String rs = RegUtil.getStrByReg(pageStr, proxyPage.getReg(), true);

        logger.info(rs);
        String[] rss = rs.split("\n");
        for (String ip : rss) {
            String[] ips = ip.split(proxyPage.getSpit());
            if (ips != null && ips.length == 2) {
                ProxyIp proxyIp = new ProxyIp(ips[0], ips[1]);
                proxyIpDao.createProxyPage(proxyIp);
                logger.info("添加" + ToStringBuilder.reflectionToString(ips) + "成功");
            } else {
                logger.info("添加" + ToStringBuilder.reflectionToString(ips) + "失败");
            }

        }
        return rs;
    }

    private WebClient getClient() {
        WebClient client = new WebClient(BrowserVersion.INTERNET_EXPLORER_6);
        client.setJavaScriptEnabled(false);
        client.setJavaScriptEngine(new JavaScriptEngine(client));
        client.setThrowExceptionOnScriptError(false);
        client.setCssEnabled(false);
        client.getCookieManager().setCookiesEnabled(false);
        return client;
    }

    private WebClient getProxyClient(String host, int port) {
        WebClient client = new WebClient(BrowserVersion.FIREFOX_3_6, host, port);
        client.setJavaScriptEnabled(true);
        client.setJavaScriptEngine(new JavaScriptEngine(client));
        client.setThrowExceptionOnScriptError(false);
        client.setCssEnabled(false);
        client.getCookieManager().setCookiesEnabled(true);
        return client;
    }

    private WebClient getNoProxyClient() {
        WebClient client = new WebClient(BrowserVersion.FIREFOX_3_6);
        client.setJavaScriptEnabled(true);
        client.setJavaScriptEngine(new JavaScriptEngine(client));
        client.setThrowExceptionOnScriptError(false);
        client.setCssEnabled(false);
        client.getCookieManager().setCookiesEnabled(true);
        return client;
    }
}
