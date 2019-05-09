package com.rayvision.rpc.common.pagination;

public class PageParam {

    private Integer offset;

    private Integer pageMaxSize;

    public Integer getOffset() {
        return offset;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    public Integer getPageMaxSize() {
        return pageMaxSize;
    }

    public void setPageMaxSize(Integer pageMaxSize) {
        this.pageMaxSize = pageMaxSize;
    }
}