package com.wab.vo.support;

/**
 * @author hcq
 * @create 2018-01-29 下午 3:21
 **/

public abstract class BaseVO {
    private int pageNow;
    private int pageSize;

    public int getPageNow() {
        return pageNow;
    }

    public void setPageNow(int pageNow) {
        this.pageNow = pageNow;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
}
