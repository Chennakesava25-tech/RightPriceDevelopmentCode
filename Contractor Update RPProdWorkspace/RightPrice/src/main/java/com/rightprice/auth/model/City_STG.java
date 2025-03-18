
package com.rightprice.auth.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@Entity
@ApiModel(value = "City", description = "City Model Attributes")
@Table(name = "synprod.MST_RP_City_STG")


public class City_STG implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "CITY_ID")
	@ApiModelProperty(notes = "City ID Auto Incremented")
	private int cityId;

	@Column(name = "CITY_NAME")
	@ApiModelProperty(notes = "City Name")
	private String cityName;

	@Column(name = "CATEGORIZATION_ID")
	@ApiModelProperty(notes = "Categorization ID")
	private int categorizationId;

	@Column(name = "COUNTRY_ID")
	@ApiModelProperty(notes = "Country ID")
	private int countryId;
	
	@Column(name = "SYNTEL_PREMIUM")
	@ApiModelProperty(notes = "SYNTEL_PREMIUM")
	private Double syntelPremium;
	
	@OneToOne( cascade={CascadeType.ALL})
	@JoinColumn(name="COUNTRY_ID",referencedColumnName = "COUNTRY_ID", insertable=false, updatable=false)
	private Country country;
	
	@OneToOne(fetch = FetchType.LAZY, cascade = javax.persistence.CascadeType.ALL)
	@JoinColumn(name="CATEGORIZATION_ID",referencedColumnName = "CODE_NAME", insertable=false, updatable=false)
	private CityCategorization cityCategory;

	@Column(name = "COLA_VALUE")
	@ApiModelProperty(notes = "Cola Value")
	private double colaValue;

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
	
	@javax.persistence.Transient
	private String countryName;
	
	@javax.persistence.Transient
	private String categorization;

	public int getCityId() {
		return cityId;
	}

	public void setCityId(int cityId) {
		this.cityId = cityId;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public int getCategorizationId() {
		return categorizationId;
	}

	public void setCategorizationId(int categorizationId) {
		this.categorizationId = categorizationId;
	}

	public int getCountryId() {
		return countryId;
	}

	public void setCountryId(int countryId) {
		this.countryId = countryId;
	}

	public double getColaValue() {
		return colaValue;
	}

	public void setColaValue(double colaValue) {
		this.colaValue = colaValue;
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

	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	public CityCategorization getCityCategory() {
		return cityCategory;
	}

	public void setCityCategory(CityCategorization cityCategory) {
		this.cityCategory = cityCategory;
	}

	public Double getSyntelPremium() {
		return syntelPremium;
	}

	public void setSyntelPremium(Double syntelPremium) {
		this.syntelPremium = syntelPremium;
	}

	public String getCountryName() {
		return countryName;
	}

	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}

	public String getCategorization() {
		return categorization;
	}

	public void setCategorization(String categorization) {
		this.categorization = categorization;
	}
}
