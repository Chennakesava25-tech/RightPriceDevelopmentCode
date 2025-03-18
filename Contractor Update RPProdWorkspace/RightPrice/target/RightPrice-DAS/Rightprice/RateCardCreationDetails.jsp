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
	src="${contextPath}/resources/js/RightPrice/RateCardCreationDetails.js"></script>
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
	ng-focus="customDatePicker()" ng-init="currentUser('<%=session.getAttribute("user")%>')">
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
													class="control-label col-sm-2 textAlignRight required-Field">Customer</label>
												<div class="col-sm-3">
													<select id="ddlRateCardCustomer" class="form-control"
														placeholder="Please select" name="ddlRateCardCustomer"
														ng-model="frmRateCard.customer"
														ng-change="resetFormDataOnCustomerChange();getCustomerVerticalId(frmRateCard.customer);"
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
													class="control-label col-sm-3 textAlignRight required-Field">Action</label>
												<div class="col-sm-3">
													<select id="ddlRateCardAction" class="form-control"
														placeholder="Please select" ng-disabled="isAddDisabled"
														ng-required="!isAddDisabled" name="ddlRateCardAction"
														ng-options="ac.id as ac.name for ac in actionType"
														ng-model="frmRateCard.action"
														ng-change="checkFlag(frmRateCard.action);"
														ng-class="{true: 'ng-border'}[saved && frmRateCard.ddlRateCardAction.$invalid]">
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
													class="control-label col-sm-2 textAlignRight required-Field">Rate
													Card ID</label>
												<div class="col-sm-3">
													<select id="ddlRateCardID" class="form-control"
														placeholder="Please select" name="ddlRateCardID"
														ng-model="frmRateCard.rateCardId"
														
														ng-options="rcd.rcId as rcd.rcId for rcd in rateCardId"
														ng-change="getRateCardDetailsFromRCId(frmRateCard.rateCardId);"
														ng-class="{true: 'ng-border'}[saved && frmRateCard.ddlRateCardID.$invalid]"
														ng-required="!isRateCardIDdisabled">
														<option value="" selected disabled>Please select</option>
													</select>
													<div class="error-messages" ng-if="saved"
														ng-messages="frmRateCard.ddlRateCardID.$error">
														<em class="error help-block has-error"
															ng-message="required">Please select Rate Card ID</em>
													</div>
												</div>
												<label
													class="control-label col-sm-3 textAlignRight required-Field">Rate
													Card Name</label>
												<div class="col-sm-3">
													<input name="txtRateCardRateCardName" type="text"
														class="form-control" id="txtRateCardRateCardName"
														ng-maxlength="100" ng-pattern="/^[a-z\d\_\:\-'\s]+$/i"
														ng-disabled="isFirstTimePageLoaded"
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
													class="control-label col-sm-2 textAlignRight required-Field">Start
													Date <br>(dd/mm/yyyy) </label>
												<div class="col-sm-3">
													<input type="text" class="form-control customDate"
														placeholder="dd/mm/yyyy" id="txtRateCardStartDate"
														ng-disabled="isFirstTimePageLoaded"
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
													class="control-label col-sm-3 textAlignRight required-Field">End
													Date<br>(dd/mm/yyyy) </label>
												<div class="col-sm-3">
													<input type="text" class="form-control customDate"
														placeholder="dd/mm/yyyy" id="txtRateCardEndDate"
														name="txtRateCardEndDate"
														ng-disabled="isFirstTimePageLoaded"
														ng-model="frmRateCard.enddate"
														ng-change="calApplicableYears(frmRateCard.startdate,frmRateCard.enddate);"
														ng-required="!isFirstTimePageLoaded"
														ng-class="{true: 'ng-border'}[saved && frmRateCard.txtRateCardEndDate.$invalid]"
														required end-date-compare="frmRateCard.startdate"
														ng-pattern="/^(((0[1-9]|[12]\d|3[01])\/(0[13578]|1[02])\/((19|[2-9]\d)\d{2}))|((0[1-9]|[12]\d|30)\/(0[13456789]|1[012])\/((19|[2-9]\d)\d{2}))|((0[1-9]|1\d|2[0-8])\/02\/((19|[2-9]\d)\d{2}))|(29\/02\/((1[6-9]|[2-9]\d)(0[48]|[2468][048]|[13579][26])|((16|[2468][048]|[3579][26])00))))$/i">
													<div class="error-messages" ng-if="saved"
														ng-messages="frmRateCard.txtRateCardEndDate.$error">
														<em class="error help-block has-error"
															ng-message="required">End Date is required!</em> 
															<em class="error help-block has-error" ng-message="pattern">End
															Date is invalid!</em>
															 <em class="error help-block has-error"
															ng-message="endDateCompare">End Date should be
															greater than Start Date</em>
													</div>
												</div>
											</div>
											<div class="row marginBottom5px">
												<label
													class="control-label col-sm-2 textAlignRight required-Field">Expected
													End Date<br>(dd/mm/yyyy) </label>
												<div class="col-sm-3">
													<input type="text" class="form-control"
														placeholder="dd/mm/yyyy" id="txtRateCardExpectedEndDate"
														name="txtRateCardExpectedEndDate"
														ng-model="frmRateCard.expectedenddate"
														ng-required="!isFirstTimePageLoaded"
														ng-disabled="isFirstTimePageLoaded" readOnly
														ng-class="{true: 'ng-border'}[saved && frmRateCard.txtRateCardExpectedEndDate.$invalid]"
														check-expected-date
														databinding="[frmRateCard.startdate,frmRateCard.enddate]"
														ng-pattern="/^(((0[1-9]|[12]\d|3[01])\/(0[13578]|1[02])\/((19|[2-9]\d)\d{2}))|((0[1-9]|[12]\d|30)\/(0[13456789]|1[012])\/((19|[2-9]\d)\d{2}))|((0[1-9]|1\d|2[0-8])\/02\/((19|[2-9]\d)\d{2}))|(29\/02\/((1[6-9]|[2-9]\d)(0[48]|[2468][048]|[13579][26])|((16|[2468][048]|[3579][26])00))))$/i">
													<div class="error-messages" ng-if="saved"
														ng-messages="frmRateCard.txtRateCardExpectedEndDate.$error">
														<em class="error help-block has-error"
															ng-message="required">Expected End Date is required!</em>
														<em class="error help-block has-error"
															ng-message="pattern">Expected End Date is invalid!</em> <em
															class="error help-block has-error"
															ng-message="checkExpectedDate">Expected End Date
															should be between Start Date and End Date.</em>
													</div>
												</div>
												<label
													class="control-label col-sm-3 textAlignRight required-Field">Consolidated
													Final Currency</label>
												<div class="col-sm-3">
													<select id="ddlRateCardCurrency" class="form-control"
														placeholder="Please select" name="ddlRateCardCurrency"
														ng-disabled="isFirstTimePageLoaded"
														ng-model="frmRateCard.currency"
														ng-options="cv.currencyId as cv.currencyName for cv in currency"
														ng-required="!isFirstTimePageLoaded"
														ng-class="{true: 'ng-border'}[saved && frmRateCard.ddlRateCardCurrency.$invalid]">
														<option value="" selected disabled>Please select</option>
													</select>
													<div class="error-messages" ng-if="saved"
														ng-messages="frmRateCard.ddlRateCardCurrency.$error">
														<em class="error help-block has-error"
															ng-message="required">Please select Consolidated
															Final Currency</em>
													</div>
												</div>
											</div>
											<div class="row marginBottom5px">
												<label
													class="control-label col-sm-2 textAlignRight required-Field">Industry</label>
												<div class="col-sm-3">
													<select id="ddlRateCardIndustry" class="form-control"
														placeholder="Please select" name="ddlRateCardIndustry"
														ng-disabled="isFirstTimePageLoaded"
														ng-model="frmRateCard.industry"
														ng-required="!isFirstTimePageLoaded"
														ng-change = "setKpoManual(frmRateCard.industry);setteamdatils(frmRateCard.industry);checkRbuDetalis(frmRateCard.industry)"
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
												<label class="control-label col-sm-3 textAlignRight">Applicable
													Months</label>
												<div class="col-sm-3">
													<input type="text" id="txtRateCardApplicableYears"
														class="form-control" name="txtRateCardApplicableYears"
														ng-disabled="isFirstTimePageLoaded"
														ng-model="frmRateCard.applicableMonths" value="4" readonly>
												</div>
											</div>

											<div class="row marginBottom5px">
												<label
													class="control-label col-sm-2 textAlignRight required-Field"
													ng-hide="rbuHidden">RBU Type</label>
												<div class="col-sm-3">
													<select id="ddlRateCardRbu" class="form-control"
														placeholder="Please select" name="ddlRateCardrbutype"
														ng-disabled="isFirstTimePageLoaded"
														ng-model="frmRateCard.rbutype"
														ng-change="setRbuDetalis(frmRateCard.industry,frmRateCard.rbutype)"
														ng-class="{true: 'ng-border'}[saved && frmRateCard.rbutype.$invalid]"
														ng-hide="rbuHidden" ng-required="!rbuHidden">
														<option value="">Please select</option>
														<option value="1">NAO</option>
														<option value="2">CEN</option>
														<option value="3">ROW</option>
														<option value="4">GLD</option>
														<option value="5">NOR</option>
														<option value="6">SOU</option>
														<option value="7">GRW</option>
														<option value="8">UKI</option>
														<option value="9">STS</option>
														<option value="10">FNZ</option>
													</select>
													<div class="error-messages" ng-if="saved"
														ng-messages="frmRateCard.rbutype.$error">
														<em class="error help-block has-error"
															ng-message="required">Please select RBU type</em>
													</div>
												</div>
											</div>



											<div class="row marginBottom5px">
												<label
													class="control-label col-sm-2 textAlignRight required-Field">Expected TCV</label>
												<div class="col-sm-3">
													<input name="txtRateCardTCV" type="text"
														class="form-control" id="txtRateCardTCV"
														ng-model="frmRateCard.tcv"
														ng-disabled="isFirstTimePageLoaded"
														ng-required="!isFirstTimePageLoaded"
														ng-class="{true: 'ng-border'}[saved && frmRateCard.txtRateCardTCV.$invalid]"
														ng-pattern="/^[0-9]+(\.[0-9]{1,2})?$/"
														>
													<div class="error-messages" ng-if="saved"
														ng-messages="frmRateCard.txtRateCardTCV.$error">
														<em class="error help-block has-error"
															ng-message="required">TCV is required!</em> 
															<em
															class="error help-block has-error" ng-message="pattern">Please
															enter numeric value up to 8 digit and 2 decimal.</em>
													</div>
												</div>
												<label class="control-label col-sm-3 textAlignRight">Volume
													Discount %</label>
												<div class="col-sm-3">
													<input name="txtRateCardVolumeDiscount" type="text"
														class="form-control" id="txtRateCardVolumeDiscount"
														ng-model="frmRateCard.volumediscount"
														ng-disabled="isFirstTimePageLoaded"
														ng-pattern="/^[0-9]+(\.[0-9]{1,2})?$/" readonly>
													<div class="error-messages" ng-if="saved"
														ng-messages="frmRateCard.txtRateCardVolumeDiscount.$error">
														<em class="error help-block has-error"
															ng-message="pattern">Please enter numbers with
															maximum of two decimal.</em>
													</div>
												</div>
											</div>
											<div class="row marginBottom5px">
												<label class="control-label col-sm-2 textAlignRight">CPC
													%</label>
												<div class="col-sm-3">
													<input name="txtCPCCharges" type="text"
														class="form-control" id="txtCPCCharges"
														ng-model="frmRateCard.cpcCharges"
														ng-disabled="isFirstTimePageLoaded"
														ng-class="{true: 'ng-border'}[saved && frmRateCard.txtCPCCharges.$invalid]"
														ng-pattern="/^[0-9]+(\.[0-9]{1,2})?$/" readonly>
													<div class="error-messages" ng-if="saved"
														ng-messages="frmRateCard.txtCPCCharges.$error">
														<em class="error help-block has-error"
															ng-message="pattern">Please enter numbers with
															maximum of two decimal.</em>
													</div>
												</div>
												<label class="control-label col-sm-3 textAlignRight">IQN
													%</label>
												<div class="col-sm-3">
													<input name="txtIQNCharges" type="text"
														class="form-control" id="txtIQNCharges"
														ng-model="frmRateCard.iqnCharges"
														ng-disabled="isFirstTimePageLoaded"
														ng-class="{true: 'ng-border'}[saved && frmRateCard.txtIQNCharges.$invalid]"
														ng-pattern="/^[0-9]+(\.[0-9]{1,2})?$/" readonly>
													<div class="error-messages" ng-if="saved"
														ng-messages="frmRateCard.txtIQNCharges.$error">
														<em class="error help-block has-error"
															ng-message="pattern">Please enter numbers with
															maximum of two decimal.</em>
													</div>
												</div>

											</div>
											<div class="row marginBottom5px">
												<label
													class="control-label col-sm-2 textAlignRight required-Field">Onsite
													% </label>
												<div class="col-sm-3">
													<input name="txtRateCardExpectedOnsiteResource" type="text"
														class="form-control"
														id="txtRateCardExpectedOnsiteResource"
														ng-model="frmRateCard.expectedonsiteresource"
														ng-blur="calculateExpectedOffshore()"
														ng-required="!isFirstTimePageLoaded"
														ng-disabled="isFirstTimePageLoaded"
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
														ng-model="frmRateCard.expectedoffshoreresource" readonly
														ng-disabled="isFirstTimePageLoaded">
												</div>
											</div>
											<div class="row marginBottom5px">
												<label
													class="control-label col-sm-2 textAlignRight required-Field">Onsite
													hours<br>Per day
												</label>
												<div class="col-sm-3">
													<select id="txtRateCardOnsiteHoursPerDay"
														class="form-control"
														name="txtRateCardOnsiteHoursPerDay"
														ng-model="frmRateCard.onsitehoursperday"
														ng-options="oi.hourId as oi.name for oi in onshoreHours"
														ng-required="!isFirstTimePageLoaded"
														ng-class="{true: 'ng-border'}[saved && frmRateCard.txtRateCardOnsiteHoursPerDay.$invalid]"
														ng-disabled="isFirstTimePageLoaded || hundPerOnsite == true" "
														>
														<!-- ng-disabled="isFirstTimePageLoaded || frmRateCard.onsitehoursperday == true" -->
														<option value="" selected disabled>Please select</option>
													</select>
													<div class="error-messages" ng-if="saved"
														ng-messages="frmRateCard.txtRateCardOnsiteHoursPerDay.$error">
														<em class="error help-block has-error"
															ng-message="required">Onsite hours Per day is
															required!</em>
													</div>
												</div>
												<label
													class="control-label col-sm-3 textAlignRight required-Field">Offshore
													hours<br>Per day
												</label>
												<div class="col-sm-3">
													<select id="txtRateCardOffshoreHoursPerDay"
														class="form-control"
														name="txtRateCardOffshoreHoursPerDay"
														ng-model="frmRateCard.offshorehoursperday"
														ng-required="!isFirstTimePageLoaded"
														ng-options="offshoreHour.hourId as offshoreHour.name for offshoreHour in offshoreHours"
														ng-class="{true: 'ng-border'}[saved && frmRateCard.txtRateCardOffshoreHoursPerDay.$invalid]"
														ng-disabled="isFirstTimePageLoaded || hundPerOffshore  == true">
														<option value="" selected disabled>Please select</option>
														<!-- ng-disabled="isFirstTimePageLoaded || frmRateCard.offshorehoursperday == true" -->
														
													</select>
													<div class="error-messages" ng-if="saved"
														ng-messages="frmRateCard.txtRateCardOffshoreHoursPerDay.$error">
														<em class="error help-block has-error"
															ng-message="required">Offshore hours Per day is
															required!</em>
													</div>
												</div>
											</div>
											<div class="row marginBottom5px">
												<label class="control-label col-sm-2 textAlignRight required-Field">Renewal </label>
												<div class="col-sm-3">
													<select id="ddlRenewal"
														class="form-control"  name="ddlRenewal"
														ng-model="frmRateCard.renewal" ng-disabled="isFirstTimePageLoaded" ng-change="checkRenewalVal(frmRateCard.renewal)"
														ng-options = "renewal.renewalId as renewal.name for renewal in renewalArray"
														ng-class="{true: 'ng-border'}[saved && frmRateCard.ddlRenewal.$invalid]">
														<option value="" selected>Please select</option>
														<!-- <option value="0">No</option>
														<option value="1">Yes</option> -->
													</select>
													<!-- <div class="error-messages" ng-if="saved"
														ng-messages="frmRateCard.txtRateCardOnsiteHoursPerDay.$error">
														<em class="error help-block has-error"
															ng-message="required">Onsite hours Per day is
															required!</em>
													</div> -->
												</div>
												<label class="control-label col-sm-3 textAlignRight required-Field" ng-hide="rateCardHidden">Rate Card ID_Name </label>
												<div class="col-sm-3">
													<select id="ddlRcIdName"  class="form-control" placeholder="Please select"
														name="ddlRcIdName" ng-model="frmRateCard.rcIdName" ng-hide="rateCardHidden" ng-required="!rateCardHidden"
														ng-options="rcd.rcId as rcd.rcId+' - '+ rcd.rcName for rcd in rateCardOnIndustry"
														ng-class="{true: 'ng-border'}[saved && frmRateCard.ddlRcIdName.$invalid]"
														ng-disabled="isFirstTimePageLoaded">
														<option value="" selected disabled>Please select</option>
													</select>
													<div class="error-messages" ng-if="saved"
														ng-messages="frmRateCard.ddlRcIdName.$error" required>
														<em class="error help-block has-error"
															ng-message="required">Please select Rate Card Name.</em>
													</div>
												</div>
											</div>
										     <div class="row marginBottom5px">
																<label
																	class="control-label col-sm-2 textAlignRight required-Field">Do you want to update<br> the volume discount?
																</label>
																<div class="col-sm-3">
																	<select id="ddlVolumeUpdate" class="form-control" ng-options="nr.id as nr.name for nr in volumeArray" ng-class="{true: 'ng-border'} [(update && frmRateCard.ddlVolumeUpdate.$invalid)]"
																		placeholder="Please select" name="ddlVolumeUpdate" ng-model="frmRateCard.ddlVolumeUpdateModel" required
																		ng-change="setvolume(frmRateCard.ddlVolumeUpdateModel)">
																		<option value="" selected >Please select</option>
															</select>
															<div class="error-messages" ng-if="saved" ng-messages="frmRateCard.ddlVolumeUpdate.$error">
														     	<em class="error help-block has-error" ng-message="required">Please select </em>
															</div>
														</div>
														
														<label class="control-label col-sm-3 textAlignRight required-Field" ng-hide="hidevolumetxt">New Volume Discount</label>
															<div class="col-sm-3" >
																<input name="txtUpdatevolume" type="text" class="form-control" id="txtUpdatevolume"ng-hide="hidevolumetxt"ng-required="isUpdate"
					                                      		ng-model="frmRateCard.txtUpdatevolumeModel" 
																ng-class="{true: 'ng-border'} [(saved && frmRateCard.txtUpdatevolumeModel.$invalid)]" ng-maxlength="50" ng-pattern="numberRegex">
																
																<div class="error-messages" ng-if="saved" ng-messages="frmRateCard.txtUpdatevolume.$error">
														     		<em class="error help-block has-error" ng-message="required">Please Provide Volume Discount.</em>
														     		<em class="error help-block has-error"
																				ng-message="pattern">Please enter numbers with
																				maximum of two decimal.</em>
																</div>
															</div>
														
																			
														
													</div>
											<div class="row marginBottom5px">
												<label
													class="control-label col-sm-2 textAlignRight required-Field">Contingent
													Risk% </label>
												<div class="col-sm-3">
													<input name="txtRateCardContingentRisk" type="text"
														class="form-control" id="txtRateCardContingentRisk"
														ng-model="frmRateCard.ContingentRisk"
														ng-required="!isFirstTimePageLoaded"
														ng-disabled="isFirstTimePageLoaded"
														ng-class="{true: 'ng-border'}[saved && frmRateCard.txtRateCardContingentRisk.$invalid]"
														ng-pattern="/^[0-9]+(\.[0-9]{1,2})?$/"
														checklthundredpercente>
													<div class="error-messages" ng-if="saved"
														ng-messages="frmRateCard.txtRateCardContingentRisk.$error">
														<em class="error help-block has-error"
															ng-message="required">Contingent Risk % is required!</em>
														<em class="error help-block has-error"
															ng-message="pattern">Please enter numbers with
															maximum of two decimal.</em> <em
															class="error help-block has-error"
															ng-message="checklthundredpercente">Can not be
															greater than 100.</em>
													</div>
												</div>
												<div class="row marginBottom5px">
													<label
														class="control-label col-sm-2 textAlignRight required-Field">SLA
														Risk% </label>
													<div class="col-sm-3">
														<input name="txtRateCardSLARisk" type="text"
															class="form-control" id="txtRateCardSLARisk"
															ng-model="frmRateCard.SLARisk"
															ng-required="!isFirstTimePageLoaded"
															ng-disabled="isFirstTimePageLoaded"
															ng-class="{true: 'ng-border'}[saved && frmRateCard.txtRateCardSLARisk.$invalid]"
															
															>
														<div class="error-messages" ng-if="saved"
															ng-messages="frmRateCard.txtRateCardSLARisk.$error">
															<em class="error help-block has-error"
																ng-message="required">SLA Risk % is required!</em> <em
																class="error help-block has-error" ng-message="pattern">Please
																enter numbers with maximum of two decimal.</em> <em
																class="error help-block has-error"
																ng-message="checklthundredpercente">Can not be
																greater than 100.</em>
														</div>
													</div>
													
												</div>



												<div class="row marginBottom5px">
													<label
														class="control-label col-sm-2 textAlignRight required-Field"
														ng-show="isRateCardType">Rate Card Type</label>
													<div class="col-sm-3">
														<select id="ddlRateCard" class="form-control"
															name="ddlRateCard" ng-model="frmRateCard.rateCardType"
															ng-disabled="isFirstTimePageLoaded"
															ng-required="isRateCardType" ng-show="isRateCardType"
															ng-options="rcType.id as rcType.name for rcType in RateCardType"
															ng-class="{true: 'ng-border'}[saved && frmRateCard.ddlRateCard.$invalid]">
															<option value="" selected>Please select</option>
														</select>
														<div class="error-messages" ng-if="saved"
															ng-messages="frmRateCard.ddlRateCard.$error">
															<em class="error help-block has-error"
																ng-message="required">Please Select Rate Card Type.</em>
														</div>
													</div>
												</div>
												<div class="row marginBottom5px">

													<label
														class="control-label col-sm-2 textAlignRight required-Field">Fx_risk_offshore
													</label>
													<div class="col-sm-3">
														<select id="ddlOffshoreRisk" class="form-control"
															ng-options="ri.riskid as ri.name for ri in Offshorerisk"
															ng-class="{true: 'ng-border'} [(update && frmRateCard.ddlOffshoreRisk.$invalid)]"
															placeholder="Please select" name="ddlOffshoreRisk"
															ng-model="frmRateCard.ddlOffshoreRiskModel" required>
															<option value="" selected>Please select</option>
														</select>
														<div class="error-messages" ng-if="saved"
															ng-messages="frmRateCard.ddlOffshoreRisk.$error">
															<em class="error help-block has-error"
																ng-message="required">Please select offshoreRisk </em>
														</div>
													</div>
												</div>

												<div class="divEmptyThrice"></div>
												<div class="row marginBottom5px">
													<div class="col-sm-1"></div>
													<div class="col-sm-10">
														<div class="table-responsive">
															<table
																class="table clsTable table-striped table-bordered table-hover"
																ng-show="yearDiference > 0">
																<thead>
																	<tr>
																		<th>Year</th>
																		<th>YOY Onsite % rate increased</th>
																		<th>YOY Offshore % rate increased</th>
																		<th>From Month</th>
																	</tr>
																</thead>
																<tbody>
																	<tr id="{{'yearlyYOY'+'_'+($index+1)}}"
																		ng-repeat="yearlyYOYDetail in yearlyYOYDetails">
																		<td><input name="{{'txtYOYYear'+'_'+($index+1)}}"
																			ng-model="yearlyYOYDetail.year" type="text"
																			class="form-control"
																			id="{{'txtYOYYear'+'_'+($index+1)}}"
																			value="{{($index+1)}}" disabled></td>
																		<td><input
																			name="{{'txtYOYOnsite'+'_'+($index+1)}}"
																			ng-model="yearlyYOYDetail.onsite" type="text"
																			class="form-control"
																			id="{{'txtYOYOnsite'+'_'+($index+1)}}"
																			value="{{($index+1)}}"
																			ng-required="frmRateCard.industry == 1"
																			ng-class="{true: 'ng-border'}[saved && frmRateCard.{{'txtYOYOnsite'+'_'+($index+1)}}.$invalid]"
																			ng-pattern="numberRegex">
																			<div class="error-messages" ng-if="saved"
																				ng-messages="frmRateCard['txtYOYOnsite'+'_'+($index+1)].$error">
																				<em class="error help-block has-error"
																					ng-message="required">Please Enter YOY Onsite
																					% !</em> <em class="error help-block has-error"
																					ng-message="pattern">Please enter numbers with
																					maximum of two decimal.</em>
																			</div></td>
																		<td><input
																			name="{{'txtYOYOffshore'+'_'+($index+1)}}"
																			ng-model="yearlyYOYDetail.offshore" type="text"
																			class="form-control"
																			id="{{'txtYOYOffshore'+'_'+($index+1)}}"
																			value="{{($index+1)}}"
																			ng-required="frmRateCard.industry == 1"
																			ng-class="{true: 'ng-border'}[saved && frmRateCard.{{'txtYOYOffshore'+'_'+($index+1)}}.$invalid]"
																			ng-pattern="numberRegex">
																			<div class="error-messages" ng-if="saved"
																				ng-messages="frmRateCard['txtYOYOffshore'+'_'+($index+1)].$error">
																				<em class="error help-block has-error"
																					ng-message="required">Please Enter YOY
																					Offshore % !</em> <em class="error help-block has-error"
																					ng-message="pattern">Please enter numbers with
																					maximum of two decimal.</em>
																			</div></td>
																		<td><select id="{{'txtYOYMonth'+'_'+($index+1)}}"
																			class="form-control" placeholder="Please select"
																			name="{{'txtYOYMonth'+'_'+($index+1)}}"
																			ng-model="yearlyYOYDetail.month" required disabled>
																				<option value="" selected disabled>Please
																					select</option>
																				<option value="1">Jan</option>
																				<option value="2">Feb</option>
																				<option value="3">Mar</option>
																				<option value="4">Apr</option>
																				<option value="5">May</option>
																				<option value="6">Jun</option>
																				<option value="7">Jul</option>
																				<option value="8">Aug</option>
																				<option value="9">Sep</option>
																				<option value="10">Oct</option>
																				<option value="11">Nov</option>
																				<option value="12">Dec</option>
																		</select></td>
																	</tr>
																</tbody>
															</table>
														</div>
													</div>
													<div class="col-sm-1"></div>
												</div>
											</div>
										</div>
								</div>
							</div>
						</div>
						<div class="row">
							<div class="col-sm-12">
								<div class="panel-group">
									<div class="panel panel-info ">
										<div class="panel-heading panelHeadingStyle"
											ng-click="ShowHideTeamDetails()">
											<div class="row ">
												<label class="control-label col-sm-10 ">Team Details</label>
												<div class="col-sm-2 textAlignRight">
													<label class="DownArrowColor textAlignRight">
														&#9660;</label>
												</div>
											</div>
										</div>
										<div class="panel-body" ng-hide="TeamDetailsHidden">
											<!-- <div class="row marginBottom5px"> -->
											<div class="row marginBottom5px">
												<label
													class="control-label col-sm-2 textAlignRight">Spoc 1</label>
												<div class="col-sm-3">
													<select id="ddlTeamDeliverySpocName" class="form-control"
														placeholder="Please select" name="ddlTeamDeliverySpocName" ng-model="frmRateCard.deliveryspocname"
														ng-class="{true: 'ng-border'}[saved && frmRateCard.ddlTeamDeliverySpocName.$invalid]" ng-required="!isFirstTimePageLoaded"
														ng-options="en.lanId as en.employeeName for en in empName| orderBy:'employeeName'" >
														<option value="" selected disabled>Please select</option>
													</select>
													<div class="error-messages" ng-if="saved"
														ng-messages="frmRateCard.ddlTeamDeliverySpocName.$error">
														<em class="error help-block has-error"
															ng-message="required">Please select Delivery Spoc Name</em>
													</div>
												</div>
											<!-- 	<label class="control-label col-sm-2 textAlignRight">Delivery Spoc Name</label>
												<div class="col-sm-3">
													<input name="txtTeamDeliverySpocName" type="text"
														class="form-control" id="txtTeamDeliverySpocName"
														ng-disabled="isFirstTimePageLoaded"
														ng-model="frmRateCard.deliveryspocname"
														ng-required="!isFirstTimePageLoaded"
														ng-pattern="/^[a-z\d\'\s]+$/i"
														ng-class="{true: 'ng-border'}[saved && frmRateCard.txtTeamDeliverySpocName.$invalid]">
													<div class="error-messages" ng-if="saved"
														ng-messages="frmRateCard.txtTeamDeliverySpocName.$error">
														<em class="error help-block has-error"
															ng-message="required">Delivery Spoc Name is
															required!</em> <em class="error help-block has-error"
															ng-message="pattern">Please enter alphanumeric
															including '_ - only</em>
													</div>
												</div> -->
												<label class="control-label col-sm-3 textAlignRight">Spoc 2</label>
													<div class="col-sm-3">
													<select id="ddlTeamSalesSpocName" class="form-control"
														placeholder="Please select" name="ddlTeamSalesSpocName" ng-model="frmRateCard.salesspocname"
														ng-class="{true: 'ng-border'}[saved && frmRateCard.ddlTeamSalesSpocName.$invalid]" ng-required="!isFirstTimePageLoaded"
														ng-options="en.lanId as en.employeeName for en in empName| orderBy:'employeeName'">
														<option value="" selected disabled>Please select</option>
													</select>
													<div class="error-messages" ng-if="saved"
														ng-messages="frmRateCard.ddlTeamSalesSpocName.$error">
														<em class="error help-block has-error"
															ng-message="required">Please select Sales Spoc Name</em>
													</div>
												</div>
												<!-- <div class="col-sm-3">
													<input name="txtTeamSalesSpocName" type="text"
														class="form-control" id="txtTeamSalesSpocName"
														ng-disabled="isFirstTimePageLoaded"
														ng-model="frmRateCard.salesspocname"
														ng-required="!isFirstTimePageLoaded"
														ng-pattern="/^[a-z\d\'\s]+$/i"
														ng-class="{true: 'ng-border'}[saved && frmRateCard.txtTeamSalesSpocName.$invalid]">
													<div class="error-messages" ng-if="saved"
														ng-messages="frmRateCard.txtTeamSalesSpocName.$error">
														<em class="error help-block has-error"
															ng-message="required">Sales Spoc Name is required!</em> <em
															class="error help-block has-error" ng-message="pattern">Please
															enter alphanumeric including '_ - only</em>
													</div>
												</div> -->
											</div>
											<div class="row marginBottom5px">
												<label class="control-label col-sm-2 textAlignRight">Delivery
													Head</label>
												<div class="col-sm-3">
													<input name="txtTeamDeliveryHead " type="text"
														class="form-control" id="txtTeamDeliveryHead "
														ng-model="frmRateCard.deliveryhead" disabled>
												</div>
												<label class="control-label col-sm-3 textAlignRight">BU
													Head</label>
												<div class="col-sm-3">
													<input name="txtTeamBUHead " type="text"
														class="form-control" id="txtTeamBUHead "
														ng-model="frmRateCard.buhead" disabled>
												</div>
											</div>
											<div class="row marginBottom5px">
												<label class="control-label col-sm-2 textAlignRight">Vertical</label>
												<div class="col-sm-3">
													<input name="txtVertical" type="text" class="form-control"
														id="txtVertical" ng-model="frmRateCard.vertical" readonly>
												</div>
											</div>

										</div>
									</div>
								</div>
							</div>
						</div>
						<div class="row">
							<div class="col-sm-12">
								<div class="panel-group" ng-hide="utilizationPanelHide">
									<div class="panel panel-info ">
										<div class="panel-heading panelHeadingStyle"
											ng-click="ShowHideProposedCurrencies()">
											<div class="row ">
												<label class="control-label col-sm-10 ">Region-wise
													ONSITE Utilization</label>
												<div class="col-sm-2 textAlignRight">
													<label class="DownArrowColor textAlignRight">
														&#9660;</label>
												</div>
											</div>
										</div>
										<div class="panel-body" ng-hide="ProposedCurrenciesHidden"
											ng-disabled="isFirstTimePageLoaded">
											<div class="row marginBottom5px">
												<div class="col-sm-1"></div>
												<label
													class="control-label col-sm-5 textAlignLeft topPadding9px redColor"><Strong>Note:
													Manual Rate Card will not be processed by the System</Strong></label>
											</div>
											<div class="row marginBottom5px">
												<div class="col-sm-1"></div>
												<div class="col-sm-10">
													<div class="table-responsive">
														<table
															class="table clsTable table-striped table-bordered table-hover">
															<thead>
																<tr>
																	<th>Select</th>
																	<th class="width30">Base Country</th>
																	<th class="width30">Currency</th>
																	<th class="width30">Utilization</th>
																	<th class="tdManualRateCardWidth">Manual Rate Card</th>
																	<!-- <th class="tdManualRateCardWidth">Country type</th>
																	<th class="tdManualRateCardWidth">FxRiskNearshore</th> -->
																</tr>
															</thead>
															<tbody id="tBodyRegionWiseLocation">

																<tr id="{{'regoinWiseUilization'+'_'+($index+1)}}"
																	ng-repeat="regoinWiseUilization in regionWiseUtilizationDetails">
																	<td><input type="checkbox"
																		name="{{'cbxSelectTbl'+'_'+($index+1)}}"
																		id="{{'cbxSelectTbl'+'_'+($index+1)}}"
																		ng-change="addUtilization(regoinWiseUilization)"
																		class="margingRightChkBx"
																		ng-model="regoinWiseUilization.selectChkBox"
																		ng-disabled="isFirstTimePageLoaded || disableInputs"></td>
																	<td><input
																		name="{{'txtCountryName'+'_'+($index+1)}}"
																		ng-model="regoinWiseUilization.countryName"
																		type="text" class="form-control"
																		id="{{'txtCountryName'+'_'+($index+1)}}" disabled></td>

																	<td><input name="{{'txtCityName'+'_'+($index+1)}}"
																		ng-model="regoinWiseUilization.currencyCode"
																		type="text" class="form-control"
																		id="{{'txtCityName'+'_'+($index+1)}}" disabled></td>

																	<td><input
																		name="{{'txtUtilization'+'_'+($index+1)}}"
																		ng-model="regoinWiseUilization.utilization"
																		type="text" class="form-control"
																		id="{{'txtUtilization'+'_'+($index+1)}}"
																		ng-change="calculateToatlUtilization()"
																		ng-disabled="isFirstTimePageLoaded || disableInputs || !regoinWiseUilization.selectChkBox"
																		ng-pattern="/^(?:[0-9]+(?:\.[0-9]{0,2})?)?$/"
																		utilization="regoinWiseUilization.selectChkBox"
																		ng-class="{true: 'ng-border'}[saved && regoinWiseUilization.selectChkBox && frmRateCard.{{'txtUtilization'+'_'+($index+1)}}.$invalid]">
																		<div class="error-messages"
																			ng-if="saved && regoinWiseUilization.selectChkBox"
																			ng-messages="frmRateCard['txtUtilization'+'_'+($index+1)].$error">
																			<em class="error help-block has-error"
																				ng-message="pattern">Please enter numbers with
																				maximum of two decimal.</em> <em
																				class="error help-block has-error"
																				ng-message="utilization">Utilization % Should
																				be less than 100</em>
																		</div></td>
																		<td ng-if="regoinWiseUilization.rateCardProcessingType==0">
																		<select id="{{'ddlManualRateCard'+'_'+($index+1)}}" class="form-control" placeholder="Please select" name="{{'ddlManualRateCard'+'_'+($index+1)}}" 
																		ng-model="regoinWiseUilization.rateCardProcessingType" ng-disabled="isFirstTimePageLoaded|| ismanualKpo " 
																		ng-options ="oi.rateCardProcessingType as oi.name for oi in ManualRateCard">
																		<option value="" selected disabled>Please select</option>
																		</select>
																		</td>
																		<td ng-if="regoinWiseUilization.rateCardProcessingType==1">
																		<select id="{{'ddlManualRateCard'+'_'+($index+1)}}" class="form-control" placeholder="Please select" name="{{'ddlManualRateCard'+'_'+($index+1)}}" 
																		ng-model="regoinWiseUilization.rateCardProcessingType" ng-disabled="true"
																		ng-options ="oi.rateCardProcessingType as oi.name for oi in ManualRateCard">
																		<option value="" selected disabled>Please select</option>
																		</select>
																		</td>
																		<!-- <td ng-if="regoinWiseUilization.rateCardCountryType==0">
																		<select id="{{'ddlRateCardCountrytype'+'_'+($index+1)}}" class="form-control" placeholder="Please select" 
																		name="{{'ddlRateCardCountrytype'+'_'+($index+1)}}" 
																		ng-model="regoinWiseUilization.rateCardCountryType" 
																		ng-disabled="isFirstTimePageLoaded || disableInputs || !regoinWiseUilization.selectChkBox" 
																		ng-options ="ct.rateCardCountryType as ct.name for ct in CountryType">
																		<option value="" selected disabled>Please select</option>
																		</select>
																		</td>
																		<td ng-if="regoinWiseUilization.rateCardCountryType==1">
																		<select id="{{'ddlRateCardCountrytype'+'_'+($index+1)}}" class="form-control" placeholder="Please select"
																		 name="{{'ddlRateCardCountrytype'+'_'+($index+1)}}" 
																		ng-model="regoinWiseUilization.rateCardCountryType"
																		ng-disabled="isFirstTimePageLoaded || disableInputs || !regoinWiseUilization.selectChkBox"
																		ng-options ="ct.rateCardCountryType as ct.name for ct in CountryType">
																		<option value="" selected disabled>Please select</option>
																		</select>
																		</td>
																		<td>
																																													
														<select id="{{'ddlNearshoreRisk'+'_'+($index+1)}}" class="form-control" placeholder="Please select"
																		 name="{{'ddlNearshoreRisk'+'_'+($index+1)}}" 
																		ng-model="regoinWiseUilization.fxrisknearshore" 
																		
																	ng-options ="nr.nriskid as nr.name for nr in Nearshorerisk "  >
																		<option value="" selected  >Please select</option>
																		//<option value="" selected disabled>Please select</option>
																		</select>
														<div class="error-messages"
																			ng-if="saved "
																			ng-messages="frmRateCard['ddlNearshoreRisk'+'_'+($index+1)].$error">
																			<em class="error help-block has-error"
																				ng-message="required">Please select NearshoreRisk .</em>
																				 
																		</div>
																		
																		
																		
																		
																		
																		
																		</td> -->
																</tr>
																<tr class="redColor">
																	<td></td>
																	<td></td>
																	<td><strong>TOTAL Onsite Utilization</strong></td>
																	<td><input name="txtTotalUtilization"
																		ng-model="totalUtilization" type="text"
																		ng-class="{true: 'ng-border'}[(saved && frmRateCard.txtTotalUtilization.$invalid)]"
																		class="form-control" id="txtTotalUtilization"
																		checktotalutilizationvalue
																		databinding="[frmRateCard.expectedonsiteresource]"
																		disabled>
																		<div class="error-messages" ng-if="saved"
																			ng-messages="frmRateCard.txtTotalUtilization.$error">
																			<em class="error help-block has-error"
																				ng-message="checktotalutilizationvalue">Total
																				Utilization should be equal to Onsite %.</em>
																		</div></td>
																		<td></td>
																		
																</tr>
																<tr class="redColor">
																	<td></td>
																	<td></td>
																	<td><strong>Offshore Utilization</strong></td>
																	<td><input name="txtoffshoreUtilization"
																		ng-model="offShoreUtilizationModel" type="text"
																		class="form-control" id="txtoffshoreUtilization"
																		disabled>
																	</td>
																	<td></td>
																	
																</tr>
															</tbody>
														</table>
													</div>
												</div>
												<div class="col-sm-1"></div>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
						<div class="row">
							<div class="col-sm-12">
								<div class="panel-group" ng-hide="utilizationPanelHide">
									<ng-form name="onsiteLocationForm" id="onsiteLocationForm">
									<div class="panel panel-info ">
										<div class="panel-heading panelHeadingStyle"
											ng-click="ShowHideAddLocation()">
											<div class="row ">
												<label class="control-label col-sm-10 ">Add Onsite
													Location Details</label>
												<div class="col-sm-2 textAlignRight">
													<label class="DownArrowColor textAlignRight">
														&#9660;</label>
												</div>
											</div>
										</div>
										<div class="panel-body" ng-hide="AddLocationHidden">
											<div class="row marginBottom5px">
												<label
													class="control-label col-sm-2 textAlignRight required-Field">Country</label>
												<div class="col-sm-3 ">
													<select id="ddlAddLocationCountry" class="form-control"
														placeholder="Please select" name="ddlAddLocationCountry"
														ng-required="notmandatonsave"
														ng-model="onsiteLocationForm.country"
														ng-options="cn as cn.countryName for cn in chkBoxSelectedCountryList| orderBy:'countryName'"
														ng-change="getCities(onsiteLocationForm.country.countryId)"
														ng-disabled="isFirstTimePageLoaded || disableInputs"
														ng-class="{true: 'ng-border'}[cityAdd && onsiteLocationForm.ddlAddLocationCountry.$invalid]">
														<option value="" disabled>Please select</option>
													</select>
													<div class="error-messages" ng-if="cityAdd"
														ng-messages="onsiteLocationForm.ddlAddLocationCountry.$error">
														<em class="error help-block has-error"
															ng-message="required">Please select Country</em>
													</div>
												</div>
												<label
													class="control-label col-sm-2 textAlignRight required-Field">City</label>
												<div class="col-sm-3 ">
													<select id="ddlAddLocationCity" class="form-control"
														placeholder="Please select" name="ddlAddLocationCity"
														ng-required="notmandatonsave"
														ng-model="onsiteLocationForm.city"
														ng-options="ci as ci.cityName for ci in city| orderBy:'cityName'"
														ng-disabled="isFirstTimePageLoaded || disableInputs"
														ng-class="{true: 'ng-border'}[cityAdd && onsiteLocationForm.ddlAddLocationCity.$invalid]">
														<option value="" selected disabled>Please select</option>
													</select>
													<div class="error-messages" ng-if="cityAdd"
														ng-messages="onsiteLocationForm.ddlAddLocationCity.$error">
														<em class="error help-block has-error"
															ng-message="required">Please select City</em>
														<!-- <em class="error help-block has-error" ng-message="city">Please select City</em> city='frmRateCard.country' -->
													</div>
												</div>
												<div class="col-sm-2 textAlignRight">
													<button type="button" class="btn btn-info btnSpace"
														id="btnDetailsAdd"
														ng-click="addLocationRow(onsiteLocationForm)"
														ng-disabled="isFirstTimePageLoaded || disableInputs">Add</button>
												</div>
											</div>
											<div class="divEmptyThrice"></div>
											<div class="row">
												<div class="col-sm-1"></div>
												<div class="col-sm-10">
													<div class="table-responsive  ">
														<table
															class="table clsTable table-striped table-bordered table-hover table-condensed "
															id="tblAddLocation" ng-show="(locationDetails).length">
															<thead>
																<tr>
																	<th class="tdCountry">Country</th>
																	<th class="tdCity">City</th>
																	<th class="width25per">City Categorization</th>
																	<th class="width25per">City Utilization %</th>
																	<th class="tdFac">Syntel Facility</th>
																	<th class="tdPrem">Syntel Premium</th>
																	<th class="width25per">Remove</th>
																</tr>
															</thead>
															<tbody id="tBodyAddLocation">
																<tr id="{{'locationDetail'+'_'+($index+1)}}"
																	ng-repeat="locationDetail in locationDetails | orderBy:'countryId'">
																	<td><input
																		name="{{'txtSelectedCountryName'+'_'+($index+1)}}"
																		ng-model="locationDetail.countryName" type="text"
																		class="form-control"
																		id="{{'txtSelectedCountryName'+'_'+($index+1)}}"
																		disabled></td>

																	<td><input name="{{'txtCityName'+'_'+($index+1)}}"
																		ng-model="locationDetail.cityName" type="text"
																		class="form-control"
																		id="{{'txtCityName'+'_'+($index+1)}}" disabled></td>

																	<td><input name="{{'txtCityName'+'_'+($index+1)}}"
																		ng-model="locationDetail.cityCategory" type="text"
																		class="form-control"
																		id="{{'txtCityName'+'_'+($index+1)}}" disabled></td>

																	<td><input
																		name="{{'txtCityUtilization'+'_'+($index+1)}}"
																		ng-model="locationDetail.utilization" type="text"
																		class="form-control"
																		id="{{'txtCityUtilization'+'_'+($index+1)}}"
																		ng-init="checkTotalUtilization(($index+1))"
																		ng-change="checkTotalUtilization(($index+1))"
																		ng-pattern="/^(?:[0-9]+(?:\.[0-9]{0,2})?)?$/" positive
																		ng-class="{true: 'ng-border'}[saved && frmRateCard.{{'txtCityUtilization'+'_'+($index+1)}}.$invalid]">
																		<div class="error-messages" ng-if="saved"
																			ng-messages="onsiteLocationForm['txtCityUtilization'+'_'+($index+1)].$error">
																			<em class="error help-block has-error"
																				ng-message="positive">Utilization % should be
																				greater than zero.</em> <em
																				class="error help-block has-error"
																				ng-message="pattern">Please enter numbers with
																				maximum of two decimal.</em>
																		</div></td>
																	<td class="tbVeticalAlignCenter"><input
																		type="checkbox"
																		name="{{'chkbxSyntelFacility'+'_'+($index+1)}}"
																		id="{{'chkbxSyntelFacility'+'_'+($index+1)}}"
																		class="margingRightChkBx ng-pristine ng-valid ng-not-empty ng-touched"
																		ng-model="locationDetail.chkbxSyntelFacility[$index+1]"
																		ng-click="checkSyntelFacility($index+1,locationDetail);"
																		ng-checked="locationDetail.syntelFacility =='1'">
																	</td>
																	<td><input
																		name="{{'txtPremiumAmount'+'_'+($index+1)}}"
																		type="text" class="form-control"
																		id="{{'txtPremiumAmount'+'_'+($index+1)}}"
																		ng-model="locationDetail.premiumAmount"
																		ng-pattern="/^(?:[0-9]+(?:\.[0-9]{0,2})?)?$/"
																		disabled>
																		<div class="error-messages" ng-if="saved"
																			ng-messages="onsiteLocationForm['txtPremiumAmount'+'_'+($index+1)].$error">
																			<em class="error help-block has-error"
																				ng-message="pattern">Please enter numbers with
																				maximum of two decimal.</em>
																		</div></td>
																	<td><button type="button"
																			id="{{'btnRemove'+'_'+($index+1)}}"
																			ng-click="removeLocation($index)">Remove</button></td>
																</tr>
															</tbody>
														</table>
														<div class="col-sm-12 textAlignRight">
															<div class="error-messages" ng-if="saved"
																ng-messages="onsiteLocationForm.test.$error">
																<em class="error help-block has-error"
																	ng-message="greater">Utilization should be 100%</em>
															</div>
															<input name="test" id="test"
																ng-model="onsiteLocationForm.utilizationLimitCountryWise"
																type="hidden"> <input name="test" id="test"
																ng-model="onsiteLocationForm.testt"
																greater="onsiteLocationForm.utilizationLimitCountryWise"
																type="hidden">
														</div>
													</div>
												</div>
											</div>
										</div>
									</div>
									</ng-form>
								</div>
							</div>
						</div>
					</fieldset>
					<ng-form class="form-inline" role="form" name="frmRateCardManualUpload" id="frmRateCardManualUpload">
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
													ng-click="uploadData(frmRateCardManualUpload);" ng-disabled = "uploadBtnDisable">Upload</button>
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
				</ng-form>
					<div class="row text-center">
						<div class="col-sm-12">
							<button type="button" class="btn btn-primary btnSpace"
								id="btnDetailsSubmitToGFT" ng-show="showBtnSubmitTOGFt" ng-click="updateRateCard(frmRateCard.rateCardId);">Submit To GFT</button>
							<button type="button" class="btn btn-primary btnSpace"
								id="btnRecycle" ng-show="showBtnRecycle" ng-click="updateRecycleStatus(frmRateCard.rateCardId);">Recycle</button>
							<button type="button" class="btn btn-primary btnSpace"
								id="btnDetailsSave" ng-disabled="btnDisable"
								ng-click="saveRateCard(frmRateCard);">Save</button>
							<!-- <button type="button" class="btn btn-danger btnSpace"
								id="btnDetailsCancel">Cancel</button> -->
							<button type="button" class="btn btn-info " id="btnDetailsNext"
								ng-click="clicked()" ng-disabled="isNextbtnDisable" >Next</button>
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
