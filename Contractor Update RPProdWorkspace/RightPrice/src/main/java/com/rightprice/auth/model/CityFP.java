package com.rightprice.auth.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class CityFP {
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "City_Id")
	private int cityId;
	
	@Column(name = "City_Name")
	private String cityName;

	public int getCityId() {
		return cityId;
	}

	public void setCityId(int CityId) {
		this.cityId = cityId;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String CityName) {
		this.cityName = cityName;
	}
	
	
}

