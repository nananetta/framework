package com.konkow.framework.domain.ume;

import javax.persistence.Entity;

import com.konkow.framework.domain.AbstractDomain;

@Entity
public class MappingRoleUser extends AbstractDomain<Long> {

	private Role role;

	private String userCode;

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}
}
