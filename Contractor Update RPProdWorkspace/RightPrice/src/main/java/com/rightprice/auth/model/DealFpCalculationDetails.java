package com.rightprice.auth.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@Entity
@ApiModel(value="Country",description="Country Model Attributes")
@Table(name = "synprod.RP_Deal_FP_Calculation_Details")
public class DealFpCalculationDetails {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "Calculation_Details_Id")
	@ApiModelProperty(notes="Calculation_Details_Id Auto Incremented")
	private Integer calculationDetailsId;
	
	@Column(name = "Calculation_Header_Id")
	@ApiModelProperty(notes="Calculation Header Id")
	private Integer calculationHeaderId;
	
	@Column(name = "Transaction_Year")
	@ApiModelProperty(notes="Transaction Year")
	private Integer transactionYear;
	
	@Column(name = "Month_Year_Header_Key_Ref")
	@ApiModelProperty(notes="Month_Year_Header_Key_Ref")
	private Integer monthYearHeaderkeyRef;
	
	@Column(name = "Visa_Type_Id")
	@ApiModelProperty(notes="Visa_Type_Id")
	private Integer visaTypeId;
	
	@Column(name = "Country_Visa_Description")
	@ApiModelProperty(notes="Country Visa Description")
	private String	countryVisaDescription;
	
	@Column(name = "City_Id")
	@ApiModelProperty(notes="City Id")
	private Integer cityId;
	
	@Column(name = "Deal_Tower_Id")
	@ApiModelProperty(notes="Deal_Tower_Id")
	private Integer dealTowerId;
	
	@Column(name = "Deduction_Type_Id")
	@ApiModelProperty(notes="Deduction_Type_Id")
	private Integer deductionTypeId;
	
	@Column(name = "Dedcution_Type_Description")
	@ApiModelProperty(notes="Dedcution Type Description")
	private String dedcutionTypeDescription;
	
	@Column(name = "Deduction_Value")
	@ApiModelProperty(notes="Deduction Value")
	private Double deductionValue;
	
	@Column(name = "Calculation_Start_Month")
	@ApiModelProperty(notes="Calculation Start Month")
	private Integer calculationStartMonth;
	
	@Column(name = "Month_Cal_Value_1")
	@ApiModelProperty(notes="Month Cal Value 1")
	private Double monthCalValue1;
	
	@Column(name = "Month_Cal_Value_2")
	@ApiModelProperty(notes="Month Cal Value 2")
	private Double monthCalValue2;
	
	@Column(name = "Month_Cal_Value_3")
	@ApiModelProperty(notes="Month Cal Value 3")
	private Double monthCalValue3;
	
	@Column(name = "Month_Cal_Value_4")
	@ApiModelProperty(notes="Month Cal Value 4")
	private Double monthCalValue4;
	
	@Column(name = "Month_Cal_Value_5")
	@ApiModelProperty(notes="Month Cal Value 5")
	private Double monthCalValue5;
	
	@Column(name = "Month_Cal_Value_6")
	@ApiModelProperty(notes="Month Cal Value 6")
	private Double monthCalValue6;
	
	@Column(name = "Month_Cal_Value_7")
	@ApiModelProperty(notes="Month Cal Value 7")
	private Double monthCalValue7;
	
	@Column(name = "Month_Cal_Value_8")
	@ApiModelProperty(notes="Month Cal Value 8")
	private Double monthCalValue8;
	
	@Column(name = "Month_Cal_Value_9")
	@ApiModelProperty(notes="Month Cal Value 9")
	private Double monthCalValue9;
	
	@Column(name = "Month_Cal_Value_10")
	@ApiModelProperty(notes="Month Cal Value 10")
	private Double monthCalValue10;
	
	@Column(name = "Month_Cal_Value_11")
	@ApiModelProperty(notes="Month Cal Value 11")
	private Double monthCalValue11;
	
	@Column(name = "Month_Cal_Value_12")
	@ApiModelProperty(notes="Month Cal Value 12")
	private Double monthCalValue12;
	
	@Column(name = "IS_ACTIVE")
	@ApiModelProperty(notes="Active status")
	private Integer isActive;
	
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
	
	@OneToOne(cascade = javax.persistence.CascadeType.ALL)
	@JoinColumn(name ="CALCULATION_HEADER_ID",referencedColumnName = "CALCULATION_HEADER_ID",insertable=false, updatable=false)
	private DealFpCalculationHeader dealFpCalculationHeader;

	public Integer getCalculationDetailsId() {
		return calculationDetailsId;
	}

	public void setCalculationDetailsId(Integer calculationDetailsId) {
		this.calculationDetailsId = calculationDetailsId;
	}

	public Integer getCalculationHeaderId() {
		return calculationHeaderId;
	}

	public void setCalculationHeaderId(Integer calculationHeaderId) {
		this.calculationHeaderId = calculationHeaderId;
	}

	public Integer getTransactionYear() {
		return transactionYear;
	}

	public void setTransactionYear(Integer transactionYear) {
		this.transactionYear = transactionYear;
	}

	public Integer getVisaTypeId() {
		return visaTypeId;
	}

	public String getCountryVisaDescription() {
		return countryVisaDescription;
	}

	public void setCountryVisaDescription(String countryVisaDescription) {
		this.countryVisaDescription = countryVisaDescription;
	}

	public void setVisaTypeId(Integer visaTypeId) {
		this.visaTypeId = visaTypeId;
	}

	public Integer getCityId() {
		return cityId;
	}

	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}

	public Integer getMonthYearHeaderkeyRef() {
		return monthYearHeaderkeyRef;
	}

	public void setMonthYearHeaderkeyRef(Integer monthYearHeaderkeyRef) {
		this.monthYearHeaderkeyRef = monthYearHeaderkeyRef;
	}

	public Integer getDealTowerId() {
		return dealTowerId;
	}

	public void setDealTowerId(Integer dealTowerId) {
		this.dealTowerId = dealTowerId;
	}

	public Integer getDeductionTypeId() {
		return deductionTypeId;
	}

	public void setDeductionTypeId(Integer deductionTypeId) {
		this.deductionTypeId = deductionTypeId;
	}

	public String getDedcutionTypeDescription() {
		return dedcutionTypeDescription;
	}

	public void setDedcutionTypeDescription(String dedcutionTypeDescription) {
		this.dedcutionTypeDescription = dedcutionTypeDescription;
	}

	public Double getDeductionValue() {
		return deductionValue;
	}

	public void setDeductionValue(Double deductionValue) {
		this.deductionValue = deductionValue;
	}

	public Integer getCalculationStartMonth() {
		return calculationStartMonth;
	}

	public void setCalculationStartMonth(Integer calculationStartMonth) {
		this.calculationStartMonth = calculationStartMonth;
	}

	public Double getMonthCalValue1() {
		return monthCalValue1;
	}

	public void setMonthCalValue1(Double monthCalValue1) {
		this.monthCalValue1 = monthCalValue1;
	}

	public Double getMonthCalValue2() {
		return monthCalValue2;
	}

	public void setMonthCalValue2(Double monthCalValue2) {
		this.monthCalValue2 = monthCalValue2;
	}

	public Double getMonthCalValue3() {
		return monthCalValue3;
	}

	public void setMonthCalValue3(Double monthCalValue3) {
		this.monthCalValue3 = monthCalValue3;
	}

	public Double getMonthCalValue4() {
		return monthCalValue4;
	}

	public void setMonthCalValue4(Double monthCalValue4) {
		this.monthCalValue4 = monthCalValue4;
	}

	public Double getMonthCalValue5() {
		return monthCalValue5;
	}

	public void setMonthCalValue5(Double monthCalValue5) {
		this.monthCalValue5 = monthCalValue5;
	}

	public Double getMonthCalValue6() {
		return monthCalValue6;
	}

	public void setMonthCalValue6(Double monthCalValue6) {
		this.monthCalValue6 = monthCalValue6;
	}

	public Double getMonthCalValue7() {
		return monthCalValue7;
	}

	public void setMonthCalValue7(Double monthCalValue7) {
		this.monthCalValue7 = monthCalValue7;
	}

	public Double getMonthCalValue8() {
		return monthCalValue8;
	}

	public void setMonthCalValue8(Double monthCalValue8) {
		this.monthCalValue8 = monthCalValue8;
	}

	public Double getMonthCalValue9() {
		return monthCalValue9;
	}

	public void setMonthCalValue9(Double monthCalValue9) {
		this.monthCalValue9 = monthCalValue9;
	}

	public Double getMonthCalValue10() {
		return monthCalValue10;
	}

	public void setMonthCalValue10(Double monthCalValue10) {
		this.monthCalValue10 = monthCalValue10;
	}

	public Double getMonthCalValue11() {
		return monthCalValue11;
	}

	public void setMonthCalValue11(Double monthCalValue11) {
		this.monthCalValue11 = monthCalValue11;
	}

	public Double getMonthCalValue12() {
		return monthCalValue12;
	}

	public void setMonthCalValue12(Double monthCalValue12) {
		this.monthCalValue12 = monthCalValue12;
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

	public DealFpCalculationHeader getDealFpCalculationHeader() {
		return dealFpCalculationHeader;
	}

	public void setDealFpCalculationHeader(DealFpCalculationHeader dealFpCalculationHeader) {
		this.dealFpCalculationHeader = dealFpCalculationHeader;
	}
}
