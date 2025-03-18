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
@ApiModel(value = "FPDeal Ratedcard details", description = "FPDeal Ratecard details")
@Table(name = "synprod.RP_FP_Deal_RateCards")
public class FPDealRatecard implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "FP_Deal_Rate_Card_Id")
	private Integer fPDealRateCardId ;
	
	@Column(name = "RP_Deal_Version_Id")
	private Integer dealVersionId;
	
	@Column(name = "Deal_RC_Id")
	private Integer dealRCId ;
	
	@Column(name = "Deal_Tower_ID")
	private Integer dealTowerId;
	
	@Column(name = "Deal_Auto_Tower_ID")
	private Integer dealautoTowerId;
	
	@Column(name = "GM_Percentage")
	private Double gmPercentage;

	@Column(name = "Is_Active")
	private Integer isActive = 1;

	@Column(name = "Created_By", updatable = false)
	@ApiModelProperty(notes = "Created by")
	private String createdBy;

	@Column(name = "Created_On", updatable = false)
	@ApiModelProperty(notes = "Created date")
	private String createdOn;

	@Column(name = "Updated_By")
	@ApiModelProperty(notes = "Updated by")
	private String updatedBy;

	@Column(name = "Updated_On")
	@ApiModelProperty(notes = "Updated date")
	private String updatedOn;

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

	public Integer getfPDealRateCardId() {
		return fPDealRateCardId;
	}

	public void setfPDealRateCardId(Integer fPDealRateCardId) {
		this.fPDealRateCardId = fPDealRateCardId;
	}

	public Integer getDealVersionId() {
		return dealVersionId;
	}

	public void setDealVersionId(Integer dealVersionId) {
		this.dealVersionId = dealVersionId;
	}

	public Integer getDealRCId() {
		return dealRCId;
	}

	public void setDealRCId(Integer dealRCId) {
		this.dealRCId = dealRCId;
	}

	public Integer getDealautoTowerId() {
		return dealautoTowerId;
	}

	public void setDealautoTowerId(Integer dealautoTowerId) {
		this.dealautoTowerId = dealautoTowerId;
	}

	public Double getGmPercentage() {
		return gmPercentage;
	}

	public void setGmPercentage(Double gmPercentage) {
		this.gmPercentage = gmPercentage;
	}

	public Integer getDealTowerId() {
		return dealTowerId;
	}

	public void setDealTowerId(Integer dealTowerId) {
		this.dealTowerId = dealTowerId;
	}
	
}
