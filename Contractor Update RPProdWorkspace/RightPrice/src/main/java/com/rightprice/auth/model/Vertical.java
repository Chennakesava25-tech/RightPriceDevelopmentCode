package com.rightprice.auth.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@Entity
@ApiModel(value="Vertical",description="Vertical Model Attributes")
@Table(name = "synprod.MST_RP_Vertical")
public class Vertical {
	
	@Id
//	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "VERTICAL_ID")
	@ApiModelProperty(notes = "customer vertical id")
	private Integer verticalId;
	
	@Column(name = "VERTICAL_NAME")
	@ApiModelProperty(notes = "customer vertical Name")
	private String verticalName;
	
	/*private String DD;*/

	@Column(name = "DELIVERY_HEAD_ID")
	@ApiModelProperty(notes = "customer vertical delivery Head Id")
	private String deliveryHeadId;
	
	@Column(name = "DELIVERY_HEAD_NAME")
	@ApiModelProperty(notes = "customer vertical delivery Head Id")
	private String DUH;
	
	@Column(name = "DELIVERY_HEAD_EMAIL")
	@ApiModelProperty(notes = "customer vertical Delivery Head Email")
	private String deliveryHeadEmail;

	@Column(name = "BU_HEAD_ID")
	@ApiModelProperty(notes = "customer vertical BUH ID")
	private String buHeadId;
	
	@Column(name = "BU_HEAD_NAME")
	@ApiModelProperty(notes = "customer vertical BUH Name")
	private String BUH;

	@Column(name = "BU_HEAD_EMAIL")
	@ApiModelProperty(notes = "customer vertical BUH Email")
	private String buHeadEmail;

	/*@Column(name = "RiskManagers_PERSON_ID")
	@ApiModelProperty(notes = "customer vertical RiskManagers Person Id")
	private String RiskManagersPersonId;

	@Column(name = "QA_PERSONAL_NAME")
	@ApiModelProperty(notes = "customer vertical RiskManagers Person Name")
	private String RiskManagers;
	
	@Column(name = "QA_PERSONAL_EMAIL")
	@ApiModelProperty(notes = "customer vertical RiskManagers Person Name")
	private String RiskManagersPersonEmail;
	*/
	@Column(name = "VERTICAL_STATUS_FLAG")
	@ApiModelProperty(notes = "customer vertical Status Flag")
	private Integer verticalStatusFlag;

	/*@Column(name ="IS_ACTIVE")
	private Integer isActive=1;*/
	
	@Column(name ="IS_ACTIVE")
	private Integer isActive;
	
	@Column(name = "CREATED_BY", updatable=false)
	@ApiModelProperty(notes="Created by")
	private String createdBy;
	
	@Column(name = "CREATED_ON", updatable=false)
	@ApiModelProperty(notes="Created date")
	private String createdOn;
	
	@Column(name = "UPDATED_BY")
	@ApiModelProperty(notes="Updated by")
	private String updatedBy;
	
	@Column(name = "UPDATED_ON")
	@ApiModelProperty(notes="Updated date")
	private String updatedOn;
	
	public Integer getVerticalId() {
		return verticalId;
	}

	public void setVerticalId(Integer verticalId) {
		this.verticalId = verticalId;
	}

	public String getVerticalName() {
		return verticalName;
	}

	public void setVerticalName(String verticalName) {
		this.verticalName = verticalName;
	}

	public String getDeliveryHeadId() {
		return deliveryHeadId;
	}

	public void setDeliveryHeadId(String deliveryHeadId) {
		this.deliveryHeadId = deliveryHeadId;
	}

	public String getDUH() {
		return DUH;
	}

	public void setDUH(String dUH) {
		DUH = dUH;
	}

	public String getDeliveryHeadEmail() {
		return deliveryHeadEmail;
	}

	public void setDeliveryHeadEmail(String deliveryHeadEmail) {
		this.deliveryHeadEmail = deliveryHeadEmail;
	}

	public String getBuHeadId() {
		return buHeadId;
	}

	public void setBuHeadId(String buHeadId) {
		this.buHeadId = buHeadId;
	}

	public String getBUH() {
		return BUH;
	}

	public void setBUH(String bUH) {
		BUH = bUH;
	}

	public String getBuHeadEmail() {
		return buHeadEmail;
	}

	public void setBuHeadEmail(String buHeadEmail) {
		this.buHeadEmail = buHeadEmail;
	}

	/*public String getRiskManagersPersonId() {
		return RiskManagersPersonId;
	}

	public void setRiskManagersPersonId(String RiskManagersPersonId) {
		this.RiskManagersPersonId = RiskManagersPersonId;
	}

	public String getRiskManagers() {
		return RiskManagers;
	}

	public void setRiskManagers(String RiskManagers) {
		RiskManagers = RiskManagers;
	}

	public String getRiskManagersPersonEmail() {
		return RiskManagersPersonEmail;
	}

	public void setRiskManagersPersonEmail(String RiskManagersPersonEmail) {
		this.RiskManagersPersonEmail = RiskManagersPersonEmail;
	}
*/
	public Integer getVerticalStatusFlag() {
		return verticalStatusFlag;
	}

	public void setVerticalStatusFlag(Integer verticalStatusFlag) {
		this.verticalStatusFlag = verticalStatusFlag;
	}

	public Integer getIsActive() {
		return isActive;
	}

	public void setIsActive(Integer isActive) {
		this.isActive = isActive;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(String createdOn) {
		this.createdOn = createdOn;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public String getUpdatedOn() {
		return updatedOn;
	}

	public void setUpdatedOn(String updatedOn) {
		this.updatedOn = updatedOn;
	}
}
