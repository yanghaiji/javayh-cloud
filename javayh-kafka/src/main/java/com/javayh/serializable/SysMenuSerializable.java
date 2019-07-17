package com.javayh.serializable;

import com.alibaba.fastjson.JSON;
import com.javayh.entity.SysMenu;
import org.apache.kafka.common.serialization.Serializer;


import java.util.Map;

/**
 * @author Dylan Yang
 * @Description: SysMenuSerializable
 * @Title: SysMenuSerializable
 * @ProjectName javayh-cloud
 * @date 2019/7/17 0:57
 */
public class SysMenuSerializable implements Serializer<SysMenu> {
    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {

    }

    @Override
    public byte[] serialize(String s, SysMenu sysMenu) {
        return JSON.toJSONBytes(sysMenu);
    }

    @Override
    public void close() {

    }
}

