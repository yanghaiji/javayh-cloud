
## 一、RabbitMQ 简介
官方推出的六种模式
### 1.1 "Hello World!" 
![在这里插入图片描述](https://img-blog.csdnimg.cn/20190720145646828.png)
简单模式 一对一生产消费
### 1.2 Work Queues
![在这里插入图片描述](https://img-blog.csdnimg.cn/20190720145947947.png)
一个生产者对应多个消费队列
默认情况下，RabbitMQ将按顺序将每条消息发送给下一个消费者。平均而言，每个消费者将获得相同数量的消息
### 1.3 Publish/Subscribe
![在这里插入图片描述](https://img-blog.csdnimg.cn/2019072015044025.png)
订阅发布：多个队列订阅一个交换机，每个队列都会接收到自己订阅的交换机
### 1.4 Routing
![在这里插入图片描述](https://img-blog.csdnimg.cn/2019072015100250.png)
路由模式：对消息进行过滤，把控消费队列获取消息的信息量
### 1.5 Topics
![在这里插入图片描述](https://img-blog.csdnimg.cn/20190720151402627.png)
主题模式：
*（星号）可以替代一个单词。＃（hash）可以替换零个或多个单词。
这里也相当于根据消费者的习性进行分发消费，功能很强大
### 1.6 RPC
![在这里插入图片描述](https://img-blog.csdnimg.cn/2019072015184010.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3dlaXhpbl8zODkzNzg0MA==,size_16,color_FFFFFF,t_70)
远程服务调用：有待学习。。。。
## 二、项目整合
### 2.1 配置文件
#### 2.1.1 pom文件
```
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>
    <parent>
        <groupId>com.javayh</groupId>
        <artifactId>javayh-mq</artifactId>
        <version>0.0.1-SNAPSHOT</version>
        <relativePath>../</relativePath> <!-- lookup parent from repository -->
    </parent>
    <groupId>com.javayh</groupId>
    <artifactId>javayh-rabbit</artifactId>
    <version>0.0.1-SNAPSHOT</version>
    <name>javayh-rabbit</name>
    <description>Demo project for Spring Boot</description>
    <dependencies>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-amqp</artifactId>
        </dependency>
    </dependencies>
</project>

```
#### 2.1.2 yml文件

```
server:
  port: 8025
spring:
  application:
    name: javayh-rabbit
  rabbitmq:
    host: localhost
    port: 5672
    username: guest
    password: guest
    #虚拟主机(一个RabbitMQ服务可以配置多个虚拟主机，每一个虚拟机主机之间是相互隔离，相互独立的，授权用户到指定的virtual-host就可以发送消息到指定队列)
    virtual-host: /
    #消息发送确认回调
    publisher-confirms: true
#    listener:
#      simple:
#        max-concurrency: 20
#        concurrency: 10
```
### 2.2 模式配置
#### 2.2.1 Fanout配置
```
@Configuration
public class FanoutRabbitConfig {

    @Bean
    public Queue AMessage() {
        return new Queue(JAVAYOHO_QUEUE);
    }

    @Bean
    public Queue BMessage() {
        return new Queue(YHJ_QUEUE);
    }

    @Bean
    public Queue CMessage() {
        return new Queue(DYLAN_QUEUE);
    }

    @Bean
    FanoutExchange fanoutExchange() {
        return new FanoutExchange(JAVAYH_EXCHANGE);
    }

    @Bean
    Binding bindingExchangeA(Queue AMessage,FanoutExchange fanoutExchange) {
        return BindingBuilder.bind(AMessage).to(fanoutExchange);
    }

    @Bean
    Binding bindingExchangeB(Queue BMessage, FanoutExchange fanoutExchange) {
        return BindingBuilder.bind(BMessage).to(fanoutExchange);
    }

    @Bean
    Binding bindingExchangeC(Queue CMessage, FanoutExchange fanoutExchange) {
        return BindingBuilder.bind(CMessage).to(fanoutExchange);
    }
}
```
#### 2.2.2  Direct配置

```
@Configuration
public class DirectConfiguration {

    /**
     * 配置交换机实例
     * 为交换机设置名称
     * @return
     */
    @Bean
    public DirectExchange directExchange() {
        return new DirectExchange(SAVE_USER_EXCHANGE_NAME);
    }

    /**
     * 配置队列实例，并且设置持久化队列
     *
     * @return
     */
    @Bean
    public Queue queue() {
        return new Queue(SAVE_USER_QUEUE_NAME, true);
    }

    /**
     * 将队列绑定到交换机上，并设置消息分发的路由键
     *
     * @return
     */
    @Bean
    public Binding binding() {
       return BindingBuilder.bind(queue()).to(directExchange()).with(SAVE_USER_QUEUE_ROUTE_KEY);
    }

}

```
#### 2.2.3  Topic配置

```
public class TopicRabbitConfig {

    @Bean
    public Queue queueMessage() {
        return new Queue(JAVAYOHO_TOPIC);
    }

    @Bean
    public Queue queueMessages() {
        return new Queue(YHJ_TOPIC);
    }

    @Bean
    TopicExchange exchange() {
        return new TopicExchange(TOPIC_EXCHANGE);
    }

    @Bean
    Binding bindingExchangeMessage(Queue queueMessage, TopicExchange exchange) {
        return BindingBuilder.bind(queueMessage).to(exchange).with(TOPIC_KEY);
    }

    @Bean
    Binding bindingExchangeMessages(Queue queueMessages, TopicExchange exchange) {
        return BindingBuilder.bind(queueMessages).to(exchange).with(TOPIC);
    }
}

```
## 三、生产者创建
### 3.1 统一接口封装
```
public interface SendService extends RabbitTemplate.ConfirmCallback {
    void sendMessage(Object message);
}
```
### 3.2 消息生产
这里主要以Topic为主，其他的模式在文章底部有代码连接
```
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
    
```
## 四、RabbitMq客户端
### 4.1 Topic主题
项目启动后，我们登录客户端，这时我们的Topic主题创建完成
![在这里插入图片描述](https://img-blog.csdnimg.cn/20190720183031917.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3dlaXhpbl8zODkzNzg0MA==,size_16,color_FFFFFF,t_70)
### 4.2 队列获取消息
项目启动后，访问生产者，进行消息生产，在队列内即可看到我们的消息
![在这里插入图片描述](https://img-blog.csdnimg.cn/20190720183332243.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3dlaXhpbl8zODkzNzg0MA==,size_16,color_FFFFFF,t_70)
### 4.3 消费者进行消费
![在这里插入图片描述](https://img-blog.csdnimg.cn/20190720183813102.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3dlaXhpbl8zODkzNzg0MA==,size_16,color_FFFFFF,t_70)
![在这里插入图片描述](https://img-blog.csdnimg.cn/20190720183905714.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3dlaXhpbl8zODkzNzg0MA==,size_16,color_FFFFFF,t_70)
至此Springboot与RabbitMq的整合已完成，希望对初学者有所帮助
项目源码：https://github.com/Dylan-haiji/javayh-cloud ，欢迎各位start
