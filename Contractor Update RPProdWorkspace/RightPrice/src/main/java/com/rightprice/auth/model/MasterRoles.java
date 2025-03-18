package com.rightprice.auth.model;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import com.rightprice.auth.util.AppLoger;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@Entity
@ApiModel(value="Master Roles",description="Master Roles")
@Table(name = "synprod.MST_RP_Master_Roles")
public class MasterRoles implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "MASTER_ROLE_ID")
	@ApiModelProperty(notes="Master Role ID")
	private Integer masterRoleId;
	
	@Column(name = "MASTER_ROLE_NAME")
	@ApiModelProperty(notes="Master Role Name")
	private String masterRoleName;
	
	@Column(name = "MASTER_ROLE_SHORT_DESCRIPTION")
	@ApiModelProperty(notes="Master Role Short Description")
	private String masterRoleShortDescription;

	@Column(name = "MASTER_ROLE_LONG_DESCRIPTION")
	@ApiModelProperty(notes="Master Role Long Description")
	private String masterRoleLongDescription;
	
	@Column(name = "SUB_PRACTICE_ID")
	@ApiModelProperty(notes="Sub Practice Id")
	private Integer subPracticeId;
	
	@Column(name = "PROFICIENCY_LEVEL_ID")
	@ApiModelProperty(notes="Proficiency Level Id")
	private Integer proficiencyLevelId;
	
	@Column(name = "PROFICIENCY_LEVEL_DESCRIPTION")
	@ApiModelProperty(notes="Proficiency Level Description")
	private String proficiencyLevelDescription;

	@Column(name = "SKILLS_ID")
	@ApiModelProperty(notes="Skills Id")
	private Integer skillsId;
	
	@Column(name = "SKILL_DESCRIPTION")
	@ApiModelProperty(notes="Skill Description")
	private String skillDescription;
	
	@Column(name = "X0_Skill_Id")
	@ApiModelProperty(notes="X0 Skills Id")
	private Integer x0SkillId;
	
	@Column(name = "X0_SKILL_DESCRIPTION")
	@ApiModelProperty(notes="X0 Skill Description")
	private String x0SkillDescription;
	
	@Column(name = "X0_Skill_Element_Id")
	@ApiModelProperty(notes="X0 Skill Element Id")
	private Integer x0SkillElementId;
	
	@Column(name = "X0_SKILL_ELEMENT_DESCRIPTION")
	@ApiModelProperty(notes="X0 Skill Element Description")
	private String x0SkillElementDescription;
	
	@Column(name = "X0_KNOWLEDGE_AREA_ID")
	@ApiModelProperty(notes="X0 Knowledge Area Id")
	private Integer x0KnowledgeAreaId;
	
	@Column(name = "X0_KNOWLEDGE_AREA_DESCRIPTION")
	@ApiModelProperty(notes="X0 Knowledge Area Description")
	private String x0KnowledgeAreaDescription;
	
	@Column(name = "X0_PROFICIENCY_ID")
	@ApiModelProperty(notes="X0 Proficiency Id")
	private Integer x0ProficiencyId;
	
	@Column(name = "X0_PROFICIENCY_DESCRIPTION")
	@ApiModelProperty(notes="X0 Proficiency Description")
	private String x0ProficiencyDescription;
	
	@Column(name = "DESIGNATION_ID")
	@ApiModelProperty(notes="Designation Id")
	private Integer designationId;
	
	@Column(name = "DESIGNATION_ID2")
	@ApiModelProperty(notes="Designation Id 2")
	private Integer designationId2;
	
	@Column(name = "DESIGNATION_ID3")
	@ApiModelProperty(notes="Designation Id 3")
	private Integer designationId3;
	
	@Column(name = "DESIGNATION_ID4")
	@ApiModelProperty(notes="Designation Id 4")
	private Integer designationId4;
	
	@Column(name = "DESIGNATION1_PERCENT")
	@ApiModelProperty(notes="Designation Percent 1")
	private Integer designation1Percent;
	
	@Column(name = "DESIGNATION2_PERCENT")
	@ApiModelProperty(notes="Designation Percent 2")
	private Integer designation2Percent;
	
	@Column(name = "DESIGNATION3_PERCENT")
	@ApiModelProperty(notes="Designation Percent 3")
	private Integer designation3Percent;
	
	@Column(name = "DESIGNATION4_PERCENT")
	@ApiModelProperty(notes="Designation Percent 4")
	private Integer designation4Percent;
		
	@Column(name = "BAND_GRADE")
	@ApiModelProperty(notes="Band Grade")
	private String bandGrade;
	
	@Column(name = "SYNTEL_ROLE_ID")
	@ApiModelProperty(notes="Syntel Role Id")
	private Integer syntelRoleId;
	
	@Column(name = "SYNTEL_ROLE_DESCRIPTION")
	@ApiModelProperty(notes="Syntel Role Description")
	private String syntelRoleDescription;
	
	@Column(name = "IS_ACTIVE")
	@ApiModelProperty(notes="Active status")
	private int isActive;
	
	@Column(name = "CREATED_BY", updatable=false)
	@ApiModelProperty(notes="Created by")
	private String createdBy;
	
	@Column(name = "CREATED_ON", updatable=false)
	@ApiModelProperty(notes="Created date")
	private String createdDate;
	
	@Column(name = "UPDATED_BY")
	@ApiModelProperty(notes="Updated by")
	private String updatedBy;
	
	@Column(name = "UPDATED_ON")
	@ApiModelProperty(notes="Updated date")
	private String updatedDate;

	@OneToOne(cascade = javax.persistence.CascadeType.ALL)
    @JoinColumn(name = "SYNTEL_ROLE_ID",insertable=false,updatable=false)
	private MasterSyntelRoles masterSyntelRoles;
	
	@OneToOne(cascade = javax.persistence.CascadeType.ALL)
    @JoinColumn(name = "SUB_PRACTICE_ID",insertable=false,updatable=false)
	private MasterSubPractice masterSubPractice;
	
	
	
	
	
	

	@OneToOne(cascade = javax.persistence.CascadeType.ALL)
    @JoinColumn(name = "DESIGNATION_ID",insertable=false,updatable=false)
	private Designation designation1;
	
	@OneToOne(cascade = javax.persistence.CascadeType.ALL)
    @JoinColumn(name = "DESIGNATION_ID2",insertable=false,updatable=false)
	private Designation designation2;
	
	@OneToOne(cascade = javax.persistence.CascadeType.ALL)
    @JoinColumn(name = "DESIGNATION_ID3",insertable=false,updatable=false)
	private Designation designation3;
	
	@OneToOne(cascade = javax.persistence.CascadeType.ALL)
    @JoinColumn(name = "DESIGNATION_ID4",insertable=false,updatable=false)
	private Designation designation4;
	
	@OneToMany( cascade={CascadeType.ALL}, fetch = FetchType.LAZY)
	@JoinColumn(name="MASTER_ROLE_ID")
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<MasterRate> masterRate;
	
	@Column(name = "GCM_CODE")
	@ApiModelProperty(notes="GCM CODE")
	private String gcmCODE;
	
	public String getGcmCODE() {
		return gcmCODE;
	}

	public void setGcmCODE(String gcmCODE) {
		this.gcmCODE = gcmCODE;
	}

	public Integer getMasterRoleId() {
		return masterRoleId;
	}

	public void setMasterRoleId(Integer masterRoleId) {
		this.masterRoleId = masterRoleId;
	}

	public String getMasterRoleName() {
		return masterRoleName;
	}

	public void setMasterRoleName(String masterRoleName) {
		this.masterRoleName = masterRoleName;
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

	public Integer getSubPracticeId() {
		return subPracticeId;
	}

	public void setSubPracticeId(Integer subPracticeId) {
		this.subPracticeId = subPracticeId;
	}

	public Integer getProficiencyLevelId() {
		return proficiencyLevelId;
	}

	public void setProficiencyLevelId(Integer proficiencyLevelId) {
		this.proficiencyLevelId = proficiencyLevelId;
	}

	public String getProficiencyLevelDescription() {
		return proficiencyLevelDescription;
	}

	public void setProficiencyLevelDescription(String proficiencyLevelDescription) {
		this.proficiencyLevelDescription = proficiencyLevelDescription;
	}

	public Integer getSkillsId() {
		return skillsId;
	}

	public void setSkillsId(Integer skillsId) {
		this.skillsId = skillsId;
	}

	public String getSkillDescription() {
		return skillDescription;
	}

	public void setSkillDescription(String skillDescription) {
		this.skillDescription = skillDescription;
	}

	public Integer getX0SkillId() {
		return x0SkillId;
	}

	public void setX0SkillId(Integer x0SkillId) {
		this.x0SkillId = x0SkillId;
	}

	public String getX0SkillDescription() {
		return x0SkillDescription;
	}

	public void setX0SkillDescription(String x0SkillDescription) {
		this.x0SkillDescription = x0SkillDescription;
	}

	public Integer getX0SkillElementId() {
		return x0SkillElementId;
	}

	public void setX0SkillElementId(Integer x0SkillElementId) {
		this.x0SkillElementId = x0SkillElementId;
	}

	public String getX0SkillElementDescription() {
		return x0SkillElementDescription;
	}

	public void setX0SkillElementDescription(String x0SkillElementDescription) {
		this.x0SkillElementDescription = x0SkillElementDescription;
	}

	public Integer getX0KnowledgeAreaId() {
		return x0KnowledgeAreaId;
	}

	public void setX0KnowledgeAreaId(Integer x0KnowledgeAreaId) {
		this.x0KnowledgeAreaId = x0KnowledgeAreaId;
	}

	public String getX0KnowledgeAreaDescription() {
		return x0KnowledgeAreaDescription;
	}

	public void setX0KnowledgeAreaDescription(String x0KnowledgeAreaDescription) {
		this.x0KnowledgeAreaDescription = x0KnowledgeAreaDescription;
	}

	public Integer getX0ProficiencyId() {
		return x0ProficiencyId;
	}

	public void setX0ProficiencyId(Integer x0ProficiencyId) {
		this.x0ProficiencyId = x0ProficiencyId;
	}

	public String getX0ProficiencyDescription() {
		return x0ProficiencyDescription;
	}

	public void setX0ProficiencyDescription(String x0ProficiencyDescription) {
		this.x0ProficiencyDescription = x0ProficiencyDescription;
	}

	public Integer getDesignationId() {
		return designationId;
	}

	public void setDesignationId(Integer designationId) {
		this.designationId = designationId;
	}

	public Integer getDesignationId2() {
		return designationId2;
	}

	public void setDesignationId2(Integer designationId2) {
		this.designationId2 = designationId2;
	}

	public Integer getDesignationId3() {
		return designationId3;
	}

	public void setDesignationId3(Integer designationId3) {
		this.designationId3 = designationId3;
	}

	public Integer getDesignationId4() {
		return designationId4;
	}

	public void setDesignationId4(Integer designationId4) {
		this.designationId4 = designationId4;
	}

	public Integer getDesignation1Percent() {
		return designation1Percent;
	}

	public void setDesignation1Percent(Integer designation1Percent) {
		this.designation1Percent = designation1Percent;
	}

	public Integer getDesignation2Percent() {
		return designation2Percent;
	}

	public void setDesignation2Percent(Integer designation2Percent) {
		this.designation2Percent = designation2Percent;
	}

	public Integer getDesignation3Percent() {
		return designation3Percent;
	}

	public void setDesignation3Percent(Integer designation3Percent) {
		this.designation3Percent = designation3Percent;
	}

	public Integer getDesignation4Percent() {
		return designation4Percent;
	}

	public void setDesignation4Percent(Integer designation4Percent) {
		this.designation4Percent = designation4Percent;
	}

	public String getBandGrade() {
		return bandGrade;
	}

	public void setBandGrade(String bandGrade) {
		this.bandGrade = bandGrade;
	}

	public Integer getSyntelRoleId() {
		return syntelRoleId;
	}

	public void setSyntelRoleId(Integer syntelRoleId) {
		this.syntelRoleId = syntelRoleId;
	}

	public String getSyntelRoleDescription() {
		return syntelRoleDescription;
	}

	public void setSyntelRoleDescription(String syntelRoleDescription) {
		this.syntelRoleDescription = syntelRoleDescription;
	}

	public int getIsActive() {
		return isActive;
	}

	public void setIsActive(int isActive) {
		this.isActive = isActive;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public String getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(String updatedDate) {
		this.updatedDate = updatedDate;
	}

	public MasterSyntelRoles getMasterSyntelRoles() {
		return masterSyntelRoles;
	}

	public void setMasterSyntelRoles(MasterSyntelRoles masterSyntelRoles) {
		this.masterSyntelRoles = masterSyntelRoles;
	}
	
	public MasterSubPractice getMasterSubPractice() {
		return masterSubPractice;
	}

	public void setMasterSubPractice(MasterSubPractice masterSubPractice) {
		this.masterSubPractice = masterSubPractice;
	}

	public Designation getDesignation1() {
		return designation1;
	}

	public void setDesignation1(Designation designation1) {
		this.designation1 = designation1;
	}

	public Designation getDesignation2() {
		return designation2;
	}

	public void setDesignation2(Designation designation2) {
		this.designation2 = designation2;
	}

	public Designation getDesignation3() {
		return designation3;
	}

	public void setDesignation3(Designation designation3) {
		this.designation3 = designation3;
	}

	public Designation getDesignation4() {
		return designation4;
	}

	public void setDesignation4(Designation designation4) {
		this.designation4 = designation4;
	}
	
	
	public List<MasterRate> getMasterRate() {
		return masterRate;
	}

	public void setMasterRate(List<MasterRate> masterRate) {
		this.masterRate = masterRate;
	}
	@Column(name = "Skill_Complexity_Id")
	@ApiModelProperty(notes="Skill Complexity Id")
	private Integer SkillComplexityId;
	
	
	public Integer getSkillComplexityId() {
		return SkillComplexityId;
	}

	public void setSkillComplexityId(Integer skillComplexityId) {
		SkillComplexityId = skillComplexityId;
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

	public String toString() {
		  StringBuilder result = new StringBuilder();
		  String newLine = System.getProperty("line.separator");

		  result.append( this.getClass().getName() );
		  result.append( " Object {" );
		  result.append(newLine);

		  //determine fields declared in this class only (no fields of superclass)
		  Field[] fields = this.getClass().getDeclaredFields();

		  //print field names paired with their values
		  for ( Field field : fields  ) {
		    result.append("  ");
		    try {
		      result.append( field.getName() );
		      result.append(": ");
		      //requires access to private field:
		      result.append( field.get(this) );
		    } catch ( IllegalAccessException ex ) {
		      AppLoger.APPLOGGER.info(ex);
		    }
		    result.append(newLine);
		  }
		  result.append("}");

		  return result.toString();
		}

	

	
	}
