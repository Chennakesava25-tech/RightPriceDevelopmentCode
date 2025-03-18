package com.rightprice.auth.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import io.swagger.annotations.ApiModelProperty;

@Entity
@Table(name="synprod.RP_Object")
public class FileUpload {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "Object_Id")
	@ApiModelProperty(notes="object ID Auto Incremented")
	private int objectid;
	
	@Column(name="FILE_NAME")
	private String filename;
	
	@Column(name = "ORIGINAL_FILE_NAME")
	private String originalFileName;
	
	public String getOriginalFileName() {
		return originalFileName;
	}
	public void setOriginalFileName(String originalFileName) {
		this.originalFileName = originalFileName;
	}
	@Column(name="OBJECT")
	private byte[] data;
	
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
	
	public int getObjectid() {
		return objectid;
	}
	public void setObjectid(int objectid) {
		this.objectid = objectid;
	}
	
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	public byte[] getData() {
		return data;
	}
	public void setData(byte[] data) {
		this.data = data;
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
