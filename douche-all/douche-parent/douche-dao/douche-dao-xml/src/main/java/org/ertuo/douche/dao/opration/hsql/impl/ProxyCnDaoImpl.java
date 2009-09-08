package org.ertuo.douche.dao.opration.hsql.impl;

import java.util.Map;

import org.ertuo.douche.dao.domain.WebProxyDo;
import org.ertuo.douche.dao.opration.ProxyCnDao;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Service;

/**
 * 代理中国存储hibernate方式实现
 * 
 * @author mo.duanm
 * 
 */
@Service("proxyCnDao")
public class ProxyCnDaoImpl implements ProxyCnDao {

	@Autowired
	private SessionFactory sessionFactory;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.ertuo.douche.dao.opration.ProxyCnDao#createProxy(org.ertuo.douche
	 * .dao.domain.WebProxyDo)
	 */
	public void createProxy(WebProxyDo webProxyDo) {
		this.getHibernateTemplate(sessionFactory).persist(webProxyDo);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.ertuo.douche.dao.opration.ProxyCnDao#createProxy(java.util.Map)
	 */
	public void createProxy(Map<String, WebProxyDo> webProxyDos) {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.ertuo.douche.dao.opration.ProxyCnDao#getInvailProxys()
	 */
	public Map<String, WebProxyDo> getInvailProxys() {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.ertuo.douche.dao.opration.ProxyCnDao#removePeoxy(org.ertuo.douche
	 * .dao.domain.WebProxyDo)
	 */
	public void removePeoxy(WebProxyDo webProxyDo) {
		// TODO Auto-generated method stub

	}

	protected HibernateTemplate getHibernateTemplate(
			SessionFactory sessionFactory) {
		return new HibernateTemplate(sessionFactory);
	}
}
