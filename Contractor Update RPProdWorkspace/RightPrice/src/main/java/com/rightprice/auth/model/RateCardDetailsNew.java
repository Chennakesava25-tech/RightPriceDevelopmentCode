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
@ApiModel(value="Rate card details New",description="Rate card details Model Attributes")
@Table(name = "synprod.RP_V2_RATE_CARD_DETAILS")

public class RateCardDetailsNew implements Serializable{
	
	private static long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Rate_card_id")
	private Integer rcId;
	
	
	@Column(name = "CUSTOMER_id") private Integer customerId;
	
	
	@Column(name = "customer_desc")
	private String customerdesc;
	
	@Column(name = "Rate_Card_Name")
	private String rcName;
	 

	@Column(name = "City_Id")
	private String cityId;
	
	@Column(name = "Country_Id")
	private String countryId;
	
	
	@Column(name = "start_date")
	private String rcStartDate;
	
	@Column(name = "End_Date")
	private String rcEndDate;
	
	@Column(name = "Expected_TCV")
	private Double tvc;
	
	@Column(name = "industry")
	private Integer isItKpo;

		
	@Column(name = "applicable_months")
	private Integer applicableMonths;
	
	@Column(name = "offshore_percentage")
	private Double expectedOnsiteResourcePercentage;
	
	@Column(name = "onsite_percentage")
	private double expectedOffshoreResourcePercentage;
	
	@Column(name = "blended_rate")
	private Integer blendedRate;
	
	@Column(name = "Blended_Cost")
	private Double blendedCost;
	
	/*
	 * @Column(name = "MARGIN") private Double margin;
	 */
	
	@Column(name = "Margin_Before_Discount")
	private Double marginbeforediscount;
	
	@Column(name = "Margin_After_Discount")
	private Double marginafterdiscount;
	
	@Column(name = "Discount")
	private Double discount;
	
	@Column(name = "Billing_Currency")
	private Integer consolidatedRcCurrencyId;

	 @Column(name ="Is_Active") private Integer isActive=1; 
	 
	
	@Column(name = "base_country")
	@ApiModelProperty(notes="base_country")
	private String baseCountryName;
	
	@Column(name = "city")
	@ApiModelProperty(notes="city")
	private String baseCityName;
	
	
	  @Column(name = "Created_By", updatable=false)
	  
	  @ApiModelProperty(notes="Created by") private String createdBy;
	  
	  @Column(name = "Created_On", updatable=false)
	
	 @ApiModelProperty(notes="Created date") private String createdOn;
	  
	  @Column(name = "Updated_By")
	  
	  @ApiModelProperty(notes="Updated by") private String updatedBy;
	 
	  @Column(name = "Updated_On")
	  
	  @ApiModelProperty(notes="Updated date") private String updatedOn;
	  
		/*
		 * @Column(name = "STATUS_INDICATOR")
		 * 
		 * @ApiModelProperty(notes="STATUS_INDICATOR") private String statusIndicator;
		 */
	  

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public static void setSerialversionuid(long serialversionuid) {
		serialVersionUID = serialversionuid;
	}

	public Integer getRcId() {
		return rcId;
	}

	public void setRcId(Integer rcId) {
		this.rcId = rcId;
	}

	

	public String getCustomerdesc() {
		return customerdesc;
	}

	public void setCustomerdesc(String customerdesc) {
		this.customerdesc = customerdesc;
	}

	public String getRcName() {
		return rcName;
	}

	public void setRcName(String rcName) {
		this.rcName = rcName;
	}

	public String getRcStartDate() {
		return rcStartDate;
	}

	public void setRcStartDate(String rcStartDate) {
		this.rcStartDate = rcStartDate;
	}

	public String getRcEndDate() {
		return rcEndDate;
	}

	public void setRcEndDate(String rcEndDate) {
		this.rcEndDate = rcEndDate;
	}

	public Double getTvc() {
		return tvc;
	}

	public void setTvc(Double tvc) {
		this.tvc = tvc;
	}

	public Integer getIsItKpo() {
		return isItKpo;
	}

	public void setIsItKpo(Integer isItKpo) {
		this.isItKpo = isItKpo;
	}

	public Integer getApplicableMonths() {
		return applicableMonths;
	}

	public void setApplicableMonths(Integer applicableMonths) {
		this.applicableMonths = applicableMonths;
	}

	public Double getExpectedOnsiteResourcePercentage() {
		return expectedOnsiteResourcePercentage;
	}

	public void setExpectedOnsiteResourcePercentage(Double expectedOnsiteResourcePercentage) {
		this.expectedOnsiteResourcePercentage = expectedOnsiteResourcePercentage;
	}

	public double getExpectedOffshoreResourcePercentage() {
		return expectedOffshoreResourcePercentage;
	}

	public void setExpectedOffshoreResourcePercentage(double expectedOffshoreResourcePercentage) {
		this.expectedOffshoreResourcePercentage = expectedOffshoreResourcePercentage;
	}

	public Integer getBlendedRate() {
		return blendedRate;
	}

	public void setBlendedRate(Integer blendedRate) {
		this.blendedRate = blendedRate;
	}

	public Double getBlendedCost() {
		return blendedCost;
	}

	public void setBlendedCost(Double blendedCost) {
		this.blendedCost = blendedCost;
	}

	/*
	 * public Double getMargin() { return margin; }
	 * 
	 * public void setMargin(Double margin) { this.margin = margin; }
	 */

	public Double getMarginbeforediscount() {
		return marginbeforediscount;
	}

	public void setMarginbeforediscount(Double marginbeforediscount) {
		this.marginbeforediscount = marginbeforediscount;
	}

	public Double getMarginafterdiscount() {
		return marginafterdiscount;
	}

	public void setMarginafterdiscount(Double marginafterdiscount) {
		this.marginafterdiscount = marginafterdiscount;
	}

	public Double getDiscount() {
		return discount;
	}

	public void setDiscount(Double discount) {
		this.discount = discount;
	}

	public Integer getConsolidatedRcCurrencyId() {
		return consolidatedRcCurrencyId;
	}

	public void setConsolidatedRcCurrencyId(Integer consolidatedRcCurrencyId) {
		this.consolidatedRcCurrencyId = consolidatedRcCurrencyId;
	}

	public String getBaseCountryName() {
		return baseCountryName;
	}

	public void setBaseCountryName(String baseCountryName) {
		this.baseCountryName = baseCountryName;
	}

	public String getBaseCityName() {
		return baseCityName;
	}

	public void setBaseCityName(String baseCityName) {
		this.baseCityName = baseCityName;
	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public static void setSerialVersionUID(long serialVersionUID) {
		RateCardDetailsNew.serialVersionUID = serialVersionUID;
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

	public Integer getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}

	
	  public Integer getIsActive() { return isActive; }
	  
	  public void setIsActive(Integer isActive) { this.isActive = isActive; }

	public String getCityId() {
		return cityId;
	}

	public void setCityId(String cityId) {
		this.cityId = cityId;
	}

	public String getCountryId() {
		return countryId;
	}

	public void setCountryId(String countryId) {
		this.countryId = countryId;
	}

	 


	
	 

	
	  
	

	/*
	 * public String getStatusIndicator() { return statusIndicator; }
	 * 
	 * public void setStatusIndicator(String statusIndicator) { this.statusIndicator
	 * = statusIndicator; }
	 */

	
	
	
	
}
