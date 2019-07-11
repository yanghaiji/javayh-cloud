package com.javayh.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.javayh.entity.ApiDubbo;
import com.javayh.entity.Result;
import com.javayh.service.ApiService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Dylan Yang
 * @Description: AipController
 * @Title: AipController
 * @ProjectName javayh-oauth2
 * @date 2019/7/11 17:21
 */
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

