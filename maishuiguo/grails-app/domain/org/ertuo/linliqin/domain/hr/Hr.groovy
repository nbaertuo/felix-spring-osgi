package org.ertuo.linliqin.domain.hr;


import javax.persistence.GenerationType;

import javax.persistence.GeneratedValue;
import javax.persistence.Transient;

import javax.annotation.Generated;

import javax.persistence.Id;

import java.util.Date



import javax.persistence.Entity;

@Entity
public class Hr {

	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	Long hrId
	//编号
	Long hrNo
	//姓名
	String name
	//性别
	String sex
	//年龄
	Date age
	//部门
	String department
	//出生日期
	Date birthplace
	//婚姻状态
	String marriage
	//政治面貌
	String politicalLandscape
	//民族
	String nation
	//身份证
	Long identity
	//学历
	String education
	//学位
	String degree
	//专业
	String professional
	//毕业学校
	String graduationSchool
	//毕业时间
	Date graduationDate
	//职称
	String title
	//职务
	String post
	//岗位
	String job
	//行政级别
	String executiveLevel
	//职称取得时间
	Date titleDate
	//聘任时间
	Date appointmentDate
	//学历年限
	Long educationDate
	//实习期
	Date practiceDate
	//转正时间
	Date regularizationDate
	//入职时间
	Date entryDate
	//离职时间
	Date separationDate
	//参加工作时间
	Date jobDate
	//退休时间
	Date retirementDate
	//用工形式
	String employmentStatus
	//离职原因
	String separationReson
	//人员类别
	String categoriesPersonnel
	//工种
	String jobs
	//联系电话
	String tel
	//岗位工资
	Long postWage
	//薪级
	Long salaryLevel
	//薪级工资
	Long salaryLevelWage
	//岗位补贴
	Long postSubsidy
	//综合补贴
	Long ConsolidatedSubsidies
	//生活补贴
	Long lifeSubsidies
	//教龄补贴
	Long senioritySubsidies
	//电话补贴
	Long phoneSubsidies
	//在岗补贴
	Long onJobSubsidies
	//交通补贴
	Long trafficSubsidies
	
	
	static constraints = {
        name(size:5..15,blank:false,unique:true)
        sex(size:5..15,blank:false)
        age(min:new Date(),nullable:false)
        birthplace(bank:false)
    }


}
