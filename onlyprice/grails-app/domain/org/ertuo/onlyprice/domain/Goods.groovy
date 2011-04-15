/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2011 All Rights Reserved.
 */
package org.ertuo.onlyprice.domain

/**
 * 商品
 * @author mo.duanm
 * @version $Id: Goods.groovy, v 0.1 2011-4-14 下午06:38:34 mo.duanm Exp $
 */
class Goods {

    /**  卖家*/
    User seller

    /**  单价*/
    Long price

    /**  商品标题*/
    String title

    /**  商品描述*/
    String descs

    /**  来源id*/
    String fId

    static mapping={
        table "goods"
        version false
        id generator:'increment', column:'id'
        seller column:'seller_id'
    }
}
