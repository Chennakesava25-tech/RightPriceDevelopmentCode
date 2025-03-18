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
@ApiModel(value = "XO_Skill_Element_Master", description = "XO_Skill_Element Model Attributes")
@Table(name = "synprod.MST_RP_XO_Skill_Element_Master")
public class MasterXOSkillElement implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "SKILL_ELEMENT_ID")
	@ApiModelProperty(notes = "SKILL ELEMENT ID Auto Incremented")
	private Integer skillElementId;

	@Column(name = "SKILL_ID")
	@ApiModelProperty(notes = "SKILL ID")
	private Integer skillId;

	@Column(name = "ELEMENT_CODE")
	@ApiModelProperty(notes = "Element Code")
	private String elementCode;

	@Column(name = "ELEMENT_NAME")
	@ApiModelProperty(notes = "ELEMENT NAME")
	private String elementName;

	@Column(name = "EFFECTIVE_FROM")
	@ApiModelProperty(notes = "Effective From")
	private String effectiveFrom;

	@Column(name = "TRI_ID")
	@ApiModelProperty(notes = "TRI Id")
	private Integer triId;

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

	public Integer getSkillElementId() {
		return skillElementId;
	}

	public void setSkillElementId(Integer skillElementId) {
		this.skillElementId = skillElementId;
	}

	public Integer getSkillId() {
		return skillId;
	}

	public void setSkillId(Integer skillId) {
		this.skillId = skillId;
	}

	public String getElementCode() {
		return elementCode;
	}

	public void setElementCode(String elementCode) {
		this.elementCode = elementCode;
	}

	public String getElementName() {
		return elementName;
	}

	public void setElementName(String elementName) {
		this.elementName = elementName;
	}

	public String getEffectiveFrom() {
		return effectiveFrom;
	}

	public void setEffectiveFrom(String effectiveFrom) {
		this.effectiveFrom = effectiveFrom;
	}

	public Integer getTriId() {
		return triId;
	}

	public void setTriId(Integer triId) {
		this.triId = triId;
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
