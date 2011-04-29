/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2011 All Rights Reserved.
 */
package org.ertuo.onlyprice.web.controllers

import org.ertuo.onlyprice.biz.TbService
import org.ertuo.onlyprice.domain.Goods
import org.ertuo.onlyprice.domain.Shelf;
import org.slf4j.Logger
import org.slf4j.LoggerFactory

/**
 * 商品查询.审核
 * @author mo.duanm
 * @version $Id: TbController.groovy, v 0.1 2011-4-15 下午04:13:08 mo.duanm Exp $
 */
class AdminController {

    def Logger logger=LoggerFactory.getLogger(AdminController.class)

    static defaultAction="index"

    TbService tbService

    def index={
        params.max=20
        params.first=0
        render(view:"search",model:[params:params,counts:0])
    }


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
        def counts=c.count(){ filter }
        logger.info "total goods $counts"
        def rs=Goods.createCriteria().list{
            filter
            maxResults(params.max ? params.max as int : 10)
            firstResult( params.offset ? params.offset as int:0)
            order("price","desc")
        }
        [counts:counts,list:rs,params:params]
    }


    def check={
        def id=params.id?:params.goods?.id
        def goods=Goods.get(id)
        //验证淘宝商品是否还在售中
        def rs=tbService.get (goods?.fId, session.sessionKey)
        //是否在售中
        if(!rs.item?.approveStatus){
            flash.message = "商品不在售中"
            return
        }
        Shelf sf=new Shelf(goods:goods);
        sf.properties = params
        //get方式请求
        if(params.id){
            return [sf:sf]
        }
        if(!sf.validate()){
            sf.errors.each {
                logger.info it.toString()
            }
        }

        return [sf:sf]
    }
}
