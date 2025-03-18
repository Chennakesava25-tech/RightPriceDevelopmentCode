package com.rightprice.auth.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@Entity
@ApiModel(value = "RP OLD deal", description = "RP OLD deal")
@Table(name = "synprod.RP_OLD_deal")
public class RpOldDeal implements Serializable {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	@Column(name = "Customer_ID")
	@ApiModelProperty(notes="CRP Deal Id")
	private int customerId;
	
	@Id
	@Column(name = "CRM_Deal_ID")
	@ApiModelProperty(notes="CRP Deal Id")
	private String crmDealId;
	
	@Column(name = "Deal_description")
	@ApiModelProperty(notes="CRP Deal Id")
	private String dealDescription;
	
	@Column(name = "Deal_Start_Date")
	@ApiModelProperty(notes="CRP Deal Id")
	private String dealStartDate;
	
	@Column(name = "Deal_End_Date")
	@ApiModelProperty(notes="CRP Deal Id")
	private String dealEndDate;
	
	@Column(name = "Deal_type")
	@ApiModelProperty(notes="CRP Deal Id")
	private int dealType;
	
	@Column(name = "Is_active")
	@ApiModelProperty(notes="Active status")
	private int isActive;
	
	@Column(name = "Created_By")
	@ApiModelProperty(notes="Created by")
	private String createdBy;
	
	@Column(name = "Created_On")
	@ApiModelProperty(notes="Created date")
	private String createdOn;
	
	@Column(name = "Updated_By")
	@ApiModelProperty(notes="Updated by")
	private String updatedBy;
	
	@Column(name = "Updated_On")
	@ApiModelProperty(notes="Updated date")
	private String updatedOn;
	
	@Column(name = "Revenue")
	@ApiModelProperty(notes="Revenue")
	private Double revenue;
	
	@Column(name = "GM")
	@ApiModelProperty(notes="GM")
	private Double gm;
	
	@Column(name = "OM_After_VD")
	@ApiModelProperty(notes="OM_After_VD")
	private Double omAfterVD;
	
	@Column(name = "Onsite_Total")
	@ApiModelProperty(notes="Onsite_Total")
	private Double onsiteTotal;
	
	@Column(name = "Offshore_Total")
	@ApiModelProperty(notes="Offshore_Total")
	private Double offshoreTotal;
	
	@Column(name = "Total_Efforts")
	@ApiModelProperty(notes="Total_Efforts")
	private Double totalEfforts;
	
	@Column(name = "B2_Percentage")
	@ApiModelProperty(notes="B2_Percentage")
	private Double b2Percentage;
	
	@Column(name = "B2_AP1_percentage")
	@ApiModelProperty(notes="B2_AP1_percentage")
	private Double b2aP1Percentage;
	
	@Column(name = "Onsite_per")
	@ApiModelProperty(notes="Onsite_per")
	private Double onsitePer;
	
	@Column(name = "Offshore_per")
	@ApiModelProperty(notes="Offshore_per")
	private Double offshorePer;
	
	@Column(name = "Project_Id")
	@ApiModelProperty(notes="Project_Id")
	private Integer projectId;
	
	@Column(name = "Old_Deal_Id")
	@ApiModelProperty(notes="Old_Deal_Id")
	private String oldDealId;
	
	@Column(name = "Version_Id")
	@ApiModelProperty(notes="Version_Id")
	private Integer versionId;
	
	@Column(name = "Version_Desc")
	@ApiModelProperty(notes="Version_Desc")
	private String versionDesc;
	
	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
//
//	public int getCrmDealId() {
//		return crmDealId;
//	}
//
//	public void setCrmDealId(int crmDealId) {
//		this.crmDealId = crmDealId;
//	}

	
	
	public String getDealDescription() {
		return dealDescription;
	}

	public String getCrmDealId() {
		return crmDealId;
	}

	public void setCrmDealId(String crmDealId) {
		this.crmDealId = crmDealId;
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

	public int getDealType() {
		return dealType;
	}

	public void setDealType(int dealType) {
		this.dealType = dealType;
	}

	public String getDealEndDate() {
		return dealEndDate;
	}

	public void setDealEndDate(String dealEndDate) {
		this.dealEndDate = dealEndDate;
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

	public Double getRevenue() {
		return revenue;
	}

	public void setRevenue(Double revenue) {
		this.revenue = revenue;
	}

	public Double getGm() {
		return gm;
	}

	public void setGm(Double gm) {
		this.gm = gm;
	}

	public Double getOmAfterVD() {
		return omAfterVD;
	}

	public void setOmAfterVD(Double omAfterVD) {
		this.omAfterVD = omAfterVD;
	}

	public Double getOnsiteTotal() {
		return onsiteTotal;
	}

	public void setOnsiteTotal(Double onsiteTotal) {
		this.onsiteTotal = onsiteTotal;
	}

	public Double getOffshoreTotal() {
		return offshoreTotal;
	}

	public void setOffshoreTotal(Double offshoreTotal) {
		this.offshoreTotal = offshoreTotal;
	}

	public Double getTotalEfforts() {
		return totalEfforts;
	}

	public void setTotalEfforts(Double totalEfforts) {
		this.totalEfforts = totalEfforts;
	}

	public Double getB2Percentage() {
		return b2Percentage;
	}

	public void setB2Percentage(Double b2Percentage) {
		this.b2Percentage = b2Percentage;
	}

	public Double getB2aP1Percentage() {
		return b2aP1Percentage;
	}

	public void setB2aP1Percentage(Double b2aP1Percentage) {
		this.b2aP1Percentage = b2aP1Percentage;
	}

	public Double getOnsitePer() {
		return onsitePer;
	}

	public void setOnsitePer(Double onsitePer) {
		this.onsitePer = onsitePer;
	}

	public Double getOffshorePer() {
		return offshorePer;
	}

	public void setOffshorePer(Double offshorePer) {
		this.offshorePer = offshorePer;
	}

	public Integer getProjectId() {
		return projectId;
	}

	public void setProjectId(Integer projectId) {
		this.projectId = projectId;
	}



	public String getOldDealId() {
		return oldDealId;
	}

	public void setOldDealId(String oldDealId) {
		this.oldDealId = oldDealId;
	}

	public Integer getVersionId() {
		return versionId;
	}

	public void setVersionId(Integer versionId) {
		this.versionId = versionId;
	}

	public String getVersionDesc() {
		return versionDesc;
	}

	public void setVersionDesc(String versionDesc) {
		this.versionDesc = versionDesc;
	}

}
