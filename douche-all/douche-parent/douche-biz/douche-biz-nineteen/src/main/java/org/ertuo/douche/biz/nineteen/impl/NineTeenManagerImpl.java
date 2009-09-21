package org.ertuo.douche.biz.nineteen.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.ertuo.douche.biz.nineteen.NineTeenManager;
import org.ertuo.douche.dao.constant.DoucheConstant;
import org.ertuo.douche.dao.constant.NineTeenConstant;
import org.ertuo.douche.dao.domain.PostDo;
import org.ertuo.douche.dao.opration.PostDao;
import org.ertuo.douche.db.hsql.HSQLServer;
import org.ertuo.douche.engine.htmlutil.webclient.WebClientLocal;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gargoylesoftware.htmlunit.html.DomNodeList;
import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlForm;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlSubmitInput;
import com.gargoylesoftware.htmlunit.html.HtmlTextArea;

/**
 * 饮食
 * @author mo.duanm
 *
 */
@Service("nineTeenManager")
public class NineTeenManagerImpl implements NineTeenManager,InitializingBean{
	private final Logger log=org.apache.log4j.Logger.getLogger(NineTeenManagerImpl.class);
	
	
	@Autowired
	private PostDao postDao;
	
	@Autowired
	private WebClientLocal webClientLocal;
	
	
	
	/**
	 * 帖子浏览id
	 */
	private static String viewId;
	
	/**
	 * 回帖id
	 */
	private static String postId;
	
	
	
	
	/**
	 * 上次发帖时间
	 */
	private static Date prePostTime=new Date();
	
	
	
	/**
	 * 分栏目
	 */
	private List<String> categorys=new ArrayList<String>();
	
	
	
	
	 
	 
	/* (non-Javadoc)
	 * @see org.ertuo.douche.biz.nineteen.NineTeenManager#getFloors()
	 */
	public List<String> getFloors(){
		List<String> floorList=new ArrayList<String>();
		
		int size=categorys.size();
		Random random=new Random();
		//随机数
		int select=random.nextInt(size); 
		HtmlPage page=webClientLocal.getHtmlPageByUrl(categorys.get(select));
		
		if(page==null){
			return floorList;
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
		
		if(StringUtils.isBlank(url)||url.startsWith("/")){
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
		if(page==null){
			return newsList;
		}
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
		
		 viewId="http://www.19lou.com/forum-"+floorId+"-thread-"+newsId+"-1-1.html";
		 postId="http://www.19lou.com/post.php?action=reply&fid="+floorId+"&tid="+newsId+"&extra=page%3D1";
		
		if(postDao.getPostById(viewId)!=null){
			//已经回复过 重复回复
			return;
		}
		
		
		try {
		HtmlPage loginAfterPagee=webClientLocal.getHtmlPageByUrl(postId);
		
		//是第一页才回复
		if(this.isFirstPage(loginAfterPagee)){
			this.replay(loginAfterPagee);
		}
		
		
		
		} catch (Exception e) {
			log.error("回复["+postId+"]错误",e);
		}
	}
	
	/**
	 * 确定是不是第一页
	 * @param page
	 * @return
	 */
	private boolean isFirstPage(HtmlPage page){
		String pageText=page.asText();
		
		if(pageText.contains(NineTeenConstant.isNotFirstPageToken)){
			return false;
		}
		
		return true;
		
	}
	
	/**
	 * 回复帖子 
	 * @param page
	 */
	private void replay(HtmlPage page){
		try {
			
			if (page != null) {
				 
				// 查找textarea
				DomNodeList<HtmlElement> textareas = page
						.getElementsByTagName("textarea");

				if (textareas == null ) {
					//log.error("页面["+page+"]中回复字段个数不等于一");
					return;
				}

				for (HtmlElement htmlElement : textareas) {
					HtmlTextArea message =(HtmlTextArea) htmlElement;
					// 设置value
					Random random=new Random();
					
					message.setText(DoucheConstant.messages[random.nextInt(DoucheConstant.messages.length)]);
				}
				
				List<HtmlForm> forms=page.getForms();
				
				if(forms==null||forms.size()!=1){
					return ;
				}
				 
				HtmlForm htmlForm= forms.get(0);
				this.formSubmit(htmlForm);
				//优先button方式
				/*if(!this.buttonSubmit(htmlForm)){
					//href方式
					this.hrefSubmit(htmlForm);
				}*/
				
			}
		 
		} catch (Exception e) {
			log.error("回复异常",e);
		}
	}
	
	/**
	 * form方式提交
	 * @param htmlForm
	 * @return
	 */
	private boolean formSubmit(HtmlForm htmlForm){
		try {
			HtmlPage page=(HtmlPage) htmlForm.submit(null);
			return this.afterPostOperation(page);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
		
	}
	
	/**
	 * button方式提交
	 * @param htmlForm
	 * @return
	 */
	private boolean buttonSubmit(HtmlForm htmlForm){
		try {
			//查找submit 
			List<HtmlElement> submits =htmlForm.getElementsByAttribute("input", "type", "submit");
			//submit方式提交
			if (submits != null && submits.size() == 1) {
				HtmlSubmitInput replysubmit = (HtmlSubmitInput) submits.get(0);
				HtmlPage replays = replysubmit.click();
				return this.afterPostOperation(replays);
			}
		} catch (Exception e) {
			log.error("按钮方式发帖错误");
		}
		return false;
		
	}
	
	
	
	/**
	 * 超链接方式提交
	 * @param htmlForm
	 * @return
	 */
	private boolean hrefSubmit(HtmlForm htmlForm){
		try {
			HtmlAnchor anchor=htmlForm.getElementById("replysubmit");
			HtmlPage page=anchor.click();
			 
			return this.afterPostOperation(page);
		} catch (Exception e) {
			log.error("按钮方式发帖错误");
		}
		return false;
		
	}
	
	/**
	 * 回复后的操作
	 * @param afterPostPage 回复后跳转的页面
	 * @return
	 */
	private boolean afterPostOperation(HtmlPage afterPostPage){
		if(afterPostPage==null){
			return false;
		}
		//log.debug(afterPostPage.asText());
		postDao.savePost(new PostDo(viewId,"summersnow8"));
		log.info("帖子["+viewId+"]回复成功");
		//发帖休息时间 19lou默认是30秒
		long sleepTime=30*1000;
		//和上次发帖的间隔时间
		long interval=new Date().getTime()-prePostTime.getTime();
		if(interval<=30*1000){
			sleepTime=sleepTime-interval;
		}else{
			return true;
		}
		prePostTime=new Date();
		try {
			log.debug("休息["+sleepTime/1000+"]秒");
			Thread.sleep(sleepTime);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return true;
		
	}
	
	/**
	 * 初始化所有分栏目
	 * @return
	 */
	private void initCategorys(){
		if(categorys.size()==0){
			HtmlPage page=webClientLocal.getHtmlPageByUrl(NineTeenConstant.host);
			List<HtmlAnchor> anchors= page.getAnchors();
			for (HtmlAnchor htmlAnchor : anchors) {
		        String anchor=htmlAnchor.getHrefAttribute();
		        if(anchor.matches(NineTeenConstant.categoryReg)){
		        	log.debug("栏目["+anchor+"]符合");
		        	categorys.add(anchor);
		        }
			}
		}
	}

	/* (non-Javadoc)
	 * @see org.springframework.beans.factory.InitializingBean#afterPropertiesSet()
	 */
	public void afterPropertiesSet() throws Exception {
		this.initCategorys();
		
	}
	
}
