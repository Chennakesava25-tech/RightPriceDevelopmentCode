/*package com.rightprice.auth.model;

public class MultipleRcCustomerMapping {

}
*/

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
@ApiModel(value="Deatils",description="MST_RP_Multi_RC_Customer")
@Table(name = "synprod.MST_RP_Multi_RC_Customer")
public class MultipleRcCustomerMapping implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Integer Id;
	
	@Column(name= "RC_Id")
	@ApiModelProperty(notes = "rcId")
	private Integer rcId;
	
	@Column(name= "Customer_Id")
	@ApiModelProperty(notes = "Customer_Id")
	private Integer customerId;
	
	@Column(name= "Customer_Name")
	@ApiModelProperty(notes = "Customer Name")
	private String custmrName;
	
	
	@Column(name= "Created_On")
	@ApiModelProperty(notes = "Created_On")
	private String createdOn;
	
	@Column(name= "Created_By")
	@ApiModelProperty(notes = "Created_Bn")
	private String createdby;

	
	@Column(name= "Updated_On")
	@ApiModelProperty(notes = "updatedOn")
	private String updatedOn;
	
	@Column(name= "Updated_By")
	@ApiModelProperty(notes = "updatedby")
	private String updatedby;
	
	@Column(name = "IS_ACTIVE")
	@ApiModelProperty(notes = "active status")
	private Integer isActive = 1;

	public Integer getId() {
		return Id;
	}

	public void setId(Integer id) {
		Id = id;
	}

	public Integer getRcId() {
		return rcId;
	}

	public void setRcId(Integer rcId) {
		this.rcId = rcId;
	}

	public Integer getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}

	public String getCustmrName() {
		return custmrName;
	}

	public void setCustmrName(String custmrName) {
		this.custmrName = custmrName;
	}

	public String getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(String createdOn) {
		this.createdOn = createdOn;
	}

	public String getCreatedby() {
		return createdby;
	}

	public void setCreatedby(String createdby) {
		this.createdby = createdby;
	}

	public String getUpdatedOn() {
		return updatedOn;
	}

	public void setUpdatedOn(String updatedOn) {
		this.updatedOn = updatedOn;
	}

	public String getUpdatedby() {
		return updatedby;
	}

	public void setUpdatedby(String updatedby) {
		this.updatedby = updatedby;
	}

	public Integer getIsActive() {
		return isActive;
	}

	public void setIsActive(Integer isActive) {
		this.isActive = isActive;
	}
}