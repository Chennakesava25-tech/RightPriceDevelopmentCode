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
@ApiModel(value="MST_RP_FP_Deal_Indirect_Cost_Master",description="MST_RP_FP_Deal_Indirect_Cost_Master")
@Table(name = "synprod.MST_RP_FP_Deal_Indirect_Cost_Master")
public class DealIndrectCostMaster implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "INDIRECT_COST_MASTER_ID")
	@ApiModelProperty(notes="ID Auto Incremented")
	private Integer indirectCostMasterId;
	
	@Column(name = "INDIRECT_COST_TYPE")
	@ApiModelProperty(notes="INDIRECT_COST_TYPE")
	private Integer indirectCostType;
	
	
	@Column(name = "INDIRECT_COST_Description")
	@ApiModelProperty(notes="INDIRECT_COST_Description")
	private String indirectCostDescription;
	
	@Column(name = "COST_Year")
	@ApiModelProperty(notes="COST_Year")
	private Integer costYear;
	
	@Column(name = "Unit_Cost")
	@ApiModelProperty(notes="Unit_Cost")
	private Integer unitCost;

	@Column(name = "COMMENT")
	@ApiModelProperty(notes="COMMENT")
	private String comment;

	@Column(name ="IS_ACTIVE")
	private Integer isActive=1;
	
	@Column(name = "CREATED_BY", updatable=false)
	@ApiModelProperty(notes="Created by")
	private String createdBy;
	
	@Column(name = "CREATED_ON", updatable=false)
	@ApiModelProperty(notes="Created date")
	private String createdOn;
	
	@Column(name = "UPDATED_BY")
	@ApiModelProperty(notes="Updated by")
	private String updatedBy;
	
	@Column(name = "UPDATED_ON")
	@ApiModelProperty(notes="UPDATED_ON")
	private String updatedOn;
	
	
	@Column(name = "DISPLAY_INDICATOR")
	@ApiModelProperty(notes="DISPLAY_INDICATOR")
	private Integer displayIndicator;
	

	public Integer getIndirectCostMasterId() {
		return indirectCostMasterId;
	}

	public void setIndirectCostMasterId(Integer indirectCostMasterId) {
		this.indirectCostMasterId = indirectCostMasterId;
	}

	public Integer getIndirectCostType() {
		return indirectCostType;
	}

	public void setIndirectCostType(Integer indirectCostType) {
		this.indirectCostType = indirectCostType;
	}

	public String getIndirectCostDescription() {
		return indirectCostDescription;
	}

	public void setIndirectCostDescription(String indirectCostDescription) {
		this.indirectCostDescription = indirectCostDescription;
	}

	public Integer getCostYear() {
		return costYear;
	}

	public void setCostYear(Integer costYear) {
		this.costYear = costYear;
	}

	public Integer getUnitCost() {
		return unitCost;
	}

	public void setUnitCost(Integer unitCost) {
		this.unitCost = unitCost;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
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

	public Integer getDisplayIndicator() {
		return displayIndicator;
	}

	public void setDisplayIndicator(Integer displayIndicator) {
		this.displayIndicator = displayIndicator;
	}
}
