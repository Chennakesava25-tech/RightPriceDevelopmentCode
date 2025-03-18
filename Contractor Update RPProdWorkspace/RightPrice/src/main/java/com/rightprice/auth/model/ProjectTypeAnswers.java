package com.rightprice.auth.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@Entity
@ApiModel(value="Stages",description="Dev Main Answers")
@Table(name = "synprod.RP_Deal_FP_Questionaire_Answers")
public class ProjectTypeAnswers {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "Answers_Id")
	@ApiModelProperty(notes="Answers_Id")
	private int answersId;
	
	@Column(name = "RP_Deal_Version_Id")
	@ApiModelProperty(notes="RP deal version id")
	private int rpDealVersionId;

	@Column(name = "Questionaire_Id")
	@ApiModelProperty(notes="Questionaire_Id")
	private int questionId;
	
	@Column(name = "Answer")
	@ApiModelProperty(notes="Answer")
	private String answer;
	
	@Column(name = "Is_Active")
	@ApiModelProperty(notes="Is_Active")
	private int isActive;
	
	@Column(name = "Created_By")
	@ApiModelProperty(notes="Created_By")
	private String createdBy;
	
	
	@Column(name = "Created_On")
	@ApiModelProperty(notes="Created_On")
	private String createdOn;

	
	@Column(name = "Updated_By")
	@ApiModelProperty(notes="Updated by")
	private String updatedBy;
	
	@Column(name = "Updated_On")
	@ApiModelProperty(notes="Updated date")
	private String updatedDate;

	public int getAnswersId() {
		return answersId;
	}


	public void setAnswersId(int answersId) {
		this.answersId = answersId;
	}


	public int getRpDealVersionId() {
		return rpDealVersionId;
	}


	public void setRpDealVersionId(int rpDealVersionId) {
		this.rpDealVersionId = rpDealVersionId;
	}


	public int getQuestionId() {
		return questionId;
	}


	public void setQuestionId(int questionId) {
		this.questionId = questionId;
	}


	public String getAnswer() {
		return answer;
	}


	public void setAnswer(String answer) {
		this.answer = answer;
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


	public String getUpdatedDate() {
		return updatedDate;
	}


	public void setUpdatedDate(String updatedDate) {
		this.updatedDate = updatedDate;
	}
	
	
}
