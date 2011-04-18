/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2011 All Rights Reserved.
 */
package org.ertuo.onlyprice.web.controllers

import org.ertuo.onlyprice.biz.TbService;
import org.slf4j.Logger
import org.slf4j.LoggerFactory

import com.taobao.api.TaobaoClient;

/**
 * 
 * @author mo.duanm
 * @version $Id: TbController.groovy, v 0.1 2011-4-15 下午04:13:08 mo.duanm Exp $
 */
class TbController {

    def Logger logger=LoggerFactory.getLogger(TbController.class)

    static defaultAction="index"

    TbService tbService


    def index={
        def openVar=grailsApplication.config.openVar
        def appKeyUrl="$openVar.tbContainer?appkey=$openVar.tbkey"
        logger.info "appkey地址:$appKeyUrl"
        redirect(url:appKeyUrl)
    }

    def callback={
        if(params.top_session){
            session.top_session=params.top_session
            session.top_sign=params.top_sign
        }
    }

    def get={
        def q=params.q
        if(!q||q.size()<4){
            render(view:"callback")
        }
        def rs=tbService.onSaleGet(q, session.top_session)
        render(view:"callback",model:[items:rs?.items,q:q])
    }
}
