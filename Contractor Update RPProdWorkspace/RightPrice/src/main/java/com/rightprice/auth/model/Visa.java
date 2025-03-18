package com.rightprice.auth.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="synprod.MST_RP_Country_Visa_Labels")
public class Visa implements Serializable {

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="Country_Visa_Label_Id")
	private int labelid;
	
	@Column(name="Visa_Type_Id")
	private int visaid;
	 
	@Column(name="Visa_Label")
	private String visalable;
	
	@Column(name="Country_Id")
	private int countryId;
	 
	@Column(name = "IS_ACTIVE")
	private int status;
	
	@Column(name = "CREATED_BY")
	private String createdBy;

	@Column(name = "CREATED_ON")
	private String createdOn;

	@Column(name = "Updated_By")
	private String lastUpdatedBy;

	@Column(name = "Updated_On")
	private String lastUpdatedOn;

	public int getLabelid() {
		return labelid;
	}

	public void setLabelid(int labelid) {
		this.labelid = labelid;
	}

	public int getVisaid() {
		return visaid;
	}

	public void setVisaid(int visaid) {
		this.visaid = visaid;
	}

	public String getVisa() {
		return visalable;
	}

	public void setVisa(String visalable) {
		this.visalable = visalable;
	}

	public int getCountryId() {
		return countryId;
	}

	public void setCountryId(int countryId) {
		this.countryId = countryId;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
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
