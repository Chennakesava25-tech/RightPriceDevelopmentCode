package com.rightprice.auth.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class XOSkillMaster 
{
@Id
@Column(name="Skill_Id")
private int skillId;



@Column(name="Skill_Name")
private String skillName;

public int getSkillId() {
	return skillId;
}

public void setSkillId(int skillId) {
	this.skillId = skillId;
}

public String getSkillName() {
	return skillName;
}

public void setSkillName(String skillName) {
	this.skillName = skillName;
}
/*@Column(name="MASTER_ROLE_ID")
private int masterRoleId;

public int getMasterRoleId() {
	return masterRoleId;
}

public void setMasterRoleId(int masterRoleId) {
	this.masterRoleId = masterRoleId;
}*/
}
