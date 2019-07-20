package com.javayh.config;

import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Dylan Yang
 * @Description: 覆盖原有方式
 * @Title: JavayhMQConverter
 * @ProjectName javayh-cloud
 * @date 2019/7/20 16:29
 */
//@Configuration
//public class JavayhMQConverter {
//
//    @Bean
//    public MessageConverter messageConverter(){
//        return new Jackson2JsonMessageConverter();
//    }
//
//}

