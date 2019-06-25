package com.javayh.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

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
    @Field(type = FieldType.Text)
    private String esIndex;
    @Field(type = FieldType.Text)
    private String esType;
    @Field(type = FieldType.Text)
    private String esCode;
}

