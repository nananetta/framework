package com.konkow.framework.domain.master;

import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.konkow.framework.domain.AbstractDomain;

@Entity
@JsonInclude(Include.NON_NULL)
public class Parameter extends AbstractDomain<Integer> {
	private String code;

	private String value;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}
