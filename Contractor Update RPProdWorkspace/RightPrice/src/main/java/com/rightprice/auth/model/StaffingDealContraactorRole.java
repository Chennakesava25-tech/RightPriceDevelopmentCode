package com.rightprice.auth.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import io.swagger.annotations.ApiModelProperty;

@Entity
@Table(name = "SYNPROD.RP_Deal_STAFFING_Contractor_Roles")
public class StaffingDealContraactorRole 
{
	@Id
	@GeneratedValue
	@Column(name = "STAFFING_Contractor_RoleId")
	private Integer staffingcontractorRoleId;
	
	@Column(name = "CONTRACTOR_ROLE_ID")
	private Integer contractorRoleId;
	
	@Column(name = "RP_DEAL_VERSION_ID")
	private Integer dealVersionId;
	
	@Column(name = "Deal_Tower_Id")
	private Integer tower_id;
	
	public Integer getTower_id() {
		return tower_id;
	}

	public void setTower_id(Integer tower_id) {
		this.tower_id = tower_id;
	}

	@Column(name = "CONTRACTOR_ROLE_NAME")
	private String customerRole;
	
	
	@Column(name = "IS_ACTIVE")
	private int isActive= 1;
	
	@Column(name = "Created_By", updatable = false)
	private String createdBy;

	@Column(name = "CREATED_ON", updatable = false)
	private String createdOn;

	@Column(name = "UPDATED_BY")
	private String updatedBy;

	@Column(name = "UPDATED_ON")
	private String updatedOn;
	
	@Column(name = "[Onsite_Cost]")
	@ApiModelProperty(notes="[Onsite Cost]")
	private Double onsiteCost;
	
	@Column(name = "[Onsite_Rate]")
	@ApiModelProperty(notes="[Onsite Rate]")
	private Double onsiteRate;
	
	@Column(name = "[Offshore_Cost]")
	@ApiModelProperty(notes="[Offshore Cost ]")
	private Double offshoreCost;
	
	@Column(name = "[Offshore_Rate]")
	@ApiModelProperty(notes="[Offshore Rate Cost ]")
	private Double offshoreRate;
	
	@Column(name = "[Onsite_rate_increment_percent]")
	@ApiModelProperty(notes="[Onsite_rate_increment_percent]")
	private Double onsiteRateIncrementPercent;
	
	@Column(name = "[Onsite_cost_increment_percent]") 
	@ApiModelProperty(notes="[Onsite_cost_increment_percent ]")
	private Double onsiteCostIncrementPercent;
	
	@Column(name = "[Offshore_rate_increment_percent]")  
	@ApiModelProperty(notes="[Offshore_rate_increment_percent]")
	private Double offshoreRateIncrementPercent;
	
	@Column(name = "[Offshore_cost_increment_percent]")  
	@ApiModelProperty(notes="[Offshore_cost_increment_percent ]")
	private Double offshoreCostIncrementPercent;
	
	public Double getOnsiteRateIncrementPercent() {
		return onsiteRateIncrementPercent;
	}

	public void setOnsiteRateIncrementPercent(Double onsiteRateIncrementPercent) {
		this.onsiteRateIncrementPercent = onsiteRateIncrementPercent;
	}

	public Double getOnsiteCostIncrementPercent() {
		return onsiteCostIncrementPercent;
	}

	public void setOnsiteCostIncrementPercent(Double onsiteCostIncrementPercent) {
		this.onsiteCostIncrementPercent = onsiteCostIncrementPercent;
	}

	public Double getOffshoreRateIncrementPercent() {
		return offshoreRateIncrementPercent;
	}

	public void setOffshoreRateIncrementPercent(Double offshoreRateIncrementPercent) {
		this.offshoreRateIncrementPercent = offshoreRateIncrementPercent;
	}

	public Double getOffshoreCostIncrementPercent() {
		return offshoreCostIncrementPercent;
	}

	public void setOffshoreCostIncrementPercent(Double offshoreCostIncrementPercent) {
		this.offshoreCostIncrementPercent = offshoreCostIncrementPercent;
	}

	public Integer getStaffingcontractorRoleId() {
		return staffingcontractorRoleId;
	}

	public void setStaffingcontractorRoleId(Integer staffingcontractorRoleId) {
		this.staffingcontractorRoleId = staffingcontractorRoleId;
	}

	public Integer getContractorRoleId() {
		return contractorRoleId;
	}

	public void setContractorRoleId(Integer contractorRoleId) {
		this.contractorRoleId = contractorRoleId;
	}

	public Integer getDealVersionId() {
		return dealVersionId;
	}

	public void setDealVersionId(Integer dealVersionId) {
		this.dealVersionId = dealVersionId;
	}

	public String getCustomerRole() {
		return customerRole;
	}

	public void setCustomerRole(String customerRole) {
		this.customerRole = customerRole;
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

	public Double getOnsiteCost() {
		return onsiteCost;
	}

	public void setOnsiteCost(Double onsiteCost) {
		this.onsiteCost = onsiteCost;
	}

	public Double getOnsiteRate() {
		return onsiteRate;
	}

	public void setOnsiteRate(Double onsiteRate) {
		this.onsiteRate = onsiteRate;
	}

	public Double getOffshoreCost() {
		return offshoreCost;
	}

	public void setOffshoreCost(Double offshoreCost) {
		this.offshoreCost = offshoreCost;
	}

	public Double getOffshoreRate() {
		return offshoreRate;
	}

	public void setOffshoreRate(Double offshoreRate) {
		this.offshoreRate = offshoreRate;
	}

	
	
	
	
	
	

}
