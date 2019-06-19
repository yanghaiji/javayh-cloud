package com.javayh.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import java.io.Serializable;

/**
 * @author Dylan Yang
 * @Description: EsEntiy
 * @Title: EsEntiy
 * @ProjectName javayh-oauth2
 * @date 2019/6/15 15:46
 */
@Data
@Document(indexName = "javayh",type = "sys_es")
public class EsEntiy implements Serializable {
    @Id
    private String esId;
    private String esIndex;
    private String esType;
    private String esCode;
}

