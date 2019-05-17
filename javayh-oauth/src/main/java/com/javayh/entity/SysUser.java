package com.javayh.entity;

import lombok.Data;

import java.util.Date;
import java.util.Set;

/**
 * @author Yang HaiJi, 2019-05-17
 * @version Araf v1.0
 */
@Data
public class SysUser {
    private int id;
    private String memberName;
    private String password;
    private String mobile;
    private String email;
    private short sex;
    private Date birthday;
    private Date createTime;
    private Set<Role> roles;
}
