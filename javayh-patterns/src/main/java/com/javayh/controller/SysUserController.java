package com.javayh.controller;

import com.javayh.entity.Result;
import com.javayh.service.SysUserContext;
import com.javayh.vo.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Dylan Yang
 * @Description: SysUserController
 * @Title: SysUserController
 * @ProjectName javayh-oauth2
 * @date 2019/6/21 17:52
 */
@RestController
@RequestMapping(value = "/javayh/")
public class SysUserController {

    @Autowired
    private SysUserContext sysUserContext;

    /**
     * 根据用户名查询用户信息
     * @param userName
     * @return
     */
    @GetMapping(value = "query/{userName}")
    public Result query(@PathVariable String userName){
        SysUser resource = sysUserContext.getResource(userName);
        return Result.javaYhQuerySuccess(resource);
    }
}

