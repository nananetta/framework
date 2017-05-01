package com.konkow.framework.domain;

import javax.persistence.Entity;

import com.konkow.framework.domain.master.AbstractSimpleDomain;

@Entity
public class ClassicError extends AbstractSimpleDomain {

    private String severity;

    public String getSeverity() {
        return severity;
    }

    public void setSeverity(String severity) {
        this.severity = severity;
    }

    
}
