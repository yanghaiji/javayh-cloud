package com.javayh.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.javayh.entity.SysRole;
import com.javayh.vo.SysRoleVO;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 角色表 Mapper 接口
 * </p>
 *
 * @author Dylan Yang
 * @since 2019-06-18
 */
public interface SysRoleMapper extends BaseMapper<SysRole> {

    IPage<SysRoleVO> queryPage(Page page, @Param("vo") SysRoleVO vo);
}
