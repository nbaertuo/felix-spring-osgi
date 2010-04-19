package org.ertuo.douche.dao.domain;

import java.io.Serializable;
import java.util.Date;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.NullValue;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

/**
 * 代理对象
 * 
 * @author mo.duanm
 * 
 */
@PersistenceCapable(identityType = IdentityType.APPLICATION)
public class WebProxyDo implements Serializable {

	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.UUIDHEX)
	private String id;

	@Persistent(nullValue = NullValue.EXCEPTION)
	private String url;

	@Persistent(nullValue = NullValue.EXCEPTION)
	private int port;

	@Persistent(nullValue = NullValue.EXCEPTION)
	private Date useDate;

	@Persistent(nullValue = NullValue.EXCEPTION)
	private Date checkDate;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
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

	@Override
	public String toString() {
		return url + ":" + port;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}

}
