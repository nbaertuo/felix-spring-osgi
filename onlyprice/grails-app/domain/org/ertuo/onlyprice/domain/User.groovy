/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2011 All Rights Reserved.
 */
package org.ertuo.onlyprice.domain

import java.util.Date;

import javax.persistence.Id;

/**
 * 用户
 * @author mo.duanm
 * @version $Id: User.groovy, v 0.1 2011-4-14 下午05:17:37 mo.duanm Exp $
 */
class User {

    /** 密码 */
    String ps

    /** 用户名 */
    String us

    /** 类型（个人, 商家, 管理员） */
    String type

    /**  */
    String email

    /**  来源*/
    String userfrom

    /**  昵称*/
    String nick

    /**  来源方id*/
    String fromUid


    /** 创建时间 */
    Date gmtCreate;

    /** 修改时间 */
    Date gmtModify=new Date()

    def sessionKey

    def uId

    static mapping={
        table "User"
        id generator:'assigned', name:'us'
        version false
    }
}
