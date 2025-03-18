package com.rightprice.auth.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class SubPractice {
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "SUB_PRACTICE_ID")
	private int subpracticeId;
	
	@Column(name = "SUB_PRACTICE_NAME")
	private String subpracticeName;
	
	@Column(name = "Practice_Id")
	private Integer practiceId;
	
	
	public Integer getPracticeId() {
		return practiceId;
	}

	public void setPracticeId(Integer practiceId) {
		this.practiceId = practiceId;
	}

	@Column(name = "IS_ACTIVE")
	private int StatusModel;

	public int getSubpracticeId() {
		return subpracticeId;
	}

	public void setSubpracticeId(int subpracticeId) {
		this.subpracticeId = subpracticeId;
	}

	public String getSubpracticeName() {
		return subpracticeName;
	}

	public void setSubpracticeName(String subpracticeName) {
		this.subpracticeName = subpracticeName;
	}

	public int getStatusModel() {
		return StatusModel;
	}

	public void setStatusModel(int statusModel) {
		StatusModel = statusModel;
	}

	
	
	
}
