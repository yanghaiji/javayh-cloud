package com.javayh.config;

import com.dangdang.ddframe.job.event.JobEventConfiguration;
import com.dangdang.ddframe.job.event.rdb.JobEventRdbConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;
import javax.sql.DataSource;

/**
 * @author Dylan Yang
 * @Description: 数据源配置,进行数据持久化
 * @Title: JobConfig
 * @ProjectName javayh-oauth2
 * @date 2019/7/3 0:06
 */
@Configuration
public class JobTaskConfig {
    @Resource
    private DataSource dataSource;

    @Bean
    public JobEventConfiguration jobEventConfiguration() {
        return new JobEventRdbConfiguration(dataSource);
    }
}

