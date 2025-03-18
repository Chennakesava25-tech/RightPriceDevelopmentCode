package com.rightprice.auth.model;

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
@Table(name = "synprod.MST_RP_Next_Yearwise_Basic_Salary")
public class YearwiseBasicSalary {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "Salary_Id")
	@ApiModelProperty(notes = "Salary_Id")
	private Integer salaryid;
	
	@Column(name = "Emp_Designation_Id")
	@ApiModelProperty(notes = "Emp Desg Id")
	private Integer empDesgId;
	
	@OneToOne(cascade = javax.persistence.CascadeType.ALL)
    @JoinColumn(name = "Emp_Designation_Id",referencedColumnName = "Emp_Designation_Id", insertable = false, updatable = false)
	private EmpDesignation empDesg;
	
	@Column(name = "Country_Id")
	@ApiModelProperty(notes = "Country Id")
	private Integer countryId;
	
	@Column(name = "Practice_Id")
	@ApiModelProperty(notes = "Practice Id")
	private Integer practiceId;
	
	@Column(name = "ANNUAL_SALARY")
	@ApiModelProperty(notes = "Annual Salary")
	private double annualSalary;

	@Column(name = "YEAR")
	@ApiModelProperty(notes = "Year")
	private Integer year;
	
	
	@Column(name = "IS_ACTIVE")
	@ApiModelProperty(notes = "Active status")
	private int isActive;

	@Column(name = "CREATED_BY", updatable = false)
	@ApiModelProperty(notes = "Created by")
	private String createdBy;

	@Column(name = "CREATED_ON", updatable = false)
	@ApiModelProperty(notes = "Created date")
	private String createdDate;

	@Column(name = "UPDATED_BY")
	@ApiModelProperty(notes = "Updated by")
	private String updatedBy;

	@Column(name = "UPDATED_ON")
	@ApiModelProperty(notes = "Updated date")
	private String updatedDate;

	public Integer getSalaryid() {
		return salaryid;
	}

	public void setSalaryid(Integer salaryid) {
		this.salaryid = salaryid;
	}

	public Integer getEmpDesgId() {
		return empDesgId;
	}

	public void setEmpDesgId(Integer empDesgId) {
		this.empDesgId = empDesgId;
	}

	public Integer getCountryId() {
		return countryId;
	}

	public void setCountryId(Integer countryId) {
		this.countryId = countryId;
	}

	public Integer getPracticeId() {
		return practiceId;
	}

	public void setPracticeId(Integer practiceId) {
		this.practiceId = practiceId;
	}

	public double getAnnualSalary() {
		return annualSalary;
	}

	public void setAnnualSalary(double annualSalary) {
		this.annualSalary = annualSalary;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
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

	public EmpDesignation getEmpDesg() {
		return empDesg;
	}

	public void setEmpDesg(EmpDesignation empDesg) {
		this.empDesg = empDesg;
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
