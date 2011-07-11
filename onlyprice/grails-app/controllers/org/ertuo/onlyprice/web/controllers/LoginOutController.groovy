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
 * 登出服务。包括淘宝登陆，微博登陆
 * @author mo.duanm
 * @version $Id: TbController.groovy, v 0.1 2011-4-15 下午04:13:08 mo.duanm Exp $
 */
class LoginOutController {

    def Logger logger=LoggerFactory.getLogger(LoginOutController.class)

    static defaultAction="out"


    def out={
        logger.info "User agent: " + request.getHeader("User-Agent")
        session.invalidate()
        redirect(uri:"/sec.gsp")
    }
}
