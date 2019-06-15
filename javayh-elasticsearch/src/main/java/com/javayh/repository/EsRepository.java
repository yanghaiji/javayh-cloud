package com.javayh.repository;

import com.javayh.entity.EsEntiy;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Dylan Yang
 * @Description: EsRepository
 * @Title: EsRepository
 * @ProjectName javayh-oauth2
 * @date 2019/6/15 15:44
 */
@Repository
public interface EsRepository extends ElasticsearchRepository<EsEntiy,String> {
}

