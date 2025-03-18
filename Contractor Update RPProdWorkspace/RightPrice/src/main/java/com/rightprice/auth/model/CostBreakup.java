package com.rightprice.auth.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import io.swagger.annotations.ApiModelProperty;

@Entity
@Table(name = "synprod.RP_Fix_deal_Role_Wise_Cost_BreakUp")
public class CostBreakup implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	@ApiModelProperty(notes = "Id")
	private Integer id;

	@Column(name = "CRM_DEAL_ID")
	@ApiModelProperty(notes = "Deal_Id")
	private String dealId;

	@Column(name = "RP_Deal_Version_Id")
	@ApiModelProperty(notes = "Location")
	private Integer versionId;

	@Column(name = "Cost_Code")
	@ApiModelProperty(notes = "Cost_Code")
	private Integer CostCode;
	
	@Column(name = "Cost_type")
	@ApiModelProperty(notes = "Cost_type")
	private String CostType;
	
/*	@Column(name = "Deduction_Category_ID")
	@ApiModelProperty(notes = "Deduction_Category_ID")
	private Integer deductionCategoryID;
	
	@Column(name = "Deduction_Type_ID")
	@ApiModelProperty(notes = "Deduction_Type_ID")
	private Integer deductionTypeID;
	
	@Column(name = "Tower_id")
	@ApiModelProperty(notes = "Tower_id")
	private Integer TowerId;*/
	
/*	@Column(name = "Country_Id")
	@ApiModelProperty(notes = "Country_Id")
	private Integer countryId;
	
	@Column(name = "City_Id")
	@ApiModelProperty(notes = "City_Id")
	private Integer cityId;
	
	@Column(name = "visa_id")
	@ApiModelProperty(notes = "visa_id")
	private Integer visaId;
	*/
	@Column(name = "Transition_Cost")
	@ApiModelProperty(notes = "Transition_Cost")
	private Float Transistion;
	
	@Column(name = "YEAR_1")
	@ApiModelProperty(notes = "NO_OF_RESOURCES")
	private Float YEAR_1;
	
	@Column(name = "YEAR_2")
	@ApiModelProperty(notes = "NO_OF_RESOURCES")
	private Float YEAR_2;
	
	@Column(name = "YEAR_3")
	@ApiModelProperty(notes = "NO_OF_RESOURCES")
	private Float YEAR_3;
	
	@Column(name = "YEAR_4")
	@ApiModelProperty(notes = "NO_OF_RESOURCES")
	private Float YEAR_4;
	
	@Column(name = "YEAR_5")
	@ApiModelProperty(notes = "NO_OF_RESOURCES")
	private Float YEAR_5;
	
	@Column(name = "YEAR_6")
	@ApiModelProperty(notes = "NO_OF_RESOURCES")
	private Float YEAR_6;
	
	@Column(name = "YEAR_7")
	@ApiModelProperty(notes = "NO_OF_RESOURCES")
	private Float YEAR_7;
	
	@Column(name = "YEAR_8")
	@ApiModelProperty(notes = "NO_OF_RESOURCES")
	private Float YEAR_8;
	
	@Column(name = "YEAR_9")
	@ApiModelProperty(notes = "NO_OF_RESOURCES")
	private Float YEAR_9;
	
	@Column(name = "YEAR_10")
	@ApiModelProperty(notes = "NO_OF_RESOURCES")
	private Float YEAR_10;
	
	@Column(name = "row_sequence_number")
	@ApiModelProperty(notes = "row_sequence_number")
	private Integer rowSequenceNumber;
	
	@Column(name = "Year_total")
	@ApiModelProperty(notes = "Year_total")
	private Double yearTotal;
	
	@Column(name = "Total_indicator")
	@ApiModelProperty(notes = "Total_indicator")
	private Integer TotalIndicator;
	
	@Column(name = "IS_ACTIVE")
	@ApiModelProperty(notes = "active status")
	private Integer isActive;

	@Column(name = "CREATED_BY", updatable = false)
	@ApiModelProperty(notes = "Created by")
	private String createdBy;

	@Column(name = "CREATED_ON", updatable = false)
	@ApiModelProperty(notes = "Created date")
	private String createdOn;

	@Column(name = "UPDATED_BY")
	@ApiModelProperty(notes = "Updated by")
	private String updatedBy;

	@Column(name = "UPDATED_ON")
	@ApiModelProperty(notes = "Updated date")
	private String updatedOn;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}


	public String getDealId() {
		return dealId;
	}

	public void setDealId(String dealId) {
		this.dealId = dealId;
	}

	public Integer getVersionId() {
		return versionId;
	}

	public void setVersionId(Integer versionId) {
		this.versionId = versionId;
	}

	public Integer getCostCode() {
		return CostCode;
	}

	public void setCostCode(Integer costCode) {
		CostCode = costCode;
	}

	public String getCostType() {
		return CostType;
	}

	public void setCostType(String costType) {
		CostType = costType;
	}

	/*public Integer getDeductionCategoryID() {
		return deductionCategoryID;
	}

	public void setDeductionCategoryID(Integer deductionCategoryID) {
		this.deductionCategoryID = deductionCategoryID;
	}

	public Integer getDeductionTypeID() {
		return deductionTypeID;
	}

	public void setDeductionTypeID(Integer deductionTypeID) {
		this.deductionTypeID = deductionTypeID;
	}

	public Integer getTowerId() {
		return TowerId;
	}

	public void setTowerId(Integer towerId) {
		TowerId = towerId;
	}*/

/*	public Integer getCountryId() {
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
	}*/

	public Float getYEAR_1() {
		return YEAR_1;
	}

	public Float getTransistion() {
		return Transistion;
	}

	public void setTransistion(Float transistion) {
		Transistion = transistion;
	}

	public void setYEAR_1(Float yEAR_1) {
		YEAR_1 = yEAR_1;
	}

	public Float getYEAR_2() {
		return YEAR_2;
	}

	public void setYEAR_2(Float yEAR_2) {
		YEAR_2 = yEAR_2;
	}

	public Float getYEAR_3() {
		return YEAR_3;
	}

	public void setYEAR_3(Float yEAR_3) {
		YEAR_3 = yEAR_3;
	}

	public Float getYEAR_4() {
		return YEAR_4;
	}

	public void setYEAR_4(Float yEAR_4) {
		YEAR_4 = yEAR_4;
	}

	public Float getYEAR_5() {
		return YEAR_5;
	}

	public void setYEAR_5(Float yEAR_5) {
		YEAR_5 = yEAR_5;
	}

	public Float getYEAR_6() {
		return YEAR_6;
	}

	public void setYEAR_6(Float yEAR_6) {
		YEAR_6 = yEAR_6;
	}

	public Float getYEAR_7() {
		return YEAR_7;
	}

	public void setYEAR_7(Float yEAR_7) {
		YEAR_7 = yEAR_7;
	}

	public Float getYEAR_8() {
		return YEAR_8;
	}

	public void setYEAR_8(Float yEAR_8) {
		YEAR_8 = yEAR_8;
	}

	public Float getYEAR_9() {
		return YEAR_9;
	}

	public void setYEAR_9(Float yEAR_9) {
		YEAR_9 = yEAR_9;
	}

	public Float getYEAR_10() {
		return YEAR_10;
	}

	public void setYEAR_10(Float yEAR_10) {
		YEAR_10 = yEAR_10;
	}

	public Integer getRowSequenceNumber() {
		return rowSequenceNumber;
	}

	public void setRowSequenceNumber(Integer rowSequenceNumber) {
		this.rowSequenceNumber = rowSequenceNumber;
	}

	public Double getYearTotal() {
		return yearTotal;
	}

	public void setYearTotal(Double yearTotal) {
		this.yearTotal = yearTotal;
	}

	public Integer getTotalIndicator() {
		return TotalIndicator;
	}

	public void setTotalIndicator(Integer totalIndicator) {
		TotalIndicator = totalIndicator;
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