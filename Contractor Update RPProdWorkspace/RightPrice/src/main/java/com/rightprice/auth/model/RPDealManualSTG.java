package com.rightprice.auth.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import io.swagger.annotations.ApiModelProperty;

@Entity
@Table(name = "[synprod].[RP_Deal_Manual_STG]")
public class RPDealManualSTG 
{

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ManualDealRP_Id")
	@ApiModelProperty(notes="manualDealRPId")
	private Integer manualDealRPId;
	
	@Column(name = "RP_Deal_Version_Id")
	@ApiModelProperty(notes="RP_Deal_Version_Id")
	private Integer RPDealVersionId;
	
	@Column(name = "Tower_Id")
	@ApiModelProperty(notes="towerId")
	private Integer towerId;
	
	@Column(name = "Onsite_Percentage")
	@ApiModelProperty(notes="Onsite Percentage")
	private Double onsitePercentage;
	
	@Column(name = "Offshore_Percentage")
	@ApiModelProperty(notes="Offshore Percentage")
	private Double OffshorePercentage;
	
	@Column(name = "Revenue")
	@ApiModelProperty(notes="Revenue")
	private BigDecimal revenue;
	
	@Column(name = "Direct_Cost")
	@ApiModelProperty(notes="Direct Cost")
	private BigDecimal directCost;
	
	@Column(name = "Gross_Margin_Percentage")
	@ApiModelProperty(notes="Gross Margin Percentage")
	private Double grossMarginPercentage;
	
	@Column(name = "Project_Specific_SGA_Including_Agile_Cost")
	@ApiModelProperty(notes="Project Specific SGA Including Agile Cost")
	private Double projectSpecificSGAIncludingAgileCost;
		
	@Column(name = "OM_Percentage")
	@ApiModelProperty(notes="OM Percentage")
	private Double oMPercentage;
	
	@Column(name = "Volume_Discount_Percentage")
	@ApiModelProperty(notes="Volume Discount Percentage")
	private Double volumeDiscountPercentage;
	
	@Column(name = "Penalty_Percentage")
	@ApiModelProperty(notes="Penalty Percentage")
	private Double penaltyPercentage;
	
	@Column(name = "OM_Percentage_After_Volume_Discount")
	@ApiModelProperty(notes="OM Percentage After Volume Discount")
	private Double oMPercentageAfterVolumeDiscount;
	
		
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

	public Integer getManualDealRPId() {
		return manualDealRPId;
	}

	public void setManualDealRPId(Integer manualDealRPId) {
		this.manualDealRPId = manualDealRPId;
	}

	public Integer getRPDealVersionId() {
		return RPDealVersionId;
	}

	public void setRPDealVersionId(Integer rPDealVersionId) {
		RPDealVersionId = rPDealVersionId;
	}

	public Integer getTowerId() {
		return towerId;
	}

	public void setTowerId(Integer towerId) {
		this.towerId = towerId;
	}

	public Double getOnsitePercentage() {
		return onsitePercentage;
	}

	public void setOnsitePercentage(Double onsitePercentage) {
		this.onsitePercentage = onsitePercentage;
	}

	public Double getOffshorePercentage() {
		return OffshorePercentage;
	}

	public void setOffshorePercentage(Double offshorePercentage) {
		OffshorePercentage = offshorePercentage;
	}

	

	public BigDecimal getRevenue() {
		return revenue;
	}

	public void setRevenue(BigDecimal revenue) {
		this.revenue = revenue;
	}

	public BigDecimal getDirectCost() {
		return directCost;
	}

	public void setDirectCost(BigDecimal directCost) {
		this.directCost = directCost;
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

	public Double getoMPercentage() {
		return oMPercentage;
	}

	public void setoMPercentage(Double oMPercentage) {
		this.oMPercentage = oMPercentage;
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

	public Double getoMPercentageAfterVolumeDiscount() {
		return oMPercentageAfterVolumeDiscount;
	}

	public void setoMPercentageAfterVolumeDiscount(Double oMPercentageAfterVolumeDiscount) {
		this.oMPercentageAfterVolumeDiscount = oMPercentageAfterVolumeDiscount;
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
	@Column(name = "Project_Margin")
	@ApiModelProperty(notes="Project Margin")
	private Double projectMargin;
	
	public Double getProjectMargin() {
		return projectMargin;
	}

	public void setProjectMargin(Double projectMargin) {
		this.projectMargin = projectMargin;
	}
	@Column(name = "Project_Margin_percentage")
	@ApiModelProperty(notes="Project Margin percentage")
	private Double projectMarginPercentage;
	
	@Column(name = "Project_Margin_incl_Risk")
	@ApiModelProperty(notes="Project Margin incl Risk")
	private Double pminclrisk;
	
	@Column(name = "Project_Margin_incl_Risk_VD")
	@ApiModelProperty(notes="Project Margin incl Risk VD")
	private Double pminclRiskVD;

	
	
	public Double getProjectMarginPercentage() {
		return projectMarginPercentage;
	}

	public void setProjectMarginPercentage(Double projectMarginPercentage) {
		this.projectMarginPercentage = projectMarginPercentage;
	}

	public Double getPminclrisk() {
		return pminclrisk;
	}

	public void setPminclrisk(Double pminclrisk) {
		this.pminclrisk = pminclrisk;
	}

	public Double getPminclRiskVD() {
		return pminclRiskVD;
	}

	public void setPminclRiskVD(Double pminclRiskVD) {
		this.pminclRiskVD = pminclRiskVD;
	}
	
	
	
}
