package com.javayh.controller;

import com.javayh.entity.EsEntiy;
import com.javayh.exception.BaseException;
import com.javayh.service.EsService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

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
    public EsEntiy save(@RequestBody EsEntiy esEntiy){
        return esService.save(esEntiy);
    }

    /**
     * 查询
     * @param code
     * @return
     */
    @GetMapping(value = "findCode/{code}")
    public EsEntiy findbyEs(@PathVariable String code){
        return esService.findbyEs(code);
    }
}

