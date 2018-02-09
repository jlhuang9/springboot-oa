package com.wab.vo;

public class PageSkip {

    private int pageNow;
    private int pageSize;

    private long skip;

    private long totalPage;

    private long count;
    public PageSkip(int pageSize, int pageNow, long count) {
        this.pageSize = pageSize;
        this.pageNow = pageNow;
        this.skip = count - pageSize * pageNow;
        this.totalPage = count % pageSize == 0? count/pageSize : count/pageSize + 1;
        this.count = count;
    }
    public int getPageNow() {
        return pageNow;
    }

    public int getPageSize() {
        return pageSize;
    }

    public long getSkip() {
        return skip;
    }

    public long getTotalPage() {
        return totalPage;
    }

    public long getCount() {
        return count;
    }
}
