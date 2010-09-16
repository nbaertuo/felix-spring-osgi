package org.ertuo.credit.taobao.domain

import javax.persistence.CascadeType 
import javax.persistence.Entity;
import javax.persistence.FetchType 
import javax.persistence.GeneratedValue 
import javax.persistence.GenerationType 
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;


@Entity
class SellNum {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	Long sellId
	
	@ManyToOne
	PhoneNum phoneNum
	
	String viewNum
	
	String prise
	
	String date
	
	
	public String getDisplay(){
		phoneNum?.num?.replaceAll(viewNum, "<font color='red'>"+viewNum+"</font>")
	}

}
