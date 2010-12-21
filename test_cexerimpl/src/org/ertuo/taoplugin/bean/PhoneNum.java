package org.ertuo.taoplugin.bean;

import java.util.List;

import javax.jdo.annotations.PrimaryKey;
import javax.jdo.annotations.Unique;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity(name = "PhoneNum")
public class PhoneNum {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @PrimaryKey
    Long          id;

    @Unique
    @Column
    String        num;

    @Column
    String        prise;

    @Column
    String        date;

    @Column
    String        city;

    @OneToMany(mappedBy = "phoneNum", fetch = FetchType.LAZY)
    //cascade = CascadeType.ALL
    List<SellNum> sellNums;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
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

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public List<SellNum> getSellNums() {
        return sellNums;
    }

    public void setSellNums(List<SellNum> sellNums) {
        this.sellNums = sellNums;
    }

}
