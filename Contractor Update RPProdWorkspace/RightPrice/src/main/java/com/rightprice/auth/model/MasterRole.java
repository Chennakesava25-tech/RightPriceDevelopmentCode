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
public class MasterRole implements Serializable{

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
	
	@Column(name = "IS_ACTIVE")
	@ApiModelProperty(notes = "Active status")
	private int isActive;
	
	@Column(name = "Band_Grade")
	@ApiModelProperty(notes="Band_Grade")
	private String bandGrade;
	
	@Column(name = "Sub_Practice_Id")
	@ApiModelProperty(notes="Sub_Practice_Id")
	private int subPracticeId;
	

	@Column(name = "Syntel_Role_Description")
	private String syntelRoleDescription;

	@Column(name = "GCM_CODE")
	@ApiModelProperty(notes="GCM_CODE")
	private String strGcmCODE;
	
	public String getStrGcmCODE() {
		return strGcmCODE;
	}

	public void setStrGcmCODE(String strGcmCODE) {
		this.strGcmCODE = strGcmCODE;
	}

	public String getSyntelRoleDescription() {
		return syntelRoleDescription;
	}

	public void setSyntelRoleDescription(String syntelRoleDescription) {
		this.syntelRoleDescription = syntelRoleDescription;
	}

	public String getBandGrade() {
		return bandGrade;
	}

	public void setBandGrade(String bandGrade) {
		this.bandGrade = bandGrade;
	}

	public int getSubPracticeId() {
		return subPracticeId;
	}

	public void setSubPracticeId(int subPracticeId) {
		this.subPracticeId = subPracticeId;
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

	public int getIsActive() {
		return isActive;
	}

	public void setIsActive(int isActive) {
		this.isActive = isActive;
	}
	
	}
