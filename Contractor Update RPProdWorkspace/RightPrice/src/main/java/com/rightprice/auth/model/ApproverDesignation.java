package com.rightprice.auth.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ApproverDesignation {

	/*
	@Column(name = "RiskManagers")
	private String RiskManagersApprover;*/
	@Id
	@Column(name = "DELIVERY")
	private String deliveryApprover;
	
	@Column(name = "BUH_HEAD")
	private String buHeadApprover;
	
	@Column(name = "DELIVERY_DIRECTOR")
	private String deliveryDirector;
	
	@Column(name = "CEO")
	private String ceoApprover;
	
	@Column(name = "CDO")
	private String cdoApprover;
	
	@Column(name = "Level 1")
	private String level1CeoApprover;
	
	@Column(name = "Level 2")
	private String level2CeoApprover;

	/*public String getRiskManagersApprover() {
		return RiskManagersApprover;
	}

	public void setRiskManagersApprover(String RiskManagersApprover) {
		this.RiskManagersApprover = RiskManagersApprover;
	}
*/
	public String getDeliveryApprover() {
		return deliveryApprover;
	}

	public void setDeliveryApprover(String deliveryApprover) {
		this.deliveryApprover = deliveryApprover;
	}

	public String getBuHeadApprover() {
		return buHeadApprover;
	}

	public void setBuHeadApprover(String buHeadApprover) {
		this.buHeadApprover = buHeadApprover;
	}

	public String getDeliveryDirector() {
		return deliveryDirector;
	}

	public void setDeliveryDirector(String deliveryDirector) {
		this.deliveryDirector = deliveryDirector;
	}

	public String getCeoApprover() {
		return ceoApprover;
	}

	public void setCeoApprover(String ceoApprover) {
		this.ceoApprover = ceoApprover;
	}

	public String getCdoApprover() {
		return cdoApprover;
	}

	public void setCdoApprover(String cdoApprover) {
		this.cdoApprover = cdoApprover;
	}
}
