package com.javayh.service.send;

import com.javayh.util.LogOut;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static com.javayh.util.StaticNumber.JAVAYOHO_TOPIC;
import static com.javayh.util.StaticNumber.TOPIC_EXCHANGE;
import static com.javayh.util.StaticNumber.YHJ_TOPIC;

/**
 * @author Dylan Yang
 * @Description: 主题实现
 * @Title: SendTopicService
 * @ProjectName javayh-cloud
 * @date 2019/7/20 16:54
 */
@Slf4j
@Component
public class SendTopicService implements SendService {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Override
    public void sendMessage(Object message) {
        rabbitTemplate.convertAndSend(TOPIC_EXCHANGE,JAVAYOHO_TOPIC,message);
        rabbitTemplate.convertAndSend(TOPIC_EXCHANGE,YHJ_TOPIC,message);
        log.info("send topic success -> {}",message);
    }

    @Override
    public void confirm(CorrelationData correlationData, boolean isSendSuccess, String arg) {
        LogOut.logOut(correlationData,isSendSuccess,arg);
    }

}

