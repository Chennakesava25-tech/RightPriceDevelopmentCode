package com.rightprice.auth.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
/*@Table(name = "TRN_INS_POLICY_DETAILS")*/
public class InsurancePolicyView {
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "POLICY_DETAILS_ID")
	private String policyDetailsId;
	
	@Column(name = "POLICY_STATUS")
	private String policyStatus;

	
	@Column(name = "POLICY_NUMBER")
	private String policyNumber;

	@Column(name = "POLICY_NAME")
	private String policyName;

	@Column(name = "POLICY_START_DATE")
	private String policyStartDate;

	@Column(name = "POLICY_END_DATE")
	private String policyEndDate;
	
	@Column(name = "DESCRIPTION")
	private String description;

	public String getPolicyStatus() {
		return policyStatus;
	}

	public void setPolicyStatus(String policyStatus) {
		this.policyStatus = policyStatus;
	}

	public String getPolicyNumber() {
		return policyNumber;
	}

	public void setPolicyNumber(String policyNumber) {
		this.policyNumber = policyNumber;
	}

	public String getPolicyName() {
		return policyName;
	}

	public void setPolicyName(String policyName) {
		this.policyName = policyName;
	}

	public String getPolicyStartDate() {
		return policyStartDate;
	}

	public void setPolicyStartDate(String policyStartDate) {
		this.policyStartDate = policyStartDate;
	}

	public String getPolicyEndDate() {
		return policyEndDate;
	}

	public void setPolicyEndDate(String policyEndDate) {
		this.policyEndDate = policyEndDate;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPolicyDetailsId() {
		return policyDetailsId;
	}

	public void setPolicyDetailsId(String policyDetailsId) {
		this.policyDetailsId = policyDetailsId;
	}

}
