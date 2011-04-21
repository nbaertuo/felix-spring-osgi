/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2011 All Rights Reserved.
 */
package org.ertuo.onlyprice.web.controllers

import org.ertuo.onlyprice.biz.TbService
import org.ertuo.onlyprice.domain.Shelf
import org.slf4j.Logger
import org.slf4j.LoggerFactory

/**
 * 竞价
 * @author mo.duanm
 * @version $Id: TbController.groovy, v 0.1 2011-4-15 下午04:13:08 mo.duanm Exp $
 */
class ShelfController {

    def Logger logger=LoggerFactory.getLogger(ShelfController.class)

    static defaultAction="list"

    def list={
        return [shelfs:Shelf.list()]
    }
}
