package org.ertuo.htmlutil.proxycn.domain;

import java.util.Date;

/**
 * 代理对象
 * @author mo.duanm
 *
 */
public class WebProxy {
	
	private int id;
	
	private String url;
	
	private String port;
	
	private Date useDate;
	
	private Date checkDate;

	 

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
	}

	public Date getUseDate() {
		return useDate;
	}

	public void setUseDate(Date useDate) {
		this.useDate = useDate;
	}

	public Date getCheckDate() {
		return checkDate;
	}

	public void setCheckDate(Date checkDate) {
		this.checkDate = checkDate;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

}
