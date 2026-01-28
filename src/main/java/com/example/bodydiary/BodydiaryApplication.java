package com.example.bodydiary;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 启动类
 */
@SpringBootApplication
@MapperScan("com.example.bodydiary.api.*.mapper")
public class BodydiaryApplication {

    public static void main(String[] args) {
        SpringApplication.run(BodydiaryApplication.class, args);
    }

}
