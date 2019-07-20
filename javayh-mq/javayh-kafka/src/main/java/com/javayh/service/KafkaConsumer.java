package com.javayh.service;

import com.javayh.entity.SysMenu;
import com.javayh.mapper.SysMeunMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.kafka.ConcurrentKafkaListenerContainerFactoryConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerEndpointRegistry;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;
import org.springframework.kafka.listener.MessageListenerContainer;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Dylan Yang
 * @Description: KafkaConsumer
 * @Title: KafkaConsumer
 * @ProjectName javayh-cloud
 * @date 2019/7/17 15:53
 */
@Slf4j
@Component
public class KafkaConsumer {

    @Autowired
    private SysMeunMapper sysMeunMapper;

    @Autowired
    private KafkaListenerEndpointRegistry registry;

    /**
     *  消费者
     * @param record 消息
     */
    @Transactional
    @KafkaListener(topics = "#{kafkaTopicName}", groupId = "#{topicGroupId}" )
    public void processMessage(ConsumerRecord<String, SysMenu> record) {
        SysMenu sysMenu = record.value();
        log.info("SysMenu --> {}",sysMenu.toString());
        if (sysMenu == null) {
            throw new RuntimeException("模拟业务出错");
        }
        log.info("kafka processMessage start, topic = {}, msg = {}", record.topic(), sysMenu);
        //入库
        Integer i = sysMeunMapper.modifyById(sysMenu);
        log.info("更新 {}",i);
        if (i == null || i == 0){
            log.info("kafka processMessage end failure");
        }else {
            log.info("kafka processMessage end success");
        }
    }


//    /**
//     *  消费者 定时
//     * @param record 消息
//     */
//    @KafkaListener(id = "YangHaiJi" ,topics = "#{kafkaTopicName}", groupId = "#{topicGroupId}",containerFactory = "batchFactory" )
//    public void processScheduled(ConsumerRecord record) {
//        String sysMenu = record.value();
//        log.info("SysMenu --> {}",sysMenu.toString());
//        if (sysMenu == null) {
//            throw new RuntimeException("模拟业务出错");
//        }
//        log.info("kafka processMessage start, topic = {}, msg = {}", record.topic(), sysMenu);
//
//        log.info("kafka processMessage end success {}",sysMenu);
//
//    }
//
//    @Scheduled(cron = "0 45 16 * * ?")
//    public void startListener() {
//        log.info("开启监听");
//        MessageListenerContainer container = registry.getListenerContainer("YangHaiJi");
//        if (!container.isRunning()) {
//            container.start();
//        }
//        //恢复
//        container.resume();
//    }
//
//    @Scheduled(cron = "0 54 16 * * ?")
//    public void shutdownListener() {
//        log.info("关闭监听");
//        //暂停
//        MessageListenerContainer container = registry.getListenerContainer("YangHaiJi");
//        container.pause();
//    }
//
//
//    /**
//     * kafka监听工厂
//     *
//     * @param configurer
//     * @return
//     */
//    @Bean("batchFactory")
//    public ConcurrentKafkaListenerContainerFactory<?, ?> kafkaListenerContainerFactory(
//            ConcurrentKafkaListenerContainerFactoryConfigurer configurer,
//            ConsumerFactory consumerFactory) {
//        ConcurrentKafkaListenerContainerFactory<Object, Object> factory = new ConcurrentKafkaListenerContainerFactory<>();
//        factory.setConsumerFactory(consumerFactory);
//        //开启批量消费功能
//        factory.setBatchListener(true);
//        //不自动启动
//        factory.setAutoStartup(false);
//        configurer.configure(factory, consumerFactory);
//        return factory;
//    }
//

}

