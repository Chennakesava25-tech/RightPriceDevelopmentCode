package com.rightprice.auth.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import io.swagger.annotations.ApiModelProperty;

@Entity
@Table(name="SYNPROD.RP_RATE_CARD_LOCATION")
public class RateCardLocation implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "RC_LOCATION_ID")
	private int rateCardLocationId;
	
	@Column(name ="RC_ID")
	@ApiModelProperty(notes="RC_ID")
	private Integer rcId;
	
	@Column(name ="CITY_ID")
	@ApiModelProperty(notes="City ID")
	private Integer cityId;
	
	@Column(name ="COUNTRY_ID")
	@ApiModelProperty(notes="COUNTRY_ID")
	private Integer countryId;
	
	@Column(name ="MANUAL_UPLOAD_ID")
	@ApiModelProperty(notes="MANUAL_UPLOAD_ID")
	private Integer manualUploadId;
	
	@OneToOne( cascade={CascadeType.ALL})
	@JoinColumn(name="CITY_ID",referencedColumnName = "CITY_ID", insertable=false, updatable=false)
	private City city;
	
	
	@Column(name = "CITY_RESOURCE_UTILIZATION")
	private double cityResourceUtilization;
	
	@Column(name="SYNTEL_FACILITY")
	private Integer syntelFacility;
	
	@Column(name="PREMIUM_AMOUNT")
	private Double premiumAmount;
	
	@Column(name="IS_OFFSHORE")
	private String isOffShore;
	
	
	public String getIsOffShore() {
		return isOffShore;
	}

	public void setIsOffShore(String isOffShore) {
		this.isOffShore = isOffShore;
	}

	@Column(name="IS_DATA_SAVED")
	private Integer isDataSaved;

	@Column(name ="IS_ACTIVE")
	@ApiModelProperty(notes="active status")
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

	
	public Integer getCityId() {
		return cityId;
	}

	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}

	public int getRateCardLocationId() {
		return rateCardLocationId;
	}

	public void setRateCardLocationId(int rateCardLocationId) {
		this.rateCardLocationId = rateCardLocationId;
	}
	

	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}


	public double getCityResourceUtilization() {
		return cityResourceUtilization;
	}

	public void setCityResourceUtilization(double cityResourceUtilization) {
		this.cityResourceUtilization = cityResourceUtilization;
	}
	
	public Integer getSyntelFacility() {
		return syntelFacility;
	}

	public void setSyntelFacility(Integer syntelFacility) {
		this.syntelFacility = syntelFacility;
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

	public Integer getCountryId() {
		return countryId;
	}

	public void setCountryId(Integer countryId) {
		this.countryId = countryId;
	}

	public Double getPremiumAmount() {
		return premiumAmount;
	}

	public void setPremiumAmount(Double premiumAmount) {
		this.premiumAmount = premiumAmount;
	}
	
	public Integer getRcId() {
		return rcId;
	}

	public void setRcId(Integer rcId) {
		this.rcId = rcId;
	}
	
	public Integer getManualUploadId() {
		return manualUploadId;
	}

	public void setManualUploadId(Integer manualUploadId) {
		this.manualUploadId = manualUploadId;
	}
	
	public Integer getIsDataSaved() {
		return isDataSaved;
	}

	public void setIsDataSaved(Integer isDataSaved) {
		this.isDataSaved = isDataSaved;
	}

	@Override
	public String toString() {
		return "RateCardLocation [rateCardLocationId=" + rateCardLocationId + ", rcId=" + rcId + ", cityId=" + cityId
				+ ", countryId=" + countryId + ", manualUploadId=" + manualUploadId + ", city=" + city
				+ ", cityResourceUtilization=" + cityResourceUtilization + ", syntelFacility=" + syntelFacility
				+ ", premiumAmount=" + premiumAmount + ", isOffShore=" + isOffShore + ", isDataSaved=" + isDataSaved
				+ ", isActive=" + isActive + ", createdBy=" + createdBy + ", createdOn=" + createdOn + ", updatedBy="
				+ updatedBy + ", updatedOn=" + updatedOn + "]";
	}



	
	
}
