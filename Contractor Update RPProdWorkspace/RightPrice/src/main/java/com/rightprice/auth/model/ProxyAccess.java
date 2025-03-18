package com.rightprice.auth.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import io.swagger.annotations.ApiModelProperty;

@Entity
@Table(name = "SYNPROD.MST_RP_PROXY_ACCESS")
public class ProxyAccess implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "ID")
	@ApiModelProperty(notes = "id")
	private Integer id;
	
	@Column(name= "USER_AD_Id")
	@ApiModelProperty(notes = "USER_AD_Id")
	private String userADId;
	
	@Column(name= "PROXY_AD_ID")
	@ApiModelProperty(notes = "PROXY_AD_ID")
	private String proxyADId;
	
	@Column(name= "ACCESS_START_DATE")
	@ApiModelProperty(notes = "ACCESS_START_DATE")
	private String accessStartDate;
	
	@Column(name= "ACCESS_END_DATE")
	@ApiModelProperty(notes = "ACCESS_END_DATE")
	private String accessEndDate;
	
	
	@Column(name = "IS_ACTIVE")
	@ApiModelProperty(notes = "active status")
	private Integer isActive = 1;

	@Column(name = "CREATED_BY", updatable = false)
	@ApiModelProperty(notes = "Created by")
	private String createdBy;

	@Column(name = "CREATED_ON", updatable = false)
	@ApiModelProperty(notes = "Created date")
	private String createdOn;

	@Column(name = "UPDATED_BY")
	@ApiModelProperty(notes = "Updated by")
	private String updatedBy;

	@Column(name = "UPDATED_ON")
	@ApiModelProperty(notes = "Updated date")
	private String updatedOn;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUserADId() {
		return userADId;
	}

	public void setUserADId(String userADId) {
		this.userADId = userADId;
	}

	public String getProxyADId() {
		return proxyADId;
	}

	public void setProxyADId(String proxyADId) {
		this.proxyADId = proxyADId;
	}

	public String getAccessStartDate() {
		return accessStartDate;
	}

	public void setAccessStartDate(String accessStartDate) {
		this.accessStartDate = accessStartDate;
	}

	public String getAccessEndDate() {
		return accessEndDate;
	}

	public void setAccessEndDate(String accessEndDate) {
		this.accessEndDate = accessEndDate;
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
	
	@Column(name = "Is_Email")
	@ApiModelProperty(notes = "email")
	private Integer isEmail;
	
	@Column(name = "User_id_Email")
	@ApiModelProperty(notes = "user email")
	private String userIdMail;
	
	

	public String getUserIdMail() {
		return userIdMail;
	}

	public void setUserIdMail(String userIdMail) {
		this.userIdMail = userIdMail;
	}
	
	public Integer getIsEmail() {
		return isEmail;
	}

	public void setIsEmail(Integer isEmail) {
		this.isEmail = isEmail;
	}

}
