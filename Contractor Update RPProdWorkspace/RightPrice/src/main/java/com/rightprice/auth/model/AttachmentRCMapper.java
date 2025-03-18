package com.rightprice.auth.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import io.swagger.annotations.ApiModelProperty;
@Entity
public class AttachmentRCMapper  {
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "RateCard_Attachemt_ID")
	@ApiModelProperty(notes="RateCard_Attachemt_ID")
	private Integer Id;
	
	
	@Column(name = "RateCard_Id")
	private Integer rcId;
	

	@Column(name = "File_Name")
	private String fileName;
	
	
	
	@Column(name ="Is_Active")
	private Integer isActive=1; 
	
	public Integer getId() {
		return Id;
	}

	public void setId(Integer id) {
		Id = id;
	}

	public Integer getRcId() {
		return rcId;
	}

	public void setRcId(Integer rcId) {
		this.rcId = rcId;
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

	@Column(name = "Created_By", updatable=false)
	  
	  @ApiModelProperty(notes="Created by") private String createdBy;
	  
	  @Column(name = "Created_On", updatable=false)
	
	 @ApiModelProperty(notes="Created date") private String createdOn;
	  
	  @Column(name = "Updated_By")
	  
	  @ApiModelProperty(notes="Updated by") private String updatedBy;
	 
	  @Column(name = "Updated_On")
	  
	  @ApiModelProperty(notes="Updated date") private String updatedOn;

	
	

}
