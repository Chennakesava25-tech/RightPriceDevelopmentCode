package com.rightprice.auth.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import io.swagger.annotations.ApiModelProperty;

@Entity
@Table(name = "synprod.MST_RP_Emp_Designation")
public class EmpDesignation {
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "Emp_Designation_Id")
	private Integer desgId;
	
	@Column(name = "Emp_Designation_Description")
	private String desgDesc;
	
	@Column(name = "EMP_Band_Id")
	private Integer bandId;
	
	@OneToOne(cascade = javax.persistence.CascadeType.ALL)
    @JoinColumn(name = "EMP_Band_Id",referencedColumnName = "ID", insertable = false, updatable = false)
	private Band bandmap;
	
	@Column(name = "EMP_Grade_Id")
	private Integer gradeId;
	
	@OneToOne(cascade = javax.persistence.CascadeType.ALL)
    @JoinColumn(name = "EMP_Grade_Id",referencedColumnName = "ID", insertable = false, updatable = false)
	private Grade grademap;
	
	@Column(name = "Knowledge_Level_Id")
	private Integer knowId;
	
	@Column(name = "Skill_Level_Id")
	private Integer skillId;
	
	@Column(name = "New_Emp_Band_Id")
	private Integer newbandId;
	
	@Column(name = "New_Emp_Grade_Id")
	private Integer newgradeId;
	
	@Column(name = "Minimum_Experience_Years")
	private String minExpYrs;
	
	@Column(name = "Maximum_Experience_Years")
	private String maxExpYrs;
	
	@Column(name = "Is_Active")
	private Integer StatusModel;
	
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

	@Column(name = "GCM_CODE")
	@ApiModelProperty(notes="GCM CODE")
	private String gcmCODE;
	
	public String getGcmCODE() {
		return gcmCODE;
	}

	public void setGcmCODE(String gcmCODE) {
		this.gcmCODE = gcmCODE;
	}
	
	public Integer getDesgId() {
		return desgId;
	}

	public void setDesgId(Integer desgId) {
		this.desgId = desgId;
	}

	public String getDesgDesc() {
		return desgDesc;
	}

	public void setDesgDesc(String desgDesc) {
		this.desgDesc = desgDesc;
	}

	public Integer getBandId() {
		return bandId;
	}

	public void setBandId(Integer bandId) {
		this.bandId = bandId;
	}

	public Integer getGradeId() {
		return gradeId;
	}

	public void setGradeId(Integer gradeId) {
		this.gradeId = gradeId;
	}

	public Integer getKnowId() {
		return knowId;
	}

	public void setKnowId(Integer knowId) {
		this.knowId = knowId;
	}

	public Integer getSkillId() {
		return skillId;
	}

	public void setSkillId(Integer skillId) {
		this.skillId = skillId;
	}

	public Integer getNewbandId() {
		return newbandId;
	}

	public void setNewbandId(Integer newbandId) {
		this.newbandId = newbandId;
	}

	public Integer getNewgradeId() {
		return newgradeId;
	}

	public void setNewgradeId(Integer newgradeId) {
		this.newgradeId = newgradeId;
	}

	public String getMinExpYrs() {
		return minExpYrs;
	}

	public void setMinExpYrs(String minExpYrs) {
		this.minExpYrs = minExpYrs;
	}

	public Grade getGrademap() {
		return grademap;
	}

	public void setGrademap(Grade grademap) {
		this.grademap = grademap;
	}

	public String getMaxExpYrs() {
		return maxExpYrs;
	}

	public void setMaxExpYrs(String maxExpYrs) {
		this.maxExpYrs = maxExpYrs;
	}

	public Integer getStatusModel() {
		return StatusModel;
	}

	public void setStatusModel(Integer statusModel) {
		StatusModel = statusModel;
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

	public Band getBandmap() {
		return bandmap;
	}

	public void setBandmap(Band bandmap) {
		this.bandmap = bandmap;
	}
}
