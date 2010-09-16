package org.ertuo.credit.taobao.domain

import javax.persistence.Entity;
import javax.persistence.GeneratedValue 
import javax.persistence.GenerationType 
import javax.persistence.Id 
import javax.persistence.OneToMany 


@Entity
class PhoneNum {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	Long phoneId
	
	String num
	
	String prise
	
	String date
	
	String city
	
	@OneToMany(mappedBy = "phoneNum")
	//cascade = CascadeType.ALL
	List<SellNum>	 sellNums
	
}
