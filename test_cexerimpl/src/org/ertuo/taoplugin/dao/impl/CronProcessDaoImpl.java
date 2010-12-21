package org.ertuo.taoplugin.dao.impl;

import java.util.List;

import org.ertuo.taoplugin.bean.CronProcess;
import org.ertuo.taoplugin.dao.CronProcessDao;
import org.springframework.orm.jpa.support.JpaDaoSupport;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

public class CronProcessDaoImpl extends JpaDaoSupport implements CronProcessDao {

    @Transactional
    public void create(CronProcess message) {
        getJpaTemplate().persist(message);
    }

    public CronProcess findByCodex(String codex) {
        List<CronProcess> cps = getJpaTemplate().find(
            "select b from CronProcess  b where codex='" + codex + "'");
        return CollectionUtils.isEmpty(cps) ? null : cps.get(0);
    }
}
