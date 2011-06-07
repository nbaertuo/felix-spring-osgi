/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2011 All Rights Reserved.
 */
package org.ertuo.onlyprice.web.controllers

import grails.converters.JSON

import org.ertuo.onlyprice.biz.TbService
import org.ertuo.onlyprice.domain.Goods
import org.ertuo.onlyprice.web.domain.WebAjaxRs;
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
        //页面返回结果
        def rs=[:]
        def id=params.fId
        if(!id){
            rs=["su":false, "msg":"请选择商品","id":g.fId]
        }
        def item= tbService.get (id, session.user.sessionKey)?.item

        if(item){
            Goods g=new Goods(fId:item.numIid,price:item.price as Double,title:item.title,seller:session.user,gmtCreate:new Date(),picUrl:item.picUrl,status:0)
            logger.info "描述 $params.descs"
            g.descs = params.descs
            if(!g.validate()){
                g.errors.each { logger.info it.toString() }
                rs=["su":false, "msg":g.errors,"id":g.fId]
            }
            if(!g.save()){
                g.errors.each { logger.info  it.toString() }
                rs=["su":false, "msg":g.errors,"id":g.fId]
            }else{
                logger.info "goods  $g.title save success"
                rs=["su":true, "id":g.fId,"title":g.title]
            }
        }else{
            rs=["su":false, "msg":"商品不存在","id":g.fId]
        }
        render rs as JSON
    }
}
