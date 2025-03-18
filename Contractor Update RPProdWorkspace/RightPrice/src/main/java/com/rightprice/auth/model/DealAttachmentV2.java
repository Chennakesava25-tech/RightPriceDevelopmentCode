package com.rightprice.auth.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.http.ResponseEntity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@Entity
@ApiModel(value="synprod.RP_Attachments",description="synprod.RP_Attachments")
@Table(name = "synprod.RP_V2_Deal_Attachment")
public class DealAttachmentV2 implements Serializable{
	
	private static final long serialVersionUID = 1L;
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "Deal_Attachemt_ID")
	@ApiModelProperty(notes="upload_Attachment_Id")
	private Integer dealAttachmentId;

	
	
	@Column(name = "Deal_Id")
	@ApiModelProperty(notes="Deal_Id")
	private String dealId;
	
	
	
	@Column(name = "File_Name")
	@ApiModelProperty(notes="fileName")
	private String fileName;

	

	@Column(name = "Is_Active")
	private Integer isActive;
	
	@Column(name = "CREATED_BY")
	private String createdBy;

	@Column(name = "CREATED_ON")
	private String createdOn;

	@Column(name = "UPDATED_BY")
	private String UpdatedBy;

	@Column(name = "UPDATED_ON")
	private String UpdatedOn;

	public Integer getDealAttachmentId() {
		return dealAttachmentId;
	}

	public void setDealAttachmentId(Integer dealAttachmentId) {
		this.dealAttachmentId = dealAttachmentId;
	}

	public String getDealId() {
		return dealId;
	}

	public void setDealId(String dealId) {
		this.dealId = dealId;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
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



	
}
