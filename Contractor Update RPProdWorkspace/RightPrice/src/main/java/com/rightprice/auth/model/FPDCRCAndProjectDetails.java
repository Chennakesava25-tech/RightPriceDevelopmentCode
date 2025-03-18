package com.rightprice.auth.model;

import java.util.List;

public class FPDCRCAndProjectDetails {

	private Integer dealVersionId;
	
	private List<DealCurrencyUtilization> dealCurrencyUtilization;
	
	private List<DealLocation> dealLocation;
	
	private List<DealFixLocation> dealFPTower;

	private List<DealTower> dealTower;
	
	private List<DealRateCard> dealRateCard;
	
	private List<FPDealRatecard> dealFPRateCard;
	
	private List<RPDealHardwareSoftwareCost> onlineCostDetails;
	
	private List<RPDealRelocationDetails> travelRelocationCostDetails;
	
	private List<StaffingDealContraactorRole> stfContrRoleList;
	
	private List<AccessControl> rpAccessControls;
	
	private List<RpRole> rpRoleAccess;

	

	private List<ContractorRole> rpContractorRoles;
	
	public List<StaffingDealContraactorRole> getStfContrRoleList() {
		return stfContrRoleList;
	}

	public void setStfContrRoleList(List<StaffingDealContraactorRole> stfContrRoleList) {
		this.stfContrRoleList = stfContrRoleList;
	}

	public List<DealRateCard> getDealRateCard() {
		return dealRateCard;
	}

	public void setDealRateCard(List<DealRateCard> dealRateCard) {
		this.dealRateCard = dealRateCard;
	}

	public Integer getDealVersionId() {
		return dealVersionId;
	}

	public void setDealVersionId(Integer dealVersionId) {
		this.dealVersionId = dealVersionId;
	}

	public List<DealCurrencyUtilization> getDealCurrencyUtilization() {
		return dealCurrencyUtilization;
	}

	public void setDealCurrencyUtilization(List<DealCurrencyUtilization> dealCurrencyUtilization) {
		this.dealCurrencyUtilization = dealCurrencyUtilization;
	}

	public List<DealLocation> getDealLocation() {
		return dealLocation;
	}

	public void setDealLocation(List<DealLocation> dealLocation) {
		this.dealLocation = dealLocation;
	}

	public List<RPDealHardwareSoftwareCost> getOnlineCostDetails() {
		return onlineCostDetails;
	}

	public void setOnlineCostDetails(List<RPDealHardwareSoftwareCost> onlineCostDetails) {
		this.onlineCostDetails = onlineCostDetails;
	}

	public List<RPDealRelocationDetails> getTravelRelocationCostDetails() {
		return travelRelocationCostDetails;
	}

	public void setTravelRelocationCostDetails(List<RPDealRelocationDetails> travelRelocationCostDetails) {
		this.travelRelocationCostDetails = travelRelocationCostDetails;
	}

	public List<AccessControl> getRpAccessControls() {
		return rpAccessControls;
	}

	public void setRpAccessControls(List<AccessControl> rpAccessControls) {
		this.rpAccessControls = rpAccessControls;
	}

	public List<ContractorRole> getRpContractorRoles() {
		return rpContractorRoles;
	}

	public void setRpContractorRoles(List<ContractorRole> rpContractorRoles) {
		this.rpContractorRoles = rpContractorRoles;
	}

	public List<FPDealRatecard> getDealFPRateCard() {
		return dealFPRateCard;
	}

	public void setDealFPRateCard(List<FPDealRatecard> dealFPRateCard) {
		this.dealFPRateCard = dealFPRateCard;
	}

	public List<DealFixLocation> getDealFPTower() {
		return dealFPTower;
	}

	public void setDealFPTower(List<DealFixLocation> dealFPTower) {
		this.dealFPTower = dealFPTower;
	}

	public List<DealTower> getDealTower() {
		return dealTower;
	}

	public void setDealTower(List<DealTower> dealTower) {
		this.dealTower = dealTower;
	
}

	public List<RpRole> getRpRoleAccess() {
		return rpRoleAccess;
	}

	public void setRpRoleAccess(List<RpRole> rpRoleAccess) {
		this.rpRoleAccess = rpRoleAccess;
	}
}