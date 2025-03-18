package com.rightprice.auth.model;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import com.rightprice.auth.util.AppLoger;
import com.rightprice.auth.util.DateUtil;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@Entity
@ApiModel(value="Rate card details",description="Rate card details Model Attributes")
@Table(name = "synprod.RP_RATE_CARD_DETAILS")
public class MyDashboardRC implements Serializable{
	
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "RC_ID")
	private Integer rcId;
	
	@Column(name = "CUSTOMER_VERTICAL_MAP_ID")
	private Integer customerVerticalMapId;
	
	@Column(name = "RC_NAME")
	private String rcName;
	
	@Column(name = "RC_START_DATE")
	private String rcStartDate;
	
	@Column(name = "RC_END_DATE")
	private String rcEndDate;

	@Column(name = "EXPECTED_RC_END_DATE")
	private String expectedRCEndDate;
	
	@Column(name = "TCV")
	private Double tvc;
	
	@Column(name = "VOLUME_DISCOUNT")
	private Double volumeDiscount;

	@Column(name = "IS_IT_KPO")
	private int isItKpo;

	@Column(name = "APPLICABLE_YEARS")
	private Integer applicableYears;
	
	@Column(name = "APPLICABLE_MONTHS")
	private Integer applicableMonths;
	
	@Column(name = "Vertical_Id")
	private Integer verticalId;
	
	public Integer getVerticalId() {
		return verticalId;
	}

	public void setVerticalId(Integer verticalId) {
		this.verticalId = verticalId;
	}

	@Column(name = "EXPECTED_ONSITE_RESOURCE_PERCENTAGE")
	private Integer expectedOnsiteResourcePercentage;
	
	@Column(name = "EXPECTED_OFFSHORE_RESOURCE_PERCENTAGE")
	private double expectedOffshoreResourcePercentage;

	@Column(name="Is_Manual_RC")
	private String isManualRc;
	
	@Column(name = "BASE_COUNTRY_NAME")
	@ApiModelProperty(notes="BASE_COUNTRY_NAME")
	private String baseCountryName;
	
	@Column(name = "BASE_CITY_NAME")
	@ApiModelProperty(notes="BASE_CITY_NAME")
	private String baseCityName;
	
	@Column(name="IQN_Charges")
	private double iqnCharges;
	
	@Column(name = "RC_PROGRESS_STATUS_ID")
	private Integer rcProgressStatusId;

	@Column(name = "CONSOLIDATED_RC_CURRENCY_ID")
	private Integer consolidatedRcCurrencyId;
	
	@Column(name = "DELIVERY_COMMENTS")
	private String deliveryComments;
	
	@Column(name = "BLENDED_RATE")
	private Integer blendedRate;
	
	@Column(name = "Blended_Cost")
	private Double blendedCost;
	
	@Column(name = "REVENUE")
	private Double revenue;
	
	@Column(name = "REVENUE_POST_DISCOUNT")
	private Double revenuePostPercentage;
	
	@Column(name = "CALCULATED_RP_GM_PERCENTAGE")
	private Double calculatedRPGMPercentage;
	
	@Column(name = "CALCULATED_RP_GM_PERCENTAGE_POST_DISCOUNT")
	private Double calculatedRPGMPercentagePostDiscount;
	
	@Column(name ="CALCULATED_GM_PERCENTAGE")
	private Double calculatedGMPercentage;
	
	@Column(name ="CALCULATED_GM_PERCENTAGE_POST_DISCOUNT")
	private Double calculatedGMPercentagePostDiscount;
	
	@Column(name = "DELIVERY_SPOC_NAME")
	private String deliverySpocName;
	
	@Column(name = "SALES_SPOC_NAME")
	private String salesSpocName;
	
	@Column(name = "Level_1_Approver_Role_Id")
	private Integer level1ApproverRoleId;
	
	@Column(name = "Level_2_Approver_Role_Id")
	private Integer level2ApproverRoleId;
	
	@Column(name = "Level_3_Approver_Role_Id")
	private Integer level3ApproverRoleId;
	
	@Column(name = "Level_4_Approver_Role_Id")
	private Integer level4ApproverRoleId;
	
	@Column(name = "Level_5_Approver_Role_Id")
	private Integer level5ApproverRoleId;
	
	@Column(name = "Level_6_Approver_Role_Id")
	private Integer level6ApproverRoleId;
	
	@Column(name = "Level_7_Approver_Role_Id")
	private Integer level7ApproverRoleId;
	
	@Column(name = "CURRENT_APPROVAL_STATUS")
	private Integer currentApprovalStatus;
	
	@Column(name = "CURRENT_APPROVAL_LEVEL")
	private Integer currentApprovalLevel;
	
	@Column(name ="CURRENT_APPROVER_ID")
	private String currentApproverId;
	
	@Column(name = "Current_Approver_Role_Id")
	private Integer currentApproverRoleId;
	
	@Column(name ="APPROVER_COMMENTS")
	private String approverComments;
	
	@Column(name="ONSITE_HOURS_PER_DAY")
	private Double onsiteHoursPerDay;
	
	@Column(name="OFFSHORE_HOURS_PER_DAY")
	private Double offshoreHoursPerDay;
	
	@Column(name="CPC_CHARGES")
	@ApiModelProperty(notes="CPC_CHARGES")
	private Double cpcCharges;
	
	@Column(name ="IS_ACTIVE")
	private Integer isActive=1;
	
	@Column(name = "CREATED_BY", updatable=false)
	@ApiModelProperty(notes="Created by")
	private String createdBy;
	
	@Column(name = "CREATED_ON", updatable=false)
	@ApiModelProperty(notes="Created date")
	private String createdOn;
	
	@Column(name = "UPDATED_BY")
	@ApiModelProperty(notes="Updated by")
	private String updatedBy;
	
	@Column(name = "UPDATED_ON")
	@ApiModelProperty(notes="Updated date")
	private String updatedOn;

	@Column(name = "RENEWAL_RC_ID")
	@ApiModelProperty(notes="Renewal_RC_ID")
	private Integer renewalRCId;

	@Column(name = "IS_RENEWAL")
	@ApiModelProperty(notes="IS_RENEWAL")
	private Integer isRenewal;
	
	@Column(name = "CUSTOMER_ID")
	@ApiModelProperty(notes="CUSTOMER ID")
	private Integer customerId;
	
	@Column(name = "STATUS_INDICATOR")
	@ApiModelProperty(notes="STATUS_INDICATOR")
	private String statusIndicator;
	
	@Column(name = "SPOC_NAME")
	@ApiModelProperty(notes="SPOC_NAME")
	private String spocName;
	
	@Column(name = "Rainbow_Approval_Flag")
	@ApiModelProperty(notes = "Rainbow_Approval_Flag")
	private Integer rainbowApprovalFlag;
	
	@OneToOne(cascade = javax.persistence.CascadeType.ALL)
	@JoinColumn(name ="CUSTOMER_ID",referencedColumnName = "CUSTOMER_ID",insertable=false, updatable=false)
	private Customer customer;
	
	
	@Column(name = "RC_Discount_Flag")
	private Integer discFlag;
	
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
	
	@Transient
	private String userType;
	
	@Transient
	private String rcStartDateExcel;
	
	@Transient
	private String rcEndDateExcel;
	
	@Transient
	private String expectedRCEndDateExcel;
	
	@Transient
	private String createdOnExcel;
	
	@Transient
	private String updatedOnExcel;
	
	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public String getRcStartDateExcel() {
		return rcStartDateExcel;
	}

	public void setRcStartDateExcel(String rcStartDateExcel) {
		this.rcStartDateExcel = rcStartDateExcel;
	}

	public String getRcEndDateExcel() {
		return rcEndDateExcel;
	}

	public void setRcEndDateExcel(String rcEndDateExcel) {
		this.rcEndDateExcel = rcEndDateExcel;
	}

	public String getExpectedRCEndDateExcel() {
		return expectedRCEndDateExcel;
	}

	public void setExpectedRCEndDateExcel(String expectedRCEndDateExcel) {
		this.expectedRCEndDateExcel = expectedRCEndDateExcel;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Integer getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}

	public Integer getRcId() {
		return rcId;
	}

	public void setRcId(Integer rcId) {
		this.rcId = rcId;
	}

	public Integer getBlendedRate() {
		return blendedRate;
	}

	public void setBlendedRate(Integer blendedRate) {
		this.blendedRate = blendedRate;
	}

	public Double getBlendedCost() {
		return blendedCost;
	}

	public void setBlendedCost(Double blendedCost) {
		this.blendedCost = blendedCost;
	}

	public Integer getCustomerVerticalMapId() {
		return customerVerticalMapId;
	}

	public void setCustomerVerticalMapId(Integer customerVerticalMapId) {
		this.customerVerticalMapId = customerVerticalMapId;
	}

	public String getRcName() {
		return rcName;
	}

	public void setRcName(String rcName) {
		this.rcName = rcName;
	}

	public String getRcStartDate() {
		return rcStartDate;
	}

	
	public void setRcStartDate(String rcStartDate) {
		
		this.rcStartDate =  DateUtil.getMSSqlFormattedDate(rcStartDate);
	}

	public String getRcEndDate() {
		return rcEndDate;
	}

	public void setRcEndDate(String rcEndDate) {
		this.rcEndDate =  DateUtil.getMSSqlFormattedDate(rcEndDate);
	}

	public String getExpectedRCEndDate() {
		return expectedRCEndDate;
	}

	public void setExpectedRCEndDate(String expectedRCEndDate) {
		this.expectedRCEndDate = DateUtil.getMSSqlFormattedDate(expectedRCEndDate);
	}

	public Double getTvc() {
		return tvc;
	}

	public void setTvc(Double tvc) {
		this.tvc = tvc;
	}

	public int getIsItKpo() {
		return isItKpo;
	}

	public void setIsItKpo(int isItKpo) {
		this.isItKpo = isItKpo;
	}

	

	public String getIsManualRc() {
		return isManualRc;
	}

	public void setIsManualRc(String isManualRc) {
		this.isManualRc = isManualRc;
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

	public double getIqnCharges() {
		return iqnCharges;
	}

	public void setIqnCharges(double iqnCharges) {
		this.iqnCharges = iqnCharges;
	}

	public Integer getApplicableYears() {
		return applicableYears;
	}

	public void setApplicableYears(Integer applicableYears) {
		this.applicableYears = applicableYears;
	}

	public Integer getExpectedOnsiteResourcePercentage() {
		return expectedOnsiteResourcePercentage;
	}

	public void setExpectedOnsiteResourcePercentage(Integer expectedOnsiteResourcePercentage) {
		this.expectedOnsiteResourcePercentage = expectedOnsiteResourcePercentage;
	}

	public String getDeliverySpocName() {
		return deliverySpocName;
	}

	public void setDeliverySpocName(String deliverySpocName) {
		this.deliverySpocName = deliverySpocName;
	}

	public String getSalesSpocName() {
		return salesSpocName;
	}

	public void setSalesSpocName(String salesSpocName) {
		this.salesSpocName = salesSpocName;
	}

	public Integer getRcProgressStatusId() {
		return rcProgressStatusId;
	}

	public void setRcProgressStatusId(Integer rcProgressStatusId) {
		this.rcProgressStatusId = rcProgressStatusId;
	}

	public Integer getConsolidatedRcCurrencyId() {
		return consolidatedRcCurrencyId;
	}

	public void setConsolidatedRcCurrencyId(Integer consolidatedRcCurrencyId) {
		this.consolidatedRcCurrencyId = consolidatedRcCurrencyId;
	}
	
	

	public String getDeliveryComments() {
		return deliveryComments;
	}

	public void setDeliveryComments(String deliveryComments) {
		this.deliveryComments = deliveryComments;
	}

	public Double getRevenue() {
		return revenue;
	}

	public void setRevenue(Double revenue) {
		this.revenue = revenue;
	}

	public Double getRevenuePostPercentage() {
		return revenuePostPercentage;
	}

	public void setRevenuePostPercentage(Double revenuePostPercentage) {
		this.revenuePostPercentage = revenuePostPercentage;
	}

	public Double getCalculatedRPGMPercentage() {
		return calculatedRPGMPercentage;
	}

	public void setCalculatedRPGMPercentage(Double calculatedRPGMPercentage) {
		this.calculatedRPGMPercentage = calculatedRPGMPercentage;
	}

	public Double getCalculatedRPGMPercentagePostDiscount() {
		return calculatedRPGMPercentagePostDiscount;
	}

	public Double getCalculatedGMPercentage() {
		return calculatedGMPercentage;
	}

	public void setCalculatedGMPercentage(Double calculatedGMPercentage) {
		this.calculatedGMPercentage = calculatedGMPercentage;
	}

	public Double getCalculatedGMPercentagePostDiscount() {
		return calculatedGMPercentagePostDiscount;
	}

	
	public Integer getLevel1ApproverRoleId() {
		return level1ApproverRoleId;
	}

	public void setLevel1ApproverRoleId(Integer level1ApproverRoleId) {
		this.level1ApproverRoleId = level1ApproverRoleId;
	}



	public Integer getCurrentApprovalStatus() {
		return currentApprovalStatus;
	}

	public void setCurrentApprovalStatus(Integer currentApprovalStatus) {
		this.currentApprovalStatus = currentApprovalStatus;
	}

	public String getApproverComments() {
		return approverComments;
	}

	public void setApproverComments(String approverComments) {
		this.approverComments = approverComments;
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

	public void setCreatedOn(String createdDate) {
		this.createdOn = createdDate;
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
	
	public void setCalculatedRPGMPercentagePostDiscount(Double calculatedRPGMPercentagePostDiscount) {
		this.calculatedRPGMPercentagePostDiscount = calculatedRPGMPercentagePostDiscount;
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

	public void setCalculatedGMPercentagePostDiscount(Double calculatedGMPercentagePostDiscount) {
		this.calculatedGMPercentagePostDiscount = calculatedGMPercentagePostDiscount;
	}

	public String getCurrentApproverId() {
		return currentApproverId;
	}

	public void setCurrentApproverId(String currentApproverId) {
		this.currentApproverId = currentApproverId;
	}

	public Double getVolumeDiscount() {
		return volumeDiscount;
	}

	public void setVolumeDiscount(Double volumeDiscount) {
		this.volumeDiscount = volumeDiscount;
	}
	
	public Integer getCurrentApprovalLevel() {
		return currentApprovalLevel;
	}

	public void setCurrentApprovalLevel(Integer currentApprovalLevel) {
		this.currentApprovalLevel = currentApprovalLevel;
	}

	
	public Double getOnsiteHoursPerDay() {
		return onsiteHoursPerDay;
	}

	public void setOnsiteHoursPerDay(Double onsiteHoursPerDay) {
		this.onsiteHoursPerDay = onsiteHoursPerDay;
	}

	public Double getOffshoreHoursPerDay() {
		return offshoreHoursPerDay;
	}

	public void setOffshoreHoursPerDay(Double offshoreHoursPerDay) {
		this.offshoreHoursPerDay = offshoreHoursPerDay;
	}


	public Integer getCurrentApproverRoleId() {
		return currentApproverRoleId;
	}

	public void setCurrentApproverRoleId(Integer currentApproverRoleId) {
		this.currentApproverRoleId = currentApproverRoleId;
	}
	
	public Double getCpcCharges() {
		return cpcCharges;
	}

	public void setCpcCharges(Double cpcCharges) {
		this.cpcCharges = cpcCharges;
	}
	
	public Integer getApplicableMonths() {
		return applicableMonths;
	}

	public void setApplicableMonths(Integer applicableMonths) {
		this.applicableMonths = applicableMonths;
	}

	public double getExpectedOffshoreResourcePercentage() {
		return expectedOffshoreResourcePercentage;
	}

	public void setExpectedOffshoreResourcePercentage(double expectedOffshoreResourcePercentage) {
		this.expectedOffshoreResourcePercentage = expectedOffshoreResourcePercentage;
	}

	public Integer getRenewalRCId() {
		return renewalRCId;
	}

	public void setRenewalRCId(Integer renewalRCId) {
		this.renewalRCId = renewalRCId;
	}

	public Integer getIsRenewal() {
		return isRenewal;
	}

	public void setIsRenewal(Integer isRenewal) {
		this.isRenewal = isRenewal;
	}

	public String getStatusIndicator() {
		return statusIndicator;
	}

	public void setStatusIndicator(String statusIndicator) {
		this.statusIndicator = statusIndicator;
	}
	
	public String getCreatedOnExcel() {
		return createdOnExcel;
	}

	public void setCreatedOnExcel(String createdOnExcel) {
		this.createdOnExcel = createdOnExcel;
	}

	public String getUpdatedOnExcel() {
		return updatedOnExcel;
	}

	public void setUpdatedOnExcel(String updatedOnExcel) {
		this.updatedOnExcel = updatedOnExcel;
	}

	public String getSpocName() {
		return spocName;
	}

	public void setSpocName(String spocName) {
		this.spocName = spocName;
	}

	public Integer getRainbowApprovalFlag() {
		return rainbowApprovalFlag;
	}

	public void setRainbowApprovalFlag(Integer rainbowApprovalFlag) {
		this.rainbowApprovalFlag = rainbowApprovalFlag;
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

	public Integer getDiscFlag() {
		return discFlag;
	}

	public void setDiscFlag(Integer discFlag) {
		this.discFlag = discFlag;
	}


	public String toString() {
		  StringBuilder result = new StringBuilder();
		  String newLine = System.getProperty("line.separator");

		  result.append( this.getClass().getName() );
		  result.append( " Object {" );
		  result.append(newLine);

		  //determine fields declared in this class only (no fields of superclass)
		  Field[] fields = this.getClass().getDeclaredFields();

		  //print field names paired with their values
		  for ( Field field : fields  ) {
		    result.append("  ");
		    try {
		      result.append( field.getName() );
		      result.append(": ");
		      //requires access to private field:
		      result.append( field.get(this) );
		    } catch ( IllegalAccessException ex ) {
		      AppLoger.APPLOGGER.info(ex);
		    }
		    result.append(newLine);
		  }
		  result.append("}");

		  return result.toString();
		}
}
