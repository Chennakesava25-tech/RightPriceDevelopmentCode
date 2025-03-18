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

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@Entity
@ApiModel(value="Emp Designation",description="Emp Designation Model Attributes")
@Table(name = "synprod.MST_RP_Emp_Designation")
public class Designation implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "EMP_DESIGNATION_ID")
	@ApiModelProperty(notes="EMP DESIGNATION ID Auto Incremented")
	private Integer empDesignationId;
	
	@Column(name = "EMP_DESIGNATION_DESCRIPTION")
	@ApiModelProperty(notes="Emp Designation Description")
	private String empDesignationDescription;
	
	@Column(name = "EMP_BAND_ID")
	@ApiModelProperty(notes="EMP Band Id")
	private Integer empBandId;
	
	@Column(name = "EMP_GRADE_ID")
	@ApiModelProperty(notes="EMP Grade Id")
	private Integer empGradeId;
	
	@Column(name = "KNOWLEDGE_LEVEL_ID")
	@ApiModelProperty(notes="Knowledge Level Id")
	private Integer knowledgeLevelId;
	
	@Column(name = "SKILL_LEVEL_ID")
	@ApiModelProperty(notes="Skill Level Id")
	private Integer skillLevelId;
	
	@Column(name = "NEW_EMP_BAND_ID")
	@ApiModelProperty(notes="New Emp Band Id")
	private Integer newEmpBandId;
	
	@Column(name = "NEW_EMP_GRADE_ID")
	@ApiModelProperty(notes="New Emp Grade Id")
	private Integer newEmpGradeId;
	
	@Column(name = "MINIMUM_EXPERIENCE_YEARS")
	@ApiModelProperty(notes="Minimum Experience Years")
	private Double minimumExperienceYears;
	
	@Column(name = "MAXIMUM_EXPERIENCE_YEARS")
	@ApiModelProperty(notes="Maximum Experience Years")
	private Double maximumExperienceYears;
	
	@Column(name = "IS_ACTIVE")
	@ApiModelProperty(notes="Is Active")
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
	
	@OneToOne(cascade = javax.persistence.CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name ="emp_band_id",referencedColumnName = "CODE_NAME", insertable=false, updatable=false )
	private Band band;
	
	@OneToOne(cascade = javax.persistence.CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name ="emp_grade_id",referencedColumnName = "CODE_NAME" , insertable=false, updatable=false)
	private Grade grade;

	@Column(name = "GCM_CODE")
	@ApiModelProperty(notes="GCM CODE")
	private String gcmCODE;
	
	public String getGcmCODE() {
		return gcmCODE;
	}

	public void setGcmCODE(String gcmCODE) {
		this.gcmCODE = gcmCODE;
	}
	
	public Integer getEmpDesignationId() {
		return empDesignationId;
	}

	public void setEmpDesignationId(Integer empDesignationId) {
		this.empDesignationId = empDesignationId;
	}

	public String getEmpDesignationDescription() {
		return empDesignationDescription;
	}

	public void setEmpDesignationDescription(String empDesignationDescription) {
		this.empDesignationDescription = empDesignationDescription;
	}

	public Integer getEmpBandId() {
		return empBandId;
	}

	public void setEmpBandId(Integer empBandId) {
		this.empBandId = empBandId;
	}

	public Integer getEmpGradeId() {
		return empGradeId;
	}

	public void setEmpGradeId(Integer empGradeId) {
		this.empGradeId = empGradeId;
	}

	public Integer getKnowledgeLevelId() {
		return knowledgeLevelId;
	}

	public void setKnowledgeLevelId(Integer knowledgeLevelId) {
		this.knowledgeLevelId = knowledgeLevelId;
	}

	public Integer getSkillLevelId() {
		return skillLevelId;
	}

	public void setSkillLevelId(Integer skillLevelId) {
		this.skillLevelId = skillLevelId;
	}

	public Integer getNewEmpBandId() {
		return newEmpBandId;
	}

	public void setNewEmpBandId(Integer newEmpBandId) {
		this.newEmpBandId = newEmpBandId;
	}

	public Integer getNewEmpGradeId() {
		return newEmpGradeId;
	}

	public void setNewEmpGradeId(Integer newEmpGradeId) {
		this.newEmpGradeId = newEmpGradeId;
	}

	public Double getMinimumExperienceYears() {
		return minimumExperienceYears;
	}

	public void setMinimumExperienceYears(Double minimumExperienceYears) {
		this.minimumExperienceYears = minimumExperienceYears;
	}

	public Double getMaximumExperienceYears() {
		return maximumExperienceYears;
	}

	public void setMaximumExperienceYears(Double maximumExperienceYears) {
		this.maximumExperienceYears = maximumExperienceYears;
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

	public Band getBand() {
		return band;
	}

	public void setBand(Band band) {
		this.band = band;
	}

	public Grade getGrade() {
		return grade;
	}

	public void setGrade(Grade grade) {
		this.grade = grade;
	}	
}
