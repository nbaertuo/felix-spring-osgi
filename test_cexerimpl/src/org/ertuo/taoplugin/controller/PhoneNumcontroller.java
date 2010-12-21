package org.ertuo.taoplugin.controller;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.http.impl.cookie.DateUtils;
import org.ertuo.taoplugin.bean.CityNum;
import org.ertuo.taoplugin.bean.PhoneNum;
import org.ertuo.taoplugin.bean.ScheduleProcess;
import org.ertuo.taoplugin.dao.GeneralDao;
import org.ertuo.taoplugin.dao.PhoneNumDao;
import org.ertuo.taoplugin.facade.TopFacade;
import org.ertuo.taoplugin.facade.XyzCodexCronService;
import org.ertuo.taoplugin.web.util.LianTingWebClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import com.taobao.api.domain.Item;

/**
 * 号码管理
 * 
 * @author mo.duanm
 * 
 */
@Controller
public class PhoneNumcontroller {

    private static final Logger logger      = Logger.getLogger(PhoneNumcontroller.class.getName());

    final UserService           userService = UserServiceFactory.getUserService();

    @Autowired
    PhoneNumDao                 phoneNumDao;

    @Autowired
    LianTingWebClient           lianTingWebClient;

    @Autowired
    XyzCodexCronService         xyzCodexCronService;

    @Autowired
    GeneralDao<ScheduleProcess> scheduleDao;

    @Autowired
    TopFacade                   topFacade;

    @Autowired
    GeneralDao<CityNum>         cityNumDao;

    @RequestMapping(value = "/phone", method = RequestMethod.GET)
    public ModelAndView list() {
        ModelAndView modelAndView = new ModelAndView("messages-list");
        List<PhoneNum> pns = phoneNumDao.top();
        logger.info("数据长度" + pns.size());
        modelAndView.addObject("phoneNums", pns);
        modelAndView.setViewName("phoneNum/list");
        return modelAndView;
    }

    @RequestMapping(value = "/phone/add")
    public String add(ModelMap m, String num, String city, String date, String prise) {
        logger.info("快速添加" + num);
        if (StringUtils.isBlank(prise) || StringUtils.isBlank(date) || StringUtils.isBlank(city)
            || StringUtils.isBlank(num)) {
            logger.warning("参数为空");
            m.put("rs", "参数无效");
            return "phoneNum/add";
        }
        PhoneNum p = new PhoneNum();
        p.setCity(city);
        p.setDate(date);
        p.setNum(num);
        p.setPrise(prise);
        phoneNumDao.createPhoneNum(p);
        m.put("rs", "添加" + num + "成功");
        return "phoneNum/add";

    }

    @RequestMapping("/phone/search")
    public ModelAndView search(ModelAndView mv, String ps) {
        mv.setViewName("phoneNum/search");
        String[] totalPages = null;
        try {
            HtmlPage page = lianTingWebClient.getCurrentPage("017%7C017001", ps);
            //logger.info(page.asXml());
            // 获得总页数
            totalPages = lianTingWebClient.getTotalPage(page);
            if (totalPages != null) {
                mv.addObject("currPage", totalPages[0]);
                mv.addObject("totalPage", totalPages[1]);
            }
            logger.info("总页数" + ToStringBuilder.reflectionToString(totalPages));
            xyzCodexCronService.asynCron(lianTingWebClient.getTr(page), "武汉");
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            logger.info("查询号码错误" + sw.toString());
            mv.addObject("rs", "连接联通错误");
            return mv;
        }
        mv.addObject("rs", "查询总页数" + totalPages);
        return mv;
    }

    @RequestMapping("/phone/cron")
    public ModelAndView cron(ModelAndView mv) {
        mv.setViewName("phoneNum/search");

        List<ScheduleProcess> sps = scheduleDao
            .find("select w from ScheduleProcess w where w.isUpdateTOP=false order by w.date desc ");
        if (sps.size() < 1) {
            logger.info("当前没有需要处理的城市");
            return mv;
        }

        ScheduleProcess sp = sps.get(0);//scheduleDao.get(city);
        CityNum cy = cityNumDao.get(sp.getId());
        logger.info("当前正在处理的城市是" + ToStringBuilder.reflectionToString(cy));
        /*
        if (sp == null) {
            //mv.setViewName("redirect:http://taohaoma.appspot.com/app/schedule/check?city=" + city);
            this.createSchedule(city);
            return mv;
        }*/
        if ((sp.getCurrPage() - sp.getTotalPage() == 1) && !sp.isUpdateTOP()) {
            String desc = this.getTopContext(cy.getNumber());
            logger.info("saleNum list:" + desc);
            if (StringUtils.isNotBlank(desc)) {
                logger.info("开始更新[" + cy.getChsName() + "]日期为[" + sp.getDate() + "]");
                Item it = topFacade.updateItemDesc(cy.getItemId(), desc,
                    cy.getTitle() + DateUtils.formatDate(new Date(), "MMdd"));
                if (it != null) {
                    logger.info("更新结果[" + ToStringBuilder.reflectionToString(it) + "]");
                    sp.setUpdateTOP(true);
                    scheduleDao.create(sp);
                }
                mv.addObject("it", it);
            }

        }
        if (sp.getCurrPage() <= sp.getTotalPage()) {
            logger.info("开始处理" + sp.getCurrPage() + "页");
            int currPage = sp.getCurrPage();
            HtmlPage page = lianTingWebClient.getCurrentPage(cy.getCookies(),
                String.valueOf(currPage));
            xyzCodexCronService.asynCron(lianTingWebClient.getTr(page), cy.getChsName());
            currPage++;
            sp.setCurrPage(currPage);
            scheduleDao.create(sp);
        }

        return mv;
    }

    private String getTopContext(String city) {
        logger.info("开始查询城市[" + city + "]的号码列表");
        WebClient wc = new WebClient();
        wc.setJavaScriptEnabled(false);
        wc.setCssEnabled(false);
        String url = "http://taohaoma.appspot.com";
        //String url = "http://localhost:8080";
        HtmlPage page = null;
        try {
            page = wc.getPage(url + "/app/sale/list?city=" + city);
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            logger.info("查询号码错误" + sw.toString());
        }
        HtmlElement div = page.getElementById("top");
        //String desc = div.asText();
        return div.asXml();

    }

}
