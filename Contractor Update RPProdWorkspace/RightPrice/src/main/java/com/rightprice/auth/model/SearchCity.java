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
/*@Table(name = "synprod.MST_RP_City")*/
public class SearchCity {

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "CITY_NAME")
	@ApiModelProperty(notes="City Name")
	private String cityName;
	
	@Column(name = "CITY_ID")
	@ApiModelProperty(notes="City ID")
	private int cityId;
	
	@Column(name = "COLA_VALUE")
	@ApiModelProperty(notes="Cola Value")
	private int colaValue;

	@Column(name = "COUNTRY_ID")
	@ApiModelProperty(notes="Country ID ")
	private int countryId;
	
	@Column(name = "COUNTRY_NAME")
	@ApiModelProperty(notes="Country Name ")
	private String countryName;
	
	@Column(name = "DESCRIPTION")
	@ApiModelProperty(notes="Country Name ")
	private String description;
	
	/*@Column(name = "IS_ACTIVE")
	@ApiModelProperty(notes="Is Active")
	private int IsActive;
	*/
	/*public int getIsActive() {
		return IsActive;
	}

	public void setIsActive(int isActive) {
		IsActive = isActive;
	}*/

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public int getCityId() {
		return cityId;
	}

	public void setCityId(int cityId) {
		this.cityId = cityId;
	}
	
	public int getColaValue() {
		return colaValue;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setColaValue(int colaValue) {
		this.colaValue = colaValue;
	}
	
	public int getCountryId() {
		return countryId;
	}

	public void setCountryId(int countryId) {
		this.countryId = countryId;
	}

	public String getCountryName() {
		return countryName;
	}

	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}

	/*public int getCategorizationId() {
		return categorizationId;
	}

	public void setCategorizationId(int categorizationId) {
		this.categorizationId = categorizationId;
	}*/

}

