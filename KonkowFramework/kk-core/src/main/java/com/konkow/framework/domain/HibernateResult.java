package com.konkow.framework.domain;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.konkow.framework.FrameworkProperties;

public class HibernateResult<E> implements Result<E> {

    private long total;

    private List<E> list;

    @JsonIgnore
    private Order orderBy;

    @JsonIgnore
    private Order orderBy2;

    @JsonIgnore
    private SessionFactory sessionFactory;

    @JsonIgnore
    private Criteria criteria;

    private int pageSize = Integer.parseInt(FrameworkProperties.getDefaultPageSize());

    @JsonIgnore
    private ProjectionList projectionColumnList;

    public HibernateResult(SessionFactory sessionFactory, Criteria criteria, Order orderBy) {
        this.sessionFactory = sessionFactory;
        this.criteria = criteria;
        this.orderBy = orderBy;
        criteria.setProjection(Projections.rowCount());
        this.total = (Long) criteria.uniqueResult();
    }

    public HibernateResult(SessionFactory sessionFactory, Criteria criteria) {
        this.sessionFactory = sessionFactory;
        this.criteria = criteria;
        criteria.setProjection(Projections.rowCount());
        this.total = (Long) criteria.uniqueResult();
    }

    public HibernateResult(SessionFactory sessionFactory, Criteria criteria, Projection projection) {
        this.sessionFactory = sessionFactory;
        this.criteria = criteria;
        criteria.setProjection(Projections.rowCount());
        this.total = (Long) criteria.uniqueResult();
        resetProjection();
        criteria.setProjection(projection);

    }

    public HibernateResult(SessionFactory sessionFactory, Class<E> entity) {
        this.sessionFactory = sessionFactory;
        this.criteria = sessionFactory.getCurrentSession().createCriteria(entity);
        criteria.setProjection(Projections.rowCount());
        this.total = (Long) criteria.uniqueResult();
    }

    public HibernateResult(SessionFactory sessionFactory, Class<E> entity, Order orderBy) {
        this.sessionFactory = sessionFactory;
        this.criteria = sessionFactory.getCurrentSession().createCriteria(entity);
        this.orderBy = orderBy;
        criteria.setProjection(Projections.rowCount());
        this.total = (Long) criteria.uniqueResult();
    }

    public HibernateResult(SessionFactory sessionFactory, Class<E> entity, Order orderBy, Order orderBy2) {
        this.sessionFactory = sessionFactory;
        this.criteria = sessionFactory.getCurrentSession().createCriteria(entity);
        this.orderBy = orderBy;
        this.orderBy2 = orderBy2;
        criteria.setProjection(Projections.rowCount());
        this.total = (Long) criteria.uniqueResult();
    }

    @JsonIgnore
    public HibernateResult<E> getFullResult() {
        resetProjection();
        if (orderBy != null) {
            criteria.addOrder(this.orderBy);
        }
        if (orderBy2 != null) {
            criteria.addOrder(this.orderBy2);
        }
        this.list = criteria.list();
        return this;
    }

    private void resetProjection() {
        // Reset projection and transformer before new query
        criteria.setProjection(null);
        criteria.setResultTransformer(Criteria.ROOT_ENTITY);
    }

    private void setColumnProjection() {
        if (projectionColumnList != null && projectionColumnList.getLength() > 0) {
            criteria.setProjection(projectionColumnList);
        }
    }

    public List<E> getList() {
        getFullResult();
        return list;
    }

    public long getTotal() {
        return total;
    }
    
    public Page<E> getPage(Integer page, Integer pageSize) {
    	if(pageSize != null){
            this.pageSize = pageSize;
    	}
        resetProjection();
        setColumnProjection();
        page = page < 1 ? 1 : page;
        int startIndex = (page - 1) * this.pageSize; // Start from 0
        int noOfPage = (int) Math.ceil((double) (total) / this.pageSize);
        criteria.setFirstResult(startIndex);
        criteria.setMaxResults(this.pageSize);
        if (orderBy != null) {
            criteria.addOrder(this.orderBy);
        }
        if (orderBy2 != null) {
            criteria.addOrder(this.orderBy2);
        }
        List<E> list = criteria.list();
        Page<E> resultPage = new Page<E>(list);
        resultPage.setStartIndex(startIndex);
        resultPage.setPage(page);
        resultPage.setPageSize(this.pageSize);
        resultPage.setTotal(total);
        resultPage.setTotalPage(noOfPage);
        return resultPage;
    }

    public Page<E> getPage(int page) {
        return getPage(page, pageSize);
    }

    public Page<E> getPageWithProjection(Integer page, Integer pageSize) {
        this.pageSize = pageSize;
        setColumnProjection();
        page = page < 1 ? 1 : page;
        int startIndex = (page - 1) * pageSize; // Start from 0
        int noOfPage = (int) Math.ceil((double) (total) / pageSize);
        criteria.setFirstResult(startIndex);
        criteria.setMaxResults(pageSize);
        if (orderBy != null) {
            criteria.addOrder(this.orderBy);
        }
        if (orderBy2 != null) {
            criteria.addOrder(this.orderBy2);
        }
        List<E> list = criteria.list();
        Page<E> resultPage = new Page<E>(list);
        resultPage.setStartIndex(startIndex);
        resultPage.setPage(page);
        resultPage.setPageSize(pageSize);
        resultPage.setTotal(total);
        resultPage.setTotalPage(noOfPage);
        return resultPage;
    }
    
    public Page<E> getPageWithProjection(int page) {
        return getPageWithProjection(page, pageSize);
    }

    public void setProjectionColumns(String... columns) {
        projectionColumnList = Projections.projectionList();
        for (String column : columns) {
            projectionColumnList.add(Projections.property(column));
        }
    }

}
