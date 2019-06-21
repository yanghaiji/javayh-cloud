package com.javayh.controller;

import com.javayh.entity.Result;
import com.javayh.feign.MpService;
import com.javayh.vo.SysRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author Dylan Yang
 * @Description: MpFeignController
 * @Title: MpFeignController
 * @ProjectName javayh-oauth2
 * @date 2019/6/22 0:38
 */
@RestController
@RequestMapping(value = "javayh/")
public class MpFeignController {

    @Autowired
    private MpService mpService;

    @PostMapping(value = "saveAll")
    public Result saveAll(@RequestBody SysRole sysRole){
        return Result.javaYhInsertSuccess(mpService.saveAll(sysRole).getData());
    }
}

