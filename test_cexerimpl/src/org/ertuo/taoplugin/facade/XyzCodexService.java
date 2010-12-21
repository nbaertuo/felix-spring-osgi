package org.ertuo.taoplugin.facade;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import org.apache.commons.lang.StringUtils;
import org.ertuo.taoplugin.util.SpitUtils;
import org.springframework.stereotype.Component;

@Component
public class XyzCodexService {

    private static final Logger log = Logger.getLogger(XyzCodexService.class.getName());

    /**
     * 对数组号码进行处理
     * @param numPhone
     */
    String process(String pnum) {
        String spitStr = null;
        List<String> list = new ArrayList<String>();
        Date d1 = new Date();
        SpitUtils.computeDuplicateVar(pnum, 3, list);
        for (String it : list) {
            spitStr = StringUtils.isBlank(spitStr) ? it : spitStr + "," + it;
        }
        //list.each { spitStr=StringUtils.isBlank(spitStr)?it:spitStr+","+it}
        Date d2 = new Date();
        log.info("analyse  " + pnum + " " + (d2.getTime() - d1.getTime()) / 1000
                 + " min .result is " + spitStr);
        return spitStr;
    }
}
