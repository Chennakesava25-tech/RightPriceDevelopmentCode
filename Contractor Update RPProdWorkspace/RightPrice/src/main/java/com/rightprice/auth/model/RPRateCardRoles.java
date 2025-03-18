package com.rightprice.auth.model;

import java.io.Serializable;
import java.lang.reflect.Field;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.rightprice.auth.util.AppLoger;

import io.swagger.annotations.ApiModelProperty;


@Entity
@Table(name="SYNPROD.RP_Rate_Card_Roles")
public class RPRateCardRoles implements Serializable
{
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	@Column(name = "RC_ROLE_ID")
	@ApiModelProperty(notes="RC Role Id")
	private int rcRoleId;		
	
	@Column(name = "RC_ID")
	@ApiModelProperty(notes="RC Id")
	private int rcId;
	
	@Column(name = "SKILL_ID")
	@ApiModelProperty(notes="Skill Id")
	private Integer xOSkillIndex;
	
	@OneToOne(cascade = javax.persistence.CascadeType.ALL)
    @JoinColumn(name = "SKILL_ID",referencedColumnName = "SKILL_ID", insertable = false, updatable = false)
	private MasterXOSkill masterXOSkill;
	
	@Column(name = "GCM_CODE")
	@ApiModelProperty(notes="GCM CODE")
	private String gcmCODE;
	
	public String getGcmCODE() {
		return gcmCODE;
	}

	public void setGcmCODE(String gcmCODE) {
		this.gcmCODE = gcmCODE;
	}

	public MasterXOSkill getMasterXOSkill() {
		return masterXOSkill;
	}

	public void setMasterXOSkill(MasterXOSkill masterXOSkill) {
		this.masterXOSkill = masterXOSkill;
	}

	@Column(name = "SKILL_ELEMENT_ID")
	@ApiModelProperty(notes="SKILL ELEMENT ID")
	private Integer xOSkillElementIndex;
	
	@OneToOne(cascade = javax.persistence.CascadeType.ALL)
    @JoinColumn(name = "SKILL_ELEMENT_ID",referencedColumnName = "SKILL_ELEMENT_ID", insertable = false, updatable = false)
	private MasterXOSkillElement masterXOSkillElement;
	
	
	public MasterXOSkillElement getMasterXOSkillElement() {
		return masterXOSkillElement;
	}

	public void setMasterXOSkillElement(MasterXOSkillElement masterXOSkillElement) {
		this.masterXOSkillElement = masterXOSkillElement;
	}
	
	
	@Column(name = "KNOWLEDGE_ID")
	@ApiModelProperty(notes="KNOWLEDGE ID")
	private Integer xOKnowledgeIndex;
	
	@OneToOne(cascade = javax.persistence.CascadeType.ALL)
    @JoinColumn(name = "KNOWLEDGE_ID",referencedColumnName = "KNOWLEDGE_ID", insertable = false, updatable = false)
	private MasterX0Knowledge masterX0Knowledge;
	
	
	public MasterX0Knowledge getMasterX0Knowledge() {
		return masterX0Knowledge;
	}

	public void setMasterX0Knowledge(MasterX0Knowledge masterX0Knowledge) {
		this.masterX0Knowledge = masterX0Knowledge;
	}

	@Column(name = "RC_MASTER_ROLE_ID")
	@ApiModelProperty(notes="RC MASTER ROLE ID")
	private int masterRoleId;
	
	@Column(name = "CLIENT_ROLE")
	@ApiModelProperty(notes="CLIENT ROLE")
	private String clientRole;
	
	@Column(name = "COMMENTS")
	@ApiModelProperty(notes="COMMENTS")
	private String comments;
	
	@Column(name = "RC_Row_Count")
	@ApiModelProperty(notes="RC_Row_Count")
	private Integer rcRowCount;
	
	@Column(name = "IS_ACTIVE")
	@ApiModelProperty(notes="IS ACTIVE")
	private int activeStatus = 1;

	@Column(name = "CREATED_BY",updatable=false)
	@ApiModelProperty(notes="CREATED BY")
	private String createdBy;

	@Column(name = "CREATED_ON",updatable=false)
	@ApiModelProperty(notes="CREATED ON")
	private String createdOn;

	@Column(name = "UPDATED_BY")
	@ApiModelProperty(notes="UPDATED BY")
	private String lastUpdatedBy;

	@Column(name = "UPDATED_ON")
	@ApiModelProperty(notes="UPDATED ON")
	private String lastUpdatedOn;

	public int getRcRoleId() {
		return rcRoleId;
	}

	public void setRcRoleId(int rcRoleId) {
		this.rcRoleId = rcRoleId;
	}

	public int getRcId() {
		return rcId;
	}

	public void setRcId(int rcId) {
		this.rcId = rcId;
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

	public int getMasterRoleId() {
		return masterRoleId;
	}

	public void setMasterRoleId(int masterRoleId) {
		this.masterRoleId = masterRoleId;
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

	public int getActiveStatus() {
		return activeStatus;
	}

	public void setActiveStatus(int activeStatus) {
		this.activeStatus = activeStatus;
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

	public String getLastUpdatedBy() {
		return lastUpdatedBy;
	}

	public void setLastUpdatedBy(String lastUpdatedBy) {
		this.lastUpdatedBy = lastUpdatedBy;
	}

	public String getLastUpdatedOn() {
		return lastUpdatedOn;
	}

	public void setLastUpdatedOn(String lastUpdatedOn) {
		this.lastUpdatedOn = lastUpdatedOn;
	}
	
	public Integer getRcRowCount() {
		return rcRowCount;
	}

	public void setRcRowCount(Integer rcRowCount) {
		this.rcRowCount = rcRowCount;
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
