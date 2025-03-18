package com.rightprice.auth.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@Entity
@ApiModel(value="Members",description="vertical members")
@Table(name = "synprod.MST_RP_Vertical_Members")
public class VerticalMemberData {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "Vertical_Member_Id")
	@ApiModelProperty(notes="vertical member id Auto Incremented")
	private int verticalMemberId;
	
	@Column(name = "Customer_Id")
	@ApiModelProperty(notes="client RC id")
	private int customerId;
	
	@Column(name = "Vertical_Id")
	@ApiModelProperty(notes="Vertical Id")
	private int verticalId;
	
	@Column(name = "Employee_LAN_Id")
	@ApiModelProperty(notes="Employee LAN Id")
	private String employeeLanId;
	
	@Column(name = "Role_type")
	@ApiModelProperty(notes="Designation Id")
	private int roleType;
	
	@Column(name = "IS_ACTIVE")
	@ApiModelProperty(notes="Active status")
	private int isActive;
	
	@Column(name = "CREATED_BY", updatable=false)
	@ApiModelProperty(notes="Created by")
	private String createdBy;
	
	@Column(name = "CREATED_ON", updatable=false)
	@ApiModelProperty(notes="Created date")
	private String createdDate;
	
	@Column(name = "UPDATED_BY")
	@ApiModelProperty(notes="Updated by")
	private String updatedBy;
	
	@Column(name = "UPDATED_ON")
	@ApiModelProperty(notes="Updated date")
	private String updatedDate;

	public int getVerticalMemberId() {
		return verticalMemberId;
	}

	public void setVerticalMemberId(int verticalMemberId) {
		this.verticalMemberId = verticalMemberId;
	}

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public int getVerticalId() {
		return verticalId;
	}

	public void setVerticalId(int verticalId) {
		this.verticalId = verticalId;
	}

	public String getEmployeeLanId() {
		return employeeLanId;
	}

	public void setEmployeeLanId(String employeeLanId) {
		this.employeeLanId = employeeLanId;
	}

	public int getRoleType() {
		return roleType;
	}

	public void setRoleType(int roleType) {
		this.roleType = roleType;
	}

	public int getIsActive() {
		return isActive;
	}

	public void setIsActive(int isActive) {
		this.isActive = isActive;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public String getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(String updatedDate) {
		this.updatedDate = updatedDate;
	}
}
