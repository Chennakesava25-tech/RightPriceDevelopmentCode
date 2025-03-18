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
@ApiModel(value="Rate card details",description="Rate card details Model Attributes")
@Table(name = "synprod.RP_RATE_CARD_CURRENCY_UTILIZATION")
public class RateCardCurrencyUtilization implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "RC_Currency_Utilization_Id")
	private Integer rcCurrencyUtilizationId;
	
	@Column(name = "RC_ID")
	private Integer rcId;
	
	@Column(name = "Base_Country_Id")
	private Integer baseCountryId;
	
	@Column(name = "Utilization")
	private double utilization;
	
	@Column(name = "RATECARD_PROCESSING_TYPE")
	private Integer rateCardProcessingType;
	
	@Column(name ="IS_ACTIVE")
	private Integer isActive=1;
	
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

	public Integer getRcCurrencyUtilizationId() {
		return rcCurrencyUtilizationId;
	}

	public void setRcCurrencyUtilizationId(Integer rcCurrencyUtilizationId) {
		this.rcCurrencyUtilizationId = rcCurrencyUtilizationId;
	}

	public Integer getBaseCountryId() {
		return baseCountryId;
	}

	public void setBaseCountryId(Integer baseCountryId) {
		this.baseCountryId = baseCountryId;
	}

	public double getUtilization() {
		return utilization;
	}

	public void setUtilization(double utilization) {
		this.utilization = utilization;
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

	public Integer getRateCardProcessingType() {
		return rateCardProcessingType;
	}

	public void setRateCardProcessingType(Integer rateCardProcessingType) {
		this.rateCardProcessingType = rateCardProcessingType;
	}

	public Integer getRcId() {
		return rcId;
	}

	public void setRcId(Integer rcId) {
		this.rcId = rcId;
	}
}
