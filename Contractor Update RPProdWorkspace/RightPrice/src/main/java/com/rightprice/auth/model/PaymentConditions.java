package com.rightprice.auth.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class PaymentConditions {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "PAYMENT_CONDITION_ID")
	private int paymentConditionId;

	@Column(name = "PAYMENT_CONDITION")
	private String paymentCondition;

	public int getPaymentConditionId() {
		return paymentConditionId;
	}

	public void setPaymentConditionId(int paymentConditionId) {
		this.paymentConditionId = paymentConditionId;
	}

	public String getPaymentCondition() {
		return paymentCondition;
	}

	public void setPaymentCondition(String paymentCondition) {
		this.paymentCondition = paymentCondition;
	}

}
