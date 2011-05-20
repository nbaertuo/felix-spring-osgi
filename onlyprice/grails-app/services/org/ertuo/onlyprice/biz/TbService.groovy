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
        ItemsOnsaleGetRequest req=new ItemsOnsaleGetRequest();
        req.setFields("approve_status,num_iid,title,nick,type,cid,pic_url,num,props,valid_thru,list_time,price,has_discount,has_invoice,has_warranty,has_showcase,modified,delist_time,postage_id,seller_cids,outer_id");
        req.q=q
        tbClient.execute req,session
    }

    def get(id,sessionKey){

        if(!id||!sessionKey){
            logger.error "淘宝请求$id $sessionKey 参数为空"
            return
        }
        ItemGetRequest req=new ItemGetRequest();
        req.setFields("detail_url,num_iid,title,nick,type,cid,seller_cids,props,input_pids,input_str,desc,pic_url,num,valid_thru,list_time,delist_time,stuff_status,location,price,post_fee,express_fee,ems_fee,has_discount,freight_payer,has_invoice,has_warranty,has_showcase,modified,increment,approve_status,postage_id,product_id,auction_point,property_alias,item_img,prop_img,sku,video,outer_id,is_virtual");
        req.setNumIid(id as Long);
        tbClient.execute(req , sessionKey);
    }
}
