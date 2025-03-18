package com.rightprice.auth.model;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import io.swagger.annotations.ApiModelProperty;

@Entity
@Table(name = "synprod.MST_RP_App_Code_Common_Cost")
public class CommonCost implements Externalizable,Serializable {
	private static final long serialVersionUID = 1L;
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	
	@Column(name = "ID")
	@ApiModelProperty(notes="Id Auto Incremented")
	private int id;
	
	@Column(name = "APP_CODE")
	private String appCode;
	
	@Column(name = "CODE_TYPE")
	private String codeType;
	
	@Column(name = "CODE_NAME")
	private int codeName;
	
	@Column(name = "DESCRIPTION")
	private String paramName;
	
	@Column(name = "Is_Active")
	private int isActive;
	
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

	public String getAppCode() {
		return appCode;
	}

	public void setAppCode(String appCode) {
		this.appCode = appCode;
	}

	public String getCodeType() {
		return codeType;
	}

	public void setCodeType(String codeType) {
		this.codeType = codeType;
	}

	public int getCodeName() {
		return codeName;
	}

	public void setCodeName(int codeName) {
		this.codeName = codeName;
	}

	public String getParamName() {
		return paramName;
	}

	public void setParamName(String paramName) {
		this.paramName = paramName;
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

	@Override
	public void writeExternal(ObjectOutput out) throws IOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
		// TODO Auto-generated method stub
		
	}

}
