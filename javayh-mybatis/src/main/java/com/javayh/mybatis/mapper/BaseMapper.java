package com.javayh.mybatis.mapper;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 * @author Dylan Yang
 * @Description: BaseMapper
 * @Title: BaseMapper
 * @ProjectName javayh-oauth2
 * @date 2019/5/19 19:54
 */
public interface BaseMapper<T> extends Mapper<T>, MySqlMapper<T> {
}

