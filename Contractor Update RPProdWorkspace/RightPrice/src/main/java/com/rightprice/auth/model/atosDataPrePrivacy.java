package com.rightprice.auth.model;
import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@Entity
@Table(name = "submitTable")
@ApiModel(value="submitTable",description="Atos PRe Privacy Model Attributes")
public class atosDataPrePrivacy implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID")
	private int id;
	
	@Column(name = "userId")
	private String userId;
	
	@Column(name = "checkSubmit")
	@ApiModelProperty(notes="checkSubmit Name")
	private Integer checkSubmit;
	
	@Column(name = "is_Active")
	@ApiModelProperty(notes="is_Active")
	private Integer is_Active;
	
	@Column(name = "CREATED_BY", updatable=false)
	@ApiModelProperty(notes="Created By")
	private String createdBy;
	
	@Column(name = "CREATED_ON", updatable=false)
	@ApiModelProperty(notes="Created On")
	private String createdOn;
	
	@Column(name = "last_updated_by")
	@ApiModelProperty(notes="last_updated_by ")
	private String last_updated_by;
	
	@Column(name = "last_updated_dt")
	@ApiModelProperty(notes="last_updated_dt On")
	private String last_updated_dt;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Integer getCheckSubmit() {
		return checkSubmit;
	}

	public void setCheckSubmit(Integer checkSubmit) {
		this.checkSubmit = checkSubmit;
	}

	public Integer getIs_Active() {
		return is_Active;
	}

	public void setIs_Active(Integer is_Active) {
		this.is_Active = is_Active;
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

	public String getLast_updated_by() {
		return last_updated_by;
	}

	public void setLast_updated_by(String last_updated_by) {
		this.last_updated_by = last_updated_by;
	}

	public String getLast_updated_dt() {
		return last_updated_dt;
	}

	public void setLast_updated_dt(String last_updated_dt) {
		this.last_updated_dt = last_updated_dt;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	
}


