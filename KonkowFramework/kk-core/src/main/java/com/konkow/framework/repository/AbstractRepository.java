package com.konkow.framework.repository;

import java.io.Serializable;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.konkow.framework.domain.IDomain;

public abstract class AbstractRepository<E extends IDomain<K>, K extends Serializable> implements Repository<E, K> {

    @Autowired
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    protected Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    protected Criteria createCriteria(Class entity) {
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(entity);
        return criteria;
    }

    protected String replaceWildcards(String str) {
        if (str != null) {
            return str.replace("*", "%").replace("?", "_");
        }
        return str;
    }

    protected boolean containWildcards(String str) {
        if (str.contains("*") || str.contains("?")) {
            return true;
        }
        return false;
    }

}
