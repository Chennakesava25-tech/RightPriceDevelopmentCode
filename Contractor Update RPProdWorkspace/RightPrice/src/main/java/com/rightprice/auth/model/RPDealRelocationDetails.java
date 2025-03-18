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
@ApiModel(value = "City", description = "City Model Attributes")
@Table(name = "synprod.RP_Deal_Relocation_Cost")
public class RPDealRelocationDetails  implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "DEAL_RELOCATION_COST_ID")
	@ApiModelProperty(notes = "City ID Auto Incremented")
	private Integer dealRelocationCostId;
	
	@Column(name = "RP_DEAL_VERSION_ID")
	@ApiModelProperty(notes = "versionId")
	private Integer rpDealVersionId;
	
	@Column(name = "RELOCATION_COST_ID")
	@ApiModelProperty(notes = "Relocation Cost ID ")
	private Integer relocationCostId;
	
	@Column(name = "Relocation_Description")
	@ApiModelProperty(notes = "Relocation Description")
	private String relocationDescription;
	
	@Column(name = "Category_Id")
	@ApiModelProperty(notes = "Category_Id")
	private Integer categoryId;
	
	@Column(name = "Year1_Quantity")
	@ApiModelProperty(notes = "Year 1 Quantity")
	private Integer year1Quantity;
	
	@Column(name = "Year2_Quantity")
	@ApiModelProperty(notes = "Year 2 Quantity")
	private Integer year2Quantity;
	
	@Column(name = "Year3_Quantity")
	@ApiModelProperty(notes = "Year 3 Quantity")
	private Integer year3Quantity;
	
	@Column(name = "Year4_Quantity")
	@ApiModelProperty(notes = "Year 4 Quantity")
	private Integer year4Quantity;
	
	@Column(name = "Year5_Quantity")
	@ApiModelProperty(notes = "Year 5 Quantity")
	private Integer year5Quantity;
	
	@Column(name = "Year6_Quantity")
	@ApiModelProperty(notes = "Year 6 Quantity")
	private Integer year6Quantity;
	
	@Column(name = "Year7_Quantity")
	@ApiModelProperty(notes = "Year 7 Quantity")
	private Integer year7Quantity;
	
	@Column(name = "Year8_Quantity")
	@ApiModelProperty(notes = "Year 8 Quantity")
	private Integer year8Quantity;
	
	@Column(name = "Year9_Quantity")
	@ApiModelProperty(notes = "Year 9 Quantity")
	private Integer year9Quantity;
	
	@Column(name = "Year10_Quantity")
	@ApiModelProperty(notes = "Year 10 Quantity")
	private Integer year10Quantity;
	
	
	@Column(name = "Year1_Cost")
	@ApiModelProperty(notes = "Year 1 Cost")
	private Double year1Cost;
	
	@Column(name = "Year2_Cost")
	@ApiModelProperty(notes = "Year 2 Cost")
	private Double year2Cost;
	
	@Column(name = "Year3_Cost")
	@ApiModelProperty(notes = "Year 3 Cost")
	private Double year3Cost;
	
	@Column(name = "Year4_Cost")
	@ApiModelProperty(notes = "Year 4 Cost")
	private Double year4Cost;
	
	@Column(name = "Year5_Cost")
	@ApiModelProperty(notes = "Year 5 Cost")
	private Double year5Cost;
	
	@Column(name = "Year6_Cost")
	@ApiModelProperty(notes = "Year 6 Cost")
	private Double year6Cost;
	
	@Column(name = "Year7_Cost")
	@ApiModelProperty(notes = "Year 7 Cost")
	private Double year7Cost;
	
	@Column(name = "Year8_Cost")
	@ApiModelProperty(notes = "Year 8 Cost")
	private Double year8Cost;
	
	@Column(name = "Year9_Cost")
	@ApiModelProperty(notes = "Year 9 Cost")
	private Double year9Cost;
	
	@Column(name = "Year10_Cost")
	@ApiModelProperty(notes = "Year 10 Cost")
	private Double year10Cost;
		
	public Double getYear6Cost() {
		return year6Cost;
	}

	public void setYear6Cost(Double year6Cost) {
		this.year6Cost = year6Cost;
	}

	public Double getYear7Cost() {
		return year7Cost;
	}

	public void setYear7Cost(Double year7Cost) {
		this.year7Cost = year7Cost;
	}

	public Double getYear8Cost() {
		return year8Cost;
	}

	public void setYear8Cost(Double year8Cost) {
		this.year8Cost = year8Cost;
	}

	public Double getYear9Cost() {
		return year9Cost;
	}

	public void setYear9Cost(Double year9Cost) {
		this.year9Cost = year9Cost;
	}

	public Double getYear10Cost() {
		return year10Cost;
	}

	public void setYear10Cost(Double year10Cost) {
		this.year10Cost = year10Cost;
	}

	@Column(name = "COUNTRY_ID")
	@ApiModelProperty(notes = "COUNTRY_ID")
	private Integer countryId;
	
	@Column(name = "CITY_ID")
	@ApiModelProperty(notes = "CITY_ID")
	private Integer cityId;
	
	@Column(name = "TOWER_ID")
	@ApiModelProperty(notes = "TOWER_ID")
	private Integer towerId;
	
	@Column(name = "IS_ACTIVE")
	@ApiModelProperty(notes = "Active status")
	private int isActive;

	@Column(name = "CREATED_BY", updatable = false)
	@ApiModelProperty(notes = "Created by")
	private String createdBy;

	@Column(name = "CREATED_ON", updatable = false)
	@ApiModelProperty(notes = "Created date")
	private String createdDate;

	@Column(name = "UPDATED_BY")
	@ApiModelProperty(notes = "Updated by")
	private String updatedBy;

	@Column(name = "UPDATED_ON")
	@ApiModelProperty(notes = "Updated date")
	private String updatedDate;

	public Integer getDealRelocationCostId() {
		return dealRelocationCostId;
	}

	public void setDealRelocationCostId(Integer dealRelocationCostId) {
		this.dealRelocationCostId = dealRelocationCostId;
	}

	public Integer getRpDealVersionId() {
		return rpDealVersionId;
	}

	public void setRpDealVersionId(Integer rpDealVersionId) {
		this.rpDealVersionId = rpDealVersionId;
	}

	public Integer getRelocationCostId() {
		return relocationCostId;
	}

	public void setRelocationCostId(Integer relocationCostId) {
		this.relocationCostId = relocationCostId;
	}

	public String getRelocationDescription() {
		return relocationDescription;
	}

	public void setRelocationDescription(String relocationDescription) {
		this.relocationDescription = relocationDescription;
	}

	public Integer getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

	public Integer getYear1Quantity() {
		return year1Quantity;
	}

	public void setYear1Quantity(Integer year1Quantity) {
		this.year1Quantity = year1Quantity;
	}

	public Integer getYear2Quantity() {
		return year2Quantity;
	}

	public void setYear2Quantity(Integer year2Quantity) {
		this.year2Quantity = year2Quantity;
	}

	public Integer getYear3Quantity() {
		return year3Quantity;
	}

	public void setYear3Quantity(Integer year3Quantity) {
		this.year3Quantity = year3Quantity;
	}

	public Integer getYear4Quantity() {
		return year4Quantity;
	}

	public void setYear4Quantity(Integer year4Quantity) {
		this.year4Quantity = year4Quantity;
	}

	public Integer getYear5Quantity() {
		return year5Quantity;
	}

	public void setYear5Quantity(Integer year5Quantity) {
		this.year5Quantity = year5Quantity;
	}

	public Integer getYear6Quantity() {
		return year6Quantity;
	}

	public void setYear6Quantity(Integer year6Quantity) {
		this.year6Quantity = year6Quantity;
	}

	public Integer getYear7Quantity() {
		return year7Quantity;
	}

	public void setYear7Quantity(Integer year7Quantity) {
		this.year7Quantity = year7Quantity;
	}

	public Integer getYear8Quantity() {
		return year8Quantity;
	}

	public void setYear8Quantity(Integer year8Quantity) {
		this.year8Quantity = year8Quantity;
	}

	public Integer getYear9Quantity() {
		return year9Quantity;
	}

	public void setYear9Quantity(Integer year9Quantity) {
		this.year9Quantity = year9Quantity;
	}

	public Integer getYear10Quantity() {
		return year10Quantity;
	}

	public void setYear10Quantity(Integer year10Quantity) {
		this.year10Quantity = year10Quantity;
	}
	
	public Double getYear1Cost() {
		return year1Cost;
	}

	public void setYear1Cost(Double year1Cost) {
		this.year1Cost = year1Cost;
	}

	public Double getYear2Cost() {
		return year2Cost;
	}

	public void setYear2Cost(Double year2Cost) {
		this.year2Cost = year2Cost;
	}

	public Double getYear3Cost() {
		return year3Cost;
	}

	public void setYear3Cost(Double year3Cost) {
		this.year3Cost = year3Cost;
	}

	public Double getYear4Cost() {
		return year4Cost;
	}

	public void setYear4Cost(Double year4Cost) {
		this.year4Cost = year4Cost;
	}

	public Double getYear5Cost() {
		return year5Cost;
	}

	public void setYear5Cost(Double year5Cost) {
		this.year5Cost = year5Cost;
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

	public Integer getTowerId() {
		return towerId;
	}

	public void setTowerId(Integer towerId) {
		this.towerId = towerId;
	}

	public int getIsActive() {
		return isActive;
	}

	public void setIsActive(int isActive) {
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
