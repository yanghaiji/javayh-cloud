package com.javayh.service;

import com.javayh.entity.ApiDubbo;

/**
 * @author Dylan Yang
 * @Description: ApiService
 * @Title: ApiService
 * @ProjectName javayh-oauth2
 * @date 2019/7/11 16:52
 */
public interface  ApiService {

    ApiDubbo save();

    String findProt(String prot);

}

