package com.rightprice.auth.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="MST_POLICY_STAGE")
public class PolicyStages {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "POLICY_STAGE_ID")
	private int policystageId;
	
	@Column(name = "POLICY_STAGE_DESC")
	private String policyStageDescription;
	
	@Column(name = "RENEWAL_ETA")
	private String renewalEta;
	
	@Column(name = "EST_DAYS_TO_COMPLETE_PHASE")
	private String estimatedDays;

	public int getPolicystageId() {
		return policystageId;
	}

	public void setPolicystageId(int policystageId) {
		this.policystageId = policystageId;
	}

	public String getPolicyStageDescription() {
		return policyStageDescription;
	}

	public void setPolicyStageDescription(String policyStageDescription) {
		this.policyStageDescription = policyStageDescription;
	}

	public String getRenewalEta() {
		return renewalEta;
	}

	public void setRenewalEta(String renewalEta) {
		this.renewalEta = renewalEta;
	}

	public String getEstimatedDays() {
		return estimatedDays;
	}

	public void setEstimatedDays(String estimatedDays) {
		this.estimatedDays = estimatedDays;
	}
}
