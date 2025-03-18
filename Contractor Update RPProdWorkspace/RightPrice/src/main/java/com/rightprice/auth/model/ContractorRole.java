package com.rightprice.auth.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@Entity
@ApiModel(value = "Campus Hire", description = "Campus Hire Model Attributes")
@Table(name = "SYNPROD.RP_RATE_CARD_CONTRACTOR_DETAIL")
public class ContractorRole implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "CONTRACTOR_KEY_ID")
	@ApiModelProperty(notes = "Contractor key ID Auto Incremented")
	private Integer contractorkeyID;

	@Column(name = "RC_ID")
	@ApiModelProperty(notes = "RC Id")
	private Integer rcId;
	
	@Column(name = "RC_ROLE_ID")
	@ApiModelProperty(notes = "RC Role Id")
	private Integer rcRoleId;
	
	@Column(name = "RC_YEAR")
	@ApiModelProperty(notes = "RC Year")
	private Integer rcYear;
	
	@Column(name = "RC_COUNTRY_ID")
	@ApiModelProperty(notes = "RC Country Id")
	private Integer rcCountryId;
	
	@Column(name = "CLIENT_ROLE")
	@ApiModelProperty(notes = "CLIENT ROLE")
	private String rcCustRole;
	
	@Column(name = "RC_CITY_ID")
	@ApiModelProperty(notes = "RC City Id")
	private Integer rcCityId;
	
	@Column(name = "MASTER_ROLE_ID")
	@ApiModelProperty(notes = "Master Role Id")
	private Integer masterRoleId;
	
	@Column(name = "ONSITE_RATE_HOUR")
	@ApiModelProperty(notes = "Onsite Rate Hour")
	private Double onsiteRateHour;
	
	@Column(name = "ONSITE_COST_HOUR")
	@ApiModelProperty(notes = "Onsite Cost Hour")
	private Double onsiteCostHour;
	
	@Column(name = "OFFSHORE_RATE_HOUR")
	@ApiModelProperty(notes = "offshore Rate Hour")
	private Double offshoreRateHour;
	
	@Column(name = "OFFSHORE_COST_HOUR")
	@ApiModelProperty(notes = "Offshore Cost Hour")
	private Double offshoreCostHour;
	
	@Column(name = "IS_ACTIVE")
	@ApiModelProperty(notes = "Is Active")
	private Integer isActive;
	
	@Column(name = "CREATED_BY")
	@ApiModelProperty(notes = "Created By")
	private String createdBy;
	
	@Column(name = "CREATED_ON")
	@ApiModelProperty(notes = "Created On")
	private String createdOn;
	
	@Column(name = "UPDATED_BY")
	@ApiModelProperty(notes = "Updated By")
	private String updatedBy;
	
	@Column(name = "UPDATED_ON")
	@ApiModelProperty(notes = "Updated On")
	private String updatedOn;

	public Integer getContractorkeyID() {
		return contractorkeyID;
	}

	public void setContractorkeyID(Integer contractorkeyID) {
		this.contractorkeyID = contractorkeyID;
	}

	public Integer getRcId() {
		return rcId;
	}

	public void setRcId(Integer rcId) {
		this.rcId = rcId;
	}

	public Integer getRcRoleId() {
		return rcRoleId;
	}

	public void setRcRoleId(Integer rcRoleId) {
		this.rcRoleId = rcRoleId;
	}

	public Integer getRcYear() {
		return rcYear;
	}

	public void setRcYear(Integer rcYear) {
		this.rcYear = rcYear;
	}

	public Integer getRcCountryId() {
		return rcCountryId;
	}

	public void setRcCountryId(Integer rcCountryId) {
		this.rcCountryId = rcCountryId;
	}

	public Integer getRcCityId() {
		return rcCityId;
	}

	public void setRcCityId(Integer rcCityId) {
		this.rcCityId = rcCityId;
	}

	public Integer getMasterRoleId() {
		return masterRoleId;
	}

	public void setMasterRoleId(Integer masterRoleId) {
		this.masterRoleId = masterRoleId;
	}

	public Double getOnsiteRateHour() {
		return onsiteRateHour;
	}

	public void setOnsiteRateHour(Double onsiteRateHour) {
		this.onsiteRateHour = onsiteRateHour;
	}

	public Double getOnsiteCostHour() {
		return onsiteCostHour;
	}

	public void setOnsiteCostHour(Double onsiteCostHour) {
		this.onsiteCostHour = onsiteCostHour;
	}

	public Double getOffshoreRateHour() {
		return offshoreRateHour;
	}

	public void setOffshoreRateHour(Double offshoreRateHour) {
		this.offshoreRateHour = offshoreRateHour;
	}
	
	public Double getOffshoreCostHour() {
		return offshoreCostHour;
	}

	public void setOffshoreCostHour(Double offshoreCostHour) {
		this.offshoreCostHour = offshoreCostHour;
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

	public String getRcCustRole() {
		return rcCustRole;
	}

	public void setRcCustRole(String rcCustRole) {
		this.rcCustRole = rcCustRole;
	}

	
}
