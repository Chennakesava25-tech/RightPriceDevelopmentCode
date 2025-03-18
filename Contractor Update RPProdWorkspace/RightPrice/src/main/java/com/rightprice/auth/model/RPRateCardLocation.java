package com.rightprice.auth.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class RPRateCardLocation {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "RATE_CARD_LOCATION_ID")
	private int rateCardLocationId;
	
	@Column(name = "RATE_CARD_ID")
	private int rateCardId;
	
	@Column(name = "CITY_ID")
	private int cityId;
	
	@Column(name = "CITY_RESOURCE_UTILIZATION")
	private double cityResourceUtilization;

	@Column(name = "IS_ACTIVE")
	private double isActive;

}
