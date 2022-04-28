package com.tuzhi.edu_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @program: guli_parent
 * @description:
 * @author: 兔子
 * @create: 2022-03-07 08:35
 **/

@SpringBootApplication
//可以扫描到导入的模块的同包配置
@ComponentScan(basePackages = "com.tuzhi")
@EnableDiscoveryClient //nacos服务发现注册
@EnableFeignClients //调用nacos注册的微服务
//@EnableHystrix
public class EduApplication {
    public static void main(String[] args) {
        SpringApplication.run(EduApplication.class, args);
    }
}
