package com.javayh.exception;

import lombok.Data;

/**
 * @author Dylan Yang
 * @Description: BaseException
 * @Title: BaseException
 * @ProjectName javayh-oauth2
 * @date 2019/5/20 19:59
 */
@Data
public class BaseException extends RuntimeException {
    private String massage;
    private Object object;

    public BaseException(String massage){

    }
    public BaseException(String massage,Object object){

    }
}

