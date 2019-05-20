package com.javayh.entity;

import lombok.Data;

import javax.persistence.Id;

/**
 * @author Dylan Yang
 * @Description: SysMenu
 * @Title: SysMenu
 * @ProjectName javayh-oauth2
 * @date 2019/5/19 18:29
 */
@Data
public class SysMenu {
    @Id
    private String id;
    private String code;
    private String pcode;

}

