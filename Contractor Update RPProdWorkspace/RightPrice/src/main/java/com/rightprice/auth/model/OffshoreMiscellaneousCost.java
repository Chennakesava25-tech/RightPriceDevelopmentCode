package com.rightprice.auth.model;

/*import java.io.Serializable;*/

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


@Entity
@ApiModel(value="MiscellaneousCost",description="Miscellaneous Cost Attributes")
@Table(name = "synprod.MST_RP_Offshore_Miscellaneous_Cost") 
public class OffshoreMiscellaneousCost {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "MISCELLANIOUS_COST_ID")
	@ApiModelProperty(notes="Miscellanious Cost Id")
	private int miscellaniousCostId;

	@Column(name = "CITY_ID")
	@ApiModelProperty(notes="City Id")
	private int cityId;
	
	@OneToOne(cascade = javax.persistence.CascadeType.ALL)
    @JoinColumn(name = "CITY_ID",referencedColumnName = "CITY_ID", insertable = false, updatable = false)
	private City Miscellaneous;
	
	public City getMiscellaneous() {
		return Miscellaneous;
	}

	public void setMiscellaneous(City miscellaneous) {
		Miscellaneous = miscellaneous;
	}

	@Column(name = "SECOND_SHIFT_COST")
	@ApiModelProperty(notes=" Second Shift Cost")
	private int secondShiftCost;
	
	@Column(name = "NIGHT_SHIFT_COST")
	@ApiModelProperty(notes=" Night Shift Cost")
	private int nightShiftCost;
	
	@Column(name = "SECOND_SHIFT_TRANSPORT_COST")
	@ApiModelProperty(notes=" Second Shift Cost")
	private int secondShiftTransportCost;
	
	@Column(name = "NIGHT_SHIFT_TRANSPORT_COST")
	@ApiModelProperty(notes=" Night Shift Transport Cost")
	private int nightShiftTransportCost;
	
	@Column(name = "IS_ACTIVE")
	@ApiModelProperty(notes=" Is Active")
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

	public int getMiscellaniousCostId() {
		return miscellaniousCostId;
	}

	public void setMiscellaniousCostId(int miscellaniousCostId) {
		this.miscellaniousCostId = miscellaniousCostId;
	}

	public int getCityId() {
		return cityId;
	}

	public void setCityId(int cityId) {
		this.cityId = cityId;
	}

	public int getSecondShiftCost() {
		return secondShiftCost;
	}

	public void setSecondShiftCost(int secondShiftCost) {
		this.secondShiftCost = secondShiftCost;
	}

	public int getNightShiftCost() {
		return nightShiftCost;
	}

	public void setNightShiftCost(int nightShiftCost) {
		this.nightShiftCost = nightShiftCost;
	}

	public int getSecondShiftTransportCost() {
		return secondShiftTransportCost;
	}

	public void setSecondShiftTransportCost(int secondShiftTransportCost) {
		this.secondShiftTransportCost = secondShiftTransportCost;
	}

	public int getNightShiftTransportCost() {
		return nightShiftTransportCost;
	}

	public void setNightShiftTransportCost(int nightShiftTransportCost) {
		this.nightShiftTransportCost = nightShiftTransportCost;
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
	
}
