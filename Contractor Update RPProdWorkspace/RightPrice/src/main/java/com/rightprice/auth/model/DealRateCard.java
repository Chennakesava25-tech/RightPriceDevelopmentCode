package com.rightprice.auth.model;

import java.io.Serializable;
import java.lang.reflect.Field;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import com.rightprice.auth.util.AppLoger;

import io.swagger.annotations.ApiModelProperty;

@Entity
@Table(name="synprod.RP_Deal_RateCards")
public class DealRateCard implements Serializable
{	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Deal_Rate_Card_Id")
	private int dealRateCardId;	
	
	@Column(name ="RP_Deal_Version_Id")
	@ApiModelProperty(notes="RP_Deal_Version_Id")
	private Integer dealVersionId;
	
	@Column(name ="Deal_RC_Id")
	@ApiModelProperty(notes="RC_ID")
	private Integer rcId;
	
	@Column(name = "RC_Weightage")
	private double weightage;
	
	@Column(name = "GM_Percentage")
	private double percentage;	
	
	
	@Column(name ="IS_ACTIVE")
	@ApiModelProperty(notes="active status")
	private Integer isActive=1;	
	
	@Column(name = "CREATED_BY", updatable=false)
	@ApiModelProperty(notes="Created by")
	private String createdBy;	
	
	@Column(name = "CREATED_ON", updatable=false)
	@ApiModelProperty(notes="Created date")
	private String createdOn;	
	
	@Column(name = "UPDATED_BY")
	@ApiModelProperty(notes="Updated by")
	private String updatedBy;	
	
	@Column(name = "UPDATED_ON")
	@ApiModelProperty(notes="Updated date")
	private String updatedOn;
	
	@OneToOne(fetch = FetchType.LAZY, cascade = javax.persistence.CascadeType.ALL)
	@NotFound(action = NotFoundAction.IGNORE)
	@JoinColumn(name ="Deal_RC_Id",referencedColumnName = "RC_ID",insertable=false, updatable=false)
	private RateCardDetails rateCrdDetails;
	
	
	
	public int getDealRateCardId() {
		return dealRateCardId;
	}
	public void setDealRateCardId(int dealRateCardId) {
		this.dealRateCardId = dealRateCardId;
	}
	public Integer getDealVersionId() {
		return dealVersionId;
	}
	public void setDealVersionId(Integer dealVersionId) {
		this.dealVersionId = dealVersionId;
	}
	public Integer getRcId() {
		return rcId;
	}
	public void setRcId(Integer rcId) {
		this.rcId = rcId;
	}
	public double getWeightage() {
		return weightage;
	}
	public void setWeightage(double weightage) {
		this.weightage = weightage;
	}
	public double getPercentage() {
		return percentage;
	}
	public void setPercentage(double percentage) {
		this.percentage = percentage;
	}
	public Integer getIsActive() {
		return isActive;
	}
	public void setIsActive(Integer isActive) {
		this.isActive = isActive;
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
	public String getUpdatedBy() {
		return updatedBy;
	}
	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}
	public String getUpdatedOn() {
		return updatedOn;
	}
	public void setUpdatedOn(String updatedOn) {
		this.updatedOn = updatedOn;
	}
	public RateCardDetails getRateCrdDetails() {
		return rateCrdDetails;
	}
	public void setRateCrdDetails(RateCardDetails rateCrdDetails) {
		this.rateCrdDetails = rateCrdDetails;
	}
	
	public String toString() {
		  StringBuilder result = new StringBuilder();
		  String newLine = System.getProperty("line.separator");

		  result.append( this.getClass().getName() );
		  result.append( " Object {" );
		  result.append(newLine);

		  //determine fields declared in this class only (no fields of superclass)
		  Field[] fields = this.getClass().getDeclaredFields();

		  //print field names paired with their values
		  for ( Field field : fields  ) {
		    result.append("  ");
		    try {
		      result.append( field.getName() );
		      result.append(": ");
		      //requires access to private field:
		      result.append( field.get(this) );
		    } catch ( IllegalAccessException ex ) {
		      AppLoger.APPLOGGER.info(ex);
		    }
		    result.append(newLine);
		  }
		  result.append("}");

		  return result.toString();
		}
	
}
