package com.javayh.receive;

import com.javayh.entity.SysMenu;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.stereotype.Component;

import static com.javayh.util.StaticNumber.*;

/**
 * @author Dylan Yang
 * @Description: TopicService
 * @Title: TopicService
 * @ProjectName javayh-cloud
 * @date 2019/7/20 17:12
 */
@Slf4j
@Component
@RabbitListener(queues = JAVAYOHO_TOPIC)
public class TopicService {
    @RabbitHandler
    public void receiveMessage(String message) {
        log.info("javayoho is message {}" ,message);
        //可以添加自定义业务逻辑处理
    }
}

@Slf4j
@Component
@RabbitListener(queues = YHJ_TOPIC)
class TopicTwoService {
    @RabbitHandler
    public void receiveMessage(String message) {
        log.info("yhj is message {}" ,message);
        //可以添加自定义业务逻辑处理
    }
}
