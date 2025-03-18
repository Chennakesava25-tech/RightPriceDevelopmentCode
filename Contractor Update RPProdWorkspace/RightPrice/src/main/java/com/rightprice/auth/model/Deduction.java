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
public class Deduction implements Serializable{
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
	
	@OneToOne(cascade = javax.persistence.CascadeType.ALL)
    @JoinColumn(name = "DEDUCTION_TYPE_ID",referencedColumnName = "ID", insertable = false, updatable = false)
	@NotFound(action = NotFoundAction.IGNORE)
	private MasterTaxType taxMap;
	
	@OneToOne(cascade = javax.persistence.CascadeType.ALL)
	@JoinColumn(name = "DEDUCTION_TYPE_ID",referencedColumnName = "ID", insertable = false, updatable = false)
	private MasterAssumptions assuMap;
	
	@ManyToOne(cascade = javax.persistence.CascadeType.ALL,fetch=FetchType.LAZY)
    @JoinColumn(name = "DEDUCTION_TYPE_ID",referencedColumnName = "CODE_NAME", insertable = false, updatable = false)
	private CommonCost deductionTypeMap;
	
	@Column(name = "FIXED_DEDUCTION")
	private Double codeName;
	
	@Column(name = "PERCENT_DEDUCTION")
	private Double parDeduction;
	
	@Column(name = "COUNTRY_ID")
	private Integer countryId;
	
	@Column(name = "VISA_TYPE_ID")
	private Integer visaTypeId;
	
	@OneToOne(cascade = javax.persistence.CascadeType.ALL)
    @JoinColumn(name = "VISA_TYPE_ID",referencedColumnName = "ID", insertable = false, updatable = false)
	private VisaType visaMap;
	
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

	public MasterTaxType getTaxMap() {
		return taxMap;
	}

	public void setTaxMap(MasterTaxType taxMap) {
		this.taxMap = taxMap;
	}

	public MasterAssumptions getAssuMap() {
		return assuMap;
	}

	public void setAssuMap(MasterAssumptions assuMap) {
		this.assuMap = assuMap;
	}

	public CommonCost getDeductionTypeMap() {
		return deductionTypeMap;
	}

	public void setDeductionTypeMap(CommonCost deductionTypeMap) {
		this.deductionTypeMap = deductionTypeMap;
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

	public VisaType getVisaMap() {
		return visaMap;
	}

	public void setVisaMap(VisaType visaMap) {
		this.visaMap = visaMap;
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
