package com.javayh.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.io.Serializable;

/**
 * @author Dylan Yang
 * @Description: KafkaTopicProperties
 * @Title: KafkaTopicProperties
 * @ProjectName javayh-cloud
 * @date 2019/7/16 23:23
 */
@Data
@ConfigurationProperties("kafka.topic")
public class KafkaTopicProperties implements Serializable {
    private String groupId;
    private String[] topicName;

}

