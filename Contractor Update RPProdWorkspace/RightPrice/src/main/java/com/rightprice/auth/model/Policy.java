package com.rightprice.auth.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Policy {
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "POLICY_NAME_ID")
	private int policyNameId;
	
	@Column(name = "POLICY_NAME")
	private String policyName;
	
	@Column(name = "POLICY_CATEGORY_ID")
	private int policyCategoryId;

	public int getPolicyNameId() {
		return policyNameId;
	}

	public void setPolicyNameId(int policyNameId) {
		this.policyNameId = policyNameId;
	}

	public String getPolicyName() {
		return policyName;
	}

	public void setPolicyName(String policyName) {
		this.policyName = policyName;
	}
	
}
