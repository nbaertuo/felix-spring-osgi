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
 * 
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
            render(text:"请选择商品")
        }

        def item= tbService.get (id, session.top_session).item

        if(item){
            Goods g=new Goods(fId:item.numIid,price:item.price as Double,title:item.title,seller:session.user,gmtCreate:new Date(),descs:"")
            if(!g.save()){
                g.errors.each { println it }
                render(text:"申请失败")
            }else{
                logger.info "商品$g.title 保存成功"
                render(text:"申请成功")
            }
        }else{
            render(text:"商品不存在")
        }
    }

    /**  商品查询*/
    def search={
    }

    /**  管理员审批上架商品*/
    def check={
    }
}
