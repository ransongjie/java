package com.xcrj.junit4me.unified;


import java.io.Serializable;
import java.util.List;

public class PageRes<T> implements Serializable {
    private static final long serialVersionUID = 1L;

    //总记录数
    private long total;
    //是否成功
    private Boolean succuss;
    //错误码
    private String code;
    //消息提示
    private String description;
    //结果对象
    private List<T> data;
    //服务器当前时间戳
    private Long millis = System.currentTimeMillis();

    private PageRes() {
    }

    private PageRes(Long total, Boolean succuss, String code, String description, List<T> data) {
        this.total = total;
        this.succuss = succuss;
        this.code = code;
        this.description = description;
        this.data = data;
    }

    public static <T> PageRes<T> success() {
        return new PageRes<T>(null, true, ErrorCode.SUCCESS.getCode(), ErrorCode.SUCCESS.getDescription(), null);
    }

    public static <T> PageRes<T> success(long total, List<T> data) {
        // new Result<T>创建对象
        return new PageRes<T>(total, true, ErrorCode.SUCCESS.getCode(), ErrorCode.SUCCESS.getDescription(), data);
    }

    public static <T> PageRes<T> error(ErrorCode errorCode) {
        return new PageRes<T>(null, false, errorCode.getCode(), errorCode.getDescription(), null);
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
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

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    public Long getMillis() {
        return millis;
    }

    public void setMillis(Long millis) {
        this.millis = millis;
    }
}
