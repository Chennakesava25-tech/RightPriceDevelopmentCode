package com.rightprice.auth.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import io.swagger.annotations.ApiModelProperty;

@Entity
@Table(name = "synprod.MST_RP_RBU")
public class MasterRBU {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "RBU_Id")
	@ApiModelProperty(notes = "RBU Id")
	private Integer rbuId;
	
	@Column(name = "RBU_Name")
	@ApiModelProperty(notes = "RBU Name")
	private String rbuName;
	
	@Column(name = "BU_Head_Id")
	@ApiModelProperty(notes = "BU Head Id")
	private String buHeadId;
	
	@Column(name = "BU_Head_Name")
	@ApiModelProperty(notes = "BU Head Name")
	private String BUH;
	
	@Column(name = "BU_Head_Email")
	@ApiModelProperty(notes = "BU Head Email")
	private String buHeadEmail;


	
	@Column(name = "Is_Active")
	@ApiModelProperty(notes = "Is Active")
	private int isActive;

	@Column(name = "CREATED_BY", updatable = false)
	@ApiModelProperty(notes = "Created by")
	private String createdBy;

	@Column(name = "CREATED_ON", updatable = false)
	@ApiModelProperty(notes = "Created date")
	private String createdDate;

	@Column(name = "UPDATED_BY")
	@ApiModelProperty(notes = "Updated by")
	private String updatedBy;

	@Column(name = "UPDATED_ON")
	@ApiModelProperty(notes = "Updated date")
	private String updatedDate;
	
	@Column(name = "Vertical_Id")
	@ApiModelProperty(notes = "Vertical Id")
	private int verticalId;
	
	@Column(name = "Delivery_Head_Id")
	@ApiModelProperty(notes = "Delivery Head Id")
	private String deliveryHeadId;
	
	@Column(name = "Delivery_Head_Name")
	@ApiModelProperty(notes = "Delivery Head Name")
	private String DUH;
	
	@Column(name = "Delivery_Head_Email")
	@ApiModelProperty(notes = "Delivery Head Email")
	private String deliveryHeadEmail;
	
	@Column(name = "Vertical_Name")
	@ApiModelProperty(notes = "Vertical Name")
	private String verticalName;
	
	@Column(name = "Vertical_Status_Flag")
	@ApiModelProperty(notes = "Vertical Status Flag")
	private int verticalStatusFlag;

	public Integer getRbuId() {
		return rbuId;
	}

	public void setRbuId(Integer rbuId) {
		this.rbuId = rbuId;
	}

	public String getRbuName() {
		return rbuName;
	}

	public void setRbuName(String rbuName) {
		this.rbuName = rbuName;
	}

	public String getBuHeadId() {
		return buHeadId;
	}

	public void setBuHeadId(String buHeadId) {
		this.buHeadId = buHeadId;
	}

	
	public String getBuHeadEmail() {
		return buHeadEmail;
	}

	public void setBuHeadEmail(String buHeadEmail) {
		this.buHeadEmail = buHeadEmail;
	}

	

	

	public int getIsActive() {
		return isActive;
	}

	public void setIsActive(int isActive) {
		this.isActive = isActive;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public String getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(String updatedDate) {
		this.updatedDate = updatedDate;
	}

	public int getVerticalId() {
		return verticalId;
	}

	public void setVerticalId(int verticalId) {
		this.verticalId = verticalId;
	}

	public String getDeliveryHeadId() {
		return deliveryHeadId;
	}

	public void setDeliveryHeadId(String deliveryHeadId) {
		this.deliveryHeadId = deliveryHeadId;
	}

	

	public String getDeliveryHeadEmail() {
		return deliveryHeadEmail;
	}

	public void setDeliveryHeadEmail(String deliveryHeadEmail) {
		this.deliveryHeadEmail = deliveryHeadEmail;
	}

	public String getVerticalName() {
		return verticalName;
	}

	public void setVerticalName(String verticalName) {
		this.verticalName = verticalName;
	}

	public int getVerticalStatusFlag() {
		return verticalStatusFlag;
	}

	public void setVerticalStatusFlag(int verticalStatusFlag) {
		this.verticalStatusFlag = verticalStatusFlag;
	}
	
	public String getDUH() {
		return DUH;
	}

	public void setDUH(String dUH) {
		DUH = dUH;
	}
	
	public String getBUH() {
		return BUH;
	}

	public void setBUH(String bUH) {
		BUH = bUH;
	}
	
}
