package org.ertuo.taoplugin.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "SaleNum")
public class SaleNum {

    @Id
    String num;

    @Column
    String viewNum;

    @Column
    String prise;

    @Column
    String date;

    @Column
    String city;

    @Column
    String type;

    public String getDisplay() {
        String[] rs = viewNum.split("\n");
        //长度大于0 例如22344=22\n44

        if (rs.length > 1) {
            String dispaly = num;
            for (String string : rs) {
                dispaly = dispaly.replaceAll(string, "<font color='red'>" + string + "</font>");
            }
            return dispaly;
        }
        if (viewNum.length() > 12) {
            return viewNum;
        }
        return num.replaceAll(viewNum, "<font color='red'>" + viewNum + "</font>");
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

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

}
