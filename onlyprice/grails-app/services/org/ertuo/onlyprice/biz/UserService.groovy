/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2011 All Rights Reserved.
 */
package org.ertuo.onlyprice.biz

import java.io.UnsupportedEncodingException;

import org.slf4j.Logger
import org.slf4j.LoggerFactory

import weibo4j.User;
import weibo4j.Weibo
import weibo4j.WeiboException;
import weibo4j.http.AccessToken
import weibo4j.http.RequestToken

/**
 * oauth授权
 * 
 * @author mo.duanm
 * @version $Id: TbService.groovy, v 0.1 2011-4-15 下午05:45:39 mo.duanm Exp $
 */
class UserService {
    def Logger logger=LoggerFactory.getLogger(UserService.class)



    def  saveTbUser(nick,uid) {
        org.ertuo.onlyprice.domain.User u=new  org.ertuo.onlyprice.domain.User()
        u.us=nick
        u.fromUid=uid
        u.gmtCreate=new Date()
        u.nick=nick
        u.ps="123456"
        u.type="0"
        u.userfrom="tb"
        u.email="default@xxx.com"
        save(u)
    }

    def saveWbUser(weibo4j.User u,token,secret){
        org.ertuo.onlyprice.domain.User user=new org.ertuo.onlyprice.domain.User
                (
                nick:u.name,
                us:u.id as String,
                ps:"123456",
                type:"1",
                userfrom:"wb",
                fromUid:u.id as String,
                email:"xxx@xx.com",
                accessToken:token,
                accessSecret:secret,
                gmtCreate:new Date()
                )
        save(user)
    }

    private  save(user){
        if(!user.save()){
            user.errors.each{ logger.error it.toString() }
            return null
        }else{
            return user
        }
    }
}
