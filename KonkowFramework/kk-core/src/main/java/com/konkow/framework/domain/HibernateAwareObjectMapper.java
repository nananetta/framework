package com.konkow.framework.domain;

import java.text.SimpleDateFormat;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.fasterxml.jackson.datatype.hibernate4.Hibernate4Module;


@Component
public class HibernateAwareObjectMapper extends ObjectMapper {

    private static final long serialVersionUID = 1L;

    public HibernateAwareObjectMapper() {
        registerModule(new Hibernate4Module());
        SimpleFilterProvider filters = new SimpleFilterProvider();
        filters.setFailOnUnknownId(false);
        setFilters(filters);
        configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false); 
      
        setDateFormat(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ"));
    }
}
