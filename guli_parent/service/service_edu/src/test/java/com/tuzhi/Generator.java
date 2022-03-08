package com.tuzhi;



import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import org.apache.ibatis.annotations.Mapper;
import org.junit.Test;
import org.springframework.util.StringUtils;

import java.util.Collections;

/**
 * @program: guli_parent
 * @description:
 * @author: 兔子
 * @create: 2022-03-06 20:06
 **/


public class Generator {
    @Test
    public void run() {
        FastAutoGenerator.create("jdbc:mysql://localhost:3306/guli?serverTimezone=Asia/Shanghai&amp&useSSL=true&amp&useUnicode=true&amp&characterEncoding=utf-8",
                "root", "root")
                .globalConfig(builder -> {
                    builder.author("tuzhi") // 设置作者
                            .enableSwagger() // 开启 swagger 模式
                            .fileOverride() // 覆盖已生成文件
                            .outputDir("D:\\项目\\谷粒学院\\guli_parent\\service\\service_edu\\src\\main\\java") // 指定输出目录
                            .disableOpenDir(); //禁止打开输出目录
                })
                .packageConfig(builder -> {
                    builder.parent("com.tuzhi") // 设置父包名
                            .moduleName("edu_service") // 设置父包模块名
                            .entity("pojo"); //设置实体类的名字
                })
                .strategyConfig(builder -> {
                    builder.addInclude("edu_teacher") // 设置需要生成的表名
                            .addTablePrefix("t_", "c_"); // 设置过滤表前缀
                })
//                实体策略
                .strategyConfig(builder -> {
                    builder.entityBuilder()
                    .enableLombok()
                    .enableChainModel()
                    .idType(IdType.AUTO);
                })
//                controller配置策略
                .strategyConfig(builder -> {
                    builder.controllerBuilder()
//                    开启生成@RestController 控制器
                    .enableRestStyle();
                })
//                Mapper配置策略
                .strategyConfig(builder -> {
                    builder.mapperBuilder()
//                    创建Mapper接口的时候都会添加一个@Mapper注解
                    .enableMapperAnnotation();
                })
//                .templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                .execute();
    }

}
