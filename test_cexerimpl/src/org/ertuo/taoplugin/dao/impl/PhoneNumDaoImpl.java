package org.ertuo.taoplugin.dao.impl;

import java.util.List;

import org.ertuo.taoplugin.bean.PhoneNum;
import org.ertuo.taoplugin.dao.PhoneNumDao;
import org.springframework.orm.jpa.support.JpaDaoSupport;
import org.springframework.transaction.annotation.Transactional;

public class PhoneNumDaoImpl extends JpaDaoSupport implements PhoneNumDao {

    @Transactional
    public void createPhoneNum(PhoneNum message) {
        getJpaTemplate().persist(message);
    }

    public PhoneNum get(Long id) {
        return getJpaTemplate().find(PhoneNum.class, id);
    }

    @Transactional
    public void delete(PhoneNum phoneNum) {
        getJpaTemplate().remove(phoneNum);
    }

    public List<PhoneNum> findByNumId(Long numId) {
        return getJpaTemplate().find(
            "select b from PhoneNum  b where b.id > " + numId + "  order by b.id");
    }

    public List<PhoneNum> top() {
        return getJpaTemplate().find("select b from PhoneNum  b ");
    }

}
