package com.javayh.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.extension.toolkit.SqlHelper;
import com.javayh.entity.SysRole;
import com.javayh.exception.BaseException;
import com.javayh.mapper.SysRoleMapper;
import com.javayh.service.ISysRoleService;
import com.javayh.vo.SysRoleVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 角色表 服务实现类
 * </p>
 *
 * @author Dylan Yang
 * @since 2019-06-18
 */
@Slf4j
@Service
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements ISysRoleService {

    @Autowired
    private SysRoleMapper sysRoleMapper;

    @Override
    public IPage<SysRoleVO> queryPage(Page<SysRoleVO> sysRolePage, SysRoleVO vo) {
        return sysRoleMapper.queryPage(sysRolePage,vo);
    }

    @Override
    public IPage<SysRole> queryPageThree(SysRoleVO vo) {
        //where
        QueryWrapper wrapper = new QueryWrapper<>();
        wrapper.like("num",vo.getNum());
        //等于
        wrapper.eq("num",vo.getNum());
        //不等于
        wrapper.ne("num","2");
        //排序
        wrapper.orderByDesc("pid","name");
        //包含 1,2,3,4,5,6,7,8
        wrapper.inSql("num","1,2,3,4,5,6,7,8");
        //分页
        Page page = new Page(vo.getPageCurrent(),vo.getPageSize());
        return sysRoleMapper.selectPage(page,wrapper);
    }

    @Override
    public SysRole saveAll(SysRole sysRole) {
        int insert = sysRoleMapper.insert(sysRole);
        if (insert > 0){
            return sysRoleMapper.selectById(sysRole.getId());
        }else {
            throw new BaseException("save is falid");
        }
    }


}
