package com.javayh.mybatis.mapper;

import tk.mybatis.mapper.common.ConditionMapper;
import tk.mybatis.mapper.common.IdsMapper;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 * @author Dylan Yang
 * @Description: AbstractMapper
 * @Title: AbstractMapper
 * @ProjectName javayh-oauth2
 * @date 2019/5/20 19:34
 */
public interface AbstractMapper<T> extends Mapper<T>,
        MySqlMapper<T>,ConditionMapper<T>,IdsMapper<T> {
}

