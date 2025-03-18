package com.rightprice.auth.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import io.swagger.annotations.ApiModelProperty;

@Entity
@Table(name = "[synprod].[RP_Deal_Manual_Roles_STG]")
public class RPDealManualRoleSTG 
{
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "RP_Manual_Role_Id")
	private Integer manualDealRPRoleId;
	
	@Column(name = "RP_Deal_Version_Id")
	private Integer RPDealVersionId;
	
	@Column(name = "Tower_Id")
	private Integer towerId;
	
	@Column(name = "Role_Long_Description")
	@ApiModelProperty(notes="Role_Long_Description")
	private String roleLongDescription;
	
	@Column(name = "Role_Code")
	@ApiModelProperty(notes="Role_Code")
	private String roleCode;
	
	@Column(name = "Client_Role")
	@ApiModelProperty(notes="Client_Role")
	private String clientRole;

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

	public Integer getManualDealRPRoleId() {
		return manualDealRPRoleId;
	}

	public void setManualDealRPRoleId(Integer manualDealRPRoleId) {
		this.manualDealRPRoleId = manualDealRPRoleId;
	}

	public Integer getRPDealVersionId() {
		return RPDealVersionId;
	}

	public void setRPDealVersionId(Integer rPDealVersionId) {
		RPDealVersionId = rPDealVersionId;
	}

	public Integer getTowerId() {
		return towerId;
	}

	public void setTowerId(Integer towerId) {
		this.towerId = towerId;
	}

	public String getRoleLongDescription() {
		return roleLongDescription;
	}

	public void setRoleLongDescription(String roleLongDescription) {
		this.roleLongDescription = roleLongDescription;
	}

	public String getRoleCode() {
		return roleCode;
	}

	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}

	public String getClientRole() {
		return clientRole;
	}

	public void setClientRole(String clientRole) {
		this.clientRole = clientRole;
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
	
	
}
