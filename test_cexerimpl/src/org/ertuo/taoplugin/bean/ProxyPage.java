package org.ertuo.taoplugin.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "ProxyPage")
public class ProxyPage {

	@Id
	String url;

	@Column
	String reg;

	@Column
	String spit = " ";

	public ProxyPage(String url, String reg, String spit) {
		this.url = url;
		this.reg = reg;
		this.spit = spit;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getReg() {
		return reg;
	}

	public void setReg(String reg) {
		this.reg = reg;
	}

	public String getSpit() {
		return spit;
	}

	public void setSpit(String spit) {
		this.spit = spit;
	}

}
