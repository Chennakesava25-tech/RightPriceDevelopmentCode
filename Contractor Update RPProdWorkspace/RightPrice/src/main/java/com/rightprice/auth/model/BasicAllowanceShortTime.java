package com.rightprice.auth.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import io.swagger.annotations.ApiModelProperty;

@Entity
@Table(name = "synprod.MST_RP_Basic_Allowance_ShortTerm")
public class BasicAllowanceShortTime implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "Allowance_Id")
	private Integer allowanceId;
	
	@Column(name = "Emp_Designation_Id")
	private Integer desgId;
	
	
	@OneToOne(cascade = javax.persistence.CascadeType.ALL)
    @JoinColumn(name = "Emp_Designation_Id",referencedColumnName = "Emp_Designation_Id", insertable = false, updatable = false)
	private EmpDesignation empDesgmap;
	
	@Column(name = "Country_Id")
	private Integer countryId;
	
	@Column(name = "Annual_Allowance_Low")
	private String lowallowance;
	
	@Column(name = "Annual_Allowance_Medium")
	private String mediumallowance;
	
	@Column(name = "Annual_Allowance_High")
	private String highallowance;
	
	@Column(name = "Annual_Allowance_Very_High")
	private String veryhighallowance;
	
	@Column(name = "Visa_Type_Id")
	private Integer visaid;
	
	@Column(name = "Year")
	private Integer year;
	
	@Column(name = "Is_Active")
	private Integer statusModel;
	
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

	public Integer getAllowanceId() {
		return allowanceId;
	}

	public void setAllowanceId(Integer allowanceId) {
		this.allowanceId = allowanceId;
	}

	public Integer getDesgId() {
		return desgId;
	}

	public void setDesgId(Integer desgId) {
		this.desgId = desgId;
	}

	public EmpDesignation getEmpDesgmap() {
		return empDesgmap;
	}

	public void setEmpDesgmap(EmpDesignation empDesgmap) {
		this.empDesgmap = empDesgmap;
	}

	public Integer getCountryId() {
		return countryId;
	}

	public void setCountryId(Integer countryId) {
		this.countryId = countryId;
	}

	public String getLowallowance() {
		return lowallowance;
	}

	public void setLowallowance(String lowallowance) {
		this.lowallowance = lowallowance;
	}

	public String getMediumallowance() {
		return mediumallowance;
	}

	public void setMediumallowance(String mediumallowance) {
		this.mediumallowance = mediumallowance;
	}

	public String getHighallowance() {
		return highallowance;
	}

	public void setHighallowance(String highallowance) {
		this.highallowance = highallowance;
	}

	public String getVeryhighallowance() {
		return veryhighallowance;
	}

	public void setVeryhighallowance(String veryhighallowance) {
		this.veryhighallowance = veryhighallowance;
	}

	public Integer getVisaid() {
		return visaid;
	}

	public void setVisaid(Integer visaid) {
		this.visaid = visaid;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}
	
	public Integer getStatusModel() {
		return statusModel;
	}

	public void setStatusModel(Integer statusModel) {
		this.statusModel = statusModel;
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

}
