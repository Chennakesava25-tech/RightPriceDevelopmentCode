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
@ApiModel(value="RP Fix Deal Location",description="RP Fix Deal Location")
@Table(name = "synprod.RP_FIX_Deal_Location")
public class DealFixLocation implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "Deal_auto_Tower_Id")
	@ApiModelProperty(notes="dealTowerId Auto Incremented")
	private Integer dealAutoTowerId; 
	
	@Column(name = "RP_Deal_Version_Id")
	@ApiModelProperty(notes="rpDealVersionId")
	private Integer rpDealVersionId;
	
	@Column(name = "Deal_Tower_Id")
	@ApiModelProperty(notes="Deal_Tower_Id")
	private Integer dealTowerId;
	
	@Column(name = "deal_Tower_name")
	@ApiModelProperty(notes="deal_Tower_name")
	private String dealTowerName;
	
	@Column(name = "Deal_Tower_Description")
	@ApiModelProperty(notes="Deal_Tower_Description")
	private String dealTowerDescription;
	
	@Column(name = "City_Description")
	@ApiModelProperty(notes="City_Description")
	private String description;
	
	@Column(name = "Country_Id")
	@ApiModelProperty(notes="Country_Id")
	private Integer countryId;

	@Column(name = "City_Id")
	@ApiModelProperty(notes="City_Id")
	private Integer cityId;
	
	@Column(name = "IS_ACTIVE")
	@ApiModelProperty(notes="Is Active")
	private Integer isActive;
	
/*	@Column(name = "TransitionMonth")
	@ApiModelProperty(notes="TransitionMonth")
	private Double transitionMonth;*/
	
	@Column(name = "TransitionMonth")
	private Double transitionMonth;
	
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
	@ApiModelProperty(notes="Updated On")
	private String updatedOn;

	public Integer getDealTowerId() {
		return dealTowerId;
	}

	public void setDealTowerId(Integer dealTowerId) {
		this.dealTowerId = dealTowerId;
	}

	public Integer getRpDealVersionId() {
		return rpDealVersionId;
	}

	public void setRpDealVersionId(Integer rpDealVersionId) {
		this.rpDealVersionId = rpDealVersionId;
	}
	
	public Integer getDealAutoTowerId() {
		return dealAutoTowerId;
	}

	public void setDealAutoTowerId(Integer dealAutoTowerId) {
		this.dealAutoTowerId = dealAutoTowerId;
	}

	public String getDealTowerName() {
		return dealTowerName;
	}

	public void setDealTowerName(String dealTowerName) {
		this.dealTowerName = dealTowerName;
	}

	public Integer getCountryId() {
		return countryId;
	}

	public void setCountryId(Integer countryId) {
		this.countryId = countryId;
	}

	public Integer getCityId() {
		return cityId;
	}

	public void setCityId(Integer cityId) {
		this.cityId = cityId;
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

	public String getDealTowerDescription() {
		return dealTowerDescription;
	}

	public void setDealTowerDescription(String dealTowerDescription) {
		this.dealTowerDescription = dealTowerDescription;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Double getTransitionMonth() {
		return transitionMonth;
	}

	public void setTransitionMonth(Double transitionMonth) {
		this.transitionMonth = transitionMonth;
	}

	
	
}
