package com.rightprice.auth.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.rightprice.auth.util.DateUtil;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@Entity
@ApiModel(value="Lob",description="Lob Model Attributes")
@Table(name = "synprod.MST_RP_LOB",uniqueConstraints=
@UniqueConstraint(columnNames={"LOB_ID","LOB_CODE"})
)
public class Lob {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "LOB_ID")
	@ApiModelProperty(notes="Lob ID Auto Incremented")
	private int lobId;
	
	@Column(name = "LOB_CODE")
	@ApiModelProperty(notes="Lob Code")
	private String lobCode;
	
	@Column(name = "LOB_DESCRIPTION")
	@ApiModelProperty(notes="Lob Description")
	private String lobDescription;
	
	@Column(name = "LOB_START_DATE")
	@ApiModelProperty(notes="Lob Start Date")
	private String lobStartDate;
   
	@Column(name = "LOB_END_DATE")
	@ApiModelProperty(notes="Lob End Date")
	private String lobEndDate;
	
	@Column(name = "IS_ACTIVE")
	@ApiModelProperty(notes="Is Active")
	private int isActive;
	
	@Column(name = "CREATED_BY", updatable=false)
	@ApiModelProperty(notes="Created By")
	private String createdBy;
	
	@Column(name = "CREATED_ON", updatable=false)
	@ApiModelProperty(notes="Created On")
	private String createdOn;
	
	@Column(name = "UPDATED_BY")
	@ApiModelProperty(notes="updated By")
	private String updatedBy;
	
	@Column(name = "UPDATED_ON")
	@ApiModelProperty(notes="updated On")
	private String updatedOn;

	public int getLobId() {
		return lobId;
	}

	public void setLobId(int lobId) {
		this.lobId = lobId;
	}

	public String getLobCode() {
		return lobCode;
	}

	public void setLobCode(String lobCode) {
		this.lobCode = lobCode;
	}

	public String getLobDescription() {
		return lobDescription;
	}

	public void setLobDescription(String lobDescription) {
		this.lobDescription = lobDescription;
	}

	public String getLobStartDate() {
		return lobStartDate;
	}

	public void setLobStartDate(String lobStartDate) {
		this.lobStartDate = DateUtil.getMySqlFormattedDate(lobStartDate);
	}

	public String getLobEndDate() {
		return lobEndDate;
	}

	public void setLobEndDate(String lobEndDate) {
		this.lobEndDate = DateUtil.getMySqlFormattedDate(lobEndDate);
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
