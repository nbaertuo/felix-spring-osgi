package org.ertuo.taoplugin.dao;

import java.util.List;

import org.ertuo.taoplugin.bean.ProxyIp;

public interface ProxyIpDao {

	public void createProxyPage(ProxyIp proxyIp);

	public ProxyIp get(String id);

	public List<ProxyIp> getAll();

}
