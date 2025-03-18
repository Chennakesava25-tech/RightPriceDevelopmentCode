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
//@Table(name = "SFHUB.DBO.VW_CRM_FIN_CUST_MAP")
//for testing purpose  --------------------------------- please comment bfore deploying -------------------------------
@Table(name = "SFHUB.DBO.VW_CRM_FIN_CUST_MAP")
public class SFCustomerMapping implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "OPPORTUNITY_ID")
	private String strOpportunityID;
	
	@Column(name = "Customer_Group")
	private String strCustomerGroup;

	public String getStrOpportunityID() {
		return strOpportunityID;
	}

	public void setStrOpportunityID(String strOpportunityID) {
		this.strOpportunityID = strOpportunityID;
	}

	public String getStrCustomerGroup() {
		return strCustomerGroup;
	}

	public void setStrCustomerGroup(String strCustomerGroup) {
		this.strCustomerGroup = strCustomerGroup;
	}

	
}