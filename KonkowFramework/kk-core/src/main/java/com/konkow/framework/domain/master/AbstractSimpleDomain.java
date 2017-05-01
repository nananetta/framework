package com.konkow.framework.domain.master;

import javax.persistence.MappedSuperclass;

import org.springframework.util.StringUtils;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.konkow.framework.domain.AbstractDomain;

@MappedSuperclass
public abstract class AbstractSimpleDomain extends AbstractDomain<Integer> implements Cloneable {

    private String code;

    private String description;

    @JsonIgnore
    private Integer orderDp;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getOrderDp() {
        return orderDp;
    }

    public void setOrderDp(Integer orderDp) {
        this.orderDp = orderDp;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (StringUtils.isEmpty(this.getCode())) {
            return false;
        }
        if (this.getCode().equals(((AbstractSimpleDomain) obj).getCode())) {
            return true;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
