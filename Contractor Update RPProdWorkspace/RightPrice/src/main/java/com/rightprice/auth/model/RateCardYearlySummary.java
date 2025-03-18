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

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@Entity
@ApiModel(value="yearly summary",description="Rate card yearly summary")
@Table(name = "synprod.RP_Rate_Card_Yearly_Summary")
public class RateCardYearlySummary {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "RC_Yearly_Summary_Id")
	private Integer rcYearlySummaryId;
	
	@Column(name = "RC_Id")
	private Integer rcId;
	
	@Column(name = "RC_Location_Id")
	private Integer rcLocationId;
	
	@Column(name = "Proposed_Currency_Id")
	private Integer proposedCurrencyId;
	
	@Column(name = "Country_Id")
	private Integer countryId;
	
	@Column(name = "Country_City_Name")
	private String countryCityName;
	
	@Column(name = "Blended_Rate")	
	private Double blendedRate;
	
	@Column(name = "Blended_Cost")	
	private Double blendedCost;
	
	@Column(name = "Increment_Percent")	
	private Double incrementPresent;
	
	@Column(name = "Calculated_RP_GM_Percentage")	
	private Double calaculateRpGmPercentage;
	
	@Column(name = "Calculated_RP_GM_Percentage_Post_Discount")	
	private Double calculateRpGmPercentagePostDiscount;
	
	@Column(name = "Calculated_GM_Percentage")	
	private Double calculateGmPercentage;
	
	@Column(name = "Calculated_GM_Percentage_Post_Discount")	
	private Double calculatedGmPercentagePostDiscount;
	
	@Column(name = "Discount")	
	private Double discount;
	
	@Column(name = "Month_Year_Header")	
	private String monthYearHeader;
	
	@Column(name = "Step_Year")	
	private Integer stepYear;
	
	@Column(name = "Transaction_Year")	
	private Integer transactionYear;
	
	@Column(name = "Summary_Type")	
	private Integer summaryType;
	
	@Column(name = "IS_ACTIVE")
	@ApiModelProperty(notes="Active status")
	private Integer isActive;

	public Integer getRcYearlySummaryId() {
		return rcYearlySummaryId;
	}

	public void setRcYearlySummaryId(Integer rcYearlySummaryId) {
		this.rcYearlySummaryId = rcYearlySummaryId;
	}

	public Integer getRcId() {
		return rcId;
	}

	public void setRcId(Integer rcId) {
		this.rcId = rcId;
	}

	public Integer getRcLocationId() {
		return rcLocationId;
	}

	public void setRcLocationId(Integer rcLocationId) {
		this.rcLocationId = rcLocationId;
	}

	public Integer getProposedCurrencyId() {
		return proposedCurrencyId;
	}

	public void setProposedCurrencyId(Integer proposedCurrencyId) {
		this.proposedCurrencyId = proposedCurrencyId;
	}

	public Integer getCountryId() {
		return countryId;
	}

	public void setCountryId(Integer countryId) {
		this.countryId = countryId;
	}

	public String getCountryCityName() {
		return countryCityName;
	}

	public void setCountryCityName(String countryCityName) {
		this.countryCityName = countryCityName;
	}

	public Double getBlendedRate() {
		return blendedRate;
	}

	public void setBlendedRate(Double blendedRate) {
		this.blendedRate = blendedRate;
	}

	public Double getBlendedCost() {
		return blendedCost;
	}

	public void setBlendedCost(Double blendedCost) {
		this.blendedCost = blendedCost;
	}

	public Double getIncrementPresent() {
		return incrementPresent;
	}

	public void setIncrementPresent(Double incrementPresent) {
		this.incrementPresent = incrementPresent;
	}

	public Double getCalaculateRpGmPercentage() {
		return calaculateRpGmPercentage;
	}

	public void setCalaculateRpGmPercentage(Double calaculateRpGmPercentage) {
		this.calaculateRpGmPercentage = calaculateRpGmPercentage;
	}

	public Double getCalculateRpGmPercentagePostDiscount() {
		return calculateRpGmPercentagePostDiscount;
	}

	public void setCalculateRpGmPercentagePostDiscount(Double calculateRpGmPercentagePostDiscount) {
		this.calculateRpGmPercentagePostDiscount = calculateRpGmPercentagePostDiscount;
	}

	public Double getCalculateGmPercentage() {
		return calculateGmPercentage;
	}

	public void setCalculateGmPercentage(Double calculateGmPercentage) {
		this.calculateGmPercentage = calculateGmPercentage;
	}

	public Double getCalculatedGmPercentagePostDiscount() {
		return calculatedGmPercentagePostDiscount;
	}

	public void setCalculatedGmPercentagePostDiscount(Double calculatedGmPercentagePostDiscount) {
		this.calculatedGmPercentagePostDiscount = calculatedGmPercentagePostDiscount;
	}

	public Double getDiscount() {
		return discount;
	}

	public void setDiscount(Double discount) {
		this.discount = discount;
	}

	public String getMonthYearHeader() {
		return monthYearHeader;
	}

	public void setMonthYearHeader(String monthYearHeader) {
		this.monthYearHeader = monthYearHeader;
	}

	public Integer getStepYear() {
		return stepYear;
	}

	public void setStepYear(Integer stepYear) {
		this.stepYear = stepYear;
	}

	public Integer getTransactionYear() {
		return transactionYear;
	}

	public void setTransactionYear(Integer transactionYear) {
		this.transactionYear = transactionYear;
	}

	public Integer getSummaryType() {
		return summaryType;
	}

	public void setSummaryType(Integer summaryType) {
		this.summaryType = summaryType;
	}

	public Integer getIsActive() {
		return isActive;
	}

	public void setIsActive(Integer isActive) {
		this.isActive = isActive;
	}
	
}
