package org.ertuo.taoplugin.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "ScheduleProcess")
public class ScheduleProcess {

    @Id
    String  id;

    /**
     * 日期
     */
    @Column
    String  date;

    /**
     * 总页数
     */
    @Column
    int     totalPage;

    /**
     * 当前处理页
     */
    @Column
    int     currPage;

    /**
     * 是否已经更新到开放平台
     */
    @Column
    boolean isUpdateTOP;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getCurrPage() {
        return currPage;
    }

    public void setCurrPage(int currPage) {
        this.currPage = currPage;
    }

    public boolean isUpdateTOP() {
        return isUpdateTOP;
    }

    public void setUpdateTOP(boolean isUpdateTOP) {
        this.isUpdateTOP = isUpdateTOP;
    }

}
