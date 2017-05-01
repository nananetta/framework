package com.konkow.framework.domain.ume;

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
public class Role extends AbstractDomain<Long> {

	public Role() {
		this.auths = new ArrayList<Authorization>();
		this.users = new HashSet<User>();
	}

	private String roleCode;
	private String roleName;
	private List<Authorization> auths;
	private Set<User> users;

	public String getRoleCode() {
		return roleCode;
	}

	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public List<Authorization> getAuths() {
		return auths;
	}

	public void setAuths(List<Authorization> auths) {
		this.auths = auths;
	}

	public Set<User> getUsers() {
		return users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}

}
