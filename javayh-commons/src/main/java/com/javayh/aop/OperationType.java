package com.javayh.aop;

/**
 * @author Dylan Yang
 * @Description: OperationType
 * @Title: OperationType
 * @ProjectName javayh-oauth2
 * @date 2019/6/17 18:11
 */
public enum  OperationType {
    /**
     * 操作类型
     */
    UNKNOWN("unknown"),
    DELETE("delete"),
    SELECT("select"),
    UPDATE("update"),
    INSERT("insert");

    private String value;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    OperationType(String s) {
        this.value = s;
    }
}

