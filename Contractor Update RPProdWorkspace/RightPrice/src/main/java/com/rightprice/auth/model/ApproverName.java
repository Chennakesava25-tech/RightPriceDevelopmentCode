package com.rightprice.auth.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ApproverName {
	
	@Id
	@Column(name = "DELIVERY_HEAD_ID")
	private String deliveryHeadId;
	
	@Column(name = "DELIVERY_HEAD_NAME")
	private String deliveryHeadName;
	
	@Column(name = "BU_HEAD_ID")
	private String buHeadId;
	
	@Column(name = "BU_HEAD_NAME")
	private String buHeadName;
	
	/*@Column(name = "RiskManagers_PERSON_ID")
	private String RiskManagersPersonId;
	
	@Column(name = "QA_PERSONAL_NAME")
	private String qaPersonalName;*/
	
	public String getDeliveryHeadId() {
		return deliveryHeadId;
	}

	public void setDeliveryHeadId(String deliveryHeadId) {
		this.deliveryHeadId = deliveryHeadId;
	}

	public String getDeliveryHeadName() {
		return deliveryHeadName;
	}

	public void setDeliveryHeadName(String deliveryHeadName) {
		this.deliveryHeadName = deliveryHeadName;
	}

	public String getBuHeadId() {
		return buHeadId;
	}

	public void setBuHeadId(String buHeadId) {
		this.buHeadId = buHeadId;
	}

	public String getBuHeadName() {
		return buHeadName;
	}

	public void setBuHeadName(String buHeadName) {
		this.buHeadName = buHeadName;
	}

	/*public String getRiskManagersPersonId() {
		return RiskManagersPersonId;
	}

	public void setRiskManagersPersonId(String RiskManagersPersonId) {
		this.RiskManagersPersonId = RiskManagersPersonId;
	}

	public String getQaPersonalName() {
		return qaPersonalName;
	}

	public void setQaPersonalName(String qaPersonalName) {
		this.qaPersonalName = qaPersonalName;
	}*/

}
