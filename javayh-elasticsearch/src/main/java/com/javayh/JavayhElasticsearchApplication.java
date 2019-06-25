package com.javayh;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

//@MapperScan(basePackages = "com.javayh.repository.*")
@EnableElasticsearchRepositories(basePackages ="com.javayh.repository")
@SpringBootApplication
public class JavayhElasticsearchApplication {

    public static void main(String[] args) {
        SpringApplication.run(JavayhElasticsearchApplication.class, args);
    }
}
