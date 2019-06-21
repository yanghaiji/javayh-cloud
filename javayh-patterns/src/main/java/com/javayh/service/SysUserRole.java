package com.javayh.service;

import com.javayh.vo.SysUser;
import org.springframework.stereotype.Component;

/**
 * @author Dylan Yang
 * @Description: SysUserRole
 * @Title: SysUserRole
 * @ProjectName javayh-oauth2
 * @date 2019/6/21 17:46
 */
@Component(value = "yhj")
public class SysUserRole implements AbstractHandler{

    @Override
    public SysUser handle(String userName) {
        SysUser sysUser = new SysUser.SysUserBuild().
                setRole("guset").
                setUserName("yhj").
                setPassWord("yhj").
                setUserType("select").buildTwo();
        return sysUser;
    }
}

