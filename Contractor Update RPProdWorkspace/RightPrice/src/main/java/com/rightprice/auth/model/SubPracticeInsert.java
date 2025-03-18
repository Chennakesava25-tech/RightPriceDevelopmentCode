package com.rightprice.auth.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@Entity
@ApiModel(value="SubPracticeInsert",description="SubPracticeInsert Model Attributes")
@Table(name = "synprod.MST_RP_Sub_Practice")
public class SubPracticeInsert {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	
	@Column(name = "SUB_PRACTICE_ID")
	@ApiModelProperty(notes="Sub_prac_id Auto Incremented")
	private int subPracId;
	@Column(name = "PRACTICE_ID")
	private int practiceId;
	@Column(name = "SUB_PRACTICE_NAME")
	private String subPracticeName;
	@Column(name = "IS_ACTIVE")
	private int activeStatus = 1;
	@Column(name = "CREATED_BY", updatable=false)
	private String createdBy;
	@Column(name = "CREATED_ON", updatable=false)
	private String createdOn;
	@Column(name = "UPDATED_BY")
	private String lastUpdatedBy;
	@Column(name = "UPDATED_ON")
	private String lastUpdatedOn;
	
	public int getSubPracId() {
		return subPracId;
	}
	public void setSubPracId(int subPracId) {
		this.subPracId = subPracId;
	}
	
	public int getPracticeId() {
		return practiceId;
	}
	public void setPracticeId(int practiceId) {
		this.practiceId = practiceId;
	}
	public String getSubPracticeName() {
		return subPracticeName;
	}
	public void setSubPracticeName(String subPracticeName) {
		this.subPracticeName = subPracticeName;
	}
	
	public int getActiveStatus() {
		return activeStatus;
	}
	public void setActiveStatus(int activeStatus) {
		this.activeStatus = activeStatus;
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
	public String getLastUpdatedBy() {
		return lastUpdatedBy;
	}
	public void setLastUpdatedBy(String lastUpdatedBy) {
		this.lastUpdatedBy = lastUpdatedBy;
	}
	public String getLastUpdatedOn() {
		return lastUpdatedOn;
	}
	public void setLastUpdatedOn(String lastUpdatedOn) {
		this.lastUpdatedOn = lastUpdatedOn;
	}

}
