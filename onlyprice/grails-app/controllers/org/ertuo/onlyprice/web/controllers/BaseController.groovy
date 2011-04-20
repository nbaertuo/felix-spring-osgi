/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2011 All Rights Reserved.
 */
package org.ertuo.onlyprice.web.controllers;

/**
 * 基础控制器.
 * 
 * @author yang.ly
 * @version $Id: BaseController.java, v 0.1 2011-4-20 下午08:02:51 yang.ly Exp $
 */
public class BaseController {
    
    /**
     * 登陆认证.
     */
    def loginAuth() {
        if(!session.userId) {
            def originalRequestParams = [controller:controllerName, action:actionName]
            originalRequestParams.putAll(params)
            session.originalRequestParams = originalRequestParams
            
            // 跳转到登陆页面
            redirect(controller:'user',action:'login')
            return false
        }
    }

}
