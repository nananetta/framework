package com.konkow.framework.repository;

import java.io.Serializable;

import com.konkow.framework.domain.IDomain;
import com.konkow.framework.domain.Result;
import com.konkow.framework.exception.BusinessSQLException;

public interface Repository<E extends IDomain<K>, K extends Serializable> {

	Result<E> findAll();
	
	E findByKey(K key); 
	
	E store(E entity) throws BusinessSQLException;
	
	void remove(K key) throws BusinessSQLException;
}
