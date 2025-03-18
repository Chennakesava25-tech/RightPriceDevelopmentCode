package com.rightprice.auth.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import io.swagger.annotations.ApiModelProperty;

@Entity
@Table(name = "synprod.RP_TRN_Page_Tracker")
public class PageTracker {
/*	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Id")
	private Integer id;*/
	@Id
	@Column(name = "RC_or_Deal_ID")
	private Integer rcIdOrDealId;
	
	@Column(name = "Record_Type")
	private Integer recordType;
	
	@Column(name = "no_of_pages")
	private Integer noOfPages;
	
	@Column(name = "No_of_submitted_pages")
	private Integer noOfSubmittedPages;
	
	@Column(name = "Record_status")
	private Integer recordStatus;
	
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

	public Integer getRcIdOrDealId() {
		return rcIdOrDealId;
	}

	public void setRcIdOrDealId(Integer rcIdOrDealId) {
		this.rcIdOrDealId = rcIdOrDealId;
	}

	public Integer getRecordType() {
		return recordType;
	}

	public void setRecordType(Integer recordType) {
		this.recordType = recordType;
	}

	public Integer getNoOfPages() {
		return noOfPages;
	}

	public void setNoOfPages(Integer noOfPages) {
		this.noOfPages = noOfPages;
	}

	public Integer getNoOfSubmittedPages() {
		return noOfSubmittedPages;
	}

	public void setNoOfSubmittedPages(Integer noOfSubmittedPages) {
		this.noOfSubmittedPages = noOfSubmittedPages;
	}

	public Integer getRecordStatus() {
		return recordStatus;
	}

	public void setRecordStatus(Integer recordStatus) {
		this.recordStatus = recordStatus;
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
