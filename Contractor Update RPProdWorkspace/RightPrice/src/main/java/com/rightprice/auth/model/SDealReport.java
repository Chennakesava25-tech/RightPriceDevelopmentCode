package com.rightprice.auth.model;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import com.rightprice.auth.util.AppLoger;
import com.rightprice.auth.util.DateUtil;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@Entity
@ApiModel(value="Report",description="Summery Deal Report Model Attributes")
@Table(name = "[synprod].[RP_DealSummary]")
public class SDealReport implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "crm_deal_id")
	private String crm_deal_id;

	@Column(name = "IS_ACTIVE")
	@ApiModelProperty(notes="Is_Active")
	private Integer isActive;
	
	@Column(name = "Comments")
	@ApiModelProperty(notes="Comments")
	private String comments;
	
	@Column(name = "Date")
	@ApiModelProperty(notes="Date")
	private String date;
	
	@Column(name = "New_Renewal")
	@ApiModelProperty(notes="New_Renewal")
	private String newRenewal;
	
	
	@Column(name = "Vertical_Name")
	@ApiModelProperty(notes="Vertical_Name")
	private String verticalName;
	
	@Column(name = "Vertical_Id")
	@ApiModelProperty(notes="Vertical_Id")
	private Integer verticalId;
	
	
	@Column(name = "Customer_Name")
	@ApiModelProperty(notes="Customer_Name")
	private String customerName;
	
	@Column(name = "Deal_Description")
	@ApiModelProperty(notes="Deal_Description")
	private String dealDescription;
	
	
	@Column(name = "deal_Version")
	@ApiModelProperty(notes="deal_Version")
	private String dealVersion;
	
	@Column(name = "Project_Type")
	@ApiModelProperty(notes="Project_Type")
	private String projectType;
	
	@Column(name = "CapacityBased")
	@ApiModelProperty(notes="CapacityBased")
	private String CapacityBased;	
	
	@Column(name = "Currency_Code")
	@ApiModelProperty(notes="Currency_Code")
	private String currencyCode;	
	
	@Column(name = "deal_tcv")
	@ApiModelProperty(notes="deal_tcv")
	private Double dealTcv;
	
	@Column(name = "direct_cost")
	@ApiModelProperty(notes="direct_cost")
	private Double directCost;
		
	@Column(name = "GM")
	@ApiModelProperty(notes="GM")
	private Double GM;
	
	@Column(name = "SGA_cost")
	@ApiModelProperty(notes="SGA_cost")
	private Double 	SGAcost;
	
	@Column(name = "OM")
	@ApiModelProperty(notes="OM")
	private Double OM;

	@Column(name = "Volume_discount_percent")
	@ApiModelProperty(notes="Volume_discount_percent")
	private Double volumediscountpercent;
	
	@Column(name = "OM_percent_after_Volume_disocunt")
	@ApiModelProperty(notes="OM_percent_after_Volume_disocunt")
	private Double OMpercentafterVolumedisocunt;

	@Column(name = "YTD_Sold_GM_percent")
	@ApiModelProperty(notes="YTD_Sold_GM_percent")
	private Double YTD_Sold_GM_percent;

	
	@Column(name = "Deal_Start_Date")
	@ApiModelProperty(notes="Deal_Start_Date")
	private String dealStartDate;
	
	@Column(name = "Deal_End_Date")
	@ApiModelProperty(notes="Deal_End_Date")
	private String dealEndDate;
	
	@Column(name = "lob_code")
	@ApiModelProperty(notes="lob_code")
	private String lob_code;
	
	@Column(name = "Local_HC")
	@ApiModelProperty(notes="Local_HC")
	private Double Local_HC;
	
	@Column(name = "Deputed_HC")
	@ApiModelProperty(notes="Deputed_HC")
	private Double Deputed_HC;
	
	@Column(name = "Offshore_HC")
	@ApiModelProperty(notes="Offshore_HC")
	private Double Offshore_HC;
	
	@Column(name = "SubcontractorOnsiteHC")
	@ApiModelProperty(notes="SubcontractorOnsiteHC")
	private Double SubcontractorOnsiteHC;
	
	@Column(name = "SubcontractorOffshore")
	@ApiModelProperty(notes="SubcontractorOffshore")
	private Double SubcontractorOffshore;
	
	@Column(name = "Total_HC")
	@ApiModelProperty(notes="Total_HC")
	private Double Total_HC;
	
	@Column(name = "Onsite_percent")
	@ApiModelProperty(notes="Onsite_percent")
	private Double Onsite_percent;
	
	@Column(name = "Off_B2_percent")
	@ApiModelProperty(notes="Off_B2_percent")
	private Double Off_B2_percent;
	
	@Column(name = "Offshore_CH")
	@ApiModelProperty(notes="Offshore_CH")
	private Double Offshore_CH;
	
	@Column(name = "Old_Deal_ID")
	@ApiModelProperty(notes="Old_Deal_ID")
	private String Old_Deal_ID;
	
	@Column(name = "Old_Project_ID")
	@ApiModelProperty(notes="Old_Project_ID")
	private Integer Old_Project_ID;
	
	@Column(name = "Old_sold_margin")
	@ApiModelProperty(notes="Old_sold_margin")
	private Double Old_sold_margin;
	
	
	@Column(name = "Operating_metrics")
	@ApiModelProperty(notes="Operating_metrics")
	private String Operating_metrics;
	
	@Column(name = "rate_card")
	@ApiModelProperty(notes="rate_card")
	private Integer rate_card;
	
	@Column(name = "On_Hrs_per_month")
	@ApiModelProperty(notes="On_Hrs_per_month")
	private Double On_Hrs_per_month;
	
	@Column(name = "Off_Hrs_per_month")
	@ApiModelProperty(notes="Off_Hrs_per_month")
	private Double Off_Hrs_per_month;
	
	@Column(name = "Onsite_Hrs")
	@ApiModelProperty(notes="Onsite_Hrs")
	private Double Onsite_Hrs;
	
	@Column(name = "Off_Hrs")
	@ApiModelProperty(notes="Off_Hrs")
	private Double Off_Hrs;
	
	@Column(name = "Total_Hrs")
	@ApiModelProperty(notes="Total_Hrs")
	private Double Total_Hrs;
	
	@Column(name = "Requestor_Name")
	@ApiModelProperty(notes="Requestor_Name")
	private String Requestor_Name;
	
	
	@Column(name = "On_Hrs_per_month2")
	@ApiModelProperty(notes="On_Hrs_per_month2")
	private Double On_Hrs_per_month2;
	
	@Column(name = "Off_Hrs_per_month2")
	@ApiModelProperty(notes="Off_Hrs_per_month2")
	private Double Off_Hrs_per_month2;
	
	@Column(name = "Onsite_Hrs2")
	@ApiModelProperty(notes="Onsite_Hrs2")
	private Double Onsite_Hrs2;
	
	@Column(name = "Off_Hrs2")
	@ApiModelProperty(notes="Off_Hrs2")
	private Double Off_Hrs2;
	
	@Column(name = "Total_Hrs2")
	@ApiModelProperty(notes="Total_Hrs2")
	private Double Total_Hrs2;
	
	@Column(name = "Level_1_Approver_Role_Id")
	@ApiModelProperty(notes="Level_1_Approver_Role_Id")
	private Integer Level_1_Approver_Role_Id;
	
	@Column(name = "Level_2_Approver_Role_Id")
	@ApiModelProperty(notes="Level_2_Approver_Role_Id")
	private Integer Level_2_Approver_Role_Id;
	
	@Column(name = "Level_3_Approver_Role_Id")
	@ApiModelProperty(notes="Level_3_Approver_Role_Id")
	private Integer Level_3_Approver_Role_Id;
	
	@Column(name = "Level_4_Approver_Role_Id")
	@ApiModelProperty(notes="Level_4_Approver_Role_Id")
	private Integer Level_4_Approver_Role_Id;
	
	@Column(name = "Level_5_Approver_Role_Id")
	@ApiModelProperty(notes="Level_5_Approver_Role_Id")
	private Integer Level_5_Approver_Role_Id;
	
	@Column(name = "Level_6_Approver_Role_Id")
	@ApiModelProperty(notes="Level_6_Approver_Role_Id")
	private Integer Level_6_Approver_Role_Id;
	
	@Column(name = "Level_7_Approver_Role_Id")
	@ApiModelProperty(notes="Level_7_Approver_Role_Id")
	private Integer Level_7_Approver_Role_Id;
	
	@Column(name = "Level_8_Approver_Role_Id")
	@ApiModelProperty(notes="Level_8_Approver_Role_Id")
	private Integer Level_8_Approver_Role_Id;
	
	@Column(name = "Level_9_Approver_Role_Id")
	@ApiModelProperty(notes="Level_9_Approver_Role_Id")
	private Integer Level_9_Approver_Role_Id;
	
	@Column(name = "Current_Approval_Level")
	@ApiModelProperty(notes="Current_Approval_Level")
	private Integer Current_Approval_Level;
		
	@Column(name = "Current_Approval_Status")
	@ApiModelProperty(notes="Current_Approval_Status")
	private Integer Current_Approval_Status;
	
	@Column(name = "Status_indicator")
	@ApiModelProperty(notes="Status_indicator")
	private String Status_indicator;

	@Column(name = "Updated_By")
	@ApiModelProperty(notes="Updated_By")
	private String Updated_By;

	
	public String getUpdated_By() {
		return Updated_By;
	}

	public void setUpdated_By(String updated_By) {
		Updated_By = updated_By;
	}

	public String getStatus_indicator() {
		return Status_indicator;
	}

	public void setStatus_indicator(String status_indicator) {
		Status_indicator = status_indicator;
	}

	public Integer getLevel_1_Approver_Role_Id() {
		return Level_1_Approver_Role_Id;
	}

	public void setLevel_1_Approver_Role_Id(Integer level_1_Approver_Role_Id) {
		Level_1_Approver_Role_Id = level_1_Approver_Role_Id;
	}

	public Integer getLevel_2_Approver_Role_Id() {
		return Level_2_Approver_Role_Id;
	}

	public void setLevel_2_Approver_Role_Id(Integer level_2_Approver_Role_Id) {
		Level_2_Approver_Role_Id = level_2_Approver_Role_Id;
	}

	public Integer getLevel_3_Approver_Role_Id() {
		return Level_3_Approver_Role_Id;
	}

	public void setLevel_3_Approver_Role_Id(Integer level_3_Approver_Role_Id) {
		Level_3_Approver_Role_Id = level_3_Approver_Role_Id;
	}

	public Integer getLevel_4_Approver_Role_Id() {
		return Level_4_Approver_Role_Id;
	}

	public void setLevel_4_Approver_Role_Id(Integer level_4_Approver_Role_Id) {
		Level_4_Approver_Role_Id = level_4_Approver_Role_Id;
	}

	public Integer getLevel_5_Approver_Role_Id() {
		return Level_5_Approver_Role_Id;
	}

	public void setLevel_5_Approver_Role_Id(Integer level_5_Approver_Role_Id) {
		Level_5_Approver_Role_Id = level_5_Approver_Role_Id;
	}

	public Integer getLevel_6_Approver_Role_Id() {
		return Level_6_Approver_Role_Id;
	}

	public void setLevel_6_Approver_Role_Id(Integer level_6_Approver_Role_Id) {
		Level_6_Approver_Role_Id = level_6_Approver_Role_Id;
	}

	public Integer getLevel_7_Approver_Role_Id() {
		return Level_7_Approver_Role_Id;
	}

	public void setLevel_7_Approver_Role_Id(Integer level_7_Approver_Role_Id) {
		Level_7_Approver_Role_Id = level_7_Approver_Role_Id;
	}

	public Integer getLevel_8_Approver_Role_Id() {
		return Level_8_Approver_Role_Id;
	}

	public void setLevel_8_Approver_Role_Id(Integer level_8_Approver_Role_Id) {
		Level_8_Approver_Role_Id = level_8_Approver_Role_Id;
	}

	public Integer getLevel_9_Approver_Role_Id() {
		return Level_9_Approver_Role_Id;
	}

	public void setLevel_9_Approver_Role_Id(Integer level_9_Approver_Role_Id) {
		Level_9_Approver_Role_Id = level_9_Approver_Role_Id;
	}

	public Integer getCurrent_Approval_Level() {
		return Current_Approval_Level;
	}

	public void setCurrent_Approval_Level(Integer current_Approval_Level) {
		Current_Approval_Level = current_Approval_Level;
	}

	public Integer getCurrent_Approval_Status() {
		return Current_Approval_Status;
	}

	public void setCurrent_Approval_Status(Integer current_Approval_Status) {
		Current_Approval_Status = current_Approval_Status;
	}


	public String getCrm_deal_id() {
		return crm_deal_id;
	}

	public void setCrm_deal_id(String crm_deal_id) {
		this.crm_deal_id = crm_deal_id;
	}

	public Integer getIsActive() {
		return isActive;
	}

	public void setIsActive(Integer isActive) {
		this.isActive = isActive;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getNewRenewal() {
		return newRenewal;
	}

	public void setNewRenewal(String newRenewal) {
		this.newRenewal = newRenewal;
	}

	public String getVerticalName() {
		return verticalName;
	}

	public void setVerticalName(String verticalName) {
		this.verticalName = verticalName;
	}

	public Integer getVerticalId() {
		return verticalId;
	}

	public void setVerticalId(Integer verticalId) {
		this.verticalId = verticalId;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getDealDescription() {
		return dealDescription;
	}

	public void setDealDescription(String dealDescription) {
		this.dealDescription = dealDescription;
	}

	public String getDealVersion() {
		return dealVersion;
	}

	public void setDealVersion(String dealVersion) {
		this.dealVersion = dealVersion;
	}

	public String getProjectType() {
		return projectType;
	}

	public void setProjectType(String projectType) {
		this.projectType = projectType;
	}

	public String getCapacityBased() {
		return CapacityBased;
	}

	public void setCapacityBased(String capacityBased) {
		CapacityBased = capacityBased;
	}

	public String getCurrencyCode() {
		return currencyCode;
	}

	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}

	public Double getDealTcv() {
		return dealTcv;
	}

	public void setDealTcv(Double dealTcv) {
		this.dealTcv = dealTcv;
	}

	public Double getDirectCost() {
		return directCost;
	}

	public void setDirectCost(Double directCost) {
		this.directCost = directCost;
	}

	public Double getGM() {
		return GM;
	}

	public void setGM(Double gM) {
		GM = gM;
	}

	public Double getSGAcost() {
		return SGAcost;
	}

	public void setSGAcost(Double sGAcost) {
		SGAcost = sGAcost;
	}

	public Double getOM() {
		return OM;
	}

	public void setOM(Double oM) {
		OM = oM;
	}

	public Double getVolumediscountpercent() {
		return volumediscountpercent;
	}

	public void setVolumediscountpercent(Double volumediscountpercent) {
		this.volumediscountpercent = volumediscountpercent;
	}

	public Double getOMpercentafterVolumedisocunt() {
		return OMpercentafterVolumedisocunt;
	}

	public void setOMpercentafterVolumedisocunt(Double oMpercentafterVolumedisocunt) {
		OMpercentafterVolumedisocunt = oMpercentafterVolumedisocunt;
	}

	public Double getYTD_Sold_GM_percent() {
		return YTD_Sold_GM_percent;
	}

	public void setYTD_Sold_GM_percent(Double yTD_Sold_GM_percent) {
		YTD_Sold_GM_percent = yTD_Sold_GM_percent;
	}

	public String getDealStartDate() {
		return dealStartDate;
	}

	public void setDealStartDate(String dealStartDate) {
		this.dealStartDate = dealStartDate;
	}

	public String getDealEndDate() {
		return dealEndDate;
	}

	public void setDealEndDate(String dealEndDate) {
		this.dealEndDate = dealEndDate;
	}

	public String getLob_code() {
		return lob_code;
	}

	public void setLob_code(String lob_code) {
		this.lob_code = lob_code;
	}

	public Double getLocal_HC() {
		return Local_HC;
	}

	public void setLocal_HC(Double local_HC) {
		Local_HC = local_HC;
	}

	public Double getDeputed_HC() {
		return Deputed_HC;
	}

	public void setDeputed_HC(Double deputed_HC) {
		Deputed_HC = deputed_HC;
	}

	public Double getOffshore_HC() {
		return Offshore_HC;
	}

	public void setOffshore_HC(Double offshore_HC) {
		Offshore_HC = offshore_HC;
	}

	public Double getSubcontractorOnsiteHC() {
		return SubcontractorOnsiteHC;
	}

	public void setSubcontractorOnsiteHC(Double subcontractorOnsiteHC) {
		SubcontractorOnsiteHC = subcontractorOnsiteHC;
	}

	public Double getSubcontractorOffshore() {
		return SubcontractorOffshore;
	}

	public void setSubcontractorOffshore(Double subcontractorOffshore) {
		SubcontractorOffshore = subcontractorOffshore;
	}

	public Double getTotal_HC() {
		return Total_HC;
	}

	public void setTotal_HC(Double total_HC) {
		Total_HC = total_HC;
	}

	public Double getOnsite_percent() {
		return Onsite_percent;
	}

	public void setOnsite_percent(Double onsite_percent) {
		Onsite_percent = onsite_percent;
	}

	public Double getOff_B2_percent() {
		return Off_B2_percent;
	}

	public void setOff_B2_percent(Double off_B2_percent) {
		Off_B2_percent = off_B2_percent;
	}

	public Double getOffshore_CH() {
		return Offshore_CH;
	}

	public void setOffshore_CH(Double offshore_CH) {
		Offshore_CH = offshore_CH;
	}

	public String getOld_Deal_ID() {
		return Old_Deal_ID;
	}

	public void setOld_Deal_ID(String old_Deal_ID) {
		Old_Deal_ID = old_Deal_ID;
	}

	public Integer getOld_Project_ID() {
		return Old_Project_ID;
	}

	public void setOld_Project_ID(Integer old_Project_ID) {
		Old_Project_ID = old_Project_ID;
	}

	public Double getOld_sold_margin() {
		return Old_sold_margin;
	}

	public void setOld_sold_margin(Double old_sold_margin) {
		Old_sold_margin = old_sold_margin;
	}

	public String getOperating_metrics() {
		return Operating_metrics;
	}

	public void setOperating_metrics(String operating_metrics) {
		Operating_metrics = operating_metrics;
	}

	public Integer getRate_card() {
		return rate_card;
	}

	public void setRate_card(Integer rate_card) {
		this.rate_card = rate_card;
	}

	public Double getOn_Hrs_per_month() {
		return On_Hrs_per_month;
	}

	public void setOn_Hrs_per_month(Double on_Hrs_per_month) {
		On_Hrs_per_month = on_Hrs_per_month;
	}

	public Double getOff_Hrs_per_month() {
		return Off_Hrs_per_month;
	}

	public void setOff_Hrs_per_month(Double off_Hrs_per_month) {
		Off_Hrs_per_month = off_Hrs_per_month;
	}

	public Double getOnsite_Hrs() {
		return Onsite_Hrs;
	}

	public void setOnsite_Hrs(Double onsite_Hrs) {
		Onsite_Hrs = onsite_Hrs;
	}

	public Double getOff_Hrs() {
		return Off_Hrs;
	}

	public void setOff_Hrs(Double off_Hrs) {
		Off_Hrs = off_Hrs;
	}

	public Double getTotal_Hrs() {
		return Total_Hrs;
	}

	public void setTotal_Hrs(Double total_Hrs) {
		Total_Hrs = total_Hrs;
	}

	public String getRequestor_Name() {
		return Requestor_Name;
	}

	public void setRequestor_Name(String requestor_Name) {
		Requestor_Name = requestor_Name;
	}

	public Double getOn_Hrs_per_month2() {
		return On_Hrs_per_month2;
	}

	public void setOn_Hrs_per_month2(Double on_Hrs_per_month2) {
		On_Hrs_per_month2 = on_Hrs_per_month2;
	}

	public Double getOff_Hrs_per_month2() {
		return Off_Hrs_per_month2;
	}

	public void setOff_Hrs_per_month2(Double off_Hrs_per_month2) {
		Off_Hrs_per_month2 = off_Hrs_per_month2;
	}

	public Double getOnsite_Hrs2() {
		return Onsite_Hrs2;
	}

	public void setOnsite_Hrs2(Double onsite_Hrs2) {
		Onsite_Hrs2 = onsite_Hrs2;
	}

	public Double getOff_Hrs2() {
		return Off_Hrs2;
	}

	public void setOff_Hrs2(Double off_Hrs2) {
		Off_Hrs2 = off_Hrs2;
	}

	public Double getTotal_Hrs2() {
		return Total_Hrs2;
	}

	public void setTotal_Hrs2(Double total_Hrs2) {
		Total_Hrs2 = total_Hrs2;
	}
	
	
	
	
	
	



}
