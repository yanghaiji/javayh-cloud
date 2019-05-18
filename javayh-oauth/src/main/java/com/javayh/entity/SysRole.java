package com.javayh.entity;

import lombok.Data;

import java.util.Set;

/**
 * @author Dylan Yang
 * @Description: TODO
 * @Title: SysRole
 * @ProjectName javayh-oauth2
 * @date 2019/5/18 15:11
 */
@Data
public class SysRole {
    private int id;
    private String roleName;
    private String valid;
    private Set<SysPermission> sysPermissions;
}

