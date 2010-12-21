package org.ertuo.taoplugin.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "CronProcess")
public class CronProcess {

    /*@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    Long cronId*/

    @Id
    String codex;

    @Column
    Long   phoneId;

    public String getCodex() {
        return codex;
    }

    public void setCodex(String codex) {
        this.codex = codex;
    }

    public Long getPhoneId() {
        return phoneId;
    }

    public void setPhoneId(Long phoneId) {
        this.phoneId = phoneId;
    }

}
