package com.javayh.entity;

import lombok.Data;

import java.util.Date;

/**
 * @author Yang HaiJi, 2019-05-17
 * @version Araf v1.0
 */
@Data
public class Permission {

    private int id;
    private String zuulPrefix;
    private String servicePrefix;
    private String method;
    private String uri;
    private Date createTime;
    private Date updateTime;
}

