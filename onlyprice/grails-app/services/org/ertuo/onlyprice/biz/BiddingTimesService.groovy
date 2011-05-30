/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2011 All Rights Reserved.
 */
package org.ertuo.onlyprice.biz

import java.io.UnsupportedEncodingException;

import org.slf4j.Logger
import org.slf4j.LoggerFactory

import weibo4j.User;
import weibo4j.Weibo
import weibo4j.WeiboException;
import weibo4j.http.AccessToken
import weibo4j.http.RequestToken

/**
 * 竞价的次数服务 
 * @author mo.duanm
 * @version $Id: TbService.groovy, v 0.1 2011-4-15 下午05:45:39 mo.duanm Exp $
 */
class BiddingTimesService {


    def Logger logger=LoggerFactory.getLogger(BiddingTimesService.class)



    /**
     * 增加用户出价记录
     * 如果用户还有出价机会，更新记录返回成功。
     * 如果用户没有出价机会，返回失败。
     * @param uId 用户id
     * @param gId 商品id
     * @return boolean。
     */
    def boolean add(uId,gId) {
    }


    /**
     * 查询用户当前商品的出价次数
     * @param uId
     * @param gId
     * @return
     */
    def queryLimit(uId,gId){
    }

    /**
     * 为用户设置竞价次数上限
     * 这里是个初始化数据，没有限定任何产品
     * @param uId 用户id
     * @param limitTimes 次数限制
     * @return 
     */
    def saveUserLimit(uId,limitTimes){
    }
}
