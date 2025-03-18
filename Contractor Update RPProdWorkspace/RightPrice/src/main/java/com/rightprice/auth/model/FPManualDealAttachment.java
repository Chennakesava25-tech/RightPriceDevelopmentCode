package com.rightprice.auth.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
@Entity
@ApiModel(value="FP_Manual_Deal_Attachement",description="FP_Manual__Deal_Attachement")
@Table(name = "synprod.RP_manual_deal_Attachments")
public class FPManualDealAttachment implements Serializable{
	
	
	private static final long serialVersionUID = 1L;
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "DEAL_ATTACHMENT_ID")
	@ApiModelProperty(notes="deal_attachment_id")
	private Integer dealAttachmentId;

	@Column(name = "RP_DEAL_VERSION_ID")
	@ApiModelProperty(notes="RP_DEAL_VERSION_ID")
	private Integer rpDealVersionId;
	
	@Column(name = "RP_DEAL_TOWER_ID")
	@ApiModelProperty(notes="RP_DEAL_VERSION_ID")
	private Integer dealAutoTowerId;
	
	@Column(name = "DOC_TYPE")
	@ApiModelProperty(notes="RP_DEAL_VERSION_ID")
	private Integer docType;
	
	@Column(name = "File_Name")
	@ApiModelProperty(notes="RP_DEAL_VERSION_ID")
	private String fileName;
	
	@Column(name = "Date_stamp")
	@ApiModelProperty(notes="Date_stamp")
	private String dateStamp;
	
	@Column(name = "Is_Active")
	private Integer isActive;
	
	@Column(name = "Created_By")
	private String createdBy;

	@Column(name = "Created_On")
	private String createdOn;

	@Column(name = "Updated_By")
	private String UpdatedBy;

	@Column(name = "Updated_On")
	private String UpdatedOn;
	
	@Transient
	private byte[] data;

	public Integer getDealAttachmentId() {
		return dealAttachmentId;
	}

	public void setDealAttachmentId(Integer dealAttachmentId) {
		this.dealAttachmentId = dealAttachmentId;
	}

	public Integer getRpDealVersionId() {
		return rpDealVersionId;
	}

	public void setRpDealVersionId(Integer rpDealVersionId) {
		this.rpDealVersionId = rpDealVersionId;
	}

	public Integer getDealAutoTowerId() {
		return dealAutoTowerId;
	}

	public void setDealAutoTowerId(Integer dealAutoTowerId) {
		this.dealAutoTowerId = dealAutoTowerId;
	}

	public Integer getDocType() {
		return docType;
	}

	public void setDocType(Integer docType) {
		this.docType = docType;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getDateStamp() {
		return dateStamp;
	}

	public void setDateStamp(String dateStamp) {
		this.dateStamp = dateStamp;
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
		return UpdatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		UpdatedBy = updatedBy;
	}

	public String getUpdatedOn() {
		return UpdatedOn;
	}

	public void setUpdatedOn(String updatedOn) {
		UpdatedOn = updatedOn;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public byte[] getData() {
		return data;
	}

	public void setData(byte[] data) {
		this.data = data;
	}

}
