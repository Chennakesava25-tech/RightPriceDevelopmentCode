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
@ApiModel(value="ACCOUNT",description="ACCOUNT Model Attributes")
@Table(name = "SY_CUSTOMER_ACCOUNT_TBL")
public class SyCustomerAccontTbl implements Serializable{
	/*
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
   // @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "Account_Name")
	@ApiModelProperty(notes="Account Name")
	private String accountname;
	
	@Column(name = "Account_Name_2")
	@ApiModelProperty(notes="Account Name two")
	private String accountnametwo;
	
	@Column(name = "Account_Name_3")
	@ApiModelProperty(notes="Account Name three")
	private String accountnamethree;
	
	@Column(name = "Account_Group")
	@ApiModelProperty(notes="Account Group")
	private String acountgroup;
	

	@Column(name = "IRIS_Atos_Account_ID")
	@ApiModelProperty(notes="IRIS Atos Account ID")
	private String irsaccountidsf;
	
	@Column(name = "Street_Number")
	@ApiModelProperty(notes="Street Number")
	private String streetnumber;
	
	@Column(name = "City")
	@ApiModelProperty(notes="City")
	private String city;
	
	@Column(name = "Country")
	@ApiModelProperty(notes="Country")
	private String country;
	
	@Column(name = "Postal_Code")
	@ApiModelProperty(notes="Postal Code")
	private String postalcode;

	
	@Column(name = "Region")
	@ApiModelProperty(notes="Region")
	private String region;
	
	@Column(name = "Telephone")
	@ApiModelProperty(notes="Telephone")
	private String telephone;
	
	@Column(name = "Website")
	@ApiModelProperty(notes="Website")
	private String website;
	
	@Column(name = "Global_Client_leader")
	@ApiModelProperty(notes="Global Client leader")
	private String clientleader;
	
	@Column(name = "Atos_Account_ID")
	@ApiModelProperty(notes="Atos Account ID")
	private String atosaccountid;
	
	@Column(name = "IRIS_code")
	@ApiModelProperty(notes="IRIS_code")
	private String iriscode;
	
	@Column(name = "Dun_Bradstreet_ID")
	@ApiModelProperty(notes="Dun Bradstreet ID")
	private String dunbradsheetid;
		
	
	@Column(name = "Atos_Account_ID_Industry")
	@ApiModelProperty(notes="Atos Account ID Industry")
	private String  atosaccountidindustry;
	
	@Column(name = "Atos_Account_ID_Sub_Industry")
	@ApiModelProperty(notes="Atos Account ID Sub Industry")
	private String atosaccountidsubindustry;
	
	
	@Column(name = "Lead_GBU")
	@ApiModelProperty(notes="Lead GBU")
	private String leadgbu;
	
	@Column(name = "Client_Type")
	@ApiModelProperty(notes="Client Type")
	private String clienttype;
	
	@Column(name = "Block")
	@ApiModelProperty(notes="Block")
	private String block;
	
	@Column(name = "Delete_flag")
	@ApiModelProperty(notes="Delete flag")
	private String deleteflag;
	
	@Column(name = "PO_Box")
	@ApiModelProperty(notes="PO Box")
	private String pobox;	
		 
	@Column(name = "PO_Box_Postal_Code")
	@ApiModelProperty(notes="PO Box Postal Code")
	private String poboxpostalcode;
		
	@Column(name = "Status")
	@ApiModelProperty(notes="Status")
	private String status;
	
	@Column(name = "Vertical_ID")
	@ApiModelProperty(notes="Vertical ID")
	private String verticalid;
	
	@Column(name = "Vertical_Name")
	@ApiModelProperty(notes="Vertical Name")
	private String vertical;
	
	@Column(name = "Account_Source	")
	@ApiModelProperty(notes="Account Source	")
	private String accountsource;
	
	@Column(name = "Syntel_Account_ID")
	@ApiModelProperty(notes="Syntel Account ID")
	private String syntelacccountid;
	
	@Column(name = "Syntel_Account_Name")
	@ApiModelProperty(notes="Syntel Account Name")
	private String syntelaccountname;
	
	@Column(name = "Syntel_SubVerticalID")
	@ApiModelProperty(notes="Syntel SubVerticalID")
	private String syntelsubverticalID;
	
	@Column(name = "Syntel_SubVertical_Name	")
	@ApiModelProperty(notes="Syntel SubVertical Name")
	private String syntelsubverticalname;
	
	@Column(name = "Fin_Customer_ID")
	@ApiModelProperty(notes="Fin Customer ID")
	private String fincustomerid;
	
	@Column(name = "Fin_Customer_Name")
	@ApiModelProperty(notes="Fin Customer Name")
	private String fincustomername;
	
		
	
	@Column(name = "Modified_Date")
	@ApiModelProperty(notes="Modified Date")
	private String modifieddate;
	
	@Column(name = "Syntel_Country")
	@ApiModelProperty(notes="Syntel Country")
	private String syntelcountry;
	
	@Column(name = "SyntelLead_GBU")
	@ApiModelProperty(notes="SyntelLead GBU")
	private String syntelleadgbu;
	
	@Column(name = "Syntel_Client_Type")
	@ApiModelProperty(notes="Syntel Client Type")
	private String syntelclienttype;

	public String getAccountname() {
		return accountname;
	}

	public void setAccountname(String accountname) {
		this.accountname = accountname;
	}

	public String getAccountnametwo() {
		return accountnametwo;
	}

	public void setAccountnametwo(String accountnametwo) {
		this.accountnametwo = accountnametwo;
	}

	public String getAccountnamethree() {
		return accountnamethree;
	}

	public void setAccountnamethree(String accountnamethree) {
		this.accountnamethree = accountnamethree;
	}

	public String getAcountgroup() {
		return acountgroup;
	}

	public void setAcountgroup(String acountgroup) {
		this.acountgroup = acountgroup;
	}

	public String getIrsaccountidsf() {
		return irsaccountidsf;
	}

	public void setIrsaccountidsf(String irsaccountidsf) {
		this.irsaccountidsf = irsaccountidsf;
	}

	public String getStreetnumber() {
		return streetnumber;
	}

	public void setStreetnumber(String streetnumber) {
		this.streetnumber = streetnumber;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getPostalcode() {
		return postalcode;
	}

	public void setPostalcode(String postalcode) {
		this.postalcode = postalcode;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public String getClientleader() {
		return clientleader;
	}

	public void setClientleader(String clientleader) {
		this.clientleader = clientleader;
	}

	public String getAtosaccountid() {
		return atosaccountid;
	}

	public void setAtosaccountid(String atosaccountid) {
		this.atosaccountid = atosaccountid;
	}

	public String getIriscode() {
		return iriscode;
	}

	public void setIriscode(String iriscode) {
		this.iriscode = iriscode;
	}

	public String getDunbradsheetid() {
		return dunbradsheetid;
	}

	public void setDunbradsheetid(String dunbradsheetid) {
		this.dunbradsheetid = dunbradsheetid;
	}

	public String getAtosaccountidindustry() {
		return atosaccountidindustry;
	}

	public void setAtosaccountidindustry(String atosaccountidindustry) {
		this.atosaccountidindustry = atosaccountidindustry;
	}

	public String getAtosaccountidsubindustry() {
		return atosaccountidsubindustry;
	}

	public void setAtosaccountidsubindustry(String atosaccountidsubindustry) {
		this.atosaccountidsubindustry = atosaccountidsubindustry;
	}

	public String getLeadgbu() {
		return leadgbu;
	}

	public void setLeadgbu(String leadgbu) {
		this.leadgbu = leadgbu;
	}

	public String getClienttype() {
		return clienttype;
	}

	public void setClienttype(String clienttype) {
		this.clienttype = clienttype;
	}

	public String getBlock() {
		return block;
	}

	public void setBlock(String block) {
		this.block = block;
	}

	public String getDeleteflag() {
		return deleteflag;
	}

	public void setDeleteflag(String deleteflag) {
		this.deleteflag = deleteflag;
	}

	public String getPobox() {
		return pobox;
	}

	public void setPobox(String pobox) {
		this.pobox = pobox;
	}

	public String getPoboxpostalcode() {
		return poboxpostalcode;
	}

	public void setPoboxpostalcode(String poboxpostalcode) {
		this.poboxpostalcode = poboxpostalcode;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getVerticalid() {
		return verticalid;
	}

	public void setVerticalid(String verticalid) {
		this.verticalid = verticalid;
	}

	public String getVertical() {
		return vertical;
	}

	public void setVertical(String vertical) {
		this.vertical = vertical;
	}

	public String getAccountsource() {
		return accountsource;
	}

	public void setAccountsource(String accountsource) {
		this.accountsource = accountsource;
	}

	public String getSyntelacccountid() {
		return syntelacccountid;
	}

	public void setSyntelacccountid(String syntelacccountid) {
		this.syntelacccountid = syntelacccountid;
	}

	public String getSyntelaccountname() {
		return syntelaccountname;
	}

	public void setSyntelaccountname(String syntelaccountname) {
		this.syntelaccountname = syntelaccountname;
	}

	public String getSyntelsubverticalID() {
		return syntelsubverticalID;
	}

	public void setSyntelsubverticalID(String syntelsubverticalID) {
		this.syntelsubverticalID = syntelsubverticalID;
	}

	public String getSyntelsubverticalname() {
		return syntelsubverticalname;
	}

	public void setSyntelsubverticalname(String syntelsubverticalname) {
		this.syntelsubverticalname = syntelsubverticalname;
	}

	public String getFincustomerid() {
		return fincustomerid;
	}

	public void setFincustomerid(String fincustomerid) {
		this.fincustomerid = fincustomerid;
	}

	public String getFincustomername() {
		return fincustomername;
	}

	public void setFincustomername(String fincustomername) {
		this.fincustomername = fincustomername;
	}

	public String getModifieddate() {
		return modifieddate;
	}

	public void setModifieddate(String modifieddate) {
		this.modifieddate = modifieddate;
	}

	public String getSyntelcountry() {
		return syntelcountry;
	}

	public void setSyntelcountry(String syntelcountry) {
		this.syntelcountry = syntelcountry;
	}

	public String getSyntelleadgbu() {
		return syntelleadgbu;
	}

	public void setSyntelleadgbu(String syntelleadgbu) {
		this.syntelleadgbu = syntelleadgbu;
	}

	public String getSyntelclienttype() {
		return syntelclienttype;
	}

	public void setSyntelclienttype(String syntelclienttype) {
		this.syntelclienttype = syntelclienttype;
	}
	
	
}
