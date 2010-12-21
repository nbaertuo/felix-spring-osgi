package org.ertuo.taoplugin.controller;

import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import org.apache.commons.lang.StringUtils;
import org.apache.http.impl.cookie.DateUtils;
import org.ertuo.taoplugin.bean.CityNum;
import org.ertuo.taoplugin.bean.SaleNum;
import org.ertuo.taoplugin.bean.ScheduleProcess;
import org.ertuo.taoplugin.dao.GeneralDao;
import org.ertuo.taoplugin.facade.TopFacade;
import org.ertuo.taoplugin.web.util.LianTingWebClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import com.taobao.api.domain.Item;

/**
 * 定时任务
 * 
 * @author mo.duanm
 * 
 */
@Controller
public class SaleNumController {

    private static final Logger logger      = Logger.getLogger(SaleNumController.class.getName());

    @Autowired
    GeneralDao<SaleNum>         saleNumDao;

    @Autowired
    GeneralDao<ScheduleProcess> scheduleDao;

    @Autowired
    GeneralDao<CityNum>         cityNumDao;

    @Autowired
    TopFacade                   topFacade;

    final UserService           userService = UserServiceFactory.getUserService();

    @Autowired
    LianTingWebClient           lianTingWebClient;

    @RequestMapping("/sale/list")
    public ModelAndView list(ModelAndView mv, String city) {
        mv.addObject("city", city);
        List<CityNum> cns = cityNumDao.find(null);
        mv.addObject("cns", cns);
        if (StringUtils.isBlank(city)) {
            return mv;
        }
        ScheduleProcess sp = scheduleDao.get(city);
        String cityCHS = cityNumDao.get(city).getChsName();
        List<SaleNum> sns = saleNumDao.find("select s from SaleNum s where date>='" + sp.getDate()
                                            + "' and city='" + cityCHS + "'");
        logger.info("结果长度" + sns.size());
        mv.addObject("sns", sns);

        mv.addObject("updateTime", DateUtils.formatDate(new Date(), "yyyyMMdd"));
        return mv;
    }

    @RequestMapping(value = "/sale/send", method = RequestMethod.POST)
    public ModelAndView send(ModelAndView mv, String desc, String city) {

        CityNum cy = cityNumDao.get(city);
        if (StringUtils.isNotBlank(desc)) {
            Item it = topFacade.updateItemDesc(cy.getItemId(), desc,
                cy.getTitle() + DateUtils.formatDate(new Date(), "MMdd") + "更新");
            if (it == null) {
                mv.addObject("rs", "更新失败");
                logger.warning("更新[" + city + "]失败");
                mv.setViewName("sale/list");
                return mv;
            }
        }
        mv.setViewName("redirect:http://item.taobao.com/item.htm?id=" + cy.getItemId().toString());
        return mv;
    }

    @RequestMapping(value = "/sale/verify", method = RequestMethod.GET)
    public ModelAndView verify(ModelAndView mv, String num, String city) {
        boolean bl = lianTingWebClient.verifyNum(cityNumDao.get(city).getCookies(), num);
        if (bl) {
            mv.addObject("rs", "恭喜，号码[" + num + "]正在热销中！");
        } else {
            mv.addObject("rs", "杯具，这个号码[" + num + "]已经被人抢先一步啦！");
        }
        return mv;
    }
}
