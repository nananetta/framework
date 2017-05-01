package com.konkow.framework.domain;

import java.util.List;

public class Page<E> {

    private int page;
    private int totalPage;

    private int startIndex;
    private long total;
    private int pageSize;

    private List<E> list;

    public Page(List<E> e) {
        this.list = e;
    }
    
    public List<E> getList() {
        return list;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getPageSize() {
        return this.pageSize;
    }
    
    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public long getTotal() {
        return total;
    }

    public int getStartIndex() {
        return startIndex;
    }

    public void setStartIndex(int startIndex) {
        this.startIndex = startIndex;
    }

    public void setTotal(long total) {
        this.total = total;
    }

}
