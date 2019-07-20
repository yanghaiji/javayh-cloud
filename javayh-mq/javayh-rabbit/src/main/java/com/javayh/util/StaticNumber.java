package com.javayh.util;

/**
 * @author Dylan Yang
 * @Description: StaticNumber
 * @Title: StaticNumber
 * @ProjectName javayh-cloud
 * @date 2019/7/17 15:15
 */
public interface StaticNumber {

    /*Direct 方式*/
    //保存用户-交换机名称
    String SAVE_USER_EXCHANGE_NAME = "direct.exchange.name";
    //保存用户-队列名称
    String SAVE_USER_QUEUE_NAME = "save.queue.name";
    //保存用户-队列路由键
    String SAVE_USER_QUEUE_ROUTE_KEY = "save.queue.route.key";

    /*Fanout 方式*/
    /*队列名*/
    String JAVAYOHO_QUEUE = "javayoho";
    String DYLAN_QUEUE = "dylan";
    String YHJ_QUEUE = "yanghaiji";
    /*交换机名*/
    String JAVAYH_EXCHANGE = "javayh.exchange";

    /*主题模式*/
    String JAVAYOHO_TOPIC = "topic.javayh";
    String YHJ_TOPIC = "topic.yhj";
    String TOPIC = "topic.#";
    String TOPIC_EXCHANGE = "javayh.topic";
    String TOPIC_KEY = "javayh.topic.key";

}
