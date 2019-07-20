package com.javayh.receive;

import com.javayh.entity.SysMenu;
import com.javayh.util.StaticNumber;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import static com.javayh.util.StaticNumber.SAVE_USER_QUEUE_NAME;

/**
 * @author Dylan Yang
 * @Description: 消息接收者
 * @Title: ReceiveMessage
 * @ProjectName javayh-cloud
 * @date 2019/7/20 11:00
 */
@Slf4j
@Component
@RabbitListener(queues = SAVE_USER_QUEUE_NAME)
public class ReceiveMessage {

    @RabbitHandler
    public void receiveMessage(SysMenu sysMenu) {
        log.info("save is menu {}" ,sysMenu);
        //可以添加自定义业务逻辑处理
    }
}

