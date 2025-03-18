package com.rightprice.auth.web;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import javax.ws.rs.FormParam;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rightprice.auth.model.*;
import com.rightprice.auth.service.RightPriceService;
import com.rightprice.auth.util.AppLoger;
import com.rightprice.auth.util.AuditTrails;
import com.rightprice.auth.util.ExcelView;
import com.rightprice.auth.util.FileUploadErrors;
import com.sun.corba.se.spi.servicecontext.UEInfoServiceContext;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/RightPrice-DAS")
@Api("RightPriceController")
public class RightPriceController  {

	@Autowired
	private RightPriceService rightPriceService;
	
	@Autowired
	private AuditTrails auditTrails;
	
	@ApiOperation(value = "Get current logged in user details")
	@RequestMapping(value = "/getCurrentUserDetails", method = RequestMethod.GET, headers = "Accept=application/json")
	public ResponseEntity<Object> getCurrentUserDetails() {
		AppLoger.APPLOGGER.info("--------------------------Applogger------------------------------------");
		AppLoger.APPLOGGER.info(
				"/getCurrentUserDetails......................................................................................");
		return rightPriceService.getCurrentUserDetails();
	}
	
	

	@ApiOperation(value = "Get all the rate cards related to current user vertical", response = SubPracticeView.class)
	@RequestMapping(value = "/getRateCards/{customerVerticalMappingId}", method = RequestMethod.GET, headers = "Accept=application/json")
	public ResponseEntity<Object> getRateCards(@PathVariable("customerVerticalMappingId") int customerVerticalMappingId) {
		return rightPriceService.getRateCards(customerVerticalMappingId);
	}

	@ApiOperation(value = "Get rate card details for selected rate card id", response = SubPracticeView.class)
	@RequestMapping(value = "/getRateCardDetailsFromRCId/{rcId}", method = RequestMethod.GET, headers = "Accept=application/json")
	public ResponseEntity<Object> getRateCardDetailsFromRCId(@PathVariable("rcId") int rcId) {
		AppLoger.APPLOGGER.info("Fetching details for rate card id : " + rcId);
		return rightPriceService.getRateCardDetails(rcId);
	}

	@ApiOperation(value = "Add new rate card")
	@RequestMapping(value = "/rateCardDetails", method = RequestMethod.POST)
	public ResponseEntity<Object> rateCardDetails(@RequestBody RateCardDetails rateCardDetails) {
		AppLoger.APPLOGGER.info(rateCardDetails.toString());
		return rightPriceService.rateCardDetails(rateCardDetails);
	}

	@RequestMapping(value = "/getCity/{countryId}", method = RequestMethod.GET, headers = "Accept=application/json")
	public ResponseEntity<Object> getPolicyName(@PathVariable("countryId") int countryId) {
		AppLoger.APPLOGGER.info("Returning policy name for countryId : " + countryId);
		return rightPriceService.getCity(countryId);
	}
	
	
	@RequestMapping(value = "/getCityData/{countryId}", method = RequestMethod.GET, headers = "Accept=application/json")
	public ResponseEntity<Object> getCityData(@PathVariable("countryId") int countryId) {		
		return rightPriceService.getCityData(countryId);
	}
	
	
	@RequestMapping(value = "/getAsumpVisaTypes/{countryId}", method = RequestMethod.GET, headers = "Accept=application/json")
	public ResponseEntity<Object> getVisaTypes(@PathVariable("countryId") int countryId) {
		AppLoger.APPLOGGER.info("Returning visa type for countryId : " + countryId);
		return rightPriceService.getVisaTypes(countryId);
	}

	@ApiOperation(value = "getSearchCity", response = SearchCity.class)
	@RequestMapping(value = "/getSearchCity", method = RequestMethod.POST)
	public ResponseEntity<Object> getSearchCity(@RequestBody SearchCity cityDetails) {
		AppLoger.APPLOGGER.info("country id--------" + cityDetails.getCountryId());
		AppLoger.APPLOGGER.info("city id--------" + cityDetails.getCityId());

		return rightPriceService.getSearchCity(cityDetails);
	}

	@RequestMapping(value = "/getCitycategorization", method = RequestMethod.GET, headers = "Accept=application/json")
	public ResponseEntity<Object> getCityCategorization() {
		AppLoger.APPLOGGER.info("getCityCategorization");

		return rightPriceService.getCityCategorization();
	}

	@ApiOperation(value = "add city details", response = ViewCitiesData.class)
	@RequestMapping(value = "/addCityDetails", method = RequestMethod.POST)
	public ResponseEntity<Object> addCityDetails(@RequestBody ViewCitiesData cityDetailsAdd) {
		AppLoger.APPLOGGER.info("inside add contry controller");
		return rightPriceService.addCityDetails(cityDetailsAdd);
	}

	@ApiOperation(value = "update city details", response = ViewCitiesData.class)
	@RequestMapping(value = "/updateCityDetails", method = RequestMethod.POST)
	public ResponseEntity<Object> updateCityDetails(@RequestBody ViewCitiesData cityDetailsUpdate) {
		AppLoger.APPLOGGER.info("inside update city controller");
		AppLoger.APPLOGGER.info("active status-------------------------------------------------------------- "
				+ cityDetailsUpdate.getIsActive());
		return rightPriceService.updateCityDetails(cityDetailsUpdate);
	}

	/* Rate card details creation [PRASAD] */
	
	/*Fixed Price Deal Creation Rate Card And Project Details [PRASAD]*/ 
	
	@ApiOperation(value = "Add FP deal creation RC and project details for given RP_DEAL_VERSION_ID")
	@RequestMapping(value = "/FPDealCreationRCAndProjectDetails", method = RequestMethod.POST)
	public ResponseEntity<Object> saveFPDealCreationRCAndProjectDetails(@RequestBody FPDCRCAndProjectDetails fPDCRCAndProjectDetails) {
		AppLoger.APPLOGGER.info("saveFPDealCreationRCAndProjectDetails -- Controller");
		AppLoger.APPLOGGER.info(fPDCRCAndProjectDetails.toString());
		AppLoger.APPLOGGER.info("Tower size : "+fPDCRCAndProjectDetails.getDealFPTower().size());
		return rightPriceService.saveFPDealCreationRCAndProjectDetails(fPDCRCAndProjectDetails);
		
	}
	
	@ApiOperation(value = "Add FP deal creation RC and project details for given RP_DEAL_VERSION_ID")
	@RequestMapping(value = "/TMDealCreationRCAndProjectDetails", method = RequestMethod.POST)
	public ResponseEntity<Object> saveTMDealCreationRCAndProjectDetails(@RequestBody FPDCRCAndProjectDetails fPDCRCAndProjectDetails) {		
		return rightPriceService.saveTMDealCreationRCAndProjectDetails(fPDCRCAndProjectDetails);
		
	}

	@ApiOperation(value="get FP deal creation RC and project details for given RP_DEAL_VERSION_ID")
	@RequestMapping(value = "/FPDealCreationRCAndProjectDetails/{dealVersionId}", method = RequestMethod.GET)
	public ResponseEntity<Object> getFPDealCreationRCAndProjectDetails(@PathVariable("dealVersionId") int dealVersionId) {
		AppLoger.APPLOGGER.info(dealVersionId);
		return rightPriceService.getFPDealCreationRCAndProjectDetails(dealVersionId);
	}
	@ApiOperation(value="get TM deal creation RC and project details for given RP_DEAL_VERSION_ID")
	@RequestMapping(value = "/TMDealCreationRCAndProjectDetails/{dealVersionId}", method = RequestMethod.GET)
	public ResponseEntity<Object> getTMDealCreationRCAndProjectDetails(@PathVariable("dealVersionId") int dealVersionId) {
		AppLoger.APPLOGGER.info(dealVersionId);
		return rightPriceService.getTMDealCreationRCAndProjectDetails(dealVersionId);
	}
	/*Fixed Price Deal Creation Rate Card And Project Details [PRASAD]*/ 
	/*Fixed Price Deal Creation Role Selection And Add contractor Role [PRASAD]*/
	
	@ApiOperation(value = "Add FP deal creation Roles and contractor details for given RP_DEAL_VERSION_ID")
	@RequestMapping(value = "/saveTMDCRoleSelectionAndContractorRole", method = RequestMethod.POST)
	public ResponseEntity<Object> saveTMDCRoleSelectionAndContractorRole(@RequestBody FPDCRoleAndContractor fPDCRoleAndContractor) {
		AppLoger.APPLOGGER.info(fPDCRoleAndContractor.getDealVersionId());
		AppLoger.APPLOGGER.info("Role size : "+fPDCRoleAndContractor.getDealRoles().size());
		AppLoger.APPLOGGER.info("Contractor role size : "+fPDCRoleAndContractor.getDealContractorRole().size());
		return rightPriceService.saveTMDCRoleSelectionAndContractorRole(fPDCRoleAndContractor);
	}
	
	@ApiOperation(value = "Add FP deal creation Roles and contractor details for given RP_DEAL_VERSION_ID")
	@RequestMapping(value = "/getFPDCRoleSelectionAndContractorRole/{dealVersionId}", method = RequestMethod.GET)
	public ResponseEntity<Object> getFPDCRoleSelectionAndContractorRole(@PathVariable("dealVersionId") int dealVersionId) {
		AppLoger.APPLOGGER.info(dealVersionId);
		return rightPriceService.getFPDCRoleSelectionAndContractorRole(dealVersionId);
	}
	
	
	/*Fixed Price Deal Creation Role Selection And Add contractor Role [PRASAD]*/

	@RequestMapping(value = "/getCurrency", method = RequestMethod.GET, headers = "Accept=application/json")
	public ResponseEntity<Object> getCurrency() {
		return rightPriceService.getCurrency();
	}

	
	@RequestMapping(value = "/getExchangeRate", method = RequestMethod.GET, headers = "Accept=application/json")
	public ResponseEntity<Object> getExchangeRate() {
		return rightPriceService.getExchangeRate();
	}

	@RequestMapping(value = "/getCountryDetail", method = RequestMethod.GET, headers = "Accept=application/json")
	public ResponseEntity<Object> getCountryDetail() {
		return rightPriceService.getCountryDetail();
	}
	
	@RequestMapping(value = "/getCountryData", method = RequestMethod.GET, headers = "Accept=application/json")
	public ResponseEntity<Object> getCountryData() {
		return rightPriceService.getCountryData();
	}
	
	@RequestMapping(value = "/getrateCardId", method = RequestMethod.GET, headers = "Accept=application/json")
	public ResponseEntity<Object> getrateCardId() {
		return rightPriceService.getrateCardId();
	}

	@ApiOperation(value = "add country details", response = Country.class)
	@RequestMapping(value = "/addCountryDetails", method = RequestMethod.POST)
	public ResponseEntity<Object> addCountryDetails(@RequestBody Country countryDetails) {
		AppLoger.APPLOGGER.info("inside add contry controller");
		return rightPriceService.addCountryDetails(countryDetails);
	}

	@ApiOperation(value = "update country details", response = Country.class)
	@RequestMapping(value = "/updateCountryDetails", method = RequestMethod.POST)
	public ResponseEntity<Object> updateCountryDetails(@RequestBody Country[] countryDetails) {
		AppLoger.APPLOGGER.info("inside update contry controller");
		try
		{
			return rightPriceService.updateCountryDetails(countryDetails);
		}
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();					
			return ResponseEntity.status(205).contentType(MediaType.TEXT_PLAIN).body("Currently We are facing technical issues, please try again later.");
		}
	}

	// Added by Khyati on 5 Oct 2017 - For Master --> SubPrice -- Start
	@ApiOperation(value = "Get Practice Dropdown", response = Practice.class)
	@RequestMapping(value = "/getPractice", method = RequestMethod.GET, headers = "Accept=application/json")
	public ResponseEntity<Object> getPractice() {
		return rightPriceService.getPractice();
	}

	@ApiOperation(value = "Get SubPractice Dropdown dependant on Practice", response = SubPractice.class)
	@RequestMapping(value = "/getSubPractice/{pracId}", method = RequestMethod.GET, headers = "Accept=application/json")
	public ResponseEntity<Object> getSubPractice(@PathVariable("pracId") int pracId) {
		return rightPriceService.getSubPractice(pracId);
	}

	@ApiOperation(value = "View SubPractice Data", response = SubPracticeView.class)
	@RequestMapping(value = "/viewSubPracticeData/{practiceId}", method = RequestMethod.GET, headers = "Accept=application/json")
	public ResponseEntity<Object> viewSubPracticeData(@PathVariable("practiceId") int practiceId) {
		return rightPriceService.viewSubPracticeData(practiceId);
	}

	@ApiOperation(value = "Insert/Save SubPractice Data", response = SubPracticeInsert.class)
	@RequestMapping(value = "/insertSubPractice", method = RequestMethod.POST)
	public ResponseEntity<Object> insertSubPractice(@RequestBody SubPracticeInsert subPracticeInsert) {
		return rightPriceService.insertSubPractice(subPracticeInsert);
	}

	@ApiOperation(value = "Update SubPractice Data", response = SubPracticeInsert.class)
	@RequestMapping(value = "/updateSubPractice", method = RequestMethod.POST)
	public ResponseEntity<Object> updateSubPractice(@RequestBody SubPracticeInsert subPracticeInsert) {
		return rightPriceService.updateSubPractice(subPracticeInsert);
	}

	@ApiOperation(value = "View contractor Data", response = SubPracticeView.class)
	@RequestMapping(value = "/viewcontractorRoleList", method = RequestMethod.GET, headers = "Accept=application/json")
	public ResponseEntity<Object> viewcontractorRoleList() {
		return rightPriceService.viewcontractorRoleList();
	}
	
	@RequestMapping(value = "/getStaffingcontractorRoleList/{rpVrsId}/{tower_id}", method = RequestMethod.GET, headers = "Accept=application/json")	
	public ResponseEntity<Object> getStaffingcontractorRoleList(@PathVariable("rpVrsId") int rpVrsId,@PathVariable("tower_id") int tower_id) {
		return rightPriceService.getStaffingcontractorRoleList(rpVrsId,tower_id);
	}
	
	@RequestMapping(value = "/getCustomerByVerticalGroupId", method = RequestMethod.GET, headers = "Accept=application/json")
	public ResponseEntity<Object> getCustomerByVerticalGroupId() {
		return rightPriceService.getCustomerByVerticalGroupId();
	}
	
	@RequestMapping(value = "/getCustomerForUser", method = RequestMethod.GET, headers = "Accept=application/json")
	public ResponseEntity<Object> getCustomerForUser() {
		return rightPriceService.getCustomerForUser();
	}
	@RequestMapping(value="/getCountryList",method= RequestMethod.GET, headers = "Accept=application/json")
	public ResponseEntity<Object> getCountryList() {
		return rightPriceService.getCountryList();
	}
	@RequestMapping(value = "/getGFTCustomer ", method = RequestMethod.GET, headers = "Accept=application/json")
	public ResponseEntity<Object> getGFTCustomer() {
		return rightPriceService.getGFTCustomer();
	}
	/*
	 * @RequestMapping(value = "/getDeals/{customerId}", method =
	 * RequestMethod.GET,headers="Accept=application/json") public
	 * ResponseEntity<Object> getDeals(@PathVariable("customerId") int
	 * customerId){ AppLoger.APPLOGGER.info("Returning category for countryId : " +
	 * customerId); return rightPriceService.getDeals(customerId); }
	 */


	@RequestMapping(value = "/getDealsFrmCrmStages/{customerId}/{dealTypeId}", method = RequestMethod.GET, headers = "Accept=application/json")
	public ResponseEntity<Object> getDealsFrmCrmStages(@PathVariable("customerId") int customerId, @PathVariable("dealTypeId") int dealTypeId) {
		System.out.println(" 307controller getDealsFrmCrmStages*********************************************");
		//AppLoger.APPLOGGER.info("Returning category for countryId : " + customerId);
		return rightPriceService.getDealsFrmCrmStages(customerId,dealTypeId);
	}
	 @RequestMapping(value = "/getOldDealId/{customerIdForOlddeal}/{current_DealModel}", method = RequestMethod.GET, headers = "Accept=application/json")
		public ResponseEntity<Object> getOldDealId(@PathVariable("customerIdForOlddeal") int customerIdForOlddeal, @PathVariable("current_DealModel") int current_DealModel) {
			
			return rightPriceService.getOldDealId(customerIdForOlddeal,current_DealModel);
		}

	@RequestMapping(value = "/getVersionDetails/{crmDealId}", method = RequestMethod.GET, headers = "Accept=application/json")
	public ResponseEntity<Object> getVersionDetails(@PathVariable("crmDealId") String crmDealId) {
		AppLoger.APPLOGGER.info("RP deal id: " + crmDealId);
		return rightPriceService.getVersionDetails(crmDealId);
	}
	@RequestMapping(value = "/getVersionDetailsTM/{crmDealId}", method = RequestMethod.GET, headers = "Accept=application/json")
	public ResponseEntity<Object> getVersionDetailsTM(@PathVariable("crmDealId") String crmDealId) {
		AppLoger.APPLOGGER.info("RP deal id: " + crmDealId);
		return rightPriceService.getVersionDetailsTM(crmDealId);
	}

	@RequestMapping(value = "/getRiskAnswers/{rpDealVersionId}", method = RequestMethod.GET, headers = "Accept=application/json")
	public ResponseEntity<Object> getRiskAnswers(@PathVariable("rpDealVersionId") int rpDealVersionId) {
		AppLoger.APPLOGGER.info("RP deal version id: " + rpDealVersionId);
		return rightPriceService.getRiskAnswers(rpDealVersionId);
	}
	
	@ApiOperation(value = "insert RP deal data", response = Deal.class)
	@RequestMapping(value = "/insertDealData", method = RequestMethod.POST)
	public ResponseEntity<Object> insertDealData(@RequestParam(value = "file", required = false) MultipartFile[] fileArr, 
									             @RequestParam(value = "name", required = false) String[] fileNameArr,
									             @RequestParam("jsonData") String jsonData) {
		AppLoger.APPLOGGER.info("inside insertDealData");
		try {
			Deal dealData = new ObjectMapper().readValue(jsonData, Deal.class);
			JSONObject obj = new JSONObject(jsonData);
			return rightPriceService.insertDealData(dealData, fileArr, fileNameArr);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return ResponseEntity.status(205).contentType(MediaType.TEXT_PLAIN)
					.body("Currently We are facing technical issues, please try again later.");
		}
		// return rightPriceService.insertDealData(dealData);
	}
	

	@ApiOperation(value = "insert Fp deal data", response = FpDeal.class)
	@RequestMapping(value = "/insertFpDealData", method = RequestMethod.POST)
	public ResponseEntity<Object> insertFpDealData(@RequestParam(value = "file", required = false) MultipartFile[] fileArr, 
									             @RequestParam(value = "name", required = false) String[] fileNameArr,
									             @RequestParam("jsonData") String jsonData) {
		AppLoger.APPLOGGER.info("inside insertFpDealData");
		try {
			FpDeal dealData = new ObjectMapper().readValue(jsonData, FpDeal.class);
			JSONObject obj = new JSONObject(jsonData);
			JSONArray params = obj.getJSONArray("riskBasedAnswers");
			JSONArray paramsdevmain = obj.getJSONArray("riskDevMainBasedAnswers");
			AppLoger.APPLOGGER.info("param : " + params);
			AppLoger.APPLOGGER.info("param : " + paramsdevmain);
			List<RiskBasedAnswers> answersArrray = Arrays
					.asList(new ObjectMapper().readValue(params.toString(), RiskBasedAnswers[].class));
			List<ProjectTypeAnswers> answersDevMainArrray = Arrays
					.asList(new ObjectMapper().readValue(paramsdevmain.toString(), ProjectTypeAnswers[].class));
			for (RiskBasedAnswers currPrPoDetails : answersArrray) {
				AppLoger.APPLOGGER.info("currPrPoDetails " + currPrPoDetails.getRiskAnswer());
			}
			for (ProjectTypeAnswers currDevMainPrPoDetails : answersDevMainArrray) {
				AppLoger.APPLOGGER.info("currDevMainPrPoDetails " + currDevMainPrPoDetails.getAnswer());
			}
			return rightPriceService.insertFpDealData(dealData, fileArr, fileNameArr);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return ResponseEntity.status(205).contentType(MediaType.TEXT_PLAIN)
					.body("Currently We are facing technical issues, please try again later.");
		}
		// return rightPriceService.insertDealData(dealData);
	}
	
	
	@ApiOperation(value = "insert DealCurrencyUtilization ", response = DealCurrencyUtilization.class)
	@RequestMapping(value = "/insertDlCurrUTI", method = RequestMethod.POST)
	public ResponseEntity<Object>insertDlCurrUTI(@RequestParam("jsonData") String jsonData) {
			
				try {
						DealCurrencyUtilization dcuti = new ObjectMapper().readValue(jsonData, DealCurrencyUtilization.class);
						JSONObject obj = new JSONObject(jsonData);						
						return rightPriceService.insertDlCurrUTI(dcuti);
					} catch (Exception e) {
					// TODO Auto-generated catch block
						e.printStackTrace();
						return ResponseEntity.status(205).contentType(MediaType.TEXT_PLAIN)
							.body("Currently We are facing technical issues, please try again later.");
					}
	}
	@ApiOperation(value = "insert insertDlLoc ", response = DealLocation.class)
	@RequestMapping(value = "/insertDlLoc", method = RequestMethod.POST)
	public ResponseEntity<Object>insertDlLoc(@RequestParam("jsonData") String jsonData) {			
				try {
						DealLocation dcuti = new ObjectMapper().readValue(jsonData, DealLocation.class);
						JSONObject obj = new JSONObject(jsonData);						
						return rightPriceService.insertDlLoc(dcuti);
					} catch (Exception e) {
					// TODO Auto-generated catch block
						e.printStackTrace();
						return ResponseEntity.status(205).contentType(MediaType.TEXT_PLAIN)
							.body("Currently We are facing technical issues, please try again later.");
					}
	}
	
	
	@ApiOperation(value = "update RP deal data", response = Deal.class)
	@RequestMapping(value = "/updateDealData", method = RequestMethod.POST)
	public ResponseEntity<Object> updateDealData(@RequestParam(value = "file", required = false) MultipartFile[] fileArr, 
									             @RequestParam(value = "name", required = false) String[] fileNameArr,
								   	             @RequestParam("jsonData") String jsonData) {
		AppLoger.APPLOGGER.info("inside update deal data");
		try {
			Deal dealData = new ObjectMapper().readValue(jsonData, Deal.class);
			JSONObject obj = new JSONObject(jsonData);
			return rightPriceService.updateDealData(dealData, fileArr, fileNameArr);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return ResponseEntity.status(205).contentType(MediaType.TEXT_PLAIN)
					.body("Currently We are facing technical issues, please try again later.");
		}
		// return rightPriceService.insertDealData(dealData);
	}
	

	@ApiOperation(value = "update Fp deal data", response = FpDeal.class)
	@RequestMapping(value = "/updateFpDealData", method = RequestMethod.POST)
	public ResponseEntity<Object> updateFpDealData(@RequestParam(value = "file", required = false) MultipartFile[] fileArr, 
									             @RequestParam(value = "name", required = false) String[] fileNameArr,
								   	             @RequestParam("jsonData") String jsonData) {
		AppLoger.APPLOGGER.info("inside update Fp deal data");
		try {
			FpDeal dealData = new ObjectMapper().readValue(jsonData, FpDeal.class);
			JSONObject obj = new JSONObject(jsonData);
			JSONArray params = obj.getJSONArray("riskBasedAnswers");
			JSONArray paramsdevmain = obj.getJSONArray("riskDevMainBasedAnswers");
			AppLoger.APPLOGGER.info("param : " + params);
			AppLoger.APPLOGGER.info("paramdevmain : " + paramsdevmain);
			List<RiskBasedAnswers> answersArrray = Arrays
					.asList(new ObjectMapper().readValue(params.toString(), RiskBasedAnswers[].class));
			for (RiskBasedAnswers currPrPoDetails : answersArrray) {
				AppLoger.APPLOGGER.info("currPrPoDetails " + currPrPoDetails.getRiskAnswer());
			}
			List<ProjectTypeAnswers> answersDevMainArrray = Arrays
					.asList(new ObjectMapper().readValue(paramsdevmain.toString(), ProjectTypeAnswers[].class));
			for (ProjectTypeAnswers currDevMainPrPoDetails : answersDevMainArrray) {
				AppLoger.APPLOGGER.info("currDevMainPrPoDetails " + currDevMainPrPoDetails.getAnswer());
			}
			return rightPriceService.updateFpDealData(dealData, answersArrray,answersDevMainArrray, fileArr, fileNameArr);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return ResponseEntity.status(205).contentType(MediaType.TEXT_PLAIN)
					.body("Currently We are facing technical issues, please try again later.");
		}
		// return rightPriceService.insertDealData(dealData);
	}

	@ApiOperation(value = "get rate card detail", response = RateCardDetails.class)
	@RequestMapping(value = "/getRateCardDetails/{rcId}", method = RequestMethod.GET, headers = "Accept=application/json")
	public ResponseEntity<Object> getRateCardDetails(@PathVariable("rcId") int rcId) {
		AppLoger.APPLOGGER.info("inside getRateCardDetails");
		return rightPriceService.getRateCardDetails(rcId);
	}

	@ApiOperation(value = "get rate card detail", response = Vertical.class)
	@RequestMapping(value = "/getVerticalDetails/{rcId}", method = RequestMethod.GET, headers = "Accept=application/json")
	public ResponseEntity<Object> getVerticalDetails(@PathVariable("rcId") int verticalId) {
		AppLoger.APPLOGGER.info("inside getRateCardDetails");
		return rightPriceService.getVerticalDetails(verticalId);
	}

	@ApiOperation(value = "get leadership detail", response = LeadershipDetails.class)
	@RequestMapping(value = "/getLeadershipDetails", method = RequestMethod.GET, headers = "Accept=application/json")
	public ResponseEntity<Object> getLeadershipDetails() {
		AppLoger.APPLOGGER.info("inside getLeadershipDetails");
		return rightPriceService.getLeadershipDetails();
	}
	
	@ApiOperation(value = "get Ratecard detail", response = MyDashboardRC.class)
	@RequestMapping(value = "/getDataOnSearch/{rateCardID}", method = RequestMethod.GET, headers = "Accept=application/json")
	public ResponseEntity<Object> getDataOnSearch(@PathVariable("rateCardID") int rateCardID) {
		AppLoger.APPLOGGER.info("inside getLeadershipDetails");
		return rightPriceService.getDataOnSearch(rateCardID);
	}
	/*@ApiOperation(value = "get Ratecard detail", response = LeadershipDetails.class)
	@RequestMapping(value = "/getDataOnSearch", method = RequestMethod.GET, headers = "Accept=application/json")
	public ResponseEntity<Object> getDataOnSearch() {
		AppLoger.APPLOGGER.info("inside getLeadershipDetails");
		return rightPriceService.getDataOnSearch();
	}*/

	@ApiOperation(value = "save approval data for rate card", response = RateCardDetails.class)
	@RequestMapping(value = "/updateApprovalData", method = RequestMethod.POST)
	public ResponseEntity<Object> updateApprovalDetails(@RequestBody RateCardDetails rateCardApproval) {
		return rightPriceService.updateApprovalDatails(rateCardApproval);
	}

	@ApiOperation(value = "save data for audit in rate card summary", response = RateCardApprovalAudit.class)
	@RequestMapping(value = "/saveApprovalData", method = RequestMethod.POST)
	public ResponseEntity<Object> saveApprovalData(@RequestBody RateCardApprovalAudit rateCardApprovalAudit) {
		return rightPriceService.saveApprovalData(rateCardApprovalAudit);
	}

	@ApiOperation(value = "save approval data for rate card", response = RateCardDetails.class)
	@RequestMapping(value = "/updateApprovalDataForRateCard", method = RequestMethod.POST)
	public ResponseEntity<Object> saveApprovalData(@RequestBody RateCardDetails rateCardApprovalDetails) {
		return rightPriceService.updateApprovalDataForRateCard(rateCardApprovalDetails);
	}

	/*
	 * @ApiOperation(value = "get list of Lob's", response = Lob.class)
	 * 
	 * @RequestMapping(value = "/getLobDetails", method =
	 * RequestMethod.GET,headers="Accept=application/json") public
	 * ResponseEntity<Object> getLob(){ AppLoger.APPLOGGER.info("inside Controller");
	 * List list = rightPriceService.getLob(); if(list.size()==1){ }
	 * AppLoger.APPLOGGER.info(ResponseEntity.ok(list)); return
	 * ResponseEntity.ok(list); }
	 */
	
	/* PS5029150 Start */
	@ApiOperation(value = "get list of Lob's", response = Lob.class)
	@RequestMapping(value = "/getLobDetails", method = RequestMethod.GET, headers = "Accept=application/json")
	public ResponseEntity<Object> getLob() {
		AppLoger.APPLOGGER.info("inside Controller");
		List list = rightPriceService.getLob();
		if (list.size() == 1) {
		}
		AppLoger.APPLOGGER.info(ResponseEntity.ok(list));
		return ResponseEntity.ok(list);
	}
	
	@ApiOperation(value = "Saving the data of the Lob", response = Lob.class)
	@RequestMapping(value = "/insertLobDetails", method = RequestMethod.POST)
	public ResponseEntity<Object> insertLobDetails(@RequestBody Lob lobDetails) {
		try {
			AppLoger.APPLOGGER.info("Data received " + lobDetails);
			return rightPriceService.insertLobDetails(lobDetails);

		} catch (Exception e) {
			AppLoger.APPLOGGER.info("Exception : " + e);
		}
		return null;
	}

	@ApiOperation(value = "updating the data of the Lob", response = Lob.class)
	@RequestMapping(value = "/updateLobDetails", method = RequestMethod.POST)
	public ResponseEntity<Object> updateLobDetails(@RequestBody Lob lobDetails) {
		AppLoger.APPLOGGER.info("Inside Controller");
		try {
			AppLoger.APPLOGGER.info("Data received " + lobDetails);
			return rightPriceService.updateLobDetails(lobDetails);

		} catch (Exception e) {
			AppLoger.APPLOGGER.info("Exception : " + e);
		}
		return null;
	}

	@ApiOperation(value = "View Rate Card Information", response = RateCardDetailsView.class)
	@RequestMapping(value = "/viewRateCardInfo/{rcId}", method = RequestMethod.GET, headers = "Accept=application/json")
	public ResponseEntity<Object> viewRateCardInformation(@PathVariable("rcId") int rcId) {
		AppLoger.APPLOGGER.info("RC ID.......... Controller--------------------" + rcId);
		AppLoger.APPLOGGER.info("Inside the Controller");
		return rightPriceService.viewRateCardInformation(rcId);
	}

	@ApiOperation(value = "view approver designation", response = ApproverDesignation.class)
	@RequestMapping(value = "/getApproverDesignation/{rcId}", method = RequestMethod.GET, headers = "Accept=application/json")
	public ResponseEntity<Object> getApproverDesignation(@PathVariable("rcId") int rcId) {
		AppLoger.APPLOGGER.info("Inside the Controller");
		AppLoger.APPLOGGER.info("Ratecard details is............. " + rcId);
		return rightPriceService.viewApproverDesignation(rcId);
	}

	/*@ApiOperation(value = "view approver Name based on vertical id")
	@RequestMapping(value = "/getApproverName/{rcId}", method = RequestMethod.GET, headers = "Accept=application/json")
	public ResponseEntity<Object> getApproverName(@PathVariable("rcId") int rcId) {
		AppLoger.APPLOGGER.info("Inside the Controller");
		return rightPriceService.getApproverName(rcId);
	}*/
	
	@ApiOperation(value = "view approver Name based on vertical id")
	@RequestMapping(value = "/getApproverName/{rcId}/{rbutype}", method = RequestMethod.GET, headers = "Accept=application/json")
	public ResponseEntity<Object> getApproverName(@PathVariable("rcId") int rcId,@PathVariable("rbutype") int rbutype) {
		AppLoger.APPLOGGER.info("Inside the Controller");
		return rightPriceService.getApproverName(rcId,rbutype);
	} // manglam updated

	@ApiOperation(value = "view approver Name based on vertical id")
	@RequestMapping(value = "/getLeaderApproverName", method = RequestMethod.GET, headers = "Accept=application/json")
	public ResponseEntity<Object> getLeaderApproverName() {
		AppLoger.APPLOGGER.info("Inside the Controller");
		return rightPriceService.getLeaderApproverName();
	}

	@ApiOperation(value = "view approver Name based on vertical id")
	@RequestMapping(value = "/getDealDetails/{crmDealId}/{rpVrsId}", method = RequestMethod.GET, headers = "Accept=application/json")
	public ResponseEntity<Object> getDealDetails(@PathVariable("crmDealId") String crmDealId , @PathVariable("rpVrsId") int rpVrsId ) {
		AppLoger.APPLOGGER.info("Inside the Controller, rpVrsId"+rpVrsId);
		System.out.println("***************************************************************"+rpVrsId);
		return rightPriceService.getDealDetails(crmDealId,rpVrsId);
	}
	
	
	@ApiOperation(value = "view getRatecardSummaryData")
	@RequestMapping(value = "/getRatecardSummaryData/{rpVrsId}", method = RequestMethod.GET, headers = "Accept=application/json")
	public ResponseEntity<Object> getRatecardSummaryData(@PathVariable("rpVrsId") int rpVrsId ) {		
		return rightPriceService.getRatecardSummaryData(rpVrsId);
	}
	
	@ApiOperation(value = "view getStaffingPercentage")
	@RequestMapping(value = "/getStaffingPercentage/{rpVrsId}", method = RequestMethod.GET, headers = "Accept=application/json")
	public ResponseEntity<Object> getStaffingPercentage(@PathVariable("rpVrsId") int rpVrsId ) {		
		return rightPriceService.getStaffingPercentage(rpVrsId);
	}
	
	@ApiOperation(value = "view getFinal_GM_Per")
	@RequestMapping(value = "/getFinal_GM_Per/{rpVrsId}", method = RequestMethod.GET, headers = "Accept=application/json")
	public ResponseEntity<Object> getFinal_GM_Per(@PathVariable("rpVrsId") int rpVrsId ) {		
		return rightPriceService.getFinal_GM_Per(rpVrsId);
	}
	
	@ApiOperation(value = "view getFinal_GM_Per_Sum")
	@RequestMapping(value = "/getFinal_GM_Per_Sum/{rpVrsId}", method = RequestMethod.GET, headers = "Accept=application/json")
	public ResponseEntity<Object> getFinal_GM_Per_Sum(@PathVariable("rpVrsId") int rpVrsId ) {		
		return rightPriceService.getFinal_GM_Per_Sum(rpVrsId);
	}
	@ApiOperation(value = "view updateRP_Deal_Table")
	@RequestMapping(value = "/updateRP_Deal_Table/{rpVrsId}/{calc_total_GM_session}/{tCVModelValue}", method = RequestMethod.GET, headers = "Accept=application/json")
	public ResponseEntity<Object> updateRP_Deal_Table(@PathVariable("rpVrsId") int rpVrsId, @PathVariable("calc_total_GM_session") Double calc_total_GM_session, @PathVariable("tCVModelValue") Double tCVModelValue ) {		
		return rightPriceService.updateRP_Deal_Table(rpVrsId,calc_total_GM_session,tCVModelValue);
	}
	
	@ApiOperation(value = "view getStaffingDone")
	@RequestMapping(value = "/getStaffingDone/{rpVrsId}", method = RequestMethod.GET, headers = "Accept=application/json")
	public ResponseEntity<Object> getStaffingDone(@PathVariable("rpVrsId") int rpVrsId ) {		
		return rightPriceService.getStaffingDone(rpVrsId);
	}
	
	@ApiOperation(value = "view get_onloadCoun_City")
	@RequestMapping(value = "/get_onloadCoun_City/{rpVrsId}", method = RequestMethod.GET, headers = "Accept=application/json")
	public ResponseEntity<Object> get_onloadCoun_City(@PathVariable("rpVrsId") int rpVrsId ) {		
		return rightPriceService.get_onloadCoun_City(rpVrsId);
	}
	
	@ApiOperation(value = "getVerticalData", response = Vertical.class)
	@RequestMapping(value = "/getVerticalData", method = RequestMethod.POST)
	public ResponseEntity<Object> getTeamVertical(@RequestBody VerticalMembers verticalDetails) {
			return rightPriceService.getTeamVertical(verticalDetails);
	}
	
	/*@ApiOperation(value = "getQMVerticalData", response = Vertical.class)
	@RequestMapping(value = "/getQMVerticalData", method = RequestMethod.POST)
	public ResponseEntity<Object> getQMVertical(@RequestBody VerticalRiskManagersMembers verticalQMDetails) {
			return rightPriceService.getQMVertical(verticalQMDetails);
	}
		*/	
		
	@ApiOperation(value = "getEmpDetails", response = EmpDetails.class)
	@RequestMapping(value = "/getEmpDetails", method = RequestMethod.POST)
	public ResponseEntity<Object> getEmpDetails(@RequestBody EmpDetails empDetails) {
				return rightPriceService.getEmpDetails(empDetails);
	}

				
	@ApiOperation(value = "getRiskManagersEmpDetails", response = EmpDetails.class)
	@RequestMapping(value = "/getRiskManagersEmpDetails", method = RequestMethod.POST)
	public ResponseEntity<Object> getRiskManagersEmpDetails(@RequestBody EmpDetails empDetails) {
			AppLoger.APPLOGGER.info("Inside Controller................ ");
				AppLoger.APPLOGGER.info("The Vertical Of the user is.......... "+empDetails.getVerticalId());
				return rightPriceService.getRiskManagersEmpDetails(empDetails);
	}

	
	@RequestMapping(value = "/saveVerticalMemebers", method = RequestMethod.POST)
	public ResponseEntity<Object> saveVerticalMemebers(@RequestBody VerticalMembers[] verticalMember) {
		AppLoger.APPLOGGER.info("Data received for save.................. " + verticalMember.length);
		return rightPriceService.saveVerticalMembers(verticalMember);

		// return null;
	}
	
	@RequestMapping(value = "/deallocateVerticalMemebers", method = RequestMethod.POST)
	public ResponseEntity<Object> deallocateVerticalMemebers(@RequestBody VerticalMembers[] verticalMember) {
		AppLoger.APPLOGGER.info("Data received to deallocate employee.................. " + verticalMember.length);
		return rightPriceService.deallocateVerticalMemebers(verticalMember);
	}
	
	/*@RequestMapping(value = "/deallocateVerticalQM", method = RequestMethod.POST)
	public ResponseEntity<Object> deallocateVerticalQM(@RequestBody VerticalRiskManagersMembers[] verticalQM) {
		AppLoger.APPLOGGER.info("Data received to deallocate QM employee.................. " + verticalQM.length);
		return rightPriceService.deallocateVerticalQM(verticalQM);
	}
	
	@RequestMapping(value = "/saveRiskManagersVerticalMembers", method = RequestMethod.POST)
	public ResponseEntity<Object> saveRiskManagersVerticalMemebers(@RequestBody VerticalRiskManagersMembers[] RiskManagersMember) {
		AppLoger.APPLOGGER.info("Data received for save.................. " + RiskManagersMember.length);
		return rightPriceService.saveRiskManagersVerticalMemebers(RiskManagersMember);

		// return null;
	}*/
	/*PS5029150 --  END*/
	
	/* Developed by AG5027026------------------------------------ */

	@RequestMapping(value = "/getCampusHireThresholdPercent ", method = RequestMethod.GET, headers = "Accept=application/json")
	public ResponseEntity<Object> getCampusHireThresholdPercent() {
		return rightPriceService.getCampusHireThresholdPercent();
	}

	@ApiOperation(value = "Update Campus Hire Data", response = MasterCampusHire.class)
	@RequestMapping(value = "/updateThresholdPercent", method = RequestMethod.POST)
	public ResponseEntity<Object> updateThresholdPercent(@RequestBody List<MasterCampusHire> masterCampusHire) {
		return rightPriceService.updateThresholdPercent(masterCampusHire);
	}

	@RequestMapping(value = "/getContractualTerms/{versionId}", method = RequestMethod.GET, headers = "Accept=application/json")
	public ResponseEntity<Object> getContractualTerms(@PathVariable("versionId") int versionId) {
		return rightPriceService.getContractualTerms(versionId);
	}

	@ApiOperation(value = "Save/Update ContractualTerms", response = ContractualTerms.class)
	@RequestMapping(value = "/saveContractualTerms", method = RequestMethod.POST)
	public ResponseEntity<Object> saveContractualTerms(@RequestBody ContractualTerms contractualTerms) {
		return rightPriceService.saveContractualTerms(contractualTerms);
	}

	@RequestMapping(value = "/getDesignation ", method = RequestMethod.GET, headers = "Accept=application/json")
	public ResponseEntity<Object> getDesignation() {
		return rightPriceService.getDesignation();
	}

	@RequestMapping(value = "/getBand ", method = RequestMethod.GET, headers = "Accept=application/json")
	public ResponseEntity<Object> getBand() {
		return rightPriceService.getBand();
	}

	@RequestMapping(value = "/getGrade ", method = RequestMethod.GET, headers = "Accept=application/json")
	public ResponseEntity<Object> getGrade() {
		return rightPriceService.getGrade();
	}

	@ApiOperation(value = "Add Designation", response = ContractualTerms.class)
	@RequestMapping(value = "/addDesignation", method = RequestMethod.POST)
	public ResponseEntity<Object> addDesignation(@RequestBody Designation designation) {
		return rightPriceService.addDesignation(designation);
	}

	@ApiOperation(value = "Update Designation", response = ContractualTerms.class)
	@RequestMapping(value = "/updateDesignation", method = RequestMethod.POST)
	public ResponseEntity<Object> updateDesignation(@RequestBody Designation designation) {
		return rightPriceService.updateDesignation(designation);
	}
	
	@ApiOperation(value = "getEmpAdIdDetails", response = EmpDetails.class)
	@RequestMapping(value = "/getEmpAdIdDetails", method = RequestMethod.POST)
	public ResponseEntity<Object> getEmpAdIdDetails(@RequestBody EmpDetails empDetails) {
		return rightPriceService.getEmpAdIdDetails(empDetails);
	}
	@RequestMapping(value = "/getVerticalGroupID", method = RequestMethod.GET, headers = "Accept=application/json")
	public ResponseEntity<Object> getVerticalGroupID() {
		return rightPriceService.getVerticalGroupID();
	}
	@RequestMapping(value = "/getVertical", method = RequestMethod.GET, headers = "Accept=application/json")
	public ResponseEntity<Object> getVertical() {
		return rightPriceService.getVertical();
	}
	
	
	@RequestMapping(value = "/getCustomers/{verticalId}", method = RequestMethod.GET, headers = "Accept=application/json")
	public ResponseEntity<Object> getCustomers(@PathVariable("verticalId") int verticalId) {
		return rightPriceService.getCustomers(verticalId);
	}
		
	@ApiOperation(value = "Add Vertical", response = ContractualTerms.class)
	@RequestMapping(value = "/addVertical", method = RequestMethod.POST)
	public ResponseEntity<Object> addVertical(@RequestBody Vertical Vertical) {
		return rightPriceService.addVertical(Vertical);

	}

	@ApiOperation(value = "Update Vertical", response = ContractualTerms.class)
	@RequestMapping(value = "/updateVertical", method = RequestMethod.POST)
	public ResponseEntity<Object> updateVertical(@RequestBody Vertical Vertical) {
		 return rightPriceService.updateVertical(Vertical);
	}
	@RequestMapping(value = "/getRoles/{practiceId}/{subPracticeId}", method = RequestMethod.GET, headers = "Accept=application/json")
	public ResponseEntity<Object> getRoles(@PathVariable("practiceId") int practiceId,@PathVariable("subPracticeId") int subPracticeId) {
		return rightPriceService.getRoles(practiceId,subPracticeId);
	}
	
	@RequestMapping(value = "/getAddRoles", method = RequestMethod.GET, headers = "Accept=application/json")
	public ResponseEntity<Object> getAddRoles() {
		return rightPriceService.getAddRoles();
	}
	
	
	@RequestMapping(value = "/getUpdateMasterRoles", method = RequestMethod.GET, headers = "Accept=application/json")
	public ResponseEntity<Object> getUpdateMasterRoles() {
		return rightPriceService.getUpdateMasterRoles();
	}
	
	@RequestMapping(value = "/getMasterRateRoles/{countryId}", method = RequestMethod.GET, headers = "Accept=application/json")
	public ResponseEntity<Object> getMasterRateRoles(@PathVariable("countryId") int countryId) {
		return rightPriceService.getMasterRateRoles(countryId);
	}
	
	@RequestMapping(value = "/getSkills", method = RequestMethod.GET, headers = "Accept=application/json")
	public ResponseEntity<Object> getSkills() {
		return rightPriceService.getSkills();
	}
	
	@RequestMapping(value = "/getPracticeRoles", method = RequestMethod.GET, headers = "Accept=application/json")
	public ResponseEntity<Object> getPracticeRoles() {
		return rightPriceService.getPracticeRoles();
	}
	
	@RequestMapping(value = "/getSubPracticeRole/{practiceId}", method = RequestMethod.GET, headers = "Accept=application/json")
	public ResponseEntity<Object> getSubPracticeRole(@PathVariable("practiceId") int practiceId) {
		return rightPriceService.getSubPracticeRole(practiceId);
	}
	
	@RequestMapping(value = "/getXOSkillsRoles", method = RequestMethod.GET, headers = "Accept=application/json")
	public ResponseEntity<Object> getXOSkillsRoles() {
		return rightPriceService.getXOSkillsRoles();
	}
	
	@RequestMapping(value = "/getSyntelRoles", method = RequestMethod.GET, headers = "Accept=application/json")
	public ResponseEntity<Object> getSyntelRoles() {
		return rightPriceService.getSyntelRoles();
	}
	
	@RequestMapping(value = "/getProficiency", method = RequestMethod.GET, headers = "Accept=application/json")
	public ResponseEntity<Object> getProficiency() {
		return rightPriceService.getProficiency();
	}
	
	@RequestMapping(value = "/getXOSkillElementRoles/{skillId}", method = RequestMethod.GET, headers = "Accept=application/json")
	public ResponseEntity<Object> getXOSkillElementRoles(@PathVariable("skillId") int skillId) {
		return rightPriceService.getXOSkillElementRoles(skillId);
	}
	
	@RequestMapping(value = "/getKnowledgeNameRole", method = RequestMethod.GET, headers = "Accept=application/json")
	public ResponseEntity<Object> getKnowledgeNameRple() {
		return rightPriceService.getKnowledgeNameRole();
	}
	
	@ApiOperation(value = "Save Master Role", response = MasterRoles.class)
	@RequestMapping(value = "/addRole", method = RequestMethod.POST)
	public ResponseEntity<Object> addRole(@RequestBody MasterRoles masterRoles ) {
		 return rightPriceService.addRole(masterRoles);
	}
	
	@ApiOperation(value = "Update Master Role", response = MasterRoles.class)
	@RequestMapping(value = "/updateRole", method = RequestMethod.POST)
	public ResponseEntity<Object> updateRole(@RequestBody MasterRoles masterRoles ) {
		 return rightPriceService.updateRole(masterRoles);
	}
	
	@RequestMapping(value = "/getRolesDetails/{masterRoleId}", method = RequestMethod.GET, headers = "Accept=application/json")
	public ResponseEntity<Object> getRolesDetails(@PathVariable("masterRoleId") int masterRoleId) {
		return rightPriceService.getRolesDetails(masterRoleId);
	}
		
//	@ApiOperation(value = "Add Rate Data", response = SubPracticeView.class)
//	@RequestMapping(value = "/addRate", method = RequestMethod.GET, headers = "Accept=application/json")
//	public ResponseEntity<Object> addRate(@RequestBody MasterRate masterRate) {
//		return rightPriceService.addRate(masterRate);
//	}
	/* Developed by AG5027026------------------------------------ */

	// Start Added for FP Deal Creation -Khyati
	@ApiOperation(value = "getUploadedDoc", response = UploadedDocuments.class)
	@RequestMapping(value = "/getUploadedDoc/{versionId}", method = RequestMethod.GET, headers = "Accept=application/json")
	public ResponseEntity<Object> getUploadedDoc(@PathVariable("versionId") int versionId) {
		AppLoger.APPLOGGER.info("inside controller getUploadedDoc");
		return rightPriceService.getUploadedDoc(versionId);
	}

	@RequestMapping(value = "/downloadFileFormID/{objectId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> downloadFile(@PathVariable("objectId") int objectId) {
		return rightPriceService.downloadFile(objectId);
	}
	
	@RequestMapping(value = "/downloadFileWithName/{dealAttachmentId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> downloadFileWithFileName(@PathVariable("dealAttachmentId") int dealAttachmentId) {
		return rightPriceService.downloadFileWithFileName(dealAttachmentId);
	}
	

	@RequestMapping(value = "/updateActiveStatus/{docId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> updateActiveStatus(@PathVariable("docId") int docId) {
		return rightPriceService.updateActiveStatus(docId);
	}

	@RequestMapping(value = "/saveuploadedFile", method = RequestMethod.POST)
	public ResponseEntity<Object> saveuploadedFile(
			@RequestParam(value = "file", required = false) MultipartFile[] fileArr,
			@RequestParam(value = "name", required = false) String[] fileNameArr,
			@RequestParam("jsonData") String jsonData) throws IOException {
		FileUpload uploadFile = new FileUpload();
		for (MultipartFile curFile : fileArr) {
			AppLoger.APPLOGGER.info("name  : " + curFile.getOriginalFilename() + " size : " + curFile.getSize());
			uploadFile.setFilename(curFile.getOriginalFilename());
			uploadFile.setData(curFile.getBytes());
			uploadFile.setActiveStatus(1);
			rightPriceService.handleFileUpload(uploadFile);
		}
		UploadedDocuments uploadedDocuments = new ObjectMapper().readValue(jsonData, UploadedDocuments.class);
		uploadedDocuments.setFileobjId(uploadFile.getObjectid());
		uploadedDocuments.setRpid(10);
		uploadedDocuments.setVerified(0);
		uploadedDocuments.setVerifiedBy("Test");
		uploadedDocuments.setActiveStatus(1);
		rightPriceService.insertuploadedDocuments(uploadedDocuments);
		return null;
	}

	/* Role Selection RITU ---------------------------------------- */

	@RequestMapping(value = "/getRateCard", method = RequestMethod.GET, headers = "Accept=application/json")
	public ResponseEntity<Object> getRateCard() {
		return rightPriceService.getRateCard();
	}
	@RequestMapping(value = "/getRateCardDeal/{rpVrsId}", method = RequestMethod.GET, headers = "Accept=application/json")
	public ResponseEntity<Object> getRateCardDeal(@PathVariable("rpVrsId") int rpVrsId) {
		return rightPriceService.getRateCardDeal(rpVrsId);
	}
	
	@RequestMapping(value = "/getXOSkills", method = RequestMethod.GET, headers = "Accept=application/json")
	public ResponseEntity<Object> getXOSkills() {
		return rightPriceService.getXOSkills();
	}
	
	@RequestMapping(value = "/getXOSkillsElementMaster/{skillId}", method = RequestMethod.GET, headers = "Accept=application/json")
	public ResponseEntity<Object> getXOSkillsElementMaster(@PathVariable("skillId") int skillId ) {
		AppLoger.APPLOGGER.info("Returning skill id for skill element: " + skillId);
		return rightPriceService.getXOSkillsElementMaster(skillId);
	}
	
	@RequestMapping(value = "/getXOSkillFromSubPracticeId/{subPracticeId}", method = RequestMethod.GET, headers = "Accept=application/json")
	public ResponseEntity<Object> getXOSkillFromSubPracticeId(@PathVariable("subPracticeId") int subPracticeId) {
		AppLoger.APPLOGGER.info("Returning skill id for skill element: " + subPracticeId);
		return rightPriceService.getXOSkillFromSubPracticeId(subPracticeId);
	}
	@RequestMapping(value = "/getXOSkillFromSubPracticeId2/{subPracticeId}/{masterRoleId}/{rcId_value}", method = RequestMethod.GET, headers = "Accept=application/json")
	public ResponseEntity<Object> getXOSkillFromSubPracticeId2(@PathVariable("subPracticeId") int subPracticeId, @PathVariable("masterRoleId") int masterRoleId, @PathVariable("rcId_value") int rcId_value) { 
		
		return rightPriceService.getXOSkillFromSubPracticeId2(subPracticeId,masterRoleId,rcId_value);
	}
	
	@RequestMapping(value = "/getKnowledgeName", method = RequestMethod.GET, headers = "Accept=application/json")
	public ResponseEntity<Object> getKnowledgeName() {
		return rightPriceService.getKnowledgeName();
	}
	@RequestMapping(value = "/getKnowledgeName_saved/{masterRoleId}/{rcId_value}", method = RequestMethod.GET, headers = "Accept=application/json")
	public ResponseEntity<Object> getKnowledgeName_saved(@PathVariable("masterRoleId") int masterRoleId , @PathVariable("rcId_value") int rcId_value) {
		return rightPriceService.getKnowledgeName_saved(masterRoleId,rcId_value);
	}

	@RequestMapping(value = "/getRoleSelectionDetails/{rcId}", method = RequestMethod.GET, headers = "Accept=application/json")
	public ResponseEntity<Object> getRoleSelectionDetails(@PathVariable("rcId") int rcId) {
		AppLoger.APPLOGGER.info("Returning rateCardId for rateCard: " + rcId);
		return rightPriceService.getRoleSelectionDetails(rcId);
	}

	@RequestMapping(value = "/getPersistRateCardDetails/{rcId}", method = RequestMethod.GET, headers = "Accept=application/json")
	public ResponseEntity<Object> getPersistRateCardDetails(@PathVariable("rcId") int rcId) {
		AppLoger.APPLOGGER.info("Returning rateCardId for getPersistRateCardDetails: " + rcId);
		return rightPriceService.getPersistRateCardDetails(rcId);
	}

	@RequestMapping(value = "/UpdateRateCardDetails", method = RequestMethod.POST)
	public ResponseEntity<Object> update(@RequestBody ArrayList<RPRateCardRoles> rpRateCardRoles) {
		AppLoger.APPLOGGER.info("Data received for save.................. " + rpRateCardRoles.size());
		for (RPRateCardRoles rpRateCardRoles2 : rpRateCardRoles) {
			AppLoger.APPLOGGER.info(rpRateCardRoles2.toString());
		}
		return rightPriceService.save(rpRateCardRoles);

		// return null;
	}

	@RequestMapping(value = "/searchRateCardData", method = RequestMethod.POST, headers = "Accept=application/json")
	public ResponseEntity<Object> searchRateCardDataPost(@RequestBody String searchRoles) {
		AppLoger.APPLOGGER.info("POST   Returning searchRateCardData for rateCard: " + searchRoles.replaceAll("\\{", ""));
		return rightPriceService.searchRateCardData(searchRoles.replaceAll("\\{", ""));
	}

	/* Role Selection RITU ---------------------------------------- */

	@ApiOperation(value = "Update/Save Contractor Data", response = Contractor.class)
	@RequestMapping(value = "/saveContractorRole", method = RequestMethod.POST)
	public ResponseEntity<Object> saveContractorRole(@RequestBody Contractor[] contractor) {
		AppLoger.APPLOGGER.info("inside Contractor Data:Size is -- " + contractor.length);
		return rightPriceService.saveContractorRole(contractor);
	}

	/* visa screen sneha start---------- */

	@RequestMapping(value = "/getCountry", method = RequestMethod.GET, headers = "Accept=application/json")
	public ResponseEntity<Object> getCountry() {
		AppLoger.APPLOGGER.info("getcountry");
		return rightPriceService.getCountry();
	}

	@RequestMapping(value = "/getRateCardName/{customerId}/{deal_Id}/{cityId}/{countryId}/{Industry_ID}", method = RequestMethod.GET, headers = "Accept=application/json")
	public ResponseEntity<Object> getRateCardName(@PathVariable("customerId") int customerId, @PathVariable("deal_Id") String deal_Id,@PathVariable("cityId") int cityId,@PathVariable("countryId") int countryId,@PathVariable("Industry_ID") int Industry_ID)
	{
		System.out.println("817controller getRateCardName***********************"+customerId);
		//AppLoger.APPLOGGER.info("getRateCardName==controller==");
		return rightPriceService.getRateCardName(customerId,deal_Id,cityId,countryId,Industry_ID);
	}
	@RequestMapping(value = "/getVisaTypeDetails", method = RequestMethod.GET, headers = "Accept=application/json")
	public ResponseEntity<Object> getVisa() {
		AppLoger.APPLOGGER.info("getVisa");
		return rightPriceService.getVisa();
	}

	@ApiOperation(value = "add visa details", response = VisaLabels.class)
	@RequestMapping(value = "/addVisaDetails", method = RequestMethod.POST)
	public ResponseEntity<Object> addVisaDetails(@RequestBody VisaLabels visaDetailsAdd) {
		AppLoger.APPLOGGER.info("inside add Visa controller");
		return rightPriceService.addVisaDetails(visaDetailsAdd);
	}

	@ApiOperation(value = "update Visa details", response = VisaLabels.class)
	@RequestMapping(value = "/updateVisaDetails", method = RequestMethod.POST)
	public ResponseEntity<Object> updateVisaDetails(@RequestBody VisaLabels visaDetailsUpdate) {
		AppLoger.APPLOGGER.info("inside update city controller");
		// AppLoger.APPLOGGER.info("active status "+visaDetailsUpdate.getIsActive());
		return rightPriceService.updateVisaDetails(visaDetailsUpdate);
	}

	@ApiOperation(value = "View Visa details", response = VisaLabels.class)
	@RequestMapping(value = "/viewVisaDetails", method = RequestMethod.POST)
	public ResponseEntity<Object> viewVisaDetails(@RequestBody VisaLabels visaDetailsView) {
		/*
		 * AppLoger.APPLOGGER.info("inside view city controller");
		 * AppLoger.APPLOGGER.info("country id--------"+visaDetailsView.getCountryId(
		 * ));
		 */
		return rightPriceService.viewVisaDetails(visaDetailsView);
	}

	@RequestMapping(value = "/getVisaLabel/{countryId}/{visaTypeId}", method = RequestMethod.GET, headers = "Accept=application/json")
	public ResponseEntity<Object> getVisaLabel(@PathVariable("countryId") int countryId,
			@PathVariable("visaTypeId") int visaTypeId) {
		AppLoger.APPLOGGER.info("Returning VisaLabel name for countryId : " + countryId + "visaTypeId : " + visaTypeId);
		return rightPriceService.getVisaLabel(countryId, visaTypeId);
	}

	@ApiOperation(value = "Get Visa Dropdown", response = Visa.class)
	@RequestMapping(value = "/getVisa", method = RequestMethod.GET, headers = "Accept=application/json")
	public ResponseEntity<Object> getVisaOnLoad() {
		return rightPriceService.getVisaOnLoad();
	}

	@ApiOperation(value = "view allowances", response = BasicAllowance.class)
	@RequestMapping(value = "/viewAllowances", method = RequestMethod.POST)
	public ResponseEntity<Object> viewAllowances(@RequestBody BasicAllowance basicAllowance) {
		return rightPriceService.viewAllowances(basicAllowance);
	}

	@ApiOperation(value = "check Annual Allownces", response = BasicAllowance.class)
	@RequestMapping(value = "/checkAnnualAllownces", method = RequestMethod.POST)
	public ResponseEntity<Object> checkAnnualAllownces(@RequestBody BasicAllowance basicAllowance) {
		return rightPriceService.checkAnnualAllownces(basicAllowance);
	}

	@ApiOperation(value = "add Annual Allownces", response = BasicAllowance.class)
	@RequestMapping(value = "/saveAnnualAllowances", method = RequestMethod.POST)
	public ResponseEntity<Object> saveAnnualAllowances(@RequestBody BasicAllowance[] basicAllowance) {
		 return rightPriceService.saveAnnualAllowances(basicAllowance);
	}
	
	@ApiOperation(value = "update Annual Allownces", response = BasicAllowance.class)
	@RequestMapping(value = "/updateAnnualAllowances", method = RequestMethod.POST)
	public ResponseEntity<Object> updateAnnualAllowances(@RequestBody BasicAllowance[] basicAllowance) {
		return rightPriceService.updateAnnualAllowances(basicAllowance);
	}
	
	/*@ApiOperation(value = "View Miscellaneous Cost", response = OffshoreMiscellaneousCost.class)
	@RequestMapping(value = "/viewMiscellaneousCost", method = RequestMethod.POST)
	public ResponseEntity<Object> viewMiscellaneousCost(@RequestBody OffshoreMiscellaneousCost MiscellaneousDetailsView) {
		AppLoger.APPLOGGER.info("inside view Miscellaneous Cost controller");
		return rightPriceService.viewMiscellaneousCost(MiscellaneousDetailsView);
	}*/
/*AC5029212-------------------*/
	
	@ApiOperation(value = "get rate card utilization", response = City.class)
	@RequestMapping(value = "/getRoleUtilization/{cityId}/{rateCardId}", method = RequestMethod.GET)
	 public ResponseEntity<Object> getRoleUtilization(@PathVariable("cityId") int cityId,@PathVariable("rateCardId") int rateCardId){
		return rightPriceService.getRoleUtilization(cityId,rateCardId);
	 }
	
	@ApiOperation(value = "update rate card utilzation and rates data", response = RateCardRoleUtilization.class)
	@RequestMapping(value = "/updateRateUtilizationAndRates", method = RequestMethod.POST)
	 public ResponseEntity<Object> updateRateUtilizationAndRates(@RequestBody List<RateCardRoleUtilization> rateCardRoleUtilization){
		AppLoger.APPLOGGER.info("*************updateRateUtilizationAndRates controller");
		return rightPriceService.updateRateUtilizationAndRates(rateCardRoleUtilization);
	 }
	
	@ApiOperation(value = "update rate card utilzation and rates data", response = StaffingDetails.class)
	@RequestMapping(value = "/updateFpDealStaffingDetails", method = RequestMethod.POST)
	 public ResponseEntity<Object> updateFpDealStaffingDetails(@RequestBody List<StaffingDetails> staffingDetails){
		AppLoger.APPLOGGER.info("inside add updateFpDealStaffingDetails controller");
		return rightPriceService.updateFpDealStaffingDetails(staffingDetails);
	 }
	@ApiOperation(value = "update rate card utilzation and rates data", response = StaffingDetails.class)
	@RequestMapping(value = "/updateTMDealStaffingDetails", method = RequestMethod.POST)
	 public ResponseEntity<Object> updateTMDealStaffingDetails(@RequestBody List<StaffingDetails> staffingDetails){
		AppLoger.APPLOGGER.info("inside add updateTMDealStaffingDetails controller");
		return rightPriceService.updateTMDealStaffingDetails(staffingDetails);
	 }
	
	@ApiOperation(value = "update rate card utilzation and rates data") 
	@RequestMapping(value = "/updateStaffingContractorRole", method = RequestMethod.POST)
	 public ResponseEntity<Object> updateStaffingContractorRole(@RequestBody FPDCRCAndProjectDetails fPDCRCAndProjectDetails){
		System.out.println("List details"+fPDCRCAndProjectDetails.getStfContrRoleList());
		AppLoger.APPLOGGER.info("************************************************updateStaffingContractorRole******************");
		return rightPriceService.updateStaffingContractorRole(fPDCRCAndProjectDetails);
	 }
	
	@ApiOperation(value = "view Parameter", response = Deduction.class)
	@RequestMapping(value = "/onViewParameterForm", method = RequestMethod.POST)
	public ResponseEntity<Object> onViewParameterForm(@RequestBody Deduction deduction) {
		AppLoger.APPLOGGER.info("View Parameter:"+deduction.getCountryId()+" -- "+deduction.getYear());
		return rightPriceService.onViewParameterForm(deduction);
	}
	@ApiOperation(value = "view Parameter", response = Deduction.class)
	@RequestMapping(value = "/onTaxViewParameterForm", method = RequestMethod.POST)
	public ResponseEntity<Object> onTaxViewParameterForm(@RequestBody Deduction deduction) {
		AppLoger.APPLOGGER.info("View Parameter:"+deduction.getCountryId()+" -- "+deduction.getYear());
		return rightPriceService.onTaxViewParameterForm(deduction);
	}
	@ApiOperation(value = "view Parameter", response = Deduction.class)
	@RequestMapping(value = "/onAssumptionsViewParameterForm", method = RequestMethod.POST)
	public ResponseEntity<Object> onAssumptionsViewParameterForm(@RequestBody Deduction deduction) {
		AppLoger.APPLOGGER.info("View Parameter:"+deduction.getCountryId()+" -- "+deduction.getYear());
		return rightPriceService.onAssumptionsViewParameterForm(deduction);
	}
	
	@RequestMapping(value = "/getParamList", method = RequestMethod.GET, headers = "Accept=application/json")
	public ResponseEntity<Object> getParamList() {
		return rightPriceService.getParamList();
	}
	@RequestMapping(value = "/getTaxParamList", method = RequestMethod.GET, headers = "Accept=application/json")
	public ResponseEntity<Object> getTaxParamList() {
		return rightPriceService.getTaxParamList();
	}
	@RequestMapping(value = "/getAssumptionsParamList", method = RequestMethod.GET, headers = "Accept=application/json")
	public ResponseEntity<Object> getAssumptionsParamList() {
		return rightPriceService.getAssumptionsParamList();
	}
	
	@ApiOperation(value = "onAddCCPSearch", response = Deduction.class)
	@RequestMapping(value = "/onAddCCPSearch", method = RequestMethod.POST)
	public ResponseEntity<Object> onAddCCPSearch(@RequestBody Deduction deduction) {
		AppLoger.APPLOGGER.info("on search click :CCP --> "+deduction.getCountryId() + " -- " + deduction.getYear() +"--"+deduction.getDeductionTypeId());
		return rightPriceService.onAddCCPSearch(deduction);
	}
	@ApiOperation(value = "onAddTPSearch", response = Deduction.class)
	@RequestMapping(value = "/onAddTPSearch", method = RequestMethod.POST)
	public ResponseEntity<Object> onAddTPSearch(@RequestBody Deduction deduction) {
		AppLoger.APPLOGGER.info("on search click :CCP --> "+deduction.getCountryId() + " -- " + deduction.getYear() +"--"+deduction.getDeductionTypeId());
		return rightPriceService.onAddTPSearch(deduction);
	}
	
	
	@RequestMapping(value = "/onAddAPSearch/{countryId}/{visaTypeId}/{year}", method = RequestMethod.GET, headers = "Accept=application/json")
	public ResponseEntity<Object> onAddAPSearch(@PathVariable("countryId") int countryId,@PathVariable("visaTypeId") int visaTypeId, @PathVariable("year") int year) {
		AppLoger.APPLOGGER.info("Returning parameter");
		return rightPriceService.onAddAPSearch(countryId,visaTypeId,year);
	}
	
	@RequestMapping(value = "/onUpdateTPSearch/{countryId}/{visaTypeId}/{year}", method = RequestMethod.GET, headers = "Accept=application/json")
	public ResponseEntity<Object> onUpdateTPSearch(@PathVariable("countryId") int countryId,@PathVariable("visaTypeId") int visaTypeId, @PathVariable("year") int year) {
		AppLoger.APPLOGGER.info("Returning parameter");
		return rightPriceService.onUpdateTPSearch(countryId,visaTypeId,year);
	}
	
	@ApiOperation(value = "Update/Save Common Cost Parameter", response = Deduction.class)
	@RequestMapping(value = "/saveCommonCostParam", method = RequestMethod.POST)
	public ResponseEntity<Object> saveCommonCostParam(@RequestBody Deduction[] deduction) {
		AppLoger.APPLOGGER.info("inside Contractor Data:Size is -- " + deduction.length);
//		return rightPriceService.saveContractorRole(contractor);
		return rightPriceService.saveCommonCostParam(deduction);
	}
	@ApiOperation(value = "Update/Save Tax Parameter", response = Deduction.class)
	@RequestMapping(value = "/saveTaxParam", method = RequestMethod.POST)
	public ResponseEntity<Object> saveTaxParam(@RequestBody Deduction[] deduction) {
		AppLoger.APPLOGGER.info("inside Contractor Data:Size is -- " + deduction.length);
//		return rightPriceService.saveContractorRole(contractor);
		return rightPriceService.saveTaxParam(deduction);
	}
	@ApiOperation(value = "Update/Save Assumptions Parameter", response = Deduction.class)
	@RequestMapping(value = "/saveAssumptionsParam", method = RequestMethod.POST)
	public ResponseEntity<Object> saveAssumptionsParam(@RequestBody Deduction[] deduction) {
		AppLoger.APPLOGGER.info("inside Contractor Data:Size is -- " + deduction.length);
//		return rightPriceService.saveContractorRole(contractor);
		return rightPriceService.saveAssumptionsParam(deduction);
	}
	
	@ApiOperation(value = "update Common Cost Parameter", response = Deduction.class)
	@RequestMapping(value = "/updateCommonCostParam", method = RequestMethod.POST)
	public ResponseEntity<Object> updateCommonCostParam(@RequestBody Deduction[] deduction) {
		AppLoger.APPLOGGER.info("updateCommonCostParam -- " + deduction.length);
		return rightPriceService.updateCommonCostParam(deduction);
	}
	@ApiOperation(value = "update Common Cost Parameter", response = Deduction.class)
	@RequestMapping(value = "/updateTaxParam", method = RequestMethod.POST)
	public ResponseEntity<Object> updateTaxParam(@RequestBody Deduction[] deduction) {
		AppLoger.APPLOGGER.info("updateTaxParam -- " + deduction.length);
		return rightPriceService.updateTaxParam(deduction);
	}
	@ApiOperation(value = "update Assumptions Parameter", response = Deduction.class)
	@RequestMapping(value = "/updateAssumptionsParam", method = RequestMethod.POST)
	public ResponseEntity<Object> updateAssumptionsParam(@RequestBody Deduction[] deduction) {
		AppLoger.APPLOGGER.info("updateAssumptionsParam -- " + deduction.length);
		return rightPriceService.updateAssumptionsParam(deduction);
	}
	
	@ApiOperation(value = "update Assumptions Parameter", response = Deduction.class)
	@RequestMapping(value = "/updateAssumptionsParamValue", method = RequestMethod.POST)
	public ResponseEntity<Object> updateAssumptionsParamValue(@RequestBody DeductionforUpdate[] deduction) {
		AppLoger.APPLOGGER.info("updateAssumptionsParam -- " + deduction.length);
		return rightPriceService.updateAssumptionsParamValue(deduction);
	}
	/*@ApiOperation(value = "update Parameter", response = Deduction.class)
	@RequestMapping(value = "/onUpdateCCPSearch", method = RequestMethod.POST)
	public ResponseEntity<Object> onUpdateCCPSearch(@RequestBody Deduction deduction) {
		AppLoger.APPLOGGER.info("update Parameter:"+deduction.getCountryId()+" -- "+deduction.getYear()+"--"+deduction.getDeductionTypeId());
		return rightPriceService.onUpdateCCPSearch(deduction);
	}*/
	
	@RequestMapping(value = "/onUpdateCCPSearch/{countryId}/{visaTypeId}/{year}", method = RequestMethod.GET, headers = "Accept=application/json")
	public ResponseEntity<Object> onUpdateCCPSearch(@PathVariable("countryId") int countryId,@PathVariable("visaTypeId") int visaTypeId, @PathVariable("year") int year) {
		AppLoger.APPLOGGER.info("Returning parameter");
		return rightPriceService.onUpdateCCPSearch(countryId,visaTypeId,year);
	}
	
	@ApiOperation(value = "update Parameter", response = Deduction.class)
	@RequestMapping(value = "/onUpdateAPSearch", method = RequestMethod.POST)
	public ResponseEntity<Object> onUpdateAPSearch(@RequestBody Deduction deduction) {
		AppLoger.APPLOGGER.info("update Parameter:"+deduction.getCountryId()+" -- "+deduction.getYear()+"--"+deduction.getDeductionTypeId());
		return rightPriceService.onUpdateAPSearch(deduction);
	}
	
	// Parthi Start
   @RequestMapping(value = "/downloadLobExcel", method = RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> getExcelData() throws IOException {
	    String fileName = null;
	    
		String timeStamp = new SimpleDateFormat("yyyy_MM_dd_hh_mm_ss").format(Calendar.getInstance().getTime());
		
		fileName = "Master_Lob_Details"+"_"+timeStamp+".xls";
		
    	List<Lob> lobDetails = (List<Lob>) rightPriceService.getLob();
    	
        byte []bis = ExcelView.getLobDetailsReport(lobDetails);
        
        return ResponseEntity.status(200).header("Content-disposition",
        		"attachment; filename="+fileName).contentType(MediaType.parseMediaType("application/octet-stream")).body(bis);
        
    }
   
   @RequestMapping(value = "/downloadDesignationExcel", method = RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE)
 	public ResponseEntity<Object> getDesignationExcelData() throws IOException {
 	    String fileName = null;
 		String timeStamp = new SimpleDateFormat("yyyy_MM_dd_hh_mm_ss").format(Calendar.getInstance().getTime());
 		fileName = "Master_Designation_Details"+"_"+timeStamp+".xls";
		ResponseEntity<Object> designationDetails = (ResponseEntity<Object>) rightPriceService.getDesignation();
		List<Designation> list = 	(List<Designation>) designationDetails.getBody();
         byte []bis = ExcelView.getDesignationReport(list);
         return ResponseEntity.status(200).header("Content-disposition",
         		"attachment; filename="+fileName).contentType(MediaType.parseMediaType("application/octet-stream")).body(bis);
         
     }
   
   @RequestMapping(value = "/downloadVerticalExcel", method = RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> getVerticalExcel() throws IOException {
	    String fileName = null;
		String timeStamp = new SimpleDateFormat("yyyy_MM_dd_hh_mm_ss").format(Calendar.getInstance().getTime());
		fileName = "Master_Vertical_Details"+"_"+timeStamp+".xls";
		ResponseEntity<Object> verticalDetails = (ResponseEntity<Object>) rightPriceService.getVertical();
		List<Vertical> list = 	(List<Vertical>) verticalDetails.getBody();
        byte []bis = ExcelView.getVerticalReport(list);
        return ResponseEntity.status(200).header("Content-disposition",
        		"attachment; filename="+fileName).contentType(MediaType.parseMediaType("application/octet-stream")).body(bis);
        
    }
   
   @ApiOperation(value = "get Report Details of City", response =  SearchCity.class)
	@RequestMapping(value = "/downloadCityExcel/{countryId}/{cityId}/{countryName}/{cityName}", method = RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> getCityExcel(@PathVariable("countryId") int countryId,@PathVariable("cityId") int cityId,@PathVariable("countryName") String countryName, @PathVariable("cityName") String cityName) {
	    String fileName = null;
		String timeStamp = new SimpleDateFormat("yyyy_MM_dd_hh_mm_ss").format(Calendar.getInstance().getTime());
		fileName = "Master_City_Details"+"_"+timeStamp+".xls";
		ResponseEntity<Object> city= (ResponseEntity<Object>) rightPriceService.getSearchExportCity(countryId,cityId);
		AppLoger.APPLOGGER.info("The Data from the response is................         "+city.getBody());
		List<SearchCity> cityList = 	(List<SearchCity>) city.getBody();
		AppLoger.APPLOGGER.info("City LIst is............ "+ cityList.size());
       byte []bis = ExcelView.getCityReport(cityList,countryId,cityId,countryName,cityName);
       return ResponseEntity.status(200).header("Content-disposition",
       		"attachment; filename="+fileName).contentType(MediaType.parseMediaType("application/octet-stream")).body(bis);
	}
   
   
   @ApiOperation(value = "get Report Details of Vertical Members", response =  VerticalMembers.class)
  	@RequestMapping(value = "/downloadViewVerticalExcel/{verticalId}", method = RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE)
  	public ResponseEntity<Object> getVerticalMemberExcel(@PathVariable("verticalId") int verticalId) {
  	    String fileName = null;
  		String timeStamp = new SimpleDateFormat("yyyy_MM_dd_hh_mm_ss").format(Calendar.getInstance().getTime());
  		fileName = "Master_Vertical_Member_Details"+"_"+timeStamp+".xls";
  		ResponseEntity<Object> verticalMember= (ResponseEntity<Object>) rightPriceService.getTeamVerticalExportDetails(verticalId);
  		AppLoger.APPLOGGER.info("The Data from the response is................         "+verticalMember.getBody());
  		List<EmpDetails> verticalData = 	(List<EmpDetails>) verticalMember.getBody();
  		AppLoger.APPLOGGER.info("City LIst is............ "+ verticalData.size());
         byte []bis = ExcelView.getVerticalMemberExcelReport(verticalData, verticalId);
         return ResponseEntity.status(200).header("Content-disposition",
         		"attachment; filename="+fileName).contentType(MediaType.parseMediaType("application/octet-stream")).body(bis);
  	}
   
  
   
   /*@ApiOperation(value = "get Report Details of Rate card Utilization Role", response = RateCardRoleUtilization.class)
   @RequestMapping(value = "/downloadUtilizationRoleExcel", method = RequestMethod.POST)
   public ResponseEntity<Object>downloadUtilizationRoleExcel(@RequestBody RateCardRoleUtilization[] rateCardDetails) 
   {
	   try
	   {
		   String fileName = null;
		   String timeStamp = new SimpleDateFormat("yyyy_MM_dd_hh_mm_ss").format(Calendar.getInstance().getTime());
		   fileName = "Rate Catd_"+ rateCardDetails[0].getRcId() + "_creationRoleandRates"+"_"+timeStamp+".xls";
		
		   AppLoger.APPLOGGER.info("Data received " + rateCardDetails);		   
		   List <RoleUtilizationYear> lstYears = new ArrayList<RoleUtilizationYear>();
		   
		   for(int i=0; i < rateCardDetails.length; i++)
		   {
			   RoleUtilizationYear objYear = new RoleUtilizationYear();
			   if(i == 0)
			   {
				   objYear.setYear(rateCardDetails[i].getTransactionYear());
				   objYear.setYearDesc(rateCardDetails[i].getMonthYearHeader());
				   lstYears.add(objYear);
			   }
			   else
			   {
			   	 int counter = 0; 
				   for(int j=0; j < lstYears.size(); j++)
				   {					 
					  if(lstYears.get(j).getYear().equals(rateCardDetails[i].getTransactionYear()))
					  {
						  counter++;				
					  }					  
				   }
				   if(counter == 0)
				   {
					   RoleUtilizationYear year = new RoleUtilizationYear();
					   year.setYear(rateCardDetails[i].getTransactionYear());
					   year.setYearDesc(rateCardDetails[i].getMonthYearHeader());
					   lstYears.add(year);
				   }
			   }
		   }
		   
		   AppLoger.APPLOGGER.info("Data received " + lstYears);
		   
		   byte []bis = ExcelView.getLocalCurrencyExcelReport(lstYears,rateCardDetails);
	     	return ResponseEntity.status(200).header("Content-disposition",
	     		"attachment; filename="+fileName).contentType(MediaType.parseMediaType("application/octet-stream")).body(bis);
		   
		   //return ResponseEntity.status(200).contentType(MediaType.TEXT_PLAIN).body("Data Inserted Successfully");
	   }
	   catch (Exception e) 
	   {
		   e.printStackTrace();
		   AppLoger.APPLOGGER.info("Exception : " + e);
		   return ResponseEntity.status(205).contentType(MediaType.TEXT_PLAIN).body("Currently We are facing technical issues, please try again later.");
	   }
   }*/
   
   @ApiOperation(value = "get Report Details of Rate card Utilization Role", response = RateCardRoleUtilization.class)
   @RequestMapping(value = "/downloadUtilizationRoleExcel/{cityId}/{rcId}/{multifier}/{isBillCurrency}/{currencyName}", method = RequestMethod.GET,produces=MediaType.APPLICATION_OCTET_STREAM_VALUE)
   public ResponseEntity<Object>downloadUtilizationRoleExcel(@PathVariable("cityId") int cityId, @PathVariable("rcId") int rcId,
		   @PathVariable("multifier") Double multifier, @PathVariable("isBillCurrency") boolean isBillCurrency, @PathVariable("currencyName") String currencyName) 
   {
	   String fileName = null;
	   String timeStamp = new SimpleDateFormat("yyyy_MM_dd_hh_mm_ss").format(Calendar.getInstance().getTime());
	   fileName = "Rate Catd_"+ rcId + "_creationRoleandRates"+"_"+timeStamp+".xls";
	   ResponseEntity<Object> parentData = (ResponseEntity<Object>) rightPriceService.getRoleUtilization(cityId,rcId);
	   ResponseEntity<Object> rcLocData = (ResponseEntity<Object>) rightPriceService.getRCCityDetails(cityId, rcId);
	   List<RateCardLocation> rcLocDetails = (List<RateCardLocation>) rcLocData.getBody();
	   AppLoger.APPLOGGER.info("City isOffshore: " + rcLocDetails.get(0).getIsOffShore()); 
	   
	   HashMap<Object, Object> objHashMap = (HashMap) parentData.getBody();
	   List<RateCardRoleUtilization> lstRCRoleUtilization = new ArrayList<RateCardRoleUtilization>();
	   List<RateCardDetails> lstRCDetails= new ArrayList<RateCardDetails>();
	   /*List<RateCardYOYIncrement> lstRCYOY= new ArrayList<RateCardYOYIncrement>();*/
	   for(Object object : objHashMap.entrySet()) 
	   {	
		   lstRCRoleUtilization = (List<RateCardRoleUtilization>) objHashMap.get("dataList");		
		   lstRCDetails = (List<RateCardDetails>) objHashMap.get("rcList");
		   /*lstRCYOY = (List<RateCardYOYIncrement>) objHashMap.get("rcYOY");*/
	   }
	   
	   AppLoger.APPLOGGER.info("RC Role Utilization Details ............ "+ lstRCRoleUtilization.size());
	   
	   
	   List<RoleUtilizationYear> lstYears = new ArrayList<RoleUtilizationYear>(); 
	   
	   for(int i=0; i < lstRCRoleUtilization.size(); i++)
	   {
		   RoleUtilizationYear objYear = new RoleUtilizationYear();
		   if(i == 0)
		   {
			   objYear.setYear(lstRCRoleUtilization.get(i).getTransactionYear());
			   objYear.setYearDesc(lstRCRoleUtilization.get(i).getMonthYearHeader());
			   lstYears.add(objYear);
		   }
		   else
		   {
		   	 int counter = 0; 
			   for(int j=0; j < lstYears.size(); j++)
			   {					 
				  if(lstYears.get(j).getYear().equals(lstRCRoleUtilization.get(i).getTransactionYear()))
				  {
					  counter++;				
				  }					  
			   }
			   if(counter == 0)
			   {
				   RoleUtilizationYear year = new RoleUtilizationYear();
				   year.setYear(lstRCRoleUtilization.get(i).getTransactionYear());
				   year.setYearDesc(lstRCRoleUtilization.get(i).getMonthYearHeader());
				   lstYears.add(year);
			   }
		   }
	   }
	  
	   AppLoger.APPLOGGER.info("RC Role Utilization Years  Details ............ "+ lstYears.size());
	  // Double dblMultifier = Double.parseDouble(multifier);	   
	   /*byte []bis = ExcelView.downloadUtilizationRoleExcel(lstYears,lstRCRoleUtilization,lstRCDetails,isBillCurrency,multifier,currencyName,lstRCYOY);*/
	   byte []bis = ExcelView.downloadUtilizationRoleExcel(lstYears,lstRCRoleUtilization,lstRCDetails,isBillCurrency,multifier,currencyName,rcLocDetails);
    	return ResponseEntity.status(200).header("Content-disposition",
    		"attachment; filename="+fileName).contentType(MediaType.parseMediaType("application/octet-stream")).body(bis);	   
   }
   
   @ApiOperation(value = "get Report Details of Basic Allowances", response = BasicAllowance.class)
	@RequestMapping(value = "/downloadMasterAllowanceExcel/{countryId}/{visaId}/{year}/{countryName}/{visaName}", method = RequestMethod.GET,produces=MediaType.APPLICATION_OCTET_STREAM_VALUE)
	public ResponseEntity<Object> getAllownaceExcelReport(@PathVariable("countryId") int countryId,@PathVariable("visaId") int visaId,@PathVariable("year") int year,@PathVariable("countryName") String countryName,@PathVariable("visaName") String visaName) {
	    String fileName = null;
		String timeStamp = new SimpleDateFormat("yyyy_MM_dd_hh_mm_ss").format(Calendar.getInstance().getTime());
		fileName = "Master_Allowance_Details"+"_"+timeStamp+".xls";
		ResponseEntity<Object> basicAllowance= (ResponseEntity<Object>) rightPriceService.viewAllowancesExportDetails(countryId,visaId,year);
		List<BasicAllowance> allowanceList = 	(List<BasicAllowance>) basicAllowance.getBody();
		AppLoger.APPLOGGER.info("City LIst is............ "+ allowanceList.size());
      byte []bis = ExcelView.getBasicAllowanceExcelReport(allowanceList,countryId,visaId,year,countryName,visaName);
      return ResponseEntity.status(200).header("Content-disposition",
      		"attachment; filename="+fileName).contentType(MediaType.parseMediaType("application/octet-stream")).body(bis);
	}
   
   @ApiOperation(value = "get Roles excel template", response = FpDealRoleExcel.class)
  	@RequestMapping(value = "/downloadRolesExcel/{towerId}/{autoDealTowerId}/{dealVersionId}/{towerName}/{currencyNm}/{country}/{city}/{dealVersion}", method = RequestMethod.GET,produces=MediaType.APPLICATION_OCTET_STREAM_VALUE)
  	public ResponseEntity<Object> downloadRolesExcel(@PathVariable("towerId") int towerId,@PathVariable("autoDealTowerId") int autoDealTowerId,
  			@PathVariable("dealVersionId") int dealVersionId,@PathVariable("towerName") String towerName,@PathVariable("currencyNm") String currencyNm,
  			@PathVariable("country") String country,@PathVariable("city") String city,@PathVariable("dealVersion") String dealVersion) {
  	    String fileName = null;
  		String timeStamp = new SimpleDateFormat("yyyy_MM_dd_hh_mm_ss").format(Calendar.getInstance().getTime());
  		fileName = towerName+"_"+dealVersion+"_"+country+"_"+city+"_"+currencyNm+"_"+timeStamp+".xls";
  		
  		ResponseEntity<Object> parentData= (ResponseEntity<Object>) rightPriceService.viewRolesExportDetails(towerId,dealVersionId);
  		
  		HashMap<Object, Object> objHashMap = (HashMap) parentData.getBody();
  		List<Deal> dealData= new ArrayList<Deal>();
  		List<StaffingDetails> lstStaffing= new ArrayList<StaffingDetails>();
  		
  		for(Object object : objHashMap.entrySet()) 
  		{	
  			dealData = (List<Deal>) objHashMap.get("dealData");
  			lstStaffing = (List<StaffingDetails>) objHashMap.get("staffingDetails");
  		}
	   
  		List<String> lstDetails = new ArrayList<String>();
  		lstDetails.add(0, towerName);
  		lstDetails.add(1, dealVersion);
  		lstDetails.add(2, country);
  		lstDetails.add(3, city);
  		lstDetails.add(4, currencyNm);
  		AppLoger.APPLOGGER.info("Role list is............ "+ lstStaffing.size());
        byte []bis = ExcelView.getRolesExcelDetails(dealData,towerId,autoDealTowerId,lstDetails,lstStaffing);
        return ResponseEntity.status(200).header("Content-disposition",
        		"attachment; filename="+fileName).contentType(MediaType.parseMediaType("application/octet-stream")).body(bis);
  		
  	}
   
   @ApiOperation(value = "get Report Details of Basic Allowances", response = BasicAllowance.class)
	@RequestMapping(value = "/downloadMasterRolesExcel/{practiceId}/{description}/{subpracticeId}/{subpracticeName}", method = RequestMethod.GET,produces=MediaType.APPLICATION_OCTET_STREAM_VALUE)
	public ResponseEntity<Object> getMasterRolesEcelReport(@PathVariable("practiceId") int practiceId,@PathVariable("description") String description,
			@PathVariable("subpracticeId") int subpracticeId,@PathVariable("subpracticeName") String subpracticeName){
	    String fileName = null;
		String timeStamp = new SimpleDateFormat("yyyy_MM_dd_hh_mm_ss").format(Calendar.getInstance().getTime());
		fileName = "Master_Roles_Details"+"_"+timeStamp+".xls";
		ResponseEntity<Object> masterRoles= (ResponseEntity<Object>) rightPriceService.viewMasterRolesExportDetails(practiceId,subpracticeId);
		List<MasterRoles> objMasterRolesList = 	(List<MasterRoles>) masterRoles.getBody();
		AppLoger.APPLOGGER.info("Master roles List is............ "+ objMasterRolesList.size());
		byte []bis = ExcelView.getMasterRolesExcelReport(objMasterRolesList,practiceId,description,subpracticeId,subpracticeName);
		return ResponseEntity.status(200).header("Content-disposition",
     		"attachment; filename="+fileName).contentType(MediaType.parseMediaType("application/octet-stream")).body(bis);
	}
   
   @ApiOperation(value = "get Report Details of Right Price Rate", response = YearwiseBasicSalary.class)
	@RequestMapping(value = "/downloadMasterSalExcel/{countryId}/{practiceId}/{year}/{countryName}/{practiceName}", method = RequestMethod.GET,produces=MediaType.APPLICATION_OCTET_STREAM_VALUE)
	public ResponseEntity<Object> getMasterRPRateExcel(@PathVariable("countryId") int countryId,@PathVariable("practiceId") int practiceId,@PathVariable("year") int year,@PathVariable("countryName") String countryName,@PathVariable("practiceName") String practiceName) {
	    String fileName = null;
		String timeStamp = new SimpleDateFormat("yyyy_MM_dd_hh_mm_ss").format(Calendar.getInstance().getTime());
		fileName = "Master_Salaray_Details"+"_"+timeStamp+".xls";
		ResponseEntity<Object> basicSalary = (ResponseEntity<Object>) rightPriceService.viewSalaryExportDetails(countryId,practiceId,year);
		List<BasicSalary> salaryList = 	(List<BasicSalary>) basicSalary.getBody();
		AppLoger.APPLOGGER.info("Salary LIst is............ "+ salaryList.size());
     byte []bis = ExcelView.getSalaryExcelReport(salaryList,countryId,practiceId,year,countryName,practiceName);
     return ResponseEntity.status(200).header("Content-disposition",
     		"attachment; filename="+fileName).contentType(MediaType.parseMediaType("application/octet-stream")).body(bis);
	}
   
   @ApiOperation(value = "get Report Details of Right Price Rate", response = MasterRate.class)
   @RequestMapping(value = "/downloadMasterRPRateExcel/{countryId}/{year}/{countryName}", method = RequestMethod.GET,produces=MediaType.APPLICATION_OCTET_STREAM_VALUE)
   public ResponseEntity<Object> getSalExcelReport(@PathVariable("countryId") int countryId,@PathVariable("year") int year,@PathVariable("countryName") String countryName) 
   {
	   String fileName = null;
	   String timeStamp = new SimpleDateFormat("yyyy_MM_dd_hh_mm_ss").format(Calendar.getInstance().getTime());
	   fileName = "Master_RightPriceRate_Details"+"_"+timeStamp+".xls";
	   ResponseEntity<Object> objeRPRate = (ResponseEntity<Object>) rightPriceService.viewRPRateDetails(countryId,year);
	   List<MasterRate> rPRateList = 	(List<MasterRate>) objeRPRate.getBody();
	   AppLoger.APPLOGGER.info("Right Price Rate LIst is............ "+ rPRateList.size());
       byte []bis = ExcelView.getRPRateExcelReport(rPRateList,countryId,year,countryName);
       return ResponseEntity.status(200).header("Content-disposition",
       		"attachment; filename="+fileName).contentType(MediaType.parseMediaType("application/octet-stream")).body(bis);
  		//return null;
  	}
   
   @ApiOperation(value = "get Report Details of common Cost Parameters", response = Deduction.class)
	@RequestMapping(value = "/downloadMasterCommonCostExcel/{countryId}/{year}", method = RequestMethod.GET,produces=MediaType.APPLICATION_OCTET_STREAM_VALUE)
	public ResponseEntity<Object> getCommonCostExcelReport(@PathVariable("countryId") int countryId,@PathVariable("year") int year) {
	    String fileName = null;
		String timeStamp = new SimpleDateFormat("yyyy_MM_dd_hh_mm_ss").format(Calendar.getInstance().getTime());
		fileName = "Master_Common_Cost_Parameters_Details"+"_"+timeStamp+".xls";
		ResponseEntity<Object> commonCostParam= (ResponseEntity<Object>) rightPriceService.viewCommonCostExportDetails(countryId,year);
		Map<String, Object> res = (Map<String, Object>) commonCostParam.getBody();
		ArrayList<Deduction> commonCostDesc= (ArrayList<Deduction>) res.get("commonCostData");
		ArrayList<CommonCost> commonCostDescData  = (ArrayList<CommonCost>) res.get("commonCostDescData");
		AppLoger.APPLOGGER.info("Common Cost Deduction List is............ "+ commonCostDesc);
		AppLoger.APPLOGGER.info("Common Cost Des Data is............. "+commonCostDescData);
     byte []bis = ExcelView.getCommonCostParametersReport(commonCostDesc,commonCostDescData,countryId,year);
     return ResponseEntity.status(200).header("Content-disposition",
     		"attachment; filename="+fileName).contentType(MediaType.parseMediaType("application/octet-stream")).body(bis);
	}
   @ApiOperation(value = "get Report Details of common Cost Parameters", response = Deduction.class)
   @RequestMapping(value = "/downloadMasterTaxExcel/{countryId}/{year}", method = RequestMethod.GET,produces=MediaType.APPLICATION_OCTET_STREAM_VALUE)
   public ResponseEntity<Object> getTaxExportDetails(@PathVariable("countryId") int countryId,@PathVariable("year") int year) {
	   String fileName = null;
	   String timeStamp = new SimpleDateFormat("yyyy_MM_dd_hh_mm_ss").format(Calendar.getInstance().getTime());
	   fileName = "Master_Common_Cost_Parameters_Details"+"_"+timeStamp+".xls";
	   ResponseEntity<Object> taxParam= (ResponseEntity<Object>) rightPriceService.getTaxExportDetails(countryId,year);
	   Map<String, Object> res = (Map<String, Object>) taxParam.getBody();
	   ArrayList<Deduction> taxDesc= (ArrayList<Deduction>) res.get("commonCostData");
	   ArrayList<MasterTaxType> taxDescData  = (ArrayList<MasterTaxType>) res.get("commonCostDescData");
	   AppLoger.APPLOGGER.info("Common Cost Deduction List is............ "+ taxDesc);
	   AppLoger.APPLOGGER.info("Common Cost Des Data is............. "+taxDescData);
	   byte []bis = ExcelView.getTaxParametersReport(taxDesc,taxDescData,countryId,year);
	   return ResponseEntity.status(200).header("Content-disposition",
			   "attachment; filename="+fileName).contentType(MediaType.parseMediaType("application/octet-stream")).body(bis);
   }
   @ApiOperation(value = "get Report Details of common Cost Parameters", response = Deduction.class)
   @RequestMapping(value = "/downloadAssumptionsExcel/{countryId}/{year}", method = RequestMethod.GET,produces=MediaType.APPLICATION_OCTET_STREAM_VALUE)
   public ResponseEntity<Object> getAssumptionsExportDetails(@PathVariable("countryId") int countryId,@PathVariable("year") int year) {
	   String fileName = null;
	   String timeStamp = new SimpleDateFormat("yyyy_MM_dd_hh_mm_ss").format(Calendar.getInstance().getTime());
	   fileName = "Master_Common_Cost_Parameters_Details"+"_"+timeStamp+".xls";
	   ResponseEntity<Object> assumptionsParam= (ResponseEntity<Object>) rightPriceService.getAssumptionsExportDetails(countryId,year);
	   Map<String, Object> res = (Map<String, Object>) assumptionsParam.getBody();
	   ArrayList<Deduction> assumptionsDesc= (ArrayList<Deduction>) res.get("commonCostData");
	   ArrayList<MasterAssumptions> taxDescData  = (ArrayList<MasterAssumptions>) res.get("commonCostDescData");
	   AppLoger.APPLOGGER.info("Common Cost Deduction List is............ "+ assumptionsDesc);
	   AppLoger.APPLOGGER.info("Common Cost Des Data is............. "+taxDescData);
	   byte []bis = ExcelView.getAssumptionsParametersReport(assumptionsDesc,taxDescData,countryId,year);
	   return ResponseEntity.status(200).header("Content-disposition",
			   "attachment; filename="+fileName).contentType(MediaType.parseMediaType("application/octet-stream")).body(bis);
   }
   
   @RequestMapping(value = "/getCountryForexExcel", method = RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE)
  	public ResponseEntity<Object> getCountryForexExcelData() throws IOException {
  	    String fileName = null;
  		String timeStamp = new SimpleDateFormat("yyyy_MM_dd_hh_mm_ss").format(Calendar.getInstance().getTime());
  		fileName = "Master_Country_Forex_Details"+"_"+timeStamp+".xls";
  		ResponseEntity<Object> countryDetails= (ResponseEntity<Object>) rightPriceService.getCountryDetail();
  		List<Country> countryDetailsList = 	(List<Country>) countryDetails.getBody();
  		byte []bis = ExcelView.getCountryForexExcelReport(countryDetailsList);
          return ResponseEntity.status(200).header("Content-disposition",
          		"attachment; filename="+fileName).contentType(MediaType.parseMediaType("application/octet-stream")).body(bis);
      }
   
   @ApiOperation(value = "get Report Details of Vertical Members", response =  VerticalMembers.class)
 	@RequestMapping(value = "/downloadSubPracticeExcelData/{practiceId}/{practiceName}", method = RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE)
 	public ResponseEntity<Object> getSubPracticeExcel(@PathVariable("practiceId") int practiceId, @PathVariable("practiceName") String practiceName) {
 	    String fileName = null;
 		String timeStamp = new SimpleDateFormat("yyyy_MM_dd_hh_mm_ss").format(Calendar.getInstance().getTime());
 		fileName = "Master_Sub_Practice_Details"+"_"+timeStamp+".xls";
 		ResponseEntity<Object> subPraciceDetails= (ResponseEntity<Object>) rightPriceService.getSubPractice(practiceId);
 		AppLoger.APPLOGGER.info("The Data from the response is................         "+subPraciceDetails.getBody());
 		List<SubPractice> subPracticeData = 	(List<SubPractice>) subPraciceDetails.getBody();
 		AppLoger.APPLOGGER.info("City LIst is............ "+ subPracticeData.size());
        byte []bis = ExcelView.getSubPracticeExcelReport(subPracticeData, practiceId,practiceName);
        return ResponseEntity.status(200).header("Content-disposition",
        		"attachment; filename="+fileName).contentType(MediaType.parseMediaType("application/octet-stream")).body(bis);
 	}
   
   @ApiOperation(value = "get Report Details of Miscelleneous Report", response = OffshoreMiscellaneousCost.class)
  	@RequestMapping(value = "/downloadMasterMiscelleneousExcel/{city}", method = RequestMethod.GET,produces=MediaType.APPLICATION_OCTET_STREAM_VALUE)
  	public ResponseEntity<Object> getMasterMiscelleneousReport(@PathVariable("city") int city) {
  	    String fileName = null;
  	    
  		String timeStamp = new SimpleDateFormat("yyyy_MM_dd_hh_mm_ss").format(Calendar.getInstance().getTime());
  		
  		fileName = "Master_Miscelleneous_Report_Details"+"_"+timeStamp+".xls";
  		
  		ResponseEntity<Object> miscelleneousReport= (ResponseEntity<Object>) rightPriceService.getUpdateMiscellaneousCost(city);
  		
  		List<OffshoreMiscellaneousCost> miscellenoeusData = 	(List<OffshoreMiscellaneousCost>) miscelleneousReport.getBody();
  		
       byte []bis = ExcelView.getMiscelleneousExcelReport(miscellenoeusData,city);
       
       return ResponseEntity.status(200).header("Content-disposition",
       		"attachment; filename="+fileName).contentType(MediaType.parseMediaType("application/octet-stream")).body(bis);
  	}
   
  /* @ApiOperation(value = "get Report Details of Tax Assumptions", response = Deduction.class)
 	@RequestMapping(value = "/downloadTaxAssumptionExcel/{country}/{deductionId}", method = RequestMethod.GET,produces=MediaType.APPLICATION_OCTET_STREAM_VALUE)
 	public ResponseEntity<Object> getMasterTaxParameters(@PathVariable("country") int country, @PathVariable("deductionId") int deductionId) {
 	    String fileName = null;
 		String timeStamp = new SimpleDateFormat("yyyy_MM_dd_hh_mm_ss").format(Calendar.getInstance().getTime());
 		fileName = "Master_Tax_Parameters_Report_Details"+"_"+timeStamp+".xls";
 		ResponseEntity<Object> taxAssumptionDetails= (ResponseEntity<Object>) rightPriceService.getTaxAssumptionReportExcel(country,deductionId);
 		List<Deduction> taxAssumptionData = 	(List<Deduction>) taxAssumptionDetails.getBody();
      byte []bis = ExcelView.getTaxAssumptionExcelReport(taxAssumptionData,country,deductionId);
      return ResponseEntity.status(200).header("Content-disposition",
      		"attachment; filename="+fileName).contentType(MediaType.parseMediaType("application/octet-stream")).body(bis);
 	}*/
   
	
   // Parthi END
	//Sneha----------
	@ApiOperation(value = "View Miscellaneous Cost", response = City.class)
	@RequestMapping(value = "/viewMiscellaneousCost", method = RequestMethod.POST)
	public ResponseEntity<Object> viewMiscellaneousCost(@RequestBody City city) {
		AppLoger.APPLOGGER.info("inside view City");
		AppLoger.APPLOGGER.info(city.getCityId());
		AppLoger.APPLOGGER.info("coutry id goes here:");
		AppLoger.APPLOGGER.info(city.getCountryId());
		return rightPriceService.viewMiscellaneousCost(city);
	}
		
	/*@ApiOperation(value = "add visa details", response = OffshoreMiscellaneousCost.class)
	@RequestMapping(value = "/addMiscellaneousCost", method = RequestMethod.POST)
	public ResponseEntity<Object> addMiscellaneousCost(@RequestBody OffshoreMiscellaneousCost miscellaneousCostAdd) {
		return rightPriceService.addMiscellaneousCost(miscellaneousCostAdd);
	}*/
			
	@RequestMapping(value = "/getUpdateMiscellaneousCost/{cityId}", method = RequestMethod.GET, headers = "Accept=application/json")
	public ResponseEntity<Object> getUpdateMiscellaneousCost(@PathVariable("cityId") int cityId) {
		AppLoger.APPLOGGER.info("Returning Miscellaneous Cost for cityId : " + cityId );
		return rightPriceService.getUpdateMiscellaneousCost(cityId);
	}
			
	@ApiOperation(value = "update Miscellaneous Cost", response = OffshoreMiscellaneousCost.class)
	@RequestMapping(value = "/updateMiscellaneousCost", method = RequestMethod.POST)
	public ResponseEntity<Object> updateMiscellaneousCost(@RequestBody OffshoreMiscellaneousCost miscellaneousCostUpdate) {
		return rightPriceService.updateMiscellaneousCost(miscellaneousCostUpdate);
	}
	
	//-----------Sneha
	
	@RequestMapping(value = "/getRateCardUtilizationCountry/{rcId}", method = RequestMethod.GET, headers = "Accept=application/json")
	public ResponseEntity<Object> getRateCardUtilizationCountry(@PathVariable("rcId") int rcId) {
		return rightPriceService.getRateCardUtilizationCountry(rcId);
	}
	
	@RequestMapping(value = "/getRateCardUploadCity/{rcId}/{countryId}", method = RequestMethod.GET, headers = "Accept=application/json")
	public ResponseEntity<Object> getRateCardUploadCity(@PathVariable("rcId") int rcId,@PathVariable("countryId") int countryId) {
		return rightPriceService.getRateCardUploadCity(rcId,countryId);
	}

	@RequestMapping(value = "/getRateUtilizationCity/{rcId}/{countryId}", method = RequestMethod.GET, headers = "Accept=application/json")
	public ResponseEntity<Object> getRateUtilizationCity(@PathVariable("rcId") int rcId,@PathVariable("countryId") int countryId) {
		return rightPriceService.getRateUtilizationCity(rcId,countryId);
	}
	
	@ApiOperation(value = "get vertical member data", response = VerticalMemberData.class)
	@RequestMapping(value = "/getVerticalMemberData/{user}", method = RequestMethod.GET,headers="Accept=application/json")
	 public ResponseEntity<Object> getVerticalMemberData(@PathVariable("user")String user){
		
		AppLoger.APPLOGGER.info("inside vertical member controller---------------------------------------------");
		AppLoger.APPLOGGER.info("user"+user);
		return rightPriceService.getVerticalMemberData(user);
	 }
//my dashboard
	@RequestMapping(value = "/getSummaryCountBasedOnVerticalId", method = RequestMethod.POST)
	public ResponseEntity<Object> getSummaryCountBasedOnVerticalId(@RequestBody MyDashboardRC[] rateCardCount){
		AppLoger.APPLOGGER.info("Data received " + rateCardCount.length);
		return rightPriceService.getSummaryCountBasedOnVerticalId(rateCardCount);
	}
/*	@RequestMapping(value = "/getSummaryCountBasedOnVerticalId", method = RequestMethod.POST)
	public ResponseEntity<Object> getSummaryCountBasedOnVerticalId(@RequestBody RateCardDetails[] rateCardCount){
		AppLoger.APPLOGGER.info("Data received " + rateCardCount.length);
		return rightPriceService.getSummaryCountBasedOnVerticalId(rateCardCount);
	}
*/	
	@RequestMapping(value = "/getRateCardData", method = RequestMethod.POST)
	public ResponseEntity<Object> getRateCardData(@RequestBody MyDashboardRC[] rateCardDetails){
		AppLoger.APPLOGGER.info("Data received in controller for getRateCardData " + rateCardDetails.length);
		return rightPriceService.getRateCardData(rateCardDetails);
	}
	
	@RequestMapping(value = "/getDealCountBasedOnVerticalId", method = RequestMethod.POST)
	public ResponseEntity<Object> getDealCountBasedOnVerticalId(@RequestBody MyDashboardDeal[] dealCount) {
		AppLoger.APPLOGGER.info("Data received " + dealCount.length);
		return rightPriceService.getDealCountBasedOnVerticalId(dealCount);
	}
	
	@RequestMapping(value = "/getCustomerBasedDealRecords", method = RequestMethod.POST)
	public ResponseEntity<Object> getCustomerBasedDealRecords(@RequestBody DealCrmStages[] dealCrmStages){
		AppLoger.APPLOGGER.info("Data received " + dealCrmStages.length);
		return rightPriceService.getCustomerBasedDealRecords(dealCrmStages);
	}
	
	@RequestMapping(value = "/getDashboardDealData", method = RequestMethod.POST)
	public ResponseEntity<Object> getDashboardDealData(@RequestBody MyDashboardDeal[] dealData) {
		AppLoger.APPLOGGER.info("Data received " + dealData.length);
		return rightPriceService.getDashboardDealData(dealData);
	}
	
	@ApiOperation(value = "Get getDescName Dropdown dependant on Tax&Assumption", response = SubPractice.class)
	@RequestMapping(value = "/getDescName/{taxnAssmId}", method = RequestMethod.GET, headers = "Accept=application/json")
	public ResponseEntity<Object> getDescName(@PathVariable("taxnAssmId") int taxnAssmId) {
		AppLoger.APPLOGGER.info("getDesc ----"+taxnAssmId);
		return rightPriceService.getDescName(taxnAssmId);
	}
	
	@ApiOperation(value = "insertTaxnAssumtionParam", response = Deduction.class)
	@RequestMapping(value = "/insertTaxnAssumtionParam", method = RequestMethod.POST)
	public ResponseEntity<Object> insertTaxnAssumtionParam(@RequestBody Deduction deduction) {
		AppLoger.APPLOGGER.info("insertTaxnAssumtionParam--------------");
		 return rightPriceService.insertTaxnAssumtionParam(deduction);
	}
	@ApiOperation(value = "updateTaxnAssumtionParam", response = Deduction.class)
	@RequestMapping(value = "/updateTaxnAssumtionParam", method = RequestMethod.POST)
	public ResponseEntity<Object> updateTaxnAssumtionParam(@RequestBody Deduction deduction) {
		
		return rightPriceService.updateTaxnAssumtionParam(deduction);
	}
	@ApiOperation(value = "viewTaxParam", response = Deduction.class)
	@RequestMapping(value = "/viewTaxParam", method = RequestMethod.POST)
	public ResponseEntity<Object> viewTaxParam(@RequestBody Deduction deduction) {
		AppLoger.APPLOGGER.info("viewTaxnAssumtionParam--------------"+deduction.getCountryId());
		return rightPriceService.viewTaxParam(deduction);
	}
	
	@RequestMapping(value = "/getGFTMemberDetails", method = RequestMethod.GET, headers = "Accept=application/json")
	public ResponseEntity<Object> getGFTMemberDetails() {
		AppLoger.APPLOGGER.info("getGFTMemberDetails-----------");
		return rightPriceService.getGFTMemberDetails();
	}
	
	@ApiOperation(value = "Get country, city based on deal version", response = DealLocation.class)
	@RequestMapping(value = "/getCountryCityBasedOnDealVersion/{rpDealVersionId}", method = RequestMethod.GET, headers = "Accept=application/json")
	public ResponseEntity<Object> getCountryCityBasedOnDealVersion(@PathVariable("rpDealVersionId") int rpDealVersionId) {
		return rightPriceService.getCountryCityBasedOnDealVersion(rpDealVersionId);
	}
	
	@ApiOperation(value = "Get tower based on deal version", response = DealTower.class)
	@RequestMapping(value = "/getTowerDetails/{rpDealVersionId}", method = RequestMethod.GET, headers = "Accept=application/json")
	public ResponseEntity<Object> getTowerDetails(@PathVariable("rpDealVersionId") int rpDealVersionId) {
		AppLoger.APPLOGGER.info("getTowerDetails");
		return rightPriceService.getTowerDetails(rpDealVersionId);
	}
	
	@ApiOperation(value = "Get summary data", response = DealFpCalculationDetails.class)
	@RequestMapping(value = "/getSummaryCalculationData/{cityId}/{towerId}", method = RequestMethod.GET, headers = "Accept=application/json")
	public ResponseEntity<Object> getSummaryCalculationData(@PathVariable("cityId") int cityId, @PathVariable("towerId") int towerId) {
		AppLoger.APPLOGGER.info("getSummaryData");
		return rightPriceService.getSummaryCalculationData(cityId, towerId);
	}
	
	@ApiOperation(value = "Get other data")
	@RequestMapping(value = "/getOtherCalculationData/{cityId}/{towerId}", method = RequestMethod.GET, headers = "Accept=application/json")
	public ResponseEntity<Object> getOtherCalculationData(@PathVariable("cityId") int cityId, @PathVariable("towerId") int towerId) {
		AppLoger.APPLOGGER.info("getOtherCalculationData");
		return rightPriceService.getOtherCalculationData(cityId, towerId);
	}
	
	@RequestMapping(value = "/getFpDealStaffingData/{rpDealVersionId}/{cityId}/{towerId}", method = RequestMethod.GET, headers = "Accept=application/json")
	public ResponseEntity<Object> getFpDealStaffingData(@PathVariable("rpDealVersionId") int rpDealVersionId,@PathVariable("cityId") int cityId,@PathVariable("towerId") int towerId) {
		return rightPriceService.getFpDealStaffingData(rpDealVersionId,cityId,towerId);
	}
	
	
	@RequestMapping(value = "/getTMDealStaffingData/{cityId}/{towerId}", method = RequestMethod.GET, headers = "Accept=application/json")
	public ResponseEntity<Object> getTMDealStaffingData(@PathVariable("cityId") int cityId,@PathVariable("towerId") int towerId) {
		return rightPriceService.getTMDealStaffingData(cityId,towerId);
	}
	

	@RequestMapping(value = "/findTotalTransMonth/{towerId}/{rpDealVersionId}", method = RequestMethod.GET, headers = "Accept=application/json")
	public ResponseEntity<Object> findTotalTransMonth(@PathVariable("towerId") int towerId,@PathVariable("rpDealVersionId") int rpDealVersionId) {
		return rightPriceService.findTotalTransMonth(towerId,rpDealVersionId);
	}
	
	@RequestMapping(value = "/getPageTrackerData/{rcId}", method = RequestMethod.GET, headers = "Accept=application/json")
	public ResponseEntity<Object> getPageTrackerData(@PathVariable("rcId") int rcId) {
		return rightPriceService.getPageTrackerData(rcId);
	}
	
	@ApiOperation(value = "View Salary Cost", response = YearwiseBasicSalary.class)
	@RequestMapping(value = "/viewSalary", method = RequestMethod.POST)
	public ResponseEntity<Object> viewSalary(@RequestBody YearwiseBasicSalary salary) {
		System.out.println("controller view salary ");
		return rightPriceService.viewSalary(salary);
	}
	
	@ApiOperation(value = "add salary search", response = BasicSalary.class)
	@RequestMapping(value = "/addSalarySearch", method = RequestMethod.POST)
	public ResponseEntity<Object> addSalarySearch(@RequestBody BasicSalary salary) {
		System.out.println("inside add salary search-------"+salary.getCountryId());
		return rightPriceService.addSalarySearch(salary);
	}
	
	@ApiOperation(value = "add salary", response = BasicSalary.class)
	@RequestMapping(value = "/insertSalary", method = RequestMethod.POST)
	public ResponseEntity<Object> insertSalary(@RequestBody BasicSalary[] salary) {
		System.out.println("inside insertsalary-------------");
		 return rightPriceService.insertSalary(salary);
	}
	
	@RequestMapping(value = "/getPrevDataDeal_Version/{versionId}", method = RequestMethod.GET, headers = "Accept=application/json")
	public ResponseEntity<Object> getPrevDataDeal_Version(@PathVariable("versionId") int versionId) 
	{
		System.out.println("getPrevDataDeal_Version==controller_versionId=  : " + versionId);
		return rightPriceService.getPrevDataDeal_Version(versionId); //, crmDealId, customerId
	}
	@RequestMapping(value = "/getContry_City/{versionId}", method = RequestMethod.GET, headers = "Accept=application/json")
	public ResponseEntity<Object> getContry_City(@PathVariable("versionId") int versionId) 
	{		
		return rightPriceService.getContry_City(versionId); //, crmDealId, customerId
	}
	/*@RequestMapping(value = "/showRateCardTable1/{rpversionId}", method = RequestMethod.GET, headers = "Accept=application/json")
	public ResponseEntity<Object>  showRateCardTable(@PathVariable("rpversionId") int rpversionId)
	{  System.out.println("=1291======CONTROLLER===");
		return rightPriceService.showRateCardTable(rpversionId);
		
	}*/
	
	@ApiOperation(value = "update Salary ", response = BasicSalary.class)
	@RequestMapping(value = "/updateSalary", method = RequestMethod.POST)
	public ResponseEntity<Object> updateSalary(@RequestBody BasicSalary[] salary) {
		return rightPriceService.updateSalary(salary);
	}
	
	@ApiOperation(value = "update salary search", response = BasicSalary.class)
	@RequestMapping(value = "/updateSalarySearch", method = RequestMethod.POST)
	public ResponseEntity<Object> updateSalarySearch(@RequestBody BasicSalary salary) {
		return rightPriceService.updateSalarySearch(salary);
	}
	
	@ApiOperation(value = "View Master Rate", response = MasterRate.class)
	@RequestMapping(value = "/onViewMasterRate", method = RequestMethod.POST)
	public ResponseEntity<Object> onViewMasterRate(@RequestBody MasterRate masterRate) {
		return rightPriceService.onViewMasterRate(masterRate);
	}
	
	// upload Master Country
		@RequestMapping(value = "/uploadCountry", method = RequestMethod.POST)
	    public ResponseEntity<Object> uploadCountry(@RequestParam(value = "file", required = false) MultipartFile  file, 
	                                                     @RequestParam(value = "name", required = false) String fileName,
	                                                     @FormParam("file") InputStream uploadedInputStream){
	           AppLoger.APPLOGGER.info("@Controller Requested method : /uploadCountry");
	           		try {
	           			ArrayList<Country> country = null;
	           			int errorCount = 1;
			        	 InputStream stream = file.getInputStream();
						 XSSFWorkbook workbook = new XSSFWorkbook(stream);
						 XSSFSheet sheet = workbook.getSheetAt(0);
						 int rowCount = sheet.getLastRowNum();
						 country = new ArrayList<Country>();
						 List<Object> errorList = new ArrayList<>();
						 HashMap<Object,Object> errorMap = new HashMap<Object,Object>();
						 String errorCause =  "";
						 boolean isErrorInTemplate=false;
						 if(rowCount > 0) {
						 for(int i=1;i<=rowCount;i++) {
						 Country countryBean = new Country();
						 XSSFRow row = sheet.getRow(i);
						 int columnCount = sheet.getRow(i).getLastCellNum();
						 System.out.println("Column Count is............"+columnCount);
						 
						 if(row.getCell(0) != null) { 
								 if (row.getCell(0) != null && row.getCell(0).getCellType() == HSSFCell.CELL_TYPE_STRING) {
									String countryName = row.getCell(0).getStringCellValue();
									AppLoger.APPLOGGER.info("0 Cell Value : \t"+countryName);
									countryBean.setCountryName(countryName);
									System.out.println("Country Name after setting into bean is........ "+countryBean.getCountryName());
								}
								if (row.getCell(1) != null && row.getCell(1).getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
									Integer currencyId = (int) row.getCell(1).getNumericCellValue();
									AppLoger.APPLOGGER.info("1 Cell Value : \t"+currencyId);
									countryBean.setCurrencyId(Integer.toString(currencyId));
									System.out.println("Currency Id after setting into bean is........ "+countryBean.getCurrencyId());
								}
								if (row.getCell(2) != null && row.getCell(2).getCellType() == HSSFCell.CELL_TYPE_STRING) {
									String currencyCode = row.getCell(2).getStringCellValue();
									AppLoger.APPLOGGER.info("2 Cell Value : \t"+currencyCode);
									countryBean.setCurrencyCode(currencyCode);
									System.out.println("Currency Id after setting into bean is........ "+countryBean.getCurrencyCode());
								}
								
								if (row.getCell(3) != null && row.getCell(3).getCellType() == HSSFCell.CELL_TYPE_STRING) {
									String countryShortName = row.getCell(3).getStringCellValue();
									AppLoger.APPLOGGER.info("3 Cell Value : \t"+countryShortName);
									countryBean.setCountryShortName(countryShortName);
									System.out.println("Currency Id after setting into bean is........ "+countryBean.getCountryShortName());
								}
								
								if (row.getCell(4) != null && row.getCell(4).getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
									Double exchangeRate = row.getCell(4).getNumericCellValue();
									AppLoger.APPLOGGER.info("4 Cell Value : \t"+exchangeRate);
									countryBean.setExchangeRate(exchangeRate);
									System.out.println("Currency Id after setting into bean is........ "+countryBean.getExchangeRate());
								}
								if (row.getCell(5) != null && row.getCell(5).getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
									Double syntelFacilityCost = row.getCell(5).getNumericCellValue();
									AppLoger.APPLOGGER.info("5 Cell Value : \t"+syntelFacilityCost);
									countryBean.setSyntelFacilityCost(syntelFacilityCost);
									System.out.println("Currency Id after setting into bean is........ "+countryBean.getSyntelFacilityCost());
								}
								
								if (row.getCell(6) != null && row.getCell(6).getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
									int isOffshoreSite = (int) row.getCell(6).getNumericCellValue();
									AppLoger.APPLOGGER.info("6 Cell Value : \t"+isOffshoreSite);
									countryBean.setIsOffshoreSite(isOffshoreSite);
									System.out.println("Currency Id after setting into bean is........ "+countryBean.getIsOffshoreSite());
								}
								country.add(countryBean);	
							 }
						 	
						 	else if(row.getCell(0) == null){
								 System.out.println("error in CountryName");
								 errorCause = FileUploadErrors.nullEntry("Country Name ",errorCount);
								 System.out.println("Error message for Country Name......"+errorCause);
								 errorMap.put("ErrorCase10", errorCause);
								 isErrorInTemplate = true;
						 	}
						 }
						 errorCount++;
						 AppLoger.APPLOGGER.info("Country Information Size "+country.size());
					  }
						 if(!country.isEmpty()) {
								System.out.println(" In isErrorInTemplate The Error Message on zero Entry is............. "+ errorCause);
								errorList.add(errorMap.values());
								 return rightPriceService.uploadCountry(country,errorList);	 
						 }
					} 
	           catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	           return ResponseEntity.status(205)
	                   .contentType(MediaType.TEXT_PLAIN)
	                   .body("Currently We are facing technical issues, please try again later.");
	    }
		
		// upload Master City
		@RequestMapping(value = "/uploadCity", method = RequestMethod.POST)
	    public ResponseEntity<Object> uploadCity(@RequestParam(value = "file", required = false) MultipartFile  file, 
	         @RequestParam(value = "name", required = false) String fileName,
	         @FormParam("file") InputStream uploadedInputStream)
		{
			AppLoger.APPLOGGER.info("@Controller Requested method : /uploadCity");
			try 
			{
				ArrayList<City_STG> arrCitySTG = new ArrayList<City_STG>();
				int errorCount = 0;
				InputStream stream = file.getInputStream();
				XSSFWorkbook workbook = new XSSFWorkbook(stream);
				XSSFSheet sheet = workbook.getSheetAt(0);
				int rowCount = sheet.getLastRowNum();
				String errorCause = "";
				ArrayList<String> strErrorElement = new ArrayList<String>();
				List<Object> errorList = new ArrayList<>();
				ArrayList<String> arryErrorList = new ArrayList<String>();
				boolean isErrorInTemplate=false;
				if(rowCount > 0) 
				{
					for(int i=1;i<=rowCount;i++) 
					{
						City_STG citySTGBean = new City_STG();
						XSSFRow row = sheet.getRow(i);
						int columnCount = sheet.getRow(i).getLastCellNum();
						System.out.println("Column Count is............"+columnCount);
						errorCause = "";
						strErrorElement = new ArrayList<String>();
						
						 
						if ((row.getCell(0) != null && row.getCell(0).getStringCellValue().isEmpty() == false)
								&& (row.getCell(1) != null && row.getCell(1).getStringCellValue().isEmpty() == false)
								&& (row.getCell(2) != null && row.getCell(2).getStringCellValue().isEmpty() == false)
								&& (row.getCell(3) != null && row.getCell(3).getNumericCellValue() >= 0)) 
						{
							AppLoger.APPLOGGER.info("0 Cell Value : \t"+row.getCell(0).getStringCellValue());
							citySTGBean.setCityName(row.getCell(0).getStringCellValue());
							System.out.println("City Name after setting into bean is........ "+citySTGBean.getCityName());
						
							AppLoger.APPLOGGER.info("1 Cell Value : \t"+row.getCell(1).getStringCellValue());
							citySTGBean.setCategorization(row.getCell(1).getStringCellValue());
							System.out.println("Categorization after setting into bean is........ "+citySTGBean.getCategorization());
						
							AppLoger.APPLOGGER.info("2 Cell Value : \t"+ row.getCell(2).getStringCellValue());
							citySTGBean.setCountryName(row.getCell(2).getStringCellValue());								
							System.out.println("Country Id after setting into bean is........ "+citySTGBean.getCountryName());
						
							AppLoger.APPLOGGER.info("3 Cell Value : \t"+row.getCell(3).getNumericCellValue());
							citySTGBean.setColaValue(row.getCell(3).getNumericCellValue());
							System.out.println("Cola Value after setting into bean is........ "+citySTGBean.getColaValue());
							arrCitySTG.add(citySTGBean);
						}
						else
						{
							if (row.getCell(0) == null || row.getCell(0).getStringCellValue().isEmpty()) 
							{
								strErrorElement.add("City");
								isErrorInTemplate = true;
								errorCount++;
							}
							if (row.getCell(1) == null || row.getCell(1).getStringCellValue().isEmpty()) 
							{
								strErrorElement.add("Categorization");
								isErrorInTemplate = true;
								errorCount++;
							}
							if (row.getCell(2) == null || row.getCell(2).getStringCellValue().isEmpty()) 
							{
								strErrorElement.add("Country");
								isErrorInTemplate = true;
								errorCount++;
							}
							if (row.getCell(3) == null || !(row.getCell(3).getNumericCellValue() > 0)) 
							{
								strErrorElement.add("Cola Value");
								isErrorInTemplate = true;
								errorCount++;
							}
							for (int j=0;j<strErrorElement.size();j++) {										
								errorCause += strErrorElement.get(j);
								if(j < strErrorElement.size()-1)
								{
									errorCause += ", ";
								}
							}
							arryErrorList.add(FileUploadErrors.nullEntry(errorCause, i+1) + "<br>");
						}					
					}// main for loop ends
					if (errorCount > 0)
					{
						System.out.println("Errorlist Value for first empty cell ::::::::" + arryErrorList);
						workbook.close();
						return ResponseEntity.accepted().body(arryErrorList);
					}
				}
				AppLoger.APPLOGGER.info(" Now calling service layer to upload City............. ");
				workbook.close();
				return rightPriceService.uploadCity(arrCitySTG);
			} 
			catch (IOException e) 
			{
				e.printStackTrace();					
				return ResponseEntity.status(205).contentType(MediaType.TEXT_PLAIN).body("Currently We are facing technical issues, please try again later.");
			}			
    	}
		
				
		@ApiOperation(value = "onMasterRateSearch", response = MasterRate.class)
		@RequestMapping(value = "/onMasterRateSearch", method = RequestMethod.POST)
		public ResponseEntity<Object> onMasterRateSearch(@RequestBody MasterRate masterRate) {
			AppLoger.APPLOGGER.info("on search click :CCP --> "+masterRate.getCountryId() + " -- " + masterRate.getTransactionYear() +"--"+masterRate.getMasterRoleId());
			return rightPriceService.onMasterRateSearch(masterRate);
		}
		
		@ApiOperation(value = "update Master Rate", response = MasterRate.class)
		@RequestMapping(value = "/onUpdateMasterRateSearch", method = RequestMethod.POST)
		public ResponseEntity<Object> onUpdateMasterRateSearch(@RequestBody MasterRate masterRate) {
			AppLoger.APPLOGGER.info("on update master rate --> "+masterRate.getCountryId() + " -- " + masterRate.getTransactionYear() +"--"+masterRate.getMasterRoleId());
			return rightPriceService.onUpdateMasterRateSearch(masterRate);
		}
		
		@ApiOperation(value = "Update/Save Master Rate", response = MasterRate.class)
		@RequestMapping(value = "/onAddMasterRate", method = RequestMethod.POST)
		public ResponseEntity<Object> onAddMasterRate(@RequestBody MasterRate masterRate) {
			AppLoger.APPLOGGER.info("on update master rate --> "+masterRate.getCountryId() + " -- " + masterRate.getTransactionYear() +"--"+masterRate.getMasterRoleId());
			AppLoger.APPLOGGER.info(+masterRate.getRateLow() + " -- " + masterRate.getRateHigh() +"--"+masterRate.getRateOffshore()+"--"+masterRate.getRateVHigh());
			return rightPriceService.onAddMasterRate(masterRate);
		}
		
		@ApiOperation(value = "update Common Cost Parameter", response = MasterRate.class)
		@RequestMapping(value = "/onUpdateMasterRate", method = RequestMethod.POST)
		public ResponseEntity<Object> onUpdateMasterRate(@RequestBody MasterRate masterRate) {
			AppLoger.APPLOGGER.info("on update master rate --> "+masterRate.getCountryId() + " -- " + masterRate.getTransactionYear() +"--"+masterRate.getMasterRoleId());
			AppLoger.APPLOGGER.info(+masterRate.getRateLow() + " -- " + masterRate.getRateHigh() +"--"+masterRate.getRateOffshore()+"--"+masterRate.getRateVHigh());
			return rightPriceService.onUpdateMasterRate(masterRate);
		}
		
		// Getting Onsite Facilty Cost
		@RequestMapping(value = "/getOnsiteFacilityCost/{countryId}/{cityId}/{towerId}", method = RequestMethod.GET, headers = "Accept=application/json")
		public ResponseEntity<Object> getOnsiteFaciltyCost(@PathVariable("countryId") int countryId,@PathVariable("cityId") int cityId, @PathVariable("towerId") int towerId) {
			return rightPriceService.getOnsiteFacilityCost(countryId,cityId,towerId);
		}
		
		// Getting Onsite Facilty Cost
		@RequestMapping(value = "/getRelocationDetails/{countryId}/{cityId}/{towerId}", method = RequestMethod.GET, headers = "Accept=application/json")
		public ResponseEntity<Object> getRelocationCostDetails(@PathVariable("countryId") int countryId,@PathVariable("cityId") int cityId, @PathVariable("towerId") int towerId) {
			return rightPriceService.getRelocationCostDetails(countryId,cityId,towerId);
		}

		// Getting Shift Working Data
		@RequestMapping(value = "/getShiftWorkingDetails/{countryId}/{cityId}/{towerId}", method = RequestMethod.GET, headers = "Accept=application/json")
		public ResponseEntity<Object> getShiftWorkingDetails(@PathVariable("countryId") int countryId,@PathVariable("cityId") int cityId, @PathVariable("towerId") int towerId) {
			return rightPriceService.getShiftWorkingDetails(countryId,cityId,towerId);
		}
		
		// Inserting data for RP Deal Onsite costs
		@ApiOperation(value = "update Common Cost Parameter", response = RPDealHardwareSoftwareCost.class)
		@RequestMapping(value = "/saveOnlineCostData", method = RequestMethod.POST)
		public ResponseEntity<Object> saveOnsiteCostData(@RequestBody  FPDCRCAndProjectDetails  hardWareCostData) {
			AppLoger.APPLOGGER.info(hardWareCostData.toString());
			AppLoger.APPLOGGER.info("Data received for save.................. "+hardWareCostData.getOnlineCostDetails().size());
			AppLoger.APPLOGGER.info("Online Cost Array is ........ "+hardWareCostData.getOnlineCostDetails().get(0));
			AppLoger.APPLOGGER.info("Data received for save.................. "+hardWareCostData.getTravelRelocationCostDetails().size());
			return rightPriceService.saveOnsiteCostData(hardWareCostData);
		}
		
		// upload Master Salary
		@RequestMapping(value = "/uploadMasterSal", method = RequestMethod.POST)
		public ResponseEntity<Object> uploadMasterSal(@RequestParam(value = "file", required = false) MultipartFile file,
				@RequestParam(value = "name", required = false) String fileName,
				@FormParam("file") InputStream uploadedInputStream) {
			AppLoger.APPLOGGER.info("@Controller Requested method : /uploadMasterSal");
			try {

				DecimalFormat numberFormat = new DecimalFormat("0.0000");
				ArrayList<BasicSalarySTG> arrLiSal = new ArrayList<BasicSalarySTG>();
				int errorCount = 0;
				InputStream stream = file.getInputStream();
				XSSFWorkbook workbook = new XSSFWorkbook(stream);
				XSSFSheet sheet = workbook.getSheetAt(0);
				int rowCount = sheet.getLastRowNum();
				ArrayList<String> arryErrorList = new ArrayList<String>();
				//List<Object> errorList = new ArrayList<>();
				//HashMap<Object, Object> errorMap = new HashMap<Object, Object>();
				String errorCause = "";
				boolean isErrorInTemplate = false;
				//int columnCounter = 0;
				//int RowCounter = 0;
				ArrayList<String> arrPractice = new ArrayList<String>(); 
				Integer intEmpDesgID = 0;
				Integer intCountryId = 0;
				if (rowCount > 0) {
					for (int i = 0; i <= rowCount; i++) 
					{
						BasicSalarySTG salBean = new BasicSalarySTG();
						// City cityBean = new City();
						XSSFRow row = sheet.getRow(i);
						int columnCount = sheet.getRow(i).getLastCellNum();
						System.out.println("Column Count is............" + columnCount);
						
						if(i == 0)
						{
							for (int k = 4; k < columnCount; k++) 
							{
								arrPractice.add(row.getCell(k).getStringCellValue());							
							}
						}
						else
						{
							//Check if Country and Year is not empty 
							if ((row.getCell(2) != null || row.getCell(2).getStringCellValue() != "")
									&& (row.getCell(3) != null || row.getCell(3).getNumericCellValue() > 0)) 
							{
								
								intEmpDesgID = getEmpDesgIdFromEmpDesgDesc(row.getCell(0).getStringCellValue().trim());
								intCountryId = getCountryIdFromCountryName(row.getCell(2).getStringCellValue().trim());
								
								//Traverse through each Practice
								for (int j = 4; j < columnCount; j++) 
								{
									//Check if salary is not empty w.r.t. practice
									if (row.getCell(j) != null && row.getCell(j).getNumericCellValue() > 0) 
									{
										salBean.setEmpDesgId(intEmpDesgID);
										//salBean.setSynRoleDesc(row.getCell(0).getStringCellValue());
										salBean.setCountryId(intCountryId);
										salBean.setYear((int) row.getCell(3).getNumericCellValue());
										salBean.setOnsite_SOG_Salary_Percent(0);
										salBean.setOffshore_SOG_Salary_Percent(0);
										//salBean.setCountryName(row.getCell(2).getStringCellValue());
										salBean.setPracticeId(getPracticeIdFromPracticeDesc(arrPractice.get(j-4)));
										//salBean.setPracticeDesc(arrPractice.get(j-4));
										salBean.setAnnualSalary(new BigDecimal(row.getCell(j).getNumericCellValue())
												.setScale(4, RoundingMode.HALF_UP).doubleValue());
										arrLiSal.add(salBean);
										salBean = new BasicSalarySTG();
									}
								}
							} else 
							{
								// To add error message if Country or and Year is empty
								if (row.getCell(2) == null || row.getCell(2).getStringCellValue() == "") 
								{
									errorCause = FileUploadErrors.nullEntry("Country Name ", i + 1);
									isErrorInTemplate = true;
									arryErrorList.add(errorCause + "<br>");
									errorCount++;
								}
								if (row.getCell(3) == null || row.getCell(3).getNumericCellValue() < 1) 
								{
									errorCause = FileUploadErrors.nullEntry("Year ", i + 1);
									isErrorInTemplate = true;
									arryErrorList.add(errorCause + "<br>");
									errorCount++;
								}
							}
						}
					} // main for loop ends
					if (errorCount > 0)
					{
						System.out.println("Errorlist Value for first empty cell ::::::::" + arryErrorList);
						return ResponseEntity.accepted().body(arryErrorList);
					}
				}
				if (!arrLiSal.isEmpty() || isErrorInTemplate == false) {
					AppLoger.APPLOGGER.info(" Now calling service layer to upload Salary............. ");
					 return rightPriceService.uploadSalary(arrLiSal);
				}
				else
				{
					return ResponseEntity.status(205).contentType(MediaType.TEXT_PLAIN)
							.body("Currently We are facing technical issues, please try again later.");
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();					
				return ResponseEntity.status(205).contentType(MediaType.TEXT_PLAIN).body("Currently We are facing technical issues, please try again later.");
			}
			
		}
		
		private Integer getEmpDesgIdFromEmpDesgDesc(String strDescription){
			return rightPriceService.getEmpDesgIdFromEmpDesgDesc(strDescription);
		}
		
		private Integer getCountryIdFromCountryName(String strCountryName){
			return rightPriceService.getCountryIdFromCountryName(strCountryName);
		}
		
		private Integer getPracticeIdFromPracticeDesc(String strDesc){
			return rightPriceService.getPracticeIdFromPracticeDesc(strDesc);
		}
		
		// upload Master Salary
		@RequestMapping(value = "/uploadMannualRC", method = RequestMethod.POST)
		public ResponseEntity<Object> uploadMannualRC(@RequestParam(value = "file", required = false) MultipartFile file,
				@RequestParam(value = "name", required = false) String fileName,
				@RequestParam(value = "rcId", required = false) int rcId,
				@FormParam("file") InputStream uploadedInputStream) {
			AppLoger.APPLOGGER.info("@Controller Requested method : /uploadMannualRC");
			try {

				ArrayList<RateCardRolesSTG> arrLiRcRolesSTG = new ArrayList<RateCardRolesSTG>();
				int errorCount = 0;
				InputStream stream = file.getInputStream();
				XSSFWorkbook workbook = new XSSFWorkbook(stream);
				XSSFSheet sheet = workbook.getSheetAt(0);
				ArrayList<String> arryErrorList = new ArrayList<String>();
				String errorCause = "";
				ArrayList<String> strErrorElement = new ArrayList<String>();
				boolean isErrorInTemplate = false;
				if (sheet.getLastRowNum() > 0) 
				{
					for (int i = 1; i <= sheet.getLastRowNum(); i++) 
					{
						errorCause = "";
						strErrorElement = new ArrayList<String>();
						RateCardRolesSTG rcRolesBean = new RateCardRolesSTG();
						XSSFRow row = sheet.getRow(i);
						int columnCount = sheet.getRow(i).getLastCellNum();
						System.out.println("Column Count is............" + columnCount);
						
						//Check if Mandatory fields are not empty 
						if ((row.getCell(1).getStringCellValue().isEmpty() == false)
							&& (row.getCell(2).getStringCellValue().isEmpty() == false)
							&& (row.getCell(3).getStringCellValue().isEmpty() == false)
							&& (row.getCell(4).getStringCellValue().isEmpty() == false)
							&& (row.getCell(5).getStringCellValue().isEmpty() == false)
							&& (row.getCell(6).getStringCellValue().isEmpty() == false)
							&& (row.getCell(13).getStringCellValue().isEmpty() == false)
							&& (row.getCell(14).getNumericCellValue() > 0)
							&& (row.getCell(16).getNumericCellValue() > 0)
							&& (row.getCell(17).getNumericCellValue() > 0)
							&& (row.getCell(18).getNumericCellValue() > 0)
							&& (row.getCell(19).getNumericCellValue() > 0))
							
						{
							rcRolesBean.setCountry(row.getCell(1).getStringCellValue());
							rcRolesBean.setCity(row.getCell(2).getStringCellValue());
							rcRolesBean.setPractice(row.getCell(3).getStringCellValue());
							rcRolesBean.setSubPractice(row.getCell(4).getStringCellValue());
							rcRolesBean.setRole(row.getCell(3).getStringCellValue() +"/"+row.getCell(4).getStringCellValue()+"/"+row.getCell(5).getStringCellValue()+"/"+row.getCell(6).getStringCellValue());
							if(!CheckMasterRole(rcRolesBean.getRole()))
							{
								strErrorElement.add(rcRolesBean.getRole());
								isErrorInTemplate = true;
								errorCount++;
								
								for (int j=0;j<strErrorElement.size();j++) {										
									errorCause += strErrorElement.get(j);
									if(j < strErrorElement.size()-1)
									{
										errorCause += ",";
									}
								}
								arryErrorList.add(FileUploadErrors.notFound(errorCause, i) + "<br>");
							}
							rcRolesBean.setProficiencyDesc(row.getCell(6).getStringCellValue());
							String strBandGrade = row.getCell(7).getStringCellValue();
							String [] parts = strBandGrade.split(" ");
							rcRolesBean.setBand(parts[0]);
							rcRolesBean.setGrade(parts[1]);
							rcRolesBean.setxOSkills(row.getCell(8).getStringCellValue());
							rcRolesBean.setxOSkillsElement(row.getCell(9).getStringCellValue());
							if(row.getCell(10).getStringCellValue() != "")
								rcRolesBean.setxOKnowledge(row.getCell(10).getStringCellValue());
							rcRolesBean.setxOBandGrade(row.getCell(11).getStringCellValue());
							rcRolesBean.setxOProficiency(row.getCell(12).getStringCellValue());
							rcRolesBean.setClientRole(row.getCell(13).getStringCellValue());
							rcRolesBean.setLocalPercentage((int) row.getCell(14).getNumericCellValue());
							rcRolesBean.setDeputedPercentage((int) row.getCell(15).getNumericCellValue());
							rcRolesBean.setOnsiteCustomerRate(new BigDecimal(row.getCell(16).getNumericCellValue())
							.setScale(2, RoundingMode.HALF_UP).doubleValue());
							rcRolesBean.setOnsiteUsage((int) row.getCell(17).getNumericCellValue());
							rcRolesBean.setOffshoreCustomerRate(new BigDecimal(row.getCell(18).getNumericCellValue())
									.setScale(2, RoundingMode.HALF_UP).doubleValue());
							rcRolesBean.setOffshoreUsage((int) row.getCell(19).getNumericCellValue());
							if(row.getCell(20).getNumericCellValue() > 0)
								rcRolesBean.setTransactionYear((int) row.getCell(20).getNumericCellValue());
							/*if(row.getCell(21).getNumericCellValue() > 0)
								rcRolesBean.setYear3Usage((int) row.getCell(21).getNumericCellValue());
							if(row.getCell(22).getNumericCellValue() > 0)
								rcRolesBean.setYear4Usage((int) row.getCell(22).getNumericCellValue());
							if(row.getCell(23).getNumericCellValue() > 0)
								rcRolesBean.setYear5Usage((int) row.getCell(23).getNumericCellValue());*/
							if(rcId > 0)
								rcRolesBean.setRcId(rcId);
							arrLiRcRolesSTG.add(rcRolesBean);
							
						}	
						else 
						{
							// To add error message if mandatory field(s) are empty
							if (row.getCell(1).getStringCellValue().isEmpty()) 
							{
								strErrorElement.add("Country");
								isErrorInTemplate = true;
								errorCount++;
							}
							if (row.getCell(2).getStringCellValue().isEmpty()) 
							{
								strErrorElement.add("City");
								isErrorInTemplate = true;
								errorCount++;
							}
							if (row.getCell(3).getStringCellValue().isEmpty()) 
							{
								strErrorElement.add("Practice");
								isErrorInTemplate = true;
								errorCount++;
							}
							if (row.getCell(4).getStringCellValue().isEmpty()) 
							{
								strErrorElement.add("Sub - Practice");
								isErrorInTemplate = true;
								errorCount++;
							}
							if (row.getCell(5).getStringCellValue().isEmpty()) 
							{
								strErrorElement.add("Role");
								isErrorInTemplate = true;
								errorCount++;
							}
							if (row.getCell(6).getStringCellValue().isEmpty()) 
							{
								strErrorElement.add("Proficiency Level");
								isErrorInTemplate = true;
								errorCount++;
							}
							
							for (int j=0;j<strErrorElement.size();j++) {										
								errorCause += strErrorElement.get(j);
								if(j < strErrorElement.size()-1)
								{
									errorCause += ",";
								}
							}
							
							arryErrorList.add(FileUploadErrors.nullEntry(errorCause, i) + "<br>");
						}
						
					} // main for loop ends
					if (errorCount > 0)
					{
						System.out.println("Errorlist Value for first empty cell ::::::::" + arryErrorList);
						workbook.close();
						return ResponseEntity.accepted().body(arryErrorList);
					}
				}
				if (!arrLiRcRolesSTG.isEmpty() || isErrorInTemplate == false) {
					AppLoger.APPLOGGER.info(" Now calling service layer to upload MannualRC............. ");
					workbook.close();
					return rightPriceService.uploadMannualRC(arrLiRcRolesSTG);
				}
				else
				{
					workbook.close();
					return ResponseEntity.status(205).contentType(MediaType.TEXT_PLAIN)
							.body("Currently We are facing technical issues, please try again later.");
				}
				 
			} catch (Exception e) {
				e.printStackTrace();					
				return ResponseEntity.status(205).contentType(MediaType.TEXT_PLAIN).body("Currently We are facing technical issues, please try again later.");
			}
			
		}

		private boolean CheckMasterRole(String role) {
			return rightPriceService.CheckMasterRole(role);
		}
		
		@ApiOperation(value = "Access Control Mapping", response = AccessControl.class)
		@RequestMapping(value = "/addRPAccessControlData", method = RequestMethod.POST)
		public ResponseEntity<Object> addRPAccessControlData(@RequestBody FPDCRCAndProjectDetails accessControl){
			System.out.println("rpAccessControls......."+accessControl.getRpAccessControls().size());
			return rightPriceService.addRPAccessControlData(accessControl);
		}
		
		
		@RequestMapping(value = "/uploadMasterAllowance", method = RequestMethod.POST)
		public ResponseEntity<Object> uploadMasterAllowance(@RequestParam(value = "file", required = false) MultipartFile file,
			@RequestParam(value = "name", required = false) String fileName,			
			@FormParam("file") InputStream uploadedInputStream) {
			AppLoger.APPLOGGER.info("@Controller Requested method : /uploadMasterAllowance");
			try {

				ArrayList<BasicAllowanceSTG> arrLiBasicAllowanceSTG = new ArrayList<BasicAllowanceSTG>();
				int errorCount = 0;
				InputStream stream = file.getInputStream();
				XSSFWorkbook workbook = new XSSFWorkbook(stream);
				XSSFSheet sheet = workbook.getSheetAt(0);
				ArrayList<String> arryErrorList = new ArrayList<String>();
				String errorCause = "";
				ArrayList<String> strErrorElement = new ArrayList<String>();
				boolean isErrorInTemplate = false;
				if (sheet.getLastRowNum() > 0) 
				{
					for (int i = 1; i <= sheet.getLastRowNum(); i++) 
					{
						errorCause = "";
						strErrorElement = new ArrayList<String>();
						BasicAllowanceSTG basicAllowanceSTGBean = new BasicAllowanceSTG();
						XSSFRow row = sheet.getRow(i);
						int columnCount = sheet.getRow(i).getLastCellNum();
						System.out.println("Column Count is............" + columnCount);
						
						//Check if Mandatory fields are not empty 
						if ((row.getCell(1) != null && row.getCell(1).getStringCellValue().isEmpty() == false)
							&& (row.getCell(2) != null && row.getCell(2).getCellType() != HSSFCell.CELL_TYPE_BLANK  && row.getCell(2).getNumericCellValue() >= 0)
							&& (row.getCell(3) != null && row.getCell(3).getCellType() != HSSFCell.CELL_TYPE_BLANK  && row.getCell(3).getNumericCellValue() >= 0)
							&& (row.getCell(4) != null && row.getCell(4).getCellType() != HSSFCell.CELL_TYPE_BLANK  && row.getCell(4).getNumericCellValue() >= 0)
							&& (row.getCell(5) != null && row.getCell(5).getCellType() != HSSFCell.CELL_TYPE_BLANK  && row.getCell(5).getNumericCellValue() >= 0)
							&& (row.getCell(6) != null && row.getCell(6).getStringCellValue().isEmpty() == false)
							&& (row.getCell(7) != null && row.getCell(2).getCellType() != HSSFCell.CELL_TYPE_BLANK  && row.getCell(7).getNumericCellValue() > 0))
							
						{
							basicAllowanceSTGBean.setSynRoleDesc(row.getCell(0).getStringCellValue());
							basicAllowanceSTGBean.setCountryName(row.getCell(1).getStringCellValue());
							basicAllowanceSTGBean.setLowallowance(String.valueOf((new BigDecimal(row.getCell(2).getNumericCellValue())
									.setScale(2, RoundingMode.HALF_UP).doubleValue())));
							basicAllowanceSTGBean.setMediumallowance(String.valueOf((new BigDecimal(row.getCell(3).getNumericCellValue())
									.setScale(2, RoundingMode.HALF_UP).doubleValue())));
							basicAllowanceSTGBean.setHighallowance(String.valueOf((new BigDecimal(row.getCell(4).getNumericCellValue())
									.setScale(2, RoundingMode.HALF_UP).doubleValue())));
							basicAllowanceSTGBean.setVeryhighallowance(String.valueOf((new BigDecimal(row.getCell(5).getNumericCellValue())
									.setScale(2, RoundingMode.HALF_UP).doubleValue())));
							basicAllowanceSTGBean.setVisaType(row.getCell(6).getStringCellValue());
							basicAllowanceSTGBean.setYear((int)row.getCell(7).getNumericCellValue());
							arrLiBasicAllowanceSTG.add(basicAllowanceSTGBean);
						}	
						else 
						{
							// To add error message if mandatory field(s) are empty
							if (row.getCell(1) == null || row.getCell(1).getStringCellValue().isEmpty()) 
							{
								strErrorElement.add("Country");
								isErrorInTemplate = true;
								errorCount++;
							}
							if (row.getCell(2) == null || row.getCell(2).getCellType() == HSSFCell.CELL_TYPE_BLANK || (row.getCell(2).getNumericCellValue() < 0)) 
							{
								strErrorElement.add("Annual_Allowance_Low");
								isErrorInTemplate = true;
								errorCount++;
							}							
							if (row.getCell(3) == null || row.getCell(3).getCellType() == HSSFCell.CELL_TYPE_BLANK || (row.getCell(3).getNumericCellValue() < 0)) 
							{
								strErrorElement.add("Annual_Allowance_Medium");
								isErrorInTemplate = true;
								errorCount++;
							}
							if (row.getCell(4) == null || row.getCell(4).getCellType() == HSSFCell.CELL_TYPE_BLANK || (row.getCell(4).getNumericCellValue() < 0)) 
							{
								strErrorElement.add("Annual_Allowance_High");
								isErrorInTemplate = true;
								errorCount++;
							}
							if (row.getCell(5) == null || row.getCell(5).getCellType() == HSSFCell.CELL_TYPE_BLANK || (row.getCell(5).getNumericCellValue() < 0)) 
							{
								strErrorElement.add("Annual_Allowance_Very_High");
								isErrorInTemplate = true;
								errorCount++;
							}
							if (row.getCell(6) == null || row.getCell(6).getStringCellValue().isEmpty()) 
							{
								strErrorElement.add("Visa_Type");
								isErrorInTemplate = true;
								errorCount++;
							}
							if (row.getCell(7) == null || row.getCell(7).getCellType() == HSSFCell.CELL_TYPE_BLANK || (row.getCell(7).getNumericCellValue() <= 0)) 
							{
								strErrorElement.add("Year");
								isErrorInTemplate = true;
								errorCount++;
							}
							
							
							for (int j=0;j<strErrorElement.size();j++) {										
								errorCause += strErrorElement.get(j);
								if(j < strErrorElement.size()-1)
								{
									errorCause += ", ";
								}
							}
							
							arryErrorList.add(FileUploadErrors.nullEntry(errorCause, i+1) + "<br>");
						}
						
					} // main for loop ends
					if (errorCount > 0)
					{
						System.out.println("Errorlist Value for first empty cell ::::::::" + arryErrorList);
						workbook.close();
						return ResponseEntity.accepted().body(arryErrorList);
					}
				}
				if (!arrLiBasicAllowanceSTG.isEmpty() || isErrorInTemplate == false) {
					AppLoger.APPLOGGER.info(" Now calling service layer to upload MannualRC............. ");
					workbook.close();
					return rightPriceService.uploadMasterAllowance(arrLiBasicAllowanceSTG);
				}
				else
				{
					workbook.close();
					return ResponseEntity.status(205).contentType(MediaType.TEXT_PLAIN)
							.body("Currently We are facing technical issues, please try again later.");
				}
				
			} catch (Exception e) {
				e.printStackTrace();					
				return ResponseEntity.status(205).contentType(MediaType.TEXT_PLAIN).body("Currently We are facing technical issues, please try again later.");
			}	
		}

		@ApiOperation(value = "Add Designation", response = ContractualTerms.class)
		@RequestMapping(value = "/insertContractorRole", method = RequestMethod.POST)
		public ResponseEntity<Object> insertContractorRole(@RequestBody FPDCRCAndProjectDetails contractorRole) {
			return rightPriceService.insertContractorRole(contractorRole);
		}
		@ApiOperation(value = "get the Contractor details based on cityId and rateCardId", response = Deal.class)
		@RequestMapping(value = "/getContractor/{cityId}/{rateCardId}", method = RequestMethod.GET)
		 public ResponseEntity<Object> getContractorDetails(@PathVariable("cityId") int cityId,@PathVariable("rateCardId") int rateCardId){
			return rightPriceService.getContractorDetails(cityId,rateCardId);
	   }
		
		@RequestMapping(value = "/uploadMasterRPRate", method = RequestMethod.POST)
		public ResponseEntity<Object> uploadMasterRPRate(@RequestParam(value = "file", required = false) MultipartFile file,
			@RequestParam(value = "name", required = false) String fileName,			
			@FormParam("file") InputStream uploadedInputStream) {
			AppLoger.APPLOGGER.info("@Controller Requested method : /uploadMasterRPRate");
			try 
			{
				ArrayList<MasterRateSTG> arrLiMasterRPRate = new ArrayList<MasterRateSTG>();
				int errorCount = 0;
				InputStream stream = file.getInputStream();
				// changes are done for XLS and XLSX files
				Workbook workbook = WorkbookFactory.create(stream); 
				//XSSFWorkbook workbook = new XSSFWorkbook(stream);
				//Sheet sheet = workbook.getSheetAt(0);
				Sheet sheet = workbook.getSheetAt(0);
				ArrayList<String> arryErrorList = new ArrayList<String>();
				String errorCause = "";
				ArrayList<String> strErrorElement = new ArrayList<String>();
				boolean isErrorInTemplate = false;
				AppLoger.APPLOGGER.info("total number of rows :"+ sheet.getLastRowNum());
				if (sheet.getLastRowNum() > 0) 
				{
					for (int i = 1; i <= sheet.getLastRowNum(); i++) 
					{
						errorCause = "";
						strErrorElement = new ArrayList<String>();
						MasterRateSTG objMasterRPRate = new MasterRateSTG();
						Row row = sheet.getRow(i);
						int columnCount = sheet.getRow(i).getLastCellNum();
						System.out.println("Column Count is............" + columnCount);
						
						//Check if Mandatory fields are not empty 
						if ((row.getCell(0) != null && row.getCell(0).getStringCellValue().isEmpty() == false)
							&& (row.getCell(1) != null && row.getCell(1).getStringCellValue().isEmpty() == false)							
							&& (row.getCell(2) != null && row.getCell(2).getNumericCellValue() > 0)
							&& (row.getCell(3) != null && row.getCell(3).getNumericCellValue() > 0)
							&& (row.getCell(4) != null && row.getCell(4).getNumericCellValue() > 0)
							&& (row.getCell(5) != null && row.getCell(5).getNumericCellValue() > 0)
							&& (row.getCell(6) != null && row.getCell(6).getNumericCellValue() > 0)
							&& (row.getCell(7) != null && row.getCell(7).getNumericCellValue() > 0))
							
						{
							objMasterRPRate.setCountryName(row.getCell(0).getStringCellValue());
							AppLoger.APPLOGGER.info("Country Name: "+ i +" \t"+ row.getCell(0).getStringCellValue());
							
							objMasterRPRate.setMasterRoleCode(row.getCell(1).getStringCellValue());
							AppLoger.APPLOGGER.info("Master Role Code: "+ i +" \t"+ row.getCell(1).getStringCellValue());
							
							objMasterRPRate.setTransactionYear((int) row.getCell(2).getNumericCellValue());
							AppLoger.APPLOGGER.info("Transaction Year: "+ i +" \t"+ row.getCell(2).getNumericCellValue());
														
							objMasterRPRate.setRateLow(new BigDecimal(row.getCell(3).getNumericCellValue())
									.setScale(2, RoundingMode.HALF_UP).doubleValue());
							AppLoger.APPLOGGER.info("Low Rate: "+ i +" \t"+ new BigDecimal(row.getCell(3).getNumericCellValue())
									.setScale(2, RoundingMode.HALF_UP).doubleValue());

							objMasterRPRate.setRateMedium(new BigDecimal(row.getCell(4).getNumericCellValue())
									.setScale(2, RoundingMode.HALF_UP).doubleValue());
							AppLoger.APPLOGGER.info("Medium Rate: "+ i +" \t"+ new BigDecimal(row.getCell(4).getNumericCellValue())
									.setScale(2, RoundingMode.HALF_UP).doubleValue());

							objMasterRPRate.setRateHigh(new BigDecimal(row.getCell(5).getNumericCellValue())
									.setScale(2, RoundingMode.HALF_UP).doubleValue());
							AppLoger.APPLOGGER.info("High Rate: "+ i +" \t"+ new BigDecimal(row.getCell(5).getNumericCellValue())
									.setScale(2, RoundingMode.HALF_UP).doubleValue());
							
							objMasterRPRate.setRateVHigh(new BigDecimal(row.getCell(6).getNumericCellValue())
									.setScale(2, RoundingMode.HALF_UP).doubleValue());
							AppLoger.APPLOGGER.info("VHigh Rate: "+ i +" \t"+ new BigDecimal(row.getCell(6).getNumericCellValue())
									.setScale(2, RoundingMode.HALF_UP).doubleValue());
							
							objMasterRPRate.setRateOffshore(new BigDecimal(row.getCell(7).getNumericCellValue())
									.setScale(2, RoundingMode.HALF_UP).doubleValue());
							AppLoger.APPLOGGER.info("Offshore Rate: "+ i +" \t"+ new BigDecimal(row.getCell(7).getNumericCellValue())
									.setScale(2, RoundingMode.HALF_UP).doubleValue());

							arrLiMasterRPRate.add(objMasterRPRate);
						}	
						else if(row.getCell(0).getCellType() == Cell.CELL_TYPE_BLANK  && row.getCell(1).getCellType() == Cell.CELL_TYPE_BLANK 
								&& row.getCell(2).getCellType() == Cell.CELL_TYPE_BLANK && row.getCell(3).getCellType() == Cell.CELL_TYPE_BLANK
								&& row.getCell(4).getCellType() == Cell.CELL_TYPE_BLANK && row.getCell(5).getCellType() == Cell.CELL_TYPE_BLANK
								&& row.getCell(6).getCellType() == Cell.CELL_TYPE_BLANK && row.getCell(7).getCellType() == Cell.CELL_TYPE_BLANK)
						{
							break;
						}
						else 
						{
							// To add error message if mandatory field(s) are empty							
							if (row.getCell(0).getStringCellValue().isEmpty()) 
							{
								strErrorElement.add("Country");
								isErrorInTemplate = true;
								errorCount++;
							}
							if (row.getCell(1).getStringCellValue().isEmpty()) 
							{
								strErrorElement.add("Role Code");
								isErrorInTemplate = true;
								errorCount++;
							}							
							if (row.getCell(2).getCellType() == Cell.CELL_TYPE_BLANK) 
							{
								strErrorElement.add("Year");
								isErrorInTemplate = true;
								errorCount++;
							}							
							if (row.getCell(3).getCellType() == Cell.CELL_TYPE_BLANK) 
							{
								strErrorElement.add("Low");
								isErrorInTemplate = true;
								errorCount++;
							}
							if (row.getCell(4).getCellType() == Cell.CELL_TYPE_BLANK) 
							{
								strErrorElement.add("Medium");
								isErrorInTemplate = true;
								errorCount++;
							}
							if (row.getCell(5).getCellType() == Cell.CELL_TYPE_BLANK) 
							{
								strErrorElement.add("High");
								isErrorInTemplate = true;
								errorCount++;
							}
							if (row.getCell(6).getCellType() == Cell.CELL_TYPE_BLANK) 
							{
								strErrorElement.add("VHigh");
								isErrorInTemplate = true;
								errorCount++;
							}
							if (row.getCell(7).getCellType() == Cell.CELL_TYPE_BLANK) 
							{
								strErrorElement.add("Offshore");
								isErrorInTemplate = true;
								errorCount++;
							}
														
							for (int j=0;j<strErrorElement.size();j++) 
							{										
								errorCause += strErrorElement.get(j);
								if(j < strErrorElement.size()-1)
								{
									errorCause += ", ";
								}
							}
							
							arryErrorList.add(FileUploadErrors.nullEntry(errorCause, i+1) + "<br>");
						}
						
					} // main for loop ends
					if (errorCount > 0)
					{
						System.out.println("Errorlist Value for first empty cell ::::::::" + arryErrorList);
						workbook.close();
						return ResponseEntity.accepted().body(arryErrorList);
					}
				}
				if (!arrLiMasterRPRate.isEmpty() || isErrorInTemplate == false) {
					AppLoger.APPLOGGER.info(" Now calling service layer to upload Master Right Price Rate............. ");
					workbook.close();
					return rightPriceService.uploadMasterRPRate(arrLiMasterRPRate);
				}
				else
				{
					workbook.close();
					return ResponseEntity.status(205).contentType(MediaType.TEXT_PLAIN)
							.body("Currently We are facing technical issues, please try again later.");
				}
				
			} catch (Exception e) 
			{
				e.printStackTrace();					
				return ResponseEntity.status(205).contentType(MediaType.TEXT_PLAIN).body("Currently We are facing technical issues, please try again later.");
			}
			
		}
		
		@ApiOperation(value = "get the deal details based on Id", response = Deal.class)
		@RequestMapping(value = "/getDealDetailsFromId/{dealId}/{rpVersionId}", method = RequestMethod.GET)
		 public ResponseEntity<Object> getDealDetailById(@PathVariable("dealId") String dealId,@PathVariable("rpVersionId") int rpVersionId){
			return rightPriceService.getDealDetailById(dealId,rpVersionId);
	   }
		@ApiOperation(value = "get the deal details based on Id", response = Deal.class)
		@RequestMapping(value = "/getTMFinalizeDealComment/{rpVersionId}", method = RequestMethod.GET)
		 public ResponseEntity<Object> getTMFinalizeDealComment(@PathVariable("rpVersionId") int rpVersionId){
			return rightPriceService.getTMFinalizeDealComment(rpVersionId);
	   }
		
		@ApiOperation(value = "Updating the deal details", response = Deal.class)
		@RequestMapping(value = "/updateApprovalDealData", method = RequestMethod.POST)
		public ResponseEntity<Object> updateApprovalDataForDeal(@RequestBody Deal dealDetails){
			return rightPriceService.updateApprovalDataForDeal(dealDetails);
		}
		
		@ApiOperation(value = "get the syntelPremium based on city", response = City.class)
		@RequestMapping(value = "/getSyntelPremium/{cityId}", method = RequestMethod.GET)
		 public ResponseEntity<Object> getSyntelPremiumById(@PathVariable("cityId") int cityId){
			return rightPriceService.getSyntelPremiumById(cityId);
	   }
		
		@ApiOperation(value = "get RateCard Details", response = RateCardDetails.class)
		@RequestMapping(value = "/downloadRateCardExcel/{customerId}", method = RequestMethod.GET,produces=MediaType.APPLICATION_OCTET_STREAM_VALUE)
		public ResponseEntity<Object> getRateCardExcelData(@PathVariable("customerId") int customerId) {
		    
			AppLoger.APPLOGGER.info("customerId in getRateCardExcelData is............ "+ customerId);
			
			String fileName = null;
			String timeStamp = new SimpleDateFormat("yyyy_MM_dd_hh_mm_ss").format(Calendar.getInstance().getTime());
			fileName = "RateCard_Details"+"_"+timeStamp+".xls";
			ResponseEntity<Object> excelRateCardDetails= (ResponseEntity<Object>) rightPriceService.getRateCardExportDetails(customerId);
			List<RateCardDetails> rateCardDetailsList = 	(List<RateCardDetails>) excelRateCardDetails.getBody();
			AppLoger.APPLOGGER.info("RateCardDetails LIst is............ "+ rateCardDetailsList.size());
			
	        byte []bis = ExcelView.getRateCardReport(rateCardDetailsList);
	        return ResponseEntity.status(200).header("Content-disposition",
	      		"attachment; filename="+fileName).contentType(MediaType.parseMediaType("application/octet-stream")).body(bis);
		}
		
		@ApiOperation(value = "Get rate card Country using RC Id", response = RateCardCurrencyUtilization.class)
		@RequestMapping(value = "/getRateCardManualUploadCountry/{rateCardId}", method = RequestMethod.GET)
		public ResponseEntity<Object> getRateCardManualUploadCountry(@PathVariable("rateCardId") int rateCardId){
			return rightPriceService.getRateCardManualUploadCountry(rateCardId);
		}
		
		@ApiOperation(value = "get rate card utilization", response = RateCardManualMarginCal.class)
		@RequestMapping(value = "/gerRateCardCalDetails/{cityId}/{rateCardId}", method = RequestMethod.GET)
		public ResponseEntity<Object> getRateCardCalDetails(@PathVariable("cityId") int cityId,@PathVariable("rateCardId") int rateCardId){
			return rightPriceService.getRateCardCalDetails(cityId,rateCardId);
		}
		
		
		@ApiOperation(value = "save the calculated Margin Details", response = RateCardManualMarginCal.class)
		@RequestMapping(value = "/saveCalculatedMargin", method = RequestMethod.POST)
		public ResponseEntity<Object> saveCalculatedMarginData(@RequestBody RateCardManualMarginCal[] rateCardMarginData){
			return rightPriceService.saveCalculatedMarginData(rateCardMarginData);
		}
		
		@ApiOperation(value = "Saving the data of the Rate Card", response =RateCardDetails.class)
		@RequestMapping(value = "/saveMasterRCDetails", method = RequestMethod.POST)
		public ResponseEntity<Object> saveRateCardDetails(@RequestBody RateCardDetails rateCardDetails) {
			try {
				AppLoger.APPLOGGER.info("Data received " + rateCardDetails);
				return rightPriceService.saveRateCardDetails(rateCardDetails);

			} catch (Exception e) {
				AppLoger.APPLOGGER.info("Exception : " + e);
			}
			return null;
		}
		@ApiOperation(value = "save the calculated Margin Details", response = RateCardManualMarginCal.class)
		@RequestMapping(value = "/uploadManualRCFile", method = RequestMethod.POST)
		public ResponseEntity<Object> uploadManualRateCard(@RequestParam(value = "file", required = false) MultipartFile file,
				@RequestParam(value = "name", required = false) String fileName,			
				@FormParam("file") InputStream uploadedInputStream,
				@FormParam("rpDealVersionId") int rpDealVersionId,
				@FormParam("dealAutoTowerId") int dealAutoTowerId,
				@FormParam("countryId") int countryId){
			
			try {
				AppLoger.APPLOGGER.info("File length : " + file);
				if(null != fileName){
					AppLoger.APPLOGGER.info("File name length : " + fileName.toString());
					AppLoger.APPLOGGER.info("RateCard Id from JS"+ rpDealVersionId);
				}
				return rightPriceService.uploadManualRateCard(file,fileName,uploadedInputStream,rpDealVersionId,dealAutoTowerId,countryId);
			}catch (Exception e) {
				e.printStackTrace();
				return ResponseEntity.status(205)
					       .contentType(MediaType.TEXT_PLAIN)
					       .body("Currently We are facing technical issues, please try again later.");
			}
			
		}
		
		@ApiOperation(value = "get rate card summary old", response = RateCardYearlySummary.class)
		@RequestMapping(value = "/getSummaryOld/{rcId}", method = RequestMethod.GET, headers = "Accept=application/json")
		public ResponseEntity<Object> getSummaryOld(@PathVariable("rcId") int rcId) {
			AppLoger.APPLOGGER.info("rc id-------------" + rcId);
			System.out.println(rcId);
			return rightPriceService.getSummaryOld(rcId);
		}
		@ApiOperation(value = "get rate card summary", response = RateCardSummaryDetails.class)
		@RequestMapping(value = "/getSummary/{rcId}", method = RequestMethod.GET, headers = "Accept=application/json")
		public ResponseEntity<Object> getSummary(@PathVariable("rcId") int rcId) {
			AppLoger.APPLOGGER.info("rc id-------------" + rcId);
			System.out.println(rcId);
			return rightPriceService.getSummary(rcId);
		}
		@ApiOperation(value = "get rate yoy increment", response = RateCardYOYIncrement.class)
		@RequestMapping(value = "/getYoyIncrement/{rcId}", method = RequestMethod.GET, headers = "Accept=application/json")
		public ResponseEntity<Object> getYoyIncrement(@PathVariable("rcId") int rcId) {
			AppLoger.APPLOGGER.info("rc id-------------" + rcId);
			System.out.println(rcId);
			return rightPriceService.getYoyIncrement(rcId);
		}
		
		@ApiOperation(value = "Upload Manual rateCard file", response = RateCardManualMarginCal.class)
		@RequestMapping(value = "/uploadManualFile", method = RequestMethod.POST)
		public ResponseEntity<Object> uploadManualRateCardFile(@RequestParam(value = "file", required = false) MultipartFile file,
				@RequestParam(value = "name", required = false) String fileName,			
				@FormParam("file") InputStream uploadedInputStream,
				@FormParam("rcId") int rcId){
			try {
				AppLoger.APPLOGGER.info("File length : " + file);
				if(null != fileName){
					AppLoger.APPLOGGER.info("File name length : " + fileName.toString());
					AppLoger.APPLOGGER.info("RateCard Id from JS"+ rcId);
				}
				return rightPriceService.uploadManualRateCardFile(file,fileName,uploadedInputStream,rcId);
			}catch (Exception e) {
				e.printStackTrace();
				return ResponseEntity.status(205)
					       .contentType(MediaType.TEXT_PLAIN)
					       .body("Currently We are facing technical issues, please try again later.");
			}
			
		}
		
		@RequestMapping(value = "/updateRateCardStatus/{rcId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
		public ResponseEntity<Object> updateRateCardStatus(@PathVariable("rcId") int rcId) {
			return rightPriceService.updateRateCardStatus(rcId);
		}
		
		@ApiOperation(value = "Get rate card Country using RC Id", response = RateCardCurrencyUtilization.class)
		@RequestMapping(value = "/getSummaryCountry/{rateCardId}", method = RequestMethod.GET)
		public ResponseEntity<Object> getSummaryCountry(@PathVariable("rateCardId") int rateCardId){
			return rightPriceService.getSummaryCountry(rateCardId);
		}
		@RequestMapping(value = "/getCustomer", method = RequestMethod.GET, headers = "Accept=application/json")
		public ResponseEntity<Object> getCustomer() {
			return rightPriceService.getCustomer();
		}

		@ApiOperation(value = "Update Customer", response = ContractualTerms.class)
		@RequestMapping(value = "/updateCustomer", method = RequestMethod.POST)
		public ResponseEntity<Object> updateCustomer(@RequestBody Customer Customer) {
			 return rightPriceService.updateCustomer(Customer);
		}
		
		@ApiOperation(value = "get fx_rate summary", response = RateCardSummaryDetails.class)
		@RequestMapping(value = "/getFxRateComment/{rcId}", method = RequestMethod.GET, headers = "Accept=application/json")
		public ResponseEntity<Object> getFxRateComment(@PathVariable("rcId") int rcId) {
			AppLoger.APPLOGGER.info("rc id-------------" + rcId);
			System.out.println(rcId);
			return rightPriceService.getFxRateComment(rcId);
		}
		@RequestMapping(value = "/getEmpName/{customerId}", method = RequestMethod.GET, headers = "Accept=application/json")
		public ResponseEntity<Object> getEmpName(@PathVariable("customerId") int customerId) {
			return rightPriceService.getEmpName(customerId);
		}
		
		@ApiOperation(value = "get currency_utilization", response = RateCardCurrencyUtilization.class)
		@RequestMapping(value = "/getSummaryCurrencyUti/{rcId}", method = RequestMethod.GET, headers = "Accept=application/json")
		public ResponseEntity<Object> getSummaryCurrencyUti(@PathVariable("rcId") int rcId) {
			AppLoger.APPLOGGER.info("rc id-------------" + rcId);
			System.out.println(rcId);
			return rightPriceService.getSummaryCurrencyUti(rcId);
		}
		
		@RequestMapping(value = "/getRateCardAssumtion/{assumtion_id}", method = RequestMethod.GET, headers = "Accept=application/json")
		public ResponseEntity<Object> getRateCardAssumtion(@PathVariable("assumtion_id") int assumtion_id) {
			return rightPriceService.getRateCardAssumtion(assumtion_id);
		}
		
		@ApiOperation(value = "get ratecard location details", response = RateCardLocation.class)
		@RequestMapping(value = "/getRateCardLocation/{rcId}", method = RequestMethod.GET, headers = "Accept=application/json")
		public ResponseEntity<Object> getRateCardLocation(@PathVariable("rcId") int rcId) {
			AppLoger.APPLOGGER.info("rc id-------------" + rcId);
			System.out.println(rcId);
			return rightPriceService.getRateCardLocation(rcId);
		}
		@ApiOperation(value = "view approver designation", response = ApproverDesignation.class)
		@RequestMapping(value = "/getDealApproverDesignation/{rpVrsId}", method = RequestMethod.GET, headers = "Accept=application/json")
		public ResponseEntity<Object> getDealApproverInfo(@PathVariable("rpVrsId") int rpVrsId) {
			AppLoger.APPLOGGER.info("Inside the Controller");
			AppLoger.APPLOGGER.info("Deal details is............. " + rpVrsId);
			return rightPriceService.getDealApproverInfo(rpVrsId);
		}
		@ApiOperation(value = "view approver designation", response = ApproverDesignation.class)
		@RequestMapping(value = "/getTMDealApproverDesignation/{rpVrsId}", method = RequestMethod.GET, headers = "Accept=application/json")
		public ResponseEntity<Object> getTMDealApproverInfo(@PathVariable("rpVrsId") int rpVrsId) {
			
			return rightPriceService.getTMDealApproverInfo(rpVrsId);
		}
		
		/*@ApiOperation(value = "view approver Name based on vertical id")
		@RequestMapping(value = "/getDealApproverName/{rpVrsId}", method = RequestMethod.GET, headers = "Accept=application/json")
		public ResponseEntity<Object> getDealApproverName(@PathVariable("rpVrsId") int rpVrsId) {
			AppLoger.APPLOGGER.info("Inside the Controller");
			return rightPriceService.getDealApproverName(rpVrsId);
		}*/
		
		@ApiOperation(value = "view approver Name based on vertical id")
		@RequestMapping(value = "/getDealApproverName/{rpVrsId}/{rbuName}", method = RequestMethod.GET, headers = "Accept=application/json")
		public ResponseEntity<Object> getDealApproverName(@PathVariable("rpVrsId") int rpVrsId, @PathVariable("rbuName") String rbuName) {
			AppLoger.APPLOGGER.info("Inside the Controller");
			return rightPriceService.getDealApproverName(rpVrsId,rbuName);
		} 
		//Akhilesh updated

		@RequestMapping(value = "/getDealQuest", method = RequestMethod.GET, headers = "Accept=application/json")
		public ResponseEntity<Object> getDealQuest() {
			return rightPriceService.getDealQuest();
		}
		
		@RequestMapping(value = "/getVersionData/{rpDealVersionId}", method = RequestMethod.GET, headers = "Accept=application/json")
		public ResponseEntity<Object> getVersionData(@PathVariable("rpDealVersionId") int rpDealVersionId) {
			AppLoger.APPLOGGER.info("RP deal version id: " + rpDealVersionId);
			return rightPriceService.getVersionData(rpDealVersionId);
		}
		
		
		@RequestMapping(value = "/getDevMainAnswers/{rpDealVersionId}", method = RequestMethod.GET, headers = "Accept=application/json")
		public ResponseEntity<Object> getDevMainAnswers(@PathVariable("rpDealVersionId") int rpDealVersionId) {
			AppLoger.APPLOGGER.info("RP deal version id: " + rpDealVersionId);
			return rightPriceService.getDevMainAnswers(rpDealVersionId);
		}
		
		@RequestMapping(value = "/getDealTower/{dealVersionId}", method = RequestMethod.GET, headers = "Accept=application/json")
		public ResponseEntity<Object> getDealTower(@PathVariable("dealVersionId") int dealVersionId) {
			AppLoger.APPLOGGER.info("RP deal version id: " + dealVersionId);
			return rightPriceService.getDealTower(dealVersionId);
		}
		
		@RequestMapping(value = "/getFpDealRateCard/{dealTowerId}/{dealVersionId}", method = RequestMethod.GET, headers = "Accept=application/json")
		public ResponseEntity<Object> getFpDealRateCard(@PathVariable("dealTowerId") int dealTowerId,@PathVariable("dealVersionId") int dealVersionId) {
			AppLoger.APPLOGGER.info("RP deal Tower id: " + dealTowerId);
			AppLoger.APPLOGGER.info("RP deal version id: " + dealVersionId);
			return rightPriceService.getFpDealRateCard(dealTowerId,dealVersionId);
		}
		
		@ApiOperation(value = "getAttachementData", response = AttachmentMapper.class)
		@RequestMapping(value = "/getAttachementData", method = RequestMethod.POST)
		 public ResponseEntity<Object> getAttachementData(@RequestBody AttachmentMapper rpDealVersionId){
			return rightPriceService.getAttachementData(rpDealVersionId);
			 }
		
		 
	
		@ApiOperation(value = "getTMAttachementData", response = AttachmentMapper.class)
		@RequestMapping(value = "/getTMAttachementData", method = RequestMethod.POST)
		 public ResponseEntity<Object> getTMAttachementData(@RequestBody AttachmentMapper rpDealVersionId) {
			 System.out.println("getTMAttachementData====================================================");
		return rightPriceService.getTMAttachementData(rpDealVersionId);
	}
		
			 
			 @RequestMapping(value = "/changeActiveStatus/{objectid}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
			 public ResponseEntity<Object> changeActiveStatus(@PathVariable("objectid") int objectid) {
			return rightPriceService.changeActiveStatus(objectid);
		}
		
			 @RequestMapping(value = "/deleteMasterRCAttachment/{objectid}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
			 public ResponseEntity<Object> deleteMasterRCAttachment(@PathVariable("objectid") int objectid) {
			return rightPriceService.deleteMasterRCAttachment(objectid);
		}
			 
			 @RequestMapping(value = "/deleteRateCard/{dltRcId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
			 public ResponseEntity<Object> deleteRateCard(@PathVariable("dltRcId") int dltRcId) {
				 return rightPriceService.deleteRateCard(dltRcId);
			 }
		
		
				@ApiOperation(value = "Upload Manual file", response = FPDealAttachment.class)
				@RequestMapping(value = "/uploadMFile", method = RequestMethod.POST)
				public ResponseEntity<Object> uploadManualDataFile(@RequestParam(value = "file", required = false) MultipartFile file,
						@RequestParam(value = "name", required = false) String fileName,			
						@FormParam("file") InputStream uploadedInputStream,
						@FormParam("versionId") Integer versionId,
						@FormParam("category") String category){
					try {
						AppLoger.APPLOGGER.info("File length : " + file);
						if(null != fileName){
							AppLoger.APPLOGGER.info("File name length : " + fileName.toString());
							AppLoger.APPLOGGER.info("Version Id from JS "+ versionId);
						}
						return rightPriceService.uploadManualFile(file,fileName,uploadedInputStream,versionId,category);
					}catch (Exception e) {
						e.printStackTrace();
						return ResponseEntity.status(205)
							       .contentType(MediaType.TEXT_PLAIN)
							       .body("Currently We are facing technical issues, please try again later.");
					}
					
				}
				
				
				@ApiOperation(value = "uploadTMFileData file", response = FPDealAttachment.class)
				@RequestMapping(value = "/uploadTMFileData", method = RequestMethod.POST)
				public ResponseEntity<Object> uploadTMFileData(@RequestParam(value = "file", required = false) MultipartFile file,
						@RequestParam(value = "name", required = false) String fileName,			
						@FormParam("file") InputStream uploadedInputStream,
						@FormParam("versionId") Integer versionId,
						@FormParam("category") String category){
					System.out.println("uploadTMFileData========================================C");
					try {
						AppLoger.APPLOGGER.info("TM File length : " + file);
						if(null != fileName){
							AppLoger.APPLOGGER.info("File name length : " + fileName.toString());
							AppLoger.APPLOGGER.info("Version Id from JS "+ versionId);
						}
						return rightPriceService.uploadTMFileData(file,fileName,uploadedInputStream,versionId,category);
					}catch (Exception e) {
						e.printStackTrace();
						return ResponseEntity.status(205)
							       .contentType(MediaType.TEXT_PLAIN)
							       .body("Currently We are facing technical issues, please try again later.");
					}
					
				}

				@ApiOperation(value = "Upload Manual Rate Card file", response = FPDealAttachment.class)
				@RequestMapping(value = "/uploadRCFile", method = RequestMethod.POST)
				public ResponseEntity<Object> uploadManualRCFile(@RequestParam(value = "file", required = false) MultipartFile file,
						@RequestParam(value = "name", required = false) String fileName,			
						@FormParam("file") InputStream uploadedInputStream,
						@FormParam("rcId") Integer rcId,
						@FormParam("category") String category){
					try {
						AppLoger.APPLOGGER.info("File length : " + file);
						if(null != fileName){
							AppLoger.APPLOGGER.info("File name length : " + fileName.toString());
							AppLoger.APPLOGGER.info("Version Id from JS "+ rcId);
						}
						return rightPriceService.uploadManualFile(file,fileName,uploadedInputStream,rcId,category);
					}catch (Exception e) {
						e.printStackTrace();
						return ResponseEntity.status(205)
							       .contentType(MediaType.TEXT_PLAIN)
							       .body("Currently We are facing technical issues, please try again later.");
					}
					
				}
				
				@ApiOperation(value = "Upload Manual FpDeal Summary file", response = FPManualDealAttachment.class)
				@RequestMapping(value = "/uploadMSFile", method = RequestMethod.POST)
				public ResponseEntity<Object> uploadManualDealFile(@RequestParam(value = "file", required = false) MultipartFile file,
						@RequestParam(value = "name", required = false) String fileName,			
						@FormParam("file") InputStream uploadedInputStream,
						@FormParam("rpDealVersionId") Integer rpDealVersionId,
						@FormParam("dealAutoTowerId")Integer dealAutoTowerId,
						@FormParam("docType") Integer docType,
						@FormParam("noOfTowers") Integer noOfTowers){
					try {
						AppLoger.APPLOGGER.info("File length : " + file);
						if(null != fileName){
							AppLoger.APPLOGGER.info("File name length : " + fileName.toString());
							AppLoger.APPLOGGER.info("Version Id from JS "+ rpDealVersionId);
							AppLoger.APPLOGGER.info("Deal Auto Tower Id from JS "+ dealAutoTowerId);
						}
						return rightPriceService.uploadManualDealFile(file,fileName,uploadedInputStream,rpDealVersionId,dealAutoTowerId,docType,noOfTowers);
					}catch (Exception e) {
						e.printStackTrace();
						return ResponseEntity.status(205)
							       .contentType(MediaType.TEXT_PLAIN)
							       .body("Currently We are facing technical issues, please try again later.");
					}
					
				}
				
				
				@ApiOperation(value = "Add FP deal creation Roles and contractor details for given RP_DEAL_VERSION_ID and Deal_Auto_Tower_Id")
				@RequestMapping(value = "/saveFPDealRoleSelectionAndContractorRole", method = RequestMethod.POST)
				public ResponseEntity<Object> saveFPDealRoleSelectionAndContractorRole(@RequestBody FPDealRoleAndContractor fPDealRoleAndContractor) {
					AppLoger.APPLOGGER.info(fPDealRoleAndContractor.getDealVersionId());
					AppLoger.APPLOGGER.info(fPDealRoleAndContractor.getDealAutoTowerId());
					AppLoger.APPLOGGER.info(fPDealRoleAndContractor.getIsMasterRole());
					AppLoger.APPLOGGER.info("Role size : "+fPDealRoleAndContractor.getDealRoles().size());
					AppLoger.APPLOGGER.info("Contractor role size : "+fPDealRoleAndContractor.getDealContractorRole().size());
					AppLoger.APPLOGGER.info("RCID:"+fPDealRoleAndContractor.getDealRoles());
					return rightPriceService.saveFPDealRoleSelectionAndContractorRole(fPDealRoleAndContractor);
				}
				
				
				@ApiOperation(value = "Get FP deal creation Roles and contractor details for given RP_DEAL_VERSION_ID")
				@RequestMapping(value = "/getFPDealRoleSelectionAndContractorRole/{dealVersionId}/{dealAutoTowerId}", method = RequestMethod.GET)
				public ResponseEntity<Object> getFPDealRoleSelectionAndContractorRole(@PathVariable("dealVersionId") int dealVersionId,@PathVariable("dealAutoTowerId") int dealAutoTowerId) {
					AppLoger.APPLOGGER.info(dealVersionId);
					AppLoger.APPLOGGER.info(dealAutoTowerId);
					return rightPriceService.getFPDealRoleSelectionAndContractorRole(dealVersionId,dealAutoTowerId);
				}
				
				@RequestMapping(value = "/getDealRateCardName/{customerId}/{deal_Id}/{currencyId}/{countryId}/{cityCategory}/{industryType}", method = RequestMethod.GET, headers = "Accept=application/json")
				public ResponseEntity<Object> getDealRateCardName(@PathVariable("customerId") int customerId, @PathVariable("deal_Id") String deal_Id,@PathVariable("currencyId") int currencyId,@PathVariable("countryId") int countryId,@PathVariable("cityCategory") int cityCategory,@PathVariable("industryType") int industryType)
				{
					System.out.println("817controller getRateCardName***********************"+customerId);
					//AppLoger.APPLOGGER.info("getRateCardName==controller==");
					return rightPriceService.getDealRateCardName(customerId,deal_Id,currencyId,countryId,cityCategory,industryType);
				}
				
				@RequestMapping(value = "/getUserRoles/{usreName}", method = RequestMethod.GET, headers = "Accept=application/json")
				public List<String> getUserRoles(@PathVariable("usreName") String usreName) {		
					return rightPriceService.getUserRoles(usreName);
				}
				
				@RequestMapping(value = "/getVersionAttachment/{rpDealVersionId}", method = RequestMethod.GET, headers = "Accept=application/json")
				public ResponseEntity<Object> getVersionAttachment(@PathVariable("rpDealVersionId") int rpDealVersionId) {
					AppLoger.APPLOGGER.info("RP deal version id: " + rpDealVersionId);
					return rightPriceService.getVersionAttachment(rpDealVersionId);
				}
			
				
				@RequestMapping(value = "/changeFpActiveStatus/{dealAttachmentId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
				 public ResponseEntity<Object> changeFpActiveStatus(@PathVariable("dealAttachmentId") int dealAttachmentId) {
				return rightPriceService.changeFpActiveStatus(dealAttachmentId);
				
				}
			
				@RequestMapping(value = "/getFpDealCostInputData/{towerId}/{costType}/{rpdealVersionId}", method = RequestMethod.GET, headers = "Accept=application/json")
				public ResponseEntity<Object> getFpDealCostInputsDetails(@PathVariable("towerId") int towerId,@PathVariable("costType") int costType,@PathVariable("rpdealVersionId") int rpdealVersionId) {
					return rightPriceService.getFpDealCostInputsDetails(towerId,costType,rpdealVersionId);
				}
				
				@RequestMapping(value = "/getFPCostCalCulationData/{towerId}/{rpdealVersionId}", method = RequestMethod.GET, headers = "Accept=application/json")
				public ResponseEntity<Object> getFPCostCalCulationData(@PathVariable("towerId") int towerId,@PathVariable("rpdealVersionId") int rpdealVersionId) {
					return rightPriceService.getFPCostCalCulationData(towerId,rpdealVersionId);
				}
				
				
				@ApiOperation(value = "Update Indirect Cost Inputs Data", response = DealIndirectCostInputs.class)
				@RequestMapping(value = "/updateCostInputData", method = RequestMethod.POST)
				public ResponseEntity<Object> updateCostInputData(@RequestBody List<DealIndirectCostInputs> dealIndirectCost) {
					return rightPriceService.updateCostInputData(dealIndirectCost);
				}
				
				@RequestMapping(value = "/updateDealVersionStatus/{versionId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
				public ResponseEntity<Object> updateManualDealVersionStatus(@PathVariable("versionId") int versionId) {
					return rightPriceService.updateManualDealVersionStatus(versionId);
				}
				
				@RequestMapping(value = "/updateManualGFTDeal/{versionId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
				public ResponseEntity<Object> updateManualGFTDeal(@PathVariable("versionId") int versionId) {
					return rightPriceService.updateManualGFTDeal(versionId);
				}
	
				
				@RequestMapping(value = "/getDescription/{cityId}", method = RequestMethod.GET, headers = "Accept=application/json")
				public ResponseEntity<Object> getDescription(@PathVariable("cityId") int cityId) {
					AppLoger.APPLOGGER.info("Returning desc for cityId : " + cityId);
					return rightPriceService.getDescription(cityId);
				}
				
				@ApiOperation(value = "view approver designation", response = ApproverDesignation.class)
				@RequestMapping(value = "/getFPManualDealApproverInfoDesignation/{rpDealVersionId}", method = RequestMethod.GET, headers = "Accept=application/json")
				public ResponseEntity<Object> getFPManualDealApproverInfoDesignation(@PathVariable("rpDealVersionId") int rpDealVersionId) {
					AppLoger.APPLOGGER.info("Inside the Controller");
					AppLoger.APPLOGGER.info("Ratecard details is............. " + rpDealVersionId);
					return rightPriceService.viewFPMDApproverDesignation(rpDealVersionId);
				}
				
				@ApiOperation(value = "save approval data for rate card", response = FpDeal.class)
				@RequestMapping(value = "/updateFPMDApprovalData", method = RequestMethod.POST)
				public ResponseEntity<Object> updateFPMDApprovalDetails(@RequestBody FpDeal fpmdApproval) {
					return rightPriceService.updateFPMDApprovalDetails(fpmdApproval);
				}
				
			/*	@ApiOperation(value = "view approver Name based on vertical id")
				@RequestMapping(value = "/getFPMDApproverNames/{verticalId}/{rpDealVersionId}", method = RequestMethod.GET, headers = "Accept=application/json")
				public ResponseEntity<Object> getFPMDApproverNames(@PathVariable("verticalId") int verticalId,@PathVariable("rpDealVersionId") int rpDealVersionId) {
					AppLoger.APPLOGGER.info("Inside the Controller");
					return rightPriceService.getFPMDApproverNames(verticalId,rpDealVersionId);
				}*/
				
				@ApiOperation(value = "view approver Name based on vertical id")
				@RequestMapping(value = "/getFPMDApproverNames/{verticalId}/{rpDealVersionId}/{rbuName}", method = RequestMethod.GET, headers = "Accept=application/json")
				public ResponseEntity<Object> getFPMDApproverNames(@PathVariable("verticalId") int verticalId,@PathVariable("rpDealVersionId") int rpDealVersionId,@PathVariable("rbuName") String rbuName) {
					AppLoger.APPLOGGER.info("Inside the Controller");
					return rightPriceService.getFPMDApproverNames(verticalId,rpDealVersionId,rbuName);
				}
				
				@ApiOperation(value = "save data for audit in fp manual dealsummary", response = RateCardApprovalAudit.class)
				@RequestMapping(value = "/saveFPMDApprovalData", method = RequestMethod.POST)
				public ResponseEntity<Object> saveFPMDApprovalData(@RequestBody RateCardApprovalAudit rateCardApprovalAudit) {
					return rightPriceService.saveFPMDApprovalData(rateCardApprovalAudit);
				}
				
				@ApiOperation(value = "save approval data for rate card", response = FpDeal.class)
				@RequestMapping(value = "/updateApprovalDataForFPMD", method = RequestMethod.POST)
				public ResponseEntity<Object> saveApprovalData(@RequestBody FpDeal fpdealmanualApprovalDetails) {
					return rightPriceService.updateApprovalDataForFPMD(fpdealmanualApprovalDetails);
				}
				
				//======================= Ayyaz Data { Resource Factory} starts
				@ApiOperation(value = "get CRM-Deal Data")
				@RequestMapping(value = "/getDealData", method = RequestMethod.GET, headers = "Accept=application/json")
				public ResponseEntity<Object> getDealData() {
					System.out.println("*************************************************************** inside deal detail");
					return rightPriceService.getDealData();
				}
				
				@ApiOperation(value = "ForecastData", response = ResourceForcastData.class)
				@RequestMapping(value = "/ForecastData", method = RequestMethod.POST)
				 public ResponseEntity<Object> getResourceData(@RequestBody ResourceForcastData forcastData){
					AppLoger.APPLOGGER.info("RP deal id: " + forcastData.getcRMDealId());
					AppLoger.APPLOGGER.info("RP customer id: " + forcastData.getCustomerId());
					
					return rightPriceService.getResourceData(forcastData);
					 }
				
				@ApiOperation(value = "get Lob Data")
				@RequestMapping(value = "/getLobData", method = RequestMethod.GET, headers = "Accept=application/json")
				public ResponseEntity<Object> getLobData() {
					System.out.println("*************************************************************** inside deal detail");
					return rightPriceService.getLobData();
				}

				@RequestMapping(value = "/getRolesData", method = RequestMethod.GET, headers = "Accept=application/json")
				public ResponseEntity<Object> getRolesData() {
					return rightPriceService.getRolesData();
				}
				
				@RequestMapping(value = "/getOldResourceData/{cRMDealId}", method = RequestMethod.GET, headers = "Accept=application/json")
				public ResponseEntity<Object> getOldResourceData(@PathVariable("cRMDealId") String cRMDealId) {
					return rightPriceService.getOldResourceData(cRMDealId);
				}
				
				

					@ApiOperation(value = "saveResourceData", response = ResourceForecast.class)
                           @RequestMapping(value = "/saveResourceData/{lob}/{lobFlag}", method = RequestMethod.POST)
                           public ResponseEntity<Object> saveResourceData(@RequestBody ResourceForecast forcastData,@PathVariable("lob") String lob,@PathVariable("lobFlag") int flag){
                                  AppLoger.APPLOGGER.info("LOB data: " + lob);
                                  AppLoger.APPLOGGER.info("deal id deal id: " + forcastData.getDealId());
                                  AppLoger.APPLOGGER.info("role id: " + forcastData.getSyntelRoleID());
                                  
                                  return rightPriceService.saveResourceData(forcastData,lob,flag);
                                  } 

				
				//================================================ changes for cost summery crmDealId,rpVrsId
				@RequestMapping(value = "/getCostData/{crmDealId}/{rpVrsId}", method = RequestMethod.GET, headers = "Accept=application/json")
				public ResponseEntity<Object> getCostBreakupData(@PathVariable("crmDealId") String cRMDealId,@PathVariable("rpVrsId") int dealVersionId) {
					return rightPriceService.getCostBreakupData(cRMDealId,dealVersionId);
				}
				
				 @ApiOperation(value = "get Report Details of Basic Allowances", response = CostBreakup.class)
					@RequestMapping(value = "/downloadCostBreakupExcel/{deald}/{dealVersionId}", method = RequestMethod.GET,produces=MediaType.APPLICATION_OCTET_STREAM_VALUE)
					public ResponseEntity<Object> getCostExcelReport(@PathVariable("deald") String deald,@PathVariable("dealVersionId") int dealVersionId) {
					    String fileName = null;
					    List<String> exchRate=new ArrayList<String>();
					    List<String> currencyCode=new ArrayList<String>();
					    List countryDatas = new ArrayList();
					    List <Integer> countryId=new ArrayList<Integer>();
					    Map<String, List<String>>  map= new HashMap<String, List<String>>();
						String timeStamp = new SimpleDateFormat("yyyy_MM_dd_hh_mm_ss").format(Calendar.getInstance().getTime());
						fileName = "Cost_BreakUp_Details"+"_"+timeStamp+".xls";
						ResponseEntity<Object> costData= (ResponseEntity<Object>) rightPriceService.getCostBreakupData(deald,dealVersionId);
						
						List[] costList = 	(List[]) costData.getBody();
						List<CostBreakup> cost=costList[0];
						List<DealFixLocation> deal =  costList[1];
						AppLoger.APPLOGGER.info("City LIst is............ ");
						
						ResponseEntity<Object> dealData= (ResponseEntity<Object>) rightPriceService.getDealDetails(deald,dealVersionId);
						List<DealDetailsView> dealList = 	(List<DealDetailsView>) dealData.getBody();
						AppLoger.APPLOGGER.info("City LIst is............ "+ dealList.size());
						
						for(int i=0;i<deal.size();i++)
						{
							countryId.add(deal.get(i).getCountryId());	
						}
						
						List<Integer> uniqueCountry = new ArrayList<Integer>(new HashSet<Integer>(countryId));						
						
						ResponseEntity<Object> countryData = (ResponseEntity<Object>) rightPriceService.getCountry();
						List<Country> countryList =(List<Country>) countryData.getBody();
						for(int i=0;i<uniqueCountry.size();i++)
						{
							for(int j=0;j<countryList.size();j++){
								
								if(uniqueCountry.get(i)==countryList.get(j).getCountryId()){
									String exchRates = new Double(countryList.get(j).getExchangeRate()).toString();
									exchRate.add(exchRates);
									currencyCode.add(countryList.get(j).getCurrencyCode());
								}
							}
						}
						map.put("1", exchRate);
						map.put("2",currencyCode );
						
						 
						
						
				      byte []bis = ExcelView.getCostExcelReport(cost,deal,dealList,map);
				      return ResponseEntity.status(200).header("Content-disposition",
				      		"attachment; filename="+fileName).contentType(MediaType.parseMediaType("application/octet-stream")).body(bis);
					}
				   
				@ApiOperation(value = "updateBillingSchedule", response = ResourceForecast.class)
				@RequestMapping(value = "/updateBillingSchedule/{deald}/{dealVersionId}", method = RequestMethod.POST)
				 public ResponseEntity<Object> updateBillingSchedule(@RequestBody CostBreakup costBreakup,
						 @PathVariable("deald") int deald,@PathVariable("dealVersionId") int dealVersionId){
					AppLoger.APPLOGGER.info("deal Id is............ "+deald);
					AppLoger.APPLOGGER.info("deal Version Id is............ "+dealVersionId);
					
					return rightPriceService.updateBillingSchedule(costBreakup,deald,dealVersionId);
					 }
				
				//================================================ changes for cost summery ends here
				
				//======================= Ayyaz Data { Resource Factory} ends
				
				
				@RequestMapping(value = "/getWhatIfData/{rpVrsId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
				public ResponseEntity<Object> getWhatIfData(@PathVariable("rpVrsId") int rpVrsId) {
					return rightPriceService.getWhatIfData(rpVrsId);
				}
				
				
				@RequestMapping(value = "/getWhatIfEffortData/{rpVrsId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
				public ResponseEntity<Object> getWhatIfEffortData(@PathVariable("rpVrsId") int rpVrsId) {
					return rightPriceService.getWhatIfEffortData(rpVrsId);
				}

			
				@ApiOperation(value = "update what if data", response = FPDealWhatIf.class)
				@RequestMapping(value = "/updateFpDealWhatIfDetails", method = RequestMethod.POST)
				 public ResponseEntity<Object> updateFpDealWhatIfDetails(@RequestBody FPWhatIfDetailsInsert fpWhatIfDetailsInsert){
					AppLoger.APPLOGGER.info("inside add updateFpDealWhatIfDetails controller");
					return rightPriceService.updateFpDealWhatIfDetails(fpWhatIfDetailsInsert);
				 }
				
				
				@ApiOperation(value = "save approval data for Automatic FP Deal", response = RateCardDetails.class)
				@RequestMapping(value = "/updateFPApprovalStatus", method = RequestMethod.POST)
				public ResponseEntity<Object> updateFPApproverDetails(@RequestBody FpDeal fpData) {
					return rightPriceService.updateFPApproverDetails(fpData);
				}
				
				@ApiOperation(value = "save approval data for FP Automatic Deal", response = FpDeal.class)
				@RequestMapping(value = "/updateFPApprover", method = RequestMethod.POST)
				public ResponseEntity<Object> updateFPApproverStatus(@RequestBody FpDeal[] fpDealApprove) {
					return rightPriceService.updateFPApproverStatus(fpDealApprove);
				}


				

				   @ApiOperation(value = "get Report Details of FPDealCreationRoleSelection", response = FPDealRoleAndContractor.class)
				   @RequestMapping(value = "/downloadFPDealCreationRoleSelectionExcel/{rpDealVersionId}/{dealVersionId}/{dealId}/{rpVrsId}/{dealAutoTowerId}", method = RequestMethod.GET,produces=MediaType.APPLICATION_OCTET_STREAM_VALUE)
				   public ResponseEntity<Object>downloadFPDealCreationRoleSelectionExcel(@PathVariable("rpDealVersionId") int rpDealVersionId, @PathVariable("dealVersionId") int dealVersionId,
						   @PathVariable("dealId") String dealId, @PathVariable("rpVrsId") int rpVersionId, @PathVariable("dealAutoTowerId") int dealAutoTowerId) 
				   {
					   String fileName = null;
					   String timeStamp = new SimpleDateFormat("yyyy_MM_dd_hh_mm_ss").format(Calendar.getInstance().getTime());
					   fileName = "FD_"+ dealVersionId + "_DealcreationRoleSelection"+"_"+timeStamp+".xls";
					   ResponseEntity<Object> parentTowXData = (ResponseEntity<Object>) rightPriceService.getFPDealRoleSelectionAndContractorRoleFP(dealVersionId,dealAutoTowerId);
					   ResponseEntity<Object> parentHeadingData = (ResponseEntity<Object>) rightPriceService.getVersionData(rpDealVersionId);
					   ResponseEntity<Object> parentHeadingData1 = (ResponseEntity<Object>) rightPriceService.getDealDetailById(dealId,rpVersionId);
					   ResponseEntity<Object> parentNooftower = (ResponseEntity<Object>) rightPriceService.getDealTower(dealVersionId);
					   ResponseEntity<Object> countryName = (ResponseEntity<Object>) rightPriceService.getCountry();
					   ResponseEntity<Object> cityName = (ResponseEntity<Object>) rightPriceService.getCityDataFP();
					   ResponseEntity<Object> x0skillElName = (ResponseEntity<Object>) rightPriceService.getXOSkillElementRolesFP();
					   ResponseEntity<Object> currencyName = (ResponseEntity<Object>) rightPriceService.getCurrency();
					   ResponseEntity<Object> x0skilllName = (ResponseEntity<Object>) rightPriceService.getXOSkills();
					   ResponseEntity<Object> x0KnowledgeName = (ResponseEntity<Object>) rightPriceService.getKnowledgeName();
					   HashMap<Object, Object> objHashMap = (HashMap) parentTowXData.getBody();
					   List<DealContraactorRole> contractList = new ArrayList<DealContraactorRole>();
					   List<PersistedFpDealRoles> xODataList= new ArrayList<PersistedFpDealRoles>();
					   for(Object object : objHashMap.entrySet()) 
					   {	
						   xODataList = (List<PersistedFpDealRoles>) objHashMap.get("X0List");		
						   contractList = (List<DealContraactorRole>) objHashMap.get("contractList");
					   }
					   List<FpDeal> parentHeadingDataList = (List<FpDeal>) parentHeadingData.getBody();
					   List<Deal> parentHeadingData1List = (List<Deal>) parentHeadingData1.getBody();
					   List<FPDealTower> parentNooftowerList = (List<FPDealTower>) parentNooftower.getBody();
					   List<Country> countryList = (List<Country>) countryName.getBody();
					   List<CityFP> cityList = (List<CityFP>) cityName.getBody();
					   List<MasterXOSkillElement> x0skillEList = (List<MasterXOSkillElement>) x0skillElName.getBody();
					   List<Currency> currencyList = (List<Currency>) currencyName.getBody();
					   List<XOSkillMaster> x0skillList = (List<XOSkillMaster>) x0skilllName.getBody();
					   List<X0KnowledgeMaster> x0KnowledgeList = (List<X0KnowledgeMaster>) x0KnowledgeName.getBody();
					   
					   
					   AppLoger.APPLOGGER.info("FPDealCreationRoleSelectionExcel tower  Details ............ ");
						  // Double dblMultifier = Double.parseDouble(multifier); 	   
						   /*byte []bis = ExcelView.downloadUtilizationRoleExcel(lstYears,lstRCRoleUtilization,lstRCDetails,isBillCurrency,multifier,currencyName,lstRCYOY);*/
					   		byte []bis = ExcelView.downloadFPDealCreationRoleSelectionExcel(xODataList,contractList,parentHeadingDataList,parentHeadingData1List,dealAutoTowerId,parentNooftowerList,countryList,cityList,x0skillEList,currencyList,x0skillList,x0KnowledgeList);
					    	return ResponseEntity.status(200).header("Content-disposition",
					    		"attachment; filename="+fileName).contentType(MediaType.parseMediaType("application/octet-stream")).body(bis);	
				   
				   }
	@ApiOperation(value = "get Ratecard detail", response = MyDashboardDeal.class)
	@RequestMapping(value = "/getDealDataOnSearch/{crmDealID}", method = RequestMethod.GET, headers = "Accept=application/json")
	public ResponseEntity<Object> getDealDataOnSearch(@PathVariable("crmDealID") String crmDealID) 
	{
		return rightPriceService.getDealDataOnSearch(crmDealID);
	}
	
	/*@ApiOperation(value = "get Ratecard detail", response = MyDashboardDeal.class)
	@RequestMapping(value = "/getDealDataOnSearch/{dealVresionID}", method = RequestMethod.GET, headers = "Accept=application/json")
	public ResponseEntity<Object> getDealDataOnSearch(@PathVariable("dealVresionID") int dealVresionID) 
	{
		return rightPriceService.getDealDataOnSearch(dealVresionID);
	}*/
	
	@RequestMapping(value = "/deleteDealVersion/{dltDealVersionId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> deleteDealVersion(@PathVariable("dltDealVersionId") int dltDealVersionId)
	{
		return rightPriceService.deleteDealVersion(dltDealVersionId);
	}
					
	@RequestMapping(value = "/getFpDealTowerData", method = RequestMethod.GET, headers = "Accept=application/json")
	public ResponseEntity<Object> getFpDealTowerData() 
	{
		return rightPriceService.getFpDealTowerData();
	}
	
	@RequestMapping(value = "/getWhatIfCalculationData/{rpVrsId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> getWhatIfCalculationData(@PathVariable("rpVrsId") int rpVrsId) {
		return rightPriceService.getWhatIfCalculationData(rpVrsId);
	}
	
	@RequestMapping(value = "/getFpDealRoleDetails/{dealVersionId}", method = RequestMethod.GET, headers = "Accept=application/json")
	public ResponseEntity<Object> getFpDealRoleDetails(@PathVariable("dealVersionId") int dealVersionId) {
		AppLoger.APPLOGGER.info("RP deal version id: " + dealVersionId);
		return rightPriceService.getFpDealRoleDetails(dealVersionId);
	}
	
	
	@ApiOperation(value = "get Report Details of Basic Allowances", response = MyDashboardRC.class)
	@RequestMapping(value = "/downloadMyDashboardExcel/{userType}/{rcStatusIndicator}/{dealStatusIndicator}/{rateCardStatus}/{dealStatus}/{customerId}/{onloadFlag}/{rateCardID}/{dealID}/{currentDealStatus}/{dealStatusValue}", method = RequestMethod.GET,produces=MediaType.APPLICATION_OCTET_STREAM_VALUE)
	public ResponseEntity<Object> downloadMyDashboardExcel(@PathVariable("userType") String userType,@PathVariable("rcStatusIndicator") String rcStatusIndicator,
			@PathVariable("dealStatusIndicator") String dealStatusIndicator, @PathVariable("rateCardStatus") String rateCardStatus,
			@PathVariable("dealStatus") String dealStatus,@PathVariable("customerId") int customerId,
			@PathVariable("onloadFlag") int onloadFlag,@PathVariable("rateCardID") int rateCardID,@PathVariable("dealID") String  dealID, @PathVariable("currentDealStatus") int  currentDealStatus,@PathVariable("dealStatusValue") int dealStatusValue ) 
	{
		AppLoger.APPLOGGER.info("RC INDIICATOR::" +rcStatusIndicator);
		// @RequestBody MyDashboardRC[] rateCardCount
		if (!(userType.equals("User")) && !(userType.equals("Delivery"))) {
			ResponseEntity<Object> arr;
			ArrayList<CustomerVerticalMapping> customer;
			
			// adding customer data for RC data and deal data
			if (userType.equals("GFT") || userType.equals("CEO") || userType.equals("CDO") 
				|| userType.equals("LEVELl1User") || userType.equals("LEVELl2User")) {
				arr = rightPriceService.getGFTCustomer();
				customer = (ArrayList) arr.getBody();
			} else {
				arr = rightPriceService.getCustomerByVerticalGroupId();
				customer = (ArrayList) arr.getBody();
			}

			ArrayList<MyDashboardRC> rateCardCountArrayList = new ArrayList<MyDashboardRC>();
			MyDashboardRC objRC = new MyDashboardRC();
			
			objRC.setUserType(userType);
			objRC.setStatusIndicator(rcStatusIndicator);
			
			if (customerId != 0) {
				objRC.setCustomerId(customerId);
			}
			rateCardCountArrayList.add(objRC);
			MyDashboardRC[] rateCardCount = new MyDashboardRC[1];
			rateCardCount = rateCardCountArrayList.toArray(rateCardCount);
			String fileName = null;
			String timeStamp = new SimpleDateFormat("yyyy_MM_dd_hh_mm_ss").format(Calendar.getInstance().getTime());
			fileName = "MyDashboard_Details" + "_" + timeStamp + ".xls";
			
			ResponseEntity<Object> costData;
			List costList;
			List<MyDashboardRC> RC;
			if (onloadFlag == 0) {
				if(rateCardID==0)
				{
				costData = (ResponseEntity<Object>) rightPriceService.getSummaryCountBasedOnVerticalId(rateCardCount);
				costList = (List<Object[]>) costData.getBody();
				RC = (List<MyDashboardRC>) costList.get(2);
				}
				else
				{
					costData = (ResponseEntity<Object>) rightPriceService.getDataOnSearch(rateCardID);
					costList = (List<Object[]>) costData.getBody();
					RC = (List<MyDashboardRC>) costList;
				}
				
			} else {
				int[] customerArray = new int[customer.size()];
				for (int i = 0; i < customerArray.length; i++) {
					customerArray[i] = customer.get(i).getCustomerId();
				}
				if(rateCardID==0)
				{	
				if (customerId == 0) {
						rateCardCount = new MyDashboardRC[customerArray.length];
						for (int i = 0; i < customerArray.length; i++) {
						MyDashboardRC objRC1 = new MyDashboardRC();
						objRC1.setUserType(userType);
						objRC1.setStatusIndicator(rcStatusIndicator);
						objRC1.setCustomerId(customerArray[i]);
						rateCardCountArrayList.add(objRC1);
						rateCardCount = rateCardCountArrayList.toArray(rateCardCount);
					}
				}

				costData = (ResponseEntity<Object>) rightPriceService.getRateCardData(rateCardCount);
				costList = (List<Object[]>) costData.getBody();
				RC = (List<MyDashboardRC>) costList;
				}else{
					costData = (ResponseEntity<Object>) rightPriceService.getDataOnSearch(rateCardID);
					costList = (List<Object[]>) costData.getBody();
					RC = (List<MyDashboardRC>) costList;
				}
			}

			
			
			//=============================================================== deal data 
			
			ArrayList<MyDashboardDeal> arrMyDashBoradDeal = new ArrayList<MyDashboardDeal>();
			MyDashboardDeal objDeal = new MyDashboardDeal();
			objDeal.setUserType(userType);
			objDeal.setStatusIndicator(dealStatusIndicator);
			objDeal.setCurrentApprovalStatus(currentDealStatus);
			if (customerId != 0) {
				objDeal.setCustomerId(customerId);
			}
			arrMyDashBoradDeal.add(objDeal);
			MyDashboardDeal[] deal = new MyDashboardDeal[1];
			deal = arrMyDashBoradDeal.toArray(deal);
			ResponseEntity<Object> dealData;
			
			List dealList;
			List<MyDashboardDeal> dealdetails;
			
			if (onloadFlag == 0) 
			{
				if(dealID.contains("0"))
				{
					if(dealStatusValue==0){
						dealData = (ResponseEntity<Object>) rightPriceService.getDealCountBasedOnVerticalId(deal);
						dealList = (List<Object[]>) dealData.getBody();
						dealdetails = (List<MyDashboardDeal>) dealList.get(2);
					}else{
						int[] customerArray = new int[customer.size()];
						for (int i = 0; i < customerArray.length; i++) {
							customerArray[i] = customer.get(i).getCustomerId();
						}
						if (customerId == 0) {
							deal = new MyDashboardDeal[customerArray.length];
							for (int i = 0; i < customerArray.length; i++) {
							MyDashboardDeal objRC1 = new MyDashboardDeal();
							objRC1.setUserType(userType);
							objRC1.setCustomerId(customerArray[i]);
							objRC1.setDealStatusIdWL(dealStatusValue);
							arrMyDashBoradDeal.add(objRC1);
							deal = arrMyDashBoradDeal.toArray(deal);
						} 
						}
						else{
							MyDashboardDeal objRC1 = new MyDashboardDeal();
							objRC1.setUserType(userType);
							objRC1.setCustomerId(customerId);
							objRC1.setDealStatusIdWL(dealStatusValue);
							arrMyDashBoradDeal.add(objRC1);
							deal = arrMyDashBoradDeal.toArray(deal);
						}
						dealData = (ResponseEntity<Object>)	rightPriceService.getDealStatusData(deal);
						dealdetails = (List<MyDashboardDeal>) dealData.getBody();
					}
			
				}else
				{
					dealData = (ResponseEntity<Object>) rightPriceService.getDealDataOnSearch(dealID);
					dealList = (List<Object[]>) dealData.getBody();
					dealdetails = (List<MyDashboardDeal>) dealList;
				}
			} else {
				int[] customerArray = new int[customer.size()];
				for (int i = 0; i < customerArray.length; i++) {
					customerArray[i] = customer.get(i).getCustomerId();
				}
				if(dealID.contains("0")){
					if(dealStatusValue==0){
					if (customerId == 0) {
						deal = new MyDashboardDeal[customerArray.length];
						for (int i = 0; i < customerArray.length; i++) {
						MyDashboardDeal objRC1 = new MyDashboardDeal();
						objRC1.setUserType(userType);
						objRC1.setStatusIndicator(rcStatusIndicator);
						objRC1.setCustomerId(customerArray[i]);
						objRC1.setCurrentApprovalStatus(currentDealStatus);
						arrMyDashBoradDeal.add(objRC1);
						deal = arrMyDashBoradDeal.toArray(deal);
					} 

				}
					else{
						MyDashboardDeal objRC1 = new MyDashboardDeal();
						objRC1.setUserType(userType);
						objRC1.setStatusIndicator(rcStatusIndicator);
						objRC1.setCustomerId(customerId);
						objRC1.setCurrentApprovalStatus(currentDealStatus);
						arrMyDashBoradDeal.add(objRC1);
						deal = arrMyDashBoradDeal.toArray(deal);
					
					}
					dealData = (ResponseEntity<Object>) rightPriceService.getDashboardDealData(deal);
					}
					else{
						if (customerId == 0) {
							deal = new MyDashboardDeal[customerArray.length];
							for (int i = 0; i < customerArray.length; i++) {
							MyDashboardDeal objRC1 = new MyDashboardDeal();
							objRC1.setUserType(userType);
							objRC1.setCustomerId(customerArray[i]);
							objRC1.setDealStatusIdWL(dealStatusValue);
							arrMyDashBoradDeal.add(objRC1);
							deal = arrMyDashBoradDeal.toArray(deal);
						} 
						
						}else{
							MyDashboardDeal objRC1 = new MyDashboardDeal();
							objRC1.setUserType(userType);
							objRC1.setCustomerId(customerId);
							objRC1.setDealStatusIdWL(dealStatusValue);
							arrMyDashBoradDeal.add(objRC1);
							deal = arrMyDashBoradDeal.toArray(deal);
						}
						dealData = (ResponseEntity<Object>)	rightPriceService.getDealStatusData(deal);
					}
					
					dealList = (List<Object[]>) dealData.getBody();
					dealdetails = (List<MyDashboardDeal>) dealList;	
				}
				else{
				dealData = (ResponseEntity<Object>) rightPriceService.getDealDataOnSearch(dealID);
				dealList = (List<Object[]>) dealData.getBody();
				dealdetails = (List<MyDashboardDeal>) dealList;
				}
				}

			ResponseEntity<Object> towerData1 = (ResponseEntity<Object>) rightPriceService.getFpDealTowerData();
			List towerList = (List<Object[]>) towerData1.getBody();
			List<FPDealTower> towerdetails = (List<FPDealTower>) towerList;
			
			
			ResponseEntity<Object> appCodeListObj = (ResponseEntity<Object>)rightPriceService.getAppCodeData();
			List appCodeList = (List<Object[]>) appCodeListObj.getBody();
			List<AppCode> appCodedetails = (List<AppCode>) appCodeList;
					
			byte[] bis = ExcelView.getDashboardExcelReport(RC, dealdetails, rateCardStatus, dealStatus, towerdetails,appCodedetails);
			AppLoger.APPLOGGER.info("--------------------------bis------------------------------------");
			AppLoger.APPLOGGER.info(bis);

			return ResponseEntity.status(200).header("Content-disposition", "attachment; filename=" + fileName)
					.contentType(MediaType.parseMediaType("application/octet-stream")).body(bis);

		}

		
		else {
			
			ResponseEntity<Object> arr= rightPriceService.getCustomerForUser();
			ArrayList<AccessControl> customer = (ArrayList) arr.getBody();

			ArrayList<MyDashboardRC> rateCardCountArrayList = new ArrayList<MyDashboardRC>();
			MyDashboardRC objRC = new MyDashboardRC();
			int[] customerArray = new int[customer.size()];
			MyDashboardRC[] rateCardCount = new MyDashboardRC[customerArray.length];
			String fileName = null;
			String timeStamp = new SimpleDateFormat("yyyy_MM_dd_hh_mm_ss").format(Calendar.getInstance().getTime());
			fileName = "MyDashboard_Details" + "_" + timeStamp + ".xls";
			ResponseEntity<Object> costData;
			List costList;
			List<MyDashboardRC> RC;
			
			for (int i = 0; i < customerArray.length; i++) {
				customerArray[i] = customer.get(i).getCustomerId();
			}
			
			objRC.setUserType(userType);
			objRC.setStatusIndicator(rcStatusIndicator);
			
			
			if (customerId != 0) {
				rateCardCount = new MyDashboardRC[1];
				objRC.setCustomerId(customerId);
				rateCardCountArrayList.add(objRC);
				rateCardCount = rateCardCountArrayList.toArray(rateCardCount);
			}
			else{
				for (int i = 0; i < customerArray.length; i++) {
					MyDashboardRC objRC1 = new MyDashboardRC();
					objRC1.setUserType(userType);
					objRC1.setStatusIndicator(rcStatusIndicator);
					objRC1.setCustomerId(customerArray[i]);
					rateCardCountArrayList.add(objRC1);
					rateCardCount = rateCardCountArrayList.toArray(rateCardCount);
				}
			}
			
			
			
			if (onloadFlag == 0) {
				if(rateCardID==0)
				{
				costData = (ResponseEntity<Object>) rightPriceService.getSummaryCountBasedOnVerticalId(rateCardCount);
				costList = (List<Object[]>) costData.getBody();
				RC = (List<MyDashboardRC>) costList.get(2);
				}
				else
				{
					costData = (ResponseEntity<Object>) rightPriceService.getDataOnSearch(rateCardID);
					costList = (List<Object[]>) costData.getBody();
					RC = (List<MyDashboardRC>) costList;
				}
				
			} else {
				for (int i = 0; i < customerArray.length; i++) {
					customerArray[i] = customer.get(i).getCustomerId();
				}
				if(rateCardID==0)
				{	
				if (customerId == 0) {
						rateCardCount = new MyDashboardRC[customerArray.length];
						for (int i = 0; i < customerArray.length; i++) {
						MyDashboardRC objRC1 = new MyDashboardRC();
						objRC1.setUserType(userType);
						objRC1.setStatusIndicator(rcStatusIndicator);
						objRC1.setCustomerId(customerArray[i]);
						rateCardCountArrayList.add(objRC1);
						rateCardCount = rateCardCountArrayList.toArray(rateCardCount);
					}
				}

				costData = (ResponseEntity<Object>) rightPriceService.getRateCardData(rateCardCount);
				costList = (List<Object[]>) costData.getBody();
				RC = (List<MyDashboardRC>) costList;
				}else{
					costData = (ResponseEntity<Object>) rightPriceService.getDataOnSearch(rateCardID);
					costList = (List<Object[]>) costData.getBody();
					RC = (List<MyDashboardRC>) costList;
				}
			}

			
			
			//=============================================================== deal data 
			
			ArrayList<MyDashboardDeal> arrMyDashBoradDeal = new ArrayList<MyDashboardDeal>();
			MyDashboardDeal objDeal = new MyDashboardDeal();
			objDeal.setUserType(userType);
			objDeal.setStatusIndicator(dealStatusIndicator);
			objDeal.setCurrentApprovalStatus(currentDealStatus);
			arrMyDashBoradDeal.add(objDeal);
			MyDashboardDeal[] deal = new MyDashboardDeal[1];
			deal = arrMyDashBoradDeal.toArray(deal);
			ResponseEntity<Object> dealData;
			
			List dealList;
			List<MyDashboardDeal> dealdetails;
			
			if (customerId != 0) {
				objDeal.setCustomerId(customerId);
				arrMyDashBoradDeal.add(objDeal);
				deal = arrMyDashBoradDeal.toArray(deal);
			}else{
				for (int i = 0; i < customerArray.length; i++) {
					MyDashboardDeal objDeal1 = new MyDashboardDeal();
					objDeal1.setUserType(userType);
					objDeal1.setStatusIndicator(rcStatusIndicator);
					objDeal1.setCustomerId(customerArray[i]);
					objDeal1.setCurrentApprovalStatus(currentDealStatus);
					arrMyDashBoradDeal.add(objDeal1);
					deal = arrMyDashBoradDeal.toArray(deal);
				}
			}
		
			
			if (onloadFlag == 0) 
			{
				if(dealID.contains("0"))
				{
				if(dealStatusValue==0){
					dealData = (ResponseEntity<Object>) rightPriceService.getDealCountBasedOnVerticalId(deal);
					dealList = (List<Object[]>) dealData.getBody();
					dealdetails = (List<MyDashboardDeal>) dealList.get(2);
				}
				else{
					int[] customerArray2 = new int[customer.size()];
					for (int i = 0; i < customerArray2.length; i++) {
						customerArray2[i] = customer.get(i).getCustomerId();
					}
					

					if (customerId == 0) {
						deal = new MyDashboardDeal[customerArray2.length];
						for (int i = 0; i < customerArray2.length; i++) {
						MyDashboardDeal objRC1 = new MyDashboardDeal();
						objRC1.setUserType(userType);
						objRC1.setCustomerId(customerArray2[i]);
						objRC1.setDealStatusIdWL(dealStatusValue);
						arrMyDashBoradDeal.add(objRC1);
						deal = arrMyDashBoradDeal.toArray(deal);
					} 
					}else{
						MyDashboardDeal objRC1 = new MyDashboardDeal();
						objRC1.setUserType(userType);
						objRC1.setCustomerId(customerId);
						objRC1.setDealStatusIdWL(dealStatusValue);
						arrMyDashBoradDeal.add(objRC1);
						deal = arrMyDashBoradDeal.toArray(deal);
					}
					dealData = (ResponseEntity<Object>)	rightPriceService.getDealStatusData(deal);
					dealdetails = (List<MyDashboardDeal>) dealData.getBody();
				
				}
				}else
				{
					dealData = (ResponseEntity<Object>) rightPriceService.getDealDataOnSearch(dealID);
					dealList = (List<Object[]>) dealData.getBody();
					dealdetails = (List<MyDashboardDeal>) dealList;
				}
			} else {
				for (int i = 0; i < customerArray.length; i++) {
					customerArray[i] = customer.get(i).getCustomerId();
				}
				if(dealID.contains("0")){
					if(dealStatusValue==0){
					if (customerId == 0) {
						deal = new MyDashboardDeal[customerArray.length];
						for (int i = 0; i < customerArray.length; i++) {
						MyDashboardDeal objRC1 = new MyDashboardDeal();
						objRC1.setUserType(userType);
						objRC1.setStatusIndicator(rcStatusIndicator);
						objRC1.setCustomerId(customerArray[i]);
						objRC1.setCurrentApprovalStatus(currentDealStatus);
						arrMyDashBoradDeal.add(objRC1);
						deal = arrMyDashBoradDeal.toArray(deal);
					} 

				}else{
					MyDashboardDeal objRC1 = new MyDashboardDeal();
					objRC1.setUserType(userType);
					objRC1.setStatusIndicator(rcStatusIndicator);
					objRC1.setCustomerId(customerId);
					objRC1.setCurrentApprovalStatus(currentDealStatus);
					arrMyDashBoradDeal.add(objRC1);
					deal = arrMyDashBoradDeal.toArray(deal);
				}
					dealData = (ResponseEntity<Object>) rightPriceService.getDashboardDealData(deal);
					dealList = (List<Object[]>) dealData.getBody();
					dealdetails = (List<MyDashboardDeal>) dealList;	
					}
					else{
						if (customerId == 0) {
							deal = new MyDashboardDeal[customerArray.length];
							for (int i = 0; i < customerArray.length; i++) {
							MyDashboardDeal objRC1 = new MyDashboardDeal();
							objRC1.setUserType(userType);
							objRC1.setStatusIndicator(rcStatusIndicator);
							objRC1.setCustomerId(customerArray[i]);
							objRC1.setCurrentApprovalStatus(currentDealStatus);
							arrMyDashBoradDeal.add(objRC1);
							deal = arrMyDashBoradDeal.toArray(deal);
						} 

					}else{
						MyDashboardDeal objRC1 = new MyDashboardDeal();
						objRC1.setUserType(userType);
						objRC1.setStatusIndicator(rcStatusIndicator);
						objRC1.setCustomerId(customerId);
						objRC1.setCurrentApprovalStatus(currentDealStatus);
						arrMyDashBoradDeal.add(objRC1);
						deal = arrMyDashBoradDeal.toArray(deal);
					}
						dealData = (ResponseEntity<Object>) 	rightPriceService.getDealStatusData(deal);
						dealdetails = (List<MyDashboardDeal>) dealData.getBody();
						
					}
					
				}
				else{
				dealData = (ResponseEntity<Object>) rightPriceService.getDealDataOnSearch(dealID);
				dealList = (List<Object[]>) dealData.getBody();
				dealdetails = (List<MyDashboardDeal>) dealList;
				}
				}

			ResponseEntity<Object> towerData1 = (ResponseEntity<Object>) rightPriceService.getFpDealTowerData();
			List towerList = (List<Object[]>) towerData1.getBody();
			List<FPDealTower> towerdetails = (List<FPDealTower>) towerList;
			
			ResponseEntity<Object> appCodeListObj = (ResponseEntity<Object>)rightPriceService.getAppCodeData();
			List appCodeList = (List<Object[]>) appCodeListObj.getBody();
			List<AppCode> appCodedetails = (List<AppCode>) appCodeList;

			byte[] bis = ExcelView.getDashboardExcelReport(RC, dealdetails, rateCardStatus, dealStatus, towerdetails,appCodeList);
			AppLoger.APPLOGGER.info("--------------------------bis------------------------------------");
			AppLoger.APPLOGGER.info(bis);

			return ResponseEntity.status(200).header("Content-disposition", "attachment; filename=" + fileName)
					.contentType(MediaType.parseMediaType("application/octet-stream")).body(bis);

		}
	}

	@RequestMapping(value = "/getRateCardManualAttachment/{cityId}/{rcId}", method = RequestMethod.GET, headers = "Accept=application/json")
	public ResponseEntity<Object> getRateCardManualAttachment(@PathVariable("cityId") int cityId,@PathVariable("rcId") int rcId) {
		AppLoger.APPLOGGER.info("RP deal version id: " + cityId);
		return rightPriceService.getRateCardManualAttachment(cityId,rcId);
	}
	
	 @RequestMapping(value = "/changeFileActiveStatus/{attachmentId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	 public ResponseEntity<Object> changeFileActiveStatus(@PathVariable("attachmentId") int attachmentId) {
	return rightPriceService.changeFileActiveStatus(attachmentId);
}
	 

		@RequestMapping(value = "/getWhatIfContractData/{rpVrsId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
		public ResponseEntity<Object> getWhatIfContractData(@PathVariable("rpVrsId") int rpVrsId) {
			return rightPriceService.getWhatIfContractData(rpVrsId);
		}
		
		

		@RequestMapping(value = "/getApprovalMatrixWhatIf/{rpVrsId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
		public ResponseEntity<Object> getApprovalMatrixWhatIf(@PathVariable("rpVrsId") int rpVrsId) {
			return rightPriceService.getApprovalMatrixWhatIf(rpVrsId);
		}

		@RequestMapping(value = "/getUpdatedApprovalMatrix/{rpVrsId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
		public ResponseEntity<Object> getUpdatedApprovalMatrix(@PathVariable("rpVrsId") int rpVrsId) {
			return rightPriceService.getUpdatedApprovalMatrix(rpVrsId);
		}
		

		@RequestMapping(value = "/getFpVersionsReadyToSubmit/{crmDealId}", method = RequestMethod.GET, headers = "Accept=application/json")
		public ResponseEntity<Object> getFpVersionsReadyToSubmit(@PathVariable("crmDealId") String crmDealId) {
			AppLoger.APPLOGGER.info("RP deal id: " + crmDealId);
			return rightPriceService.getFpVersionsReadyToSubmit(crmDealId);
		}
		
		@RequestMapping(value = "/getYearCount/{rpVrsId}", method = RequestMethod.GET, headers = "Accept=application/json")
		public ResponseEntity<Object> getYearCount(@PathVariable("rpVrsId") int versioId) {
			AppLoger.APPLOGGER.info("RP deal id: " + versioId);
			return rightPriceService.getYearCount(versioId);
		}
		
		@ApiOperation(value = "save approval data for Automatic FP RFP/RPI Deal", response = FpDeal.class)
		@RequestMapping(value = "/updateFPApprovalRFPStatus", method = RequestMethod.POST)
		public ResponseEntity<Object> updateFPApprovalRFPDetails(@RequestBody FpDeal[] fpData) {
			return rightPriceService.updateFPApprovalRFPDetails(fpData);
		}
		
		@ApiOperation(value = "update Short Term Annual Allownces", response = BasicAllowanceShortTime.class)
		@RequestMapping(value = "/updateShortTermAllowances", method = RequestMethod.POST)
		public ResponseEntity<Object> updateShortTermAllowances(@RequestBody BasicAllowanceShortTime[] basicShortTermAllowance) {
			return rightPriceService.updateShortTermAllowances(basicShortTermAllowance);
		}
		
		
			@ApiOperation(value = "add Short Term Allownces", response = BasicAllowance.class)
	@RequestMapping(value = "/saveMasterAnnualAllowances", method = RequestMethod.POST)
	public ResponseEntity<Object> saveMasterAnnualAllowances(@RequestBody BasicAllowanceShortTime[] basicShortAllowance) {
		 return rightPriceService.saveMasterAnnualAllowances(basicShortAllowance);
	}
			
			@RequestMapping(value = "/getFpRfpRfiFinalVersion/{crmDealId}", method = RequestMethod.GET, headers = "Accept=application/json")
			public ResponseEntity<Object> getFpRfpRfiFinalVersion(@PathVariable("crmDealId") String crmDealId) {
				AppLoger.APPLOGGER.info("RP deal id: " + crmDealId);
				return rightPriceService.getFpRfpRfiFinalVersion(crmDealId);
			}
			 
			
			@ApiOperation(value = "save Final approval data for Automatic FP RFP/RPI Deal", response = FpDeal.class)
			@RequestMapping(value = "/updateFPRpiRpfFinalApprovalStatus", method = RequestMethod.POST)
			public ResponseEntity<Object> updateFPRpiRpfFinalApprovalStatus(@RequestBody FpDeal fpData) {
				return rightPriceService.updateFPRpiRpfFinalApprovalStatus(fpData);
			}
			
			@ApiOperation(value = "Update Indirect Cost Inputs Data", response = CostBreakup.class)
			@RequestMapping(value = "/updateCostBreakupData", method = RequestMethod.POST)
			public ResponseEntity<Object> updateCostBreakupData(@RequestBody List<CostBreakup> costBreakup) {
				return rightPriceService.updateCostBreakupData(costBreakup);
			}
	
			 @ApiOperation(value = "get Report Details of FPDealCreationRoleSelection", response = StaffingDetails.class)
			   @RequestMapping(value = "/downloadFPDealCreationStaffingExcel/{rpDealVersionId}/{cityId}/{towerId}//{dealId}", method = RequestMethod.GET,produces=MediaType.APPLICATION_OCTET_STREAM_VALUE)
			   public ResponseEntity<Object>downloadFPDealCreationStaffingExcel(@PathVariable("rpDealVersionId") int rpDealVersionId,@PathVariable("cityId") int cityId,@PathVariable("towerId") int towerId,@PathVariable("dealId") String dealId) 
			   {
				
				   String fileName = null;
				   String timeStamp = new SimpleDateFormat("yyyy_MM_dd_hh_mm_ss").format(Calendar.getInstance().getTime());
				   fileName = "FD_"+ rpDealVersionId + "_DealCreationStaffing"+"_"+timeStamp+".xls";
				   ResponseEntity<Object> staffingData = (ResponseEntity<Object>)  rightPriceService.getFpDealStaffingData(rpDealVersionId,cityId,towerId);
				   ResponseEntity<Object> staffingDataHeder = (ResponseEntity<Object>)  rightPriceService.getFpDealStaffingDataExcel(rpDealVersionId,towerId);
				   ResponseEntity<Object> parentHeadingData = (ResponseEntity<Object>) rightPriceService.getVersionData(rpDealVersionId);
				   ResponseEntity<Object> parentHeadingData1 = (ResponseEntity<Object>) rightPriceService.getDealDetailById(dealId,rpDealVersionId);
				   ResponseEntity<Object> parentNooftower = (ResponseEntity<Object>) rightPriceService.getDealTower(rpDealVersionId);
				   ResponseEntity<Object> pName = (ResponseEntity<Object>) rightPriceService.getPractice();;
				   ResponseEntity<Object> spName = (ResponseEntity<Object>) rightPriceService.getSubPracticeS();
				   ResponseEntity<Object> countryName = (ResponseEntity<Object>) rightPriceService.getCountry();
				   ResponseEntity<Object> cityName = (ResponseEntity<Object>) rightPriceService.getCityDataFP();
				   ResponseEntity<Object> bandName = (ResponseEntity<Object>) rightPriceService.getBandDataFPS();
				   ResponseEntity<Object> currencyName = (ResponseEntity<Object>) rightPriceService.getCurrency();
				   List<FpDeal> parentHeadingDataList = (List<FpDeal>) parentHeadingData.getBody();
				   List<FPDealTower> parentNooftowerList = (List<FPDealTower>) parentNooftower.getBody();
				   List<Country> countryList = (List<Country>) countryName.getBody();
				   List<CityFP> cityList = (List<CityFP>) cityName.getBody();
				   List<MasterRole> bandDescList = (List<MasterRole>) bandName.getBody();
				   List<Currency> currencyList = (List<Currency>) currencyName.getBody();
				   List<Practice> pList = (List<Practice>) pName.getBody();
				   List<SubPractice> spList = (List<SubPractice>) spName.getBody();
				   List<StaffingDetails> staffingDataList = (List<StaffingDetails>) staffingData.getBody();
				   List<StaffingDetailsHeader> staffingDataHederList = (List<StaffingDetailsHeader>) staffingDataHeder.getBody();
				   List<Deal> parentHeadingData1List = (List<Deal>) parentHeadingData1.getBody();
				   AppLoger.APPLOGGER.info("FPDealCreationStaffingExcel tower  Details ............ ");
					  // Double dblMultifier = Double.parseDouble(multifier); 	   
					   /*byte []bis = ExcelView.downloadUtilizationRoleExcel(lstYears,lstRCRoleUtilization,lstRCDetails,isBillCurrency,multifier,currencyName,lstRCYOY);*/
				   		byte []bis = ExcelView.downloadFPDealCreationStaffingExcel(parentHeadingDataList,towerId,parentNooftowerList,countryList,cityList,currencyList,staffingDataList,staffingDataHederList,bandDescList,parentHeadingData1List,pList,spList);
				    	return ResponseEntity.status(200).header("Content-disposition",
				    		"attachment; filename="+fileName).contentType(MediaType.parseMediaType("application/octet-stream")).body(bis);	
			   
			   }
			   
			   @RequestMapping(value = "/getFpVersionsWhatIfApproval/{crmDealId}", method = RequestMethod.GET, headers = "Accept=application/json")
				public ResponseEntity<Object> getFpVersionsWhatIfApproval(@PathVariable("crmDealId") String crmDealId) {
					AppLoger.APPLOGGER.info("RP deal id: " + crmDealId);
					return rightPriceService.getFpVersionsWhatIfApproval(crmDealId);
				}
			 

			 @ApiOperation(value = "get Report Details of FPDealCreationRoleSelection", response = StaffingDetails.class)
			   //@RequestMapping(value = "/downloadTMDealCreationStaffingExcel/{rpDealVersionId}/{cityId}/{towerId}/{dealId}/{crmDeal}", method = RequestMethod.GET,produces=MediaType.APPLICATION_OCTET_STREAM_VALUE)
			 @RequestMapping(value = "/downloadTMDealCreationStaffingExcel/{rpDealVersionId}/{cityId}/{towerId}/{dealId}/{crmDeal}", method = RequestMethod.GET,produces=MediaType.APPLICATION_OCTET_STREAM_VALUE)
			   public ResponseEntity<Object>downloadTMDealCreationStaffingExcel(@PathVariable("rpDealVersionId") int rpDealVersionId,@PathVariable("cityId") int cityId,@PathVariable("towerId") int towerId,@PathVariable("dealId") String dealId,@PathVariable("crmDeal") String crmDeal) 
			   {
				
				   String fileName = null;
				   String timeStamp = new SimpleDateFormat("yyyy_MM_dd_hh_mm_ss").format(Calendar.getInstance().getTime());
				   fileName = "TM_"+ rpDealVersionId + "_DealCreationStaffing"+"_"+timeStamp+".xls";
				   ResponseEntity<Object> staffingData = (ResponseEntity<Object>)  rightPriceService.getTMDealStaffingData(cityId,rpDealVersionId);
				   ResponseEntity<Object> staffingDataHeder = (ResponseEntity<Object>)  rightPriceService.getFpDealStaffingDataExcel(rpDealVersionId,towerId);
				   ResponseEntity<Object> countryName = (ResponseEntity<Object>) rightPriceService.getCountry();
				   ResponseEntity<Object> pName = (ResponseEntity<Object>) rightPriceService.getPractice();;
				   ResponseEntity<Object> spName = (ResponseEntity<Object>) rightPriceService.getSubPracticeS();
				   ResponseEntity<Object> headingData = (ResponseEntity<Object>) rightPriceService.getDealDetails(crmDeal,rpDealVersionId);
				   ResponseEntity<Object> cityName = (ResponseEntity<Object>) rightPriceService.getCityDataFP();
				   ResponseEntity<Object> bandName = (ResponseEntity<Object>) rightPriceService.getBandDataFPS();
				   ResponseEntity<Object> currencyName = (ResponseEntity<Object>) rightPriceService.getCurrency();
				   List<Country> countryList = (List<Country>) countryName.getBody();
				   List<CityFP> cityList = (List<CityFP>) cityName.getBody();
				   List<MasterRole> bandDescList = (List<MasterRole>) bandName.getBody();
				   List<Currency> currencyList = (List<Currency>) currencyName.getBody();
				   List<StaffingDetails> staffingDataList = (List<StaffingDetails>) staffingData.getBody();
				   List<StaffingDetailsHeader> staffingDataHederList = (List<StaffingDetailsHeader>) staffingDataHeder.getBody();
				   List<DealDetailsView> headingDataList = (List<DealDetailsView>) headingData.getBody();
				   List<Practice> pList = (List<Practice>) pName.getBody();
				   List<SubPractice> spList = (List<SubPractice>) spName.getBody();
				   AppLoger.APPLOGGER.info("FPDealCreationStaffingExcel tower  Details ............ ");
					  // Double dblMultifier = Double.parseDouble(multifier); 	   
					   /*byte []bis = ExcelView.downloadUtilizationRoleExcel(lstYears,lstRCRoleUtilization,lstRCDetails,isBillCurrency,multifier,currencyName,lstRCYOY);*/
				   		byte []bis = ExcelView.downloadTMDealCreationStaffingExcel(towerId,countryList,cityList,currencyList,staffingDataList,staffingDataHederList,bandDescList,headingDataList,rpDealVersionId,pList,spList);
				    	return ResponseEntity.status(200).header("Content-disposition",
				    		"attachment; filename="+fileName).contentType(MediaType.parseMediaType("application/octet-stream")).body(bis);	
			   
			   }
			 
			 
			 
			 @ApiOperation(value = "get Report Details of Right Price Rate", response = DealIndirectCostInputs.class)
			   @RequestMapping(value = "/downloadFPdealCostInputExcel/{towerId}/{costType}/{rpDealVersionId}/{noOfYears}/{crmDealId}/{rpVrsId}", method = RequestMethod.GET,produces=MediaType.APPLICATION_OCTET_STREAM_VALUE)
			   public ResponseEntity<Object> downloadFPdealCostInputExcel(@PathVariable("towerId") int towerId,@PathVariable("costType") int costType,@PathVariable("rpDealVersionId") int rpDealVersionId,@PathVariable("noOfYears") int noOfYears,@PathVariable("crmDealId") String deald,@PathVariable("rpVrsId") int dealVersionId) 
			   {
				   String fileName = null;
				   String timeStamp = new SimpleDateFormat("yyyy_MM_dd_hh_mm_ss").format(Calendar.getInstance().getTime());
				   fileName = "FP Deal Creation - Cost Inputs (Travel / Relocation)"+"_"+timeStamp+".xls";
				   ResponseEntity<Object> objeRPRate = (ResponseEntity<Object>)rightPriceService.getFpDealCostInputsDetails(towerId,costType,rpDealVersionId);
				   List<DealIndirectCostInputs> rPRateList = 	(List<DealIndirectCostInputs>) objeRPRate.getBody();
				   AppLoger.APPLOGGER.info("Right Price Rate LIst is............ "+ rPRateList.size());
				   
				   ResponseEntity<Object> dealData= (ResponseEntity<Object>) rightPriceService.getDealDetails(deald,dealVersionId);
					List<DealDetailsView> dealList = 	(List<DealDetailsView>) dealData.getBody();
					AppLoger.APPLOGGER.info("City LIst is............ "+ dealList.size());
				   
			       byte []bis = ExcelView.getFPdealCostInputExcelReport(rPRateList,noOfYears,dealList);
			       return ResponseEntity.status(200).header("Content-disposition",
			       		"attachment; filename="+fileName).contentType(MediaType.parseMediaType("application/octet-stream")).body(bis);
			  		//return null;
			  	}
			 
			 @ApiOperation(value = "download Pricing Template Excel", response = FpDeal.class)
			  	@RequestMapping(value = "/downloadPricingTemplateExcel/{dealId}/{versionId}", method = RequestMethod.GET,produces=MediaType.APPLICATION_OCTET_STREAM_VALUE)
			  	public ResponseEntity<Object> downloadFpPricingTemplate(@PathVariable("dealId") int dealId,@PathVariable("versionId") int versionId) {
			  	    String fileName = null;
			  	    
			  		String timeStamp = new SimpleDateFormat("yyyy_MM_dd_hh_mm_ss").format(Calendar.getInstance().getTime());
			  		
			  		fileName = "FP_Pricing_Template"+"_"+timeStamp+".xls";
			  		
			  		/*ResponseEntity<Object> costData= (ResponseEntity<Object>) rightPriceService.getCostBreakupData(deald,dealVersionId);
					
					List[] costList = 	(List[]) costData.getBody();
					List<CostBreakup> cost=costList[0];
					List<DealFixLocation> deal =  costList[1];
					AppLoger.APPLOGGER.info("City LIst is............ ");*/
					
					ResponseEntity<Object> dealData= (ResponseEntity<Object>) rightPriceService.getVersionData(versionId);
					List<FpDeal> dealList = 	(List<FpDeal>) dealData.getBody();
					AppLoger.APPLOGGER.info("City LIst is............ "+ dealList.size());
					
					ResponseEntity<Object> currencyData = (ResponseEntity<Object>) rightPriceService.getCurrency();
					List<Currency> currencyList = (List<Currency>) currencyData.getBody();
					
					ResponseEntity<Object> pricingTemplateData = (ResponseEntity<Object>) rightPriceService.getDealPricingData(versionId);
					List<DealPricingTemplate> pricingTemplateDetails = (List<DealPricingTemplate>) pricingTemplateData.getBody();
					
					
			      byte []bis = ExcelView.getPricingExcelReport(dealList,currencyList,pricingTemplateDetails);
			       
			       return ResponseEntity.status(200).header("Content-disposition",
			       		"attachment; filename="+fileName).contentType(MediaType.parseMediaType("application/octet-stream")).body(bis);
			  	}
			 
				
				 @ApiOperation(value = "get Report Details of Right Price Rate", response = MasterRate.class)
				   @RequestMapping(value = "/downloadFPdealCalculationExcel/{towerId}/{rpDealVersionId}/{crmDealId}", method = RequestMethod.GET,produces=MediaType.APPLICATION_OCTET_STREAM_VALUE)
				   public ResponseEntity<Object> downloadFPdealCalculationExcel(@PathVariable("towerId") int towerId,@PathVariable("rpDealVersionId") int rpDealVersionId,@PathVariable("crmDealId") String crmDealId) 
				   {
					   String fileName = null;
					   String timeStamp = new SimpleDateFormat("yyyy_MM_dd_hh_mm_ss").format(Calendar.getInstance().getTime());
					   fileName = "FP Deal Creation - Calculation Details"+"_"+timeStamp+".xls";
					   ResponseEntity<Object> objeRPRate = (ResponseEntity<Object>) rightPriceService.getFPCostCalCulationOrderedData(towerId,rpDealVersionId);
					   List<FPCalculationDetails> rPRateList = 	(List<FPCalculationDetails>) objeRPRate.getBody();
					   AppLoger.APPLOGGER.info("Right Price Rate LIst is............ "+ rPRateList.size());
					   
					   ResponseEntity<Object> dealData= (ResponseEntity<Object>) rightPriceService.getDealDetails(crmDealId,rpDealVersionId);
						List<DealDetailsView> dealList = 	(List<DealDetailsView>) dealData.getBody();
						AppLoger.APPLOGGER.info("City LIst is............ "+ dealList.size());
					   
					   
				       byte []bis = ExcelView.getFPdealCalculationExcelReport(rPRateList,dealList);
				       return ResponseEntity.status(200).header("Content-disposition",
				       		"attachment; filename="+fileName).contentType(MediaType.parseMediaType("application/octet-stream")).body(bis);
				  		//return null;
				  	}
				
				 
				 @RequestMapping(value = "/getWhatIfDataExcel/{rpVrsId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
					public ResponseEntity<Object> getWhatIfDataExcel(@PathVariable("rpVrsId") int rpVrsId) {
						return rightPriceService.getWhatIfDataExcel(rpVrsId);
					}
					// please don't remove this funcation
					/* @ApiOperation(value = "get Report Details of Rate card Utilization Role")
					   @RequestMapping(value = "/downloadWhatIfExcel/{rpVrsId}", method = RequestMethod.GET,produces=MediaType.APPLICATION_OCTET_STREAM_VALUE)
					   public ResponseEntity<Object>downloadWhatIfExcel(@PathVariable("rpVrsId") int rpVrsId) 
					   {
						   String fileName = null;
						   String timeStamp = new SimpleDateFormat("yyyy_MM_dd_hh_mm_ss").format(Calendar.getInstance().getTime());
						   fileName = "FPDeal_What_If_Application_wise"+"_"+timeStamp+".xls";
						   ResponseEntity<Object> whatIfData = (ResponseEntity<Object>) rightPriceService.getWhatIfExcel(rpVrsId);
						   HashMap<Object, Object> objHashMap = (HashMap) whatIfData.getBody();
						   List<FPDealWhatIf> lstfpDealWhatIf = new ArrayList<FPDealWhatIf>();
						   List<FPDealWhatIfEffort> lstfpDealWhatIfEffort= new ArrayList<FPDealWhatIfEffort>();
						   List<FPWhatIfContractTerms> lstfpDealContractTerms= new ArrayList<FPWhatIfContractTerms>();
						   for(Object object : objHashMap.entrySet()) 
						   {	
							   lstfpDealWhatIf = (List<FPDealWhatIf>) objHashMap.get("whatifdata");		
							   lstfpDealWhatIfEffort = (List<FPDealWhatIfEffort>) objHashMap.get("whatifeffortdata");
							   lstfpDealContractTerms = (List<FPWhatIfContractTerms>) objHashMap.get("whatifcondata");
						   }
						   byte []bis = ExcelView.downloadWhatIfExcel(lstfpDealWhatIf,lstfpDealWhatIfEffort,lstfpDealContractTerms);
					    	return ResponseEntity.status(200).header("Content-disposition",
					    		"attachment; filename="+fileName).contentType(MediaType.parseMediaType("application/octet-stream")).body(bis);	   
					   }*/
			 
				 @RequestMapping(value = "/getFpToExchangeRates/{rpVrsId}/{currencyId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
					public ResponseEntity<Object> getFpToExchangeRates(@PathVariable("rpVrsId") int rpVrsId, @PathVariable("currencyId") int currencyId) {
						return rightPriceService.getFpToExchangeRates(rpVrsId,currencyId);
					}
				 
				 @RequestMapping(value = "/getRateCardOnIndustry/{customerVerticalId}/{industry}", method = RequestMethod.GET, headers = "Accept=application/json")
					public ResponseEntity<Object> getRateCardsBasedOnIndustry(@PathVariable("customerVerticalId") int customerVerticalId,@PathVariable("industry") int industry) {
						AppLoger.APPLOGGER.info("RP deal version id: " + customerVerticalId);
						return rightPriceService.getRateCardsBasedOnIndustry(customerVerticalId,industry);
					}
				 
				 @RequestMapping(value = "/getOldDealDetails/{customerId}/{startDate}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
					public ResponseEntity<Object> getOldDealDetails(@PathVariable("customerId") int customerId,@PathVariable("startDate") String startDate) {
						return rightPriceService.getOldDealDetails(customerId,startDate);
					}
				 
				 
					@ApiOperation(value = "Upload Staffing Excel File", response = FPManualDealAttachment.class)
					@RequestMapping(value = "/uploadStaffingExcel", method = RequestMethod.POST)
					public ResponseEntity<Object> uploadfpDealStaffingFile(@RequestParam(value = "file", required = false) MultipartFile file,
							@RequestParam(value = "name", required = false) String fileName,			
							@FormParam("file") InputStream uploadedInputStream,
							@FormParam("rpDealVersionId") Integer rpDealVersionId,
							@FormParam("dealAutoTowerId")Integer dealAutoTowerId){
						try {
							AppLoger.APPLOGGER.info("File length : " + file);
							if(null != fileName){
								AppLoger.APPLOGGER.info("File name length : " + fileName.toString());
								AppLoger.APPLOGGER.info("Version Id from JS "+ rpDealVersionId);
								AppLoger.APPLOGGER.info("Deal Auto Tower Id from JS "+ dealAutoTowerId);
							}
							return rightPriceService.uploadfpDealStaffingFile(file,fileName,uploadedInputStream,rpDealVersionId,dealAutoTowerId);
						}catch (Exception e) {
							e.printStackTrace();
							return ResponseEntity.status(205)
								       .contentType(MediaType.TEXT_PLAIN)
								       .body("Currently We are facing technical issues, please try again later.");
						}
						
					}
					
					@ApiOperation(value = "Upload Manual rateCard file", response = RateCardManualMarginCal.class)
					@RequestMapping(value = "/uploadEstimationDealFile", method = RequestMethod.POST)
					public ResponseEntity<Object> uploadEstimationDealFile(@RequestParam(value = "file", required = false) MultipartFile file,
							@RequestParam(value = "name", required = false) String fileName,			
							@FormParam("file") InputStream uploadedInputStream,
							@FormParam("versionId") Integer versionId,
							@FormParam("cmnt") String cmnt,
							@FormParam("category") String category,
							@FormParam("docType") String docType
							){
						try {
							AppLoger.APPLOGGER.info("File length : " + file);
							if(null != fileName){
								AppLoger.APPLOGGER.info("File name length : " + fileName.toString());
								AppLoger.APPLOGGER.info("Version Id from JS"+ versionId);
							}
							return rightPriceService.uploadEstimationDealFile(file,fileName,uploadedInputStream,versionId, cmnt,category,docType);
						}catch (Exception e) {
							e.printStackTrace();
							return ResponseEntity.status(205)
								       .contentType(MediaType.TEXT_PLAIN)
								       .body("Currently We are facing technical issues, please try again later.");
						}
						
					}
					
					@ApiOperation(value = "getUploadedDoc", response = UploadedDocuments.class)
					@RequestMapping(value = "/getUploadedEADoc/{versionId}", method = RequestMethod.GET, headers = "Accept=application/json")
					public ResponseEntity<Object> getUploadedEADoc(@PathVariable("versionId") int versionId) {
						AppLoger.APPLOGGER.info("inside controller getUploadedDoc");
						return rightPriceService.getUploadedEADoc(versionId);
					}
					
					@RequestMapping(value = "/deleteEADoc/{attachmentId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
					public ResponseEntity<Object> deleteEADoc(@PathVariable("attachmentId") int attachmentId) {
						return rightPriceService.deleteEADoc(attachmentId);
					}
					
					@RequestMapping(value = "/getCountryByManualFlag/{manualCountryId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
					public ResponseEntity<Object> getCountryByManualFlag(@PathVariable("manualCountryId") int manualCountryId) {
						return rightPriceService.getCountryByManualFlag(manualCountryId);
					}
	
					@ApiOperation(value = "Upload FP Staffing Excel File", response = FPManualDealAttachment.class)
					@RequestMapping(value = "/uploadFPStaffingExcel", method = RequestMethod.POST)
					public ResponseEntity<Object> uploadFPStaffingExcel(@RequestParam(value = "file", required = false) MultipartFile file,
							@RequestParam(value = "name", required = false) String fileName,			
							@FormParam("file") InputStream uploadedInputStream,
							@FormParam("rpDealVersionId") Integer rpDealVersionId,
							@FormParam("cityId")Integer cityId){
						try {
							AppLoger.APPLOGGER.info("File length : " + file);
							if(null != fileName){
								AppLoger.APPLOGGER.info("File name length : " + fileName.toString());
								AppLoger.APPLOGGER.info("Version Id from JS "+ rpDealVersionId);
								AppLoger.APPLOGGER.info("Deal City Id from JS "+ cityId);
							}
							return rightPriceService.uploadfpDealStaffingFile(file,fileName,uploadedInputStream,rpDealVersionId,cityId);
						}catch (Exception e) {
							e.printStackTrace();
							return ResponseEntity.status(205)
								       .contentType(MediaType.TEXT_PLAIN)
								       .body("Currently We are facing technical issues, please try again later.");
						}
						
					}
					
					@RequestMapping(value = "/getAttachment/{rpDealVersionId}/{cityId}", method = RequestMethod.GET, headers = "Accept=application/json")
					public ResponseEntity<Object> getAttachment(@PathVariable("rpDealVersionId") int rpDealVersionId,@PathVariable("cityId") int cityId) {
						AppLoger.APPLOGGER.info("RP deal version id: " + rpDealVersionId);
						return rightPriceService.getAttachment(rpDealVersionId,cityId);
					}
					
					@ApiOperation(value = "Upload TM Staffing Excel File", response = FPManualDealAttachment.class)
					@RequestMapping(value = "/uploadTMStaffingExcel", method = RequestMethod.POST)
					public ResponseEntity<Object> uploadTMStaffingExcel(@RequestParam(value = "file", required = false) MultipartFile file,
							@RequestParam(value = "name", required = false) String fileName,			
							@FormParam("file") InputStream uploadedInputStream,
							@FormParam("rpDealVersionId") Integer rpDealVersionId,
							@FormParam("cityId")Integer cityId){
						try {
							AppLoger.APPLOGGER.info("File length : " + file);
							if(null != fileName){
								AppLoger.APPLOGGER.info("File name length : " + fileName.toString());
								AppLoger.APPLOGGER.info("Version Id from JS "+ rpDealVersionId);
								AppLoger.APPLOGGER.info("Deal City Id from JS "+ cityId);
							}
							return rightPriceService.uploadTMDealStaffingFile(file,fileName,uploadedInputStream,rpDealVersionId,cityId);
						}catch (Exception e) {
							e.printStackTrace();
							return ResponseEntity.status(205)
								       .contentType(MediaType.TEXT_PLAIN)
								       .body("Currently We are facing technical issues, please try again later.");
						}
						
					}

	@ApiOperation(value = "get RiskManagers member data", response = VerticalMemberData.class)
	@RequestMapping(value = "/getRiskManagersMemberDetail/{user}", method = RequestMethod.GET,headers="Accept=application/json")
	 public ResponseEntity<Object> getRiskManagersMemberDetail(@PathVariable("user")String user){
		return rightPriceService.getRiskManagersMemberDetail(user);
	 }
	

	@RequestMapping(value = "/getCountryForexData", method = RequestMethod.GET, headers = "Accept=application/json")
	public ResponseEntity<Object> getCountryForexData() {
		return rightPriceService.getCountryData();
	}
	
	@ApiOperation(value = "get deligate Access for the user", response = ProxyAccess.class)
	@RequestMapping(value = "/getDelicgateUserAccess/{user}", method = RequestMethod.GET,headers="Accept=application/json")
	 public ResponseEntity<Object> getDelicgateUserAccess(@PathVariable("user")String user){
		return rightPriceService.getDelicgateUserAccess(user);
	 }
	
	@ApiOperation(value = "get vertical Based on the user", response = Vertical.class)
	@RequestMapping(value = "/getVerticalApproverData", method = RequestMethod.GET,headers="Accept=application/json")
	 public ResponseEntity<Object> getVerticalApproverData(){
		return rightPriceService.getVerticalApproverData();
	 }

	@RequestMapping(value = "/getDUHCustomerVerticalMapping", method = RequestMethod.POST)
	public ResponseEntity<Object> getDUHCustomerVerticalMapping(@RequestBody Vertical[] vertical) {
		//AppLoger.APPLOGGER.info(rateCardDetails.toString());
		return rightPriceService.getDUHCustomerVerticalMapping(vertical);
	}
	
	@RequestMapping(value = "/getVerticalByCustId/{customerId}", method = RequestMethod.GET,headers="Accept=application/json")
	public ResponseEntity<Object> getVerticalByCustId(@PathVariable("customerId")int customerId){
		return rightPriceService.getVerticalByCustId(customerId);
	}
	
	@RequestMapping(value = "/getCrmDealDetails/{crmDealId}", method = RequestMethod.GET,headers="Accept=application/json")
	public ResponseEntity<Object> getCrmDealDetails(@PathVariable("crmDealId")int crmDealId){
		return rightPriceService.getCrmDealDetails(crmDealId);
	}
	
	@RequestMapping(value = "/saveTcvTMDeal/{tcv}/{rpVrsId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> saveTcvTMDeal(@PathVariable("tcv") int tcv,@PathVariable("rpVrsId") int rpVrsId) {
		return rightPriceService.saveTcvTMDeal(tcv,rpVrsId);
	}
	@RequestMapping(value = "/getStaffingSubContractorPricing/{rpVrsId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> getStaffingSubContractorPricing(@PathVariable("rpVrsId") int rpVrsId) {
		return rightPriceService.getStaffingSubContractorPricing(rpVrsId);
	}
	
	@RequestMapping(value = "/getOldDealDetailsPricing/{oldDealId}", method = RequestMethod.GET,headers="Accept=application/json")
	public ResponseEntity<Object> getOldDealDetailsPricing(@PathVariable("oldDealId")int oldDealId){
		return rightPriceService.getOldDealDetailsPricing(oldDealId);
	}
	@RequestMapping(value = "/getDealsForCust/{customerId}/{dealTypeId}", method = RequestMethod.GET, headers = "Accept=application/json")
	public ResponseEntity<Object> getDealsForCust(@PathVariable("customerId") int customerId, @PathVariable("dealTypeId") int dealTypeId) {
	
		return rightPriceService.getDealsForCust(customerId,dealTypeId);
	}
	
	@RequestMapping(value = "/getDealStatusData", method = RequestMethod.POST, headers = "Accept=application/json")
	public ResponseEntity<Object> getDealStatusData(@RequestBody MyDashboardDeal[] dealData) {
		AppLoger.APPLOGGER.info("Returning policy name for countryId : " + dealData);
		return rightPriceService.getDealStatusData(dealData);
	}
	
	// rcRecycleStatus
	@RequestMapping(value = "/rcRecycleStatus/{rcId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> updateRecycleStatus(@PathVariable("rcId") int rcId) {
		return rightPriceService.updateRecycleStatus(rcId);
	}
	
	@ApiOperation(value = "update rate card utilzation and rates data on save", response = RateCardRoleUtilization.class)
	@RequestMapping(value = "/updateRateUtilizationRates", method = RequestMethod.POST)
	 public ResponseEntity<Object> updateRateUtilizationRates(@RequestBody List<RateCardRoleUtilization> rateCardRoleUtilization){
		AppLoger.APPLOGGER.info("*************updateRateUtilizationAndRates controller");
		return rightPriceService.updateRateUtilizationRates(rateCardRoleUtilization);
	 }
	
	
	
	@ApiOperation(value = "Get all the rate cards related to current user vertical", response = SubPracticeView.class)
	@RequestMapping(value = "/getRateCardInfo/{customerVerticalMappingId}", method = RequestMethod.GET, headers = "Accept=application/json")
	public ResponseEntity<Object> getRateCardInfo(@PathVariable("customerVerticalMappingId") int customerVerticalMappingId) {
		return rightPriceService.getRateCardInfo(customerVerticalMappingId);
	}
	
	
	@ApiOperation(value = "Upload Manual GFT File", response = RateCardManualMarginCal.class)
	@RequestMapping(value = "/uploadManualFile_GFT", method = RequestMethod.POST)
	public ResponseEntity<Object> uploadManualFile_GFT(@RequestParam(value = "file", required = false) MultipartFile file,
			@RequestParam(value = "name", required = false) String fileName,			
			@FormParam("file") InputStream uploadedInputStream,
			@FormParam("rpDealVersionId") int rpDealVersionId,
			@FormParam("dealAutoTowerId") int dealAutoTowerId,
			@FormParam("countryId") int countryId){
		
		try {
			AppLoger.APPLOGGER.info("File length : " + file);
			if(null != fileName){
				AppLoger.APPLOGGER.info("File name length : " + fileName.toString());
				AppLoger.APPLOGGER.info("RateCard Id from JS"+ rpDealVersionId);
			}
			return rightPriceService.uploadManualRateCard_GFT(file,fileName,uploadedInputStream,rpDealVersionId,dealAutoTowerId,countryId);
		}catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(205)
				       .contentType(MediaType.TEXT_PLAIN)
				       .body("Currently We are facing technical issues, please try again later.");
		}
		
	}
	
	@RequestMapping(value = "/getRateGFTCountry/{rcId}", method = RequestMethod.GET, headers = "Accept=application/json")
	public ResponseEntity<Object> getRateGFTCountry(@PathVariable("rcId") int rcId) {
		return rightPriceService.getRateGFTCountry(rcId);
	}
	
	@RequestMapping(value = "/getFpDealRateCardOnVersion/{dealVersionId}", method = RequestMethod.GET, headers = "Accept=application/json")
	public ResponseEntity<Object> getFpDealRateCardOnVersion(@PathVariable("dealVersionId") int dealVersionId) {
		AppLoger.APPLOGGER.info("RP deal version id: " + dealVersionId);
		return rightPriceService.getFpDealRateCard(dealVersionId);
	}
	
	@RequestMapping(value = "/getUserName/{lanId}", method = RequestMethod.GET, headers = "Accept=application/json")
	public ResponseEntity<Object> getUserName(@PathVariable("lanId") String lanId) {
		return rightPriceService.getUserName(lanId);
	}
	
	@RequestMapping(value = "/getApproverData/{rcId}", method = RequestMethod.GET, headers = "Accept=application/json")
	public ResponseEntity<Object> getApproverConfig(@PathVariable("rcId") int rcId) {
		return rightPriceService.getApproverConfig(rcId);
		
	}
	
	@RequestMapping(value = "/getFpDealStaffingDataWhatIf/{rpDealVersionId}", method = RequestMethod.GET, headers = "Accept=application/json")
	public ResponseEntity<Object> getFpDealStaffingData(@PathVariable("rpDealVersionId") int rpDealVersionId) {
		AppLoger.APPLOGGER.info("RP deal version id: " + rpDealVersionId);
		return rightPriceService.getFpDealStaffingDataWhatIf(rpDealVersionId);
	}
	
	@RequestMapping(value = "/getAtosRcData", method = RequestMethod.GET, headers = "Accept=application/json")
	public ResponseEntity<Object> getAtosRcData() {
		return rightPriceService.getAtosRcData();
	}

	@ApiOperation(value = "add practice details", response = Country.class)
	@RequestMapping(value = "/addPracticeDetails", method = RequestMethod.POST)
	public ResponseEntity<Object> addPracticeDetails(@RequestBody MasterPractice practiceDetails) {
		AppLoger.APPLOGGER.info("inside add practice controller");
		return rightPriceService.addPracticeDetails(practiceDetails);
	}
	
	@ApiOperation(value = "update practice details", response = MasterPractice.class)
	@RequestMapping(value = "/updatePracticeDetails", method = RequestMethod.POST)
	public ResponseEntity<Object> updatePracticeDetails(@RequestBody MasterPractice practiceDetails) {
		AppLoger.APPLOGGER.info("inside update controller");
		try
		{
			return rightPriceService.updatePracticeDetails(practiceDetails);
		}
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();					
			return ResponseEntity.status(205).contentType(MediaType.TEXT_PLAIN).body("Currently We are facing technical issues, please try again later.");
		}
	}
	
	@RequestMapping(value = "/getActiveRCData", method = RequestMethod.GET, headers = "Accept=application/json")
	public ResponseEntity<Object> getActiveRCData() {
		return rightPriceService.getActiveRCData();
	}

	/*@RequestMapping(value = "/getGFTWeeklyData", method = RequestMethod.GET, headers = "Accept=application/json")
	public ResponseEntity<Object> getGFTWeeklyData() {
		return rightPriceService.getGFTWeeklyData();
	}*/
	
	@RequestMapping(value = "/getRCComment/{rcId}", method = RequestMethod.GET, headers = "Accept=application/json")
	public ResponseEntity<Object> getRCComment(@PathVariable("rcId")  int rcId) {
		return rightPriceService.getRCComment(rcId);
	}
	
	@RequestMapping(value = "/saveComment/{rcId}/{comment}", method = RequestMethod.GET, headers = "Accept=application/json")
	public ResponseEntity<Object> saveComment(@PathVariable("rcId")  int rcId,@PathVariable("comment")  String comment) {
		return rightPriceService.saveComment(rcId,comment);
	}
	
	@RequestMapping(value = "/getFileredAtosRcData/{country}/{startDate}/{endDate}/{action}", method = RequestMethod.GET, headers = "Accept=application/json")
	public ResponseEntity<Object> getFileredAtosRcData(@PathVariable("country")  int country,@PathVariable("startDate")  String startDate,@PathVariable("endDate")  String endDate,@PathVariable("action")  int action) {
		return rightPriceService.getFileredAtosRcData(country,startDate,endDate,action);
	}
	
	@RequestMapping(value = "/getCommentData", method = RequestMethod.GET, headers = "Accept=application/json")
	public ResponseEntity<Object> getCommentData() {
		return rightPriceService.getCommentData();
	}
	
	@RequestMapping(value = "/getStatusData", method = RequestMethod.GET, headers = "Accept=application/json")
	public ResponseEntity<Object> getStatusData() {
		return rightPriceService.getStatusData();
	}
	
	
	
	@ApiOperation(value = "get Report Details of Basic Allowances", response = MyDashboardRC.class)
	@RequestMapping(value = "/downloadSDealReportExcel/{startdate}/{enddate}/{status}/{vertical}/{verticalName}", method = RequestMethod.GET,produces=MediaType.APPLICATION_OCTET_STREAM_VALUE)
	public ResponseEntity<Object> downloadSDealReportExcel(@PathVariable("startdate") String startdate,@PathVariable("enddate") String enddate,
			@PathVariable("status") String status, @PathVariable("vertical") int vertical,@PathVariable("verticalName") String verticalName ) 
	{
		ResponseEntity<Object> sdeal = (ResponseEntity<Object>)rightPriceService.getStatusData(startdate,enddate,status,vertical);

		List DealList = (List<Object[]>) sdeal.getBody();
		List<SDealReport> DealData = (List<SDealReport>) DealList;

		String currentDate = this.auditTrails.getCurrentTimeStamp(); 
		//String currentUser = this.auditTrails.getCurrentUser();
		
		ResponseEntity<Object> userName = (ResponseEntity<Object>) rightPriceService.getUserName(this.auditTrails.getCurrentUser());
		String currentUser = (String) userName.getBody();
		
		for(int i = 0;i<DealData.size();i++){
			ResponseEntity<Object> updatedName = (ResponseEntity<Object>) rightPriceService.getUserName(DealData.get(i).getUpdated_By());
			DealData.get(i).setUpdated_By((String) updatedName.getBody());
		}
		
		byte[] bis = ExcelView.getDealSummeryReport(DealData,vertical,verticalName,currentDate,currentUser);
		AppLoger.APPLOGGER.info("--------------------------bis------------------------------------");
		AppLoger.APPLOGGER.info(bis);
		String timeStamp = new SimpleDateFormat("yyyy_MM_dd_hh_mm_ss").format(Calendar.getInstance().getTime());
		
		String fileName = "Deal_Summery_Report" + "_" + timeStamp + ".xls";
		
		return ResponseEntity.status(200).header("Content-disposition", "attachment; filename=" + fileName)
				.contentType(MediaType.parseMediaType("application/octet-stream")).body(bis);
	}
	
/*	@RequestMapping(value = "/getPreSalesData", method = RequestMethod.GET, headers = "Accept=application/json")
	public ResponseEntity<Object> getPreSalesData() {
		return rightPriceService.getPreSalesData();
	}
	*/
	
	@ApiOperation(value = "get list of category", response = atosDataPrePrivacy.class)
	@RequestMapping(value = "/setSubmitEnabled", method = RequestMethod.GET,headers="Accept=application/json")
	 public ResponseEntity<Object> setSubmitEnabled(){
		return rightPriceService.setSubmitEnabled();
	 }
	
	@ApiOperation(value = "get list of category", response = atosDataPrePrivacy.class)
	@RequestMapping(value = "/setSubmitDisabled", method = RequestMethod.GET,headers="Accept=application/json")
	 public ResponseEntity<Object> setSubmitDisabled(){
		AppLoger.APPLOGGER.info("setSubmit Disabled called =====");
		return rightPriceService.setSubmitDisabled();
	 }
	
	/*@RequestMapping(value = "/getPreSalesDataOnId/{salesId}", method = RequestMethod.GET, headers = "Accept=application/json")
	public ResponseEntity<Object> getPreSalesDataOnId(@PathVariable("salesId") int salesId) {
		return rightPriceService.getPreSalesDataOnId(salesId);
	}
	
	@RequestMapping(value = "/getPreSalesApprovedData/{salesId}", method = RequestMethod.GET, headers = "Accept=application/json")
	public ResponseEntity<Object> getPreSalesApprovedData(@PathVariable("salesId") int salesId) {
		return rightPriceService.getPreSalesApprovedData(salesId);
	}
	
	@RequestMapping(value = "/getPreSalesApprovalData/{salesId}", method = RequestMethod.GET, headers = "Accept=application/json")
	public ResponseEntity<Object> getPreSalesApprovalData(@PathVariable("salesId") int salesId) {
		return rightPriceService.getPreSalesApprovalData(salesId);
	}
	
	@RequestMapping(value = "/getOpportunityDetails", method = RequestMethod.GET, headers = "Accept=application/json")
	public ResponseEntity<Object> getOpportunityDetails() {
		return rightPriceService.getOpportunityDetails();
	}
	
	@RequestMapping(value = "/putOpportunityDealData/{dealId}", method = RequestMethod.GET, headers = "Accept=application/json")
	public ResponseEntity<Object> putOpportunityDealData(@PathVariable("dealId")  int dealId ) {
		return rightPriceService.putOpportunityDealData(dealId);
	}
	
	@RequestMapping(value = "/getCurrencyCode", method = RequestMethod.GET, headers = "Accept=application/json")
	public ResponseEntity<Object> getCurrencyCode() {
		return rightPriceService.getCurrencyCode();
	}
	
	@ApiOperation(value = "Add new rate card")
	@RequestMapping(value = "/saveBudgetDetails", method = RequestMethod.POST)
	public ResponseEntity<Object> saveBudgetDetails(@RequestBody Presales preSalesDetails) {
		return rightPriceService.saveBudgetDetails(preSalesDetails);
	}
	
	@ApiOperation(value = "Submit the Budget Details")
	@RequestMapping(value = "/submitBudgetDetails", method = RequestMethod.POST)
	public ResponseEntity<Object> submitBudgetDetails(@RequestBody Presales preSalesDetails) {
		return rightPriceService.submitBudgetDetails(preSalesDetails);
	}*/
	
	/*@ApiOperation(value = "Search Approved Report Data")
	@RequestMapping(value = "/searchDataOnId", method = RequestMethod.POST)
	public ResponseEntity<Object> searchDataOnId(@RequestBody Presales preSalesDetails) {
		return rightPriceService.searchDataOnOid(preSalesDetails);
	}*/

/*	@ApiOperation(value = "Update Report OppID")
	@RequestMapping(value = "/mapDataOnId", method = RequestMethod.POST)
	public ResponseEntity<Object> mapDataOnId(@RequestBody Presales preSalesDetails) {
		return rightPriceService.mapDataOnId(preSalesDetails);
	}*/
	
/*	@RequestMapping(value = "/getPresalesOpportunityDetails", method = RequestMethod.GET, headers = "Accept=application/json")
	public ResponseEntity<Object> getPresalesOpportunityDetails() {
		return rightPriceService.getPresalesOpportunityDetails();
   } */
	
	@RequestMapping(value = "/getUserNameById/{userId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<String> getUserNameByLanId(@PathVariable("userId") String userId) {
		return rightPriceService.getUserNameByLanId(userId);
	}
	@RequestMapping(value = "/getSyntelUserId/{userId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> getSyntelUserId(@PathVariable("userId") String userId) {
		return rightPriceService.getSyntelUserId(userId);
	}
	@RequestMapping(value = "/getUserRoleData/{usreName}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<String> getUserRoleData(@PathVariable("usreName") String usreName) {
//		usreName = auditTrails.getCurrentUser();
		AppLoger.APPLOGGER.info("Current user id:::::::::::::::::::::::::::::::::::::::::::::"+usreName);
		return rightPriceService.getUserRoleData(usreName);
	}
	@ApiOperation(value = "save the calculated Margin Details", response = RateCardManualMarginCal.class)
	@RequestMapping(value = "/uploadMastersFile", method = RequestMethod.POST)
	public ResponseEntity<Object> uploadMasterFile(@RequestParam(value = "file", required = false) MultipartFile file,
			@RequestParam(value = "name", required = false) String fileName,			
			@FormParam("file") InputStream uploadedInputStream,
			@FormParam("rpDealVersionId") int rpDealVersionId){
		
		try {
			AppLoger.APPLOGGER.info("File length : " + file);
			if(null != fileName){
				AppLoger.APPLOGGER.info("File name length : " + fileName.toString());
				AppLoger.APPLOGGER.info("RateCard Id from JS"+ rpDealVersionId);
			}
			return rightPriceService.uploadMasterFile(file,fileName,uploadedInputStream,rpDealVersionId);
		}catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(205)
				       .contentType(MediaType.TEXT_PLAIN)
				       .body("Currently We are facing technical issues, please try again later.");
		}
		
	}
	
	@ApiOperation(value = "getMasterAttachement", response = AttachmentMapper.class)
	@RequestMapping(value = "/getMasterAttachement", method = RequestMethod.POST)
	 public ResponseEntity<Object> getMasterAttachement(@RequestBody AttachmentMapper rpDealVersionId){
		return rightPriceService.getMasterAttachement(rpDealVersionId);
		 }
	

	@ApiOperation(value = "save the calculated Margin Details", response = RateCardManualMarginCal.class)
	@RequestMapping(value = "/uploadAutomaticRURFile", method = RequestMethod.POST)
	public ResponseEntity<Object> uploadAutomaticRURFile(@RequestParam(value = "file", required = false) MultipartFile file,
			@RequestParam(value = "name", required = false) String fileName,			
			@FormParam("file") InputStream uploadedInputStream,
			@FormParam("rpDealVersionId") int rpDealVersionId){
		
		try {
			AppLoger.APPLOGGER.info("File length : " + file);
			if(null != fileName){
				AppLoger.APPLOGGER.info("File name length : " + fileName.toString());
				AppLoger.APPLOGGER.info("RateCard Id from JS"+ rpDealVersionId);
			}
			return rightPriceService.uploadAutomaticRURFile(file,fileName,uploadedInputStream,rpDealVersionId);
		}catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(205)
				       .contentType(MediaType.TEXT_PLAIN)
				       .body("Currently We are facing technical issues, please try again later.");
		}
		
	}
	
	@ApiOperation(value = "getRCRoleUtilizationAttachement", response = AttachmentMapper.class)
	@RequestMapping(value = "/getRCRoleUtilizationAttachement", method = RequestMethod.POST)
	 public ResponseEntity<Object> getRCRoleUtilizationAttachement(@RequestBody AttachmentMapper rpDealVersionId){
		return rightPriceService.getRCRoleUtilizationAttachement(rpDealVersionId);
		 }
	
	@RequestMapping(value = "/getFpDealCostInputDataUpdate/{towerId}/{rpdealVersionId}", method = RequestMethod.GET, headers = "Accept=application/json")
	public ResponseEntity<Object> getFpDealCostInputsDetailsUpdate(@PathVariable("towerId") int towerId,@PathVariable("rpdealVersionId") int rpdealVersionId) {
		return rightPriceService.getFpDealCostInputsDetailsUpdate(towerId,rpdealVersionId);
	}
	   
	   @ApiOperation(value = "get ratecard location details", response = RateCardLocation.class)
		@RequestMapping(value = "/getRcLocationdetails/{rcId}", method = RequestMethod.GET, headers = "Accept=application/json")
		public ResponseEntity<Object> getRcLocationdetails(@PathVariable("rcId") int rcId) {
			AppLoger.APPLOGGER.info("rc id-------------" + rcId);
			System.out.println(rcId);
			return rightPriceService.getRcLocationdetails(rcId);
		}
		
	/*@ApiOperation(value = "saveSFAppointmentData", response = SyntelSFAppointment.class)
	@RequestMapping(value = "/saveSFAppointmentData", method = RequestMethod.POST)
	public ResponseEntity<Object> saveSFAppointmentData(@RequestBody SyntelSFAppointment syntelAppointmentDetails) {
		return rightPriceService.saveSFAppointmentData(syntelAppointmentDetails);
	}*/
	   
	   @ApiOperation(value = "get ratecard location details", response = RateCardLocation.class)
		@RequestMapping(value = "/getDisabledCountryData/{country}", method = RequestMethod.GET, headers = "Accept=application/json")
		public ResponseEntity<Object> getDisabledCountryData(@PathVariable("country") int countryId) {
			AppLoger.APPLOGGER.info("rc id-------------" + countryId);
			System.out.println(countryId);
			return rightPriceService.getDisabledCountryData(countryId);
		}
	
	   
	   @ApiOperation(value = "get ratecard location details", response = RateCardLocation.class)
		@RequestMapping(value = "/getFPDealSFInfo/{dealId}", method = RequestMethod.GET, headers = "Accept=application/json")
		public ResponseEntity<Object> getFPDealSFInfo(@PathVariable("dealId") String dealId) {
			
			return rightPriceService.getFPDealSFInfo(dealId);
		}
	   @ApiOperation(value = "get ratecard location details", response = RateCardLocation.class)
		@RequestMapping(value = "/CheckUserAccess/{accId}", method = RequestMethod.GET, headers = "Accept=application/json")
		public ResponseEntity<Object> CheckUserAccess(@PathVariable("accId") Integer accId) {
			
			return rightPriceService.CheckUserAccess(accId);
		}
	   @ApiOperation(value = "get ratecard location details", response = RateCardLocation.class)
		@RequestMapping(value = "/checkCustomerMappingInFin/{crmdealID}", method = RequestMethod.GET, headers = "Accept=application/json")
		public ResponseEntity<Object> checkCustomerMappingInFin(@PathVariable("crmdealID") String crmdealId) {
			
			return rightPriceService.checkCustomerMappingInFin(crmdealId);
		}
	   
	   @ApiOperation(value = "get ratecard location details", response = RateCardLocation.class)
		@RequestMapping(value = "/getDealDetailsForTM_Selection/{crmdealID}", method = RequestMethod.GET, headers = "Accept=application/json")
		public ResponseEntity<Object> getDealDetailsForTM_Selection(@PathVariable("crmdealID") String crmdealId) {
			
			return rightPriceService.getDealDetailsForTM_Selection(crmdealId);
		}
	   
	   @RequestMapping(value = "/getAppCodeData", method = RequestMethod.GET, headers = "Accept=application/json")
		public ResponseEntity<Object> getAppCodeData() {
			return rightPriceService.getAppCodeData();
		}
	   
	   @RequestMapping(value = "/getDistDedCatId", method = RequestMethod.GET, headers = "Accept=application/json")
		public ResponseEntity<Object> getDistDedCatId() {
			return rightPriceService.getDistDedCatId();
		}
		 
	   @ApiOperation(value = "getMasterDeductionDataForm", response = AttachmentMapper.class)
		@RequestMapping(value = "/getMasterDeductionDataForm", method = RequestMethod.POST)
		 public ResponseEntity<Object> getMasterDeductionDataForm(@RequestBody Deduction2 deduction){
			return rightPriceService.getMasterDeductionDataForm(deduction);
			 }
	   

	   @RequestMapping(value = "/getDealDetailsForGFT", method = RequestMethod.GET, headers = "Accept=application/json")
		public ResponseEntity<Object> getDealDetailsForGFT() {
			return rightPriceService.getDealDetailsForGFT();
		}
		 
	  
	   @ApiOperation(value = "get ratecard location details", response = DealDetails_view.class)
		@RequestMapping(value = "/getviewdealdetails/{customerId}", method = RequestMethod.GET, headers = "Accept=application/json")
		public ResponseEntity<Object> getviewdealdetails(@PathVariable("customerId") int customerId) {
			
			return rightPriceService.getviewdealdetails(customerId);
		}
	   
	   
	   @ApiOperation(value = "Saving the data of the Rate Card", response=RateCardDetailsNew.class)
	   @RequestMapping(value = "/RateCardDetails_New", method = RequestMethod.POST)
	   public ResponseEntity<Object> saveRateCardDetailsNew(@RequestBody
	   RateCardDetailsNew rateCardDetailsNew) { try {
	   AppLoger.APPLOGGER.info("Data received " + rateCardDetailsNew); return
	   rightPriceService.saveRateCardDetailsNew(rateCardDetailsNew);
	   } catch (Exception e) { AppLoger.APPLOGGER.info("Exception : " + e); } return
	   null; }
	   @ApiOperation(value = "Get rate card details for selected rate card id", response = SubPracticeView.class)
	   @RequestMapping(value = "/getRateCardDetailsFromRCIdGFT/{rcId}", method = RequestMethod.GET, headers = "Accept=application/json")
	   public ResponseEntity<Object> getRateCardDetailsFromRCIdGFT(@PathVariable("rcId") int rcId) {
	   AppLoger.APPLOGGER.info("Fetching details for rate card id : " + rcId);
	   return rightPriceService.getRateCardDetailsGFT(rcId);
	   }
	   
	   
	    @ApiOperation(value = "Get all the rate cards related to current user vertical", response = SubPracticeView.class)
	   @RequestMapping(value = "/getRateCardsNew/{customerVerticalMappingId}", method = RequestMethod.GET, headers = "Accept=application/json")
	   public ResponseEntity<Object> getRateCardsNew(@PathVariable("customerVerticalMappingId") int customerVerticalMappingId) {
	   return rightPriceService.getRateCardsNew(customerVerticalMappingId);
	   }

	   @ApiOperation(value = "Upload Manual Rate Card file", response =
	   RateCardAttachment.class)
	   @RequestMapping(value = "/uploadRCFileNew", method = RequestMethod.POST)
	   public ResponseEntity<Object> uploadManualRCFileNew(@RequestParam(value =
	   "file", required = false) MultipartFile file,
	   @RequestParam(value = "name", required = false) String fileName,
	   @FormParam("file") InputStream uploadedInputStream,
	   @FormParam("rcId") Integer rcId,
	   @FormParam("Id") Integer Id){ try { AppLoger.APPLOGGER.info("File length : "
	   + file); if(null != fileName){ AppLoger.APPLOGGER.info("File name length : "
	   + fileName.toString()); AppLoger.APPLOGGER.info("Version Id from JS "+ rcId);
	   } return
	   rightPriceService.uploadManualFileNew(file,fileName,uploadedInputStream,rcId,
	   Id); }catch (Exception e) { e.printStackTrace(); return
	   ResponseEntity.status(205) .contentType(MediaType.TEXT_PLAIN)
	   .body("Currently We are facing technical issues, please try again later."); }
	   }
	   /*
	   * @ApiOperation(value = "getFileData")
	   *
	   * @RequestMapping(value = "/getFileData", method = RequestMethod.GET) public
	   * ResponseEntity<Object> getFileData(@PathVariable("rcd") String rcId) {
	   * AppLoger.APPLOGGER.info(rcId); return rightPriceService.getFileData(rcId); }
	   */
	   @ApiOperation(value = "getMasterAttachementRC", response = AttachmentRCMapper.class)
	   @RequestMapping(value = "/getMasterAttachementRC", method = RequestMethod.POST)
	   public ResponseEntity<Object> getMasterAttachementRC(@RequestBody AttachmentRCMapper rcId){
	   return rightPriceService.getMasterAttachementRC(rcId);
	   }
	   @RequestMapping(value = "/downloadFileWithNameRC/{RateCard_Attachemt_ID}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	   public ResponseEntity<Object> downloadFileWithFileNameRC(@PathVariable("RateCard_Attachemt_ID") int Id) {
	   return rightPriceService.downloadFileWithFileNameRC(Id);
	   }
	   @RequestMapping(value = "/deleteRCFile/{AttachmentId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	   public ResponseEntity<Object> deleteRCFile(@PathVariable("AttachmentId") int AttachmentId) {
	   return rightPriceService.deleteRCFile(AttachmentId);
	   }
	   
	   /*Deal screen*/
	   @ApiOperation(value = "get Deal Details based on customer id")
		@RequestMapping(value = "/getDealId/{customerId}", method = RequestMethod.GET, headers = "Accept=application/json")
		public ResponseEntity<Object> getDealId(@PathVariable("customerId") int customerId ) {		
			return rightPriceService.getDealId(customerId);
		}
	   
	   @RequestMapping(value = "/getAllDealsFrmCrmStages/{customerId}/{dealTypeId}", method = RequestMethod.GET, headers = "Accept=application/json")
		public ResponseEntity<Object> getAllDealsFrmCrmStages(@PathVariable("customerId") int customerId, @PathVariable("dealTypeId") int dealTypeId) {
			System.out.println(" 307controller getDealsFrmCrmStages*********************************************");
			//AppLoger.APPLOGGER.info("Returning category for countryId : " + customerId);
			return rightPriceService.getAllDealsFrmCrmStages(customerId,dealTypeId);
		}
	   

		@RequestMapping(value = "/getAllDealsForCust/{customerId}/{dealTypeId}", method = RequestMethod.GET, headers = "Accept=application/json")
		public ResponseEntity<Object> getAllDealsForCust(@PathVariable("customerId") int customerId, @PathVariable("dealTypeId") int dealTypeId) {
		
			return rightPriceService.getAllDealsForCust(customerId,dealTypeId);
		}
		
	   //@ApiOperation(value = "get ratecard location details", response = RateCardLocation.class)
		@RequestMapping(value = "/getDealDetailsFor_Selection/{crmdealID}", method = RequestMethod.GET, headers = "Accept=application/json")
		public ResponseEntity<Object> getDealDetailsFor_Selection(@PathVariable("crmdealID") String crmdealId) {
			
			return rightPriceService.getDealDetailsFor_Selection(crmdealId);
		}
	   
		@ApiOperation(value = "Add Deal Data")
		@RequestMapping(value = "/saveDealData", method = RequestMethod.POST)
		public ResponseEntity<Object> saveDealData(@RequestBody DealCreationDetails_RP_V2 dealDetails) {
			AppLoger.APPLOGGER.info(dealDetails.toString());
			return rightPriceService.saveDealData(dealDetails);
		}
		
		
		
		/*
		 * @ApiOperation(value = "Upload File", response = DealAttachmentV2.class)
		 * 
		 * @RequestMapping(value = "/uploadDealsFile", method = RequestMethod.POST)
		 * public ResponseEntity<Object> uploadDealsFile(
		 * 
		 * @RequestParam(value = "file", required = false) MultipartFile file,
		 * 
		 * @RequestParam(value = "name", required = false) String fileName,
		 * 
		 * 
		 * @FormParam("file") InputStream uploadedInputStream) {
		 * 
		 * try { AppLoger.APPLOGGER.info("File length : " + file); if (null != fileName)
		 * { AppLoger.APPLOGGER.info("File name length : " + fileName.toString());
		 * 
		 * } return rightPriceService.uploadDealsFile(file, fileName,
		 * uploadedInputStream); } catch (Exception e) { e.printStackTrace(); return
		 * ResponseEntity.status(205).contentType(MediaType.TEXT_PLAIN)
		 * .body("Currently We are facing technical issues, please try again later."); }
		 * 
		 * }
		 */
		
		@ApiOperation(value = "uploadTMFileData file", response = DealAttachmentV2.class)
		@RequestMapping(value = "/uploadDealsFile", method = RequestMethod.POST)
		public ResponseEntity<Object> uploadDealsFile(
				@FormParam(value = "file") MultipartFile file,
				 @RequestParam(value = "filename") String filename,	
				 
				 @FormParam(value="file") InputStream uploadedInputStream,
				 @RequestParam(value= "dealId") String crmDealId)
			{
			System.out.println("uploadFileData========================================C");
			try {
				AppLoger.APPLOGGER.info("TM File length : " + file);
				if(null != filename){
					AppLoger.APPLOGGER.info("File name length : " + filename.toString());
					AppLoger.APPLOGGER.info("Version Id from JS "+ crmDealId);
				}
				return rightPriceService.uploadDealsFile(file,filename,crmDealId,uploadedInputStream);

			}catch (Exception e) {
				e.printStackTrace();
				return ResponseEntity.status(205)
					       .contentType(MediaType.TEXT_PLAIN)
					       .body("Currently We are facing technical issues, please try again later.");
			}
			
		}
		
	
		@ApiOperation(value = "getFileData")
		@RequestMapping(value = "/getFileData", method = RequestMethod.GET)
		public ResponseEntity<Object> getFileData(@PathVariable("crmDealId") String crmDealId) {
			AppLoger.APPLOGGER.info(crmDealId);
			return rightPriceService.getFileData(crmDealId);
		}
		@RequestMapping(value = "/downloadDealFileWithFileName/{dealAttachmentId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
		public ResponseEntity<Object> downloadDealFileWithFileName(@PathVariable("dealAttachmentId") int dealAttachmentId) {
			return rightPriceService.downloadDealFileWithFileName(dealAttachmentId);
		}
		@RequestMapping(value = "/deleteDealFile/{dealAttachmentId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
		 public ResponseEntity<Object> deleteDealFile(@PathVariable("dealAttachmentId") int dealAttachmentId) {
		return rightPriceService.deleteDealFile(dealAttachmentId);
	}
		@ApiOperation(value = "getFileAttachementData", response = AttachmentMapper2.class)
		@RequestMapping(value = "/getFileAttachementData", method = RequestMethod.POST)
		 public ResponseEntity<Object> getFileAttachementData(@RequestBody AttachmentMapper2 crmDealId) {
			 System.out.println("getFileAttachementData====================================================");
		return rightPriceService.getFileAttachementData(crmDealId);
	}
		
		@ApiOperation(value = "get ratecard location details", response = Ratecard_view.class)
 		@RequestMapping(value = "/getviewrcdetails/{customerId}", method = RequestMethod.GET, headers = "Accept=application/json")
 		public ResponseEntity<Object> getviewrcdetails(@PathVariable("customerId") int customerId) {
 			
 			return rightPriceService.getviewrcdetails(customerId);
 		}
   
		
		/*@RequestMapping(value = "/getviewrcdetails", method = RequestMethod.POST)
		public ResponseEntity<Object> getviewrcdetails(@RequestBody Ratecard_view[] rateCardDetails){
			AppLoger.APPLOGGER.info("Data received in controller for getRateCardData " + rateCardDetails.length);
			return rightPriceService.getviewrcdetails(rateCardDetails);
		}
		*/
		
		@ApiOperation(value = "get Ratecard detail", response = Ratecard_view.class)
		@RequestMapping(value = "/getDataOnSearchview/{rcId}", method = RequestMethod.GET, headers = "Accept=application/json")
		public ResponseEntity<Object> getDataOnSearchview(@PathVariable("rcId") int rcId) {
			AppLoger.APPLOGGER.info("inside getLeadershipDetails");
			return rightPriceService.getDataOnSearchview(rcId);
		}
		
		@ApiOperation(value = "get Deal detail", response = DealDetails_view.class)
		@RequestMapping(value = "/getDealDataOnSearchview/{dealId}", method = RequestMethod.GET, headers = "Accept=application/json")
		public ResponseEntity<Object> getDealDataOnSearchview(@PathVariable("dealId") String dealId) {
			AppLoger.APPLOGGER.info("inside getLeadershipDetails");
			return rightPriceService.getDealDataOnSearchview(dealId);
		}
		
		@ApiOperation(value = "getEmpRoleDetails", response = EmpDetails.class)
		@RequestMapping(value = "/getEmpRoleDetails", method = RequestMethod.POST)
		public ResponseEntity<Object> getEmpRoleDetails(@RequestBody EmpDetails empDetails) {
			return rightPriceService.getEmpRoleDetails(empDetails);
		}

		
		@ApiOperation(value = "Access Control Mapping", response = RpRole.class)
		@RequestMapping(value = "/addRProleData", method = RequestMethod.POST)
		public ResponseEntity<Object> addRProleData(@RequestBody FPDCRCAndProjectDetails role) {
			System.out.println("rpAccessControls......." + role.getRpRoleAccess().size());
			return rightPriceService.addRProleData(role);
		}
		
		@ApiOperation(value = "view approver Name based on vertical id")
		@RequestMapping(value = "/getDealDetailss/{crmDealId}", method = RequestMethod.GET, headers = "Accept=application/json")
		public ResponseEntity<Object> getDealDetailss(@PathVariable("crmDealId") String crmDealId  ) {
			

			return rightPriceService.getDealDetailss(crmDealId);
		}
		
		
		@RequestMapping(value = "/downloadVideos/{type}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
		public ResponseEntity<Object> downloadVideos(@PathVariable("type") int type) {
		return rightPriceService.downloadVideos(type);
		}
		
		@RequestMapping(value = "/getXOSkillFromMasterRoleId/{masterRoleId}", method = RequestMethod.GET, headers = "Accept=application/json")
		public ResponseEntity<Object> getXOSkillFromMasterRoleId(@PathVariable("masterRoleId") int masterRoleId) {
			AppLoger.APPLOGGER.info("Returning skill id for skill element: " + masterRoleId);
			return rightPriceService.getXOSkillFromMasterRoleId(masterRoleId);
		}
		@RequestMapping(value = "/getCurrentCustomerUserDetailskpo/{industry}/{vertical}", method = RequestMethod.GET, headers = "Accept=application/json")
		public ResponseEntity<Object> getCurrentCustomerUserDetailskpo(@PathVariable("industry")int industry,@PathVariable("vertical")String vertical ) {
			return rightPriceService.getCurrentCustomerUserDetailskpo(industry,vertical);
		}
		/*@ApiOperation(value = "Get current logged in user details")
		@RequestMapping(value = "/getCurrentCustomerUserDetails/{verticalId}", method = RequestMethod.GET, headers = "Accept=application/json")
		public ResponseEntity<Object> getCurrentCustomerUserDetails(@PathVariable("verticalId") int verticalId) {
			AppLoger.APPLOGGER.info("--------------------------Applogger------------------------------------");
			AppLoger.APPLOGGER.info(
					"/getCurrentCustomerUserDetails......................................................................................");
			return rightPriceService.getCurrentCustomerUserDetails(verticalId);
		}*/
		
		@ApiOperation(value = "Get current logged in user details")
		@RequestMapping(value = "/getCurrentCustomerUserDetails/{verticalId}/{rbutype}", method = RequestMethod.GET, headers = "Accept=application/json")
		public ResponseEntity<Object> getCurrentCustomerUserDetails(@PathVariable("verticalId") int verticalId,@PathVariable("rbutype")int rbutype ) {
			AppLoger.APPLOGGER.info("--------------------------Applogger------------------------------------");
			AppLoger.APPLOGGER.info(
					"/getCurrentCustomerUserDetails......................................................................................");
			return rightPriceService.getCurrentCustomerUserDetails(verticalId,rbutype);
		}
		
		
		//manglam updated
		@RequestMapping(value = "/getWonDealDetailsForGFT", method = RequestMethod.GET, headers = "Accept=application/json")
		public ResponseEntity<Object> getWonDealDetailsForGFT() {
			return rightPriceService.getWonDealDetailsForGFT();
		}
		
		@RequestMapping(value = "/getPageTrckrData/{rcId}", method = RequestMethod.GET, headers = "Accept=application/json")
		public ResponseEntity<Object> getPageTrckrData(@PathVariable("rcId") int rcId) {
			return rightPriceService.getPageTrckrData(rcId);	
			}		

		//manglam updated
		
	@ApiOperation(value = "getPLAttachementData", response = AttachmentMapperPL.class)
	@RequestMapping(value = "/getPLAttachementData", method = RequestMethod.POST)
	public ResponseEntity<Object> getPLAttachementData(@RequestBody AttachmentMapperPL rpDealVersionId) {
		System.out.println("getPLAttachementData====================================================");
		return rightPriceService.getPLAttachementData(rpDealVersionId);
	}

	@ApiOperation(value = "uploadPLFileData file", response = PLAttachment.class)
	@RequestMapping(value = "/uploadPLFileData", method = RequestMethod.POST)
	public ResponseEntity<Object> uploadPLFileData(@RequestParam(value = "file", required = false) MultipartFile file,
			@RequestParam(value = "name", required = false) String fileName,
			@FormParam("file") InputStream uploadedInputStream, @FormParam("versionId") Integer versionId) {
		System.out.println("uploadPLFileData========================================C");
		try {
			AppLoger.APPLOGGER.info("PL File length : " + file);
			if (null != fileName) {
				AppLoger.APPLOGGER.info("File name length : " + fileName.toString());
				AppLoger.APPLOGGER.info("Version Id from JS " + versionId);
			}
			return rightPriceService.uploadPLFileData(file, fileName, uploadedInputStream, versionId);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(205).contentType(MediaType.TEXT_PLAIN)
					.body("Currently We are facing technical issues, please try again later.");
		}
	}

	@RequestMapping(value = "/getCurrentCustomerUserDetailsJVkpo/{industry}/{verticalId}", method = RequestMethod.GET, headers = "Accept=application/json")
	public ResponseEntity<Object> getCurrentCustomerUserDetailsJVkpo(@PathVariable("industry")int industry,@PathVariable("verticalId")int verticalId) {
		return rightPriceService.getCurrentCustomerUserDetailsJVkpo(industry,verticalId);
	}
	
		
}
