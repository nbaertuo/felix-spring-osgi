package org.ertuo.taoplugin.controller;

import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.http.impl.cookie.DateUtils;
import org.ertuo.taoplugin.bean.CityNum;
import org.ertuo.taoplugin.bean.ScheduleProcess;
import org.ertuo.taoplugin.dao.GeneralDao;
import org.ertuo.taoplugin.web.util.LianTingWebClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

/**
 * 任务管理
 * 
 * @author mo.duanm
 * 
 */
@Controller
public class ScheduleController {

    private static final Logger logger      = Logger.getLogger(ScheduleController.class.getName());

    final UserService           userService = UserServiceFactory.getUserService();
    @Autowired
    LianTingWebClient           lianTingWebClient;

    @Autowired
    GeneralDao<ScheduleProcess> scheduleDao;
    @Autowired
    GeneralDao<CityNum>         cityNumDao;

    @RequestMapping(value = "/schedule/list", method = RequestMethod.GET)
    public ModelAndView list(ModelAndView mv) {
        List<ScheduleProcess> pns = scheduleDao.find(null);
        logger.info("数据长度" + pns.size());
        mv.addObject("pns", pns);
        return mv;
    }

    @RequestMapping(value = "/schedule/check")
    public ModelAndView add(ModelAndView mv, String city) {
        //页面输入
        if (StringUtils.isNotBlank(city)) {
            ScheduleProcess sp = scheduleDao.get(city);
            this.updateSchedule(city, sp);
            return mv;
        }
        //调度任务
        List<ScheduleProcess> sps = scheduleDao
            .find("select w from ScheduleProcess w where w.isUpdateTOP=true order by w.date desc ");
        if (sps.size() < 1) {
            logger.info("当前没有需要处理的城市");
            return mv;
        }
        for (ScheduleProcess sp : sps) {
            this.updateSchedule(sp.getId(), sp);
        }

        return mv;

    }

    private void updateSchedule(String city, ScheduleProcess sp) {
        CityNum cy = cityNumDao.get(city);
        logger.info("当前正在处理的城市是" + ToStringBuilder.reflectionToString(cy));
        HtmlPage page = lianTingWebClient.getCookiePage(cy.getCookies());
        String[] rs = lianTingWebClient.getTotalPage(page);
        int totalPage = Integer.parseInt(rs[1]);

        ScheduleProcess currSp = new ScheduleProcess();
        currSp.setCurrPage(0);
        currSp.setDate(DateUtils.formatDate(new Date(), "yyyyMMdd"));
        currSp.setTotalPage(totalPage);
        currSp.setId(cy.getNumber());

        if (sp == null || (sp != null && Math.abs(sp.getTotalPage() - totalPage) > 10)) {
            scheduleDao.create(currSp);
        }
    }
}
