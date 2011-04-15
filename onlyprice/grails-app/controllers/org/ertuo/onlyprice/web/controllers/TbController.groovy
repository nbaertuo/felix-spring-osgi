/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2011 All Rights Reserved.
 */
package org.ertuo.onlyprice.web.controllers

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
    
    

    def index={
        def openVar=grailsApplication.config.openVar
        def appKeyUrl="$openVar.tbContainer?appkey=$openVar.tbkey"
        logger.info "appkey地址:$appKeyUrl"
        redirect(url:appKeyUrl)
        
    }

    def callback={
    }
}
