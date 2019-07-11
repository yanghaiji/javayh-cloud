package com.javayh.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.javayh.entity.ApiDubbo;
/**
 * @author Dylan Yang
 * @Description: ApiServer
 * @Title: ApiServer
 * @ProjectName javayh-oauth2
 * @date 2019/7/11 17:15
 */
@Service(version = "javayh-0.1",interfaceClass = ApiService.class)
public class ApiServer implements ApiService{

    @Override
    public ApiDubbo save() {
        ApiDubbo apiDubbo = new ApiDubbo.ApiDubboBuilder().
                setApiName("javayh-dubbo-api").
                setApiType("AipDubbo.class").
                setApiUrl("javayh/dubbo/api/save").build();
        return apiDubbo;
    }

    @Override
    public String findProt(String prot) {
        return prot;
    }
}

