package com.rightprice.auth.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@Entity
@ApiModel(value="Role Utilization and Rates",description="Roles Utilization and Rates Model Attributes")
@Table(name = "synprod.RP_RATE_CARD_ROLES_WISE_RCGM_YEARLY_SUMMARY")
public class RateCardRoleUtilization implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8534665883572392911L;

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "RC_COST_SUMMARY_ID")
	@ApiModelProperty(notes="Rate Card Cost Summary ID Auto Incremented")
	private Integer rcCostSummaryId;
	
	@Column(name = "RC_ROLE_ID")
	@ApiModelProperty(notes="Rate Card Role ID")
	private Integer rcRoleId;
	
	@Column(name = "rc_id")
	@ApiModelProperty(notes="Rate Card ID")
	private Integer rcId;
	
	@Column(name = "RC_YEAR")
	@ApiModelProperty(notes="Rate Card Year")
	private Integer rcYear;
	
	@Column(name = "master_role_id",nullable = true)
	@ApiModelProperty(notes="smaster_role_id")
	private Integer masterRoleId;
	
	public Integer getMasterRoleId() {
		return masterRoleId;
	}

	public void setMasterRoleId(Integer masterRoleId) {
		this.masterRoleId = masterRoleId;
	}

	/*@Column(name = "step_year",nullable = true)
	@ApiModelProperty(notes="step year")
	private Integer stepYear;
	
	public Integer getStepYear() {
		return stepYear;
	}

	public void setStepYear(Integer stepYear) {
		this.stepYear = stepYear;
	}*/

	@Column(name = "RC_LOCATION_ID")
	@ApiModelProperty(notes="Rate Card Location ID")
	private Integer rcLocationId;
	
	@Column(name = "RC_COUNTRY_ID")
	@ApiModelProperty(notes="Rate Card country ID")
	private Integer rcCountryId;
	
	public Integer getRcCountryId() {
		return rcCountryId;
	}

	public void setRcCountryId(Integer rcCountryId) {
		this.rcCountryId = rcCountryId;
	}

	@Column(name = "RC_CITY_ID")
	@ApiModelProperty(notes="Rate Card city ID")
	private Integer rcCityId;
	
	@Column(name = "TR_START_DATE")
	@ApiModelProperty(notes="Rate Card Start Date")
	private String trStartDate;
	
	@Column(name = "TR_END_DATE")
	@ApiModelProperty(notes="Rate Card End Date")
	private String trEndDate;
	
	@Column(name = "MONTH_YEAR_HEADER")
	@ApiModelProperty(notes="Rate Card Month Year")
	private String monthYearHeader;
	
	@Column(name = "TRANSACTION_MONTH")
	@ApiModelProperty(notes="Rate Card Transaction Month")
	private Integer trasactionMonth;
	
	@Column(name = "TRANSACTION_YEAR")
	@ApiModelProperty(notes="Rate Card Transaction Year")
	private Integer transactionYear;
	
	/*@Column(name = "DEPUTED_UTILIZATION")
	@ApiModelProperty(notes="Rate Card Deputed Utilization")
	private Double deputedUtilization;*/
	
	@Column(name = "DEPUTED_COST")
	@ApiModelProperty(notes="Rate Card Deputed Cost")
	private Double deputedCost;
	
	@Column(name = "DEPUTED_REVENUE")
	@ApiModelProperty(notes="Rate Card Deputed Revenue")
	private Double deputedRevenue;
	
	@Column(name = "DEPUTED_MARGIN")
	@ApiModelProperty(notes="Rate Card Deputed Margin")
	private Double deputedMargin;
	
	@Column(name = "DEPUTED_HOURS")
	@ApiModelProperty(notes="Rate Card Deputed Hour")
	private Double deputedHour;
	
	@Column(name = "DEPUTED_USAGE_PERCENT")
	@ApiModelProperty(notes="Deputed Usage Percent")
	private Double deputedUsagePercent;
	
	@Column(name = "DOMESTIC_USAGE_PERCENT")
	@ApiModelProperty(notes="Domestic Usage Percent")
	private Double domesticUsagePercent;
	
	public Double getDeputedUsagePercent() {
		return deputedUsagePercent;
	}

	public void setDeputedUsagePercent(Double deputedUsagePercent) {
		this.deputedUsagePercent = deputedUsagePercent;
	}

	public Double getDomesticUsagePercent() {
		return domesticUsagePercent;
	}

	public void setDomesticUsagePercent(Double domesticUsagePercent) {
		this.domesticUsagePercent = domesticUsagePercent;
	}

	public Double getOnsiteActualGmPercent() {
		return onsiteActualGmPercent;
	}

	public void setOnsiteActualGmPercent(Double onsiteActualGmPercent) {
		this.onsiteActualGmPercent = onsiteActualGmPercent;
	}

	@Column(name = "DEPUTED_COST_BREAKUP_ID")
	@ApiModelProperty(notes="Rate Card Deputed Cost Breakup Id")
	private Double deputedCostBreakupId;
	
	/*@Column(name = "DOMESTIC_UTILIZATION")
	@ApiModelProperty(notes="Rate Card Domestic Utilization")
	private Double domesticUtilization;*/
	
	@Column(name = "DOMESTIC_COST")
	@ApiModelProperty(notes="Rate Card Domestic Cost")
	private Double domesticCost;
	
	@Column(name = "DOMESTIC_REVENUE")
	@ApiModelProperty(notes="Rate Card Domestic Revenue")
	private Double domesticRevenue;
	
	@Column(name = "DOMESTIC_MARGIN")
	@ApiModelProperty(notes="Rate Card Domestic Margin")
	private Double domesticMargin;
	
	@Column(name = "DOMESTIC_HOURS")
	@ApiModelProperty(notes="Rate Card Domestic Hour")
	private Double domesticHour;
	
	@Column(name = "DOMESTIC_COST_BREAKUP_ID")
	@ApiModelProperty(notes="Rate Card Domestic Cost Breakup Id")
	private Double domesticCostBreakupId;
	
	@Column(name = "ONSITE_USAGE")
	@ApiModelProperty(notes="Rate Card Onsite Usage")
	private Double onsiteUsage;
	
	@Column(name = "ONSITE_MASTER_RATE")
	@ApiModelProperty(notes="Rate Card Onsite Master Rate")
	private Double onsiteMasterRate;
	
	@Column(name = "ONSITE_ACTUAL_GM_PERCENT")
	@ApiModelProperty(notes="Rate Card Onsite Rate Utilization")
	private Double onsiteActualGmPercent;
	
	@Column(name = "Onsite_Cost")
	@ApiModelProperty(notes="Rate Card Onsite Cost")
	private Double onsiteCost;
	
	@Column(name = "ONSITE_REVENUE")
	@ApiModelProperty(notes="Rate Card Onsite Revenue")
	private Double onsiteRevenue;
	
	@Column(name = "ONSITE_MASTER_GM_PERCENT")
	@ApiModelProperty(notes="Rate Card Onsite Master GM Percent")
	private Double onsiteMasterGmPercent;
	
	@Column(name = "ONSITE_PROPOSED_CLIENT_RATE")
	@ApiModelProperty(notes="Rate Card Onsite Proposed Client Rate")
	private Double onsiteProposedClientRate;
	
	@Column(name = "ONSITE_DISCOUNT_PREMIUM")
	@ApiModelProperty(notes="Rate Card Onsite Discount Premium")
	private Double onsiteDiscountPremium;
	
	@Column(name = "OFFSHORE_USAGE")
	@ApiModelProperty(notes="Rate Card Offshore Usage")
	private Double offshoreUsage;
	
	@Column(name = "OFFSHORE_REVENUE")
	@ApiModelProperty(notes="Rate Card Offshore Revenue")
	private Double offshoreRevenue;
	
	@Column(name = "OFFSHORE_COST")
	@ApiModelProperty(notes="Rate Card Offshore Cost")
	private Double offshoreCost;
	
	@Column(name = "OFFSHORE_MARGIN")
	@ApiModelProperty(notes="Rate Card Offshore Margin")
	private Double offshoreMargin;
	
	@Column(name = "OFFSHORE_HOURS")
	@ApiModelProperty(notes="Rate Card Offshore Hours")
	private Double offshoreHours;
	
	public Double getOffshoreHours() {
		return offshoreHours;
	}

	public void setOffshoreHours(Double offshoreHours) {
		this.offshoreHours = offshoreHours;
	}

	@Column(name = "OFFSHORE_MASTER_RATE")
	@ApiModelProperty(notes="Rate Card Offshore Master Rate")
	private Double offshoreMasterRate;
	
	@Column(name = "OFFSHORE_ACTUAL_GM_PERCENT")
	@ApiModelProperty(notes="Rate Card Offshore Rate Utilization")
	private Double offshoreActualGmPercent;
	
	@Column(name = "OFFSHORE_MASTER_RATE_GM_PERCENT")
	@ApiModelProperty(notes="Rate Card Offshore Master Rate GM Percent")
	private Double offshoreMasterRateGmPercent;
	
	@Column(name = "OFFSHORE_PROPOSED_CLIENT_RATE")
	@ApiModelProperty(notes="Rate Card Offshore Proposed Client Rate")
	private Double offshoreProposedClientRate;
	
	@Column(name = "OFFSHORE_DISCOUNT_PREMIUM")
	@ApiModelProperty(notes="Rate Card Offshore Discount Premium")
	private Double offshoreDiscountPremium;
	
	public Double getOnsiteCost() {
		return onsiteCost;
	}

	public void setOnsiteCost(Double onsiteCost) {
		this.onsiteCost = onsiteCost;
	}

	@Column(name = "OFFSHORE_COST_BREAKUP_ID")
	@ApiModelProperty(notes="Rate Card Offshore Cost Breakup ID")
	private Integer offshoreCostBreakupId;
	
	@Column(name = "DEPUTED_COST_ON_UTILIZATION")
	@ApiModelProperty(notes="Rate Card Deputed Cost On Utilization")
	private Double deputedCostOnUtilization;
	
	@Column(name = "DOMESTIC_COST_ON_UTILIZATION")
	@ApiModelProperty(notes="Rate Card Domestic Cost On Utilization")
	private Double domesticCostOnUtilization;
	
	@Column(name = "VOLUME_DISCOUNT")
	@ApiModelProperty(notes="Volume Discount")
	private Double volumeDiscount;
	
	@Column(name = "COMMENTS")
	@ApiModelProperty(notes="Comments")
	private String comments;
	

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	@Column(name = "IS_SUM_TOTAL")
	@ApiModelProperty(notes="Is Sum Total")
	private Integer isSumTotal;
	
	@Column(name = "IS_ACTIVE")
	@ApiModelProperty(notes="Active status")
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
	
	@OneToOne(optional = true, fetch = FetchType.LAZY)
    @JoinColumn(name = "MASTER_ROLE_ID",insertable=false,updatable=false)
	private MasterRoles masterRoles;
	
	@OneToOne(optional = true, fetch = FetchType.LAZY)
    @JoinColumns({ 
    	@JoinColumn(name = "STEP_YEAR",referencedColumnName="STEP_YEAR",insertable=false,updatable=false),
    	@JoinColumn(name = "RC_ID",referencedColumnName="RC_ID",insertable=false,updatable=false)
    })
	private RateCardYOYIncrement rateCardYOYIncrement;
	
	@OneToOne(optional = true, fetch = FetchType.LAZY)
    @JoinColumns({ 
    	@JoinColumn(name = "RC_ROLE_ID",referencedColumnName="RC_ROLE_ID",insertable=false,updatable=false),
    	@JoinColumn(name = "RC_ID",referencedColumnName="RC_ID",insertable=false,updatable=false)
    })
	private RPRateCardRoles rateCardRoles;

	public RPRateCardRoles getRateCardRoles() {
		return rateCardRoles;
	}

	public void setRateCardRoles(RPRateCardRoles rateCardRoles) {
		this.rateCardRoles = rateCardRoles;
	}

	public Integer getRcCostSummaryId() {
		return rcCostSummaryId;
	}

	public RateCardYOYIncrement getRateCardYOYIncrement() {
		return rateCardYOYIncrement;
	}

	public void setRateCardYOYIncrement(RateCardYOYIncrement rateCardYOYIncrement) {
		this.rateCardYOYIncrement = rateCardYOYIncrement;
	}

	public void setRcCostSummaryId(Integer rcCostSummaryId) {
		this.rcCostSummaryId = rcCostSummaryId;
	}

	public Integer getRcRoleId() {
		return rcRoleId;
	}

	public void setRcRoleId(Integer rcRoleId) {
		this.rcRoleId = rcRoleId;
	}

	public Integer getRcId() {
		return rcId;
	}

	public void setRcId(Integer rcId) {
		this.rcId = rcId;
	}

	public Integer getRcYear() {
		return rcYear;
	}

	public void setRcYear(Integer rcYear) {
		this.rcYear = rcYear;
	}

	public Integer getRcLocationId() {
		return rcLocationId;
	}

	public void setRcLocationId(Integer rcLocationId) {
		this.rcLocationId = rcLocationId;
	}

	/*public Integer getMasterRoleId() {
		return masterRoleId;
	}

	public void setMasterRoleId(Integer masterRoleId) {
		this.masterRoleId = masterRoleId;
	}*/

	public Integer getTrasactionMonth() {
		return trasactionMonth;
	}

	public void setTrasactionMonth(Integer trasactionMonth) {
		this.trasactionMonth = trasactionMonth;
	}

	public Integer getTransactionYear() {
		return transactionYear;
	}

	public void setTransactionYear(Integer transactionYear) {
		this.transactionYear = transactionYear;
	}

/*	public Double getDeputedUtilization() {
		return deputedUtilization;
	}

	public void setDeputedUtilization(Double deputedUtilization) {
		this.deputedUtilization = deputedUtilization;
	}*/

	public Double getDeputedCost() {
		return deputedCost;
	}

	public void setDeputedCost(Double deputedCost) {
		this.deputedCost = deputedCost;
	}

	public Double getDeputedRevenue() {
		return deputedRevenue;
	}

	public void setDeputedRevenue(Double deputedRevenue) {
		this.deputedRevenue = deputedRevenue;
	}

	public Double getDeputedMargin() {
		return deputedMargin;
	}

	public void setDeputedMargin(Double deputedMargin) {
		this.deputedMargin = deputedMargin;
	}

	public Double getDeputedCostBreakupId() {
		return deputedCostBreakupId;
	}

	public void setDeputedCostBreakupId(Double deputedCostBreakupId) {
		this.deputedCostBreakupId = deputedCostBreakupId;
	}

/*	public Double getDomesticUtilization() {
		return domesticUtilization;
	}

	public void setDomesticUtilization(Double domesticUtilization) {
		this.domesticUtilization = domesticUtilization;
	}*/

	public Double getDomesticCost() {
		return domesticCost;
	}

	public void setDomesticCost(Double domesticCost) {
		this.domesticCost = domesticCost;
	}

	public Double getDomesticRevenue() {
		return domesticRevenue;
	}

	public void setDomesticRevenue(Double domesticRevenue) {
		this.domesticRevenue = domesticRevenue;
	}

	public Double getDomesticMargin() {
		return domesticMargin;
	}

	public void setDomesticMargin(Double domesticMargin) {
		this.domesticMargin = domesticMargin;
	}

	public Double getDomesticCostBreakupId() {
		return domesticCostBreakupId;
	}

	public void setDomesticCostBreakupId(Double domesticCostBreakupId) {
		this.domesticCostBreakupId = domesticCostBreakupId;
	}

	public Double getOnsiteUsage() {
		return onsiteUsage;
	}

	public void setOnsiteUsage(Double onsiteUsage) {
		this.onsiteUsage = onsiteUsage;
	}

	public Double getOnsiteMasterRate() {
		return onsiteMasterRate;
	}

	public void setOnsiteMasterRate(Double onsiteMasterRate) {
		this.onsiteMasterRate = onsiteMasterRate;
	}

	/*public Double getOnsiteRateOnUtilization() {
		return onsiteActualGmPercent;
	}

	public void setOnsiteRateOnUtilization(Double onsiteActualGmPercent) {
		this.onsiteActualGmPercent = onsiteActualGmPercent;
	}*/


	public Double getOnsiteRevenue() {
		return onsiteRevenue;
	}

	public void setOnsiteRevenue(Double onsiteRevenue) {
		this.onsiteRevenue = onsiteRevenue;
	}

	public Double getOnsiteMasterGmPercent() {
		return onsiteMasterGmPercent;
	}

	public void setOnsiteMasterGmPercent(Double onsiteMasterGmPercent) {
		this.onsiteMasterGmPercent = onsiteMasterGmPercent;
	}

	public Double getOnsiteProposedClientRate() {
		return onsiteProposedClientRate;
	}

	public void setOnsiteProposedClientRate(Double onsiteProposedClientRate) {
		this.onsiteProposedClientRate = onsiteProposedClientRate;
	}

	public Double getOnsiteDiscountPremium() {
		return onsiteDiscountPremium;
	}

	public void setOnsiteDiscountPremium(Double onsiteDiscountPremium) {
		this.onsiteDiscountPremium = onsiteDiscountPremium;
	}

	public Double getOffshoreUsage() {
		return offshoreUsage;
	}

	public void setOffshoreUsage(Double offshoreUsage) {
		this.offshoreUsage = offshoreUsage;
	}

	public Double getOffshoreRevenue() {
		return offshoreRevenue;
	}

	public void setOffshoreRevenue(Double offshoreRevenue) {
		this.offshoreRevenue = offshoreRevenue;
	}

	public Double getOffshoreCost() {
		return offshoreCost;
	}

	public void setOffshoreCost(Double offshoreCost) {
		this.offshoreCost = offshoreCost;
	}

	public Double getOffshoreMargin() {
		return offshoreMargin;
	}

	public void setOffshoreMargin(Double offshoreMargin) {
		this.offshoreMargin = offshoreMargin;
	}

	public Double getOffshoreMasterRate() {
		return offshoreMasterRate;
	}

	public void setOffshoreMasterRate(Double offshoreMasterRate) {
		this.offshoreMasterRate = offshoreMasterRate;
	}


	public Double getOffshoreActualGmPercent() {
		return offshoreActualGmPercent;
	}

	public void setOffshoreActualGmPercent(Double offshoreActualGmPercent) {
		this.offshoreActualGmPercent = offshoreActualGmPercent;
	}

	public Double getOffshoreMasterRateGmPercent() {
		return offshoreMasterRateGmPercent;
	}

	public void setOffshoreMasterRateGmPercent(Double offshoreMasterRateGmPercent) {
		this.offshoreMasterRateGmPercent = offshoreMasterRateGmPercent;
	}

	public Double getOffshoreProposedClientRate() {
		return offshoreProposedClientRate;
	}

	public void setOffshoreProposedClientRate(Double offshoreProposedClientRate) {
		this.offshoreProposedClientRate = offshoreProposedClientRate;
	}

	public Double getOffshoreDiscountPremium() {
		return offshoreDiscountPremium;
	}

	public void setOffshoreDiscountPremium(Double offshoreDiscountPremium) {
		this.offshoreDiscountPremium = offshoreDiscountPremium;
	}

	public Integer getOffshoreCostBreakupId() {
		return offshoreCostBreakupId;
	}

	public void setOffshoreCostBreakupId(Integer offshoreCostBreakupId) {
		this.offshoreCostBreakupId = offshoreCostBreakupId;
	}

	public Double getDeputedCostOnUtilization() {
		return deputedCostOnUtilization;
	}

	public void setDeputedCostOnUtilization(Double deputedCostOnUtilization) {
		this.deputedCostOnUtilization = deputedCostOnUtilization;
	}

	public Double getDomesticCostOnUtilization() {
		return domesticCostOnUtilization;
	}

	public void setDomesticCostOnUtilization(Double domesticCostOnUtilization) {
		this.domesticCostOnUtilization = domesticCostOnUtilization;
	}

	public Integer getIsSumTotal() {
		return isSumTotal;
	}

	public void setIsSumTotal(Integer isSumTotal) {
		this.isSumTotal = isSumTotal;
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

	public MasterRoles getMasterRoles() {
		return masterRoles;
	}

	public void setMasterRoles(MasterRoles masterRoles) {
		this.masterRoles = masterRoles;
	}

	public String getTrStartDate() {
		return trStartDate;
	}

	public void setTrStartDate(String trStartDate) {
		this.trStartDate = trStartDate;
	}

	public String getTrEndDate() {
		return trEndDate;
	}

	public void setTrEndDate(String trEndDate) {
		this.trEndDate = trEndDate;
	}

	public String getMonthYearHeader() {
		return monthYearHeader;
	}

	public void setMonthYearHeader(String monthYearHeader) {
		this.monthYearHeader = monthYearHeader;
	}

	public Double getVolumeDiscount() {
		return volumeDiscount;
	}

	public void setVolumeDiscount(Double volumeDiscount) {
		this.volumeDiscount = volumeDiscount;
	}

	public Double getDeputedHour() {
		return deputedHour;
	}

	public void setDeputedHour(Double deputedHour) {
		this.deputedHour = deputedHour;
	}

	public Double getDomesticHour() {
		return domesticHour;
	}

	public void setDomesticHour(Double domesticHour) {
		this.domesticHour = domesticHour;
	}

	public Integer getRcCityId() {
		return rcCityId;
	}

	public void setRcCityId(Integer rcCityId) {
		this.rcCityId = rcCityId;
	}

}
