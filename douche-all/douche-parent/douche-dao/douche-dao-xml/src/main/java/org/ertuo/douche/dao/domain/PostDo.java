package org.ertuo.douche.dao.domain;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class PostDo {
	
	@Id
	private String postId;
	
	private String userId;
	
	public  PostDo() {
		
	}
	public  PostDo(String postId,String userId) {
		this.postId=postId;
		this.userId=userId;
	}

	public String getPostId() {
		return postId;
	}

	public void setPostId(String postId) {
		this.postId = postId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

}
