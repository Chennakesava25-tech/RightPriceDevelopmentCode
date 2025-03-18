package com.rightprice.auth.model;

import java.lang.reflect.Field;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.rightprice.auth.util.AppLoger;

import io.swagger.annotations.ApiModelProperty;

@Entity
@Table(name = "SYNPROD.RP_Deal_Roles")
public class DealRoles {
	@Id
	@GeneratedValue
	@Column(name = "DEAL_ROLE_ID")
	private Integer dealRoleId;

	@Column(name = "RP_Deal_Version_Id")
	private Integer dealVersionId;

	@Column(name = "RC_ID")
	private Integer rcId;

	@Column(name = "MASTER_ROLE_ID")
	private int masterRoleId;

	@Column(name = "CLIENT_ROLE")
	private String clientRole;

	@Column(name = "SKILL_ID")
	private Integer xOSkillIndex;

	@Column(name = "SKILL_ELEMENT_ID")
	private Integer xOSkillElementIndex;

	@Column(name = "KNOWLEDGE_ID")
	private Integer xOKnowledgeIndex;

	@Column(name = "COMMENTS")
	private String comments;

	@Column(name = "IS_ACTIVE")
	private int isAtive = 1;

	@Column(name = "CREATED_BY", updatable = false)
	private String createdBy;

	@Column(name = "CREATED_ON", updatable = false)
	private String createdOn;

	@Column(name = "UPDATED_BY")
	private String updatedBy;

	@Column(name = "UPDATED_ON")
	private String updatedOn;

	@Column(name = "GCM_CODE")
	private String gcmCODE;
	
	public Integer getDealRoleId() {
		return dealRoleId;
	}

	public void setDealRoleId(Integer dealRoleId) {
		this.dealRoleId = dealRoleId;
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

	public int getIsAtive() {
		return isAtive;
	}

	public void setIsAtive(int isAtive) {
		this.isAtive = isAtive;
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

	public String getGcmCODE() {
		return gcmCODE;
	}

	public void setGcmCODE(String gcmCODE) {
		this.gcmCODE = gcmCODE;
	}

	public String toString() {
		StringBuilder result = new StringBuilder();
		String newLine = System.getProperty("line.separator");

		result.append(this.getClass().getName());
		result.append(" Object {");
		result.append(newLine);

		// determine fields declared in this class only (no fields of
		// superclass)
		Field[] fields = this.getClass().getDeclaredFields();

		// print field names paired with their values
		for (Field field : fields) {
			result.append("  ");
			try {
				result.append(field.getName());
				result.append(": ");
				// requires access to private field:
				result.append(field.get(this));
			} catch (IllegalAccessException ex) {
				AppLoger.APPLOGGER.info(ex);
			}
			result.append(newLine);
		}
		result.append("}");

		return result.toString();
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
	
	
	
}
