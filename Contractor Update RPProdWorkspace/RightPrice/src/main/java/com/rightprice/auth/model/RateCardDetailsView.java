package com.rightprice.auth.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
@Entity
public class RateCardDetailsView {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "RC_ID")
	private Integer rcId;
	
	@Column(name = "CUSTOMER_VERTICAL_MAP_ID")
	private Integer customerVerticalMapId;
	
	@Column(name = "RC_NAME")
	private String rcName;
	
	@Column(name = "RC_START_DATE")
	private String rcStartDate;
	
	@Column(name = "RC_END_DATE")
	private String rcEndDate;

	@Column(name = "TCV")
	private double tvc;
	
	@Column(name = "VOLUME_DISCOUNT")
	private Integer volumeDiscount;

	@Column(name = "APPLICABLE_YEARS")
	private Integer applicableYears;
	
	@Column(name = "EXPECTED_ONSITE_RESOURCE_PERCENTAGE")
	private Double expectedOnsiteResourcePercentage;
	
	@Column(name = "EXPECTED_OFFSHORE_RESOURCE_PERCENTAGE")
	private Integer expectedOffshoreResourcePercentage;
	
	@Column(name = "CONSOLIDATED_RC_CURRENCY_ID")
	private Integer consolidatedRcCurrencyId;
	
	@Column(name = "VERTICAL_ID")
	private Integer VerticalId;
	
	@Column(name = "STATUS")
	private String Status;
	
	@Column(name = "CUSTOMER_NAME")
	private String customerName;
	
	@Column(name = "CALCULATED_GM_PERCENTAGE")
	private Double calculatedGMPercentage;
	
	@Column(name = "CALCULATED_RP_GM_PERCENTAGE_POST_DISCOUNT")
	private Double  calculatedRPGMPercentagePostDiscount;
	
	@Column(name = "CURRENCY")
	private String currency;
	
	@Column(name = "CURRENT_APPROVAL_LEVEL")
	private Integer currentApprovalLevel;

	@Column(name = "ONSITE_WORK_HOURS")
	private Integer onsiteWorkHours;
	
	@Column(name = "OFFSHORE_WORK_HOURS")
	private Integer offShoreWorkHours;

	@Column(name = "Calculated_GM_Percentage_Post_Discount")
	private Integer calculatedGMPercentagePostDiscount;

	public Integer getRcId() {
		return rcId;
	}

	public void setRcId(Integer rcId) {
		this.rcId = rcId;
	}

	public Integer getCustomerVerticalMapId() {
		return customerVerticalMapId;
	}

	public void setCustomerVerticalMapId(Integer customerVerticalMapId) {
		this.customerVerticalMapId = customerVerticalMapId;
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

	public double getTvc() {
		return tvc;
	}

	public void setTvc(double tvc) {
		this.tvc = tvc;
	}

	public Integer getVolumeDiscount() {
		return volumeDiscount;
	}

	public void setVolumeDiscount(Integer volumeDiscount) {
		this.volumeDiscount = volumeDiscount;
	}

	public Integer getApplicableYears() {
		return applicableYears;
	}

	public void setApplicableYears(Integer applicableYears) {
		this.applicableYears = applicableYears;
	}

	public Double getExpectedOnsiteResourcePercentage() {
		return expectedOnsiteResourcePercentage;
	}

	public void setExpectedOnsiteResourcePercentage(Double expectedOnsiteResourcePercentage) {
		this.expectedOnsiteResourcePercentage = expectedOnsiteResourcePercentage;
	}

	public Integer getExpectedOffshoreResourcePercentage() {
		return expectedOffshoreResourcePercentage;
	}

	public void setExpectedOffshoreResourcePercentage(Integer expectedOffshoreResourcePercentage) {
		this.expectedOffshoreResourcePercentage = expectedOffshoreResourcePercentage;
	}

	public Integer getConsolidatedRcCurrencyId() {
		return consolidatedRcCurrencyId;
	}

	public void setConsolidatedRcCurrencyId(Integer consolidatedRcCurrencyId) {
		this.consolidatedRcCurrencyId = consolidatedRcCurrencyId;
	}

	public Integer getVerticalId() {
		return VerticalId;
	}

	public void setVerticalId(Integer verticalId) {
		VerticalId = verticalId;
	}

	public String getStatus() {
		return Status;
	}

	public void setStatus(String status) {
		Status = status;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public Double getCalculatedGMPercentage() {
		return calculatedGMPercentage;
	}

	public void setCalculatedGMPercentage(Double calculatedGMPercentage) {
		this.calculatedGMPercentage = calculatedGMPercentage;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public Integer getCurrentApprovalLevel() {
		return currentApprovalLevel;
	}

	public void setCurrentApprovalLevel(Integer currentApprovalLevel) {
		this.currentApprovalLevel = currentApprovalLevel;
	}

	public Integer getOnsiteWorkHours() {
		return onsiteWorkHours;
	}

	public void setOnsiteWorkHours(Integer onsiteWorkHours) {
		this.onsiteWorkHours = onsiteWorkHours;
	}

	public Integer getOffShoreWorkHours() {
		return offShoreWorkHours;
	}

	public void setOffShoreWorkHours(Integer offShoreWorkHours) {
		this.offShoreWorkHours = offShoreWorkHours;
	}

	public Double getCalculatedRPGMPercentagePostDiscount() {
		return calculatedRPGMPercentagePostDiscount;
	}

	public void setCalculatedRPGMPercentagePostDiscount(Double calculatedRPGMPercentagePostDiscount) {
		this.calculatedRPGMPercentagePostDiscount = calculatedRPGMPercentagePostDiscount;
	}

	public Integer getCalculatedGMPercentagePostDiscount() {
		return calculatedGMPercentagePostDiscount;
	}

	public void setCalculatedGMPercentagePostDiscount(Integer calculatedGMPercentagePostDiscount) {
		this.calculatedGMPercentagePostDiscount = calculatedGMPercentagePostDiscount;
	}

	
}
