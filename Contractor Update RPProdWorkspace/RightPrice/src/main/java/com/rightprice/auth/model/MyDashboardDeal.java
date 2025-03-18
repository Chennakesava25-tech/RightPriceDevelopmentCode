package com.rightprice.auth.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@Entity
@ApiModel(value="Deal",description="Deal Model Attributes")
@Table(name = "synprod.RP_Deal")
public class MyDashboardDeal implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "RP_Deal_Version_Id")
	@ApiModelProperty(notes="crm deal id Auto Incremented")
	private Integer rpDealVersionId;
	
	@Column(name = "CRM_Deal_Id")
	@ApiModelProperty(notes="CRP Deal Id")
	private String crmDealId;
	
	@Column(name = "Customer_Vertical_Map_Id")
	@ApiModelProperty(notes="Customer Vertical Map Id")
	private Integer customerVerticalMapId;
	
	@Column(name = "Deal_Version")
	@ApiModelProperty(notes="Deal Version")
	private String dealVersion;

	@Column(name = "Currency_Id")
	@ApiModelProperty(notes="Currency id")
	private Integer currencyId;
	
	@Column(name = "Is_Agile_Project")
	@ApiModelProperty(notes="Is_Agile_Project")
	private boolean isAgileProject;
	
	@Column(name = "Is_Syntel_Onsite_Facility_Used")
	@ApiModelProperty(notes="Is Syntel Onsite Facility Used")
	private Integer isSyntelOnsieFacilityUsed;
	
	@Column(name = "Is_HC_Included")
	@ApiModelProperty(notes="Is HC Included")
	private boolean isHcIncluded;
	
	@Column(name = "Is_New_Deal")
	@ApiModelProperty(notes="Is New Deal")
	private Integer isNewDeal;
	
	@Column(name = "IsBizOps_Involved")
	@ApiModelProperty(notes="IsBizOps Involved")
	private boolean isBizOpsInvolved;
	
	@Column(name = "Is_Send_To_DeMS")
	@ApiModelProperty(notes="Is Send To DeMS")
	private boolean isSendToDeMS;
	
	@Column(name = "Project_LOB_Id")
	@ApiModelProperty(notes="Project LOB Id")
	private Integer projectLobId;
	
	@Column(name = "Old_Deal_Id")
	@ApiModelProperty(notes="Old Deal Id")
	private String oldDealId;
	
	@Column(name = "Old_Deal_Desc")
	@ApiModelProperty(notes="Old Deal Desc")
	private String oldDealDesc;
	
	@Column(name = "Old_Project_Id")
	@ApiModelProperty(notes="project id")
	private Integer projectId;
	
	@Column(name = "Deal_Type_Id")
	@ApiModelProperty(notes="Deal Type Id")
	private Integer dealTypeId;
	
	@Column(name = "FP_Project_Type_Id")
	@ApiModelProperty(notes="FP Project Type Id")
	private Integer fpProjectTypeId;
	
	@Column(name = "Risk_Category_Id")
	@ApiModelProperty(notes="Risk Category Id")
	private Integer riskCategoryId;
	
	@Column(name = "Deal_Progress_Status_Id")
	@ApiModelProperty(notes="Deal Progress Status Id")
	private Integer dealProgressStatusId;
	
	@Column(name = "VERTICAL_ID")
	@ApiModelProperty(notes="VERTICAL_ID")
	private Integer verticalId;
		
	/*@Column(name = "Deal_Approval_Status_Id")
	@ApiModelProperty(notes="Deal_Approval_Status_Id")
	private int dealApprovaStatusId;
	*/
	
	
	@Column(name = "Working_Days")
	@ApiModelProperty(notes ="Working_Days")
	private Integer workingDays;
	
	@Column(name = "Level_1_Approver_Role_Id")
	@ApiModelProperty(notes ="Level_1_Approver_Role_Id")
	private Integer level1ApproverRoleId;

	@Column(name = "Level_2_Approver_Role_Id")
	@ApiModelProperty(notes ="Level_2_Approver_Role_Id")
	private Integer level2ApproverRoleId;
	
	@Column(name = "Level_3_Approver_Role_Id")
	@ApiModelProperty(notes ="Level_3_Approver_Role_Id")
	private Integer level3ApproverRoleId;
	
	@Column(name = "Level_4_Approver_Role_Id")
	@ApiModelProperty(notes ="Level_4_Approver_Role_Id")
	private Integer level4ApproverRoleId;

	@Column(name = "Level_5_Approver_Role_Id")
	@ApiModelProperty(notes ="Level_5_Approver_Role_Id")
	private Integer level5ApproverRoleId;

	@Column(name = "Level_6_Approver_Role_Id")
	@ApiModelProperty(notes ="Level_6_Approver_Role_Id")
	private Integer level6ApproverRoleId;
	
	@Column(name = "Level_7_Approver_Role_Id")
	@ApiModelProperty(notes ="Level_7_Approver_Role_Id")
	private Integer level7ApproverRoleId;
	
	
	@Column(name = "Current_Approval_Status")
	@ApiModelProperty(notes="Current Approval Status")
	private Integer currentApprovalStatus;
	
	@Column(name = "Current_Approver_Id")
	@ApiModelProperty(notes="Current Approver Id")
	private String currentApproverId;
	
	@Column(name = "Current_Approval_Level")
	@ApiModelProperty(notes="Current_Approval_Level")
	private Integer currentApprovalLevel;
	
	@Column(name = "Current_Approver_Role_Id")
	@ApiModelProperty(notes="Current_Approver_Role_Id")
	private Integer currentApproverRoleId;
		
	@Column(name = "Approver_Comments")
	@ApiModelProperty(notes="Approver_Comments")
	private String approverComments;
	
	@Column(name = "Rejection_Comments")
	@ApiModelProperty(notes="Rejection Comments")
	private String rejectionComments;
	
	@Column(name = "Project_Industry")
	@ApiModelProperty(notes="Project Industry")
	private Integer projectIndustry;
	
	@Column(name = "Deal_Start_Date")
	@ApiModelProperty(notes="Project Start Date")
	private String dealStartDate;
	
	@Column(name = "Deal_End_Date")
	@ApiModelProperty(notes="Project end Date")
	private String dealEndDate;
	
	@Column(name = "Deal_TCV")
	@ApiModelProperty(notes="deal tcv")
	private Integer dealTcv;
	
	@Column(name = "GM_Percentage")
	@ApiModelProperty(notes="GM Percentage")
	private Double gmPercentage;
	
	@Column(name = "Estimated_Revenue")
	@ApiModelProperty(notes="Estimated Revenue")
	private double estimatedRevenue;
	
	@Column(name = "Penalty_Percent")
	@ApiModelProperty(notes="Penalty Percent")
	private double penaltyPercentage;
	
	@Column(name = "Is_Manual_Deal")
	@ApiModelProperty(notes="Is Manual Deal")
	private Integer isManualDeal;
	
	@Column(name = "Onsite_Working_Hours")
	@ApiModelProperty(notes="Onsite Working Hours")
	private double onsiteHours;
	
	@Column(name = "OffShore_Working_Hours")
	@ApiModelProperty(notes="OffShore Working Hours")
	private double offShoreHours;
	
	@Column(name = "Deal_Version_Status")
	@ApiModelProperty(notes="Is version finalized")
	private Integer isVersionFinalized;
	
	@Column(name = "MANUAL_DEAL_OBJECT_ID")
	@ApiModelProperty(notes="MANUAL DEAL OBJECT ID")
	private Integer manualDealObjectId;
	
	@Column(name = "ATTACHMENT_1_OBJECT_ID")
	@ApiModelProperty(notes="ATTACHMENT 1 OBJECT ID")
	private Integer attachment1ObjectId;
	
	@Column(name = "ATTACHMENT_2_OBJECT_ID")
	@ApiModelProperty(notes="ATTACHMENT 2 OBJECT ID")
	private Integer attachment2ObjectId;
	
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
	
	@Column(name = "BASE_COUNTRY_NAME")
	@ApiModelProperty(notes="BASE_COUNTRY_NAME")
	private String baseCountryName;
	
	@Column(name="BASE_CITY_NAME")
	@ApiModelProperty(notes="BASE_CITY_NAME")
	private String baseCityName;
	
	@Column(name="STATUS_INDICATOR")
	@ApiModelProperty(notes="STATUS_INDICATOR")
	private String statusIndicator;
	
	@Column(name="CUSTOMER_ID")
	@ApiModelProperty(notes="CUSTOMER_ID")
	private Integer customerId;
	
	//RC_Comments
	@Column(name="RC_COMMENTS")
	@ApiModelProperty(notes="RC_COMMENTS")
	private String rcComments;
	
	@Transient
	private String userType;
	
	@Transient
	private Integer dealStatusIdWL;
	
	@Column(name = "Atos_Email_ID")
	@ApiModelProperty(notes = "Atos_Email_ID")
	private String atosEmailId;
	
	@Column(name = "Employee_name")
	@ApiModelProperty(notes = "Employee_name")
	private String employeeName;
	
	@Column(name = "update_Atos_Email_ID")
	@ApiModelProperty(notes = "update_Atos_Email_ID")
	private String updateAtosEmailId;
	
	@Column(name = "update_Employee_name")
	@ApiModelProperty(notes = "update_Employee_name")
	private String updateEmployeeName;
	
	@OneToOne(cascade = javax.persistence.CascadeType.ALL)
	@JoinColumn(name ="CUSTOMER_ID",referencedColumnName = "CUSTOMER_ID",insertable=false, updatable=false)
	private Customer customer;
	
	@OneToOne(cascade = javax.persistence.CascadeType.ALL, fetch=FetchType.EAGER)
    @JoinColumn(name = "CRM_Deal_Id",referencedColumnName = "CRM_Deal_Id",insertable=false, updatable=false)
	private DealCrmStages2 dealcrmstagesdata2;
	
	@OneToOne(cascade = javax.persistence.CascadeType.ALL)
    @JoinColumn(name = "Currency_Id",referencedColumnName = "CODE_NAME",insertable=false, updatable=false)
	private AppCode appCode;
	
	@Column(name = "Cyber_Security")
	@ApiModelProperty(notes="Cyber Security")
	private String cyberSecurity;
	
	@Column(name = "Industry_Solution")
	@ApiModelProperty(notes="Industry Solution")
	private String industrySolution;
	
	@Column(name = "Is_Synbots")
	@ApiModelProperty(notes="Is Synbots")
	private String isSynbots;
	
	@Column(name = "Synergy_Type")
	@ApiModelProperty(notes="Synergy Type")
	private String synergyType;
	
	public Integer getRpDealVersionId() {
		return rpDealVersionId;
	}

	public void setRpDealVersionId(Integer rpDealVersionId) {
		this.rpDealVersionId = rpDealVersionId;
	}

	public String getCrmDealId() {
		return crmDealId;
	}

	public void setCrmDealId(String crmDealId) {
		this.crmDealId = crmDealId;
	}

	public Integer getCustomerVerticalMapId() {
		return customerVerticalMapId;
	}

	public void setCustomerVerticalMapId(Integer customerVerticalMapId) {
		this.customerVerticalMapId = customerVerticalMapId;
	}

	public String getDealVersion() {
		return dealVersion;
	}

	public void setDealVersion(String dealVersion) {
		this.dealVersion = dealVersion;
	}

	public Integer getCurrencyId() {
		return currencyId;
	}

	public void setCurrencyId(Integer currencyId) {
		this.currencyId = currencyId;
	}

	public boolean isAgileProject() {
		return isAgileProject;
	}

	public void setAgileProject(boolean isAgileProject) {
		this.isAgileProject = isAgileProject;
	}

	public Integer getIsSyntelOnsieFacilityUsed() {
		return isSyntelOnsieFacilityUsed;
	}

	public void setIsSyntelOnsieFacilityUsed(Integer isSyntelOnsieFacilityUsed) {
		this.isSyntelOnsieFacilityUsed = isSyntelOnsieFacilityUsed;
	}

	public boolean isHcIncluded() {
		return isHcIncluded;
	}

	public void setHcIncluded(boolean isHcIncluded) {
		this.isHcIncluded = isHcIncluded;
	}

	public Integer getIsNewDeal() {
		return isNewDeal;
	}

	public void setIsNewDeal(Integer isNewDeal) {
		this.isNewDeal = isNewDeal;
	}

	public boolean isBizOpsInvolved() {
		return isBizOpsInvolved;
	}

	public void setBizOpsInvolved(boolean isBizOpsInvolved) {
		this.isBizOpsInvolved = isBizOpsInvolved;
	}

	public boolean isSendToDeMS() {
		return isSendToDeMS;
	}

	public void setSendToDeMS(boolean isSendToDeMS) {
		this.isSendToDeMS = isSendToDeMS;
	}

	public Integer getProjectLobId() {
		return projectLobId;
	}

	public void setProjectLobId(Integer projectLobId) {
		this.projectLobId = projectLobId;
	}

	public String getOldDealId() {
		return oldDealId;
	}

	public void setOldDealId(String oldDealId) {
		this.oldDealId = oldDealId;
	}

	public String getOldDealDesc() {
		return oldDealDesc;
	}

	public void setOldDealDesc(String oldDealDesc) {
		this.oldDealDesc = oldDealDesc;
	}

	public Integer getProjectId() {
		return projectId;
	}

	public void setProjectId(Integer projectId) {
		this.projectId = projectId;
	}

	public Integer getDealTypeId() {
		return dealTypeId;
	}

	public void setDealTypeId(Integer dealTypeId) {
		this.dealTypeId = dealTypeId;
	}

	public Integer getFpProjectTypeId() {
		return fpProjectTypeId;
	}

	public void setFpProjectTypeId(Integer fpProjectTypeId) {
		this.fpProjectTypeId = fpProjectTypeId;
	}

	public Integer getRiskCategoryId() {
		return riskCategoryId;
	}

	public void setRiskCategoryId(Integer riskCategoryId) {
		this.riskCategoryId = riskCategoryId;
	}

	public Integer getDealProgressStatusId() {
		return dealProgressStatusId;
	}

	public void setDealProgressStatusId(Integer dealProgressStatusId) {
		this.dealProgressStatusId = dealProgressStatusId;
	}

	public Integer getVerticalId() {
		return verticalId;
	}

	public void setVerticalId(Integer verticalId) {
		this.verticalId = verticalId;
	}

	public Integer getWorkingDays() {
		return workingDays;
	}

	public void setWorkingDays(Integer workingDays) {
		this.workingDays = workingDays;
	}

	public Integer getLevel1ApproverRoleId() {
		return level1ApproverRoleId;
	}

	public void setLevel1ApproverRoleId(Integer level1ApproverRoleId) {
		this.level1ApproverRoleId = level1ApproverRoleId;
	}

	public Integer getLevel2ApproverRoleId() {
		return level2ApproverRoleId;
	}

	public void setLevel2ApproverRoleId(Integer level2ApproverRoleId) {
		this.level2ApproverRoleId = level2ApproverRoleId;
	}

	public Integer getLevel3ApproverRoleId() {
		return level3ApproverRoleId;
	}

	public void setLevel3ApproverRoleId(Integer level3ApproverRoleId) {
		this.level3ApproverRoleId = level3ApproverRoleId;
	}

	public Integer getLevel4ApproverRoleId() {
		return level4ApproverRoleId;
	}

	public void setLevel4ApproverRoleId(Integer level4ApproverRoleId) {
		this.level4ApproverRoleId = level4ApproverRoleId;
	}

	public Integer getLevel5ApproverRoleId() {
		return level5ApproverRoleId;
	}

	public void setLevel5ApproverRoleId(Integer level5ApproverRoleId) {
		this.level5ApproverRoleId = level5ApproverRoleId;
	}

	public Integer getLevel6ApproverRoleId() {
		return level6ApproverRoleId;
	}

	public void setLevel6ApproverRoleId(Integer level6ApproverRoleId) {
		this.level6ApproverRoleId = level6ApproverRoleId;
	}

	public Integer getLevel7ApproverRoleId() {
		return level7ApproverRoleId;
	}

	public void setLevel7ApproverRoleId(Integer level7ApproverRoleId) {
		this.level7ApproverRoleId = level7ApproverRoleId;
	}

	public Integer getCurrentApprovalStatus() {
		return currentApprovalStatus;
	}

	public void setCurrentApprovalStatus(Integer currentApprovalStatus) {
		this.currentApprovalStatus = currentApprovalStatus;
	}

	public String getCurrentApproverId() {
		return currentApproverId;
	}

	public void setCurrentApproverId(String currentApproverId) {
		this.currentApproverId = currentApproverId;
	}

	public Integer getCurrentApprovalLevel() {
		return currentApprovalLevel;
	}

	public void setCurrentApprovalLevel(Integer currentApprovalLevel) {
		this.currentApprovalLevel = currentApprovalLevel;
	}

	public Integer getCurrentApproverRoleId() {
		return currentApproverRoleId;
	}

	public void setCurrentApproverRoleId(Integer currentApproverRoleId) {
		this.currentApproverRoleId = currentApproverRoleId;
	}

	public String getApproverComments() {
		return approverComments;
	}

	public void setApproverComments(String approverComments) {
		this.approverComments = approverComments;
	}

	public String getRejectionComments() {
		return rejectionComments;
	}

	public void setRejectionComments(String rejectionComments) {
		this.rejectionComments = rejectionComments;
	}

	public Integer getProjectIndustry() {
		return projectIndustry;
	}

	public void setProjectIndustry(Integer projectIndustry) {
		this.projectIndustry = projectIndustry;
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

	public Integer getDealTcv() {
		return dealTcv;
	}

	public void setDealTcv(Integer dealTcv) {
		this.dealTcv = dealTcv;
	}

	public Double getGmPercentage() {
		return gmPercentage;
	}

	public void setGmPercentage(Double gmPercentage) {
		this.gmPercentage = gmPercentage;
	}

	public double getEstimatedRevenue() {
		return estimatedRevenue;
	}

	public void setEstimatedRevenue(double estimatedRevenue) {
		this.estimatedRevenue = estimatedRevenue;
	}

	public double getPenaltyPercentage() {
		return penaltyPercentage;
	}

	public void setPenaltyPercentage(double penaltyPercentage) {
		this.penaltyPercentage = penaltyPercentage;
	}

	public Integer getIsManualDeal() {
		return isManualDeal;
	}

	public void setIsManualDeal(Integer isManualDeal) {
		this.isManualDeal = isManualDeal;
	}

	public double getOnsiteHours() {
		return onsiteHours;
	}

	public void setOnsiteHours(double onsiteHours) {
		this.onsiteHours = onsiteHours;
	}

	public double getOffShoreHours() {
		return offShoreHours;
	}

	public void setOffShoreHours(double offShoreHours) {
		this.offShoreHours = offShoreHours;
	}

	public Integer getIsVersionFinalized() {
		return isVersionFinalized;
	}

	public void setIsVersionFinalized(Integer isVersionFinalized) {
		this.isVersionFinalized = isVersionFinalized;
	}

	public Integer getManualDealObjectId() {
		return manualDealObjectId;
	}

	public void setManualDealObjectId(Integer manualDealObjectId) {
		this.manualDealObjectId = manualDealObjectId;
	}

	public Integer getAttachment1ObjectId() {
		return attachment1ObjectId;
	}

	public void setAttachment1ObjectId(Integer attachment1ObjectId) {
		this.attachment1ObjectId = attachment1ObjectId;
	}

	public Integer getAttachment2ObjectId() {
		return attachment2ObjectId;
	}

	public void setAttachment2ObjectId(Integer attachment2ObjectId) {
		this.attachment2ObjectId = attachment2ObjectId;
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

	public String getBaseCountryName() {
		return baseCountryName;
	}

	public void setBaseCountryName(String baseCountryName) {
		this.baseCountryName = baseCountryName;
	}

	public String getBaseCityName() {
		return baseCityName;
	}

	public void setBaseCityName(String baseCityName) {
		this.baseCityName = baseCityName;
	}

	public String getStatusIndicator() {
		return statusIndicator;
	}

	public void setStatusIndicator(String statusIndicator) {
		this.statusIndicator = statusIndicator;
	}

	public Integer getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}

	public String getRcComments() {
		return rcComments;
	}

	public void setRcComments(String rcComments) {
		this.rcComments = rcComments;
	}
	
	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public DealCrmStages2 getDealcrmstagesdata2() {
		return dealcrmstagesdata2;
	}

	public void setDealcrmstagesdata2(DealCrmStages2 dealcrmstagesdata2) {
		this.dealcrmstagesdata2 = dealcrmstagesdata2;
	}

	public AppCode getAppCode() {
		return appCode;
	}

	public void setAppCode(AppCode appCode) {
		this.appCode = appCode;
	}

	public Integer getDealStatusIdWL() {
		return dealStatusIdWL;
	}

	public void setDealStatusIdWL(Integer dealStatusIdWL) {
		this.dealStatusIdWL = dealStatusIdWL;
	}

	public String getAtosEmailId() {
		return atosEmailId;
	}

	public void setAtosEmailId(String atosEmailId) {
		this.atosEmailId = atosEmailId;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public String getUpdateAtosEmailId() {
		return updateAtosEmailId;
	}

	public void setUpdateAtosEmailId(String updateAtosEmailId) {
		this.updateAtosEmailId = updateAtosEmailId;
	}

	public String getUpdateEmployeeName() {
		return updateEmployeeName;
	}

	public void setUpdateEmployeeName(String updateEmployeeName) {
		this.updateEmployeeName = updateEmployeeName;
	}

	public String getCyberSecurity() {
		return cyberSecurity;
	}

	public void setCyberSecurity(String cyberSecurity) {
		this.cyberSecurity = cyberSecurity;
	}

	public String getIndustrySolution() {
		return industrySolution;
	}

	public void setIndustrySolution(String industrySolution) {
		this.industrySolution = industrySolution;
	}

	public String getIsSynbots() {
		return isSynbots;
	}

	public void setIsSynbots(String isSynbots) {
		this.isSynbots = isSynbots;
	}

	public String getSynergyType() {
		return synergyType;
	}

	public void setSynergyType(String synergyType) {
		this.synergyType = synergyType;
	}
	
	@Column(name = "Deal_Discount_Flag")
	private Integer dealdiscFlag;


	public Integer getDealdiscFlag() {
		return dealdiscFlag;
	}

	public void setDealdiscFlag(Integer dealdiscFlag) {
		this.dealdiscFlag = dealdiscFlag;
	}

}
