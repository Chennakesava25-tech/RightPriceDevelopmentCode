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
@ApiModel(value="Presales",description="Presales Model Attributes")
@Table(name = "synprod.RP_Deal_Pre_Sales_Details")
public class Presales  implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "Sales_Id")
	@ApiModelProperty(notes="Sales Id Auto Incremented")
	private Integer salesId;
	
	@Column(name = "Opportunity_Id")
	@ApiModelProperty(notes="Opportunity Id")
	private Integer opportunityId;
	
	@Column(name = "Opportunity_Description")
	@ApiModelProperty(notes="Opportunity Description")
	private String opportunityDescription;
	
	@Column(name = "Deal_Type")
	@ApiModelProperty(notes="Deal Type")
	private Integer dealType;
	
	@Column(name = "Deal_Status")
	@ApiModelProperty(notes="Deal Status")
	private String dealStatus;
	
	@Column(name = "Deal_Start_Date")
	@ApiModelProperty(notes="Deal_Start_Date")
	private String dealStartDate;
	
	@Column(name = "Deal_End_Date")
	@ApiModelProperty(notes="Deal_End_Date")
	private String dealEndDate;

	@Column(name = "CRM_Customer_Id")
	@ApiModelProperty(notes="CRM Customer Id")
	private Integer cRMCustomerId;
	
	@Column(name = "Customer_Name")
	@ApiModelProperty(notes="Customer Name")
	private String customerName;
	
	@Column(name = "Finance_Customer")
	@ApiModelProperty(notes="Finance Customer")
	private String financeCustomer;
	
	@Column(name = "IRIS_Code")
	@ApiModelProperty(notes="IRIS Code")
	private String iRISCode;
	
	@Column(name = "Vertical")
	@ApiModelProperty(notes="Vertical")
	private String vertical;
	
	@Column(name = "GBU")
	@ApiModelProperty(notes="GBU")
	private String gBU;
	
	@Column(name = "Presales_WBS_Number")
	@ApiModelProperty(notes="Presales WBS Number")
	private String presalesWBSNumber;
	
	@Column(name = "Deal_Currency_Code")
	@ApiModelProperty(notes="Deal Currency Code")
	private Integer dealCurrencyCode;

	@Column(name = "Total_TCV")
	@ApiModelProperty(notes="Total TCV")
	private Double totalTCV;
	
	
	@Column(name = "Bid_Start_Date")
	@ApiModelProperty(notes="Bid_Start_Date")
	private String bidStartDate;
	
	
	@Column(name = "Bid_End_Date")
	@ApiModelProperty(notes="Bid_End_Date")
	private String bidEndDate;
	/*
	@Column(name = "Approved_TCV")
	@ApiModelProperty(notes="Approved_TCV")
	private Double approvedTCV;
	
	@Column(name = "Total_BID_Budget")
	@ApiModelProperty(notes="Total_BID_Budget")
	private Double totalBIDBudget;
	
	@Column(name = "Total_Approved_Budget")
	@ApiModelProperty(notes="Total Approved Budget")
	private Double totalApprovedBudget;
	*/
	@Column(name = "BPS_TCV")
	@ApiModelProperty(notes="BPS_TCV")
	private Double bPSTCV;
	
	@Column(name = "BPS_BID_Budget")
	@ApiModelProperty(notes="BPS BID Budget")
	private Double bPSBIDBudget;
	
	@Column(name = "BPS_Approved_BID_Budget")
	@ApiModelProperty(notes="BPS Approved BID Budget")
	private Double bPSApprovedBIDBudget;
	
	@Column(name = "LEVEL_1_APPOVAL_COMMENTS")
	@ApiModelProperty(notes="LEVEL 1 APPOVAL COMMENTS")
	private String level1ApprovalComments;
	
	@Column(name = "LEVEL_1_APPOVAL_DATE")
	@ApiModelProperty(notes="LEVEL 1 APPOVAL DATE")
	private String level1ApprovalDate;

	@Column(name = "LEVEL_2_APPROVAL_COMMENTS")
	@ApiModelProperty(notes="LEVEL 2 APPOVAL COMMENTS")
	private String level2ApprovalComments;
	
	@Column(name = "LEVEL_2_APPOVAL_DATE")
	@ApiModelProperty(notes="LEVEL 2 APPOVAL DATE")
	private String level2ApprovalDate;
	
	@Column(name = "BPS_BID_Budget_IN_Euro")
	@ApiModelProperty(notes="BPS BID Budget IN Euro")
	private Double bPSBIDBudgetINEuro;
	
	@Column(name = "BPS_TCV_IN_Euro")
	@ApiModelProperty(notes="BPS TCV IN Euro")
	private Double bPSTCVINEuro;

	@Column(name = "PERCENTAGE_of_BPS_TCV_on_Total_TCV")
	@ApiModelProperty(notes="PERCENTAGE of BPS TCV on Total TCV")
	private Double percentageofBPSTCVonTotalTCV;

	@Column(name = "Pre_Sales_Cost_Against_WBS")
	@ApiModelProperty(notes="Pre Sales Cost Against WBS")
	private Double preSalesCostAgainstWBS;
	
	@Column(name = "Pre_Sales_Cost_Against_WBS_In_Euro")
	@ApiModelProperty(notes="Pre Sales Cost Against WBS In Euro")
	private Double preSalesCostAgainstWBSInEuro;
	
	@Column(name = "BALANCE")
	@ApiModelProperty(notes="BALANCE")
	private Double balance;
	
	@Column(name = "Balance_In_Euro")
	@ApiModelProperty(notes="Balance In Euro")
	private Double balanceInEuro;
	
	@Column(name = "Comments")
	@ApiModelProperty(notes="Comments")
	private String comments;
	
	@Column(name = "status")
	@ApiModelProperty(notes="status")
	private Integer status;
	
	@Column(name = "vertical_id")
	@ApiModelProperty(notes="vertical_id")
	private Integer verticalId;
	
	@Column(name = "Status_indicator")
	@ApiModelProperty(notes="Status_indicator")
	private String statusIndicator;
	
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
	
	@Transient
	private Boolean existingAlready;

	public Integer getSalesId() {
		return salesId;
	}

	public void setSalesId(Integer salesId) {
		this.salesId = salesId;
	}

	public Integer getOpportunityId() {
		return opportunityId;
	}

	public void setOpportunityId(Integer opportunityId) {
		this.opportunityId = opportunityId;
	}

	public String getOpportunityDescription() {
		return opportunityDescription;
	}

	public void setOpportunityDescription(String opportunityDescription) {
		this.opportunityDescription = opportunityDescription;
	}
	

	public String getDealStatus() {
		return dealStatus;
	}

	public void setDealStatus(String dealStatus) {
		this.dealStatus = dealStatus;
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

	public Integer getcRMCustomerId() {
		return cRMCustomerId;
	}

	public void setcRMCustomerId(Integer cRMCustomerId) {
		this.cRMCustomerId = cRMCustomerId;
	}

	public String getCustomer_Name() {
		return customerName;
	}

	public void setCustomer_Name(String customer_Name) {
		this.customerName = customerName;
	}

	public String getFinanceCustomer() {
		return financeCustomer;
	}

	public void setFinanceCustomer(String financeCustomer) {
		this.financeCustomer = financeCustomer;
	}

	public String getiRISCode() {
		return iRISCode;
	}

	public void setiRISCode(String iRISCode) {
		this.iRISCode = iRISCode;
	}

	public String getVertical() {
		return vertical;
	}

	public void setVertical(String vertical) {
		this.vertical = vertical;
	}

	public String getgBU() {
		return gBU;
	}

	public void setgBU(String gBU) {
		this.gBU = gBU;
	}

	public String getPresalesWBSNumber() {
		return presalesWBSNumber;
	}

	public void setPresalesWBSNumber(String presalesWBSNumber) {
		this.presalesWBSNumber = presalesWBSNumber;
	}

	public Double getTotalTCV() {
		return totalTCV;
	}

	public void setTotalTCV(Double totalTCV) {
		this.totalTCV = totalTCV;
	}

/*	public Double getApprovedTCV() {
		return approvedTCV;
	}

	public void setApprovedTCV(Double approvedTCV) {
		this.approvedTCV = approvedTCV;
	}

	public Double getTotalBIDBudget() {
		return totalBIDBudget;
	}

	public void setTotalBIDBudget(Double totalBIDBudget) {
		this.totalBIDBudget = totalBIDBudget;
	}

	public Double getTotalApprovedBudget() {
		return totalApprovedBudget;
	}

	public void setTotalApprovedBudget(Double totalApprovedBudget) {
		this.totalApprovedBudget = totalApprovedBudget;
	}*/

	public Double getbPSTCV() {
		return bPSTCV;
	}

	public void setbPSTCV(Double bPSTCV) {
		this.bPSTCV = bPSTCV;
	}

	public Double getbPSBIDBudget() {
		return bPSBIDBudget;
	}

	public void setbPSBIDBudget(Double bPSBIDBudget) {
		this.bPSBIDBudget = bPSBIDBudget;
	}

	public Double getbPSApprovedBIDBudget() {
		return bPSApprovedBIDBudget;
	}

	public void setbPSApprovedBIDBudget(Double bPSApprovedBIDBudget) {
		this.bPSApprovedBIDBudget = bPSApprovedBIDBudget;
	}

	public String getLevel1ApprovalComments() {
		return level1ApprovalComments;
	}

	public void setLevel1ApprovalComments(String level1ApprovalComments) {
		this.level1ApprovalComments = level1ApprovalComments;
	}

	public String getLevel1ApprovalDate() {
		return level1ApprovalDate;
	}

	public void setLevel1ApprovalDate(String level1ApprovalDate) {
		this.level1ApprovalDate = level1ApprovalDate;
	}

	public String getLevel2ApprovalComments() {
		return level2ApprovalComments;
	}

	public void setLevel2ApprovalComments(String level2ApprovalComments) {
		this.level2ApprovalComments = level2ApprovalComments;
	}

	public String getLevel2ApprovalDate() {
		return level2ApprovalDate;
	}

	public void setLevel2ApprovalDate(String level2ApprovalDate) {
		this.level2ApprovalDate = level2ApprovalDate;
	}

	public Double getbPSBIDBudgetINEuro() {
		return bPSBIDBudgetINEuro;
	}

	public void setbPSBIDBudgetINEuro(Double bPSBIDBudgetINEuro) {
		this.bPSBIDBudgetINEuro = bPSBIDBudgetINEuro;
	}

	public Double getbPSTCVINEuro() {
		return bPSTCVINEuro;
	}

	public void setbPSTCVINEuro(Double bPSTCVINEuro) {
		this.bPSTCVINEuro = bPSTCVINEuro;
	}

	public Double getPercentageofBPSTCVonTotalTCV() {
		return percentageofBPSTCVonTotalTCV;
	}

	public void setPercentageofBPSTCVonTotalTCV(Double percentageofBPSTCVonTotalTCV) {
		this.percentageofBPSTCVonTotalTCV = percentageofBPSTCVonTotalTCV;
	}

	public Double getPreSalesCostAgainstWBS() {
		return preSalesCostAgainstWBS;
	}

	public void setPreSalesCostAgainstWBS(Double preSalesCostAgainstWBS) {
		this.preSalesCostAgainstWBS = preSalesCostAgainstWBS;
	}

	public Double getPreSalesCostAgainstWBSInEuro() {
		return preSalesCostAgainstWBSInEuro;
	}

	public void setPreSalesCostAgainstWBSInEuro(Double preSalesCostAgainstWBSInEuro) {
		this.preSalesCostAgainstWBSInEuro = preSalesCostAgainstWBSInEuro;
	}

	public Double getBalance() {
		return balance;
	}

	public void setBalance(Double balance) {
		this.balance = balance;
	}

	public Double getBalanceInEuro() {
		return balanceInEuro;
	}

	public void setBalanceInEuro(Double balanceInEuro) {
		this.balanceInEuro = balanceInEuro;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
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

	public Boolean getExistingAlready() {
		return existingAlready;
	}

	public void setExistingAlready(Boolean existingAlready) {
		this.existingAlready = existingAlready;
	}

	public Integer getDealType() {
		return dealType;
	}

	public void setDealType(Integer dealType) {
		this.dealType = dealType;
	}

	public Integer getDealCurrencyCode() {
		return dealCurrencyCode;
	}

	public void setDealCurrencyCode(Integer dealCurrencyCode) {
		this.dealCurrencyCode = dealCurrencyCode;
	}

	public Integer getVerticalId() {
		return verticalId;
	}

	public void setVerticalId(Integer verticalId) {
		this.verticalId = verticalId;
	}

	public String getStatusIndicator() {
		return statusIndicator;
	}

	public void setStatusIndicator(String statusIndicator) {
		this.statusIndicator = statusIndicator;
	}

	public String getBidStartDate() {
		return bidStartDate;
	}

	public void setBidStartDate(String bidStartDate) {
		this.bidStartDate = bidStartDate;
	}

	public String getBidEndDate() {
		return bidEndDate;
	}

	public void setBidEndDate(String bidEndDate) {
		this.bidEndDate = bidEndDate;
	}
	
}
