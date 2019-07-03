package com.javayh.task;

import com.dangdang.ddframe.job.api.ElasticJob;
import com.dangdang.ddframe.job.api.dataflow.DataflowJob;
import com.dangdang.ddframe.job.api.simple.SimpleJob;
import com.dangdang.ddframe.job.config.JobCoreConfiguration;
import com.dangdang.ddframe.job.config.JobTypeConfiguration;
import com.dangdang.ddframe.job.config.dataflow.DataflowJobConfiguration;
import com.dangdang.ddframe.job.config.simple.SimpleJobConfiguration;
import com.dangdang.ddframe.job.event.JobEventConfiguration;
import com.dangdang.ddframe.job.lite.config.LiteJobConfiguration;
import com.dangdang.ddframe.job.lite.spring.api.SpringJobScheduler;
import com.dangdang.ddframe.job.reg.zookeeper.ZookeeperRegistryCenter;
import com.javayh.dao.JobDao;
import com.javayh.entity.JobConfig;
import com.javayh.util.BeanTools;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Dylan Yang
 * @Description: ElasticJobRunner
 * @Title: ElasticJobRunner
 * @ProjectName javayh-oauth2
 * @date 2019/7/3 10:57
 */
@Component
public class ElasticJobRunner implements CommandLineRunner {

    private Logger log = LoggerFactory.getLogger(ElasticJobRunner.class);

    private static final Map<String, String> JOB_CONFIG_MAP = new HashMap<>();

    @Resource
    private ZookeeperRegistryCenter zookeeperRegistryCenter;

    @Resource
    private JobEventConfiguration jobEventConfiguration;

    @Resource
    private JobDao elasticJobConfigDao;

    /**
     * 根据jobName获取配置
     *
     * @param jobName
     * @return
     */
    public static String getJobConfig(String jobName) {
        return JOB_CONFIG_MAP.get(jobName);
    }

    /**
     * 更改jobConfig
     *
     * @param jobName
     * @param jobConfig
     */
    public static void setJobConfig(String jobName, String jobConfig) {
        JOB_CONFIG_MAP.put(jobName, jobConfig);
    }

    /**
     * 注入任务
     *
     * @param jobConfig
     */
    @SuppressWarnings("unchecked")
    private void registryJob(JobConfig jobConfig) {
        try {
            Class<? extends ElasticJob> jobClass = (Class<? extends ElasticJob>) Class
                    .forName(jobConfig.getJobClass());
            ElasticJob elasticJob = getInstance(jobClass);
            SpringJobScheduler jobScheduler = jobScheduler(elasticJob, jobClass, jobConfig);
            jobScheduler.init();
            log.info("初始化定时任务 {} ", jobConfig.toString());
        } catch (Exception e) {
            log.error("注册Job出错：{} ", jobConfig.toString(), e);
        }

    }

    /**
     * 通过反射对有@Resource和@Autowired的属性赋值
     *
     * @param jobClass
     * @return
     * @throws InstantiationException
     * @throws IllegalAccessException
     */
    private ElasticJob getInstance(Class<? extends ElasticJob> jobClass)
            throws InstantiationException, IllegalAccessException {
        Field[] declaredFields = jobClass.getDeclaredFields();
        ElasticJob newInstance = jobClass.newInstance();
        for (Field field : declaredFields) {
            Annotation[] annotations = field.getAnnotations();
            if (annotations == null || annotations.length == 0) {
                continue;
            }
            boolean flag = false;
            for (Annotation annotation : annotations) {
                Class<? extends Annotation> annotationType = annotation.annotationType();
                if (annotationType.equals(Resource.class) || annotationType.equals(Autowired.class)) {
                    flag = true;
                    break;
                }
            }
            if (flag) {
                field.setAccessible(true);
                field.set(newInstance, BeanTools.getBean(field.getType()));
            }
        }
        return newInstance;
    }

    /**
     * 注册SpringJobScheduler
     *
     * @param elasticJob
     * @param jobClass
     * @param jobConfig
     * @return
     */
    private SpringJobScheduler jobScheduler(ElasticJob elasticJob, Class<? extends ElasticJob> jobClass,
                                            JobConfig jobConfig) {
        LiteJobConfiguration build = LiteJobConfiguration.newBuilder(jobConfiguration(elasticJob, jobConfig))
                .overwrite(true).build();
        SpringJobScheduler springJobScheduler = new SpringJobScheduler(elasticJob, zookeeperRegistryCenter, build,
                jobEventConfiguration);
        return springJobScheduler;
    }

    /**
     * job配置
     *
     * @param elasticJob
     * @param jobConfig
     * @return
     */
    private JobTypeConfiguration jobConfiguration(final ElasticJob elasticJob,
                                                  JobConfig jobConfig) {
        JobCoreConfiguration jobCoreConfiguration = JobCoreConfiguration
                .newBuilder(jobConfig.getJobName(), jobConfig.getCron(),
                        jobConfig.getShardingTotalCount())
                .shardingItemParameters(jobConfig.getShardingItemParameters())
                .misfire(Boolean.valueOf(jobConfig.getMisfire()))
                .description(jobConfig.getDescription())
                .failover(Boolean.valueOf(jobConfig.getFailover()))
                .jobParameter(jobConfig.getJobParameter())
                .build();
        if (elasticJob instanceof SimpleJob) {
            return new SimpleJobConfiguration(jobCoreConfiguration, elasticJob.getClass().getCanonicalName());
        }
        if (elasticJob instanceof DataflowJob) {
            return new DataflowJobConfiguration(jobCoreConfiguration, elasticJob.getClass().getCanonicalName(),
                    Boolean.valueOf(jobConfig.getStreamingProcess()));
        }
        throw new RuntimeException("未知类型定时任务：" + elasticJob.getClass().getName());
    }

    @Override
    public void run(String... args) throws Exception {
        List<JobConfig> elasticJobConfigList = elasticJobConfigDao.selectAll();
        if (elasticJobConfigList == null || elasticJobConfigList.size() == 0) {
            return;
        }
        elasticJobConfigList.forEach(elasticJobConfig -> {
            registryJob(elasticJobConfig);
            JOB_CONFIG_MAP.put(elasticJobConfig.getJobName(), elasticJobConfig.getJobConfig());
        });
    }
}

