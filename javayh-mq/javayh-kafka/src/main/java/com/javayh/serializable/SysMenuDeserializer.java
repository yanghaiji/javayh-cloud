package com.javayh.serializable;

import com.alibaba.fastjson.JSON;
import com.javayh.entity.SysMenu;
import org.apache.kafka.common.serialization.Deserializer;

import java.util.Map;

/**
 * @author Dylan Yang
 * @Description: SysMenuDeserializer
 * @Title: SysMenuDeserializer
 * @ProjectName javayh-cloud
 * @date 2019/7/17 1:01
 */
public class SysMenuDeserializer implements Deserializer<SysMenu> {
    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {

    }

    @Override
    public SysMenu deserialize(String topic, byte[] data) {
        return JSON.parseObject(data, SysMenu.class);
    }

    @Override
    public void close() {

    }
}

