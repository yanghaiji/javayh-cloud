package com.javayh.service;

import com.javayh.entity.EsEntiy;
import com.javayh.entity.EsHighlight;
import com.javayh.exception.BaseException;
import com.javayh.id.UuidUtils;
import com.javayh.repository.EsRepository;
import org.apache.commons.lang3.StringUtils;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.index.query.DisMaxQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.SearchResultMapper;
import org.springframework.data.elasticsearch.core.aggregation.AggregatedPage;
import org.springframework.data.elasticsearch.core.aggregation.impl.AggregatedPageImpl;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
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

    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;

    /**
     * 保存
     * @param esEntiy
     * @return
     */
    public EsEntiy save(EsEntiy esEntiy){
        if (StringUtils.isEmpty(esEntiy.getEsCode()))
            throw new BaseException("eslasticsearch code is null");
        esEntiy.setEsId(UuidUtils.getUuid());
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

    /**
     * 删除
     * @param id
     * @return
     */
    public int delete(String id){
        if(StringUtils.isEmpty(id))
            esRepository.deleteById(id);
        EsEntiy esEntiy = esRepository.findById(id).get();
        return  esEntiy == null ? 0: 1 ;
    }

    /**
     * matchQuery       : 单个字段查询
     * matchAllQuery    : 匹配所有
     * multiMatchQuery  : 多个字段匹配某一个值
     * wildcardQuery    : 模糊查询
     * boost            : 设置权重,数值越大权重越大
     * 混合搜索
     * @param content
     * @return
     */
    public Page<EsEntiy> querySearch(String content){
        DisMaxQueryBuilder disMaxQueryBuilder = QueryBuilders.disMaxQuery();
        QueryBuilder ikTypeQuery = QueryBuilders.matchQuery("esType", content).boost(2f);
        QueryBuilder pinyinTypeQuery = QueryBuilders.matchQuery("esType.pinyin", content);
        QueryBuilder ikCodeQuery = QueryBuilders.matchQuery("esCode", content).boost(2f);
        QueryBuilder pinyinCodeQuery = QueryBuilders.matchQuery("esCode.pinyin", content);
        QueryBuilder wildcardCodeQuery = QueryBuilders.wildcardQuery("esCode", content);
        QueryBuilder multiCodeQuery = QueryBuilders.multiMatchQuery(content,"esType","esCode");
        disMaxQueryBuilder.add(ikTypeQuery);
        disMaxQueryBuilder.add(pinyinTypeQuery);
        disMaxQueryBuilder.add(ikCodeQuery);
        disMaxQueryBuilder.add(pinyinCodeQuery);
        disMaxQueryBuilder.add(wildcardCodeQuery);
        disMaxQueryBuilder.add(multiCodeQuery);
        SearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withQuery(disMaxQueryBuilder).build();
        Page<EsEntiy> search = esRepository.search(searchQuery);
        return search;
    }

    /**
     * 高亮检索
     * @param type
     * @return
     */
    public AggregatedPage<EsEntiy> querySearchType(String type){
        DisMaxQueryBuilder disMaxQueryBuilder = QueryBuilders.disMaxQuery();
        QueryBuilder ikTypeQuery = QueryBuilders.wildcardQuery("esType", type).boost(2f);
        QueryBuilder ikCodeQuery = QueryBuilders.wildcardQuery("esCode", type).boost(2f);
        List<String> highlightFields = new ArrayList<String>();
        highlightFields.add("esType");
        highlightFields.add("esCode");
        HighlightBuilder.Field[] fields = new HighlightBuilder.Field[highlightFields.size()];
        for (int x = 0; x < highlightFields.size(); x++) {
            fields[x] = new HighlightBuilder.Field(highlightFields.get(x)).preTags(EsHighlight.HIGH_LIGHT_START_TAG)
                    .postTags(EsHighlight.HIGH_LIGHT_END_TAG);
        }
        disMaxQueryBuilder.add(ikTypeQuery);
        disMaxQueryBuilder.add(ikCodeQuery);
        SearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withQuery(disMaxQueryBuilder)
                .withHighlightFields(fields)
                //.withPageable(PageRequest.of(1, 10))
                .build();
        //不需要高亮就直接分页返回
        //Page<EsEntiy> esEntiys = esRepository.search(searchQuery);
        //高亮显示
        AggregatedPage<EsEntiy> esEntiys = elasticsearchTemplate.queryForPage(searchQuery, EsEntiy.class, new SearchResultMapper() {
            @Override
            public <T> AggregatedPage<T> mapResults(SearchResponse searchResponse, Class<T> aClass, Pageable pageable) {
                pageable = PageRequest.of(1, 10);
                List<EsEntiy> chunk = new ArrayList<>();
                for (SearchHit searchHit : searchResponse.getHits()) {
                    if (searchResponse.getHits().getHits().length <= 0) {
                        return null;
                    }
                    EsEntiy esEntiy = new EsEntiy();
                    esEntiy.setEsId(searchHit.getId());
                    esEntiy.setEsIndex(searchHit.getIndex());
                    //name or memoe
                    HighlightField code = searchHit.getHighlightFields().get("esCode");
                    if (code != null) {
                        esEntiy.setEsCode(code.fragments()[0].toString());
                    } else {
                        Object esCode = searchHit.getSourceAsMap().get("esCode");
                        if(esCode == null)
                            esEntiy.setEsCode("");
                        else
                            esEntiy.setEsCode(esCode.toString());
                    }
                    HighlightField type = searchHit.getHighlightFields().get("esType");
                    if (type != null) {
                        esEntiy.setEsType(type.fragments()[0].toString());
                    }else {
                        Object esType = searchHit.getSourceAsMap().get("esType");
                        if(esType == null )
                            esEntiy.setEsType("");
                        else
                            esEntiy.setEsType(esType.toString());
                    }
                    chunk.add(esEntiy);
                }
                if (chunk.size() > 0) {
                    return  new AggregatedPageImpl<T>((List<T>) chunk);
                }
                return null;
            }
        });
        return esEntiys;
    }

}

