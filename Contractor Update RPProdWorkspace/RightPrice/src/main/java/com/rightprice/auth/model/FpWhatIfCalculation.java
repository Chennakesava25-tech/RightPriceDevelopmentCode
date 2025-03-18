package com.rightprice.auth.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@Entity
@ApiModel(value = "FPDeal What if Calculation Details", description = "FPDeal What if Calculation Details")
@Table(name = "synprod.RP_Deal_what_if_details")
public class FpWhatIfCalculation implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "Deal_version_ID")
	private Integer dealVersionID;
	
	@Column(name = "Current_revenue")
	private Double currentRevenue;
	
	@Column(name = "Current_margin")
	private Double currentMargin;
	
	@Column(name = "Current_margin_percent")
	private Double currentMarginPercent;
	
	@Column(name = "Cost_without_CPC")
	private Double costWithoutCPC;
	
	@Column(name = "Current_CPC")
	private Double currentCPC;
	
	@Column(name = "Project_SGA")
	private Double projectSGA;
	
	@Column(name = "volume_discount_Percent")
	private Double volumeDiscountPercent;
	
	@Column(name = "BU_Margin")
	private Double buMargin;
	
	@Column(name = "COO_margin")
	private Double cooMargin;
	
	@Column(name = "CEO_margin")
	private Double ceoMargin;
	
	@Column(name = "Is_Active")
	private Integer isActive;
	
	@Column(name = "Created_By")
	private String createdBy;
	
	@Column(name = "Created_On")
	private String createdOn;
	
	@Column(name = "Updated_By")
	private String updatedBy;
	
	@Column(name = "Updated_On")
	private String updatedOn;
	
	@Column(name = "Total_hours")
	private Double totalHours;

	@Column(name = "Onsite_Total_hours")
	private Double onsiteTotalHours;
	
	@Column(name = "offshore_Total_hours")
	private Double offshoreTotalHours;
	
	@Column(name = "No_of_towers")
	private Integer noOfTowers;

	@Column(name = "No_of_years")
	private Integer noOfYears;
	
	@Column(name = "last_year_No_of_Month")
	private Integer lastYearNoOfMonth;
	
	@Column(name = "Revenue_or_Margin_Flag")
	private Integer revenueOrMarginFlag;

	@Column(name = "Is_staffing_complete")
	private Integer IsStaffingComplete;
	
	@Column(name = "No_of_transistion_month")
	private Double noOfTransistionMonth;
	
	
	@Column(name = "master_revenue")
	private Double masterRevenue;
	
	@Column(name = "RateCard_revenue")
	private Double rateCardRevenue;
	
	@Column(name = "contractor_revenue")
	private Double contractorRevenue;
	
	@Column(name = "MSA_revenue")
	private Double msaRevenue;
	
	public Integer getDealVersionID() {
		return dealVersionID;
	}
	
	public void setDealVersionID(Integer dealVersionID) {
		this.dealVersionID = dealVersionID;
	}

	public Double getCurrentRevenue() {
		return currentRevenue;
	}

	public void setCurrentRevenue(Double currentRevenue) {
		this.currentRevenue = currentRevenue;
	}

	public Double getCurrentMargin() {
		return currentMargin;
	}

	public void setCurrentMargin(Double currentMargin) {
		this.currentMargin = currentMargin;
	}

	public Double getCurrentMarginPercent() {
		return currentMarginPercent;
	}

	public void setCurrentMarginPercent(Double currentMarginPercent) {
		this.currentMarginPercent = currentMarginPercent;
	}

	public Double getCostWithoutCPC() {
		return costWithoutCPC;
	}

	public void setCostWithoutCPC(Double costWithoutCPC) {
		this.costWithoutCPC = costWithoutCPC;
	}

	public Double getCurrentCPC() {
		return currentCPC;
	}

	public void setCurrentCPC(Double currentCPC) {
		this.currentCPC = currentCPC;
	}

	public Double getProjectSGA() {
		return projectSGA;
	}

	public void setProjectSGA(Double projectSGA) {
		this.projectSGA = projectSGA;
	}

	public Double getVolumeDiscountPercent() {
		return volumeDiscountPercent;
	}

	public void setVolumeDiscountPercent(Double volumeDiscountPercent) {
		this.volumeDiscountPercent = volumeDiscountPercent;
	}

	public Double getBuMargin() {
		return buMargin;
	}

	public void setBuMargin(Double buMargin) {
		this.buMargin = buMargin;
	}

	public Double getCooMargin() {
		return cooMargin;
	}

	public void setCooMargin(Double cooMargin) {
		this.cooMargin = cooMargin;
	}

	public Double getCeoMargin() {
		return ceoMargin;
	}

	public void setCeoMargin(Double ceoMargin) {
		this.ceoMargin = ceoMargin;
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

	public Double getTotalHours() {
		return totalHours;
	}

	public void setTotalHours(Double totalHours) {
		this.totalHours = totalHours;
	}

	public Double getOnsiteTotalHours() {
		return onsiteTotalHours;
	}

	public void setOnsiteTotalHours(Double onsiteTotalHours) {
		this.onsiteTotalHours = onsiteTotalHours;
	}

	public Double getOffshoreTotalHours() {
		return offshoreTotalHours;
	}

	public void setOffshoreTotalHours(Double offshoreTotalHours) {
		this.offshoreTotalHours = offshoreTotalHours;
	}

	public Integer getNoOfTowers() {
		return noOfTowers;
	}

	public void setNoOfTowers(Integer noOfTowers) {
		this.noOfTowers = noOfTowers;
	}

	public Integer getNoOfYears() {
		return noOfYears;
	}

	public void setNoOfYears(Integer noOfYears) {
		this.noOfYears = noOfYears;
	}

	public Integer getLastYearNoOfMonth() {
		return lastYearNoOfMonth;
	}

	public void setLastYearNoOfMonth(Integer lastYearNoOfMonth) {
		this.lastYearNoOfMonth = lastYearNoOfMonth;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Integer getRevenueOrMarginFlag() {
		return revenueOrMarginFlag;
	}

	public void setRevenueOrMarginFlag(Integer revenueOrMarginFlag) {
		this.revenueOrMarginFlag = revenueOrMarginFlag;
	}

	public Integer getIsStaffingComplete() {
		return IsStaffingComplete;
	}

	public void setIsStaffingComplete(Integer isStaffingComplete) {
		IsStaffingComplete = isStaffingComplete;
	}

	public Double getNoOfTransistionMonth() {
		return noOfTransistionMonth;
	}

	public void setNoOfTransistionMonth(Double noOfTransistionMonth) {
		this.noOfTransistionMonth = noOfTransistionMonth;
	}

	public Double getMasterRevenue() {
		return masterRevenue;
	}

	public void setMasterRevenue(Double masterRevenue) {
		this.masterRevenue = masterRevenue;
	}

	public Double getRateCardRevenue() {
		return rateCardRevenue;
	}

	public void setRateCardRevenue(Double rateCardRevenue) {
		this.rateCardRevenue = rateCardRevenue;
	}

	public Double getContractorRevenue() {
		return contractorRevenue;
	}

	public void setContractorRevenue(Double contractorRevenue) {
		this.contractorRevenue = contractorRevenue;
	}

	public Double getMsaRevenue() {
		return msaRevenue;
	}

	public void setMsaRevenue(Double msaRevenue) {
		this.msaRevenue = msaRevenue;
	}
	
	@Column(name = "FX_Risk_Offshore")  
	private Double fxriskoffshore ;     
	@Column(name = "ContingentRisk")	
	private Double contingentrisk ;	      
	/* @Column(name = "TotalOnsiteDirectcost")	
	 * 	private Double totalonsitedirectcost ;*/
	@Column(name = "TotalOffshoreDirectcost")	
	private Double totaloffshoredirectcost ;
	@Column(name = "SLARisk")		
	private Double slarisk ;	
	@Column(name = "Totalriskamount")	
	private Double totalriskamount ;
	@Column(name = "ProjectMarginperinclRisk")
	private Double projectmarginperinclRisk ;
	@Column(name = "PMinclRiskandVD")	
	private Double pminclRiskandVD ;

	public Double getFxriskoffshore() {
		return fxriskoffshore;
	}

	public void setFxriskoffshore(Double fxriskoffshore) {
		this.fxriskoffshore = fxriskoffshore;
	}

	public Double getContingentrisk() {
		return contingentrisk;
	}

	public void setContingentrisk(Double contingentrisk) {
		this.contingentrisk = contingentrisk;
	}

	public Double getTotaloffshoredirectcost() {
		return totaloffshoredirectcost;
	}

	public void setTotaloffshoredirectcost(Double totaloffshoredirectcost) {
		this.totaloffshoredirectcost = totaloffshoredirectcost;
	}

	public Double getSlarisk() {
		return slarisk;
	}

	public void setSlarisk(Double slarisk) {
		this.slarisk = slarisk;
	}

	public Double getTotalriskamount() {
		return totalriskamount;
	}

	public void setTotalriskamount(Double totalriskamount) {
		this.totalriskamount = totalriskamount;
	}

	public Double getProjectmarginperinclRisk() {
		return projectmarginperinclRisk;
	}

	public void setProjectmarginperinclRisk(Double projectmarginperinclRisk) {
		this.projectmarginperinclRisk = projectmarginperinclRisk;
	}

	public Double getPminclRiskandVD() {
		return pminclRiskandVD;
	}

	public void setPminclRiskandVD(Double pminclRiskandVD) {
		this.pminclRiskandVD = pminclRiskandVD;
	}
	


				
}
