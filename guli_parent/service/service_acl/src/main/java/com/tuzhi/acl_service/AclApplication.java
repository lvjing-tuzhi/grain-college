package com.tuzhi.acl_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

/**
 * @program: guli_parent
 * @description:启动类
 * @author: 兔子
 * @create: 2022-04-27 15:55
 **/

@SpringBootApplication
@EnableDiscoveryClient //nacos
@ComponentScan(basePackages = "com.tuzhi")
public class AclApplication {
    public static void main(String[] args) {
        SpringApplication.run(AclApplication.class, args);
    }
}
