package com.rightprice.auth.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import io.swagger.annotations.ApiModelProperty;

@Entity
public class uploadedEstimationAttach {
	
	@Id
	@Column(name = "ID")
	@ApiModelProperty(notes="Deal_Document_Id Auto Incremented")
	private int id;
	
	@Column(name="Version_Id")
	private int VersionId;
	
	@Column(name="DOCTYPE")
	private String doctype;
	
	@Column(name="ATTACHMENT_ID")
	private int attachmentId;
	
	@Column(name="Comment")
	private String comments;
	
	@Column(name = "IS_ACTIVE")
	private int is_Active;

	@Column(name = "CREATED_BY", updatable=false)
	private String createdBy;

	@Column(name = "CREATED_ON", updatable=false)
	private String createdOn;

	@Column(name = "UPDATED_BY")
	private String lastUpdatedBy;

	@Column(name = "UPDATED_ON")
	private String lastUpdatedOn;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getVersionId() {
		return VersionId;
	}

	public void setVersionId(int versionId) {
		VersionId = versionId;
	}

	public String getDoctype() {
		return doctype;
	}

	public void setDoctype(String doctype) {
		this.doctype = doctype;
	}

	public int getAttachmentId() {
		return attachmentId;
	}

	public void setAttachmentId(int attachmentId) {
		this.attachmentId = attachmentId;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public int getIs_Active() {
		return is_Active;
	}

	public void setIs_Active(int is_Active) {
		this.is_Active = is_Active;
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

	public String getLastUpdatedBy() {
		return lastUpdatedBy;
	}

	public void setLastUpdatedBy(String lastUpdatedBy) {
		this.lastUpdatedBy = lastUpdatedBy;
	}

	public String getLastUpdatedOn() {
		return lastUpdatedOn;
	}

	public void setLastUpdatedOn(String lastUpdatedOn) {
		this.lastUpdatedOn = lastUpdatedOn;
	}

	
}

