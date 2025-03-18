package com.rightprice.auth.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class FpExchangeRates {
	
	
	@Id
	@Column(name="CURRENCY_ID")
	private int currencyId;
	
	@Column(name="CURRENCY_CODE")
	private String currencyCode;
	
	@Column(name = "EXCHANGE_RATE")
	private Double exchangeRate;

	public int getCurrencyId() {
		return currencyId;
	}

	public void setCurrencyId(int currencyId) {
		this.currencyId = currencyId;
	}

	public String getCurrencyCode() {
		return currencyCode;
	}

	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}

	public Double getExchangeRate() {
		return exchangeRate;
	}

	public void setExchangeRate(Double exchangeRate) {
		this.exchangeRate = exchangeRate;
	}
	
	
	
	
	
	

}
