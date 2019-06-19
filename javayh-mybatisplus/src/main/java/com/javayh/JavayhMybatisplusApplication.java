package com.javayh;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan(basePackages = "com.javayh.mapper")
@SpringBootApplication
public class JavayhMybatisplusApplication {

    public static void main(String[] args) {
        SpringApplication.run(JavayhMybatisplusApplication.class, args);
    }

}
