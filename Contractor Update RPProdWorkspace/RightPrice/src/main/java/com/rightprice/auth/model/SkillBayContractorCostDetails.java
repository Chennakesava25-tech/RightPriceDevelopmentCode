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
@ApiModel(value="skill bay",description="skill bay contractor details")
@Table(name = "synprod.RP_Deal_FP_SkillBay_Contractor_Cost_Details")
public class SkillBayContractorCostDetails {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "Contractor_Cost_Details_Id")
	@ApiModelProperty(notes="Contractor Cost Details Id")
	private Integer contractorCostDetailsId;
	
	@Column(name = "Contractor_Cost_Header_Id")
	@ApiModelProperty(notes="Calculation cost Header Id")
	private Integer calculationCostHeaderId;
	
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
	
	@Column(name = "Contractor_Cost_Parameter_Id")
	@ApiModelProperty(notes="Contractor Cost Parameter Id")
	private Integer contractorCostParameterId;
	
	@Column(name = "Contractor_Cost_Parameter_Description")
	@ApiModelProperty(notes="Contractor Cost Parameter Description")
	private String contractorCostParameterDescription;
	
	@Column(name = "Contractor_Cost_Parameter_Value")
	@ApiModelProperty(notes="Contractor Cost Parameter Value")
	private Double contractorCostParameterValue;
	
	@Column(name = "Contractor_Cost_Value_1")
	@ApiModelProperty(notes="Contractor Cost value 1")
	private Double contractorCostValue1;
	
	@Column(name = "Contractor_Cost_Value_2")
	@ApiModelProperty(notes="Contractor Cost value 2")
	private Double contractorCostValue2;
	
	@Column(name = "Contractor_Cost_Value_3")
	@ApiModelProperty(notes="Contractor Cost value 3")
	private Double contractorCostValue3;
	
	@Column(name = "Contractor_Cost_Value_4")
	@ApiModelProperty(notes="Contractor Cost value 4")
	private Double contractorCostValue4;
	
	@Column(name = "Contractor_Cost_Value_5")
	@ApiModelProperty(notes="Contractor Cost value 5")
	private Double contractorCostValue5;
	
	@Column(name = "Contractor_Cost_Value_6")
	@ApiModelProperty(notes="Contractor Cost value 6")
	private Double contractorCostValue6;
	
	@Column(name = "Contractor_Cost_Value_7")
	@ApiModelProperty(notes="Contractor Cost value 7")
	private Double contractorCostValue7;
	
	@Column(name = "Contractor_Cost_Value_8")
	@ApiModelProperty(notes="Contractor Cost value 8")
	private Double contractorCostValue8;
	
	@Column(name = "Contractor_Cost_Value_9")
	@ApiModelProperty(notes="Contractor Cost value 9")
	private Double contractorCostValue9;
	
	@Column(name = "Contractor_Cost_Value_10")
	@ApiModelProperty(notes="Contractor Cost value 10")
	private Double contractorCostValue10;
	
	@Column(name = "Contractor_Cost_Value_11")
	@ApiModelProperty(notes="Contractor Cost value 11")
	private Double contractorCostValue11;
	
	@Column(name = "Contractor_Cost_Value_12")
	@ApiModelProperty(notes="Contractor Cost value 12")
	private Double contractorCostValue12;
	
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

	public Integer getContractorCostDetailsId() {
		return contractorCostDetailsId;
	}

	public void setContractorCostDetailsId(Integer contractorCostDetailsId) {
		this.contractorCostDetailsId = contractorCostDetailsId;
	}

	public Integer getCalculationCostHeaderId() {
		return calculationCostHeaderId;
	}

	public void setCalculationCostHeaderId(Integer calculationCostHeaderId) {
		this.calculationCostHeaderId = calculationCostHeaderId;
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

	public Integer getContractorCostParameterId() {
		return contractorCostParameterId;
	}

	public void setContractorCostParameterId(Integer contractorCostParameterId) {
		this.contractorCostParameterId = contractorCostParameterId;
	}

	public String getContractorCostParameterDescription() {
		return contractorCostParameterDescription;
	}

	public void setContractorCostParameterDescription(String contractorCostParameterDescription) {
		this.contractorCostParameterDescription = contractorCostParameterDescription;
	}

	public Double getContractorCostParameterValue() {
		return contractorCostParameterValue;
	}

	public void setContractorCostParameterValue(Double contractorCostParameterValue) {
		this.contractorCostParameterValue = contractorCostParameterValue;
	}

	public Double getContractorCostValue1() {
		return contractorCostValue1;
	}

	public void setContractorCostValue1(Double contractorCostValue1) {
		this.contractorCostValue1 = contractorCostValue1;
	}

	public Double getContractorCostValue2() {
		return contractorCostValue2;
	}

	public void setContractorCostValue2(Double contractorCostValue2) {
		this.contractorCostValue2 = contractorCostValue2;
	}

	public Double getContractorCostValue3() {
		return contractorCostValue3;
	}

	public void setContractorCostValue3(Double contractorCostValue3) {
		this.contractorCostValue3 = contractorCostValue3;
	}

	public Double getContractorCostValue4() {
		return contractorCostValue4;
	}

	public void setContractorCostValue4(Double contractorCostValue4) {
		this.contractorCostValue4 = contractorCostValue4;
	}

	public Double getContractorCostValue5() {
		return contractorCostValue5;
	}

	public void setContractorCostValue5(Double contractorCostValue5) {
		this.contractorCostValue5 = contractorCostValue5;
	}

	public Double getContractorCostValue6() {
		return contractorCostValue6;
	}

	public void setContractorCostValue6(Double contractorCostValue6) {
		this.contractorCostValue6 = contractorCostValue6;
	}

	public Double getContractorCostValue7() {
		return contractorCostValue7;
	}

	public void setContractorCostValue7(Double contractorCostValue7) {
		this.contractorCostValue7 = contractorCostValue7;
	}

	public Double getContractorCostValue8() {
		return contractorCostValue8;
	}

	public void setContractorCostValue8(Double contractorCostValue8) {
		this.contractorCostValue8 = contractorCostValue8;
	}

	public Double getContractorCostValue9() {
		return contractorCostValue9;
	}

	public void setContractorCostValue9(Double contractorCostValue9) {
		this.contractorCostValue9 = contractorCostValue9;
	}

	public Double getContractorCostValue10() {
		return contractorCostValue10;
	}

	public void setContractorCostValue10(Double contractorCostValue10) {
		this.contractorCostValue10 = contractorCostValue10;
	}

	public Double getContractorCostValue11() {
		return contractorCostValue11;
	}

	public void setContractorCostValue11(Double contractorCostValue11) {
		this.contractorCostValue11 = contractorCostValue11;
	}

	public Double getContractorCostValue12() {
		return contractorCostValue12;
	}

	public void setContractorCostValue12(Double contractorCostValue12) {
		this.contractorCostValue12 = contractorCostValue12;
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
