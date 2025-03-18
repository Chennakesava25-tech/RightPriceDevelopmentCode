package com.rightprice.auth.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@Entity
@ApiModel(value = "Campus Hire", description = "Campus Hire Model Attributes")
@Table(name = "synprod.MST_RP_Campus_Hire_Threshold_Percent")
public class MasterCampusHire implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "THRESHOLD_ID")
	@ApiModelProperty(notes = "Threshold Id Auto Incremented")
	private int thresholdId;
	
	@Column(name = "DEAL_TYPE")
	@ApiModelProperty(notes = "Deal Type")
	private String dealType;
	
	@Column(name = "THRESHOLD_PERCENT")
	@ApiModelProperty(notes = "Threshold Percent")
	private Double thresholdPercent;
	
	@Column(name = "IS_ACTIVE")
	@ApiModelProperty(notes = "Is Active")
	private Integer isActive;
	
	@Column(name = "CREATED_BY")
	@ApiModelProperty(notes = "Created By")
	private String createdBy;
	
	@Column(name = "CREATED_DATE")
	@ApiModelProperty(notes = "Created Date")
	private String createdDate;
	
	@Column(name = "UPDATED_BY")
	@ApiModelProperty(notes = "Updated By")
	private String updatedBy;

	@Column(name = "UPDATED_DATE")
	@ApiModelProperty(notes = "Updated Date")
	private String updatedDate;

	public int getThresholdId() {
		return thresholdId;
	}

	public void setThresholdId(int thresholdId) {
		this.thresholdId = thresholdId;
	}

	public String getDealType() {
		return dealType;
	}

	public void setDealType(String dealType) {
		this.dealType = dealType;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public Double getThresholdPercent() {
		return thresholdPercent;
	}

	public void setThresholdPercent(Double thresholdPercent) {
		this.thresholdPercent = thresholdPercent;
	}

	public Integer getIsActive() {
		return isActive;
	}

	public void setIsActive(Integer isActive) {
		this.isActive = isActive;
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
