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

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@Entity
@ApiModel(value="Deal Staffing Details",description="Fixed Price Deal Staffing Details")
/*@Table(name = "synprod.RP_Deal_Staffing_Details" ,schema = "RightPriceDB")*/
@Table(name = "synprod.RP_Deal_Staffing_Details")
public class StaffingDetails implements Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "STAFFING_ROW_ID")
	@ApiModelProperty(notes="Deal Staffing Row ID")
	private Integer staffingRowId;
	
	@Column(name = "Staffing_Header_Id")
	@ApiModelProperty(notes="Staffing Header ID")
	private Integer staffingHeaderId;
	
	@Column(name = "Master_Role_Id")
	@ApiModelProperty(notes="Master Role ID")
	private Integer masterRoleId;
	
	@Column(name = "RP_DEAL_VERSION_ID")
	@ApiModelProperty(notes="Deal Version ID")
	private Integer rpDealVersionId;
	
	@Column(name = "DEAL_TOWER_ID")
	@ApiModelProperty(notes="Deal Tower ID")
	private Integer dealTowerId;

	@Column(name = "IS_CONTRACTOR")
	@ApiModelProperty(notes="Is Contractor Record Flag")
	private Integer isContractor;
	
	@Column(name = "CUSTOMER_ROLE")
	@ApiModelProperty(notes="Customer Role")
	private String customerRole;
	
	@Column(name = "MONTH_YEAR_HEADER")
	@ApiModelProperty(notes="Month Year Header")
	private String monthYearHeader;
	
	@Column(name = "COUNTRY_ID")
	@ApiModelProperty(notes="Country ID")
	private Integer countryId;
	
	@Column(name = "CITY_ID")
	@ApiModelProperty(notes="City ID")
	private Integer cityId;
	
	@Column(name = "VISA_ID")
	@ApiModelProperty(notes="Visa ID")
	private Integer visaId;
	
	@Column(name = "COUNTRY_VISA_LABEL_DESCRIPTION")
	@ApiModelProperty(notes="Visa Lable Description")
	private String countryVisaLabelDescription;
	
	@Column(name = "START_DATE")
	@ApiModelProperty(notes="Start Date")
	private String startDate;
	
	@Column(name = "END_DATE")
	@ApiModelProperty(notes="End Date")
	private String endDate;
	
	@Column(name = "STAFFING_YEAR")
	@ApiModelProperty(notes="Staffing Year")
	private Integer staffingYear;
	
	@Column(name = "STAFFING_START_MONTH")
	@ApiModelProperty(notes="Staffing Start Month")
	private Integer staffingStartMonth;
	
	@Column(name = "STAFFING_MONTH_1_COUNT")
	@ApiModelProperty(notes="Staffing First Month Count")
	private Double staffingFirstMonthCount;
	
	@Column(name = "STAFFING_MONTH_2_COUNT")
	@ApiModelProperty(notes="Staffing Second Month Count")
	private Double staffingSecondMonthCount;
	
	@Column(name = "STAFFING_MONTH_3_COUNT")
	@ApiModelProperty(notes="Staffing Third Month Count")
	private Double staffingThirdMonthCount;
	
	@Column(name = "STAFFING_MONTH_4_COUNT")
	@ApiModelProperty(notes="Staffing Fourth Month Count")
	private Double staffingFourthMonthCount;
	
	@Column(name = "STAFFING_MONTH_5_COUNT")
	@ApiModelProperty(notes="Staffing Fifth Month Count")
	private Double staffingFifthMonthCount;
	
	@Column(name = "STAFFING_MONTH_6_COUNT")
	@ApiModelProperty(notes="Staffing Sixth Month Count")
	private Double staffingSixthMonthCount;
	
	@Column(name = "STAFFING_MONTH_7_COUNT")
	@ApiModelProperty(notes="Staffing Seventh Month Count")
	private Double staffingSeventhMonthCount;
	
	@Column(name = "STAFFING_MONTH_8_COUNT")
	@ApiModelProperty(notes="Staffing Eighth Month Count")
	private Double staffingEighthMonthCount;
	
	@Column(name = "STAFFING_MONTH_9_COUNT")
	@ApiModelProperty(notes="Staffing Ninth Month Count")
	private Double staffingNinthMonthCount;
	
	@Column(name = "STAFFING_MONTH_10_COUNT")
	@ApiModelProperty(notes="Staffing Tenth Month Count")
	private Double staffingTenthMonthCount;
	
	@Column(name = "STAFFING_MONTH_11_COUNT")
	@ApiModelProperty(notes="Staffing Eleventh Month Count")
	private Double staffingEleventhMonthCount;
	
	@Column(name = "STAFFING_MONTH_12_COUNT")
	@ApiModelProperty(notes="Staffing Twelth Month Count")
	private Double staffingTwelthMonthCount;
	
	@Column(name = "YEARLY_SUM_TOTAL")
	@ApiModelProperty(notes="Yearly Sum Total")
	private Double yearlyTotal;
	
	@Column(name = "SUM_TOTAL_TYPE")
	@ApiModelProperty(notes="Is Sum Total")
	private Integer sumTotalType;
	
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
	
	@OneToOne(optional = true, fetch = FetchType.EAGER)
    @JoinColumn(name = "MASTER_ROLE_ID",insertable=false,updatable=false)
	private MasterRoles masterRoles;
	
	@OneToOne(optional = true, fetch = FetchType.EAGER)
    @JoinColumn(name = "STAFFING_HEADER_ID",referencedColumnName="STAFFING_HEADER_ID",insertable=false,updatable=false)
	private StaffingDetailsHeader staffingDetailsHeader;
	
	@Column(name = "billing_Rate")
	@ApiModelProperty(notes="billing_Rate notes")
	private Double stfaffingBilling_Rate;

	public Double getStfaffingBilling_Rate() {
		return stfaffingBilling_Rate;
	}

	public void setStfaffingBilling_Rate(Double stfaffingBilling_Rate) {
		this.stfaffingBilling_Rate = stfaffingBilling_Rate;
	}

	public Integer getStaffingRowId() {
		return staffingRowId;
	}

	public void setStaffingRowId(Integer staffingRowId) {
		this.staffingRowId = staffingRowId;
	}

	public Integer getStaffingHeaderId() {
		return staffingHeaderId;
	}

	public void setStaffingHeaderId(Integer staffingHeaderId) {
		this.staffingHeaderId = staffingHeaderId;
	}

	public Integer getMasterRoleId() {
		return masterRoleId;
	}

	public void setMasterRoleId(Integer masterRoleId) {
		this.masterRoleId = masterRoleId;
	}

	public Integer getRpDealVersionId() {
		return rpDealVersionId;
	}

	public void setRpDealVersionId(Integer rpDealVersionId) {
		this.rpDealVersionId = rpDealVersionId;
	}

	public Integer getDealTowerId() {
		return dealTowerId;
	}

	public void setDealTowerId(Integer dealTowerId) {
		this.dealTowerId = dealTowerId;
	}

	public Integer getIsContractor() {
		return isContractor;
	}

	public void setIsContractor(Integer isContractor) {
		this.isContractor = isContractor;
	}

	public String getCustomerRole() {
		return customerRole;
	}

	public void setCustomerRole(String customerRole) {
		this.customerRole = customerRole;
	}

	public String getMonthYearHeader() {
		return monthYearHeader;
	}

	public void setMonthYearHeader(String monthYearHeader) {
		this.monthYearHeader = monthYearHeader;
	}

	public Integer getCountryId() {
		return countryId;
	}

	public void setCountryId(Integer countryId) {
		this.countryId = countryId;
	}

	public Integer getCityId() {
		return cityId;
	}

	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}

	public Integer getVisaId() {
		return visaId;
	}

	public void setVisaId(Integer visaId) {
		this.visaId = visaId;
	}

	public String getCountryVisaLabelDescription() {
		return countryVisaLabelDescription;
	}

	public void setCountryVisaLabelDescription(String countryVisaLabelDescription) {
		this.countryVisaLabelDescription = countryVisaLabelDescription;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public Integer getStaffingYear() {
		return staffingYear;
	}

	public void setStaffingYear(Integer staffingYear) {
		this.staffingYear = staffingYear;
	}

	public Integer getStaffingStartMonth() {
		return staffingStartMonth;
	}

	public void setStaffingStartMonth(Integer staffingStartMonth) {
		this.staffingStartMonth = staffingStartMonth;
	}

	public Double getStaffingFirstMonthCount() {
		return staffingFirstMonthCount;
	}

	public void setStaffingFirstMonthCount(Double staffingFirstMonthCount) {
		this.staffingFirstMonthCount = staffingFirstMonthCount;
	}

	public Double getStaffingSecondMonthCount() {
		return staffingSecondMonthCount;
	}

	public void setStaffingSecondMonthCount(Double staffingSecondMonthCount) {
		this.staffingSecondMonthCount = staffingSecondMonthCount;
	}

	public Double getStaffingThirdMonthCount() {
		return staffingThirdMonthCount;
	}

	public void setStaffingThirdMonthCount(Double staffingThirdMonthCount) {
		this.staffingThirdMonthCount = staffingThirdMonthCount;
	}

	public Double getStaffingFourthMonthCount() {
		return staffingFourthMonthCount;
	}

	public void setStaffingFourthMonthCount(Double staffingFourthMonthCount) {
		this.staffingFourthMonthCount = staffingFourthMonthCount;
	}

	public Double getStaffingFifthMonthCount() {
		return staffingFifthMonthCount;
	}

	public void setStaffingFifthMonthCount(Double staffingFifthMonthCount) {
		this.staffingFifthMonthCount = staffingFifthMonthCount;
	}

	public Double getStaffingSixthMonthCount() {
		return staffingSixthMonthCount;
	}

	public void setStaffingSixthMonthCount(Double staffingSixthMonthCount) {
		this.staffingSixthMonthCount = staffingSixthMonthCount;
	}

	public Double getStaffingSeventhMonthCount() {
		return staffingSeventhMonthCount;
	}

	public void setStaffingSeventhMonthCount(Double staffingSeventhMonthCount) {
		this.staffingSeventhMonthCount = staffingSeventhMonthCount;
	}

	public Double getStaffingEighthMonthCount() {
		return staffingEighthMonthCount;
	}

	public void setStaffingEighthMonthCount(Double staffingEighthMonthCount) {
		this.staffingEighthMonthCount = staffingEighthMonthCount;
	}

	public Double getStaffingNinthMonthCount() {
		return staffingNinthMonthCount;
	}

	public void setStaffingNinthMonthCount(Double staffingNinthMonthCount) {
		this.staffingNinthMonthCount = staffingNinthMonthCount;
	}

	public Double getStaffingTenthMonthCount() {
		return staffingTenthMonthCount;
	}

	public void setStaffingTenthMonthCount(Double staffingTenthMonthCount) {
		this.staffingTenthMonthCount = staffingTenthMonthCount;
	}

	public Double getStaffingEleventhMonthCount() {
		return staffingEleventhMonthCount;
	}

	public void setStaffingEleventhMonthCount(Double staffingEleventhMonthCount) {
		this.staffingEleventhMonthCount = staffingEleventhMonthCount;
	}

	public Double getStaffingTwelthMonthCount() {
		return staffingTwelthMonthCount;
	}

	public void setStaffingTwelthMonthCount(Double staffingTwelthMonthCount) {
		this.staffingTwelthMonthCount = staffingTwelthMonthCount;
	}

	public Double getYearlyTotal() {
		return yearlyTotal;
	}

	public void setYearlyTotal(Double yearlyTotal) {
		this.yearlyTotal = yearlyTotal;
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

	public StaffingDetailsHeader getStaffingDetailsHeader() {
		return staffingDetailsHeader;
	}

	public void setStaffingDetailsHeader(StaffingDetailsHeader staffingDetailsHeader) {
		this.staffingDetailsHeader = staffingDetailsHeader;
	}

	public Integer getSumTotalType() {
		return sumTotalType;
	}

	public void setSumTotalType(Integer sumTotalType) {
		this.sumTotalType = sumTotalType;
	}
	
	
	
	
	
	
}
