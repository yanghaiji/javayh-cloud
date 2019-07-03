package com.javayh.entity;

import lombok.Data;

import javax.persistence.Id;

/**
 * @author Dylan Yang
 * @Description: TODO
 * @Title: JobConfig
 * @ProjectName javayh-oauth2
 * @date 2019/7/3 9:36
 */
@Data
public class JobConfig {
    @Id
    private String id;
    private String jobName;
    private String cron;
    private Integer shardingTotalCount;
    private String shardingItemParameters;
    private String jobParameter;
    private String failover;
    private String misfire;
    private String description;
    private String jobClass;
    private String streamingProcess;
    private String jobConfig;
}

