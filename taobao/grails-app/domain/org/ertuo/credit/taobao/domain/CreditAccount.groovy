package org.ertuo.credit.taobao.domain

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue 
import javax.persistence.GenerationType 
import javax.persistence.Id;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.Cache;

@Entity
class CreditAccount {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	Long accountId
	
	String email
	
	String taobaoPW
	
	String taobaoUS
	
	String alias
	
	String emailStatus
	
	String taobaoStatus
	
	Date createTime
	
	

    static constraints = {
		email(blank:false,unique:true)
		taobaoUS(blank:false)
		taobaoPW(blank:false)
    }
}
