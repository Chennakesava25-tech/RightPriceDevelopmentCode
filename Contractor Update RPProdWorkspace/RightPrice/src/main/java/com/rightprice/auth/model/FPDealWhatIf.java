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
@ApiModel(value = "FPDeal What if Details", description = "FPDeal What if Details")
@Table(name = "synprod.RP_Deal_FP_What_If_details")
public class FPDealWhatIf implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Deal_Threshold_Id")
	private Integer dealThresholdId;
	
	
	@Column(name = "RP_Deal_Version_Id")
	private Integer rpDealVersionId;
	
	
	@Column(name = "Threshold_Particulars_Id")
	private Integer thresholdParticularsId;
	
	
	@Column(name = "Description")
	private String description;
	
	
	@Column(name = "User_Input_Value")
	private double userInputValue;
	

	@Column(name = "BUH_Threshold_Value")
	private double buhThresholdValue;
	
	@Column(name = "CEO_Approval_Value")
	private double ceoApprovalValue;
	
	
	@Column(name = "Global_Rate_Card_Value")
	private String globalRateCardValue;
	
	@Column(name = "Indicator_Flag")
	private Integer indicatorFlag;
	
	@Column(name = "Is_Active")
	private Integer isActive;
	
	@Column(name = "Created_By")
	private String createdBy;
	
	@Column(name = "Created_On")
	private String createdOn;
	
	@Column(name = "Updated_By")
	private String updatedBy;
	
	@Column(name = "Updated_On")
	private String updatedOn;

	public Integer getDealThresholdId() {
		return dealThresholdId;
	}

	public void setDealThresholdId(Integer dealThresholdId) {
		this.dealThresholdId = dealThresholdId;
	}

	public Integer getRpDealVersionId() {
		return rpDealVersionId;
	}

	public void setRpDealVersionId(Integer rpDealVersionId) {
		this.rpDealVersionId = rpDealVersionId;
	}

	public Integer getThresholdParticularsId() {
		return thresholdParticularsId;
	}

	public void setThresholdParticularsId(Integer thresholdParticularsId) {
		this.thresholdParticularsId = thresholdParticularsId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getUserInputValue() {
		return userInputValue;
	}

	public void setUserInputValue(double userInputValue) {
		this.userInputValue = userInputValue;
	}

	public double getBuhThresholdValue() {
		return buhThresholdValue;
	}

	public void setBuhThresholdValue(double buhThresholdValue) {
		this.buhThresholdValue = buhThresholdValue;
	}

	public double getCeoApprovalValue() {
		return ceoApprovalValue;
	}

	public void setCeoApprovalValue(double ceoApprovalValue) {
		this.ceoApprovalValue = ceoApprovalValue;
	}

	public String getGlobalRateCardValue() {
		return globalRateCardValue;
	}

	public void setGlobalRateCardValue(String globalRateCardValue) {
		this.globalRateCardValue = globalRateCardValue;
	}

	public Integer getIndicatorFlag() {
		return indicatorFlag;
	}

	public void setIndicatorFlag(Integer indicatorFlag) {
		this.indicatorFlag = indicatorFlag;
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
