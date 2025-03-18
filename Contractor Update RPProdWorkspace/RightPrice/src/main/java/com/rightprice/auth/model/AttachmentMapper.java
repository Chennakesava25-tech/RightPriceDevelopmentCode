package com.rightprice.auth.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import io.swagger.annotations.ApiModelProperty;


@Entity
public class AttachmentMapper {

	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "OBJECT_ID")
	@ApiModelProperty(notes="object ID Auto Incremented")
	private int objectid;
	
	@Column(name = "VERSION_ID")
	@ApiModelProperty(notes="Version_Id")
	private Integer versionId;

	@Column(name="FILE_NAME")
	private String filename;
	
	@Column(name="OBJECT")
	private byte[] object;
	
	@Column(name = "Category")
	private String category;
	
	
	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	@Column(name = "CREATED_ON", updatable=false)
	private String createdOn;
	
	@Column(name = "ORIGINAL_FILE_NAME", updatable=false)
	private String ORIGINALFILENAME;
	
	public int getObjectid() {
		return objectid;
	}

	public void setObjectid(int objectid) {
		this.objectid = objectid;
	}

	public Integer getVersionId() {
		return versionId;
	}

	public void setVersionId(Integer versionId) {
		this.versionId = versionId;
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
