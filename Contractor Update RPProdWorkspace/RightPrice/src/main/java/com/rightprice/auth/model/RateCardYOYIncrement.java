package com.rightprice.auth.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@Entity
@ApiModel(value="Rate Card Year Increment Percent",description="Master Roles")
@Table(name = "synprod.RP_RATE_CARD_YOY_INCREMENT_PERCENT")
public class RateCardYOYIncrement implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "RATE_CARD_YOY_ID")
	@ApiModelProperty(notes="Rate Card YOY ID")
	private Integer rcCardYoyId;
	
	
	@Column(name = "RC_ID")
	@ApiModelProperty(notes="Rate Card ID")
	private Integer rcId;
	
	
	@Column(name = "YOY_INC_START_MONTH")
	@ApiModelProperty(notes="Start Month")
	private Integer YoyIncStartMonth;
	
	
	@Column(name = "YOY_INC_YEAR")
	@ApiModelProperty(notes="Year")
	private Integer yoyIncYear;
	
	@Column(name = "STEP_YEAR")
	@ApiModelProperty(notes="Step Year")
	private Integer stepYear;
	
	
	@Column(name = "INCREMENT_PERCENT_ONSITE")
	@ApiModelProperty(notes="Increment Onsite Percentage")
	private Double incrementPercentOnsite;
	
	@Column(name = "INCREMENT_PERCENT_OFFSHORE")
	@ApiModelProperty(notes="Increment Offshore Percentage")
	private Double incrementPercentOffshore;
	
	@Column(name = "IS_ACTIVE")
	@ApiModelProperty(notes="Active status")
	private int isActive;
	
	@Column(name = "CREATED_BY", updatable=false)
	@ApiModelProperty(notes="Created by")
	private String createdBy;
	
	@Column(name = "CREATED_ON", updatable=false)
	@ApiModelProperty(notes="Created date")
	private String createdDate;
	
	@Column(name = "UPDATED_BY")
	@ApiModelProperty(notes="Updated by")
	private String updatedBy;
	
	@Column(name = "UPDATED_ON")
	@ApiModelProperty(notes="Updated date")
	private String updatedDate;
	
	@Transient
	static Integer previousYear;
	
	
	public Integer getRcCardYoyId() {
		return rcCardYoyId;
	}

	public void setRcCardYoyId(Integer rcCardYoyId) {
		this.rcCardYoyId = rcCardYoyId;
	}

	public Integer getRcId() {
		return rcId;
	}

	public void setRcId(Integer rcId) {
		this.rcId = rcId;
	}

	public Integer getYoyIncStartMonth() {
		return YoyIncStartMonth;
	}

	public void setYoyIncStartMonth(Integer yoyIncStartMonth) {
		YoyIncStartMonth = yoyIncStartMonth;
	}

	public Integer getYoyIncYear() {
		return yoyIncYear;
	}

	public void setYoyIncYear(Integer yoyIncYear) {
		this.yoyIncYear = yoyIncYear;
	}

	public Integer getStepYear() {
		return stepYear;
	}

	public void setStepYear(Integer stepYear) {
		this.stepYear = stepYear;
	}

	public Double getIncrementPercentOnsite() {
		return incrementPercentOnsite;
	}

	public void setIncrementPercentOnsite(Double incrementPercentOnsite) {
		this.incrementPercentOnsite = incrementPercentOnsite;
	}

	public Double getIncrementPercentOffshore() {
		return incrementPercentOffshore;
	}

	public void setIncrementPercentOffshore(Double incrementPercentOffshore) {
		this.incrementPercentOffshore = incrementPercentOffshore;
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

	public String getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public String getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(String updatedDate) {
		this.updatedDate = updatedDate;
	}

	public Integer getPreviousYear() {
		return previousYear;
	}

	public void setPreviousYear(Integer previousYear) {
		this.previousYear = previousYear;
	}
}
