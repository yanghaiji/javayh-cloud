package com.javayh.id;

import java.util.UUID;

/**
 * @author Dylan Yang
 * @Description: UuidUtils
 * @Title: UuidUtils
 * @ProjectName javayh-oauth2
 * @date 2019/5/20 20:14
 */
public class UuidUtils {

    public static String getUuid(){
        return UUID.randomUUID().toString().replaceAll("-", "");
    }
}

