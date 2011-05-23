/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2011 All Rights Reserved.
 */
package org.ertuo.onlyprice.biz
import java.util.Calendar;

import org.apache.commons.lang.time.DateUtils;
import org.ertuo.onlyprice.domain.Shelf
import org.hibernate.LockMode
import org.slf4j.Logger
import org.slf4j.LoggerFactory

/**
 * 
 * 货架服务
 * @author mo.duanm
 * @version $Id: TbService.groovy, v 0.1 2011-4-15 下午05:45:39 mo.duanm Exp $
 */
class ShelfService {

    def Logger logger=LoggerFactory.getLogger(ShelfService.class)


    /**  获取当前货架中最晚下架的时间,返回空时，说明数据库没有数据*/
    def getLastEndTime={ getLastTimeOrLock(false) }

    def getLastTimeOrLock={ isLock->

        def c = Shelf.createCriteria()

        def maxTime = c.get() {
            projections { max("offTime") }
        }
        c.lock isLock
        logger.info "数据库最近的下架时间:$maxTime"
        if(!maxTime){
            maxTime=DateUtils.truncate(new Date(), Calendar.HOUR)
        }
        logger.info "最近的下架时间:$maxTime"
        return maxTime
    }

    /**
     * 保存
     * @param shelf
     * @return
     */
    def save(shelf){
        Date maxTime=getLastTimeOrLock(true)
        if(maxTime.getTime()==shelf.onTime.getTime()){
            if(!shelf.save()){
                shelf.errors.each { logger.info "保存错误:$it.toString()" }
                return false
            }else{
                return true
            }
        }
    }
}
