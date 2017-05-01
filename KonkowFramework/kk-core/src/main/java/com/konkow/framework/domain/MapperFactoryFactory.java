package com.konkow.framework.domain;

import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;

import org.springframework.beans.factory.FactoryBean;

public class MapperFactoryFactory implements FactoryBean<MapperFactory> {

	public MapperFactory getObject() throws Exception {
		// MapperFactory factory = new DefaultMapperFactory.Builder().build();
		return new DefaultMapperFactory.Builder().build();
	}

	public Class<?> getObjectType() {
		return MapperFactory.class;
	}

	public boolean isSingleton() {
		return true;
	}
}
