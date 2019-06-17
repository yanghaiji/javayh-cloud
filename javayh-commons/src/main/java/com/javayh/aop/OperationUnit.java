package com.javayh.aop;

/**
 * @author Dylan Yang
 * @Description: OperationUnit
 * @Title: OperationUnit
 * @ProjectName javayh-oauth2
 * @date 2019/6/17 18:11
 */
public enum OperationUnit {
    /**
     * 被操作的单元
     */
    UNKNOWN("unknown"),
    USER("user"),
    EMPLOYEE("employee"),
    Redis("redis");

    private String value;

    OperationUnit(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
