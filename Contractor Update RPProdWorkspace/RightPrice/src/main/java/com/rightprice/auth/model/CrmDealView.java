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
@ApiModel(value = "City", description = "City Model Attributes")
//for production  purpose
@Table(name = "dbo.OppDataForRP")
//for testing purpose
//@Table(name = "SFHUB.dbo.OppDataForRP")
public class CrmDealView implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "SYNTEL_OPPORTUNITYID")
	@ApiModelProperty(notes = "City ID Auto Incremented")
	private String Syntel_OpportunityID;


	
	@Column(name = "ATOS_OPPORTUNITY_ID")
	@ApiModelProperty(notes = "City ID Auto Incremented")
	private String Atos_Opportunity_ID;


	public String getSyntel_OpportunityID() {
		return Syntel_OpportunityID;
	}


	public void setSyntel_OpportunityID(String syntel_OpportunityID) {
		Syntel_OpportunityID = syntel_OpportunityID;
	}


	public String getAtos_Opportunity_ID() {
		return Atos_Opportunity_ID;
	}


	public void setAtos_Opportunity_ID(String atos_Opportunity_ID) {
		Atos_Opportunity_ID = atos_Opportunity_ID;
	}

	
	
	
}