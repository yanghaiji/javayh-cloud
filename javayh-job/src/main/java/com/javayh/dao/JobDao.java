package com.javayh.dao;

import com.javayh.entity.JobConfig;
import com.javayh.mybatis.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author Dylan Yang
 * @Description: JobDao
 * @Title: JobDao
 * @ProjectName javayh-oauth2
 * @date 2019/7/3 9:24
 */
@Mapper
public interface JobDao extends BaseMapper<JobConfig> {

}

