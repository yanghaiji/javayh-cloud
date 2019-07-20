package com.javayh.service;

import com.javayh.entity.SysMenu;
import com.javayh.util.StaticNumber;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;
/**
 * @author Dylan Yang
 * @Description: KafkaService
 * @Title: KafkaService
 * @ProjectName javayh-cloud
 * @date 2019/7/16 23:26
 */
@Slf4j
@Service
public class KafkaService {

    @Autowired
    private KafkaTemplate kafkaTemplate;

    /**
     * 生产者
     * @param data
     */
    public void send(SysMenu data){
        ListenableFuture send = kafkaTemplate.send(StaticNumber.JAVAYOHO, data);
        send.addCallback(new ListenableFutureCallback(){
            @Override
            public void onSuccess(Object result) {
                log.info("send success");
            }
            @Override
            public void onFailure(Throwable ex) {
                log.info("send failure");
            }
        });
        log.info("send success");
    }

//    public void sendString(String data){
//        ListenableFuture send = kafkaTemplate.send(StaticNumber.YANGHJ, data);
//        send.addCallback(new ListenableFutureCallback(){
//            @Override
//            public void onSuccess(Object result) {
//                log.info("send success");
//            }
//            @Override
//            public void onFailure(Throwable ex) {
//                log.info("send failure");
//            }
//        });
//        log.info("send success");
//    }
}


