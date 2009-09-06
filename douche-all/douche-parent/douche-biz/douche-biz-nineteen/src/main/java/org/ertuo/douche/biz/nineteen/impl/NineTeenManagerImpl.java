package org.ertuo.douche.biz.nineteen.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.ertuo.douche.biz.nineteen.NineTeenManager;
import org.ertuo.douche.engine.htmlutil.webclient.WebClientLocal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
import com.gargoylesoftware.htmlunit.html.HtmlForm;
import com.gargoylesoftware.htmlunit.html.HtmlHiddenInput;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlPasswordInput;
import com.gargoylesoftware.htmlunit.html.HtmlSubmitInput;
import com.gargoylesoftware.htmlunit.html.HtmlTextArea;
import com.gargoylesoftware.htmlunit.html.HtmlTextInput;

/**
 * 饮食
 * @author mo.duanm
 *
 */
@Service
public class NineTeenManagerImpl implements NineTeenManager{
	private final Logger log=org.apache.log4j.Logger.getLogger(NineTeenManagerImpl.class);
	
	@Autowired
	private WebClientLocal webClientLocal;
	
	//登陆地址
	private static String login_url = "http://www.19lou.com/passportlogin.php?action=login";
	
	//登陆action
	private static String login_action = "http://www.19lou.com/passportlogin.php?action=login&referer=http%3A%2F%2Fwww.19lou.com%2F";
	
	
	private String[] biteSupUrl=new String[]{
			"http://food.19lou.com/","http://tour.19lou.com/","http://auto.19lou.com/","http://fashion.19lou.com/",
			//"http://love.19lou.com/","http://baby.19lou.com/","http://family.19lou.com/","http://money.19lou.com/",
			//"http://house.19lou.com/","http://home.19lou.com/","http://digi.19lou.com/","http://edu.19lou.com/",
			//"http://job.19lou.com/","http://health.19lou.com/","http://sport.19lou.com/","http://bb.19lou.com/",
			//"http://design.19lou.com/","http://photo.19lou.com/","http://ent.19lou.com/"
			};
	
	
	
	
	//回帖内容
	private static String guanggao="&nbsp;[h2008] 支持楼主！顶起！[h2003]";
	 
	/* (non-Javadoc)
	 * @see org.ertuo.douche.biz.nineteen.NineTeenManager#login()
	 */
	public boolean login() {
		try {
		
		HtmlPage page1 = webClientLocal.getHtmlPageByUrl(login_url);
		final HtmlForm form = page1.getFormByName("login");
		form.setActionAttribute(NineTeenManagerImpl.login_action);

		final HtmlSubmitInput button = (HtmlSubmitInput) form
				.getInputByName("loginsubmit");
		final HtmlTextInput username = (HtmlTextInput) form
				.getInputByName("username");
		final HtmlPasswordInput password = (HtmlPasswordInput) form
				.getInputByName("password");
		final HtmlHiddenInput formhash = (HtmlHiddenInput) form
				.getInputByName("formhash");
		formhash.setValueAttribute("a31eb5c8");
		username.setValueAttribute("summersnow8");
		password.setValueAttribute("keyidaxie");
		webClientLocal.getClickHtmlPage(button);
		} catch (Exception e) {
			log.error("登录失败",e);
			return false;
		}
		return true;
	}
	 
	/* (non-Javadoc)
	 * @see org.ertuo.douche.biz.nineteen.NineTeenManager#getFloors()
	 */
	public List<String> getFloors(){
		List<String> floorList=new ArrayList<String>();
		HtmlPage page=null;
		for (String site : biteSupUrl) {
			page=webClientLocal.getHtmlPageByUrl(site);	
		}
		List<HtmlAnchor> auchors= page.getAnchors();
		for (HtmlAnchor htmlAnchor : auchors) {
			String href=htmlAnchor.getHrefAttribute();
			if(StringUtils.isNotBlank(href)){
				String regex = ".*(forum)-[1-9]\\d{0,4}-[1][.](html)";
				if(href.matches(regex)){
					floorList.add(htmlAnchor.getHrefAttribute());
					//log.info(href);
				}
			}
		}
		
		return floorList;
	}
	
	 
	/* (non-Javadoc)
	 * @see org.ertuo.douche.biz.nineteen.NineTeenManager#getFloorList(java.lang.String)
	 */
	public Map<String,String> getFloorList(String url){
		Map<String,String> newsList=new HashMap<String, String>();
		
		if(StringUtils.isBlank(url)||StringUtils.startsWith(url, "/")){
			return newsList;
		}
		
		String forumName=url.substring(url.lastIndexOf("/")+1, url.indexOf(".html")-1)+"thread-";
		//log.info(forumName);
		//取得楼层
		String floor="";
		String[] floorIds=forumName.split("[^1-9]");
		
		for (String floorId : floorIds) {
			if(StringUtils.isNotBlank(floorId)){
				floor=floorId;
			}
		}
		HtmlPage page=webClientLocal.getHtmlPageByUrl(url);
		List<HtmlAnchor> anchors= page.getAnchors();
		for (HtmlAnchor htmlAnchor : anchors) {
			if(StringUtils.isBlank(htmlAnchor.getHrefAttribute())){
				continue;
			}
			String href=htmlAnchor.getHrefAttribute();
			if(href.startsWith(forumName)){
				String[] ids=href.split("[^1-9]");
				for (String id : ids) {
					if(id.length()>7){
						//log.info("可回复的链接["+href+"]["+id+"]");
						newsList.put(id,floor);
					}
				}
				
				
			}
			
			
			
		}
		 
		
		return newsList;
		
	}

	
	/* (non-Javadoc)
	 * @see org.ertuo.douche.biz.nineteen.NineTeenManager#getNewsTitles()
	 */
	public List<String> getNewsTitles(){
		
		NineTeenManagerImpl biteSup=new NineTeenManagerImpl();
		List<String> floorList=biteSup.getFloors();
		for (String floor : floorList) {
			Map<String,String> newsMap=biteSup.getFloorList(floor);
			 
			for (String id : newsMap.keySet()) {
				biteSup.answer(newsMap.get(id),id);
			}
		}
		
		return null;
		
	}
	 
	/* (non-Javadoc)
	 * @see org.ertuo.douche.biz.nineteen.NineTeenManager#answer(java.lang.String, java.lang.String)
	 */
	public void answer(String floorId,String newsId) {
		String answerUrl="http://www.19lou.com/post.php?action=reply&fid="+floorId+"&tid="+newsId+"&extra=page%3D1";
		try {
		HtmlPage loginAfterPagee=webClientLocal.getHtmlPageByUrl(answerUrl);
		HtmlForm postform = (HtmlForm) loginAfterPagee
				.getElementById("postform");
		if(!postform.asText().contains("请谨慎发帖")){
			log.info("帖子["+answerUrl+"]可能不存在或者审核中!");
			return;
		}
		// 内容
		HtmlTextArea message = postform.getTextAreaByName("message");
		// 设置value
		message.focus();
		message.setText(guanggao);
		message.blur();
		final HtmlSubmitInput replysubmit = (HtmlSubmitInput) postform
				.getInputByName("replysubmit");
		HtmlPage htmlPage=webClientLocal.getClickHtmlPage(replysubmit);
		if(htmlPage.asText().contains("http://shop58883417.taobao.com/")){
			log.info("帖子["+answerUrl+"]回复");
			try {
				Thread.currentThread().sleep(30*1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		} catch (Exception e) {
			//log.error("回复["+answerUrl+"]错误",e);
		}
	}
}
