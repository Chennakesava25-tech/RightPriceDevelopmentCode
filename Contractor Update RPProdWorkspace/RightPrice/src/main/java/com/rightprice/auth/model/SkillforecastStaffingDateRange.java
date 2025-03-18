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
@Table(name = "synprod.RP_SY_OPP_Skillforecast_STAFFING_DATE_RANGE")
public class SkillforecastStaffingDateRange implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	
	@Id
	@Column(name = "Row_No")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@ApiModelProperty(notes = "Row_No")
	private Integer rowNo;
	
	@Column(name = "ID")
	@ApiModelProperty(notes = "Id")
	private Integer id;

	@Column(name = "OPPORTUNITY_ID")
	@ApiModelProperty(notes = "OPPORTUNITY_ID")
	private Integer opportunityId;
	
	@Column(name = "START_DT")
	@ApiModelProperty(notes = "START_DT")
	private String startDate;
	
	@Column(name = "END_DT")
	@ApiModelProperty(notes = "END_DT")
	private String endDate;
	
	@Column(name = "ROLE")
	@ApiModelProperty(notes = "ROLE")
	private String role;

	@Column(name = "CATOGERY")
	@ApiModelProperty(notes = "CATOGERY")
	private String catogery;
	
	@Column(name = "BAND")
	@ApiModelProperty(notes = "BAND")
	private String band;
	
	@Column(name = "QTY")
	@ApiModelProperty(notes = "QTY")
	private Integer quantity;

	@Column(name = "Skills_name")
	@ApiModelProperty(notes = "Skills_name")
	private String skillsName;
	
	@Column(name = "LOB")
	@ApiModelProperty(notes = "LOB")
	private String lob;
	
	@Column(name = "Experience")
	@ApiModelProperty(notes = "Experience")
	private Integer experience;
	
	@Column(name = "XOSkill_id")
	@ApiModelProperty(notes = "XOSkill_id")
	private Integer skillID;
	
	@Column(name = "XOSkill_Element_id")
	@ApiModelProperty(notes = "XOSkill_Element_id")
	private Integer skillElementId;
	
	@Column(name = "XOKnowledge_id")
	@ApiModelProperty(notes = "XOKnowledge_id")
	private Integer xOKnowledgeID;
	
	@Column(name = "XO_BAND")
	@ApiModelProperty(notes = "XO_BAND")
	private String xoBand;
	
	@Column(name = "XO_GRADE")
	@ApiModelProperty(notes = "XO_GRADE")
	private String xoGrade;
	
	@Column(name = "KNOWLEDGE_SKILL_NAME")
	@ApiModelProperty(notes = "KNOWLEDGE_SKILL_NAME")
	private String knowledgeSkillName;
	
	/*@Column(name = "BAND_GRADE_ID")
	@ApiModelProperty(notes = "Band_Grade_Id")
	private Integer bandGradeId;
	
	@Column(name = "BAND_GRADE")
	@ApiModelProperty(notes = "Band_Grade")
	private String bandGrade;*/
	
	@Column(name = "XO_KnowledgeName")
	@ApiModelProperty(notes = "XO_KnowledgeName")
	private String xOKnowledgeName;
	
	@Column(name = "XO_skillname")
	@ApiModelProperty(notes = "XO_skillname")
	private String xoSkillName;
	
	@Column(name = "Migration_status")
	@ApiModelProperty(notes = "Migration_status")
	private String migrationStatus;
	
	@Column(name = "run_date")
	@ApiModelProperty(notes = "run_date")
	private String runDate;
	
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

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getRowNo() {
		return rowNo;
	}

	public void setRowNo(Integer rowNo) {
		this.rowNo = rowNo;
	}

	public Integer getOpportunityId() {
		return opportunityId;
	}

	public void setOpportunityId(Integer opportunityId) {
		this.opportunityId = opportunityId;
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

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getCatogery() {
		return catogery;
	}

	public void setCatogery(String catogery) {
		this.catogery = catogery;
	}

	public String getBand() {
		return band;
	}

	public void setBand(String band) {
		this.band = band;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public String getSkillsName() {
		return skillsName;
	}

	public void setSkillsName(String skillsName) {
		this.skillsName = skillsName;
	}

	public String getLob() {
		return lob;
	}

	public void setLob(String lob) {
		this.lob = lob;
	}

	public Integer getExperience() {
		return experience;
	}

	public void setExperience(Integer experience) {
		this.experience = experience;
	}

	public Integer getSkillID() {
		return skillID;
	}

	public void setSkillID(Integer skillID) {
		this.skillID = skillID;
	}

	public Integer getSkillElementId() {
		return skillElementId;
	}

	public void setSkillElementId(Integer skillElementId) {
		this.skillElementId = skillElementId;
	}

	public Integer getxOKnowledgeID() {
		return xOKnowledgeID;
	}

	public void setxOKnowledgeID(Integer xOKnowledgeID) {
		this.xOKnowledgeID = xOKnowledgeID;
	}

	public String getXoBand() {
		return xoBand;
	}

	public void setXoBand(String xoBand) {
		this.xoBand = xoBand;
	}

	public String getXoGrade() {
		return xoGrade;
	}

	public void setXoGrade(String xoGrade) {
		this.xoGrade = xoGrade;
	}

	public String getKnowledgeSkillName() {
		return knowledgeSkillName;
	}

	public void setKnowledgeSkillName(String knowledgeSkillName) {
		this.knowledgeSkillName = knowledgeSkillName;
	}

	/*public Integer getBandGradeId() {
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
	}*/

	public String getxOKnowledgeName() {
		return xOKnowledgeName;
	}

	public void setxOKnowledgeName(String xOKnowledgeName) {
		this.xOKnowledgeName = xOKnowledgeName;
	}

	public String getXoSkillName() {
		return xoSkillName;
	}

	public void setXoSkillName(String xoSkillName) {
		this.xoSkillName = xoSkillName;
	}

	public String getMigrationStatus() {
		return migrationStatus;
	}

	public void setMigrationStatus(String migrationStatus) {
		this.migrationStatus = migrationStatus;
	}

	public String getRunDate() {
		return runDate;
	}

	public void setRunDate(String runDate) {
		this.runDate = runDate;
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

}
