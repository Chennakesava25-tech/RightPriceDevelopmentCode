package com.rightprice.auth.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import io.swagger.annotations.ApiModelProperty;

@Entity
@Table(name = "[synprod].[RP_Deal_Manual_SSS_YearData_STG]")
public class RPDealManualSSSYearDataSTG 
{
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "[RP_Deal_Manual_SSS_YearData_Id")
	private Integer RPDealManulaSSSYrDataId;
	
	@Column(name = "RP_Deal_Manual_SSS_Role_Id")
	@ApiModelProperty(notes="RPDealManualSSSRoleId")
	private Integer RPDealManualSSSRoleId;
	
	@Column(name = "RP_Deal_Version_Id")
	@ApiModelProperty(notes="RPDealVersionId")
	private Integer RPDealVersionId;
	
	@Column(name = "Tower_Id")
	@ApiModelProperty(notes="towerId")
	private Integer towerId;
	
	@Column(name = "Year")
	@ApiModelProperty(notes="year")
	private Integer year;
	
	@Column(name = "Month1")
	@ApiModelProperty(notes="month1")
	private Double month1;
	
	@Column(name = "Month2")
	@ApiModelProperty(notes="month2")
	private Double month2;
	
	@Column(name = "Month3")
	@ApiModelProperty(notes="month3")
	private Double month3;
	
	@Column(name = "Month4")
	@ApiModelProperty(notes="month4")
	private Double month4;
	
	@Column(name = "Month5")
	@ApiModelProperty(notes="month5")
	private Double month5;
	
	@Column(name = "Month6")
	@ApiModelProperty(notes="month6")
	private Double month6;
	
	@Column(name = "Month7")
	@ApiModelProperty(notes="month7")
	private Double month7;
	
	@Column(name = "Month8")
	@ApiModelProperty(notes="month8")
	private Double month8;
	
	@Column(name = "Month9")
	@ApiModelProperty(notes="month9")
	private Double month9;
	
	@Column(name = "Month10")
	@ApiModelProperty(notes="month10")
	private Double month10;
	
	@Column(name = "Month11")
	@ApiModelProperty(notes="month11")
	private Double month11;
	
	@Column(name = "Month12")
	@ApiModelProperty(notes="month12")
	private Double month12;
	
	@Column(name = "Visa_Id")
	@ApiModelProperty(notes="visa Id")
	private Integer visa_Id;
		
	@Column(name = "CREATED_BY", updatable=false)
	@ApiModelProperty(notes="Created by")
	private String createdBy;
	
	@Column(name = "CREATED_ON", updatable=false)
	@ApiModelProperty(notes="Created On")
	private String createdOn;
	
	@Column(name = "UPDATED_BY")
	@ApiModelProperty(notes="Updated by")
	private String updatedBy;
	
	@Column(name = "UPDATED_ON")
	@ApiModelProperty(notes="Updated On")
	private String updatedOn;

	public Integer getRPDealManulaSSSYrDataId() {
		return RPDealManulaSSSYrDataId;
	}

	public void setRPDealManulaSSSYrDataId(Integer rPDealManulaSSSYrDataId) {
		RPDealManulaSSSYrDataId = rPDealManulaSSSYrDataId;
	}

	public Integer getRPDealManualSSSRoleId() {
		return RPDealManualSSSRoleId;
	}

	public void setRPDealManualSSSRoleId(Integer rPDealManualSSSRoleId) {
		RPDealManualSSSRoleId = rPDealManualSSSRoleId;
	}

	public Integer getRPDealVersionId() {
		return RPDealVersionId;
	}

	public void setRPDealVersionId(Integer rPDealVersionId) {
		RPDealVersionId = rPDealVersionId;
	}

	public Integer getTowerId() {
		return towerId;
	}

	public void setTowerId(Integer towerId) {
		this.towerId = towerId;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public Double getMonth1() {
		return month1;
	}

	public void setMonth1(Double month1) {
		this.month1 = month1;
	}

	public Double getMonth2() {
		return month2;
	}

	public void setMonth2(Double month2) {
		this.month2 = month2;
	}

	public Double getMonth3() {
		return month3;
	}

	public void setMonth3(Double month3) {
		this.month3 = month3;
	}

	public Double getMonth4() {
		return month4;
	}

	public void setMonth4(Double month4) {
		this.month4 = month4;
	}

	public Double getMonth5() {
		return month5;
	}

	public void setMonth5(Double month5) {
		this.month5 = month5;
	}

	public Double getMonth6() {
		return month6;
	}

	public void setMonth6(Double month6) {
		this.month6 = month6;
	}

	public Double getMonth7() {
		return month7;
	}

	public void setMonth7(Double month7) {
		this.month7 = month7;
	}

	public Double getMonth8() {
		return month8;
	}

	public void setMonth8(Double month8) {
		this.month8 = month8;
	}

	public Double getMonth9() {
		return month9;
	}

	public void setMonth9(Double month9) {
		this.month9 = month9;
	}

	public Double getMonth10() {
		return month10;
	}

	public void setMonth10(Double month10) {
		this.month10 = month10;
	}

	public Double getMonth11() {
		return month11;
	}

	public void setMonth11(Double month11) {
		this.month11 = month11;
	}

	public Double getMonth12() {
		return month12;
	}

	public void setMonth12(Double month12) {
		this.month12 = month12;
	}

	public Integer getVisa_Id() {
		return visa_Id;
	}

	public void setVisa_Id(Integer visa_Id) {
		this.visa_Id = visa_Id;
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
