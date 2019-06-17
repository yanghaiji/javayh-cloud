package com.javayh.entity;

import lombok.Data;

@Data
public class Result {

    private int code;
    private String message;
    private Object data;

    public Result() {
    }

    public Result(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public Result(int code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }



    /**
     * 查询成功
     * @param data
     * @return
     */
    public static Result javaYhQuerySuccess(Object data){
        return new Result(ResultCode.SUCCESS.getCode(),ResultCode.SUCCESS.getMessage(),data);
    }

    /**
     * 新增成功
     * @param data
     * @return
     */
    public static Result javaYhInsertSuccess(Object data){
        return new Result(ResultCode.SUCCESS.getCode(),ResultCode.SUCCESS.getMessage(),data);
    }


    /**
     * 更新成功
     * @param data
     * @return
     */
    public static Result javaYhUpdateSuccess(Object data){
        return new Result(ResultCode.SUCCESS.getCode(),ResultCode.SUCCESS.getMessage(),data);
    }

    /**
     * 删除成功
     * @param data
     * @return
     */
    public static Result javaYhDeleteSuccess(Object data){
        return new Result(ResultCode.SUCCESS.getCode(),ResultCode.SUCCESS.getMessage(),data);
    }

    /**
     * 处理异常
     * @param data
     * @return
     */
    public static Result javaYhResultFailed(Object data){
        return new Result(ResultCode.FAILED.getCode(),ResultCode.FAILED.getMessage(),data);
    }



}
