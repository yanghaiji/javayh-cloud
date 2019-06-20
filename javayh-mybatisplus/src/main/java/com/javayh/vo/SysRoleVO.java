package com.javayh.vo;

import com.javayh.entity.PageMp;
import lombok.Data;

/**
 * @author Dylan Yang
 * @Description: SysRoleVO
 * @Title: SysRoleVO
 * @ProjectName javayh-oauth2
 * @date 2019/6/18 21:42
 */
@Data
public class SysRoleVO extends PageMp {
    /**
     * 序号
     */
    private Integer num;

    /**
     * 父角色id
     */
    private Integer pid;

    /**
     * 角色名称
     */
    private String name;

}

