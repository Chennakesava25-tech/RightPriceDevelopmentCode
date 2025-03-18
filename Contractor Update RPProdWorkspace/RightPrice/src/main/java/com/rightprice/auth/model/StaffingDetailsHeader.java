package com.rightprice.auth.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


@Entity
@ApiModel(value="Deal Staffing Header",description="Deal Staffing Header Details")
/*@Table(name = "SYNPROD.RP_DEAL_STAFFING_HEADER" ,schema = "RightPriceDB")*/
@Table(name = "SYNPROD.RP_DEAL_STAFFING_HEADER")
public class StaffingDetailsHeader implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "STAFFING_HEADER_ID")
	@ApiModelProperty(notes="Deal Staffing Header ID")
	private Integer staffingHeaderId;
	
	@Column(name = "DEAL_TOWER_ID")
	@ApiModelProperty(notes="Deal Tower ID")
	private Integer dealTowerId;
	
	@Column(name = "RP_DEAL_VERSION_ID")
	@ApiModelProperty(notes="Deal Version ID")
	private Integer rpDealVersionId;
	
	@Column(name = "MONTH_YEAR_HEADER_1")
	@ApiModelProperty(notes="Month Year Header")
	private String monthYearHeader1;
	
	@Column(name = "MONTH_YEAR_HEADER_2")
	@ApiModelProperty(notes="Month Year Header")
	private String monthYearHeader2;
	
	@Column(name = "MONTH_YEAR_HEADER_3")
	@ApiModelProperty(notes="Month Year Header")
	private String monthYearHeader3;
	
	@Column(name = "MONTH_YEAR_HEADER_4")
	@ApiModelProperty(notes="Month Year Header")
	private String monthYearHeader4;
	
	@Column(name = "MONTH_YEAR_HEADER_5")
	@ApiModelProperty(notes="Month Year Header")
	private String monthYearHeader5;
	
	@Column(name = "MONTH_YEAR_HEADER_6")
	@ApiModelProperty(notes="Month Year Header")
	private String monthYearHeader6;
	
	@Column(name = "MONTH_YEAR_HEADER_7")
	@ApiModelProperty(notes="Month Year Header")
	private String monthYearHeader7;
	
	@Column(name = "MONTH_YEAR_HEADER_8")
	@ApiModelProperty(notes="Month Year Header")
	private String monthYearHeader8;
	
	@Column(name = "MONTH_YEAR_HEADER_9")
	@ApiModelProperty(notes="Month Year Header")
	private String monthYearHeader9;
	
	@Column(name = "MONTH_YEAR_HEADER_10")
	@ApiModelProperty(notes="Month Year Header")
	private String monthYearHeader10;
	
	public String getMonthYearHeader7() {
		return monthYearHeader7;
	}

	public void setMonthYearHeader7(String monthYearHeader7) {
		this.monthYearHeader7 = monthYearHeader7;
	}

	public String getMonthYearHeader8() {
		return monthYearHeader8;
	}

	public void setMonthYearHeader8(String monthYearHeader8) {
		this.monthYearHeader8 = monthYearHeader8;
	}

	public String getMonthYearHeader9() {
		return monthYearHeader9;
	}

	public void setMonthYearHeader9(String monthYearHeader9) {
		this.monthYearHeader9 = monthYearHeader9;
	}

	public String getMonthYearHeader10() {
		return monthYearHeader10;
	}

	public void setMonthYearHeader10(String monthYearHeader10) {
		this.monthYearHeader10 = monthYearHeader10;
	}

	public String getMonthYearHeader11() {
		return monthYearHeader11;
	}

	public void setMonthYearHeader11(String monthYearHeader11) {
		this.monthYearHeader11 = monthYearHeader11;
	}

	@Column(name = "MONTH_YEAR_HEADER_11")
	@ApiModelProperty(notes="Month Year Header")
	private String monthYearHeader11;	
	
	public String getMonthYearHeader6() {
		return monthYearHeader6;
	}

	public void setMonthYearHeader6(String monthYearHeader6) {
		this.monthYearHeader6 = monthYearHeader6;
	}

	@Column(name = "COUNTRY_ID")
	@ApiModelProperty(notes="Country ID")
	private Integer countryId;
	
	@Column(name = "CITY_ID")
	@ApiModelProperty(notes="City ID")
	private Integer cityId;
	
	/*@Column(name = "STAFFING_VERSION")
	@ApiModelProperty(notes="Staffing Version")
	private Integer staffingVersion;*/
	
	@Column(name = "STAFFING_MONTH_1")
	@ApiModelProperty(notes="Staffing First Month")
	private String staffingFirstMonth;
	
	@Column(name = "STAFFING_MONTH_2")
	@ApiModelProperty(notes="Staffing Second Month")
	private String staffingSecondMonth;
	
	@Column(name = "STAFFING_MONTH_3")
	@ApiModelProperty(notes="Staffing Third Month")
	private String staffingThirdMonth;
	
	@Column(name = "STAFFING_MONTH_4")
	@ApiModelProperty(notes="Staffing Fourth Month")
	private String staffingFourthMonth;
	
	@Column(name = "STAFFING_MONTH_5")
	@ApiModelProperty(notes="Staffing Fifth Month")
	private String staffingFifthMonth;
	
	@Column(name = "STAFFING_MONTH_6")
	@ApiModelProperty(notes="Staffing Sixth Month")
	private String staffingSixthMonth;
	
	@Column(name = "STAFFING_MONTH_7")
	@ApiModelProperty(notes="Staffing Seventh Month")
	private String staffingSeventhMonth;
	
	@Column(name = "STAFFING_MONTH_8")
	@ApiModelProperty(notes="Staffing Eighth Month")
	private String staffingEighthMonth;
	
	@Column(name = "STAFFING_MONTH_9")
	@ApiModelProperty(notes="Staffing Ninth Month")
	private String staffingNinthMonth;
	
	@Column(name = "STAFFING_MONTH_10")
	@ApiModelProperty(notes="Staffing Tenth Month")
	private String staffingTenthMonth;
	
	@Column(name = "STAFFING_MONTH_11")
	@ApiModelProperty(notes="Staffing Eleventh Month")
	private String staffingEleventhMonth;
	
	@Column(name = "STAFFING_MONTH_12")
	@ApiModelProperty(notes="Staffing Twelth Month")
	private String staffingTwelthMonth;
	
	@Column(name = "IS_ACTIVE")
	@ApiModelProperty(notes="Active status")
	private int isActive;
	
	@Column(name = "CREATED_BY", updatable=false)
	@ApiModelProperty(notes="Created by")
	private String createdBy;
	
	@Column(name = "CREATED_ON", updatable=false)
	@ApiModelProperty(notes="Created date")
	private String createdDate;
	
	@Column(name = "UPDATED_BY")
	@ApiModelProperty(notes="Updated by")
	private String updatedBy;
	
	@Column(name = "UPDATED_ON")
	@ApiModelProperty(notes="Updated date")
	private String updatedDate;

	public Integer getStaffingHeaderId() {
		return staffingHeaderId;
	}

	public void setStaffingHeaderId(Integer staffingHeaderId) {
		this.staffingHeaderId = staffingHeaderId;
	}

	public Integer getDealTowerId() {
		return dealTowerId;
	}

	public void setDealTowerId(Integer dealTowerId) {
		this.dealTowerId = dealTowerId;
	}

	public Integer getRpDealVersionId() {
		return rpDealVersionId;
	}

	public void setRpDealVersionId(Integer rpDealVersionId) {
		this.rpDealVersionId = rpDealVersionId;
	}

	public Integer getCountryId() {
		return countryId;
	}

	public void setCountryId(Integer countryId) {
		this.countryId = countryId;
	}


	public String getMonthYearHeader1() {
		return monthYearHeader1;
	}

	public void setMonthYearHeader1(String monthYearHeader1) {
		this.monthYearHeader1 = monthYearHeader1;
	}

	public String getMonthYearHeader2() {
		return monthYearHeader2;
	}

	public void setMonthYearHeader2(String monthYearHeader2) {
		this.monthYearHeader2 = monthYearHeader2;
	}

	public String getMonthYearHeader3() {
		return monthYearHeader3;
	}

	public void setMonthYearHeader3(String monthYearHeader3) {
		this.monthYearHeader3 = monthYearHeader3;
	}

	public String getMonthYearHeader4() {
		return monthYearHeader4;
	}

	public void setMonthYearHeader4(String monthYearHeader4) {
		this.monthYearHeader4 = monthYearHeader4;
	}

	public String getMonthYearHeader5() {
		return monthYearHeader5;
	}

	public void setMonthYearHeader5(String monthYearHeader5) {
		this.monthYearHeader5 = monthYearHeader5;
	}

	public Integer getCityId() {
		return cityId;
	}

	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}

	/*public Integer getStaffingVersion() {
		return staffingVersion;
	}

	public void setStaffingVersion(Integer staffingVersion) {
		this.staffingVersion = staffingVersion;
	}*/

	public String getStaffingFirstMonth() {
		return staffingFirstMonth;
	}

	public void setStaffingFirstMonth(String staffingFirstMonth) {
		this.staffingFirstMonth = staffingFirstMonth;
	}

	public String getStaffingSecondMonth() {
		return staffingSecondMonth;
	}

	public void setStaffingSecondMonth(String staffingSecondMonth) {
		this.staffingSecondMonth = staffingSecondMonth;
	}

	public String getStaffingThirdMonth() {
		return staffingThirdMonth;
	}

	public void setStaffingThirdMonth(String staffingThirdMonth) {
		this.staffingThirdMonth = staffingThirdMonth;
	}

	public String getStaffingFourthMonth() {
		return staffingFourthMonth;
	}

	public void setStaffingFourthMonth(String staffingFourthMonth) {
		this.staffingFourthMonth = staffingFourthMonth;
	}

	public String getStaffingFifthMonth() {
		return staffingFifthMonth;
	}

	public void setStaffingFifthMonth(String staffingFifthMonth) {
		this.staffingFifthMonth = staffingFifthMonth;
	}

	public String getStaffingSixthMonth() {
		return staffingSixthMonth;
	}

	public void setStaffingSixthMonth(String staffingSixthMonth) {
		this.staffingSixthMonth = staffingSixthMonth;
	}

	public String getStaffingSeventhMonth() {
		return staffingSeventhMonth;
	}

	public void setStaffingSeventhMonth(String staffingSeventhMonth) {
		this.staffingSeventhMonth = staffingSeventhMonth;
	}

	public String getStaffingEighthMonth() {
		return staffingEighthMonth;
	}

	public void setStaffingEighthMonth(String staffingEighthMonth) {
		this.staffingEighthMonth = staffingEighthMonth;
	}

	public String getStaffingNinthMonth() {
		return staffingNinthMonth;
	}

	public void setStaffingNinthMonth(String staffingNinthMonth) {
		this.staffingNinthMonth = staffingNinthMonth;
	}

	public String getStaffingTenthMonth() {
		return staffingTenthMonth;
	}

	public void setStaffingTenthMonth(String staffingTenthMonth) {
		this.staffingTenthMonth = staffingTenthMonth;
	}

	public String getStaffingEleventhMonth() {
		return staffingEleventhMonth;
	}

	public void setStaffingEleventhMonth(String staffingEleventhMonth) {
		this.staffingEleventhMonth = staffingEleventhMonth;
	}

	public String getStaffingTwelthMonth() {
		return staffingTwelthMonth;
	}

	public void setStaffingTwelthMonth(String staffingTwelthMonth) {
		this.staffingTwelthMonth = staffingTwelthMonth;
	}

	public int getIsActive() {
		return isActive;
	}

	public void setIsActive(int isActive) {
		this.isActive = isActive;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public String getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(String updatedDate) {
		this.updatedDate = updatedDate;
	}

}
