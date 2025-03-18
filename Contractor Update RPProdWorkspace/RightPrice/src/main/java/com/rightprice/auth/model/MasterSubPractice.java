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

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@Entity
@ApiModel(value="Sub Practice",description="Sub Practice Model Attributes")
@Table(name = "synprod.MST_RP_Sub_Practice")
public class MasterSubPractice implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "SUB_PRACTICE_ID")
	@ApiModelProperty(notes="SUB PRACTICE ID Auto Incremented")
	private Integer subpracticeId;
	
	@Column(name = "SUB_PRACTICE_NAME")
	@ApiModelProperty(notes="SUB PRACTICE NAME")
	private String subpracticeName;
	
	@Column(name = "PRACTICE_ID")
	@ApiModelProperty(notes="Practice Id")
	private Integer practiceId;
	
	@Column(name = "IS_ACTIVE")
	@ApiModelProperty(notes="IS ACTIVE")
	private Integer isActive;

	@Column(name = "CREATED_BY")
	@ApiModelProperty(notes="Created By")
	private String createdBy;
	
	@Column(name = "CREATED_ON")
	@ApiModelProperty(notes="Created On")
	private String createdOn;
	
	@Column(name = "UPDATED_BY")
	@ApiModelProperty(notes="Updated By")
	private String updatedBy;
	
	@Column(name = "UPDATED_ON")
	@ApiModelProperty(notes="Updated On")
	private String updatedOn;
	
	@OneToOne(cascade = javax.persistence.CascadeType.ALL)
    @JoinColumn(name = "PRACTICE_ID",insertable=false,updatable=false)
	@NotFound(action = NotFoundAction.IGNORE)
	private MasterPractice masterPractice;

	public Integer getSubpracticeId() {
		return subpracticeId;
	}

	public void setSubpracticeId(Integer subpracticeId) {
		this.subpracticeId = subpracticeId;
	}

	public String getSubpracticeName() {
		return subpracticeName;
	}

	public void setSubpracticeName(String subpracticeName) {
		this.subpracticeName = subpracticeName;
	}

	public Integer getPracticeId() {
		return practiceId;
	}

	public void setPracticeId(Integer practiceId) {
		this.practiceId = practiceId;
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

	public MasterPractice getMasterPractice() {
		return masterPractice;
	}

	public void setMasterPractice(MasterPractice masterPractice) {
		this.masterPractice = masterPractice;
	}
	
		
}
