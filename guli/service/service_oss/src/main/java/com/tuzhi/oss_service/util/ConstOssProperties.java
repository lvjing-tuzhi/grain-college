package com.tuzhi.oss_service.util;

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
public class ConstOssProperties implements InitializingBean {

    @Value("${aliyun.oss.file.endpoint}")
    private String endpoint;
    @Value("${aliyun.oss.file.accessKeyId}")
    private String accessKeyId;
    @Value("${aliyun.oss.file.accessKeySecret}")
    private String accessKeySecret;
    @Value("${aliyun.oss.file.bucketname}")
    private String bucketname;
    
//    常量配置属性
    public static String ENDPOINT;
    public static String ACCESSKEYID;
    public static String ACCESSKEYSCRET;
    public static String BUCKETNAME;

    @Override
    public void afterPropertiesSet() throws Exception {
        ENDPOINT = endpoint;
        ACCESSKEYID = accessKeyId;
        ACCESSKEYSCRET = accessKeySecret;
        BUCKETNAME = bucketname;
    }
}
