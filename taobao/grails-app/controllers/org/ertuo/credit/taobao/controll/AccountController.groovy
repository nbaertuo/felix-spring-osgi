package org.ertuo.credit.taobao.controll

import java.util.logging.Logger;

import javax.persistence.EntityManager 
import javax.persistence.EntityManagerFactory;
import org.apache.commons.lang.StringUtils 
import org.ertuo.credit.taobao.domain.CreditAccount 
import com.google.appengine.api.datastore.Blob 
import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

class AccountController {
	
	private static final Logger logger = Logger.getLogger(AccountController.class.getName())
	
	EntityManagerFactory entityManagerFactory
	
	static defaultAction = "list"
	
	final UserService userService = UserServiceFactory.getUserService();
	
	
	def list={
		User user = userService.getCurrentUser();
		EntityManager em=entityManagerFactory.createEntityManager()
		def accounts=em.createQuery("select h from CreditAccount h").getResultList()
		return [accounts:accounts,user:user]
	}
	
	def edit={
		CreditAccount creditAccount=new CreditAccount()
		bindData(creditAccount,params)
		
		
		EntityManager em=entityManagerFactory.createEntityManager()

		//持久化保存
		def persist={
			try {
				em.persist(it)
			} finally {
				em.close()
			}
		}
		//更新
		if(creditAccount.accountId!=null){
			persist(creditAccount)
			redirect(action:"list")
			return [creditAccount:creditAccount]
		}
		
		if(StringUtils.isNotBlank(params.id)){
			logger.info(params.id)
			creditAccount=(CreditAccount)em.find(CreditAccount.class,Long.parseLong(params.id))
			return [creditAccount:creditAccount]
		}
		//新增保存
		creditAccount.setCreateTime(new Date())
		if(creditAccount.validate()) {
			persist(creditAccount)
			redirect(action:"list")
		}else{
			 creditAccount.errors.each {
			        logger.info(it.toString())
			   }

		}
		return [creditAccount:creditAccount]
		
	}

	def fastAdd={
		CreditAccount creditAccount=new CreditAccount()
		logger.info ("快速添加"+params.email)
		if(StringUtils.isNotBlank(params.email)){
			creditAccount.email=params.email+"@breakcn.com"
			creditAccount.taobaoPW="keyiDax1e"
			creditAccount.taobaoUS=params.email+"breakcn"
		}
		
		EntityManager em=entityManagerFactory.createEntityManager()

		//持久化保存
		def persist={
			try {
				em.persist(it)
			} finally {
				em.close()
			}
		}
		persist(creditAccount)
		redirect(action:"list")
	}

	def del={
			EntityManager em=entityManagerFactory.createEntityManager()
			if(StringUtils.isNotBlank(params.id)){
				logger.info(params.id)
				CreditAccount creditAccount=(CreditAccount)em.find(CreditAccount.class,Long.parseLong(params.id))
				em.remove(creditAccount)
				em.close()
			}
			redirect(action:"list")
	}
	def view={
			CreditAccount creditAccount=new CreditAccount()
			EntityManager em=entityManagerFactory.createEntityManager()
			if(StringUtils.isNotBlank(params.id)){
				logger.info(params.id)
				creditAccount=(CreditAccount)em.find(CreditAccount.class,Long.parseLong(params.id))
				creditAccount?.properties.each{
					logger.info ("huo de de creditAccount="+it)
				}
			}
			chain(action:list,model:[creditAccount:creditAccount])
	}
	
}
