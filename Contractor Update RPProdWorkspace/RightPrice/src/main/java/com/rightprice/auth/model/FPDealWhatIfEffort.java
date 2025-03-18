package com.rightprice.auth.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@Entity
@ApiModel(value = "FPDeal What if Efforts In person months", description = "FPDeal What if Effort Details")
@Table(name = "synprod.RP_Deal_Efforts_In_person_months")
public class FPDealWhatIfEffort implements Serializable {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7283442150971435316L;

	@Column(name = "RP_Deal_Version_Id")
	private Integer rpDealVersionId;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "location_description")
	private String locationDescription;
	
	@Column(name = "location_type_ID")
	private Integer locationTypeID;
	
	@Column(name = "Year1_Efforts")
	private Double year1Efforts;
	
	@Column(name = "Year2_Efforts")
	private Double year2Efforts;
	
	@Column(name = "Year3_Efforts")
	private Double year3Efforts;
	
	@Column(name = "Year4_Efforts")
	private Double year4Efforts;
	
	@Column(name = "Year5_Efforts")
	private Double year5Efforts;
	
	@Column(name = "Year6_Efforts")
	private Double year6Efforts;
	
	@Column(name = "Year7_Efforts")
	private Double year7Efforts;
	
	@Column(name = "Year8_Efforts")
	private Double year8Efforts;
	
	@Column(name = "Year9_Efforts")
	private Double year9Efforts;
	
	@Column(name = "Year10_Efforts")
	private Double year10Efforts;
	
	@Column(name = "Year11_Efforts")
	private Double year11Efforts;
	
	@Column(name = "Year12_Efforts")
	private Double year12Efforts;
	
	@Column(name = "Efforts_total")
	private Double effortsTotal;
	
	@Column(name = "Sum_total_Type")
	private Integer sumTotalType;
	
	@Column(name = "Is_Active")
	private Integer isActive;
	
	@Column(name = "Created_By")
	private String createdBy;
	
	@Column(name = "Created_On")
	private String createdOn;
	
	@Column(name = "Updated_By")
	private String updatedBy;
	
	@Column(name = "Updated_On")
	private String updatedOn;
	
	@Column(name = "percent_indicator")
	private Integer percentIndicator;
	
	public Integer getRpDealVersionId() {
		return rpDealVersionId;
	}

	public void setRpDealVersionId(Integer rpDealVersionId) {
		this.rpDealVersionId = rpDealVersionId;
	}

	public String getLocationDescription() {
		return locationDescription;
	}

	public void setLocationDescription(String locationDescription) {
		this.locationDescription = locationDescription;
	}

	public Integer getLocationTypeID() {
		return locationTypeID;
	}

	public void setLocationTypeID(Integer locationTypeID) {
		this.locationTypeID = locationTypeID;
	}

	public Double getYear1Efforts() {
		return year1Efforts;
	}

	public void setYear1Efforts(Double year1Efforts) {
		this.year1Efforts = year1Efforts;
	}

	public Double getYear2Efforts() {
		return year2Efforts;
	}

	public void setYear2Efforts(Double year2Efforts) {
		this.year2Efforts = year2Efforts;
	}

	public Double getYear3Efforts() {
		return year3Efforts;
	}

	public void setYear3Efforts(Double year3Efforts) {
		this.year3Efforts = year3Efforts;
	}

	public Double getYear4Efforts() {
		return year4Efforts;
	}

	public void setYear4Efforts(Double year4Efforts) {
		this.year4Efforts = year4Efforts;
	}

	public Double getYear5Efforts() {
		return year5Efforts;
	}

	public void setYear5Efforts(Double year5Efforts) {
		this.year5Efforts = year5Efforts;
	}

	public Double getYear6Efforts() {
		return year6Efforts;
	}

	public void setYear6Efforts(Double year6Efforts) {
		this.year6Efforts = year6Efforts;
	}

	public Double getYear7Efforts() {
		return year7Efforts;
	}

	public void setYear7Efforts(Double year7Efforts) {
		this.year7Efforts = year7Efforts;
	}

	public Double getYear8Efforts() {
		return year8Efforts;
	}

	public void setYear8Efforts(Double year8Efforts) {
		this.year8Efforts = year8Efforts;
	}

	public Double getYear9Efforts() {
		return year9Efforts;
	}

	public void setYear9Efforts(Double year9Efforts) {
		this.year9Efforts = year9Efforts;
	}

	public Double getYear10Efforts() {
		return year10Efforts;
	}

	public void setYear10Efforts(Double year10Efforts) {
		this.year10Efforts = year10Efforts;
	}

	public Double getYear11Efforts() {
		return year11Efforts;
	}

	public void setYear11Efforts(Double year11Efforts) {
		this.year11Efforts = year11Efforts;
	}

	public Double getYear12Efforts() {
		return year12Efforts;
	}

	public void setYear12Efforts(Double year12Efforts) {
		this.year12Efforts = year12Efforts;
	}

	public Double getEffortsTotal() {
		return effortsTotal;
	}

	public void setEffortsTotal(Double effortsTotal) {
		this.effortsTotal = effortsTotal;
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

	public Integer getPercentIndicator() {
		return percentIndicator;
	}

	public void setPercentIndicator(Integer percentIndicator) {
		this.percentIndicator = percentIndicator;
	}


}
