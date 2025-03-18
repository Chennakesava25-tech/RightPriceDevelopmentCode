package com.rightprice.auth.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@Entity
@ApiModel(value="cpc revenue",description="cpc revenue details")
@Table(name = "synprod.RP_Deal_FP_Shift_Cost_Details")
public class ShiftCostDetails {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "Shift_Cost_Header_Details_Id")
	@ApiModelProperty(notes="Shift Cost Header Details Id")
	private Integer shiftCostHeaderDetailsId;
	
	@Column(name = "Shift_Cost_Header_Id")
	@ApiModelProperty(notes="Shift Cost Header Id")
	private Integer shiftCostHeaderId;
	
	@Column(name = "Transaction_Year")
	@ApiModelProperty(notes="Transaction Year")
	private Integer transactionYear;
	
	@Column(name = "Month_Year_Header_Key_Ref")
	@ApiModelProperty(notes="Month_Year_Header_Key_Ref")
	private Integer monthYearHeaderkeyRef;
	
	@Column(name = "City_Id")
	@ApiModelProperty(notes="City Id")
	private Integer cityId;
	
	@Column(name = "Deal_Tower_Id")
	@ApiModelProperty(notes="Deal_Tower_Id")
	private Integer dealTowerId;
	
	@Column(name = "Shift_Cost_Type_Id")
	@ApiModelProperty(notes="Shift Cost Type Id")
	private Integer shiftCostTypeId;
	
	@Column(name = "Shift_Cost_Type_Description")
	@ApiModelProperty(notes="Shift Cost Type Description")
	private String shiftCostTypeDescription;
	
	@Column(name = "Shift_Cost_Value")
	@ApiModelProperty(notes="Shift Cost Value")
	private Double shiftCostValue;
	
	@Column(name = "Shift_Cost_Value_1")
	@ApiModelProperty(notes="Shift Cost Value 1")
	private Double shiftCostValue1;
	
	@Column(name = "Shift_Cost_Value_2")
	@ApiModelProperty(notes="Shift Cost Value 2")
	private Double shiftCostValue2;
	
	@Column(name = "Shift_Cost_Value_3")
	@ApiModelProperty(notes="Shift Cost Value 3")
	private Double shiftCostValue3;
	
	@Column(name = "Shift_Cost_Value_4")
	@ApiModelProperty(notes="Shift Cost Value 4")
	private Double shiftCostValue4;
	
	@Column(name = "Shift_Cost_Value_5")
	@ApiModelProperty(notes="Shift Cost Value 5")
	private Double shiftCostValue5;
	
	@Column(name = "Shift_Cost_Value_6")
	@ApiModelProperty(notes="Shift Cost Value 6")
	private Double shiftCostValue6;
	
	@Column(name = "Shift_Cost_Value_7")
	@ApiModelProperty(notes="Shift Cost Value 7")
	private Double shiftCostValue7;
	
	@Column(name = "Shift_Cost_Value_8")
	@ApiModelProperty(notes="Shift Cost Value 8")
	private Double shiftCostValue8;
	
	@Column(name = "Shift_Cost_Value_9")
	@ApiModelProperty(notes="Shift Cost Value 9")
	private Double shiftCostValue9;
	
	@Column(name = "Shift_Cost_Value_10")
	@ApiModelProperty(notes="Shift Cost Value 10")
	private Double shiftCostValue10;
	
	@Column(name = "Shift_Cost_Value_11")
	@ApiModelProperty(notes="Shift Cost Value 11")
	private Double shiftCostValue11;
	
	@Column(name = "Shift_Cost_Value_12")
	@ApiModelProperty(notes="Shift Cost Value 12")
	private Double shiftCostValue12;
	
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

	public Integer getShiftCostHeaderDetailsId() {
		return shiftCostHeaderDetailsId;
	}

	public void setShiftCostHeaderDetailsId(Integer shiftCostHeaderDetailsId) {
		this.shiftCostHeaderDetailsId = shiftCostHeaderDetailsId;
	}

	public Integer getShiftCostHeaderId() {
		return shiftCostHeaderId;
	}

	public void setShiftCostHeaderId(Integer shiftCostHeaderId) {
		this.shiftCostHeaderId = shiftCostHeaderId;
	}

	public Integer getTransactionYear() {
		return transactionYear;
	}

	public void setTransactionYear(Integer transactionYear) {
		this.transactionYear = transactionYear;
	}

	public Integer getMonthYearHeaderkeyRef() {
		return monthYearHeaderkeyRef;
	}

	public void setMonthYearHeaderkeyRef(Integer monthYearHeaderkeyRef) {
		this.monthYearHeaderkeyRef = monthYearHeaderkeyRef;
	}

	public Integer getCityId() {
		return cityId;
	}

	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}

	public Integer getDealTowerId() {
		return dealTowerId;
	}

	public void setDealTowerId(Integer dealTowerId) {
		this.dealTowerId = dealTowerId;
	}

	public Integer getShiftCostTypeId() {
		return shiftCostTypeId;
	}

	public void setShiftCostTypeId(Integer shiftCostTypeId) {
		this.shiftCostTypeId = shiftCostTypeId;
	}

	public String getShiftCostTypeDescription() {
		return shiftCostTypeDescription;
	}

	public void setShiftCostTypeDescription(String shiftCostTypeDescription) {
		this.shiftCostTypeDescription = shiftCostTypeDescription;
	}

	public Double getShiftCostValue() {
		return shiftCostValue;
	}

	public void setShiftCostValue(Double shiftCostValue) {
		this.shiftCostValue = shiftCostValue;
	}

	public Double getShiftCostValue1() {
		return shiftCostValue1;
	}

	public void setShiftCostValue1(Double shiftCostValue1) {
		this.shiftCostValue1 = shiftCostValue1;
	}

	public Double getShiftCostValue2() {
		return shiftCostValue2;
	}

	public void setShiftCostValue2(Double shiftCostValue2) {
		this.shiftCostValue2 = shiftCostValue2;
	}

	public Double getShiftCostValue3() {
		return shiftCostValue3;
	}

	public void setShiftCostValue3(Double shiftCostValue3) {
		this.shiftCostValue3 = shiftCostValue3;
	}

	public Double getShiftCostValue4() {
		return shiftCostValue4;
	}

	public void setShiftCostValue4(Double shiftCostValue4) {
		this.shiftCostValue4 = shiftCostValue4;
	}

	public Double getShiftCostValue5() {
		return shiftCostValue5;
	}

	public void setShiftCostValue5(Double shiftCostValue5) {
		this.shiftCostValue5 = shiftCostValue5;
	}

	public Double getShiftCostValue6() {
		return shiftCostValue6;
	}

	public void setShiftCostValue6(Double shiftCostValue6) {
		this.shiftCostValue6 = shiftCostValue6;
	}

	public Double getShiftCostValue7() {
		return shiftCostValue7;
	}

	public void setShiftCostValue7(Double shiftCostValue7) {
		this.shiftCostValue7 = shiftCostValue7;
	}

	public Double getShiftCostValue8() {
		return shiftCostValue8;
	}

	public void setShiftCostValue8(Double shiftCostValue8) {
		this.shiftCostValue8 = shiftCostValue8;
	}

	public Double getShiftCostValue9() {
		return shiftCostValue9;
	}

	public void setShiftCostValue9(Double shiftCostValue9) {
		this.shiftCostValue9 = shiftCostValue9;
	}

	public Double getShiftCostValue10() {
		return shiftCostValue10;
	}

	public void setShiftCostValue10(Double shiftCostValue10) {
		this.shiftCostValue10 = shiftCostValue10;
	}

	public Double getShiftCostValue11() {
		return shiftCostValue11;
	}

	public void setShiftCostValue11(Double shiftCostValue11) {
		this.shiftCostValue11 = shiftCostValue11;
	}

	public Double getShiftCostValue12() {
		return shiftCostValue12;
	}

	public void setShiftCostValue12(Double shiftCostValue12) {
		this.shiftCostValue12 = shiftCostValue12;
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
