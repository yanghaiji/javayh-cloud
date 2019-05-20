package com.javayh.tree;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @author Dylan Yang
 * @Description: TODO
 * @Title: TreeNode
 * @ProjectName javayh-oauth2
 * @date 2019/5/20 21:27
 */
@Data
public class TreeNode {
    private String id;
    /**
     * 机构编码
     */
    private String officeCode;
    /**
     * 父级编号
     */
    private String parentCode;
    /**
     * 所有父级编号
     */
    private String parentCodes;
    /**
     * 本级排序号（升序）
     */
    private BigDecimal treeSort;
    /**
     * 所有级别排序号
     */
    private String treeSorts;
    /**
     * 是否最末级
     */
    private String treeLeaf;
    /**
     * 层次级别
     */
    private BigDecimal treeLevel;
    /**
     * 全节点名
     */
    private String treeNames;
    /**
     * 机构代码
     */
    private String viewCode;
    /**
     * 机构名称
     */
    private String officeName;
    /**
     * 机构全称
     */
    private String fullName;
    /**
     * 机构类型
     */
    private String officeType;
    /**
     * 负责人
     */
    private String leader;
    /**
     * 办公电话
     */
    private String phone;
    /**
     * 联系地址
     */
    private String address;
    /**
     * 邮政编码
     */
    private String zipCode;
    /**
     * 电子邮箱
     */
    private String email;
    /**
     * 状态（0正常 1删除 2停用）
     */
    private String status;
    /**
     * 创建者
     */
    private String createBy;
    /**
     * 创建时间
     */
    private Date createDate;
    /**
     * 更新者
     */
    private String updateBy;
    /**
     * 更新时间
     */
    private Date updateDate;
    /**
     * 备注信息
     */
    private String remarks;
    /**
     * 租户代码
     */
    private String corpCode;
    /**
     * 租户名称
     */
    private String corpName;

    private List<TreeNode> treeNodeList;

}