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
@ApiModel(value="Vertical POC Deatils",description="Vertical POC Deatils Model Attributes")
@Table(name = "synprod.MST_RP_Vertical_Wise_BU_POC")
public class VerticalWiseBUPOCDetails implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Integer Id;
	
	@Column(name= "Vertical_Id")
	@ApiModelProperty(notes = "verticalID")
	private Integer verticalID;
	
	@Column(name= "Vertical_Desc")
	@ApiModelProperty(notes = "Vertical Desc")
	private String verticalDesc;
	
	@Column(name= "Employee_LAN_Id")
	@ApiModelProperty(notes = "EmployeeID")
	private String EmployeeADId;
	
	@Column(name= "Employee_Name")
	@ApiModelProperty(notes = "EmployeeName")
	private String Employeename;
	
	@Column(name= "Employee_Email")
	@ApiModelProperty(notes = "EmployeeEmail")
	private String Employeeemail;
	
	@Column(name= "Created_On")
	@ApiModelProperty(notes = "Created_On")
	private String createdOn;
	
	@Column(name= "Created_By")
	@ApiModelProperty(notes = "Created_Bn")
	private String createdby;
	
	@Column(name = "IS_ACTIVE")
	@ApiModelProperty(notes = "active status")
	private Integer isActive = 1;


	public Integer getId() {
		return Id;
	}

	public void setId(Integer id) {
		Id = id;
	}

	public String getVerticalDesc() {
		return verticalDesc;
	}

	public void setVerticalDesc(String verticalDesc) {
		this.verticalDesc = verticalDesc;
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

	public Integer getIsActive() {
		return isActive;
	}

	public void setIsActive(Integer isActive) {
		this.isActive = isActive;
	}

	public Integer getVerticalID() {
		return verticalID;
	}

	public void setVerticalID(Integer verticalID) {
		this.verticalID = verticalID;
	}

	public String getEmployeeADId() {
		return EmployeeADId;
	}

	public void setEmployeeADId(String employeeADId) {
		EmployeeADId = employeeADId;
	}

	public String getEmployeename() {
		return Employeename;
	}

	public void setEmployeename(String employeename) {
		Employeename = employeename;
	}

	public String getEmployeeemail() {
		return Employeeemail;
	}

	public void setEmployeeemail(String employeeemail) {
		Employeeemail = employeeemail;
	}
	
	

}

