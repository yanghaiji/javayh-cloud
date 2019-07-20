package com.javayh;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@MapperScan(basePackages = "com.javayh.mapper")
@SpringBootApplication
public class JavayhKafkaApplication {

    public static void main(String[] args) {
        SpringApplication.run(JavayhKafkaApplication.class, args);
    }

}
