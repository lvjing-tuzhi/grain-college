package com.tuzhi.order_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @program: guli_parent
 * @description:
 * @author: 兔子
 * @create: 2022-04-26 08:01
 **/

@SpringBootApplication
//可以扫描到导入的模块的同包配置
@ComponentScan(basePackages = "com.tuzhi")
public class OrderApplication {
    public static void main(String[] args) {
        SpringApplication.run(OrderApplication.class, args);
    }
}
