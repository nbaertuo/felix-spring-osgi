/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2011 All Rights Reserved.
 */
package org.ertuo.onlyprice.biz
import java.util.Calendar

import org.apache.commons.lang.time.DateUtils
import org.ertuo.onlyprice.domain.Goods
import org.ertuo.onlyprice.domain.Shelf
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
            projections { max("onTime") }
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
        def id=shelf.ident()
        //查询数据库中是否已经存在
        def old=getSf(shelf.goods.fId,shelf.owner)

        def hasError=false
        if(old){
            //存在的话更新老的
            def gmtCreate=old.gmtCreate
            old.properties=shelf.properties
            old.gmtCreate=gmtCreate
            hasError=old.save(insert:false)
        }else{
            //插入新的
            hasError=shelf.save(insert:true)
        }
        if(!hasError){
            shelf.errors.each { logger.info "保存错误:$it.toString()" }
            return false
        }else{
            return true
        }
    }

    /** 获得今天的货架列表，按照上架时间排序 */
    def listAfterToday={ owner->
        def c = Shelf.createCriteria()
        def list=c.list{
            //大于当前，小于明天
            def today=DateUtils.truncate(new Date(), java.util.Calendar.AM)
            gt("onTime",today)
            le("onTime",DateUtils.addDays(new Date(),1))
            eq("owner",owner)
            order ("onTime","asc")
            cache true
        }
        return list
    }

    /**  获得当前需要竞价的商品*/
    def getNowShelf={ owner->
        def todayList=listAfterToday(owner)
        def nowShelf
        def index
        todayList.eachWithIndex() { obj, i ->
            if(obj.onTime.before(new Date())){
                nowShelf=obj
                index=i
                logger.info '最近的时间是$i'
            }
        }
    }

    def getSf(fId,owner){
        Shelf.findByGoodsAndOwner(new Goods(fId:fId),owner)
    }
}
