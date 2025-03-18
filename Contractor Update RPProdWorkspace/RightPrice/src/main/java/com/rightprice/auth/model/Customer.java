package com.rightprice.auth.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import io.swagger.annotations.ApiModelProperty;

@Entity
@Table(name = "synprod.MST_RP_Customer")
public class Customer implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "CUSTOMER_ID")
	@ApiModelProperty(notes = "customer id")
	private int customerId;

	@Column(name = "CUSTOMER_NAME")
	@ApiModelProperty(notes = "customer vertical map id")
	private String customerName;

	@Column(name = "Industry_Name")
	@ApiModelProperty(notes = "customer vertical map id")
	private String industryName;
	
	
	@Column(name = "SALES_PERSON")
	@ApiModelProperty(notes = "SALES_PERSON name")
	private String salesPerson;
	
	@Column(name = "SPOC_NAMES")
	@ApiModelProperty(notes = "Spoc Name ")
	private String spocNames;
	
	@Column(name = "CUSTOMER_ADDRESS")
	@ApiModelProperty(notes = "customer Address details")
	private String customerAddress;
	
	@Column(name = "ANNUAL_VOLUME")
	@ApiModelProperty(notes = "Annual_Volume")
	private Double annualVolume;
	
	@Column(name = "IS_OUTSOURCING_FIRSTIME")
	@ApiModelProperty(notes = "flag for Is_Outsourcing_Firstime")
	private Integer isOutsourcingFirstime;
	
	@Column(name = "ROUND_OF_NEGOTIATION")
	@ApiModelProperty(notes = "Round_Of_Negotiation")
	private Integer roundOfNegotiation;
	
	@Column(name = "COUNTRY_CODE")
	@ApiModelProperty(notes = "customer Country Code")
	private String countryCode;
	
	@Column(name = "MONTHLY_WORKING_DAYS")
	@ApiModelProperty(notes = "Monthly_Working_Days")
	private Integer monthlyWorkingDays;
	
	@Column(name = "ONSITE_WORK_HOURS")
	@ApiModelProperty(notes = "Onsite_Work_Hours")
	private Double onsiteWorkHours;
	
	@Column(name = "OFFSHORE_WORK_HOURS")
	@ApiModelProperty(notes = "Offshore_Work_Hours")
	private Double offshoreWorkHours;

	@Column(name = "IS_ACTIVE")
	@ApiModelProperty(notes = "active status")
	private Integer isActive = 1;

	@Column(name = "CREATED_BY", updatable = false)
	@ApiModelProperty(notes = "Created by")
	private String createdBy;

	@Column(name = "CREATED_ON", updatable = false)
	@ApiModelProperty(notes = "Created date")
	private String createdOn;

	@Column(name = "UPDATED_BY")
	@ApiModelProperty(notes = "Updated by")
	private String updatedBy;

	@Column(name = "UPDATED_ON")
	@ApiModelProperty(notes = "Updated date")
	private String updatedOn;
	
	@Column(name = "VOLUME_DISCOUNT")
	@ApiModelProperty(notes = "Volume_Discount")
	private Double volumeDiscount;

	@Column(name = "CPC_CHARGES")
	@ApiModelProperty(notes = "CPC_CHARGES")
	private Double cpcCharges;
	
	@Column(name = "IQN_CHARGES")
	@ApiModelProperty(notes = "IQN_CHARGES")
	private Double ipcCharges;
	
	
	@Column(name = "Is_Atos")
	@ApiModelProperty(notes = "Is Atos")
	private Integer isAtos;
	

	@Column(name = "CPC_FP_Charges ")
	@ApiModelProperty(notes = "CPC_FP_Charges ")
	private Double cpcChargesFP;
	
	/*@Column(name = "IRIS_CODE")
	@ApiModelProperty(notes = "IRIS_CODE")
	private String irisCode;
	
	@Column(name = "GBU")
	@ApiModelProperty(notes = "GBU")
	private String gbu;
	
	@Column(name = "Finance_Name")
	@ApiModelProperty(notes = "Finance_Name")
	private String financeName;*/
	

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getSalesPerson() {
		return salesPerson;
	}

	public void setSalesPerson(String salesPerson) {
		this.salesPerson = salesPerson;
	}

	public String getSpocNames() {
		return spocNames;
	}

	public void setSpocNames(String spocNames) {
		this.spocNames = spocNames;
	}

	public String getCustomerAddress() {
		return customerAddress;
	}

	public void setCustomerAddress(String customerAddress) {
		this.customerAddress = customerAddress;
	}

	public Double getAnnualVolume() {
		return annualVolume;
	}

	public void setAnnualVolume(Double annualVolume) {
		this.annualVolume = annualVolume;
	}

	public Integer getIsOutsourcingFirstime() {
		return isOutsourcingFirstime;
	}

	public void setIsOutsourcingFirstime(Integer isOutsourcingFirstime) {
		this.isOutsourcingFirstime = isOutsourcingFirstime;
	}

	public Integer getRoundOfNegotiation() {
		return roundOfNegotiation;
	}

	public void setRoundOfNegotiation(Integer roundOfNegotiation) {
		this.roundOfNegotiation = roundOfNegotiation;
	}

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public Integer getMonthlyWorkingDays() {
		return monthlyWorkingDays;
	}

	public void setMonthlyWorkingDays(Integer monthlyWorkingDays) {
		this.monthlyWorkingDays = monthlyWorkingDays;
	}

	public Double getOnsiteWorkHours() {
		return onsiteWorkHours;
	}

	public void setOnsiteWorkHours(Double onsiteWorkHours) {
		this.onsiteWorkHours = onsiteWorkHours;
	}

	public Double getOffshoreWorkHours() {
		return offshoreWorkHours;
	}

	public void setOffshoreWorkHours(Double offshoreWorkHours) {
		this.offshoreWorkHours = offshoreWorkHours;
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

	public Double getVolumeDiscount() {
		return volumeDiscount;
	}

	public void setVolumeDiscount(Double volumeDiscount) {
		this.volumeDiscount = volumeDiscount;
	}

	public Double getCpcCharges() {
		return cpcCharges;
	}

	public void setCpcCharges(Double cpcCharges) {
		this.cpcCharges = cpcCharges;
	}

	public Double getIpcCharges() {
		return ipcCharges;
	}

	public void setIpcCharges(Double ipcCharges) {
		this.ipcCharges = ipcCharges;
	}

	public Integer getIsAtos() {
		return isAtos;
	}

	public void setIsAtos(Integer isAtos) {
		this.isAtos = isAtos;
	}

	public Double getCpcChargesFP() {
		return cpcChargesFP;
	}

	public void setCpcChargesFP(Double cpcChargesFP) {
		this.cpcChargesFP = cpcChargesFP;
	}

	public String getIndustryName() {
		return industryName;
	}

	public void setIndustryName(String industryName) {
		this.industryName = industryName;
	}

	
	/*public String getIrisCode() {
		return irisCode;
	}

	public void setIrisCode(String irisCode) {
		this.irisCode = irisCode;
	}

	public String getGbu() {
		return gbu;
	}

	public void setGbu(String gbu) {
		this.gbu = gbu;
	}

	public String getFinanceName() {
		return financeName;
	}

	public void setFinanceName(String financeName) {
		this.financeName = financeName;
	}*/
	
}