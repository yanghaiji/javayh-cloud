package com.javayh.vo;

import com.javayh.entity.Pages;
import lombok.Data;

/**
 * @author Dylan Yang
 * @Description: SysMenuVO
 * @Title: SysMenuVO
 * @ProjectName javayh-oauth2
 * @date 2019/6/18 15:30
 */
@Data
public class SysMenuVO extends Pages {
    private String id;
    private String code;
    private String pcode;
}

