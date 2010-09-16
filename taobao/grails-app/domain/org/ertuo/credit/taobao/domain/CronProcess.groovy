package org.ertuo.credit.taobao.domain

import javax.persistence.CascadeType 
import javax.persistence.Entity;
import javax.persistence.FetchType 
import javax.persistence.GeneratedValue 
import javax.persistence.GenerationType 
import javax.persistence.Id;
import javax.persistence.ManyToOne;


@Entity
class CronProcess {
	
	/*@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	Long cronId*/
	
	@Id
	String codex
	
	Long phoneId
	

}
