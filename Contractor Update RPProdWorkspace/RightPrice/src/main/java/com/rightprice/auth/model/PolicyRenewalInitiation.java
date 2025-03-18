package com.rightprice.auth.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "TRN_INS_POLICY_RENEWAL_INTIATION")
public class PolicyRenewalInitiation {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Policy_Renewal_intiation_ID")
	private int policyRenwalInitiationId;
	
	@Column(name = "LINK_POLICY_DETAIL_ID")
	private int linkedPolicyDetailId;

	@Column(name = "CURRENT_POLICY_STAGE_ID")
	private int currentPolicyStageId;
	
	@Column(name = "POLICY_RENEWAL_START_DATE")
	private int policyRenewalStartDate;
	
	@Column(name = "ACTIVE_STATUS")
	private int activeStatus;

	@Column(name = "CREATED_BY", updatable=false)
	private String createdBy;

	@Column(name = "CREATED_ON", updatable=false)
	private String createdOn;

	@Column(name = "LAST_UPDATED_BY")
	private String lastUpdatedBy;

	@Column(name = "LAST_UPDATED_ON")
	private String lastUpdatedOn;

	public int getPolicyRenwalInitiationId() {
		return policyRenwalInitiationId;
	}

	public void setPolicyRenwalInitiationId(int policyRenwalInitiationId) {
		this.policyRenwalInitiationId = policyRenwalInitiationId;
	}

	public int getLinkedPolicyDetailId() {
		return linkedPolicyDetailId;
	}

	public void setLinkedPolicyDetailId(int linkedPolicyDetailId) {
		this.linkedPolicyDetailId = linkedPolicyDetailId;
	}

	public int getCurrentPolicyStageId() {
		return currentPolicyStageId;
	}

	public void setCurrentPolicyStageId(int currentPolicyStageId) {
		this.currentPolicyStageId = currentPolicyStageId;
	}

	public int getPolicyRenewalStartDate() {
		return policyRenewalStartDate;
	}

	public void setPolicyRenewalStartDate(int policyRenewalStartDate) {
		this.policyRenewalStartDate = policyRenewalStartDate;
	}

	public int getActiveStatus() {
		return activeStatus;
	}

	public void setActiveStatus(int activeStatus) {
		this.activeStatus = activeStatus;
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

	public String getLastUpdatedBy() {
		return lastUpdatedBy;
	}

	public void setLastUpdatedBy(String lastUpdatedBy) {
		this.lastUpdatedBy = lastUpdatedBy;
	}

	public String getLastUpdatedOn() {
		return lastUpdatedOn;
	}

	public void setLastUpdatedOn(String lastUpdatedOn) {
		this.lastUpdatedOn = lastUpdatedOn;
	}
}
