package com.rightprice.auth.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@Entity
@ApiModel(value = "Campus Hire", description = "Campus Hire Model Attributes")
@Table(name = "synprod.RP_Deal_FP_Contract_Terms")
public class ContractualTerms implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "CONTRACT_TERM_ID")
	@ApiModelProperty(notes = "Contract Term Id Auto Incremented")
	private Integer contractTermId;

	@Column(name = "RP_DEAL_VERSION_ID")
	@ApiModelProperty(notes = "RP Deal Version Id")
	private Integer rpDealVersionId;
	
	@Column(name = "IS_TRANSITION_TO_PROVIDED")
	@ApiModelProperty(notes = "Is Transition To Provided")
	private String isTransitionToProvided;
	
	@Column(name = "IS_TRANSITION_CHARGEABLE")
	@ApiModelProperty(notes = "Is Transition Chargeable")
	private String isTransitionChargeable;
	
	@Column(name = "CHARGEABLE_TRANSITION_PERCENT")
	@ApiModelProperty(notes = "Chargeable Transition Percent")
	private Double chargeableTransitionPercent;
	
	@Column(name = "TOTAL_TRANSITION_EFFORTS")
	@ApiModelProperty(notes = "Total Transition Efforts")
	private Integer totalTransitionEfforts;
	
	@Column(name = "IS_WARRANTY_PROVIDED")
	@ApiModelProperty(notes = "Is Warranty Provided")
	private String isWarrantyProvided;
	
	@Column(name = "IS_WARRANTY_CHARGEABLE")
	@ApiModelProperty(notes = "Is Warranty Chargeable")
	private String isWarrantyChargeable;
	
	@Column(name = "CHARGEABLE_WARRANTY_PERCENT")
	@ApiModelProperty(notes = "Chargeable Warranty Percent")
	private Double chargeableWarrantyPercent;
	
	@Column(name = "TOTAL_WARRANTY_EFFORTS")
	@ApiModelProperty(notes = "Total Warranty Efforts")
	private Integer totalWarrantyEfforts;
	
	@Column(name = "IS_ACTIVE")
	@ApiModelProperty(notes = "Is Active")
	private Integer isActive;
	
	@Column(name = "CREATED_BY")
	@ApiModelProperty(notes = "Created By")
	private String createdBy;
	
	@Column(name = "CREATED_ON")
	@ApiModelProperty(notes = "Created On")
	private String createdOn;
	
	@Column(name = "UPDATED_BY")
	@ApiModelProperty(notes = "Updated By")
	private String updatedBy;
	
	@Column(name = "UPDATED_ON")
	@ApiModelProperty(notes = "Updated On")
	private String updatedOn;

	public Integer getContractTermId() {
		return contractTermId;
	}

	public void setContractTermId(Integer contractTermId) {
		this.contractTermId = contractTermId;
	}

	public Integer getRpDealVersionId() {
		return rpDealVersionId;
	}

	public void setRpDealVersionId(Integer rpDealVersionId) {
		this.rpDealVersionId = rpDealVersionId;
	}

	public String getIsTransitionToProvided() {
		return isTransitionToProvided;
	}

	public void setIsTransitionToProvided(String isTransitionToProvided) {
		this.isTransitionToProvided = isTransitionToProvided;
	}

	public String getIsTransitionChargeable() {
		return isTransitionChargeable;
	}

	public void setIsTransitionChargeable(String isTransitionChargeable) {
		this.isTransitionChargeable = isTransitionChargeable;
	}

	public Double getChargeableTransitionPercent() {
		return chargeableTransitionPercent;
	}

	public void setChargeableTransitionPercent(Double chargeableTransitionPercent) {
		this.chargeableTransitionPercent = chargeableTransitionPercent;
	}

	public Integer getTotalTransitionEfforts() {
		return totalTransitionEfforts;
	}

	public void setTotalTransitionEfforts(Integer totalTransitionEfforts) {
		this.totalTransitionEfforts = totalTransitionEfforts;
	}

	public String getIsWarrantyProvided() {
		return isWarrantyProvided;
	}

	public void setIsWarrantyProvided(String isWarrantyProvided) {
		this.isWarrantyProvided = isWarrantyProvided;
	}

	public String getIsWarrantyChargeable() {
		return isWarrantyChargeable;
	}

	public void setIsWarrantyChargeable(String isWarrantyChargeable) {
		this.isWarrantyChargeable = isWarrantyChargeable;
	}

	public Double getChargeableWarrantyPercent() {
		return chargeableWarrantyPercent;
	}

	public void setChargeableWarrantyPercent(Double chargeableWarrantyPercent) {
		this.chargeableWarrantyPercent = chargeableWarrantyPercent;
	}

	public Integer getTotalWarrantyEfforts() {
		return totalWarrantyEfforts;
	}

	public void setTotalWarrantyEfforts(Integer totalWarrantyEfforts) {
		this.totalWarrantyEfforts = totalWarrantyEfforts;
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
	
	
}
