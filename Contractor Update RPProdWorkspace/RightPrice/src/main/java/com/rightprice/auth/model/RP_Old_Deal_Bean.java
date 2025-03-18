package com.rightprice.auth.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class RP_Old_Deal_Bean {
	
	
	@Id	
	@Column(name="CRM_Deal_ID")
	private String crmDealId;

	public String getCrmDealId() {
		return crmDealId;
	}

	public void setCrmDealId(String crmDealId) {
		this.crmDealId = crmDealId;
	}
	

	
	
		

}
