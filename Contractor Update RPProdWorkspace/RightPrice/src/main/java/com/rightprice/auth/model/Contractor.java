package com.rightprice.auth.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@Entity
@ApiModel(value="Contractor",description="Contractor_Roles")
@Table(name = "synprod.RP_Deal_Contractor_Roles")
public class Contractor {
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	
	@Column(name = "Contractor_Role_Id")
	@ApiModelProperty(notes="Contractor_Role_Id Auto Incremented")
	private int Contractor_Role_Id;
	
	@Column(name = "RP_Deal_Version_Id")
	private int RP_Deal_Version_Id;
	
	@Column(name = "Contractor_Role_Name")
	private String contactorRole;
	
	/*@Column(name = "Onsite_Hourly_Cost")
	private double onsiteCost;
	
	@Column(name = "Offshore_Hourly_Cost")
	private double offshoreCost;
	
	@Column(name = "Onsite_Rate")
	private double onsiteRate;
	
	@Column(name = "Offshore_Rate")
	private double offshoreRate;*/
	
	@Column(name = "Comments")
	private String comments;
	
	@Column(name = "Is_Active")
	private String isActive;
	
	//@Column(name = "CREATED_BY", updatable=false)
	@Column(name = "CREATED_BY")
	private String createdBy;
	
	//@Column(name = "CREATED_ON", updatable=false)
	@Column(name = "CREATED_ON")
	private String createdOn;
	
	@Column(name = "UPDATED_BY")
	private String lastUpdatedBy;
	
	@Column(name = "UPDATED_ON")
	private String lastUpdatedOn;

	public int getContractor_Role_Id() {
		return Contractor_Role_Id;
	}

	public void setContractor_Role_Id(int contractor_Role_Id) {
		Contractor_Role_Id = contractor_Role_Id;
	}

	public int getRP_Deal_Version_Id() {
		return RP_Deal_Version_Id;
	}

	public void setRP_Deal_Version_Id(int rP_Deal_Version_Id) {
		RP_Deal_Version_Id = rP_Deal_Version_Id;
	}

	public String getContactorRole() {
		return contactorRole;
	}

	public void setContactorRole(String contactorRole) {
		this.contactorRole = contactorRole;
	}

	/*public double getOnsiteCost() {
		return onsiteCost;
	}

	public void setOnsiteCost(double onsiteCost) {
		this.onsiteCost = onsiteCost;
	}

	public double getOffshoreCost() {
		return offshoreCost;
	}

	public void setOffshoreCost(double offshoreCost) {
		this.offshoreCost = offshoreCost;
	}

	public double getOnsiteRate() {
		return onsiteRate;
	}

	public void setOnsiteRate(double onsiteRate) {
		this.onsiteRate = onsiteRate;
	}

	public double getOffshoreRate() {
		return offshoreRate;
	}

	public void setOffshoreRate(double offshoreRate) {
		this.offshoreRate = offshoreRate;
	}*/

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public String getIsActive() {
		return isActive;
	}

	public void setIsActive(String isActive) {
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
