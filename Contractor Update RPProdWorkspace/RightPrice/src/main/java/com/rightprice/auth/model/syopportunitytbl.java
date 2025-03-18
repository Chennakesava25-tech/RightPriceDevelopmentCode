package com.rightprice.auth.model;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


@Entity
@ApiModel(value="opportunity",description="Opportunity Model Attributes")
@Table(name = "sy_opportunity_tbl")
public class syopportunitytbl implements Serializable{
	/*
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
   // @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "Account_Name")
	@ApiModelProperty(notes="Account Name")
	private String accountname;
	
	@Column(name = "Atos_Account_ID")
	@ApiModelProperty(notes="Atos Account ID")
	private String atosaccountid;
	
	@Column(name = "Syntel_Account_ID")
	@ApiModelProperty(notes="Syntel Account ID")
	private String syntelaccountid;
	
	@Column(name = "Opportunity_Name")
	@ApiModelProperty(notes="Opportunity Name")
	private String opportunityname;
	

	@Column(name = "Opportunity_Type")
	@ApiModelProperty(notes="Opportunity Type")
	private String opportunitytype;
	
	@Column(name = "Item_Order_Entry_Currency")
	@ApiModelProperty(notes="Item Order Entry Currency")
	private String currency;
	
	@Column(name = "Item_Order_Entry_Currency_Base")
	@ApiModelProperty(notes="Item Order Entry Currency Base")
	private String currencyBase;
	
	@Column(name = "Item_Order_Entry")
	@ApiModelProperty(notes="Item Order Entry")
	private Double opportunityvalue;
	
	@Column(name = "Item_Order_Entry_Base")
	@ApiModelProperty(notes="Item Order Entry Base")
	private Double opportunityvaluebase;

	
	@Column(name = "Leading_Profit_Center_Country")
	@ApiModelProperty(notes="Leading Profit Center Country")
	private String lpccountry;
	
	@Column(name = "Opportunity_Sales_Lead")
	@ApiModelProperty(notes="Opportunity Sales Lead")
	private String owner;
	
	@Column(name = "Opportunity_Sales_Lead_Alias")
	@ApiModelProperty(notes="Opportunity Sales Lead Alias")
	private String SalesLeadAlias;
	
	@Column(name = "Billing_type")
	@ApiModelProperty(notes="Billing type")
	private String billingtype;
	
	@Column(name = "Probability")
	@ApiModelProperty(notes="Probability")
	private String Probability;
	
	@Column(name = "Item_Project_Margin")
	@ApiModelProperty(notes="Item Project Margin")
	private Double projectMargin;
	
	@Column(name = "TCV_Rainbow	")
	@ApiModelProperty(notes="TCV Rainbow")
	private Double tcvRainbow	;
		
	
	@Column(name = "ESTIMATED_GM")
	@ApiModelProperty(notes="ESTIMATED GM")
	private Double estimatedGM ;
	
	@Column(name = "ESTIMATED_CONTRACT_VALUE")
	@ApiModelProperty(notes="ESTIMATED CONTRACT VALUE")
	private Double estimatedvalue;
	
	
	@Column(name = "Item_Order_Entry_Sum")
	@ApiModelProperty(notes="Item Order Entry Sum")
	private Double entrysum;
	
	@Column(name = "Summary")
	@ApiModelProperty(notes="Summary")
	private String summary;
	
	@Column(name = "Duration_of_Service_Delivery_months")
	@ApiModelProperty(notes="Duration of Service Delivery months")
	private Integer durationofServiceDeliverymonths;
	
	@Column(name = "Phase")
	@ApiModelProperty(notes="Phase")
	private String phase;
	
	@Column(name = "Status")
	@ApiModelProperty(notes="Status")
	private String status;	
		 
	@Column(name = "Atos_Opportunity_ID")
	@ApiModelProperty(notes="Atos Opportunity_ID")
	private String atosOpportunityID;
		
	@Column(name = "RFP_Arrival_Date")
	@ApiModelProperty(notes="RFP Arrival Date")
	private String arrival_Date;
	
	@Column(name = "Date_proposal_to_customer")
	@ApiModelProperty(notes="Date proposal to customer")
	private String proposaltocustomer;
	
	@Column(name = "Delivery_Start_Date")
	@ApiModelProperty(notes="Delivery Start Date")
	private String startDate;
	
	@Column(name = "Delivery_End_Date")
	@ApiModelProperty(notes="Delivery End Date")
	private String endDate;
	
	@Column(name = "Item_Number")
	@ApiModelProperty(notes="Item Number")
	private String itemnumber;
	
	@Column(name = "Syntel_opportunityID")
	@ApiModelProperty(notes="Syntel Opportunity_ID")
	private String syntelOpportunityID;
	
	@Column(name = "Opportunity_ID")
	@ApiModelProperty(notes="Opportunity_ID")
	private String opportunityID;
	
	@Column(name = "Origin")
	@ApiModelProperty(notes="Origin")
	private String origin;
	@Column(name = "Offering_Name")
	@ApiModelProperty(notes="Offering Name")
	private String offeringName;
	
	@Column(name = "Created_Date")
	@ApiModelProperty(notes="Created Date")
	private String createdDate;
	
	@Column(name = "Priority")
	@ApiModelProperty(notes="Priority")
	private String priority;
	@Column(name = "Closing_Date")
	@ApiModelProperty(notes="Closing Date")
	private String closingDate;
	
		
		
	@Column(name = "Relevant_for_Pipe")
	@ApiModelProperty(notes="Relevant for_Pipe")
	private String relevantforpipe;
	
	@Column(name = "Delivery_Division")
	@ApiModelProperty(notes="Delivery Division")
	private String deliveryDivision;
	
	@Column(name = "Reason")
	@ApiModelProperty(notes="Reason")
	private String reason;
	
	@Column(name = "Proactive")
	@ApiModelProperty(notes="Proactive")
	private String proactive;
	
	@Column(name = "Consulting")
	@ApiModelProperty(notes="Consulting")
	private String consulting;
	
	@Column(name = "Service")
	@ApiModelProperty(notes="Service")
	private String service;
	
	@Column(name = "SubService")
	@ApiModelProperty(notes="SubService")
	private String subService;
	
	@Column(name = "Software")
	@ApiModelProperty(notes="Software")
	private String software;
	
	@Column(name = "Vertical")
	@ApiModelProperty(notes="Vertical")
	private String verticalsf;
	
	@Column(name = "LOB_TYPE")
	@ApiModelProperty(notes="LOB TYPE")
	private String lobTYPE;
	
	@Column(name = "synergy")
	@ApiModelProperty(notes="synergy")
	private String synergy;	
	
	@Column(name = "Contract_ORG")
	@ApiModelProperty(notes="Contract ORG")
	private String contractORG;
	
	@Column(name = "Cyber_Security")
	@ApiModelProperty(notes="Cyber Security")
	private String cyberSecurity;
	
	@Column(name = "Industry_Solution")
	@ApiModelProperty(notes="Industry Solution")
	private String industrySolution;
	
	@Column(name = "Syntbots")
	@ApiModelProperty(notes="Syntbots")
	private String syntbotssf;
	
	@Column(name = "Leading_Sub_Division")
	@ApiModelProperty(notes="Leading Sub Division")
	private String leadingSubDivision;
	
	@Column(name = "Modified_ON")
	@ApiModelProperty(notes="Modified ON")
	private String modifiedON;

	
	@Column(name = "GM_APPROVED_DATE_RP")
	@ApiModelProperty(notes="GMAPPROVEDDATERP")
	private String gmapproveddaterp;
	
	@Column(name = "ITEM_DELETEION_FLAG")
	@ApiModelProperty(notes="ITEM DELETEION FLAG")
	private String itemdeltionflag;
	
	@Column(name = "ITEM_DELETEION_DATE")
	@ApiModelProperty(notes="ITEM DELETEION DATE")
	private String itemdeletiondate;
	
	@Column(name = "CREATED_NOTIFICATION")
	@ApiModelProperty(notes="CREATED NOTIFICATION")
	private String creatednotification;
	
	@Column(name = "STATUS_CHANGE_NOTIFICATION")
	@ApiModelProperty(notes="STATUS CHANGE NOTIFICATION")
	private String statusnotifcation;
	
	@Column(name = "Delivery_Country_code")
	@ApiModelProperty(notes="Delivery Country code")
	private String deliverycountrycode;
	
	@Column(name = "Leading_RBU")
	@ApiModelProperty(notes="Leading RBU")
	private String leadingrbu;
	
	@Column(name = "Bid_Manager")
	@ApiModelProperty(notes="Bid Manager")
	private String bidmangaer;
	
	@Column(name = "Delivery_RBU")
	@ApiModelProperty(notes="Delivery RBU")
	private String deliveryrbu;
	
	@Column(name = "Delivery_GBU")
	@ApiModelProperty(notes="Delivery GBU")
	private String deliverygbu;

	public String getAccountname() {
		return accountname;
	}

	public void setAccountname(String accountname) {
		this.accountname = accountname;
	}

	public String getAtosaccountid() {
		return atosaccountid;
	}

	public void setAtosaccountid(String atosaccountid) {
		this.atosaccountid = atosaccountid;
	}

	public String getSyntelaccountid() {
		return syntelaccountid;
	}

	public void setSyntelaccountid(String syntelaccountid) {
		this.syntelaccountid = syntelaccountid;
	}

	public String getOpportunityname() {
		return opportunityname;
	}

	public void setOpportunityname(String opportunityname) {
		this.opportunityname = opportunityname;
	}

	public String getOpportunitytype() {
		return opportunitytype;
	}

	public void setOpportunitytype(String opportunitytype) {
		this.opportunitytype = opportunitytype;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getCurrencyBase() {
		return currencyBase;
	}

	public void setCurrencyBase(String currencyBase) {
		this.currencyBase = currencyBase;
	}

	public Double getOpportunityvalue() {
		return opportunityvalue;
	}

	public void setOpportunityvalue(Double opportunityvalue) {
		this.opportunityvalue = opportunityvalue;
	}

	public Double getOpportunityvaluebase() {
		return opportunityvaluebase;
	}

	public void setOpportunityvaluebase(Double opportunityvaluebase) {
		this.opportunityvaluebase = opportunityvaluebase;
	}

	public String getLpccountry() {
		return lpccountry;
	}

	public void setLpccountry(String lpccountry) {
		this.lpccountry = lpccountry;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public String getSalesLeadAlias() {
		return SalesLeadAlias;
	}

	public void setSalesLeadAlias(String salesLeadAlias) {
		SalesLeadAlias = salesLeadAlias;
	}

	public String getBillingtype() {
		return billingtype;
	}

	public void setBillingtype(String billingtype) {
		this.billingtype = billingtype;
	}

	public String getProbability() {
		return Probability;
	}

	public void setProbability(String probability) {
		Probability = probability;
	}

	public Double getProjectMargin() {
		return projectMargin;
	}

	public void setProjectMargin(Double projectMargin) {
		this.projectMargin = projectMargin;
	}

	public Double getTcvRainbow() {
		return tcvRainbow;
	}

	public void setTcvRainbow(Double tcvRainbow) {
		this.tcvRainbow = tcvRainbow;
	}

	public Double getEstimatedGM() {
		return estimatedGM;
	}

	public void setEstimatedGM(Double estimatedGM) {
		this.estimatedGM = estimatedGM;
	}

	public Double getEstimatedvalue() {
		return estimatedvalue;
	}

	public void setEstimatedvalue(Double estimatedvalue) {
		this.estimatedvalue = estimatedvalue;
	}

	public Double getEntrysum() {
		return entrysum;
	}

	public void setEntrysum(Double entrysum) {
		this.entrysum = entrysum;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public Integer getDurationofServiceDeliverymonths() {
		return durationofServiceDeliverymonths;
	}

	public void setDurationofServiceDeliverymonths(Integer durationofServiceDeliverymonths) {
		this.durationofServiceDeliverymonths = durationofServiceDeliverymonths;
	}

	public String getPhase() {
		return phase;
	}

	public void setPhase(String phase) {
		this.phase = phase;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getAtosOpportunityID() {
		return atosOpportunityID;
	}

	public void setAtosOpportunityID(String atosOpportunityID) {
		this.atosOpportunityID = atosOpportunityID;
	}

	public String getArrival_Date() {
		return arrival_Date;
	}

	public void setArrival_Date(String arrival_Date) {
		this.arrival_Date = arrival_Date;
	}

	public String getProposaltocustomer() {
		return proposaltocustomer;
	}

	public void setProposaltocustomer(String proposaltocustomer) {
		this.proposaltocustomer = proposaltocustomer;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getItemnumber() {
		return itemnumber;
	}

	public void setItemnumber(String itemnumber) {
		this.itemnumber = itemnumber;
	}

	public String getSyntelOpportunityID() {
		return syntelOpportunityID;
	}

	public void setSyntelOpportunityID(String syntelOpportunityID) {
		this.syntelOpportunityID = syntelOpportunityID;
	}

	public String getOpportunityID() {
		return opportunityID;
	}

	public void setOpportunityID(String opportunityID) {
		this.opportunityID = opportunityID;
	}

	public String getOrigin() {
		return origin;
	}

	public void setOrigin(String origin) {
		this.origin = origin;
	}

	public String getOfferingName() {
		return offeringName;
	}

	public void setOfferingName(String offeringName) {
		this.offeringName = offeringName;
	}

	public String getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}

	public String getPriority() {
		return priority;
	}

	public void setPriority(String priority) {
		this.priority = priority;
	}

	public String getClosingDate() {
		return closingDate;
	}

	public void setClosingDate(String closingDate) {
		this.closingDate = closingDate;
	}

	public String getRelevantforpipe() {
		return relevantforpipe;
	}

	public void setRelevantforpipe(String relevantforpipe) {
		this.relevantforpipe = relevantforpipe;
	}

	public String getDeliveryDivision() {
		return deliveryDivision;
	}

	public void setDeliveryDivision(String deliveryDivision) {
		this.deliveryDivision = deliveryDivision;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getProactive() {
		return proactive;
	}

	public void setProactive(String proactive) {
		this.proactive = proactive;
	}

	public String getConsulting() {
		return consulting;
	}

	public void setConsulting(String consulting) {
		this.consulting = consulting;
	}

	public String getService() {
		return service;
	}

	public void setService(String service) {
		this.service = service;
	}

	public String getSubService() {
		return subService;
	}

	public void setSubService(String subService) {
		this.subService = subService;
	}

	public String getSoftware() {
		return software;
	}

	public void setSoftware(String software) {
		this.software = software;
	}

	public String getVerticalsf() {
		return verticalsf;
	}

	public void setVerticalsf(String verticalsf) {
		this.verticalsf = verticalsf;
	}

	public String getLobTYPE() {
		return lobTYPE;
	}

	public void setLobTYPE(String lobTYPE) {
		this.lobTYPE = lobTYPE;
	}

	public String getSynergy() {
		return synergy;
	}

	public void setSynergy(String synergy) {
		this.synergy = synergy;
	}

	public String getContractORG() {
		return contractORG;
	}

	public void setContractORG(String contractORG) {
		this.contractORG = contractORG;
	}

	public String getCyberSecurity() {
		return cyberSecurity;
	}

	public void setCyberSecurity(String cyberSecurity) {
		this.cyberSecurity = cyberSecurity;
	}

	public String getIndustrySolution() {
		return industrySolution;
	}

	public void setIndustrySolution(String industrySolution) {
		this.industrySolution = industrySolution;
	}

	public String getSyntbotssf() {
		return syntbotssf;
	}

	public void setSyntbotssf(String syntbotssf) {
		this.syntbotssf = syntbotssf;
	}

	public String getLeadingSubDivision() {
		return leadingSubDivision;
	}

	public void setLeadingSubDivision(String leadingSubDivision) {
		this.leadingSubDivision = leadingSubDivision;
	}

	public String getModifiedON() {
		return modifiedON;
	}

	public void setModifiedON(String modifiedON) {
		this.modifiedON = modifiedON;
	}

	public String getGmapproveddaterp() {
		return gmapproveddaterp;
	}

	public void setGmapproveddaterp(String gmapproveddaterp) {
		this.gmapproveddaterp = gmapproveddaterp;
	}

	public String getItemdeltionflag() {
		return itemdeltionflag;
	}

	public void setItemdeltionflag(String itemdeltionflag) {
		this.itemdeltionflag = itemdeltionflag;
	}

	public String getItemdeletiondate() {
		return itemdeletiondate;
	}

	public void setItemdeletiondate(String itemdeletiondate) {
		this.itemdeletiondate = itemdeletiondate;
	}

	public String getCreatednotification() {
		return creatednotification;
	}

	public void setCreatednotification(String creatednotification) {
		this.creatednotification = creatednotification;
	}

	public String getStatusnotifcation() {
		return statusnotifcation;
	}

	public void setStatusnotifcation(String statusnotifcation) {
		this.statusnotifcation = statusnotifcation;
	}

	public String getDeliverycountrycode() {
		return deliverycountrycode;
	}

	public void setDeliverycountrycode(String deliverycountrycode) {
		this.deliverycountrycode = deliverycountrycode;
	}

	public String getLeadingrbu() {
		return leadingrbu;
	}

	public void setLeadingrbu(String leadingrbu) {
		this.leadingrbu = leadingrbu;
	}

	public String getBidmangaer() {
		return bidmangaer;
	}

	public void setBidmangaer(String bidmangaer) {
		this.bidmangaer = bidmangaer;
	}

	public String getDeliveryrbu() {
		return deliveryrbu;
	}

	public void setDeliveryrbu(String deliveryrbu) {
		this.deliveryrbu = deliveryrbu;
	}

	public String getDeliverygbu() {
		return deliverygbu;
	}

	public void setDeliverygbu(String deliverygbu) {
		this.deliverygbu = deliverygbu;
	}

	
}
