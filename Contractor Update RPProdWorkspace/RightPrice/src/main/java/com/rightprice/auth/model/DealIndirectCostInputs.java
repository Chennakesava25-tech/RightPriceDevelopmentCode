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
@ApiModel(value="RP_FP_Deal_Indirect_Cost_Input_Details",description="RP Deal Indirect Cost Input Details")
@Table(name = "synprod.RP_FP_Deal_Indirect_Cost_Input_Details")
public class DealIndirectCostInputs implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "INDIRECT_COST_ROW_ID")
	@ApiModelProperty(notes="ID Auto Incremented")
	private Integer indirectCostRowId;
	
	
	@Column(name = "STAFFING_HEADER_ID")
	@ApiModelProperty(notes="STAFFING_HEADER_ID")
	private Integer staffingHeaderId;
	
	
	@Column(name = "INDIRECT_COST_HEADER_ID")
	@ApiModelProperty(notes="INDIRECT_COST_HEADER_ID")
	private Integer indirectCostHeaderId;
	
	@Column(name = "INDIRECT_COST_TYPE")
	@ApiModelProperty(notes="INDIRECT_COST_TYPE")
	private Integer indirectCostType;
	
	@Column(name = "INDIRECT_COST_ID")
	@ApiModelProperty(notes="INDIRECT_COST_ID")
	private Integer indirectCostId;
	
	@Column(name = "RP_DEAL_VERSION_ID")
	@ApiModelProperty(notes="RP_DEAL_VERSION_ID")
	private Integer rpDetalVersionId;
	
	@Column(name = "DEAL_TOWER_ID")
	@ApiModelProperty(notes="DEAL_TOWER_ID")
	private Integer dealTowerId;
	
	@Column(name = "MONTH_YEAR_HEADER")
	@ApiModelProperty(notes="MONTH_YEAR_HEADER")
	private String monthYearHeader;
	
	@Column(name = "COUNTRY_ID")
	@ApiModelProperty(notes="COUNTRY_ID")
	private Integer countryId;
	
	@Column(name = "CITY_ID")
	@ApiModelProperty(notes="CITY_ID")
	private Integer cityId;
	
	@Column(name = "INDIRECT_COST_DESCRIPTION")
	@ApiModelProperty(notes="INDIRECT_COST_DESCRIPTION")
	private String indirectCostDescription;
	
	@Column(name = "START_DATE")
	@ApiModelProperty(notes="START_DATE")
	private String startDate;
	
	@Column(name = "END_DATE")
	@ApiModelProperty(notes="END_DATE")
	private String endDate;
	
	@Column(name = "STAFFING_YEAR")
	@ApiModelProperty(notes="STAFFING_YEAR")
	private Integer staffingYear;
	
	@Column(name = "STAFFING_START_MONTH")
	@ApiModelProperty(notes="STAFFING_START_MONTH")
	private Integer staffingStartMonth;
	
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
	
	@Column(name = "Unit_Cost")
	@ApiModelProperty(notes="Unit_Cost")
	private Double unitCost;
	
	@Column(name = "Comment")
	@ApiModelProperty(notes="Comment")
	private String comment;
	
	@Column(name = "Visa_Id")
	@ApiModelProperty(notes="Visa_Id")
	private Integer visaId;
	
	@Column(name = "Currency_Country_ID")
	@ApiModelProperty(notes="Currency_Country_ID")
	private Integer currencyCountryId;
	
	//Sum_Total_Type
	@Column(name = "SUM_TOTAL_TYPE")
	@ApiModelProperty(notes="SUM_TOTAL_TYPE")
	private Integer sumTotalType;
	
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
	
	@OneToOne(optional = true, fetch = FetchType.EAGER)
    @JoinColumn(name = "STAFFING_HEADER_ID",referencedColumnName="STAFFING_HEADER_ID",insertable=false,updatable=false)
	private StaffingDetailsHeader staffingDetailsHeader;
	
	@OneToOne(optional = true, fetch = FetchType.EAGER)
    @JoinColumn(name = "INDIRECT_COST_ID",referencedColumnName="INDIRECT_COST_MASTER_ID",insertable=false,updatable=false)
	private DealIndrectCostMaster inDirectCostmaster;

	public Integer getIndirectCostRowId() {
		return indirectCostRowId;
	}

	public void setIndirectCostRowId(Integer indirectCostRowId) {
		this.indirectCostRowId = indirectCostRowId;
	}

	public Integer getIndirectCostHeaderId() {
		return indirectCostHeaderId;
	}

	public void setIndirectCostHeaderId(Integer indirectCostHeaderId) {
		this.indirectCostHeaderId = indirectCostHeaderId;
	}

	public Integer getIndirectCostType() {
		return indirectCostType;
	}

	public void setIndirectCostType(Integer indirectCostType) {
		this.indirectCostType = indirectCostType;
	}

	public Integer getIndirectCostId() {
		return indirectCostId;
	}

	public void setIndirectCostId(Integer indirectCostId) {
		this.indirectCostId = indirectCostId;
	}

	public Integer getRpDetalVersionId() {
		return rpDetalVersionId;
	}

	public void setRpDetalVersionId(Integer rpDetalVersionId) {
		this.rpDetalVersionId = rpDetalVersionId;
	}

	public Integer getDealTowerId() {
		return dealTowerId;
	}

	public void setDealTowerId(Integer dealTowerId) {
		this.dealTowerId = dealTowerId;
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

	public String getIndirectCostDescription() {
		return indirectCostDescription;
	}

	public void setIndirectCostDescription(String indirectCostDescription) {
		this.indirectCostDescription = indirectCostDescription;
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

	public Integer getSumTotalType() {
		return sumTotalType;
	}

	public void setSumTotalType(Integer sumTotalType) {
		this.sumTotalType = sumTotalType;
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

	public StaffingDetailsHeader getStaffingDetailsHeader() {
		return staffingDetailsHeader;
	}

	public void setStaffingDetailsHeader(StaffingDetailsHeader staffingDetailsHeader) {
		this.staffingDetailsHeader = staffingDetailsHeader;
	}

	public Integer getStaffingHeaderId() {
		return staffingHeaderId;
	}

	public void setStaffingHeaderId(Integer staffingHeaderId) {
		this.staffingHeaderId = staffingHeaderId;
	}

	public Double getStaffingFirstMonthCount() {
		return staffingFirstMonthCount;
	}

	public void setStaffingFirstMonthCount(Double staffingFirstMonthCount) {
		this.staffingFirstMonthCount = staffingFirstMonthCount;
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

	public Double getUnitCost() {
		return unitCost;
	}

	public void setUnitCost(Double unitCost) {
		this.unitCost = unitCost;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Integer getVisaId() {
		return visaId;
	}

	public void setVisaId(Integer visaId) {
		this.visaId = visaId;
	}

	public Integer getCurrencyCountryId() {
		return currencyCountryId;
	}

	public void setCurrencyCountryId(Integer currencyCountryId) {
		this.currencyCountryId = currencyCountryId;
	}

	public DealIndrectCostMaster getInDirectCostmaster() {
		return inDirectCostmaster;
	}

	public void setInDirectCostmaster(DealIndrectCostMaster inDirectCostmaster) {
		this.inDirectCostmaster = inDirectCostmaster;
	}
	
	
	
}
