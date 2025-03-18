package com.rightprice.auth.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class SubPracticeView {
	
	@Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
//	@Column(name = "SUB_PRACTICE_ID")
//	private int subpracticeId;
	
	@Column(name = "SUB_PRACTICE_NAME")
	private String subpractice;
	
	@Column(name = "IS_ACTIVE")
	private int isActive;

//	public int getSubpracticeId() {
//		return subpracticeId;
//	}
//
//	public void setSubpracticeId(int subpracticeId) {
//		this.subpracticeId = subpracticeId;
//	}

	public String getSubpractice() {
		return subpractice;
	}

	public void setSubpractice(String subpractice) {
		this.subpractice = subpractice;
	}

	public int getIsActive() {
		return isActive;
	}

	public void setIsActive(int isActive) {
		this.isActive = isActive;
	}

	

	
	
	
}
