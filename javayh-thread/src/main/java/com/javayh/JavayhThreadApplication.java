package com.javayh;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

@MapperScan(basePackages = "com.javayh.mapper")
@SpringBootApplication
public class JavayhThreadApplication {

    public static void main(String[] args) {
        SpringApplication.run(JavayhThreadApplication.class, args);
    }

}
