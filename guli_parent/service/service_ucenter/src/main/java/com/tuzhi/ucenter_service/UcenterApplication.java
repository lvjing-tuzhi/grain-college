package com.tuzhi.ucenter_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @program: guli_parent
 * @description:启动类
 * @author: 兔子
 * @create: 2022-04-23 13:48
 **/

@SpringBootApplication
//可以扫描到导入的模块的同包配置
@ComponentScan(basePackages = "com.tuzhi")
public class UcenterApplication {
    public static void main(String[] args) {
        SpringApplication.run(UcenterApplication.class, args);
    }
}
