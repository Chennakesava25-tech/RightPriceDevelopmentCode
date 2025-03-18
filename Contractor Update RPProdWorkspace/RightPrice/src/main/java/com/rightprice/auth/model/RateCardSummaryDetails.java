package com.rightprice.auth.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


@Entity
@ApiModel(value="summary details",description="Rate card summary details")
@Table(name = "synprod.RP_Rate_Card_Summary_details")
public class RateCardSummaryDetails {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "RC_Summary_id")
	private Integer rcSummaryId;
	
	@Column(name ="RC_Id")
	private Integer rcId;
	
	public Integer getRcSummaryId() {
		return rcSummaryId;
	}

	public void setRcSummaryId(Integer rcSummaryId) {
		this.rcSummaryId = rcSummaryId;
	}

	@Column(name="Country_Id")
	private Integer countryId;
	
	@Column(name = "Location")
	private String Location;
	
	@Column(name = "Utilization_Mix")
	private Double utilizationMix;
	
	@Column(name = "Year1_BR")
	private Double year1BR;
	
	@Column(name = "Year2_BR")
	private Double year2BR;
	
	@Column(name = "Year3_BR")	
	private Double year3BR;
	
	@Column(name = "Year4_BR")	
	private Double year4BR;
	
	@Column(name = "Year5_BR")	
	private Double year5BR;
	
	
	@Column(name = "Year6_BR")
	private Double year6BR;
	
	@Column(name = "Year7_BR")
	private Double year7BR;
	
	@Column(name = "Year8_BR")	
	private Double year8BR;
	
	@Column(name = "Year9_BR")	
	private Double year9BR;
	
	@Column(name = "Year10_BR")	
	private Double year10BR;
	
	
	
	public Double getYear6BR() {
		return year6BR;
	}

	public void setYear6BR(Double year6br) {
		year6BR = year6br;
	}

	public Double getYear7BR() {
		return year7BR;
	}

	public void setYear7BR(Double year7br) {
		year7BR = year7br;
	}

	public Double getYear8BR() {
		return year8BR;
	}

	public void setYear8BR(Double year8br) {
		year8BR = year8br;
	}

	public Double getYear9BR() {
		return year9BR;
	}

	public void setYear9BR(Double year9br) {
		year9BR = year9br;
	}

	public Double getYear10BR() {
		return year10BR;
	}

	public void setYear10BR(Double year10br) {
		year10BR = year10br;
	}

	@Column(name = "Average_BR")	
	private Double averageBR;
	
	@Column(name = "Year1_BC")
	private Double year1BC;
	
	@Column(name = "Year2_BC")
	private Double year2BC;
	
	@Column(name = "Year3_BC")
	private Double year3BC;
	
	@Column(name = "Year4_BC")
	private Double year4BC;
	
	@Column(name = "Year5_BC")
	private Double year5BC;
	
	@Column(name = "Year6_BC")
	private Double year6BC;
	
	@Column(name = "Year7_BC")
	private Double year7BC;
	
	public Double getYear6BC() {
		return year6BC;
	}

	public void setYear6BC(Double year6bc) {
		year6BC = year6bc;
	}

	public Double getYear7BC() {
		return year7BC;
	}

	public void setYear7BC(Double year7bc) {
		year7BC = year7bc;
	}

	public Double getYear8BC() {
		return year8BC;
	}

	public void setYear8BC(Double year8bc) {
		year8BC = year8bc;
	}

	public Double getYear9BC() {
		return year9BC;
	}

	public void setYear9BC(Double year9bc) {
		year9BC = year9bc;
	}

	public Double getYear10BC() {
		return year10BC;
	}

	public void setYear10BC(Double year10bc) {
		year10BC = year10bc;
	}

	@Column(name = "Year8_BC")
	private Double year8BC;
	
	@Column(name = "Year9_BC")
	private Double year9BC;
	
	@Column(name = "Year10_BC")
	private Double year10BC;
	
	@Column(name = "Average_BC")
	private Double averageBC;
	
	@Column(name = "Year1_before_GM")	
	private Double year1beforeGM;
	
	@Column(name = "Year2_before_GM")	
	private Double year2beforeGM;
	
	@Column(name = "Year3_before_GM")	
	private Double year3beforeGM;
	
	@Column(name = "Year4_before_GM")	
	private Double year4beforeGM;
	
	@Column(name = "Year5_before_GM")	
	private Double year5beforeGM;
	
	@Column(name = "Year6_before_GM")	
	private Double year6beforeGM;
	
	@Column(name = "Year7_before_GM")	
	private Double year7beforeGM;
	
	@Column(name = "Year8_before_GM")	
	private Double year8beforeGM;
	
	@Column(name = "Year9_before_GM")	
	private Double year9beforeGM;
	
	@Column(name = "Year10_before_GM")	
	private Double year10beforeGM;
	
	@Column(name = "Average_Before_GM")	
	private Double averagebeforeGM;
	
	@Column(name = "Year1_GM")
	private Double year1GM;
	
	@Column(name = "Year2_GM")
	private Double year2GM;
	
	@Column(name = "Year3_GM")
	private Double year3GM;
	
	@Column(name = "Year4_GM")
	private Double year4GM;
	
	@Column(name = "Year5_GM")
	private Double year5GM;
	
	@Column(name = "Year6_GM")
	private Double year6GM;
	
	@Column(name = "Year7_GM")
	private Double year7GM;
	
	@Column(name = "Year8_GM")
	private Double year8GM;
	
	@Column(name = "Year9_GM")
	private Double year9GM;
	
	@Column(name = "Year10_GM")
	private Double year10GM;

	@Column(name = "Average_GM")	
	private Double averageGM;
	
	@Column(name="Year1_before_MGM")
	private Double year1beforeMGM;
	
	@Column(name="Year2_before_MGM")
	private Double year2beforeMGM;
	
	@Column(name="Year3_before_MGM")
	private Double year3beforeMGM;
	
	@Column(name="Year4_before_MGM")
	private Double year4beforeMGM;
	
	@Column(name="Year5_before_MGM")
	private Double year5beforeMGM;
	
	@Column(name="Year6_before_MGM")
	private Double year6beforeMGM;
	
	@Column(name="Year7_before_MGM")
	private Double year7beforeMGM;
	
	@Column(name="Year8_before_MGM")
	private Double year8beforeMGM;
	
	@Column(name="Year9_before_MGM")
	private Double year9beforeMGM;
	
	@Column(name="Year10_before_MGM")
	private Double year10beforeMGM;
	
	@Column(name = "Average_Before_MGM")	
	private Double averagebeforeMGM;
	
	@Column(name="Year1_MGM")
	private Double year1MGM;
	
	@Column(name="Year2_MGM")
	private Double year2MGM;
	
	@Column(name="Year3_MGM")
	private Double year3MGM;
	
	@Column(name="Year4_MGM")
	private Double year4MGM;
	
	@Column(name="Year5_MGM")
	private Double year5MGM;
	
	@Column(name="Year6_MGM")
	private Double year6MGM;
	
	@Column(name="Year7_MGM")
	private Double year7MGM;
	
	@Column(name="Year8_MGM")
	private Double year8MGM;
	
	@Column(name="Year9_MGM")
	private Double year9MGM;
	
	@Column(name="Year10_MGM")
	private Double year10MGM;
	
	
	@Column(name = "Average_MGM")	
	private Double averageMGM;
	
	@Column(name = "Fx_Rate_comment")	
	private String fxRateComment;
	
	@Column(name = "Record_type")	
	private Integer recordType;
	
	@Column(name = "IS_ACTIVE")
	@ApiModelProperty(notes="Active status")
	private Integer isActive;

	

	public Integer getCountryId() {
		return countryId;
	}

	public void setCountryId(Integer countryId) {
		this.countryId = countryId;
	}

	public Integer getRcId() {
		return rcId;
	}

	public void setRcId(Integer rcId) {
		this.rcId = rcId;
	}



	public Double getYear3BR() {
		return year3BR;
	}

	public void setYear3BR(Double year3br) {
		year3BR = year3br;
	}

	public Double getYear4BR() {
		return year4BR;
	}

	public void setYear4BR(Double year4br) {
		year4BR = year4br;
	}

	public Double getYear5BR() {
		return year5BR;
	}

	public void setYear5BR(Double year5br) {
		year5BR = year5br;
	}

	public Double getAverageBR() {
		return averageBR;
	}

	public void setAverageBR(Double averageBR) {
		this.averageBR = averageBR;
	}

	
	public Double getYear1beforeGM() {
		return year1beforeGM;
	}

	public void setYear1beforeGM(Double year1beforeGM) {
		this.year1beforeGM = year1beforeGM;
	}

	public Double getYear2beforeGM() {
		return year2beforeGM;
	}

	public void setYear2beforeGM(Double year2beforeGM) {
		this.year2beforeGM = year2beforeGM;
	}

	public Double getYear3beforeGM() {
		return year3beforeGM;
	}

	public void setYear3beforeGM(Double year3beforeGM) {
		this.year3beforeGM = year3beforeGM;
	}

	public Double getYear4beforeGM() {
		return year4beforeGM;
	}

	public void setYear4beforeGM(Double year4beforeGM) {
		this.year4beforeGM = year4beforeGM;
	}

	public Double getYear5beforeGM() {
		return year5beforeGM;
	}

	public void setYear5beforeGM(Double year5beforeGM) {
		this.year5beforeGM = year5beforeGM;
	}

	public Double getAveragebeforeGM() {
		return averagebeforeGM;
	}

	public void setAveragebeforeGM(Double averagebeforeGM) {
		this.averagebeforeGM = averagebeforeGM;
	}

	public Double getAverageGM() {
		return averageGM;
	}

	public void setAverageGM(Double averageGM) {
		this.averageGM = averageGM;
	}


	public String getLocation() {
		return Location;
	}

	public void setLocation(String location) {
		Location = location;
	}

	public Double getUtilizationMix() {
		return utilizationMix;
	}

	public void setUtilizationMix(Double utilizationMix) {
		this.utilizationMix = utilizationMix;
	}

	public Double getYear1BR() {
		return year1BR;
	}

	public void setYear1BR(Double year1br) {
		year1BR = year1br;
	}

	public Double getYear2BR() {
		return year2BR;
	}

	public void setYear2BR(Double year2br) {
		year2BR = year2br;
	}


	public String getFxRateComment() {
		return fxRateComment;
	}

	public void setFxRateComment(String fxRateComment) {
		this.fxRateComment = fxRateComment;
	}

	public Integer getRecordType() {
		return recordType;
	}

	public void setRecordType(Integer recordType) {
		this.recordType = recordType;
	}

	public Integer getIsActive() {
		return isActive;
	}

	public void setIsActive(Integer isActive) {
		this.isActive = isActive;
	}

	public Double getAverageMGM() {
		return averageMGM;
	}

	public void setAverageMGM(Double averageMGM) {
		this.averageMGM = averageMGM;
	}

	public Double getYear1BC() {
		return year1BC;
	}

	public void setYear1BC(Double year1bc) {
		year1BC = year1bc;
	}

	public Double getYear2BC() {
		return year2BC;
	}

	public void setYear2BC(Double year2bc) {
		year2BC = year2bc;
	}

	public Double getYear3BC() {
		return year3BC;
	}

	public void setYear3BC(Double year3bc) {
		year3BC = year3bc;
	}

	public Double getYear4BC() {
		return year4BC;
	}

	public void setYear4BC(Double year4bc) {
		year4BC = year4bc;
	}

	public Double getYear5BC() {
		return year5BC;
	}

	public void setYear5BC(Double year5bc) {
		year5BC = year5bc;
	}

	public Double getAverageBC() {
		return averageBC;
	}

	public void setAverageBC(Double averageBC) {
		this.averageBC = averageBC;
	}

	public Double getYear1GM() {
		return year1GM;
	}

	public void setYear1GM(Double year1gm) {
		year1GM = year1gm;
	}

	public Double getYear2GM() {
		return year2GM;
	}

	public void setYear2GM(Double year2gm) {
		year2GM = year2gm;
	}

	public Double getYear3GM() {
		return year3GM;
	}

	public void setYear3GM(Double year3gm) {
		year3GM = year3gm;
	}

	public Double getYear4GM() {
		return year4GM;
	}

	public void setYear4GM(Double year4gm) {
		year4GM = year4gm;
	}

	public Double getYear5GM() {
		return year5GM;
	}

	public void setYear5GM(Double year5gm) {
		year5GM = year5gm;
	}

	public Double getYear1beforeMGM() {
		return year1beforeMGM;
	}

	public void setYear1beforeMGM(Double year1beforeMGM) {
		this.year1beforeMGM = year1beforeMGM;
	}

	public Double getYear2beforeMGM() {
		return year2beforeMGM;
	}

	public void setYear2beforeMGM(Double year2beforeMGM) {
		this.year2beforeMGM = year2beforeMGM;
	}

	public Double getYear3beforeMGM() {
		return year3beforeMGM;
	}

	public void setYear3beforeMGM(Double year3beforeMGM) {
		this.year3beforeMGM = year3beforeMGM;
	}

	public Double getYear4beforeMGM() {
		return year4beforeMGM;
	}

	public void setYear4beforeMGM(Double year4beforeMGM) {
		this.year4beforeMGM = year4beforeMGM;
	}

	public Double getYear5beforeMGM() {
		return year5beforeMGM;
	}

	public void setYear5beforeMGM(Double year5beforeMGM) {
		this.year5beforeMGM = year5beforeMGM;
	}

	public Double getAveragebeforeMGM() {
		return averagebeforeMGM;
	}

	public void setAveragebeforeMGM(Double averagebeforeMGM) {
		this.averagebeforeMGM = averagebeforeMGM;
	}

	public Double getYear1MGM() {
		return year1MGM;
	}

	public void setYear1MGM(Double year1mgm) {
		year1MGM = year1mgm;
	}

	public Double getYear2MGM() {
		return year2MGM;
	}

	public void setYear2MGM(Double year2mgm) {
		year2MGM = year2mgm;
	}

	public Double getYear3MGM() {
		return year3MGM;
	}

	public void setYear3MGM(Double year3mgm) {
		year3MGM = year3mgm;
	}

	public Double getYear4MGM() {
		return year4MGM;
	}

	public void setYear4MGM(Double year4mgm) {
		year4MGM = year4mgm;
	}

	public Double getYear5MGM() {
		return year5MGM;
	}

	public void setYear5MGM(Double year5mgm) {
		year5MGM = year5mgm;
	}

	public Double getYear6beforeGM() {
		return year6beforeGM;
	}

	public void setYear6beforeGM(Double year6beforeGM) {
		this.year6beforeGM = year6beforeGM;
	}

	public Double getYear7beforeGM() {
		return year7beforeGM;
	}

	public void setYear7beforeGM(Double year7beforeGM) {
		this.year7beforeGM = year7beforeGM;
	}

	public Double getYear8beforeGM() {
		return year8beforeGM;
	}

	public void setYear8beforeGM(Double year8beforeGM) {
		this.year8beforeGM = year8beforeGM;
	}

	public Double getYear9beforeGM() {
		return year9beforeGM;
	}

	public void setYear9beforeGM(Double year9beforeGM) {
		this.year9beforeGM = year9beforeGM;
	}

	public Double getYear10beforeGM() {
		return year10beforeGM;
	}

	public void setYear10beforeGM(Double year10beforeGM) {
		this.year10beforeGM = year10beforeGM;
	}

	public Double getYear6GM() {
		return year6GM;
	}

	public void setYear6GM(Double year6gm) {
		year6GM = year6gm;
	}

	public Double getYear7GM() {
		return year7GM;
	}

	public void setYear7GM(Double year7gm) {
		year7GM = year7gm;
	}

	public Double getYear8GM() {
		return year8GM;
	}

	public void setYear8GM(Double year8gm) {
		year8GM = year8gm;
	}

	public Double getYear9GM() {
		return year9GM;
	}

	public void setYear9GM(Double year9gm) {
		year9GM = year9gm;
	}

	public Double getYear10GM() {
		return year10GM;
	}

	public void setYear10GM(Double year10gm) {
		year10GM = year10gm;
	}

	public Double getYear6beforeMGM() {
		return year6beforeMGM;
	}

	public void setYear6beforeMGM(Double year6beforeMGM) {
		this.year6beforeMGM = year6beforeMGM;
	}

	public Double getYear7beforeMGM() {
		return year7beforeMGM;
	}

	public void setYear7beforeMGM(Double year7beforeMGM) {
		this.year7beforeMGM = year7beforeMGM;
	}

	public Double getYear8beforeMGM() {
		return year8beforeMGM;
	}

	public void setYear8beforeMGM(Double year8beforeMGM) {
		this.year8beforeMGM = year8beforeMGM;
	}

	public Double getYear9beforeMGM() {
		return year9beforeMGM;
	}

	public void setYear9beforeMGM(Double year9beforeMGM) {
		this.year9beforeMGM = year9beforeMGM;
	}

	public Double getYear10beforeMGM() {
		return year10beforeMGM;
	}

	public void setYear10beforeMGM(Double year10beforeMGM) {
		this.year10beforeMGM = year10beforeMGM;
	}

	public Double getYear6MGM() {
		return year6MGM;
	}

	public void setYear6MGM(Double year6mgm) {
		year6MGM = year6mgm;
	}

	public Double getYear7MGM() {
		return year7MGM;
	}

	public void setYear7MGM(Double year7mgm) {
		year7MGM = year7mgm;
	}

	public Double getYear8MGM() {
		return year8MGM;
	}

	public void setYear8MGM(Double year8mgm) {
		year8MGM = year8mgm;
	}

	public Double getYear9MGM() {
		return year9MGM;
	}

	public void setYear9MGM(Double year9mgm) {
		year9MGM = year9mgm;
	}

	public Double getYear10MGM() {
		return year10MGM;
	}

	public void setYear10MGM(Double year10mgm) {
		year10MGM = year10mgm;
	}
	
	@Column(name = "YEAR1_After_risk_VD_GM")
	private Double year1AfterRiskVD;
	
	@Column(name = "YEAR2_After_risk_VD_GM")
	private Double year2AfterRiskVD;
	
	@Column(name = "YEAR3_After_risk_VD_GM")	
	private Double year3AfterRiskVD;
	
	@Column(name = "YEAR4_After_risk_VD_GM")	
	private Double year4AfterRiskVD;
	
	@Column(name = "YEAR5_After_risk_VD_GM")	
	private Double year5AfterRiskVD;
	
	
	@Column(name = "YEAR6_After_risk_VD_GM")
	private Double year6AfterRiskVD;
	
	@Column(name = "YEAR8_After_risk_VD_GM")
	private Double year7AfterRiskVD;
	
	@Column(name = "YEAR7_After_risk_VD_GM")	
	private Double year8AfterRiskVD;
	
	@Column(name = "YEAR9_After_risk_VD_GM")	
	private Double year9AfterRiskVD;
	
	@Column(name = "YEAR10_After_risk_VD_GM")	
	private Double year10AfterRiskVD;

	@Column(name = "Average_After_risk_VD_GM")	
	private Double averageAfterRiskVD;
	

	
	
	
	public Double getAverageAfterRiskVD() {
		return averageAfterRiskVD;
	}

	public void setAverageAfterRiskVD(Double averageAfterRiskVD) {
		this.averageAfterRiskVD = averageAfterRiskVD;
	}

	public Double getYear1AfterRiskVD() {
		return year1AfterRiskVD;
	}

	public void setYear1AfterRiskVD(Double year1AfterRiskVD) {
		this.year1AfterRiskVD = year1AfterRiskVD;
	}

	public Double getYear2AfterRiskVD() {
		return year2AfterRiskVD;
	}

	public void setYear2AfterRiskVD(Double year2AfterRiskVD) {
		this.year2AfterRiskVD = year2AfterRiskVD;
	}

	public Double getYear3AfterRiskVD() {
		return year3AfterRiskVD;
	}

	public void setYear3AfterRiskVD(Double year3AfterRiskVD) {
		this.year3AfterRiskVD = year3AfterRiskVD;
	}

	public Double getYear4AfterRiskVD() {
		return year4AfterRiskVD;
	}

	public void setYear4AfterRiskVD(Double year4AfterRiskVD) {
		this.year4AfterRiskVD = year4AfterRiskVD;
	}

	public Double getYear5AfterRiskVD() {
		return year5AfterRiskVD;
	}

	public void setYear5AfterRiskVD(Double year5AfterRiskVD) {
		this.year5AfterRiskVD = year5AfterRiskVD;
	}

	public Double getYear6AfterRiskVD() {
		return year6AfterRiskVD;
	}

	public void setYear6AfterRiskVD(Double year6AfterRiskVD) {
		this.year6AfterRiskVD = year6AfterRiskVD;
	}

	public Double getYear7AfterRiskVD() {
		return year7AfterRiskVD;
	}

	public void setYear7AfterRiskVD(Double year7AfterRiskVD) {
		this.year7AfterRiskVD = year7AfterRiskVD;
	}

	public Double getYear8AfterRiskVD() {
		return year8AfterRiskVD;
	}

	public void setYear8AfterRiskVD(Double year8AfterRiskVD) {
		this.year8AfterRiskVD = year8AfterRiskVD;
	}

	public Double getYear9AfterRiskVD() {
		return year9AfterRiskVD;
	}

	public void setYear9AfterRiskVD(Double year9AfterRiskVD) {
		this.year9AfterRiskVD = year9AfterRiskVD;
	}

	public Double getYear10AfterRiskVD() {
		return year10AfterRiskVD;
	}

	public void setYear10AfterRiskVD(Double year10AfterRiskVD) {
		this.year10AfterRiskVD = year10AfterRiskVD;
	}
	
	
	@Column(name = "Totalriskamount")        
	private Double totalriskamount;  

	@Column(name = "offshoreblendedrate")
	private Double offshoreblendedrate; 
	
	@Column(name = "offshoreblendedcost")
	private Double offshoreblendedcost; 
	
	@Column(name = "overalblendedrate")
	private Double overallblendedrate; 
	
	@Column(name = "overallblendedcost")
	private Double overallblendedcost;

	public Double getTotalriskamount() {
		return totalriskamount;
	}

	public void setTotalriskamount(Double totalriskamount) {
		this.totalriskamount = totalriskamount;
	}

	public Double getOffshoreblendedrate() {
		return offshoreblendedrate;
	}

	public void setOffshoreblendedrate(Double offshoreblendedrate) {
		this.offshoreblendedrate = offshoreblendedrate;
	}

	public Double getOffshoreblendedcost() {
		return offshoreblendedcost;
	}

	public void setOffshoreblendedcost(Double offshoreblendedcost) {
		this.offshoreblendedcost = offshoreblendedcost;
	}

	public Double getOverallblendedrate() {
		return overallblendedrate;
	}

	public void setOverallblendedrate(Double overallblendedrate) {
		this.overallblendedrate = overallblendedrate;
	}

	public Double getOverallblendedcost() {
		return overallblendedcost;
	}

	public void setOverallblendedcost(Double overallblendedcost) {
		this.overallblendedcost = overallblendedcost;
	} 
	
}
