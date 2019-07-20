package com.javayh.service;

import com.javayh.entity.SysMenu;
import com.javayh.service.send.SendFanoutService;
import com.javayh.service.send.SendDirectService;
import com.javayh.service.send.SendTopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Dylan Yang
 * @Description: JavaYhService
 * @Title: JavaYhService
 * @ProjectName javayh-cloud
 * @date 2019/7/20 10:39
 */
@Service
@Transactional
public class JavaYhService {

    @Autowired
    private SendDirectService sendDirectService;

    @Autowired
    private SendFanoutService sendFanoutService;

    @Autowired
    private SendTopicService sendTopicService;

    /**
     * Direct
     * @param sysMenu
     * @return
     */
    public String save(SysMenu sysMenu) {
        //发送消息到RabbitMQ
        sendDirectService.sendMessage(sysMenu);
        return sysMenu.getId();
    }

    /**
     * Fanout
     * @param sysMenu
     * @return
     */
    public String saveFanout(SysMenu sysMenu) {
        //发送消息到RabbitMQ
        sendFanoutService.sendMessage(sysMenu);
        return sysMenu.getId();
    }

    /**
     * Topic
     * @param message
     * @return
     */
    public String saveTopic(String message) {
        //发送消息到RabbitMQ
        sendTopicService.sendMessage(message);
        return message;
    }

}

