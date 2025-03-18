package com.rightprice.auth.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import io.swagger.annotations.ApiModelProperty;

@Entity
public class MstRpMasterRolesSearch
{

	@Id
	@Column(name="MASTER_ROLE_ID")
	private int masterRoleId;
	
	@Column(name="MASTER_ROLE_SHORT_DESCRIPTION")
	private String masterRoleShortDescription;
	
	@Column(name="MASTER_ROLE_LONG_DESCRIPTION")
	private String masterRoleLongDescription;
	
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
	
	@Column(name="SUB_PRACTICE_ID")
	private Integer subPracticeId;
	
	@Column(name="SUB_PRACTICE_NAME")
	private String subPracticeName;	
	
    @Column(name="PRACTICE_NAME")
	private String practiceName;
    
    @Column(name="Syntel_Role_Id")
    private Integer syntelRoleId;
    
    @Column(name="SYNTEL_ROLE_NAME")
    private String syntelRoleName;
    
    @Column(name="GCM_CODE")
	private String gcmCODE;
    
    
    
    public String getGcmCODE() {
		return gcmCODE;
	}

	public void setGcmCODE(String gcmCODE) {
		this.gcmCODE = gcmCODE;
	}

	public int getMasterRoleId() {
		return masterRoleId;
	}

	public void setMasterRoleId(int masterRoleId) {
		this.masterRoleId = masterRoleId;
	}

	public String getMasterRoleShortDescription() {
		return masterRoleShortDescription;
	}

	public void setMasterRoleShortDescription(String masterRoleShortDescription) {
		this.masterRoleShortDescription = masterRoleShortDescription;
	}

	public String getMasterRoleLongDescription() {
		return masterRoleLongDescription;
	}

	public void setMasterRoleLongDescription(String masterRoleLongDescription) {
		this.masterRoleLongDescription = masterRoleLongDescription;
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

	public String getBandGrade() {
		return bandGrade;
	}

	public void setBandGrade(String bandGrade) {
		this.bandGrade = bandGrade;
	}

	public Integer getSubPracticeId() {
		return subPracticeId;
	}

	public void setSubPracticeId(Integer subPracticeId) {
		this.subPracticeId = subPracticeId;
	}

	public String getSubPracticeName() {
		return subPracticeName;
	}

	public void setSubPracticeName(String subPracticeName) {
		this.subPracticeName = subPracticeName;
	}

	public String getPracticeName() {
		return practiceName;
	}

	public void setPracticeName(String practiceName) {
		this.practiceName = practiceName;
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

	public Integer getSyntelRoleId() {
		return syntelRoleId;
	}

	public void setSyntelRoleId(Integer syntelRoleId) {
		this.syntelRoleId = syntelRoleId;
	}

	public String getSyntelRoleName() {
		return syntelRoleName;
	}

	public void setSyntelRoleName(String syntelRoleName) {
		this.syntelRoleName = syntelRoleName;
	}
	

    @Column(name="Skill_Complexity_Desc")
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
