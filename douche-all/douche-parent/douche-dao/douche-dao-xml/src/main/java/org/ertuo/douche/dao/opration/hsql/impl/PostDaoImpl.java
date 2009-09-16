package org.ertuo.douche.dao.opration.hsql.impl;

import org.ertuo.douche.dao.domain.PostDo;
import org.ertuo.douche.dao.opration.PostDao;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Service;

/**
 * @author Administrator
 *
 */
@Service("postDao")
public class PostDaoImpl implements PostDao{
	
	@Autowired
	private SessionFactory sessionFactory;

	public PostDo getPostById(String postId) {
		 
		return this.getHibernateTemplate(sessionFactory).get(PostDo.class, postId);
	}

	public void savePost(PostDo postDo) {
		this.getHibernateTemplate(sessionFactory).save(postDo);
		
	}
	
	protected HibernateTemplate getHibernateTemplate(
			SessionFactory sessionFactory) {
		return new HibernateTemplate(sessionFactory);
	}

}
