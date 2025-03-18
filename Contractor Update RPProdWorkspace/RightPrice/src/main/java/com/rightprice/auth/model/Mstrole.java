package com.rightprice.auth.model;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import com.rightprice.auth.util.AppLoger;
import com.rightprice.auth.util.DateUtil;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@Entity
@ApiModel(value="Trnappmenumapping details",description="Trnappmenumapping details Model Attributes")
@Table(name = "synprod.mst_role ")
public class Mstrole implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "MST_ROLE_ID")
	private Integer mstroleid ;
	
	
	public Integer getMstroleid() {
		return mstroleid;
	}

	public void setMstroleid(Integer mstroleid) {
		this.mstroleid = mstroleid;
	}

	@Column(name = "APP_ID")
	private Integer appid=3;
	
	
	@Column(name = "ROLE_NAME")
	private String rolename;
	
	@Column(name ="IS_ROLE_ACTIVE")
	private Integer isroleactive;
	
	
	@Column(name ="ACTIVE_STATUS")
	private Integer Activestatus;
	
	
	
	@Column(name = "CREATED_BY", updatable=false)
	@ApiModelProperty(notes="Created by")
	private String createdBy;
	
	@Column(name = "CREATED_ON", updatable=false)
	@ApiModelProperty(notes="Created date")
	private String createdOn;
	
	@Column(name = "LAST_UPDATED_BY")
	@ApiModelProperty(notes=" Last Updated by")
	private String lastupdatedBy;
	
	@Column(name = "LAST_UPDATED_ON")
	@ApiModelProperty(notes="Last Updated date")
	private String lastupdatedOn;
	
	


	@Transient
	private int requesterFlag = 0;

	

	public int getRequesterFlag() {
		return requesterFlag;
	}

	public void setRequesterFlag(int requesterFlag) {
		this.requesterFlag = requesterFlag;
	}

	public Integer getAppid() {
		return appid;
	}

	public void setAppid(Integer appid) {
		this.appid = appid;
	}

	public String getRolename() {
		return rolename;
	}

	public void setRolename(String rolename) {
		this.rolename = rolename;
	}

	public Integer getIsroleactive() {
		return isroleactive;
	}

	public void setIsroleactive(Integer isroleactive) {
		this.isroleactive = isroleactive;
	}

	public Integer getActivestatus() {
		return Activestatus;
	}

	public void setActivestatus(Integer activestatus) {
		Activestatus = activestatus;
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

	public String getLastupdatedBy() {
		return lastupdatedBy;
	}

	public void setLastupdatedBy(String lastupdatedBy) {
		this.lastupdatedBy = lastupdatedBy;
	}

	public String getLastupdatedOn() {
		return lastupdatedOn;
	}

	public void setLastupdatedOn(String lastupdatedOn) {
		this.lastupdatedOn = lastupdatedOn;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	
	
	
	
	
	
	
}
