package com.rightprice.auth.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import io.swagger.annotations.ApiModelProperty;

@Entity
@Table(name="synprod.RP_Deal_FP_Uploaded_Documents")
public class UploadedDocuments {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "Deal_Document_Id")
	@ApiModelProperty(notes="Deal_Document_Id Auto Incremented")
	private int dealdocId;
	
	@Column(name="RP_Deal_Version_Id")
	private int rpid;
	
	@Column(name="Document_Type_Id")
	private int doctypeId;
	
	@Column(name="File_Object_Id")
	private int fileobjId;
	
	@Column(name="Verified_Ok")
	private int verified;
	
	@Column(name="Comments")
	private String comments;
	
	@Column(name="Verified_By")
	private String verifiedBy;
	
	@Column(name = "IS_ACTIVE")
	private int activeStatus;

	@Column(name = "CREATED_BY", updatable=false)
	private String createdBy;

	@Column(name = "CREATED_ON", updatable=false)
	private String createdOn;

	@Column(name = "UPDATED_BY")
	private String lastUpdatedBy;

	@Column(name = "UPDATED_ON")
	private String lastUpdatedOn;

	public int getDealdocId() {
		return dealdocId;
	}

	public void setDealdocId(int dealdocId) {
		this.dealdocId = dealdocId;
	}

	public int getRpid() {
		return rpid;
	}

	public void setRpid(int rpid) {
		this.rpid = rpid;
	}

	public int getDoctypeId() {
		return doctypeId;
	}

	public void setDoctypeId(int doctypeId) {
		this.doctypeId = doctypeId;
	}

	public int getFileobjId() {
		return fileobjId;
	}

	public void setFileobjId(int fileobjId) {
		this.fileobjId = fileobjId;
	}

	public int getVerified() {
		return verified;
	}

	public void setVerified(int verified) {
		this.verified = verified;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public String getVerifiedBy() {
		return verifiedBy;
	}

	public void setVerifiedBy(String verifiedBy) {
		this.verifiedBy = verifiedBy;
	}

	public int getActiveStatus() {
		return activeStatus;
	}

	public void setActiveStatus(int activeStatus) {
		this.activeStatus = activeStatus;
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
