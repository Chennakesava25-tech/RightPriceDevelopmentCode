package com.rightprice.auth.model;

import java.util.List;

public class FPWhatIfDetailsInsert {

	private Integer dealVersionID;
	
	private List<FPDealWhatIf> whatIfDetails;

	private List<FpWhatIfCalculation> whatIfCalDetails;
	
	private List<FPWhatIfContractTerms> whatIfContractorTerms;
	
	private List<FpDeal> whatIfRpDealData;
	
	
	private List<CostBreakup> whatIfCostBreakupData;

	public Integer getDealVersionID() {
		return dealVersionID;
	}

	public void setDealVersionID(Integer dealVersionID) {
		this.dealVersionID = dealVersionID;
	}

	public List<FPDealWhatIf> getWhatIfDetails() {
		return whatIfDetails;
	}

	public void setWhatIfDetails(List<FPDealWhatIf> whatIfDetails) {
		this.whatIfDetails = whatIfDetails;
	}

	public List<FpWhatIfCalculation> getWhatIfCalDetails() {
		return whatIfCalDetails;
	}

	public void setWhatIfCalDetails(List<FpWhatIfCalculation> whatIfCalDetails) {
		this.whatIfCalDetails = whatIfCalDetails;
	}

	public List<FPWhatIfContractTerms> getWhatIfContractorTerms() {
		return whatIfContractorTerms;
	}

	public void setWhatIfContractorTerms(List<FPWhatIfContractTerms> whatIfContractorTerms) {
		this.whatIfContractorTerms = whatIfContractorTerms;
	}

	public List<FpDeal> getWhatIfRpDealData() {
		return whatIfRpDealData;
	}

	public void setWhatIfRpDealData(List<FpDeal> whatIfRpDealData) {
		this.whatIfRpDealData = whatIfRpDealData;
	}

	public List<CostBreakup> getWhatIfCostBreakupData() {
		return whatIfCostBreakupData;
	}

	public void setWhatIfCostBreakupData(List<CostBreakup> whatIfCostBreakupData) {
		this.whatIfCostBreakupData = whatIfCostBreakupData;
	}
	
}
