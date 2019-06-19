package com.javayh.controller;


import com.baomidou.mybatisplus.core.conditions.AbstractWrapper;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.javayh.entity.Result;
import com.javayh.entity.SysRole;
import com.javayh.service.ISysRoleService;
import com.javayh.vo.SysRoleVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * <p>
 * 角色表 前端控制器
 * </p>
 *
 * @author Dylan Yang
 * @since 2019-06-18
 */
@RestController
@RequestMapping("/sysRole/")
public class SysRoleController {

    @Autowired
    private ISysRoleService iSysRoleService;

    /**
     * 自定义SQL
     * @param sysRolePage
     * @param vo
     * @return
     */
    @GetMapping(value = "queryPageOne")
    public Result queryPageOne(Page<SysRoleVO> sysRolePage,SysRoleVO vo){
        vo.setPid(1);
        IPage<SysRoleVO> list = iSysRoleService.queryPage(sysRolePage,vo);
        return Result.javaYhQuerySuccess(list);
    }

    /**
     * service直接调用page
     * @param sysRolePage
     * @param vo
     * @return
     */
    @GetMapping(value = "queryPageTwo")
    public Result queryPageTwo(Page<SysRole> sysRolePage,SysRoleVO vo){
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.like("num",vo.getNum());
        return Result.javaYhQuerySuccess(iSysRoleService.page(sysRolePage,wrapper));
    }

    /**
     * 调用mapper实现
     * @param vo
     * @return
     */
    @PostMapping(value = "queryPageThree")
    public Result queryPageThree(@RequestBody SysRoleVO vo){
        return Result.javaYhQuerySuccess(iSysRoleService.queryPageThree(vo));
    }

    /**
     * 新增
     * @param sysRole
     * @return
     */
    @PostMapping(value = "save")
    public Result save(@RequestBody @Valid SysRole sysRole){
        return Result.javaYhInsertSuccess(iSysRoleService.saveAll(sysRole));
    }
}

