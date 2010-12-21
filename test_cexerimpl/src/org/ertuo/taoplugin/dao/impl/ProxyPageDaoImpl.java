package org.ertuo.taoplugin.dao.impl;

import java.util.List;

import org.ertuo.taoplugin.bean.ProxyPage;
import org.ertuo.taoplugin.dao.ProxyPageDao;
import org.springframework.orm.jpa.support.JpaDaoSupport;
import org.springframework.transaction.annotation.Transactional;

public class ProxyPageDaoImpl extends JpaDaoSupport implements ProxyPageDao {

	@Transactional
	public void createProxyPage(ProxyPage proxyPage) {
		getJpaTemplate().persist(proxyPage);
	}

	public ProxyPage get(String id) {
		// TODO Auto-generated method stub
		return getJpaTemplate().find(ProxyPage.class, id);
	}

	@Override
	public List<ProxyPage> getAll() {
		// TODO Auto-generated method stub
		return getJpaTemplate().find("select h from ProxyPage");
	}

}
