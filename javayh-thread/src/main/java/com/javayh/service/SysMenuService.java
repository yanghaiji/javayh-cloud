package com.javayh.service;

import com.javayh.aop.OperationType;
import com.javayh.aop.OperationUnit;
import com.javayh.aop.WebLogAspect;
import com.javayh.entity.SysMenu;
import com.javayh.mapper.SysMeunMapper;
import com.javayh.mybatis.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Dylan Yang
 * @Description: SysMenuService
 * @Title: SysMenuService
 * @ProjectName javayh-oauth2
 * @date 2019/5/19 21:22
 */
@Service
public class SysMenuService{
    @Autowired
    private SysMeunMapper sysMeunMapper;

    /**
     * 查询所有菜单
     * @return
     */
    @WebLogAspect(detail="查询所有菜单",level = 3,operationType =OperationType.SELECT,operationUnit = OperationUnit.UNKNOWN)
    public List<SysMenu> query(){
        return sysMeunMapper.selectAll();
    }
}

