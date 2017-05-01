package com.konkow.framework.domain;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

public interface Result<E> {

    @JsonIgnore
    public Result<E> getFullResult();

    public List<E> getList();

    public long getTotal();

    public Page<E> getPage(int page);
    
    public Page<E> getPage(Integer page, Integer pageSize);

}
