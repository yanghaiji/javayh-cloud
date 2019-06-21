package com.javayh.api;

import com.javayh.entity.Result;
import com.javayh.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Dylan Yang
 * @Description: RedisServerApi
 * @Title: RedisServerApi
 * @ProjectName javayh-oauth2
 * @date 2019/6/21 22:07
 */
@RestController
@RequestMapping(value = "/javayh/redis/")
public class RedisServerCtr {

    @Autowired
    private RedisUtil redisUtil;

    /**
     * 普通获取
     * @param key
     * @return
     */
    @PostMapping(value = "get")
    public Object get(@RequestParam(value = "key") String key){
        return redisUtil.get(key);
    }

    /**
     * 普通存入
     * @param key
     * @param object
     * @return
     */
    @PostMapping(value = "setString")
    public Result set(String key,Object object){
        return Result.javaYhInsertSuccess(redisUtil.set(key,object));
    }

    /**
     * 存入list
     * @param key
     * @param object
     * @return
     */
    @PostMapping(value = "setList")
    public Result setList(String key, List<Object> object){
        return Result.javaYhInsertSuccess(redisUtil.lSet(key,object));
    }

}

