package com.javayh.service;

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
public class SysMenuService  extends BaseService {
    @Autowired
    private SysMeunMapper sysMeunMapper;

    /**
     * 查询所有菜单
     * @return
     */
    public List<SysMenu> query(){
        return sysMeunMapper.selectAll();
    }
}

