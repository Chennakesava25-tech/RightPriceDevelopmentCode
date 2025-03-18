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
import javax.persistence.Transient;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import com.rightprice.auth.util.AppLoger;
import com.rightprice.auth.util.DateUtil;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@Entity
@ApiModel(value="TrnRole details",description="TrnRole details Model Attributes")
@Table(name = "synprod.trn_role_details  ")
public class TrnRole implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = " TRN_ROLE_DETAILS_ID")
	private Integer  trnroledetailsid;
	
	
	
	@Column(name = "MST_ROLE_ID")
	private Integer mstroleid ;
	

	@Column(name = "MENU_ID")
	private String menuid;
	
	
	@Column(name ="ACTIVE_STATUS")
	private Integer Activestatus;
	
	
	
	@Column(name = "CREATED_BY", updatable=false)
	@ApiModelProperty(notes="Created by")
	private String createdBy;
	
	@Column(name = "CREATED_ON", updatable=false)
	@ApiModelProperty(notes="Created date")
	private String createdOn;
	
	@Column(name = "LAST_UPDATED_BY")
	@ApiModelProperty(notes=" Last Updated by")
	private String lastupdatedBy;
	
	@Column(name = "LAST_UPDATED_ON")
	@ApiModelProperty(notes="Last Updated date")
	private String lastupdatedOn;

	public Integer getTrnroledetailsid() {
		return trnroledetailsid;
	}

	public void setTrnroledetailsid(Integer trnroledetailsid) {
		this.trnroledetailsid = trnroledetailsid;
	}

	public Integer getMstroleid() {
		return mstroleid;
	}

	public void setMstroleid(Integer mstroleid) {
		this.mstroleid = mstroleid;
	}

	public String getMenuid() {
		return menuid;
	}

	public void setMenuid(String menuid) {
		this.menuid = menuid;
	}

	public Integer getActivestatus() {
		return Activestatus;
	}

	public void setActivestatus(Integer activestatus) {
		Activestatus = activestatus;
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

	public String getLastupdatedBy() {
		return lastupdatedBy;
	}

	public void setLastupdatedBy(String lastupdatedBy) {
		this.lastupdatedBy = lastupdatedBy;
	}

	public String getLastupdatedOn() {
		return lastupdatedOn;
	}

	public void setLastupdatedOn(String lastupdatedOn) {
		this.lastupdatedOn = lastupdatedOn;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@OneToOne(cascade = javax.persistence.CascadeType.ALL)
	@JoinColumn(name ="MST_ROLE_ID",referencedColumnName = "MST_ROLE_ID",insertable=false, updatable=false)
	private Mstrole role;

	public Mstrole getRole() {
		return role;
	}

	public void setRole(Mstrole role) {
		this.role = role;
	}

	public int getRequesterFlag() {
		return requesterFlag;
	}

	public void setRequesterFlag(int requesterFlag) {
		this.requesterFlag = requesterFlag;
	}

	@Transient
	private int requesterFlag = 0;
	
	
	
}
