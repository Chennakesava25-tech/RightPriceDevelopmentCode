package com.rightprice.auth.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@Entity
@ApiModel(value="Stages",description="Stages Model Attributes")
@Table(name = "synprod.RP_Deal_CRM_Stage")
public class DealCrmStages implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "CRM_Deal_Id")
	@ApiModelProperty(notes="crm deal id Auto Incremented")
	private String crmDealId;
	
	@Column(name = "Client_RC_Id")
	@ApiModelProperty(notes="client RC id")
	private Integer clientRcId;
	
	@Column(name = "Deal_Description")
	@ApiModelProperty(notes="Deal description")
	private String dealDescription;
	
	@Column(name = "currencyname")
	@ApiModelProperty(notes="currencyname")
	private String currencyname;
	
	public String getCurrencyname() {
		return currencyname;
	}

	public void setCurrencyname(String currencyname) {
		this.currencyname = currencyname;
	}

	@Column(name = "Sales_Spoc")
	@ApiModelProperty(notes="Sales spoc")
	private String salesSpoc;
	
	@Column(name = "Deal_status")
	@ApiModelProperty(notes="deal status")
	private Integer dealStatusId;
	
	@OneToOne(cascade = javax.persistence.CascadeType.ALL, fetch=FetchType.EAGER)
    @JoinColumn(name = "DEAL_STATUS",referencedColumnName = "CODE_NAME",insertable=false, updatable=false)
	private CodeApplication dealStatus;

	@Column(name = "Deal_Priority_Id")
	@ApiModelProperty(notes="Deal priority id")
	private Integer dealPriorityId;
	
	@OneToOne(cascade = javax.persistence.CascadeType.ALL, fetch=FetchType.EAGER)
    @JoinColumn(name = "DEAL_PRIORITY_ID",referencedColumnName = "CODE_NAME",insertable=false, updatable=false)
	private CodeApplication dealPriority;
	
	@Column(name = "Revenue")
	@ApiModelProperty(notes="Revenue")
	private Double revenue;
	
	@Column(name = "Currency_Id")
	@ApiModelProperty(notes="Currency id")
	private Integer currencyId;
	
	@OneToOne(cascade = javax.persistence.CascadeType.ALL, fetch=FetchType.EAGER)
    @JoinColumn(name = "CURRENCY_ID",referencedColumnName = "CURRENCY_ID",insertable=false, updatable=false)
	private Country currency;
	
	@Column(name = "Deal_Start_Date")
	@ApiModelProperty(notes="Deal start date")
	private String dealStartDate;
	
	@Column(name = "Deal_End_Date")
	@ApiModelProperty(notes="deal end date")
	private String dealEndDate;
	
	@Column(name = "Percentage_Close")
	@ApiModelProperty(notes="Percentage close")
	private Integer percentageClose;
	
	@Column(name = "Deal_Type_Id")
	@ApiModelProperty(notes="Deal type id")
	private Integer dealTypeId;
	
	@Column(name = "Stage_Id")
	@ApiModelProperty(notes="stage id")
	private Integer stageId;
	
	@Column(name = "Industry")
	@ApiModelProperty(notes="Industry")
	private Integer industry;
	
	@Column(name = "FP_Type")
	@ApiModelProperty(notes="FP type")
	private Integer fpType;
	
	@Column(name = "Customer_Id")
	@ApiModelProperty(notes="Customer Id")
	private Integer customerId;
	
	@Column(name = "Std_Hrs")
	@ApiModelProperty(notes="Standard hours")
	private Integer stdHrs;
	
	@Column(name = "Negotiated_Hrs")
	@ApiModelProperty(notes="Negotiated hours")
	private Integer negotiatedHrs;
	
	@Column(name = "Deal_Version")
	@ApiModelProperty(notes="Deal version")
	private Integer dealVersion;
	
	@Column(name = "Client_Rate_Card_Version")
	@ApiModelProperty(notes="client rate card version")
	private Integer clientRateCardVersion;
	
	@Column(name = "User_Category_Id")
	@ApiModelProperty(notes="User category id")
	private Integer userCategoryId;
	
	@Column(name = "Approval_Status")
	@ApiModelProperty(notes="Approval Status")
	private Integer approvalStatus;
	
	@Column(name = "Is_Manual")
	@ApiModelProperty(notes="Is manual")
	private Integer isManual;
	
	@Column(name = "RateCard_Id")
	@ApiModelProperty(notes="Rate card id")
	private Integer rateCardId;
	
	@Column(name = "Project_Id")
	@ApiModelProperty(notes="Project id")
	private Integer projectId;
	
	@Column(name = "Old_Deal_Id")
	@ApiModelProperty(notes="Old deal id")
	private Integer oldDealId;
	
	@Column(name = "Vertical_Name")
	@ApiModelProperty(notes="Vertical name")
	private String verticalName;
	
	@Column(name = "Estimation")
	@ApiModelProperty(notes="estimation")
	private Double estimation;
	
	@Column(name = "hc_Inclusion")
	@ApiModelProperty(notes="HC inclusion")
	private Integer hcInclusion;
	
	@Column(name = "IS_ACTIVE")
	@ApiModelProperty(notes="Active status")
	private Integer isActive;
	
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

	@OneToOne(cascade = javax.persistence.CascadeType.ALL, fetch=FetchType.LAZY)
	@JoinColumn(name = "CUSTOMER_ID" ,insertable=false, updatable=false)
	@JsonIgnore
	private Customer customer;
	
	
	@OneToOne(cascade = javax.persistence.CascadeType.ALL, fetch=FetchType.LAZY)
	@JoinColumn(name ="STAGE_ID",referencedColumnName = "CODE_NAME",insertable=false, updatable=false)
	private CodeApplication stage;
	
	/*@OneToOne(cascade = javax.persistence.CascadeType.ALL)
	@JoinColumn(name ="deal_status",referencedColumnName = "CODE_NAME")
	private CodeApplication dealStatusId;*/
	


	public Integer getClientRcId() {
		return clientRcId;
	}

	public String getCrmDealId() {
		return crmDealId;
	}

	public void setCrmDealId(String crmDealId) {
		this.crmDealId = crmDealId;
	}

	public void setClientRcId(Integer clientRcId) {
		this.clientRcId = clientRcId;
	}

	public String getDealDescription() {
		return dealDescription;
	}

	public void setDealDescription(String dealDescription) {
		this.dealDescription = dealDescription;
	}

	public String getSalesSpoc() {
		return salesSpoc;
	}

	public void setSalesSpoc(String salesSpoc) {
		this.salesSpoc = salesSpoc;
	}

	/*public Integer getDealStatus() {
		return dealStatus;
	}

	public void setDealStatus(Integer dealStatus) {
		this.dealStatus = dealStatus;
	}
*/
	/*public Integer getDealPriorityId() {
		return dealPriorityId;
	}

	public void setDealPriorityId(Integer dealPriorityId) {
		this.dealPriorityId = dealPriorityId;
	}
*/
	public Double getRevenue() {
		return revenue;
	}

	public void setRevenue(Double revenue) {
		this.revenue = revenue;
	}

	public Integer getCurrencyId() {
		return currencyId;
	}

	public void setCurrencyId(Integer currencyId) {
		this.currencyId = currencyId;
	}

	public String getDealStartDate() {
		return dealStartDate;
	}

	public void setDealStartDate(String dealStartDate) {
		this.dealStartDate = dealStartDate;
	}

	public String getDealEndDate() {
		return dealEndDate;
	}

	public void setDealEndDate(String dealEndDate) {
		this.dealEndDate = dealEndDate;
	}

	public Integer getPercentageClose() {
		return percentageClose;
	}

	public void setPercentageClose(Integer percentageClose) {
		this.percentageClose = percentageClose;
	}

	public Integer getDealTypeId() {
		return dealTypeId;
	}

	public void setDealTypeId(Integer dealTypeId) {
		this.dealTypeId = dealTypeId;
	}

	public Integer getStageId() {
		return stageId;
	}

	public void setStageId(Integer stageId) {
		this.stageId = stageId;
	}

	public Integer getIndustry() {
		return industry;
	}

	public void setIndustry(Integer industry) {
		this.industry = industry;
	}

	public Integer getFpType() {
		return fpType;
	}

	public void setFpType(Integer fpType) {
		this.fpType = fpType;
	}

	public Integer getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}

	public Integer getStdHrs() {
		return stdHrs;
	}

	public void setStdHrs(Integer stdHrs) {
		this.stdHrs = stdHrs;
	}

	public Integer getNegotiatedHrs() {
		return negotiatedHrs;
	}

	public void setNegotiatedHrs(Integer negotiatedHrs) {
		this.negotiatedHrs = negotiatedHrs;
	}

	public Integer getDealVersion() {
		return dealVersion;
	}

	public void setDealVersion(Integer dealVersion) {
		this.dealVersion = dealVersion;
	}

	public Integer getClientRateCardVersion() {
		return clientRateCardVersion;
	}

	public void setClientRateCardVersion(Integer clientRateCardVersion) {
		this.clientRateCardVersion = clientRateCardVersion;
	}

	public Integer getUserCategoryId() {
		return userCategoryId;
	}

	public void setUserCategoryId(Integer userCategoryId) {
		this.userCategoryId = userCategoryId;
	}

	public Integer getApprovalStatus() {
		return approvalStatus;
	}

	public void setApprovalStatus(Integer approvalStatus) {
		this.approvalStatus = approvalStatus;
	}

	public Integer getIsManual() {
		return isManual;
	}

	public void setIsManual(Integer isManual) {
		this.isManual = isManual;
	}

	public Integer getRateCardId() {
		return rateCardId;
	}

	public void setRateCardId(Integer rateCardId) {
		this.rateCardId = rateCardId;
	}

	public Integer getProjectId() {
		return projectId;
	}

	public void setProjectId(Integer projectId) {
		this.projectId = projectId;
	}

	public Integer getOldDealId() {
		return oldDealId;
	}

	public void setOldDealId(Integer oldDealId) {
		this.oldDealId = oldDealId;
	}

	public String getVerticalName() {
		return verticalName;
	}

	public void setVerticalName(String verticalName) {
		this.verticalName = verticalName;
	}

	public Double getEstimation() {
		return estimation;
	}

	public void setEstimation(Double estimation) {
		this.estimation = estimation;
	}

	public Integer getHcInclusion() {
		return hcInclusion;
	}

	public void setHcInclusion(Integer hcInclusion) {
		this.hcInclusion = hcInclusion;
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

	public Integer getDealStatusId() {
		return dealStatusId;
	}

	public void setDealStatusId(Integer dealStatusId) {
		this.dealStatusId = dealStatusId;
	}

	public CodeApplication getDealStatus() {
		return dealStatus;
	}

	public void setDealStatus(CodeApplication dealStatus) {
		this.dealStatus = dealStatus;
	}

	public Integer getDealPriorityId() {
		return dealPriorityId;
	}

	public void setDealPriorityId(Integer dealPriorityId) {
		this.dealPriorityId = dealPriorityId;
	}

	public CodeApplication getDealPriority() {
		return dealPriority;
	}

	public void setDealPriority(CodeApplication dealPriority) {
		this.dealPriority = dealPriority;
	}

	/*public Country getCurrency() {
		return currency;
	}

	public void setCurrency(Country currency) {
		this.currency = currency;
	}*/

	public CodeApplication getStage() {
		return stage;
	}

	public void setStage(CodeApplication stage) {
		this.stage = stage;
	}

	public Country getCurrency() {
		return currency;
	}

	public void setCurrency(Country currency) {
		this.currency = currency;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	
	/*public Integer getDealPriority() {
		return dealPriority;
	}

	public void setDealPriority(Integer dealPriority) {
		this.dealPriority = dealPriority;
	}*/
	@Column(name = "RBU_Profit_Center")
	@ApiModelProperty(notes="rbu profit center")
	private String rbuProfitCenter;

	public String getRbuProfitCenter() {
		return rbuProfitCenter;
	}

	public void setRbuProfitCenter(String rbuProfitCenter) {
		this.rbuProfitCenter = rbuProfitCenter;
	}

	
}
