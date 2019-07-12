# javayh-cloud
#### 本代码仅作为学习使用，如有错误还请多多包涵，大家可以将自己学习的代码进行上传，可以联系小编:372787553进行微信沟通

## 项目层次介绍
#### 1.版本
`本示例基于SpringBoot2.0.2 SpringCloud Finchley.SR1进行开发学习`
#### 2.模块介绍
##### `<!--父工程--> javayh-cloud`
##### `<!--持久层封装--> javayh-mybatis`
##### `<!--工具类--> javayh-commons`
##### `<!--dubbo--> javayh-dubbo`
##### `<!--公用类--> javayh-dubbo-api`
##### `<!--消费者--> javayh-dubbo-client`
##### `<!--生产者--> javayh-dubbo-server`
##### `<!--监控台--> javayh-dubbo-admin`
##### `<!--监控台启动类--> dubbo-admin`
##### `<!--监控台--> dubbo-monitor-simple`
##### `<!--监控台--> dubbo-registry-simple`
#### 3.模块间关系
##### 3.1 dubbo+dubbo-admin+zk+boot分布式治理
#### `需启动：`
##### `<!--生产者--> javayh-dubbo-server`
##### `<!--消费者--> javayh-dubbo-client`
##### `<!--监控台--> javayh-dubbo-admin`
##### `<!--监控台启动类--> javayh-dubbo-admin/dubbo-admin`
#####  监控台服务启动后访问http://localhost:7001 
#####  name:root/guest  pwd:root/guest 二选一
##### 如果一直报错，请调大超时时间，根据个人电脑配置有关，找不到api，则需要利用maven install
#### 3.2 其他
#### `学习微服务相关知识，请切换至master分支上`




### 遇到其他问题可以联系小编；微信：372787553