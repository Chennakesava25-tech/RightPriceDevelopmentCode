package com.rightprice.auth.model;

import java.io.Serializable;

public class CheckUserType implements Serializable{
	private static final long serialVersionUID = 1L;
	
	public static int userTypeId;
	private String userTypeName;
	public static Integer sumbitAtos; 
	
	public int getUserTypeId() {
		return userTypeId;
	}
	public void setUserTypeId(int userTypeId) {
		CheckUserType.userTypeId = userTypeId;
	}
	public String getUserTypeName() {
		return userTypeName;
	}
	public void setUserTypeName(String userTypeName) {
		this.userTypeName = userTypeName;
	}
	public Integer getSumbitAtos() {
		return sumbitAtos;
	}
	public void setSumbitAtos(Integer sumbitAtos) {
		this.sumbitAtos = sumbitAtos;
	}
}
