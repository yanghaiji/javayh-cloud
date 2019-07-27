
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

详细说明请查看：https://blog.csdn.net/weixin_38937840/article/details/96591029

## 一、RabbitMQ 安装（wind）

### 下载 Erlang安装包
> 由于rabbit基于Erlang，所以先安装
> 安装完成后配置环境变量，并追加到PATH后
> cdm 输入 erl 查看是否安装成功

### 下载 RabbitMQ安装包
> 安装完成后配置环境变量，并追加到PATH后
> cdm 输入 rabbitmqctl status 查看是否安装成功
> 成功则输入 rabbitmq-plugins enable rabbitmq_management 进行web插件安装

### 所需要的安装包
链接：https://pan.baidu.com/s/16SVHxuiZ08vj40QJCsEaYA 
提取码：nm8q 


