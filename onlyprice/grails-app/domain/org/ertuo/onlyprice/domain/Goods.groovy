/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2011 All Rights Reserved.
 */
package org.ertuo.onlyprice.domain

import java.util.Date;

/**
 * 商品
 * @author mo.duanm
 * @version $Id: Goods.groovy, v 0.1 2011-4-14 下午06:38:34 mo.duanm Exp $
 */
class Goods {

    /**  来源id*/
    String fId

    /**  卖家*/
    User seller

    /**  单价*/
    Double price

    /**  商品标题*/
    String title

    /**  商品描述*/
    String descs

    /** 图片 */
    String picUrl;



    /**  状态（申请=0，发布中=1）*/
    int status

    /** 创建时间 */
    Date gmtCreate

    /** 修改时间 */
    Date gmtModify=new Date()


    static mapping={
        table "goods"
        version false
        //id generator:'increment', column:'id'
        seller column:'seller_id'
        id generator:'assigned', name:'fId'
        //fId unique: true
    }

    static constraints = { descs (blank:false,maxSize:120) }
}
