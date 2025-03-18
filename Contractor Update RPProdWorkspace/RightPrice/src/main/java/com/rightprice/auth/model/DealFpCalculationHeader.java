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
@ApiModel(value="deal calculation header",description="RP Deal FP Calculation Header")
@Table(name = "synprod.RP_Deal_FP_Calculation_Header")
public class DealFpCalculationHeader {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "Calculation_Header_Id")
	@ApiModelProperty(notes="Calculation Header Id Auto Incremented")
	private Integer calculationHeaderId;
	
	@Column(name = "RP_Deal_Version_Id")
	@ApiModelProperty(notes="RP Deal Version Id")
	private Integer rpDealVersionId;
	
	@Column(name = "Deal_Tower_Id")
	@ApiModelProperty(notes="Deal Tower Id")
	private Integer dealTowerId;
	
	@Column(name = "Deal_Location_Id")
	@ApiModelProperty(notes="Deal Location Id")
	private Integer dealLocationId;
	
	@Column(name = "Country_Id")
	@ApiModelProperty(notes="Country Id")
	private Integer countryId;
	
	@Column(name = "City_Id")
	@ApiModelProperty(notes="City Id")
	private Integer cityId;
	
	@Column(name = "Header_Key1")
	@ApiModelProperty(notes="Month Year Header 1")
	private Integer headerKey1;
	
	@Column(name = "Header_Key2")
	@ApiModelProperty(notes="Month Year Header 1")
	private Integer headerKey2;
	
	@Column(name = "Header_Key3")
	@ApiModelProperty(notes="Month Year Header 1")
	private Integer headerKey3;
	
	@Column(name = "Header_Key4")
	@ApiModelProperty(notes="Month Year Header 1")
	private Integer headerKey4;
	
	@Column(name = "Header_Key5")
	@ApiModelProperty(notes="Month Year Header 1")
	private Integer headerKey5;
	
	@Column(name = "Month_Year_Header_1")
	@ApiModelProperty(notes="Month Year Header 1")
	private String monthYearHeader1;
	
	@Column(name = "Month_Year_Header_2")
	@ApiModelProperty(notes="Month Year Header 1")
	private String monthYearHeader2;
	
	@Column(name = "Month_Year_Header_3")
	@ApiModelProperty(notes="Month Year Header 1")
	private String monthYearHeader3;
	
	@Column(name = "Month_Year_Header_4")
	@ApiModelProperty(notes="Month Year Header 4")
	private String monthYearHeader4;
	
	@Column(name = "Month_Year_Header_5")
	@ApiModelProperty(notes="Month Year Header 5")
	private String monthYearHeader5;
	
	/*@Column(name = "Month_Year_Header_6")
	@ApiModelProperty(notes="Month Year Header 6")
	private String monthYearHeader6;
	
	public String getMonthYearHeader6() {
		return monthYearHeader6;
	}

	public void setMonthYearHeader6(String monthYearHeader6) {
		this.monthYearHeader6 = monthYearHeader6;
	}*/

	@Column(name = "Month_1")
	@ApiModelProperty(notes="Month 1")
	private String month1;
	
	@Column(name = "Month_2")
	@ApiModelProperty(notes="Month 2")
	private String month2;
	
	@Column(name = "Month_3")
	@ApiModelProperty(notes="Month 3")
	private String month3;
	
	@Column(name = "Month_4")
	@ApiModelProperty(notes="Month 4")
	private String month4;
	
	@Column(name = "Month_5")
	@ApiModelProperty(notes="Month 5")
	private String month5;
	
	@Column(name = "Month_6")
	@ApiModelProperty(notes="Month 6")
	private String month6;
	
	@Column(name = "Month_7")
	@ApiModelProperty(notes="Month 7")
	private String month7;
	
	@Column(name = "Month_8")
	@ApiModelProperty(notes="Month 8")
	private String month8;
	
	@Column(name = "Month_9")
	@ApiModelProperty(notes="Month 9")
	private String month9;
	
	@Column(name = "Month_10")
	@ApiModelProperty(notes="Month 10")
	private String month10;
	
	@Column(name = "Month_11")
	@ApiModelProperty(notes="Month 11")
	private String month11;
	
	@Column(name = "Month_12")
	@ApiModelProperty(notes="Month 12")
	private String month12;
	
	@Column(name = "IS_ACTIVE")
	@ApiModelProperty(notes="Active status")
	private Integer isActive;
	
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

	public Integer getCalculationHeaderId() {
		return calculationHeaderId;
	}

	public void setCalculationHeaderId(Integer calculationHeaderId) {
		this.calculationHeaderId = calculationHeaderId;
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

	public Integer getDealLocationId() {
		return dealLocationId;
	}

	public void setDealLocationId(Integer dealLocationId) {
		this.dealLocationId = dealLocationId;
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

	public Integer getHeaderKey1() {
		return headerKey1;
	}

	public void setHeaderKey1(Integer headerKey1) {
		this.headerKey1 = headerKey1;
	}

	public Integer getHeaderKey2() {
		return headerKey2;
	}

	public void setHeaderKey2(Integer headerKey2) {
		this.headerKey2 = headerKey2;
	}

	public Integer getHeaderKey3() {
		return headerKey3;
	}

	public void setHeaderKey3(Integer headerKey3) {
		this.headerKey3 = headerKey3;
	}

	public Integer getHeaderKey4() {
		return headerKey4;
	}

	public void setHeaderKey4(Integer headerKey4) {
		this.headerKey4 = headerKey4;
	}

	public Integer getHeaderKey5() {
		return headerKey5;
	}

	public void setHeaderKey5(Integer headerKey5) {
		this.headerKey5 = headerKey5;
	}

	public String getMonthYearHeader1() {
		return monthYearHeader1;
	}

	public void setMonthYearHeader1(String monthYearHeader1) {
		this.monthYearHeader1 = monthYearHeader1;
	}

	public String getMonthYearHeader2() {
		return monthYearHeader2;
	}

	public void setMonthYearHeader2(String monthYearHeader2) {
		this.monthYearHeader2 = monthYearHeader2;
	}

	public String getMonthYearHeader3() {
		return monthYearHeader3;
	}

	public void setMonthYearHeader3(String monthYearHeader3) {
		this.monthYearHeader3 = monthYearHeader3;
	}

	public String getMonthYearHeader4() {
		return monthYearHeader4;
	}

	public void setMonthYearHeader4(String monthYearHeader4) {
		this.monthYearHeader4 = monthYearHeader4;
	}

	public String getMonthYearHeader5() {
		return monthYearHeader5;
	}

	public void setMonthYearHeader5(String monthYearHeader5) {
		this.monthYearHeader5 = monthYearHeader5;
	}

	public String getMonth1() {
		return month1;
	}

	public void setMonth1(String month1) {
		this.month1 = month1;
	}

	public String getMonth2() {
		return month2;
	}

	public void setMonth2(String month2) {
		this.month2 = month2;
	}

	public String getMonth3() {
		return month3;
	}

	public void setMonth3(String month3) {
		this.month3 = month3;
	}

	public String getMonth4() {
		return month4;
	}

	public void setMonth4(String month4) {
		this.month4 = month4;
	}

	public String getMonth5() {
		return month5;
	}

	public void setMonth5(String month5) {
		this.month5 = month5;
	}

	public String getMonth6() {
		return month6;
	}

	public void setMonth6(String month6) {
		this.month6 = month6;
	}

	public String getMonth7() {
		return month7;
	}

	public void setMonth7(String month7) {
		this.month7 = month7;
	}

	public String getMonth8() {
		return month8;
	}

	public void setMonth8(String month8) {
		this.month8 = month8;
	}

	public String getMonth9() {
		return month9;
	}

	public void setMonth9(String month9) {
		this.month9 = month9;
	}

	public String getMonth10() {
		return month10;
	}

	public void setMonth10(String month10) {
		this.month10 = month10;
	}

	public String getMonth11() {
		return month11;
	}

	public void setMonth11(String month11) {
		this.month11 = month11;
	}

	public String getMonth12() {
		return month12;
	}

	public void setMonth12(String month12) {
		this.month12 = month12;
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
}
