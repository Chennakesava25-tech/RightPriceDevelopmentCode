package com.rightprice.auth.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "TRN_INS_PR_PO_MAPPING")
public class PrPoDetails {
	@Id
	@GeneratedValue
	@Column(name = "PR_PO_MAPPING_ID")
	private int prPoMappingId;
	
	@Column(name = "POLICY_DETAILS_ID")
	private int policyDetailsId;
	
	@Column(name = "PR_NUMBER")
	private String prNum;
	
	@Column(name = "PR_AMOUNT")
	private double prAmt;
	
	@Column(name = "PO_NUMBER")
	private String poNum;
	
	@Column(name = "PO_AMOUNT")
	private double poAmt;
	
	@Column(name = "PR_PO_POLICY_STATUS")
	private int prPoStatus;

	@Column(name = "ACTIVE_STATUS")
	private int activeStatus = 1;

	@Column(name = "CREATED_BY")
	private String createdBy ="PA5027293";

	@Column(name = "CREATED_ON")
	private String createdOn = "2017-08-22 18:57:17";

	@Column(name = "LAST_UPDATED_BY")
	private String lastUpdatedBy ="PA5027293";

	@Column(name = "LAST_UPDATED_ON")
	private String lastUpdatedOn = "2017-08-22 18:57:17";

	public int getPrPoMappingId() {
		return prPoMappingId;
	}
	
	public void setPrPoMappingId(int prPoMappingId) {
		this.prPoMappingId = prPoMappingId;
	}
	
	public int getPolicyDetailsId() {
		return policyDetailsId;
	}

	public void setPolicyDetailsId(int policyDetailsId) {
		this.policyDetailsId = policyDetailsId;
	}

	/*public int getPrPoPolicyStatus() {
		return prPoPolicyStatus;
	}
	
	public void setPrPoPolicyStatus(int prPoPolicyStatus) {
		this.prPoPolicyStatus = prPoPolicyStatus;
	}*/

	public String getPrNum() {
		return prNum;
	}

	public int getPrPoStatus() {
		return prPoStatus;
	}

	public void setPrPoStatus(int prPoStatus) {
		this.prPoStatus = prPoStatus;
	}

	public void setPrNum(String prNum) {
		this.prNum = prNum;
	}

	public double getPrAmt() {
		return prAmt;
	}

	public void setPrAmt(double prAmt) {
		this.prAmt = prAmt;
	}

	public String getPoNum() {
		return poNum;
	}

	public void setPoNum(String poNum) {
		this.poNum = poNum;
	}

	public double getPoAmt() {
		return poAmt;
	}

	public void setPoAmt(double poAmt) {
		this.poAmt = poAmt;
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
