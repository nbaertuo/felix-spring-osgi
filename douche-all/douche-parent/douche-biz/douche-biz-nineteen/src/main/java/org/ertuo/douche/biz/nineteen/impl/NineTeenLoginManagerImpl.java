/**
 * 
 */
package org.ertuo.douche.biz.nineteen.impl;

import java.util.Iterator;
import java.util.Set;

import org.apache.commons.httpclient.Cookie;
import org.apache.log4j.Logger;
import org.ertuo.douche.biz.nineteen.LoginManager;
import org.ertuo.douche.dao.constant.NineTeenConstant;
import org.ertuo.douche.engine.htmlutil.webclient.WebClientLocal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gargoylesoftware.htmlunit.html.HtmlForm;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlPasswordInput;
import com.gargoylesoftware.htmlunit.html.HtmlSubmitInput;
import com.gargoylesoftware.htmlunit.html.HtmlTextInput;

/**
 * 19Â¥µÇÂ¼¹ÜÀíÆ÷
 * @author mo.duanm
 *
 */
@Service("nineTeenLoginManager")
public class NineTeenLoginManagerImpl implements LoginManager {
	private final Logger log=Logger.getLogger(NineTeenLoginManagerImpl.class);
	
	@Autowired
	private WebClientLocal webClientLocal;

	/* (non-Javadoc)
	 * @see org.ertuo.douche.biz.nineteen.LoginManager#getCookies(java.lang.String)
	 */
	public void getCookies(String userName) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see org.ertuo.douche.biz.nineteen.LoginManager#getRandomCookies()
	 */
	public void getRandomCookies() {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see org.ertuo.douche.biz.nineteen.NineTeenManager#login()
	 */
	public boolean login() {
		try {
		
		
		HtmlPage page1 = webClientLocal.getHtmlPageByUrl(NineTeenConstant.login_url);
		final HtmlForm form = page1.getFormByName("login");
		
		form.setActionAttribute(NineTeenConstant.login_action);

		final HtmlSubmitInput button = (HtmlSubmitInput) form
				.getInputByName("loginsubmit");
		final HtmlTextInput username = (HtmlTextInput) form
				.getInputByName("username");
		final HtmlPasswordInput password = (HtmlPasswordInput) form
				.getInputByName("password");
		username.setValueAttribute("summersnow88");
		password.setValueAttribute("keyidaxie");
		webClientLocal.getClickHtmlPage(button);
		Set<Cookie> cookies=webClientLocal.webClient.getCookieManager().getCookies(NineTeenConstant.domain);
		Iterator<Cookie> it=cookies.iterator();
	    while(it.hasNext()){
	    	Cookie co=it.next();
	    	log.debug(co.getValue());
	    }
		
		} catch (Exception e) {
			log.error("µÇÂ¼Ê§°Ü",e);
			return false;
		}
		return true;
	}

	/* (non-Javadoc)
	 * @see org.ertuo.douche.biz.nineteen.LoginManager#loginOnCookies()
	 */
	public boolean loginOnCookies() {
		// TODO Auto-generated method stub
		return false;
	}

}
