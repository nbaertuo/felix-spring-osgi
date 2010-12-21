package org.ertuo.taoplugin.controller;

import java.util.List;
import java.util.logging.Logger;

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
 * 淘宝刷pv
 * 
 * @author mo.duanm
 * 
 */
@Controller
public class PvController {

    private static final Logger logger      = Logger.getLogger(PvController.class.getName());

    final UserService           userService = UserServiceFactory.getUserService();
    @Autowired
    LianTingWebClient           lianTingWebClient;

    @Autowired
    GeneralDao<ScheduleProcess> scheduleDao;

    @Autowired
    GeneralDao<CityNum>         cityNumDao;

    @RequestMapping(value = "/pv/list", method = RequestMethod.GET)
    public ModelAndView list(ModelAndView mv) {
        List<CityNum> cys = cityNumDao.find(null);
        logger.info("数据长度" + cys.size());
        mv.addObject("cys", cys);
        return mv;
    }

    @RequestMapping(value = "/pv/add")
    public ModelAndView add(ModelAndView mv) {
        return mv;
    }

    @RequestMapping(value = "/pv/add", method = RequestMethod.POST)
    public ModelAndView add(ModelAndView mv, String itemId) {

        return mv;
    }

    @RequestMapping(value = "/pv/shua")
    public ModelAndView shua(ModelAndView mv) {
        logger.info("喜唰唰");
        return mv;
    }
}
