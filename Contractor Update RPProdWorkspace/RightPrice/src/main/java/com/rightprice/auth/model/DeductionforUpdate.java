package com.rightprice.auth.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@Entity
@Table(name = "synprod.MST_RP_Deductions")
public class DeductionforUpdate implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	
	@Column(name = "DEDUCTION_ID")
	@ApiModelProperty(notes="DEDUCTION_ID Auto Incremented")
	private Integer deductionid;
	
	@Column(name = "DEDUCTION_CATEGORY_ID")
	private Integer deductionCatId;
	
	@Column(name = "DEDUCTION_TYPE_ID")
	private Integer deductionTypeId;

	@Column(name = "FIXED_DEDUCTION")
	private Double codeName;
	
	@Column(name = "PERCENT_DEDUCTION")
	private Double parDeduction;
	
	@Column(name = "COUNTRY_ID")
	private Integer countryId;
	
	@Column(name = "VISA_TYPE_ID")
	private Integer visaTypeId;
	
	@Column(name = "YEAR")
	private Integer year;
	
	@Column(name = "Is_Active")
	private Integer isActive;
	
	@Column(name = "CREATED_BY", updatable=false)
	private String createdBy;
	
	@Column(name = "CREATED_ON", updatable=false)
	private String createdOn;
	
	@Column(name = "UPDATE_BY")
	private String lastUpdatedBy;
	
	@Column(name = "UPDATED_ON")
	private String lastUpdatedOn;

	public Integer getDeductionid() {
		return deductionid;
	}

	public void setDeductionid(Integer deductionid) {
		this.deductionid = deductionid;
	}

	public Integer getDeductionCatId() {
		return deductionCatId;
	}

	public void setDeductionCatId(Integer deductionCatId) {
		this.deductionCatId = deductionCatId;
	}

	public Integer getDeductionTypeId() {
		return deductionTypeId;
	}

	public void setDeductionTypeId(Integer deductionTypeId) {
		this.deductionTypeId = deductionTypeId;
	}

	public Double getCodeName() {
		return codeName;
	}

	public void setCodeName(Double codeName) {
		this.codeName = codeName;
	}

	public Double getParDeduction() {
		return parDeduction;
	}

	public void setParDeduction(Double parDeduction) {
		this.parDeduction = parDeduction;
	}

	public Integer getCountryId() {
		return countryId;
	}

	public void setCountryId(Integer countryId) {
		this.countryId = countryId;
	}

	public Integer getVisaTypeId() {
		return visaTypeId;
	}

	public void setVisaTypeId(Integer visaTypeId) {
		this.visaTypeId = visaTypeId;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
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
