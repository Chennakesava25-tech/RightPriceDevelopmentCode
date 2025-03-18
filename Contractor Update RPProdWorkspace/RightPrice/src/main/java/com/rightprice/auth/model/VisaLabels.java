package com.rightprice.auth.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@Entity
@ApiModel(value="Visa",description="Visa Model Attributes")
@Table(name = "synprod.MST_RP_Country_Visa_Labels") 
public class VisaLabels {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)	
	
	@Column(name = "COUNTRY_VISA_LABEL_ID")
	@ApiModelProperty(notes="Visa Type ID")
	private int CountryVisaLabelId;
	
	@Column(name = "VISA_TYPE_ID")
	@ApiModelProperty(notes="Visa Type ID")
	private int visaTypeId;
	
	@OneToOne(cascade = javax.persistence.CascadeType.ALL)
    @JoinColumn(name = "VISA_TYPE_ID",referencedColumnName = "CODE_NAME", insertable = false, updatable = false)
	private AppCodeVisaType visaType;

	/*public CodeApplication getVisaType() {
		return visaType;
	}

	public void setVisaType(CodeApplication visaType) {
		this.visaType = visaType;
	}*/

	public AppCodeVisaType getVisaType() {
		return visaType;
	}

	public void setVisaType(AppCodeVisaType visaType) {
		this.visaType = visaType;
	}

	@Column(name = "VISA_LABEL")
	@ApiModelProperty(notes="Visa Label")
	private String visaLabel;
	
	@Column(name = "COUNTRY_ID")
	@ApiModelProperty(notes="Country ID")
	private int countryId;
	
	@Column(name = "IS_ACTIVE")
	@ApiModelProperty(notes="Is Active")
	private int isActive;
	
	@Column(name = "CREATED_BY", updatable=false)
	@ApiModelProperty(notes="Created by")
	private String createdBy;
	
	@Column(name = "CREATED_ON", updatable=false)
	@ApiModelProperty(notes="Created date")
	private String createdDate;
	
	@Column(name = "UPDATED_BY")
	@ApiModelProperty(notes="Updated by")
	private String updatedBy;
	
	@Column(name = "UPDATED_ON")
	@ApiModelProperty(notes="Updated date")
	private String updatedDate;
	
	@Column(name = "IS_STAFFING")
	@ApiModelProperty(notes="Is Staffing")
	private Integer isStaffing; 


	public int getCountryVisaLabelId() {
		return CountryVisaLabelId;
	}

	public void setCountryVisaLabelId(int countryVisaLabelId) {
		CountryVisaLabelId = countryVisaLabelId;
	}

	public int getVisaTypeId() {
		return visaTypeId;
	}
	
	public void setVisaTypeId(int visaTypeId) {
		this.visaTypeId = visaTypeId;
	}
	
	public String getVisaLabel() {
		return visaLabel;
	}
	
	public void setVisaLabel(String visaLabel) {
		this.visaLabel = visaLabel;
	}
	
	public int getCountryId() {
		return countryId;
	}
	
	public void setCountryId(int countryId) {
		this.countryId = countryId;
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
	
	public String getCreatedDate() {
		return createdDate;
	}
	
	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}
	
	public String getUpdatedBy() {
		return updatedBy;
	}
	
	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}
	
	public String getUpdatedDate() {
		return updatedDate;
	}
	
	public void setUpdatedDate(String updatedDate) {
		this.updatedDate = updatedDate;
	}

	public Integer getIsStaffing() {
		return isStaffing;
	}

	public void setIsStaffing(Integer isStaffing) {
		this.isStaffing = isStaffing;
	}

	


}