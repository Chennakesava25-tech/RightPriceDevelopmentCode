package com.rightprice.auth.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class RPRateCardYOYIncrementPercent {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "RC_YOY_ID")
	private int rcYoyId;
	
	@Column(name = "RC_ID")
	private int rcId;
	
	@Column(name = "YOY_INC_START_MONTH")
	private String yoyIncStartMonth;
	
	@Column(name = "INCREMENT_PERCENT_ONSITE")
	private double incrementPercentOnsite;
	
	@Column(name = "INCREMENT_PERCENT_OFFSHORE")
	private double incrementPercentOffshore;
	
	@Column(name = "IS_ACTIVE")
	private boolean isActive;

	public int getRcYoyId() {
		return rcYoyId;
	}

	public void setRcYoyId(int rcYoyId) {
		this.rcYoyId = rcYoyId;
	}

	public int getRcId() {
		return rcId;
	}

	public void setRcId(int rcId) {
		this.rcId = rcId;
	}

	public String getYoyIncStartMonth() {
		return yoyIncStartMonth;
	}

	public void setYoyIncStartMonth(String yoyIncStartMonth) {
		this.yoyIncStartMonth = yoyIncStartMonth;
	}

	public double getIncrementPercentOnsite() {
		return incrementPercentOnsite;
	}

	public void setIncrementPercentOnsite(double incrementPercentOnsite) {
		this.incrementPercentOnsite = incrementPercentOnsite;
	}

	public double getIncrementPercentOffshore() {
		return incrementPercentOffshore;
	}

	public void setIncrementPercentOffshore(double incrementPercentOffshore) {
		this.incrementPercentOffshore = incrementPercentOffshore;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

}
