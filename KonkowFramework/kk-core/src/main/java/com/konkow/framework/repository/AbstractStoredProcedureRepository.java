package com.konkow.framework.repository;

import java.io.Serializable;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.konkow.framework.domain.AbstractQuery;
import com.konkow.framework.domain.IDomain;
import com.konkow.framework.domain.Result;
import com.konkow.framework.domain.StoredProcedureResult;

public abstract class AbstractStoredProcedureRepository<E extends IDomain<K>, K extends Serializable, Q extends AbstractQuery> implements Repository<E, K> {

    @Autowired
    private SessionFactory sessionFactory;
    
    protected Query dataQuery;
    
    protected Query projectionQuery;
    
    protected String dataQueryName;
    
    protected String projectionQueryName;
    
    protected Class clz;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    protected Session getSession() {
        return sessionFactory.getCurrentSession();
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
    
    private void createQuery() {
        dataQuery = getSession().getNamedQuery(dataQueryName);
        projectionQuery = getSession().getNamedQuery(projectionQueryName);
    }
    
    abstract protected void init();
    
    abstract protected void assignParameters(Q query);
    
    public Result<E> findByQuery(Q query) {
        init();
        createQuery();
        assignParameters(query);
        return new StoredProcedureResult<E>(projectionQuery, dataQuery, clz);
    }
    
}
