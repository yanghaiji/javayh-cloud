package com.javayh.config;

import com.dangdang.ddframe.job.reg.zookeeper.ZookeeperConfiguration;
import com.dangdang.ddframe.job.reg.zookeeper.ZookeeperRegistryCenter;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Dylan Yang
 * @Description: ZK连接配置
 * @Title: RegistryCenterConfig
 * @ProjectName javayh-oauth2
 * @date 2019/7/3 8:38
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "javayh.elasticjob.zk-config")
public class RegistryCenterConfig {

    /**
     * 连接Zookeeper服务器的列表.
     * 包括IP地址和端口号. 多个地址用逗号分隔. 如: host1:2181,host2:2181
     */
    private String serverLists;

    /**
     * 命名空间.
     */
    private String namespace;

    /**
     * 等待重试的间隔时间的初始值. 单位毫秒.
     */
    private int baseSleepTimeMilliseconds;

    /**
     * 等待重试的间隔时间的最大值. 单位毫秒.
     */
    private int maxSleepTimeMilliseconds;

    /**
     * 最大重试次数.
     */
    private int maxRetries;

    /**
     * 登录权限
     */
    private String digest;

    @Bean(initMethod = "init")
    public ZookeeperRegistryCenter zookeeperRegistryCenter() {
        ZookeeperConfiguration zookeeperConfiguration = new ZookeeperConfiguration(serverLists, namespace);
        zookeeperConfiguration.setMaxRetries(maxRetries);
        zookeeperConfiguration.setBaseSleepTimeMilliseconds(baseSleepTimeMilliseconds);
        zookeeperConfiguration.setMaxSleepTimeMilliseconds(maxSleepTimeMilliseconds);
        zookeeperConfiguration.setDigest(digest);
        return new ZookeeperRegistryCenter(zookeeperConfiguration);
    }

}

