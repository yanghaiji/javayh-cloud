package com.javayh.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.support.CorrelationData;

/**
 * @author Dylan Yang
 * @Description: LogOut
 * @Title: LogOut
 * @ProjectName javayh-cloud
 * @date 2019/7/20 16:55
 */
@Slf4j
public class LogOut {

    public static void logConfirm(CorrelationData correlationData, boolean isSendSuccess, String arg){
        log.info("confirm id : {}" ,correlationData.getId());
        if (isSendSuccess) {
            log.info("confirm isSendSuccess :{}" ,isSendSuccess);
        } else {
            log.info("confirm falid : {}" + arg);
        }
    }

    /**
     * 消费失败回调
     * @param message       消息主体
     * @param code          主体
     * @param txt           描述
     * @param exchange      使用的交换器
     * @param key           路由建
     */
    public static void logReturnedMessage(Message message, int code, String txt, String exchange, String key) {
        log.info("message  : {}" ,message);
        log.info("code  : {}" ,code);
        log.info("txt  : {}" ,txt);
        log.info("exchange  : {}" ,exchange);
        log.info("key  : {}" ,key);
    }
}

