package org.ertuo.credit.taobao.lt.codex

import java.util.logging.Logger 
import javax.persistence.EntityManager 
import javax.persistence.EntityManagerFactory 

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils 
import org.apache.commons.lang.builder.ToStringBuilder 
import org.apache.commons.lang.builder.ToStringStyle 
import org.ertuo.credit.taobao.domain.CronProcess 
import org.ertuo.credit.taobao.domain.PhoneNum 
import org.ertuo.credit.taobao.domain.SellNum 
import org.ertuo.credit.taobao.lt.utils.SpitUtils 
import org.springframework.scheduling.annotation.Async 
import org.springframework.stereotype.Component 



@Component
class XyzCodexCronService {
	
	private static final Logger logger = Logger.getLogger(XyzCodexCronService.class.getName())
	
	
	EntityManagerFactory entityManagerFactory
	
	XyzCodexService xyzCodexService 
	
	static transactional = false
	
	@Async
	void asynCron(){
		EntityManager em=entityManagerFactory.createEntityManager()
		def phoneNums
		def maxs=em.createQuery("select b from CronProcess  b where codex='xyz' ").getSingleResult()
		//CronProcess.findWhere(codex:"xyz")
		//15是定时任务60的1/4
		def maxResults=SpitUtils.genMaxResults(15, "18602702748", 3)
		
		
		if(maxs==null){
			phoneNums=em.createQuery("select b from PhoneNum  b  order by b.phoneId ").setMaxResults(maxResults).getResultList()
		}else{
			phoneNums = em.createQuery("select b from PhoneNum  b where b.phoneId > $maxs.phoneId  order by b.phoneId").setMaxResults(maxResults).getResultList()
		}
		
		def noLazy=[]
		 phoneNums.each{
		 def noLazyBean=new PhoneNum()
		 noLazyBean.city=it.city
		 noLazyBean.date=it.date
		 noLazyBean.num=it.num
		 noLazyBean.phoneId=it.phoneId
		 noLazyBean.prise=it.prise
		 noLazy.add noLazyBean
		 }
		 em.close()
		 
			noLazy.each {
				String rs=xyzCodexService.process(it.num)
				if(StringUtils.isNotBlank(rs)){
					//def sell=em.createQuery("select b from SellNum  b where b.phoneNum.phoneId:phoneId and b.viewNum:viewNum").setParameter ("phoneId", it.phoneId).setParameter ("viewNum",rs).getSingleResult()
					 def sell=SellNum.findWhere(phoneNum:it,viewNum:rs)
					if(sell==null){
						SellNum.withTransaction{ status ->
							def phoneNum=PhoneNum.get(it.phoneId)
							SellNum sn=new SellNum()
							sn.setPhoneNum(phoneNum)
							sn.setViewNum(rs)
							logger.info("save sellNum $sn.viewNum ")
							if(!sn.save()){
								sn.errors.each { logger.info(it) }
								status.setRollbackOnly()

							 }
						}
						// flush:true
						// if(!sn.save()){
						  //sn.errors.each { logger.info(it) }
						  //}
						//em.persist sn
					 }else{
						 logger.info "数据库已经存在:"+ToStringBuilder.reflectionToString(sell,ToStringStyle.MULTI_LINE_STYLE)
					 }
						
						
							
				}
				//更新最大id
				if(maxs==null){
					maxs=new CronProcess()
				}
				if(it.phoneId>maxs.phoneId){
					
					
					/*if(!maxs.save()){
					 maxs.errors.each { logger.info(it) }
					 }*/
					//em.persist maxs
					CronProcess.withTransaction{ status ->
						logger.info("update CronProcess id $maxs.phoneId to $it.phoneId")
						def saveob=new CronProcess()
						saveob.phoneId=it.phoneId
						saveob.codex="xyz"
						if(!saveob.save()){
							saveob.errors.each { logger.info(it) }
							status.setRollbackOnly()

						 }
					}
				}
			}
	}
}
