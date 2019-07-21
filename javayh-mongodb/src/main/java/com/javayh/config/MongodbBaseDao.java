package com.javayh.config;

import java.io.Serializable;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

/**
 * @author Dylan Yang
 * @Description: MongodbBaseDao 基础封装
 * @Title: MongodbBaseDao
 * @ProjectName javayh-cloud
 * @date 2019/7/21 17:13
 */
public abstract class MongodbBaseDao<T extends Serializable>{

    /**
     * spring mongodb　集成操作类　
     */
    @Autowired
    private MongoTemplate mongoTemplate;

    /**
     * 通过条件查询实体(集合)
     *
     * @param query
     */
    public List<T> find(Query query) {
        return mongoTemplate.find(query,this.getEntityClass());
    }

    /**
     * 通过一定的条件查询一个实体
     *
     * @param query
     * @return
     */
    public T findOne(Query query) {
        return mongoTemplate.findOne(query, this.getEntityClass());
    }

    /**
     * 通过条件查询更新数据
     *
     * @param query
     * @param update
     * @return
     */
    public void update(Query query, Update update) {
        mongoTemplate.upsert(query, update, this.getEntityClass());
    }

    /**
     * 保存一个对象到mongodb
     *
     * @param bean
     * @return
     */
    public T save(T bean) {
        mongoTemplate.save(bean);
        return bean;
    }

    /**
     * 通过ID获取记录
     *
     * @param id
     * @return
     */
    public T get(String id) {
        return mongoTemplate.findById(id, this.getEntityClass());
    }

    /**
     * 通过ID获取记录,并且指定了集合名(表的意思)
     *
     * @param id
     * @param collectionName
     *            集合名
     * @return
     */
    public T get(String id, String collectionName) {
        return mongoTemplate.findById(id, this.getEntityClass(), collectionName);
    }

    /**
     * 获取需要操作的实体类class
     *
     * @return
     */
    protected abstract Class<T> getEntityClass();


}

