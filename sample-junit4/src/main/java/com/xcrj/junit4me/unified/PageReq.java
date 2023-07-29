package com.xcrj.junit4me.unified;

public class PageReq {
    // 第几页
    private Integer pageNum;
    // 每页数据量
    private Integer pageSize;
    // 是否排序
    private Boolean ordered=false;
    // 排序列
    private String orderColumn;
    // true正序
    private Boolean asc=true;

    public PageReq() {
    }
    public Integer getPageNum() {
        return pageNum;
    }
    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }
    public Integer getPageSize() {
        return pageSize;
    }
    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }
    public Boolean getAsc() {
        return asc;
    }
    public void setAsc(Boolean asc) {
        this.asc = asc;
    }
    public String getOrderColumn() {
        return orderColumn;
    }
    public void setOrderColumn(String orderColumn) {
        this.orderColumn = orderColumn;
    }
    public Boolean getOrdered() {
        return ordered;
    }
    public void setOrdered(Boolean ordered) {
        this.ordered = ordered;
    }
}
