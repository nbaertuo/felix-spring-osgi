/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2011 All Rights Reserved.
 */
package org.ertuo.onlyprice.domain

import java.io.Serializable;
import java.util.Date;


/**
 * 竞价次数
 * @author mo.duanm
 * @version $Id: Bidding.groovy, v 0.1 2011-4-14 下午06:42:09 mo.duanm Exp $
 */
class BiddingTimes implements Serializable{

    /** 购买者 */
    String purchaser_id;

    /**商品  */
    String goods_id;

    /** 出价次数 */
    Integer bidTimes;

    /** 最多的出价次数 */
    Integer limitTimes;

    /** 创建时间 */
    Date gmtCreate;

    /** 修改时间 */
    Date gmtModify=new Date()

    static mapping={
        table "BiddingTimes"
        version false
        id composite:['purchaser_id', 'goods_id']
    }
}
