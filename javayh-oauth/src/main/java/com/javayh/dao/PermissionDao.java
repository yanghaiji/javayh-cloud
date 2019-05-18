package com.javayh.dao;

import com.javayh.entity.SysPermission;
import org.apache.ibatis.annotations.Mapper;

import javax.annotation.ManagedBean;
import java.util.Set;

/**
 * @author Dylan Yang
 * @Description: TODO
 * @Title: PermissionDao
 * @ProjectName javayh-oauth2
 * @date 2019/5/18 17:07
 */
@Mapper
public interface PermissionDao {

    Set<SysPermission> findByRoleId(Integer roleId);
}
