package com.javayh.dao;

import com.javayh.entity.SysRole;
import com.javayh.vo.SysUserVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author Yang HaiJi, 2019-05-17
 * @version Araf v1.0
 */
@Mapper
public interface UserDao {
    /**
     * 根据名字查询
     * @param username
     * @return
     */
    SysUserVO findByUserName(String username);

}
