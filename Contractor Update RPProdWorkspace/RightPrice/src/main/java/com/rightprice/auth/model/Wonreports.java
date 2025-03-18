package com.rightprice.auth.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Wonreports {
	
	
	@Id
   // @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "Opportunity_Id")
	private String opportunityId;
	
	@Column(name = "Atos_Opportunity_ID")
	private String atosOpportunityID;
	
	@Column(name = "Vertical_Name")
	private String vertical;
	
	@Column(name = "Opportunity_Name")
	private String opportunityname;
	
	@Column(name = "Syntel_Account_Name")
	private String syntelaccountname;
	
	@Column(name = "Account_Name")
	private String accountname;
	

	@Column(name = "Item_Order_Entry_Currency")
	private String currency;
	
	@Column(name = "Atos_Account_ID")
	private String atosaccountid;
	
	@Column(name = "Syntel_Account_ID")
	private String syntelaccountid;
	

	@Column(name = "Delivery_Start_Date")
	private String startDate;
	

	@Column(name = "Delivery_End_Date")
	private String endDate;
	
	@Column(name = "Billing_type")
	private String billingtype;

	@Column(name = "Estimated_GM")
	private Double estimatedGM;
	

	@Column(name = "Item_Order_Entry")
	private Double opportunityvalue;
	
	@Column(name = "Phase")
	private String phase;
	
	@Column(name = "Opportunity_Type")
	private String opportunitytype;

	@Column(name = "Duration_of_Service_Delivery_months")
	private Integer durationofServiceDeliverymonths;
	

	@Column(name = "opportunity_Sales_Lead")
	private String owner;
	
	@Column(name = "Closing_Date")
	private String closingDate;
	
	@Column(name = "Created_Date")
	private String createdDate;
	
	@Column(name = "Status")
	private String status;

	public String getOpportunityId() {
		return opportunityId;
	}

	public void setOpportunityId(String opportunityId) {
		this.opportunityId = opportunityId;
	}

	public String getAtosOpportunityID() {
		return atosOpportunityID;
	}

	public void setAtosOpportunityID(String atosOpportunityID) {
		this.atosOpportunityID = atosOpportunityID;
	}

	public String getVertical() {
		return vertical;
	}

	public void setVertical(String vertical) {
		this.vertical = vertical;
	}

	public String getOpportunityname() {
		return opportunityname;
	}

	public void setOpportunityname(String opportunityname) {
		this.opportunityname = opportunityname;
	}

	public String getSyntelaccountname() {
		return syntelaccountname;
	}

	public void setSyntelaccountname(String syntelaccountname) {
		this.syntelaccountname = syntelaccountname;
	}

	
	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getAtosaccountid() {
		return atosaccountid;
	}

	public void setAtosaccountid(String atosaccountid) {
		this.atosaccountid = atosaccountid;
	}

	

	public String getSyntelaccountid() {
		return syntelaccountid;
	}

	public void setSyntelaccountid(String syntelaccountid) {
		this.syntelaccountid = syntelaccountid;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getBillingtype() {
		return billingtype;
	}

	public void setBillingtype(String billingtype) {
		this.billingtype = billingtype;
	}

	public Double getEstimatedGM() {
		return estimatedGM;
	}

	public void setEstimatedGM(Double estimatedGM) {
		this.estimatedGM = estimatedGM;
	}

	public Double getOpportunityvalue() {
		return opportunityvalue;
	}

	public void setOpportunityvalue(Double opportunityvalue) {
		this.opportunityvalue = opportunityvalue;
	}

	public String getPhase() {
		return phase;
	}

	public void setPhase(String phase) {
		this.phase = phase;
	}

	public String getOpportunitytype() {
		return opportunitytype;
	}

	public void setOpportunitytype(String opportunitytype) {
		this.opportunitytype = opportunitytype;
	}

	public Integer getDurationofServiceDeliverymonths() {
		return durationofServiceDeliverymonths;
	}

	public void setDurationofServiceDeliverymonths(Integer durationofServiceDeliverymonths) {
		this.durationofServiceDeliverymonths = durationofServiceDeliverymonths;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getClosingDate() {
		return closingDate;
	}

	public void setClosingDate(String closingDate) {
		this.closingDate = closingDate;
	}

	public String getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}

	public String getAccountname() {
		return accountname;
	}

	public void setAccountname(String accountname) {
		this.accountname = accountname;
	}

	
}
