package com.tuzhi.service_msm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

import javax.sql.DataSource;

/**
 * @program: guli_parent
 * @description: 启动类
 * @author: 兔子
 * @create: 2022-04-22 19:11
 **/

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@ComponentScan(basePackages = {"com.tuzhi"})
@EnableDiscoveryClient //nacos服务发现注册
public class MsmApplication {
    public static void main(String[] args) {
        SpringApplication.run(MsmApplication.class, args);
    }
}
