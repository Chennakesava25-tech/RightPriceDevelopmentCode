package com.rightprice.auth.model;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@Entity
@ApiModel(value="Stages",description="Stages Model Attributes")
@Table(name = "synprod.RP_Deal_CRM_Stage")
public class DealCrmStageSFData implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
   // @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "CRM_DEAL_ID")
	//@ApiModelProperty(notes="crm deal id Auto Incremented")
	private String strCrmDealId;
	
	@Column(name = "SF_ACCOUNT_ID")
	@ApiModelProperty(notes="Deal SF_ACCOUNT_ID")
	private String strSFAccountId;
	
	@Column(name = "SF_ACCOUNT_NAME")
	@ApiModelProperty(notes="Deal SF_ACCOUNT_NAME")
	private String strSFAccountName;
	
	@Column(name = "SF_IRIS_ACCOUNT_ID")
	@ApiModelProperty(notes="Deal SF_IRIS_ACCOUNT_ID")
	private String strSFIrisAccountId;
	
	@Column(name = "SF_IRIS_ACCOUNT_NAME")
	@ApiModelProperty(notes="Deal SF_IRIS_ACCOUNT_NAME")
	private String strSFIrisAccountName;
	
	@Column(name = "SF_IRIS_CODE")
	@ApiModelProperty(notes="Deal SF_IRIS_CODE")
	private String strSFIrisCode;
	
	@Column(name = "Is_Active")
	@ApiModelProperty(notes="Deal SF_IRIS_CODE")
	private Integer intIsActive;
	

	public Integer getIntIsActive() {
		return intIsActive;
	}

	public void setIntIsActive(Integer intIsActive) {
		this.intIsActive = intIsActive;
	}

	public String getStrCrmDealId() {
		return strCrmDealId;
	}

	public void setStrCrmDealId(String strCrmDealId) {
		this.strCrmDealId = strCrmDealId;
	}

	public String getStrSFAccountId() {
		return strSFAccountId;
	}

	public void setStrSFAccountId(String strSFAccountId) {
		this.strSFAccountId = strSFAccountId;
	}

	public String getStrSFAccountName() {
		return strSFAccountName;
	}

	public void setStrSFAccountName(String strSFAccountName) {
		this.strSFAccountName = strSFAccountName;
	}

	public String getStrSFIrisAccountId() {
		return strSFIrisAccountId;
	}

	public void setStrSFIrisAccountId(String strSFIrisAccountId) {
		this.strSFIrisAccountId = strSFIrisAccountId;
	}

	public String getStrSFIrisAccountName() {
		return strSFIrisAccountName;
	}

	public void setStrSFIrisAccountName(String strSFIrisAccountName) {
		this.strSFIrisAccountName = strSFIrisAccountName;
	}

	public String getStrSFIrisCode() {
		return strSFIrisCode;
	}

	public void setStrSFIrisCode(String strSFIrisCode) {
		this.strSFIrisCode = strSFIrisCode;
	}
	
	
	
}
