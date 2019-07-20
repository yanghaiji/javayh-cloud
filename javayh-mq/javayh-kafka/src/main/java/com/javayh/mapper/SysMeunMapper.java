package com.javayh.mapper;

import com.javayh.entity.SysMenu;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author Dylan Yang
 * @Description: SysMeunMapper
 * @Title: SysMeunMapper
 * @ProjectName javayh-oauth2
 * @date 2019/5/19 18:21
 */
@Mapper
public interface SysMeunMapper{

    int modifyById(SysMenu sysMenu);
}
