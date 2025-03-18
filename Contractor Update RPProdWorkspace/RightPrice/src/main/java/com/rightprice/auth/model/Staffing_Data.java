package com.rightprice.auth.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Staffing_Data {
	@Id
	@Column(name="city_id")
	private Integer cityId;
	
	@Column(name="Country_ID")
	private Integer countryId;
	
	@Column(name="City_Name")
	private String cityName;
	
	@Column(name="Country_Name")
	private String countryName;
	
	@Column(name="City_Resource_Utilization")
	private Double utilization;
	
	public Integer getCityId() {
		return cityId;
	}
	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}
	public Integer getCountryId() {
		return countryId;
	}
	public void setCountryId(Integer countryId) {
		this.countryId = countryId;
	}
	public String getCityName() {
		return cityName;
	}
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	public String getCountryName() {
		return countryName;
	}
	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}
	public Double getUtilization() {
		return utilization;
	}
	public void setUtilization(Double utilization) {
		this.utilization = utilization;
	}
	
}
