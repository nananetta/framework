package com.konkow.framework.domain.ume;

import javax.persistence.Entity;

import com.konkow.framework.domain.AbstractDomain;

@Entity
public class Authorization extends AbstractDomain<Long> {

	private int authId;

	private String authCode;

	private String authName;

	private String authObjectCode;

	private String authField1ValueFrom;

	private String authField1ValueTo;

	private String authField2ValueFrom;

	private String authField2ValueTo;

	private String authField3ValueFrom;

	private String authField3ValueTo;

	private String authField4ValueFrom;

	private String authField4ValueTo;

	private String authField5ValueFrom;

	private String authField5ValueTo;

	public int getAuthId() {
		return authId;
	}

	public void setAuthId(int authId) {
		this.authId = authId;
	}

	public String getAuthCode() {
		return authCode;
	}

	public void setAuthCode(String authCode) {
		this.authCode = authCode;
	}

	public String getAuthName() {
		return authName;
	}

	public void setAuthName(String authName) {
		this.authName = authName;
	}

	public String getAuthObjectCode() {
		return authObjectCode;
	}

	public void setAuthObjectCode(String authObjectCode) {
		this.authObjectCode = authObjectCode;
	}

	public String getAuthField1ValueFrom() {
		return authField1ValueFrom;
	}

	public void setAuthField1ValueFrom(String authField1ValueFrom) {
		this.authField1ValueFrom = authField1ValueFrom;
	}

	public String getAuthField1ValueTo() {
		return authField1ValueTo;
	}

	public void setAuthField1ValueTo(String authField1ValueTo) {
		this.authField1ValueTo = authField1ValueTo;
	}

	public String getAuthField2ValueFrom() {
		return authField2ValueFrom;
	}

	public void setAuthField2ValueFrom(String authField2ValueFrom) {
		this.authField2ValueFrom = authField2ValueFrom;
	}

	public String getAuthField2ValueTo() {
		return authField2ValueTo;
	}

	public void setAuthField2ValueTo(String authField2ValueTo) {
		this.authField2ValueTo = authField2ValueTo;
	}

	public String getAuthField3ValueFrom() {
		return authField3ValueFrom;
	}

	public void setAuthField3ValueFrom(String authField3ValueFrom) {
		this.authField3ValueFrom = authField3ValueFrom;
	}

	public String getAuthField3ValueTo() {
		return authField3ValueTo;
	}

	public void setAuthField3ValueTo(String authField3ValueTo) {
		this.authField3ValueTo = authField3ValueTo;
	}

	public String getAuthField4ValueFrom() {
		return authField4ValueFrom;
	}

	public void setAuthField4ValueFrom(String authField4ValueFrom) {
		this.authField4ValueFrom = authField4ValueFrom;
	}

	public String getAuthField4ValueTo() {
		return authField4ValueTo;
	}

	public void setAuthField4ValueTo(String authField4ValueTo) {
		this.authField4ValueTo = authField4ValueTo;
	}

	public String getAuthField5ValueFrom() {
		return authField5ValueFrom;
	}

	public void setAuthField5ValueFrom(String authField5ValueFrom) {
		this.authField5ValueFrom = authField5ValueFrom;
	}

	public String getAuthField5ValueTo() {
		return authField5ValueTo;
	}

	public void setAuthField5ValueTo(String authField5ValueTo) {
		this.authField5ValueTo = authField5ValueTo;
	}

}
