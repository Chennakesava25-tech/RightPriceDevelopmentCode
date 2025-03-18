package com.rightprice.auth.model;

import java.io.Serializable;
import java.lang.reflect.Field;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.rightprice.auth.util.AppLoger;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@Entity
@ApiModel(value="Deal currency utilization details",description="Deal currency utilization details")
@Table(name = "synprod.RP_DEAL_CURRENCY_UTILIZATION")
public class DealCurrencyUtilization implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "DEAL_CURRENCY_UTILIZATION_ID")
	private Integer dealCurrencyUtilizationId;
	
	@Column(name = "RP_DEAL_VERSION_ID")
	private Integer dealVersionId;

	@Column(name = "BASE_COUNTRY_ID")
	private Integer baseCountryId;
	
	@Column(name = "UTILIZATION")
	private Double utilization;
	
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
	@ApiModelProperty(notes="Updated date")
	private String updatedOn;
	
	
	@OneToOne(cascade = javax.persistence.CascadeType.ALL)
	@JoinColumn(name ="BASE_COUNTRY_ID",referencedColumnName = "COUNTRY_ID",insertable=false, updatable=false)
	private Country country;
	
	@OneToOne(cascade = javax.persistence.CascadeType.ALL)
	@JoinColumn(name ="BASE_COUNTRY_ID",referencedColumnName = "COUNTRY_ID",insertable=false, updatable=false)
	private City city;
	

	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}

	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	public Integer getDealCurrencyUtilizationId() {
		return dealCurrencyUtilizationId;
	}

	public void setDealCurrencyUtilizationId(Integer dealCurrencyUtilizationId) {
		this.dealCurrencyUtilizationId = dealCurrencyUtilizationId;
	}

	public Integer getDealVersionId() {
		return dealVersionId;
	}

	public void setDealVersionId(Integer dealVersionId) {
		this.dealVersionId = dealVersionId;
	}

	public Integer getBaseCountryId() {
		return baseCountryId;
	}

	public void setBaseCountryId(Integer baseCountryId) {
		this.baseCountryId = baseCountryId;
	}

	public Double getUtilization() {
		return utilization;
	}

	public void setUtilization(Double utilization) {
		this.utilization = utilization;
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
	
	public String toString() {
		  StringBuilder result = new StringBuilder();
		  String newLine = System.getProperty("line.separator");

		  result.append( this.getClass().getName() );
		  result.append( " Object {" );
		  result.append(newLine);

		  //determine fields declared in this class only (no fields of superclass)
		  Field[] fields = this.getClass().getDeclaredFields();

		  //print field names paired with their values
		  for ( Field field : fields  ) {
		    result.append("  ");
		    try {
		      result.append( field.getName() );
		      result.append(": ");
		      //requires access to private field:
		      result.append( field.get(this) );
		    } catch ( IllegalAccessException ex ) {
		      AppLoger.APPLOGGER.info(ex);
		    }
		    result.append(newLine);
		  }
		  result.append("}");

		  return result.toString();
		}
}
