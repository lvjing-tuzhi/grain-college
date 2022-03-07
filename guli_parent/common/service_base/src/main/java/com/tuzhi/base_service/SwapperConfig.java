package com.tuzhi.base_service;

import com.google.common.base.Predicates;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.env.Profiles;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.VendorExtension;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;


@Configuration
@EnableSwagger2  //开启Swagger2
public class SwapperConfig {

    //配置Swapper的Docket的bean实例
    @Bean
    public Docket docket(Environment environment) {
//        获取到当前是生产环境还是发布环境，从而来进行自动的改变swapper配置
        Profiles dev = Profiles.of("dev");
        boolean flag = environment.acceptsProfiles(dev);
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .enable(flag)
                .select()
//                RequestHandlerSelectors:
//                    basePackage 指定要扫描的包
//                    any() 扫描全部
//                    none() 不扫描
//                    withClassAnnotation 扫描类上的注解，参数是一个注解的放射对象，annocation.class
//                    withMethodAnnotation 扫描方法上的注解
//                .apis(RequestHandlerSelectors.basePackage("com.tuzhi.controller"))
//                .paths(PathSelectors.any())//过滤路径
                .paths(Predicates.not(PathSelectors.regex("/error.*")))
                .paths(Predicates.not(PathSelectors.regex("/admin/.*")))
                .build();
    }

    //    配置Swapper信息
    public ApiInfo apiInfo() {
        Contact contact = new Contact("吕竟", "127.0.0.1/hello", "542918096@qq.com");
        return new ApiInfo(
                "谷粒商城",
                "这是一个兔子开发的谷粒商城接口文档",
                "1.0",
                "urn:tos",
                contact,
                "Apache 2.0",
                "http://www.apache.org/licenses/LICENSE-2.0",
                new ArrayList<VendorExtension>()

        );
    }

}