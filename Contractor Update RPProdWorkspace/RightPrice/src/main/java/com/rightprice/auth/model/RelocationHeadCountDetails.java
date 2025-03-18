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
@ApiModel(value="direct cost header",description="direct cost header details")
@Table(name = "synprod.RP_Deal_FP_Relocation_HeadCount_Details")
public class RelocationHeadCountDetails {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "Relocation_HeadCount_Details_Id")
	@ApiModelProperty(notes="Relocation HeadCount Details Id")
	private Integer relocationHeadCountDetailsId;
	
	@Column(name = "Calculation_Header_Id")
	@ApiModelProperty(notes="Calculation Header Id")
	private Integer calculationHeaderId;
	
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
	
	@Column(name = "Relocation_Type_Id")
	@ApiModelProperty(notes="Deduction_Type_Id")
	private Integer deductionTypeId;
	
	@Column(name = "Relocation_Type_Description")
	@ApiModelProperty(notes="Direct Cost Type Description")
	private String directCostTypeDescription;
	
	@Column(name = "Relocation_Value")
	@ApiModelProperty(notes="Relocation Value")
	private Double relocationValue;
	
	@Column(name = "Relocation_Cost_1")
	@ApiModelProperty(notes="Relocation Cost 1")
	private Double relocationCost1;
	
	@Column(name = "Relocation_Cost_2")
	@ApiModelProperty(notes="Relocation Cost 2")
	private Double relocationCost2;
	
	@Column(name = "Relocation_Cost_3")
	@ApiModelProperty(notes="Relocation Cost 3")
	private Double relocationCost3;
	
	@Column(name = "Relocation_Cost_4")
	@ApiModelProperty(notes="Relocation Cost 4")
	private Double relocationCost4;
	
	@Column(name = "Relocation_Cost_5")
	@ApiModelProperty(notes="Relocation Cost 5")
	private Double relocationCost5;
	
	@Column(name = "Relocation_Cost_6")
	@ApiModelProperty(notes="Relocation Cost 6")
	private Double relocationCost6;
	
	@Column(name = "Relocation_Cost_7")
	@ApiModelProperty(notes="Relocation Cost 7")
	private Double relocationCost7;
	
	@Column(name = "Relocation_Cost_8")
	@ApiModelProperty(notes="Relocation Cost 8")
	private Double relocationCost8;
	
	@Column(name = "Relocation_Cost_9")
	@ApiModelProperty(notes="Relocation Cost 9")
	private Double relocationCost9;
	
	@Column(name = "Relocation_Cost_10")
	@ApiModelProperty(notes="Relocation Cost 10")
	private Double relocationCost10;
	
	@Column(name = "Relocation_Cost_11")
	@ApiModelProperty(notes="Relocation Cost 11")
	private Double relocationCost11;
	
	@Column(name = "Relocation_Cost_12")
	@ApiModelProperty(notes="Relocation Cost 12")
	private Double relocationCost12;
	
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


	public Integer getRelocationHeadCountDetailsId() {
		return relocationHeadCountDetailsId;
	}

	public void setRelocationHeadCountDetailsId(Integer relocationHeadCountDetailsId) {
		this.relocationHeadCountDetailsId = relocationHeadCountDetailsId;
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

	public Double getRelocationValue() {
		return relocationValue;
	}

	public void setRelocationValue(Double relocationValue) {
		this.relocationValue = relocationValue;
	}

	public Double getRelocationCost1() {
		return relocationCost1;
	}

	public void setRelocationCost1(Double relocationCost1) {
		this.relocationCost1 = relocationCost1;
	}

	public Double getRelocationCost2() {
		return relocationCost2;
	}

	public void setRelocationCost2(Double relocationCost2) {
		this.relocationCost2 = relocationCost2;
	}

	public Double getRelocationCost3() {
		return relocationCost3;
	}

	public void setRelocationCost3(Double relocationCost3) {
		this.relocationCost3 = relocationCost3;
	}

	public Double getRelocationCost4() {
		return relocationCost4;
	}

	public void setRelocationCost4(Double relocationCost4) {
		this.relocationCost4 = relocationCost4;
	}

	public Double getRelocationCost5() {
		return relocationCost5;
	}

	public void setRelocationCost5(Double relocationCost5) {
		this.relocationCost5 = relocationCost5;
	}

	public Double getRelocationCost6() {
		return relocationCost6;
	}

	public void setRelocationCost6(Double relocationCost6) {
		this.relocationCost6 = relocationCost6;
	}

	public Double getRelocationCost7() {
		return relocationCost7;
	}

	public void setRelocationCost7(Double relocationCost7) {
		this.relocationCost7 = relocationCost7;
	}

	public Double getRelocationCost8() {
		return relocationCost8;
	}

	public void setRelocationCost8(Double relocationCost8) {
		this.relocationCost8 = relocationCost8;
	}

	public Double getRelocationCost9() {
		return relocationCost9;
	}

	public void setRelocationCost9(Double relocationCost9) {
		this.relocationCost9 = relocationCost9;
	}

	public Double getRelocationCost10() {
		return relocationCost10;
	}

	public void setRelocationCost10(Double relocationCost10) {
		this.relocationCost10 = relocationCost10;
	}

	public Double getRelocationCost11() {
		return relocationCost11;
	}

	public void setRelocationCost11(Double relocationCost11) {
		this.relocationCost11 = relocationCost11;
	}

	public Double getRelocationCost12() {
		return relocationCost12;
	}

	public void setRelocationCost12(Double relocationCost12) {
		this.relocationCost12 = relocationCost12;
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
