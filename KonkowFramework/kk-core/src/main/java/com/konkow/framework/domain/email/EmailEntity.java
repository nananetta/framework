package com.konkow.framework.domain.email;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.konkow.framework.domain.AbstractDomain;

@Entity
@JsonInclude(Include.NON_NULL)
public class EmailEntity extends AbstractDomain<Integer> {

    public EmailEntity() {
        this.variables = new ArrayList<String>();
        this.items = new HashSet<EmailItem>();
        this.attachments = new ArrayList<EmailAttachment>();
    }

    private String sentDate;
    private String sentTime;
    private String templateName;
    private List<String> variables;
    private List<EmailAttachment> attachments;
    private Set<EmailItem> items;

    public Set<EmailItem> getItems() {
        return items;
    }

    public void setItems(Set<EmailItem> items) {
        this.items = items;
    }

    public List<String> getVariables() {
        return variables;
    }

    public void setVariables(List<String> variables) {
        this.variables = variables;
    }

    public String getSentDate() {
        return sentDate;
    }

    public void setSentDate(String sentDate) {
        this.sentDate = sentDate;
    }

    public String getSentTime() {
        return sentTime;
    }

    public void setSentTime(String sentTime) {
        this.sentTime = sentTime;
    }

    public String getTemplateName() {
        return templateName;
    }

    public void setTemplateName(String templateName) {
        this.templateName = templateName;
    }

    public List<EmailAttachment> getAttachments() {
        return attachments;
    }

    public void setAttachments(List<EmailAttachment> attachments) {
        this.attachments = attachments;
    }

}
