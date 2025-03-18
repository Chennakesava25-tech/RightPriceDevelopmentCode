package com.rightprice.auth.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "synprod.RP_RATE_CARD_DETAILS")
public class RateCardData {

	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "RC_ID")
	private Integer rcId;
	
	@Column(name = "RC_Name")
	private String rcName;


	public Integer getRcId() {
		return rcId;
	}

	public void setRcId(Integer rcId) {
		this.rcId = rcId;
	}

	public String getRcName() {
		return rcName;
	}

	public void setRcName(String rcName) {
		this.rcName = rcName;
	}

}
