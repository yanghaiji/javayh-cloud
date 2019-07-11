package com.javayh.entity;

import lombok.Data;
import org.hibernate.validator.internal.constraintvalidators.bv.size.SizeValidatorForArraysOfDouble;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;

/**
 * @author Dylan Yang
 * @Description: ApiDubbo
 * @Title: ApiDubbo
 * @ProjectName javayh-oauth2
 * @date 2019/7/11 16:44
 */
@Data
public class ApiDubbo implements Serializable {
    private String apiName;
    private String apiType;
    private String apiUrl;

    public ApiDubbo() {
    }

    public ApiDubbo(String apiName, String apiType, String apiUrl) {
        this.apiName = apiName;
        this.apiType = apiType;
        this.apiUrl = apiUrl;
    }

    public static class ApiDubboBuilder{
        private String apiName;
        private String apiType;
        private String apiUrl;

        public ApiDubboBuilder setApiName(String apiName) {
            this.apiName = apiName;
            return this;
        }

        public ApiDubboBuilder setApiType(String apiType) {
            this.apiType = apiType;
            return this;
        }

        public ApiDubboBuilder setApiUrl(String apiUrl) {
            this.apiUrl = apiUrl;
            return this;
        }

        public ApiDubbo build(){
            return new ApiDubbo(apiName,apiType,apiUrl);
        }
        public ApiDubbo buildTwo(){
            return new ApiDubbo();
        }
    }
}

