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
@ApiModel(value="Right Price Access Control",description="AccessControl Model Attributes")
@Table(name = "synprod.MST_RP_Access_Control")
public class AccessControl implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID")
	@ApiModelProperty(notes="ID")
	private Integer Id;
	
	@Column(name = "LAN_ID")
	@ApiModelProperty(notes="EMP LAN ID ")
	private String lanId;
	
	@Column(name = "CUSTOMER_VERTICAL_MAP_ID")
	@ApiModelProperty(notes="CUSTOMER VERTICAL MAP ID ")
	private Integer customerVerticalMapId
	;
	@Column(name = "VERTICAL_ID")
	@ApiModelProperty(notes="EMP VERTICAL ID ")
	private Integer verticalId;
	
	@Column(name = "VERTICAL_NAME")
	@ApiModelProperty(notes="EMP VERTICAL NAME")
	private String verticalName;
	
	@Column(name = "CUSTOMER_ID")
	@ApiModelProperty(notes="CUSTOMER ID")
	private Integer customerId;
	
	@Column(name = "CUSTOMER_NAME")
	@ApiModelProperty(notes="CUSTOMER NAME")
	private String customerName;
	
	@Column(name = "EMPLOYEE_NAME")
	@ApiModelProperty(notes="EMPLOYEE NAME")
	private String employeeName;
	
	@Column(name = "IS_ACTIVE")
	@ApiModelProperty(notes="IS ACTIVE")
	private Integer isActive;
	
	@Column(name = "CREATED_BY", updatable=false)
	@ApiModelProperty(notes="CREATED BY")
	private String createdBy;
	
	@Column(name = "CREATED_ON", updatable=false)
	@ApiModelProperty(notes="CREATED ON")
	private String createdOn;
	
	@Column(name = "UPDATED_BY")
	@ApiModelProperty(notes="UPDATED BY")
	private String updatedBy;
	
	@Column(name = "UPDATED_ON")
	@ApiModelProperty(notes="UPDATED ON")
	private String updatedOn;
	
	@OneToOne(cascade = javax.persistence.CascadeType.ALL)
	@JoinColumn(name ="CUSTOMER_ID",referencedColumnName = "CUSTOMER_ID",insertable=false, updatable=false)
	private Customer customer;
	
	
	@Transient
	private int requesterFlag = 1;


	public Integer getId() {
		return Id;
	}

	public void setId(Integer id) {
		Id = id;
	}

	public String getLanId() {
		return lanId;
	}

	public void setLanId(String lanId) {
		this.lanId = lanId;
	}

	public Integer getCustomerVerticalMapId() {
		return customerVerticalMapId;
	}

	public void setCustomerVerticalMapId(Integer customerVerticalMapId) {
		this.customerVerticalMapId = customerVerticalMapId;
	}

	public Integer getVerticalId() {
		return verticalId;
	}

	public void setVerticalId(Integer verticalId) {
		this.verticalId = verticalId;
	}

	public String getVerticalName() {
		return verticalName;
	}

	public void setVerticalName(String verticalName) {
		this.verticalName = verticalName;
	}

	public Integer getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
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

	public int getRequesterFlag() {
		return requesterFlag;
	}

	public void setRequesterFlag(int requesterFlag) {
		this.requesterFlag = requesterFlag;
	}

	/*public CustomerVerticalMapping getCustomer() {
		return customer;
	}

	public void setCustomer(CustomerVerticalMapping customer) {
		this.customer = customer;
	}*/

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	
}
