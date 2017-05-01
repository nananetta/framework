package com.konkow.framework.domain;

import java.math.BigDecimal;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.transform.Transformers;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.konkow.framework.FrameworkProperties;

public class StoredProcedureResult<E> implements Result<E> {

    private long total;

    private List<E> list;

    private int pageSize = Integer.parseInt(FrameworkProperties.getDefaultPageSize());

    private Query projectionQuery;

    private Query dataQuery;

    private Class clz;

    public StoredProcedureResult(Query projectionQuery, Query dataQuery, Class clz) {
        this.projectionQuery = projectionQuery;
        this.dataQuery = dataQuery;
        this.clz = clz;
        projectionQuery.setParameter("page", 1);
        projectionQuery.setParameter("startIndex", 1);
        projectionQuery.setParameter("pageSize", pageSize);
        List<BigDecimal> projectionResult = projectionQuery.list();
        if (projectionResult != null && projectionResult.size() > 0) {
            this.total = projectionResult.get(0).longValue();
        }
    }

    @JsonIgnore
    public StoredProcedureResult<E> getFullResult() {
        dataQuery.setParameter("page", 0);
        dataQuery.setParameter("startIndex", 0);
        dataQuery.setParameter("pageSize", 0);
        this.list = dataQuery.setResultTransformer(Transformers.aliasToBean(clz)).list();
        return this;
    }

    public List<E> getList() {
        getFullResult();
        return list;
    }

    public long getTotal() {
        return total;
    }

    public Page<E> getPage(Integer page, Integer pageSize) {
        page = page < 1 ? 1 : page;
        int startIndex = (page - 1) * pageSize; // Start from 0
        int noOfPage = (int) Math.ceil((double) (total) / pageSize);
        dataQuery.setParameter("page", page);
        dataQuery.setParameter("startIndex", startIndex);
        dataQuery.setParameter("pageSize", pageSize);
        List<E> list = dataQuery.setResultTransformer(Transformers.aliasToBean(clz)).list();

        Page<E> resultPage = new Page<E>(list);
        resultPage.setStartIndex(startIndex);
        resultPage.setPage(page);
        resultPage.setTotal(total);
        resultPage.setTotalPage(noOfPage);
        return resultPage;
    }
    
    public Page<E> getPage(int page) {
        return getPage(page, pageSize);
    }

}
