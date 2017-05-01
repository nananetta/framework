package com.konkow.framework.domain.master;

import javax.persistence.Entity;

@Entity
public class DropdownValue extends AbstractSimpleDomain{

	private DropdownGroup droupdownGroup;
	private String flagDefault;
	
	public DropdownGroup getDroupdownGroup() {
		return droupdownGroup;
	}
	public void setDroupdownGroup(DropdownGroup droupdownGroup) {
		this.droupdownGroup = droupdownGroup;
	}
	public String getFlagDefault() {
		return flagDefault;
	}
	public void setFlagDefault(String flagDefault) {
		this.flagDefault = flagDefault;
	}
	
}
