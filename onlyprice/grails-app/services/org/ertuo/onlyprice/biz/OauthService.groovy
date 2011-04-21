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
class OauthService {
    def Logger logger=LoggerFactory.getLogger(OauthService.class)



    def RequestToken request( backUrl) {
        try {
            System.setProperty("weibo4j.oauth.consumerKey", Weibo.CONSUMER_KEY);
            System.setProperty("weibo4j.oauth.consumerSecret",Weibo.CONSUMER_SECRET);
            Weibo weibo=new Weibo()
            RequestToken requestToken = weibo.getOAuthRequestToken(backUrl);

            logger.info("Got request token.");
            logger.info("Request token: " + requestToken.getToken());
            logger.info("Request token secret: "
                    + requestToken.getTokenSecret());
            return requestToken;
        } catch (Exception e) {
            logger.error "获取请求token失败",e
            return null;
        }
    }

    /**
     * 永久token
     * @param token
     * @param tokenSecret
     * @param verifier
     * @return
     */
    def AccessToken requstAccessToken(token,tokenSecret ,verifier) {
        try {
            System.setProperty("weibo4j.oauth.consumerKey", Weibo.CONSUMER_KEY);
            System.setProperty("weibo4j.oauth.consumerSecret",
                    Weibo.CONSUMER_SECRET);
            Weibo weibo=new Weibo()
            AccessToken accessToken = weibo.getOAuthAccessToken(token, tokenSecret, verifier);

            logger.info("Got access token.");
            logger.info("access token: " + accessToken.getToken());
            logger.info("access token secret: "
                    + accessToken.getTokenSecret());
            return accessToken;
        } catch (Exception e) {
            logger.error "获取持久token失败",e
            return null;
        }
    }

    /**
     * 根据用户ID获取用户资料（授权用户）
     * @param args
     * @throws UnsupportedEncodingException
     */
    public   getUser(token,secret,id) throws UnsupportedEncodingException {
        System.setProperty("weibo4j.oauth.consumerKey", Weibo.CONSUMER_KEY);
        System.setProperty("weibo4j.oauth.consumerSecret", Weibo.CONSUMER_SECRET);
        try {
            User user = getWeibo(token,secret).showUser(""+id);
            logger.info(user.toString());
            return user
        } catch (WeiboException e) {
            logger.error "获取用户信息失败",e
            return null
        }
    }

    private  Weibo getWeibo(token,secret) {
        Weibo weibo =new Weibo()
        weibo.setToken(token, secret);
        return weibo;
    }
}
