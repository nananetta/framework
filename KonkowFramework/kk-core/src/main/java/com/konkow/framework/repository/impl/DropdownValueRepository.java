package com.konkow.framework.repository.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.konkow.framework.domain.Result;
import com.konkow.framework.domain.master.DropdownValue;
import com.konkow.framework.repository.AbstractRepository;
import com.konkow.framework.repository.IDropdownValueRepository;

@Component
public class DropdownValueRepository extends
		AbstractRepository<DropdownValue, Integer> implements
		IDropdownValueRepository {
	
	@Autowired
    private SessionFactory sessionFactory;

	public Result<DropdownValue> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	public DropdownValue findByKey(Integer key) {
		// TODO Auto-generated method stub
		return null;
	}

	public DropdownValue store(DropdownValue entity) {
		// TODO Auto-generated method stub
		return null;
	}

	public void remove(Integer key) {
		// TODO Auto-generated method stub

	}

	public DropdownValue findByCustomerCode(String code) {
        Criteria criteria = createCriteria(DropdownValue.class);
        criteria.add(Restrictions.eq("code", code));
        List<DropdownValue> result = criteria.list();
        if (result != null && result.size() > 0) {
            return result.get(0);
        } else {
            return null;
        }
	}

}
