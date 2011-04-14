/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2011 All Rights Reserved.
 */
package org.ertuo.onlyprice.web.controllers

import org.ertuo.onlyprice.domain.Price
import org.slf4j.Logger
import org.slf4j.LoggerFactory

/**
 * 
 * @author mo.duanm
 * @version $Id: PriceController.groovy, v 0.1 2011-4-14 ����10:46:08 mo.duanm Exp $
 */
class PriceController {
    static defaultAction = "list"

    private static final Logger logger=LoggerFactory.getLogger(PriceController.class)

    def list={

        return[prices:Price.list()]
    }

    def edit={
        Price p=new Price(date:new Date())
        bindData(p,params)
        logger.info p.title
        p.validate()
        if(p.hasErrors()) {
            p.errors.each {
                logger.info  "错误"+it.toString()
            }
            return
        }
        p.save()


        return [p:p]
    }
}
