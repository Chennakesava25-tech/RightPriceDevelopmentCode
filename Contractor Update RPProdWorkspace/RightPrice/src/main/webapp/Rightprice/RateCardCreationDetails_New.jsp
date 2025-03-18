<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Eviden RightPrice Portal</title>
<meta charset="utf-8">
<meta name="csrf-token" content="${_csrf.token}" />
<meta name="_csrf_header" content="${_csrf.headerName}" />
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="X-UA-Compatible" content="IE=Edge" />
<link href="${contextPath}/resources/css/bootstrap.css" rel="stylesheet" />
<link href="${contextPath}/resources/css/SAPStyleSheet.css"
	rel="stylesheet" />
<link href="${contextPath}/resources/css/loader.css" rel="stylesheet" />
<link href="${contextPath}/resources/css/AngularCSS.css"
	rel="stylesheet" />
<script src="${contextPath}/resources/js/jquery.min.js"></script>
<script src="${contextPath}/resources/js/bootstrap.js"></script>
<script src="${contextPath}/resources/js/jquery.serializeJSON.min.js"></script>
<script src="${contextPath}/resources/js/jquery-ui.js"></script>
<link href="${contextPath}/resources/css/jquery-ui.css" rel="stylesheet" />
<link href="${contextPath}/resources/css/bootstrap-dialog.css"
	rel="stylesheet" />
<script src="${contextPath}/resources/js/bootstrap-dialog.js"></script>
<script src="${contextPath}/resources/js/jquery.validate.js"></script>
<script src="${contextPath}/resources/js/jqueryValidations.js"></script>
<script src="${contextPath}/resources/js/additional-methods.js"></script>
<script src="${contextPath}/resources/js/jqueryValidations.js"></script>
<link
	href="${contextPath}/resources/css/ie10-viewport-bug-workaround.css"
	rel="stylesheet" />
<link href="${contextPath}/resources/css/sticky-footer-navbar.css"
	rel="stylesheet" />
<script
	src="${contextPath}/resources/js/ie10-viewport-bug-workaround.js"></script>
<script src="${contextPath}/resources/js/angular.js"></script>
<script src="${contextPath}/resources/js/angular-ui.min.js"></script>
<script src="${contextPath}/resources/js/angular-messages.js"></script>
<script src="${contextPath}/resources/js/ngStorage.js"></script>
<script src="${contextPath}/resources/js/loader.js"></script>
<script
	src="${contextPath}/resources/js/crypto-js.min.js"></script>
<script
	src="${contextPath}/resources/js/aes.js"></script>
	<script
	src="${contextPath}/resources/js/core.min.js"></script>
	<script
	src="${contextPath}/resources/js/cipher-core.min.js"></script>
	<script
	src="${contextPath}/resources/js/mode-cfb.min.js"></script>
	<script
	src="${contextPath}/resources/js/pad-pkcs7.min.js"></script>
<script
	src="${contextPath}/resources/js/pbkdf2.js"></script>
<script
	src="${contextPath}/resources/js/RightPrice/RateCardCreationDetails_New.js"></script>
<script src="${contextPath}/resources/js/Factory/WebServiceFactory.js"></script>
<script
	src="${contextPath}/resources/js/Directives/angularCustomValidations.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		$(".customDate").datepicker({
			dateFormat : 'dd/mm/yy',
			changeMonth : true,
			changeYear : true
		});
	});
</script>
</head>
<body ng-app="RightPriceApp" ng-controller="RateCardCreationController"
	ng-focus="customDatePicker()" ng-init="currentUser('<%=session.getAttribute("username")%>')">
	<%-- ng-focus="customDatePicker()" ng-init="currentUser('<%=org.springframework.security.core.context.SecurityContextHolder.getContext().getAuthentication().getName()%>')"> --%>
	<div id="includedContent"
		ng-include="'${contextPath}/Portal/Header.jsp'"></div>
	<fieldset ng-disabled="loading || showLoader">
		<div class="container">
			<div class="divEmpty"></div>
			<div class="row marginBottom5px">
				<div class="col-sm-12">
					<h3 class="text-left" id="PageHeading">Rate Card Creation -
						Details</h3>
				</div>
			</div>
			<div>
				<form class="form-inline" role="form" name="frmRateCard"
					id="frmRateCard" novalidate>
					<fieldset ng-disabled="isViewRequest">
						<div id="includedRateCardStages" ng-if="autosidebar"
							ng-include="'${contextPath}/Portal/RateCardCompletionStage.jsp'"></div>
						<div id="includedRateCardStages" ng-if="manualsidebar"
							ng-include="'${contextPath}/Portal/RateCardManualCompletionStage.jsp'"></div>
						<div id="includedRateCardStages" ng-if="hybridsidebar"
							ng-include="'${contextPath}/Portal/RateCardHybridCompletionStage.jsp'"></div>
						<div class="row">
							<div class="col-sm-12">
								<div class="panel-group">
									<div class="panel panel-info ">
										<div class="panel-heading panelHeadingStyle">
											<div class="row ">
												<label class="control-label col-sm-10">Rate Card
													Details</label>
											</div>
										</div>
										<!--  <div class="panel-body" ng-hide = "RateCardDetailsHidden"> -->
										<div class="panel-body">
											<div class="row marginBottom5px">
												<label
													class="control-label col-sm-2 textAlignRight ">Customer</label>
												<div class="col-sm-3">
													<select id="ddlRateCardCustomer" class="form-control"
														placeholder="Please select" name="ddlRateCardCustomer"
														ng-model="frmRateCard.customer"
														ng-required="!isFirstTimePageLoaded"
														ng-change="resetFormDataOnCustomerChange();getRateCardsNew(frmRateCard.customer)"
														ng-options="cvi.customer.customerId as cvi.customer.customerName for cvi in customer| orderBy:'customer.customerName'" 
														required
														ng-class="{true: 'ng-border'}[saved && frmRateCard.ddlRateCardCustomer.$invalid]">
														<option value="" selected disabled>Please select</option>
													</select>
													<div class="error-messages" ng-if="saved"
														ng-messages="frmRateCard.ddlRateCardCustomer.$error">
														<em class="error help-block has-error"
															ng-message="required">Please select Customer</em>
													</div>
												</div>
												<label
													class="control-label col-sm-3 textAlignRight ">Action</label>
												<div class="col-sm-3">
													<select id="ddlRateCardAction" class="form-control"
														placeholder="Please select" ng-disabled="isAddDisabled"
														ng-required="!isAddDisabled" name="ddlRateCardAction"
														ng-options="ac.id as ac.name for ac in actionType"
														ng-model="frmRateCard.action"
														ng-change="resetFormDataOnActionChange();"
														ng-required="!isFirstTimePageLoaded"
														ng-class="{true: 'ng-border'}[saved && frmRateCard.ddlRateCardAction.$invalid]" required>
														<option value="" selected>Please select</option>
														<!-- <option value="1">Add</option>
												<option value="2">Edit</option>
												<option value="3">View</option> -->
													</select>
													<div class="error-messages" ng-if="saved"
														ng-messages="frmRateCard.ddlRateCardAction.$error">
														<em class="error help-block has-error"
															ng-message="required">Please select Action</em>
													</div>
												</div>
											</div>
											<div class="row marginBottom5px">
												<label
													class="control-label col-sm-2 textAlignRight">Rate
													Card ID</label>
												<div class="col-sm-3">
													<select id="ddlRateCardID" class="form-control"
														placeholder="Please select" name="ddlRateCardID"
														ng-model="frmRateCard.rateCardId"
														ng-disabled="isRateCardIDdisabled"
														ng-options="rcd.rcId as rcd.rcId for rcd in rateCardId"
														ng-change="getRateCardDetailsFromRCIdGFT(frmRateCard.rateCardId);"
														ng-class="{true: 'ng-border'}[saved && frmRateCard.ddlRateCardID.$invalid]"
														ng-required="!isRateCardIDdisabled"
														ng-required="!isFirstTimePageLoaded">
														<option value="" selected disabled>Please select</option>
													</select>
													<div class="error-messages" ng-if="saved"
														ng-messages="frmRateCard.ddlRateCardID.$error">
														<em class="error help-block has-error"
															ng-message="required">Please select Rate Card ID</em>
													</div>
												</div>
												<label
													class="control-label col-sm-3 textAlignRight ">Rate
													Card Name</label>
												<div class="col-sm-3">
													<input name="txtRateCardRateCardName" type="text"
														class="form-control" id="txtRateCardRateCardName"
														ng-maxlength="100" ng-pattern="/^[a-z\d\_\:\-'\s]+$/i"
														
														ng-model="frmRateCard.ratecardname"
														ng-class="{true: 'ng-border'}[saved && frmRateCard.txtRateCardRateCardName.$invalid]"
														ng-required="!isFirstTimePageLoaded">
													<div class="error-messages" ng-if="saved"
														ng-messages="frmRateCard.txtRateCardRateCardName.$error">
														<em class="error help-block has-error"
															ng-message="required">Rate Card Name is required!</em> <em
															class="error help-block has-error" ng-message="maxlength">Rate
															card name should not exceed 100 character.</em> <em
															class="error help-block has-error" ng-message="pattern">Please
															enter alphanumeric only</em>
													</div>
												</div>
											</div>
											<div class="row marginBottom5px">
												<label
													class="control-label col-sm-2 textAlignRight ">Start
													Date <br>(dd/mm/yyyy) </label>
												<div class="col-sm-3">
													<input type="text" class="form-control customDate"
														placeholder="dd/mm/yyyy" id="txtRateCardStartDate"
														
														name="txtRateCardStartDate"
														ng-model="frmRateCard.startdate"
														ng-change="calApplicableYears(frmRateCard.startdate,frmRateCard.enddate);"
														ng-class="{true: 'ng-border'}[saved && frmRateCard.txtRateCardStartDate.$invalid]"
														ng-required="!isFirstTimePageLoaded"
														ng-pattern="/^(((0[1-9]|[12]\d|3[01])\/(0[13578]|1[02])\/((19|[2-9]\d)\d{2}))|((0[1-9]|[12]\d|30)\/(0[13456789]|1[012])\/((19|[2-9]\d)\d{2}))|((0[1-9]|1\d|2[0-8])\/02\/((19|[2-9]\d)\d{2}))|(29\/02\/((1[6-9]|[2-9]\d)(0[48]|[2468][048]|[13579][26])|((16|[2468][048]|[3579][26])00))))$/i">
													<div class="error-messages" ng-if="saved"
														ng-messages="frmRateCard.txtRateCardStartDate.$error">
														<em class="error help-block has-error"
															ng-message="required">Start Date is required!</em> <em
															class="error help-block has-error" ng-message="pattern">Start
															Date is invalid!</em>
													</div>
												</div>
												
												<label
													class="control-label col-sm-3 textAlignRight ">End
													Date<br>(dd/mm/yyyy) </label>
												<div class="col-sm-3">
													<input type="text" class="form-control customDate"
														placeholder="dd/mm/yyyy" id="txtRateCardEndDate"
														name="txtRateCardEndDate"
														
														ng-model="frmRateCard.enddate"
														ng-change="calApplicableYears(frmRateCard.startdate,frmRateCard.enddate);"
														ng-required="!isFirstTimePageLoaded"
														ng-class="{true: 'ng-border'}[saved && frmRateCard.txtRateCardEndDate.$invalid]"
														required end-date-compare="frmRateCard.startdate"
														ng-pattern="/^(((0[1-9]|[12]\d|3[01])\/(0[13578]|1[02])\/((19|[2-9]\d)\d{2}))|((0[1-9]|[12]\d|30)\/(0[13456789]|1[012])\/((19|[2-9]\d)\d{2}))|((0[1-9]|1\d|2[0-8])\/02\/((19|[2-9]\d)\d{2}))|(29\/02\/((1[6-9]|[2-9]\d)(0[48]|[2468][048]|[13579][26])|((16|[2468][048]|[3579][26])00))))$/i">
													<div class="error-messages" ng-if="saved"
														ng-messages="frmRateCard.txtRateCardEndDate.$error">
														<em class="error help-block has-error"
															ng-message="required">End Date is required!</em> <em
															class="error help-block has-error" ng-message="pattern">End
															Date is invalid!</em> <em class="error help-block has-error"
															ng-message="endDateCompare">End Date should be
															greater than Start Date</em>
													</div>
												</div>
											</div>
											<div class="row marginBottom5px">
											<label class="control-label col-sm-2 textAlignRight">Applicable
													Months</label>
												<div class="col-sm-3">
													<input type="text" id="txtRateCardApplicableYears"
														class="form-control" name="txtRateCardApplicableYears"
														
														ng-model="frmRateCard.applicableMonths"
														ng-class="{true: 'ng-border'}[saved && frmRateCard.txtRateCardApplicableYears.$invalid]" 
														ng-required="!isFirstTimePageLoaded" value="4" readonly>
													
												<div class="error-messages" ng-if="saved"
														ng-messages="frmRateCard.txtRateCardApplicableYears.$error">
														<em class="error help-block has-error"
															ng-message="required">Please select Applicable
															 Months</em>
													</div>
													</div>
												<label
													class="control-label col-sm-3 textAlignRight ">Billing
													Currency</label>
												<div class="col-sm-3">
													<select id="ddlRateCardCurrency" class="form-control"
														placeholder="Please select" name="ddlRateCardCurrency"
														ng-required="!isFirstTimePageLoaded"
														ng-model="frmRateCard.currency"
														ng-options="cv.currencyId as cv.currencyName for cv in currency"
														ng-required="!isFirstTimePageLoaded"
														ng-class="{true: 'ng-border'}[saved && frmRateCard.ddlRateCardCurrency.$invalid]">
														<option value="" selected disabled>Please select</option>
													</select>
													<div class="error-messages" ng-if="saved"
														ng-messages="frmRateCard.ddlRateCardCurrency.$error">
														<em class="error help-block has-error"
															ng-message="required">Please select Billing
															 Currency</em>
													</div>
												</div>
											</div>
											<div class="row marginBottom5px">
												<label
													class="control-label col-sm-2 textAlignRight ">Industry</label>
												<div class="col-sm-3">
													<select id="ddlRateCardIndustry" class="form-control"
														placeholder="Please select" name="ddlRateCardIndustry"
														
														ng-model="frmRateCard.industry"
														ng-required="!isFirstTimePageLoaded"
														ng-change = "setKpoManual(frmRateCard.industry);"
														ng-class="{true: 'ng-border'}[saved && frmRateCard.ddlRateCardIndustry.$invalid]">
														<option value="" selected disabled>Please select</option>
														<option value="1">IT</option>
														<option value="0">KPO</option>
														<option value="2">KPO JV </option>
													</select>
													<div class="error-messages" ng-if="saved"
														ng-messages="frmRateCard.ddlRateCardIndustry.$error">
														<em class="error help-block has-error"
															ng-message="required">Please select Industry</em>
													</div>
												</div>
											<label
													class="control-label col-sm-3 textAlignRight ">Expected TCV</label>
												<div class="col-sm-3">
													<input name="txtRateCardTCV" type="text"
														class="form-control" id="txtRateCardTCV"
														ng-model="frmRateCard.tcv"
														
														ng-required="!isFirstTimePageLoaded"
														ng-class="{true: 'ng-border'}[saved && frmRateCard.txtRateCardTCV.$invalid]"
														>
													<div class="error-messages" ng-if="saved"
														ng-messages="frmRateCard.txtRateCardTCV.$error">
														<em class="error help-block has-error"
															ng-message="required">TCV is required!</em> <em
															class="error help-block has-error" ng-message="pattern">Please
															enter numeric value up to 8 digit and 2 decimal.</em>
													</div>
												</div>	
											
											</div>
											<div class="row marginBottom5px" >						
						<label class="control-label col-sm-2 textAlignRight " >Country
							</label>
						<!-- ng-options="rc as rc.rcName for rc in rateCardName" "con.countryId as con.country.countryName for con in country"-->
						<!-- "rate.rcId as rate.rcName for rate in rateCards"	 -->						
						<div class="col-sm-3">							
							<Select id="dlcountry" class="form-control"
										placeholder="Please select" name="dlcountry" ng-change="getCities(frmRateCard.countryNameModel)" ng-class="{true: 'ng-border'} [(update && frmRateCard.dlcountry.$invalid)]"
										ng-model="frmRateCard.countryNameModel" ng-options="con.countryId as con.countryName for con in country| orderBy:'countryName'" required>
										<option value="" selected >Please select</option>										
							</Select>
							 <div class="error-messages" ng-if="saved" ng-messages="frmRateCard.dlcountry.$error">
						     		<em class="error help-block has-error" ng-message="required">Please select Country</em>
								</div>
						</div> 
						<label class="control-label col-sm-3 textAlignRight ">City
							</label>
						<div class="col-sm-3">							
							<Select id="dlcity" class="form-control"
										placeholder="Please select" name="dlcity"  ng-class="{true: 'ng-border'} [(update && frmRateCardl.dlcity.$invalid)]"
										ng-model="frmRateCard.cityNameModel" ng-options="cit.cityId as cit.cityName for cit in city| orderBy:'cityName'"  required>
										<option value="" selected >Please select</option>
										
							</Select>
							 <div class="error-messages" ng-if="saved" ng-messages="frmRateCard.dlcity.$error">
						     		<em class="error help-block has-error" ng-message="required">Please select City</em>
								</div>
						</div> 
					</div>
					
										<div class="row marginBottom5px">
												<label
													class="control-label col-sm-2 textAlignRight ">Onsite
													% </label>
												<div class="col-sm-3">
													<input name="txtRateCardExpectedOnsiteResource" type="text"
														class="form-control"
														id="txtRateCardExpectedOnsiteResource"
														ng-model="frmRateCard.expectedonsiteresource"
														ng-blur="calculateExpectedOffshore()"
														ng-required="!isFirstTimePageLoaded"
														
														ng-class="{true: 'ng-border'}[saved && frmRateCard.txtRateCardExpectedOnsiteResource.$invalid]"
														ng-pattern="/^[0-9]+(\.[0-9]{1,2})?$/"
														checklthundredpercente>
													 <div class="error-messages" ng-if="saved"
														ng-messages="frmRateCard.txtRateCardExpectedOnsiteResource.$error">
														<em class="error help-block has-error"
															ng-message="required">Expected Onsite Resource % is
															required!</em> <em class="error help-block has-error"
															ng-message="pattern">Please enter numbers with
															maximum of two decimal.</em> <em
															class="error help-block has-error"
															ng-message="checklthundredpercente">Can not be
															greater than 100.</em>
													</div> 
												</div>
												<label class="control-label col-sm-3 textAlignRight">Offshore
													%</label>
												<div class="col-sm-3">
													<input name="txtRateCardExpectedOffshoreResource"
														type="text" class="form-control"
														id="txtRateCardExpectedOffshoreResource"
														ng-model="frmRateCard.expectedoffshoreresource"
														ng-class="{true: 'ng-border'}[saved && frmRateCard.txtRateCardExpectedOffshoreResource.$invalid]" 
														ng-required="!isFirstTimePageLoaded" readonly
														>
												
												<div class="error-messages" ng-if="saved" ng-messages="frmRateCard.txtRateCardExpectedOffshoreResource.$error">
						     		<em class="error help-block has-error" ng-message="required"> Please select offshore% </em>
								</div>
										</div>		
											</div>
											<div class="row marginBottom5px">
												<label class="control-label col-sm-2 textAlignRight">Blended Rate
													</label>
												<div class="col-sm-3">
													<input type="text" id="txtRateCardBlendedRate"
														class="form-control" name="txtRateCardBlendedRtae"
														
														ng-model="frmRateCard.blendedRate"
														ng-class="{true: 'ng-border'}[saved && frmRateCard.txtRateCardBlendedRtae.$invalid]" 
														ng-required="!isFirstTimePageLoaded" value="4" >
													
												<div class="error-messages" ng-if="saved" ng-messages="frmRateCard.txtRateCardBlendedRtae.$error">
						     		<em class="error help-block has-error" ng-message="required">Please select Blended Rate</em>
								</div>
								</div>
												<label class="control-label col-sm-3 textAlignRight">Blended Cost
													</label>
												<div class="col-sm-3">
													<input type="text" id="txtRateCardBlendedCost"
														class="form-control" name="txtRateCardBlendedCost"
														
														ng-model="frmRateCard.blendedCost"
														ng-class="{true: 'ng-border'}[saved && frmRateCard.txtRateCardBlendedCost.$invalid]" 
														ng-required="!isFirstTimePageLoaded" value="4" >
													
												<div class="error-messages" ng-if="saved" ng-messages="frmRateCard.txtRateCardBlendedCost.$error">
						     		<em class="error help-block has-error" ng-message="required">Please select Blended Cost</em>
								</div>
								</div>
													</div>
													<div class="row marginBottom5px">
											<label class="control-label col-sm-2 textAlignRight">Margin Before Discount
													</label>
												<div class="col-sm-3">
													<input type="text" id="txtRateCardMarginBeforeDiscount"
														class="form-control" name="txtRateMarginBeforeDiscount"
														
														ng-model="frmRateCard.marginBeforeDiscount"
														ng-class="{true: 'ng-border'}[saved && frmRateCard.txtRateMarginBeforeDiscount.$invalid]" 
														ng-required="!isFirstTimePageLoaded" value="">
													
												<div class="error-messages" ng-if="saved" ng-messages="frmRateCard.txtRateMarginBeforeDiscount.$error">
						     		<em class="error help-block has-error" ng-message="required">Please select Margin Before Discount</em>
								</div>	
								</div>
												<label class="control-label col-sm-3 textAlignRight"> Discount
													</label>
												<div class="col-sm-3">
													<input type="text" id="txtRateCardDiscount"
														class="form-control" name="txtRateMarginDiscount"
														
														ng-model="frmRateCard.marginDiscount" 
														ng-class="{true: 'ng-border'}[saved && frmRateCard.txtRateMarginDiscount.$invalid]" 
														ng-required="!isFirstTimePageLoaded"  value="" >
												
												<div class="error-messages" ng-if="saved" ng-messages="frmRateCard.txtRateMarginDiscount.$error">
						     		<em class="error help-block has-error" ng-message="required">Please select Discount</em>
								</div>
										</div>	</div>
										     
												
											<div class="row marginBottom5px">
											<label class="control-label col-sm-2 textAlignRight">Margin After Discount
													</label>
												<div class="col-sm-3">
													<input type="text" id="txtRateCardMarginAfterDiscount"
														class="form-control" name="txtRateMarginAfterDisCount"
														ng-class="{true: 'ng-border'}[saved && frmRateCard.txtRateMarginAfterDisCount.$invalid]" 
														ng-required="!isFirstTimePageLoaded"
														ng-model="frmRateCard.marginafterdiscount" value="" >
												
												<div class="error-messages" ng-if="saved" ng-messages="frmRateCard.txtRateMarginAfterDisCount.$error">
						     		<em class="error help-block has-error" ng-message="required">Please select Margin After Discount</em>
								</div>		
												
											</div>
											
											<!-- <label class="control-label col-sm-3 textAlignRight">Customer Desc
													</label>
											 	<div class="col-sm-3">
													<input name="txtRateCardcustomerDesc"
														type="text" class="form-control"
														id="txtRateCardcustomerDesc"
														ng-model="frmRateCard.customerDesc"
														ng-class="{true: 'ng-border'}[saved && frmRateCard.txtRateCardcustomerDesc.$invalid]" 
														ng-required="!isFirstTimePageLoaded"
														>
												
												<div class="error-messages" ng-if="saved"
														ng-messages="frmRateCard.txtRateCardcustomerDesc.$error">
														<em class="error help-block has-error"
															ng-message="required">Please mention Customer Desc</em>
													</div>
													</div> -->
</div>											
											
											
											
										</div>
									</div>
								</div>
							</div>
						</div>
						
					<!-- <ng-form class="form-inline" role="form" name="frmRateCardManualUpload" id="frmRateCardManualUpload">
						<div class="row">
						<div class="col-sm-12">
							<div class="panel-group" ng-hide="UploadHidden">
								<div class="panel panel-info ">
									<div class="panel-heading panelHeadingStyle">
										<div class="row ">
											<label class="control-label col-sm-10 ">Upload Manual
										</div>
									</div>
									<div class="panel-body">
									<div class="panel-body">
										<div class="row">
											<div class="col-sm-2 "></div>
											<label
												class="control-label col-sm-2 textAlignRight required-Field">Upload
												File name</label>
											<div class="col-sm-3 ">
												<input type="file" class="form-control"
													name="fuUploadFilename" id="fuUploadFilename"
													ng-model="frmRateCardManualUpload.fuUploadFilenameModel"
													check-file-size="frmRateCardManualUpload.fuUploadFilenameModel"
													valid-file-rate-card
													ng-class="{true: 'ng-border'}[(upload) && frmRateCardManualUpload.fuUploadFilename.$invalid]"
													>
												<div class="error-messages" ng-if="(upload)"
													ng-messages="frmRateCardManualUpload.fuUploadFilename.$error">
													<em class="error help-block has-error"
														ng-message="checkfilesize">File size is not valid for
														uploading!</em> <em class="error help-block has-error"
														ng-message="required">Please upload file</em> <em
														class="error help-block has-error" ng-message="extension">File
														format is not valid for uploading!</em>
												</div>
											</div>
											<div class="col-sm-1">
												<button type="button" class="btn btn-primary btnSpace"
													id="btnUpload"
													ng-click="uploadDataNew(frmRateCardManualUploadNew);" ng-disabled = "uploadBtnDisable">Upload</button>
											</div>
					       					<div class="col-sm-1 divPaddingLeftZero">
												<div class="divDownloadImg">
														<a href="#" id="APPROVAL_ATTACHMENT_ID"
															class="anchorTrancColor"
															ng-click="downloadFile(manualRCData);"> <span model= "manualAttachementModel" id="manualAttachementFile"></span>
														</a>
												 </div>
										</div>
										</div>
									</div>
								<div>
								<fieldset ng-disabled="IsDisabled" ng-hide="tableHide">
									<table
										class="	table clsTable table-striped table-bordered table-hover table-condensed "
										border="0" id="PolicyReportTbl">
										<thead>
											<tr>
												<th width="60%">File Name</th>
												<th width="15%">Date Of Upload [dd/mm/yyyy]</th>
												<th width="15%">Action</th>
											</tr>
										</thead>
										<tbody id="tBody">
											<tr id="ManualDealattachements"
												ng-repeat="row in MDAttachementData">
												<td width="60%"><a href="#" id="Attach_Download"
													class="control-label  textAlignLeft"
													ng-click="downloadFile(row.objectid);"><label>{{row.originalfilename}}</label></a></td>
												<td width="25%"><label>{{row.createdOn}}</label></td>
												<td width="15%"><a href="#" id="Attach_Delete"
													class="control-label  textAlignLeft"
													ng-click="deleteFile(row.objectid);"><label>Delete</label></a>
												</td>

											</tr>
											<tr>
											</tr>


										</tbody>
									</table>
								</fieldset>
								</div>
								</div>
							</div>
							</div>
						</div>
					</div>
				</ng-form> -->
				<ng-form role="form" name="frmRateCardManualUploadNew" id="frmRateCardManualUploadNew">
										<div class="row">
						<div class="col-sm-12">
							<div class="panel-group" ng-hide="UploadHidden">
								<div class="panel panel-info ">
									<div class="panel-heading panelHeadingStyle">
										<div class="row ">
											<label class="control-label col-sm-10 ">Upload Section
										</div>
									</div>
									<div class="panel-body">
									<div class="panel-body">
										<div class="row text-center">
											
											<label class="control-label col-md-4 textAlignRight marginBottom">Upload File</label>
											<div class="col-md-4">
												<input type="file" class="form-control marginBottom" name="fuUploadFilename" id="fuUploadFilename"
													ng-model="frmRateCardManualUploadNew.fuUploadFilenameModel" check-file-size="frmRateCardManualUploadNew.fuUploadFilenameModel"
													valid-File ng-class="{true: 'ng-border'}[onUpload && frmRateCardManualUploadNew.fuUploadFilename.$invalid]" required="onUpload">
												<div class="error-messages" ng-if="onUpload" ng-messages="frmRateCardManualUploadNew.fuUploadFilename.$error">
													<em class="error help-block has-error" ng-message="required">Please insert the file.</em> 
													<em class="error help-block has-error" ng-message="checkfilesize">File size is not valid for uploading!</em> 
													<em class="error help-block has-error" ng-message="extension">File format is not valid for uploading!</em>
												</div>
											</div>
											<div class="col-md-2">
												<button type="button" class="btn btn-primary btnSpace" id="btnUpload" ng-click="uploadDataNew(frmRateCardManualUploadNew);"
													 ng-disabled="isReqestSubmitted">Upload</button>
											</div>
											
										</div>
										
										</div>
										<div class="divEmptyThrice"></div>
										<div class="divEmptyThrice"></div>
										<div class="row">
											<div class="col-md-12">
												<div class="table-responsive">
													<table class="	table clsTable table-striped table-bordered table-hover table-condensed"
														border="0" id="fileDownloadTable" ng-hide = "downloadHide">
														<thead>
															<tr>
																<th width="50%">File Name</th>
																 <!-- <th width="25%">File Description</th> -->
																<!-- <th width="15%">Uploaded By</th> -->
																<th width="25%">Date Of Upload [mm/dd/yyyy]</th> 
																<th width="15%" ng-hide="isOpportunitySubmitted">Action</th>
															</tr>
														</thead>
														<tbody id="tBody">
															<tr id="ManualDealattachements" ng-repeat="row in MDAttachementData">
																<td width="60%"><a href="#" id="Attach_Download" ng-model= "manualAttachementModel"
																	class="control-label  textAlignLeft"
																	ng-click="downloadFileRC(row.id);"><label>{{row.fileName}}</label></a></td>
																<!-- <td width="25%"><label>{{row.fileDescription}}</label></td>
																<td width="15%"><label>{{row.fileUploaderName}}</label></td> -->	
																<td width="25%"><label>{{row.createdOn}}</label></td>
																<td width="15%" ng-hide="isOpportunitySubmitted"><a href="#" id="Attach_Delete"
																	class="control-label  textAlignLeft"
																	ng-click="deleteRCFile(row.id);"><label>Delete</label></a>
																</td>
															</tr>
														</tbody>
													</table>
												</div>
											</div>
										</div>
										</div>
										</div>
									</ng-form>
									
					<div class="divEmptyThrice"></div>
					<div class="divEmptyThrice"></div>				
					<div class="row text-center">
						<div class="col-sm-12">
							
							<button type="button" class="btn btn-primary btnSpace"
								id="btnDetailsSave" ng-disabled="btnDisable"
								ng-click="saveRateCardNew(frmRateCard);">Save</button>
							
							<button type="button" class="btn btn-info" id="btnNext" ng-click="cancelRateCard(frmRateCard)">Close</button>
							
						</div>
					</div>
				</form>
				<div class="divEmptyThrice"></div>
<!-- 				<ng-form class="form-inline" role="form" name="frmRateCardManualUpload" id="frmRateCardManualUpload"> -->
<!-- 						<div class="row"> -->
<!-- 						<div class="col-sm-12"> -->
<!-- 							<div class="panel-group" ng-hide="UploadHidden"> -->
<!-- 								<div class="panel panel-info "> -->
<!-- 									<div class="panel-heading panelHeadingStyle"> -->
<!-- 										<div class="row "> -->
<!-- 											<label class="control-label col-sm-10 ">Upload Manual -->
<!-- 										</div> -->
<!-- 									</div> -->
<!-- 									<div class="panel-body"> -->
<!-- 									<div class="panel-body"> -->
<!-- 										<div class="row"> -->
<!-- 											<div class="col-sm-2 "></div> -->
<!-- 											<label -->
<!-- 												class="control-label col-sm-2 textAlignRight required-Field">Upload -->
<!-- 												File name</label> -->
<!-- 											<div class="col-sm-3 "> -->
<!-- 												<input type="file" class="form-control" -->
<!-- 													name="fuUploadFilename" id="fuUploadFilename" -->
<!-- 													ng-model="frmRateCardManualUpload.fuUploadFilenameModel" -->
<!-- 													check-file-size="frmRateCardManualUpload.fuUploadFilenameModel" -->
<!-- 													valid-file-rate-card -->
<!-- 													ng-class="{true: 'ng-border'}[(upload) && frmRateCardManualUpload.fuUploadFilename.$invalid]" -->
<!-- 													> -->
<!-- 												<div class="error-messages" ng-if="(upload)" -->
<!-- 													ng-messages="frmRateCardManualUpload.fuUploadFilename.$error"> -->
<!-- 													<em class="error help-block has-error" -->
<!-- 														ng-message="checkfilesize">File size is not valid for -->
<!-- 														uploading!</em> <em class="error help-block has-error" -->
<!-- 														ng-message="required">Please upload file</em> <em -->
<!-- 														class="error help-block has-error" ng-message="extension">File -->
<!-- 														format is not valid for uploading!</em> -->
<!-- 												</div> -->
<!-- 											</div> -->
<!-- 											<div class="col-sm-1"> -->
<!-- 												<button type="button" class="btn btn-primary btnSpace" -->
<!-- 													id="btnUpload" -->
<!-- 													ng-click="uploadData(frmRateCardManualUpload);" ng-disabled = "uploadBtnDisable">Upload</button> -->
<!-- 											</div> -->
<!-- 					       					<div class="col-sm-1 divPaddingLeftZero"> -->
<!-- 												<div class="divDownloadImg"> -->
<!-- 														<a href="#" id="APPROVAL_ATTACHMENT_ID" -->
<!-- 															class="anchorTrancColor" -->
<!-- 															ng-click="downloadFile(manualRCData);"> <span model= "manualAttachementModel" id="manualAttachementFile"></span> -->
<!-- 														</a> -->
<!-- 												 </div> -->
<!-- 										</div> -->
<!-- 										</div> -->
<!-- 									</div> -->
<!-- 								<div> -->
<!-- 								<fieldset ng-disabled="IsDisabled" ng-hide="tableHide"> -->
<!-- 									<table -->
<!-- 										class="	table clsTable table-striped table-bordered table-hover table-condensed " -->
<!-- 										border="0" id="PolicyReportTbl"> -->
<!-- 										<thead> -->
<!-- 											<tr> -->
<!-- 												<th width="60%">File Name</th> -->
<!-- 												<th width="15%">Date Of Upload [dd/mm/yyyy]</th> -->
<!-- 												<th width="15%">Action</th> -->
<!-- 											</tr> -->
<!-- 										</thead> -->
<!-- 										<tbody id="tBody"> -->
<!-- 											<tr id="ManualDealattachements" -->
<!-- 												ng-repeat="row in MDAttachementData"> -->
<!-- 												<td width="60%"><a href="#" id="Attach_Download" -->
<!-- 													class="control-label  textAlignLeft" -->
<!-- 													ng-click="downloadFile(row.objectid);"><label>{{row.originalfilename}}</label></a></td> -->
<!-- 												<td width="25%"><label>{{row.createdOn}}</label></td> -->
<!-- 												<td width="15%"><a href="#" id="Attach_Delete" -->
<!-- 													class="control-label  textAlignLeft" -->
<!-- 													ng-click="deleteFile(row.objectid);"><label>Delete</label></a> -->
<!-- 												</td> -->

<!-- 											</tr> -->
<!-- 											<tr> -->
<!-- 											</tr> -->


<!-- 										</tbody> -->
<!-- 									</table> -->
<!-- 								</fieldset> -->
<!-- 								</div> -->
<!-- 								</div> -->
<!-- 							</div> -->
<!-- 							</div> -->
<!-- 						</div> -->
<!-- 					</div> -->
<!-- 				</ng-form> -->

			</div>
		</div>
	</fieldset>
	<div id="footer" ng-include="'${contextPath}/Portal/Footer.jsp'"></div>
	<div id="Footer"
		ng-include="'${contextPath}/Portal/TopBottomNavigation.jsp'"></div>
	<div id="loader" ng-include="'${contextPath}/Portal/loader.jsp'"></div>
</body>
</html>
