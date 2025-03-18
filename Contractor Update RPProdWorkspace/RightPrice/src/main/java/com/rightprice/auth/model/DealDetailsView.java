package com.rightprice.auth.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import io.swagger.annotations.ApiModelProperty;

@Entity
public class DealDetailsView {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "CRM_DEAL_ID")
	private String dealId;
	

	@Column(name = "Client_RC_Id")
	@ApiModelProperty(notes="client RC id")
	private Integer clientRcId;
	
	@Column(name = "Deal_Description")
	private String dealDescription;
	
	@Column(name = "Deal_Start_Date")
	private String dealStartDate;
	
	@Column(name = "Deal_End_Date")
	private String dealEndDate;

	@Column(name = "FP_Type")
	private Integer fpType;
	
	@Column(name = "Deal_Type_Id")
	private Integer dealTypeId;
	
	/*@Column(name = "customer_Id")
	private Integer customerId;
	
	public Integer getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}*/

	@Column(name = "customer_Name")
	private String customerName;
	
	@Column(name = "currency")
	private String currency;
	
	@Column(name = "DealStatus")
	private String dealStatus;
	
	public String getDealTCV() {
		return dealTCV;
	}

	public void setDealTCV(String dealTCV) {
		this.dealTCV = dealTCV;
	}
	@Column(name = "Percentage_Close")
	private Integer percentageClose;
	
	@Column(name ="stages")
	private String stages;

	@Column(name = "Penalty_Percent")
	private String penaltyPercentage;
	@Column(name = "Deal_TCV")
	private String dealTCV;
	
	@Column(name="Deal_Version")
	private String dealVersion;
	
	@Column(name="Current_Approval_Level")
	private String currentApprovalLevel;

	@Column(name="Current_Approval_Status")
	private Integer currentApprovalStatus;
	
	@Column(name = "Project_Industry")
	private int projectIndustry;
	
	public int getProjectIndustry() {
		return projectIndustry;
	}

	public void setProjectIndustry(int projectIndustry) {
		this.projectIndustry = projectIndustry;
	}

	public String getCurrentApprovalLevel() {
		return currentApprovalLevel;
	}

	public void setCurrentApprovalLevel(String currentApprovalLevel) {
		this.currentApprovalLevel = currentApprovalLevel;
	}

	public String getDealVersion() {
		return dealVersion;
	}

	public void setDealVersion(String dealVersion) {
		this.dealVersion = dealVersion;
	}

	public String getPenaltyPercentage() {
		return penaltyPercentage;
	}

	public void setPenaltyPercentage(String penaltyPercentage) {
		this.penaltyPercentage = penaltyPercentage;
	}


	public String getDealId() {
		return dealId;
	}

	public void setDealId(String dealId) {
		this.dealId = dealId;
	}

	public String getDealDescription() {
		return dealDescription;
	}

	public void setDealDescription(String dealDescription) {
		this.dealDescription = dealDescription;
	}

	public String getDealStartDate() {
		return dealStartDate;
	}

	public void setDealStartDate(String dealStartDate) {
		this.dealStartDate = dealStartDate;
	}

	public String getDealEndDate() {
		return dealEndDate;
	}

	public void setDealEndDate(String dealEndDate) {
		this.dealEndDate = dealEndDate;
	}

	public Integer getFpType() {
		return fpType;
	}

	public void setFpType(Integer fpType) {
		this.fpType = fpType;
	}

	public Integer getDealTypeId() {
		return dealTypeId;
	}

	public void setDealTypeId(Integer dealTypeId) {
		this.dealTypeId = dealTypeId;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getDealStatus() {
		return dealStatus;
	}

	public void setDealStatus(String dealStatus) {
		this.dealStatus = dealStatus;
	}

	public Integer getPercentageClose() {
		return percentageClose;
	}

	public void setPercentageClose(Integer percentageClose) {
		this.percentageClose = percentageClose;
	}

	public String getStages() {
		return stages;
	}

	public Integer getCurrentApprovalStatus() {
		return currentApprovalStatus;
	}

	public void setCurrentApprovalStatus(Integer currentApprovalStatus) {
		this.currentApprovalStatus = currentApprovalStatus;
	}

	public void setStages(String stages) {
		this.stages = stages;
	}

	public Integer getClientRcId() {
		return clientRcId;
	}

	public void setClientRcId(Integer clientRcId) {
		this.clientRcId = clientRcId;
	}
	
}
