package com.rightprice.auth.model;
import javax.persistence.*;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class MstRpMasterRolesExcel 
{

	@Id
	@Column(name="RC_ROLE_ID")
	private int roleId;
	
	@Column(name="RC_ID")
	private int rcId;
	
	@Column(name="MASTER_ROLE_ID")
	private int masterRoleId;
	
	@Column(name="RC_Row_Count")
	private Integer rcRowCount;
	
	@Column(name = "SKILL_ID")
	private Integer xOSkillIndex;
	
	@Column(name="KNOWLEDGE_NAME")
	private String strKnowledgeName;
	
	@Column(name="SKILL_NAME")
	private String strSkill_Name;
	
	@Column(name="ELEMENT_NAME")
	private String strElement_Name;
		
	@Column(name = "SKILL_ELEMENT_ID")
	private Integer xOSkillElementIndex;
	
	@Column(name = "KNOWLEDGE_ID")
	private Integer xOKnowledgeIndex;
	
	/*@Column(name="MASTER_ROLE_NAME")
	private String masterRoleName;*/
	
	@Column(name="PRACTICE_NAME")
	private String practiceName;
	
	@Column(name="SUB_PRACTICE_NAME")
	private String subPracticeName;	
	
	@Column(name="SYNTEL_ROLE_ID")
	private int syntelRoleId;
	
	@Column(name="SUB_PRACTICE_ID")
	private Integer subPracticeId;
	
	@Column(name="SYNTEL_ROLE_NAME")
	private String syntelRoleName;
	
	@Column(name="GCM_CODE")
	private String gcmCODE;
	
	@Column(name="PROFICIENCY_LEVEL_ID")
	private int proficiencyLevelId;
	
	@Column(name="PROFICIENCY_LEVEL_DESCRIPTION")
	private String proficiencyLevelDescription;
	
	@Column(name="X0_PROFICIENCY_ID")
	private Integer X0ProficiencyId;
	
	@Column(name="X0_PROFICIENCY_DESCRIPTION")
	private String X0ProficiencyDescription;
    
	@Column(name="BAND_GRADE")
	private String bandGrade;
	
	@Column(name="MASTER_ROLE_SHORT_DESCRIPTION")
	private String masterRoleShortDescription;
	
	@Column(name="MASTER_ROLE_LONG_DESCRIPTION")
	private String masterRoleLongDescription;
	
	
	@Column(name="CLIENT_ROLE")
	private String clientRole;
	
	@Column(name="COMMENTS")
	private String comments;

	public Integer getRcRowCount() {
		return rcRowCount;
	}

	public void setRcRowCount(Integer rcRowCount) {
		this.rcRowCount = rcRowCount;
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

	public int getRcId() {
		return rcId;
	}

	public void setRcId(int rcId) {
		this.rcId = rcId;
	}

	public Integer getSubPracticeId() {
		return subPracticeId;
	}

	public String getPracticeName() {
		return practiceName;
	}

	public void setPracticeName(String practiceName) {
		this.practiceName = practiceName;
	}

	public String getSubPracticeName() {
		return subPracticeName;
	}

	public void setSubPracticeName(String subPracticeName) {
		this.subPracticeName = subPracticeName;
	}

	public void setSubPracticeId(Integer subPracticeId) {
		this.subPracticeId = subPracticeId;
	}

	public String getBandGrade() {
		return bandGrade;
	}

	public void setBandGrade(String bandGrade) {
		this.bandGrade = bandGrade;
	}

	public String getMasterRoleShortDescription() {
		return masterRoleShortDescription;
	}

	public void setMasterRoleShortDescription(String masterRoleShortDescription) {
		this.masterRoleShortDescription = masterRoleShortDescription;
	}
	
	
    public int getMasterRoleId() {
		return masterRoleId;
	}

	public void setMasterRoleId(int masterRoleId) {
		this.masterRoleId = masterRoleId;
	}
/*
	public String getMasterRoleName() {
		return masterRoleName;
	}

	public void setMasterRoleName(String masterRoleName) {
		this.masterRoleName = masterRoleName;
	}
*/
	public int getSyntelRoleId() {
		return syntelRoleId;
	}

	public void setSyntelRoleId(int syntelRoleId) {
		this.syntelRoleId = syntelRoleId;
	}

	public String getSyntelRoleName() {
		return syntelRoleName;
	}

	public void setSyntelRoleName(String syntelRoleName) {
		this.syntelRoleName = syntelRoleName;
	}

	public int getProficiencyLevelId() {
		return proficiencyLevelId;
	}

	public void setProficiencyLevelId(int proficiencyLevelId) {
		this.proficiencyLevelId = proficiencyLevelId;
	}

	public Integer getX0ProficiencyId() {
		return X0ProficiencyId;
	}

	public void setX0ProficiencyId(Integer x0ProficiencyId) {
		X0ProficiencyId = x0ProficiencyId;
	}

	public String getClientRole() {
		return clientRole;
	}

	public void setClientRole(String clientRole) {
		this.clientRole = clientRole;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	public String getMasterRoleLongDescription() {
		return masterRoleLongDescription;
	}

	public void setMasterRoleLongDescription(String masterRoleLongDescription) {
		this.masterRoleLongDescription = masterRoleLongDescription;
	}

	public String getProficiencyLevelDescription() {
		return proficiencyLevelDescription;
	}

	public void setProficiencyLevelDescription(String proficiencyLevelDescription) {
		this.proficiencyLevelDescription = proficiencyLevelDescription;
	}

	public String getX0ProficiencyDescription() {
		return X0ProficiencyDescription;
	}

	public void setX0ProficiencyDescription(String x0ProficiencyDescription) {
		X0ProficiencyDescription = x0ProficiencyDescription;
	}

	public String getGcmCODE() {
		return gcmCODE;
	}

	public void setGcmCODE(String gcmCODE) {
		this.gcmCODE = gcmCODE;
	}

	public String getStrKnowledgeName() {
		return strKnowledgeName;
	}

	public void setStrKnowledgeName(String strKnowledgeName) {
		this.strKnowledgeName = strKnowledgeName;
	}

	public String getStrSkill_Name() {
		return strSkill_Name;
	}

	public void setStrSkill_Name(String strSkill_Name) {
		this.strSkill_Name = strSkill_Name;
	}

	public String getStrElement_Name() {
		return strElement_Name;
	}

	public void setStrElement_Name(String strElement_Name) {
		this.strElement_Name = strElement_Name;
	}
	@Column(name="Skill_Complexity_Desc")
	private String skillComplexityDesc;

	public String getSkillComplexityDesc() {
		return skillComplexityDesc;
	}

	public void setSkillComplexityDesc(String skillComplexityDesc) {
		this.skillComplexityDesc = skillComplexityDesc;
	}



	

}
