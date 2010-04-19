package org.ertuo.douche.biz.nineteen;

import java.util.List;
import java.util.Map;

/**
 * 十九楼管理
 * 
 * @author mo.duanm
 * 
 */
public interface NineTeenManager {

	/**
	 * @return
	 */
	List<String> getFloors();

	/**
	 * 获得楼层和楼层下帖子关系列表
	 * 
	 * @param url
	 * @return Map<帖子id,楼层id>
	 */
	Map<String, String> getFloorList(String url);

	/**
	 * 获得帖子列表
	 * 
	 * @return
	 */
	List<String> getNewsTitles();

	/**
	 * 回复指定id的帖子
	 * 
	 * @param floorId
	 *            楼层id
	 * @param newsId
	 *            帖子id
	 */
	void answer(String floorId, String newsId);
}
