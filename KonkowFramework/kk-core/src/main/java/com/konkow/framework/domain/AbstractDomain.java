package com.konkow.framework.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import org.springframework.format.annotation.DateTimeFormat;

@MappedSuperclass
public abstract class AbstractDomain<K extends Serializable> implements IDomain<K> {

    @Id
    @GeneratedValue
    private K id;

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ssZ")
    private Date lastUpdateDate;

    private String lastUpdateBy;
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ssZ")
    private Date createDate;

    private String createBy;

    private int rowVersion;

    public K getId() {
        return id;
    }

    public void setId(K id) {
        this.id = id;
    }

    public Date getLastUpdateDate() {
        return lastUpdateDate;
    }

    public void setLastUpdateDate(Date lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }

    public String getLastUpdateBy() {
        return lastUpdateBy;
    }

    public void setLastUpdateBy(String lastUpdateBy) {
        this.lastUpdateBy = lastUpdateBy;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public int getRowVersion() {
        return rowVersion;
    }

    public void setRowVersion(int rowVersion) {
        this.rowVersion = rowVersion;
    }

}
