package com.tuzhi.vod_service.util;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @program: guli_parent
 * @description: OSS的常量配置
 * @author: 兔子
 * @create: 2022-03-28 21:45
 **/

@Component
public class ConstVodProperties implements InitializingBean {


    @Value("${aliyun.vod.file.keyId}")
    private String accessKeyId;
    @Value("${aliyun.vod.file.keySecret}")
    private String accessKeySecret;
    
//    常量配置属性
    public static String ACCESSKEYID;
    public static String ACCESSKEYSCRET;

    @Override
    public void afterPropertiesSet() throws Exception {
        ACCESSKEYID = accessKeyId;
        ACCESSKEYSCRET = accessKeySecret;
    }
}
