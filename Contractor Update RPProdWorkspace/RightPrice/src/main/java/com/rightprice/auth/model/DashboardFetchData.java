package com.rightprice.auth.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class DashboardFetchData {

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "RP_Deal_Version_Id")
	private int rpDealVersionId;
	
	@Column(name = "CRM_Deal_Id")
	private String crmDealId;
	
	@Column(name = "Customer_Name")
	private String customerName;
	
	@Column(name = "deal_description")
	private String dealDescription;
	
	@Column(name = "Deal_Type_Id")
	private Integer dealTypeId;
	
	@Column(name = "Is_Syntel_Onsite_Facility_Used")
	private Integer isSyntelOnsiteFAcilityUsed;
	
	@Column(name = "Deal_Start_Date")
	private String dealStartDate;
	
	@Column(name = "Deal_End_Date")
	private String dealEndDate;
	
	@Column(name = "Deal_TCV")
	private Integer dealTcv;
	
	@Column(name = "Deal_Progress_Status_Id")
	private Integer dealProgressStatusId;
	
	@Column(name = "Current_Approval_Status")
	private Integer currentApprovalStatus;
	
	@Column(name = "Current_Approver_Id")
	private Integer currentApproverId;
	

	public int getRpDealVersionId() {
		return rpDealVersionId;
	}

	public void setRpDealVersionId(int rpDealVersionId) {
		this.rpDealVersionId = rpDealVersionId;
	}



	public String getCrmDealId() {
		return crmDealId;
	}

	public void setCrmDealId(String crmDealId) {
		this.crmDealId = crmDealId;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getDealDescription() {
		return dealDescription;
	}

	public void setDealDescription(String dealDescription) {
		this.dealDescription = dealDescription;
	}

	public Integer getDealTypeId() {
		return dealTypeId;
	}

	public void setDealTypeId(Integer dealTypeId) {
		this.dealTypeId = dealTypeId;
	}

	public Integer getIsSyntelOnsiteFAcilityUsed() {
		return isSyntelOnsiteFAcilityUsed;
	}

	public void setIsSyntelOnsiteFAcilityUsed(Integer isSyntelOnsiteFAcilityUsed) {
		this.isSyntelOnsiteFAcilityUsed = isSyntelOnsiteFAcilityUsed;
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

	public Integer getDealTcv() {
		return dealTcv;
	}

	public void setDealTcv(Integer dealTcv) {
		this.dealTcv = dealTcv;
	}

	public Integer getDealProgressStatusId() {
		return dealProgressStatusId;
	}

	public void setDealProgressStatusId(Integer dealProgressStatusId) {
		this.dealProgressStatusId = dealProgressStatusId;
	}

	public Integer getCurrentApprovalStatus() {
		return currentApprovalStatus;
	}

	public void setCurrentApprovalStatus(Integer currentApprovalStatus) {
		this.currentApprovalStatus = currentApprovalStatus;
	}

	public Integer getCurrentApproverId() {
		return currentApproverId;
	}

	public void setCurrentApproverId(Integer currentApproverId) {
		this.currentApproverId = currentApproverId;
	}
	
}
