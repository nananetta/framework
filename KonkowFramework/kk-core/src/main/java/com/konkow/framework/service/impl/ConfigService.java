package com.konkow.framework.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.konkow.framework.domain.master.Parameter;
import com.konkow.framework.repository.impl.ParameterRepository;

@Service("configService")
public class ConfigService {

    @Autowired
    private ParameterRepository repository;

    @Transactional
    public String getConfigByKey(String key) {
        Parameter parameter = repository.findByCode(key);
        if (parameter != null) {
            return parameter.getValue();
        }
        return null;
    }
    
    @Transactional
    public String setConfigByKey(String key, String value) {
        Parameter parameter = repository.findByCode(key);
        parameter.setValue(value);
        repository.store(parameter);
        return parameter.getValue();
    }
}
