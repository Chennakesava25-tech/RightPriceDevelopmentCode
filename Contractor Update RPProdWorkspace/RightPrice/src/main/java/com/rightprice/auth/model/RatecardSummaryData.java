package com.rightprice.auth.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class RatecardSummaryData {
	
	@Id
	@Column(name="RC_Id")
	private int rc_Id;

	@Column(name="RC_Name")
	private String rcName;
	
	@Column(name="Expected_Onsite_Resource_Percentage")
	private double onsite_Per;
	
	@Column(name="Expected_Offshore_Resource_Percentage")
	private double offshore_Per;
	
	@Column(name="Calculated_GM_Percentage_Post_Discount")
	private double gm_Per;
	
	@Column(name="RC_Weightage")
	public double weightage;
	
	@Column(name="Blended_Rate")
	public double bl_rate;
	
	@Column(name="Blended_Cost")
	public double bl_cost;
	
	public double weightageXbl_rate;
	public double weightageXbl_cost;
	public double getWeightageXbl_rate() {
		return weightageXbl_rate;
	}

	public void setWeightageXbl_rate(double weightageXbl_rate) {
		this.weightageXbl_rate = weightageXbl_rate;
	}

	public double getWeightageXbl_cost() {
		return weightageXbl_cost;
	}

	public void setWeightageXbl_cost(double weightageXbl_cost) {
		this.weightageXbl_cost = weightageXbl_cost;
	}

	@Column(name="final_GM")
	public double final_GM;
	

	public double getFinal_GM() {
		return final_GM;
	}

	public void setFinal_GM(double final_GM) {
		this.final_GM = final_GM;
	}

	public double getWeightage() {
		return weightage;
	}

	public void setWeightage(double weightage) {
		this.weightage = weightage;
	}

	public double getBl_rate() {
		return bl_rate;
	}

	public void setBl_rate(double bl_rate) {
		this.bl_rate = bl_rate;
	}

	public double getBl_cost() {
		return bl_cost;
	}

	public void setBl_cost(double bl_cost) {
		this.bl_cost = bl_cost;
	}

	

	public int getRc_Id() {
		return rc_Id;
	}

	public void setRc_Id(int rc_Id) {
		this.rc_Id = rc_Id;
	}

	public String getRcName() {
		return rcName;
	}

	public void setRcName(String rcName) {
		this.rcName = rcName;
	}

	public double getOnsite_Per() {
		return onsite_Per;
	}

	public void setOnsite_Per(double onsite_Per) {
		this.onsite_Per = onsite_Per;
	}	
	public double getOffshore_Per() {
		return offshore_Per;
	}

	public void setOffshore_Per(double offshore_Per) {
		this.offshore_Per = offshore_Per;
	}

	public double getGm_Per() {
		return gm_Per;
	}

	public void setGm_Per(double gm_Per) {
		this.gm_Per = gm_Per;
	}	

}
