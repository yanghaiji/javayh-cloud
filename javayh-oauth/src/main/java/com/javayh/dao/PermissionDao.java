package com.javayh.dao;

import com.javayh.entity.Permission;

import java.util.List;

/**
 * @author Yang HaiJi, 2019-05-17
 * @version Araf v1.0
 */
public interface PermissionDao {
    /**
     * 根据角色id查找权限列表
     * @param roleId 角色id
     * @return 权限列表
     */
    List<Permission> findByRoleId(Integer roleId);
}
