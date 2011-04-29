/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2011 All Rights Reserved.
 */
package org.ertuo.onlyprice.web.controllers

import org.ertuo.onlyprice.biz.TbService
import org.ertuo.onlyprice.domain.Goods
import org.slf4j.Logger
import org.slf4j.LoggerFactory

/**
 * 商品申请.审核
 * @author mo.duanm
 * @version $Id: TbController.groovy, v 0.1 2011-4-15 下午04:13:08 mo.duanm Exp $
 */
class GoodsController {

    def Logger logger=LoggerFactory.getLogger(GoodsController.class)

    static defaultAction="index"

    TbService tbService


    def index={
    }
    /**  淘宝卖家申请上架*/
    def ajaxAdd={
        def id=params.numId
        if(!id){
            render(text:"please select goods")
        }

        def item= tbService.get (id, session.top_session).item

        if(item){
            Goods g=new Goods(fId:item.numIid,price:item.price as Double,title:item.title,seller:session.user,gmtCreate:new Date(),descs:"",status:0)
            if(!g.save()){
                g.errors.each { logger.info  it.toString() }
                render(text:"apply fail")
            }else{
                logger.info "goods  $g.title save success"
                render(text:"apply sucess")
            }
        }else{
            render(text:"goods do not exist")
        }
    }
}
