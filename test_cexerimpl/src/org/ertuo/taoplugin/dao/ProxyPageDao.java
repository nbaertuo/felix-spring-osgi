package org.ertuo.taoplugin.dao;

import java.util.List;

import org.ertuo.taoplugin.bean.ProxyPage;

public interface ProxyPageDao {

	public void createProxyPage(ProxyPage proxyPage);

	public ProxyPage get(String id);

	public List<ProxyPage> getAll();

}
