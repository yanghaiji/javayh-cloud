package com.javayh.dao;

import com.javayh.entity.Role;

import java.util.List;

/**
 * @author Yang HaiJi, 2019-05-17
 * @version Araf v1.0
 */
public interface RoleDao {
    /**
     * 根据用户id查找角色列表
     * @param memberId 用户id
     * @return 角色列表
     */
    List<Role> findByMemberId(Integer memberId);
}
