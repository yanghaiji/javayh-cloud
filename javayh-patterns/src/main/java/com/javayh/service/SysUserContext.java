package com.javayh.service;

import com.javayh.vo.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Dylan Yang
 * @Description: SysUserContext
 * @Title: SysUserContext
 * @ProjectName javayh-oauth2
 * @date 2019/6/21 17:12
 */
@Service
public class SysUserContext {

    /**
     * 通过Spring上下文实现自动管理,获取不同的ID进行匹配
     */
    @Autowired
    private final Map<String, AbstractHandler> strategyMap = new ConcurrentHashMap<>();

    @Autowired
    public SysUserContext(Map<String, AbstractHandler> strategyMap) {
        this.strategyMap.clear();
        strategyMap.forEach((k, v)-> this.strategyMap.put(k, v));
    }

    /**
     * 根据不同用户名查询
     * @param userName
     * @return
     */
    public SysUser getResource(String userName){
        return strategyMap.get(userName).handle(userName);
    }


}

