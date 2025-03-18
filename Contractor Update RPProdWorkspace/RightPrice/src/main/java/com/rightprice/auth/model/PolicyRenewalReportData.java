package com.rightprice.auth.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class PolicyRenewalReportData {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "POLICY_DETAILS_ID")
	private String policyDetailsId;
	
	@Column(name = "COUNTRY_NAME")
	private String countryName;
	
	@Column(name = "CATEGORY_NAME")
	private String policyCategoryName;
	
	@Column(name = "SYNTEL_ENTITY")
	private String syntelEntity;
	
	@Column(name = "POLICY_NAME")
	private String policyName;
	
	@Column(name = "POLICY_STATUS")
	private int policyStatusId;
	
	@Column(name = "PREMIUM_AMT")
	private double premiumAmt;
	
	@Column(name = "POLICY_START_DATE")
	private String policyStartDate;

	@Column(name = "POLICY_END_DATE")
	private String policyEndDate;
	
	@Column(name = "INSURER_NAME")
	private String insurerName;

	@Column(name = "BROKER_NAME")
	private String brokerName;
	
	@Column(name = "CURRENT_POLICY_STAGE_ID")
	private String currentPolicyStageId;

	@Column(name = "COUNTRY_ID")
	private int countryId;

	@Column(name = "POLICY_CATEGORY_ID")
	private int policyCategoryId;

	@Column(name = "POLICY_NAME_ID")
	private int policyNameId;
	
	@Column(name = "SYNTEL_ENTITY_ID")
	private int syntelEntityId;
	
	@Column(name = "DIFF_IN_DAYS")
	private int diffInDays;
	
	@Column(name = "LINK_POLICY_DETAIL_ID")
	private String linkPolicyDetailId;
	/*@Column(name = "POLICY_NAME")
	private String policyName;*/
	
	public String getPolicyDetailsId() {
		return policyDetailsId;
	}

	public void setPolicyDetailsId(String policyDetailsId) {
		this.policyDetailsId = policyDetailsId;
	}

	public String getCountryName() {
		return countryName;
	}

	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}

	public String getPolicyCategoryName() {
		return policyCategoryName;
	}

	public void setPolicyCategoryName(String policyCategoryName) {
		this.policyCategoryName = policyCategoryName;
	}

	public String getSyntelEntity() {
		return syntelEntity;
	}

	public void setSyntelEntity(String syntelEntity) {
		this.syntelEntity = syntelEntity;
	}

	public String getPolicyName() {
		return policyName;
	}

	public void setPolicyName(String policyName) {
		this.policyName = policyName;
	}

	public int getPolicyStatusId() {
		return policyStatusId;
	}

	public void setPolicyStatusId(int policyStatusId) {
		this.policyStatusId = policyStatusId;
	}

	public double getPremiumAmt() {
		return premiumAmt;
	}

	public void setPremiumAmt(double premiumAmt) {
		this.premiumAmt = premiumAmt;
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

	public String getInsurerName() {
		return insurerName;
	}

	public void setInsurerName(String insurerName) {
		this.insurerName = insurerName;
	}

	public String getBrokerName() {
		return brokerName;
	}

	public void setBrokerName(String brokerName) {
		this.brokerName = brokerName;
	}

	public String getCurrentPolicyStageId() {
		return currentPolicyStageId;
	}

	public void setCurrentPolicyStageId(String currentPolicyStageId) {
		this.currentPolicyStageId = currentPolicyStageId;
	}

	public int getCountryId() {
		return countryId;
	}

	public void setCountryId(int countryId) {
		this.countryId = countryId;
	}

	public int getPolicyCategoryId() {
		return policyCategoryId;
	}

	public void setPolicyCategoryId(int policyCategoryId) {
		this.policyCategoryId = policyCategoryId;
	}

	public int getPolicyNameId() {
		return policyNameId;
	}

	public void setPolicyNameId(int policyNameId) {
		this.policyNameId = policyNameId;
	}

	public int getSyntelEntityId() {
		return syntelEntityId;
	}

	public void setSyntelEntityId(int syntelEntityId) {
		this.syntelEntityId = syntelEntityId;
	}

	public int getDiffInDays() {
		return diffInDays;
	}

	public void setDiffInDays(int diffInDays) {
		this.diffInDays = diffInDays;
	}

	public String getLinkPolicyDetailId() {
		return linkPolicyDetailId;
	}

	public void setLinkPolicyDetailId(String linkPolicyDetailId) {
		this.linkPolicyDetailId = linkPolicyDetailId;
	}
}
