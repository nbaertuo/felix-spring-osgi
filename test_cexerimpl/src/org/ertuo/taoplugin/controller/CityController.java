package org.ertuo.taoplugin.controller;

import java.util.List;
import java.util.logging.Logger;

import org.apache.commons.lang.StringUtils;
import org.ertuo.taoplugin.bean.CityNum;
import org.ertuo.taoplugin.bean.ScheduleProcess;
import org.ertuo.taoplugin.dao.GeneralDao;
import org.ertuo.taoplugin.web.util.LianTingWebClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

/**
 * 任务管理
 * 
 * @author mo.duanm
 * 
 */
@Controller
public class CityController {

    private static final Logger logger      = Logger.getLogger(CityController.class.getName());

    final UserService           userService = UserServiceFactory.getUserService();
    @Autowired
    LianTingWebClient           lianTingWebClient;

    @Autowired
    GeneralDao<ScheduleProcess> scheduleDao;

    @Autowired
    GeneralDao<CityNum>         cityNumDao;

    @RequestMapping(value = "/city/list", method = RequestMethod.GET)
    public ModelAndView list(ModelAndView mv) {
        List<CityNum> cys = cityNumDao.find(null);
        logger.info("数据长度" + cys.size());
        mv.addObject("cys", cys);
        return mv;
    }

    @RequestMapping(value = "/city/add")
    public ModelAndView add(ModelAndView mv) {
        return mv;
    }

    @RequestMapping(value = "/city/add", method = RequestMethod.POST)
    public ModelAndView add(ModelAndView mv, String city, String chsName, String itemId,
                            String title) {
        CityNum cy = new CityNum();
        cy.setChsName(chsName);
        cy.setCookies(city.substring(0, 3) + "%7C" + city);
        cy.setItemId(Long.parseLong(itemId));
        cy.setNumber(city);
        cy.setTitle(title);
        cityNumDao.create(cy);
        mv.addObject("city", city);
        return mv;
    }

    @RequestMapping(value = "/city/del")
    public ModelAndView del(ModelAndView mv, String id) {
        mv.setViewName("forward:list");
        if (StringUtils.isBlank(id)) {
            logger.warning("id[" + id + "]为空");
            return mv;
        }
        CityNum cn = cityNumDao.get(id);
        ScheduleProcess sp = scheduleDao.get(id);
        cityNumDao.delete(cn);
        scheduleDao.delete(sp);
        logger.info("id[" + id + "]对应的城市和任务删除完毕");
        return mv;
    }
}
