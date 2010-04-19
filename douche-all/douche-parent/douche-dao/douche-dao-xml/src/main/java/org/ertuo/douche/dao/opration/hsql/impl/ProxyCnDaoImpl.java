package org.ertuo.douche.dao.opration.hsql.impl;

import javax.jdo.PersistenceManager;

import org.ertuo.douche.dao.domain.WebProxyDo;
import org.ertuo.douche.dao.opration.impl.JdoRepository;

import com.google.inject.Inject;
import com.google.inject.Provider;

/**
 * 代理中国存储jdo方式实现
 * 
 * @author mo.duanm
 * 
 */
public class ProxyCnDaoImpl extends JdoRepository<WebProxyDo> {

	@Inject
	public ProxyCnDaoImpl(Provider<PersistenceManager> pmProvider) {
		super(WebProxyDo.class, pmProvider);
	}

}
