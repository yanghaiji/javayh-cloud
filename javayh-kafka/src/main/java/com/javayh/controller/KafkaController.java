package com.javayh.controller;

import com.javayh.entity.SysMenu;
import com.javayh.service.KafkaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Dylan Yang
 * @Description: KafkaController
 * @Title: KafkaController
 * @ProjectName javayh-cloud
 * @date 2019/7/16 23:50
 */
@RestController
@RequestMapping(value = "/javayh/kafka/")
public class KafkaController {
    @Autowired
    private KafkaService kafkaService;

    @GetMapping(value = "send")
    public void send(){
        SysMenu sysMenu = new SysMenu();
        sysMenu.setId("1");
        sysMenu.setCode("123456");
        sysMenu.setPcode("Kafka Send");
        kafkaService.send(sysMenu);
    }

//    @GetMapping(value = "sendString")
//    public void sendString(){
//        kafkaService.sendString("Kafka send");
//    }
}

