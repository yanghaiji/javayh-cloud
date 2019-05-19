package com.javayh.mapper;

import com.javayh.entity.SysRole;
import org.apache.ibatis.annotations.Mapper;

import java.util.Set;

/**
 * @author Dylan Yang
 * @Description: TODO
 * @Title: RoleDao
 * @ProjectName javayh-oauth2
 * @date 2019/5/18 16:56
 */
@Mapper
public interface RoleDao {
    /**
     * 查询角色
     * @param userId
     * @return
     */
    Set<SysRole> findByUserId(Integer userId);
}

