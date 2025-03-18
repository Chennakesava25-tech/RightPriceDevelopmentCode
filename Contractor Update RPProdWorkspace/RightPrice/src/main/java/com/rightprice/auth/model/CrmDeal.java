package com.rightprice.auth.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@Entity
@ApiModel(value="Stages",description="Stages Model Attributes")
@Table(name = "synprod.RP_CRM_Deal")
public class CrmDeal {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "CRM_Deal_Id")
	@ApiModelProperty(notes="crm deal id Auto Incremented")
	private String crmDealId;
	
	@Column(name = "RP_Deal_Id")
	@ApiModelProperty(notes="rp deal id")
	private int rpDealId;
	
	@Column(name = "Deal_Description")
	@ApiModelProperty(notes="Deal description")
	private String dealDescription;
	
	@Column(name = "Deal_Status_Id")
	@ApiModelProperty(notes="Deal status id")
	private int dealStatusId;
	
	@OneToOne(cascade = javax.persistence.CascadeType.ALL)
    @JoinColumn(name = "DEAL_STATUS_ID",referencedColumnName = "CODE_NAME")
	private CodeApplication dealStatus;
	
	@Column(name = "Deal_Priority_Id")
	@ApiModelProperty(notes="Deal priority Id")
	private int dealPriorityId;
	
	@OneToOne(cascade = javax.persistence.CascadeType.ALL)
    @JoinColumn(name = "DEAL_PRIORITY_ID",referencedColumnName = "CODE_NAME")
	private CodeApplication dealPriority;
	
	@Column(name = "Deal_TCV")
	@ApiModelProperty(notes="Deal TCV")
	private double revenue;
	
	@Column(name = "Sales_Spoc_Names")
	@ApiModelProperty(notes="Sales spoc name")
	private String salesSpoc;
	
	@Column(name = "Customer_Id")
	@ApiModelProperty(notes="Customer Id")
	private int customerId;
	
	@Column(name = "Customer_Address")
	@ApiModelProperty(notes="Customer address")
	private String customerAddress;
	
	@Column(name = "Deal_Start_Date")
	@ApiModelProperty(notes="Deal start date")
	private String dealStartDate;
	
	@Column(name = "Deal_End_Date")
	@ApiModelProperty(notes="deal end date")
	private String dealEndDate;
	
	@Column(name = "Deal_Duration")
	@ApiModelProperty(notes="Deal duration")
	private int dealDuration;
	
	@Column(name = "Deal_Stage_Id")
	@ApiModelProperty(notes="Deal stage id")
	private int dealStageId;
	
	@OneToOne(cascade = javax.persistence.CascadeType.ALL)
    @JoinColumn(name = "DEAL_STAGE_ID",referencedColumnName = "CODE_NAME")
	private CodeApplication stage;
	
	@Column(name = "Percentage_Close")
	@ApiModelProperty(notes="Percentage Close")
	private int percentageClose;
	

/*	@Column(name = "Onsite_Work_Hrs")
	@ApiModelProperty(notes="Onsite work hours")
	private int onsiteWorkHrs;
	
	@Column(name = "Offshore_Work_Hrs")
	@ApiModelProperty(notes="Offshore Work hours")
	private int offshoreWorkHrs;*/
	
	@Column(name = "Deal_Approval_Status_Id")
	@ApiModelProperty(notes="Deal approval status id")
	private int dealApprovalStausId;
	
	@Column(name = "Current_Deal_Approver_Id")
	@ApiModelProperty(notes="Current Deal approver id")
	private int currentDealApproverId;
	
	@Column(name = "Rejection_Comments")
	@ApiModelProperty(notes="Rejection comments")
	private String rejectionComments;
	
	@Column(name = "Deal_Creation_Progress_Status_Id")
	@ApiModelProperty(notes="Deal creation progress status id")
	private int dealCreationProgressStatusId;
	
	@Column(name = "IS_ACTIVE")
	@ApiModelProperty(notes="Active status")
	private int isActive;
	
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
	


	public String getCrmDealId() {
		return crmDealId;
	}

	public void setCrmDealId(String crmDealId) {
		this.crmDealId = crmDealId;
	}

	public int getRpDealId() {
		return rpDealId;
	}

	public void setRpDealId(int rpDealId) {
		this.rpDealId = rpDealId;
	}

	public String getDealDescription() {
		return dealDescription;
	}

	public void setDealDescription(String dealDescription) {
		this.dealDescription = dealDescription;
	}

	public int getDealStatusId() {
		return dealStatusId;
	}

	public void setDealStatusId(int dealStatusId) {
		this.dealStatusId = dealStatusId;
	}

	public int getDealPriorityId() {
		return dealPriorityId;
	}

	public void setDealPriorityId(int dealPriorityId) {
		this.dealPriorityId = dealPriorityId;
	}

	/*public double getDealTcv() {
		return dealTcv;
	}

	public void setDealTcv(double dealTcv) {
		this.dealTcv = dealTcv;
	}*/

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public String getCustomerAddress() {
		return customerAddress;
	}

	public void setCustomerAddress(String customerAddress) {
		this.customerAddress = customerAddress;
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

	public int getDealDuration() {
		return dealDuration;
	}

	public void setDealDuration(int dealDuration) {
		this.dealDuration = dealDuration;
	}

	public int getDealStageId() {
		return dealStageId;
	}

	public void setDealStageId(int dealStageId) {
		this.dealStageId = dealStageId;
	}

	public int getPercentageClose() {
		return percentageClose;
	}

	public void setPercentageClose(int percentageClose) {
		this.percentageClose = percentageClose;
	}


	/*public int getOnsiteWorkHrs() {
		return onsiteWorkHrs;
	}

	public void setOnsiteWorkHrs(int onsiteWorkHrs) {
		this.onsiteWorkHrs = onsiteWorkHrs;
	}

	public int getOffshoreWorkHrs() {
		return offshoreWorkHrs;
	}

	public void setOffshoreWorkHrs(int offshoreWorkHrs) {
		this.offshoreWorkHrs = offshoreWorkHrs;
	}*/


	public int getDealApprovalStausId() {
		return dealApprovalStausId;
	}

	public void setDealApprovalStausId(int dealApprovalStausId) {
		this.dealApprovalStausId = dealApprovalStausId;
	}

	public int getCurrentDealApproverId() {
		return currentDealApproverId;
	}

	public void setCurrentDealApproverId(int currentDealApproverId) {
		this.currentDealApproverId = currentDealApproverId;
	}

	public String getRejectionComments() {
		return rejectionComments;
	}

	public void setRejectionComments(String rejectionComments) {
		this.rejectionComments = rejectionComments;
	}

	public int getDealCreationProgressStatusId() {
		return dealCreationProgressStatusId;
	}

	public void setDealCreationProgressStatusId(int dealCreationProgressStatusId) {
		this.dealCreationProgressStatusId = dealCreationProgressStatusId;
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

	public CodeApplication getDealStatus() {
		return dealStatus;
	}

	public void setDealStatus(CodeApplication dealStatus) {
		this.dealStatus = dealStatus;
	}

	public CodeApplication getDealPriority() {
		return dealPriority;
	}

	public void setDealPriority(CodeApplication dealPriority) {
		this.dealPriority = dealPriority;
	}

	public double getRevenue() {
		return revenue;
	}

	public void setRevenue(double revenue) {
		this.revenue = revenue;
	}

	public String getSalesSpoc() {
		return salesSpoc;
	}

	public void setSalesSpoc(String salesSpoc) {
		this.salesSpoc = salesSpoc;
	}

	public CodeApplication getStage() {
		return stage;
	}

	public void setStage(CodeApplication stage) {
		this.stage = stage;
	}
}
