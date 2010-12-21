package org.ertuo.taoplugin.dao;

import java.util.List;

/**
 * 提供常用dao操作
 * 增删改查
 * @author mo.duanm
 *
 */
public interface GeneralDao<T> {

    /**
     * 增加，更新
     * @param t
     */
    void create(T t);

    /**
     * 删除
     * @param t
     */
    void delete(T t);

    /**
     * 通过id获取单个实例
     * @param id
     */
    T get(Object id);

    /**
     * 通过sql查询
     * @param sql
     * @return
     */
    List<T> find(String sql);

}
