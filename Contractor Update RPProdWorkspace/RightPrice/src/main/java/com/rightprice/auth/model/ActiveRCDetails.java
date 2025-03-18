package com.rightprice.auth.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class ActiveRCDetails implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "RC_ID")
	private Integer rcId;

	@Column(name = "Vertical_Name")
	private String vertical;

	@Column(name = "Master_Role_Name")
	private String masterRole;
	
	@Column(name = "RC_Name")
	private String RCName;
	
	@Column(name = "Customer_Id")
	private Integer customerId;
	
	@Column(name = "Customer_Name")
	private String customerName;
	
	@Column(name = "Onsite_Percentage")
	private Integer onsitePer;
	
	@Column(name = "Offshore_Percentage")
	private Integer offshorePer;
	
	@Column(name = "Volume_Discount")
	private Integer volumeDiscount;
	
	@Column(name = "TCV")
	private Integer tcv;
	
	@Column(name = "Practice_Name")
	private String practiceName;
	
	@Column(name = "Sub_Practice_Name")
	private String subPracticeName;
	
	@Column(name = "Proficiency_Level_Name")
	private String proficiencyLevelName;
	
	@Column(name = "Client_Role")
	private String Client_Role;
	
	@Column(name = "Syntel_Role_Description")
	private String syntelRoleDescription;
	
	@Column(name = "Onsite_Usage")
	private Integer onsiteUsage;

	@Column(name = "Offshore_Usage")
	private Integer offshoreUsage;
	
	@Column(name = "Onsite_Proposed_Client_Rate")
	private Integer onsiteProposedClientRate;

	@Column(name = "Offshore_Proposed_Client_Rate")
	private Integer offshoreProposedClientRate;
	
	@Column(name = "RC_Start_Date")
	private String RCStartDate;
	
	@Column(name = "RC_End_Date")
	private String RCEndDate;
	
	@Column(name = "Expected_RC_End_Date")
	private String ExpectedEndDate;
	
	@Column(name = "Country_Name")
	private String countryName;
	
	@Column(name = "City_Name")
	private String cityName;
	
	@Column(name = "Categorization_Id")
	private String categorizationId;
	
	@Column(name = "Categorization_Name")
	private String categorization_Name;

	public Integer getRcId() {
		return rcId;
	}

	public void setRcId(Integer rcId) {
		this.rcId = rcId;
	}

	public String getVertical() {
		return vertical;
	}

	public void setVertical(String vertical) {
		this.vertical = vertical;
	}

	public String getMasterRole() {
		return masterRole;
	}

	public void setMasterRole(String masterRole) {
		this.masterRole = masterRole;
	}

	public String getRCName() {
		return RCName;
	}

	public void setRCName(String rCName) {
		RCName = rCName;
	}

	public Integer getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public Integer getOnsitePer() {
		return onsitePer;
	}

	public void setOnsitePer(Integer onsitePer) {
		this.onsitePer = onsitePer;
	}

	public Integer getOffshorePer() {
		return offshorePer;
	}

	public void setOffshorePer(Integer offshorePer) {
		this.offshorePer = offshorePer;
	}

	public Integer getVolumeDiscount() {
		return volumeDiscount;
	}

	public void setVolumeDiscount(Integer volumeDiscount) {
		this.volumeDiscount = volumeDiscount;
	}

	public Integer getTcv() {
		return tcv;
	}

	public void setTcv(Integer tcv) {
		this.tcv = tcv;
	}

	public String getPracticeName() {
		return practiceName;
	}

	public void setPracticeName(String practiceName) {
		this.practiceName = practiceName;
	}

	public String getSubPracticeName() {
		return subPracticeName;
	}

	public void setSubPracticeName(String subPracticeName) {
		this.subPracticeName = subPracticeName;
	}

	public String getProficiencyLevelName() {
		return proficiencyLevelName;
	}

	public void setProficiencyLevelName(String proficiencyLevelName) {
		this.proficiencyLevelName = proficiencyLevelName;
	}

	public String getClient_Role() {
		return Client_Role;
	}

	public void setClient_Role(String client_Role) {
		Client_Role = client_Role;
	}

	public String getSyntelRoleDescription() {
		return syntelRoleDescription;
	}

	public void setSyntelRoleDescription(String syntelRoleDescription) {
		this.syntelRoleDescription = syntelRoleDescription;
	}

	public Integer getOnsiteUsage() {
		return onsiteUsage;
	}

	public void setOnsiteUsage(Integer onsiteUsage) {
		this.onsiteUsage = onsiteUsage;
	}

	public Integer getOffshoreUsage() {
		return offshoreUsage;
	}

	public void setOffshoreUsage(Integer offshoreUsage) {
		this.offshoreUsage = offshoreUsage;
	}

	public Integer getOnsiteProposedClientRate() {
		return onsiteProposedClientRate;
	}

	public void setOnsiteProposedClientRate(Integer onsiteProposedClientRate) {
		this.onsiteProposedClientRate = onsiteProposedClientRate;
	}

	public Integer getOffshoreProposedClientRate() {
		return offshoreProposedClientRate;
	}

	public void setOffshoreProposedClientRate(Integer offshoreProposedClientRate) {
		this.offshoreProposedClientRate = offshoreProposedClientRate;
	}

	public String getRCStartDate() {
		return RCStartDate;
	}

	public void setRCStartDate(String rCStartDate) {
		RCStartDate = rCStartDate;
	}

	public String getRCEndDate() {
		return RCEndDate;
	}

	public void setRCEndDate(String rCEndDate) {
		RCEndDate = rCEndDate;
	}

	public String getExpectedEndDate() {
		return ExpectedEndDate;
	}

	public void setExpectedEndDate(String expectedEndDate) {
		ExpectedEndDate = expectedEndDate;
	}

	public String getCountryName() {
		return countryName;
	}

	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public String getCategorizationId() {
		return categorizationId;
	}

	public void setCategorizationId(String categorizationId) {
		this.categorizationId = categorizationId;
	}

	public String getCategorization_Name() {
		return categorization_Name;
	}

	public void setCategorization_Name(String categorization_Name) {
		this.categorization_Name = categorization_Name;
	}
	
	
	
	

		
		
	
}
