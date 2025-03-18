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
@ApiModel(value="Pricing_Template",description="Pricing_Template")
@Table(name = "synprod.RP_Deal_Pricing_template")
public class DealPricingTemplate  implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "Id")
	@ApiModelProperty(notes="crm deal id Auto Incremented")
	private Integer id;
	
	@Column(name = "Rp_deal_version_ID")
	@ApiModelProperty(notes="Rp_deal_version_ID")
	private Integer rpdealVersionId;
	
	@Column(name ="skill_Mix")
	@ApiModelProperty(notes="skill_Mix")
	private String skillMix;
	
	@Column(name = "Master_role_code")
	@ApiModelProperty(notes="Master_role_code")
	private String masterRoleCode;
	
	@Column(name = "Band_Grade")
	@ApiModelProperty(notes="Band_Grade")
	private String bandGrade;
	
	@Column(name = "Customer_role")
	@ApiModelProperty(notes="Customer_role")
	private String customerRole;
	
	@Column(name = "RC_ID")
	@ApiModelProperty(notes="RC_ID")
	private Integer rcId;
	
	@Column(name = "TOWER_NAME")
	@ApiModelProperty(notes="TOWER_NAME")
	private String towerName;
	
	@Column(name="current_Onsite_bill_rate")
	@ApiModelProperty(notes="current_Onsite_bill_rate")
	private Double currentOnsiteBillRate;
	
	@Column(name="current_offshore_bill_rate")
	@ApiModelProperty(notes="current_offshore_bill_rate")
	private Double currentOffshoreBillRate;
	
	@Column(name="FTE_Year1_Onsite_ManMonath")
	@ApiModelProperty(notes="FTE_Year1_Onsite_ManMonath")
	private Double fteYearOneOnsiteManMonth;
	
	@Column(name="FTE_Year1_Offshore_ManMonath")
	@ApiModelProperty(notes="FTE_Year1_Offshore_ManMonath")
	private Double fteYearOneOffshoreManMonth;
	
	@Column(name="FTE_Year2_Onsite_ManMonath")
	@ApiModelProperty(notes="FTE_Year2_Onsite_ManMonath")
	private Double fteSecondYearOnsiteMonth;
	
	@Column(name="FTE_Year2_Offshore_ManMonath")
	@ApiModelProperty(notes="FTE_Year2_Offshore_ManMonath")
	private Double fteSecondYearOffshoreMonth;
	
	@Column(name="FTE_Year3_Onsite_ManMonath")
	@ApiModelProperty(notes="FTE_Year3_Onsite_ManMonath")
	private Double fteThirdYearOnsiteMonth;
	
	@Column(name="FTE_Year3_Offshore_ManMonath")
	@ApiModelProperty(notes="FTE_Year3_Offshore_ManMonath")
	private Double fteYear3OffshoreMonth;
	
	@Column(name="FTE_Year4_Onsite_ManMonath")
	@ApiModelProperty(notes="FTE_Year4_Onsite_ManMonath")
	private Double fteYear4OnsiteMonth;
	
	@Column(name="FTE_Year4_Offshore_ManMonath")
	@ApiModelProperty(notes="FTE_Year4_Offshore_ManMonath")
	private Double fteYear4OffshoreMonth;
	
	@Column(name="FTE_Year5_Onsite_ManMonath")
	@ApiModelProperty(notes="FTE_Year5_Onsite_ManMonath")
	private Double fteYear5OnsiteMonth;
	
	@Column(name="FTE_Year5_Offshore_ManMonath")
	@ApiModelProperty(notes="FTE_Year5_Offshore_ManMonath")
	private Double fteYear5OffshoreMonth;
	
	@Column(name="FTE_Year6_Onsite_ManMonath")
	@ApiModelProperty(notes="FTE_Year6_Onsite_ManMonath")
	private Double fteYear6OnsiteMonth;
	
	@Column(name="FTE_Year6_Offshore_ManMonath")
	@ApiModelProperty(notes="FTE_Year6_Offshore_ManMonath")
	private Double fteYear6OffshoreMonth;
	
	@Column(name="FTE_Year7_Onsite_ManMonath")
	@ApiModelProperty(notes="FTE_Year7_Onsite_ManMonath")
	private Double fteYear7OnsiteMonth;
	
	@Column(name="FTE_Year7_Offshore_ManMonath")
	@ApiModelProperty(notes="FTE_Year7_Offshore_ManMonath")
	private Double fteYear7OffshoreMonth;
	
	@Column(name="FTE_Year8_Onsite_ManMonath")
	@ApiModelProperty(notes="FTE_Year8_Onsite_ManMonath")
	private Double fteYear8OnsiteMonth;
	
	@Column(name="FTE_Year8_Offshore_ManMonath")
	@ApiModelProperty(notes="FTE_Year8_Offshore_ManMonath")
	private Double fteYear8OffshoreMonth;
	
	@Column(name="FTE_Year9_Onsite_ManMonath")
	@ApiModelProperty(notes="FTE_Year9_Onsite_ManMonath")
	private Double fteYear9OnsiteMonth;
	
	@Column(name="FTE_Year9_Offshore_ManMonath")
	@ApiModelProperty(notes="FTE_Year9_Offshore_ManMonath")
	private Double fteYear9OffshoreMonth;
	
	@Column(name="FTE_Year10_Onsite_ManMonath")
	@ApiModelProperty(notes="FTE_Year10_Onsite_ManMonath")
	private Double fteYear10OnsiteMonth;
	
	@Column(name="FTE_Year10_Offshore_ManMonath")
	@ApiModelProperty(notes="FTE_Year10_Offshore_ManMonath")
	private Double fteYear10OffShoreMonth;
	
	@Column(name="Onsite_TCV_localC")
	@ApiModelProperty(notes="Onsite_TCV_localC")
	private Double onsiteTCVLocalC;
	
	@Column(name="Offshore_TCV_localC")
	@ApiModelProperty(notes="Offshore_TCV_localC")
	private Double offShoreTCVLocalC;
	
	@Column(name="TCV_localC")
	@ApiModelProperty(notes="TCV_localC")
	private Double tcvLocalC;
	
	@Column(name="Onsite_TCV_billingC")
	@ApiModelProperty(notes="Onsite_TCV_billingC")
	private Double onsiteTcvBillingC;
	
	@Column(name="Offshore_TCV_billingC")
	@ApiModelProperty(notes="Offshore_TCV_billingC")
	private Double offshoreTCVBillingC;
	
	@Column(name="TCV_billingC")
	@ApiModelProperty(notes="TCV_billingC")
	private Double tcvBillingC;
	
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

	public Integer getRpdealVersionId() {
		return rpdealVersionId;
	}

	public void setRpdealVersionId(Integer rpdealVersionId) {
		this.rpdealVersionId = rpdealVersionId;
	}

	public String getMasterRoleCode() {
		return masterRoleCode;
	}

	public void setMasterRoleCode(String masterRoleCode) {
		this.masterRoleCode = masterRoleCode;
	}

	public String getBandGrade() {
		return bandGrade;
	}

	public void setBandGrade(String bandGrade) {
		this.bandGrade = bandGrade;
	}

	public String getCustomerRole() {
		return customerRole;
	}

	public void setCustomerRole(String customerRole) {
		this.customerRole = customerRole;
	}

	public Integer getRcId() {
		return rcId;
	}

	public void setRcId(Integer rcId) {
		this.rcId = rcId;
	}

	public String getTowerName() {
		return towerName;
	}

	public void setTowerName(String towerName) {
		this.towerName = towerName;
	}

	public Double getCurrentOnsiteBillRate() {
		return currentOnsiteBillRate;
	}

	public void setCurrentOnsiteBillRate(Double currentOnsiteBillRate) {
		this.currentOnsiteBillRate = currentOnsiteBillRate;
	}

	public Double getCurrentOffshoreBillRate() {
		return currentOffshoreBillRate;
	}

	public void setCurrentOffshoreBillRate(Double currentOffshoreBillRate) {
		this.currentOffshoreBillRate = currentOffshoreBillRate;
	}

	public Double getFteYearOneOnsiteManMonth() {
		return fteYearOneOnsiteManMonth;
	}

	public void setFteYearOneOnsiteManMonth(Double fteYearOneOnsiteManMonth) {
		this.fteYearOneOnsiteManMonth = fteYearOneOnsiteManMonth;
	}

	public Double getFteYearOneOffshoreManMonth() {
		return fteYearOneOffshoreManMonth;
	}

	public void setFteYearOneOffshoreManMonth(Double fteYearOneOffshoreManMonth) {
		this.fteYearOneOffshoreManMonth = fteYearOneOffshoreManMonth;
	}

	public Double getFteSecondYearOnsiteMonth() {
		return fteSecondYearOnsiteMonth;
	}

	public void setFteSecondYearOnsiteMonth(Double fteSecondYearOnsiteMonth) {
		this.fteSecondYearOnsiteMonth = fteSecondYearOnsiteMonth;
	}

	public Double getFteSecondYearOffshoreMonth() {
		return fteSecondYearOffshoreMonth;
	}

	public void setFteSecondYearOffshoreMonth(Double fteSecondYearOffshoreMonth) {
		this.fteSecondYearOffshoreMonth = fteSecondYearOffshoreMonth;
	}

	public Double getFteThirdYearOnsiteMonth() {
		return fteThirdYearOnsiteMonth;
	}

	public void setFteThirdYearOnsiteMonth(Double fteThirdYearOnsiteMonth) {
		this.fteThirdYearOnsiteMonth = fteThirdYearOnsiteMonth;
	}

	public Double getFteYear3OffshoreMonth() {
		return fteYear3OffshoreMonth;
	}

	public void setFteYear3OffshoreMonth(Double fteYear3OffshoreMonth) {
		this.fteYear3OffshoreMonth = fteYear3OffshoreMonth;
	}

	public Double getFteYear4OnsiteMonth() {
		return fteYear4OnsiteMonth;
	}

	public void setFteYear4OnsiteMonth(Double fteYear4OnsiteMonth) {
		this.fteYear4OnsiteMonth = fteYear4OnsiteMonth;
	}

	public Double getFteYear4OffshoreMonth() {
		return fteYear4OffshoreMonth;
	}

	public void setFteYear4OffshoreMonth(Double fteYear4OffshoreMonth) {
		this.fteYear4OffshoreMonth = fteYear4OffshoreMonth;
	}

	public Double getFteYear5OnsiteMonth() {
		return fteYear5OnsiteMonth;
	}

	public void setFteYear5OnsiteMonth(Double fteYear5OnsiteMonth) {
		this.fteYear5OnsiteMonth = fteYear5OnsiteMonth;
	}

	public Double getFteYear5OffshoreMonth() {
		return fteYear5OffshoreMonth;
	}

	public void setFteYear5OffshoreMonth(Double fteYear5OffshoreMonth) {
		this.fteYear5OffshoreMonth = fteYear5OffshoreMonth;
	}

	public Double getFteYear6OnsiteMonth() {
		return fteYear6OnsiteMonth;
	}

	public void setFteYear6OnsiteMonth(Double fteYear6OnsiteMonth) {
		this.fteYear6OnsiteMonth = fteYear6OnsiteMonth;
	}

	public Double getFteYear6OffshoreMonth() {
		return fteYear6OffshoreMonth;
	}

	public void setFteYear6OffshoreMonth(Double fteYear6OffshoreMonth) {
		this.fteYear6OffshoreMonth = fteYear6OffshoreMonth;
	}

	public Double getFteYear7OnsiteMonth() {
		return fteYear7OnsiteMonth;
	}

	public void setFteYear7OnsiteMonth(Double fteYear7OnsiteMonth) {
		this.fteYear7OnsiteMonth = fteYear7OnsiteMonth;
	}

	public Double getFteYear7OffshoreMonth() {
		return fteYear7OffshoreMonth;
	}

	public void setFteYear7OffshoreMonth(Double fteYear7OffshoreMonth) {
		this.fteYear7OffshoreMonth = fteYear7OffshoreMonth;
	}

	public Double getFteYear8OnsiteMonth() {
		return fteYear8OnsiteMonth;
	}

	public void setFteYear8OnsiteMonth(Double fteYear8OnsiteMonth) {
		this.fteYear8OnsiteMonth = fteYear8OnsiteMonth;
	}

	public Double getFteYear8OffshoreMonth() {
		return fteYear8OffshoreMonth;
	}

	public void setFteYear8OffshoreMonth(Double fteYear8OffshoreMonth) {
		this.fteYear8OffshoreMonth = fteYear8OffshoreMonth;
	}

	public Double getFteYear9OnsiteMonth() {
		return fteYear9OnsiteMonth;
	}

	public void setFteYear9OnsiteMonth(Double fteYear9OnsiteMonth) {
		this.fteYear9OnsiteMonth = fteYear9OnsiteMonth;
	}

	public Double getFteYear9OffshoreMonth() {
		return fteYear9OffshoreMonth;
	}

	public void setFteYear9OffshoreMonth(Double fteYear9OffshoreMonth) {
		this.fteYear9OffshoreMonth = fteYear9OffshoreMonth;
	}

	public Double getFteYear10OnsiteMonth() {
		return fteYear10OnsiteMonth;
	}

	public void setFteYear10OnsiteMonth(Double fteYear10OnsiteMonth) {
		this.fteYear10OnsiteMonth = fteYear10OnsiteMonth;
	}

	public Double getFteYear10OffShoreMonth() {
		return fteYear10OffShoreMonth;
	}

	public void setFteYear10OffShoreMonth(Double fteYear10OffShoreMonth) {
		this.fteYear10OffShoreMonth = fteYear10OffShoreMonth;
	}

	public Double getOnsiteTCVLocalC() {
		return onsiteTCVLocalC;
	}

	public void setOnsiteTCVLocalC(Double onsiteTCVLocalC) {
		this.onsiteTCVLocalC = onsiteTCVLocalC;
	}

	public Double getOffShoreTCVLocalC() {
		return offShoreTCVLocalC;
	}

	public void setOffShoreTCVLocalC(Double offShoreTCVLocalC) {
		this.offShoreTCVLocalC = offShoreTCVLocalC;
	}

	public Double getTcvLocalC() {
		return tcvLocalC;
	}

	public void setTcvLocalC(Double tcvLocalC) {
		this.tcvLocalC = tcvLocalC;
	}

	public Double getOnsiteTcvBillingC() {
		return onsiteTcvBillingC;
	}

	public void setOnsiteTcvBillingC(Double onsiteTcvBillingC) {
		this.onsiteTcvBillingC = onsiteTcvBillingC;
	}

	public Double getOffshoreTCVBillingC() {
		return offshoreTCVBillingC;
	}

	public void setOffshoreTCVBillingC(Double offshoreTCVBillingC) {
		this.offshoreTCVBillingC = offshoreTCVBillingC;
	}

	public Double getTcvBillingC() {
		return tcvBillingC;
	}

	public void setTcvBillingC(Double tcvBillingC) {
		this.tcvBillingC = tcvBillingC;
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

	public String getSkillMix() {
		return skillMix;
	}

	public void setSkillMix(String skillMix) {
		this.skillMix = skillMix;
	}
}
