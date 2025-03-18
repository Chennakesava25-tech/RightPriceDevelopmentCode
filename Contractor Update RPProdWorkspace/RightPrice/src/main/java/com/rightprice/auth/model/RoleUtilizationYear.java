package com.rightprice.auth.model;

import java.io.Serializable;


public class RoleUtilizationYear implements Serializable
{
		
	private static final long serialVersionUID = 1L;

	private Integer year;
	
	private String yearDesc;
	
	public Integer getYear() {
		return year;
	}
	public void setYear(Integer year) {
		this.year = year;
	}
	public String getYearDesc() {
		return yearDesc;
	}
	public void setYearDesc(String yearDesc) {
		this.yearDesc = yearDesc;
	}
}
