package com.rightprice.auth.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@Entity
@ApiModel(value="Country",description="Country Model Attributes")
@Table(name = "synprod.MST_RP_Country")
public class Country implements Serializable  {
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "COUNTRY_ID")
	@ApiModelProperty(notes="Country ID Auto Incremented")
	private Integer countryId;
	
	@Column(name = "COUNTRY_NAME", updatable=false, unique=true)
	@ApiModelProperty(notes="Country Name")
	private String countryName;
	
	@Column(name = "CURRENCY_ID")
	@ApiModelProperty(notes="Currency Code")
	private String currencyId;
	
	@Column(name = "CURRENCY_CODE")
	@ApiModelProperty(notes="Currency Code")
	private String currencyCode;
	
	@Column(name = "COUNTRY_SHORT_NAME")
	@ApiModelProperty(notes="Country Short Name")
	private String countryShortName;
	
	@Column(name = "EXCHANGE_RATE")
	@ApiModelProperty(notes="Exchange rate")
	private Double exchangeRate;
	
	@Column(name = "SYNTEL_FACILITY_COST")
	@ApiModelProperty(notes="Syntel facility cost")
	private Double syntelFacilityCost;

	@Column(name = "IS_OFFSHORE_SITE")
	@ApiModelProperty(notes="Is offshore site")
	private Integer isOffshoreSite;
	
	@Column(name = "IS_ACTIVE")
	@ApiModelProperty(notes="Active status")
	private Integer isActive;
	
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
	
	@Column(name = "Only_manual_RC_OR_Deal")
	@ApiModelProperty(notes="Is only mannual RC or Deal")
	private int isOnlyManRCDeal;
	
	@Transient
	private List<AppCodeVisaType> visaTypeArray;

	@Transient
	private List<VisaLabels> visaLabelArray;
	
	
	public Integer getCountryId() {
		return countryId;
	}

	public void setCountryId(Integer countryId) {
		this.countryId = countryId;
	}

	public String getCountryName() {
		return countryName;
	}

	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}

	public String getCurrencyId() {
		return currencyId;
	}

	public void setCurrencyId(String currencyId) {
		this.currencyId = currencyId;
	}

	public String getCurrencyCode() {
		return currencyCode;
	}

	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}

	public String getCountryShortName() {
		return countryShortName;
	}

	public void setCountryShortName(String countryShortName) {
		this.countryShortName = countryShortName;
	}

	public Double getExchangeRate() {
		return exchangeRate;
	}

	public void setExchangeRate(Double exchangeRate) {
		this.exchangeRate = exchangeRate;
	}

	public Double getSyntelFacilityCost() {
		return syntelFacilityCost;
	}

	public void setSyntelFacilityCost(Double syntelFacilityCost) {
		this.syntelFacilityCost = syntelFacilityCost;
	}

	public Integer getIsOffshoreSite() {
		return isOffshoreSite;
	}

	public void setIsOffshoreSite(Integer isOffshoreSite) {
		this.isOffshoreSite = isOffshoreSite;
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

	public List<AppCodeVisaType> getVisaTypeArray() {
		return visaTypeArray;
	}

	public void setVisaTypeArray(List<AppCodeVisaType> visaTypeArray) {
		this.visaTypeArray = visaTypeArray;
	}

	public List<VisaLabels> getVisaLabelArray() {
		return visaLabelArray;
	}

	public void setVisaLabelArray(List<VisaLabels> visaLabelArray) {
		this.visaLabelArray = visaLabelArray;
	}


	public int getIsOnlyManRCDeal() {
		return isOnlyManRCDeal;
	}

	public void setIsOnlyManRCDeal(int isOnlyManRCDeal) {
		this.isOnlyManRCDeal = isOnlyManRCDeal;
	}

	@Override
	public String toString() {
		return "Country [countryId=" + countryId + ", countryName=" + countryName + ", currencyId=" + currencyId
				+ ", currencyCode=" + currencyCode + ", countryShortName=" + countryShortName + ", exchangeRate="
				+ exchangeRate + ", syntelFacilityCost=" + syntelFacilityCost + ", isOffshoreSite=" + isOffshoreSite
				+ ", isActive=" + isActive + ", createdBy=" + createdBy + ", createdDate=" + createdDate
				+ ", updatedBy=" + updatedBy + ", updatedDate=" + updatedDate + ", isOnlyManRCDeal=" + isOnlyManRCDeal
				+ ", visaTypeArray=" + visaTypeArray + ", visaLabelArray=" + visaLabelArray + "]";
	}

	
	

}
