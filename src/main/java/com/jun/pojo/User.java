package com.jun.pojo;

import java.util.Calendar;
import java.util.Date;

public class User {
	private String email;
	private int status;
	private String validateCode;
	private Date registerTime;
	private Date lastActivateTime;
	
	public Date getLastActivateTime() {
		Calendar cl =Calendar.getInstance();
		cl.setTime(registerTime);
		cl.add(Calendar.DATE,2);
		return cl.getTime();
	}



	public Date getRegisterTime() {
		return registerTime;
	}

	public void setRegisterTime(Date registerTime) {
		this.registerTime = registerTime;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getValidateCode() {
		return validateCode;
	}

	public void setValidateCode(String validateCode) {
		this.validateCode = validateCode;
	}



	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
