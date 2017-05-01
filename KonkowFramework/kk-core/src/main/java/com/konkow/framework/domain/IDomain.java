package com.konkow.framework.domain;

import java.io.Serializable;
import java.util.Date;

public interface IDomain<K extends Serializable> {

	K getId();

	Date getLastUpdateDate();

	void setLastUpdateDate(Date lastUpdateDate);

	String getLastUpdateBy();

	void setLastUpdateBy(String lastUpdateBy);

	Date getCreateDate();

	void setCreateDate(Date createDate);

	String getCreateBy();

	void setCreateBy(String createBy);
}
