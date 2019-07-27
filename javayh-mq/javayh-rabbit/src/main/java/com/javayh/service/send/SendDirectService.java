package com.javayh.service.send;

import com.javayh.util.LogOut;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

import static com.javayh.util.StaticNumber.SAVE_USER_EXCHANGE_NAME;
import static com.javayh.util.StaticNumber.SAVE_USER_QUEUE_ROUTE_KEY;

/**
 * @author Dylan Yang
 * @Description: 统一消息发送 Direct 方式
 * @Title: SendMessageService
 * @ProjectName javayh-cloud
 * @date 2019/7/20 10:22
 */
@Slf4j
@Component
public class SendDirectService implements SendService{

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Override
    public void sendMessage(Object message) {
        //构建回调返回的数据
        CorrelationData correlationData = new CorrelationData(UUID.randomUUID().toString());
        rabbitTemplate.convertAndSend(SAVE_USER_EXCHANGE_NAME, SAVE_USER_QUEUE_ROUTE_KEY, message, correlationData);
        log.info("Send direct is success {}" , message);
    }

    /**
     * 消息发送回调函数
     * @param correlationData
     * @param isSendSuccess
     * @param arg
     */
    @Override
    public void confirm(CorrelationData correlationData, boolean isSendSuccess, String arg) {
        LogOut.logConfirm(correlationData,isSendSuccess,arg);
    }

    /**
     * 消费失败回调
     * @param message       消息主体
     * @param code          主体
     * @param txt           描述
     * @param exchange      使用的交换器
     * @param key           路由建
     */
    @Override
    public void returnedMessage(Message message, int code, String txt, String exchange, String key) {
        LogOut.logReturnedMessage(message,code,txt,exchange,key);
    }

    @Override
    public void afterPropertiesSet() {
        rabbitTemplate.setConfirmCallback(this::confirm);
        rabbitTemplate.setReturnCallback(this::returnedMessage);
    }


}

