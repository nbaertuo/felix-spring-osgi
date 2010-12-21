package org.ertuo.taoplugin.dao;

import java.util.List;

import org.ertuo.taoplugin.bean.PhoneNum;

public interface PhoneNumDao {

    public void createPhoneNum(PhoneNum message);

    public PhoneNum get(Long id);

    public void delete(PhoneNum phoneNum);

    public List<PhoneNum> findByNumId(Long numId);

    public List<PhoneNum> top();

}
