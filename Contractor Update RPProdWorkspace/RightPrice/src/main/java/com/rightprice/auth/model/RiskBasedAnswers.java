package com.rightprice.auth.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@Entity
@ApiModel(value="Stages",description="Stages Model Attributes")
@Table(name = "synprod.RP_Deal_Risks_Answers")
/*@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@JsonAutoDetect(getterVisibility=JsonAutoDetect.Visibility.NONE)*/
public class RiskBasedAnswers {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "Risk_Questionaire_Id")
	@ApiModelProperty(notes="Risk Questionaire Id")
	private int riskQuestionaireId;
	
	@Column(name = "Question_Id", updatable=false)
	@ApiModelProperty(notes="Question id")
	private int questionId;
	
	@Column(name = "RP_DEAL_VERSION_ID")
	@ApiModelProperty(notes="RP deal version id")
	private int rpDealVersionId;
	
	@Column(name = "Risk_Answer")
	@ApiModelProperty(notes="Risk answer")
	private String riskAnswer;
	
	@Column(name = "IS_ACTIVE")
	@ApiModelProperty(notes="Active status")
	private int isActive;
	
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

	/*@ManyToOne
	@JoinColumn(name="RP_DEAL_VERSION_ID")
	private Deal deal;*/
	
	public int getRiskQuestionaireId() {
		return riskQuestionaireId;
	}

	public void setRiskQuestionaireId(int riskQuestionaireId) {
		this.riskQuestionaireId = riskQuestionaireId;
	}


	public int getQuestionId() {
		return questionId;
	}

	public void setQuestionId(int questionId) {
		this.questionId = questionId;
	}

	public int getRpDealVersionId() {
		return rpDealVersionId;
	}

	public void setRpDealVersionId(int rpDealVersionId) {
		this.rpDealVersionId = rpDealVersionId;
	}

	public String getRiskAnswer() {
		return riskAnswer;
	}

	public void setRiskAnswer(String riskAnswer) {
		this.riskAnswer = riskAnswer;
	}

	public int getIsActive() {
		return isActive;
	}

	public void setIsActive(int isActive) {
		this.isActive = isActive;
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

	/*public Deal getDeal() {
		return deal;
	}

	public void setDeal(Deal deal) {
		this.deal = deal;
	}*/
	
}
