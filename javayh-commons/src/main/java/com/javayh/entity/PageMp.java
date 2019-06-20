package com.javayh.entity;

import lombok.Data;

import java.util.List;

/**
 * @author Dylan Yang
 * @Description: PageMp
 * @Title: PageMp
 * @ProjectName javayh-oauth2
 * @date 2019/6/20 12:27
 */
@Data
public class PageMp {
    /**
     * 当前页
     * MP : pageCurrent
     */
    private long pageCurrent;
    /*条数*/
    private long pageSize;
    /*排序*/
    private String orderBy;
    private List<String> orderBys;
}

