package com.rightprice.auth.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import io.swagger.annotations.ApiModelProperty;

@Entity
@Table(name = "synprod.RP_Rate_Card_Roles_GFT_STG")
public class RateCardRolesGFTSTG {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "RC_ROLE_ID")
	@ApiModelProperty(notes = "RC Role Id")
	private Integer rcRoleId; 
	
	@Column(name = "RC_ID")
	@ApiModelProperty(notes = "RC Id")
	private Integer rcId;
	
	@Column(name = "RC_MASTER_ROLE_ID")
	@ApiModelProperty(notes = "RC Master Role Id")
	private Integer rcMasterRoleId;
	
	@Column(name = "CLIENT_ROLE")
	@ApiModelProperty(notes = "Client Role")
	private String clientRole;

	@Column(name = "COUNTRY_ID")
	@ApiModelProperty(notes = "Country Id")
	private Integer countryId;
	
	@Column(name = "CITY_ID")
	@ApiModelProperty(notes = "City Id")
	private Integer cityId;
	
	@Column(name = "SKILL_ID")
	@ApiModelProperty(notes = "Skill Id")
	private Integer skillId;
	
	@Column(name = "SKILL_ELEMENT_ID")
	@ApiModelProperty(notes = "Skill Element Id")
	private Integer skillElementId;
	
	@Column(name = "KNOWLEDGE_ID")
	@ApiModelProperty(notes = "Knowledge Id")
	private Integer knowledgeId;
		
	@Column(name = "Onsite_Proposed_Client_Rate")
	@ApiModelProperty(notes = "Onsite Customer Rate")
	private Double onsiteProposedClientRate;
	
	@Column(name = "Offshore_Proposed_Client_Rate")
	@ApiModelProperty(notes = "Offshore Customer Rate")
	private Double offshoreProposedClientRate;

	@Column(name = "COMMENTS")
	@ApiModelProperty(notes = "Comments")
	private String comments;
	
	@Column(name = "CREATED_BY", updatable = false)
	@ApiModelProperty(notes = "Created by")
	private String createdBy;
	
	@Column(name = "CREATED_ON", updatable = false)
	@ApiModelProperty(notes = "Created On")
	private String createdOn;
	
	@Column(name = "UPDATED_BY")
	@ApiModelProperty(notes = "Updated by")
	private String updatedBy;

	@Column(name = "UPDATED_ON")
	@ApiModelProperty(notes = "Updated On")
	private String updatedOn;

	
	@Transient
	private String role;
	
	
	public String getRole() {
		return role;
	}

	public void setRole(String rOle) {
		role = rOle;
	}

	public Integer getRcRoleId() {
		return rcRoleId;
	}

	public void setRcRoleId(Integer rcRoleId) {
		this.rcRoleId = rcRoleId;
	}

	public Integer getRcId() {
		return rcId;
	}

	public void setRcId(Integer rcId) {
		this.rcId = rcId;
	}

	public Integer getRcMasterRoleId() {
		return rcMasterRoleId;
	}

	public void setRcMasterRoleId(Integer rcMasterRoleId) {
		this.rcMasterRoleId = rcMasterRoleId;
	}

	public String getClientRole() {
		return clientRole;
	}

	public void setClientRole(String clientRole) {
		this.clientRole = clientRole;
	}

	public Integer getCountryId() {
		return countryId;
	}

	public void setCountryId(Integer countryId) {
		this.countryId = countryId;
	}

	public Integer getCityId() {
		return cityId;
	}

	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}

	public Integer getSkillId() {
		return skillId;
	}

	public void setSkillId(Integer skillId) {
		this.skillId = skillId;
	}

	public Integer getSkillElementId() {
		return skillElementId;
	}

	public void setSkillElementId(Integer skillElementId) {
		this.skillElementId = skillElementId;
	}

	public Integer getKnowledgeId() {
		return knowledgeId;
	}

	public void setKnowledgeId(Integer knowledgeId) {
		this.knowledgeId = knowledgeId;
	}

	public Double getOnsiteProposedClientRate() {
		return onsiteProposedClientRate;
	}

	public void setOnsiteProposedClientRate(Double onsiteProposedClientRate) {
		this.onsiteProposedClientRate = onsiteProposedClientRate;
	}

	public Double getOffshoreProposedClientRate() {
		return offshoreProposedClientRate;
	}

	public void setOffshoreProposedClientRate(Double offshoreProposedClientRate) {
		this.offshoreProposedClientRate = offshoreProposedClientRate;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
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
