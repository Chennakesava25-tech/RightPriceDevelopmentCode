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
@ApiModel(value="City",description="City Model Attributes")
@Table(name = "synprod.MST_RP_City")
public class ViewCitiesData {

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	
	@Column(name = "CITY_ID")
	@ApiModelProperty(notes="city Id")
	private int cityId;
	
	@Column(name = "CITY_NAME")
	@ApiModelProperty(notes="city Name")
	private String cityName;
	
	@Column(name = "CATEGORIZATION_ID")
	@ApiModelProperty(notes="categorization Id")
	private int categorizationId;
	
	@Column(name = "COUNTRY_ID")
	@ApiModelProperty(notes="Country ID")
	private int countryId;

	@Column(name = "COLA_VALUE")
	@ApiModelProperty(notes="Cola Value")
	private int colaValue;
	
	@Column(name = "IS_ACTIVE")
	@ApiModelProperty(notes="Is Active")
	private int isActive;
	
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
	
	
	public int getCityId() {
		return cityId;
	}

	public void setCityId(int cityId) {
		this.cityId = cityId;
	}

	public int getCategorizationId() {
		return categorizationId;
	}

	public void setCategorizationId(int categorizationId) {
		this.categorizationId = categorizationId;
	}

	public int getColaValue() {
		return colaValue;
	}

	public void setColaValue(int colaValue) {
		this.colaValue = colaValue;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	

	public int getCountryId() {
		return countryId;
	}

	public void setCountryId(int countryId) {
		this.countryId = countryId;
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

	public int getIsActive() {
		return isActive;
	}

	public void setIsActive(int isActive) {
		this.isActive = isActive;
	}


}
