package com.rightprice.auth.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class ContractorView {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "Contractor_Role_Id")
	private int Contractor_Role_Id;
	
	@Column(name = "Contractor_Role_Name")
	private String contactorRole;
	
	@Column(name = "Onsite_Hourly_Cost")
	private double onsiteCost;
	
	@Column(name = "Offshore_Hourly_Cost")
	private double offshoreCost;
	
	@Column(name = "Onsite_Rate")
	private double onsiteRate;
	
	@Column(name = "Offshore_Rate")
	private double offshoreRate;
	
	@Column(name = "Comments")
	private String comments;
	
	
	public int getContractor_Role_Id() {
		return Contractor_Role_Id;
	}

	public void setContractor_Role_Id(int contractor_Role_Id) {
		Contractor_Role_Id = contractor_Role_Id;
	}

	public String getContactorRole() {
		return contactorRole;
	}

	public void setContactorRole(String contactorRole) {
		this.contactorRole = contactorRole;
	}

	public double getOnsiteCost() {
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
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}
	
}
