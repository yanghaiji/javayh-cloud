package com.javayh.entity;

import lombok.Data;

import java.util.Date;
import java.util.Set;

/**
 * @author Yang HaiJi, 2019-05-17
 * @version Araf v1.0
 */
@Data
public class Role {
    private int id;
    private String roleName;
    private short valid;
    private Date createTime;
    private Date updateTime;
    private Set<Permission> permissions;
}
