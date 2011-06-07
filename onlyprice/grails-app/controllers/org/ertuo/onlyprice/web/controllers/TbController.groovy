/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2011 All Rights Reserved.
 */
package org.ertuo.onlyprice.web.controllers

import org.ertuo.onlyprice.biz.TbService
import org.ertuo.onlyprice.biz.UserService;
import org.ertuo.onlyprice.domain.User
import org.ertuo.onlyprice.utils.TopUtils
import org.slf4j.Logger
import org.slf4j.LoggerFactory

/**
 * 淘宝
 * @author mo.duanm
 * @version $Id: TbController.groovy, v 0.1 2011-4-15 下午04:13:08 mo.duanm Exp $
 */
class TbController {

    def Logger logger=LoggerFactory.getLogger(TbController.class)

    static defaultAction="index"

    TbService tbService

    UserService userService
    def index={
        def openVar=grailsApplication.config.openVar
        def appKeyUrl="$openVar.tbContainer?appkey=$openVar.tbkey"
        logger.info "appkey地址:$appKeyUrl"
        redirect(url:appKeyUrl)
    }

    def callback={
        if(params.top_session){
            def topMap=TopUtils.convertBase64StringtoMap(params.top_parameters)

            User u=userService.saveTbUser (topMap.visitor_nick, topMap.visitor_id)
            u?.sessionKey=params.top_session
            session.user=u
        }
    }

    def get={
        def q=params.q
        if(!q||q.size()<4){
            flash.message = "输入长度不能小于4"
            render(view:"callback")
        }
        def rs=tbService.onSaleGet(q, session.user.sessionKey)
        render(view:"callback",model:[items:rs?.items,q:q])
    }
}
