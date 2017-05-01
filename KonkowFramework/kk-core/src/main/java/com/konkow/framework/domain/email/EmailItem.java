package com.konkow.framework.domain.email;

import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.konkow.framework.domain.AbstractDomain;

@Entity
@JsonInclude(Include.NON_NULL)
public class EmailItem extends AbstractDomain<Integer> {

    public EmailItem() {

    }

    private String recipient;

    public String getRecipient() {
        return recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

}
