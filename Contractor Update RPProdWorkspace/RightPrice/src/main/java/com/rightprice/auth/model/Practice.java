package com.rightprice.auth.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Practice {
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "PRACTICE_ID")
	private int practiceId;
	
	@Column(name = "PRACTICE_NAME")
	private String practiceName;

	public int getPracticeId() {
		return practiceId;
	}

	public void setPracticeId(int practiceId) {
		this.practiceId = practiceId;
	}

	public String getPracticeName() {
		return practiceName;
	}

	public void setPracticeName(String practiceName) {
		this.practiceName = practiceName;
	}
	
	
}
