package com.konkow.framework.domain.email;

import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.konkow.framework.domain.AbstractDomain;

@Entity
@JsonInclude(Include.NON_NULL)
public class EmailAttachment extends AbstractDomain<Integer> {

    public EmailAttachment() {

    }

    private String filename;

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

}
