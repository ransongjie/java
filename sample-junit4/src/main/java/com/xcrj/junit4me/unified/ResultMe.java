package com.xcrj.junit4me.unified;

import java.io.Serializable;

public class ResultMe<T> implements Serializable {
    private static final long serialVersionUID = 1L;

    //是否成功
    private Boolean succuss;
    //错误码
    private String code;
    //消息提示
    private String description;
    //结果对象
    private T data;
    //服务器当前时间戳
    private Long millis = System.currentTimeMillis();

    private ResultMe() {
    }

    private ResultMe(Boolean succuss, String code, String description, T data) {
        this.succuss = succuss;
        this.code = code;
        this.description = description;
        this.data = data;
    }

    public static <T> ResultMe<T> success() {
        return new ResultMe<T>(true, ErrorCode.SUCCESS.getCode(), ErrorCode.SUCCESS.getDescription(), null);
    }

    public static <T> ResultMe<T> success(T data) {
        // new Result<T>创建对象
        return new ResultMe<T>(true, ErrorCode.SUCCESS.getCode(), ErrorCode.SUCCESS.getDescription(), data);
    }

    public static <T> ResultMe<T> error(ErrorCode errorCode) {
        return new ResultMe<T>(false, errorCode.getCode(), errorCode.getDescription(), null);
    }

    public static <T> ResultMe<T> error(ErrorCode errorCode, T data) {
        return new ResultMe<T>(false, errorCode.getCode(), errorCode.getDescription(), data);
    }

    public Boolean getSuccuss() {
        return succuss;
    }

    public void setSuccuss(Boolean succuss) {
        this.succuss = succuss;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Long getMillis() {
        return millis;
    }

    public void setMillis(Long millis) {
        this.millis = millis;
    }
}
