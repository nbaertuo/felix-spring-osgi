package org.ertuo.taoplugin.dao;

import org.ertuo.taoplugin.bean.CronProcess;

public interface CronProcessDao {

    public void create(CronProcess message);

    public CronProcess findByCodex(String codex);
}
