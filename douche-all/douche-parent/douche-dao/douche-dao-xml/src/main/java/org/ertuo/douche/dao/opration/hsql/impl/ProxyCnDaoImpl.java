package org.ertuo.douche.dao.opration.hsql.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.log4j.spi.LoggerFactory;
import org.ertuo.douche.dao.domain.WebProxyDo;
import org.ertuo.douche.dao.opration.ProxyCnDao;
import org.ertuo.douche.db.hsql.HSQLServer;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 代理中国存储hibernate方式实现
 * 
 * @author mo.duanm
 * 
 */
@Service("proxyCnDao")
public class ProxyCnDaoImpl implements ProxyCnDao {
	

	
	private final Logger log=Logger.getLogger(ProxyCnDaoImpl.class);

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
		try {
			this.getHibernateTemplate(sessionFactory).save(webProxyDo);
			this.getInvailProxys();
		} catch (Exception e) {
			log.error("代理["+webProxyDo+"]重复");
		}
		

	}

	 

	protected HibernateTemplate getHibernateTemplate(
			SessionFactory sessionFactory) {
		return new HibernateTemplate(sessionFactory);
	}



	public void createProxy(List<WebProxyDo> webProxyDos) {
		for (WebProxyDo webProxyDo : webProxyDos) {
			this.createProxy(webProxyDo);
			
			
		}
		
	}



	public List<WebProxyDo> getInvailProxys() {
		List<WebProxyDo> rs=this.getHibernateTemplate(sessionFactory).find("from WebProxyDo");
		log.debug("结果集长度["+rs.size()+"]");
		if(rs==null){
			rs=new ArrayList<WebProxyDo>();
		}
		return rs;
	}



	public void removePeoxy(WebProxyDo webProxyDo) {
		this.getHibernateTemplate(sessionFactory).delete(webProxyDo);
		
	}
}
