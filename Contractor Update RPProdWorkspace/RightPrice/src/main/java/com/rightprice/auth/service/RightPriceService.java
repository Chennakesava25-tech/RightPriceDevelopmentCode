package com.rightprice.auth.service;


import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import com.rightprice.auth.model.AtosRateCardDetails;
import com.rightprice.auth.model.AttachmentMapper;
import com.rightprice.auth.model.AttachmentMapper2;
import com.rightprice.auth.model.AttachmentMapperPL;
import com.rightprice.auth.model.AttachmentRCMapper;
import com.rightprice.auth.model.BasicAllowance;
import com.rightprice.auth.model.BasicAllowanceSTG;
import com.rightprice.auth.model.BasicAllowanceShortTime;
import com.rightprice.auth.model.BasicSalary;
import com.rightprice.auth.model.BasicSalarySTG;
import com.rightprice.auth.model.City;
import com.rightprice.auth.model.City_STG;
import com.rightprice.auth.model.Contractor;
import com.rightprice.auth.model.ContractualTerms;
import com.rightprice.auth.model.CostBreakup;
import com.rightprice.auth.model.Country;
import com.rightprice.auth.model.Customer;
import com.rightprice.auth.model.Deal;
import com.rightprice.auth.model.DealAttachmentV2;
import com.rightprice.auth.model.DealCreationDetails_RP_V2;
import com.rightprice.auth.model.DealCrmStages;
import com.rightprice.auth.model.DealCurrencyUtilization;
import com.rightprice.auth.model.DealIndirectCostInputs;
import com.rightprice.auth.model.DealLocation;
import com.rightprice.auth.model.Deduction;
import com.rightprice.auth.model.Deduction2;
import com.rightprice.auth.model.DeductionforUpdate;
import com.rightprice.auth.model.Designation;
import com.rightprice.auth.model.EmpDetails;
import com.rightprice.auth.model.FPDCRCAndProjectDetails;
import com.rightprice.auth.model.FPDCRoleAndContractor;
import com.rightprice.auth.model.FPDealAttachment;
import com.rightprice.auth.model.FPDealRoleAndContractor;
import com.rightprice.auth.model.FPWhatIfDetailsInsert;
import com.rightprice.auth.model.FileObjectTable;
import com.rightprice.auth.model.FileUpload;
import com.rightprice.auth.model.FpDeal;
import com.rightprice.auth.model.Lob;
import com.rightprice.auth.model.MasterCampusHire;
import com.rightprice.auth.model.MasterPractice;
import com.rightprice.auth.model.MasterRate;
import com.rightprice.auth.model.MasterRateSTG;
import com.rightprice.auth.model.MasterRoles;
import com.rightprice.auth.model.MyDashboardDeal;
import com.rightprice.auth.model.MyDashboardRC;
import com.rightprice.auth.model.OffshoreMiscellaneousCost;
import com.rightprice.auth.model.Presales;
import com.rightprice.auth.model.ProjectTypeAnswers;
import com.rightprice.auth.model.RPDealManualRoleSTG;
import com.rightprice.auth.model.RPDealManualSTG;
import com.rightprice.auth.model.RPRateCardRoles;
import com.rightprice.auth.model.RateCardApprovalAudit;
import com.rightprice.auth.model.RateCardDetails;
import com.rightprice.auth.model.RateCardDetailsNew;
import com.rightprice.auth.model.RateCardManualMarginCal;
import com.rightprice.auth.model.RateCardRoleUtilization;
import com.rightprice.auth.model.RateCardRolesSTG;
import com.rightprice.auth.model.Ratecard_view;
import com.rightprice.auth.model.ResourceForcastData;
import com.rightprice.auth.model.ResourceForecast;
import com.rightprice.auth.model.RiskBasedAnswers;
import com.rightprice.auth.model.SearchCity;
import com.rightprice.auth.model.StaffingDetails;
import com.rightprice.auth.model.SubPracticeInsert;
import com.rightprice.auth.model.SyntelSFAppointment;
import com.rightprice.auth.model.UploadedDocuments;
import com.rightprice.auth.model.User;
import com.rightprice.auth.model.Vertical;
import com.rightprice.auth.model.VerticalMembers;
//import com.rightprice.auth.model.VerticalRiskManagersMembers;
import com.rightprice.auth.model.ViewCitiesData;
import com.rightprice.auth.model.VisaLabels;
import com.rightprice.auth.model.YearwiseBasicSalary;


public interface RightPriceService {

	/*ResponseEntity<Object> getCountry();*/

	ResponseEntity<Object> addCountryDetails(Country countryDetails);

	ResponseEntity<Object> getCurrency();
	
	ResponseEntity<Object> getCurrencyFP();
	
	ResponseEntity<Object> getExchangeRate();

	ResponseEntity<Object> getCountryDetail();
	
	ResponseEntity<Object> getPractice();
	ResponseEntity<Object> getSubPracticeS();

	ResponseEntity<Object> insertSubPractice(SubPracticeInsert subPracticeInsert);

	ResponseEntity<Object> getSubPractice(int pracId);

	ResponseEntity<Object> updateSubPractice(SubPracticeInsert subPracticeInsert);

	ResponseEntity<Object> viewSubPracticeData(int practiceId);

	ResponseEntity<Object> updateCountryDetails(Country[] countryDetails);

	ResponseEntity<Object> viewcontractorRoleList();
	ResponseEntity<Object> getStaffingcontractorRoleList(int rpVrsId,int tower_id);
	//Load all Lobs
		List<Lob> getLob();
		
	// Insert the Lob Details 
		ResponseEntity<Object> insertLobDetails(Lob lobDetails);
		
	// Update the Lob Details
		ResponseEntity<Object> updateLobDetails(Lob lobDetails);

		ResponseEntity<Object> viewRateCardInformation(int rcId);

		ResponseEntity<Object> viewApproverDesignation(int rcId);
		
		ResponseEntity<Object> getApproverName(int rcId,int rbutype);

		ResponseEntity<Object> getLeaderApproverName();

		ResponseEntity<Object> getDealDetails(String crmDealId, int rpVrsId);
	
	ResponseEntity<Object> getCity(int countryId);//Sneha
	ResponseEntity<Object> getCityData(int countryId);
	ResponseEntity<Object> getCityDataFP();
	ResponseEntity<Object> getBandDataFPS();
	ResponseEntity<Object> getCityCategorization();//Sneha

	ResponseEntity<Object> addCityDetails(ViewCitiesData cityDetailsAdd);//Sneha
	
	ResponseEntity<Object> updateCityDetails(ViewCitiesData cityDetailsUpdate);//Sneha

	ResponseEntity<Object> getSearchCity(SearchCity cityDetails);//Sneha

	ResponseEntity<Object> getCountry();

	ResponseEntity<Object> saveContractorRole(Contractor[] contractor);

	void handleFileUpload(FileUpload uploadFile);

	void insertuploadedDocuments(UploadedDocuments uploadedDocuments);
	
/*Developed by AG5027026------------------------------------*/
	
	ResponseEntity<Object> getCampusHireThresholdPercent();

	ResponseEntity<Object> updateThresholdPercent(List<MasterCampusHire> masterCampusHire);
	
	ResponseEntity<Object> saveContractualTerms(ContractualTerms contractualTerms);

	ResponseEntity<Object> getContractualTerms(int versionId);
	
	ResponseEntity<Object> getDesignation();

	ResponseEntity<Object> getBand();

	ResponseEntity<Object> getGrade();

	ResponseEntity<Object> addDesignation(Designation designation);
	
	ResponseEntity<Object> updateDesignation(Designation designation);
	
	ResponseEntity<Object> getVertical();
	
	ResponseEntity<Object> getEmpAdIdDetails(EmpDetails empDetails);
	
	ResponseEntity<Object> getVerticalGroupID();
	
	ResponseEntity<Object> addVertical(Vertical vertical);
	
	ResponseEntity<Object> updateVertical(Vertical vertical);
	
	ResponseEntity<Object> getRoles(int practiceId, int subPracticeId);
	
	ResponseEntity<Object> getSkills();
	
	ResponseEntity<Object> getPracticeRoles();
	
	ResponseEntity<Object> getSubPracticeRole(int practiceId);
	
	ResponseEntity<Object> getXOSkillsRoles();
	
	ResponseEntity<Object> getSyntelRoles();
	
	ResponseEntity<Object> getProficiency();
	
	ResponseEntity<Object> getXOSkillElementRoles(int skillId);
	ResponseEntity<Object> getXOSkillElementRolesFP();
	
	ResponseEntity<Object> getKnowledgeNameRole();
	
	ResponseEntity<Object> addRole(MasterRoles masterRoles);
	
	ResponseEntity<Object> updateRole(MasterRoles masterRoles);
	
	ResponseEntity<Object> getRolesDetails(int masterRoleId);
//	ResponseEntity<Object> addRate(MasterRate masterRate);
	
	/*Developed by AG5027026------------------------------------*/
	
	ResponseEntity<Object> getDeals(int customerId);

	ResponseEntity<Object> getDealsFrmCrmStages(int customerId,int dealTypeForTM);
	ResponseEntity<Object> getOldDealId(int customerIdForOlddeal,int current_DealModel);
	ResponseEntity<Object> getVersionDetails(String crmDealId);
	ResponseEntity<Object> getVersionDetailsTM(String crmDealId);
	ResponseEntity<Object> getRiskAnswers(int rpDealVersionId);

	ResponseEntity<Object> insertFpDealData(FpDeal dealData,
			MultipartFile[] fileArr, String[] fileNameArr);
	ResponseEntity<Object> insertDealData(Deal dealData, MultipartFile[] fileArr, String[] fileNameArr);
	ResponseEntity<Object> insertDlCurrUTI(DealCurrencyUtilization dcuti);
	ResponseEntity<Object> insertDlLoc(DealLocation dlLoc);
	ResponseEntity<Object> updateFpDealData(FpDeal dealData, List<RiskBasedAnswers> answersArrray, List<ProjectTypeAnswers> answersDevMainArrray, MultipartFile[] fileArr, String[] fileNameArr);
	ResponseEntity<Object> updateDealData(Deal dealData, MultipartFile[] fileArr, String[] fileNameArr);

	ResponseEntity<Object> getSummary(int rcId);

	ResponseEntity<Object> saveApprovalData(RateCardApprovalAudit rateCardApprovalAudit);

	ResponseEntity<Object> getVerticalDetails(int verticalId);

	ResponseEntity<Object> getLeadershipDetails();

	ResponseEntity<Object> updateApprovalDataForRateCard(RateCardDetails rateCardApprovalDetails);

	ResponseEntity<Object> getUploadedDoc(int versionId);

	ResponseEntity<Object> downloadFile(int objectId);

	ResponseEntity<Object> updateActiveStatus(int docId);
	
	/*Rate card details creation [PRASAD]*/
	
	/*Fixed Price Deal Creation Rate Card And Project Details [PRASAD]*/ 
	
	ResponseEntity<Object> saveFPDealCreationRCAndProjectDetails(FPDCRCAndProjectDetails fPDCRCAndProjectDetails);
	ResponseEntity<Object> saveTMDealCreationRCAndProjectDetails(FPDCRCAndProjectDetails fPDCRCAndProjectDetails);
	ResponseEntity<Object> getFPDealCreationRCAndProjectDetails(int dealVersionId);
	ResponseEntity<Object> getTMDealCreationRCAndProjectDetails(int dealVersionId);
	
	/*Fixed Price Deal Creation Rate Card And Project Details [PRASAD]*/ 
	
	/*Fixed Price Deal Creation Role Selection And Add contractor Role [PRASAD]*/
	
	ResponseEntity<Object> saveTMDCRoleSelectionAndContractorRole(FPDCRoleAndContractor fPDCRoleAndContractor);
	
	ResponseEntity<Object> getFPDCRoleSelectionAndContractorRole(int dealVersionId);
	
	/*Fixed Price Deal Creation Role Selection And Add contractor Role [PRASAD]*/

	ResponseEntity<Object> getCurrentUserDetails();
	
	
	
	ResponseEntity<Object> rateCardDetails(RateCardDetails rateCardDetails);

	ResponseEntity<Object> getRateCards(int customerVerticalMappingId);

	ResponseEntity<Object> getRateCardDetails(int rcId);
	
	ResponseEntity<Object>  getCustomerByVerticalGroupId();
	
	ResponseEntity<Object>  getCustomerForUser();

	/*Rate card details creation [PRASAD]*/
	
	/*Role Selection RITU */
	ResponseEntity<Object> getRateCard();
	
	ResponseEntity<Object> getRateCardDeal(int rpVrsId);
	
	ResponseEntity<Object> getXOSkills();
	
	ResponseEntity<Object> getXOSkillsElementMaster(int skillId);

	ResponseEntity<Object> getXOSkillFromSubPracticeId(int subPracticeId);
	ResponseEntity<Object> getXOSkillFromSubPracticeId2(int subPracticeId,int masterRoleId,int rcId_value);
	
	ResponseEntity<Object> getKnowledgeName();
	ResponseEntity<Object> getKnowledgeName_saved(int masterRoleId, int rcId_value);
	
	ResponseEntity<Object> getRoleSelectionDetails(int rcId);
	
	ResponseEntity<Object> getPersistRateCardDetails(int rcId);
	
	ResponseEntity<Object> save(ArrayList<RPRateCardRoles> rpRateCardRoles);
	
	ResponseEntity<Object> searchRateCardData(String searchRoles);
	
	ResponseEntity<Object> getVisa();

	ResponseEntity<Object> addVisaDetails(VisaLabels visaDetailsAdd);

	ResponseEntity<Object> updateVisaDetails(VisaLabels visaDetailsUpdate);

	ResponseEntity<Object> viewVisaDetails(VisaLabels visaDetailsView);

	ResponseEntity<Object> getVisaLabel(int countryId, int visaTypeId);
	/*-------visa screen sneha*/
	
	//updating the details of the RCID approvals
	ResponseEntity<Object> updateApprovalDatails(RateCardDetails rateCardApproval);

	ResponseEntity<Object> getVisaOnLoad();

	ResponseEntity<Object> viewAllowances(BasicAllowance basicAllowance);

	ResponseEntity<Object> checkAnnualAllownces(BasicAllowance basicAllowance);

	ResponseEntity<Object> saveAnnualAllowances(BasicAllowance[] basicAllowance);

	ResponseEntity<Object> updateAnnualAllowances(BasicAllowance[] basicAllowance);

	/*ResponseEntity<Object> viewMiscellaneousCost(OffshoreMiscellaneousCost miscellaneousDetailsView);*/
/*AC5029212--------------*/

	ResponseEntity<Object> getRoleUtilization(int cityId, int rateCardId);

	ResponseEntity<Object> updateRateUtilizationAndRates(List<RateCardRoleUtilization> rateCardRoleUtilization);
	
	ResponseEntity<Object> updateFpDealStaffingDetails(List<StaffingDetails> staffingDetails);

	ResponseEntity<Object> updateTMDealStaffingDetails(List<StaffingDetails> staffingDetails);
	
	ResponseEntity<Object> onViewParameterForm(Deduction deduction);

	ResponseEntity<Object> getParamList();

	ResponseEntity<Object> onAddCCPSearch(Deduction deduction);

	ResponseEntity<Object> saveCommonCostParam(Deduction[] deduction);

	ResponseEntity<Object> updateCommonCostParam(Deduction[] deduction);

	ResponseEntity<Object> onUpdateCCPSearch(int countryId, int visaTypeId, int year);
	
	/*PS5029150 - Start*/
	ResponseEntity<Object> getTeamVertical(VerticalMembers verticalDetails);

	ResponseEntity<Object> getEmpDetails(EmpDetails empDetails);

	ResponseEntity<Object> saveVerticalMembers(VerticalMembers[] verticalMember);
	
	ResponseEntity<Object> getRiskManagersEmpDetails(EmpDetails empDetails);

	//ResponseEntity<Object> saveRiskManagersVerticalMemebers(VerticalRiskManagersMembers[] RiskManagersMember);
	/*PS5029150-- END */
	
	//sneha-------------
	
	ResponseEntity<Object> viewMiscellaneousCost(City city);

	//ResponseEntity<Object> addMiscellaneousCost(OffshoreMiscellaneousCost miscellaneousCostAdd);

	ResponseEntity<Object> getUpdateMiscellaneousCost(int cityId);

	ResponseEntity<Object> updateMiscellaneousCost(OffshoreMiscellaneousCost miscellaneousCostUpdate);

	//----------sneha
	
	ResponseEntity<Object> getRateCardUtilizationCountry(int rcId);
	
	ResponseEntity<Object> getRateUtilizationCity(int rcId, int countryId);
	
	ResponseEntity<Object> getVerticalMemberData(String user);
	
	ResponseEntity<Object> getSummaryCountBasedOnVerticalId(MyDashboardRC[] rateCardCount);
/*	ResponseEntity<Object> getSummaryCountBasedOnVerticalId(RateCardDetails[] rateCardCount);
*/
	ResponseEntity<Object> getRateCardData(MyDashboardRC[] rateCardDetails);

	ResponseEntity<Object> getDealCountBasedOnVerticalId(MyDashboardDeal[] dealCount);

	ResponseEntity<Object> getCustomerBasedDealRecords(DealCrmStages[] dealCrmStages);

	ResponseEntity<Object> getDashboardDealData(MyDashboardDeal[] dealData);

	ResponseEntity<Object> getDescName(int taxnAssmId);

	ResponseEntity<Object> insertTaxnAssumtionParam(Deduction deduction);

	ResponseEntity<Object> updateTaxnAssumtionParam(Deduction deduction);

	ResponseEntity<Object> viewTaxParam(Deduction deduction);
	
	ResponseEntity<Object> getGFTMemberDetails();
	
	ResponseEntity<Object> viewCommonCostExportDetails(int countryId, int year);

	ResponseEntity<Object> getSearchExportCity(int countryId, int cityId);

	ResponseEntity<Object> getTeamVerticalExportDetails(int verticalId);

	ResponseEntity<Object> viewAllowancesExportDetails(int countryId, int visaId, int year);

//	ResponseEntity<Object> getTaxAssumptionReportExcel(int country, int deductionId);
	
	ResponseEntity<Object> getCountryCityBasedOnDealVersion(int rpDealVersionId);

	ResponseEntity<Object> getTowerDetails(int rpDealVersionId);

	ResponseEntity<Object> getSummaryCalculationData(int cityId, int towerId);

	ResponseEntity<Object> getOtherCalculationData(int cityId, int towerId);
	
	ResponseEntity<Object> getFpDealStaffingData(int cityId, int towerId, int rpVersionId);
	ResponseEntity<Object> getFpDealStaffingDataExcel(int rpVersionId,int towerId);
	ResponseEntity<Object> getTMDealStaffingData(int cityId, int towerId);
	ResponseEntity<Object> findTotalTransMonth (int towerId, int rpDealVersionId);
	ResponseEntity<Object> getRateCardName(int customerId, String deal_Id, int cityId, int countryId,int Industry_ID);

	ResponseEntity<Object> getPageTrackerData(int rcId);

	ResponseEntity<Object> viewSalary(YearwiseBasicSalary salary);

	ResponseEntity<Object> addSalarySearch(BasicSalary salary);

	ResponseEntity<Object> insertSalary(BasicSalary[] salary);
	ResponseEntity<Object> getPrevDataDeal_Version(int versionId);//, int crmDealId, int customerId
	ResponseEntity<Object> getContry_City(int versionId);
	/*ResponseEntity<Object> showRateCardTable(int rpversionId);*/

	ResponseEntity<Object> updateSalary(BasicSalary[] salary);

	ResponseEntity<Object> updateSalarySearch(BasicSalary salary);

	ResponseEntity<Object> onViewMasterRate(MasterRate masterRate);

	ResponseEntity<Object> getMasterRole();
	
	ResponseEntity<Object> uploadCountry(ArrayList<Country> country, List<Object> errorList);
	
	ResponseEntity<Object> uploadCity(ArrayList<City_STG> city);
	
	ResponseEntity<Object> uploadSalary(ArrayList<BasicSalarySTG> salary);

	ResponseEntity<Object> onMasterRateSearch(MasterRate masterRate);

	ResponseEntity<Object> onUpdateMasterRateSearch(MasterRate masterRate);

	ResponseEntity<Object> onAddMasterRate(MasterRate masterRate);

	ResponseEntity<Object> onUpdateMasterRate(MasterRate masterRate);

	ResponseEntity<Object> getOnsiteFacilityCost(int countryId, int cityId, int towerId);

	ResponseEntity<Object> saveOnsiteCostData(FPDCRCAndProjectDetails hardWareCostData);

	ResponseEntity<Object> getRelocationCostDetails(int countryId, int cityId, int towerId);

	ResponseEntity<Object> getShiftWorkingDetails(int countryId, int cityId, int towerId);

	Integer getEmpDesgIdFromEmpDesgDesc(String strDescription);

	Integer getCountryIdFromCountryName(String strCountryName);

	Integer getPracticeIdFromPracticeDesc(String strDesc);

	ResponseEntity<Object> updateStaffingContractorRole(FPDCRCAndProjectDetails fPDCRCAndProjectDetails);

	ResponseEntity<Object> uploadMannualRC(ArrayList<RateCardRolesSTG> arrLiRcRolesSTG);

	boolean CheckMasterRole(String role);

	ResponseEntity<Object> addRPAccessControlData(FPDCRCAndProjectDetails accessControl);

	ResponseEntity<Object> getGFTCustomer();

	ResponseEntity<Object> uploadMasterAllowance(ArrayList<BasicAllowanceSTG> arrLiBasicAllowanceSTG);

	ResponseEntity<Object> getDealDetailById(String dealId, int rpVersionId);
	ResponseEntity<Object> getTMFinalizeDealComment(int rpVersionId);

	ResponseEntity<Object> updateApprovalDataForDeal(Deal dealDetails);

	ResponseEntity<Object> getSyntelPremiumById(int cityId);

	ResponseEntity<Object> insertContractorRole(FPDCRCAndProjectDetails contractorRole);

	ResponseEntity<Object> getContractorDetails(int cityId, int rateCardId);
	
	ResponseEntity<Object> getCountryList();

	
	ResponseEntity<Object> viewSalaryExportDetails(int countryId, int practiceId, int year);

	
	ResponseEntity<Object> viewRPRateDetails(int countryId, int year);


	ResponseEntity<Object> getMasterRateRoles(int countryId);

	ResponseEntity<Object> getRateCardExportDetails(int customerId);

	ResponseEntity<Object> getRatecardSummaryData(int rpVrsId);	
	ResponseEntity<Object> getStaffingPercentage(int rpVrsId);
	ResponseEntity<Object> getFinal_GM_Per(int rpVrsId);
	ResponseEntity<Object> getFinal_GM_Per_Sum(int rpVrsId);
	ResponseEntity<Object> updateRP_Deal_Table(int rpVrsId, Double calc_total_GM_session, Double tCVModelValue);
	ResponseEntity<Object> getStaffingDone(int rpVrsId);
	ResponseEntity<Object> get_onloadCoun_City(int rpVrsId);
	ResponseEntity<Object> uploadMasterRPRate(ArrayList<MasterRateSTG> arrLiMasterRPRate);

	ResponseEntity<Object> getCustomers(int verticalId);

	ResponseEntity<Object> deallocateVerticalMemebers(VerticalMembers[] verticalMember);

	/*ResponseEntity<Object> getQMVertical(VerticalRiskManagersMembers verticalQMDetails);

	ResponseEntity<Object> deallocateVerticalQM(VerticalRiskManagersMembers[] verticalQM);
*/
	//ResponseEntity<Object> saveTravelRelocationData(RPDealRelocationDetails[] dealRelocationData);
	
	ResponseEntity<Object> getRateCardCalDetails(int cityId, int rateCardId);

	ResponseEntity<Object> saveCalculatedMarginData(RateCardManualMarginCal[] rateCardMarginData);

	ResponseEntity<Object> getRateCardManualUploadCountry(int rateCardId);

	ResponseEntity<Object> getRateCardUploadCity(int rcId, int countryId);

	ResponseEntity<Object> getUpdateMasterRoles();

	ResponseEntity<Object> viewMasterRolesExportDetails(int practiceId, int subpracticeId);
	
	ResponseEntity<Object> uploadManualRateCard(MultipartFile file, String fileName, InputStream uploadedInputStream, int dealAutoTowerId, int rpDealVersionId,int countryId);

	ResponseEntity<Object> onAssumptionsViewParameterForm(Deduction deduction);

	ResponseEntity<Object> onTaxViewParameterForm(Deduction deduction);

	ResponseEntity<Object> getTaxParamList();

	ResponseEntity<Object> getAssumptionsParamList();

	ResponseEntity<Object> onAddTPSearch(Deduction deduction);

	ResponseEntity<Object> onAddAPSearch(int countryId, int visaTypeId, int year);

	ResponseEntity<Object> saveTaxParam(Deduction[] deduction);

	ResponseEntity<Object> saveAssumptionsParam(Deduction[] deduction);

	ResponseEntity<Object> updateTaxParam(Deduction[] deduction);

	ResponseEntity<Object> updateAssumptionsParam(Deduction[] deduction);

	ResponseEntity<Object> onUpdateTPSearch(int countryId, int visaTypeId, int year);

	ResponseEntity<Object> onUpdateAPSearch(Deduction deduction);

	ResponseEntity<Object> getTaxExportDetails(int countryId, int year);

	ResponseEntity<Object> getAssumptionsExportDetails(int countryId, int year);
	
	ResponseEntity<Object> getrateCardId();

	ResponseEntity<Object> saveRateCardDetails(RateCardDetails rateCardDetails);
	
	ResponseEntity<Object> getSummaryOld(int rcId);
	
	ResponseEntity<Object> getYoyIncrement(int rcId);
	
	ResponseEntity<Object> uploadManualRateCardFile(MultipartFile file, String fileName,
			InputStream uploadedInputStream, int rcId);

	ResponseEntity<Object> updateRateCardStatus(int rcId);
	
	ResponseEntity<Object> getAddRoles();
	
	ResponseEntity<Object> getSummaryCountry(int rateCardId);

	ResponseEntity<Object> getVisaTypes(int countryId);
	
	ResponseEntity<Object> getCustomer();

	ResponseEntity<Object> updateCustomer(Customer customer);
	
	ResponseEntity<Object> getFxRateComment(int rcId);

	ResponseEntity<Object> getEmpName(int customerId);

	ResponseEntity<Object> getVisaDataLabel();
	
	ResponseEntity<Object> getSummaryCurrencyUti(int rcId);
	
	ResponseEntity<Object> getRateCardAssumtion(int assumtion_id);
	
	ResponseEntity<Object> getRateCardLocation(int rcId);
	
	ResponseEntity<Object> getDealApproverInfo(int rpVrsId);
	ResponseEntity<Object> getTMDealApproverInfo(int rpVrsId);
	
	ResponseEntity<Object> getDealApproverName(int rpVrsId,String rbuName);
	ResponseEntity<Object> getDealQuest();
	
	ResponseEntity<Object> getVersionData(int rpDealVersionId);
	
	ResponseEntity<Object> getDevMainAnswers(int rpDealVersionId);
	
	ResponseEntity<Object> getDealTower(int dealVersionId);
	
	ResponseEntity<Object> getFpDealRateCard(int dealTowerId, int dealVersionId);
	
	
	ResponseEntity<Object> saveFPDealRoleSelectionAndContractorRole(FPDealRoleAndContractor fPDealRoleAndContractor);
	
	
	ResponseEntity<Object> getFPDealRoleSelectionAndContractorRole(int dealVersionId,int dealAutoTowerId);
	ResponseEntity<Object> getFPDealRoleSelectionAndContractorRoleFP(int dealVersionId,int dealAutoTowerId);
	
	ResponseEntity<Object> getDataOnSearch(int rateCardID);
	
//	ResponseEntity<Object> getDataOnSearch();
	
	ResponseEntity<Object> uploadManualFile(MultipartFile file, String fileName, InputStream uploadedInputStream,
			Integer versionId, String category);
	
	ResponseEntity<Object> uploadTMFileData(MultipartFile file, String fileName, InputStream uploadedInputStream,
			Integer versionId, String category);

	ResponseEntity<Object> getAttachementData(AttachmentMapper rpDealVersionId);
	ResponseEntity<Object> getTMAttachementData(AttachmentMapper rpDealVersionId);
	
	
	ResponseEntity<Object> changeActiveStatus(int objectid);

	ResponseEntity<Object> getCountryData();

	ResponseEntity<Object> getDealRateCardName(int customerId, String deal_Id, int currencyId, int countryId, int cityCategory, int industryType);
	
	ResponseEntity<Object> uploadManualDealFile(MultipartFile file, String fileName, InputStream uploadedInputStream,
			Integer rpDealVersionId, Integer dealAutoTowerId, Integer docType, Integer noOfTowers);
	
	ResponseEntity<Object> deleteRateCard(int dltRcId);
	
	List<String> getUserRoles(String usreName);
	
	ResponseEntity<Object> getVersionAttachment(int rpDealVersionId);
	
	
	ResponseEntity<Object> changeFpActiveStatus(int dealAttachmentId);

	ResponseEntity<Object> getFpDealCostInputsDetails(int cityId, int towerId, int rpdealVersionId);

	ResponseEntity<Object> updateCostInputData(List<DealIndirectCostInputs> dealIndirectCost);

	ResponseEntity<Object> downloadFileWithFileName(int dealAttachmentId);
	
	ResponseEntity<Object> getDescription(int cityId);

	ResponseEntity<Object> updateManualDealVersionStatus(int versionId);
	
	ResponseEntity<Object> updateManualGFTDeal(int versionId);

	ResponseEntity<Object> uploadRCManualFile(MultipartFile file, String fileName, InputStream uploadedInputStream,
			Integer rcId,Integer cityId, Integer docType);

	ResponseEntity<Object> viewFPMDApproverDesignation(int rpDealVersionId);
	
	
	ResponseEntity<Object> updateFPMDApprovalDetails(FpDeal fpmdApproval);

	ResponseEntity<Object> getFPMDApproverNames(int verticalId, int rpDealVersionId,String rbuName);
	
	ResponseEntity<Object> saveFPMDApprovalData(RateCardApprovalAudit rateCardApprovalAudit);

	ResponseEntity<Object> updateApprovalDataForFPMD(FpDeal fpdealmanualApprovalDetails);
	
	ResponseEntity<Object> getDealData();

	ResponseEntity<Object> getResourceData(ResourceForcastData forcastData);
	
	ResponseEntity<Object> getLobData();

	ResponseEntity<Object> getRolesData();
	
	ResponseEntity<Object>  getOldResourceData(String cRMDealId);
	
	ResponseEntity<Object> saveResourceData(ResourceForecast forcastData,String lob,int flag);
	
	ResponseEntity<Object> getFPCostCalCulationData(int towerId, int rpdealVersionId);

	
	ResponseEntity<Object> getCostBreakupData(String cRMDealId,int dealVersionId);
	
	ResponseEntity<Object> getWhatIfData(int rpVrsId);
	
	ResponseEntity<Object> getWhatIfEffortData(int rpVrsId);

	ResponseEntity<Object> updateFpDealWhatIfDetails(FPWhatIfDetailsInsert fpWhatIfDetailsInsert);
	

	ResponseEntity<Object> updateFPApproverDetails(FpDeal fpData);

	ResponseEntity<Object> updateFPApproverStatus(FpDeal[] fpDealApprove);
	
	ResponseEntity<Object> getDealDataOnSearch(String crmDealID);
	
//	ResponseEntity<Object> getDealDataOnSearch(int dealVresionID);

	ResponseEntity<Object> deleteDealVersion(int dltDealVersionId);

	ResponseEntity<Object> getFpDealTowerData();
	
	ResponseEntity<Object> updateBillingSchedule(CostBreakup costBreakup, int deald, int dealVersionId);
	
	ResponseEntity<Object> getWhatIfCalculationData(int rpVrsId);
	
	ResponseEntity<Object> getFpDealRoleDetails(int dealVersionId);
	
	ResponseEntity<Object> getWhatIfContractData(int rpVrsId);

	ResponseEntity<Object> getRateCardManualAttachment(int cityId, int rcId);

	ResponseEntity<Object> changeFileActiveStatus(int attachmentId);

	ResponseEntity<Object> getApprovalMatrixWhatIf(int rpVrsId);
	
	ResponseEntity<Object> getFpVersionsReadyToSubmit(String crmDealId);
	
	ResponseEntity<Object> getRCCityDetails(int cityId, int rateCardId);
	
	ResponseEntity<Object> getYearCount(int rpVrsId);

	ResponseEntity<Object> updateFPApprovalRFPDetails(FpDeal[] fpData);
	
	ResponseEntity<Object> saveMasterAnnualAllowances(BasicAllowanceShortTime[] basicShortAllowance);

	ResponseEntity<Object> updateShortTermAllowances(BasicAllowanceShortTime[] basicShortTermAllowance);
	
	ResponseEntity<Object> getFpRfpRfiFinalVersion(String crmDealId);

	ResponseEntity<Object> updateFPRpiRpfFinalApprovalStatus(FpDeal fpData);

	ResponseEntity<Object> updateCostBreakupData(List<CostBreakup> costBreakup);

	ResponseEntity<Object> getDealPricingData(int versionId);
	
	ResponseEntity<Object> getWhatIfDataExcel(int rpVrsId);
	
	ResponseEntity<Object> getFPCostCalCulationOrderedData(int towerId, int rpDealVersionId);

	ResponseEntity<Object> getFpVersionsWhatIfApproval(String crmDealId);
	
	ResponseEntity<Object> getFpToExchangeRates(int rpVrsId,int currencyId);

	ResponseEntity<Object> getRateCardsBasedOnIndustry(int customerVerticalId, int industry);

	ResponseEntity<Object> getOldDealDetails(int customerId,String startDate);

	ResponseEntity<Object> uploadfpDealStaffingFile(MultipartFile file, String fileName,
			InputStream uploadedInputStream, Integer rpDealVersionId, Integer dealAutoTowerId);

	ResponseEntity<Object> uploadEstimationDealFile(MultipartFile file, String fileName, InputStream uploadedInputStream,
			Integer versionId, String comment,String category,String docType);
	
	ResponseEntity<Object> getUploadedEADoc(int versionId);
	
	ResponseEntity<Object> deleteEADoc(int attachmentId);

	ResponseEntity<Object> getCountryByManualFlag(int manualCountryId);
	
	ResponseEntity<Object> getAttachment(int rpDealVersionId,int cityId);
	
	ResponseEntity<Object> uploadTMDealStaffingFile(MultipartFile file, String fileName,
			InputStream uploadedInputStream, Integer rpDealVersionId, Integer dealAutoTowerId);
	
	ResponseEntity<Object> getRiskManagersMemberDetail(String user);

	ResponseEntity<Object> getDelicgateUserAccess(String user);

	ResponseEntity<Object> getVerticalApproverData();

	ResponseEntity<Object> getDUHCustomerVerticalMapping(Vertical[] vertical);

	ResponseEntity<Object> getVerticalByCustId(int customerId);
	
	ResponseEntity<Object> getCrmDealDetails(int crmDealId);
	
	ResponseEntity<Object> saveTcvTMDeal(int tcv, int rpVrsId);
	
	ResponseEntity<Object> getStaffingSubContractorPricing(int rpVrsId);
	
	ResponseEntity<Object> getOldDealDetailsPricing(int oldDealId);

	ResponseEntity<Object> getDealsForCust(int customerId,int dealTypeForFM);

	ResponseEntity<Object> getDealStatusData(MyDashboardDeal[] dealData);
	
	ResponseEntity<Object> viewRolesExportDetails(int towerId, int dealVersionId);

	ResponseEntity<Object> updateRecycleStatus(int rcId);

	ResponseEntity<Object> updateRateUtilizationRates(List<RateCardRoleUtilization> rateCardRoleUtilization);

	ResponseEntity<Object> getRateCardInfo(int customerVerticalMappingId);
	
	ResponseEntity<Object> uploadManualRateCard_GFT(MultipartFile file, String fileName,
			InputStream uploadedInputStream, int dealAutoTowerId, int rpDealVersionId,int countryId);
	
	ResponseEntity<Object> getRateGFTCountry(int rcId);
	
	ResponseEntity<Object> getFpDealRateCard(int dealVersionId);

	ResponseEntity<Object> getUserName(String lanId);

	ResponseEntity<Object> getApproverConfig(int rcId);
	
	ResponseEntity<Object> getFpDealStaffingDataWhatIf(int rpDealVersionId);
	
	ResponseEntity<Object> getAtosRcData();
	
	ResponseEntity<Object> addPracticeDetails(MasterPractice practiceDetails);

	ResponseEntity<Object> updatePracticeDetails(MasterPractice practiceDetails);
	
	ResponseEntity<Object> getActiveRCData();
	
	/*ResponseEntity<Object> getGFTWeeklyData();*/
	
	ResponseEntity<Object>	getRCComment(int rcId);
	
	ResponseEntity<Object>	saveComment(int rcId,String comment);
	
	//ResponseEntity<Object>	getFileredAtosRcData(AtosRateCardDetails rateCardDetails,int country);

	ResponseEntity<Object> getFileredAtosRcData(int country, String rcStartDate, String rcEndDate, int currentApStatus);

	ResponseEntity<Object> getCommentData();
	
	ResponseEntity<Object> getStatusData();

	ResponseEntity<Object> getStatusData(String startdate, String enddate, String status, int vertical);

	ResponseEntity<Object> getUpdatedApprovalMatrix(int rpVrsId);
	
/*	ResponseEntity<Object> getPreSalesData();*/

	ResponseEntity<Object> setSubmitEnabled();

	ResponseEntity<Object> setSubmitDisabled();
	
/*	ResponseEntity<Object> getPreSalesDataOnId(int salesId);
	
	ResponseEntity<Object> getPreSalesApprovedData(int salesId);

	ResponseEntity<Object> getPreSalesApprovalData(int salesId);
	
	ResponseEntity<Object> getOpportunityDetails();

	ResponseEntity<Object> putOpportunityDealData(int dealId);

	ResponseEntity<Object> getCurrencyCode();

	ResponseEntity<Object> saveBudgetDetails(Presales preSalesDetails);

	ResponseEntity<Object> submitBudgetDetails(Presales preSalesDetails);

	ResponseEntity<Object> searchDataOnOid(Presales preSalesDetails);

	ResponseEntity<Object> mapDataOnId(Presales preSalesDetails);

	ResponseEntity<Object> getPresalesOpportunityDetails();
	*/
	List<String> getUserNameByLanId(String userId);
	
	ResponseEntity<Object> getSyntelUserId(String userId);

	List<String> getUserRoleData(String usreName);
	
	ResponseEntity<Object> uploadMasterFile(MultipartFile file, String fileName, InputStream uploadedInputStream,
			int rpDealVersionId);

	ResponseEntity<Object> getMasterAttachement(AttachmentMapper rpDealVersionId);
	
	ResponseEntity<Object> deleteMasterRCAttachment(int objectid);
	
	ResponseEntity<Object> uploadAutomaticRURFile(MultipartFile file, String fileName, InputStream uploadedInputStream,
			int rpDealVersionId);
	
	ResponseEntity<Object> getRCRoleUtilizationAttachement(AttachmentMapper rpDealVersionId);
	
	ResponseEntity<Object> getFpDealCostInputsDetailsUpdate(int towerId, int costType);
	/*
	ResponseEntity<Object> getFpDealCostInputsDetailsArray(int cityId, int rpdealVersionId);
	
	ResponseEntity<Object> getRoleSelectionExcelDetails(int rcId);*/

	ResponseEntity<Object> getRcLocationdetails(int rcId);

/*	ResponseEntity<Object> saveSFAppointmentData(SyntelSFAppointment syntelAppointmentDetails);
*/	
	ResponseEntity<Object> getDisabledCountryData(int countryId);
	

	ResponseEntity<Object> getFPDealSFInfo(String dealId);

	ResponseEntity<Object> CheckUserAccess(Integer accId);

	ResponseEntity<Object> checkCustomerMappingInFin(String crmdealId);
	
	ResponseEntity<Object> getDealDetailsForTM_Selection(String crmdealId);

	ResponseEntity<Object> getAppCodeData();

	ResponseEntity<Object> getDistDedCatId();

	ResponseEntity<Object> getMasterDeductionDataForm(Deduction2 deduction);
	
	ResponseEntity<Object> getDealDetailsForGFT();

	ResponseEntity<Object> updateAssumptionsParamValue(DeductionforUpdate[] deduction);



	ResponseEntity<Object> getviewdealdetails(int customerId);

	ResponseEntity<Object> getviewrcdetails(int customerId);
	
    ResponseEntity<Object> getRateCardDetailsGFT(int rcId);
 ResponseEntity<Object> getRateCardsNew(int customerVerticalMappingId);

ResponseEntity<Object> uploadManualFileNew(MultipartFile file, String
fileName, InputStream uploadedInputStream, Integer rcId,Integer Id);
/* ResponseEntity<Object> getFileData(String rcId); */

 ResponseEntity<Object> getMasterAttachementRC(AttachmentRCMapper rcId);
 ResponseEntity<Object> downloadFileWithFileNameRC(int Id);
 ResponseEntity<Object> deleteRCFile(int attachmentId);

ResponseEntity<Object> saveRateCardDetailsNew(RateCardDetailsNew rateCardDetailsNew);


/*Deal*/

ResponseEntity<Object> getDealDetailss(String crmDealId);

ResponseEntity<Object> getDealId(int customerId);





ResponseEntity<Object> getDealDetailsFor_Selection(String crmDealId);

ResponseEntity<Object> saveDealData(DealCreationDetails_RP_V2 dealDetails);

ResponseEntity<Object> uploadDealsFile(MultipartFile file, String filename, String crmDealId,
		InputStream uploadedInputStream);

ResponseEntity<Object> getFileData(String crmDealId);

ResponseEntity<Object> downloadDealFileWithFileName(int dealAttachmentId);

ResponseEntity<Object> deleteDealFile(int dealAttachmentId);


ResponseEntity<Object> getFileAttachementData(AttachmentMapper2 crmDealId);

ResponseEntity<Object> getAllDealsFrmCrmStages(int customerId, int dealTypeId);

ResponseEntity<Object> getAllDealsForCust(int customerId, int dealTypeId);

ResponseEntity<Object> getDataOnSearchview(int rcId);

ResponseEntity<Object> getDealDataOnSearchview(String dealId);

ResponseEntity<Object> getEmpRoleDetails(EmpDetails empDetails);

ResponseEntity<Object> addRProleData(FPDCRCAndProjectDetails role);

ResponseEntity<Object> downloadVideos(int type);

ResponseEntity<Object> getXOSkillFromMasterRoleId(int masterRoleId);

ResponseEntity<Object> getCurrentCustomerUserDetailskpo(int industry, String vertical);


ResponseEntity<Object> getCurrentCustomerUserDetails(int verticalId,int rbutype);
  
//manglam updated
ResponseEntity<Object> getWonDealDetailsForGFT();
ResponseEntity<Object> getPageTrckrData(int rcId);

ResponseEntity<Object> getPLAttachementData(AttachmentMapperPL rpDealVersionId);
ResponseEntity<Object> uploadPLFileData(MultipartFile file, String fileName, InputStream uploadedInputStream, Integer versionId);

ResponseEntity<Object> getCurrentCustomerUserDetailsJVkpo(int industry, int verticalId);




}
