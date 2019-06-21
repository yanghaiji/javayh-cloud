package com.javayh.service;

import com.javayh.vo.SysUser;
import org.springframework.stereotype.Component;

/**
 * @author Dylan Yang
 * @Description: SysUserPermit
 * @Title: SysUserPermit
 * @ProjectName javayh-oauth2
 * @date 2019/6/21 17:43
 */
@Component(value = "dylan")
public class SysUserPermit implements AbstractHandler {

    @Override
    public SysUser handle(String userName) {
        SysUser sysUser = new SysUser.SysUserBuild().
                        setRole("admin").
                        setUserName("dylan").
                        setPassWord("dylan").
                        setUserType("select insert update delete").buildTwo();
        return sysUser;
    }

}

