package com.rightprice.auth.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import io.swagger.annotations.ApiModelProperty;

@Entity
@Table(name = "synprod.MST_RP_Customer_Vertical_Mapping")
public class CustomerVerticalMapping implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "CUSTOMER_VERTICAL_MAP_ID")
	@ApiModelProperty(notes = "customer vertical map id")
	private int customerVerticalMapId;
	
	@Column(name = "VERTICAL_ID")
	@ApiModelProperty(notes = "customer vertical id")
	private int verticalId;
	
	@Column(name = "CUSTOMER_ID")
	@ApiModelProperty(notes = "customer id")
	private int customerId;
	
	@Column(name = "IS_ACTIVE")
	@ApiModelProperty(notes = "active status")
	private Integer isActive = 1;

	@Column(name = "CREATED_BY", updatable = false)
	@ApiModelProperty(notes = "Created by")
	private String createdBy;

	@Column(name = "CREATED_ON", updatable = false)
	@ApiModelProperty(notes = "Created date")
	private String createdOn;

	@Column(name = "UPDATED_BY")
	@ApiModelProperty(notes = "Updated by")
	private String updatedBy;

	@Column(name = "UPDATED_ON")
	@ApiModelProperty(notes = "Updated date")
	private String updatedOn;
	
	@OneToOne(cascade = javax.persistence.CascadeType.ALL)
	@JoinColumn(name ="CUSTOMER_ID",referencedColumnName = "CUSTOMER_ID",insertable=false, updatable=false)
	private Customer customer;
	
	@Transient
	private int requesterFlag = 0;
	
	public int getCustomerVerticalMapId() {
		return customerVerticalMapId;
	}

	public void setCustomerVerticalMapId(int customerVerticalMapId) {
		this.customerVerticalMapId = customerVerticalMapId;
	}

	public int getVerticalId() {
		return verticalId;
	}

	public void setVerticalId(int verticalId) {
		this.verticalId = verticalId;
	}

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
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

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public int getRequesterFlag() {
		return requesterFlag;
	}

	public void setRequesterFlag(int requesterFlag) {
		this.requesterFlag = requesterFlag;
	}

}
