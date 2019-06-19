package com.javayh.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;

/**
 * <p>
 * 角色表
 * </p>
 *
 * @author Dylan Yang
 * @since 2019-06-18
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class SysRole implements Serializable {

private static final long serialVersionUID=1L;

    @TableId(type=IdType.AUTO)
    private Integer id;
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
    @NotBlank(message="is null")
    private String name;

    /**
     * 部门名称
     */
    private Integer deptid;

    /**
     * 提示
     */
    private String tips;

    /**
     * 保留字段(暂时没用）
     */
    private Integer version;


}
