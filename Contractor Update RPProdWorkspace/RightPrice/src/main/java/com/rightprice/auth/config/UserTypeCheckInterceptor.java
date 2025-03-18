package com.rightprice.auth.config;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.rightprice.auth.model.CheckUserType;
import com.rightprice.auth.util.AppLoger;
@Component
@Configuration
@EnableTransactionManagement
public class UserTypeCheckInterceptor  extends HandlerInterceptorAdapter{

static int checkedValue = 0;

 @Override
 public boolean preHandle(HttpServletRequest request, 
	HttpServletResponse response, Object object) throws Exception { 
	 if((request.getRequestURI().equalsIgnoreCase("/MyDashBoard"))||(request.getRequestURI().equalsIgnoreCase("/RightPrice-DAS/MyDashBoard"))
			 ||(request.getRequestURI().equalsIgnoreCase("/QualityDashBoard"))||(request.getRequestURI().equalsIgnoreCase("/RightPrice-DAS/QualityDashBoard"))
			 ||(request.getRequestURI().equalsIgnoreCase("/welcome"))||(request.getRequestURI().equalsIgnoreCase("/RightPrice-DAS/welcome"))
			 ||(request.getRequestURI().equalsIgnoreCase("/RateCardCreationDetails"))||(request.getRequestURI().equalsIgnoreCase("/RightPrice-DAS/RateCardCreationDetails"))
			 || (request.getRequestURI().equalsIgnoreCase("/RateCardCreationRoleSelection"))||(request.getRequestURI().equalsIgnoreCase("/RightPrice-DAS/RateCardCreationRoleSelection"))
			 || (request.getRequestURI().equalsIgnoreCase("/RateCardCreationAddContractorRole"))||(request.getRequestURI().equalsIgnoreCase("/RightPrice-DAS/RateCardCreationAddContractorRole"))
			 ||(request.getRequestURI().equalsIgnoreCase("/RateCardContractorRole"))||(request.getRequestURI().equalsIgnoreCase("/RightPrice-DAS/RateCardContractorRole"))
			 ||(request.getRequestURI().equalsIgnoreCase("/RateCardCreationRoleUtilizationAndRates"))||(request.getRequestURI().equalsIgnoreCase("/RightPrice-DAS/RateCardCreationRoleUtilizationAndRates"))
			 ||(request.getRequestURI().equalsIgnoreCase("/RateCardCreationSummary"))||(request.getRequestURI().equalsIgnoreCase("/RightPrice-DAS/RateCardCreationSummary"))
			 ||(request.getRequestURI().equalsIgnoreCase("/RateCardCreationFinaliseRateCard"))||(request.getRequestURI().equalsIgnoreCase("/RightPrice-DAS/RateCardCreationFinaliseRateCard"))
			 ||(request.getRequestURI().equalsIgnoreCase("/RateCardCompletionStage"))||(request.getRequestURI().equalsIgnoreCase("/RightPrice-DAS/RateCardCompletionStage"))
			 ||(request.getRequestURI().equalsIgnoreCase("/TMDealCreationDetails"))||(request.getRequestURI().equalsIgnoreCase("/RightPrice-DAS/TMDealCreationDetails"))
			 ||(request.getRequestURI().equalsIgnoreCase("/FPDealCreationDetails"))||(request.getRequestURI().equalsIgnoreCase("/RightPrice-DAS/FPDealCreationDetails"))
			 ||(request.getRequestURI().equalsIgnoreCase("/TMDealCreationRateCardAndProjectDetails"))||(request.getRequestURI().equalsIgnoreCase("/RightPrice-DAS/TMDealCreationRateCardAndProjectDetails"))
			 ||(request.getRequestURI().equalsIgnoreCase("/TMDealCreationTeamDetails"))||(request.getRequestURI().equalsIgnoreCase("/RightPrice-DAS/TMDealCreationTeamDetails"))
			 ||(request.getRequestURI().equalsIgnoreCase("/TMDealCreationRoleSelection"))||(request.getRequestURI().equalsIgnoreCase("/RightPrice-DAS/TMDealCreationRoleSelection"))
			 ||(request.getRequestURI().equalsIgnoreCase("/TMDealCreationAddContractorRole"))||(request.getRequestURI().equalsIgnoreCase("/RightPrice-DAS/TMDealCreationAddContractorRole"))
			 ||(request.getRequestURI().equalsIgnoreCase("/TMDealCreationStaffing"))||(request.getRequestURI().equalsIgnoreCase("/RightPrice-DAS/TMDealCreationStaffing"))
			 ||(request.getRequestURI().equalsIgnoreCase("/TMDealCreationProjectSpecificCost"))||(request.getRequestURI().equalsIgnoreCase("/RightPrice-DAS/TMDealCreationProjectSpecificCost"))
			 ||(request.getRequestURI().equalsIgnoreCase("/TMDealCreationUploadEstimationRelatedDocuments"))||(request.getRequestURI().equalsIgnoreCase("/RightPrice-DAS/TMDealCreationUploadEstimationRelatedDocuments"))
			 ||(request.getRequestURI().equalsIgnoreCase("/TMDealCreationSummary"))||(request.getRequestURI().equalsIgnoreCase("/RightPrice-DAS/TMDealCreationSummary"))
			 ||(request.getRequestURI().equalsIgnoreCase("/TMDealCreationFinaliseDeal"))||(request.getRequestURI().equalsIgnoreCase("/RightPrice-DAS/TMDealCreationFinaliseDeal"))
			 ||(request.getRequestURI().equalsIgnoreCase("/FPDealCreationContractualTerms"))||(request.getRequestURI().equalsIgnoreCase("/RightPrice-DAS/FPDealCreationContractualTerms"))
			 ||(request.getRequestURI().equalsIgnoreCase("/FPDealCreationApplicationDetails"))||(request.getRequestURI().equalsIgnoreCase("/RightPrice-DAS/FPDealCreationApplicationDetails"))
			 ||(request.getRequestURI().equalsIgnoreCase("/FPDealCreationRoleSelection"))||(request.getRequestURI().equalsIgnoreCase("/RightPrice-DAS/FPDealCreationRoleSelection"))
			 ||(request.getRequestURI().equalsIgnoreCase("/FPDealCreationAddContractorRole"))||(request.getRequestURI().equalsIgnoreCase("/RightPrice-DAS/FPDealCreationAddContractorRole"))
			 ||(request.getRequestURI().equalsIgnoreCase("/FPDealCreationStaffing"))||(request.getRequestURI().equalsIgnoreCase("/RightPrice-DAS/FPDealCreationStaffing"))
			 ||(request.getRequestURI().equalsIgnoreCase("/FPDealCreationCostInputs"))||(request.getRequestURI().equalsIgnoreCase("/RightPrice-DAS/FPDealCreationCostInputs"))
			 ||(request.getRequestURI().equalsIgnoreCase("/FPDealCreationProjectSpecificCost"))||(request.getRequestURI().equalsIgnoreCase("/RightPrice-DAS/FPDealCreationProjectSpecificCost"))
			 ||(request.getRequestURI().equalsIgnoreCase("/FPDealCreationUploadEstimationRelatedDocuments"))||(request.getRequestURI().equalsIgnoreCase("/RightPrice-DAS/FPDealCreationUploadEstimationRelatedDocuments"))
			 ||(request.getRequestURI().equalsIgnoreCase("/FPDealCreationWhatIfApplicationwise"))||(request.getRequestURI().equalsIgnoreCase("/RightPrice-DAS/FPDealCreationWhatIfApplicationwise"))
			 ||(request.getRequestURI().equalsIgnoreCase("/FPDealCreationCostSummary"))||(request.getRequestURI().equalsIgnoreCase("/RightPrice-DAS/FPDealCreationCostSummary"))
			 ||(request.getRequestURI().equalsIgnoreCase("/FPDealCreationYearlySummary"))||(request.getRequestURI().equalsIgnoreCase("/RightPrice-DAS/FPDealCreationYearlySummary"))
			 ||(request.getRequestURI().equalsIgnoreCase("/FPDealCreationSummary"))||(request.getRequestURI().equalsIgnoreCase("/RightPrice-DAS/FPDealCreationSummary"))
			 ||(request.getRequestURI().equalsIgnoreCase("/FPDealCreationFinaliseDeal"))||(request.getRequestURI().equalsIgnoreCase("/RightPrice-DAS/FPDealCreationFinaliseDeal"))
			 ||(request.getRequestURI().equalsIgnoreCase("/FPDealCreationCalculationDetails"))||(request.getRequestURI().equalsIgnoreCase("/RightPrice-DAS/FPDealCreationCalculationDetails"))
			 ||(request.getRequestURI().equalsIgnoreCase("/RateCardCreationGMRateDetails"))||(request.getRequestURI().equalsIgnoreCase("/RightPrice-DAS/RateCardCreationGMRateDetails"))
			 ||(request.getRequestURI().equalsIgnoreCase("/FPDealCreationRateCardAndProjectDetails"))||(request.getRequestURI().equalsIgnoreCase("/RightPrice-DAS/FPDealCreationRateCardAndProjectDetails"))
			 ||(request.getRequestURI().equalsIgnoreCase("/MasterAllowances"))||(request.getRequestURI().equalsIgnoreCase("/RightPrice-DAS/MasterAllowances"))
			 || (request.getRequestURI().equalsIgnoreCase("/MasterCampusHire"))||(request.getRequestURI().equalsIgnoreCase("/RightPrice-DAS/MasterCampusHire"))
			 || (request.getRequestURI().equalsIgnoreCase("/MasterCity"))||(request.getRequestURI().equalsIgnoreCase("/RightPrice-DAS/MasterCity"))
			 ||(request.getRequestURI().equalsIgnoreCase("/MasterCommonCostParameters"))||(request.getRequestURI().equalsIgnoreCase("/RightPrice-DAS/MasterCommonCostParameters"))
			 ||(request.getRequestURI().equalsIgnoreCase("/MasterCountryForex"))||(request.getRequestURI().equalsIgnoreCase("/RightPrice-DAS/MasterCountryForex"))
			 ||(request.getRequestURI().equalsIgnoreCase("/MasterCountryVisa"))||(request.getRequestURI().equalsIgnoreCase("/RightPrice-DAS/MasterCountryVisa"))
			 ||(request.getRequestURI().equalsIgnoreCase("/MasterDeliveryTeam"))||(request.getRequestURI().equalsIgnoreCase("/RightPrice-DAS/MasterDeliveryTeam"))
			 ||(request.getRequestURI().equalsIgnoreCase("/MasterRightPriceRate"))||(request.getRequestURI().equalsIgnoreCase("/RightPrice-DAS/MasterRightPriceRate"))
			 ||(request.getRequestURI().equalsIgnoreCase("/MasterRoles"))||(request.getRequestURI().equalsIgnoreCase("/RightPrice-DAS/MasterRoles"))
			 ||(request.getRequestURI().equalsIgnoreCase("/MasterSalary"))||(request.getRequestURI().equalsIgnoreCase("/RightPrice-DAS/MasterSalary"))
			 ||(request.getRequestURI().equalsIgnoreCase("/MastersDesignation"))||(request.getRequestURI().equalsIgnoreCase("/RightPrice-DAS/MastersDesignation"))
			 ||(request.getRequestURI().equalsIgnoreCase("/MastersLOB"))||(request.getRequestURI().equalsIgnoreCase("/RightPrice-DAS/MastersLOB"))
			 ||(request.getRequestURI().equalsIgnoreCase("/MastersMiscellaneousCost"))||(request.getRequestURI().equalsIgnoreCase("/RightPrice-DAS/MastersMiscellaneousCost"))
			 ||(request.getRequestURI().equalsIgnoreCase("/MastersSubPractice"))||(request.getRequestURI().equalsIgnoreCase("/RightPrice-DAS/MastersSubPractice"))
			 ||(request.getRequestURI().equalsIgnoreCase("/MastersVertical"))||(request.getRequestURI().equalsIgnoreCase("/RightPrice-DAS/MastersVertical"))
			 ||(request.getRequestURI().equalsIgnoreCase("/MasterTaxParameters"))||(request.getRequestURI().equalsIgnoreCase("/RightPrice-DAS/MasterTaxParameters"))
			 ||(request.getRequestURI().equalsIgnoreCase("/MasterAssumptionsParameters"))||(request.getRequestURI().equalsIgnoreCase("/RightPrice-DAS/MasterAssumptionsParameters"))
			 ||(request.getRequestURI().equalsIgnoreCase("/MasterRateCard"))||(request.getRequestURI().equalsIgnoreCase("/RightPrice-DAS/MasterRateCard"))
			 ||(request.getRequestURI().equalsIgnoreCase("/MasterCustomerUpdate"))||(request.getRequestURI().equalsIgnoreCase("/RightPrice-DAS/MasterCustomerUpdate"))
			 ||(request.getRequestURI().equalsIgnoreCase("/MastersPractice"))||(request.getRequestURI().equalsIgnoreCase("/RightPrice-DAS/MastersPractice"))
			 ||(request.getRequestURI().equalsIgnoreCase("/RPAccessControl"))||(request.getRequestURI().equalsIgnoreCase("/RightPrice-DAS/RPAccessControl"))
			 ||(request.getRequestURI().equalsIgnoreCase("/FpPricingDetails"))||(request.getRequestURI().equalsIgnoreCase("/RightPrice-DAS/FpPricingDetails"))
			 ||(request.getRequestURI().equalsIgnoreCase("/RateCardCreationGMRateDetails"))||(request.getRequestURI().equalsIgnoreCase("/RightPrice-DAS/RateCardCreationGMRateDetails"))
			 ||(request.getRequestURI().equalsIgnoreCase("/FPDealCreationManualDealSummary"))||(request.getRequestURI().equalsIgnoreCase("/RightPrice-DAS/FPDealCreationManualDealSummary"))
			 ||(request.getRequestURI().equalsIgnoreCase("/FPManualDealCreationApproval"))||(request.getRequestURI().equalsIgnoreCase("/RightPrice-DAS/FPManualDealCreationApproval"))
			 ||(request.getRequestURI().equalsIgnoreCase("/ResourceForcast"))||(request.getRequestURI().equalsIgnoreCase("/RightPrice-DAS/ResourceForcast"))
			 ||(request.getRequestURI().equalsIgnoreCase("/RFOpportunity"))||(request.getRequestURI().equalsIgnoreCase("/RightPrice-DAS/RFOpportunity"))
			 ||(request.getRequestURI().equalsIgnoreCase("/FPDealCreationFinalizeDeal"))||(request.getRequestURI().equalsIgnoreCase("/RightPrice-DAS/FPDealCreationFinalizeDeal"))){
	 if(CheckUserType.userTypeId==1){
		 AppLoger.APPLOGGER.info("TYPE = 1");
	 }
	 else if(CheckUserType.userTypeId == 2)
	 {
		 if((request.getRequestURI().equalsIgnoreCase("/MasterAllowances"))||(request.getRequestURI().equalsIgnoreCase("/RightPrice-DAS/MasterAllowances"))
				 || (request.getRequestURI().equalsIgnoreCase("/MasterCampusHire"))||(request.getRequestURI().equalsIgnoreCase("/RightPrice-DAS/MasterCampusHire"))
				 || (request.getRequestURI().equalsIgnoreCase("/MasterCity"))||(request.getRequestURI().equalsIgnoreCase("/RightPrice-DAS/MasterCity"))
				 ||(request.getRequestURI().equalsIgnoreCase("/MasterCommonCostParameters"))||(request.getRequestURI().equalsIgnoreCase("/RightPrice-DAS/MasterCommonCostParameters"))
				 ||(request.getRequestURI().equalsIgnoreCase("/MasterCountryForex"))||(request.getRequestURI().equalsIgnoreCase("/RightPrice-DAS/MasterCountryForex"))
				 ||(request.getRequestURI().equalsIgnoreCase("/MasterCountryVisa"))||(request.getRequestURI().equalsIgnoreCase("/RightPrice-DAS/MasterCountryVisa"))
				 ||(request.getRequestURI().equalsIgnoreCase("/MasterDeliveryTeam"))||(request.getRequestURI().equalsIgnoreCase("/RightPrice-DAS/MasterDeliveryTeam"))
				 ||(request.getRequestURI().equalsIgnoreCase("/MasterRightPriceRate"))||(request.getRequestURI().equalsIgnoreCase("/RightPrice-DAS/MasterRightPriceRate"))
				 ||(request.getRequestURI().equalsIgnoreCase("/MasterRoles"))||(request.getRequestURI().equalsIgnoreCase("/RightPrice-DAS/MasterRoles"))
				 ||(request.getRequestURI().equalsIgnoreCase("/MasterSalary"))||(request.getRequestURI().equalsIgnoreCase("/RightPrice-DAS/MasterSalary"))
				 ||(request.getRequestURI().equalsIgnoreCase("/MastersDesignation"))||(request.getRequestURI().equalsIgnoreCase("/RightPrice-DAS/MastersDesignation"))
				 ||(request.getRequestURI().equalsIgnoreCase("/MastersLOB"))||(request.getRequestURI().equalsIgnoreCase("/RightPrice-DAS/MastersLOB"))
				 ||(request.getRequestURI().equalsIgnoreCase("/MastersMiscellaneousCost"))||(request.getRequestURI().equalsIgnoreCase("/RightPrice-DAS/MastersMiscellaneousCost"))
				 ||(request.getRequestURI().equalsIgnoreCase("/MastersSubPractice"))||(request.getRequestURI().equalsIgnoreCase("/RightPrice-DAS/MastersSubPractice"))
				 ||(request.getRequestURI().equalsIgnoreCase("/MastersVertical"))||(request.getRequestURI().equalsIgnoreCase("/RightPrice-DAS/MastersVertical"))
				 ||(request.getRequestURI().equalsIgnoreCase("/MasterTaxParameters"))||(request.getRequestURI().equalsIgnoreCase("/RightPrice-DAS/MasterTaxParameters"))
				 ||(request.getRequestURI().equalsIgnoreCase("/MasterAssumptionsParameters"))||(request.getRequestURI().equalsIgnoreCase("/RightPrice-DAS/MasterAssumptionsParameters"))
				 ||(request.getRequestURI().equalsIgnoreCase("/MasterRateCard"))||(request.getRequestURI().equalsIgnoreCase("/RightPrice-DAS/MasterRateCard"))
				 ||(request.getRequestURI().equalsIgnoreCase("/MasterCustomerUpdate"))||(request.getRequestURI().equalsIgnoreCase("/RightPrice-DAS/MasterCustomerUpdate"))
				 ||(request.getRequestURI().equalsIgnoreCase("/RPAccessControl"))||(request.getRequestURI().equalsIgnoreCase("/RightPrice-DAS/RPAccessControl"))
				 ||(request.getRequestURI().equalsIgnoreCase("/ResourceForcast"))||(request.getRequestURI().equalsIgnoreCase("/RightPrice-DAS/ResourceForcast"))
				 ||(request.getRequestURI().equalsIgnoreCase("/RFOpportunity"))||(request.getRequestURI().equalsIgnoreCase("/RightPrice-DAS/RFOpportunity"))){
			 response.sendRedirect("/RightPrice-DAS/Portal/403.jsp");
			 }
	 }
	 else if(CheckUserType.userTypeId == 3)
	 {
		 if((request.getRequestURI().equalsIgnoreCase("/MasterAllowances"))||(request.getRequestURI().equalsIgnoreCase("/RightPrice-DAS/MasterAllowances"))
				 || (request.getRequestURI().equalsIgnoreCase("/MasterCampusHire"))||(request.getRequestURI().equalsIgnoreCase("/RightPrice-DAS/MasterCampusHire"))
				 || (request.getRequestURI().equalsIgnoreCase("/MasterCity"))||(request.getRequestURI().equalsIgnoreCase("/RightPrice-DAS/MasterCity"))
				 ||(request.getRequestURI().equalsIgnoreCase("/MasterCommonCostParameters"))||(request.getRequestURI().equalsIgnoreCase("/RightPrice-DAS/MasterCommonCostParameters"))
				 ||(request.getRequestURI().equalsIgnoreCase("/MasterCountryForex"))||(request.getRequestURI().equalsIgnoreCase("/RightPrice-DAS/MasterCountryForex"))
				 ||(request.getRequestURI().equalsIgnoreCase("/MasterCountryVisa"))||(request.getRequestURI().equalsIgnoreCase("/RightPrice-DAS/MasterCountryVisa"))
				 ||(request.getRequestURI().equalsIgnoreCase("/MasterRightPriceRate"))||(request.getRequestURI().equalsIgnoreCase("/RightPrice-DAS/MasterRightPriceRate"))
				 ||(request.getRequestURI().equalsIgnoreCase("/MasterRoles"))||(request.getRequestURI().equalsIgnoreCase("/RightPrice-DAS/MasterRoles"))
				 ||(request.getRequestURI().equalsIgnoreCase("/MasterSalary"))||(request.getRequestURI().equalsIgnoreCase("/RightPrice-DAS/MasterSalary"))
				 ||(request.getRequestURI().equalsIgnoreCase("/MastersDesignation"))||(request.getRequestURI().equalsIgnoreCase("/RightPrice-DAS/MastersDesignation"))
				 ||(request.getRequestURI().equalsIgnoreCase("/MastersLOB"))||(request.getRequestURI().equalsIgnoreCase("/RightPrice-DAS/MastersLOB"))
				 ||(request.getRequestURI().equalsIgnoreCase("/MastersMiscellaneousCost"))||(request.getRequestURI().equalsIgnoreCase("/RightPrice-DAS/MastersMiscellaneousCost"))
				 ||(request.getRequestURI().equalsIgnoreCase("/MastersSubPractice"))||(request.getRequestURI().equalsIgnoreCase("/RightPrice-DAS/MastersSubPractice"))
				 ||(request.getRequestURI().equalsIgnoreCase("/MasterTaxParameters"))||(request.getRequestURI().equalsIgnoreCase("/RightPrice-DAS/MasterTaxParameters"))
				 ||(request.getRequestURI().equalsIgnoreCase("/MasterAssumptionsParameters"))||(request.getRequestURI().equalsIgnoreCase("/RightPrice-DAS/MasterAssumptionsParameters"))
				 ||(request.getRequestURI().equalsIgnoreCase("/MasterRateCard"))||(request.getRequestURI().equalsIgnoreCase("/RightPrice-DAS/MasterRateCard"))
				 ||(request.getRequestURI().equalsIgnoreCase("/MasterCustomerUpdate"))||(request.getRequestURI().equalsIgnoreCase("/RightPrice-DAS/MasterCustomerUpdate"))
				 
				 ||(request.getRequestURI().equalsIgnoreCase("/RPAccessControl"))||(request.getRequestURI().equalsIgnoreCase("/RightPrice-DAS/RPAccessControl"))){
			 response.sendRedirect("/RightPrice-DAS/Portal/403.jsp");
			 }
	 }
	 else if(CheckUserType.userTypeId == 4)
	 {
		 if((request.getRequestURI().equalsIgnoreCase("/RateCardCreationDetails"))||(request.getRequestURI().equalsIgnoreCase("/RightPrice-DAS/RateCardCreationDetails"))
				 || (request.getRequestURI().equalsIgnoreCase("/RateCardCreationRoleSelection"))||(request.getRequestURI().equalsIgnoreCase("/RightPrice-DAS/RateCardCreationRoleSelection"))
				 || (request.getRequestURI().equalsIgnoreCase("/RateCardCreationAddContractorRole"))||(request.getRequestURI().equalsIgnoreCase("/RightPrice-DAS/RateCardCreationAddContractorRole"))
				 ||(request.getRequestURI().equalsIgnoreCase("/RateCardContractorRole"))||(request.getRequestURI().equalsIgnoreCase("/RightPrice-DAS/RateCardContractorRole"))
				 ||(request.getRequestURI().equalsIgnoreCase("/RateCardCreationRoleUtilizationAndRates"))||(request.getRequestURI().equalsIgnoreCase("/RightPrice-DAS/RateCardCreationRoleUtilizationAndRates"))
				 ||(request.getRequestURI().equalsIgnoreCase("/RateCardCreationSummary"))||(request.getRequestURI().equalsIgnoreCase("/RightPrice-DAS/RateCardCreationSummary"))
				 ||(request.getRequestURI().equalsIgnoreCase("/RateCardCreationFinaliseRateCard"))||(request.getRequestURI().equalsIgnoreCase("/RightPrice-DAS/RateCardCreationFinaliseRateCard"))
				 ||(request.getRequestURI().equalsIgnoreCase("/RateCardCompletionStage"))||(request.getRequestURI().equalsIgnoreCase("/RightPrice-DAS/RateCardCompletionStage"))
				 ||(request.getRequestURI().equalsIgnoreCase("/TMDealCreationDetails"))||(request.getRequestURI().equalsIgnoreCase("/RightPrice-DAS/TMDealCreationDetails"))
				 ||(request.getRequestURI().equalsIgnoreCase("/TMDealCreationRateCardAndProjectDetails"))||(request.getRequestURI().equalsIgnoreCase("/RightPrice-DAS/TMDealCreationRateCardAndProjectDetails"))
				 ||(request.getRequestURI().equalsIgnoreCase("/TMDealCreationTeamDetails"))||(request.getRequestURI().equalsIgnoreCase("/RightPrice-DAS/TMDealCreationTeamDetails"))
				 ||(request.getRequestURI().equalsIgnoreCase("/TMDealCreationRoleSelection"))||(request.getRequestURI().equalsIgnoreCase("/RightPrice-DAS/TMDealCreationRoleSelection"))
				 ||(request.getRequestURI().equalsIgnoreCase("/TMDealCreationAddContractorRole"))||(request.getRequestURI().equalsIgnoreCase("/RightPrice-DAS/TMDealCreationAddContractorRole"))
				 ||(request.getRequestURI().equalsIgnoreCase("/TMDealCreationStaffing"))||(request.getRequestURI().equalsIgnoreCase("/RightPrice-DAS/TMDealCreationStaffing"))
				 ||(request.getRequestURI().equalsIgnoreCase("/TMDealCreationProjectSpecificCost"))||(request.getRequestURI().equalsIgnoreCase("/RightPrice-DAS/TMDealCreationProjectSpecificCost"))
				 ||(request.getRequestURI().equalsIgnoreCase("/TMDealCreationUploadEstimationRelatedDocuments"))||(request.getRequestURI().equalsIgnoreCase("/RightPrice-DAS/TMDealCreationUploadEstimationRelatedDocuments"))
				 ||(request.getRequestURI().equalsIgnoreCase("/TMDealCreationSummary"))||(request.getRequestURI().equalsIgnoreCase("/RightPrice-DAS/TMDealCreationSummary"))
				 ||(request.getRequestURI().equalsIgnoreCase("/TMDealCreationFinaliseDeal"))||(request.getRequestURI().equalsIgnoreCase("/RightPrice-DAS/TMDealCreationFinaliseDeal"))
				 ||(request.getRequestURI().equalsIgnoreCase("/RateCardCreationGMRateDetails"))||(request.getRequestURI().equalsIgnoreCase("/RightPrice-DAS/RateCardCreationGMRateDetails"))
				 ||(request.getRequestURI().equalsIgnoreCase("/MasterAllowances"))||(request.getRequestURI().equalsIgnoreCase("/RightPrice-DAS/MasterAllowances"))
				 || (request.getRequestURI().equalsIgnoreCase("/MasterCampusHire"))||(request.getRequestURI().equalsIgnoreCase("/RightPrice-DAS/MasterCampusHire"))
				 || (request.getRequestURI().equalsIgnoreCase("/MasterCity"))||(request.getRequestURI().equalsIgnoreCase("/RightPrice-DAS/MasterCity"))
				 ||(request.getRequestURI().equalsIgnoreCase("/MasterCommonCostParameters"))||(request.getRequestURI().equalsIgnoreCase("/RightPrice-DAS/MasterCommonCostParameters"))
				 ||(request.getRequestURI().equalsIgnoreCase("/MasterCountryForex"))||(request.getRequestURI().equalsIgnoreCase("/RightPrice-DAS/MasterCountryForex"))
				 ||(request.getRequestURI().equalsIgnoreCase("/MasterCountryVisa"))||(request.getRequestURI().equalsIgnoreCase("/RightPrice-DAS/MasterCountryVisa"))
				 ||(request.getRequestURI().equalsIgnoreCase("/MasterDeliveryTeam"))||(request.getRequestURI().equalsIgnoreCase("/RightPrice-DAS/MasterDeliveryTeam"))
				 ||(request.getRequestURI().equalsIgnoreCase("/MasterRightPriceRate"))||(request.getRequestURI().equalsIgnoreCase("/RightPrice-DAS/MasterRightPriceRate"))
				 ||(request.getRequestURI().equalsIgnoreCase("/MasterRoles"))||(request.getRequestURI().equalsIgnoreCase("/RightPrice-DAS/MasterRoles"))
				 ||(request.getRequestURI().equalsIgnoreCase("/MasterSalary"))||(request.getRequestURI().equalsIgnoreCase("/RightPrice-DAS/MasterSalary"))
				 ||(request.getRequestURI().equalsIgnoreCase("/MastersDesignation"))||(request.getRequestURI().equalsIgnoreCase("/RightPrice-DAS/MastersDesignation"))
				 ||(request.getRequestURI().equalsIgnoreCase("/MastersLOB"))||(request.getRequestURI().equalsIgnoreCase("/RightPrice-DAS/MastersLOB"))
				 ||(request.getRequestURI().equalsIgnoreCase("/MastersMiscellaneousCost"))||(request.getRequestURI().equalsIgnoreCase("/RightPrice-DAS/MastersMiscellaneousCost"))
				 ||(request.getRequestURI().equalsIgnoreCase("/MastersSubPractice"))||(request.getRequestURI().equalsIgnoreCase("/RightPrice-DAS/MastersSubPractice"))
				 ||(request.getRequestURI().equalsIgnoreCase("/MastersVertical"))||(request.getRequestURI().equalsIgnoreCase("/RightPrice-DAS/MastersVertical"))
				 ||(request.getRequestURI().equalsIgnoreCase("/MasterTaxParameters"))||(request.getRequestURI().equalsIgnoreCase("/RightPrice-DAS/MasterTaxParameters"))
				 ||(request.getRequestURI().equalsIgnoreCase("/MasterAssumptionsParameters"))||(request.getRequestURI().equalsIgnoreCase("/RightPrice-DAS/MasterAssumptionsParameters"))
				 ||(request.getRequestURI().equalsIgnoreCase("/MasterRateCard"))||(request.getRequestURI().equalsIgnoreCase("/RightPrice-DAS/MasterRateCard"))
				 ||(request.getRequestURI().equalsIgnoreCase("/MasterCustomerUpdate"))||(request.getRequestURI().equalsIgnoreCase("/RightPrice-DAS/MasterCustomerUpdate"))
				 
				 ||(request.getRequestURI().equalsIgnoreCase("/RPAccessControl"))||(request.getRequestURI().equalsIgnoreCase("/RightPrice-DAS/RPAccessControl"))
				 ||(request.getRequestURI().equalsIgnoreCase("/RateCardCreationGMRateDetails"))||(request.getRequestURI().equalsIgnoreCase("/RightPrice-DAS/RateCardCreationGMRateDetails"))
				 ||(request.getRequestURI().equalsIgnoreCase("/ResourceForcast"))||(request.getRequestURI().equalsIgnoreCase("/RightPrice-DAS/ResourceForcast"))
				 ||(request.getRequestURI().equalsIgnoreCase("/RFOpportunity"))||(request.getRequestURI().equalsIgnoreCase("/RightPrice-DAS/RFOpportunity"))){
			 	response.sendRedirect("/RightPrice-DAS/Portal/403.jsp");
		 }
		 
	 }
	 else if(CheckUserType.userTypeId == 5)
	 {
	 if((request.getRequestURI().equalsIgnoreCase("/MasterAllowances"))||(request.getRequestURI().equalsIgnoreCase("/RightPrice-DAS/MasterAllowances"))
			 || (request.getRequestURI().equalsIgnoreCase("/MasterCampusHire"))||(request.getRequestURI().equalsIgnoreCase("/RightPrice-DAS/MasterCampusHire"))
			 || (request.getRequestURI().equalsIgnoreCase("/MasterCity"))||(request.getRequestURI().equalsIgnoreCase("/RightPrice-DAS/MasterCity"))
			 ||(request.getRequestURI().equalsIgnoreCase("/MasterCommonCostParameters"))||(request.getRequestURI().equalsIgnoreCase("/RightPrice-DAS/MasterCommonCostParameters"))
			 ||(request.getRequestURI().equalsIgnoreCase("/MasterCountryForex"))||(request.getRequestURI().equalsIgnoreCase("/RightPrice-DAS/MasterCountryForex"))
			 ||(request.getRequestURI().equalsIgnoreCase("/MasterCountryVisa"))||(request.getRequestURI().equalsIgnoreCase("/RightPrice-DAS/MasterCountryVisa"))
			 ||(request.getRequestURI().equalsIgnoreCase("/MasterDeliveryTeam"))||(request.getRequestURI().equalsIgnoreCase("/RightPrice-DAS/MasterDeliveryTeam"))
			 ||(request.getRequestURI().equalsIgnoreCase("/MasterRightPriceRate"))||(request.getRequestURI().equalsIgnoreCase("/RightPrice-DAS/MasterRightPriceRate"))
			 ||(request.getRequestURI().equalsIgnoreCase("/MasterRoles"))||(request.getRequestURI().equalsIgnoreCase("/RightPrice-DAS/MasterRoles"))
			 ||(request.getRequestURI().equalsIgnoreCase("/MasterSalary"))||(request.getRequestURI().equalsIgnoreCase("/RightPrice-DAS/MasterSalary"))
			 ||(request.getRequestURI().equalsIgnoreCase("/MastersDesignation"))||(request.getRequestURI().equalsIgnoreCase("/RightPrice-DAS/MastersDesignation"))
			 ||(request.getRequestURI().equalsIgnoreCase("/MastersLOB"))||(request.getRequestURI().equalsIgnoreCase("/RightPrice-DAS/MastersLOB"))
			 ||(request.getRequestURI().equalsIgnoreCase("/MastersMiscellaneousCost"))||(request.getRequestURI().equalsIgnoreCase("/RightPrice-DAS/MastersMiscellaneousCost"))
			 ||(request.getRequestURI().equalsIgnoreCase("/MastersSubPractice"))||(request.getRequestURI().equalsIgnoreCase("/RightPrice-DAS/MastersSubPractice"))
			 ||(request.getRequestURI().equalsIgnoreCase("/MastersVertical"))||(request.getRequestURI().equalsIgnoreCase("/RightPrice-DAS/MastersVertical"))
			 ||(request.getRequestURI().equalsIgnoreCase("/MasterTaxParameters"))||(request.getRequestURI().equalsIgnoreCase("/RightPrice-DAS/MasterTaxParameters"))
			 ||(request.getRequestURI().equalsIgnoreCase("/MasterAssumptionsParameters"))||(request.getRequestURI().equalsIgnoreCase("/RightPrice-DAS/MasterAssumptionsParameters"))
			 ||(request.getRequestURI().equalsIgnoreCase("/MasterRateCard"))||(request.getRequestURI().equalsIgnoreCase("/RightPrice-DAS/MasterRateCard"))
			 ||(request.getRequestURI().equalsIgnoreCase("/MasterCustomerUpdate"))||(request.getRequestURI().equalsIgnoreCase("/RightPrice-DAS/MasterCustomerUpdate"))
			 
			 ||(request.getRequestURI().equalsIgnoreCase("/RPAccessControl"))||(request.getRequestURI().equalsIgnoreCase("/RightPrice-DAS/RPAccessControl"))){
		 response.sendRedirect("/RightPrice-DAS/Portal/403.jsp");
		 }
	 }
	 else if(CheckUserType.userTypeId ==6)
	 {
		 response.sendRedirect("/RightPrice-DAS/Portal/403.jsp");
	 }
	 else if(CheckUserType.userTypeId ==7)
	 {
		 response.sendRedirect("/RightPrice-DAS/DeligateUserAccess");
	 } 
	 
	 else if(CheckUserType.userTypeId == 8) {
		 if ((request.getRequestURI().equalsIgnoreCase("/MasterAllowances"))||(request.getRequestURI().equalsIgnoreCase("/RightPrice-DAS/MasterAllowances"))
				 || (request.getRequestURI().equalsIgnoreCase("/MasterCampusHire"))||(request.getRequestURI().equalsIgnoreCase("/RightPrice-DAS/MasterCampusHire"))
				 || (request.getRequestURI().equalsIgnoreCase("/MasterCity"))||(request.getRequestURI().equalsIgnoreCase("/RightPrice-DAS/MasterCity"))
				 ||(request.getRequestURI().equalsIgnoreCase("/MasterCommonCostParameters"))||(request.getRequestURI().equalsIgnoreCase("/RightPrice-DAS/MasterCommonCostParameters"))
				 ||(request.getRequestURI().equalsIgnoreCase("/MasterCountryForex"))||(request.getRequestURI().equalsIgnoreCase("/RightPrice-DAS/MasterCountryForex"))
				 ||(request.getRequestURI().equalsIgnoreCase("/MasterCountryVisa"))||(request.getRequestURI().equalsIgnoreCase("/RightPrice-DAS/MasterCountryVisa"))
				 ||(request.getRequestURI().equalsIgnoreCase("/MasterDeliveryTeam"))||(request.getRequestURI().equalsIgnoreCase("/RightPrice-DAS/MasterDeliveryTeam"))
				 ||(request.getRequestURI().equalsIgnoreCase("/MasterRightPriceRate"))||(request.getRequestURI().equalsIgnoreCase("/RightPrice-DAS/MasterRightPriceRate"))
				 ||(request.getRequestURI().equalsIgnoreCase("/MasterRoles"))||(request.getRequestURI().equalsIgnoreCase("/RightPrice-DAS/MasterRoles"))
				 ||(request.getRequestURI().equalsIgnoreCase("/MasterSalary"))||(request.getRequestURI().equalsIgnoreCase("/RightPrice-DAS/MasterSalary"))
				 ||(request.getRequestURI().equalsIgnoreCase("/MastersDesignation"))||(request.getRequestURI().equalsIgnoreCase("/RightPrice-DAS/MastersDesignation"))
				 ||(request.getRequestURI().equalsIgnoreCase("/MastersLOB"))||(request.getRequestURI().equalsIgnoreCase("/RightPrice-DAS/MastersLOB"))
				 ||(request.getRequestURI().equalsIgnoreCase("/MastersMiscellaneousCost"))||(request.getRequestURI().equalsIgnoreCase("/RightPrice-DAS/MastersMiscellaneousCost"))
				 ||(request.getRequestURI().equalsIgnoreCase("/MastersSubPractice"))||(request.getRequestURI().equalsIgnoreCase("/RightPrice-DAS/MastersSubPractice"))
				 ||(request.getRequestURI().equalsIgnoreCase("/MastersVertical"))||(request.getRequestURI().equalsIgnoreCase("/RightPrice-DAS/MastersVertical"))
				 ||(request.getRequestURI().equalsIgnoreCase("/MasterTaxParameters"))||(request.getRequestURI().equalsIgnoreCase("/RightPrice-DAS/MasterTaxParameters"))
				 ||(request.getRequestURI().equalsIgnoreCase("/MasterAssumptionsParameters"))||(request.getRequestURI().equalsIgnoreCase("/RightPrice-DAS/MasterAssumptionsParameters"))
				 ||(request.getRequestURI().equalsIgnoreCase("/MasterRateCard"))||(request.getRequestURI().equalsIgnoreCase("/RightPrice-DAS/MasterRateCard"))
				 ||(request.getRequestURI().equalsIgnoreCase("/MasterCustomerUpdate"))||(request.getRequestURI().equalsIgnoreCase("/RightPrice-DAS/MasterCustomerUpdate"))
				 ||(request.getRequestURI().equalsIgnoreCase("/RPAccessControl"))||(request.getRequestURI().equalsIgnoreCase("/RightPrice-DAS/RPAccessControl"))){
			 	response.sendRedirect("/RightPrice-DAS/Portal/403.jsp");
		 }
		 
	 }
	 else
	 {
		 AppLoger.APPLOGGER.info("TYPE = 3");
		 response.sendRedirect("/RightPrice-DAS/Portal/403.jsp");
	}
	}
	/*}
	}
	}*/
		return true;
	}

@Override
 public void postHandle(HttpServletRequest request, HttpServletResponse response, 
		Object object, ModelAndView model)
		throws Exception {}

 @Override
 public void afterCompletion(HttpServletRequest request, HttpServletResponse response, 
		Object object, Exception arg3)
		throws Exception {}
 

 
}
