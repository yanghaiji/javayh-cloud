## 一、 Kafka 配置

### 1.下载kafka
>http://kafka.apache.org/downloads
### 2.安装配置
#### wind 配置修改
>2.1 kafka安装路径/config/server.properties,讲以下内容进行替换
>2.2 listeners=PLAINTEXT://:9092
>2.3 advertised.listeners=PLAINTEXT://localhost:9092
#### 3.启动
>.\bin\windows\kafka-server-start.bat .\config\server.properties

### 注：
> 可以使用kafka客户端进行监控，下载地址如下
> http://www.kafkatool.com/download.html

## 二、安装zookeeper
### 1.下载
> http://zookeeper.apache.org/releases.html
### 2.安装配置
#### wind 配置修改
>2.1 将conf下“zoo_sample.cfg”重命名为“zoo.cfg”,这里可以将log地址改成自定义位置
### 3.启动
> 打开新的cmd，输入zkServer，运行Zookeeper

