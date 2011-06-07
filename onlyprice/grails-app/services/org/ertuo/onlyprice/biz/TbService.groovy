/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2011 All Rights Reserved.
 */
package org.ertuo.onlyprice.biz

import org.slf4j.Logger
import org.slf4j.LoggerFactory

import com.taobao.api.TaobaoClient
import com.taobao.api.request.ItemGetRequest
import com.taobao.api.request.ItemsOnsaleGetRequest

/**
 * 
 * @author mo.duanm
 * @version $Id: TbService.groovy, v 0.1 2011-4-15 下午05:45:39 mo.duanm Exp $
 */
class TbService {

    def Logger logger=LoggerFactory.getLogger(TbService.class)

    TaobaoClient tbClient




    def onSaleGet(q,session){
        logger.info "查询条件$q"
        ItemsOnsaleGetRequest req=new ItemsOnsaleGetRequest();
        req.setFields("approve_status,num_iid,title,nick,pic_url,price,desc");
        req.q=q
        tbClient.execute req,session
    }

    def get(id,sessionKey){

        if(!id||!sessionKey){
            logger.error "淘宝请求$id $sessionKey 参数为空"
            return
        }
        ItemGetRequest req=new ItemGetRequest();
        req.setFields("detail_url,num_iid,title,nick,desc,pic_url,price");
        req.setNumIid(id as Long);
        tbClient.execute(req , sessionKey);
    }
}
