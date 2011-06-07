/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2011 All Rights Reserved.
 */
package org.ertuo.onlyprice.web.controllers

import org.apache.commons.lang.time.DateUtils;
import org.ertuo.onlyprice.biz.ShelfService;
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

    ShelfService shelfService

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
        //TODO 数据权限控制 商户只能查询商户自己的商品 管理员可以查询所有商品

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


    /**  只传递id*/
    def get={
        def sf=this.getSf(params)
        if(!sf){
            flash.message = "请填写商品id"
            render(view:"search")
        }
        render(view:"check",model:[sf:sf])
    }

    /**  修改*/
    def mod={
        def sf=shelfService.getSf(params.fId,params.owner)
        if(!sf){
            flash.message = "无法查询到对应的商品，请重新选择!"
            forward(action:"list")
        }
        render(view:"check",model:[sf:sf])
    }


    /**  post保存*/
    def check={
        def sf=this.getSf(params)
        if(!sf.validate()){
            sf.errors.each {
                logger.info it.toString()
            }
            return [sf:sf]
        }

        if(shelfService.save(sf)){
            flash.message = "发布成功"
            forward(action:"list")
        }
        return [sf:sf]
    }

    /** 返回null的话 说明id不存在*/
    private Shelf getSf(params){
        def id=params.fId?:params.goods?.fId
        logger.info "id= $id "
        if(!id){
            return null;
        }
        def goods=Goods.findByFId(id)
        Shelf sf=new Shelf(goods:goods,gmtCreate:new Date());
        sf.properties = params
        //数据权限控制 ，当前操作用户作为货架的拥有者
        sf.owner=session.user.us
        return sf
    }

    def list={

        def list= shelfService.ListAfterToday(session.user.us)
        logger.info "web size=$list.size"
        return [list:list]
    }
}
