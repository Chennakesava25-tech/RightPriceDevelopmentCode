package com.rightprice.auth.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import io.swagger.annotations.ApiModelProperty;

@Entity
@Table(name = "synprod.Resource_Forecast")
public class ResourceForecast implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	@ApiModelProperty(notes = "Id")
	private Integer id;

	@Column(name = "DEAL_ID")
	@ApiModelProperty(notes = "Deal_Id")
	private Integer dealId;

	@Column(name = "LOCATION")
	@ApiModelProperty(notes = "Location")
	private String location;

	@Column(name = "SKILL_ID")
	@ApiModelProperty(notes = "Skill_ID")
	private Integer skillID;
	
	@Column(name = "SKILL")
	@ApiModelProperty(notes = "Skill")
	private String skill;
	
	@Column(name = "SKILL_ELEMENT_ID")
	@ApiModelProperty(notes = "Skill_Element_Id")
	private Integer skillElementId;
	
	@Column(name = "SKILL_ELEMENT")
	@ApiModelProperty(notes = "Skill_Element")
	private String skillElement;
	
	@Column(name = "BAND_GRADE_ID")
	@ApiModelProperty(notes = "Band_Grade_Id")
	private Integer bandGradeId;
	
	@Column(name = "BAND_GRADE")
	@ApiModelProperty(notes = "Band_Grade")
	private String bandGrade;
	
	@Column(name = "SYNTEL_XO_BAND_GRADE")
	@ApiModelProperty(notes = "Syntel_XO_Band_Grade")
	private String syntelXOBandGrade;
	
	@Column(name = "SYNTEL_XO_KNOWLEDGE_ID")
	@ApiModelProperty(notes = "Syntel_XO_Knowledge_ID")
	private Integer syntelXOKnowledgeID;
	
	@Column(name = "SYNTEL_XO_KNOWLEDGE")
	@ApiModelProperty(notes = "Syntel_XO_Knowledge")
	private String syntelXOKnowledge;
	
	@Column(name = "NO_OF_RESOURCES")
	@ApiModelProperty(notes = "NO_OF_RESOURCES")
	private Integer noOfResource;

	@Column(name = "START_DATE")
	@ApiModelProperty(notes = "Startdate")
	private String startDate;
	
	@Column(name = "END_DATE")
	@ApiModelProperty(notes = "EndDate")
	private String endDate;
	
	@Column(name = "SYNTEL_ROLE_ID")
	@ApiModelProperty(notes = "Syntel_Role_ID")
	private Integer syntelRoleID;
	
	@Column(name = "SYNTEL_ROLE_NAME")
	@ApiModelProperty(notes = "Syntel_Role_Name")
	private String syntelRoleName;
	
	@Column(name = "IS_ACTIVE")
	@ApiModelProperty(notes = "active status")
	private Integer isActive;

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


	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public Integer getSkillID() {
		return skillID;
	}

	public void setSkillID(Integer skillID) {
		this.skillID = skillID;
	}

	public String getSkill() {
		return skill;
	}

	public void setSkill(String skill) {
		this.skill = skill;
	}

	public Integer getSkillElementId() {
		return skillElementId;
	}

	public void setSkillElementId(Integer skillElementId) {
		this.skillElementId = skillElementId;
	}

	public String getSkillElement() {
		return skillElement;
	}

	public void setSkillElement(String skillElement) {
		this.skillElement = skillElement;
	}

	public Integer getBandGradeId() {
		return bandGradeId;
	}

	public void setBandGradeId(Integer bandGradeId) {
		this.bandGradeId = bandGradeId;
	}

	public String getBandGrade() {
		return bandGrade;
	}

	public void setBandGrade(String bandGrade) {
		this.bandGrade = bandGrade;
	}

	public String getSyntelXOBandGrade() {
		return syntelXOBandGrade;
	}

	public void setSyntelXOBandGrade(String syntelXOBandGrade) {
		this.syntelXOBandGrade = syntelXOBandGrade;
	}

	public Integer getSyntelXOKnowledgeID() {
		return syntelXOKnowledgeID;
	}

	public void setSyntelXOKnowledgeID(Integer syntelXOKnowledgeID) {
		this.syntelXOKnowledgeID = syntelXOKnowledgeID;
	}

	public String getSyntelXOKnowledge() {
		return syntelXOKnowledge;
	}

	public void setSyntelXOKnowledge(String syntelXOKnowledge) {
		this.syntelXOKnowledge = syntelXOKnowledge;
	}

	public Integer getNoOfResource() {
		return noOfResource;
	}

	public void setNoOfResource(Integer noOfResource) {
		this.noOfResource = noOfResource;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public Integer getSyntelRoleID() {
		return syntelRoleID;
	}

	public void setSyntelRoleID(Integer syntelRoleID) {
		this.syntelRoleID = syntelRoleID;
	}

	public String getSyntelRoleName() {
		return syntelRoleName;
	}

	public void setSyntelRoleName(String syntelRoleName) {
		this.syntelRoleName = syntelRoleName;
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
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getDealId() {
		return dealId;
	}

	public void setDealId(Integer dealId) {
		this.dealId = dealId;
	}

}