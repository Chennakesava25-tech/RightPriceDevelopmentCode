package com.rightprice.auth.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import io.swagger.annotations.ApiModelProperty;
@Entity
public class AttachmentMapper2 {


	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "OBJECT_ID")
	@ApiModelProperty(notes="object ID Auto Incremented")
	private int objectid;
	
	@Column(name = "Deal_Id")
	@ApiModelProperty(notes="dealId")
	private String crmDealId;

	@Column(name="File_Name")
	private String filename;
	
	@Column(name="OBJECT")
	private byte[] object;
	
	@Column(name = "CREATED_ON", updatable=false)
	private String createdOn;
	
	@Column(name = "ORIGINAL_FILE_NAME", updatable=false)
	private String ORIGINALFILENAME;
	
	
	
	



	public String getCrmDealId() {
		return crmDealId;
	}

	public void setCrmDealId(String crmDealId) {
		this.crmDealId = crmDealId;
	}

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


	public byte[] getObject() {
		return object;
	}

	public void setObject(byte[] object) {
		this.object = object;
	}

	public String getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(String createdOn) {
		this.createdOn = createdOn;
	}

	public String getORIGINALFILENAME() {
		return ORIGINALFILENAME;
	}

	public void setORIGINALFILENAME(String oRIGINALFILENAME) {
		ORIGINALFILENAME = oRIGINALFILENAME;
	}

	

}
