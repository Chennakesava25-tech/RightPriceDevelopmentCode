package com.rightprice.auth.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import io.swagger.annotations.ApiModelProperty;

@Entity
@Table(name = "synprod.MST_RP_Basic_Allowance")
public class BasicAllowance {
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "Allowance_Id")
	private int allowanceId;
	
	@Column(name = "Emp_Designation_Id")
	private int desgId;
	
	@OneToOne(cascade = javax.persistence.CascadeType.ALL)
    @JoinColumn(name = "Emp_Designation_Id",referencedColumnName = "Emp_Designation_Id", insertable = false, updatable = false)
	private EmpDesignation empDesgmap;
	
	@Column(name = "Country_Id")
	private int countryId;
	
	@Column(name = "Annual_Allowance_Low")
	private String lowallowance;
	
	@Column(name = "Annual_Allowance_Medium")
	private String mediumallowance;
	
	@Column(name = "Annual_Allowance_High")
	private String highallowance;
	
	@Column(name = "Annual_Allowance_Very_High")
	private String veryhighallowance;
	

//	@Column(name = "Offshore_Allowance")
//	private Integer offshoreallowance;
	
//	@Column(name = "Annual_cola")
//	private String annualcola;
	

	@Column(name = "Visa_Type_Id")
	private int visaid;
	
	@Column(name = "Year")
	private int year;
	
	@Column(name = "Is_Active")
	private int statusModel;
	
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
	
	public int getAllowanceId() {
		return allowanceId;
	}
	public void setAllowanceId(int allowanceId) {
		this.allowanceId = allowanceId;
	}
	public int getDesgId() {
		return desgId;
	}
	public void setDesgId(int desgId) {
		this.desgId = desgId;
	}
	public int getCountryId() {
		return countryId;
	}
	public void setCountryId(int countryId) {
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
	
	public int getVisaid() {
		return visaid;
	}
	public EmpDesignation getEmpDesgmap() {
		return empDesgmap;
	}
	public void setEmpDesgmap(EmpDesignation empDesgmap) {
		this.empDesgmap = empDesgmap;
	}
	public void setVisaid(int visaid) {
		this.visaid = visaid;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	
	public String getCreatedBy() {
		return createdBy;
	}
	public int getStatusModel() {
		return statusModel;
	}
	public void setStatusModel(int statusModel) {
		this.statusModel = statusModel;
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
