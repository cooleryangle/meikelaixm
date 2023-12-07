package com.meikelai;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.meikelai.mapper")
public class MeikelaiApplication {

    public static void main(String[] args) {
        SpringApplication.run(MeikelaiApplication.class, args);
    }

}
