package com.rightprice.auth.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


@Entity
public class ResourceForcastData implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "CRM_Deal_Id")
	@ApiModelProperty(notes="CRM_Deal_Id")
	private Integer cRMDealId;

	@Column(name = "Customer_Name")
	@ApiModelProperty(notes="Customer_Name")
	private String customerName;
	

	@Column(name = "Customer_Id")
	@ApiModelProperty(notes="Customer_Id")
	private Integer  customerId;
	
	@Column(name = "Deal_Description")
	@ApiModelProperty(notes="Deal_Description")
	private String Deal_Description;

	@Column(name = "Deal_Type_Id")
	@ApiModelProperty(notes="Deal_Type_Id")
	private String Deal_Type_Id;
	
	@Column(name = "Deal_Start_Date")
	@ApiModelProperty(notes="Deal_Start_Date")
	private String Deal_Start_Date;
	
	@Column(name = "Deal_End_Date")
	@ApiModelProperty(notes="Deal_End_Date")
	private String Deal_End_Date;
	
	@Column(name = "Industry")
	@ApiModelProperty(notes="Industry")
	private String industry;
		

	public String getIndustry() {
		return industry;
	}

	public void setIndustry(String industry) {
		this.industry = industry;
	}

	public String getDeal_Description() {
		return Deal_Description;
	}

	public void setDeal_Description(String deal_Description) {
		Deal_Description = deal_Description;
	}

	public String getDeal_Type_Id() {
		return Deal_Type_Id;
	}

	public void setDeal_Type_Id(String deal_Type_Id) {
		Deal_Type_Id = deal_Type_Id;
	}

	public String getDeal_Start_Date() {
		return Deal_Start_Date;
	}

	public void setDeal_Start_Date(String deal_Start_Date) {
		Deal_Start_Date = deal_Start_Date;
	}

	public String getDeal_End_Date() {
		return Deal_End_Date;
	}

	public void setDeal_End_Date(String deal_End_Date) {
		Deal_End_Date = deal_End_Date;
	}

	public Integer getcRMDealId() {
		return cRMDealId;
	}

	public void setcRMDealId(Integer cRMDealId) {
		this.cRMDealId = cRMDealId;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public Integer getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}
	
	
}
