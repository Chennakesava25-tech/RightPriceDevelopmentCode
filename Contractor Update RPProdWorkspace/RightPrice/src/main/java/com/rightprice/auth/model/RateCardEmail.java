package com.rightprice.auth.model;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import com.rightprice.auth.util.AppLoger;
import com.rightprice.auth.util.DateUtil;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@Entity
public class RateCardEmail implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "RC_Summary_Id")
	private Integer rcSummaryId;
	
	@Column(name = "RC_Id")
	private Integer rcId;
	
	@Column(name = "Location")
	private String Location;
	
	/*@Column(name = "COUNTRY_ID")
	private Integer countryId;
	
	@Column(name = "Country_Name")
	private String countryName;
	
	@Column(name = "CITY_ID")
	private int cityId;

	@Column(name = "CITY_NAME")
	private String cityName;*/

	@Column(name = "Average_GM")	
	private Double averageGM;
	
	@Column(name = "Average_Before_GM")	
	private Double averagebeforeGM;

	@Column(name = "Average_Before_MGM")	
	private Double averagebeforeMGM;
	
	@Column(name = "Average_BR")	
	private Double averageBR;
	
	@Column(name ="IS_ACTIVE")
	private Integer isActive=1;
	
	@Column(name = "Utilization_Mix")
	private Double utilizationMix;
	
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

/*	public Integer getCountryId() {
		return countryId;
	}

	public void setCountryId(Integer countryId) {
		this.countryId = countryId;
	}

	public String getCountryName() {
		return countryName;
	}

	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}

	public int getCityId() {
		return cityId;
	}

	public void setCityId(int cityId) {
		this.cityId = cityId;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}*/

	public Double getAverageGM() {
		return averageGM;
	}

	public void setAverageGM(Double averageGM) {
		this.averageGM = averageGM;
	}

	public Double getAveragebeforeGM() {
		return averagebeforeGM;
	}

	public void setAveragebeforeGM(Double averagebeforeGM) {
		this.averagebeforeGM = averagebeforeGM;
	}

	public Double getAveragebeforeMGM() {
		return averagebeforeMGM;
	}

	public void setAveragebeforeMGM(Double averagebeforeMGM) {
		this.averagebeforeMGM = averagebeforeMGM;
	}

	public Double getAverageBR() {
		return averageBR;
	}

	public void setAverageBR(Double averageBR) {
		this.averageBR = averageBR;
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

	public Integer getRcSummaryId() {
		return rcSummaryId;
	}

	public void setRcSummaryId(Integer rcSummaryId) {
		this.rcSummaryId = rcSummaryId;
	}

	public Double getUtilizationMix() {
		return utilizationMix;
	}

	public void setUtilizationMix(Double utilizationMix) {
		this.utilizationMix = utilizationMix;
	}

	public Integer getRcId() {
		return rcId;
	}

	public void setRcId(Integer rcId) {
		this.rcId = rcId;
	}

	public String getLocation() {
		return Location;
	}

	public void setLocation(String location) {
		Location = location;
	}
}
