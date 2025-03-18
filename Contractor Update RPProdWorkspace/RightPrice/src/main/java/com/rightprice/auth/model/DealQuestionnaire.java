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
@ApiModel(value="Deals",description="Deal Questionnaire")
@Table(name = "synprod.MST_RP_Deal_Questionaire")
public class DealQuestionnaire implements Serializable{

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "Questionaire_Id")
	@ApiModelProperty(notes="Questionaire id Auto Incremented")
	private int questionaireId;	
	
	@Column(name = "Questionaire_Desc")
	@ApiModelProperty(notes="Questionaire Desc")
	private String questionaireDesc;
	
	@Column(name = "Category_Id")
	@ApiModelProperty(notes="Category Id")
	private Integer categoryId;
	
	@Column(name = "Is_Active")
	@ApiModelProperty(notes="Is Active")
	private Integer isActive;

	public int getQuestionaireId() {
		return questionaireId;
	}

	public void setQuestionaireId(int questionaireId) {
		this.questionaireId = questionaireId;
	}

	public String getQuestionaireDesc() {
		return questionaireDesc;
	}

	public void setQuestionaireDesc(String questionaireDesc) {
		this.questionaireDesc = questionaireDesc;
	}

	public Integer getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

	public Integer getIsActive() {
		return isActive;
	}

	public void setIsActive(Integer isActive) {
		this.isActive = isActive;
	}

}
