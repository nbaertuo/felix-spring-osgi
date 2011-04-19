/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2011 All Rights Reserved.
 */
package org.ertuo.onlyprice.web.controllers

import org.ertuo.onlyprice.biz.TbService
import org.ertuo.onlyprice.domain.User
import org.ertuo.onlyprice.utils.TopUtils
import org.slf4j.Logger
import org.slf4j.LoggerFactory

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
            def topMap=TopUtils.convertBase64StringtoMap(params.top_parameters)
            User u=new User()
            u.sessionKey=params.top_session
            u.us=topMap.visitor_nick
            u.fromUid=topMap.visitor_id
            u.gmtCreate=new Date()
            u.nick=topMap.visitor_nick
            u.ps="123456"
            u.type="0"
            u.userfrom="tb"
            u.email="default@xxx.com"
            session.user=u
            if(User.get(u.nick)){
                logger.info "用户$u.nick 已经存在"
            }else{
                if( !u.save() ) {
                    u.errors.each { println it }
                }
                logger.info "添加用户$u.nick"
            }
        }
    }

    def get={
        def q=params.q
        if(!q||q.size()<4){
            render(view:"callback")
        }
        def rs=tbService.onSaleGet(q, session.user.sessionKey)
        render(view:"callback",model:[items:rs?.items,q:q])
    }
}
