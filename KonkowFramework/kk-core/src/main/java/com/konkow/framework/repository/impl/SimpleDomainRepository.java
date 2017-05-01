package com.konkow.framework.repository.impl;

import java.util.List;
import java.util.Map;
import java.util.Vector;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.konkow.framework.domain.HibernateResult;
import com.konkow.framework.domain.Result;
import com.konkow.framework.domain.master.AbstractSimpleDomain;
import com.konkow.framework.repository.AbstractRepository;

@Component
public class SimpleDomainRepository extends AbstractRepository<AbstractSimpleDomain, Integer> {

    private static final Logger LOGGER = LogManager.getLogger(SimpleDomainRepository.class);
    
    @Autowired
    private SessionFactory sessionFactory;

    public Result<AbstractSimpleDomain> findAll(Class clazz) {
        Criteria criteria = createCriteria(clazz);
        criteria.setCacheable(true);
        return new HibernateResult<AbstractSimpleDomain>(sessionFactory, criteria);
    }

    public AbstractSimpleDomain findByKey(Class clazz, Integer key) {
        Criteria criteria = createCriteria(clazz);
        criteria.add(Restrictions.eq("id", key));
        criteria.setCacheable(true);
        List<AbstractSimpleDomain> result = criteria.list();
        if (result != null && result.size() > 0) {
            return result.get(0);
        } else {
            return null;
        }
    }

    public AbstractSimpleDomain findByCode(Class clazz, String code) {
        Criteria criteria = createCriteria(clazz);
        criteria.add(Restrictions.eq("code", code));
        criteria.setCacheable(true);
        List<AbstractSimpleDomain> result = criteria.list();
        if (result != null && result.size() > 0) {
            return result.get(0);
        } else {
            return null;
        }
    }

    public AbstractSimpleDomain find(Class clazz, Vector props) {
        Criteria criteria = createCriteria(clazz);
        for (Object prop : props) {
            Map map = (Map) prop;
            String relation = (String) map.get("relation");
            String alias = (String) map.get("alias");

            criteria = criteria.createAlias(relation, alias, JoinType.LEFT_OUTER_JOIN);

        }
        for (Object prop : props) {
            Map map = (Map) prop;
            String relation = (String) map.get("relation");
            String alias = (String) map.get("alias");
            String field = (String) map.get("field");
            String value = (String) map.get("value");
            String operator = (String) map.get("operator");
            if (relation != null && !relation.equals("")) {
                if (value != null || !value.equals("")) {
                    if (operator.equals("equals")) {

                        LOGGER.info(alias + "." + field + "=" + value);
                        if (value.contains("*") || value.contains("*")) {
                            criteria = criteria.add(Restrictions.like(alias + "." + field, replaceWildcards(value)));
                        } else {
                            criteria = criteria.add(Restrictions.eq(alias + "." + field, value));
                        }
                    } else if (operator.equals("greater")) {
                        criteria = criteria.add(Restrictions.ge(alias + "." + field, value));
                    } else if (operator.equals("less")) {
                        criteria = criteria.add(Restrictions.le(alias + "." + field, value));
                    }

                }
            } else {
                if (operator.equals("equals")) {
                    LOGGER.info(field + "=" + value);

                    if (value != null || !value.equals("")) {
                        if (value.contains("*") || value.contains("*")) {
                            criteria = criteria.add(Restrictions.like(field, replaceWildcards(value)));
                        } else {
                            criteria = criteria.add(Restrictions.eq(field, value));
                        }
                    }
                } else if (operator.equals("greater")) {
                    criteria = criteria.add(Restrictions.ge(field, value));
                } else if (operator.equals("less")) {
                    criteria = criteria.add(Restrictions.le(field, value));
                }

            }
        }

        criteria.setCacheable(true);
        List<AbstractSimpleDomain> result = criteria.list();
        if (result != null && result.size() > 0) {
            return result.get(0);
        } else {
            return null;
        }
    }

    public AbstractSimpleDomain create(AbstractSimpleDomain entity) {
        getSession().save(entity);
        return entity;
    }

    public AbstractSimpleDomain store(AbstractSimpleDomain entity) {
        AbstractSimpleDomain e = findByKey(entity.getId());
        getSession().merge(entity);
        return entity;
    }

    public void remove(Integer key) {
    }

    public Result<AbstractSimpleDomain> findAll() {
        // TODO unused.
        return null;
    }

    public AbstractSimpleDomain findByKey(Integer key) {
        // TODO unused.
        return null;
    }

}
