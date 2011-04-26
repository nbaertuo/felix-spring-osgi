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
                logger.info "goods  $g.title 保存成功"
                render(text:"apply sucess")
            }
        }else{
            render(text:"goods do not exist")
        }
    }

    /**  商品查询*/
    def search={
        def bPrice=params.bPrice
        def ePrice=params.ePrice
        logger.info bPrice+" "+ePrice

        //其中一个为空都不行，如果用户没有填写，页面提交的时候自动填写一个最大值.或者其中一个不是数字
        if((!bPrice||!ePrice)||(!bPrice.isDouble()||!ePrice.isDouble())){
            flash.message = "please input correct price !"
            return
        }
        def bprice=bPrice.toDouble()
        def eprice=ePrice.toDouble()
        def filter="between('price',$bprice,$eprice)"
        def c=Goods.createCriteria()
        def counts=c.count(filter)
        def rs=c.list{
            filter
            maxResults(10)
            firstResult(50)
            order("price","desc")
        }
        [count:counts,list:rs,params:params]
    }

    /**  管理员审批上架商品*/
    def check={
    }
}
