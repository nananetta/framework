package com.konkow.framework.repository.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.konkow.framework.domain.Result;
import com.konkow.framework.domain.master.Parameter;
import com.konkow.framework.repository.AbstractRepository;

@Component
public class ParameterRepository extends AbstractRepository<Parameter, Integer> {

	@Autowired
	private SessionFactory sessionFactory;

	public Result<Parameter> findAll() {
		return null;
	}

	public Parameter findByCode(String key) {
		Criteria criteria = createCriteria(Parameter.class);
		criteria.add(Restrictions.eq("code", key));
		List<Parameter> result = criteria.list();
		if (result != null && result.size() > 0) {
			return result.get(0);
		} else {
			return null;
		}
	}

	public Parameter create(Parameter entity) {
		getSession().save(entity);
		return entity;
	}

	public Parameter store(Parameter entity) {
		Parameter e = findByKey(entity.getId());
		getSession().merge(entity);
		return entity;
	}

	public void remove(Integer key) {
	}

	public Parameter findByKey(Integer key) {
		Criteria criteria = createCriteria(Parameter.class);
		criteria.add(Restrictions.eq("id", key));
		List<Parameter> result = criteria.list();
		if (result != null && result.size() > 0) {
			return result.get(0);
		} else {
			return null;
		}
	}

}
