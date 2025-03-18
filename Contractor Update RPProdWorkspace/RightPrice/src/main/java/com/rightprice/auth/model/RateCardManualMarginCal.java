package com.rightprice.auth.model;

import java.io.Serializable;

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
@ApiModel(value="Rate_Card_Manual_Margin",description="Rate_Card_Manual_Margin")
@Table(name = "synprod.RP_Rate_Card_ManualUpload_Margin_Cal")
public class RateCardManualMarginCal implements Serializable {

	private static final long serialVersionUID = 1L;


	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	
	@Column(name = "Upload_key_ID")
	@ApiModelProperty(notes="upload_Key_Id")
	private Integer uploadKeyId;
	
	
	@Column(name = "RC_Id")
	@ApiModelProperty(notes="RC_Id")
	private Integer rcId;

	@Column(name = "RC_Year")
	@ApiModelProperty(notes="RC_Year")
	private Integer rcYear;
	
	@Column(name = "RC_Country_Id")
	@ApiModelProperty(notes="RC_Country_Id")
	private Integer rcCountryId;
	
	@Column(name = "RC_City_Id")
	@ApiModelProperty(notes="RC_City_Id")
	private Integer rcCityId;

	@Column(name = "Onsite_Rate_Hour")
	@ApiModelProperty(notes="Onsite_Rate_Hour")
	private Double onsiteRateHour;

	@Column(name = "Onsite_Cost_Hour")
	@ApiModelProperty(notes="Onsite_Cost_Hour")
	private Double onsiteCostHour;
	
	@Column(name = "Offshore_Rate_Hour")
	@ApiModelProperty(notes="Offshore_Rate_Hour")
	private Double offshoreRateHour;
	
	@Column(name = "Offshore_Cost_Hour")
	@ApiModelProperty(notes="Offshore_Cost_Hour")
	private Double offshoreCostHour;

	@Column(name = "Onsite_Master_Bleneded_Rate")
	@ApiModelProperty(notes="Onsite_Master_Bleneded_Rate")
	private Double onsiteMasterBlendedRate;

	@Column(name = "Offshore_Master_Blended_Rate")
	@ApiModelProperty(notes="Offshore_Master_Blended_Rate")
	private Double offshoreMasterBlendedRate;

	@Column(name = "Onsite_Master_Blended_Cost")
	@ApiModelProperty(notes="Onsite_Master_Blended_Cost")
	private Double onsiteMasterBlendedCost;
	
	@Column(name = "Offshore_Master_Bleneded_Cost")
	@ApiModelProperty(notes="Offshore_Master_Bleneded_Cost")
	private Double offshoreMasterBlendedCost;
	
	@Column(name = "GM_After_discount")
	@ApiModelProperty(notes="GM_After_discount")
	private Double gmAfterDiscount;
	
	@Column(name = "GM_before_discount")
	@ApiModelProperty(notes="GM_before_discount")
	private Double gmBeforeDiscount;

	@Column(name = "Is_Active")
	private Integer isActive;
	
	//@Column(name = "CREATED_BY", updatable=false)
	@Column(name = "CREATED_BY")
	private String createdBy;
	
	//@Column(name = "CREATED_ON", updatable=false)
	@Column(name = "CREATED_ON")
	private String createdOn;
	
	@Column(name = "UPDATED_BY")
	private String UpdatedBy;
	
	@Column(name = "UPDATED_ON")
	private String UpdatedOn;
	
	@Transient
	private Integer manualUploadId;

	public Integer getUploadKeyId() {
		return uploadKeyId;
	}

	public void setUploadKeyId(Integer uploadKeyId) {
		this.uploadKeyId = uploadKeyId;
	}

	public Integer getRcId() {
		return rcId;
	}

	public void setRcId(Integer rcId) {
		this.rcId = rcId;
	}

	public Integer getRcYear() {
		return rcYear;
	}

	public void setRcYear(Integer rcYear) {
		this.rcYear = rcYear;
	}

	public Integer getRcCountryId() {
		return rcCountryId;
	}

	public void setRcCountryId(Integer rcCountryId) {
		this.rcCountryId = rcCountryId;
	}

	public Integer getRcCityId() {
		return rcCityId;
	}

	public void setRcCityId(Integer rcCityId) {
		this.rcCityId = rcCityId;
	}

	public Double getOnsiteRateHour() {
		return onsiteRateHour;
	}

	public void setOnsiteRateHour(Double onsiteRateHour) {
		this.onsiteRateHour = onsiteRateHour;
	}

	public Double getOnsiteCostHour() {
		return onsiteCostHour;
	}

	public void setOnsiteCostHour(Double onsiteCostHour) {
		this.onsiteCostHour = onsiteCostHour;
	}

	public Double getOffshoreRateHour() {
		return offshoreRateHour;
	}

	public void setOffshoreRateHour(Double offshoreRateHour) {
		this.offshoreRateHour = offshoreRateHour;
	}

	public Double getOffshoreCostHour() {
		return offshoreCostHour;
	}

	public void setOffshoreCostHour(Double offshoreCostHour) {
		this.offshoreCostHour = offshoreCostHour;
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
		return UpdatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		UpdatedBy = updatedBy;
	}

	public String getUpdatedOn() {
		return UpdatedOn;
	}

	public void setUpdatedOn(String updatedOn) {
		UpdatedOn = updatedOn;
	}

	public int getManualUploadId() {
		return manualUploadId;
	}

	public void setManualUploadId(int manualUploadId) {
		this.manualUploadId = manualUploadId;
	}

	public Double getOnsiteMasterBlendedRate() {
		return onsiteMasterBlendedRate;
	}

	public void setOnsiteMasterBlendedRate(Double onsiteMasterBlendedRate) {
		this.onsiteMasterBlendedRate = onsiteMasterBlendedRate;
	}

	public Double getOnsiteMasterBlendedCost() {
		return onsiteMasterBlendedCost;
	}

	public void setOnsiteMasterBlendedCost(Double onsiteMasterBlendedCost) {
		this.onsiteMasterBlendedCost = onsiteMasterBlendedCost;
	}

	public Double getOffshoreMasterBlendedRate() {
		return offshoreMasterBlendedRate;
	}

	public void setOffshoreMasterBlendedRate(Double offshoreMasterBlendedRate) {
		this.offshoreMasterBlendedRate = offshoreMasterBlendedRate;
	}

	public Double getOffshoreMasterBlendedCost() {
		return offshoreMasterBlendedCost;
	}

	public void setOffshoreMasterBlendedCost(Double offshoreMasterBlendedCost) {
		this.offshoreMasterBlendedCost = offshoreMasterBlendedCost;
	}

	public Double getGmAfterDiscount() {
		return gmAfterDiscount;
	}

	public void setGmAfterDiscount(Double gmAfterDiscount) {
		this.gmAfterDiscount = gmAfterDiscount;
	}

	public Double getGmBeforeDiscount() {
		return gmBeforeDiscount;
	}

	public void setGmBeforeDiscount(Double gmBeforeDiscount) {
		this.gmBeforeDiscount = gmBeforeDiscount;
	}
}
