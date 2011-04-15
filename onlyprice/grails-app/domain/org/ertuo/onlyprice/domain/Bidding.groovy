/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2011 All Rights Reserved.
 */
package org.ertuo.onlyprice.domain

/**
 * 竞价
 * @author mo.duanm
 * @version $Id: Bidding.groovy, v 0.1 2011-4-14 下午06:42:09 mo.duanm Exp $
 */
class Bidding {

    /** 购买者 */
    User purchaser;

    /**商品  */
    Goods goods;

    /** 出价 */
    Long price;

    static mapping={
        table "Bidding"
        version false
        purchaser column:'purchaser_id'
        goods column:'goods_id'
    }
}
