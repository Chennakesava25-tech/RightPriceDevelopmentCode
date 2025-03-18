package com.rightprice.auth.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import io.swagger.annotations.ApiModel;

@Entity
@ApiModel(value = "FPDeal What if Contract Terms", description = "FPDeal What if Contract Terms")
@Table(name = "synprod.RP_Deal_FP_Contract_Terms")
public class FPWhatIfContractTerms {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Contract_Term_Id")
	private Integer contractTermId;
	
	@Column(name = "RP_Deal_Version_Id")
	private Integer rpDealVersionId;
	
	@Column(name = "Is_Transition_Chargeable")
	private Integer isTransitionChargeable;
	
	@Column(name = "Chargeable_Transition_Percent")
	private Double chargeableTransitionPercent;
	
	@Column(name = "Is_efforts_considered_for_costing")
	private Integer isEffortsConsideredForCosting;
	
	@Column(name = "Total_Transition_Efforts")
	private Double totalTransitionEfforts;
	

	@Column(name = "Is_Warranty_Provided")
	private Integer isWarrantyProvided;
	

	@Column(name = "Is_Warranty_Chargeable")
	private Integer isWarrantyChargeable;
	

	@Column(name = "Chargeable_Warranty_Percent")
	private Double chargeableWarrantyPercent;
	
	
	@Column(name = "Is_warrenty_efforts_considered_for_costing")
	private Integer isWarrentyEffortsConsideredForCosting;
	

	@Column(name = "Total_Warranty_Efforts")
	private Double totalWarrantyEfforts;
	
	@Column(name = "Is_Active")
	private Integer isActive;
	
	@Column(name = "Created_By")
	private String createdBy;
	
	@Column(name = "Created_On")
	private String createdOn;
	
	@Column(name = "Updated_By")
	private String updatedBy;
	
	@Column(name = "Updated_On")
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

	public Integer getIsTransitionChargeable() {
		return isTransitionChargeable;
	}

	public void setIsTransitionChargeable(Integer isTransitionChargeable) {
		this.isTransitionChargeable = isTransitionChargeable;
	}

	public Double getChargeableTransitionPercent() {
		return chargeableTransitionPercent;
	}

	public void setChargeableTransitionPercent(Double chargeableTransitionPercent) {
		this.chargeableTransitionPercent = chargeableTransitionPercent;
	}

	public Integer getIsEffortsConsideredForCosting() {
		return isEffortsConsideredForCosting;
	}

	public void setIsEffortsConsideredForCosting(Integer isEffortsConsideredForCosting) {
		this.isEffortsConsideredForCosting = isEffortsConsideredForCosting;
	}

	public Double getTotalTransitionEfforts() {
		return totalTransitionEfforts;
	}

	public void setTotalTransitionEfforts(Double totalTransitionEfforts) {
		this.totalTransitionEfforts = totalTransitionEfforts;
	}

	public Integer getIsWarrantyProvided() {
		return isWarrantyProvided;
	}

	public void setIsWarrantyProvided(Integer isWarrantyProvided) {
		this.isWarrantyProvided = isWarrantyProvided;
	}

	public Integer getIsWarrantyChargeable() {
		return isWarrantyChargeable;
	}

	public void setIsWarrantyChargeable(Integer isWarrantyChargeable) {
		this.isWarrantyChargeable = isWarrantyChargeable;
	}

	public Double getChargeableWarrantyPercent() {
		return chargeableWarrantyPercent;
	}

	public void setChargeableWarrantyPercent(Double chargeableWarrantyPercent) {
		this.chargeableWarrantyPercent = chargeableWarrantyPercent;
	}

	public Integer getIsWarrentyEffortsConsideredForCosting() {
		return isWarrentyEffortsConsideredForCosting;
	}

	public void setIsWarrentyEffortsConsideredForCosting(Integer isWarrentyEffortsConsideredForCosting) {
		this.isWarrentyEffortsConsideredForCosting = isWarrentyEffortsConsideredForCosting;
	}

	public Double getTotalWarrantyEfforts() {
		return totalWarrantyEfforts;
	}

	public void setTotalWarrantyEfforts(Double totalWarrantyEfforts) {
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
