package com.rightprice.auth.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class GFTREQData {
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "RP_Deal_Version_Id")
	private Integer RP_Deal_Version_Id;
	
	@Column(name = "Vertical_Name")
	private String Vertical_Name;
	
	@Column(name = "Customer_Name")
	private String customer_name;
	
	@Column(name = "CRM_DEAL_ID")
	private String CRM_DEAL_ID;
	
	@Column(name = "revenue")
	private Double revenue;
	
	@Column(name = "currencyname")
	private String currencyname;
	
	@Column(name = "Deal_type")
	private String Deal_type;
	
	@Column(name = "Deal_Start_Date")
	private String Deal_Start_Date;
	
	@Column(name = "Deal_End_Date")
	private String Deal_End_Date;
	
	@Column(name = "Deal_status")
	private String Deal_status;

	@Column(name = "Deal_Stage")
	private String Deal_Stage;

	@Column(name = "GM_Pre_Discount")
	private Double GM_Pre_Discount;
	
	
	@Column(name = "GM_post_discount")
	private Double GM_post_discount;
	
	@Column(name = "Industry_Type")
	private String Industry_Type;

	@Column(name = "ONSITE_efforts")
	private Double ONSITE_efforts;
	
	@Column(name = "Offshore_efforts")
	private Double Offshore_efforts;

	@Column(name = "Total_Efforts")
	private Double Total_Efforts;
	
	@Column(name = "AC_percent")
	private Double AC_percent;
	
	@Column(name = "CH_percent")
	private Double CH_percent;
	
	@Column(name = "Off_percent")
	private Double Off_percent;
	
	@Column(name = "ON_percent")
	private Double ON_percent;

	public Integer getRP_Deal_Version_Id() {
		return RP_Deal_Version_Id;
	}

	public void setRP_Deal_Version_Id(Integer rP_Deal_Version_Id) {
		RP_Deal_Version_Id = rP_Deal_Version_Id;
	}

	public String getVertical_Name() {
		return Vertical_Name;
	}

	public void setVertical_Name(String vertical_Name) {
		Vertical_Name = vertical_Name;
	}

	public String getCustomer_name() {
		return customer_name;
	}

	public void setCustomer_name(String customer_name) {
		this.customer_name = customer_name;
	}

	public String getCRM_DEAL_ID() {
		return CRM_DEAL_ID;
	}

	public void setCRM_DEAL_ID(String cRM_DEAL_ID) {
		CRM_DEAL_ID = cRM_DEAL_ID;
	}

	public Double getRevenue() {
		return revenue;
	}

	public void setRevenue(Double revenue) {
		this.revenue = revenue;
	}

	public String getCurrencyname() {
		return currencyname;
	}

	public void setCurrencyname(String currencyname) {
		this.currencyname = currencyname;
	}

	public String getDeal_type() {
		return Deal_type;
	}

	public void setDeal_type(String deal_type) {
		Deal_type = deal_type;
	}

	public String getDeal_Start_Date() {
		return Deal_Start_Date;
	}

	public void setDeal_Start_Date(String deal_Start_Date) {
		Deal_Start_Date = deal_Start_Date;
	}

	public String getDeal_End_Date() {
		return Deal_End_Date;
	}

	public void setDeal_End_Date(String deal_End_Date) {
		Deal_End_Date = deal_End_Date;
	}

	public String getDeal_status() {
		return Deal_status;
	}

	public void setDeal_status(String deal_status) {
		Deal_status = deal_status;
	}

	public String getDeal_Stage() {
		return Deal_Stage;
	}

	public void setDeal_Stage(String deal_Stage) {
		Deal_Stage = deal_Stage;
	}

	public Double getGM_Pre_Discount() {
		return GM_Pre_Discount;
	}

	public void setGM_Pre_Discount(Double gM_Pre_Discount) {
		GM_Pre_Discount = gM_Pre_Discount;
	}

	public Double getGM_post_discount() {
		return GM_post_discount;
	}

	public void setGM_post_discount(Double gM_post_discount) {
		GM_post_discount = gM_post_discount;
	}

	public String getIndustry_Type() {
		return Industry_Type;
	}

	public void setIndustry_Type(String industry_Type) {
		Industry_Type = industry_Type;
	}

	public Double getONSITE_efforts() {
		return ONSITE_efforts;
	}

	public void setONSITE_efforts(Double oNSITE_efforts) {
		ONSITE_efforts = oNSITE_efforts;
	}

	public Double getOffshore_efforts() {
		return Offshore_efforts;
	}

	public void setOffshore_efforts(Double offshore_efforts) {
		Offshore_efforts = offshore_efforts;
	}

	public Double getTotal_Efforts() {
		return Total_Efforts;
	}

	public void setTotal_Efforts(Double total_Efforts) {
		Total_Efforts = total_Efforts;
	}

	public Double getAC_percent() {
		return AC_percent;
	}

	public void setAC_percent(Double aC_percent) {
		AC_percent = aC_percent;
	}

	public Double getCH_percent() {
		return CH_percent;
	}

	public void setCH_percent(Double cH_percent) {
		CH_percent = cH_percent;
	}

	public Double getOff_percent() {
		return Off_percent;
	}

	public void setOff_percent(Double off_percent) {
		Off_percent = off_percent;
	}

	public Double getON_percent() {
		return ON_percent;
	}

	public void setON_percent(Double oN_percent) {
		ON_percent = oN_percent;
	}
	
	
	
		
		
		
		
		
		
	

	
	
	
}
