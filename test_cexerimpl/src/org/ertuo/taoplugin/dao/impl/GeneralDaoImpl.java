package org.ertuo.taoplugin.dao.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.ertuo.taoplugin.dao.GeneralDao;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.orm.jpa.support.JpaDaoSupport;
import org.springframework.transaction.annotation.Transactional;

/**
 * 常用操作类
 * @author mo.duanm
 *
 */
public abstract class GeneralDaoImpl<T> extends JpaDaoSupport implements GeneralDao<T> {

    public abstract Class getTclass();

    @Transactional
    public void create(T t) {
        try {
            getJpaTemplate().persist(t);
        } catch (DataIntegrityViolationException e) {
            //如果已经持久化，就更新这个对象
            getJpaTemplate().merge(t);
        }

    }

    @Transactional
    public void delete(T t) {
        getJpaTemplate().remove(t);

    }

    public T get(Object id) {
        return (T) getJpaTemplate().find(getTclass(), id);

    }

    public List<T> find(String sql) {
        if (StringUtils.isBlank(sql)) {
            sql = "select w from  " + getTclass().getSimpleName() + " w";
        }
        return getJpaTemplate().find(sql);
    }
}
