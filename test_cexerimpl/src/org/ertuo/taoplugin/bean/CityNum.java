package org.ertuo.taoplugin.bean;

import javax.jdo.annotations.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "CityNum")
public class CityNum {
    @Id
    String number;
    @Column
    String name;
    @Column
    String cookies;
    @Column
    String chsName;
    @Column
    Long   itemId;
    @Column
    String title;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCookies() {
        return cookies;
    }

    public void setCookies(String cookies) {
        this.cookies = cookies;
    }

    public String getChsName() {
        return chsName;
    }

    public void setChsName(String chsName) {
        this.chsName = chsName;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

}
