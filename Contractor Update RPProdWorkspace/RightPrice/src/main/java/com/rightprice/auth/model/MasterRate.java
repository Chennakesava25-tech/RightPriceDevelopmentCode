package com.rightprice.auth.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@Entity
@ApiModel(value="Master Rate",description="Rate Model Attributes")
@Table(name = "synprod.MST_RP_Master_Rate")
public class MasterRate implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "MASTER_RATE_ID")
	@ApiModelProperty(notes="Master Rate ID Auto Incremented")
	private Integer masterRateId;
	
	@Column(name = "MASTER_ROLE_ID")
	@ApiModelProperty(notes="Master Role Id")
	private Integer masterRoleId;
	
	@OneToOne(cascade = javax.persistence.CascadeType.ALL)
    @JoinColumn(name = "MASTER_ROLE_ID",referencedColumnName = "MASTER_ROLE_ID", insertable = false, updatable = false)
	private MasterRole masterRole;
	
	@Column(name = "COUNTRY_ID")
	@ApiModelProperty(notes="Country Id")
	private Integer countryId;
	
	@Column(name = "TRANSACTION_YEAR")
	@ApiModelProperty(notes="Transaction Year")
	private int transactionYear;
	
	@Column(name = "RATE_LOW")
	@ApiModelProperty(notes="Rate Low")
	private Double rateLow;
	
	@Column(name = "RATE_MEDIUM")
	@ApiModelProperty(notes="Rate Medium")
	private Double rateMedium;
	
	@Column(name = "RATE_HIGH")
	@ApiModelProperty(notes="Rate High")
	private Double rateHigh;
	
	@Column(name = "RATE_VHIGH")
	@ApiModelProperty(notes="Rate VHigh")
	private Double rateVHigh;
	
	@Column(name = "RATE_OFFSHORE")
	@ApiModelProperty(notes="Rate Offshore")
	private Double rateOffshore;
	
	@Column(name = "IS_ACTIVE")
	@ApiModelProperty(notes="Is Active")
	private Integer isActive;
	
	@Column(name = "CREATED_BY")
	@ApiModelProperty(notes="Created By")
	private String createdBy;
	
	@Column(name = "CREATED_ON")
	@ApiModelProperty(notes="Created On")
	private String createdOn;
	
	@Column(name = "UPDATED_BY")
	@ApiModelProperty(notes="Updated By")
	private String updatedBy;
	
	@Column(name = "UPDATED_ON")
	@ApiModelProperty(notes="Updated_On")
	private String updatedOn;
	
	@javax.persistence.Transient
	private String countryName;

	@javax.persistence.Transient
	private String masterRoleName;
	
	public Integer getMasterRateId() {
		return masterRateId;
	}

	public void setMasterRateId(Integer masterRateId) {
		this.masterRateId = masterRateId;
	}

	public Integer getMasterRoleId() {
		return masterRoleId;
	}

	public void setMasterRoleId(Integer masterRoleId) {
		this.masterRoleId = masterRoleId;
	}

	public Integer getCountryId() {
		return countryId;
	}

	public void setCountryId(Integer countryId) {
		this.countryId = countryId;
	}

	public int getTransactionYear() {
		return transactionYear;
	}

	public void setTransactionYear(int transactionYear) {
		this.transactionYear = transactionYear;
	}

	public Double getRateLow() {
		return rateLow;
	}

	public void setRateLow(Double rateLow) {
		this.rateLow = rateLow;
	}

	public Double getRateMedium() {
		return rateMedium;
	}

	public void setRateMedium(Double rateMedium) {
		this.rateMedium = rateMedium;
	}

	public Double getRateHigh() {
		return rateHigh;
	}

	public void setRateHigh(Double rateHigh) {
		this.rateHigh = rateHigh;
	}

	public Double getRateVHigh() {
		return rateVHigh;
	}

	public void setRateVHigh(Double rateVHigh) {
		this.rateVHigh = rateVHigh;
	}

	public Double getRateOffshore() {
		return rateOffshore;
	}

//	public MasterRoles getMasterRoles() {
//		return masterRoles;
//	}
//
//	public void setMasterRoles(MasterRoles masterRoles) {
//		this.masterRoles = masterRoles;
//	}

	public MasterRole getMasterRole() {
		return masterRole;
	}

	public void setMasterRole(MasterRole masterRole) {
		this.masterRole = masterRole;
	}

	public void setRateOffshore(Double rateOffshore) {
		this.rateOffshore = rateOffshore;
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

	public String getCountryName() {
		return countryName;
	}

	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}

	public String getMasterRoleName() {
		return masterRoleName;
	}

	public void setMasterRoleName(String masterRoleName) {
		this.masterRoleName = masterRoleName;
	}

}
