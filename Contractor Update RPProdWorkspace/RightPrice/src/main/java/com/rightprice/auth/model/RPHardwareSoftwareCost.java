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
@ApiModel(value = "Master Hardware Software Cost", description = "RP Hardware software Model Attributes")
@Table(name = "synprod.MST_RP_Hardware_Software_Cost")
public class RPHardwareSoftwareCost implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "HARDWARE_COST_ID")
	@ApiModelProperty(notes = "HardWare Cost ID Auto Incremented")
	private Integer hardwareCostId;

	@Column(name = "HARDWARE_COST_DESCRIPTION")
	@ApiModelProperty(notes = "HARDWARE_COST_DESCRIPTION")
	private String hardwareCostDescription;
	
	@Column(name = "HARDWARE_COST_VALUE")
	@ApiModelProperty(notes = "HARDWARE_COST_VALUE")
	private Double hardwareCostValue;
	
	@Column(name = "IS_ACTIVE")
	@ApiModelProperty(notes = "Active status")
	private int isActive;

	@Column(name = "CREATED_BY", updatable = false)
	@ApiModelProperty(notes = "Created by")
	private String createdBy;

	@Column(name = "CREATED_ON", updatable = false)
	@ApiModelProperty(notes = "Created date")
	private String createdDate;

	@Column(name = "UPDATED_BY")
	@ApiModelProperty(notes = "Updated by")
	private String updatedBy;

	@Column(name = "UPDATED_ON")
	@ApiModelProperty(notes = "Updated date")
	private String updatedDate;

	@Column(name = "COUNTRY_ID")
	@ApiModelProperty(notes = "country Id")
	private int countryId;

	@Column(name = "TIME_FLAG")
	@ApiModelProperty(notes = "Time Flag")
	private char timeFlag;

	public Integer getHardWareCostId() {
		return hardwareCostId;
	}

	public void setHardWareCostId(Integer hardwareCostId) {
		this.hardwareCostId = hardwareCostId;
	}

	public String getHardwareCostDescription() {
		return hardwareCostDescription;
	}

	public void setHardwareCostDescription(String hardwareCostDescription) {
		this.hardwareCostDescription = hardwareCostDescription;
	}
	
	public Integer getHardwareCostId() {
		return hardwareCostId;
	}

	public void setHardwareCostId(Integer hardwareCostId) {
		this.hardwareCostId = hardwareCostId;
	}

	public Double getHardwareCostValue() {
		return hardwareCostValue;
	}

	public void setHardwareCostValue(Double hardwareCostValue) {
		this.hardwareCostValue = hardwareCostValue;
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

	public int getCountryId() {
		return countryId;
	}

	public void setCountryId(int countryId) {
		this.countryId = countryId;
	}

	public char getTimeFlag() {
		return timeFlag;
	}

	public void setTimeFlag(char timeFlag) {
		this.timeFlag = timeFlag;
	}
}
