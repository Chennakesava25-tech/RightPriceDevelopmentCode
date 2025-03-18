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
@Table(name = "synprod.RP_Deal_FP_Direct_Cost_Details")
public class DirectCostDetails {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "Direct_Cost_Header_Details_Id")
	@ApiModelProperty(notes="Direct Cost Header Details Id")
	private Integer directCostHeaderDetailsId;
	
	@Column(name = "Direct_Cost_Header_Id")
	@ApiModelProperty(notes="Direct Cost Header Id")
	private Integer directCostHeaderId;
	
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
	
	@Column(name = "Direct_Cost_Type_Id")
	@ApiModelProperty(notes="Deduction_Type_Id")
	private Integer deductionTypeId;
	
	@Column(name = "Direct_Cost_Type_Description")
	@ApiModelProperty(notes="Direct Cost Type Description")
	private String directCostTypeDescription;
	
	@Column(name = "Deduction_Value")
	@ApiModelProperty(notes="Deduction Value")
	private Double directCostValue;
	
	@Column(name = "Direct_Cost_Value_1")
	@ApiModelProperty(notes="Direct Cost Value 1")
	private Double directCostValue1;
	
	@Column(name = "Direct_Cost_Value_2")
	@ApiModelProperty(notes="Direct Cost Value 2")
	private Double directCostValue2;
	
	@Column(name = "Direct_Cost_Value_3")
	@ApiModelProperty(notes="Direct Cost Value 3")
	private Double directCostValue3;
	
	@Column(name = "Direct_Cost_Value_4")
	@ApiModelProperty(notes="Direct Cost Value 4")
	private Double directCostValue4;
	
	@Column(name = "Direct_Cost_Value_5")
	@ApiModelProperty(notes="Direct Cost Value 5")
	private Double directCostValue5;
	
	@Column(name = "Direct_Cost_Value_6")
	@ApiModelProperty(notes="Direct Cost Value 6")
	private Double directCostValue6;
	
	@Column(name = "Direct_Cost_Value_7")
	@ApiModelProperty(notes="Direct Cost Value 7")
	private Double directCostValue7;
	
	@Column(name = "Direct_Cost_Value_8")
	@ApiModelProperty(notes="Direct Cost Value 8")
	private Double directCostValue8;
	
	@Column(name = "Direct_Cost_Value_9")
	@ApiModelProperty(notes="Direct Cost Value 9")
	private Double directCostValue9;
	
	@Column(name = "Direct_Cost_Value_10")
	@ApiModelProperty(notes="Direct Cost Value 10")
	private Double directCostValue10;
	
	@Column(name = "Direct_Cost_Value_11")
	@ApiModelProperty(notes="Direct Cost Value 11")
	private Double directCostValue11;
	
	@Column(name = "Direct_Cost_Value_12")
	@ApiModelProperty(notes="Direct Cost Value 12")
	private Double directCostValue12;
	
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

	public Integer getDirectCostHeaderDetailsId() {
		return directCostHeaderDetailsId;
	}

	public void setDirectCostHeaderDetailsId(Integer directCostHeaderDetailsId) {
		this.directCostHeaderDetailsId = directCostHeaderDetailsId;
	}

	public Integer getDirectCostHeaderId() {
		return directCostHeaderId;
	}

	public void setDirectCostHeaderId(Integer directCostHeaderId) {
		this.directCostHeaderId = directCostHeaderId;
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

	public Integer getDeductionTypeId() {
		return deductionTypeId;
	}

	public void setDeductionTypeId(Integer deductionTypeId) {
		this.deductionTypeId = deductionTypeId;
	}

	public String getDirectCostTypeDescription() {
		return directCostTypeDescription;
	}

	public void setDirectCostTypeDescription(String directCostTypeDescription) {
		this.directCostTypeDescription = directCostTypeDescription;
	}

	public Double getDirectCostValue() {
		return directCostValue;
	}

	public void setDirectCostValue(Double directCostValue) {
		this.directCostValue = directCostValue;
	}

	public Double getDirectCostValue1() {
		return directCostValue1;
	}

	public void setDirectCostValue1(Double directCostValue1) {
		this.directCostValue1 = directCostValue1;
	}

	public Double getDirectCostValue2() {
		return directCostValue2;
	}

	public void setDirectCostValue2(Double directCostValue2) {
		this.directCostValue2 = directCostValue2;
	}

	public Double getDirectCostValue3() {
		return directCostValue3;
	}

	public void setDirectCostValue3(Double directCostValue3) {
		this.directCostValue3 = directCostValue3;
	}

	public Double getDirectCostValue4() {
		return directCostValue4;
	}

	public void setDirectCostValue4(Double directCostValue4) {
		this.directCostValue4 = directCostValue4;
	}

	public Double getDirectCostValue5() {
		return directCostValue5;
	}

	public void setDirectCostValue5(Double directCostValue5) {
		this.directCostValue5 = directCostValue5;
	}

	public Double getDirectCostValue6() {
		return directCostValue6;
	}

	public void setDirectCostValue6(Double directCostValue6) {
		this.directCostValue6 = directCostValue6;
	}

	public Double getDirectCostValue7() {
		return directCostValue7;
	}

	public void setDirectCostValue7(Double directCostValue7) {
		this.directCostValue7 = directCostValue7;
	}

	public Double getDirectCostValue8() {
		return directCostValue8;
	}

	public void setDirectCostValue8(Double directCostValue8) {
		this.directCostValue8 = directCostValue8;
	}

	public Double getDirectCostValue9() {
		return directCostValue9;
	}

	public void setDirectCostValue9(Double directCostValue9) {
		this.directCostValue9 = directCostValue9;
	}

	public Double getDirectCostValue10() {
		return directCostValue10;
	}

	public void setDirectCostValue10(Double directCostValue10) {
		this.directCostValue10 = directCostValue10;
	}

	public Double getDirectCostValue11() {
		return directCostValue11;
	}

	public void setDirectCostValue11(Double directCostValue11) {
		this.directCostValue11 = directCostValue11;
	}

	public Double getDirectCostValue12() {
		return directCostValue12;
	}

	public void setDirectCostValue12(Double directCostValue12) {
		this.directCostValue12 = directCostValue12;
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
