package com.rightprice.auth.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import io.swagger.annotations.ApiModelProperty;

@Entity
@Table(name = "synprod.RP_Rate_Card_Approval_Audit")
public class RateCardApprovalAudit {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Approval_Audit_Id")
	private int approvalAuditId;
	
	@Column(name = "RC_OR_Deal_Id")
	private int rcOrDealID;
	
	@Column(name = "Deal_Version_Id")
	private int dealVersionId;
	
	@Column(name = "Approver_Id")
	private String approverId;
	
	@Column(name = "Approval_Status")
	private int approvalStatus;
	
	@Column(name = "Approval_Comments")
	private String approvalComments;
	
	@Column(name = "Approval_Date")
	private String approvalDate;
	
	@Column(name = "Is_RateCard")
	private boolean isRateCard;
	
	@Column(name ="IS_ACTIVE")
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

	public int getApprovalAuditId() {
		return approvalAuditId;
	}

	public void setApprovalAuditId(int approvalAuditId) {
		this.approvalAuditId = approvalAuditId;
	}

	public int getRcOrDealID() {
		return rcOrDealID;
	}

	public void setRcOrDealID(int rcOrDealID) {
		this.rcOrDealID = rcOrDealID;
	}

	public int getDealVersionId() {
		return dealVersionId;
	}

	public void setDealVersionId(int dealVersionId) {
		this.dealVersionId = dealVersionId;
	}

	public String getApproverId() {
		return approverId;
	}

	public void setApproverId(String approverId) {
		this.approverId = approverId;
	}

	public int getApprovalStatus() {
		return approvalStatus;
	}

	public void setApprovalStatus(int approvalStatus) {
		this.approvalStatus = approvalStatus;
	}

	public String getApprovalComments() {
		return approvalComments;
	}

	public void setApprovalComments(String approvalComments) {
		this.approvalComments = approvalComments;
	}

	public String getApprovalDate() {
		return approvalDate;
	}

	public void setApprovalDate(String approvalDate) {
		this.approvalDate = approvalDate;
	}

	public boolean isRateCard() {
		return isRateCard;
	}

	public void setRateCard(boolean isRateCard) {
		this.isRateCard = isRateCard;
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
