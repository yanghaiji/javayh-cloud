package com.javayh.service;

import com.javayh.dao.JobDao;
import com.javayh.entity.JobConfig;
import com.javayh.mybatis.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Dylan Yang
 * @Description: ElasticJobService
 * @Title: ElasticJobService
 * @ProjectName javayh-oauth2
 * @date 2019/7/3 9:25
 */
@Service
public class ElasticJobService extends BaseService<JobConfig> {

    @Autowired
    private JobDao jobDao;

    /**
     * 查询所有
     * @return
     */
    public List<JobConfig> queryAll(){
        return jobDao.selectAll();
    }

}

