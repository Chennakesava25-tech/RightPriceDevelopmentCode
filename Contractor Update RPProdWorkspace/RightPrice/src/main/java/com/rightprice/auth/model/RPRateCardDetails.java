package com.rightprice.auth.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import io.swagger.annotations.ApiModelProperty;



@Entity
public class RPRateCardDetails {
	
	
	@Id
	@Column(name="RC_Id")
	private int rcId;
	
	@Column(name="RC_Name")
	private String rcName;
	
	
	@Column(name = "Is_Atos")
	private Integer isAtos;
	
	public int getRcId() {
		return rcId;
	}

	public void setRcId(int rcId) {
		this.rcId = rcId;
	}

	public String getRcName() {
		return rcName;
	}

	public void setRcName(String rcName) {
		this.rcName = rcName;
	}

	public Integer getIsAtos() {
		return isAtos;
	}

	public void setIsAtos(Integer isAtos) {
		this.isAtos = isAtos;
	}
	
	


}
