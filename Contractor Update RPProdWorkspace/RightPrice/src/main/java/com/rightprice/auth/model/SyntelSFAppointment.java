package com.rightprice.auth.model;


import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import io.swagger.annotations.ApiModel;

@Entity
@ApiModel(value="SyntelSalesForceAppointment",description="SyntelSalesForceAppointmentValues")
@Table(name = "DBO.SY_SF_APPOINTMENT_TBL")
public class SyntelSFAppointment implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "APPOINTMENT_ID")
	private String appointmentId;

	@Column(name = "APPOINTMENT_SUBJECT")
	private String appointmentSubject;
	
	@Column(name = "APPOINTMENT_TYPE")
	private String appointmentType;
	
	@Column(name = "CUSTOMER_NAME")
	private String customerName;
	
	@Column(name = "CUSTOMER_ID")
	private String customerId;
	
	@Column(name = "ATTENDEES")
	private String attendees;
	
	
	@Column(name = "START_DATE")
	private String startDate;
	
	@Column(name = "DURATION")
	private String duration;
	
	@Column(name = "STATUS")
	private String status;
	
	@Column(name = "CREATED_DATE")
	private String createdDate;
	
	@Column(name = "CREATED_BY")
	private String createdBy;
	
	
	@Column(name = "CREATOR_DAS")
	private String creatorDas;
	
	@Column(name = "LAST_MODIFIED_DATE")
	private String lastModifiedDate;

	public String getAppointmentId() {
		return appointmentId;
	}

	public void setAppointmentId(String appointmentId) {
		this.appointmentId = appointmentId;
	}

	public String getAppointmentSubject() {
		return appointmentSubject;
	}

	public void setAppointmentSubject(String appointmentSubject) {
		this.appointmentSubject = appointmentSubject;
	}

	public String getAppointmentType() {
		return appointmentType;
	}

	public void setAppointmentType(String appointmentType) {
		this.appointmentType = appointmentType;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getAttendees() {
		return attendees;
	}

	public void setAttendees(String attendees) {
		this.attendees = attendees;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getCreatorDas() {
		return creatorDas;
	}

	public void setCreatorDas(String creatorDas) {
		this.creatorDas = creatorDas;
	}

	public String getLastModifiedDate() {
		return lastModifiedDate;
	}

	public void setLastModifiedDate(String lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	

}

