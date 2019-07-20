package com.javayh.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static com.javayh.util.StaticNumber.*;

/**
 * @author Dylan Yang
 * @Description: 主题模式
 * @Title: TopicRabbitConfig
 * @ProjectName javayh-cloud
 * @date 2019/7/20 16:34
 */
@Configuration
public class TopicRabbitConfig {

    @Bean("queueMessage")
    public Queue queueMessage() {
        return new Queue(JAVAYOHO_TOPIC);
    }

    @Bean("queueMessages")
    public Queue queueMessages() {
        return new Queue(YHJ_TOPIC);
    }

    @Bean
    TopicExchange exchange() {
        return new TopicExchange(TOPIC_EXCHANGE);
    }

    @Bean
    Binding bindingExchangeMessage(Queue queueMessage, TopicExchange exchange) {
        return BindingBuilder.bind(queueMessage).to(exchange).with(JAVAYOHO_TOPIC);
    }

    @Bean
    Binding bindingExchangeMessages(Queue queueMessages, TopicExchange exchange) {
        return BindingBuilder.bind(queueMessages).to(exchange).with(TOPIC);
    }
}

