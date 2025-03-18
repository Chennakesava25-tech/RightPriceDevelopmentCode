package com.rightprice.auth.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@Entity
@ApiModel(value = "XO Skill Master", description = "XO Skill Model Attributes")
@Table(name = "synprod.MST_RP_XO_Skill_Master")
public class MasterXOSkill implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "SKILL_ID")
	@ApiModelProperty(notes = "SKILL ID Auto Incremented")
	private Integer skillId;

	@Column(name = "COMPETENCY_ID")
	@ApiModelProperty(notes = "Competency Id")
	private Integer competencyId;

	@Column(name = "SKILL_CODE")
	@ApiModelProperty(notes = "Skill Code")
	private String skillCode;

	@Column(name = "SKILL_NAME")
	@ApiModelProperty(notes = "SKILL NAME")
	private String skillName;

	@Column(name = "EFFECTIVE_FROM")
	@ApiModelProperty(notes = "Effective From")
	private String effectiveFrom;

	@Column(name = "IS_ACTIVE")
	@ApiModelProperty(notes = "Is Active")
	private Integer isActive;

	@Column(name = "CREATED_BY")
	@ApiModelProperty(notes = "Created by")
	private String createdby;

	@Column(name = "CREATED_ON")
	@ApiModelProperty(notes = "Created On")
	private String createdOn;

	@Column(name = "UPDATED_BY")
	@ApiModelProperty(notes = "Updated By")
	private String updatedBy;

	@Column(name = "UPDATED_ON")
	@ApiModelProperty(notes = "Updated On")
	private String updatedOn;

	public Integer getSkillId() {
		return skillId;
	}

	public void setSkillId(Integer skillId) {
		this.skillId = skillId;
	}

	public Integer getCompetencyId() {
		return competencyId;
	}

	public void setCompetencyId(Integer competencyId) {
		this.competencyId = competencyId;
	}

	public String getSkillCode() {
		return skillCode;
	}

	public void setSkillCode(String skillCode) {
		this.skillCode = skillCode;
	}

	public String getSkillName() {
		return skillName;
	}

	public void setSkillName(String skillName) {
		this.skillName = skillName;
	}

	public String getEffectiveFrom() {
		return effectiveFrom;
	}

	public void setEffectiveFrom(String effectiveFrom) {
		this.effectiveFrom = effectiveFrom;
	}

	public Integer getIsActive() {
		return isActive;
	}

	public void setIsActive(Integer isActive) {
		this.isActive = isActive;
	}

	public String getCreatedby() {
		return createdby;
	}

	public void setCreatedby(String createdby) {
		this.createdby = createdby;
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
