package com.rightprice.auth.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class StaffingPercentage 
{
	public Integer getTempId() {
		return tempId;
	}

	public void setTempId(Integer tempId) {
		this.tempId = tempId;
	}

	public Double getTotalStaffing() {
		return totalStaffing;
	}

	public void setTotalStaffing(Double totalStaffing) {
		this.totalStaffing = totalStaffing;
	}

	public Double getTotalOffshore() {
		return totalOffshore;
	}

	public void setTotalOffshore(Double totalOffshore) {
		this.totalOffshore = totalOffshore;
	}

	@Id
	@Column(name="TEMP_ID")
	private Integer tempId;
	
	@Column(name="total_staffing")
	private Double totalStaffing;
	
	@Column(name="total_offshore")
	private Double totalOffshore;	


}
