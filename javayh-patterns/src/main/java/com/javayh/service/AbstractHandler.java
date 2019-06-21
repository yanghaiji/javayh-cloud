package com.javayh.service;

import com.javayh.vo.SysUser;

/**
 * @author Dylan Yang
 * @Description: AbstractHandler
 * @Title: AbstractHandler
 * @ProjectName javayh-oauth2
 * @date 2019/6/21 16:59
 */
public interface AbstractHandler {

    /**
     * 策略接口
     * @param userName
     * @return
     */
    SysUser handle(String userName);

}

