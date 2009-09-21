/**
 * 
 */
package org.ertuo.douche.dao.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * @author mo.duanm
 *
 */
@Entity
public class CookiesDo {
	
	@Id
	private String id;
	
	private String username;
	
	private String host;
	
	@Column(length=256)
	private String cookies;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String getCookies() {
		return cookies;
	}

	public void setCookies(String cookies) {
		this.cookies = cookies;
	}

}
