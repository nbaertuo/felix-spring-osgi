package org.ertuo.douche.dao.opration;

import org.ertuo.douche.dao.domain.PostDo;

public interface PostDao {
	
	/**
	 * 通过id获得回复记录
	 * @param postId
	 * @return
	 */
	PostDo getPostById(String postId);
	
	
	/**
	 * 插入一条回复记录
	 * @param postDo
	 */
	void savePost(PostDo postDo);
	

}
