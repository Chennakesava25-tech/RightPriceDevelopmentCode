package com.rightprice.auth.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import org.opensaml.saml2.core.Assertion;
import org.opensaml.saml2.core.Attribute;
import org.opensaml.saml2.core.AttributeStatement;
import org.opensaml.xml.XMLObject;
import org.opensaml.xml.schema.impl.XSAnyImpl;
import org.opensaml.xml.schema.impl.XSStringImpl;
//import org.hsqldb.error.Error;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.saml.SAMLCredential;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.rightprice.auth.model.AccessControl;
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
import com.rightprice.auth.model.ContractorRole;
import com.rightprice.auth.model.ContractualTerms;
import com.rightprice.auth.model.CostBreakup;
import com.rightprice.auth.model.Country;
import com.rightprice.auth.model.Customer;
import com.rightprice.auth.model.Deal;
import com.rightprice.auth.model.DealAttachmentV2;
import com.rightprice.auth.model.DealContraactorRole;
import com.rightprice.auth.model.DealCreationDetails_RP_V2;
import com.rightprice.auth.model.DealCrmStages;
import com.rightprice.auth.model.DealCurrencyUtilization;
import com.rightprice.auth.model.DealFixLocation;
import com.rightprice.auth.model.DealIndirectCostInputs;
import com.rightprice.auth.model.DealLocation;
import com.rightprice.auth.model.DealRateCard;
import com.rightprice.auth.model.DealRoles;
import com.rightprice.auth.model.DealTower;
import com.rightprice.auth.model.Deduction;
import com.rightprice.auth.model.Deduction2;
import com.rightprice.auth.model.DeductionforUpdate;
import com.rightprice.auth.model.Designation;
import com.rightprice.auth.model.EmpDetails;
import com.rightprice.auth.model.EstimationAttachment;
import com.rightprice.auth.model.FPDCRCAndProjectDetails;
import com.rightprice.auth.model.FPDCRoleAndContractor;
import com.rightprice.auth.model.FPDealAttachment;
import com.rightprice.auth.model.FPDealRatecard;
import com.rightprice.auth.model.FPDealRoleAndContractor;
import com.rightprice.auth.model.FPDealWhatIf;
import com.rightprice.auth.model.FPManualDealAttachment;
import com.rightprice.auth.model.FPWhatIfContractTerms;
import com.rightprice.auth.model.FPWhatIfDetailsInsert;
import com.rightprice.auth.model.FileObjectTable;
import com.rightprice.auth.model.FileUpload;
import com.rightprice.auth.model.FpDeal;
import com.rightprice.auth.model.FpDealRole;
import com.rightprice.auth.model.FpWhatIfCalculation;
import com.rightprice.auth.model.Lob;
import com.rightprice.auth.model.MasterCampusHire;
import com.rightprice.auth.model.MasterPractice;
import com.rightprice.auth.model.MasterRate;
import com.rightprice.auth.model.MasterRateSTG;
import com.rightprice.auth.model.MasterRoles;
import com.rightprice.auth.model.MyDashboardDeal;
import com.rightprice.auth.model.MyDashboardRC;
import com.rightprice.auth.model.OffshoreMiscellaneousCost;
import com.rightprice.auth.model.PLAttachment;
import com.rightprice.auth.model.Presales;
import com.rightprice.auth.model.ProjectTypeAnswers;
import com.rightprice.auth.model.RPDealHardwareSoftwareCost;
import com.rightprice.auth.model.RPDealRelocationDetails;
import com.rightprice.auth.model.RPRateCardRoles;
import com.rightprice.auth.model.RateCardApprovalAudit;
import com.rightprice.auth.model.RateCardAttachment;
import com.rightprice.auth.model.RateCardCurrencyUtilization;
import com.rightprice.auth.model.RateCardDetails;
import com.rightprice.auth.model.RateCardDetailsNew;
import com.rightprice.auth.model.RateCardLocation;
import com.rightprice.auth.model.RateCardManualMarginCal;
import com.rightprice.auth.model.RateCardRoleUtilization;
import com.rightprice.auth.model.RateCardRolesSTG;
import com.rightprice.auth.model.ResourceForcastData;
import com.rightprice.auth.model.ResourceForecast;
import com.rightprice.auth.model.RiskBasedAnswers;
import com.rightprice.auth.model.RpRole;
import com.rightprice.auth.model.SearchCity;
import com.rightprice.auth.model.StaffingDealContraactorRole;
import com.rightprice.auth.model.StaffingDetails;
import com.rightprice.auth.model.SubPracticeInsert;
import com.rightprice.auth.model.SyntelSFAppointment;
import com.rightprice.auth.model.UploadedDocuments;
import com.rightprice.auth.model.Vertical;
import com.rightprice.auth.model.VerticalMembers;
//import com.rightprice.auth.model.VerticalRiskManagersMembers;
import com.rightprice.auth.model.ViewCitiesData;
import com.rightprice.auth.model.VisaLabels;
import com.rightprice.auth.model.YOYIncrementPercent;
import com.rightprice.auth.model.YearwiseBasicSalary;
import com.rightprice.auth.repository.RightPriceRepository;
import com.rightprice.auth.util.AppLoger;
import com.rightprice.auth.util.AuditTrails;
import com.rightprice.auth.util.FileUploadErrors;

@Service
public class RightPriceServiceImpl implements RightPriceService {

	
	
	@Autowired
	private RightPriceRepository rightPriceRepository;
	
	@Autowired
	private AuditTrails auditTrails;

	@Override
	public ResponseEntity<Object> getCountry() {
		return rightPriceRepository.getCountry();
	}



	@Override
	public ResponseEntity<Object> addCountryDetails(Country countryDetails) {
		AppLoger.APPLOGGER.info("in service");
		countryDetails.setIsActive(1);
		String syntelLanId=rightPriceRepository.getSyntelId(this.auditTrails.getCurrentUser());
		if(syntelLanId.isEmpty() || syntelLanId==null)
		{
			countryDetails.setCreatedBy(this.auditTrails.getCurrentUser());
			countryDetails.setCreatedDate(this.auditTrails.getCurrentTimeStamp());
			countryDetails.setUpdatedBy(this.auditTrails.getCurrentUser());
			countryDetails.setUpdatedDate(this.auditTrails.getCurrentTimeStamp());
		}else
		{
			countryDetails.setCreatedBy(syntelLanId);
			countryDetails.setCreatedDate(this.auditTrails.getCurrentTimeStamp());
			countryDetails.setUpdatedBy(syntelLanId);
			countryDetails.setUpdatedDate(this.auditTrails.getCurrentTimeStamp());			
		}
		return rightPriceRepository.addCountryDetails(countryDetails);
	}

	@Override
	public ResponseEntity<Object> getCurrency() {
		return rightPriceRepository.getCurrency();
	}

	@Override
	public ResponseEntity<Object> getCurrencyFP() {
		return rightPriceRepository.getCurrencyFP();
	}
	
	@Override
	public ResponseEntity<Object> getExchangeRate() {
		return rightPriceRepository.getExchangeRate();
	}

	@Override
	public ResponseEntity<Object> getCountryDetail() {
		return rightPriceRepository.getCountryDetail();
	}
	
	@Override
	public ResponseEntity<Object> getPractice() {
		return rightPriceRepository.getPractice();
	}
	
	@Override
	public ResponseEntity<Object> getSubPracticeS() {
		return rightPriceRepository.getSubPracticeS();
	}
	
	@Override
	public ResponseEntity<Object> insertSubPractice(SubPracticeInsert subPracticeInsert) {
		// TODO Auto-generated method stub
		AppLoger.APPLOGGER.info("in service:insertSubPractice");
		subPracticeInsert.setActiveStatus(1);
		String syntelLanId=rightPriceRepository.getSyntelId(this.auditTrails.getCurrentUser());
		if(syntelLanId.isEmpty() || syntelLanId==null)
		{
			subPracticeInsert.setCreatedBy(this.auditTrails.getCurrentUser());
			subPracticeInsert.setCreatedOn(this.auditTrails.getCurrentTimeStamp());
			subPracticeInsert.setLastUpdatedBy(this.auditTrails.getCurrentUser());
			subPracticeInsert.setLastUpdatedOn(this.auditTrails.getCurrentTimeStamp());
		}
		else
		{
			subPracticeInsert.setCreatedBy(syntelLanId);
			subPracticeInsert.setCreatedOn(this.auditTrails.getCurrentTimeStamp());
			subPracticeInsert.setLastUpdatedBy(syntelLanId);
			subPracticeInsert.setLastUpdatedOn(this.auditTrails.getCurrentTimeStamp());	
		}
		return rightPriceRepository.insertSubPractice(subPracticeInsert);
	}


	@Override
	public ResponseEntity<Object> getSubPractice(int pracId) {
		// TODO Auto-generated method stub
		return rightPriceRepository.getSubPractice(pracId);
	}


	@Override
	public ResponseEntity<Object> updateSubPractice(SubPracticeInsert subPracticeInsert) {
		// TODO Auto-generated method stub
		AppLoger.APPLOGGER.info("in service :updateSubPractice " );
		return rightPriceRepository.updateSubPractice(subPracticeInsert);
	}


	@Override
	public ResponseEntity<Object> viewSubPracticeData(int practiceId) {
		// TODO Auto-generated method stub
		return rightPriceRepository.viewSubPracticeData(practiceId);
	}


	@Override
	public ResponseEntity<Object> updateCountryDetails(Country[] countryDetails) {
		AppLoger.APPLOGGER.info("inside service");
		try
		{
			for (Country countryDetail : countryDetails) {
//				countryDetail.setIsActive(1);
				String syntelLanId=rightPriceRepository.getSyntelId(this.auditTrails.getCurrentUser());
				if(syntelLanId.isEmpty() || syntelLanId==null)
				{	
					countryDetail.setUpdatedBy(this.auditTrails.getCurrentUser());
					countryDetail.setUpdatedDate(this.auditTrails.getCurrentTimeStamp());
				}else
				{
					countryDetail.setUpdatedBy(syntelLanId);
					countryDetail.setUpdatedDate(this.auditTrails.getCurrentTimeStamp());					
				}
			}
			
			return rightPriceRepository.updateCountryDetails(countryDetails);
		}
		catch(Exception e){
			throw e;
		}
	}

	@Override
	public ResponseEntity<Object> viewcontractorRoleList() {
		// TODO Auto-generated method stub
		return rightPriceRepository.viewcontractorRoleList();
	}
	@Override
	public ResponseEntity<Object> getStaffingcontractorRoleList(int rpVrsId, int tower_id) {

		// TODO Auto-generated method stub
		return rightPriceRepository.getStaffingcontractorRoleList(rpVrsId,tower_id);
	}
	
	@Override
	public ResponseEntity<Object> insertLobDetails(Lob lobDetails) {
		lobDetails.setIsActive(1);
		String syntelLanId=rightPriceRepository.getSyntelId(this.auditTrails.getCurrentUser());
		if(syntelLanId.isEmpty() || syntelLanId==null)
		{	
			lobDetails.setCreatedBy(this.auditTrails.getCurrentUser());
			lobDetails.setCreatedOn(this.auditTrails.getCurrentTimeStamp());
			lobDetails.setUpdatedBy(this.auditTrails.getCurrentUser());
			lobDetails.setUpdatedOn(this.auditTrails.getCurrentTimeStamp());
		}
		else
		{
			lobDetails.setCreatedBy(syntelLanId);
			lobDetails.setCreatedOn(this.auditTrails.getCurrentTimeStamp());
			lobDetails.setUpdatedBy(syntelLanId);
			lobDetails.setUpdatedOn(this.auditTrails.getCurrentTimeStamp());			
		}
		return rightPriceRepository.insertLobDetails(lobDetails);
	}

	@Override
	public ResponseEntity<Object> updateLobDetails(Lob lobDetails) {
		AppLoger.APPLOGGER.info("inside the service impl");
		AppLoger.APPLOGGER.info(lobDetails.getIsActive());
		String syntelLanId=rightPriceRepository.getSyntelId(this.auditTrails.getCurrentUser());
		if(syntelLanId.isEmpty() || syntelLanId==null)
		{	
			lobDetails.setUpdatedBy(this.auditTrails.getCurrentUser());
			lobDetails.setUpdatedOn(this.auditTrails.getCurrentTimeStamp());
		}
		else
		{
		lobDetails.setUpdatedBy(syntelLanId);
		lobDetails.setUpdatedOn(this.auditTrails.getCurrentTimeStamp());
		}
		return rightPriceRepository.updateLobDetails(lobDetails);
	}

	@Override
	public ResponseEntity<Object> viewRateCardInformation(int rcId) {
		return rightPriceRepository.viewRateCardInformation(rcId);
	}

	@Override
	public ResponseEntity<Object> viewApproverDesignation(int rcId) {
		return rightPriceRepository.getApproverDesignation(rcId);
	}
	

	/*@Override
	public ResponseEntity<Object> getApproverName(int rcId) {
		return rightPriceRepository.getApproverName(rcId);
	}*/

	@Override
	public ResponseEntity<Object> getLeaderApproverName() {
		
		return rightPriceRepository.getLeaderApproverName();
	}

	@Override
	public ResponseEntity<Object> getDealDetails(String crmDealId, int rpVrsId) {
		System.out.println("198 198 198+++++++++++++++++++++++++++++++===++++++++++++++++++++++");
		return rightPriceRepository.getDealDetails(crmDealId,rpVrsId);
	}
	
	

/*---------sneha start 2*/
	
	
	@Override
	public ResponseEntity<Object> getCity(int countryId) {
		return rightPriceRepository.getCity(countryId);
	}
	@Override
	public ResponseEntity<Object> getCityData(int countryId) {
		return rightPriceRepository.getCityData(countryId);
	}
	
	@Override
	public ResponseEntity<Object> getCityDataFP() {
		return rightPriceRepository.getCityDataFP();
	}
	
	@Override
	public ResponseEntity<Object> getBandDataFPS() {
		return rightPriceRepository.getBandDataFPS();
	}

	@Override
	public ResponseEntity<Object> getSearchCity(SearchCity cityDetails) {
		return rightPriceRepository.getSearchCity(cityDetails);
	}

	@Override
	public ResponseEntity<Object> getCityCategorization() {
		return rightPriceRepository.getCityCategorization();
	}


	@Override
	public ResponseEntity<Object> addCityDetails(ViewCitiesData cityDetailsAdd) {
		AppLoger.APPLOGGER.info("in service");
		cityDetailsAdd.setIsActive(1);
		String syntelLanId=rightPriceRepository.getSyntelId(this.auditTrails.getCurrentUser());
		if(syntelLanId.isEmpty() || syntelLanId==null)
		{	
			cityDetailsAdd.setCreatedBy(this.auditTrails.getCurrentUser());
			cityDetailsAdd.setCreatedDate(this.auditTrails.getCurrentTimeStamp());
			cityDetailsAdd.setUpdatedBy(this.auditTrails.getCurrentUser());
			cityDetailsAdd.setUpdatedDate(this.auditTrails.getCurrentTimeStamp());
		}
		else
		{
		cityDetailsAdd.setCreatedBy(syntelLanId);
		cityDetailsAdd.setCreatedDate(this.auditTrails.getCurrentTimeStamp());
		cityDetailsAdd.setUpdatedBy(syntelLanId);
		cityDetailsAdd.setUpdatedDate(this.auditTrails.getCurrentTimeStamp());
		}
		return rightPriceRepository.addCityDetails(cityDetailsAdd);
	}


	@Override
	public ResponseEntity<Object> updateCityDetails(ViewCitiesData cityDetailsUpdate) {
		AppLoger.APPLOGGER.info("inside service");
		AppLoger.APPLOGGER.info("getIsActive"+cityDetailsUpdate.getIsActive());
		if(cityDetailsUpdate.getIsActive()!=1)
		{
			cityDetailsUpdate.setIsActive(0);
		}
		else{
			cityDetailsUpdate.setIsActive(1);
		}
		/*cityDetailsUpdate.setIsActive(1);*/
		String syntelLanId=rightPriceRepository.getSyntelId(this.auditTrails.getCurrentUser());
		if(syntelLanId.isEmpty() || syntelLanId==null)
		{	
			cityDetailsUpdate.setUpdatedBy(this.auditTrails.getCurrentUser());
			cityDetailsUpdate.setUpdatedDate(this.auditTrails.getCurrentTimeStamp());
		}
		else
		{
		cityDetailsUpdate.setUpdatedBy(syntelLanId);
		cityDetailsUpdate.setUpdatedDate(this.auditTrails.getCurrentTimeStamp());
		}
		return rightPriceRepository.updateCityDetails(cityDetailsUpdate);
	}

	/*---------sneha start 2*/
	@Override
	public ResponseEntity<Object> saveContractorRole(Contractor[] contractor) {
		// TODO Auto-generated method stub
		AppLoger.APPLOGGER.info("inside RightPriceServiceImpl : saveData");
		if(contractor.length!=0){
		for (Contractor saveContractorDetails : contractor) {
			saveContractorDetails.setIsActive("1");
			String syntelLanId=rightPriceRepository.getSyntelId(this.auditTrails.getCurrentUser());
			if(syntelLanId.isEmpty() || syntelLanId==null)
			{	
				saveContractorDetails.setCreatedBy(this.auditTrails.getCurrentUser());
				saveContractorDetails.setCreatedOn(this.auditTrails.getCurrentTimeStamp());
				saveContractorDetails.setLastUpdatedBy(this.auditTrails.getCurrentUser());
				saveContractorDetails.setLastUpdatedOn(this.auditTrails.getCurrentTimeStamp());
				saveContractorDetails.setRP_Deal_Version_Id(10);
			}
			else
			{
			saveContractorDetails.setCreatedBy(syntelLanId);
			saveContractorDetails.setCreatedOn(this.auditTrails.getCurrentTimeStamp());
			saveContractorDetails.setLastUpdatedBy(syntelLanId);
			saveContractorDetails.setLastUpdatedOn(this.auditTrails.getCurrentTimeStamp());
			saveContractorDetails.setRP_Deal_Version_Id(10);
			}
		}
		}
		return rightPriceRepository.saveContractorRole(contractor);
	}


	@Override
	public void handleFileUpload(FileUpload uploadFile) {
		String syntelLanId=rightPriceRepository.getSyntelId(this.auditTrails.getCurrentUser());
		if(syntelLanId.isEmpty() || syntelLanId==null)
		{	
			uploadFile.setCreatedBy(this.auditTrails.getCurrentUser());
			uploadFile.setCreatedOn(this.auditTrails.getCurrentTimeStamp());
			uploadFile.setLastUpdatedBy(this.auditTrails.getCurrentUser());
			uploadFile.setLastUpdatedOn(this.auditTrails.getCurrentTimeStamp());
		}
		else
		{
		uploadFile.setCreatedBy(syntelLanId);
		uploadFile.setCreatedOn(this.auditTrails.getCurrentTimeStamp());
		uploadFile.setLastUpdatedBy(syntelLanId);
		uploadFile.setLastUpdatedOn(this.auditTrails.getCurrentTimeStamp());
		}
		rightPriceRepository.handleFileUpload(uploadFile);		
	}


	@Override
	public void insertuploadedDocuments(UploadedDocuments uploadedDocuments) {
		String syntelLanId=rightPriceRepository.getSyntelId(this.auditTrails.getCurrentUser());
		if(syntelLanId.isEmpty() || syntelLanId==null)
		{	
			uploadedDocuments.setCreatedBy(this.auditTrails.getCurrentUser());
			uploadedDocuments.setCreatedOn(this.auditTrails.getCurrentTimeStamp());
			uploadedDocuments.setLastUpdatedBy(this.auditTrails.getCurrentUser());
			uploadedDocuments.setLastUpdatedOn(this.auditTrails.getCurrentTimeStamp());
		}
		else
		{
		uploadedDocuments.setCreatedBy(syntelLanId);
		uploadedDocuments.setCreatedOn(this.auditTrails.getCurrentTimeStamp());
		uploadedDocuments.setLastUpdatedBy(syntelLanId);
		uploadedDocuments.setLastUpdatedOn(this.auditTrails.getCurrentTimeStamp());
		}
		rightPriceRepository.insertuploadedDocuments(uploadedDocuments);
		
	}
	
	/*Developed by AG5027026------------------------------------*/
	@Override
	public ResponseEntity<Object> getCampusHireThresholdPercent() {
		return rightPriceRepository.getCampusHireThresholdPercent();
	}

	@Override
	public ResponseEntity<Object> updateThresholdPercent(List<MasterCampusHire> masterCampusHire) {
		for(int i=0;i< masterCampusHire.size();i++){
		masterCampusHire.get(i).setIsActive(1);
		String syntelLanId=rightPriceRepository.getSyntelId(this.auditTrails.getCurrentUser());
		if(syntelLanId.isEmpty() || syntelLanId==null)
		{	
			masterCampusHire.get(i).setUpdatedBy(this.auditTrails.getCurrentUser());
			masterCampusHire.get(i).setUpdatedDate(this.auditTrails.getCurrentTimeStamp());
		}
		else
		{
		masterCampusHire.get(i).setUpdatedBy(syntelLanId);
		masterCampusHire.get(i).setUpdatedDate(this.auditTrails.getCurrentTimeStamp());
		}
		}
		return rightPriceRepository.updateThresholdPercent(masterCampusHire);
	}
	
	@Override
	public ResponseEntity<Object> getContractualTerms(int versionId) {
		return rightPriceRepository.getContractualTerms(versionId);
	}
	
	@Override
	public ResponseEntity<Object> saveContractualTerms(ContractualTerms contractualTerms) {
		contractualTerms.setIsActive(1);
		String syntelLanId=rightPriceRepository.getSyntelId(this.auditTrails.getCurrentUser());
		if(syntelLanId.isEmpty() || syntelLanId==null)
		{	
			contractualTerms.setCreatedBy(this.auditTrails.getCurrentUser());
			contractualTerms.setCreatedOn(this.auditTrails.getCurrentTimeStamp());
			contractualTerms.setUpdatedBy(this.auditTrails.getCurrentUser());
			contractualTerms.setUpdatedOn(this.auditTrails.getCurrentTimeStamp());
		}
		else
		{
		contractualTerms.setCreatedBy(syntelLanId);
		contractualTerms.setCreatedOn(this.auditTrails.getCurrentTimeStamp());
		contractualTerms.setUpdatedBy(syntelLanId);
		contractualTerms.setUpdatedOn(this.auditTrails.getCurrentTimeStamp());
		}
		return rightPriceRepository.saveContractualTerms(contractualTerms);
	}
	
	@Override
	public ResponseEntity<Object> getDesignation() {
		return rightPriceRepository.getDesignation();
	}


	@Override
	public ResponseEntity<Object> getBand() {
		return rightPriceRepository.getBand();
	}


	@Override
	public ResponseEntity<Object> getGrade() {
		return rightPriceRepository.getGrade();
	}
	
	@Override
	public ResponseEntity<Object> addDesignation(Designation designation) {
		designation.setIsActive(1);
		String syntelLanId=rightPriceRepository.getSyntelId(this.auditTrails.getCurrentUser());
		if(syntelLanId.isEmpty() || syntelLanId==null)
		{	
			designation.setCreatedBy(this.auditTrails.getCurrentUser());
			designation.setCreatedOn(this.auditTrails.getCurrentTimeStamp());
			designation.setUpdatedBy(this.auditTrails.getCurrentUser());
			designation.setUpdatedOn(this.auditTrails.getCurrentTimeStamp());
		}
		else
		{
		designation.setCreatedBy(syntelLanId);
		designation.setCreatedOn(this.auditTrails.getCurrentTimeStamp());
		designation.setUpdatedBy(syntelLanId);
		designation.setUpdatedOn(this.auditTrails.getCurrentTimeStamp());
		}
		return rightPriceRepository.addDesignation(designation);
	}
	@Override
	public ResponseEntity<Object> updateDesignation(Designation designation) {
		String syntelLanId=rightPriceRepository.getSyntelId(this.auditTrails.getCurrentUser());
		if(syntelLanId.isEmpty() || syntelLanId==null)
		{	
			designation.setUpdatedBy(this.auditTrails.getCurrentUser());
			designation.setUpdatedOn(this.auditTrails.getCurrentTimeStamp());
		}
		else
		{
		designation.setUpdatedBy(syntelLanId);
		designation.setUpdatedOn(this.auditTrails.getCurrentTimeStamp());
		}
		return rightPriceRepository.updateDesignation(designation);
	}
	@Override
	public ResponseEntity<Object> getVertical() {
		return rightPriceRepository.getVertical();
	}
	
	@Override
	public ResponseEntity<Object> getEmpAdIdDetails(EmpDetails empDetails) {
		return rightPriceRepository.getEmpAdIdDetails(empDetails);
	}
	
	@Override
	public ResponseEntity<Object> getVerticalGroupID() {
		return rightPriceRepository.getVerticalGroupID();
	}
	@Override
	public ResponseEntity<Object> addVertical(Vertical vertical) {
		vertical.setIsActive(1);
		vertical.setVerticalStatusFlag(1);
		String syntelLanId=rightPriceRepository.getSyntelId(this.auditTrails.getCurrentUser());
		if(syntelLanId.isEmpty() || syntelLanId==null)
		{	
			vertical.setCreatedBy(this.auditTrails.getCurrentUser());
			vertical.setCreatedOn(this.auditTrails.getCurrentTimeStamp());
			vertical.setUpdatedBy(this.auditTrails.getCurrentUser());
			vertical.setUpdatedOn(this.auditTrails.getCurrentTimeStamp());
		}
		else
		{
		vertical.setCreatedBy(syntelLanId);
		vertical.setCreatedOn(this.auditTrails.getCurrentTimeStamp());
		vertical.setUpdatedBy(syntelLanId);
		vertical.setUpdatedOn(this.auditTrails.getCurrentTimeStamp());
		}
		return rightPriceRepository.addVertical(vertical);
	}
	@Override
	public ResponseEntity<Object> updateVertical(Vertical vertical) {
		String syntelLanId=rightPriceRepository.getSyntelId(this.auditTrails.getCurrentUser());
		if(syntelLanId.isEmpty() || syntelLanId==null)
		{	
			vertical.setUpdatedBy(this.auditTrails.getCurrentUser());
			vertical.setUpdatedOn(this.auditTrails.getCurrentTimeStamp());
		}
		else
		{
		vertical.setUpdatedBy(syntelLanId);
		vertical.setUpdatedOn(this.auditTrails.getCurrentTimeStamp());
		}
		return rightPriceRepository.updateVertical(vertical);
	}
	
	@Override
	public ResponseEntity<Object> getRoles(int practiceId, int subPracticeId) {
		return rightPriceRepository.getRoles(practiceId,subPracticeId);
	}
	@Override
	public ResponseEntity<Object> getUpdateMasterRoles() {
		return rightPriceRepository.getUpdateMasterRoles();
	}
	
	@Override
	public ResponseEntity<Object> getSkills() {
		return rightPriceRepository.getSkills();
	}
	
	@Override
	public ResponseEntity<Object> getPracticeRoles() {
		return rightPriceRepository.getPracticeRoles();
	}
	
	@Override
	public ResponseEntity<Object> getSubPracticeRole(int practiceId) {
		return rightPriceRepository.getSubPracticeRole(practiceId);
	}
	
	@Override
	public ResponseEntity<Object> getXOSkillsRoles() {
		return rightPriceRepository.getXOSkillsRoles();
	}
	
	@Override
	public ResponseEntity<Object> getSyntelRoles() {
		return rightPriceRepository.getSyntelRoles();
	}
	
	@Override
	public ResponseEntity<Object> getProficiency() {
		return rightPriceRepository.getProficiency();
	}
	
	@Override
	public ResponseEntity<Object> getXOSkillElementRoles(int skillId) {
		return rightPriceRepository.getXOSkillElementRoles(skillId);
	}
	
	@Override
	public ResponseEntity<Object> getXOSkillElementRolesFP() {
		return rightPriceRepository.getXOSkillElementRolesFP();
	}
	

	@Override
	public ResponseEntity<Object> getKnowledgeNameRole() {
		return rightPriceRepository.getKnowledgeNameRole();
	}
	
	@Override
	public ResponseEntity<Object> addRole(MasterRoles masterRoles) {
		masterRoles.setIsActive(1);
		String syntelLanId=rightPriceRepository.getSyntelId(this.auditTrails.getCurrentUser());
		if(syntelLanId.isEmpty() || syntelLanId==null)
		{	
			masterRoles.setCreatedBy(this.auditTrails.getCurrentUser());
			masterRoles.setCreatedDate(this.auditTrails.getCurrentTimeStamp());
			masterRoles.setUpdatedBy(this.auditTrails.getCurrentUser());
			masterRoles.setUpdatedDate(this.auditTrails.getCurrentTimeStamp());
		}
		else
		{
		masterRoles.setCreatedBy(syntelLanId);
		masterRoles.setCreatedDate(this.auditTrails.getCurrentTimeStamp());
		masterRoles.setUpdatedBy(syntelLanId);
		masterRoles.setUpdatedDate(this.auditTrails.getCurrentTimeStamp());
		}
		return rightPriceRepository.addRole(masterRoles);
	}
	
	@Override
	public ResponseEntity<Object> updateRole(MasterRoles masterRoles) {
		AppLoger.APPLOGGER.info("Inside Service IMPL.......................................");
		String syntelLanId=rightPriceRepository.getSyntelId(this.auditTrails.getCurrentUser());
		if(syntelLanId.isEmpty() || syntelLanId==null)
		{	
			masterRoles.setUpdatedBy(this.auditTrails.getCurrentUser());
			masterRoles.setUpdatedDate(this.auditTrails.getCurrentTimeStamp());
		}
		else
		{
		masterRoles.setUpdatedBy(syntelLanId);
		masterRoles.setUpdatedDate(this.auditTrails.getCurrentTimeStamp());
		}
		return rightPriceRepository.updateRole(masterRoles);
	}
	
	@Override
	public ResponseEntity<Object> getRolesDetails(int masterRoleId) {
		return rightPriceRepository.getRolesDetails(masterRoleId);
	}
	
	/*Developed by AG5027026------------------------------------*/
	
	@Override
	public List<Lob> getLob() {
		return rightPriceRepository.getLob();
	}


	@Override
	public ResponseEntity<Object> getDeals(int customerId) {
		return rightPriceRepository.getDeals(customerId);
	}

	@Override
	public ResponseEntity<Object> getDealsFrmCrmStages(int customerId, int dealTypeId) {
		return rightPriceRepository.getDealsFrmCrmStages(customerId,dealTypeId);
	}
	
	@Override
	public ResponseEntity<Object> getOldDealId(int customerIdForOlddeal,int current_DealModel) {
		return rightPriceRepository.getOldDealId(customerIdForOlddeal, current_DealModel);
	}
	
	@Override
	public ResponseEntity<Object> getVersionDetails(String crmDealId) {
		return rightPriceRepository.getVersionDetails(crmDealId);
	}
	
	@Override
	public ResponseEntity<Object> getVersionDetailsTM(String crmDealId) {
		return rightPriceRepository.getVersionDetailsTM(crmDealId);
	}

	@Override
	public ResponseEntity<Object> getRiskAnswers(int rpDealVersionId) {
		return rightPriceRepository.getRiskAnswers(rpDealVersionId);
	}

@Override
	//public ResponseEntity<Object> insertDealData(Deal dealData,MultipartFile[] fileArr, String[] fileNameArr) {
	public ResponseEntity<Object> insertDealData(Deal dealData,
			MultipartFile[] fileArr, String[] fileNameArr) {
		int rpDealVersionId;
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		SAMLCredential credential = (SAMLCredential) authentication.getCredentials();
		Assertion assertion = credential.getAuthenticationAssertion();
		HashMap<String, String> map = new HashMap<>();
	    AppLoger.APPLOGGER.info("1");
	    List<AttributeStatement> attributeStatements = assertion.getAttributeStatements();
	    AppLoger.APPLOGGER.info("attributeStatements are : " +attributeStatements);
	    for (AttributeStatement statement : attributeStatements) {
	      for (Attribute attribute : statement.getAttributes()) {
	        XMLObject xmlObject = attribute.getAttributeValues().get(0);
	        AppLoger.APPLOGGER.info("XMLObject is : " +xmlObject);
	        if (xmlObject instanceof XSStringImpl) {
	          map.put(attribute.getName(), ((XSStringImpl) xmlObject).getValue());
	          AppLoger.APPLOGGER.info("Map is : " +map);
	        } else {
	          map.put(attribute.getName(), ((XSAnyImpl) xmlObject).getTextContent());
	          AppLoger.APPLOGGER.info("Map is : " +map);
	        }
	      }
	    }
	    String dasID = map.get("uid");
	    String fName = map.get("firstname");
	    String lname = map.get("surname");
	    String atosMailID = map.get("mail");
	    String requesterName = fName+" "+lname;
		String syntelLanId=rightPriceRepository.getSyntelId(this.auditTrails.getCurrentUser());
		try{
			int fileToUploadLength = fileArr.length;
			
			if(fileToUploadLength>0){
				for (int i = 0; i < fileArr.length; i++) {
					FileObjectTable fileObject = new FileObjectTable();
					int insertId=0;
					fileObject.setFileName(fileNameArr[i]);
				//	fileObject.setOriginalFileName(fileArr[i].getOriginalFilename());
					fileObject.setObject(fileArr[i].getBytes());
					fileObject.setActiveStatus(1);
					if(syntelLanId.isEmpty() || syntelLanId==null)
					{	
						fileObject.setCreatedBy(this.auditTrails.getCurrentUser());
						fileObject.setCreatedOn(this.auditTrails.getCurrentTimeStamp());
						fileObject.setLastUpdatedBy(this.auditTrails.getCurrentUser());
						fileObject.setLastUpdatedOn(this.auditTrails.getCurrentTimeStamp());
					}
					else
					{
					fileObject.setCreatedBy(syntelLanId);
					fileObject.setCreatedOn(this.auditTrails.getCurrentTimeStamp());
					fileObject.setLastUpdatedBy(syntelLanId);
					fileObject.setLastUpdatedOn(this.auditTrails.getCurrentTimeStamp());
					}
					AppLoger.APPLOGGER.info("----------------------Adding Object-------------------------------------");
					insertId = rightPriceRepository.addObject(fileObject);
					AppLoger.APPLOGGER.info("insertId for " + fileNameArr[i] + " is  " + insertId);
					dealData = setFileInsertId(dealData,fileNameArr[i],insertId);
				}
				AppLoger.APPLOGGER.info("----------------------Adding Object Completed-------------------------------------");
			}
			
			dealData.setIsActive(1);
		if(syntelLanId.isEmpty() || syntelLanId==null)
		{
			dealData.setAtosEmailId(atosMailID);
			dealData.setEmployeeName(requesterName);
			dealData.setUpdateAtosEmailId(atosMailID);
			dealData.setUpdateEmployeeName(requesterName);
			dealData.setCreatedBy(this.auditTrails.getCurrentUser());
			dealData.setCreatedDate(this.auditTrails.getCurrentTimeStamp());
			dealData.setUpdatedBy(this.auditTrails.getCurrentUser());
			dealData.setUpdatedDate(this.auditTrails.getCurrentTimeStamp());
		}
		else
		{
		dealData.setAtosEmailId(atosMailID);
		dealData.setEmployeeName(requesterName);
		dealData.setUpdateAtosEmailId(atosMailID);
		dealData.setUpdateEmployeeName(requesterName);
		dealData.setCreatedBy(syntelLanId);
		dealData.setCreatedDate(this.auditTrails.getCurrentTimeStamp());
		dealData.setUpdatedBy(syntelLanId);
		dealData.setUpdatedDate(this.auditTrails.getCurrentTimeStamp());
		}
		//dealData.setCrmDealId();
		//rpDealVersionId = rightPriceRepository.insertDealData(dealData);
		return rightPriceRepository.insertDealData(dealData);
		
		}
		catch(Exception he){
			AppLoger.APPLOGGER.info("Exception occured while inserting object " + he.getMessage());
			return ResponseEntity.status(205).contentType(MediaType.TEXT_PLAIN).body("Currently We are facing technical issues, please try again later.");
		}
		//return ResponseEntity.status(200).contentType(MediaType.TEXT_PLAIN).body("Request submitted successfully.");
		//return rightPriceRepository.insertDealData(dealData);
	}
	

@Override
public ResponseEntity<Object> insertFpDealData(FpDeal dealData, MultipartFile[] fileArr, String[] fileNameArr) {
	
	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	SAMLCredential credential = (SAMLCredential) authentication.getCredentials();
	Assertion assertion = credential.getAuthenticationAssertion();
	HashMap<String, String> map = new HashMap<>();
    AppLoger.APPLOGGER.info("1");
    List<AttributeStatement> attributeStatements = assertion.getAttributeStatements();
    AppLoger.APPLOGGER.info("attributeStatements are : " +attributeStatements);
    for (AttributeStatement statement : attributeStatements) {
      for (Attribute attribute : statement.getAttributes()) {
        XMLObject xmlObject = attribute.getAttributeValues().get(0);
        AppLoger.APPLOGGER.info("XMLObject is : " +xmlObject);
        if (xmlObject instanceof XSStringImpl) {
          map.put(attribute.getName(), ((XSStringImpl) xmlObject).getValue());
          AppLoger.APPLOGGER.info("Map is : " +map);
        } else {
          map.put(attribute.getName(), ((XSAnyImpl) xmlObject).getTextContent());
          AppLoger.APPLOGGER.info("Map is : " +map);
        }
      }
    }
    String dasID = map.get("uid");
    String fName = map.get("firstname");
    String lname = map.get("surname");
    String atosMailID = map.get("mail");
    String requesterName = fName+" "+lname;
	try
	{	
		dealData.setIsActive(1);
		String syntelLanId=rightPriceRepository.getSyntelId(this.auditTrails.getCurrentUser());
		if(syntelLanId.isEmpty() || syntelLanId==null)
		{	
			dealData.setAtosEmailId(atosMailID);
			dealData.setEmployeeName(requesterName);
			dealData.setUpdateAtosEmailId(atosMailID);
			dealData.setUpdateEmployeeName(requesterName);
			dealData.setCreatedBy(this.auditTrails.getCurrentUser());
			dealData.setCreatedDate(this.auditTrails.getCurrentTimeStamp());
			dealData.setUpdatedBy(this.auditTrails.getCurrentUser());
			dealData.setUpdatedDate(this.auditTrails.getCurrentTimeStamp());
		}
		else
		{
		dealData.setAtosEmailId(atosMailID);
		dealData.setEmployeeName(requesterName);
		dealData.setUpdateAtosEmailId(atosMailID);
		dealData.setUpdateEmployeeName(requesterName);
		dealData.setCreatedBy(syntelLanId);
		dealData.setCreatedDate(this.auditTrails.getCurrentTimeStamp());
		dealData.setUpdatedBy(syntelLanId);
		dealData.setUpdatedDate(this.auditTrails.getCurrentTimeStamp());
		}
		if(dealData.getRiskBasedAnswers().size()>0){
			for (RiskBasedAnswers answerData : dealData.getRiskBasedAnswers()) {
				AppLoger.APPLOGGER.info("-------> "+dealData.getRiskBasedAnswers());
				answerData.setIsActive(1);
				if(syntelLanId.isEmpty() || syntelLanId==null)
				{	
					answerData.setCreatedBy(this.auditTrails.getCurrentUser());
					answerData.setCreatedDate(this.auditTrails.getCurrentTimeStamp());
					answerData.setUpdatedBy(this.auditTrails.getCurrentUser());
					answerData.setUpdatedDate(this.auditTrails.getCurrentTimeStamp());
				}
				else
				{
				answerData.setCreatedBy(syntelLanId);
				answerData.setCreatedDate(this.auditTrails.getCurrentTimeStamp());
				answerData.setUpdatedBy(syntelLanId);
				answerData.setUpdatedDate(this.auditTrails.getCurrentTimeStamp());
				}
			}
		}
		
		if(dealData.getRiskDevMainBasedAnswers().size()>0){
			for (ProjectTypeAnswers answerDevMainData : dealData.getRiskDevMainBasedAnswers()) {
				answerDevMainData.setIsActive(1);
				if(syntelLanId.isEmpty() || syntelLanId==null)
				{	
					answerDevMainData.setCreatedBy(this.auditTrails.getCurrentUser());
					answerDevMainData.setCreatedOn(this.auditTrails.getCurrentTimeStamp());
					answerDevMainData.setUpdatedBy(this.auditTrails.getCurrentUser());
					answerDevMainData.setUpdatedDate(this.auditTrails.getCurrentTimeStamp());
				}
				else
				{
				answerDevMainData.setCreatedBy(syntelLanId);
				answerDevMainData.setCreatedOn(this.auditTrails.getCurrentTimeStamp());
				answerDevMainData.setUpdatedBy(syntelLanId);
				answerDevMainData.setUpdatedDate(this.auditTrails.getCurrentTimeStamp());
				}
			}
		}
		
		int fileToUploadLength = fileArr.length;
		
		if(fileToUploadLength>0){
			for (int i = 0; i < fileArr.length; i++) {
				FileObjectTable fileObject = new FileObjectTable();
				int insertId=0;
				fileObject.setFileName(fileNameArr[i]);
			//	fileObject.setOriginalFileName(fileArr[i].getOriginalFilename());
				fileObject.setObject(fileArr[i].getBytes());
				fileObject.setActiveStatus(1);
				fileObject.setCreatedBy(syntelLanId);
				fileObject.setCreatedOn(this.auditTrails.getCurrentTimeStamp());
				fileObject.setLastUpdatedBy(syntelLanId);
				fileObject.setLastUpdatedOn(this.auditTrails.getCurrentTimeStamp());
				AppLoger.APPLOGGER.info("----------------------Adding Object-------------------------------------");
				insertId = rightPriceRepository.addObject(fileObject);
				AppLoger.APPLOGGER.info("insertId for " + fileNameArr[i] + " is  " + insertId);
				dealData = setFpFileInsertId(dealData,fileNameArr[i],insertId);
			}
			AppLoger.APPLOGGER.info("----------------------Adding Object Completed-------------------------------------");
		
		return rightPriceRepository.insertFpDealData(dealData);	
			
		
		}
	}
	catch(Exception he)
	{	
		AppLoger.APPLOGGER.info("Exception occured while inserting object " + he.getMessage());
		return ResponseEntity.status(205).contentType(MediaType.TEXT_PLAIN).body("Currently We are facing technical issues, please try again later.");
		
	}
	return rightPriceRepository.insertFpDealData(dealData);
}




	/*@Override
	//public ResponseEntity<Object> insertFpDealData(Deal dealData, List<RiskBasedAnswers> answersArrray) {
	public ResponseEntity<Object> insertFpDealData(FpDeal dealData, List<RiskBasedAnswers> answersArrray,List<ProjectTypeAnswers> answersDevMainArrray,
			MultipartFile[] fileArr, String[] fileNameArr) {
		int rpDealVersionId;
		try{
			int fileToUploadLength = fileArr.length;
			
			if(fileToUploadLength>0){
				for (int i = 0; i < fileArr.length; i++) {
					FileObjectTable fileObject = new FileObjectTable();
					int insertId=0;
					fileObject.setFileName(fileNameArr[i]);
				//	fileObject.setOriginalFileName(fileArr[i].getOriginalFilename());
					fileObject.setObject(fileArr[i].getBytes());
					fileObject.setActiveStatus(1);
					fileObject.setCreatedBy(syntelLanId);
					fileObject.setCreatedOn(this.auditTrails.getCurrentTimeStamp());
					fileObject.setLastUpdatedBy(syntelLanId);
					fileObject.setLastUpdatedOn(this.auditTrails.getCurrentTimeStamp());
					AppLoger.APPLOGGER.info("----------------------Adding Object-------------------------------------");
					insertId = rightPriceRepository.addObject(fileObject);
					AppLoger.APPLOGGER.info("insertId for " + fileNameArr[i] + " is  " + insertId);
					dealData = setFpFileInsertId(dealData,fileNameArr[i],insertId);
				}
				AppLoger.APPLOGGER.info("----------------------Adding Object Completed-------------------------------------");
			}
			
		dealData.setIsActive(1);
		dealData.setCreatedBy(syntelLanId);
		dealData.setCreatedDate(this.auditTrails.getCurrentTimeStamp());
		dealData.setUpdatedBy(syntelLanId);
		dealData.setUpdatedDate(this.auditTrails.getCurrentTimeStamp());
		//dealData.setCrmDealId();
		rpDealVersionId = rightPriceRepository.insertFpDealData(dealData);
		
		
		if(answersArrray.size()>0){
			for (RiskBasedAnswers answerData : answersArrray) {
				AppLoger.APPLOGGER.info("rp version id-----------------------------"+rpDealVersionId);
				AppLoger.APPLOGGER.info("*********Inserting answers " + answerData.getRiskAnswer());
				answerData.setRpDealVersionId(rpDealVersionId);
				answerData.setIsActive(1);
				answerData.setCreatedBy(syntelLanId);
				answerData.setCreatedDate(this.auditTrails.getCurrentTimeStamp());
				answerData.setUpdatedBy(syntelLanId);
				answerData.setUpdatedDate(this.auditTrails.getCurrentTimeStamp());
				rightPriceRepository.addRiskAnswers(answerData);
			}
		}
		
		if(answersDevMainArrray.size()>0){
			for (ProjectTypeAnswers answerDevMainData : answersDevMainArrray) {
				AppLoger.APPLOGGER.info("rp version id-----------------------------"+rpDealVersionId);
				AppLoger.APPLOGGER.info("*********Inserting answers " + answerDevMainData.getAnswer());
			
				answerDevMainData.setRpDealVersionId(rpDealVersionId);
				answerDevMainData.setIsActive(1);
				answerDevMainData.setCreatedBy(syntelLanId);
				answerDevMainData.setCreatedOn(this.auditTrails.getCurrentTimeStamp());
				answerDevMainData.setUpdatedBy(syntelLanId);
				answerDevMainData.setUpdatedDate(this.auditTrails.getCurrentTimeStamp());
				rightPriceRepository.addDevMainAnswers(answerDevMainData);
			}
		 }
		}
		catch(Exception he){
			AppLoger.APPLOGGER.info("Exception occured while inserting object " + he.getMessage());
			return ResponseEntity.status(205).contentType(MediaType.TEXT_PLAIN).body("Currently We are facing technical issues, please try again later.");
		}
		return ResponseEntity.status(200).contentType(MediaType.TEXT_PLAIN).body(rpDealVersionId);
	}*/
	
	@Override
	public ResponseEntity<Object> insertDlCurrUTI(DealCurrencyUtilization dcuti)
	{
		int rpDealVersionId;
		try
		{
			dcuti.setIsActive(1);
			dcuti.setUtilization(100.00);
			String syntelLanId=rightPriceRepository.getSyntelId(this.auditTrails.getCurrentUser());
			if(syntelLanId.isEmpty() || syntelLanId==null)
			{	
				dcuti.setCreatedBy(this.auditTrails.getCurrentUser());
				dcuti.setCreatedOn(this.auditTrails.getCurrentTimeStamp());
				dcuti.setUpdatedBy(this.auditTrails.getCurrentUser());
				dcuti.setUpdatedOn(this.auditTrails.getCurrentTimeStamp());		
			}
			else
			{
			dcuti.setCreatedBy(syntelLanId);
			dcuti.setCreatedOn(this.auditTrails.getCurrentTimeStamp());
			dcuti.setUpdatedBy(syntelLanId);
			dcuti.setUpdatedOn(this.auditTrails.getCurrentTimeStamp());	
			}
			rpDealVersionId = rightPriceRepository.insertDlCurrUTI(dcuti);
		}		
		catch(Exception he)
		{			
			return ResponseEntity.status(205).contentType(MediaType.TEXT_PLAIN).body("Currently We are facing technical issues, please try again later.");
		}
		return ResponseEntity.status(200).contentType(MediaType.TEXT_PLAIN).body("Request submitted successfully.");		
	}
	@Override
	public ResponseEntity<Object> insertDlLoc(DealLocation dlLoc)
	{
		int rpDealVersionId;
		try
		{			
			dlLoc.setIsActive(1);
			dlLoc.setUtilization(100.00);
			String syntelLanId=rightPriceRepository.getSyntelId(this.auditTrails.getCurrentUser());
			if(syntelLanId.isEmpty() || syntelLanId==null)
			{	
				dlLoc.setCreatedBy(this.auditTrails.getCurrentUser());
				dlLoc.setCreatedOn(this.auditTrails.getCurrentTimeStamp());
				dlLoc.setUpdatedBy(this.auditTrails.getCurrentUser());
				dlLoc.setUpdatedOn(this.auditTrails.getCurrentTimeStamp());	
			}
			else
			{
			dlLoc.setCreatedBy(syntelLanId);
			dlLoc.setCreatedOn(this.auditTrails.getCurrentTimeStamp());
			dlLoc.setUpdatedBy(syntelLanId);
			dlLoc.setUpdatedOn(this.auditTrails.getCurrentTimeStamp());	
			}
			rpDealVersionId = rightPriceRepository.insertDlLoc(dlLoc);
		}		
		catch(Exception he)
		{			
			return ResponseEntity.status(205).contentType(MediaType.TEXT_PLAIN).body("Currently We are facing technical issues, please try again later.");
		}
		return ResponseEntity.status(200).contentType(MediaType.TEXT_PLAIN).body("Request submitted successfully.");		
	}
	
	@Override
	public ResponseEntity<Object> updateDealData(Deal dealData, MultipartFile[] fileArr, String[] fileNameArr) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		SAMLCredential credential = (SAMLCredential) authentication.getCredentials();
		Assertion assertion = credential.getAuthenticationAssertion();
		HashMap<String, String> map = new HashMap<>();
	    AppLoger.APPLOGGER.info("1");
	    List<AttributeStatement> attributeStatements = assertion.getAttributeStatements();
	    AppLoger.APPLOGGER.info("attributeStatements are : " +attributeStatements);
	    for (AttributeStatement statement : attributeStatements) {
	      for (Attribute attribute : statement.getAttributes()) {
	        XMLObject xmlObject = attribute.getAttributeValues().get(0);
	        AppLoger.APPLOGGER.info("XMLObject is : " +xmlObject);
	        if (xmlObject instanceof XSStringImpl) {
	          map.put(attribute.getName(), ((XSStringImpl) xmlObject).getValue());
	          AppLoger.APPLOGGER.info("Map is : " +map);
	        } else {
	          map.put(attribute.getName(), ((XSAnyImpl) xmlObject).getTextContent());
	          AppLoger.APPLOGGER.info("Map is : " +map);
	        }
	      }
	    }
	    String dasID = map.get("uid");
	    String fName = map.get("firstname");
	    String lname = map.get("surname");
	    String atosMailID = map.get("mail");
	    String requesterName = fName+" "+lname;
		
		try{
			int fileToUploadLength = fileArr.length;
			String syntelLanId=rightPriceRepository.getSyntelId(this.auditTrails.getCurrentUser());
			if(fileToUploadLength>0){
				for (int i = 0; i < fileArr.length; i++) {
					FileObjectTable fileObject = new FileObjectTable();
					int insertId=0;
					fileObject.setFileName(fileNameArr[i]);
					fileObject.setOriginalFileName(fileArr[i].getOriginalFilename());
					fileObject.setObject(fileArr[i].getBytes());
					fileObject.setActiveStatus(1);
					if(syntelLanId.isEmpty() || syntelLanId==null)
					{	
						fileObject.setCreatedBy(this.auditTrails.getCurrentUser());
						fileObject.setCreatedOn(this.auditTrails.getCurrentTimeStamp());
						fileObject.setLastUpdatedBy(this.auditTrails.getCurrentUser());
						fileObject.setLastUpdatedOn(this.auditTrails.getCurrentTimeStamp());
					}
					else
					{
					fileObject.setCreatedBy(syntelLanId);
					fileObject.setCreatedOn(this.auditTrails.getCurrentTimeStamp());
					fileObject.setLastUpdatedBy(syntelLanId);
					fileObject.setLastUpdatedOn(this.auditTrails.getCurrentTimeStamp());
					}
					AppLoger.APPLOGGER.info("----------------------Adding Object-------------------------------------");
					insertId = rightPriceRepository.addObject(fileObject);
					AppLoger.APPLOGGER.info("insertId for " + fileNameArr[i] + " is  " + insertId);
					dealData = setFileInsertId(dealData,fileNameArr[i],insertId);
				}
				AppLoger.APPLOGGER.info("----------------------Adding Object Completed-------------------------------------");
			}
			/*dealData.setIsActive(1); */
		dealData.setIsActive(1);
		if(syntelLanId.isEmpty() || syntelLanId==null)
		{	
			dealData.setAtosEmailId(atosMailID);
			dealData.setEmployeeName(requesterName);
			dealData.setUpdateAtosEmailId(atosMailID);
			dealData.setUpdateEmployeeName(requesterName);
			dealData.setCreatedBy(this.auditTrails.getCurrentUser());
			dealData.setCreatedDate(this.auditTrails.getCurrentTimeStamp());
			dealData.setUpdatedBy(this.auditTrails.getCurrentUser());
			dealData.setUpdatedDate(this.auditTrails.getCurrentTimeStamp());
		}
		else
		{
		dealData.setAtosEmailId(atosMailID);
		dealData.setEmployeeName(requesterName);
		dealData.setUpdateAtosEmailId(atosMailID);
		dealData.setUpdateEmployeeName(requesterName);
		dealData.setCreatedBy(syntelLanId);
		dealData.setCreatedDate(this.auditTrails.getCurrentTimeStamp());
		dealData.setUpdatedBy(syntelLanId);
		dealData.setUpdatedDate(this.auditTrails.getCurrentTimeStamp());
		}
		rightPriceRepository.updateDealData(dealData);
		
		}
		catch(Exception he){
			AppLoger.APPLOGGER.info("Exception occured while updating " + he.getMessage());
			return ResponseEntity.status(205).contentType(MediaType.TEXT_PLAIN).body("Currently We are facing technical issues, please try again later.");
		}
		return ResponseEntity.status(200).contentType(MediaType.TEXT_PLAIN).body("Request submitted successfully.");
	}
	

	@Override
	public ResponseEntity<Object> updateFpDealData(FpDeal dealData, List<RiskBasedAnswers> answersArrray,List<ProjectTypeAnswers> answersDevMainArrray, MultipartFile[] fileArr, String[] fileNameArr) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		SAMLCredential credential = (SAMLCredential) authentication.getCredentials();
		Assertion assertion = credential.getAuthenticationAssertion();
		HashMap<String, String> map = new HashMap<>();
	    AppLoger.APPLOGGER.info("1");
	    List<AttributeStatement> attributeStatements = assertion.getAttributeStatements();
	    AppLoger.APPLOGGER.info("attributeStatements are : " +attributeStatements);
	    for (AttributeStatement statement : attributeStatements) {
	      for (Attribute attribute : statement.getAttributes()) {
	        XMLObject xmlObject = attribute.getAttributeValues().get(0);
	        AppLoger.APPLOGGER.info("XMLObject is : " +xmlObject);
	        if (xmlObject instanceof XSStringImpl) {
	          map.put(attribute.getName(), ((XSStringImpl) xmlObject).getValue());
	          AppLoger.APPLOGGER.info("Map is : " +map);
	        } else {
	          map.put(attribute.getName(), ((XSAnyImpl) xmlObject).getTextContent());
	          AppLoger.APPLOGGER.info("Map is : " +map);
	        }
	      }
	    }
	    String dasID = map.get("uid");
	    String fName = map.get("firstname");
	    String lname = map.get("surname");
	    String atosMailID = map.get("mail");
	    String requesterName = fName+" "+lname;
		try{
			int fileToUploadLength = fileArr.length;
			String syntelLanId=rightPriceRepository.getSyntelId(this.auditTrails.getCurrentUser());
			if(fileToUploadLength>0){
				for (int i = 0; i < fileArr.length; i++) {
					FileObjectTable fileObject = new FileObjectTable();
					int insertId=0;
					fileObject.setFileName(fileNameArr[i]);
					fileObject.setOriginalFileName(fileArr[i].getOriginalFilename());
					fileObject.setObject(fileArr[i].getBytes());
					fileObject.setActiveStatus(1);
					if(syntelLanId.isEmpty() || syntelLanId==null)
					{	
						fileObject.setCreatedBy(this.auditTrails.getCurrentUser());
						fileObject.setCreatedOn(this.auditTrails.getCurrentTimeStamp());
						fileObject.setLastUpdatedBy(this.auditTrails.getCurrentUser());
						fileObject.setLastUpdatedOn(this.auditTrails.getCurrentTimeStamp());
					}
					else
					{
					fileObject.setCreatedBy(syntelLanId);
					fileObject.setCreatedOn(this.auditTrails.getCurrentTimeStamp());
					fileObject.setLastUpdatedBy(syntelLanId);
					fileObject.setLastUpdatedOn(this.auditTrails.getCurrentTimeStamp());
					}
					AppLoger.APPLOGGER.info("----------------------Adding Object-------------------------------------");
					insertId = rightPriceRepository.addObject(fileObject);
					AppLoger.APPLOGGER.info("insertId for " + fileNameArr[i] + " is  " + insertId);
					dealData = setFpFileInsertId(dealData,fileNameArr[i],insertId);
				}
				AppLoger.APPLOGGER.info("----------------------Adding Object Completed-------------------------------------");
			}
			/*dealData.setIsActive(1); */
		dealData.setIsActive(1);
		if(syntelLanId.isEmpty() || syntelLanId==null)
		{
			dealData.setAtosEmailId(atosMailID);
			dealData.setEmployeeName(requesterName);
			dealData.setUpdateAtosEmailId(atosMailID);
			dealData.setUpdateEmployeeName(requesterName);
			dealData.setCreatedBy(this.auditTrails.getCurrentUser());
			dealData.setCreatedDate(this.auditTrails.getCurrentTimeStamp());
			dealData.setUpdatedBy(this.auditTrails.getCurrentUser());
			dealData.setUpdatedDate(this.auditTrails.getCurrentTimeStamp());
		}
		else
		{
			dealData.setAtosEmailId(atosMailID);
			dealData.setEmployeeName(requesterName);
			dealData.setUpdateAtosEmailId(atosMailID);
			dealData.setUpdateEmployeeName(requesterName);
			dealData.setCreatedBy(syntelLanId);
			dealData.setCreatedDate(this.auditTrails.getCurrentTimeStamp());
			dealData.setUpdatedBy(syntelLanId);
			dealData.setUpdatedDate(this.auditTrails.getCurrentTimeStamp());
		}
		rightPriceRepository.updateFpDealData(dealData);
		
		if(answersArrray.size()>0){
			for (RiskBasedAnswers answerData : answersArrray) {
				AppLoger.APPLOGGER.info("rp version id-----------------------------"+dealData.getRpDealVersionId());
				AppLoger.APPLOGGER.info("*********updating answers " + answerData.getRiskAnswer());
				//answerData.setRpDealVersionId(rpDealVersionId);
				/*answerData.setIsActive(1);
				answerData.setCreatedBy(syntelLanId);
				answerData.setCreatedDate(this.auditTrails.getCurrentTimeStamp());*/
				answerData.setIsActive(1);
				if(syntelLanId.isEmpty() || syntelLanId==null)
				{	
					answerData.setRpDealVersionId(dealData.getRpDealVersionId());
					answerData.setUpdatedBy(this.auditTrails.getCurrentUser());
					answerData.setUpdatedDate(this.auditTrails.getCurrentTimeStamp());
				}
				else
				{
				answerData.setUpdatedBy(syntelLanId);
				answerData.setRpDealVersionId(dealData.getRpDealVersionId());
				answerData.setUpdatedDate(this.auditTrails.getCurrentTimeStamp());
				}
			}
			rightPriceRepository.updateRiskAnswers(answersArrray);
		}
		if(answersDevMainArrray.size()>0){
			for (ProjectTypeAnswers answerDevMainData : answersDevMainArrray) {
				AppLoger.APPLOGGER.info("rp version id-----------------------------"+dealData.getRpDealVersionId());
				AppLoger.APPLOGGER.info("*********updating answers " + answerDevMainData.getAnswer());
				//answerData.setRpDealVersionId(rpDealVersionId);
				/*answerData.setIsActive(1);
				answerData.setCreatedBy(syntelLanId);
				answerData.setCreatedDate(this.auditTrails.getCurrentTimeStamp());*/
				answerDevMainData.setIsActive(1);
				if(syntelLanId.isEmpty() || syntelLanId==null)
				{	
					answerDevMainData.setRpDealVersionId(dealData.getRpDealVersionId());
					answerDevMainData.setUpdatedBy(this.auditTrails.getCurrentUser());
					answerDevMainData.setUpdatedDate(this.auditTrails.getCurrentTimeStamp());
				}
				else
				{
				answerDevMainData.setUpdatedBy(syntelLanId);
				answerDevMainData.setRpDealVersionId(dealData.getRpDealVersionId());
				answerDevMainData.setUpdatedDate(this.auditTrails.getCurrentTimeStamp());
				}
			}
			rightPriceRepository.updateDevMainAnswers(answersDevMainArrray);
		}
		}
		catch(Exception he){
			AppLoger.APPLOGGER.info("Exception occured while updating " + he.getMessage());
			return ResponseEntity.status(205).contentType(MediaType.TEXT_PLAIN).body("Currently We are facing technical issues, please try again later.");
		}
		return ResponseEntity.status(200).contentType(MediaType.TEXT_PLAIN).body("Request submitted successfully.");
	}

	private Deal setFileInsertId(Deal dealDetails, String fileName, int insertId) {
		AppLoger.APPLOGGER.info("deal details : " + dealDetails + "File Name : " + fileName + " insertId " + insertId);
		switch (fileName) {
		case "MANUAL_DEAL": {
			dealDetails.setManualDealObjectId(insertId);
			break;
		}
		case "ATTACHMENT_ID1": {
			dealDetails.setAttachment1ObjectId(insertId);
			break;
		}
		case "ATTACHMENT_ID2": {
			dealDetails.setAttachment2ObjectId(insertId);
			break;
		}
		default: {

			AppLoger.APPLOGGER.info("NO case available");
			break;
		}
		}

		return dealDetails;
	}
	
	private FpDeal setFpFileInsertId(FpDeal dealDetails, String fileName, int insertId) {
		AppLoger.APPLOGGER.info("deal details : " + dealDetails + "File Name : " + fileName + " insertId " + insertId);
		switch (fileName) {
		case "MANUAL_DEAL": {
			dealDetails.setManualDealObjectId(insertId);
			break;
		}
		case "ATTACHMENT_ID1": {
			dealDetails.setAttachment1ObjectId(insertId);
			break;
		}
		case "ATTACHMENT_ID2": {
			dealDetails.setAttachment2ObjectId(insertId);
			break;
		}
		default: {

			AppLoger.APPLOGGER.info("NO case available");
			break;
		}
		}

		return dealDetails;
	}
	
	@Override
	public ResponseEntity<Object> getSummary(int rcId) {
		return rightPriceRepository.getSummary(rcId);
	}

	@Override
	public ResponseEntity<Object> getRateCardDetails(int rcId) {
		return rightPriceRepository.getRateCardDetails(rcId);
	}

	@Override
	public ResponseEntity<Object> saveApprovalData(RateCardApprovalAudit rateCardApprovalAudit) {
		AppLoger.APPLOGGER.info("in service");
		rateCardApprovalAudit.setIsActive(1);
		String syntelLanId=rightPriceRepository.getSyntelId(this.auditTrails.getCurrentUser());
		if(syntelLanId.isEmpty() || syntelLanId==null)
		{	
			rateCardApprovalAudit.setCreatedBy(this.auditTrails.getCurrentUser());
			rateCardApprovalAudit.setCreatedDate(this.auditTrails.getCurrentTimeStamp());
			rateCardApprovalAudit.setUpdatedBy(this.auditTrails.getCurrentUser());
			rateCardApprovalAudit.setUpdatedDate(this.auditTrails.getCurrentTimeStamp());
		}
		else
		{
		rateCardApprovalAudit.setCreatedBy(syntelLanId);
		rateCardApprovalAudit.setCreatedDate(this.auditTrails.getCurrentTimeStamp());
		rateCardApprovalAudit.setUpdatedBy(syntelLanId);
		rateCardApprovalAudit.setUpdatedDate(this.auditTrails.getCurrentTimeStamp());
		}
		return rightPriceRepository.saveApprovalData(rateCardApprovalAudit);
	}

	@Override
	public ResponseEntity<Object> getVerticalDetails(int verticalId) {
		return rightPriceRepository.getVerticalDetails(verticalId);
	}

	@Override
	public ResponseEntity<Object> getLeadershipDetails() {
		return rightPriceRepository.getLeadershipDetails();
	}

	@Override
	public ResponseEntity<Object> updateApprovalDataForRateCard(RateCardDetails rateCardApprovalDetails) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    	SAMLCredential credential = (SAMLCredential) authentication.getCredentials();
    	Assertion assertion = credential.getAuthenticationAssertion();
		HashMap<String, String> map = new HashMap<>();
        AppLoger.APPLOGGER.info("1");
        List<AttributeStatement> attributeStatements = assertion.getAttributeStatements();
        AppLoger.APPLOGGER.info("attributeStatements are : " +attributeStatements);
        for (AttributeStatement statement : attributeStatements) {
          for (Attribute attribute : statement.getAttributes()) {
            XMLObject xmlObject = attribute.getAttributeValues().get(0);
            AppLoger.APPLOGGER.info("XMLObject is : " +xmlObject);
            if (xmlObject instanceof XSStringImpl) {
              map.put(attribute.getName(), ((XSStringImpl) xmlObject).getValue());
              AppLoger.APPLOGGER.info("Map is : " +map);
            } else {
              map.put(attribute.getName(), ((XSAnyImpl) xmlObject).getTextContent());
              AppLoger.APPLOGGER.info("Map is : " +map);
            }
          }
        }
        String dasID = map.get("uid");
        String fName = map.get("firstname");
        String lname = map.get("surname");
        String atosMailID = map.get("mail");
        String requesterName = fName+" "+lname;
		String syntelLanId=rightPriceRepository.getSyntelId(this.auditTrails.getCurrentUser());
		if(syntelLanId.isEmpty() || syntelLanId==null)
		{	
			rateCardApprovalDetails.setUpdateAtosEmailId(atosMailID);
			rateCardApprovalDetails.setUpdateEmployeeName(requesterName);
			rateCardApprovalDetails.setUpdatedBy(this.auditTrails.getCurrentUser());
			rateCardApprovalDetails.setUpdatedOn(this.auditTrails.getCurrentTimeStamp());
		}
		else
		{
			 rateCardApprovalDetails.setUpdateAtosEmailId(atosMailID);
		     rateCardApprovalDetails.setUpdateEmployeeName(requesterName);
			rateCardApprovalDetails.setUpdatedBy(syntelLanId);
			rateCardApprovalDetails.setUpdatedOn(this.auditTrails.getCurrentTimeStamp());
		}
		return rightPriceRepository.updateApprovalDataForRateCard(rateCardApprovalDetails);
	}


	@Override
	public ResponseEntity<Object> getUploadedDoc(int versionId) {
		return rightPriceRepository.getUploadedDoc(versionId);
	}


	@Override
	public ResponseEntity<Object> downloadFile(int objectId) {
		return rightPriceRepository.downloadFile(objectId);
	}


	@Override
	public ResponseEntity<Object> updateActiveStatus(int docId) {
		return rightPriceRepository.updateActiveStatus(docId);
	}

	
	/*Rate card details creation [PRASAD]*/

	@Override
	public ResponseEntity<Object> getCurrentUserDetails() {
		return rightPriceRepository.getCurrentUserDetails();
	}
	
	

	@Override
	public ResponseEntity<Object> rateCardDetails(RateCardDetails rateCardDetails) {
		rateCardDetails.setIsActive(1);
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    	SAMLCredential credential = (SAMLCredential) authentication.getCredentials();
    	Assertion assertion = credential.getAuthenticationAssertion();
		HashMap<String, String> map = new HashMap<>();
        AppLoger.APPLOGGER.info("1");
        List<AttributeStatement> attributeStatements = assertion.getAttributeStatements();
        AppLoger.APPLOGGER.info("attributeStatements are : " +attributeStatements);
        for (AttributeStatement statement : attributeStatements) {
          for (Attribute attribute : statement.getAttributes()) {
            XMLObject xmlObject = attribute.getAttributeValues().get(0);
            AppLoger.APPLOGGER.info("XMLObject is : " +xmlObject);
            if (xmlObject instanceof XSStringImpl) {
              map.put(attribute.getName(), ((XSStringImpl) xmlObject).getValue());
              AppLoger.APPLOGGER.info("Map is : " +map);
            } else {
              map.put(attribute.getName(), ((XSAnyImpl) xmlObject).getTextContent());
              AppLoger.APPLOGGER.info("Map is : " +map);
            }
          }
        }
        String dasID = map.get("uid");
        String fName = map.get("firstname");
        String lname = map.get("surname");
        String atosMailID = map.get("mail");
        String requesterName = fName+" "+lname;
		String syntelLanId=rightPriceRepository.getSyntelId(this.auditTrails.getCurrentUser());
		if(syntelLanId.isEmpty() || syntelLanId==null)
		{	
		 	rateCardDetails.setAtosEmailId(atosMailID);
			rateCardDetails.setEmployeeName(requesterName);
			rateCardDetails.setUpdateAtosEmailId(atosMailID);
			rateCardDetails.setUpdateEmployeeName(requesterName);
			rateCardDetails.setCreatedBy(this.auditTrails.getCurrentUser());
			rateCardDetails.setCreatedOn(this.auditTrails.getCurrentTimeStamp());
			rateCardDetails.setUpdatedBy(this.auditTrails.getCurrentUser());
			rateCardDetails.setUpdatedOn(this.auditTrails.getCurrentTimeStamp());
		}
		else
		{
		 	rateCardDetails.setAtosEmailId(atosMailID);
			rateCardDetails.setEmployeeName(requesterName);
			rateCardDetails.setUpdateAtosEmailId(atosMailID);
			rateCardDetails.setUpdateEmployeeName(requesterName);
			rateCardDetails.setCreatedBy(syntelLanId);
			rateCardDetails.setCreatedOn(this.auditTrails.getCurrentTimeStamp());
			rateCardDetails.setUpdatedBy(syntelLanId);
			rateCardDetails.setUpdatedOn(this.auditTrails.getCurrentTimeStamp());
		}
		if(rateCardDetails.getyOYIncrementPercents().size()>0){
			for (YOYIncrementPercent currentYOY : rateCardDetails.getyOYIncrementPercents()) {
				AppLoger.APPLOGGER.info("-------> "+currentYOY.getIncrementPercentOnsite());
				currentYOY.setIsActive(1);
				if(syntelLanId.isEmpty() || syntelLanId==null)
				{	
					currentYOY.setCreatedBy(this.auditTrails.getCurrentUser());
					currentYOY.setCreatedOn(this.auditTrails.getCurrentTimeStamp());
					currentYOY.setUpdatedBy(this.auditTrails.getCurrentUser());
					currentYOY.setUpdatedOn(this.auditTrails.getCurrentTimeStamp());
				}
				else
				{
				currentYOY.setCreatedBy(syntelLanId);
				currentYOY.setCreatedOn(this.auditTrails.getCurrentTimeStamp());
				currentYOY.setUpdatedBy(syntelLanId);
				currentYOY.setUpdatedOn(this.auditTrails.getCurrentTimeStamp());
				}
				}
		}
		
		if(rateCardDetails.getRegionWiseUtilization().size()>0){
			for (RateCardCurrencyUtilization currRCCurrencyUtilization : rateCardDetails.getRegionWiseUtilization()) {
				currRCCurrencyUtilization.setIsActive(1);
				if(syntelLanId.isEmpty() || syntelLanId==null)
				{	
					currRCCurrencyUtilization.setCreatedBy(this.auditTrails.getCurrentUser());
					currRCCurrencyUtilization.setCreatedOn(this.auditTrails.getCurrentTimeStamp());
					currRCCurrencyUtilization.setUpdatedBy(this.auditTrails.getCurrentUser());
					currRCCurrencyUtilization.setUpdatedOn(this.auditTrails.getCurrentTimeStamp());
				}
				else
				{
				currRCCurrencyUtilization.setCreatedBy(syntelLanId);
				currRCCurrencyUtilization.setCreatedOn(this.auditTrails.getCurrentTimeStamp());
				currRCCurrencyUtilization.setUpdatedBy(syntelLanId);
				currRCCurrencyUtilization.setUpdatedOn(this.auditTrails.getCurrentTimeStamp());
				}
			}
		}
		
		if(rateCardDetails.getRateCardLocations().size()>0){
			for (RateCardLocation currentLocation : rateCardDetails.getRateCardLocations()) {
				currentLocation.setIsActive(1);
				if(syntelLanId.isEmpty() || syntelLanId==null)
				{	
					currentLocation.setCreatedBy(this.auditTrails.getCurrentUser());
					currentLocation.setCreatedOn(this.auditTrails.getCurrentTimeStamp());
					currentLocation.setUpdatedBy(this.auditTrails.getCurrentUser());
					currentLocation.setUpdatedOn(this.auditTrails.getCurrentTimeStamp());
				}
				else
				{
				currentLocation.setCreatedBy(syntelLanId);
				currentLocation.setCreatedOn(this.auditTrails.getCurrentTimeStamp());
				currentLocation.setUpdatedBy(syntelLanId);
				currentLocation.setUpdatedOn(this.auditTrails.getCurrentTimeStamp());
				}
			}
		}
		return rightPriceRepository.rateCardDetails(rateCardDetails);
		
	}


	@Override
	public ResponseEntity<Object> getRateCards(int customerVerticalMappingId) {
		return rightPriceRepository.getRateCards(customerVerticalMappingId);
	}
	
	@Override
	public ResponseEntity<Object>getCustomerByVerticalGroupId() {
		return rightPriceRepository.getCustomerByVerticalGroupId();
	}
	
	@Override
	public ResponseEntity<Object>getCustomerForUser() {
		return rightPriceRepository.getCustomerForUser();
	}
	
	@Override
	public ResponseEntity<Object>getCountryList() {
		return rightPriceRepository.getCountryList();
	}
	/*Rate card details creation [PRASAD]*/
	
	/*Fixed Price Deal Creation Rate Card And Project Details [PRASAD]*/ 
	
	@Override
	public ResponseEntity<Object> saveFPDealCreationRCAndProjectDetails(FPDCRCAndProjectDetails fPDCRCAndProjectDetails) {
		AppLoger.APPLOGGER.info("saveFPDealCreationRCAndProjectDetails -- serviceImpl  ");
		String syntelLanId=rightPriceRepository.getSyntelId(this.auditTrails.getCurrentUser());
		if(fPDCRCAndProjectDetails.getDealFPTower().size()>0){
			for (DealFixLocation currentTower : fPDCRCAndProjectDetails.getDealFPTower()) {
				AppLoger.APPLOGGER.info(" 1 -------> "+currentTower.toString());
				currentTower.setIsActive(1);
				if(syntelLanId.isEmpty() || syntelLanId==null)
				{	
					currentTower.setCreatedBy(this.auditTrails.getCurrentUser());
					currentTower.setCreatedOn(this.auditTrails.getCurrentTimeStamp());
					currentTower.setUpdatedBy(this.auditTrails.getCurrentUser());
					currentTower.setUpdatedOn(this.auditTrails.getCurrentTimeStamp());
				}
				else
				{
				currentTower.setCreatedBy(syntelLanId);
				currentTower.setCreatedOn(this.auditTrails.getCurrentTimeStamp());
				currentTower.setUpdatedBy(syntelLanId);
				currentTower.setUpdatedOn(this.auditTrails.getCurrentTimeStamp());
				}
			}
		}
		
		if(fPDCRCAndProjectDetails.getDealFPRateCard().size()>0){
			AppLoger.APPLOGGER.info("4 -------> ");
			for (FPDealRatecard currentRateCard : fPDCRCAndProjectDetails.getDealFPRateCard()) {
				currentRateCard.setIsActive(1);
				if(syntelLanId.isEmpty() || syntelLanId==null)
				{	
					currentRateCard.setCreatedBy(this.auditTrails.getCurrentUser());
					currentRateCard.setCreatedOn(this.auditTrails.getCurrentTimeStamp());
					currentRateCard.setUpdatedBy(this.auditTrails.getCurrentUser());
					currentRateCard.setUpdatedOn(this.auditTrails.getCurrentTimeStamp());
				}
				else
				{
				currentRateCard.setCreatedBy(syntelLanId);
				currentRateCard.setCreatedOn(this.auditTrails.getCurrentTimeStamp());
				currentRateCard.setUpdatedBy(syntelLanId);
				currentRateCard.setUpdatedOn(this.auditTrails.getCurrentTimeStamp());
				}
				//currentRateCard.setRcId(fPDCRCAndProjectDetails.get);
			}
		}
		
		//end arvind

		return rightPriceRepository.saveFPDealCreationRCAndProjectDetails(fPDCRCAndProjectDetails);
	}
	
	
	@Override
	public ResponseEntity<Object> saveTMDealCreationRCAndProjectDetails(FPDCRCAndProjectDetails fPDCRCAndProjectDetails) {
		AppLoger.APPLOGGER.info("saveFPDealCreationRCAndProjectDetails -- serviceImpl  ");
		String syntelLanId=rightPriceRepository.getSyntelId(this.auditTrails.getCurrentUser());
		if(fPDCRCAndProjectDetails.getDealTower().size()>0){
			for (DealTower currentTower : fPDCRCAndProjectDetails.getDealTower()) {
				AppLoger.APPLOGGER.info(" 1 -------> "+currentTower.toString());
				currentTower.setIsActive(1);
				if(syntelLanId.isEmpty() || syntelLanId==null)
				{	
					currentTower.setCreatedBy(this.auditTrails.getCurrentUser());
					currentTower.setCreatedOn(this.auditTrails.getCurrentTimeStamp());
					currentTower.setUpdatedBy(this.auditTrails.getCurrentUser());
					currentTower.setUpdatedOn(this.auditTrails.getCurrentTimeStamp());
					
				}
				else
				{
				currentTower.setCreatedBy(syntelLanId);
				currentTower.setCreatedOn(this.auditTrails.getCurrentTimeStamp());
				currentTower.setUpdatedBy(syntelLanId);
				currentTower.setUpdatedOn(this.auditTrails.getCurrentTimeStamp());
				}
				currentTower.setDealVersionId(fPDCRCAndProjectDetails.getDealVersionId());
			}
		}
		
		//arvind
		if(fPDCRCAndProjectDetails.getDealRateCard().size()>0){
			AppLoger.APPLOGGER.info("4 -------> ");
			for (DealRateCard currentRateCard : fPDCRCAndProjectDetails.getDealRateCard()) {
				currentRateCard.setIsActive(1);
				if(syntelLanId.isEmpty() || syntelLanId==null)
				{	
					currentRateCard.setCreatedBy(this.auditTrails.getCurrentUser());
					currentRateCard.setCreatedOn(this.auditTrails.getCurrentTimeStamp());
					currentRateCard.setUpdatedBy(this.auditTrails.getCurrentUser());
					currentRateCard.setUpdatedOn(this.auditTrails.getCurrentTimeStamp());
				}
				else
				{
				/*currentRateCard.setPercentage(fPDCRCAndProjectDetails.getDealRateCard().);*/
				currentRateCard.setCreatedBy(syntelLanId);
				currentRateCard.setCreatedOn(this.auditTrails.getCurrentTimeStamp());
				currentRateCard.setUpdatedBy(syntelLanId);
				currentRateCard.setUpdatedOn(this.auditTrails.getCurrentTimeStamp());
				}
				currentRateCard.setDealVersionId(fPDCRCAndProjectDetails.getDealVersionId());
				//currentRateCard.setRcId(fPDCRCAndProjectDetails.get);
			}
		}
		
		//end arvind

		return rightPriceRepository.saveTMDealCreationRCAndProjectDetails(fPDCRCAndProjectDetails);
	}
	
	@Override
	public ResponseEntity<Object> getFPDealCreationRCAndProjectDetails(int dealVersionId){
		return rightPriceRepository.getFPDealCreationRCAndProjectDetails(dealVersionId);
	}
	@Override
	public ResponseEntity<Object> getTMDealCreationRCAndProjectDetails(int dealVersionId){
		return rightPriceRepository.getTMDealCreationRCAndProjectDetails(dealVersionId);
	}
	/*Fixed Price Deal Creation Rate Card And Project Details [PRASAD]*/ 
	
	/*Fixed Price Deal Creation Role Selection And Add contractor Role [PRASAD]*/
	@Override
	public ResponseEntity<Object> saveTMDCRoleSelectionAndContractorRole(FPDCRoleAndContractor fPDCRoleAndContractor){
		
		if(fPDCRoleAndContractor.getDealRoles().size()>0){
			for (DealRoles currentRole : fPDCRoleAndContractor.getDealRoles()) {
				currentRole.setIsAtive(1);
				String syntelLanId=rightPriceRepository.getSyntelId(this.auditTrails.getCurrentUser());
				if(syntelLanId.isEmpty() || syntelLanId==null)
				{	
					currentRole.setCreatedBy(this.auditTrails.getCurrentUser());
					currentRole.setCreatedOn(this.auditTrails.getCurrentTimeStamp());
					currentRole.setUpdatedBy(this.auditTrails.getCurrentUser());
					currentRole.setUpdatedOn(this.auditTrails.getCurrentTimeStamp());
				}
				else
				{
				currentRole.setCreatedBy(syntelLanId);
				currentRole.setCreatedOn(this.auditTrails.getCurrentTimeStamp());
				currentRole.setUpdatedBy(syntelLanId);
				currentRole.setUpdatedOn(this.auditTrails.getCurrentTimeStamp());
				}
				currentRole.setDealVersionId(fPDCRoleAndContractor.getDealVersionId());
				currentRole.setRcId(0);
				
			}
		}
		
		if(fPDCRoleAndContractor.getDealContractorRole().size()>0){
			for (DealContraactorRole currentContractorRole : fPDCRoleAndContractor.getDealContractorRole()) {
				currentContractorRole.setIsActive(1);
				String syntelLanId=rightPriceRepository.getSyntelId(this.auditTrails.getCurrentUser());
				if(syntelLanId.isEmpty() || syntelLanId==null)
				{	
					currentContractorRole.setCreatedBy(this.auditTrails.getCurrentUser());
					currentContractorRole.setCreatedOn(this.auditTrails.getCurrentTimeStamp());
					currentContractorRole.setUpdatedBy(this.auditTrails.getCurrentUser());
					currentContractorRole.setUpdatedOn(this.auditTrails.getCurrentTimeStamp());
				}
				else
				{
				currentContractorRole.setCreatedBy(syntelLanId);
				currentContractorRole.setCreatedOn(this.auditTrails.getCurrentTimeStamp());
				currentContractorRole.setUpdatedBy(syntelLanId);
				currentContractorRole.setUpdatedOn(this.auditTrails.getCurrentTimeStamp());
				}
				currentContractorRole.setDealVersionId(fPDCRoleAndContractor.getDealVersionId());
			}
		}
		
		return rightPriceRepository.saveTMDCRoleSelectionAndContractorRole(fPDCRoleAndContractor);
	}

	
	@Override
	public ResponseEntity<Object> getFPDCRoleSelectionAndContractorRole(int dealVersionId){
		return rightPriceRepository.getFPDCRoleSelectionAndContractorRole(dealVersionId);
		
	}
	/*Fixed Price Deal Creation Role Selection And Add contractor Role [PRASAD]*/
	
	/*Role Selection RITU .....*/
	
	@Override
	public ResponseEntity<Object> getRateCard() {

		return rightPriceRepository.getRateCard();
	}
	
	@Override
	public ResponseEntity<Object> getRateCardDeal(int rpVrsId) {

		return rightPriceRepository.getRateCardDeal(rpVrsId);
	}
	@Override
	public ResponseEntity<Object> getXOSkills() {

		return rightPriceRepository.getXOSkills();
	}
	
	@Override
	public ResponseEntity<Object> getXOSkillsElementMaster(int skillId) {

		return rightPriceRepository.getXOSkillsElementMaster(skillId );
	}
	
	@Override
	public ResponseEntity<Object> getXOSkillFromSubPracticeId(int subPracticeId) {
		
		return rightPriceRepository.getXOSkillFromSubPracticeId(subPracticeId);
	}
	@Override
	public ResponseEntity<Object> getXOSkillFromSubPracticeId2(int subPracticeId,int masterRoleId, int rcId_value) {
		
		return rightPriceRepository.getXOSkillFromSubPracticeId2(subPracticeId,masterRoleId,rcId_value);
	}
	@Override
	public ResponseEntity<Object> getKnowledgeName() {

		return rightPriceRepository.getKnowledgeName();
	}	
	@Override
	public ResponseEntity<Object> getKnowledgeName_saved(int masterRoleId, int rcId_value) {

		return rightPriceRepository.getKnowledgeName_saved(masterRoleId,rcId_value);
	}
	@Override
	public ResponseEntity<Object> getRoleSelectionDetails(int rcId) {

		return rightPriceRepository.getRoleSelectionDetails(rcId);
	}
	
	@Override
	public ResponseEntity<Object> getPersistRateCardDetails(int rcId) {

		return rightPriceRepository.getPersistRateCardDetails(rcId);
	}

	@Override
	public ResponseEntity<Object> save(ArrayList<RPRateCardRoles> rpRateCardRoles) {
		AppLoger.APPLOGGER.info("in service.................");
		for (RPRateCardRoles rpRateCardRoles1 : rpRateCardRoles) {
		rpRateCardRoles1.setActiveStatus(1);
		String syntelLanId=rightPriceRepository.getSyntelId(this.auditTrails.getCurrentUser());
		if(syntelLanId.isEmpty() || syntelLanId==null)
		{	
			AppLoger.APPLOGGER.info("in servic when SynteLanid is empty and"+this.auditTrails.getCurrentUser());
			rpRateCardRoles1.setCreatedBy(this.auditTrails.getCurrentUser());
			rpRateCardRoles1.setCreatedOn(this.auditTrails.getCurrentTimeStamp());
			rpRateCardRoles1.setLastUpdatedBy(this.auditTrails.getCurrentUser());
			rpRateCardRoles1.setLastUpdatedOn(this.auditTrails.getCurrentTimeStamp());
		}
		else
		{
		rpRateCardRoles1.setCreatedBy(syntelLanId);
		rpRateCardRoles1.setCreatedOn(this.auditTrails.getCurrentTimeStamp());
		rpRateCardRoles1.setLastUpdatedBy(syntelLanId);
		rpRateCardRoles1.setLastUpdatedOn(this.auditTrails.getCurrentTimeStamp());
		}
		
	}
		return rightPriceRepository.save(rpRateCardRoles);
	}

	
	
	@Override
	public ResponseEntity<Object> searchRateCardData(String searchRoles) 
	{
		return rightPriceRepository.searchRateCardData(searchRoles);
	}
	
	/*Role Selection RITU .....*/
	/*visa screen sneha-----------*/
	@Override
	public ResponseEntity<Object> getVisa() {
		return rightPriceRepository.getVisa();
	}


	@Override
	public ResponseEntity<Object> addVisaDetails(VisaLabels visaDetailsAdd) {
		AppLoger.APPLOGGER.info("in service");
		visaDetailsAdd.setIsActive(1);
		String syntelLanId=rightPriceRepository.getSyntelId(this.auditTrails.getCurrentUser());
		if(syntelLanId.isEmpty() || syntelLanId==null)
		{	
			visaDetailsAdd.setCreatedBy(this.auditTrails.getCurrentUser());
			visaDetailsAdd.setCreatedDate(this.auditTrails.getCurrentTimeStamp());
			visaDetailsAdd.setUpdatedBy(this.auditTrails.getCurrentUser());
			visaDetailsAdd.setUpdatedDate(this.auditTrails.getCurrentTimeStamp());
		}
		else
		{
		visaDetailsAdd.setCreatedBy(syntelLanId);
		visaDetailsAdd.setCreatedDate(this.auditTrails.getCurrentTimeStamp());
		visaDetailsAdd.setUpdatedBy(syntelLanId);
		visaDetailsAdd.setUpdatedDate(this.auditTrails.getCurrentTimeStamp());
		}
		return rightPriceRepository.addVisaDetails(visaDetailsAdd);
	}


	@Override
	public ResponseEntity<Object> updateVisaDetails(VisaLabels visaDetailsUpdate) {
		AppLoger.APPLOGGER.info("inside service");
		AppLoger.APPLOGGER.info("getIsActive"+visaDetailsUpdate.getIsActive());
		visaDetailsUpdate.setIsActive(1);
		String syntelLanId=rightPriceRepository.getSyntelId(this.auditTrails.getCurrentUser());
		if(syntelLanId.isEmpty() || syntelLanId==null)
		{	
			visaDetailsUpdate.setUpdatedBy(this.auditTrails.getCurrentUser());
			visaDetailsUpdate.setUpdatedDate(this.auditTrails.getCurrentTimeStamp());
		}
		else
		{
		visaDetailsUpdate.setUpdatedBy(syntelLanId);
		visaDetailsUpdate.setUpdatedDate(this.auditTrails.getCurrentTimeStamp());
		}
		return rightPriceRepository.updateVisaDetails(visaDetailsUpdate);
	}


	@Override
	public ResponseEntity<Object> viewVisaDetails(VisaLabels visaDetailsView) {
		return rightPriceRepository.viewVisaDetails(visaDetailsView);
	}


	@Override
	public ResponseEntity<Object> getVisaLabel(int countryId, int visaTypeId) {
		return rightPriceRepository.getVisaLabel(countryId, visaTypeId);
	}


	@Override
	public ResponseEntity<Object> updateApprovalDatails(RateCardDetails rateCardApproval) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    	SAMLCredential credential = (SAMLCredential) authentication.getCredentials();
    	Assertion assertion = credential.getAuthenticationAssertion();
		HashMap<String, String> map = new HashMap<>();
        AppLoger.APPLOGGER.info("1");
        List<AttributeStatement> attributeStatements = assertion.getAttributeStatements();
        AppLoger.APPLOGGER.info("attributeStatements are : " +attributeStatements);
        for (AttributeStatement statement : attributeStatements) {
          for (Attribute attribute : statement.getAttributes()) {
            XMLObject xmlObject = attribute.getAttributeValues().get(0);
            AppLoger.APPLOGGER.info("XMLObject is : " +xmlObject);
            if (xmlObject instanceof XSStringImpl) {
              map.put(attribute.getName(), ((XSStringImpl) xmlObject).getValue());
              AppLoger.APPLOGGER.info("Map is : " +map);
            } else {
              map.put(attribute.getName(), ((XSAnyImpl) xmlObject).getTextContent());
              AppLoger.APPLOGGER.info("Map is : " +map);
            }
          }
        }
        String dasID = map.get("uid");
        String fName = map.get("firstname");
        String lname = map.get("surname");
        String atosMailID = map.get("mail");
        String requesterName = fName+" "+lname;
		String syntelLanId=rightPriceRepository.getSyntelId(this.auditTrails.getCurrentUser());
		if(syntelLanId.isEmpty() || syntelLanId==null)
		{	
			rateCardApproval.setUpdateAtosEmailId(atosMailID);
			rateCardApproval.setUpdateEmployeeName(requesterName);
			rateCardApproval.setUpdatedBy(this.auditTrails.getCurrentUser());
			rateCardApproval.setUpdatedOn(this.auditTrails.getCurrentTimeStamp());
		}
		else
		{
			rateCardApproval.setUpdateAtosEmailId(atosMailID);
			rateCardApproval.setUpdateEmployeeName(requesterName);
			rateCardApproval.setUpdatedBy(syntelLanId);
			rateCardApproval.setUpdatedOn(this.auditTrails.getCurrentTimeStamp());
		}
		return rightPriceRepository.updateApprovalDetails(rateCardApproval);
	}

	@Override
	public ResponseEntity<Object> getVisaOnLoad() {
		// TODO Auto-generated method stub
		return rightPriceRepository.getVisaOnLoad();
	}


	@Override
	public ResponseEntity<Object> viewAllowances(BasicAllowance basicAllowance) {
		// TODO Auto-generated method stub
		return rightPriceRepository.viewAllowances(basicAllowance);
	}


	@Override
	public ResponseEntity<Object> checkAnnualAllownces(BasicAllowance basicAllowance) {
		// TODO Auto-generated method stub
		return rightPriceRepository.checkAnnualAllownces(basicAllowance);
	}


	@Override
	public ResponseEntity<Object> saveAnnualAllowances(BasicAllowance[] basicAllowance) {
		// TODO Auto-generated method stub
		for (BasicAllowance currBasicAllowance : basicAllowance) {
			currBasicAllowance.setStatusModel(1);
			String syntelLanId=rightPriceRepository.getSyntelId(this.auditTrails.getCurrentUser());
			if(syntelLanId.isEmpty() || syntelLanId==null)
			{	
				currBasicAllowance.setCreatedBy(this.auditTrails.getCurrentUser());
				currBasicAllowance.setCreatedDate(this.auditTrails.getCurrentTimeStamp());
				currBasicAllowance.setUpdatedBy(this.auditTrails.getCurrentUser());
				currBasicAllowance.setUpdatedDate(this.auditTrails.getCurrentTimeStamp());
			}
			else
			{
			currBasicAllowance.setCreatedBy(syntelLanId);
			currBasicAllowance.setCreatedDate(this.auditTrails.getCurrentTimeStamp());
			currBasicAllowance.setUpdatedBy(syntelLanId);
			currBasicAllowance.setUpdatedDate(this.auditTrails.getCurrentTimeStamp());
			}
		}
		return rightPriceRepository.saveAnnualAllowances(basicAllowance);
	}


	@Override
	public ResponseEntity<Object> updateAnnualAllowances(BasicAllowance[] basicAllowance) {
		// TODO Auto-generated method stub
		return rightPriceRepository.updateAnnualAllowances(basicAllowance);
	}


	/*@Override
	public ResponseEntity<Object> viewMiscellaneousCost(OffshoreMiscellaneousCost miscellaneousDetailsView) {
		return rightPriceRepository.viewMiscellaneousCost(miscellaneousDetailsView);
	}*/
	
/*AC5029212----------------*/
	
	@Override
	public ResponseEntity<Object> getRoleUtilization(int cityId, int rateCardId) {
		return rightPriceRepository.getRoleUtilization(cityId, rateCardId);
	}


	@Override
	public ResponseEntity<Object> updateRateUtilizationAndRates(List<RateCardRoleUtilization> rateCardRoleUtilization) {
		// TODO Auto-generated method stub
		return rightPriceRepository.updateRateUtilizationAndRates(rateCardRoleUtilization);
	}
	
	
	@Override
	public ResponseEntity<Object> onViewParameterForm(Deduction deduction) {
		// TODO Auto-generated method stub
		return rightPriceRepository.onViewParameterForm(deduction);
	}
	@Override
	public ResponseEntity<Object> onTaxViewParameterForm(Deduction deduction) {
		// TODO Auto-generated method stub
		return rightPriceRepository.onTaxViewParameterForm(deduction);
	}


	@Override
	public ResponseEntity<Object> getParamList() {
		// TODO Auto-generated method stub
		return rightPriceRepository.getParamList();
	}


	@Override
	public ResponseEntity<Object> onAddCCPSearch(Deduction deduction) {
		// TODO Auto-generated method stub
		return rightPriceRepository.onAddCCPSearch(deduction);
	}
	@Override
	public ResponseEntity<Object> onAddTPSearch(Deduction deduction) {
		// TODO Auto-generated method stub
		return rightPriceRepository.onAddTPSearch(deduction);
	}


	@Override
	public ResponseEntity<Object> saveCommonCostParam(Deduction[] deduction) {
		for (Deduction deduction2 : deduction) {
			deduction2.setIsActive(1);
			deduction2.setDeductionCatId(1);
			String syntelLanId=rightPriceRepository.getSyntelId(this.auditTrails.getCurrentUser());
			if(syntelLanId.isEmpty() || syntelLanId==null)
			{	
				deduction2.setCreatedBy(this.auditTrails.getCurrentUser());
				deduction2.setCreatedOn(this.auditTrails.getCurrentTimeStamp());
				deduction2.setLastUpdatedBy(this.auditTrails.getCurrentUser());
				deduction2.setLastUpdatedOn(this.auditTrails.getCurrentTimeStamp());
			}
			else
			{
			deduction2.setCreatedBy(syntelLanId);
			deduction2.setCreatedOn(this.auditTrails.getCurrentTimeStamp());
			deduction2.setLastUpdatedBy(syntelLanId);
			deduction2.setLastUpdatedOn(this.auditTrails.getCurrentTimeStamp());
			}
		}
		return rightPriceRepository.saveCommonCostParam(deduction);
	}


	@Override
	public ResponseEntity<Object> onUpdateCCPSearch(int countryId, int visaTypeId, int year) {
		// TODO Auto-generated method stub
		return rightPriceRepository.onUpdateCCPSearch(countryId, visaTypeId, year);
	}
	@Override
	public ResponseEntity<Object> onUpdateTPSearch(int countryId, int visaTypeId, int year) {
		return rightPriceRepository.onUpdateTPSearch(countryId, visaTypeId, year);
	}


	@Override
	public ResponseEntity<Object> updateCommonCostParam(Deduction[] deduction) {
		for (Deduction deduction2 : deduction) {
			String syntelLanId=rightPriceRepository.getSyntelId(this.auditTrails.getCurrentUser());
			if(syntelLanId.isEmpty() || syntelLanId==null)
			{	
				deduction2.setLastUpdatedBy(this.auditTrails.getCurrentUser());
				deduction2.setLastUpdatedOn(this.auditTrails.getCurrentTimeStamp());
			}
			else
			{
			deduction2.setLastUpdatedBy(syntelLanId);
			deduction2.setLastUpdatedOn(this.auditTrails.getCurrentTimeStamp());
			}
			}
		return rightPriceRepository.updateCommonCostParam(deduction);
	}
	
	/*PS5029150 --  Start*/
	@Override
	public ResponseEntity<Object> getTeamVertical(VerticalMembers verticalDetails) {
		// TODO Auto-generated method stub
		return rightPriceRepository.getTeamVertical(verticalDetails);
	}


	@Override
	public ResponseEntity<Object> getEmpDetails(EmpDetails empDetails) {
		// TODO Auto-generated method stub
		return rightPriceRepository.getEmpDetails(empDetails);
	}


	@Override
	public ResponseEntity<Object> saveVerticalMembers(VerticalMembers[] verticalMember) {
		
		for (VerticalMembers vertical : verticalMember) {
			vertical.setIsActive(1);
			String syntelLanId=rightPriceRepository.getSyntelId(this.auditTrails.getCurrentUser());
			if(syntelLanId.isEmpty() || syntelLanId==null)
			{	
				vertical.setCreatedBy(this.auditTrails.getCurrentUser());
				vertical.setCreatedOn(this.auditTrails.getCurrentTimeStamp());
				vertical.setUpdatedBy(this.auditTrails.getCurrentUser());
				vertical.setUpdatedOn(this.auditTrails.getCurrentTimeStamp());
			}
			else
			{
			vertical.setCreatedBy(syntelLanId);
			vertical.setCreatedOn(this.auditTrails.getCurrentTimeStamp());
			vertical.setUpdatedBy(syntelLanId);
			vertical.setUpdatedOn(this.auditTrails.getCurrentTimeStamp());
			}
		}
		return rightPriceRepository.saveVerticalMember(verticalMember);
	}
	
	@Override
	public ResponseEntity<Object> getRiskManagersEmpDetails(EmpDetails empDetails) {
		// TODO Auto-generated method stub
		return rightPriceRepository.getRiskManagersEmpDetails(empDetails);
	}


	/*@Override
	public ResponseEntity<Object> saveRiskManagersVerticalMemebers(VerticalRiskManagersMembers[] RiskManagersMember) {
		for (VerticalRiskManagersMembers RiskManagersVertical : RiskManagersMember) {
			RiskManagersVertical.setIsActive(1);
			String syntelLanId=rightPriceRepository.getSyntelId(this.auditTrails.getCurrentUser());
			if(syntelLanId.isEmpty() || syntelLanId==null)
			{	
				RiskManagersVertical.setCreatedBy(this.auditTrails.getCurrentUser());
				RiskManagersVertical.setCreatedOn(this.auditTrails.getCurrentTimeStamp());
				RiskManagersVertical.setUpdatedBy(this.auditTrails.getCurrentUser());
				RiskManagersVertical.setUpdatedOn(this.auditTrails.getCurrentTimeStamp());
			}
			else
			{
			RiskManagersVertical.setCreatedBy(syntelLanId);
			RiskManagersVertical.setCreatedOn(this.auditTrails.getCurrentTimeStamp());
			RiskManagersVertical.setUpdatedBy(syntelLanId);
			RiskManagersVertical.setUpdatedOn(this.auditTrails.getCurrentTimeStamp());
			}
		}
		return rightPriceRepository.saveRiskManagersVerticalMemebers(RiskManagersMember);
	}
*/

	@Override
	public ResponseEntity<Object> getSearchExportCity(int countryId,int cityId) {
		// TODO Auto-generated method stub
		return rightPriceRepository.getSearchExportCity(countryId,cityId);
	}


	@Override
	public ResponseEntity<Object> getTeamVerticalExportDetails(int verticalId) {
		// TODO Auto-generated method stub
		return rightPriceRepository.getTeamVerticalExportDetails(verticalId);
	}


	@Override
	public ResponseEntity<Object> viewAllowancesExportDetails(int countryId, int visaId, int year) {
		// TODO Auto-generated method stub
		return rightPriceRepository.viewAllowancesExportDetails(countryId,visaId,year);
	}


	/*PS5029150 -- End*/
	//sneha--------------
	
	@Override
	public ResponseEntity<Object> viewMiscellaneousCost(City city) {
		// TODO Auto-generated method stub
		return rightPriceRepository.viewMiscellaneousCost(city);
	}


	/*@Override
	public ResponseEntity<Object> addMiscellaneousCost(OffshoreMiscellaneousCost miscellaneousCostAdd) {
		
		AppLoger.APPLOGGER.info("in service");
		miscellaneousCostAdd.setIsActive(1);
		miscellaneousCostAdd.setCreatedBy(syntelLanId);
		miscellaneousCostAdd.setCreatedDate(this.auditTrails.getCurrentTimeStamp());
		miscellaneousCostAdd.setUpdatedBy(syntelLanId);
		miscellaneousCostAdd.setUpdatedDate(this.auditTrails.getCurrentTimeStamp());
		return rightPriceRepository.addMiscellaneousCost(miscellaneousCostAdd);
	}*/
	
	@Override
	public ResponseEntity<Object> getUpdateMiscellaneousCost(int cityId) {
		return rightPriceRepository.getUpdateMiscellaneousCost(cityId);
	}


	@Override
	public ResponseEntity<Object> updateMiscellaneousCost(OffshoreMiscellaneousCost miscellaneousCostUpdate) {
		AppLoger.APPLOGGER.info("inside service");
		AppLoger.APPLOGGER.info("getIsActive"+miscellaneousCostUpdate.getIsActive());
		if(miscellaneousCostUpdate.getIsActive()!=1)
		{
			miscellaneousCostUpdate.setIsActive(0);
		}
		else{
			miscellaneousCostUpdate.setIsActive(1);
		}
		String syntelLanId=rightPriceRepository.getSyntelId(this.auditTrails.getCurrentUser());
		if(syntelLanId.isEmpty() || syntelLanId==null)
		{	
			miscellaneousCostUpdate.setUpdatedBy(this.auditTrails.getCurrentUser());
			miscellaneousCostUpdate.setUpdatedDate(this.auditTrails.getCurrentTimeStamp());
		}
		else
		{
		miscellaneousCostUpdate.setUpdatedBy(syntelLanId);
		miscellaneousCostUpdate.setUpdatedDate(this.auditTrails.getCurrentTimeStamp());
		}
		return rightPriceRepository.updateMiscellaneousCost(miscellaneousCostUpdate);
	}
	
	//--------------sneha
	@Override
	public ResponseEntity<Object> getRateCardUtilizationCountry(int rcId) {
		return rightPriceRepository.getRateCardUtilizationCountry(rcId);
	}
	
	@Override
	public ResponseEntity<Object> getRateUtilizationCity(int rcId,int countryId) {
		return rightPriceRepository.getRateUtilizationCity(rcId, countryId);
	}
	
	@Override
	public ResponseEntity<Object> getVerticalMemberData(String user) {
		return rightPriceRepository.getVerticalMemberData(user);
	}
	
	@Override
	public ResponseEntity<Object> getSummaryCountBasedOnVerticalId(MyDashboardRC[] rateCardCount) {
		return rightPriceRepository.getSummaryCountBasedOnVerticalId(rateCardCount);
	}
	
/*	@Override
	public ResponseEntity<Object> getSummaryCountBasedOnVerticalId(RateCardDetails[] rateCardCount) {
		return rightPriceRepository.getSummaryCountBasedOnVerticalId(rateCardCount);
	}
	
*/	
	@Override
	public ResponseEntity<Object> getRateCardData(MyDashboardRC[] rateCardDetails) {
		return rightPriceRepository.getRateCardData(rateCardDetails);
	}
	
	@Override
	public ResponseEntity<Object> getDealCountBasedOnVerticalId(MyDashboardDeal[] dealCount) {
		return rightPriceRepository.getDealCountBasedOnVerticalId(dealCount);
	}
	
	
	@Override
	public ResponseEntity<Object> getCustomerBasedDealRecords(DealCrmStages[] dealCrmStages) {
		return rightPriceRepository.getCustomerBasedDealRecords(dealCrmStages);
	}
	
	
	@Override
	public ResponseEntity<Object> getDashboardDealData(MyDashboardDeal[] dealData) {
		return rightPriceRepository.getDashboardDealData(dealData);
	}
	
	@Override
	public ResponseEntity<Object> getDescName(int taxnAssmId) {
		// TODO Auto-generated method stub
		return rightPriceRepository.getDescName(taxnAssmId);
	}


	@Override
	public ResponseEntity<Object> insertTaxnAssumtionParam(Deduction deduction) {
		deduction.setIsActive(1);
		deduction.setYear(Calendar.getInstance().get(Calendar.YEAR));
		String syntelLanId=rightPriceRepository.getSyntelId(this.auditTrails.getCurrentUser());
		if(syntelLanId.isEmpty() || syntelLanId==null)
		{	
			deduction.setCreatedBy(this.auditTrails.getCurrentUser());
			deduction.setCreatedOn(this.auditTrails.getCurrentTimeStamp());
			deduction.setLastUpdatedBy(this.auditTrails.getCurrentUser());
			deduction.setLastUpdatedOn(this.auditTrails.getCurrentTimeStamp());
		}
		else
		{
		deduction.setCreatedBy(syntelLanId);
		deduction.setCreatedOn(this.auditTrails.getCurrentTimeStamp());
		deduction.setLastUpdatedBy(syntelLanId);
		deduction.setLastUpdatedOn(this.auditTrails.getCurrentTimeStamp());
		}
		return rightPriceRepository.insertTaxnAssumtionParam(deduction);
	}


	@Override
	public ResponseEntity<Object> updateTaxnAssumtionParam(Deduction deduction) {
		// TODO Auto-generated method stub
		return rightPriceRepository.updateTaxnAssumtionParam(deduction);
	}


	@Override
	public ResponseEntity<Object> viewTaxParam(Deduction deduction) {
		// TODO Auto-generated method stub
		return rightPriceRepository.viewTaxParam(deduction);
	}


	@Override
	public ResponseEntity<Object> getGFTMemberDetails() {
		return rightPriceRepository.getGFTMemberDetails();
	}


	@Override
	public ResponseEntity<Object> viewCommonCostExportDetails(int countryId, int year) {
		// TODO Auto-generated method stub
		return rightPriceRepository.getCommonCostExportDetails(countryId,year);
	}
	@Override
	public ResponseEntity<Object> getTaxExportDetails(int countryId, int year) {
		return rightPriceRepository.getTaxExportDetails(countryId,year);
	}
	@Override
	public ResponseEntity<Object> getAssumptionsExportDetails(int countryId, int year) {
		return rightPriceRepository.getAssumptionsExportDetails(countryId,year);
	}


	/*@Override
	public ResponseEntity<Object> getTaxAssumptionReportExcel(int country, int deductionId) {
		// TODO Auto-generated method stub
		return rightPriceRepository.getTaxAssumptionReportExcel(country,deductionId);
	}*/
	
	@Override
	public ResponseEntity<Object> getCountryCityBasedOnDealVersion(int rpDealVersionId) {
		return rightPriceRepository.getCountryCityBasedOnDealVersion(rpDealVersionId);
	}


	@Override
	public ResponseEntity<Object> getTowerDetails(int rpDealVersionId) {
		return rightPriceRepository.getTowerDetails(rpDealVersionId);
	}

	@Override
	public ResponseEntity<Object> getSummaryCalculationData(int cityId, int towerId) {
		return rightPriceRepository.getSummaryCalculationData(cityId, towerId);
	}


	@Override
	public ResponseEntity<Object> getOtherCalculationData(int cityId, int towerId) {
		return rightPriceRepository.getOtherCalculationData(cityId, towerId);
	}

	@Override
	public ResponseEntity<Object> getFpDealStaffingData(int rpDealVersionId,int cityId,int towerId) {
		return rightPriceRepository.getFpDealStaffingData(rpDealVersionId,cityId, towerId);
	}
	
	@Override
	public ResponseEntity<Object> getFpDealStaffingDataExcel(int rpDealVersionId,int towerId) {
		return rightPriceRepository.getFpDealStaffingDataExcel(rpDealVersionId, towerId);
	}
	
	
	@Override
	public ResponseEntity<Object> getTMDealStaffingData(int cityId,int towerId) {
		return rightPriceRepository.getTMDealStaffingData(cityId, towerId);
	}
	@Override
	
	public ResponseEntity<Object> findTotalTransMonth (int towerId,int rpDealVersionId)	 {
			return rightPriceRepository.findTotalTransMonth(towerId,rpDealVersionId);
	}
	@Override
	public ResponseEntity<Object> updateFpDealStaffingDetails(List<StaffingDetails> staffingDetails) {
		// TODO Auto-generated method stub
		for (StaffingDetails staffingDetailsObject : staffingDetails) {
			staffingDetailsObject.setIsActive(1);
			String syntelLanId=rightPriceRepository.getSyntelId(this.auditTrails.getCurrentUser());
			if(syntelLanId.isEmpty() || syntelLanId==null)
			{	
				staffingDetailsObject.setCreatedBy(this.auditTrails.getCurrentUser());
				staffingDetailsObject.setCreatedDate(this.auditTrails.getCurrentTimeStamp());
				staffingDetailsObject.setUpdatedBy(this.auditTrails.getCurrentUser());
				staffingDetailsObject.setUpdatedDate(this.auditTrails.getCurrentTimeStamp());
			}
			else
			{
			staffingDetailsObject.setCreatedBy(syntelLanId);
			staffingDetailsObject.setCreatedDate(this.auditTrails.getCurrentTimeStamp());
			staffingDetailsObject.setUpdatedBy(syntelLanId);
			staffingDetailsObject.setUpdatedDate(this.auditTrails.getCurrentTimeStamp());
			}
		}
		return rightPriceRepository.updateFpDealStaffingDetails(staffingDetails);
	}
	
	@Override
	public ResponseEntity<Object> updateTMDealStaffingDetails(List<StaffingDetails> staffingDetails) {
		// TODO Auto-generated method stub
		for (StaffingDetails staffingDetailsObject : staffingDetails) {
			staffingDetailsObject.setIsActive(1);
			String syntelLanId=rightPriceRepository.getSyntelId(this.auditTrails.getCurrentUser());
			if(syntelLanId.isEmpty() || syntelLanId==null)
			{	
				staffingDetailsObject.setCreatedBy(this.auditTrails.getCurrentUser());
				staffingDetailsObject.setCreatedDate(this.auditTrails.getCurrentTimeStamp());
				staffingDetailsObject.setUpdatedBy(this.auditTrails.getCurrentUser());
				staffingDetailsObject.setUpdatedDate(this.auditTrails.getCurrentTimeStamp());
			}
			else
			{
			staffingDetailsObject.setCreatedBy(syntelLanId);
			staffingDetailsObject.setCreatedDate(this.auditTrails.getCurrentTimeStamp());
			staffingDetailsObject.setUpdatedBy(syntelLanId);
			staffingDetailsObject.setUpdatedDate(this.auditTrails.getCurrentTimeStamp());
			}
		}
		return rightPriceRepository.updateTMDealStaffingDetails(staffingDetails);
	}
	
	@Override
	public ResponseEntity<Object> updateStaffingContractorRole(FPDCRCAndProjectDetails fPDCRCAndProjectDetails) {
		// TODO Auto-generated method stub
		for ( StaffingDealContraactorRole staffingcontractorRoleObj: fPDCRCAndProjectDetails.getStfContrRoleList()) {
			staffingcontractorRoleObj.setIsActive(1);
			String syntelLanId=rightPriceRepository.getSyntelId(this.auditTrails.getCurrentUser());
			if(syntelLanId.isEmpty() || syntelLanId==null)
			{	
				staffingcontractorRoleObj.setCreatedBy(this.auditTrails.getCurrentUser());
				staffingcontractorRoleObj.setCreatedOn(this.auditTrails.getCurrentTimeStamp());
				staffingcontractorRoleObj.setUpdatedBy(this.auditTrails.getCurrentUser());
				staffingcontractorRoleObj.setUpdatedOn(this.auditTrails.getCurrentTimeStamp());
			}
			else
			{
			staffingcontractorRoleObj.setCreatedBy(syntelLanId);
			staffingcontractorRoleObj.setCreatedOn(this.auditTrails.getCurrentTimeStamp());
			staffingcontractorRoleObj.setUpdatedBy(syntelLanId);
			staffingcontractorRoleObj.setUpdatedOn(this.auditTrails.getCurrentTimeStamp());
			}
		}
		return rightPriceRepository.updateStaffingContractorRole(fPDCRCAndProjectDetails);	
		
		
	}


	@Override
	public ResponseEntity<Object> getRateCardName(int customerId, String deal_Id,int cityId, int countryId,int Industry_ID) {
		return rightPriceRepository.getRateCardName(customerId,deal_Id,cityId,countryId,Industry_ID);
	}



	@Override
	public ResponseEntity<Object> getPageTrackerData(int rcId) {
		return rightPriceRepository.getPageTrackerData(rcId);
	}



	@Override
	public ResponseEntity<Object> viewSalary(YearwiseBasicSalary salary) {
		// TODO Auto-generated method stub
		return rightPriceRepository.viewSalary(salary);
	}



	@Override
	public ResponseEntity<Object> addSalarySearch(BasicSalary salary) {
		// TODO Auto-generated method stub
		return rightPriceRepository.addSalarySearch(salary);
	}



	@Override
	public ResponseEntity<Object> insertSalary(BasicSalary[] salary) {
		for (BasicSalary basicAllowance : salary) {
			basicAllowance.setIsActive(1);
			String syntelLanId=rightPriceRepository.getSyntelId(this.auditTrails.getCurrentUser());
			if(syntelLanId.isEmpty() || syntelLanId==null)
			{	
				basicAllowance.setCreatedBy(syntelLanId);
				basicAllowance.setCreatedDate(this.auditTrails.getCurrentTimeStamp());
				basicAllowance.setUpdatedBy(this.auditTrails.getCurrentUser());
				basicAllowance.setUpdatedDate(this.auditTrails.getCurrentTimeStamp());
			}
			else
			{
			basicAllowance.setCreatedBy(syntelLanId);
			basicAllowance.setCreatedDate(this.auditTrails.getCurrentTimeStamp());
			basicAllowance.setUpdatedBy(syntelLanId);
			basicAllowance.setUpdatedDate(this.auditTrails.getCurrentTimeStamp());
			}
		}
		return rightPriceRepository.insertSalary(salary);
	}
	
	@Override
	public ResponseEntity<Object> getPrevDataDeal_Version(int versionId) //(int versionId,int crmDealId,int customerId)
	{
		return rightPriceRepository.getPrevDataDeal_Version(versionId);
	}
	public ResponseEntity<Object> getContry_City(int versionId) //(int versionId,int crmDealId,int customerId)
	{
		return rightPriceRepository.getContry_City(versionId);
	}	
	/*@Override
	public ResponseEntity<Object> showRateCardTable(int rpversionId)
	{
		return rightPriceRepository.showRateCardTable(rpversionId);
	}*/
	
	@Override
	public ResponseEntity<Object> updateSalary(BasicSalary[] salary) {
		// TODO Auto-generated method stub
		return rightPriceRepository.updateSalary(salary);
	}



	@Override
	public ResponseEntity<Object> updateSalarySearch(BasicSalary salary) {
		// TODO Auto-generated method stub
		return rightPriceRepository.updateSalarySearch(salary);
	}
	
	@Override
	public ResponseEntity<Object> onViewMasterRate(MasterRate masterRate) {
		// TODO Auto-generated method stub
		System.out.println("inside onViewMasterRate -- "+masterRate.getCountryId() +"---"+ masterRate.getTransactionYear());
		return rightPriceRepository.onViewMasterRate(masterRate);
	}



	@Override
	public ResponseEntity<Object> getMasterRole() {
		// TODO Auto-generated method stub
		return rightPriceRepository.getMasterRole();
	}



	@Override
	public ResponseEntity<Object> uploadCountry(ArrayList<Country> countryBean,List<Object> errorList) {
		AppLoger.APPLOGGER.info("inside service");
		String errorCause =  "success";
		int rowCount = 0;
		boolean isErrorInTemplate=false;
		HashMap<Object,Object> errorMap = new HashMap<Object,Object>();
		Country nextDetails;
		String syntelLanId=rightPriceRepository.getSyntelId(this.auditTrails.getCurrentUser());
		for (Country countryData : countryBean) {
			countryData.setIsActive(1);
			if(syntelLanId.isEmpty() || syntelLanId==null)
			{	
				countryData.setCreatedBy(this.auditTrails.getCurrentUser());
				countryData.setCreatedDate(this.auditTrails.getCurrentTimeStamp());
				countryData.setUpdatedBy(this.auditTrails.getCurrentUser());
				countryData.setUpdatedDate(this.auditTrails.getCurrentTimeStamp());
			}
			else
			{
			countryData.setCreatedBy(syntelLanId);
			countryData.setCreatedDate(this.auditTrails.getCurrentTimeStamp());
			countryData.setUpdatedBy(syntelLanId);
			countryData.setUpdatedDate(this.auditTrails.getCurrentTimeStamp());
			}
		}
		outerloop:
		for (Country countryData : countryBean) {
			//
			//for validation
			// for getting the row count
			rowCount++;
			AppLoger.APPLOGGER.info("Country Name is............ :::::::: "+ countryData.getCountryName());
			System.out.println("CountryID..........."+countryData.getCountryId());
			System.out.println("Country Name..........."+countryData.getCountryName());
			System.out.println("CurrencyID..........."+countryData.getCurrencyId());
			System.out.println("Currency Code..........."+countryData.getCurrencyCode());
			System.out.println("Country Short Name..........."+countryData.getCountryShortName());
			System.out.println("Country Exchange Rate..........."+countryData.getExchangeRate());
			System.out.println("Country Syntel Facility cost..........."+countryData.getSyntelFacilityCost());
			System.out.println("Country Offshore site..........."+countryData.getIsOffshoreSite());
			System.out.println("Country Name value...................."+countryData.getCountryName());
			System.out.println("Currency ID value......................."+countryData.getCurrencyId());
			
			if(countryData.getCountryName() == null){
				System.out.println("error in Country Name ");
				errorCause = FileUploadErrors.nullEntry("Country Name ",rowCount);
				System.out.println("Error message for Country Name......." +errorCause);
				isErrorInTemplate = true;
			}
			
			if(countryData.getCurrencyId() == null){
				System.out.println("error in CurrencyId");
				errorCause = FileUploadErrors.zeroEntry("Currency ID  ", rowCount);
				System.out.println("Error message for Currency ID......"+errorCause);
				isErrorInTemplate = true;
			}
			
			if(countryData.getCurrencyCode() == null){
				System.out.println("error in CurrencyCode ");
				errorCause = FileUploadErrors.nullEntry("Currency Code ",rowCount);
				System.out.println("Error message for Currency code......." +errorCause);
				isErrorInTemplate = true;
				errorMap.put("ErrorCase2", errorCause);
				
			} else if(countryData.getCurrencyCode()!= null && countryData.getCurrencyCode().length()>3) {
				errorCause = FileUploadErrors.lengthCheck("Currency Code ", rowCount);
				System.out.println("Error message for Currency code....."+errorCause);
				isErrorInTemplate=true;
				errorMap.put("ErrorCase3", errorCause);
			}
			
			if(countryData.getCountryShortName() == null)
			{
				System.out.println("............Error in Country Short Name.........");
				errorCause = FileUploadErrors.nullEntry("Country ShortName  ", rowCount);
				System.out.println("Error message for Country Short Name........"+errorCause);
				isErrorInTemplate = true;
				errorMap.put("ErrorCase4", errorCause);
			} else if(countryData.getCountryShortName().length()>3){
				System.out.println("error in CountryShortName ");
				errorCause = FileUploadErrors.lengthCheck("Length of Country Short Name", rowCount);
				System.out.println("Error message for Country Short name.........." + errorCause);
				isErrorInTemplate = true;
				errorMap.put("ErrorCase5", errorCause);
			}
			if(countryData.getExchangeRate() == 0){
				System.out.println("error in ExchangeRate ");
				errorCause = FileUploadErrors.zeroEntry("ExchangeRate ", rowCount);
				System.out.println("Error message for Exchange Rate......"+errorCause);
				isErrorInTemplate = true;
				errorMap.put("ErrorCase6", errorCause);
			}
			
			if(countryData.getSyntelFacilityCost() == 0){
				System.out.println("error in SyntelFacilityCost ");
				errorCause= FileUploadErrors.zeroEntry("Facility cost ", rowCount);
				System.out.println("Error message for Syntel facility cost...."+errorCause);
				isErrorInTemplate = true;
				errorMap.put("ErrorCase7", errorCause);
			}
			
			System.out.println("Country Offshore value is........... "+ countryData.getIsOffshoreSite());
			if(countryData.getIsOffshoreSite()>1){
				System.out.println("error in OffshoreSitegetIsOffshoreSite coulmn");
				errorCause = FileUploadErrors.zeroEntryOff("OffshoreSite ", countryData.getIsOffshoreSite());
				System.out.println("The Error Message on zero Entry is............. "+ errorCause);
				isErrorInTemplate = true;
				errorMap.put("ErrorCase8", errorCause);
			}
			
			for (int i = 1; i<=countryBean.size();i++) {
				for(int j = i+1; j<countryBean.size();j++ ) {
					if(countryBean.get(i).getCountryName().equals(countryBean.get(j).getCountryName())){
						errorCause =FileUploadErrors.duplicateEntry(countryBean.get(i).getCountryName());
						isErrorInTemplate = true;
						errorMap.put("ErrorCase9", errorCause);
						//errorList.add(errorMap);
						break outerloop ; 
					}
				}
			}
			}
		errorList.add(errorMap.values());
		System.out.println("The Error Map value is......... "+ errorMap.values().toString());
		
		if(isErrorInTemplate){
			System.out.println(" In isErrorInTemplate The Error Message on zero Entry is............. "+ errorCause);
			return ResponseEntity.accepted().body(errorList);
		}
		else {
		System.out.println("Country Bean Size.........."+countryBean.size());
		return rightPriceRepository.uploadCountry(countryBean); 
	}
	}
	
	@Override
	public ResponseEntity<Object> uploadCity(ArrayList<City_STG> arrCitySTG) {
		AppLoger.APPLOGGER.info("inside service uploadCity");

		try
		{
			for (City_STG objCity : arrCitySTG) 
			{
				objCity.setIsActive(1);
				String syntelLanId=rightPriceRepository.getSyntelId(this.auditTrails.getCurrentUser());
				if(syntelLanId.isEmpty() || syntelLanId==null)
				{	
					objCity.setCreatedBy(this.auditTrails.getCurrentUser());
					objCity.setCreatedDate(this.auditTrails.getCurrentTimeStamp());
					objCity.setUpdatedBy(this.auditTrails.getCurrentUser());
					objCity.setUpdatedDate(this.auditTrails.getCurrentTimeStamp());
				}
				else
				{
				objCity.setCreatedBy(syntelLanId);
				objCity.setCreatedDate(this.auditTrails.getCurrentTimeStamp());
				objCity.setUpdatedBy(syntelLanId);
				objCity.setUpdatedDate(this.auditTrails.getCurrentTimeStamp());
				}
			}
			return rightPriceRepository.uploadCity(arrCitySTG);
		}
		catch(Exception e){
			throw e;
		}
	}
	
	
	@Override
	public ResponseEntity<Object> onMasterRateSearch(MasterRate masterRate) {
		// TODO Auto-generated method stub
		return rightPriceRepository.onMasterRateSearch(masterRate);
	}



	@Override
	public ResponseEntity<Object> onUpdateMasterRateSearch(MasterRate masterRate) {
		// TODO Auto-generated method stub
		return rightPriceRepository.onUpdateMasterRateSearch(masterRate);
	}



	@Override
	public ResponseEntity<Object> onAddMasterRate(MasterRate masterRate) {
		// TODO Auto-generated method stub
		masterRate.setIsActive(1);
		String syntelLanId=rightPriceRepository.getSyntelId(this.auditTrails.getCurrentUser());
		if(syntelLanId.isEmpty() || syntelLanId==null)
		{	
			masterRate.setCreatedBy(this.auditTrails.getCurrentUser());
			masterRate.setCreatedOn(this.auditTrails.getCurrentTimeStamp());
			masterRate.setUpdatedBy(this.auditTrails.getCurrentUser());
			masterRate.setUpdatedOn(this.auditTrails.getCurrentTimeStamp());
		}
		else
		{
		masterRate.setCreatedBy(syntelLanId);
		masterRate.setCreatedOn(this.auditTrails.getCurrentTimeStamp());
		masterRate.setUpdatedBy(syntelLanId);
		masterRate.setUpdatedOn(this.auditTrails.getCurrentTimeStamp());
		}
		return rightPriceRepository.onAddMasterRate(masterRate);
	}



	@Override
	public ResponseEntity<Object> onUpdateMasterRate(MasterRate masterRate) {
		// TODO Auto-generated method stub
		return rightPriceRepository.onUpdateMasterRate(masterRate);
	}



	@Override
	public ResponseEntity<Object> getOnsiteFacilityCost(int countryId,int cityId, int towerId) {
		// TODO Auto-generated method stub
		return rightPriceRepository.getOnsiteFaclityCost(countryId,cityId,towerId);
	}



	@Override
	public ResponseEntity<Object> saveOnsiteCostData(FPDCRCAndProjectDetails hardWareCostData) {
		// TODO Auto-generated method stub
		String syntelLanId=rightPriceRepository.getSyntelId(this.auditTrails.getCurrentUser());
		if(hardWareCostData.getOnlineCostDetails().size()>0){
			for (RPDealHardwareSoftwareCost hardWareCostDetails : hardWareCostData.getOnlineCostDetails()) {
				AppLoger.APPLOGGER.info(" 1 -------> "+hardWareCostDetails.toString());
				hardWareCostDetails.setIsActive(1);
				if(syntelLanId.isEmpty() || syntelLanId==null)
				{	
					hardWareCostDetails.setCreatedBy(this.auditTrails.getCurrentUser());
					hardWareCostDetails.setCreatedDate(this.auditTrails.getCurrentTimeStamp());
					hardWareCostDetails.setUpdatedBy(this.auditTrails.getCurrentUser());
					hardWareCostDetails.setUpdatedDate(this.auditTrails.getCurrentTimeStamp());
				}
				else
				{
				hardWareCostDetails.setCreatedBy(syntelLanId);
				hardWareCostDetails.setCreatedDate(this.auditTrails.getCurrentTimeStamp());
				hardWareCostDetails.setUpdatedBy(syntelLanId);
				hardWareCostDetails.setUpdatedDate(this.auditTrails.getCurrentTimeStamp());
				}
				//hardWareCostDetails.setDealVersionId(fPDCRCAndProjectDetails.getDealVersionId());
			}
		}
		
		if(hardWareCostData.getTravelRelocationCostDetails().size()>0){
			for (RPDealRelocationDetails relocationDetails : hardWareCostData.getTravelRelocationCostDetails()) {
				AppLoger.APPLOGGER.info(" 1 -------> "+relocationDetails.toString());
				relocationDetails.setYear1Cost(0.0);
				relocationDetails.setYear2Cost(0.0);
				relocationDetails.setYear3Cost(0.0);
				relocationDetails.setYear4Cost(0.0);
				relocationDetails.setYear5Cost(0.0);
				relocationDetails.setIsActive(1);
				if(syntelLanId.isEmpty() || syntelLanId==null)
				{	
					relocationDetails.setCreatedBy(this.auditTrails.getCurrentUser());
					relocationDetails.setCreatedDate(this.auditTrails.getCurrentTimeStamp());
					relocationDetails.setUpdatedBy(this.auditTrails.getCurrentUser());
					relocationDetails.setUpdatedDate(this.auditTrails.getCurrentTimeStamp());
				}
				else
				{
				relocationDetails.setCreatedBy(syntelLanId);
				relocationDetails.setCreatedDate(this.auditTrails.getCurrentTimeStamp());
				relocationDetails.setUpdatedBy(syntelLanId);
				relocationDetails.setUpdatedDate(this.auditTrails.getCurrentTimeStamp());
				}
				//hardWareCostDetails.setDealVersionId(fPDCRCAndProjectDetails.getDealVersionId());
			}
		}
		return rightPriceRepository.saveOnsiteCostData(hardWareCostData);
	}



	@Override
	public ResponseEntity<Object> getRelocationCostDetails(int countryId, int cityId, int towerId) {
		// TODO Auto-generated method stub
		return rightPriceRepository.getRelocationCostDetails(countryId,cityId,towerId);
	}

	@Override
	public ResponseEntity<Object> uploadSalary(ArrayList<BasicSalarySTG> salary) {
		try{
		for (BasicSalarySTG salaryData : salary) {
			salaryData.setIsActive(1);
			String syntelLanId=rightPriceRepository.getSyntelId(this.auditTrails.getCurrentUser());
			if(syntelLanId.isEmpty() || syntelLanId==null)
			{	
				salaryData.setCreatedBy(this.auditTrails.getCurrentUser());
				salaryData.setCreatedOn(this.auditTrails.getCurrentTimeStamp());
				salaryData.setUpdatedBy(this.auditTrails.getCurrentUser());
				salaryData.setUpdatedOn(this.auditTrails.getCurrentTimeStamp());
			}
			else
			{
			salaryData.setCreatedBy(syntelLanId);
			salaryData.setCreatedOn(this.auditTrails.getCurrentTimeStamp());
			salaryData.setUpdatedBy(syntelLanId);
			salaryData.setUpdatedOn(this.auditTrails.getCurrentTimeStamp());
			}
		}
		return rightPriceRepository.uploadSalary(salary);
		}
		catch(Exception e){
			throw e;
		}
	}


	@Override
	public ResponseEntity<Object> getShiftWorkingDetails(int countryId, int cityId, int towerId) {
		// TODO Auto-generated method stub
		return rightPriceRepository.getShiftWorkingDetails(countryId,cityId,towerId);
	}

	@Override
	public Integer getEmpDesgIdFromEmpDesgDesc(String strDescription) {
		return rightPriceRepository.getEmpDesgIdFromEmpDesgDesc(strDescription);
	}

	@Override
	public Integer getCountryIdFromCountryName(String strCountryName) {
		return rightPriceRepository.getCountryIdFromCountryName(strCountryName);
	}

	@Override
	public Integer getPracticeIdFromPracticeDesc(String strDesc) {
		return rightPriceRepository.getPracticeIdFromPracticeDesc(strDesc);
	}



	@Override
	public ResponseEntity<Object> uploadMannualRC(ArrayList<RateCardRolesSTG> arrLiRcRolesSTG) {
		try
		{
			for (RateCardRolesSTG objRcRolesStg : arrLiRcRolesSTG) 
			{
				String syntelLanId=rightPriceRepository.getSyntelId(this.auditTrails.getCurrentUser());
				if(syntelLanId.isEmpty() || syntelLanId==null)
				{	
					objRcRolesStg.setCreatedBy(this.auditTrails.getCurrentUser());
					objRcRolesStg.setCreatedOn(this.auditTrails.getCurrentTimeStamp());
					objRcRolesStg.setUpdatedBy(this.auditTrails.getCurrentUser());
					objRcRolesStg.setUpdatedOn(this.auditTrails.getCurrentTimeStamp());
				}
				else
				{
				objRcRolesStg.setCreatedBy(syntelLanId);
				objRcRolesStg.setCreatedOn(this.auditTrails.getCurrentTimeStamp());
				objRcRolesStg.setUpdatedBy(syntelLanId);
				objRcRolesStg.setUpdatedOn(this.auditTrails.getCurrentTimeStamp());
				}
			}
			return rightPriceRepository.uploadMannualRC(arrLiRcRolesSTG);
		}
		catch(Exception e){
			throw e;
		}
	}

	@Override
	public boolean CheckMasterRole(String role) {
		return rightPriceRepository.CheckMasterRole(role);
	}



	@Override
	public ResponseEntity<Object> addRPAccessControlData(FPDCRCAndProjectDetails accessControl) {
		if(accessControl.getRpAccessControls().size()>0){
			for(AccessControl rpAccessContrl : accessControl.getRpAccessControls()) {
				rpAccessContrl.setIsActive(1);
				String syntelLanId=rightPriceRepository.getSyntelId(this.auditTrails.getCurrentUser());
				if(syntelLanId.isEmpty() || syntelLanId==null)
				{	
					rpAccessContrl.setCreatedBy(this.auditTrails.getCurrentUser());
					rpAccessContrl.setCreatedOn(this.auditTrails.getCurrentTimeStamp());
					rpAccessContrl.setUpdatedBy(this.auditTrails.getCurrentUser());
					rpAccessContrl.setUpdatedOn(this.auditTrails.getCurrentTimeStamp());
				}
				else
				{
				rpAccessContrl.setCreatedBy(syntelLanId);
				rpAccessContrl.setCreatedOn(this.auditTrails.getCurrentTimeStamp());
				rpAccessContrl.setUpdatedBy(syntelLanId);
				rpAccessContrl.setUpdatedOn(this.auditTrails.getCurrentTimeStamp());
				}
			}
		}
		return rightPriceRepository.addRPAccessControlData(accessControl);
		}



	@Override
	public ResponseEntity<Object> getGFTCustomer() {
		return rightPriceRepository.getGFTCustomer();
	}



	@Override
	public ResponseEntity<Object> uploadMasterAllowance(ArrayList<BasicAllowanceSTG> arrLiBasicAllowanceSTG) {
		try {

			for (BasicAllowanceSTG objBasicAllowanceStg : arrLiBasicAllowanceSTG) {
				objBasicAllowanceStg.setStatusModel(1);
				String syntelLanId=rightPriceRepository.getSyntelId(this.auditTrails.getCurrentUser());
				if(syntelLanId.isEmpty() || syntelLanId==null)
				{	
					objBasicAllowanceStg.setCreatedBy(this.auditTrails.getCurrentUser());
					objBasicAllowanceStg.setCreatedDate(this.auditTrails.getCurrentTimeStamp());
					objBasicAllowanceStg.setUpdatedBy(this.auditTrails.getCurrentUser());
					objBasicAllowanceStg.setUpdatedDate(this.auditTrails.getCurrentTimeStamp());
				}
				else
				{
				objBasicAllowanceStg.setCreatedBy(syntelLanId);
				objBasicAllowanceStg.setCreatedDate(this.auditTrails.getCurrentTimeStamp());
				objBasicAllowanceStg.setUpdatedBy(syntelLanId);
				objBasicAllowanceStg.setUpdatedDate(this.auditTrails.getCurrentTimeStamp());
				}
			}
			return rightPriceRepository.uploadMasterAllowance(arrLiBasicAllowanceSTG);
		} catch (Exception e) {
			throw e;
		}
	}

	/*public ResponseEntity<Object> getRateCardContractorRole(int cityId, int countryId,int masterRoleId, int rateCardId) {
		// TODO Auto-generated method stub
		return rightPriceRepository.getRateCardContractorRole(cityId,countryId,masterRoleId,rateCardId);
	}
*/


	@Override
	public ResponseEntity<Object> insertContractorRole(FPDCRCAndProjectDetails contractorRole) {
		if(contractorRole.getRpContractorRoles().size()>0){
			for(ContractorRole rpcontractorRole : contractorRole.getRpContractorRoles()) {
				rpcontractorRole.setIsActive(1);
				String syntelLanId=rightPriceRepository.getSyntelId(this.auditTrails.getCurrentUser());
				if(syntelLanId.isEmpty() || syntelLanId==null)
				{	
					rpcontractorRole.setCreatedBy(this.auditTrails.getCurrentUser());
					rpcontractorRole.setCreatedOn(this.auditTrails.getCurrentTimeStamp());
					rpcontractorRole.setUpdatedBy(this.auditTrails.getCurrentUser());
					rpcontractorRole.setUpdatedOn(this.auditTrails.getCurrentTimeStamp());
				}
				else
				{
				rpcontractorRole.setCreatedBy(syntelLanId);
				rpcontractorRole.setCreatedOn(this.auditTrails.getCurrentTimeStamp());
				rpcontractorRole.setUpdatedBy(syntelLanId);
				rpcontractorRole.setUpdatedOn(this.auditTrails.getCurrentTimeStamp());
				}
			}
		}
		return rightPriceRepository.insertContractorRole(contractorRole);
	}



	@Override
	public ResponseEntity<Object> getContractorDetails(int cityId, int rateCardId) {
		return rightPriceRepository.getContractorDetails(cityId,rateCardId);
	}


	@Override
	public ResponseEntity<Object> getDealDetailById(String dealId, int rpVersionId) {
		// TODO Auto-generated method stub
		return rightPriceRepository.getDealDetailById(dealId,rpVersionId);
	}
	@Override
	public ResponseEntity<Object> getTMFinalizeDealComment(int rpVersionId) {
		// TODO Auto-generated method stub
		return rightPriceRepository.getTMFinalizeDealComment(rpVersionId);
	}


	@Override
	public ResponseEntity<Object> updateApprovalDataForDeal(Deal dealDetails) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		SAMLCredential credential = (SAMLCredential) authentication.getCredentials();
		Assertion assertion = credential.getAuthenticationAssertion();
		HashMap<String, String> map = new HashMap<>();
	    AppLoger.APPLOGGER.info("1");
	    List<AttributeStatement> attributeStatements = assertion.getAttributeStatements();
	    AppLoger.APPLOGGER.info("attributeStatements are : " +attributeStatements);
	    for (AttributeStatement statement : attributeStatements) {
	      for (Attribute attribute : statement.getAttributes()) {
	        XMLObject xmlObject = attribute.getAttributeValues().get(0);
	        AppLoger.APPLOGGER.info("XMLObject is : " +xmlObject);
	        if (xmlObject instanceof XSStringImpl) {
	          map.put(attribute.getName(), ((XSStringImpl) xmlObject).getValue());
	          AppLoger.APPLOGGER.info("Map is : " +map);
	        } else {
	          map.put(attribute.getName(), ((XSAnyImpl) xmlObject).getTextContent());
	          AppLoger.APPLOGGER.info("Map is : " +map);
	        }
	      }
	    }
	    String dasID = map.get("uid");
	    String fName = map.get("firstname");
	    String lname = map.get("surname");
	    String atosMailID = map.get("mail");
	    String requesterName = fName+" "+lname;
		dealDetails.setIsActive(1);
		String syntelLanId=rightPriceRepository.getSyntelId(this.auditTrails.getCurrentUser());
		if(syntelLanId.isEmpty() || syntelLanId==null)
		{	
			dealDetails.setUpdateAtosEmailId(atosMailID);
			dealDetails.setUpdateEmployeeName(requesterName);
			dealDetails.setUpdatedBy(this.auditTrails.getCurrentUser());
			dealDetails.setUpdatedDate(this.auditTrails.getCurrentTimeStamp());
		}
		else
		{
			dealDetails.setUpdateAtosEmailId(atosMailID);
			dealDetails.setUpdateEmployeeName(requesterName);
			dealDetails.setUpdatedBy(syntelLanId);
			dealDetails.setUpdatedDate(this.auditTrails.getCurrentTimeStamp());
		}
		return rightPriceRepository.updateApprovalDataForDeal(dealDetails);
	}



	@Override
	public ResponseEntity<Object> getSyntelPremiumById(int cityId) {
		// TODO Auto-generated method stub
		return rightPriceRepository.getSyntelPremiumById(cityId);
	}


	@Override
	public ResponseEntity<Object> viewSalaryExportDetails(int countryId, int practiceId, int year) {
		return rightPriceRepository.viewSalaryExportDetails(countryId,practiceId,year);
	}



	@Override
	public ResponseEntity<Object> viewRPRateDetails(int countryId, int year) {
		return rightPriceRepository.viewRPRateDetails(countryId,year);
	}



	@Override
	public ResponseEntity<Object> getMasterRateRoles(int countryId) {
		return rightPriceRepository.getMasterRateRoles(countryId);
	}

	@Override
	public ResponseEntity<Object> getRateCardExportDetails(int customerId) {
		return rightPriceRepository.getRateCardExportDetails(customerId);
	}
	@Override
	public ResponseEntity<Object>  getRatecardSummaryData(int rpVrsId) {
		return rightPriceRepository.getRatecardSummaryData(rpVrsId);
	}
	@Override
	public ResponseEntity<Object>  getStaffingPercentage(int rpVrsId) {
		return rightPriceRepository.getStaffingPercentage(rpVrsId);
	}

	@Override
	public ResponseEntity<Object>  getFinal_GM_Per(int rpVrsId) {
		return rightPriceRepository.getFinal_GM_Per(rpVrsId);
	}
	@Override
	public ResponseEntity<Object>  getFinal_GM_Per_Sum(int rpVrsId) {
		return rightPriceRepository.getFinal_GM_Per_Sum(rpVrsId);
	}
	@Override
	public ResponseEntity<Object>  updateRP_Deal_Table(int rpVrsId, Double calc_total_GM, Double tCVModelValue) {
		
		return rightPriceRepository.updateRP_Deal_Table(rpVrsId, calc_total_GM,tCVModelValue);
	}
	@Override
	public ResponseEntity<Object>  getStaffingDone(int rpVrsId) {
		return rightPriceRepository.getStaffingDone(rpVrsId);
	}
	
	@Override
	public ResponseEntity<Object>  get_onloadCoun_City(int rpVrsId) {
		return rightPriceRepository.get_onloadCoun_City(rpVrsId);
	}
	
	@Override
	public ResponseEntity<Object> uploadMasterRPRate(ArrayList<MasterRateSTG> arrLiMasterRPRate) 
	{
		try
		{
			for (MasterRateSTG objMasterRPRate : arrLiMasterRPRate) 
			{
				objMasterRPRate.setIsActive(1);
				String syntelLanId=rightPriceRepository.getSyntelId(this.auditTrails.getCurrentUser());
				if(syntelLanId.isEmpty() || syntelLanId==null)
				{	
					objMasterRPRate.setCreatedBy(this.auditTrails.getCurrentUser());
					objMasterRPRate.setCreatedOn(this.auditTrails.getCurrentTimeStamp());
					objMasterRPRate.setUpdatedBy(this.auditTrails.getCurrentUser());
					objMasterRPRate.setUpdatedOn(this.auditTrails.getCurrentTimeStamp());
				}
				else
				{
				objMasterRPRate.setCreatedBy(syntelLanId);
				objMasterRPRate.setCreatedOn(this.auditTrails.getCurrentTimeStamp());
				objMasterRPRate.setUpdatedBy(syntelLanId);
				objMasterRPRate.setUpdatedOn(this.auditTrails.getCurrentTimeStamp());
				}
			}
			return rightPriceRepository.uploadMasterRPRate(arrLiMasterRPRate);
		}
		catch(Exception e){
			throw e;
		}
	
	}

	@Override
	public ResponseEntity<Object> getCustomers(int verticalId) {
		return rightPriceRepository.getCustomers(verticalId);
	}

	@Override
	public ResponseEntity<Object> deallocateVerticalMemebers(VerticalMembers[] verticalMember) {
		return rightPriceRepository.deallocateVerticalMemebers(verticalMember);
	}


	/*@Override
	public ResponseEntity<Object> getQMVertical(VerticalRiskManagersMembers verticalQMDetails) {
		return rightPriceRepository.getQMVertical(verticalQMDetails);
	}


	@Override
	public ResponseEntity<Object> deallocateVerticalQM(VerticalRiskManagersMembers[] verticalQM) {
		return rightPriceRepository.deallocateVerticalQM(verticalQM);
	}*/
	
	@Override
	public ResponseEntity<Object> getRateCardCalDetails(int cityId, int rateCardId) {
		// TODO Auto-generated method stub
		return rightPriceRepository.getRateCardCalDetails(cityId,rateCardId);
	}

	@Override
	public ResponseEntity<Object> saveCalculatedMarginData(RateCardManualMarginCal[] rateCardMarginData) {
		
		for (RateCardManualMarginCal rateCardData : rateCardMarginData) {
			rateCardData.setIsActive(1);
			String syntelLanId=rightPriceRepository.getSyntelId(this.auditTrails.getCurrentUser());
			if(syntelLanId.isEmpty() || syntelLanId==null)
			{	
				rateCardData.setCreatedBy(this.auditTrails.getCurrentUser());
				rateCardData.setCreatedOn(this.auditTrails.getCurrentTimeStamp());
				rateCardData.setUpdatedBy(this.auditTrails.getCurrentUser());
				rateCardData.setUpdatedOn(this.auditTrails.getCurrentTimeStamp());
			}
			else
			{
			rateCardData.setCreatedBy(syntelLanId);
			rateCardData.setCreatedOn(this.auditTrails.getCurrentTimeStamp());
			rateCardData.setUpdatedBy(syntelLanId);
			rateCardData.setUpdatedOn(this.auditTrails.getCurrentTimeStamp());
			}
		}
		return rightPriceRepository.saveCalculateMarginData(rateCardMarginData);
	}

	@Override
	public ResponseEntity<Object> getRateCardManualUploadCountry(int rateCardId) {
		// TODO Auto-generated method stub
		return rightPriceRepository.getRateCardManualUploadCountry(rateCardId);
	}

	@Override
	public ResponseEntity<Object> getRateCardUploadCity(int rcId, int countryId) {
		return rightPriceRepository.getRateCardUploadCity(rcId,countryId);
	}


	@Override
	public ResponseEntity<Object> getTaxParamList() {
		return rightPriceRepository.getTaxParamList();
	}



	@Override
	public ResponseEntity<Object> onAssumptionsViewParameterForm(Deduction deduction) {
		// TODO Auto-generated method stub
		return rightPriceRepository.onAssumptionsViewParameterForm(deduction);
	}



	@Override
	public ResponseEntity<Object> onAddAPSearch(int countryId, int visaTypeId, int year) {
		return rightPriceRepository.onAddAPSearch(countryId,visaTypeId,year);
	}



	@Override
	public ResponseEntity<Object> onUpdateAPSearch(Deduction deduction) {
		// TODO Auto-generated method stub
		return rightPriceRepository.onUpdateAPSearch(deduction);
	}



	@Override
	public ResponseEntity<Object> getAssumptionsParamList() {
		// TODO Auto-generated method stub
		return rightPriceRepository.getAssumptionsParamList();
	}



	@Override
	public ResponseEntity<Object> saveTaxParam(Deduction[] deduction) {
		for (Deduction deduction2 : deduction) {
			deduction2.setIsActive(1);
			deduction2.setDeductionCatId(2);
			String syntelLanId=rightPriceRepository.getSyntelId(this.auditTrails.getCurrentUser());
			if(syntelLanId.isEmpty() || syntelLanId==null)
			{	
				deduction2.setCreatedBy(this.auditTrails.getCurrentUser());
				deduction2.setCreatedOn(this.auditTrails.getCurrentTimeStamp());
				deduction2.setLastUpdatedBy(this.auditTrails.getCurrentUser());
				deduction2.setLastUpdatedOn(this.auditTrails.getCurrentTimeStamp());
			}
			else
			{
			deduction2.setCreatedBy(syntelLanId);
			deduction2.setCreatedOn(this.auditTrails.getCurrentTimeStamp());
			deduction2.setLastUpdatedBy(syntelLanId);
			deduction2.setLastUpdatedOn(this.auditTrails.getCurrentTimeStamp());
			}
		}
		return rightPriceRepository.saveTaxParam(deduction);
	}
	
	@Override
	public ResponseEntity<Object> updateTaxParam(Deduction[] deduction) {
		for (Deduction deduction2 : deduction) {
		String syntelLanId=rightPriceRepository.getSyntelId(this.auditTrails.getCurrentUser());
		if(syntelLanId.isEmpty() || syntelLanId==null)
		{	
			deduction2.setLastUpdatedBy(this.auditTrails.getCurrentUser());
			deduction2.setLastUpdatedOn(this.auditTrails.getCurrentTimeStamp());
		}
		else
		{
		deduction2.setLastUpdatedBy(syntelLanId);
		deduction2.setLastUpdatedOn(this.auditTrails.getCurrentTimeStamp());
		}
		}
		return rightPriceRepository.updateTaxParam(deduction);
	}
	@Override
	public ResponseEntity<Object> saveAssumptionsParam(Deduction[] deduction) {
		for (Deduction deduction2 : deduction) {
			deduction2.setIsActive(1);
			deduction2.setDeductionCatId(3);
			String syntelLanId=rightPriceRepository.getSyntelId(this.auditTrails.getCurrentUser());
			if(syntelLanId.isEmpty() || syntelLanId==null)
			{	
				deduction2.setCreatedBy(this.auditTrails.getCurrentUser());
				deduction2.setCreatedOn(this.auditTrails.getCurrentTimeStamp());
				deduction2.setLastUpdatedBy(this.auditTrails.getCurrentUser());
				deduction2.setLastUpdatedOn(this.auditTrails.getCurrentTimeStamp());
			}
			else
			{
			deduction2.setCreatedBy(syntelLanId);
			deduction2.setCreatedOn(this.auditTrails.getCurrentTimeStamp());
			deduction2.setLastUpdatedBy(syntelLanId);
			deduction2.setLastUpdatedOn(this.auditTrails.getCurrentTimeStamp());
			}
		}
		return rightPriceRepository.saveAssumptionsParam(deduction);
	}
	
	@Override
	public ResponseEntity<Object> updateAssumptionsParam(Deduction[] deduction) {
		for (Deduction deduction2 : deduction) {
			String syntelLanId=rightPriceRepository.getSyntelId(this.auditTrails.getCurrentUser());
			if(syntelLanId.isEmpty() || syntelLanId==null)
			{	
				deduction2.setLastUpdatedBy(this.auditTrails.getCurrentUser());
				deduction2.setLastUpdatedOn(this.auditTrails.getCurrentTimeStamp());
			}
			else
			{
			deduction2.setLastUpdatedBy(syntelLanId);
			deduction2.setLastUpdatedOn(this.auditTrails.getCurrentTimeStamp());
			}
		}
		return rightPriceRepository.updateAssumptionsParam(deduction);
	}

	@Override
	public ResponseEntity<Object> viewMasterRolesExportDetails(int practiceId, int subpracticeId) {
		return rightPriceRepository.getRoles(practiceId,subpracticeId);
	}
	
	@Override
	public ResponseEntity<Object> uploadManualRateCard(MultipartFile file, String fileName,
			InputStream uploadedInputStream,int rpDealVersionId,int dealTowerId,int countryId) {
		
		try {
			/*FileObjectTable fileObj = new FileObjectTable();
			fileObj.setActiveStatus(1);
			fileObj.setFileName(fileName);
			fileObj.setOriginalFileName(file.getOriginalFilename());
			fileObj.setObject(file.getBytes());
			fileObj.setCreatedBy(syntelLanId);
			fileObj.setCreatedOn(this.auditTrails.getCurrentTimeStamp());
			fileObj.setLastUpdatedBy(syntelLanId);
			fileObj.setLastUpdatedOn(this.auditTrails.getCurrentTimeStamp());*/
			FileObjectTable fileObj = new FileObjectTable();
			FPManualDealAttachment fpData=new FPManualDealAttachment();
			String newFileName = "";
			fileObj.setActiveStatus(1);
			fileObj.setFileName(fileName);
			if(file.getOriginalFilename().contains("\\"))
			{
				String strMain = file.getOriginalFilename();
				String[] arrSplit = new String[100];
			    arrSplit = strMain.split("\\\\");
			    
			    System.out.println(arrSplit.length+" "+arrSplit[(arrSplit.length-1)]);
			    int size=arrSplit.length;
			    newFileName = arrSplit[(arrSplit.length-1)];
			    fileObj.setOriginalFileName(newFileName);
			    fpData.setFileName(newFileName);
			    AppLoger.APPLOGGER.info("File length : count1");
			}
			else{
			fileObj.setOriginalFileName(file.getOriginalFilename());
			  AppLoger.APPLOGGER.info("File length : count2");
			}
			fileObj.setObject(file.getBytes());
			String syntelLanId=rightPriceRepository.getSyntelId(this.auditTrails.getCurrentUser());
			if(syntelLanId.isEmpty() || syntelLanId==null)
			{	
				fileObj.setCreatedBy(this.auditTrails.getCurrentUser());
				fileObj.setCreatedOn(this.auditTrails.getCurrentTimeStamp());
				fileObj.setLastUpdatedBy(this.auditTrails.getCurrentUser());
				fileObj.setLastUpdatedOn(this.auditTrails.getCurrentTimeStamp());
				fpData.setCreatedBy(this.auditTrails.getCurrentUser());
				fpData.setCreatedOn(this.auditTrails.getCurrentTimeStamp());
				fpData.setDateStamp(this.auditTrails.getCurrentTimeStamp());
				fpData.setUpdatedBy(this.auditTrails.getCurrentUser());
				fpData.setUpdatedOn(this.auditTrails.getCurrentTimeStamp());
				  AppLoger.APPLOGGER.info("File length : count3");
			}
			else
			{
			fileObj.setCreatedBy(syntelLanId);
			fileObj.setCreatedOn(this.auditTrails.getCurrentTimeStamp());
			fileObj.setLastUpdatedBy(syntelLanId);
			fileObj.setLastUpdatedOn(this.auditTrails.getCurrentTimeStamp());
			fpData.setCreatedBy(syntelLanId);
			fpData.setCreatedOn(this.auditTrails.getCurrentTimeStamp());
			fpData.setDateStamp(this.auditTrails.getCurrentTimeStamp());
			fpData.setUpdatedBy(syntelLanId);
			fpData.setUpdatedOn(this.auditTrails.getCurrentTimeStamp());
			  AppLoger.APPLOGGER.info("File length : count4");
			}
			fpData.setIsActive(1);
			fpData.setRpDealVersionId(rpDealVersionId);
			fpData.setDealAutoTowerId(dealTowerId);
			fpData.setDocType(0);
			return rightPriceRepository.uploadManualRateCard(file,fileName,uploadedInputStream,fileObj,fpData, countryId);
		} catch (IOException e) {
			AppLoger.APPLOGGER.info("Exception occured while inserting object " + e.getMessage());
			return ResponseEntity.status(205)
				       .contentType(MediaType.TEXT_PLAIN)
				       .body("Currently We are facing technical issues, please try again later.");
		}
		}
	
	@Override
	public ResponseEntity<Object> getrateCardId() {
		return rightPriceRepository.getrateCardId();
	}
	
	@Override
	public ResponseEntity<Object> getSummaryOld(int rcId) {
		// TODO Auto-generated method stub
		return rightPriceRepository.getSummaryOld(rcId);
	}
	@Override
	public ResponseEntity<Object> getYoyIncrement(int rcId) {
		return rightPriceRepository.getYoyIncrement(rcId);
	}



	@Override
	public ResponseEntity<Object> uploadManualRateCardFile(MultipartFile file, String fileName,
			InputStream uploadedInputStream, int rcId) {
		try {
			FileObjectTable fileObj = new FileObjectTable();
			fileObj.setActiveStatus(1);
			fileObj.setFileName(fileName);
			fileObj.setOriginalFileName(file.getOriginalFilename());
			fileObj.setObject(file.getBytes());
			String syntelLanId=rightPriceRepository.getSyntelId(this.auditTrails.getCurrentUser());
			if(syntelLanId.isEmpty() || syntelLanId==null)
			{	
				fileObj.setCreatedBy(this.auditTrails.getCurrentUser());
				fileObj.setCreatedOn(this.auditTrails.getCurrentTimeStamp());
				fileObj.setLastUpdatedBy(this.auditTrails.getCurrentUser());
				fileObj.setLastUpdatedOn(this.auditTrails.getCurrentTimeStamp());
			}
			else
			{
			fileObj.setCreatedBy(syntelLanId);
			fileObj.setCreatedOn(this.auditTrails.getCurrentTimeStamp());
			fileObj.setLastUpdatedBy(syntelLanId);
			fileObj.setLastUpdatedOn(this.auditTrails.getCurrentTimeStamp());
			}
			return rightPriceRepository.uploadManualRateCardFile(file,fileName,uploadedInputStream,fileObj,rcId);
		} catch (IOException e) {
			AppLoger.APPLOGGER.info("Exception occured while inserting object " + e.getMessage());
			return ResponseEntity.status(205)
				       .contentType(MediaType.TEXT_PLAIN)
				       .body("Currently We are facing technical issues, please try again later.");
		}
	}



	@Override
	public ResponseEntity<Object> updateRateCardStatus(int rcId) {
		return rightPriceRepository.updateRateCardStatus(rcId);
	}



	@Override
	public ResponseEntity<Object> getAddRoles() {
		return rightPriceRepository.getAddRoles();
	}
	
	@Override
	public ResponseEntity<Object> getSummaryCountry(int rateCardId) {
		return rightPriceRepository.getSummaryCountry(rateCardId);
	}

	@Override
	public ResponseEntity<Object> saveRateCardDetails(RateCardDetails rateCardDetails) {
		rateCardDetails.setIsActive(1);
		String syntelLanId=rightPriceRepository.getSyntelId(this.auditTrails.getCurrentUser());
		if(syntelLanId.isEmpty() || syntelLanId==null)
		{	
			rateCardDetails.setUpdatedBy(this.auditTrails.getCurrentUser());
			rateCardDetails.setUpdatedOn(this.auditTrails.getCurrentTimeStamp());
		}
		else
		{
		rateCardDetails.setUpdatedBy(syntelLanId);
		rateCardDetails.setUpdatedOn(this.auditTrails.getCurrentTimeStamp());
		}
		return rightPriceRepository.saveRateCardDetails(rateCardDetails);
	}



	@Override
	public ResponseEntity<Object> getVisaTypes(int countryId) {
		return rightPriceRepository.getVisaTypes(countryId);
	}

	@Override
	public ResponseEntity<Object> getCustomer() {
		// TODO Auto-generated method stub
		return rightPriceRepository.getCustomer();
	}



	@Override
	public ResponseEntity<Object> updateCustomer(Customer customer) {
		customer.setIsActive(1);
		String syntelLanId=rightPriceRepository.getSyntelId(this.auditTrails.getCurrentUser());
		if(syntelLanId.isEmpty() || syntelLanId==null)
		{	
			customer.setCreatedBy(this.auditTrails.getCurrentUser());
			customer.setCreatedOn(this.auditTrails.getCurrentTimeStamp());
			customer.setUpdatedBy(this.auditTrails.getCurrentUser());
			customer.setUpdatedOn(this.auditTrails.getCurrentTimeStamp());
		}
		else
		{
		customer.setCreatedBy(syntelLanId);
		customer.setCreatedOn(this.auditTrails.getCurrentTimeStamp());
		customer.setUpdatedBy(syntelLanId);
		customer.setUpdatedOn(this.auditTrails.getCurrentTimeStamp());
		}
		return rightPriceRepository.updateCustomer(customer);
	}
	
	@Override
	public ResponseEntity<Object> getFxRateComment(int rcId) {
		return rightPriceRepository.getFxRateComment(rcId);
	}



	@Override
	public ResponseEntity<Object> getEmpName(int customerId) {
		return rightPriceRepository.getEmpName(customerId);
	}
	
	@Override
	public ResponseEntity<Object> getSummaryCurrencyUti(int rcId) {
		return rightPriceRepository.getSummaryCurrencyUti(rcId);
	}
	
	@Override
	public ResponseEntity<Object> getRateCardAssumtion(int assumtion_id) {
		// TODO Auto-generated method stub
		return rightPriceRepository.getRateCardAssumtion(assumtion_id);
	}

	@Override
	public ResponseEntity<Object> getRateCardLocation(int rcId) {
		return rightPriceRepository.getRateCardLocation(rcId);
	}
	
	@Override
	public ResponseEntity<Object> getVisaDataLabel() {
		return rightPriceRepository.getVisaDataLabel();
	}
	
	@Override
	public ResponseEntity<Object> getDealApproverInfo(int rpVrsId) {
		return rightPriceRepository.getDealApproverInfo(rpVrsId);
	}
	@Override
	public ResponseEntity<Object> getTMDealApproverInfo(int rpVrsId) {
		return rightPriceRepository.getTMDealApproverInfo(rpVrsId);
	}
	
	/*@Override
	public ResponseEntity<Object> getDealApproverName(int rpVrsId) {
		return rightPriceRepository.getDealApproverName(rpVrsId);
	}*/
	
	
	@Override
	public ResponseEntity<Object> getDealQuest() {
		return rightPriceRepository.getDealQuest();
	}
	
	@Override
	public ResponseEntity<Object> getVersionData(int rpDealVersionId) {
		return rightPriceRepository.getVersionData(rpDealVersionId);
	}

	@Override
	public ResponseEntity<Object> getDevMainAnswers(int rpDealVersionId) {
		return rightPriceRepository.getDevMainAnswers(rpDealVersionId);
	}
	
	@Override
	public ResponseEntity<Object> getDealTower(int dealVersionId) {
		return rightPriceRepository.getDealTower(dealVersionId);
	}

	@Override
	public ResponseEntity<Object> getFpDealRateCard(int dealTowerId,int dealVersionId) {
		return rightPriceRepository.getFpDealRateCard(dealTowerId,dealVersionId);
	}
	
	@Override
	public ResponseEntity<Object> getDataOnSearch(int rateCardID) {
		return rightPriceRepository.getDataOnSearch(rateCardID);
	}
	/*@Override
	public ResponseEntity<Object> getDataOnSearch() {
		return rightPriceRepository.getDataOnSearch();
	}*/
	
	public ResponseEntity<Object> getAttachementData(AttachmentMapper rpDealVersionId){
		return rightPriceRepository.getAttachementData(rpDealVersionId);
	}
	public ResponseEntity<Object> getTMAttachementData(AttachmentMapper rpDealVersionId){
		return rightPriceRepository.getTMAttachementData(rpDealVersionId);
	}
	
	@Override
	public ResponseEntity<Object> changeActiveStatus(int objectid) {
		return rightPriceRepository.changeActiveStatus(objectid);
	}
	
	
	@Override
	public ResponseEntity<Object> uploadManualFile(MultipartFile file, String fileName,
			InputStream uploadedInputStream, Integer versionId,String category) {
		try {
			FileObjectTable fileObj = new FileObjectTable();
			FPDealAttachment fpData=new FPDealAttachment();
			fileObj.setActiveStatus(1);
			fileObj.setFileName(fileName);
			if(file.getOriginalFilename().contains("\\"))
			{
				String strMain = file.getOriginalFilename();
				String[] arrSplit = new String[100];
			    arrSplit = strMain.split("\\\\");
			    
			    System.out.println(arrSplit.length+" "+arrSplit[(arrSplit.length-1)]);
			    int size=arrSplit.length;
			    fileObj.setOriginalFileName(arrSplit[size-1]);
			}
			else{
			fileObj.setOriginalFileName(file.getOriginalFilename());
			}
			fileObj.setObject(file.getBytes());
			String syntelLanId=rightPriceRepository.getSyntelId(this.auditTrails.getCurrentUser());
			if(syntelLanId.isEmpty() || syntelLanId==null)
			{	
				fileObj.setCreatedBy(this.auditTrails.getCurrentUser());
				fileObj.setCreatedOn(this.auditTrails.getCurrentTimeStamp());
				fileObj.setLastUpdatedBy(this.auditTrails.getCurrentUser());
				fileObj.setLastUpdatedOn(this.auditTrails.getCurrentTimeStamp());
				fpData.setCreatedBy(this.auditTrails.getCurrentUser());
				fpData.setCreatedOn(this.auditTrails.getCurrentTimeStamp());
				fpData.setUpdatedBy(this.auditTrails.getCurrentUser());
				fpData.setUpdatedOn(this.auditTrails.getCurrentTimeStamp());
			}
			else
			{
			fileObj.setCreatedBy(syntelLanId);
			fileObj.setCreatedOn(this.auditTrails.getCurrentTimeStamp());
			fileObj.setLastUpdatedBy(syntelLanId);
			fileObj.setLastUpdatedOn(this.auditTrails.getCurrentTimeStamp());
			fpData.setCreatedBy(syntelLanId);
			fpData.setCreatedOn(this.auditTrails.getCurrentTimeStamp());
			fpData.setUpdatedBy(syntelLanId);
			fpData.setUpdatedOn(this.auditTrails.getCurrentTimeStamp());
			}
			fpData.setIsActive(1);
			fpData.setVersionId(versionId);
			fpData.setCategory(category);
			return rightPriceRepository.uploadManualFile(file,fileName,uploadedInputStream,fileObj,versionId,fpData);
		} catch (IOException e) {
			AppLoger.APPLOGGER.info("Exception occured while inserting object " + e.getMessage());
			return ResponseEntity.status(205)
				       .contentType(MediaType.TEXT_PLAIN)
				       .body("Currently We are facing technical issues, please try again later.");
		}
	}

	@Override
	public ResponseEntity<Object> uploadTMFileData(MultipartFile file, String fileName,
			InputStream uploadedInputStream, Integer versionId,String category) {
		System.out.println("uploadTMFileData========================================serviceImpl");
		try {
			FileObjectTable fileObj = new FileObjectTable();
			FPDealAttachment fpData=new FPDealAttachment();
			fileObj.setActiveStatus(1);
			fileObj.setFileName(fileName);
			if(file.getOriginalFilename().contains("\\"))
			{
				String strMain = file.getOriginalFilename();
				String[] arrSplit = new String[100];
			    arrSplit = strMain.split("\\\\");
			    
			    System.out.println(arrSplit.length+" "+arrSplit[(arrSplit.length-1)]);
			    int size=arrSplit.length;
			    fileObj.setOriginalFileName(arrSplit[size-1]);
			}
			else{
			fileObj.setOriginalFileName(file.getOriginalFilename());
			}
			fileObj.setObject(file.getBytes());
			String syntelLanId=rightPriceRepository.getSyntelId(this.auditTrails.getCurrentUser());
			if(syntelLanId.isEmpty() || syntelLanId==null)
			{	
				fileObj.setCreatedBy(this.auditTrails.getCurrentUser());
				fileObj.setCreatedOn(this.auditTrails.getCurrentTimeStamp());
				fileObj.setLastUpdatedBy(this.auditTrails.getCurrentUser());
				fileObj.setLastUpdatedOn(this.auditTrails.getCurrentTimeStamp());
				fpData.setCreatedBy(this.auditTrails.getCurrentUser());
				fpData.setCreatedOn(this.auditTrails.getCurrentTimeStamp());
				fpData.setUpdatedBy(this.auditTrails.getCurrentUser());
				fpData.setUpdatedOn(this.auditTrails.getCurrentTimeStamp());
			}
			else
			{
			fileObj.setCreatedBy(syntelLanId);
			fileObj.setCreatedOn(this.auditTrails.getCurrentTimeStamp());
			fileObj.setLastUpdatedBy(syntelLanId);
			fileObj.setLastUpdatedOn(this.auditTrails.getCurrentTimeStamp());
			fpData.setCreatedBy(syntelLanId);
			fpData.setCreatedOn(this.auditTrails.getCurrentTimeStamp());
			fpData.setUpdatedBy(syntelLanId);
			fpData.setUpdatedOn(this.auditTrails.getCurrentTimeStamp());
			}
			fpData.setIsActive(1);
			fpData.setVersionId(versionId);
			fpData.setCategory(category);
			return rightPriceRepository.uploadTMFileData(file,fileName,uploadedInputStream,fileObj,versionId,fpData);
		} catch (IOException e) {
			AppLoger.APPLOGGER.info("Exception occured while inserting object " + e.getMessage());
			return ResponseEntity.status(205)
				       .contentType(MediaType.TEXT_PLAIN)
				       .body("Currently We are facing technical issues, please try again later.");
		}
	}

	@Override
	public ResponseEntity<Object> saveFPDealRoleSelectionAndContractorRole(FPDealRoleAndContractor fPDealRoleAndContractor){
		String syntelLanId=rightPriceRepository.getSyntelId(this.auditTrails.getCurrentUser());
		
		if(fPDealRoleAndContractor.getDealRoles().size()>0){
			for (FpDealRole currentRole : fPDealRoleAndContractor.getDealRoles()) {
				currentRole.setIsActive(1);
				if(syntelLanId.isEmpty() || syntelLanId==null)
				{	
					currentRole.setCreatedBy(this.auditTrails.getCurrentUser());
					currentRole.setCreatedOn(this.auditTrails.getCurrentTimeStamp());
					currentRole.setUpdatedBy(this.auditTrails.getCurrentUser());
					currentRole.setUpdatedOn(this.auditTrails.getCurrentTimeStamp());
				}
				else
				{
				currentRole.setCreatedBy(syntelLanId);
				currentRole.setCreatedOn(this.auditTrails.getCurrentTimeStamp());
				currentRole.setUpdatedBy(syntelLanId);
				currentRole.setUpdatedOn(this.auditTrails.getCurrentTimeStamp());
				}
				currentRole.setDealVersionId(fPDealRoleAndContractor.getDealVersionId());
				currentRole.setDealAutoTowerId(fPDealRoleAndContractor.getDealAutoTowerId());
				currentRole.setIsMasterRole(fPDealRoleAndContractor.getIsMasterRole());
					
			}
		}
		
		if(fPDealRoleAndContractor.getDealContractorRole().size()>0){
			for (DealContraactorRole currentContractorRole : fPDealRoleAndContractor.getDealContractorRole()) {
				currentContractorRole.setIsActive(1);
				if(syntelLanId.isEmpty() || syntelLanId==null)
				{	
					currentContractorRole.setCreatedBy(this.auditTrails.getCurrentUser());
					currentContractorRole.setCreatedOn(this.auditTrails.getCurrentTimeStamp());
					currentContractorRole.setUpdatedBy(this.auditTrails.getCurrentUser());
					currentContractorRole.setUpdatedOn(this.auditTrails.getCurrentTimeStamp());
				}
				else
				{
				currentContractorRole.setCreatedBy(syntelLanId);
				currentContractorRole.setCreatedOn(this.auditTrails.getCurrentTimeStamp());
				currentContractorRole.setUpdatedBy(syntelLanId);
				currentContractorRole.setUpdatedOn(this.auditTrails.getCurrentTimeStamp());
				}
				currentContractorRole.setDealVersionId(fPDealRoleAndContractor.getDealVersionId());
				
			}
		}
		
		return rightPriceRepository.saveFPDealRoleSelectionAndContractorRole(fPDealRoleAndContractor);
	}
	
	@Override
	public ResponseEntity<Object> getFPDealRoleSelectionAndContractorRole(int dealVersionId,int dealAutoTowerId){
		return rightPriceRepository.getFPDealRoleSelectionAndContractorRole(dealVersionId,dealAutoTowerId);
		
	}
	@Override
	public ResponseEntity<Object> getFPDealRoleSelectionAndContractorRoleFP(int dealVersionId,int dealAutoTowerId){
		return rightPriceRepository.getFPDealRoleSelectionAndContractorRoleFP(dealVersionId,dealAutoTowerId);
		
	}

	@Override
	public ResponseEntity<Object> getCountryData() {
		return rightPriceRepository.getCountryData();
	}


	@Override
	public ResponseEntity<Object> getDealRateCardName(int customerId, String deal_Id,int currencyId, int countryId, int cityCategory,int industryType) {
		return rightPriceRepository.getDealRateCardName(customerId,deal_Id,currencyId,countryId,cityCategory,industryType);
	}
	

	@Override
	public ResponseEntity<Object> uploadManualDealFile(MultipartFile file, String fileName, InputStream uploadedInputStream,
			Integer rpDealVersionId, Integer dealAutoTowerId, Integer docType, Integer noOfTowers) {
		try {
			FileObjectTable fileObj = new FileObjectTable();
			FPManualDealAttachment fpData=new FPManualDealAttachment();
			String newFileName = "";
			fileObj.setActiveStatus(1);
			fileObj.setFileName(fileName);
			if(file.getOriginalFilename().contains("\\"))
			{
				String strMain = file.getOriginalFilename();
				String[] arrSplit = new String[100];
			    arrSplit = strMain.split("\\\\");
			    
			    System.out.println(arrSplit.length+" "+arrSplit[(arrSplit.length-1)]);
			    int size=arrSplit.length;
			    newFileName = arrSplit[(arrSplit.length-1)];
			    fileObj.setOriginalFileName(newFileName);
			    fpData.setFileName(newFileName);
			}
			else{
			fileObj.setOriginalFileName(file.getOriginalFilename());
			}
			fileObj.setObject(file.getBytes());
			String syntelLanId=rightPriceRepository.getSyntelId(this.auditTrails.getCurrentUser());
			if(syntelLanId.isEmpty() || syntelLanId==null)
			{	
				fileObj.setCreatedBy(this.auditTrails.getCurrentUser());
				fileObj.setCreatedOn(this.auditTrails.getCurrentTimeStamp());
				fileObj.setLastUpdatedBy(this.auditTrails.getCurrentUser());
				fileObj.setLastUpdatedOn(this.auditTrails.getCurrentTimeStamp());
				fpData.setCreatedBy(this.auditTrails.getCurrentUser());
				fpData.setCreatedOn(this.auditTrails.getCurrentTimeStamp());
				fpData.setDateStamp(this.auditTrails.getCurrentTimeStamp());
				fpData.setUpdatedBy(this.auditTrails.getCurrentUser());
				fpData.setUpdatedOn(this.auditTrails.getCurrentTimeStamp());
			}
			else
			{
			fileObj.setCreatedBy(syntelLanId);
			fileObj.setCreatedOn(this.auditTrails.getCurrentTimeStamp());
			fileObj.setLastUpdatedBy(syntelLanId);
			fileObj.setLastUpdatedOn(this.auditTrails.getCurrentTimeStamp());
			fpData.setCreatedBy(syntelLanId);
			fpData.setCreatedOn(this.auditTrails.getCurrentTimeStamp());
			fpData.setDateStamp(this.auditTrails.getCurrentTimeStamp());
			fpData.setUpdatedBy(syntelLanId);
			fpData.setUpdatedOn(this.auditTrails.getCurrentTimeStamp());
			}
			fpData.setIsActive(1);
			fpData.setRpDealVersionId(rpDealVersionId);
			fpData.setDealAutoTowerId(dealAutoTowerId);
			fpData.setDocType(docType);
			return rightPriceRepository.uploadManualDealFile(file,fileName,uploadedInputStream,fileObj,rpDealVersionId,fpData,noOfTowers);
		} catch (IOException e) {
			AppLoger.APPLOGGER.info("Exception occured while inserting object " + e.getMessage());
			return ResponseEntity.status(205)
				       .contentType(MediaType.TEXT_PLAIN)
				       .body("Currently We are facing technical issues, please try again later.");
		}
	}


	@Override
	public ResponseEntity<Object> deleteRateCard(int dltRcId) {
		return rightPriceRepository.deleteRateCard(dltRcId);
	}



	@Override
	public ResponseEntity<Object> getFpDealCostInputsDetails(int towerId,int costType,int rpdealVersionId) {
		// TODO Auto-generated method stub
		return rightPriceRepository.getFpDealCostInputsDetails(towerId,costType,rpdealVersionId);
	}
	
	@Override
	public List<String> getUserRoles(String usreName) {
		return rightPriceRepository.getUserRoles(usreName);
	}
	
	@Override
	public ResponseEntity<Object> getVersionAttachment(int rpDealVersionId) {
		return rightPriceRepository.getVersionAttachment(rpDealVersionId);
	}
	
	@Override
	public ResponseEntity<Object> changeFpActiveStatus(int dealAttachmentId) {
		return rightPriceRepository.changeFpActiveStatus(dealAttachmentId);
	}



	@Override
	public ResponseEntity<Object> updateCostInputData(List<DealIndirectCostInputs> dealIndirectCost) {
		for(DealIndirectCostInputs costInputData : dealIndirectCost) {
			costInputData.setIsActive(1);
			String syntelLanId=rightPriceRepository.getSyntelId(this.auditTrails.getCurrentUser());
			if(syntelLanId.isEmpty() || syntelLanId==null)
			{	
				costInputData.setUpdatedBy(this.auditTrails.getCurrentUser());
				costInputData.setUpdatedOn(this.auditTrails.getCurrentTimeStamp());
			}
			else
			{
			costInputData.setUpdatedBy(syntelLanId);
			costInputData.setUpdatedOn(this.auditTrails.getCurrentTimeStamp());
			}
		}

		return rightPriceRepository.updateCostInputData(dealIndirectCost);
	}



	@Override
	public ResponseEntity<Object> downloadFileWithFileName(int dealAttachmentId) {
		return rightPriceRepository.downloadFileWithFileName(dealAttachmentId);
	}



	@Override
	public ResponseEntity<Object> updateManualDealVersionStatus(int versionId) {
		return rightPriceRepository.updateManualDealVersionStatus(versionId);
	}
	
	@Override
	public ResponseEntity<Object> updateManualGFTDeal(int versionId) {
		return rightPriceRepository.updateManualGFTDeal(versionId);
	}
	
	@Override
	public ResponseEntity<Object> getDescription(int cityId) {
		return rightPriceRepository.getDescription(cityId);
	}



	@Override
	public ResponseEntity<Object> uploadRCManualFile(MultipartFile file, String fileName,
			InputStream uploadedInputStream, Integer rcId,Integer cityId, Integer docType) {
		try {
			FileObjectTable fileObj = new FileObjectTable();
			FPManualDealAttachment fpData = new FPManualDealAttachment();
			String[] fileNameSplit = new String[100];
			String newFileName = "";
			fileObj.setActiveStatus(1);
			fileObj.setFileName(fileName);
			
			//file.getOriginalFilename().contains(s)
			if(file.getOriginalFilename().contains("\\")) {
				String modifiedFileName = file.getOriginalFilename();
				fileNameSplit = modifiedFileName.split("\\\\");
				newFileName = fileNameSplit[4];
				fileObj.setOriginalFileName(newFileName);
				fileObj.setObject(file.getBytes());
			} else {
				fileObj.setOriginalFileName(file.getOriginalFilename());
			}
			String syntelLanId=rightPriceRepository.getSyntelId(this.auditTrails.getCurrentUser());
			if(syntelLanId.isEmpty() || syntelLanId==null)
			{	
				fileObj.setCreatedBy(this.auditTrails.getCurrentUser());
				fileObj.setCreatedOn(this.auditTrails.getCurrentTimeStamp());
				fileObj.setLastUpdatedBy(this.auditTrails.getCurrentUser());
				fileObj.setLastUpdatedOn(this.auditTrails.getCurrentTimeStamp());
				fpData.setCreatedBy(this.auditTrails.getCurrentUser());
				fpData.setCreatedOn(this.auditTrails.getCurrentTimeStamp());
				fpData.setUpdatedBy(this.auditTrails.getCurrentUser());
				fpData.setUpdatedOn(this.auditTrails.getCurrentTimeStamp());
			}
			else
			{
			fileObj.setCreatedBy(syntelLanId);
			fileObj.setCreatedOn(this.auditTrails.getCurrentTimeStamp());
			fileObj.setLastUpdatedBy(syntelLanId);
			fileObj.setLastUpdatedOn(this.auditTrails.getCurrentTimeStamp());
			fpData.setCreatedBy(syntelLanId);
			fpData.setCreatedOn(this.auditTrails.getCurrentTimeStamp());
			fpData.setUpdatedBy(syntelLanId);
			fpData.setUpdatedOn(this.auditTrails.getCurrentTimeStamp());
			}
			fpData.setIsActive(1);
			fpData.setRpDealVersionId(rcId);
			return rightPriceRepository.uploadRCManualFile(file,fileName,uploadedInputStream,fileObj,rcId,cityId,fpData);
		} catch (IOException e) {
			AppLoger.APPLOGGER.info("Exception occured while inserting object " + e.getMessage());
			return ResponseEntity.status(205)
				       .contentType(MediaType.TEXT_PLAIN)
				       .body("Currently We are facing technical issues, please try again later.");
		}
	}
	
	
	@Override
	public ResponseEntity<Object> viewFPMDApproverDesignation(int rpDealVersionId) {
		return rightPriceRepository.getFPManualDealApproverInfoDesignation(rpDealVersionId);
	}
	
	
	@Override
	public ResponseEntity<Object> updateFPMDApprovalDetails(FpDeal fpmdApproval) {
		String syntelLanId=rightPriceRepository.getSyntelId(this.auditTrails.getCurrentUser());
		if(syntelLanId.isEmpty() || syntelLanId==null)
		{	
			fpmdApproval.setUpdatedBy(this.auditTrails.getCurrentUser());
			fpmdApproval.setUpdatedDate(this.auditTrails.getCurrentTimeStamp());
		}
		else
		{
		fpmdApproval.setUpdatedBy(syntelLanId);
		fpmdApproval.setUpdatedDate(this.auditTrails.getCurrentTimeStamp());
		}
		return rightPriceRepository.updateFPMDApprovalDetails(fpmdApproval);
	}
	

	/*@Override
	public ResponseEntity<Object> getFPMDApproverNames(int verticalId,int rpDealVersionId) {
		return rightPriceRepository.getFPMDApproverNames(verticalId, rpDealVersionId);
	}*/
	
	@Override
	public ResponseEntity<Object> saveFPMDApprovalData(RateCardApprovalAudit rateCardApprovalAudit) {
		AppLoger.APPLOGGER.info("in service");
		rateCardApprovalAudit.setIsActive(1);
		String syntelLanId=rightPriceRepository.getSyntelId(this.auditTrails.getCurrentUser());
		if(syntelLanId.isEmpty() || syntelLanId==null)
		{	
			rateCardApprovalAudit.setCreatedBy(this.auditTrails.getCurrentUser());
			rateCardApprovalAudit.setCreatedDate(this.auditTrails.getCurrentTimeStamp());
			rateCardApprovalAudit.setUpdatedBy(this.auditTrails.getCurrentUser());
			rateCardApprovalAudit.setUpdatedDate(this.auditTrails.getCurrentTimeStamp());
		}
		else
		{
		rateCardApprovalAudit.setCreatedBy(syntelLanId);
		rateCardApprovalAudit.setCreatedDate(this.auditTrails.getCurrentTimeStamp());
		rateCardApprovalAudit.setUpdatedBy(syntelLanId);
		rateCardApprovalAudit.setUpdatedDate(this.auditTrails.getCurrentTimeStamp());
		}
		return rightPriceRepository.saveFPMDApprovalData(rateCardApprovalAudit);
	}


	@Override
	public ResponseEntity<Object> updateApprovalDataForFPMD(FpDeal fpdealmanualApprovalDetails) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		SAMLCredential credential = (SAMLCredential) authentication.getCredentials();
		Assertion assertion = credential.getAuthenticationAssertion();
		HashMap<String, String> map = new HashMap<>();
	    AppLoger.APPLOGGER.info("1");
	    List<AttributeStatement> attributeStatements = assertion.getAttributeStatements();
	    AppLoger.APPLOGGER.info("attributeStatements are : " +attributeStatements);
	    for (AttributeStatement statement : attributeStatements) {
	      for (Attribute attribute : statement.getAttributes()) {
	        XMLObject xmlObject = attribute.getAttributeValues().get(0);
	        AppLoger.APPLOGGER.info("XMLObject is : " +xmlObject);
	        if (xmlObject instanceof XSStringImpl) {
	          map.put(attribute.getName(), ((XSStringImpl) xmlObject).getValue());
	          AppLoger.APPLOGGER.info("Map is : " +map);
	        } else {
	          map.put(attribute.getName(), ((XSAnyImpl) xmlObject).getTextContent());
	          AppLoger.APPLOGGER.info("Map is : " +map);
	        }
	      }
	    }
	    String dasID = map.get("uid");
	    String fName = map.get("firstname");
	    String lname = map.get("surname");
	    String atosMailID = map.get("mail");
	    String requesterName = fName+" "+lname;
		String syntelLanId=rightPriceRepository.getSyntelId(this.auditTrails.getCurrentUser());
		if(syntelLanId.isEmpty() || syntelLanId==null)
		{	
			fpdealmanualApprovalDetails.setUpdateAtosEmailId(atosMailID);
			fpdealmanualApprovalDetails.setUpdateEmployeeName(requesterName);
			fpdealmanualApprovalDetails.setUpdatedBy(this.auditTrails.getCurrentUser());
			fpdealmanualApprovalDetails.setUpdatedDate(this.auditTrails.getCurrentTimeStamp());
		}
		else
		{
			fpdealmanualApprovalDetails.setUpdateAtosEmailId(atosMailID);
			fpdealmanualApprovalDetails.setUpdateEmployeeName(requesterName);
			fpdealmanualApprovalDetails.setUpdatedBy(syntelLanId);
			fpdealmanualApprovalDetails.setUpdatedDate(this.auditTrails.getCurrentTimeStamp());
		}
		return rightPriceRepository.updateApprovalDataForFPMD(fpdealmanualApprovalDetails);
	}

	

	@Override
	public ResponseEntity<Object> getDealData() {
		return rightPriceRepository.getDealData();
	}
	
	@Override
	public ResponseEntity<Object>  getResourceData(ResourceForcastData forcastData) {
		return rightPriceRepository.getResourceData(forcastData);
	}
	
	@Override
	public ResponseEntity<Object> getLobData() {
		return rightPriceRepository.getLobData();
	}
	
	@Override
	public ResponseEntity<Object> getRolesData() {
		return rightPriceRepository.getRolesData();
	}
	

	@Override
       public ResponseEntity<Object>  saveResourceData(ResourceForecast forcastData,String lob,int flag) {
		forcastData.setIsActive(1);
		String syntelLanId=rightPriceRepository.getSyntelId(this.auditTrails.getCurrentUser());
		if(syntelLanId.isEmpty() || syntelLanId==null)
		{	
			forcastData.setCreatedBy(this.auditTrails.getCurrentUser());
			forcastData.setCreatedOn(this.auditTrails.getCurrentTimeStamp());
			forcastData.setUpdatedBy(this.auditTrails.getCurrentUser());
			forcastData.setUpdatedOn(this.auditTrails.getCurrentTimeStamp());
		}
		else
		{ 
			  forcastData.setCreatedBy(syntelLanId);
              forcastData.setCreatedOn(this.auditTrails.getCurrentTimeStamp());
              forcastData.setUpdatedBy(syntelLanId);
              forcastData.setUpdatedOn(this.auditTrails.getCurrentTimeStamp());
		}
              return rightPriceRepository.saveResourceData(forcastData,lob,flag);
       }


	public ResponseEntity<Object>  getOldResourceData(String cRMDealId){
		AppLoger.APPLOGGER.info("in getOldResourceData");
		return rightPriceRepository.getOldResourceData(cRMDealId);
	}

	@Override
	public ResponseEntity<Object> getFPCostCalCulationData(int towerId, int rpdealVersionId) {
		return rightPriceRepository.getFPCostCalCulationData(towerId,rpdealVersionId);
	}
	
	public ResponseEntity<Object> getCostBreakupData(String cRMDealId,int dealVersionId){
		AppLoger.APPLOGGER.info("in getOldResourceData");
		return rightPriceRepository.getCostBreakupData(dealVersionId,cRMDealId);
			
	}
	

	@Override
	public ResponseEntity<Object> getWhatIfData(int rpVrsId) {
		return rightPriceRepository.getWhatIfData(rpVrsId);
	}
	
	
	@Override
	public ResponseEntity<Object> getWhatIfEffortData(int rpVrsId) {
		return rightPriceRepository.getWhatIfEffortData(rpVrsId);
	}


	@Override
	public ResponseEntity<Object> updateFpDealWhatIfDetails(FPWhatIfDetailsInsert fpWhatIfDetailsInsert) {
		// TODO Auto-generated method stub
		String syntelLanId=rightPriceRepository.getSyntelId(this.auditTrails.getCurrentUser());
		for (FPDealWhatIf whatIfDetailsObject : fpWhatIfDetailsInsert.getWhatIfDetails()) {
			whatIfDetailsObject.setIsActive(1);
			if(syntelLanId.isEmpty() || syntelLanId==null)
			{	
				whatIfDetailsObject.setCreatedBy(this.auditTrails.getCurrentUser());
				whatIfDetailsObject.setCreatedOn(this.auditTrails.getCurrentTimeStamp());
				whatIfDetailsObject.setUpdatedBy(this.auditTrails.getCurrentUser());
				whatIfDetailsObject.setUpdatedOn(this.auditTrails.getCurrentTimeStamp());
			}
			else
			{
			whatIfDetailsObject.setCreatedBy(syntelLanId);
			whatIfDetailsObject.setCreatedOn(this.auditTrails.getCurrentTimeStamp());
			whatIfDetailsObject.setUpdatedBy(syntelLanId);
			whatIfDetailsObject.setUpdatedOn(this.auditTrails.getCurrentTimeStamp());
			}
		}
		
		
		for (FpWhatIfCalculation whatIfCalDetailsObject : fpWhatIfDetailsInsert.getWhatIfCalDetails()) {
			whatIfCalDetailsObject.setIsActive(1);
			if(syntelLanId.isEmpty() || syntelLanId==null)
			{	
				whatIfCalDetailsObject.setCreatedBy(this.auditTrails.getCurrentUser());
				whatIfCalDetailsObject.setCreatedOn(this.auditTrails.getCurrentTimeStamp());
				whatIfCalDetailsObject.setUpdatedBy(this.auditTrails.getCurrentUser());
				whatIfCalDetailsObject.setUpdatedOn(this.auditTrails.getCurrentTimeStamp());
			}
			else
			{
			whatIfCalDetailsObject.setCreatedBy(syntelLanId);
			whatIfCalDetailsObject.setCreatedOn(this.auditTrails.getCurrentTimeStamp());
			whatIfCalDetailsObject.setUpdatedBy(syntelLanId);
			whatIfCalDetailsObject.setUpdatedOn(this.auditTrails.getCurrentTimeStamp());
			}
		}
		
		for (FPWhatIfContractTerms whatIfContractorTermsObject : fpWhatIfDetailsInsert.getWhatIfContractorTerms()) {
			whatIfContractorTermsObject.setIsActive(1);
			if(syntelLanId.isEmpty() || syntelLanId==null)
			{	
				whatIfContractorTermsObject.setCreatedBy(this.auditTrails.getCurrentUser());
				whatIfContractorTermsObject.setCreatedOn(this.auditTrails.getCurrentTimeStamp());
				whatIfContractorTermsObject.setUpdatedBy(this.auditTrails.getCurrentUser());
				whatIfContractorTermsObject.setUpdatedOn(this.auditTrails.getCurrentTimeStamp());
			}
			else
			{
			whatIfContractorTermsObject.setCreatedBy(syntelLanId);
			whatIfContractorTermsObject.setCreatedOn(this.auditTrails.getCurrentTimeStamp());
			whatIfContractorTermsObject.setUpdatedBy(syntelLanId);
			whatIfContractorTermsObject.setUpdatedOn(this.auditTrails.getCurrentTimeStamp());
			}
		}
		
		for (FpDeal whatIfFpRpDealObject : fpWhatIfDetailsInsert.getWhatIfRpDealData()) {
			whatIfFpRpDealObject.setIsActive(1);
			if(syntelLanId.isEmpty() || syntelLanId==null)
			{	
				whatIfFpRpDealObject.setCreatedBy(this.auditTrails.getCurrentUser());
				whatIfFpRpDealObject.setCreatedDate(this.auditTrails.getCurrentTimeStamp());
				whatIfFpRpDealObject.setUpdatedBy(this.auditTrails.getCurrentUser());
				whatIfFpRpDealObject.setUpdatedDate(this.auditTrails.getCurrentTimeStamp());
			}
			else
			{
			whatIfFpRpDealObject.setCreatedBy(syntelLanId);
			whatIfFpRpDealObject.setCreatedDate(this.auditTrails.getCurrentTimeStamp());
			whatIfFpRpDealObject.setUpdatedBy(syntelLanId);
			whatIfFpRpDealObject.setUpdatedDate(this.auditTrails.getCurrentTimeStamp());
			}
		}
		
		
		for (CostBreakup whatIfFpCostBreakupObject : fpWhatIfDetailsInsert.getWhatIfCostBreakupData()) {
			whatIfFpCostBreakupObject.setIsActive(1);
			if(syntelLanId.isEmpty() || syntelLanId==null)
			{	
				whatIfFpCostBreakupObject.setCreatedBy(this.auditTrails.getCurrentUser());
				whatIfFpCostBreakupObject.setCreatedOn(this.auditTrails.getCurrentTimeStamp());
				whatIfFpCostBreakupObject.setUpdatedBy(this.auditTrails.getCurrentUser());
				whatIfFpCostBreakupObject.setUpdatedOn(this.auditTrails.getCurrentTimeStamp());
			}
			else
			{
			whatIfFpCostBreakupObject.setCreatedBy(syntelLanId);
			whatIfFpCostBreakupObject.setCreatedOn(this.auditTrails.getCurrentTimeStamp());
			whatIfFpCostBreakupObject.setUpdatedBy(syntelLanId);
			whatIfFpCostBreakupObject.setUpdatedOn(this.auditTrails.getCurrentTimeStamp());
			}
		}
		
		return rightPriceRepository.updateFpDealWhatIfDetails(fpWhatIfDetailsInsert);
	}




	@Override
	public ResponseEntity<Object> updateFPApproverDetails(FpDeal fpData) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		SAMLCredential credential = (SAMLCredential) authentication.getCredentials();
		Assertion assertion = credential.getAuthenticationAssertion();
		HashMap<String, String> map = new HashMap<>();
	    AppLoger.APPLOGGER.info("1");
	    List<AttributeStatement> attributeStatements = assertion.getAttributeStatements();
	    AppLoger.APPLOGGER.info("attributeStatements are : " +attributeStatements);
	    for (AttributeStatement statement : attributeStatements) {
	      for (Attribute attribute : statement.getAttributes()) {
	        XMLObject xmlObject = attribute.getAttributeValues().get(0);
	        AppLoger.APPLOGGER.info("XMLObject is : " +xmlObject);
	        if (xmlObject instanceof XSStringImpl) {
	          map.put(attribute.getName(), ((XSStringImpl) xmlObject).getValue());
	          AppLoger.APPLOGGER.info("Map is : " +map);
	        } else {
	          map.put(attribute.getName(), ((XSAnyImpl) xmlObject).getTextContent());
	          AppLoger.APPLOGGER.info("Map is : " +map);
	        }
	      }
	    }
	    String dasID = map.get("uid");
	    String fName = map.get("firstname");
	    String lname = map.get("surname");
	    String atosMailID = map.get("mail");
	    String requesterName = fName+" "+lname;
		String syntelLanId=rightPriceRepository.getSyntelId(this.auditTrails.getCurrentUser());
		if(syntelLanId.isEmpty() || syntelLanId==null)
		{	
			fpData.setUpdateAtosEmailId(atosMailID);
			fpData.setUpdateEmployeeName(requesterName);
			fpData.setUpdatedBy(this.auditTrails.getCurrentUser());
			fpData.setUpdatedDate(this.auditTrails.getCurrentTimeStamp());
		}
		else
		{
			fpData.setUpdateAtosEmailId(atosMailID);
			fpData.setUpdateEmployeeName(requesterName);
			fpData.setUpdatedBy(syntelLanId);
			fpData.setUpdatedDate(this.auditTrails.getCurrentTimeStamp());
		}
		return rightPriceRepository.updateFPApproverDetails(fpData);
	}



	@Override
	public ResponseEntity<Object> updateFPApproverStatus(FpDeal[] fpDealApprove) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		SAMLCredential credential = (SAMLCredential) authentication.getCredentials();
		Assertion assertion = credential.getAuthenticationAssertion();
		HashMap<String, String> map = new HashMap<>();
	    AppLoger.APPLOGGER.info("1");
	    List<AttributeStatement> attributeStatements = assertion.getAttributeStatements();
	    AppLoger.APPLOGGER.info("attributeStatements are : " +attributeStatements);
	    for (AttributeStatement statement : attributeStatements) {
	      for (Attribute attribute : statement.getAttributes()) {
	        XMLObject xmlObject = attribute.getAttributeValues().get(0);
	        AppLoger.APPLOGGER.info("XMLObject is : " +xmlObject);
	        if (xmlObject instanceof XSStringImpl) {
	          map.put(attribute.getName(), ((XSStringImpl) xmlObject).getValue());
	          AppLoger.APPLOGGER.info("Map is : " +map);
	        } else {
	          map.put(attribute.getName(), ((XSAnyImpl) xmlObject).getTextContent());
	          AppLoger.APPLOGGER.info("Map is : " +map);
	        }
	      }
	    }
	    String dasID = map.get("uid");
	    String fName = map.get("firstname");
	    String lname = map.get("surname");
	    String atosMailID = map.get("mail");
	    String requesterName = fName+" "+lname;
		for(FpDeal fpDetails:fpDealApprove)
		{	
			String syntelLanId=rightPriceRepository.getSyntelId(this.auditTrails.getCurrentUser());
			if(syntelLanId.isEmpty() || syntelLanId==null)
			{	
				fpDetails.setUpdateAtosEmailId(atosMailID);
				fpDetails.setUpdateEmployeeName(requesterName);
				fpDetails.setUpdatedBy(this.auditTrails.getCurrentUser());
				fpDetails.setUpdatedDate(this.auditTrails.getCurrentTimeStamp());
			}
			else
			{
				fpDetails.setUpdateAtosEmailId(atosMailID);
				fpDetails.setUpdateEmployeeName(requesterName);
				fpDetails.setUpdatedBy(syntelLanId);
				fpDetails.setUpdatedDate(this.auditTrails.getCurrentTimeStamp());
			}
		}
		return rightPriceRepository.updateFPApproverStatus(fpDealApprove);
		
		/*fpDealApprove.setUpdatedBy(syntelLanId);
		fpDealApprove.setUpdatedDate(this.auditTrails.getCurrentTimeStamp());
		return rightPriceRepository.updateFPApproverStatus(fpDealApprove);*/
	}
	
	@Override
	public ResponseEntity<Object> getDealDataOnSearch(String crmDealID) {
		return rightPriceRepository.getDealDataOnSearch(crmDealID);
	}

	/*@Override
	public ResponseEntity<Object> getDealDataOnSearch(int dealVresionID) {
		return rightPriceRepository.getDealDataOnSearch(dealVresionID);
	}*/
	
	@Override
	public ResponseEntity<Object> deleteDealVersion(int dltDealVersionId) {
		return rightPriceRepository.deleteDealVersion(dltDealVersionId,auditTrails.getCurrentUser());
	}

	@Override
	public ResponseEntity<Object> getFpDealTowerData() {
		return rightPriceRepository.getFpDealTowerData();
	}
	@Override
	public ResponseEntity<Object> updateBillingSchedule(CostBreakup costBreakup, int deald, int dealVersionId){
		costBreakup.setIsActive(1);
		String syntelLanId=rightPriceRepository.getSyntelId(this.auditTrails.getCurrentUser());
		if(syntelLanId.isEmpty() || syntelLanId==null)
		{	
			costBreakup.setCreatedBy(this.auditTrails.getCurrentUser());
			costBreakup.setCreatedOn(this.auditTrails.getCurrentTimeStamp());
			costBreakup.setUpdatedBy(this.auditTrails.getCurrentUser());
			costBreakup.setUpdatedOn(this.auditTrails.getCurrentTimeStamp());
		}
		else
		{
		costBreakup.setCreatedBy(syntelLanId);
		costBreakup.setCreatedOn(this.auditTrails.getCurrentTimeStamp());
		costBreakup.setUpdatedBy(syntelLanId);
		costBreakup.setUpdatedOn(this.auditTrails.getCurrentTimeStamp());
		}
		
		return rightPriceRepository.updateBillingSchedule(costBreakup, deald, dealVersionId);
	}
	

	@Override
	public ResponseEntity<Object> getWhatIfCalculationData(int rpVrsId) {
		return rightPriceRepository.getWhatIfCalculationData(rpVrsId);
	}
	
	
	@Override
	public ResponseEntity<Object> getFpDealRoleDetails(int dealVersionId) {
		return rightPriceRepository.getFpDealRoleDetails(dealVersionId);
	}
	
	@Override
	public ResponseEntity<Object> getRateCardManualAttachment(int cityId,int rcId) {
		// TODO Auto-generated method stub
		return rightPriceRepository.getRateCardManualAttachment(cityId,rcId);
	}



	@Override
	public ResponseEntity<Object> changeFileActiveStatus(int attachmentId) {
		// TODO Auto-generated method stub
		return rightPriceRepository.changeFileActiveStatus(attachmentId);
	}



	@Override
	public ResponseEntity<Object> getWhatIfContractData(int rpVrsId) {
		return rightPriceRepository.getWhatIfContractData(rpVrsId);
	}
	
	@Override
	public ResponseEntity<Object> getApprovalMatrixWhatIf(int rpVrsId) {
		return rightPriceRepository.getApprovalMatrixWhatIf(rpVrsId);
	}
	
	@Override
	public ResponseEntity<Object> getFpVersionsReadyToSubmit(String crmDealId) {
		return rightPriceRepository.getFpVersionsReadyToSubmit(crmDealId);
	}



	@Override
	public ResponseEntity<Object> getRCCityDetails(int cityId, int rateCardId) {
		return rightPriceRepository.getRCCityDetails(cityId,rateCardId);
	}
	
	@Override
	public ResponseEntity<Object> getYearCount(int rpVrsId) {
		return rightPriceRepository.getYearCount(rpVrsId);
	}



	@Override
	public ResponseEntity<Object> updateFPApprovalRFPDetails(FpDeal[] fpData) {
		// TODO Auto-generated method stub
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		SAMLCredential credential = (SAMLCredential) authentication.getCredentials();
		Assertion assertion = credential.getAuthenticationAssertion();
		HashMap<String, String> map = new HashMap<>();
	    AppLoger.APPLOGGER.info("1");
	    List<AttributeStatement> attributeStatements = assertion.getAttributeStatements();
	    AppLoger.APPLOGGER.info("attributeStatements are : " +attributeStatements);
	    for (AttributeStatement statement : attributeStatements) {
	      for (Attribute attribute : statement.getAttributes()) {
	        XMLObject xmlObject = attribute.getAttributeValues().get(0);
	        AppLoger.APPLOGGER.info("XMLObject is : " +xmlObject);
	        if (xmlObject instanceof XSStringImpl) {
	          map.put(attribute.getName(), ((XSStringImpl) xmlObject).getValue());
	          AppLoger.APPLOGGER.info("Map is : " +map);
	        } else {
	          map.put(attribute.getName(), ((XSAnyImpl) xmlObject).getTextContent());
	          AppLoger.APPLOGGER.info("Map is : " +map);
	        }
	      }
	    }
	    String dasID = map.get("uid");
	    String fName = map.get("firstname");
	    String lname = map.get("surname");
	    String atosMailID = map.get("mail");
	    String requesterName = fName+" "+lname;
		for(FpDeal fpDetails:fpData)
		{	
			String syntelLanId=rightPriceRepository.getSyntelId(this.auditTrails.getCurrentUser());
			if(syntelLanId.isEmpty() || syntelLanId==null)
			{	
				fpDetails.setUpdateAtosEmailId(atosMailID);
				fpDetails.setUpdateEmployeeName(requesterName);
				fpDetails.setUpdatedBy(this.auditTrails.getCurrentUser());
				fpDetails.setUpdatedDate(this.auditTrails.getCurrentTimeStamp());
			}
			else
			{
				fpDetails.setUpdateAtosEmailId(atosMailID);
				fpDetails.setUpdateEmployeeName(requesterName);
				fpDetails.setUpdatedBy(syntelLanId);
				fpDetails.setUpdatedDate(this.auditTrails.getCurrentTimeStamp());
			}
		}
		return rightPriceRepository.updateFPApprovalRFPDetails(fpData);
		
	}
	
	@Override
	public ResponseEntity<Object> saveMasterAnnualAllowances(BasicAllowanceShortTime[] basicShortAllowance) {
		for (BasicAllowanceShortTime currBasicAllowance : basicShortAllowance) {
			currBasicAllowance.setStatusModel(1);
			String syntelLanId=rightPriceRepository.getSyntelId(this.auditTrails.getCurrentUser());
			if(syntelLanId.isEmpty() || syntelLanId==null)
			{	
				currBasicAllowance.setCreatedBy(this.auditTrails.getCurrentUser());
				currBasicAllowance.setCreatedDate(this.auditTrails.getCurrentTimeStamp());
				currBasicAllowance.setUpdatedBy(this.auditTrails.getCurrentUser());
				currBasicAllowance.setUpdatedDate(this.auditTrails.getCurrentTimeStamp());
			}
			else
			{
			currBasicAllowance.setCreatedBy(syntelLanId);
			currBasicAllowance.setCreatedDate(this.auditTrails.getCurrentTimeStamp());
			currBasicAllowance.setUpdatedBy(syntelLanId);
			currBasicAllowance.setUpdatedDate(this.auditTrails.getCurrentTimeStamp());
			}
		}
		return rightPriceRepository.saveMasterAnnualAllowances(basicShortAllowance);
	}



	@Override
	public ResponseEntity<Object> updateShortTermAllowances(BasicAllowanceShortTime[] basicShortTermAllowance) {
		for (BasicAllowanceShortTime currBasicAllowance : basicShortTermAllowance) {
			String syntelLanId=rightPriceRepository.getSyntelId(this.auditTrails.getCurrentUser());
			if(syntelLanId.isEmpty() || syntelLanId==null)
			{	
				currBasicAllowance.setUpdatedBy(this.auditTrails.getCurrentUser());
				currBasicAllowance.setUpdatedDate(this.auditTrails.getCurrentTimeStamp());
			}
			else
			{
			currBasicAllowance.setUpdatedBy(syntelLanId);
			currBasicAllowance.setUpdatedDate(this.auditTrails.getCurrentTimeStamp());
			}
		}
		return rightPriceRepository.updateShortTermAllowances(basicShortTermAllowance);
	}
	
	@Override
	public ResponseEntity<Object> getFpRfpRfiFinalVersion(String crmDealId) {
		return rightPriceRepository.getFpRfpRfiFinalVersion(crmDealId);
	}
	
	
	@Override
	public ResponseEntity<Object> updateFPRpiRpfFinalApprovalStatus(FpDeal fpData) {
		// TODO Auto-generated method stub
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		SAMLCredential credential = (SAMLCredential) authentication.getCredentials();
		Assertion assertion = credential.getAuthenticationAssertion();
		HashMap<String, String> map = new HashMap<>();
	    AppLoger.APPLOGGER.info("1");
	    List<AttributeStatement> attributeStatements = assertion.getAttributeStatements();
	    AppLoger.APPLOGGER.info("attributeStatements are : " +attributeStatements);
	    for (AttributeStatement statement : attributeStatements) {
	      for (Attribute attribute : statement.getAttributes()) {
	        XMLObject xmlObject = attribute.getAttributeValues().get(0);
	        AppLoger.APPLOGGER.info("XMLObject is : " +xmlObject);
	        if (xmlObject instanceof XSStringImpl) {
	          map.put(attribute.getName(), ((XSStringImpl) xmlObject).getValue());
	          AppLoger.APPLOGGER.info("Map is : " +map);
	        } else {
	          map.put(attribute.getName(), ((XSAnyImpl) xmlObject).getTextContent());
	          AppLoger.APPLOGGER.info("Map is : " +map);
	        }
	      }
	    }
	    String dasID = map.get("uid");
	    String fName = map.get("firstname");
	    String lname = map.get("surname");
	    String atosMailID = map.get("mail");
	    String requesterName = fName+" "+lname;
		String syntelLanId=rightPriceRepository.getSyntelId(this.auditTrails.getCurrentUser());
		if(syntelLanId.isEmpty() || syntelLanId==null)
		{	
			fpData.setUpdateAtosEmailId(atosMailID);
			fpData.setUpdateEmployeeName(requesterName);
			fpData.setUpdatedBy(this.auditTrails.getCurrentUser());
			fpData.setUpdatedDate(this.auditTrails.getCurrentTimeStamp());
		}
		else
		{
			fpData.setUpdateAtosEmailId(atosMailID);
			fpData.setUpdateEmployeeName(requesterName);
			fpData.setUpdatedBy(syntelLanId);
			fpData.setUpdatedDate(this.auditTrails.getCurrentTimeStamp());
		}
		
		return rightPriceRepository.updateFPRpiRpfFinalApprovalStatus(fpData);
		
	}
	

	@Override
	public ResponseEntity<Object> updateCostBreakupData(List<CostBreakup> costBreakup) {
		for(CostBreakup costBreakupData : costBreakup) {
			costBreakupData.setIsActive(1);
			String syntelLanId=rightPriceRepository.getSyntelId(this.auditTrails.getCurrentUser());
			if(syntelLanId.isEmpty() || syntelLanId==null)
			{	
				costBreakupData.setUpdatedBy(this.auditTrails.getCurrentUser());
				costBreakupData.setUpdatedOn(this.auditTrails.getCurrentTimeStamp());
			}
			else
			{
			costBreakupData.setUpdatedBy(syntelLanId);
			costBreakupData.setUpdatedOn(this.auditTrails.getCurrentTimeStamp());
			}
		}

		return rightPriceRepository.updateCostBreakupData(costBreakup);
	}



	@Override
	public ResponseEntity<Object> getDealPricingData(int versionId) {
		// TODO Auto-generated method stub
		return rightPriceRepository.getDealPricingData(versionId);
	}

	@Override
	public ResponseEntity<Object> getWhatIfDataExcel(int rpVrsId) {
		return rightPriceRepository.getWhatIfDataExcel(rpVrsId);
	}
	@Override
	public ResponseEntity<Object> getFPCostCalCulationOrderedData(int towerId, int rpdealVersionId) {
		return rightPriceRepository.getFPCostCalCulationOrderedData(towerId,rpdealVersionId);
	}
	
	@Override
	public ResponseEntity<Object> getFpVersionsWhatIfApproval(String crmDealId) {
		return rightPriceRepository.getFpVersionsWhatIfApproval(crmDealId);
	}
	
	@Override
	public ResponseEntity<Object> getFpToExchangeRates(int rpVrsId,int currencyId) {
		return rightPriceRepository.getFpToExchangeRates(rpVrsId,currencyId);
	}



	@Override
	public ResponseEntity<Object> getRateCardsBasedOnIndustry(int customerVerticalId, int industry) {
		// TODO Auto-generated method stub
		return rightPriceRepository.getRateCardsBasedOnIndustry(customerVerticalId,industry);
	}
	
	@Override
	public ResponseEntity<Object> getOldDealDetails(int customerId,String startDate) {
		return rightPriceRepository.getOldDealDetails(customerId,startDate);
	}



	@Override
	public ResponseEntity<Object> uploadfpDealStaffingFile(MultipartFile file, String fileName,
			InputStream uploadedInputStream, Integer rpDealVersionId, Integer dealAutoTowerId) {
		try {
			FileObjectTable fileObj = new FileObjectTable();
			FPManualDealAttachment fpData=new FPManualDealAttachment();
			String newFileName = "";
			fileObj.setActiveStatus(1);
			fileObj.setFileName(fileName);
			if(file.getOriginalFilename().contains("\\"))
			{
				String strMain = file.getOriginalFilename();
				String[] arrSplit = new String[100];
			    arrSplit = strMain.split("\\\\");
			    
			    System.out.println(arrSplit.length+" "+arrSplit[(arrSplit.length-1)]);
			    int size=arrSplit.length;
			    newFileName = arrSplit[(arrSplit.length-1)];
			    fileObj.setOriginalFileName(newFileName);
			    fpData.setFileName(newFileName);
			}
			else{
			fileObj.setOriginalFileName(file.getOriginalFilename());
			}
			fileObj.setObject(file.getBytes());
			String syntelLanId=rightPriceRepository.getSyntelId(this.auditTrails.getCurrentUser());
			if(syntelLanId.isEmpty() || syntelLanId==null)
			{	
				fileObj.setCreatedBy(this.auditTrails.getCurrentUser());
				fileObj.setCreatedOn(this.auditTrails.getCurrentTimeStamp());
				fileObj.setLastUpdatedBy(this.auditTrails.getCurrentUser());
				fileObj.setLastUpdatedOn(this.auditTrails.getCurrentTimeStamp());
				fpData.setCreatedBy(this.auditTrails.getCurrentUser());
				fpData.setCreatedOn(this.auditTrails.getCurrentTimeStamp());
				fpData.setDateStamp(this.auditTrails.getCurrentTimeStamp());
				fpData.setUpdatedBy(this.auditTrails.getCurrentUser());
				fpData.setUpdatedOn(this.auditTrails.getCurrentTimeStamp());
			}
			else
			{
			fileObj.setCreatedBy(syntelLanId);
			fileObj.setCreatedOn(this.auditTrails.getCurrentTimeStamp());
			fileObj.setLastUpdatedBy(syntelLanId);
			fileObj.setLastUpdatedOn(this.auditTrails.getCurrentTimeStamp());
			fpData.setCreatedBy(syntelLanId);
			fpData.setCreatedOn(this.auditTrails.getCurrentTimeStamp());
			fpData.setDateStamp(this.auditTrails.getCurrentTimeStamp());
			fpData.setUpdatedBy(syntelLanId);
			fpData.setUpdatedOn(this.auditTrails.getCurrentTimeStamp());
			}
			fpData.setIsActive(1);
			fpData.setRpDealVersionId(rpDealVersionId);
			fpData.setDealAutoTowerId(dealAutoTowerId);
			fpData.setDocType(0);
			return rightPriceRepository.uploadfpDealStaffingFile(file,fileName,uploadedInputStream,fileObj,rpDealVersionId,fpData);
		} catch (IOException e) {
			AppLoger.APPLOGGER.info("Exception occured while inserting object " + e.getMessage());
			return ResponseEntity.status(205)
				       .contentType(MediaType.TEXT_PLAIN)
				       .body("Currently We are facing technical issues, please try again later.");
		}
	}
	
	@Override
	public ResponseEntity<Object> uploadEstimationDealFile(MultipartFile file, String fileName,
			InputStream uploadedInputStream, Integer versionId,String comment,String category,String docType) {
		try {
			FileObjectTable fileObj = new FileObjectTable();
			FPDealAttachment fpData=new FPDealAttachment();
			EstimationAttachment eAData=new EstimationAttachment();
			
			fileObj.setActiveStatus(1);
			fileObj.setFileName(fileName);
			if(file.getOriginalFilename().contains("\\"))
			{
				String strMain = file.getOriginalFilename();
				String[] arrSplit = new String[100];
			    arrSplit = strMain.split("\\\\");
			    
			    System.out.println(arrSplit.length+" "+arrSplit[(arrSplit.length-1)]);
			    int size=arrSplit.length;
			    fileObj.setOriginalFileName(arrSplit[size-1]);
			}
			else{
			fileObj.setOriginalFileName(file.getOriginalFilename());
			}
			fileObj.setObject(file.getBytes());
			String syntelLanId=rightPriceRepository.getSyntelId(this.auditTrails.getCurrentUser());
			if(syntelLanId.isEmpty() || syntelLanId==null)
			{	
				fileObj.setCreatedBy(this.auditTrails.getCurrentUser());
				fileObj.setCreatedOn(this.auditTrails.getCurrentTimeStamp());
				fileObj.setLastUpdatedBy(this.auditTrails.getCurrentUser());
				fileObj.setLastUpdatedOn(this.auditTrails.getCurrentTimeStamp());
				fpData.setCreatedBy(this.auditTrails.getCurrentUser());
				fpData.setCreatedOn(this.auditTrails.getCurrentTimeStamp());
				fpData.setUpdatedBy(this.auditTrails.getCurrentUser());
				fpData.setUpdatedOn(this.auditTrails.getCurrentTimeStamp());
				eAData.setCreatedBy(this.auditTrails.getCurrentUser());
				eAData.setCreatedOn(this.auditTrails.getCurrentTimeStamp());
				eAData.setUpdatedBy(this.auditTrails.getCurrentUser());
				eAData.setUpdatedOn(this.auditTrails.getCurrentTimeStamp());
			}
			else
			{
			fileObj.setCreatedBy(syntelLanId);
			fileObj.setCreatedOn(this.auditTrails.getCurrentTimeStamp());
			fileObj.setLastUpdatedBy(syntelLanId);
			fileObj.setLastUpdatedOn(this.auditTrails.getCurrentTimeStamp());
			fpData.setCreatedBy(syntelLanId);
			fpData.setCreatedOn(this.auditTrails.getCurrentTimeStamp());
			fpData.setUpdatedBy(syntelLanId);
			fpData.setUpdatedOn(this.auditTrails.getCurrentTimeStamp());
			eAData.setCreatedBy(syntelLanId);
			eAData.setCreatedOn(this.auditTrails.getCurrentTimeStamp());
			eAData.setUpdatedBy(syntelLanId);
			eAData.setUpdatedOn(this.auditTrails.getCurrentTimeStamp());
			}
			fpData.setIsActive(1);
			fpData.setVersionId(versionId);
			fpData.setCategory(category);
			eAData.setComment(comment);
			eAData.setDocType(docType);
			eAData.setIsActive(1);
			
			return rightPriceRepository.uploadEstimationDealFile(file,fileName,uploadedInputStream,fileObj,versionId,fpData,eAData);
		} catch (IOException e) {
			AppLoger.APPLOGGER.info("Exception occured while inserting object " + e.getMessage());
			return ResponseEntity.status(205)
				       .contentType(MediaType.TEXT_PLAIN)
				       .body("Currently We are facing technical issues, please try again later.");
		}
	}
	
	@Override
	public ResponseEntity<Object> getUploadedEADoc(int versionId) {
		return rightPriceRepository.getUploadedEADoc(versionId);
	}
	
	@Override
	public ResponseEntity<Object> deleteEADoc(int attachmentId) {
		return rightPriceRepository.deleteEADoc(attachmentId);
	}



	@Override
	public ResponseEntity<Object> getCountryByManualFlag(int manualCountryId) {
		// TODO Auto-generated method stub
		return rightPriceRepository.getCountryByManualFlag(manualCountryId);
	}
	
	@Override
	public ResponseEntity<Object> getAttachment(int rpDealVersionId,int cityId) {
		return rightPriceRepository.getAttachment(rpDealVersionId,cityId);
	}
	

	@Override
	public ResponseEntity<Object> uploadTMDealStaffingFile(MultipartFile file, String fileName,
			InputStream uploadedInputStream, Integer rpDealVersionId, Integer dealAutoTowerId) {
		try {
			FileObjectTable fileObj = new FileObjectTable();
			FPManualDealAttachment fpData=new FPManualDealAttachment();
			String newFileName = "";
			fileObj.setActiveStatus(1);
			fileObj.setFileName(fileName);
			if(file.getOriginalFilename().contains("\\"))
			{
				String strMain = file.getOriginalFilename();
				String[] arrSplit = new String[100];
			    arrSplit = strMain.split("\\\\");
			    
			    System.out.println(arrSplit.length+" "+arrSplit[(arrSplit.length-1)]);
			    int size=arrSplit.length;
			    newFileName = arrSplit[(arrSplit.length-1)];
			    fileObj.setOriginalFileName(newFileName);
			    fpData.setFileName(newFileName);
			}
			else{
			fileObj.setOriginalFileName(file.getOriginalFilename());
			}
			fileObj.setObject(file.getBytes());
			String syntelLanId=rightPriceRepository.getSyntelId(this.auditTrails.getCurrentUser());
			if(syntelLanId.isEmpty() || syntelLanId==null)
			{	
				fileObj.setCreatedBy(this.auditTrails.getCurrentUser());
				fileObj.setCreatedOn(this.auditTrails.getCurrentTimeStamp());
				fileObj.setLastUpdatedBy(this.auditTrails.getCurrentUser());
				fileObj.setLastUpdatedOn(this.auditTrails.getCurrentTimeStamp());
				fpData.setCreatedBy(this.auditTrails.getCurrentUser());
				fpData.setCreatedOn(this.auditTrails.getCurrentTimeStamp());
				fpData.setDateStamp(this.auditTrails.getCurrentTimeStamp());
				fpData.setUpdatedBy(this.auditTrails.getCurrentUser());
				fpData.setUpdatedOn(this.auditTrails.getCurrentTimeStamp());
			}
			else
			{
			fileObj.setCreatedBy(syntelLanId);
			fileObj.setCreatedOn(this.auditTrails.getCurrentTimeStamp());
			fileObj.setLastUpdatedBy(syntelLanId);
			fileObj.setLastUpdatedOn(this.auditTrails.getCurrentTimeStamp());
			fpData.setCreatedBy(syntelLanId);
			fpData.setCreatedOn(this.auditTrails.getCurrentTimeStamp());
			fpData.setDateStamp(this.auditTrails.getCurrentTimeStamp());
			fpData.setUpdatedBy(syntelLanId);
			fpData.setUpdatedOn(this.auditTrails.getCurrentTimeStamp());
			}
			fpData.setIsActive(1);
			fpData.setRpDealVersionId(rpDealVersionId);
			fpData.setDealAutoTowerId(dealAutoTowerId);
			fpData.setDocType(0);
			return rightPriceRepository.uploadTMDealStaffingFile(file,fileName,uploadedInputStream,fileObj,rpDealVersionId,fpData);
		} catch (IOException e) {
			AppLoger.APPLOGGER.info("Exception occured while inserting object " + e.getMessage());
			return ResponseEntity.status(205)
				       .contentType(MediaType.TEXT_PLAIN)
				       .body("Currently We are facing technical issues, please try again later.");
		}
	}
	@Override
	public ResponseEntity<Object> getRiskManagersMemberDetail(String user) {
		return rightPriceRepository.getRiskManagersMemberDetail(user);
	}



	@Override
	public ResponseEntity<Object> getDelicgateUserAccess(String user) {
		// TODO Auto-generated method stub
		return rightPriceRepository.getDelicgateUserAccess(user);
	}



	@Override
	public ResponseEntity<Object> getVerticalApproverData() {
		// TODO Auto-generated method stub
		return rightPriceRepository.getVerticalApproverData();
	}



	@Override
	public ResponseEntity<Object> getDUHCustomerVerticalMapping(Vertical[] vertical) {
		// TODO Auto-generated method stub
		return rightPriceRepository.getDUHCustomerVerticalMapping(vertical);
	}



	@Override
	public ResponseEntity<Object> getVerticalByCustId(int customerId) {
		// TODO Auto-generated method stub
		return  rightPriceRepository.getVerticalByCustId(customerId);
	}
	
	@Override
	public ResponseEntity<Object> getCrmDealDetails(int crmDealId) {
		// TODO Auto-generated method stub
		return  rightPriceRepository.getCrmDealDetails(crmDealId);
	}

	@Override
	public ResponseEntity<Object> saveTcvTMDeal(int tcv, int rpVrsId){
		return rightPriceRepository.saveTcvTMDeal(tcv, rpVrsId);
	}
	
	@Override
	public ResponseEntity<Object> getStaffingSubContractorPricing(int rpVrsId){
		return rightPriceRepository.getStaffingSubContractorPricing(rpVrsId);
	}
	
	@Override
	public ResponseEntity<Object> getOldDealDetailsPricing(int oldDealId) {
		// TODO Auto-generated method stub
		return  rightPriceRepository.getOldDealDetailsPricing(oldDealId);
	}

	@Override
	public ResponseEntity<Object> getDealsForCust(int customerId, int dealTypeId) {
		return rightPriceRepository.getDealsForCust(customerId,dealTypeId);
	}



	@Override
	public ResponseEntity<Object> getDealStatusData(MyDashboardDeal[] dealData) {
		// TODO Auto-generated method stub
		return rightPriceRepository.getDealStatusData(dealData);
	}



	@Override
	public ResponseEntity<Object> updateRecycleStatus(int rcId) {
		// TODO Auto-generated method stub
		return rightPriceRepository.updateRecycleStatus(rcId);
	}



	@Override
	public ResponseEntity<Object> updateRateUtilizationRates(List<RateCardRoleUtilization> rateCardRoleUtilization) {
		// TODO Auto-generated method stub
				return rightPriceRepository.updateRateUtilizationRates(rateCardRoleUtilization);
	}
	
	@Override
	public ResponseEntity<Object> viewRolesExportDetails(int towerId, int dealVersionId) {
		return rightPriceRepository.viewRolesExportDetails(towerId,dealVersionId);
	}

	@Override
	public ResponseEntity<Object> getRateCardInfo(int customerVerticalMappingId) {
		return rightPriceRepository.getRateCardInfo(customerVerticalMappingId);
	}
	
	
	public ResponseEntity<Object> uploadManualRateCard_GFT(MultipartFile file, String fileName,
			InputStream uploadedInputStream,int rpDealVersionId,int dealTowerId,int countryId) {
		
		try {
			/*FileObjectTable fileObj = new FileObjectTable();
			fileObj.setActiveStatus(1);
			fileObj.setFileName(fileName);
			fileObj.setOriginalFileName(file.getOriginalFilename());
			fileObj.setObject(file.getBytes());
			fileObj.setCreatedBy(syntelLanId);
			fileObj.setCreatedOn(this.auditTrails.getCurrentTimeStamp());
			fileObj.setLastUpdatedBy(syntelLanId);
			fileObj.setLastUpdatedOn(this.auditTrails.getCurrentTimeStamp());*/
			FileObjectTable fileObj = new FileObjectTable();
			FPManualDealAttachment fpData=new FPManualDealAttachment();
			String newFileName = "";
			fileObj.setActiveStatus(1);
			fileObj.setFileName(fileName);
			if(file.getOriginalFilename().contains("\\"))
			{
				String strMain = file.getOriginalFilename();
				String[] arrSplit = new String[100];
			    arrSplit = strMain.split("\\\\");
			    
			    System.out.println(arrSplit.length+" "+arrSplit[(arrSplit.length-1)]);
			    int size=arrSplit.length;
			    newFileName = arrSplit[(arrSplit.length-1)];
			    fileObj.setOriginalFileName(newFileName);
			    fpData.setFileName(newFileName);
			}
			else{
			fileObj.setOriginalFileName(file.getOriginalFilename());
			}
			fileObj.setObject(file.getBytes());
			String syntelLanId=rightPriceRepository.getSyntelId(this.auditTrails.getCurrentUser());
			if(syntelLanId.isEmpty() || syntelLanId==null)
			{	
				fileObj.setCreatedBy(this.auditTrails.getCurrentUser());
				fileObj.setCreatedOn(this.auditTrails.getCurrentTimeStamp());
				fileObj.setLastUpdatedBy(this.auditTrails.getCurrentUser());
				fileObj.setLastUpdatedOn(this.auditTrails.getCurrentTimeStamp());
				fpData.setCreatedBy(this.auditTrails.getCurrentUser());
				fpData.setCreatedOn(this.auditTrails.getCurrentTimeStamp());
				fpData.setDateStamp(this.auditTrails.getCurrentTimeStamp());
				fpData.setUpdatedBy(this.auditTrails.getCurrentUser());
				fpData.setUpdatedOn(this.auditTrails.getCurrentTimeStamp());
			}
			else
			{
			fileObj.setCreatedBy(syntelLanId);
			fileObj.setCreatedOn(this.auditTrails.getCurrentTimeStamp());
			fileObj.setLastUpdatedBy(syntelLanId);
			fileObj.setLastUpdatedOn(this.auditTrails.getCurrentTimeStamp());
			fpData.setCreatedBy(syntelLanId);
			fpData.setCreatedOn(this.auditTrails.getCurrentTimeStamp());
			fpData.setDateStamp(this.auditTrails.getCurrentTimeStamp());
			fpData.setUpdatedBy(syntelLanId);
			fpData.setUpdatedOn(this.auditTrails.getCurrentTimeStamp());
			}
			fpData.setIsActive(1);
			fpData.setRpDealVersionId(rpDealVersionId);
			fpData.setDealAutoTowerId(dealTowerId);
			fpData.setDocType(0);
			return rightPriceRepository.uploadManualRateCard_GFT(file,fileName,uploadedInputStream,fileObj,fpData, countryId);
		} catch (IOException e) {
			AppLoger.APPLOGGER.info("Exception occured while inserting object " + e.getMessage());
			return ResponseEntity.status(205)
				       .contentType(MediaType.TEXT_PLAIN)
				       .body("Currently We are facing technical issues, please try again later.");
		}
		}
	
	@Override
	public ResponseEntity<Object> getRateGFTCountry(int rcId) {
		return rightPriceRepository.getRateGFTCountry(rcId);
	}
	
	@Override
	public ResponseEntity<Object> getFpDealRateCard(int dealVersionId) {
		return rightPriceRepository.getFpDealRateCard(dealVersionId);
	}
	
	@Override
	public ResponseEntity<Object> getUserName(String lanId) {
		return rightPriceRepository.getUserName(lanId);
	}



	@Override
	public ResponseEntity<Object> getApproverConfig(int rcId) {
		// TODO Auto-generated method stub
		return rightPriceRepository.getApproverConfig(rcId);
	}
	
	@Override
	public ResponseEntity<Object> getFpDealStaffingDataWhatIf(int rpDealVersionId) {
		return rightPriceRepository.getFpDealStaffingDataWhatIf(rpDealVersionId);
	}
	
	@Override
	public ResponseEntity<Object> getAtosRcData() {
		return rightPriceRepository.getAtosRcData();
	}
	
	@Override
	public ResponseEntity<Object> addPracticeDetails(MasterPractice practiceDetails) {
		AppLoger.APPLOGGER.info("in service");
		practiceDetails.setIsActive(1);
		String syntelLanId=rightPriceRepository.getSyntelId(this.auditTrails.getCurrentUser());
		if(syntelLanId.isEmpty() || syntelLanId==null)
		{	
			practiceDetails.setCreatedBy(this.auditTrails.getCurrentUser());
			practiceDetails.setCreatedOn(this.auditTrails.getCurrentTimeStamp());
			practiceDetails.setUpdatedBy(this.auditTrails.getCurrentUser());
			practiceDetails.setUpdatedOn(this.auditTrails.getCurrentTimeStamp());
		}
		else
		{
		practiceDetails.setCreatedBy(syntelLanId);
		practiceDetails.setCreatedOn(this.auditTrails.getCurrentTimeStamp());
		practiceDetails.setUpdatedBy(syntelLanId);
		practiceDetails.setUpdatedOn(this.auditTrails.getCurrentTimeStamp());
		}
		return rightPriceRepository.addPracticeDetails(practiceDetails);
	}
	
	@Override
	public ResponseEntity<Object>updatePracticeDetails(MasterPractice practiceDetails) {
		AppLoger.APPLOGGER.info("inside service");
		try
		{
			
//				countryDetail.setIsActive(1);
			String syntelLanId=rightPriceRepository.getSyntelId(this.auditTrails.getCurrentUser());
			if(syntelLanId.isEmpty() || syntelLanId==null)
			{	
				practiceDetails.setUpdatedBy(this.auditTrails.getCurrentUser());
				practiceDetails.setUpdatedOn(this.auditTrails.getCurrentTimeStamp());
			}
			else
			{
			practiceDetails.setUpdatedBy(syntelLanId);
			practiceDetails.setUpdatedOn(this.auditTrails.getCurrentTimeStamp());
			}
		
			
			return rightPriceRepository.updatePracticeDetails(practiceDetails);
		}
		catch(Exception e){
			throw e;
		}
	}



	@Override
	public ResponseEntity<Object> getActiveRCData(){
		return rightPriceRepository.getActiveRCData(this.auditTrails.getCurrentTimeStamp());
	}

/*	@Override
	public ResponseEntity<Object> getGFTWeeklyData(){
		return rightPriceRepository.getGFTWeeklyData();
	}
	*/
	@Override
	public ResponseEntity<Object> getRCComment(int rcId){
		return rightPriceRepository.getRCComment(rcId);
	}

	@Override
	public ResponseEntity<Object> saveComment(int rcId,String comment){
		
		return rightPriceRepository.saveComment(rcId,comment);
	}

	 @Override
	public ResponseEntity<Object> getFileredAtosRcData(int country, String rcStartDate,String rcEndDate, int currentApStatus){
		return rightPriceRepository.getFileredAtosRcData( country,rcStartDate,rcEndDate,currentApStatus); 
	 }

		@Override
		public ResponseEntity<Object> getCommentData(){
			
			return rightPriceRepository.getCommentData();
		}

		@Override
		public ResponseEntity<Object> getStatusData(){
			
			return rightPriceRepository.getStatusData();
		}



		@Override
		public ResponseEntity<Object> getStatusData(String startdate, String enddate, String status, int vertical) {
			
			return rightPriceRepository.getStatusData(startdate,enddate,status,vertical);
		}



		@Override
		public ResponseEntity<Object> getUpdatedApprovalMatrix(int rpVrsId) {
			// TODO Auto-generated method stub
			return rightPriceRepository.getUpdatedApprovalMatrix(rpVrsId);
		}
	
	/*	@Override
		public ResponseEntity<Object> getPreSalesData(){
			
			return rightPriceRepository.getPreSalesData();
		}*/
		
		@Override
		public ResponseEntity<Object> setSubmitEnabled() {
			return rightPriceRepository.setSubmitEnabled(auditTrails.getCurrentUser());
		}
	
		@Override
		public ResponseEntity<Object> setSubmitDisabled() {
			return rightPriceRepository.setSubmitDisabled();
		}
		
/*		@Override
		public ResponseEntity<Object> getPreSalesDataOnId(int salesId){
			
			return rightPriceRepository.getPreSalesDataOnId(salesId);
		}
		
		@Override
		public ResponseEntity<Object> getPreSalesApprovedData(int salesId){
			
			return rightPriceRepository.getPreSalesApprovedData(salesId);
		}
		
		@Override
		public ResponseEntity<Object> getPreSalesApprovalData(int salesId){
			
			return rightPriceRepository.getPreSalesApprovalData(salesId);
		}
		

		@Override
		public ResponseEntity<Object> getOpportunityDetails() {
			
			return rightPriceRepository.getOpportunityDetails();
		}



		@Override
		public ResponseEntity<Object> putOpportunityDealData(int dealId) {
			
			return rightPriceRepository.putOpportunityDealData(dealId);
		}



		@Override
		public ResponseEntity<Object> getCurrencyCode() {
			return rightPriceRepository.getCurrencyCode();
		}



		@Override
		public ResponseEntity<Object> saveBudgetDetails(Presales preSalesDetails) {
			preSalesDetails.setIsActive(1);
			preSalesDetails.setCreatedBy(syntelLanId);
			preSalesDetails.setCreatedOn(this.auditTrails.getCurrentTimeStamp());
			preSalesDetails.setUpdatedBy(syntelLanId);
			preSalesDetails.setUpdatedOn(this.auditTrails.getCurrentTimeStamp());

			return rightPriceRepository.saveBudgetDetails(preSalesDetails);
		}



		@Override
		public ResponseEntity<Object> submitBudgetDetails(Presales preSalesDetails) {
			preSalesDetails.setIsActive(1);
			preSalesDetails.setCreatedBy(syntelLanId);
			preSalesDetails.setCreatedOn(this.auditTrails.getCurrentTimeStamp());
			preSalesDetails.setUpdatedBy(syntelLanId);
			preSalesDetails.setUpdatedOn(this.auditTrails.getCurrentTimeStamp());

			return rightPriceRepository.submitBudgetDetails(preSalesDetails);
		}
		
		@Override
		public ResponseEntity<Object> searchDataOnOid(Presales preSalesDetails){
				return rightPriceRepository.searchDataOnOid(preSalesDetails); 
		 }
		
		@Override
		public ResponseEntity<Object> getPresalesOpportunityDetails(){
				return rightPriceRepository.getPresalesOpportunityDetails(); 
		}

		@Override
		public ResponseEntity<Object> mapDataOnId(Presales preSalesDetails){
			preSalesDetails.setUpdatedBy(syntelLanId);
			preSalesDetails.setUpdatedOn(this.auditTrails.getCurrentTimeStamp());
			return rightPriceRepository.mapDataOnId(preSalesDetails); 
		}*/

		@Override
		public  List<String> getUserNameByLanId(String userId) {
			return rightPriceRepository.getUserNameByLanId(userId);
		}
		@Override
		public ResponseEntity<Object> getSyntelUserId(String userId) {
			return rightPriceRepository.getSyntelUserId(userId);
		}



		@Override
		public List<String> getUserRoleData(String usreName) {
			// TODO Auto-generated method stub
			return rightPriceRepository.getUserRoleData(usreName);
		}
		
		@Override
		public ResponseEntity<Object> uploadMasterFile(MultipartFile file, String fileName,
				InputStream uploadedInputStream,int rpDealVersionId) {
			
			try {
				/*FileObjectTable fileObj = new FileObjectTable();
				fileObj.setActiveStatus(1);
				fileObj.setFileName(fileName);
				fileObj.setOriginalFileName(file.getOriginalFilename());
				fileObj.setObject(file.getBytes());
				fileObj.setCreatedBy(syntelLanId);
				fileObj.setCreatedOn(this.auditTrails.getCurrentTimeStamp());
				fileObj.setLastUpdatedBy(syntelLanId);
				fileObj.setLastUpdatedOn(this.auditTrails.getCurrentTimeStamp());*/
				FileObjectTable fileObj = new FileObjectTable();
				FPManualDealAttachment fpData=new FPManualDealAttachment();
				String newFileName = "";
				fileObj.setActiveStatus(1);
				fileObj.setFileName(fileName);
				if(file.getOriginalFilename().contains("\\"))
				{
					String strMain = file.getOriginalFilename();
					String[] arrSplit = new String[100];
				    arrSplit = strMain.split("\\\\");
				    
				    System.out.println(arrSplit.length+" "+arrSplit[(arrSplit.length-1)]);
				    int size=arrSplit.length;
				    newFileName = arrSplit[(arrSplit.length-1)];
				    fileObj.setOriginalFileName(newFileName);
				    fpData.setFileName(newFileName);
				}
				else{
				fileObj.setOriginalFileName(file.getOriginalFilename());
				}
				fileObj.setObject(file.getBytes());
				String syntelLanId=rightPriceRepository.getSyntelId(this.auditTrails.getCurrentUser());
				if(syntelLanId.isEmpty() || syntelLanId==null)
				{	
					fileObj.setCreatedBy(this.auditTrails.getCurrentUser());
					fileObj.setCreatedOn(this.auditTrails.getCurrentTimeStamp());
					fileObj.setLastUpdatedBy(this.auditTrails.getCurrentUser());
					fileObj.setLastUpdatedOn(this.auditTrails.getCurrentTimeStamp());
					fpData.setCreatedBy(this.auditTrails.getCurrentUser());
					fpData.setCreatedOn(this.auditTrails.getCurrentTimeStamp());
					fpData.setDateStamp(this.auditTrails.getCurrentTimeStamp());
					fpData.setUpdatedBy(this.auditTrails.getCurrentUser());
					fpData.setUpdatedOn(this.auditTrails.getCurrentTimeStamp());
				}
				else
				{
				fileObj.setCreatedBy(syntelLanId);
				fileObj.setCreatedOn(this.auditTrails.getCurrentTimeStamp());
				fileObj.setLastUpdatedBy(syntelLanId);
				fileObj.setLastUpdatedOn(this.auditTrails.getCurrentTimeStamp());
				fpData.setCreatedBy(syntelLanId);
				fpData.setCreatedOn(this.auditTrails.getCurrentTimeStamp());
				fpData.setDateStamp(this.auditTrails.getCurrentTimeStamp());
				fpData.setUpdatedBy(syntelLanId);
				fpData.setUpdatedOn(this.auditTrails.getCurrentTimeStamp());
				}
				fpData.setIsActive(1);
				fpData.setRpDealVersionId(rpDealVersionId);
				fpData.setDealAutoTowerId(0);
		//document type = 7 for master ratecard uploads 
				fpData.setDocType(7);
				return rightPriceRepository.uploadMasterFile(file,fileName,uploadedInputStream,fileObj,fpData);
			} catch (IOException e) {
				AppLoger.APPLOGGER.info("Exception occured while inserting object " + e.getMessage());
				return ResponseEntity.status(205)
					       .contentType(MediaType.TEXT_PLAIN)
					       .body("Currently We are facing technical issues, please try again later.");
			}
			}
		
		public ResponseEntity<Object> getMasterAttachement(AttachmentMapper rpDealVersionId){
			return rightPriceRepository.getMasterAttachement(rpDealVersionId);
		}

		@Override
		public ResponseEntity<Object> deleteMasterRCAttachment(int objectid) {
			return rightPriceRepository.deleteMasterRCAttachment(objectid);
		}

		@Override
		public ResponseEntity<Object> uploadAutomaticRURFile(MultipartFile file, String fileName,
				InputStream uploadedInputStream,int rpDealVersionId) {
			
			try {
/*				FileObjectTable fileObj = new FileObjectTable();
				fileObj.setActiveStatus(1);
				fileObj.setFileName(fileName);
				fileObj.setOriginalFileName(file.getOriginalFilename());
				fileObj.setObject(file.getBytes());
				fileObj.setCreatedBy(this.auditTrails.getCurrentUser());
				fileObj.setCreatedOn(this.auditTrails.getCurrentTimeStamp());
				fileObj.setLastUpdatedBy(this.auditTrails.getCurrentUser());
				fileObj.setLastUpdatedOn(this.auditTrails.getCurrentTimeStamp());*/
				FileObjectTable fileObj = new FileObjectTable();
				FPManualDealAttachment fpData=new FPManualDealAttachment();
				String newFileName = "";
				fileObj.setActiveStatus(1);
				fileObj.setFileName(fileName);
				if(file.getOriginalFilename().contains("\\"))
				{
					String strMain = file.getOriginalFilename();
					String[] arrSplit = new String[100];
				    arrSplit = strMain.split("\\\\");
				    
				    System.out.println(arrSplit.length+" "+arrSplit[(arrSplit.length-1)]);
				    int size=arrSplit.length;
				    newFileName = arrSplit[(arrSplit.length-1)];
				    fileObj.setOriginalFileName(newFileName);
				    fpData.setFileName(newFileName);
				}
				else{
				fileObj.setOriginalFileName(file.getOriginalFilename());
				}
				fileObj.setObject(file.getBytes());
				fileObj.setCreatedBy(this.auditTrails.getCurrentUser());
				fileObj.setCreatedOn(this.auditTrails.getCurrentTimeStamp());
				fileObj.setLastUpdatedBy(this.auditTrails.getCurrentUser());
				fileObj.setLastUpdatedOn(this.auditTrails.getCurrentTimeStamp());
				
				fpData.setCreatedBy(this.auditTrails.getCurrentUser());
				fpData.setCreatedOn(this.auditTrails.getCurrentTimeStamp());
				fpData.setDateStamp(this.auditTrails.getCurrentTimeStamp());
				fpData.setUpdatedBy(this.auditTrails.getCurrentUser());
				fpData.setUpdatedOn(this.auditTrails.getCurrentTimeStamp());
				fpData.setIsActive(1);
				fpData.setRpDealVersionId(rpDealVersionId);
				fpData.setDealAutoTowerId(0);
		//document type = 8 for Role Utilization and Rates uploads 
				fpData.setDocType(8);
				return rightPriceRepository.uploadMasterFile(file,fileName,uploadedInputStream,fileObj,fpData);
			} catch (IOException e) {
				AppLoger.APPLOGGER.info("Exception occured while inserting object " + e.getMessage());
				return ResponseEntity.status(205)
					       .contentType(MediaType.TEXT_PLAIN)
					       .body("Currently We are facing technical issues, please try again later.");
			}
			}
		
		public ResponseEntity<Object> getRCRoleUtilizationAttachement(AttachmentMapper rpDealVersionId){
			return rightPriceRepository.getRCRoleUtilizationAttachement(rpDealVersionId);
		}
		
		@Override
		public ResponseEntity<Object> getFpDealCostInputsDetailsUpdate(int towerId,int rpdealVersionId) {
			// TODO Auto-generated method stub
			return rightPriceRepository.getFpDealCostInputsDetailsUpdate(towerId,rpdealVersionId);
		}
		
		/*
		@Override
		public ResponseEntity<Object> getFpDealCostInputsDetailsArray(int towerId,int rpdealVersionId) {
			// TODO Auto-generated method stub
			return rightPriceRepository.getFpDealCostInputsDetailsArray(towerId,rpdealVersionId);
		}



		@Override
		public ResponseEntity<Object> getRoleSelectionExcelDetails(int rcId) {
			// TODO Auto-generated method stub
			return rightPriceRepository.getRoleSelectionExcelDetails(rcId);
			//return rightPriceRepository.getRoleSelectionDetails(rcId);
		}*/
		
		@Override
		public ResponseEntity<Object> getRcLocationdetails(int rcId) {
			return rightPriceRepository.getRcLocationdetails(rcId);
		}

/*

		@Override
		public ResponseEntity<Object> saveSFAppointmentData(SyntelSFAppointment syntelAppointmentDetails) {
			return rightPriceRepository.saveSFAppointmentData(syntelAppointmentDetails);
		}

*/
		@Override
		public ResponseEntity<Object> getDisabledCountryData(int countryId) {
			// TODO Auto-generated method stub
			return rightPriceRepository.getDisabledCountryData(countryId);
		}
		
		
		@Override
		public ResponseEntity<Object> getFPDealSFInfo(String dealId) {
			// TODO Auto-generated method stub
			return rightPriceRepository.getFPDealSFInfo(dealId);
		}
		
		@Override
		public ResponseEntity<Object> CheckUserAccess(Integer accId) {
			// TODO Auto-generated method stub
			String strLanID = rightPriceRepository.getSyntelId(this.auditTrails.getCurrentUser());
			if(strLanID == null ||  strLanID == "" || strLanID == "NULL" ){
				AppLoger.APPLOGGER.info("currentUserDetails is " + this.auditTrails.getCurrentUser());
				return rightPriceRepository.CheckUserAccess(accId,this.auditTrails.getCurrentUser());				
			}else{
				AppLoger.APPLOGGER.info("currentUserDetails is " + strLanID);
				return rightPriceRepository.CheckUserAccess(accId,strLanID);
			}
		}

		@Override
		public ResponseEntity<Object> checkCustomerMappingInFin(String crmdealId) {
			// TODO Auto-generated method stub
			return rightPriceRepository.checkCustomerMappingInFin(crmdealId);
		}


		@Override
		public ResponseEntity<Object> getDealDetailsForTM_Selection(String crmdealId) {

				return rightPriceRepository.getDealDetailsForTM_Selection(crmdealId);
			}

		@Override
		public ResponseEntity<Object> getAppCodeData() {

				return rightPriceRepository.getAppCodeData();
			}

		@Override
		public ResponseEntity<Object> getDistDedCatId() {
			// TODO Auto-generated method stub
			return rightPriceRepository.getDistDedCatId();
		}

		public ResponseEntity<Object> getMasterDeductionDataForm(Deduction2 deduction){
			return rightPriceRepository.getMasterDeductionDataForm(deduction);
		}
		
		
		@Override
		public ResponseEntity<Object> getDealDetailsForGFT() {
			// TODO Auto-generated method stub
			return rightPriceRepository.getDealDetailsForGFT();
		}
		
		@Override
		public ResponseEntity<Object> updateAssumptionsParamValue(DeductionforUpdate[] deduction) {
			for (DeductionforUpdate deduction2 : deduction) {
				deduction2.setIsActive(1);
				String syntelLanId=rightPriceRepository.getSyntelId(this.auditTrails.getCurrentUser());
				if(syntelLanId.isEmpty() || syntelLanId==null)
				{	
					deduction2.setLastUpdatedBy(this.auditTrails.getCurrentUser());
					deduction2.setLastUpdatedOn(this.auditTrails.getCurrentTimeStamp());
				}
				else
				{
				deduction2.setLastUpdatedBy(syntelLanId);
				deduction2.setLastUpdatedOn(this.auditTrails.getCurrentTimeStamp());
				}
			}
			return rightPriceRepository.updateAssumptionsParamValue(deduction);
		}
		
		
		@Override
		public ResponseEntity<Object> getviewdealdetails(int customerId) {

				return rightPriceRepository.getviewdealdetails(customerId);
			}
		
		@Override
		public ResponseEntity<Object> getviewrcdetails(int customerId) {

				return rightPriceRepository.getviewrcdetails(customerId);
			}
		public ResponseEntity<Object> saveRateCardDetailsNew(RateCardDetailsNew rateCardDetailsNew) {
			/* rateCardDetailsNew.setIsActive(1); */
			String syntelLanId = rightPriceRepository.getSyntelId(this.auditTrails.getCurrentUser());
			if (syntelLanId.isEmpty() || syntelLanId == null) {
				rateCardDetailsNew.setCreatedBy(this.auditTrails.getCurrentUser());
				rateCardDetailsNew.setCreatedOn(this.auditTrails.getCurrentTimeStamp());
				rateCardDetailsNew.setUpdatedBy(this.auditTrails.getCurrentUser());
				rateCardDetailsNew.setUpdatedOn(this.auditTrails.getCurrentTimeStamp());
			} else {
				rateCardDetailsNew.setCreatedBy(syntelLanId);
				rateCardDetailsNew.setCreatedOn(this.auditTrails.getCurrentTimeStamp());
				rateCardDetailsNew.setUpdatedBy(syntelLanId);
				rateCardDetailsNew.setUpdatedOn(this.auditTrails.getCurrentTimeStamp());
			}
			return rightPriceRepository.saveRateCardDetailsNew(rateCardDetailsNew);
		}

		@Override
		public ResponseEntity<Object> getRateCardDetailsGFT(int rcId) {
			return rightPriceRepository.getRateCardDetailsGFT(rcId);

		}

		@Override
		public ResponseEntity<Object> getRateCardsNew(int customerVerticalMappingId) {
			return rightPriceRepository.getRateCardsNew(customerVerticalMappingId);
		}

		
		  @Override public ResponseEntity<Object> uploadManualFileNew(MultipartFile
		  file, String fileName, InputStream uploadedInputStream, Integer rcId, Integer
		  Id) { try { FileObjectTable fileObj = new FileObjectTable();
		  RateCardAttachment rcData = new RateCardAttachment();
		  fileObj.setActiveStatus(1); fileObj.setFileName(fileName);
		  
		  if (file.getOriginalFilename().contains("\\")) { String strMain =
		  file.getOriginalFilename(); String[] arrSplit = new String[100]; arrSplit =
		  strMain.split("\\\\");
		  
		  System.out.println(arrSplit.length + " " + arrSplit[(arrSplit.length - 1)]);
		  int size = arrSplit.length; fileObj.setOriginalFileName(arrSplit[size - 1]);
		  } else { fileObj.setOriginalFileName(file.getOriginalFilename()); }
		  fileObj.setObject(file.getBytes()); String syntelLanId =
		  rightPriceRepository.getSyntelId(this.auditTrails.getCurrentUser()); if
		  (syntelLanId.isEmpty() || syntelLanId == null) {
		  fileObj.setCreatedBy(this.auditTrails.getCurrentUser());
		  fileObj.setCreatedOn(this.auditTrails.getCurrentTimeStamp());
		  fileObj.setLastUpdatedBy(this.auditTrails.getCurrentUser());
		  fileObj.setLastUpdatedOn(this.auditTrails.getCurrentTimeStamp());
		  rcData.setCreatedBy(this.auditTrails.getCurrentUser());
		  rcData.setCreatedOn(this.auditTrails.getCurrentTimeStamp());
		  rcData.setUpdatedBy(this.auditTrails.getCurrentUser());
		  rcData.setUpdatedOn(this.auditTrails.getCurrentTimeStamp()); } else {
		  fileObj.setCreatedBy(syntelLanId);
		  fileObj.setCreatedOn(this.auditTrails.getCurrentTimeStamp());
		  fileObj.setLastUpdatedBy(syntelLanId);
		  fileObj.setLastUpdatedOn(this.auditTrails.getCurrentTimeStamp());
		  rcData.setCreatedBy(syntelLanId);
		  rcData.setCreatedOn(this.auditTrails.getCurrentTimeStamp());
		  rcData.setUpdatedBy(syntelLanId);
		  rcData.setUpdatedOn(this.auditTrails.getCurrentTimeStamp()); }
		  rcData.setIsActive(1); rcData.setRcId(rcId); rcData.setId(Id);
		  rcData.setFileName(fileName);
		  
		  return rightPriceRepository.uploadManualFileNew(file, fileName,
		  uploadedInputStream, fileObj, rcId, rcData); } catch (IOException e) {
		  AppLoger.APPLOGGER.info("Exception occured while inserting object " +
		  e.getMessage()); return
		  ResponseEntity.status(205).contentType(MediaType.TEXT_PLAIN)
		  .body("Currently We are facing technical issues, please try again later."); }
		  }
		 
		/*
		 * @Override public ResponseEntity<Object> getFileData(String rcId) { // TODO
		 * Auto-generated method stub return rightPriceRepository.getFileData(rcId); }
		 */
		
		public ResponseEntity<Object> getMasterAttachementRC(AttachmentRCMapper rcId) {
			return rightPriceRepository.getMasterAttachementRC(rcId);
		}
		
		@Override
		public ResponseEntity<Object> downloadFileWithFileNameRC(int Id) {
			return rightPriceRepository.downloadFileWithFileNameRC(Id);
		}
		@Override
		public ResponseEntity<Object> deleteRCFile(int AttachmentId) {
		// TODO Auto-generated method stub
		return rightPriceRepository.deleteRCFile(AttachmentId);
		}
	
		/*Deal*/
		
		@Override
		public ResponseEntity<Object> getDealDetailss(String crmDealId) {
			System.out.println("198 198 198+++++++++++++++++++++++++++++++===++++++++++++++++++++++");
			return rightPriceRepository.getDealDetailss(crmDealId);
		}
		
		@Override
		public ResponseEntity<Object> getDealId(int customerId) {
			return rightPriceRepository.getDealId(customerId);
		}
		
		@Override
		public ResponseEntity<Object> getAllDealsFrmCrmStages(int customerId, int dealTypeId) {
			return rightPriceRepository.getAllDealsFrmCrmStages(customerId,  dealTypeId);
		}
		
		@Override
		public ResponseEntity<Object> getAllDealsForCust(int customerId,int dealTypeId) {
			return rightPriceRepository.getAllDealsForCust(customerId ,dealTypeId);
		}

		
		@Override
		public ResponseEntity<Object> getDealDetailsFor_Selection(String crmDealId) {
			return rightPriceRepository.getDealDetailsFor_Selection(crmDealId);
		}

		
		@Override
		public ResponseEntity<Object> saveDealData(DealCreationDetails_RP_V2 dealDetails) {
			String syntelLanId=rightPriceRepository.getSyntelId(this.auditTrails.getCurrentUser());
			if(syntelLanId.isEmpty() || syntelLanId==null)
			{	
				dealDetails.setCreatedBy(this.auditTrails.getCurrentUser());
				dealDetails.setCreatedOn(this.auditTrails.getCurrentTimeStamp());
				dealDetails.setUpdatedBy(this.auditTrails.getCurrentUser());
				dealDetails.setUpdatedOn(this.auditTrails.getCurrentTimeStamp());
			}
			else
			{
				dealDetails.setCreatedBy(syntelLanId);
				dealDetails.setCreatedOn(this.auditTrails.getCurrentTimeStamp());
				dealDetails.setUpdatedBy(syntelLanId);
				dealDetails.setUpdatedOn(this.auditTrails.getCurrentTimeStamp());
			}
			dealDetails.setIsActive(1);
			return rightPriceRepository.saveDealData(dealDetails);
		}
		

		@Override
		public ResponseEntity<Object> getFileData(String crmDealId) {
			// TODO Auto-generated method stub
			return rightPriceRepository.getFileData(crmDealId);
		}



		@Override
		public ResponseEntity<Object> getFileAttachementData(AttachmentMapper2 crmDealId) {
			return rightPriceRepository.getFileAttachementData(crmDealId);
		}
		
		@Override
		public ResponseEntity<Object> deleteDealFile(int dealAttachmentId) {
			// TODO Auto-generated method stub
			return rightPriceRepository.deleteDealFile(dealAttachmentId);
		}
		
		@Override
		public ResponseEntity<Object> uploadDealsFile(MultipartFile file, String fileName,
				String crmDealId,InputStream uploadedInputStream) {
			System.out.println("uploadFileData========================================serviceImpl");
			DealAttachmentV2 dealData=new DealAttachmentV2();
			if(file.getOriginalFilename().contains("\\"))
			{
				String strMain = file.getOriginalFilename();
				String[] arrSplit = new String[100];
			    arrSplit = strMain.split("\\\\");
			    
			    System.out.println(arrSplit.length+" "+arrSplit[(arrSplit.length-1)]);
			    int size=arrSplit.length;
				/*
				 * fileObj.setOriginalFileName(arrSplit[size-1]);
				 */				}
			else{
				/* fileObj.setOriginalFileName(file.getOriginalFilename()); */
			}
			/* fileObj.setObject(file.getBytes()); */
			String syntelLanId=rightPriceRepository.getSyntelId(this.auditTrails.getCurrentUser());
			if(syntelLanId.isEmpty() || syntelLanId==null)
			{	
				dealData.setCreatedBy(this.auditTrails.getCurrentUser());
				dealData.setCreatedOn(this.auditTrails.getCurrentTimeStamp());
				dealData.setUpdatedBy(this.auditTrails.getCurrentUser());
				dealData.setUpdatedOn(this.auditTrails.getCurrentTimeStamp());
			}
			else
			{
				dealData.setCreatedBy(syntelLanId);
				dealData.setCreatedOn(this.auditTrails.getCurrentTimeStamp());
				dealData.setUpdatedBy(syntelLanId);
				dealData.setUpdatedOn(this.auditTrails.getCurrentTimeStamp());
			}
			/*
			 * Date date=new Date(); String datetime= date.getDate()+ ""+ date.getTime();
			 * dealData.setDateStamp(datetime);
			 */
			dealData.setIsActive(1);
			dealData.setDealId(crmDealId);
			dealData.setFileName(fileName);
			
			/*
			 * Date date = Calendar.getInstance().getTime(); DateFormat dateFormat = new
			 * SimpleDateFormat ("dd-mm-yyyy hh:mm:ss"); String strDate = dateFormat.format
			 * (date); dealData.setDateStamp(strDate);
			 */
			return rightPriceRepository.uploadDealsFile(file,fileName,dealData,crmDealId,uploadedInputStream);
		
		}



		@Override
		public ResponseEntity<Object> downloadDealFileWithFileName(int dealAttachmentId) {
			return rightPriceRepository.downloadDealFileWithFileName(dealAttachmentId);
		}


		@Override
		public ResponseEntity<Object> getDataOnSearchview(int rcId) {
			return rightPriceRepository.getDataOnSearchview(rcId);
		}
		@Override
		public ResponseEntity<Object> getDealDataOnSearchview(String dealId) {
			return rightPriceRepository.getDealDataOnSearchview(dealId);
		}
		
		@Override
		public ResponseEntity<Object> getEmpRoleDetails(EmpDetails empDetails) {
			return rightPriceRepository.getEmpRoleDetails(empDetails);
		}
		
		@Override
		public ResponseEntity<Object> addRProleData(FPDCRCAndProjectDetails role) {
			if(role.getRpRoleAccess().size()>0){
				for(RpRole rpRoleAcces : role.getRpRoleAccess()) {
					rpRoleAcces.setActivestatus(1);
					String syntelLanId=rightPriceRepository.getSyntelId(this.auditTrails.getCurrentUser());
					if(syntelLanId.isEmpty() || syntelLanId==null)
					{	
						rpRoleAcces.setCreatedBy(this.auditTrails.getCurrentUser());
						System.out.println(this.auditTrails.getCurrentUser());
						rpRoleAcces.setCreatedOn(this.auditTrails.getCurrentTimeStamp());
						rpRoleAcces.setLastupdatedBy(this.auditTrails.getCurrentUser());
						rpRoleAcces.setLastupdatedOn(this.auditTrails.getCurrentTimeStamp());
						rpRoleAcces.setRoleassignmentcatid(2);
					}
					else
					{
						rpRoleAcces.setCreatedBy(syntelLanId);
						rpRoleAcces.setCreatedOn(this.auditTrails.getCurrentTimeStamp());
						rpRoleAcces.setLastupdatedBy(syntelLanId);
						
						rpRoleAcces.setLastupdatedOn(this.auditTrails.getCurrentTimeStamp());
						rpRoleAcces.setRoleassignmentcatid(2);
						
					}
				}
			}
			return rightPriceRepository.addRProleData(role);
			}

		
		@Override
		public ResponseEntity<Object> downloadVideos(int type) {
		return rightPriceRepository.downloadVideos(type);
		}
		@Override
		public ResponseEntity<Object> getXOSkillFromMasterRoleId(int masterRoleId) {
			
			return rightPriceRepository.getXOSkillFromMasterRoleId(masterRoleId);
		}
		
		
		@Override
		public ResponseEntity<Object> getCurrentCustomerUserDetailskpo(int industry, String vertical) {
			return rightPriceRepository.getCurrentCustomerUserDetailskpo(industry,vertical);
		}

		/*@Override
		public ResponseEntity<Object> getCurrentCustomerUserDetails(int verticalId) {
			return rightPriceRepository.getCurrentCustomerUserDetails(verticalId);
		}*/
	//manglam updated
		@Override
		public ResponseEntity<Object> getWonDealDetailsForGFT() {
			// TODO Auto-generated method stub
			return rightPriceRepository.getWonDealDetailsForGFT();
		}
		
	public ResponseEntity<Object> getPLAttachementData(AttachmentMapperPL rpDealVersionId) {
		return rightPriceRepository.getPLAttachementData(rpDealVersionId);
	}
    
	@Override
	public ResponseEntity<Object> uploadPLFileData(MultipartFile file, String fileName,
			InputStream uploadedInputStream, Integer versionId) {
		System.out.println("uploadPLFileData========================================serviceImpl");
		try {
			FileObjectTable fileObj = new FileObjectTable();
			PLAttachment fpData=new PLAttachment();
			fileObj.setActiveStatus(1);
			fileObj.setFileName(fileName);
			if(file.getOriginalFilename().contains("\\"))
			{
									
				String strMain = file.getOriginalFilename();
				String[] arrSplit = new String[100];
				arrSplit = strMain.split("\\");
				System.out.println(arrSplit.length+" "+arrSplit[(arrSplit.length-1)]);
				int size=arrSplit.length;
				fileObj.setOriginalFileName(arrSplit[size-1]);
				}			
			else{			
				fileObj.setOriginalFileName(file.getOriginalFilename());
				}			
			fileObj.setObject(file.getBytes());
			String syntelLanId=rightPriceRepository.getSyntelId(this.auditTrails.getCurrentUser());
			if(syntelLanId.isEmpty() || syntelLanId==null)
			{					
				fileObj.setCreatedBy(this.auditTrails.getCurrentUser());
				fileObj.setCreatedOn(this.auditTrails.getCurrentTimeStamp());
				fileObj.setLastUpdatedBy(this.auditTrails.getCurrentUser());
				fileObj.setLastUpdatedOn(this.auditTrails.getCurrentTimeStamp());
				fpData.setCreatedBy(this.auditTrails.getCurrentUser());
				fpData.setCreatedOn(this.auditTrails.getCurrentTimeStamp());
				fpData.setUpdatedBy(this.auditTrails.getCurrentUser());
				fpData.setUpdatedOn(this.auditTrails.getCurrentTimeStamp());
				}			
			else			
			{			
				fileObj.setCreatedBy(syntelLanId);
				fileObj.setCreatedOn(this.auditTrails.getCurrentTimeStamp());
				fileObj.setLastUpdatedBy(syntelLanId);
				fileObj.setLastUpdatedOn(this.auditTrails.getCurrentTimeStamp());
				fpData.setCreatedBy(syntelLanId);
				fpData.setCreatedOn(this.auditTrails.getCurrentTimeStamp());
				fpData.setUpdatedBy(syntelLanId);
				fpData.setUpdatedOn(this.auditTrails.getCurrentTimeStamp());
				}			
			fpData.setIsActive(1);
			fpData.setVersionId(versionId);
			
			return rightPriceRepository.uploadPLFileData(file,fileName,uploadedInputStream,fileObj,versionId,fpData);
			}
		catch (IOException e) 
		{			
				AppLoger.APPLOGGER.info("Exception occured while inserting object " + e.getMessage());
				return ResponseEntity.status(205)
						.contentType(MediaType.TEXT_PLAIN)
						.body("Currently We are facing technical issues, please try again later.");
				
		}	
		}
		
		
		@Override	
		public ResponseEntity<Object> getPageTrckrData(int rcId) {
			return rightPriceRepository.getPageTrckrData(rcId);
			}

		@Override
		public ResponseEntity<Object> getApproverName(int rcId, int rbutype) {
			return rightPriceRepository.getApproverName(rcId,rbutype);
		} //Manglam updted
		
		@Override
		public ResponseEntity<Object> getDealApproverName(int rpVrsId,String rbuName) {
			return rightPriceRepository.getDealApproverName(rpVrsId, rbuName);
		}//akhilesh updated
		
		@Override
		public ResponseEntity<Object> getFPMDApproverNames(int verticalId,int rpDealVersionId, String rbuName) {
			return rightPriceRepository.getFPMDApproverNames(verticalId, rpDealVersionId, rbuName);
		}//akhilesh updated
		
		@Override
		public ResponseEntity<Object> getCurrentCustomerUserDetails(int verticalId,int rbutype) {
			return rightPriceRepository.getCurrentCustomerUserDetails(verticalId,rbutype);
		} //manglam updated
		
		@Override
		public ResponseEntity<Object> getCurrentCustomerUserDetailsJVkpo(int industry, int verticalId) {
			return rightPriceRepository.getCurrentCustomerUserDetailsJVkpo(industry, verticalId);
		}//manglam updated
		
}

