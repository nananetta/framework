package com.konkow.framework.domain.master;

import javax.persistence.Entity;

@Entity
public class DropdownGroup extends AbstractSimpleDomain{

	private String screenCode;

	public String getScreenCode() {
		return screenCode;
	}

	public void setScreenCode(String screenCode) {
		this.screenCode = screenCode;
	}
}
