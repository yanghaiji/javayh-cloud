package com.javayh.service;

import com.javayh.entity.EsEntiy;
import com.javayh.exception.BaseException;
import com.javayh.repository.EsRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author Dylan Yang
 * @Description: EsService
 * @Title: EsService
 * @ProjectName javayh-oauth2
 * @date 2019/6/15 15:49
 */
@Service
public class EsService {

    @Autowired
    private EsRepository esRepository;

    /**
     * 保存
     * @param esEntiy
     * @return
     */
    public EsEntiy save(EsEntiy esEntiy){
        if (StringUtils.isEmpty(esEntiy.getEsCode()))
            throw new BaseException("eslasticsearch code is null");
        return esRepository.save(esEntiy);
    }

    /**
     * 查询
     * @param code
     * @return
     */
    public EsEntiy findbyEs(String code){
        Optional<EsEntiy> byId = esRepository.findById(code);
        return byId.get();
    }
}

