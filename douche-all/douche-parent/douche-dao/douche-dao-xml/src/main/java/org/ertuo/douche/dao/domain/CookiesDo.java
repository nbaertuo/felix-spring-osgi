/**
 * 
 */
package org.ertuo.douche.dao.domain;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.NullValue;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

/**
 * @author mo.duanm
 *
 */
@PersistenceCapable(identityType = IdentityType.APPLICATION)
public class CookiesDo {
	
	@PrimaryKey
    @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private String id;
	
	@Persistent(nullValue=NullValue.EXCEPTION)
	private String username;
	
	@Persistent(nullValue=NullValue.EXCEPTION)
	private String host;
	
	@Persistent(nullValue=NullValue.EXCEPTION)
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
