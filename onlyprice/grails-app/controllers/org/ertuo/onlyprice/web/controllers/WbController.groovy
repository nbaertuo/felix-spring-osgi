/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2011 All Rights Reserved.
 */
package org.ertuo.onlyprice.web.controllers

import org.ertuo.onlyprice.biz.OauthService
import org.slf4j.Logger
import org.slf4j.LoggerFactory

import weibo4j.User
import weibo4j.http.AccessToken
import weibo4j.http.RequestToken

/**
 * 微博
 * @author mo.duanm
 * @version $Id: TbController.groovy, v 0.1 2011-4-15 下午04:13:08 mo.duanm Exp $
 */
class WbController {

    def Logger logger=LoggerFactory.getLogger(WbController.class)

    static defaultAction="req"

    OauthService oauthService

    org.ertuo.onlyprice.biz.UserService userService


    def req={
        RequestToken qt=oauthService.request("$grailsApplication.config.grails.serverURL/wb/callback")
        session.tokenSecret=qt.tokenSecret
        redirect(uri:"$qt.authorizationURL")
    }
    def callback={
        def oauth_token=params.oauth_token
        def oauth_verifier=params.oauth_verifier
        AccessToken at= oauthService.requstAccessToken (oauth_token,session.tokenSecret, oauth_verifier)
        logger.info  " screen_name "+at.screenName+"userid  "+at.userId
        User u=oauthService.getUser (at.token,at.tokenSecret, at.userId)
        logger.info u.toString()
        def user=userService.saveWbUser (u, at.token, at.tokenSecret)
        session.user=user
    }
}
