package com.rightprice.auth.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import io.swagger.annotations.ApiModelProperty;
@Entity
public class FpDealManualAttachData {

	@Id
	@Column(name="DEAL_ATTACHMENT_ID")
	private int dealAttachmentId;
	
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
	
	
	@Column(name = "Created_On")
	private String createdOn;

	
	@Column(name = "Updated_On")
	private String UpdatedOn;
	
	public int getDealAttachmentId() {
		return dealAttachmentId;
	}

	public void setDealAttachmentId(int dealAttachmentId) {
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

	
	public String getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(String createdOn) {
		this.createdOn = createdOn;
	}

	public String getUpdatedOn() {
		return UpdatedOn;
	}

	public void setUpdatedOn(String updatedOn) {
		UpdatedOn = updatedOn;
	}

	
}
