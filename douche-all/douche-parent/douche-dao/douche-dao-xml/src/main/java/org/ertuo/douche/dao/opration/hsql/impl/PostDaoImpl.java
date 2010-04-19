package org.ertuo.douche.dao.opration.hsql.impl;

import javax.jdo.PersistenceManager;

import org.ertuo.douche.dao.domain.PostDo;
import org.ertuo.douche.dao.opration.impl.JdoRepository;

import com.google.inject.Provider;

/**
 * @author Administrator
 * 
 */
public class PostDaoImpl extends JdoRepository<PostDo> {

	protected PostDaoImpl(Class<PostDo> clazz,
			Provider<PersistenceManager> pmProvider) {
		super(clazz, pmProvider);
		// TODO Auto-generated constructor stub
	}

}
