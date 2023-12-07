package com.meikelai;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.config.OutputFile;

import java.util.Collections;

public class CodeGenerator {

    public static void main(String[] args) {
        FastAutoGenerator.create("jdbc:mysql://localhost:3306/meikelai?useUnicode=true&characterEncoding=UTF-8&serverTimezone=UTC",
                        "root", "123456")
                .globalConfig(builder -> {
                    builder.author("Yang Le") // 设置作者
                            .outputDir("/Users/yl/Downloads/platform-master/meikelai/src/main/java") // 指定输出目录
                            .disableOpenDir(); // 禁止打开输出目录
                })
                .packageConfig(builder -> {
                    builder.parent("com.meikelai") // 设置父包名
                            .entity("entity")
                            .service("service")
                            .serviceImpl("service.impl")
                            .mapper("mapper")
                            .controller("controller")
                            .other("other")
                            .pathInfo(Collections.singletonMap(OutputFile.xml, "/Users/yl/Downloads/platform-master/meikelai/src/main/resources/mapper")); // 设置 XML 路径
                })
                .strategyConfig(builder -> {
                    builder.addInclude("Cart","Cart_item") // 设置需要生成的表名
                            .entityBuilder().naming(NamingStrategy.underline_to_camel)
                            .controllerBuilder().enableRestStyle()
                            .mapperBuilder().enableMapperAnnotation().build();
                })
                .execute();
    }
}
