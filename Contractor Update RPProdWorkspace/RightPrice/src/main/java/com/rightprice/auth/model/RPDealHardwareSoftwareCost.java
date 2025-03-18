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
@ApiModel(value = "RP Deal Hardware Software Cost", description = "RP Deal Hardware software Model Attributes")
@Table(name = "synprod.RP_Deal_Hardware_Software_Cost")
public class RPDealHardwareSoftwareCost implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "Deal_HARDWARE_COST_ID")
	@ApiModelProperty(notes = "Deal HardWare Cost ID Auto Incremented")
	private Integer dealHardWareCostId;
	
	@Column(name = "RP_DEAL_VERSION_ID")
	@ApiModelProperty(notes = "RP_DEAL_VERSION_ID")
	private Integer rpDealVersionId;
	
	@Column(name = "HARDWARE_COST_ID")
	@ApiModelProperty(notes = "HARDWARE_COST_ID")
	private Integer hardwareCostId;
	
	@Column(name = "HARDWARE_COST_DESCRIPTION")
	@ApiModelProperty(notes = "HARDWARE_COST_DESCRIPTION")
	private String hardwareCostDescription;
	
	@Column(name = "HARDWARE_COST_VALUE")
	@ApiModelProperty(notes = "HARDWARE_COST_VALUE")
	private Double hardwareCostValue;
	
	@Column(name = "HARDWARE_QUANTITY")
	@ApiModelProperty(notes = "HARDWARE_QUANTITY")
	private Integer hardwareQuantity;

	@Column(name = "HARDWARE_AMOUNT")
	@ApiModelProperty(notes = "HARDWARE_AMOUNT")
	private Double hardwareAmount;
	
	@Column(name = "COUNTRY_ID")
	@ApiModelProperty(notes = "COUNTRY_ID")
	private Integer countryId;
	
	@Column(name = "CITY_ID")
	@ApiModelProperty(notes = "CITY_ID")
	private Integer cityId;
	
	@Column(name = "TOWER_ID")
	@ApiModelProperty(notes = "TOWER_ID")
	private Integer towerId;
	
	@Column(name = "TIME_FLAG")
	@ApiModelProperty(notes = "TIME_FLAG")
	private char timeFlag;
	
	@Column(name = "DURATION")
	@ApiModelProperty(notes = "TIME_FLAG")
	private Integer duration;
	
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
	
	public Integer getDealHardWareCostId() {
		return dealHardWareCostId;
	}

	public void setDealHardWareCostId(Integer dealHardWareCostId) {
		this.dealHardWareCostId = dealHardWareCostId;
	}

	public Integer getRpDealVersionId() {
		return rpDealVersionId;
	}

	public void setRpDealVersionId(Integer rpDealVersionId) {
		this.rpDealVersionId = rpDealVersionId;
	}

	public Integer getHardwareCostId() {
		return hardwareCostId;
	}

	public void setHardwareCostId(Integer hardwareCostId) {
		this.hardwareCostId = hardwareCostId;
	}

	public String getHardwareCostDescription() {
		return hardwareCostDescription;
	}

	public void setHardwareCostDescription(String hardwareCostDescription) {
		this.hardwareCostDescription = hardwareCostDescription;
	}

	public Double getHardwareCostValue() {
		return hardwareCostValue;
	}

	public void setHardwareCostValue(Double hardwareCostValue) {
		this.hardwareCostValue = hardwareCostValue;
	}

	public Integer getHardwareQuantity() {
		return hardwareQuantity;
	}

	public void setHardwareQuantity(Integer hardwareQuantity) {
		this.hardwareQuantity = hardwareQuantity;
	}

	public Double getHardwareAmount() {
		return hardwareAmount;
	}

	public void setHardwareAmount(Double hardwareAmount) {
		this.hardwareAmount = hardwareAmount;
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

	public Integer getTowerId() {
		return towerId;
	}

	public void setTowerId(Integer towerId) {
		this.towerId = towerId;
	}
	
	public Integer getDuration() {
		return duration;
	}

	public void setDuration(Integer duration) {
		this.duration = duration;
	}

	public char getTimeFlag() {
		return timeFlag;
	}

	public void setTimeFlag(char timeFlag) {
		this.timeFlag = timeFlag;
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