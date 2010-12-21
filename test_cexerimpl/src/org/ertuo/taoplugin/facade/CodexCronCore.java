package org.ertuo.taoplugin.facade;

import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import org.apache.commons.lang.StringUtils;
import org.apache.http.impl.cookie.DateUtils;
import org.ertuo.taoplugin.bean.Regular;
import org.ertuo.taoplugin.bean.SaleNum;
import org.ertuo.taoplugin.dao.GeneralDao;
import org.ertuo.taoplugin.util.RegUtil;
import org.springframework.beans.factory.annotation.Autowired;

abstract class CodexCronCore {

    protected static final Logger logger = Logger.getLogger(CodexCronCore.class.getName());

    @Autowired
    XyzCodexService               xyzCodexService;

    @Autowired
    GeneralDao<SaleNum>           saleNumDao;

    @Autowired
    GeneralDao<Regular>           regularDao;

    /**
     * 分析号码拿到结果
     * @param num
     * @return
     */
    abstract String process(String num);

    public void asynCron(List<SaleNum> sns, String city) {
        for (SaleNum sn : sns) {
            sn.setCity(city);
            sn.setDate(DateUtils.formatDate(new Date(), "yyyyMMdd"));
            String viewNum = sn.getNum();
            List<Regular> regs = regularDao.find(null);
            for (Regular reg : regs) {
                sn.setType(reg.getType());
                String rss = RegUtil.getStrByReg(sn.getNum(), reg.getReg(), true);
                String[] rs = rss.split("\n");

                //长度大于0 例如22344=22\n44
                if (rs.length >= reg.getLength()) {
                    viewNum = rss;
                    logger.info("发现目标[" + sn.getNum() + "]结果是" + rss);
                    sn.setViewNum(viewNum);
                    saleNumDao.create(sn);
                }
                //长度为0 例如1231234=123123
                if (reg.getLength() == 0 && StringUtils.isNotBlank(rss)) {
                    viewNum = viewNum.replaceAll(rss, "<font color='red'>" + rss + "</font>");
                    saleNumDao.create(sn);
                }

            }

            String rs = this.process(sn.getNum());
            //logger.info("分析[" + sn.getNum() + "]结果是" + rs);
            if (StringUtils.isNotBlank(rs)) {
                logger.info("发现目标[" + sn.getNum() + "]结果是" + rs);
                sn.setViewNum(rs);
                saleNumDao.create(sn);
            }
        }

    }
}
