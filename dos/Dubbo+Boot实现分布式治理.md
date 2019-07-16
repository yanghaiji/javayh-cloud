## 前言

> 这是本文的代码地址
> https://github.com/Dylan-haiji/javayh-cloud/tree/javayh-dubbo/javayh-dubbo
> 欢迎各位star

## Dubbo背景

随着Internet的快速发展，Web应用程序的规模不断扩大，最终我们发现传统的垂直架构（单片机）无法再处理这个问题。分布式服务架构和流量计算架构势在必行，迫切需要一个治理系统来确保架构的有序演进。
![在这里插入图片描述](https://img-blog.csdnimg.cn/20190712111732968.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3dlaXhpbl8zODkzNzg0MA==,size_16,color_FFFFFF,t_70)

**单片架构**
当流量非常低时，只有一个应用程序，所有功能都部署在一起，以减少部署节点和成本。此时，数据访问框架（ORM）是简化CRUD工作负载的关键。

**垂直建筑**
当流量变大时，添加单片应用程序实例无法很好地加速访问，提高效率的一种方法是将单片机拆分为离散应用程序。此时，用于加速前端页面开发的Web框架（MVC）是关键。

**分布式服务架构**
当越来越多的垂直应用程序，应用程序之间的交互是不可避免的，一些核心业务被提取并作为独立服务，逐步形成一个稳定的服务中心，这样前端应用程序可以更多地响应市场需求的变化很快。此时，用于业务重用和集成的分布式服务框架（RPC）是关键。

**流量计算架构**
当服务越来越多时，容量评估变得困难，小规模的服务也经常导致资源浪费。为了解决这些问题，应该添加一个调度中心来管理基于流量的集群容量，并提高集群的利用率。此时，用于提高机器利用率的资源调度和治理中心（SOA）是关键。

以上来自官方介绍
## Dubbo版本介绍
已发布最新版本
```
<dependency>
        <groupId>org.apache.dubbo</groupId>
        <artifactId>dubbo-spring-boot-starter</artifactId>
        <version>2.7.1</version>
</dependency>
```
历史版本
![在这里插入图片描述](https://img-blog.csdnimg.cn/20190712112523991.png)
在最新版本的整合时会有些错误，还需要结合官方解决方案，本文采用的是历史版本
## 项目结构
![在这里插入图片描述](https://img-blog.csdnimg.cn/20190712125308518.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3dlaXhpbl8zODkzNzg0MA==,size_16,color_FFFFFF,t_70)
## 相关依赖
#### 父POM
```
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>
    <parent>
        <groupId>com.javayh</groupId>
        <artifactId>javayh-cloud</artifactId>
        <version>0.0.1-SNAPSHOT</version>
        <relativePath>../</relativePath> <!-- lookup parent from repository -->
    </parent>
    <groupId>com.javayh</groupId>
    <artifactId>javayh-dubbo</artifactId>
    <version>0.0.1-SNAPSHOT</version>
    <name>javayh-dubbo</name>
    <description>Demo project for Spring Boot</description>

    <properties>
        <java.version>1.8</java.version>
        <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
        <project.reporting.outputEncoding>UTF-8</project.reporting.outputEncoding>
        <dubbo.version>0.2.0</dubbo.version>
        <zk.version>0.1</zk.version>
    </properties>
    <dependencies>
        <dependency>
            <groupId>com.javayh</groupId>
            <artifactId>javayh-commons</artifactId>
            <version>0.0.1-SNAPSHOT</version>
        </dependency>

    </dependencies>

    <build>
        <plugins>
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
            </plugin>
        </plugins>
    </build>

</project>

```
#### 公用资源

```
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>
    <parent>
        <groupId>com.javayh</groupId>
        <artifactId>javayh-dubbo</artifactId>
        <version>0.0.1-SNAPSHOT</version>
        <relativePath>../</relativePath> <!-- lookup parent from repository -->
    </parent>
    <groupId>com.javayh</groupId>
    <artifactId>javayh-dubbo-api</artifactId>
    <version>0.0.1-SNAPSHOT</version>
    <name>javayh-dubbo-api</name>
    <packaging>jar</packaging>
    <description>javayh-dubbo-api</description>
    <dependencies>
        
    </dependencies>
</project>

```
#### 生产者

```
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>
    <parent>
        <groupId>com.javayh</groupId>
        <artifactId>javayh-dubbo</artifactId>
        <version>0.0.1-SNAPSHOT</version>
        <relativePath>../</relativePath> <!-- lookup parent from repository -->
    </parent>
    <groupId>com.javayh</groupId>
    <artifactId>javayh-dubbo-server</artifactId>
    <version>0.0.1-SNAPSHOT</version>
    <name>javayh-dubbo-server</name>
    <packaging>jar</packaging>
    <description>javayh-dubbo-server</description>
    <dependencies>
        <dependency>
            <groupId>com.javayh</groupId>
            <artifactId>javayh-dubbo-api</artifactId>
            <version>0.0.1-SNAPSHOT</version>
        </dependency>
        <!-- springboot集成dubbo的起步依赖 -->
        <dependency>
            <groupId>com.alibaba.boot</groupId>
            <artifactId>dubbo-spring-boot-starter</artifactId>
            <version>${dubbo.version}</version>
        </dependency>
        <!--引入zookeeper的客户端工具-->
        <!-- https://mvnrepository.com/artifact/com.github.sgroschupf/zkclient -->
        <dependency>
            <groupId>com.github.sgroschupf</groupId>
            <artifactId>zkclient</artifactId>
            <version>${zk.version}</version>
        </dependency>
    </dependencies>
</project>

```
#### 消费者

```
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>
    <parent>
        <groupId>com.javayh</groupId>
        <artifactId>javayh-dubbo</artifactId>
        <version>0.0.1-SNAPSHOT</version>
        <relativePath>../</relativePath> <!-- lookup parent from repository -->
    </parent>
    <groupId>com.javayh</groupId>
    <artifactId>javayh-dubbo-client</artifactId>
    <version>0.0.1-SNAPSHOT</version>
    <name>javayh-dubbo-client</name>
    <packaging>jar</packaging>
    <description>javayh-dubbo-client</description>
    <dependencies>
        <dependency>
            <groupId>com.javayh</groupId>
            <artifactId>javayh-dubbo-api</artifactId>
            <version>0.0.1-SNAPSHOT</version>
        </dependency>
        <!-- springboot集成dubbo的起步依赖 -->
        <dependency>
            <groupId>com.alibaba.boot</groupId>
            <artifactId>dubbo-spring-boot-starter</artifactId>
            <version>${dubbo.version}</version>
        </dependency>
        <!--引入zookeeper的客户端工具-->
        <!-- https://mvnrepository.com/artifact/com.github.sgroschupf/zkclient -->
        <dependency>
            <groupId>com.github.sgroschupf</groupId>
            <artifactId>zkclient</artifactId>
            <version>${zk.version}</version>
        </dependency>
    </dependencies>

</project>

```
## 配置文件
#### 消费者
```
spring:
  application:
    name: javayh-dubbo-client

server:
  port: 8022
dubbo:
  application:
    name: javayh-dubbo-client
  registry:
    address: zookeeper://127.0.0.1:2181
  monitor:
    protocol: registry
```
#### 生产者

```
server:
  port: 8021

dubbo:
  application:
    name: javayh-dubbo-server
  registry:
    protocol: zookeeper
    address: 127.0.0.1:2181
  #通信规则（通信协议和接口）
  protocol:
    name: dubbo
    port: 20880
  monitor:
    protocol: registry
#开启包扫描，可替代 @EnableDubbo 注解
#  scan:
#    base-packages: com.javayh
```
## RPC流程

> 1.在公用资源内声明接口
> 2.在生产者内实现
> 3.在消费者内调用
> 注：采用ZK注册中心
#### 公用API

```
public interface  ApiService {

    ApiDubbo save();

    String findProt(String prot);

}
```
#### 生产者

```
//这里的@Service注解是Dubbo内的，一定要引用对，
//关于版本号，如果生产者定义，消费者也必须采用相同的版本号
@Service(version = "javayh-0.1",interfaceClass = ApiService.class)
public class ApiServer implements ApiService{

    @Override
    public ApiDubbo save() {
        ApiDubbo apiDubbo = new ApiDubbo.ApiDubboBuilder().
                setApiName("javayh-dubbo-api").
                setApiType("AipDubbo.class").
                setApiUrl("javayh/dubbo/api/save").build();
        return apiDubbo;
    }

    @Override
    public String findProt(String prot) {
        return prot;
    }
}

```
#### 消费者

```
@RestController
@RequestMapping(value = "/javayh/dubbo/api/")
public class AipController {

    @Reference(version = "javayh-0.1")
    private ApiService apiService;

    @GetMapping(value = "save")
    public Result save(){
        ApiDubbo apiDubbo1 = apiService.save();
        return Result.javaYhInsertSuccess(apiDubbo1);
    }

    @GetMapping(value = "ip/{prot}")
    public Result findProt(@PathVariable String prot){
        return Result.javaYhInsertSuccess(apiService.findProt(prot));
    }
}
```

启动类
```
@EnableDubbo  //也可以在配置文件内配置，生产者、消费者都需要配置
@SpringBootApplication
public class ServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServerApplication.class, args);
    }

}
```
#### 服务访问
![在这里插入图片描述](https://img-blog.csdnimg.cn/2019071213190743.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3dlaXhpbl8zODkzNzg0MA==,size_16,color_FFFFFF,t_70)
此时服务已经调通
## Dubbo-Admin
启动admin后可以观察到，我们现在的服务，说明配置正确
![在这里插入图片描述](https://img-blog.csdnimg.cn/2019071213221377.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3dlaXhpbl8zODkzNzg0MA==,size_16,color_FFFFFF,t_70)
#### 安装Admin方式：
#### 方式一
最新的Admin采用的是前后端分离技术，需要单独部署
##### 生产环境配置

1. 下载代码: `git clone https://github.com/apache/dubbo-admin.git`
2. 在 `dubbo-admin-server/src/main/resources/application.properties`中指定注册中心地址
3. 构建

    > - `mvn clean package`  
4. 启动 
   * `mvn --projects dubbo-admin-server spring-boot:run`   
   或者   
   * `cd dubbo-admin-distribution/target; java -jar dubbo-admin-0.1.jar`
5. 访问 `http://localhost:8080`
---

##### 开发环境配置
* 运行`dubbo admin server`
   `dubbo admin server`是一个标准的spring boot项目, 可以在任何java IDE中运行它
* 运行`dubbo admin ui`
  `dubbo admin ui`由npm管理和构建，在开发环境中，可以单独运行: `npm run dev`
* 页面访问
  访问 `http://localhost:8081`, 由于前后端分开部署，前端支持热加载，任何页面的修改都可以实时反馈，不需要重启应用。
 * 跨域问题
    为了方便开发，我们提供了这种前后端分离的部署模式，主要的好处是支持前端热部署，在这种模式下，前端会通过8080端口访问后端的restful api接口，获取数据, 这将导致跨域访问的问题。因此我们在`dubbo-admin-ui/config/index.js`添加了支持跨域访问的配置,当前端通过`npm run dev`单独启动时，这些配置将被激活，允许跨域访问

##### Swagger 支持
部署完成后，可以访问 http://localhost:8080/swagger-ui.html 来查看所有的restful api
#### 方式二
方式二安装简单，前后端没有分离，直接启动即可
* 步骤1：
git clone https://github.com/apache/incubator-dubbo-ops
* 第2步：
cd incubator-dubbo-ops && mvn package

这里只需要将Admin的配置内的ZK改成自己的即可
```
server.port=7001
spring.velocity.cache=false
spring.velocity.charset=UTF-8
spring.velocity.layout-url=/templates/default.vm
spring.messages.fallback-to-system-locale=false
spring.messages.basename=i18n/message
spring.root.password=root
spring.guest.password=guest

dubbo.registry.address=zookeeper://127.0.0.1:2181

```
希望对大家有所帮助，欢迎点赞转发！
