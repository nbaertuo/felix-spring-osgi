package org.ertuo.onlyprice.utils

import java.security.MessageDigest
import java.security.NoSuchAlgorithmException
import java.util.prefs.Base64

import org.apache.commons.lang.StringUtils

import sun.misc.BASE64Encoder

/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2011 All Rights Reserved.
 */
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException

import org.apache.commons.codec.binary.Base64
import org.apache.commons.lang.StringUtils
import org.slf4j.Logger
import org.slf4j.LoggerFactory

import sun.misc.BASE64Encoder
/**
 * 
 * @author mo.duanm
 * @version $Id: TopUtils.groovy, v 0.1 2011-4-19 下午02:01:43 mo.duanm Exp $
 */
class TopUtils {

    def static Logger log=LoggerFactory.getLogger(TopUtils.class)
    /**
     * 把经过BASE64编码的字符串转换为Map对象
     * @param str
     * @return
     * @throws Exception
     */
    private static Map<String, String> convertBase64StringtoMap(String str) {
        if (!str)
            return null;
        String keyvalues = null;
        try {
            keyvalues = new String(Base64.decodeBase64(URLDecoder.decode(str, "utf-8").getBytes("utf-8")));
        } catch (UnsupportedEncodingException e) {
            log.error "解码[$str]错误",e
            return null
        }

        def result = keyvalues.split('&').inject([:]) { map, token ->
            token.split('=').with { map[it[0]] = it[1] }
            map
        }


        /* String[] keyvalueArray = keyvalues.split("\\&");
         Map<String, String> map = new HashMap<String, String>();
         for (String keyvalue : keyvalueArray) {
         String[] s = StringUtils.split("\\=");
         if (s == null || s.length != 2)
         return null;
         map.put(s[0], s[1]);
         }*/
        return result;
    }

    /**
     * 验证TOP回调地址的签名是否合法。要求所有参数均为已URL反编码的。
     * @param topParams TOP私有参数（未经BASE64解密）
     * @param topSession TOP私有会话码
     * @param topSign TOP回调签名
     * @param appKey 应用公钥
     * @param appSecret 应用密钥
     * @param appSecret 应用密钥
     * @return 验证成功返回true，否则返回false
     * @throws NoSuchAlgorithmException
     * @throws IOException
     */
    public static boolean verifyTopResponse(String topParams, String topSession, String topSign,
    String appKey, String appSecret) throws NoSuchAlgorithmException, IOException {
        StringBuilder result = new StringBuilder();
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        result.append(appKey).append(topParams).append(topSession).append(appSecret);
        byte[] bytes = md5.digest(result.toString().getBytes("UTF-8"));
        BASE64Encoder encoder = new BASE64Encoder();
        return encoder.encode(bytes).equals(topSign);
    }

    /**
     * 
     * @param top_parameters
     * @return module_id    模块ID（全局唯一）  字符串 
     Y  
     只有在license认证时没有本参数，其他的都有
     ts  当前时间戳   整数  
     Y  
     时间戳，插件需要对该时间戳进行验证
     visitor_id  当前用户ID  字符串 
     N  
     即uid，用户不登录则不传
     visitor_nick    当前用户昵称  字符串 
     N  
     用户不登录则不传
     visitor_role    当前用户角色  编码串 
     N  
     用户在当前站点的角色，取值：
     1-站长
     2-站点管理员
     3-站点会员
     4-淘宝用户
     5-未登录用户
     iframe  应用输出是否在IFRAME中  编码串 
     Y  
     取值说明：
     0-和TOP页面集成在一起
     1-输出到IFRAME中
     def ParametersName(String top_parameters){
     return convertBase64StringtoMap(top_parameters);
     }*/
}