# javayh-oauth2
#### 本代码仅作为学习使用，如有错误还请多多包涵，大家可以将自己学习的代码进行上传，可以联系小编:372787553进行微信沟通

## 项目层次介绍
#### 1.版本
`本示例基于SpringBoot2.0.2 SpringCloud Finchley.SR1进行开发学习`
#### 2.模块介绍
##### `<!--父工程--> javayh-oauth2`
##### `<!--注册中心--> javayh-eureka`
##### `<!--权限注册服务--> javayh-oauth`
##### `<!--资源服务--> javayh-shop`
##### `<!--路由服务--> javayh-zuul` 
##### `<!--持久层封装--> javayh-mybatis`
##### `<!--工具类--> javayh-commons`
##### `<!--搜索引擎--> javayh-elasticsearch`
##### `<!--MybatisPlus--> javayh-mybatisplus`
##### `<!--设计模式--> javayh-patterns`
##### `<!--缓存服务--> javayh-redis`
##### ` <!--Feign--> javayh-feign`
##### `<!--NIO--> javayh-nio`
##### `<!--Admin--> javayh-admin`
##### `<!--Thread--> javayh-thread`
##### `<!--Activiti--> javayh-activiti`
##### `<!--Job--> javayh-job`
#### 3.模块间关系
##### 3.1 权限验证
具体流程查看:https://blog.csdn.net/weixin_38937840/article/details/90321037
#### `需启动：`
##### `<!--注册中心--> javayh-eureka`
##### `<!--权限注册服务--> javayh-oauth`
##### `<!--资源服务--> javayh-shop`
##### `<!--路由服务--> javayh-zuul` 
`需将代码路径下的sql文件导入`
##### 3.2 Admin系统管理
#### `需启动：`
##### `<!--注册中心--> javayh-eureka`
##### `<!--Admin--> javayh-admin`
##### ` <!--Feign--> javayh-feign`
`启动后在浏览器内输入:localhost:8012即可，name：admin  pwd：admin123`
##### 3.3 服务降解,Feign接口调用
#### `需启动：`
##### `<!--注册中心--> javayh-eureka`
##### ` <!--Feign--> javayh-feign`
##### `<!--MybatisPlus--> javayh-mybatisplus`
#### 3.4 其他
#### `其他模块可单独启动测试，看到控制台报错时，不要慌张，这时启动javayh-eureka即可`




### 遇到其他问题可以联系小编；微信：372787553