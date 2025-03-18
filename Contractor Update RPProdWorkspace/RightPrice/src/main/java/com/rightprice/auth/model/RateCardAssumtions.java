package com.rightprice.auth.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import io.swagger.annotations.ApiModel;
@Entity
@ApiModel(value="Rate card assumptions",description="Rate card assumption Model Attributes")
@Table(name = "synprod.MST_RP_Rate_Card_Assumtions")
public class RateCardAssumtions implements Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="Index_ID")
	private Integer index_id;
	
	@Column(name="Assumtion_Id")
	private Integer assumtion_id;
	
	@Column(name = "Approver_comments")
	private String approverComments;

	@Column(name="Is_Active")
	private Integer isActive;

	
	@Column(name="Created_By")
	private String createdBy;
	
	@Column(name="Created_On")
	private String createdOn;
	
	@Column(name="Updated_By")
	private String updatedBy;
	
	@Column(name="Updated_On")
	private String updatedOn;

	public Integer getAssumtion_id() {
		return assumtion_id;
	}

	public void setAssumtion_id(Integer assumtion_id) {
		this.assumtion_id = assumtion_id;
	}

	public String getApproverComments() {
		return approverComments;
	}

	public void setApproverComments(String approverComments) {
		this.approverComments = approverComments;
	}

	public Integer getIsActive() {
		return isActive;
	}

	public void setIsActive(Integer isActive) {
		this.isActive = isActive;
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

	public Integer getIndex_id() {
		return index_id;
	}

	public void setIndex_id(Integer index_id) {
		this.index_id = index_id;
	}

	
}
