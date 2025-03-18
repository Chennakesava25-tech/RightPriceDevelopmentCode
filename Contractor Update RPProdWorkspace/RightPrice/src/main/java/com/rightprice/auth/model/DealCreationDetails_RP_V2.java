package com.rightprice.auth.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@Entity
@ApiModel(value = "Deal", description = "DealDetails")
@Table(name = "synprod.RP_V2_Deals_Details")
public class DealCreationDetails_RP_V2 implements Serializable  {
	
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "CRM_Deal_Id")
	private String dealId;

	@Column(name = "CUSTOMER_id")
	private Integer customerId;

	@Column(name = "Deal_Start_Date")
	private String dealStartDate;

	@Column(name = "Duration")
	private Double duration;

	@Column(name = "Deal_Status")
	private Integer dealStatus;

	@Column(name = "industry")
	private String industry;

	@Column(name = "country")
	private Integer country;

	@Column(name = "revenue")
	private Double revenue;


	@Column(name = "customer_Desc")
	private String customerDescription;
	
	@Column(name = "Deal_Description")
	private String dealDescription;
	

	@Column(name = "Deal_End_Date")
	private String dealEndDate;

	@Column(name = "Currency")
	private String currency;

	@Column(name = "Sales_Spoc")
	private String salesSpoc;

	
	
	
	@Column(name = "Deal_TCV")
	private String dealTCV;

	@Column(name = "city")
	private String city;

	@Column(name = "cost")
	private Double cost;

	@Column(name = "onsite_percentage")
	private Double onsitePercentage;

	@Column(name = "offshore_percentage")
	private Double offshorePercentage;

	@Column(name = "Gross_Margin_percentage")
	private Double grossMarginPercentage;

	@Column(name = "project_specific_SGA_Including_Agile_cost")
	private Double projectSpecificSGAIncludingAgileCost;

	@Column(name = "OM_percentage")
	private Double omPercentage;

	@Column(name = "Volume_Discount_percentage")
	private Double volumeDiscountPercentage;

	@Column(name = "penalty_percentage")
	private Double penaltyPercentage;

	@Column(name = "OM_percentage_After_Volume_Discount")
	private Double omPercentageAfterVolumeDiscount;
	
	@Column(name = "Created_By")
	private String createdBy;
	
	

	@Column(name = "Updated_By")
	private String updatedBy;
	
	
	@Column(name = "Created_On")
	private String createdOn;
	

	@Column(name = "Updated_On")
	private String updatedOn;
	
	@Column(name = "IS_ACTIVE")
	@ApiModelProperty(notes="Active status")
	private Integer isActive;


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

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public String getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(String createdOn) {
		this.createdOn = createdOn;
	}

	public String getUpdatedOn() {
		return updatedOn;
	}

	public void setUpdatedOn(String updatedOn) {
		this.updatedOn = updatedOn;
	}

	public String getDealId() {
		return dealId;
	}

	public void setDealId(String dealId) {
		this.dealId = dealId;
	}

	public Integer getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}

	public String getDealDescription() {
		return dealDescription;
	}

	public void setDealDescription(String dealDescription) {
		this.dealDescription = dealDescription;
	}

	public String getDealStartDate() {
		return dealStartDate;
	}

	public void setDealStartDate(String dealStartDate) {
		this.dealStartDate = dealStartDate;
	}

	public Double getDuration() {
		return duration;
	}

	public void setDuration(Double duration) {
		this.duration = duration;
	}



	public Integer getDealStatus() {
		return dealStatus;
	}

	public void setDealStatus(Integer dealStatus) {
		this.dealStatus = dealStatus;
	}

	public String getIndustry() {
		return industry;
	}

	public void setIndustry(String industry) {
		this.industry = industry;
	}

	public Integer getCountry() {
		return country;
	}

	public void setCountry(Integer country) {
		this.country = country;
	}

	public Double getRevenue() {
		return revenue;
	}

	public void setRevenue(Double revenue) {
		this.revenue = revenue;
	}

	public String getCustomerDescription() {
		return customerDescription;
	}

	public void setCustomerDescription(String customerDescription) {
		this.customerDescription = customerDescription;
	}

	public String getDealEndDate() {
		return dealEndDate;
	}

	public void setDealEndDate(String dealEndDate) {
		this.dealEndDate = dealEndDate;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getSalesSpoc() {
		return salesSpoc;
	}

	public void setSalesSpoc(String salesSpoc) {
		this.salesSpoc = salesSpoc;
	}

	public String getDealTCV() {
		return dealTCV;
	}

	public void setDealTCV(String dealTCV) {
		this.dealTCV = dealTCV;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public Double getCost() {
		return cost;
	}

	public void setCost(Double cost) {
		this.cost = cost;
	}

	public Double getOnsitePercentage() {
		return onsitePercentage;
	}

	public void setOnsitePercentage(Double onsitePercentage) {
		this.onsitePercentage = onsitePercentage;
	}

	public Double getOffshorePercentage() {
		return offshorePercentage;
	}

	public void setOffshorePercentage(Double offshorePercentage) {
		this.offshorePercentage = offshorePercentage;
	}

	public Double getGrossMarginPercentage() {
		return grossMarginPercentage;
	}

	public void setGrossMarginPercentage(Double grossMarginPercentage) {
		this.grossMarginPercentage = grossMarginPercentage;
	}

	public Double getProjectSpecificSGAIncludingAgileCost() {
		return projectSpecificSGAIncludingAgileCost;
	}

	public void setProjectSpecificSGAIncludingAgileCost(Double projectSpecificSGAIncludingAgileCost) {
		this.projectSpecificSGAIncludingAgileCost = projectSpecificSGAIncludingAgileCost;
	}

	public Double getOmPercentage() {
		return omPercentage;
	}

	public void setOmPercentage(Double omPercentage) {
		this.omPercentage = omPercentage;
	}

	public Double getVolumeDiscountPercentage() {
		return volumeDiscountPercentage;
	}

	public void setVolumeDiscountPercentage(Double volumeDiscountPercentage) {
		this.volumeDiscountPercentage = volumeDiscountPercentage;
	}

	public Double getPenaltyPercentage() {
		return penaltyPercentage;
	}

	public void setPenaltyPercentage(Double penaltyPercentage) {
		this.penaltyPercentage = penaltyPercentage;
	}

	public Double getOmPercentageAfterVolumeDiscount() {
		return omPercentageAfterVolumeDiscount;
	}

	public void setOmPercentageAfterVolumeDiscount(Double omPercentageAfterVolumeDiscount) {
		this.omPercentageAfterVolumeDiscount = omPercentageAfterVolumeDiscount;
	}

	@Override
	public String toString() {
		return "DealCreationDetails_RP_V2 [dealId=" + dealId + ", customerId=" + customerId + ", dealStartDate="
				+ dealStartDate + ", duration=" + duration + ", dealStatus=" + dealStatus + ", industry=" + industry
				+ ", country=" + country + ", revenue=" + revenue + ", customerDescription=" + customerDescription
				+ ", dealDescription=" + dealDescription + ", dealEndDate=" + dealEndDate + ", currency=" + currency
				+ ", salesSpoc=" + salesSpoc + ", dealTCV=" + dealTCV + ", city=" + city + ", cost=" + cost
				+ ", onsitePercentage=" + onsitePercentage + ", offshorePercentage=" + offshorePercentage
				+ ", grossMarginPercentage=" + grossMarginPercentage + ", projectSpecificSGAIncludingAgileCost="
				+ projectSpecificSGAIncludingAgileCost + ", omPercentage=" + omPercentage
				+ ", volumeDiscountPercentage=" + volumeDiscountPercentage + ", penaltyPercentage=" + penaltyPercentage
				+ ", omPercentageAfterVolumeDiscount=" + omPercentageAfterVolumeDiscount + "]";
	}
	 

}
