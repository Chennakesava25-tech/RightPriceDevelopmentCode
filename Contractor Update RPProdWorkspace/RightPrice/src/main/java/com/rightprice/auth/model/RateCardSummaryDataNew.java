/*package com.rightprice.auth.model;

public class RateCardSummaryDataNew {

}
*/
package com.rightprice.auth.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class RateCardSummaryDataNew {
	
	@Id
	@Column(name="RC_Id")
	private Integer rc_Id;

	@Column(name="RC_Name")
	private String rcName;
	
	@Column(name="Expected_Onsite_Resource_Percentage")
	private Double onsite_Per;
	
	@Column(name="Expected_Offshore_Resource_Percentage")
	private Double offshore_Per;
	
	@Column(name="Calculated_GM_Percentage_Post_Discount")
	private Double gm_Per;
	
	@Column(name="RC_Weightage")
	public Double weightage;
	
	@Column(name="Blended_Rate")
	public Double bl_rate;
	
	@Column(name="Blended_Cost")
	public Double bl_cost;
	
	public Double weightageXbl_rate;
	public Double weightageXbl_cost;
	public Double getWeightageXbl_rate() {
		return weightageXbl_rate;
	}

	public void setWeightageXbl_rate(Double weightageXbl_rate) {
		this.weightageXbl_rate = weightageXbl_rate;
	}

	public Double getWeightageXbl_cost() {
		return weightageXbl_cost;
	}

	public void setWeightageXbl_cost(Double weightageXbl_cost) {
		this.weightageXbl_cost = weightageXbl_cost;
	}

	@Column(name="final_GM")
	public Double final_GM;
	

	public Double getFinal_GM() {
		return final_GM;
	}

	public void setFinal_GM(Double final_GM) {
		this.final_GM = final_GM;
	}

	public Double getWeightage() {
		return weightage;
	}

	public void setWeightage(Double weightage) {
		this.weightage = weightage;
	}

	public Double getBl_rate() {
		return bl_rate;
	}

	public void setBl_rate(Double bl_rate) {
		this.bl_rate = bl_rate;
	}

	public double getBl_cost() {
		return bl_cost;
	}

	public void setBl_cost(Double bl_cost) {
		this.bl_cost = bl_cost;
	}

	

	public Integer getRc_Id() {
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

	public Double getOnsite_Per() {
		return onsite_Per;
	}

	public void setOnsite_Per(Double onsite_Per) {
		this.onsite_Per = onsite_Per;
	}	
	public Double getOffshore_Per() {
		return offshore_Per;
	}

	public void setOffshore_Per(Double offshore_Per) {
		this.offshore_Per = offshore_Per;
	}

	public Double getGm_Per() {
		return gm_Per;
	}

	public void setGm_Per(Double gm_Per) {
		this.gm_Per = gm_Per;
	}	

}
