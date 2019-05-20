package com.javayh.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.javayh.entity.Result;
import com.javayh.entity.ResultCode;
import com.javayh.entity.SysMenu;
import com.javayh.service.SysMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Dylan Yang
 * @Description: TODO
 * @Title: SysMenuController
 * @ProjectName javayh-oauth2
 * @date 2019/5/19 21:21
 */
@RestController
@RequestMapping(value = "/javayh/thread/")
public class SysMenuController {

    @Autowired
    private SysMenuService sysMenuService;

    @GetMapping(value = "query")
    public Result query(){
        Result result = new Result();
        PageHelper.startPage(1,10);
//        List<SysMenu> query = sysMenuService.query();
        List<SysMenu> all = sysMenuService.findAll();
        PageInfo<SysMenu> sysMenuPageInfo = new PageInfo<>(all);
        result.setCode(ResultCode.SUCCESS.getCode());
        result.setMessage("查询成功");
        result.setData(sysMenuPageInfo);
        return result;
    }

    @GetMapping(value = "tree")
    public void getTree(){

    }
}

