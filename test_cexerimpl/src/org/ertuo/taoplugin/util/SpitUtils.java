package org.ertuo.taoplugin.util;

import java.util.List;
import java.util.logging.Logger;

import org.apache.commons.lang.StringUtils;

/**
 * 拆分工具类
 * @author mo.duanm
 *
 */
public class SpitUtils {
    private static final Logger log = Logger.getLogger(SpitUtils.class.getName());

    /**
     * 将指定字符串拆分成指定长度的字符串
     * @param pnum
     * @param arrayLength
     * @param arrs
     * @return
     */
    /*static void  splitLengthStr(String pnum,int arrayLength,Map<String,String> arrs){
    	if(StringUtils.isBlank(pnum)||pnum.length()<arrayLength){
    		return ;
    	}
    	def nest={str->
        if(str.length()==arrayLength){
            // log.info "添加"+str
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
    }*/

    /**
     * 计算指定字符串拆分成指定长度并且重复的字符串
     * @param pnum 18602702748
     * @param arrayLength 3
     * @param arrs <3,3> 
     */
    public static void computeDuplicateVar(String pnum, int arrayLength, List<String> arrs) {

        int s1 = pnum.length() - arrayLength * 1 + 1;
        int s2 = pnum.length() - arrayLength * 2 + 1;
        for (int i = 0; i < s2; i++) { // pnum.length-3-3+1
            // bb = spnum.slice(i,i+ll);
            String bb = pnum.substring(i, i + arrayLength);
            //in i+arrayLength..<s1
            for (int j = i + arrayLength; j < s1; j++) { // pnum.length-3+1
                String cc = (pnum.substring(j, j + arrayLength));
                //log.info("当前分析的是" + bb + "和" + cc);
                if (StringUtils.equals(bb, cc)) {
                    //log.info("找到相同字符" + cc);
                    arrs.add(bb);
                    continue;
                }
            }
        }
    }

    /**
     * 计算X秒能处理的10的倍数个数
     * @return
     */
    /*static int genTenMaxResults(int min,String pnum,int arrayLength){
    	def d1=System.currentTimeMillis()//new Date()
    	def map =[]
    	(0..10).each{
    		computeDuplicateVar(pnum, arrayLength, map)
    	}
    	
    	def d2=System.currentTimeMillis()//new Date()
    	def withMin=(d2-d1)/1000//(d2.getTime()-d1.getTime())/1000
    	def rs=Math.floor((min*10)/withMin)
    	log.info ("analyse $pnum use $withMin min. in $min min, can process $rs ")
    	return rs;
    }*/

    /**
     * 在指定字符串中查找指定字符串出现的次数
     * @param str
     * @param reg
     * @return
     */
    /*private static List<String> nestSpitStr(String str,String reg){
    	def rs=[]
    	Pattern p = Pattern.compile(reg);
    	Matcher m = p.matcher(str);
    	while(m.find()){
    		rs.add m.group()
    	}
    	return rs;
    }*/
}
