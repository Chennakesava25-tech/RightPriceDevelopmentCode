package com.rightprice.auth.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import io.swagger.annotations.ApiModelProperty;
@Entity
public class VirewMasterRateCard {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "RC_ID")
	private Integer rcId;
	
	@Column(name = "CUSTOMER_VERTICAL_MAP_ID")
	private Integer customerVerticalMapId;
	
	@Column(name = "RC_NAME")
	private String rcName;
	
	@Column(name = "RC_START_DATE")
	private String rcStartDate;
	
	@Column(name = "RC_END_DATE")
	private String rcEndDate;

	@Column(name = "CUSTOMER_NAME")
	private String customerName;
	
	@Column(name = "CURRENT_APPROVAL_STATUS")
	private Integer CurrentStatus;
	
	@Column(name ="IS_ACTIVE")
	private Integer isActive=1;
	
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
	
	@Column(name = "EXPECTED_RC_END_DATE")
	private String expectedRCEndDate;
	
	@Column(name="RC_COMMENTS")
	private String rccomments;
	
	/*@OneToOne(cascade = javax.persistence.CascadeType.ALL)
	@JoinColumn(name ="CUSTOMER_VERTICAL_MAP_ID",referencedColumnName = "CUSTOMER_VERTICAL_MAP_ID",insertable=false, updatable=false)
	private CustomerVerticalMapping customerVerticalMapping;*/

	public String getExpectedRCEndDate() {
		return expectedRCEndDate;
	}

	public void setExpectedRCEndDate(String expectedRCEndDate) {
		this.expectedRCEndDate = expectedRCEndDate;
	}

	public Integer getRcId() {
		return rcId;
	}

	public void setRcId(Integer rcId) {
		this.rcId = rcId;
	}

	public Integer getCustomerVerticalMapId() {
		return customerVerticalMapId;
	}

	public void setCustomerVerticalMapId(Integer customerVerticalMapId) {
		this.customerVerticalMapId = customerVerticalMapId;
	}

	public String getRcName() {
		return rcName;
	}

	public void setRcName(String rcName) {
		this.rcName = rcName;
	}

	public String getRcStartDate() {
		return rcStartDate;
	}

	public void setRcStartDate(String rcStartDate) {
		this.rcStartDate = rcStartDate;
	}

	public String getRcEndDate() {
		return rcEndDate;
	}

	public void setRcEndDate(String rcEndDate) {
		this.rcEndDate = rcEndDate;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public Integer getCurrentStatus() {
		return CurrentStatus;
	}

	public void setCurrentStatus(Integer currentStatus) {
		CurrentStatus = currentStatus;
	}

/*	public CustomerVerticalMapping getCustomerVerticalMapping() {
		return customerVerticalMapping;
	}

	public void setCustomerVerticalMapping(CustomerVerticalMapping customerVerticalMapping) {
		this.customerVerticalMapping = customerVerticalMapping;
	}*/

	public String getRccomments() {
		return rccomments;
	}

	public void setRccomments(String rccomments) {
		this.rccomments = rccomments;
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
