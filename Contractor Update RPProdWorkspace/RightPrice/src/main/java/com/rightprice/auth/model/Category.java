package com.rightprice.auth.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Category {

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "POLICY_CATEGORY_ID")
	private int policyCategoryId;
	
	@Column(name = "POLICY_CATEGORY_NAME")
	private String policyCategoryName;

	public int getPolicyCategoryId() {
		return policyCategoryId;
	}

	public void setPolicyCategoryId(int policyCategoryId) {
		this.policyCategoryId = policyCategoryId;
	}

	public String getPolicyCategoryName() {
		return policyCategoryName;
	}

	public void setPolicyCategoryName(String policyCategoryName) {
		this.policyCategoryName = policyCategoryName;
	}

}
