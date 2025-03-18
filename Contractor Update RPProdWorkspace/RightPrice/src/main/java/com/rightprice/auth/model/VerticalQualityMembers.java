/*package com.rightprice.auth.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@Entity
@ApiModel(value="Vertical",description="Vertical RiskManagers Model Attributes")
@Table(name = "SYNPROD.MST_RP_VERTICAL_RiskManagers_MEMBERS")
public class VerticalRiskManagersMembers {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "RiskManagers_MEMBER_ID")
	@ApiModelProperty(notes = "RiskManagers Member id")
	private Integer RiskManagersMemberId;
	
	@Column(name = "CUSTOMER_ID")
	@ApiModelProperty(notes = "Customer Id")
	private Integer customerId;
	
	@Column(name = "VERTICAL_ID")
	@ApiModelProperty(notes = "Vertical_Id")
	private Integer verticalId;
	
	@Column(name = "EMPLOYEE_LAN_ID")
	@ApiModelProperty(notes = "EmployeeLanId")
	private String employeeLanId;
	
	@Column(name ="IS_ACTIVE")
	@ApiModelProperty(notes = "Is Active")
	private Integer isActive;
	
	@Column(name = "CREATED_BY", updatable=false)
	@ApiModelProperty(notes="Created by")
	private String createdBy;
	
	@Column(name = "CREATED_ON", updatable=false)
	@ApiModelProperty(notes="Created date")
	private String createdOn;
	
	@Column(name = "UPDATED_BY")
	@ApiModelProperty(notes="Updated by")
	private String updatedBy;
	
	@Column(name = "UPDATED_ON")
	@ApiModelProperty(notes="Updated date")
	private String updatedOn;

	public Integer getRiskManagersMemberId() {
		return RiskManagersMemberId;
	}

	public void setRiskManagersMemberId(Integer RiskManagersMemberId) {
		this.RiskManagersMemberId = RiskManagersMemberId;
	}

	public Integer getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}

	public Integer getVerticalId() {
		return verticalId;
	}

	public void setVerticalId(Integer verticalId) {
		this.verticalId = verticalId;
	}

	public String getEmployeeLanId() {
		return employeeLanId;
	}

	public void setEmployeeLanId(String employeeLanId) {
		this.employeeLanId = employeeLanId;
	}

	public Integer getIsActive() {
		return isActive;
	}

	public void setIsActive(Integer isActive) {
		this.isActive = isActive;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(String createdOn) {
		this.createdOn = createdOn;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public String getUpdatedOn() {
		return updatedOn;
	}

	public void setUpdatedOn(String updatedOn) {
		this.updatedOn = updatedOn;
	}
}
*/