package org.ertuo.taoplugin.dao.impl;

import java.util.List;

import org.ertuo.taoplugin.bean.ProxyIp;
import org.ertuo.taoplugin.dao.ProxyIpDao;
import org.springframework.orm.jpa.support.JpaDaoSupport;
import org.springframework.transaction.annotation.Transactional;

public class ProxyIpDaoImpl extends JpaDaoSupport implements ProxyIpDao {

	@Transactional
	public void createProxyPage(ProxyIp proxyPage) {
		getJpaTemplate().persist(proxyPage);
	}

	public ProxyIp get(String id) {
		// TODO Auto-generated method stub
		return getJpaTemplate().find(ProxyIp.class, id);
	}

	public List<ProxyIp> getAll() {
		// TODO Auto-generated method stub
		return getJpaTemplate().find("select h from ProxyIp h");
	}

}
