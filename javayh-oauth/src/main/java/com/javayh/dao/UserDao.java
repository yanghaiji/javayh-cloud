package com.javayh.dao;

import com.javayh.entity.SysUser;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author Yang HaiJi, 2019-05-17
 * @version Araf v1.0
 */
@Mapper
public interface UserDao {
    /**
     * 根据名字查询
     * @param memberName
     * @return
     */
    SysUser findByMemberName(String memberName);
}
