package com.javayh.config;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.repository.query.MongoEntityInformation;
import org.springframework.data.mongodb.repository.support.SimpleMongoRepository;

import java.io.Serializable;
import java.util.List;

/**
 * @author Dylan Yang
 * @Description: 自定义接口实现
 * @Title: SimpleBaseMongoRepository
 * @ProjectName javayh-cloud
 * @date 2019/7/21 10:46
 */
public class SimpleBaseMongoRepository<T, ID extends Serializable> extends SimpleMongoRepository<T, ID> implements BaseMongoRepository<T, ID> {

    private final MongoOperations mongoOperations;

    private final MongoEntityInformation<T, ID> entityInformation;

    public SimpleBaseMongoRepository(MongoEntityInformation<T, ID> metadata, MongoOperations mongoOperations) {
        super(metadata, mongoOperations);
        this.mongoOperations = mongoOperations;
        this.entityInformation = metadata;
    }

    protected Class<T> getEntityClass() {
        return entityInformation.getJavaType();
    }

    @Override
    public Page<T> findPageByQuery(Query query, Pageable pageable) {
        long total = mongoOperations.count(query, getEntityClass());
        List<T> list = mongoOperations.find(query.with(pageable), getEntityClass());

        return new PageImpl<T>(list, pageable, total);
    }

    @Override
    public Page<T> findPageByCriteria(Criteria criteria, Pageable pageable) {
        return findPageByQuery(new Query(criteria), pageable);
    }
}

