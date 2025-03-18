package com.rightprice.auth.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import io.swagger.annotations.ApiModelProperty;

@Entity
@Table(name="synprod.RP_Rate_Card_YOY_Increment_Percent")
public class YOYIncrementPercent implements Serializable{
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="Rate_Card_YOY_ID")
	@ApiModelProperty(notes="auto generated Rate_Card_YOY_ID")
	private Integer rateCardYOYId;
	
	/*@Column(name="RC_Id")
	@ApiModelProperty(notes="Rate card ID")
	private Integer rcId;*/
	
	
	@Column(name="YOY_Inc_Start_Month")
	@ApiModelProperty(notes="YOY_Inc_Start_Month")
	private Integer yOYIncStartMonth;
	
	@Column(name="YOY_Inc_Year")
	@ApiModelProperty(notes="YOY_Inc_Year")
	private Integer yOYIncYear;
	
	@Column(name="Step_Year")
	@ApiModelProperty(notes="Step_Year")
	private Integer stepYear;
	
	@Column(name="Increment_Percent_Onsite")
	@ApiModelProperty(notes="Increment_Percent_Onsite")
	private Double incrementPercentOnsite;
	
	@Column(name="Increment_Percent_Offshore")
	@ApiModelProperty(notes="Increment_Percent_Offshore")
	private Double incrementPercentOffshore;
	
	@Column(name ="IS_ACTIVE")
	@ApiModelProperty(notes="active status")
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
	@ApiModelProperty(notes="Updated date")
	private String updatedOn;

	
	
	public Integer getRateCardYOYId() {
		return rateCardYOYId;
	}

	public void setRateCardYOYId(Integer rateCardYOYId) {
		this.rateCardYOYId = rateCardYOYId;
	}

	public Integer getyOYIncStartMonth() {
		return yOYIncStartMonth;
	}

	public void setyOYIncStartMonth(Integer yOYIncStartMonth) {
		this.yOYIncStartMonth = yOYIncStartMonth;
	}

	public Integer getyOYIncYear() {
		return yOYIncYear;
	}

	public void setyOYIncYear(Integer yOYIncYear) {
		this.yOYIncYear = yOYIncYear;
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

	/*public Integer getRcId() {
		return rcId;
	}

	public void setRcId(Integer rcId) {
		this.rcId = rcId;
	}*/

	/*public RateCardDetails getRateCardDetails() {
		return rateCardDetails;
	}

	public void setRateCardDetails(RateCardDetails rateCardDetails) {
		this.rateCardDetails = rateCardDetails;
	}
	*/
	

}
