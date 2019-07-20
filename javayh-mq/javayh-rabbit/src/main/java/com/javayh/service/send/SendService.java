package com.javayh.service.send;

import org.springframework.amqp.rabbit.core.RabbitTemplate;

/**
 * @author Dylan Yang
 * @Description: 统一捷库封装
 * @Title: SendService
 * @ProjectName javayh-cloud
 * @date 2019/7/20 12:08
 */
public interface SendService extends RabbitTemplate.ConfirmCallback {
    void sendMessage(Object message);
}

