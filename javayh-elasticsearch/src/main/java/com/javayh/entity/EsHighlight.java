package com.javayh.entity;

/**
 * @author Dylan Yang
 * @Description: EsHighlight
 * @Title: EsHighlight
 * @ProjectName javayh-oauth2
 * @date 2019/6/24 20:12
 */
public interface EsHighlight {
    /**
     * 高亮显示 - 开始标签
     */
    String HIGH_LIGHT_START_TAG = "<javayh>";

    /**
     * 高亮显示 - 结束标签
     */
    String HIGH_LIGHT_END_TAG = "</javayh>";

    /**
     * 索引名称
     */
    class INDEX_NAME {
        /**
         * 游记
         */
        public static final String TRAVEL = "travel";
    }
}
