package com.rightprice.auth.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@Entity
@ApiModel(value="LeaderShip",description="LeaderShip Model Attributes")
@Table(name= "synprod.MST_RP_Leadership")
public class LeadershipDetails {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "LEADER_ID") 
	@ApiModelProperty(notes = "Leader Ship Leader Id")
	private Integer leaderId;
	
	@Column(name = "LEADER_LAN_ID")
	@ApiModelProperty(notes = "Leader Ship Leader Lan id")
	private String leaderLanID;
	
	@Column(name = "LEADER_NAME")
	@ApiModelProperty(notes = "Leader Ship Leader Leader Name")
	private String leaderName;
	
	@Column(name = "LEADER_ROLE_TYPE")
	@ApiModelProperty(notes = "Leader Ship Leader Designation Id")
	private Integer leaderDesignationId;
	
	@Column(name = "LEADER_EMAIL_ID")
	@ApiModelProperty(notes = "Leader Ship Leader Email Id")
	private String leaderEmailId;
	
	@Column(name = "IS_Active")
	@ApiModelProperty(notes = "Leader Ship Is Active ")
	private int isActive;
	
	@Column(name = "Created_BY")
	@ApiModelProperty(notes = "Leader ship Created BY")
	private String createdBy;
	
	@Column(name = "CREATED_ON")
	@ApiModelProperty(notes = "Leder shipCreated ON")
	private Date createdOn;
	

	@Column(name = "UPDATED_BY")
	@ApiModelProperty(notes = "Leader ship Updated By")
	private String updatedBy;

	@Column(name = "UPDATED_ON")
	@ApiModelProperty(notes = "Leader ship Updated On")
	private String updatedOn;

	public int getLeaderId() {
		return leaderId;
	}

	public void setLeaderId(int leaderId) {
		this.leaderId = leaderId;
	}

	public String getLeaderName() {
		return leaderName;
	}

	public void setLeaderName(String leaderName) {
		this.leaderName = leaderName;
	}

	public int getIsActive() {
		return isActive;
	}

	public void setIsActive(int isActive) {
		this.isActive = isActive;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public String getLeaderLanID() {
		return leaderLanID;
	}

	public void setLeaderLanID(String leaderLanID) {
		this.leaderLanID = leaderLanID;
	}

	public Integer getLeaderDesignationId() {
		return leaderDesignationId;
	}

	public void setLeaderDesignationId(Integer leaderDesignationId) {
		this.leaderDesignationId = leaderDesignationId;
	}

	public void setLeaderId(Integer leaderId) {
		this.leaderId = leaderId;
	}

	public String getUpdatedOn() {
		return updatedOn;
	}

	public void setUpdatedOn(String updatedOn) {
		this.updatedOn = updatedOn;
	}

	public String getLeaderEmailId() {
		return leaderEmailId;
	}

	public void setLeaderEmailId(String leaderEmailId) {
		this.leaderEmailId = leaderEmailId;
	}

}
