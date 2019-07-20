package com.javayh.config;

/**
 * @author Dylan Yang
 * @Description: 订阅发布配置
 * @Title: FanoutRabbitConfig
 * @ProjectName javayh-cloud
 * @date 2019/7/20 11:35
 */

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static com.javayh.util.StaticNumber.*;


@Configuration
public class FanoutRabbitConfig {

    @Bean
    public Queue AMessage() {
        return new Queue(JAVAYOHO_QUEUE);
    }

    @Bean
    public Queue BMessage() {
        return new Queue(YHJ_QUEUE);
    }

    @Bean
    public Queue CMessage() {
        return new Queue(DYLAN_QUEUE);
    }

    @Bean
    FanoutExchange fanoutExchange() {
        return new FanoutExchange(JAVAYH_EXCHANGE);
    }

    @Bean
    Binding bindingExchangeA(Queue AMessage,FanoutExchange fanoutExchange) {
        return BindingBuilder.bind(AMessage).to(fanoutExchange);
    }

    @Bean
    Binding bindingExchangeB(Queue BMessage, FanoutExchange fanoutExchange) {
        return BindingBuilder.bind(BMessage).to(fanoutExchange);
    }

    @Bean
    Binding bindingExchangeC(Queue CMessage, FanoutExchange fanoutExchange) {
        return BindingBuilder.bind(CMessage).to(fanoutExchange);
    }
}

