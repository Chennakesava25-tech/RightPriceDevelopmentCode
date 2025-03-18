package com.rightprice.auth.model;

import java.io.Serializable;
import java.lang.reflect.Field;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.rightprice.auth.util.AppLoger;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@Entity
@ApiModel(value = "Deal Tower details", description = "Deal Tower details")
@Table(name = "synprod.RP_DEAL_TOWER")
public class DealTower implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "DEAL_TOWER_ID")
	private Integer dealTowerId;

	@Column(name = "RP_DEAL_VERSION_ID")
	private Integer dealVersionId;

	@Column(name = "TOWER_NAME")
	private String towerName;

	@Column(name = "TOWER_UTILIZATION_PERCENTAGE")
	private Double towerUtilizationPercentage;

	@Column(name = "IS_ACTIVE")
	private Integer isActive = 1;

	@Column(name = "CREATED_BY", updatable = false)
	@ApiModelProperty(notes = "Created by")
	private String createdBy;

	@Column(name = "CREATED_ON", updatable = false)
	@ApiModelProperty(notes = "Created date")
	private String createdOn;

	@Column(name = "UPDATED_BY")
	@ApiModelProperty(notes = "Updated by")
	private String updatedBy;

	@Column(name = "UPDATED_ON")
	@ApiModelProperty(notes = "Updated date")
	private String updatedOn;

	public Integer getDealTowerId() {
		return dealTowerId;
	}

	public void setDealTowerId(Integer dealTowerId) {
		this.dealTowerId = dealTowerId;
	}

	public Integer getDealVersionId() {
		return dealVersionId;
	}

	public void setDealVersionId(Integer dealVersionId) {
		this.dealVersionId = dealVersionId;
	}

	public String getTowerName() {
		return towerName;
	}

	public void setTowerName(String towerName) {
		this.towerName = towerName;
	}

	public Double getTowerUtilizationPercentage() {
		return towerUtilizationPercentage;
	}

	public void setTowerUtilizationPercentage(Double towerUtilizationPercentage) {
		this.towerUtilizationPercentage = towerUtilizationPercentage;
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

	public String toString() {
		StringBuilder result = new StringBuilder();
		String newLine = System.getProperty("line.separator");

		result.append(this.getClass().getName());
		result.append(" Object {");
		result.append(newLine);

		// determine fields declared in this class only (no fields of
		// superclass)
		Field[] fields = this.getClass().getDeclaredFields();

		// print field names paired with their values
		for (Field field : fields) {
			result.append("  ");
			try {
				result.append(field.getName());
				result.append(": ");
				// requires access to private field:
				result.append(field.get(this));
			} catch (IllegalAccessException ex) {
				AppLoger.APPLOGGER.info(ex);
			}
			result.append(newLine);
		}
		result.append("}");

		return result.toString();
	}
}
