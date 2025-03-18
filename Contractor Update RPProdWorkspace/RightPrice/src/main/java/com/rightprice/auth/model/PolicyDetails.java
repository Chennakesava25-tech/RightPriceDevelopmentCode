package com.rightprice.auth.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.rightprice.auth.util.DateUtil;

@Entity
@Table(name = "TRN_INS_POLICY_DETAILS")
@JsonIgnoreProperties(ignoreUnknown = true)
public class PolicyDetails {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "POLICY_DETAILS_ID")
	private int policy_Details_Id;
	
	@Column(name = "COUNTRY_ID")
	private int countryId;

	@Column(name = "POLICY_CATEGORY_ID")
	private int policyCategoryId;

	@Column(name = "POLICY_START_DATE")
	private String policyStartDate;

	@Column(name = "POLICY_END_DATE")
	private String policyEndDate;

	@Column(name = "POLICY_NAME_ID")
	private int policyNameId=1;

	@Column(name = "POLICY_NUMBER")
	private String policyNumber;

	@Column(name = "OLD_POLICY_NUMBER")
	private String oldPolicyNumber;

	@Column(name = "POLICY_RISK_COVERAGE_DESC")
	private String policyRiskCoverageDesc;

	@Column(name = "POLICY_LIMIT_OR_SA")
	private String policyLimitOrSa;

	@Column(name = "POLICY_LIMIT_OR_SA_CURRENCY_ID")
	private int policyLimitOrSaCurrencyId;

	@Column(name = "COMPANY_PAID_FLAG")
	private int companyPaidFlag;

	@Column(name = "PAYMENT_CONDITION")
	private int paymentCondition;

	@Column(name = "SYNTEL_ENTITY_ID")
	private int syntelEntityId;

	@Column(name = "INSURER_ID")
	private int insurerId;

	@Column(name = "BROKER_ID")
	private int brokerId;

	@Column(name = "PREMIUM_AMT")
	private double premiumAmt;

	@Column(name = "PREMIUM_AMT_CURRENCY_ID")
	private int premiumAmtCurrencyId;

	@Column(name = "PR_PO_CURRENCY_ID")
	private int prPoCurrencyId;

	@Column(name = "COMMENTS")
	private String comments;

	@Column(name = "APPROVER_COMMENTS")
	private String approverComments;

	@Column(name = "POLICY_ATTACHMENT_ID")
	private int policyAttachmentId;

	@Column(name = "APPROVAL_ATTACHMENT_ID")
	private int approvalAttachmentId;

	@Column(name = "BINDING_INSTRUCTION_ID")
	private int bindingInstructionId;

	@Column(name = "PQ_RATING_ID")
	private int pqRatingId;

	@Column(name = "INVOICE_ID")
	private int invoiceId;

	@Column(name = "ADDITIONAL_ATTACHMENT1_ID")
	private int additionalAttachment1Id;

	@Column(name = "ADDITIONAL_ATTACHMENT2_ID")
	private int additionalAttachment2Id;

	@Column(name = "POLICY_STATUS")
	private int policyStatus;

	@Column(name = "FORM_APPROVAL_STATUS")
	private int formApprovalStatus;

	@Column(name = "ACTIVE_STATUS")
	private int activeStatus;

	@Column(name = "CREATED_BY", updatable=false)
	private String createdBy;

	@Column(name = "CREATED_ON", updatable=false)
	private String createdOn;

	@Column(name = "LAST_UPDATED_BY")
	private String lastUpdatedBy;

	@Column(name = "LAST_UPDATED_ON")
	private String lastUpdatedOn;
	
	/*//@OneToMany(mappedBy = "policyDetails",fetch=FetchType.EAGER, cascade=CascadeType.ALL)
	@OneToMany(fetch=FetchType.EAGER, cascade=CascadeType.ALL)
	@JoinColumn(name="POLICY_DETAILS_ID") 
	@OneToMany
	private List<PrPoDetails> prPoDetails;
	

	public List<PrPoDetails> getPrPoDetails() {
		return prPoDetails;
	}

	public void setPrPoDetails(List<PrPoDetails> prPoDetails) {
		this.prPoDetails = prPoDetails;
	}
	*/
	public int getCountryId() {
		return countryId;
	}

	public int getPolicy_Details_Id() {
		return policy_Details_Id;
	}

	public void setPolicy_Details_Id(int policy_Details_Id) {
		this.policy_Details_Id = policy_Details_Id;
	}

	public void setCountryId(int countryId) {
		this.countryId = countryId;
	}

	public int getPolicyCategoryId() {
		return policyCategoryId;
	}

	public void setPolicyCategoryId(int policyCategoryId) {
		this.policyCategoryId = policyCategoryId;
	}

	public String getPolicyStartDate() {
		return policyStartDate;
	}

	public void setPolicyStartDate(String policyStartDate) {
		this.policyStartDate = DateUtil.getMySqlFormattedDate(policyStartDate);
	}

	public String getPolicyEndDate() {
		return policyEndDate;
	}

	public void setPolicyEndDate(String policyEndDate) {
		this.policyEndDate = DateUtil.getMySqlFormattedDate(policyEndDate);
	}

	public int getPolicyNameId() {
		return policyNameId;
	}

	public void setPolicyNameId(int policyNameId) {
		this.policyNameId = policyNameId;
	}

	public String getPolicyNumber() {
		return policyNumber;
	}

	public void setPolicyNumber(String policyNumber) {
		this.policyNumber = policyNumber;
	}

	public String getOldPolicyNumber() {
		return oldPolicyNumber;
	}

	public void setOldPolicyNumber(String oldPolicyNumber) {
		this.oldPolicyNumber = oldPolicyNumber;
	}

	public String getPolicyRiskCoverageDesc() {
		return policyRiskCoverageDesc;
	}

	public void setPolicyRiskCoverageDesc(String policyRiskCoverageDesc) {
		this.policyRiskCoverageDesc = policyRiskCoverageDesc;
	}

	public String getPolicyLimitOrSa() {
		return policyLimitOrSa;
	}

	public void setPolicyLimitOrSa(String policyLimitOrSa) {
		this.policyLimitOrSa = policyLimitOrSa;
	}

	
	public int getPolicyLimitOrSaCurrencyId() {
		return policyLimitOrSaCurrencyId;
	}

	public void setPolicyLimitOrSaCurrencyId(int policyLimitOrSaCurrencyId) {
		this.policyLimitOrSaCurrencyId = policyLimitOrSaCurrencyId;
	}

	public int getCompanyPaidFlag() {
		return companyPaidFlag;
	}

	public void setCompanyPaidFlag(int companyPaidFlag) {
		this.companyPaidFlag = companyPaidFlag;
	}

	public int getPaymentCondition() {
		return paymentCondition;
	}

	public void setPaymentCondition(int paymentCondition) {
		this.paymentCondition = paymentCondition;
	}

	public int getSyntelEntityId() {
		return syntelEntityId;
	}

	public void setSyntelEntityId(int syntelEntityId) {
		this.syntelEntityId = syntelEntityId;
	}

	public int getInsurerId() {
		return insurerId;
	}

	public void setInsurerId(int insurerId) {
		this.insurerId = insurerId;
	}

	public int getBrokerId() {
		return brokerId;
	}

	public void setBrokerId(int brokerId) {
		this.brokerId = brokerId;
	}

	public double getPremiumAmt() {
		return premiumAmt;
	}

	public void setPremiumAmt(double premiumAmt) {
		this.premiumAmt = premiumAmt;
	}

	public int getPremiumAmtCurrencyId() {
		return premiumAmtCurrencyId;
	}

	public void setPremiumAmtCurrencyId(int premiumAmtCurrencyId) {
		this.premiumAmtCurrencyId = premiumAmtCurrencyId;
	}

	public int getPrPoCurrencyId() {
		return prPoCurrencyId;
	}

	public void setPrPoCurrencyId(int prPoCurrencyId) {
		this.prPoCurrencyId = prPoCurrencyId;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public String getApproverComments() {
		return approverComments;
	}

	public void setApproverComments(String approverComments) {
		this.approverComments = approverComments;
	}

	public int getPolicyAttachmentId() {
		return policyAttachmentId;
	}

	public void setPolicyAttachmentId(int policyAttachmentId) {
		this.policyAttachmentId = policyAttachmentId;
	}

	public int getApprovalAttachmentId() {
		return approvalAttachmentId;
	}

	public void setApprovalAttachmentId(int approvalAttachmentId) {
		this.approvalAttachmentId = approvalAttachmentId;
	}

	public int getBindingInstructionId() {
		return bindingInstructionId;
	}

	public void setBindingInstructionId(int bindingInstructionId) {
		this.bindingInstructionId = bindingInstructionId;
	}

	public int getPqRatingId() {
		return pqRatingId;
	}

	public void setPqRatingId(int pqRatingId) {
		this.pqRatingId = pqRatingId;
	}

	public int getInvoiceId() {
		return invoiceId;
	}

	public void setInvoiceId(int invoiceId) {
		this.invoiceId = invoiceId;
	}

	public int getAdditionalAttachment1Id() {
		return additionalAttachment1Id;
	}

	public void setAdditionalAttachment1Id(int additionalAttachment1Id) {
		this.additionalAttachment1Id = additionalAttachment1Id;
	}

	public int getAdditionalAttachment2Id() {
		return additionalAttachment2Id;
	}

	public void setAdditionalAttachment2Id(int additionalAttachment2Id) {
		this.additionalAttachment2Id = additionalAttachment2Id;
	}

	public int getPolicyStatus() {
		return policyStatus;
	}

	public void setPolicyStatus(int policyStatus) {
		this.policyStatus = policyStatus;
	}

	public int getFormApprovalStatus() {
		return formApprovalStatus;
	}

	public void setFormApprovalStatus(int formApprovalStatus) {
		this.formApprovalStatus = formApprovalStatus;
	}

	public int getActiveStatus() {
		return activeStatus;
	}

	public void setActiveStatus(int activeStatus) {
		this.activeStatus = activeStatus;
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

	public String getLastUpdatedBy() {
		return lastUpdatedBy;
	}

	public void setLastUpdatedBy(String lastUpdatedBy) {
		this.lastUpdatedBy = lastUpdatedBy;
	}

	public String getLastUpdatedOn() {
		return lastUpdatedOn;
	}

	public void setLastUpdatedOn(String lastUpdatedOn) {
		this.lastUpdatedOn = lastUpdatedOn;
	}

}
