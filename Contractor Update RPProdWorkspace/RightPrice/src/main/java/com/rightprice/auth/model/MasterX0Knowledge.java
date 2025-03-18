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
@Table(name = "SYNPROD.MST_RP_XO_KNOWLEDGE_MASTER")
public class MasterX0Knowledge implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "KNOWLEDGE_ID")
	@ApiModelProperty(notes = "Knowledge ID Auto Incremented")
	private int knowledgeId;

	@Column(name = "KNOWLEDGE_CODE")
	@ApiModelProperty(notes = "Knowledge Code")
	private String knowledgeCode;

	@Column(name = "KNOWLEDGE_NAME")
	@ApiModelProperty(notes = "Knowledg Name")
	private String knowledgeName;

	@Column(name = "DOMAIN_ELEMENT")
	@ApiModelProperty(notes = "Domain Element")
	private String domainElement;

	@Column(name = "Band")
	@ApiModelProperty(notes = "Band")
	private String band;

	@Column(name = "COMPETENCY_ID")
	@ApiModelProperty(notes = "Competency Id")
	private Integer competencyId;

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

	public int getKnowledgeId() {
		return knowledgeId;
	}

	public void setKnowledgeId(int knowledgeId) {
		this.knowledgeId = knowledgeId;
	}

	public String getKnowledgeCode() {
		return knowledgeCode;
	}

	public void setKnowledgeCode(String knowledgeCode) {
		this.knowledgeCode = knowledgeCode;
	}

	public String getKnowledgeName() {
		return knowledgeName;
	}

	public void setKnowledgeName(String knowledgeName) {
		this.knowledgeName = knowledgeName;
	}

	public String getDomainElement() {
		return domainElement;
	}

	public void setDomainElement(String domainElement) {
		this.domainElement = domainElement;
	}

	public String getBand() {
		return band;
	}

	public void setBand(String band) {
		this.band = band;
	}

	public Integer getCompetencyId() {
		return competencyId;
	}

	public void setCompetencyId(Integer competencyId) {
		this.competencyId = competencyId;
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
