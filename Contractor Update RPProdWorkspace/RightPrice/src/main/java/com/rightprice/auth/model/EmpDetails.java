package com.rightprice.auth.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "empDetails")
public class EmpDetails {
	
	private Long id;
	private Integer verticalId;
	private String employeeId;
	private String employeeLanId;
	private String name;
	private String designation;
	private String employeeEmailId;
	private Integer verticalgroupid;
	private String verticalgroupdescr;
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Integer getVerticalId() {
		return verticalId;
	}
	public void setVerticalId(Integer verticalId) {
		this.verticalId = verticalId;
	}
	public String getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}
	public String getEmployeeLanId() {
		return employeeLanId;
	}
	public void setEmployeeLanId(String employeeLanId) {
		this.employeeLanId = employeeLanId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDesignation() {
		return designation;
	}
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	public String getEmployeeEmailId() {
		return employeeEmailId;
	}
	public void setEmployeeEmailId(String employeeEmailId) {
		this.employeeEmailId = employeeEmailId;
	}
	public String getVerticalgroupdescr() {
		return verticalgroupdescr;
	}
	public void setVerticalgroupdescr(String verticalgroupdescr) {
		this.verticalgroupdescr = verticalgroupdescr;
	}
	public Integer getVerticalgroupid() {
		return verticalgroupid;
	}
	public void setVerticalgroupid(Integer verticalgroupid) {
		this.verticalgroupid = verticalgroupid;
	}
}
