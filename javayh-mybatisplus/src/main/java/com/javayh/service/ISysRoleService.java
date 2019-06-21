package com.javayh.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.javayh.entity.SysRole;
import com.javayh.vo.SysRoleVO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * <p>
 * 角色表 服务类
 * </p>
 *
 * @author Dylan Yang
 * @since 2019-06-18
 */
public interface ISysRoleService extends IService<SysRole> {

    IPage<SysRoleVO> queryPage(Page<SysRoleVO> sysRolePage, SysRoleVO vo);

    IPage<SysRole> queryPageThree(SysRoleVO vo);

    @PostMapping(value = "save")
    SysRole saveAll(@RequestBody SysRole sysRole);
}
