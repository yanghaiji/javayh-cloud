package com.javayh.config;

import com.javayh.util.StaticNumber;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static com.javayh.util.StaticNumber.SAVE_USER_EXCHANGE_NAME;
import static com.javayh.util.StaticNumber.SAVE_USER_QUEUE_NAME;
import static com.javayh.util.StaticNumber.SAVE_USER_QUEUE_ROUTE_KEY;

/**
 * @author Dylan Yang
 * @Description: 保存用户RabbitMQ相关配置类 Direct
 * @Title: DirectConfiguration
 * @ProjectName javayh-cloud
 * @date 2019/7/20 10:18
 */
@Configuration
public class DirectConfiguration {

    /**
     * 配置交换机实例
     * 为交换机设置名称
     * @return
     */
    @Bean
    public DirectExchange directExchange() {
        return new DirectExchange(SAVE_USER_EXCHANGE_NAME);
    }

    /**
     * 配置队列实例，并且设置持久化队列
     *
     * @return
     */
    @Bean
    public Queue queue() {
        return new Queue(SAVE_USER_QUEUE_NAME, true);
    }

    /**
     * 将队列绑定到交换机上，并设置消息分发的路由键
     *
     * @return
     */
    @Bean
    public Binding binding() {
        //链式写法: 用指定的路由键将队列绑定到交换机
        return BindingBuilder.bind(queue()).to(directExchange()).with(SAVE_USER_QUEUE_ROUTE_KEY);
    }

}

