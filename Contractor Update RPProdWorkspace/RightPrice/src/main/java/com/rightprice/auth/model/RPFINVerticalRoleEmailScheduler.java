package com.rightprice.auth.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;

import io.swagger.annotations.ApiModel;

@Entity
@ApiModel(value="Rate card details",description="Rate card details Model Attributes")
@Table(name = "synprod.RP_FIN_Vertical_Role")
@DynamicUpdate
public class RPFINVerticalRoleEmailScheduler {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "SR_NO")
	private Integer srNo;
	
	
	public Integer getSrNo() {
		return srNo;
	}

	public void setSrNo(Integer srNo) {
		this.srNo = srNo;
	}

	@Column(name = "PROCESS_INSTANCE")
	private Integer processId;
	
	@Column(name = "OPRID")
	private String oprID;
	
	
	@Column(name = "EMPLID")
	private String empId;
	
	@Column(name = "old_Vertical_Id")
	private String oldVerticalId;

	@Column(name = "Old_Sub_Vertical_id")
	private String suboldVerticalId;
	
	@Column(name = "Old_Vertical_Name")
	private String oldVerticalName;
	
	@Column(name = "Old_Sub_Vertical_Name")
	private String oldsubVerticalName;

	@Column(name = "New_Vertical_Id")
	private String newVerticalId;
	
	
	@Column(name = "New_Sub_Vertical_Id")
	private String newSubVerticalId;
	
	@Column(name = "New_Vertical_Name")
	private String newVerticalName;
	
	@Column(name = "New_Sub_Vertical_Name")
	private String newSubVerticalName;
	
	@Column(name = "Insertion_On")
	private String isnsrtedOn;
	
	@Column(name = "Email_Flag")
	private String emailFlag;

	public Integer getProcessId() {
		return processId;
	}

	public void setProcessId(Integer processId) {
		this.processId = processId;
	}

	public String getOprID() {
		return oprID;
	}

	public void setOprID(String oprID) {
		this.oprID = oprID;
	}

	public String getEmpId() {
		return empId;
	}

	public void setEmpId(String empId) {
		this.empId = empId;
	}

	public String getOldVerticalId() {
		return oldVerticalId;
	}

	public void setOldVerticalId(String oldVerticalId) {
		this.oldVerticalId = oldVerticalId;
	}

	public String getSuboldVerticalId() {
		return suboldVerticalId;
	}

	public void setSuboldVerticalId(String suboldVerticalId) {
		this.suboldVerticalId = suboldVerticalId;
	}

	public String getOldVerticalName() {
		return oldVerticalName;
	}

	public void setOldVerticalName(String oldVerticalName) {
		this.oldVerticalName = oldVerticalName;
	}

	public String getOldsubVerticalName() {
		return oldsubVerticalName;
	}

	public void setOldsubVerticalName(String oldsubVerticalName) {
		this.oldsubVerticalName = oldsubVerticalName;
	}

	public String getNewVerticalId() {
		return newVerticalId;
	}

	public void setNewVerticalId(String newVerticalId) {
		this.newVerticalId = newVerticalId;
	}

	public String getNewSubVerticalId() {
		return newSubVerticalId;
	}

	public void setNewSubVerticalId(String newSubVerticalId) {
		this.newSubVerticalId = newSubVerticalId;
	}

	public String getNewVerticalName() {
		return newVerticalName;
	}

	public void setNewVerticalName(String newVerticalName) {
		this.newVerticalName = newVerticalName;
	}

	public String getNewSubVerticalName() {
		return newSubVerticalName;
	}

	public void setNewSubVerticalName(String newSubVerticalName) {
		this.newSubVerticalName = newSubVerticalName;
	}

	public String getIsnsrtedOn() {
		return isnsrtedOn;
	}

	public void setIsnsrtedOn(String isnsrtedOn) {
		this.isnsrtedOn = isnsrtedOn;
	}

	public String getEmailFlag() {
		return emailFlag;
	}

	public void setEmailFlag(String emailFlag) {
		this.emailFlag = emailFlag;
	}
}
