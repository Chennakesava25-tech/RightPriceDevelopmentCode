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
@ApiModel(value="RP_Fix_deal_Calculation_BreakUp",description="RP_Fix_deal_Calculation_BreakUp")
@Table(name = "synprod.RP_Fix_deal_Calculation_BreakUp")
public class FPCalculationDetails implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "Id")
	@ApiModelProperty(notes="id")
	private Integer id; 
	
	@Column(name = "RP_Deal_Version_Id")
	@ApiModelProperty(notes="rpDealVersionId")
	private Integer rpDealVersionId;
	
	@Column(name = "Tower_id")
	@ApiModelProperty(notes="Tower_id")
	private Integer towerId;
	
	
	@Column(name = "Country_Id")
	@ApiModelProperty(notes="Country_Id")
	private Integer countryId;
	
	@Column(name = "City_Id")
	@ApiModelProperty(notes="City_Id")
	private Integer cityId;
	
	@Column(name = "visa_id")
	@ApiModelProperty(notes="visa_id")
	private Integer visaId;
	
	//STAFFING_HEADER_ID
	@Column(name = "STAFFING_HEADER_ID")
	@ApiModelProperty(notes="STAFFING_HEADER_ID")
	private Integer staffingHeaderId;
	
	
	@Column(name = "Month_Year_Header")
	@ApiModelProperty(notes="Month_Year_Header")
	private String monthYearHeader;

	@Column(name = "Cost_Code")
	@ApiModelProperty(notes="Cost_Code")
	private Integer costCode;

	@Column(name = "Cost_type")
	@ApiModelProperty(notes="Cost_type")
	private String costType;
	
	@Column(name = "Deduction_Category_ID")
	@ApiModelProperty(notes="Deduction_Category_ID")
	private Integer deductionTypeId;
	
	@Column(name = "ROW_SEQUENCE_NUMBER")
	@ApiModelProperty(notes="ROW_SEQUENCE_NUMBER")
	private Integer rowSequenceNumber;
	
	@Column(name = "SECTION_ID")
	@ApiModelProperty(notes="SECTION_ID")
	private Integer sectionId;
	
	@Column(name = "TRANSISTION_YEAR")
	@ApiModelProperty(notes="TRANSISTION_YEAR")
	private Integer transactionYear;
	
	@Column(name = "STAFFING_MONTH_1_COUNT")
	@ApiModelProperty(notes="STAFFING_MONTH_1_COUNT")
	private Double staffingFirstMonthCount;
	
	@Column(name = "STAFFING_MONTH_2_COUNT")
	@ApiModelProperty(notes="STAFFING_MONTH_2_COUNT")
	private Double staffingSecondMonthCount;
	
	@Column(name = "STAFFING_MONTH_3_COUNT")
	@ApiModelProperty(notes="STAFFING_MONTH_3_COUNT")
	private Double staffingThirdMonthCount;
	
	
	@Column(name = "STAFFING_MONTH_4_COUNT")
	@ApiModelProperty(notes="STAFFING_MONTH_4_COUNT")
	private Double staffingFourthMonthCount;
	
	@Column(name = "STAFFING_MONTH_5_COUNT")
	@ApiModelProperty(notes="STAFFING_MONTH_5_COUNT")
	private Double staffingFifthMonthCount;
	
	@Column(name = "STAFFING_MONTH_6_COUNT")
	@ApiModelProperty(notes="STAFFING_MONTH_6_COUNT")
	private Double staffingSixthMonthCount;
	
	@Column(name = "STAFFING_MONTH_7_COUNT")
	@ApiModelProperty(notes="STAFFING_MONTH_7_COUNT")
	private Double staffingSeventhMonthCount;
	
	@Column(name = "STAFFING_MONTH_8_COUNT")
	@ApiModelProperty(notes="STAFFING_MONTH_8_COUNT")
	private Double staffingEighthMonthCount;
	
	@Column(name = "STAFFING_MONTH_9_COUNT")
	@ApiModelProperty(notes="STAFFING_MONTH_9_COUNT")
	private Double staffingNinthMonthCount;
	
	@Column(name = "STAFFING_MONTH_10_COUNT")
	@ApiModelProperty(notes="STAFFING_MONTH_10_COUNT")
	private Double staffingTenthMonthCount;
	
	@Column(name = "STAFFING_MONTH_11_COUNT")
	@ApiModelProperty(notes="STAFFING_MONTH_11_COUNT")
	private Double staffingEleventhMonthCount;
	
	@Column(name = "STAFFING_MONTH_12_COUNT")
	@ApiModelProperty(notes="STAFFING_MONTH_12_COUNT")
	private Double staffingTwelthMonthCount;
	
	@Column(name = "YEARLY_SUM_TOTAL")
	@ApiModelProperty(notes="YEARLY_SUM_TOTAL")
	private Double yearlyTotal;
	
	@Column(name = "Total_indicator")
	@ApiModelProperty(notes="Total_indicator")
	private Integer totalIndicator;
	
	@Column(name = "IS_ACTIVE")
	@ApiModelProperty(notes="Is Active")
	private Integer isActive;
	
	@Column(name = "CREATED_BY")
	@ApiModelProperty(notes="Created By")
	private String createdBy;
	
	@Column(name = "CREATED_ON")
	@ApiModelProperty(notes="Created On")
	private String createdOn;
	
	@Column(name = "UPDATED_BY")
	@ApiModelProperty(notes="Updated By")
	private String updatedBy;
	
	@Column(name = "UPDATED_ON")
	@ApiModelProperty(notes="Updated On")
	private String updatedOn;
	
	@OneToOne(optional = true, fetch = FetchType.EAGER)
    @JoinColumn(name = "STAFFING_HEADER_ID",referencedColumnName="STAFFING_HEADER_ID",insertable=false,updatable=false)
	private StaffingDetailsHeader staffingDetailsHeader;

	public StaffingDetailsHeader getStaffingDetailsHeader() {
		return staffingDetailsHeader;
	}

	public void setStaffingDetailsHeader(StaffingDetailsHeader staffingDetailsHeader) {
		this.staffingDetailsHeader = staffingDetailsHeader;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getRpDealVersionId() {
		return rpDealVersionId;
	}

	public void setRpDealVersionId(Integer rpDealVersionId) {
		this.rpDealVersionId = rpDealVersionId;
	}

	public Integer getTowerId() {
		return towerId;
	}

	public void setTowerId(Integer towerId) {
		this.towerId = towerId;
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

	public String getMonthYearHeader() {
		return monthYearHeader;
	}

	public void setMonthYearHeader(String monthYearHeader) {
		this.monthYearHeader = monthYearHeader;
	}

	public Integer getCostCode() {
		return costCode;
	}

	public void setCostCode(Integer costCode) {
		this.costCode = costCode;
	}

	public String getCostType() {
		return costType;
	}

	public void setCostType(String costType) {
		this.costType = costType;
	}

	public Integer getDeductionTypeId() {
		return deductionTypeId;
	}

	public void setDeductionTypeId(Integer deductionTypeId) {
		this.deductionTypeId = deductionTypeId;
	}

	public Integer getRowSequenceNumber() {
		return rowSequenceNumber;
	}

	public void setRowSequenceNumber(Integer rowSequenceNumber) {
		this.rowSequenceNumber = rowSequenceNumber;
	}

	public Integer getTransactionYear() {
		return transactionYear;
	}

	public void setTransactionYear(Integer transactionYear) {
		this.transactionYear = transactionYear;
	}

	public Integer getStaffingHeaderId() {
		return staffingHeaderId;
	}

	public void setStaffingHeaderId(Integer staffingHeaderId) {
		this.staffingHeaderId = staffingHeaderId;
	}

	public Integer getSectionId() {
		return sectionId;
	}

	public void setSectionId(Integer sectionId) {
		this.sectionId = sectionId;
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

	public Integer getTotalIndicator() {
		return totalIndicator;
	}

	public void setTotalIndicator(Integer totalIndicator) {
		this.totalIndicator = totalIndicator;
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
}
