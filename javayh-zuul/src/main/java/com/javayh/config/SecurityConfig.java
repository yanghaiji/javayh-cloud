package com.javayh.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * 〈Security配置〉
 * @author Yang HaiJi, 2019-05-17
 * @version Araf v1.0
 */
@Configuration
@EnableWebSecurity
@Order(99)//必加
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    /**
     * 禁止csrf
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
    }
}
