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
@ApiModel(value = "FPDeal Tower details", description = "FPDeal Tower details")
@Table(name = "synprod.RP_Fix_Deal_Location")
public class FPDealTower implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Deal_auto_Tower_Id")
	private Integer dealautoTowerId;
	
	@Column(name = "RP_DEAL_VERSION_ID")
	private Integer dealVersionId;
	
	@Column(name = "Deal_Tower_Id")
	private Integer dealTowerId;
	
	@Column(name = "City_Description")
	private String cityDisc;
	
	@Column(name = "deal_Tower_name")
	private String towerName;
	
	@Column(name = "Country_Id")
	private Integer countryId;
	
	@Column(name = "City_Id")
	private Integer cityId;

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
	
	@Column(name = "TransitionMonth")
	@ApiModelProperty(notes = "TransitionMonth")
	private Double transitionMonth;

	public Integer getDealautoTowerId() {
		return dealautoTowerId;
	}

	public void setDealautoTowerId(Integer dealautoTowerId) {
		this.dealautoTowerId = dealautoTowerId;
	}

	public Integer getDealVersionId() {
		return dealVersionId;
	}

	public void setDealVersionId(Integer dealVersionId) {
		this.dealVersionId = dealVersionId;
	}

	public Integer getDealTowerId() {
		return dealTowerId;
	}

	public void setDealTowerId(Integer dealTowerId) {
		this.dealTowerId = dealTowerId;
	}

	public String getTowerName() {
		return towerName;
	}

	public void setTowerName(String towerName) {
		this.towerName = towerName;
	}

	public Integer getCountryId() {
		return countryId;
	}

	public void setCountryId(Integer countryId) {
		this.countryId = countryId;
	}

	public Integer getCityId() {
		return cityId;
	}

	public void setCityId(Integer cityId) {
		this.cityId = cityId;
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

	public String getCityDisc() {
		return cityDisc;
	}

	public void setCityDisc(String cityDisc) {
		this.cityDisc = cityDisc;
	}

	public Double getTransitionMonth() {
		return transitionMonth;
	}

	public void setTransitionMonth(Double transitionMonth) {
		this.transitionMonth = transitionMonth;
	}
}
