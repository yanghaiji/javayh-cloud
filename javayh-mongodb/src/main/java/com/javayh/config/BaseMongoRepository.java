package com.javayh.config;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;

/**
 * @author Dylan Yang
 * @Description: 自定义接口
 * @Title: BaseMongoRepository
 * @ProjectName javayh-cloud
 * @date 2019/7/21 10:39
 */
@NoRepositoryBean
public interface BaseMongoRepository<T, ID extends Serializable>
        extends MongoRepository<T, ID> {

    /**
     * 自定义分页查询
     *
     * @param query
     * @param pageable
     * @return
     */
    Page<T> findPageByQuery(Query query, Pageable pageable);

    /**
     * 自定义分页查询
     *
     * @param criteria
     * @param pageable
     * @return
     */
    Page<T> findPageByCriteria(Criteria criteria, Pageable pageable);
}

