

/**
 * @author mo.duanm
 *
 */
package org.ertuo.number.codex.engine.impl

import java.util.ArrayList;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.ertuo.number.codex.engine.LtCodexEngine;

import sun.beans.editors.IntEditor;

class AABBcodex implements LtCodexEngine{
	
	
	private Logger log=Logger.getLogger(AABBcodex.class);
	
	private String pnum;
	
	private int[] pnumints;
	
	/**
	 * 构造函数
	 * @param pnum 手机号
	 */
	public AABBcodex(String pnum){
		this.pnum=pnum;
		pnumints=this.split2int(pnum)
	}
	
	/**
	 * @see org.ertuo.number.codex.engine.LtCodexEngine#filterNum(java.lang.String)
	 */
	String filterNum(String phoneNumber){
	}
	
	/**
	 * 返回11位长度的数组
	 * @param phoneNumber
	 * @return
	 */
	private int[] split2int(String phoneNumber){
		def ints=new int[phoneNumber.length()]
		for(i in 0..<phoneNumber.length()) {
			ints[i]=Integer.parseInt(""+phoneNumber.charAt(i))
		}
		return ints;
	}
	
	/**
	 * 将指定字符串拆分成指定长度的字符串
	 * @param pnum
	 * @param arrayLength
	 * @param arrs
	 * @return
	 */
	Map<String,String> splitLengthStr(String pnum,int arrayLength,Map<String,String> arrs){
		if(StringUtils.isBlank(pnum)||pnum.length()<arrayLength){
			return null;
		}
		def nest={str->
			if(str.length()==arrayLength){
				log.info "添加"+str
				arrs.put str, str
			}else{
				this.splitLengthStr str, arrayLength, arrs
			}
		}
		for(i in 1..<pnum.length()){
			String a=pnum.substring (0,i)
			nest(a)
			String b=pnum.substring (i)
			nest(b)
		}
		return arrs
	}
	
	List split2arr(String pnum,int arrayLength,List arrs){
		//1.从算出位组合中取几个余数
		def remain=pnumints.length-(pnumints.length/arrayLength)*arrayLength
		
		//1.1算出11位数字钟取两个数字的
			def map=this.get2Num (11,2)
		//2.剩余数字安装参数长度来组合
			map.each { 
				def nums=it.value
				def x=it
				
				}
		return null
	} 
	
	private Map<String,int[]> get2Num(int length,int cycTimes){
		def arr=new int[cycTimes]
		for(x in 0..<cycTimes){
			for(y in 0..length){
				//arr[x]=y
				log.info (x +" " +y)
			}
		}
		
		def map=[:]
		/*for(x in 0..length){
			for(y in 0..length){
				if(x==y){
					continue;
				}
				def nums=[x,y]
				map.put (String.valueOf(x)+String.valueOf(y), nums)
			}
		}*/
		return map;
		
	}
	
	/**
	 * 拆分成指定长度的多维数组，拆分后的数组必须两个两个相连
	 * 12345678901按照3位来拆分
	 * 123 456 789 01
	 * 1 234 567 890 1
	 * 1 234 567 8 901
	 * 12 345 678 901
	 * 123 45 678 901
	 * @param arrayLength 指定的长度
	 * @return
	 */
	List<String[]> split2array(int arrayLength){
		
		//因为需要两个数组相连，先将数组里字符长度*2
		int length=arrayLength*2
		//定义多维数组
		def rs=new ArrayList<String[]>()
		//从*2后的位置开始分组
		for(i in length..pnumints.length){
			// *2位连续的拆分，开始一刀砍
			//取得第一刀的位置
			def k=i-length
			//算成第一个相连数组的值
			def joinArray=pnum.substring(k, length+k) 
			//将第一个数组拆分成2个数组
			def firstAr=joinArray.substring(0,arrayLength)
			//log.debug("第一个数组:"+firstAr)
			def secondAr=joinArray.substring(arrayLength)
			//log.debug("第二个数组:"+secondAr)
			
			def add={nestNum,kaishi->
				def others=[]
				def beginStr=firstAr+","+secondAr
				this.nestAry(kaishi,arrayLength,nestNum , others)
				others.each {
					//beginStr=beginStr+","+it
					if(it instanceof List){
						def allAry=new String()[2+it.size()]
						for( h in 0..it.size()) {
							allAry[h+2]=it
						}
						rs.add allAry
					}else{
						def allAry=[firstAr, secondAr, it]as String[]
						rs.add allAry
					}
				}
				//def allAry=beginStr.split(",")
				
			}
			
			def after=pnumints.length-i-arrayLength;
			def before=i-length-arrayLength
			//寻找其余的组合
			//往后找
			if(after>=0){
				def nestNum=pnum.substring(i)
				add(nestNum,after)
			}
			//往前找
			if(before>=0){
				def nestNum=pnum.substring(0,i-length)
				add(nestNum,before)
			}
			//前后都不能取得规定长度组合的情况
			//前后都不符合
			if(before<0&&after<0){
				def inner=[firstAr, secondAr]as String[]
				rs.add(inner)
			}
		}
		
		return rs;
	}
	
	private void nestAry(int before,int arrayLength,String pnum,List others){
		if(pnum.length()<arrayLength){
			return;
		}
		for( g in 0..before){
			try {
				def other=pnum.substring(g, g+arrayLength)
				others.add other
				int bb=pnum.length()-g-arrayLength*2
				if(bb>0){
					def newpnum=pnum.substring((g+arrayLength))
					def otherInother=[]
					nestAry (bb, arrayLength, newpnum, otherInother)
					others.add otherInother
				}
			} catch (Exception e) {
				log.error("在"+g+"查询"+pnum,e)
			}
		}
	}
	
	
	/**
	 * 对数组号码进行处理
	 * @param numPhone
	 */
	private void process(int[] numPhone){
	}
}
