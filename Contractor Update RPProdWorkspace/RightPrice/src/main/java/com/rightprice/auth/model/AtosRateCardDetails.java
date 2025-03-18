package com.rightprice.auth.model;

import java.io.Serializable;
import java.lang.reflect.Field;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.rightprice.auth.util.AppLoger;
import com.rightprice.auth.util.DateUtil;

import io.swagger.annotations.ApiModelProperty;

@Entity
public class AtosRateCardDetails implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Id")
	@ApiModelProperty(notes="Id")
	private Integer ID;
	
	@Column(name = "Attachment_Id")
	@ApiModelProperty(notes="Attachment_Id")
	private Integer attachmentID;
	
	@Id
	@Column(name = "RC_Location_Id")
	private Integer rcLocationId;
	
	@Column(name = "RC_ID")
	private Integer rcId;
	
	@Column(name = "RC_NAME")
	private String rcName;

	@Column(name = "RC_START_DATE")
	private String rcStartDate;
	
	@Column(name = "RC_END_DATE")
	private String rcEndDate;
	
	@Column(name ="IS_ACTIVE")
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
	
	@Column(name = "Expected_Onsite_Resource_Percentage")
	private Integer onsitePer;
	
	@Column(name = "Expected_Offshore_Resource_Percentage")
	private Integer offshorePer;
	
	
	public Integer getID() {
		return ID;
	}

	public void setID(Integer iD) {
		ID = iD;
	}

	public Integer getOnsitePer() {
		return onsitePer;
	}

	public void setOnsitePer(Integer onsitePer) {
		this.onsitePer = onsitePer;
	}

	public Integer getOffshorePer() {
		return offshorePer;
	}

	public void setOffshorePer(Integer offshorePer) {
		this.offshorePer = offshorePer;
	}

	@Column(name = "VERTICAL_ID")
	@ApiModelProperty(notes="VERTICAL_ID")
	private Integer verticalId;
	
	@Column(name = "Calculated_GM_Percentage_Post_Discount")
	@ApiModelProperty(notes="Calculated_GM_Percentage_Post_Discount")
	private Double calGMPercentage;
	
	public Double getCalGMPercentage() {
		return calGMPercentage;
	}

	public void setCalGMPercentage(Double calGMPercentage) {
		this.calGMPercentage = calGMPercentage;
	}

	@Column(name = "CUSTOMER_ID")
	@ApiModelProperty(notes="CUSTOMER_ID")
	private Integer customerId;

	@Column(name = "Current_Approval_Status")
	@ApiModelProperty(notes="Current_Approval_Status")
	private Integer current_ApprovalStatus;

	public Integer getAttachmentID() {
		return attachmentID;
	}

	public void setAttachmentID(Integer attachmentID) {
		this.attachmentID = attachmentID;
	}

	@Column(name = "Is_Atos")
	@ApiModelProperty(notes = "Is Atos")
	private Integer isAtos;

	public Integer getCurrent_ApprovalStatus() {
		return current_ApprovalStatus;
	}

	public void setCurrent_ApprovalStatus(Integer current_ApprovalStatus) {
		this.current_ApprovalStatus = current_ApprovalStatus;
	}

	@Column(name = "Country_Id")
	@ApiModelProperty(notes = "Country_Id")
	private Integer countryId;

	@Column(name = "City_Id")
	@ApiModelProperty(notes = "City_Id")
	private Integer cityId;

	@Column(name = "Country_Name")
	private String countryName;
	
	@Column(name = "City_Name")
	private String cityName;
	
	@Column(name = "vertical_Name")
	private String verticalName;
	
	@Column(name = "CUSTOMER_Name")
	private String customerName;
	
	
	public Integer getCountryId() {
		return countryId;
	}

	public void setCountryId(Integer countryId) {
		this.countryId = countryId;
	}

	public Integer getCityId() {
		return cityId;
	}

	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}

	public String getCountryName() {
		return countryName;
	}

	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public String getVerticalName() {
		return verticalName;
	}

	public void setVerticalName(String verticalName) {
		this.verticalName = verticalName;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public Integer getRcId() {
		return rcId;
	}

	public void setRcId(Integer rcId) {
		this.rcId = rcId;
	}

	public String getRcStartDate() {
		return rcStartDate;
	}

	
	public void setRcStartDate(String rcStartDate) {
		
		this.rcStartDate =  DateUtil.getMSSqlFormattedDate(rcStartDate);
	}

	public String getRcEndDate() {
		return rcEndDate;
	}

	public void setRcEndDate(String rcEndDate) {
		this.rcEndDate =  DateUtil.getMSSqlFormattedDate(rcEndDate);
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

	public void setCreatedOn(String createdDate) {
		this.createdOn = createdDate;
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

	public Integer getVerticalId() {
		return verticalId;
	}

	public void setVerticalId(Integer verticalId) {
		this.verticalId = verticalId;
	}

	
	public Integer getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}
	
	public Integer getIsAtos() {
		return isAtos;
	}

	public void setIsAtos(Integer isAtos) {
		this.isAtos = isAtos;
	}

	
	public String getRcName() {
		return rcName;
	}
	
	public void setRcName(String rcName) {
		this.rcName = rcName;
	}

	public Integer getRcLocationId() {
		return rcLocationId;
	}

	public void setRcLocationId(Integer rcLocationId) {
		this.rcLocationId = rcLocationId;
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
