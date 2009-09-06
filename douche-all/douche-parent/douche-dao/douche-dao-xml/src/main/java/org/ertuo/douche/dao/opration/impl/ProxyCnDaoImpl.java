package org.ertuo.douche.dao.opration.impl;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.ertuo.douche.dao.domain.WebProxyDo;
import org.ertuo.douche.dao.opration.ProxyCnDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.thoughtworks.xstream.XStream;

/**
 * 代理中国存储（xml方式）实现类
 * 
 * @author mo.duanm
 *
 */
@Service("proxyCnDao")
public class ProxyCnDaoImpl implements ProxyCnDao {
	
	@Autowired
	private XStream stream;
	
	private static final String location="d:/douche/proxy-data.xml";
	

	/* (non-Javadoc)
	 * @see org.ertuo.douche.dao.opration.ProxyCnDao#createProxy(org.ertuo.douche.dao.domain.WebProxyDo)
	 */
	public void createProxy(WebProxyDo webProxyDo) {
		if(webProxyDo==null){
		  throw new IllegalArgumentException("传入参数["+webProxyDo+"]为null");	
		}
		try {
			stream.toXML(webProxyDo, new FileOutputStream(location));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}  

	}

	public Map<String,WebProxyDo> getInvailProxys() {
		try {
			return (Map<String, WebProxyDo>) stream.fromXML(new FileInputStream(location));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
		return new HashMap<String,WebProxyDo>();
	}

	public void createProxy(Map<String,WebProxyDo> webProxyDos) {
		if(webProxyDos==null){
			throw new IllegalArgumentException("参数["+webProxyDos+"]为null");
		}
		try {
			stream.toXML(webProxyDos, new FileOutputStream(location));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}  
	}

	public void removePeoxy(WebProxyDo webProxyDo) {
		Map<String,WebProxyDo> webProxyDos=this.getInvailProxys();
		String key=webProxyDo.getUrl()+":"+webProxyDo.getPort();
		webProxyDos.remove(key);
		this.createProxy(webProxyDos);
	}

}
