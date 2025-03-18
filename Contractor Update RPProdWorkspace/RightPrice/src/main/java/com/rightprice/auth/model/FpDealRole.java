package com.rightprice.auth.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import io.swagger.annotations.ApiModelProperty;

@Entity
@Table(name = "SYNPROD.RP_FP_Deal_Roles")
public class FpDealRole {
	
	@Id
	@GeneratedValue
	@Column(name = "FP_Deal_Role_Id")
	private Integer fpDealRoleId;
	
	@Column(name = "RP_Deal_Version_Id")
	private Integer dealVersionId;

	@Column(name = "RC_Id")
	private Integer rcId;
	
	@Column(name = "Deal_auto_Tower_Id")
	private Integer dealAutoTowerId;
	
	@Column(name = "RC_Role_Id")
	private Integer rcRoleId;
	
	@Column(name = "Master_Role_Id")
	private Integer masterRoleId;
	
	@Column(name = "Client_Role")
	private String clientRole;
	
	@Column(name = "Skill_Id")
	private Integer xOSkillIndex;
	
	@Column(name = "Skill_Element_Id")
	private Integer xOSkillElementIndex;
	
	@Column(name = "Knowledge_Id")
	private Integer xOKnowledgeIndex;
	
	
	@Column(name = "Is_MasterRole")
	private Integer isMasterRole;
	
	@Column(name = "Comments")
	private String comments;
	
	@Column(name = "Is_Active")
	private Integer isActive = 1;
	
	@Column(name = "CreatedBy", updatable = false)
	private String createdBy;

	@Column(name = "Created_On", updatable = false)
	private String createdOn;

	@Column(name = "Updated_By")
	private String updatedBy;

	@Column(name = "Updated_On")
	private String updatedOn;

	@Column(name = "GCM_CODE")
	private String gcmCODE;
	
	public String getGcmCODE() {
		return gcmCODE;
	}

	public void setGcmCODE(String gcmCODE) {
		this.gcmCODE = gcmCODE;
	}

	
	public Integer getFpDealRoleId() {
		return fpDealRoleId;
	}

	public void setFpDealRoleId(Integer fpDealRoleId) {
		this.fpDealRoleId = fpDealRoleId;
	}

	public Integer getDealVersionId() {
		return dealVersionId;
	}

	public void setDealVersionId(Integer dealVersionId) {
		this.dealVersionId = dealVersionId;
	}

	public Integer getRcId() {
		return rcId;
	}

	public void setRcId(Integer rcId) {
		this.rcId = rcId;
	}

	public Integer getDealAutoTowerId() {
		return dealAutoTowerId;
	}

	public void setDealAutoTowerId(Integer dealAutoTowerId) {
		this.dealAutoTowerId = dealAutoTowerId;
	}

	public Integer getRcRoleId() {
		return rcRoleId;
	}

	public void setRcRoleId(Integer rcRoleId) {
		this.rcRoleId = rcRoleId;
	}

	public Integer getMasterRoleId() {
		return masterRoleId;
	}

	public void setMasterRoleId(Integer masterRoleId) {
		this.masterRoleId = masterRoleId;
	}

	public String getClientRole() {
		return clientRole;
	}

	public void setClientRole(String clientRole) {
		this.clientRole = clientRole;
	}

	public Integer getxOSkillIndex() {
		return xOSkillIndex;
	}

	public void setxOSkillIndex(Integer xOSkillIndex) {
		this.xOSkillIndex = xOSkillIndex;
	}

	public Integer getxOSkillElementIndex() {
		return xOSkillElementIndex;
	}

	public void setxOSkillElementIndex(Integer xOSkillElementIndex) {
		this.xOSkillElementIndex = xOSkillElementIndex;
	}

	public Integer getxOKnowledgeIndex() {
		return xOKnowledgeIndex;
	}

	public void setxOKnowledgeIndex(Integer xOKnowledgeIndex) {
		this.xOKnowledgeIndex = xOKnowledgeIndex;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
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

	public Integer getIsMasterRole() {
		return isMasterRole;
	}

	public void setIsMasterRole(Integer isMasterRole) {
		this.isMasterRole = isMasterRole;
	}

	@Column(name = "Skill_Complexity_Desc")
	@ApiModelProperty(notes="Skill Complexity Desc")
	private String skillComplexityDesc;

	
	
	public String getSkillComplexityDesc() {
		return skillComplexityDesc;
	}

	public void setSkillComplexityDesc(String skillComplexityDesc) {
		this.skillComplexityDesc = skillComplexityDesc;
	}

	@Column(name = "X0_SKILL_DESCRIPTION")
	@ApiModelProperty(notes="X0 Skill Description")
	private String x0SkillDescription;

	
	
	public String getX0SkillDescription() {
		return x0SkillDescription;
	}

	public void setX0SkillDescription(String x0SkillDescription) {
		this.x0SkillDescription = x0SkillDescription;
	}
	@Column(name="PRACTICE_NAME")
	private String practiceName;

	public String getPracticeName() {
		return practiceName;
	}

	public void setPracticeName(String practiceName) {
		this.practiceName = practiceName;
	}
	
	@Column(name = "X0_SKILL_ELEMENT_DESCRIPTION")
	@ApiModelProperty(notes="X0 Skill Element Description")
	private String x0SkillElementDescription;

	public String getX0SkillElementDescription() {
		return x0SkillElementDescription;
	}

	public void setX0SkillElementDescription(String x0SkillElementDescription) {
		this.x0SkillElementDescription = x0SkillElementDescription;
	}

	
}
