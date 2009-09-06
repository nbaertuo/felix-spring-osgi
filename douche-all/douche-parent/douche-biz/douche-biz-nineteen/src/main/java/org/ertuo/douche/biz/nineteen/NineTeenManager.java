package org.ertuo.douche.biz.nineteen;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.ertuo.douche.engine.htmlutil.webclient.WebClientLocal;
import org.springframework.beans.factory.annotation.Autowired;

import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
import com.gargoylesoftware.htmlunit.html.HtmlForm;
import com.gargoylesoftware.htmlunit.html.HtmlHiddenInput;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlPasswordInput;
import com.gargoylesoftware.htmlunit.html.HtmlSubmitInput;
import com.gargoylesoftware.htmlunit.html.HtmlTextArea;
import com.gargoylesoftware.htmlunit.html.HtmlTextInput;

/**
 * 十九楼管理
 * @author mo.duanm
 *
 */
public interface NineTeenManager {
	 
	 
	 /**
	  * 登陆
	 * @return
	 */
	boolean login() ;
	 
	 /**
	 * @return
	 */
	List<String> getFloors();
	
	/**
	 * 获得楼层和楼层下帖子关系列表
	 * @param url
	 * @return Map<帖子id,楼层id> 
	 */
	 Map<String,String> getFloorList(String url);

	
	/**
	 * 获得帖子列表
	 * @return
	 */
	 List<String> getNewsTitles();
	/**
	 *  回复指定id的帖子
	 * @param floorId 楼层id
	 * @param newsId 帖子id
	 */
	 void answer(String floorId,String newsId) ;
}
