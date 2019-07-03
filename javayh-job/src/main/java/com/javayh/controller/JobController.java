package com.javayh.controller;

import com.javayh.entity.JobConfig;
import com.javayh.entity.Result;
import com.javayh.service.ElasticJobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Dylan Yang
 * @Description: JobController
 * @Title: JobController
 * @ProjectName javayh-oauth2
 * @date 2019/7/3 9:25
 */
@RestController
@RequestMapping(value = "/javayh/job/")
public class JobController {

    @Autowired
    private ElasticJobService elasticJobService;

    @GetMapping("queryAll")
    public Result getElasticJobConfigList() {
        List<JobConfig> list = elasticJobService.queryAll();
        return Result.javaYhQuerySuccess(list);
    }

}

