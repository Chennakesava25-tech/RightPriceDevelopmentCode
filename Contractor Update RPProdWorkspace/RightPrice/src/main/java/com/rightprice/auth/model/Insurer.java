package com.rightprice.auth.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Insurer {
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "INSURER_ID")
	private int insurerId;

	@Column(name = "INSURER_NAME")
	private String insurerName;
	
	@Column(name = "INSURER_EMAIL_ID1")
	private String insurerEmailId1;

	@Column(name = "INSURER_EMAIL_ID2")
	private String insurerEmailId2;

	@Column(name = "INSURER_EMAIL_ID3")
	private String insurerEmailId3;
	
	@Column(name = "INSURER_CONTACT_NO")
	private String insurerContactNo;

	public int getInsurerId() {
		return insurerId;
	}

	public void setInsurerId(int insurerId) {
		this.insurerId = insurerId;
	}

	public String getInsurerName() {
		return insurerName;
	}

	public void setInsurerName(String insurerName) {
		this.insurerName = insurerName;
	}

	public String getInsurerEmailId1() {
		return insurerEmailId1;
	}

	public void setInsurerEmailId1(String insurerEmailId1) {
		this.insurerEmailId1 = insurerEmailId1;
	}

	public String getInsurerEmailId2() {
		return insurerEmailId2;
	}

	public void setInsurerEmailId2(String insurerEmailId2) {
		this.insurerEmailId2 = insurerEmailId2;
	}

	public String getInsurerEmailId3() {
		return insurerEmailId3;
	}

	public void setInsurerEmailId3(String insurerEmailId3) {
		this.insurerEmailId3 = insurerEmailId3;
	}

	public String getInsurerContactNo() {
		return insurerContactNo;
	}

	public void setInsurerContactNo(String insurerContactNo) {
		this.insurerContactNo = insurerContactNo;
	}

	
}
