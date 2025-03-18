package com.rightprice.auth.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@Entity
@ApiModel(value = "finance", description = "finanace view Model Attributes")
//for production  purpose --------------------------------- please uncomment bfore deploying -------------------------------
@Table(name = "VMARISHUBPRDDB1.HUBPROD.dbo.PS_SY_BI_CUST_MAP")
//for testing purpose  --------------------------------- please uncomment bfore deploying -------------------------------
//@Table(name = "VMARISPSFNDEV02.FNDLY855.dbo.PS_SY_BI_CUST_MAP")
public class financeCustMapping implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "CUST_ID")
	private String strCustId;
	
	@Column(name = "SY_SF_ACCT_ID")
	private String strSySfAcctId;

	@Column(name = "SY_IRIS_ACCOUNT_ID")
	private String strSyIrisAccountId;

	public String getStrCustId() {
		return strCustId;
	}

	public void setStrCustId(String strCustId) {
		this.strCustId = strCustId;
	}

	public String getStrSySfAcctId() {
		return strSySfAcctId;
	}

	public void setStrSySfAcctId(String strSySfAcctId) {
		this.strSySfAcctId = strSySfAcctId;
	}

	public String getStrSyIrisAccountId() {
		return strSyIrisAccountId;
	}

	public void setStrSyIrisAccountId(String strSyIrisAccountId) {
		this.strSyIrisAccountId = strSyIrisAccountId;
	}
	
}