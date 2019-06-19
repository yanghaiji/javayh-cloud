package com.javayh.controller;

import com.javayh.entity.EsEntiy;
import com.javayh.entity.Result;
import com.javayh.service.EsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author Dylan Yang
 * @Description: EsController
 * @Title: EsController
 * @ProjectName javayh-oauth2
 * @date 2019/6/15 16:01
 */
@RestController
@RequestMapping(value = "/javayh/es/")
public class EsController {
    @Autowired
    private EsService esService;

    /**
     * 保存
     * @param esEntiy
     * @return
     */
    @PostMapping(value = "save")
    public Result save(@RequestBody EsEntiy esEntiy){
        return Result.javaYhInsertSuccess(esService.save(esEntiy));
    }

    /**
     * 查询
     * @param code
     * @return
     */
    @GetMapping(value = "findCode/{code}")
    public Result findbyEs(@PathVariable String code){
        return Result.javaYhQuerySuccess(esService.findbyEs(code));
    }
}

