package com.javayh.receive;

import com.javayh.entity.SysMenu;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.stereotype.Component;

import static com.javayh.util.StaticNumber.*;

/**
 * @author Dylan Yang
 * @Description: 消息接收者
 * @Title: ReceiveMessage
 * @ProjectName javayh-cloud
 * @date 2019/7/20 11:00
 */
@Slf4j
@Component
@RabbitListener(queues = JAVAYOHO_QUEUE)
public class FanoutReceive {

    @RabbitHandler
    public void receiveMessage(SysMenu sysMenu) {
        log.info("javayh is menu {}" ,sysMenu);
        //可以添加自定义业务逻辑处理
    }
}

@Slf4j
@Component
@RabbitListener(queues = DYLAN_QUEUE)
class DylanReceive {

    @RabbitHandler
    public void receiveMessage(SysMenu sysMenu) {
        log.info("dylan is menu {}" ,sysMenu);
        //可以添加自定义业务逻辑处理
    }
}

@Slf4j
@Component
@RabbitListener(queues = YHJ_QUEUE)
class YhjReceive {

    @RabbitHandler
    public void receiveMessage(SysMenu sysMenu) {
        log.info("yhj is menu {}" ,sysMenu);
        //可以添加自定义业务逻辑处理
    }
}
//@RabbitListener(
//        bindings = @QueueBinding(
//                value = @Queue(value = YHJ_QUEUE, durable = "true"),
//                exchange = @Exchange(
//                        value = JAVAYH_EXCHANGE,
//                        ignoreDeclarationExceptions = "true"
//                ),
//                key = {"direct"}
//        ))