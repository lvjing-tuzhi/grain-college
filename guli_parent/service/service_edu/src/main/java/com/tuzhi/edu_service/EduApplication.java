package com.tuzhi.edu_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @program: guli_parent
 * @description:
 * @author: 兔子
 * @create: 2022-03-07 08:35
 **/

@SpringBootApplication
//可以扫描到导入的模块的同包配置
@ComponentScan(basePackages = "com.tuzhi")
public class EduApplication {
    public static void main(String[] args) {
        SpringApplication.run(EduApplication.class, args);
    }
}
