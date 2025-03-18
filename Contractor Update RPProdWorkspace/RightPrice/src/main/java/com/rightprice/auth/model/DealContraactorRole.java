package com.rightprice.auth.model;

import java.lang.reflect.Field;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.rightprice.auth.util.AppLoger;

@Entity
@Table(name = "SYNPROD.RP_Deal_Contractor_Roles")
public class DealContraactorRole {

	@Id
	@GeneratedValue
	@Column(name = "CONTRACTOR_ROLE_ID")
	private Integer contractorRoleId;
	
	@Column(name="Deal_auto_Tower_Id")
	private Integer dealAutoTowerId;
	
	@Column(name = "RP_DEAL_VERSION_ID")
	private Integer dealVersionId;
	
	@Column(name = "CONTRACTOR_ROLE_NAME")
	private String customerRole;
	
	@Column(name = "COMMENTS")
	private String comments;
	
	@Column(name = "IS_ACTIVE")
	private int isActive= 1;

	//@Column(name = "CREATED_BY", updatable = false)
	@Column(name = "Created_By", updatable = false)
	private String createdBy;

	@Column(name = "CREATED_ON", updatable = false)
	private String createdOn;

	@Column(name = "UPDATED_BY")
	private String updatedBy;

	@Column(name = "UPDATED_ON")
	private String updatedOn;

	public Integer getContractorRoleId() {
		return contractorRoleId;
	}

	public void setContractorRoleId(Integer contractorRoleId) {
		this.contractorRoleId = contractorRoleId;
	}

	public Integer getDealVersionId() {
		return dealVersionId;
	}

	public void setDealVersionId(Integer dealVersionId) {
		this.dealVersionId = dealVersionId;
	}

	public String getCustomerRole() {
		return customerRole;
	}

	public void setCustomerRole(String customerRole) {
		this.customerRole = customerRole;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public Integer getDealAutoTowerId() {
		return dealAutoTowerId;
	}

	public void setDealAutoTowerId(Integer dealAutoTowerId) {
		this.dealAutoTowerId = dealAutoTowerId;
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
