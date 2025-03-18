package com.rightprice.auth.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import io.swagger.annotations.ApiModelProperty;

@Entity
public class XOSkillElementMaster 
{
	@Id
	@Column(name="Skill_Element_Id")
	private int skillElementId;
	
	@Column(name="Skill_Id")
	private int skillId;
	
	@Column(name="Element_Name")
	private String elementName;
	
	

	public int getSkillElementId() {
		return skillElementId;
	}

	public void setSkillElementId(int skillElementId) {
		this.skillElementId = skillElementId;
	}

	public int getSkillId() {
		return skillId;
	}

	public void setSkillId(int skillId) {
		this.skillId = skillId;
	}

	public String getElementName() {
		return elementName;
	}

	public void setElementName(String elementName) {
		this.elementName = elementName;
	}
	
	/*@Column(name="MASTER_ROLE_ID")
	private String masterRoleId;



	public String getMasterRoleId() {
		return masterRoleId;
	}

	public void setMasterRoleId(String masterRoleId) {
		this.masterRoleId = masterRoleId;
	}*/
	
	
	
}
