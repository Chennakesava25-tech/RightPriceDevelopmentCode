package com.rightprice.auth.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class RecoveryOptions {
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "RECOVERY_OPTION_ID")
	private int recoveryOptionId;
	
	@Column(name = "RECOVERY_OPTION")
	private String recoveryOption;

	public int getRecoveryOptionId() {
		return recoveryOptionId;
	}

	public void setRecoveryOptionId(int recoveryOptionId) {
		this.recoveryOptionId = recoveryOptionId;
	}

	public String getRecoveryOption() {
		return recoveryOption;
	}

	public void setRecoveryOption(String recoveryOption) {
		this.recoveryOption = recoveryOption;
	}
	
}
