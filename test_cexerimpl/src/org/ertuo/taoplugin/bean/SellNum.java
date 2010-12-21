package org.ertuo.taoplugin.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.google.appengine.api.datastore.Key;

@Entity(name = "SellNum")
public class SellNum {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //@PrimaryKey
    //    @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
    Key      id;

    @ManyToOne(fetch = FetchType.LAZY)
    //@Embedded
    PhoneNum phoneNum;

    @Column
    String   viewNum;

    @Column
    String   prise;

    @Column
    String   date;

    public String getDisplay() {
        return phoneNum.num.replaceAll(viewNum, "<font color='red'>" + viewNum + "</font>");
    }

    public Key getId() {
        return id;
    }

    public void setId(Key id) {
        this.id = id;
    }

    public PhoneNum getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(PhoneNum phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getViewNum() {
        return viewNum;
    }

    public void setViewNum(String viewNum) {
        this.viewNum = viewNum;
    }

    public String getPrise() {
        return prise;
    }

    public void setPrise(String prise) {
        this.prise = prise;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

}
