
package com.rightprice.auth.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;

import io.swagger.annotations.ApiModel;

@Entity
@ApiModel(value="Rate card details",description="Rate card details Model Attributes")
@Table(name = "synprod.RP_CRM_Changes_History")
@DynamicUpdate
public class CRMChangesHistory {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "sr_no")
	private Integer srno;

	@Column(name = "CRM_DEAL_ID")
	private String crmDealId;
	
	@Column(name = "Old_Start_Date")
	private String oldstartDate;
	
	
	@Column(name = "New_Start_Date")
	private String newstartDate;
	
	@Column(name = "Old_End_Date")
	private String oldenddate;

	@Column(name = "New_End_Date")
	private String newEnddate;
	
	@Column(name = "Old_Deal_Type_Id")
	private Integer olddealTypeId;
	
	@Column(name = "New_Deal_Type_Id")
	private Integer newDealTypeId;

	@Column(name = "Old_TCV")
	private Double oldTCV;
	
	@Column(name = "New_TCV")
	private Double newTCV;
	
//	@Column(name = "Old_Project_Industry")
//	private Integer oldProjectIndustry;
//	
//	@Column(name = "New_Project_Industry")
//	private Integer newProjectIndustry;
	
	@Column(name = "Remark")
	private String remark;
	
//	@Column(name = "Updated_By")
//	private String updatedby;
//	
	@Column(name = "Updated_On")
	private String updatedOn;
	
	@Column(name = "Email_status")
	private String emailstatus;


	public Integer getSrno() {
		return srno;
	}

	public void setSrno(Integer srno) {
		this.srno = srno;
	}



	public String getCrmDealId() {
		return crmDealId;
	}

	public void setCrmDealId(String crmDealId) {
		this.crmDealId = crmDealId;
	}

	public String getOldstartDate() {
		return oldstartDate;
	}

	public void setOldstartDate(String oldstartDate) {
		this.oldstartDate = oldstartDate;
	}

	public String getNewstartDate() {
		return newstartDate;
	}

	public void setNewstartDate(String newstartDate) {
		this.newstartDate = newstartDate;
	}

	public String getOldenddate() {
		return oldenddate;
	}

	public void setOldenddate(String oldenddate) {
		this.oldenddate = oldenddate;
	}

	public String getNewEnddate() {
		return newEnddate;
	}

	public void setNewEnddate(String newEnddate) {
		this.newEnddate = newEnddate;
	}

	public Integer getOlddealTypeId() {
		return olddealTypeId;
	}

	public void setOlddealTypeId(Integer olddealTypeId) {
		this.olddealTypeId = olddealTypeId;
	}

	public Integer getNewDealTypeId() {
		return newDealTypeId;
	}

	public void setNewDealTypeId(Integer newDealTypeId) {
		this.newDealTypeId = newDealTypeId;
	}

	public Double getOldTCV() {
		return oldTCV;
	}

	public void setOldTCV(Double oldTCV) {
		this.oldTCV = oldTCV;
	}

	public Double getNewTCV() {
		return newTCV;
	}

	public void setNewTCV(Double newTCV) {
		this.newTCV = newTCV;
	}

//	public Integer getOldProjectIndustry() {
//		return oldProjectIndustry;
//	}
//
//	public void setOldProjectIndustry(Integer oldProjectIndustry) {
//		this.oldProjectIndustry = oldProjectIndustry;
//	}
//
//	public Integer getNewProjectIndustry() {
//		return newProjectIndustry;
//	}
//
//	public void setNewProjectIndustry(Integer newProjectIndustry) {
//		this.newProjectIndustry = newProjectIndustry;
//	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

//	public String getUpdatedby() {
//		return updatedby;
//	}

//	public void setUpdatedby(String updatedby) {
//		this.updatedby = updatedby;
//	}

	public String getUpdatedOn() {
		return updatedOn;
	}

	public void setUpdatedOn(String updatedOn) {
		this.updatedOn = updatedOn;
	}

	public String getEmailstatus() {
		return emailstatus;
	}

	public void setEmailstatus(String emailstatus) {
		this.emailstatus = emailstatus;
	}

	
}



