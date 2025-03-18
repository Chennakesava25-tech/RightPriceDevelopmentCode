<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
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
    <link href="${contextPath}/resources/css/SAPStyleSheet.css" rel="stylesheet" />
    <link href="${contextPath}/resources/css/loader.css" rel="stylesheet" />
    <link href="${contextPath}/resources/css/AngularCSS.css" rel="stylesheet" />
    <script src="${contextPath}/resources/js/jquery.min.js"></script>
    <script src="${contextPath}/resources/js/bootstrap.js"></script>
    <script src="${contextPath}/resources/js/jquery.serializeJSON.min.js"></script>
    <script src="${contextPath}/resources/js/jquery-ui.js"></script> 	
    <link href="${contextPath}/resources/css/jquery-ui.css" rel="stylesheet" />
    <link href="${contextPath}/resources/css/bootstrap-dialog.css" rel="stylesheet" />
    <script src="${contextPath}/resources/js/bootstrap-dialog.js"></script>
    <script src="${contextPath}/resources/js/jquery.validate.js"></script>
    <script src="${contextPath}/resources/js/jqueryValidations.js"></script>
    <script src="${contextPath}/resources/js/additional-methods.js"></script>
    <script src="${contextPath}/resources/js/jqueryValidations.js"></script>
    <link href="${contextPath}/resources/css/ie10-viewport-bug-workaround.css" rel="stylesheet" />
	<link href="${contextPath}/resources/css/sticky-footer-navbar.css" rel="stylesheet" />
	<script src="${contextPath}/resources/js/ie10-viewport-bug-workaround.js"></script>
	<script src="${contextPath}/resources/js/angular.js"></script>
	<script src="${contextPath}/resources/js/angular-ui.min.js"></script>
	<script src="${contextPath}/resources/js/angular-messages.js"></script>
	<script src="${contextPath}/resources/js/ngStorage.js"></script>
	<script src="${contextPath}/resources/js/loader.js"></script>
	<script src="${contextPath}/resources/js/RightPrice/MasterCustomerUpdate.js"></script>
	<script src="${contextPath}/resources/js/Directives/angularCustomValidations.js"></script>
	<script src="${contextPath}/resources/js/Factory/WebServiceFactory.js"></script> 
</head>
<body ng-app="RightPriceApp" ng-controller="MasterCustomerUpdateController"
	ng-focus="customDatePicker()">
	<div id="includedContent"
		ng-include="'${contextPath}/Portal/Header.jsp'"></div>
	<fieldset ng-disabled="loading || showLoader">
		<div class="container">
			<div class="divEmpty"></div>
			<div class="row marginBottom5px">
				<div class="col-sm-12">
					<h3 class="text-left" id="PageHeading">Master -
						Customer </h3>
				</div>
			</div>
			<div>
				<form class="form-inline" role="form" name="MasterCustomer"
					id="MasterCustomer" novalidate>
					<fieldset ng-disabled="isViewRequest">
						
						<div class="row">
							<div class="col-sm-12">
								<div class="panel-group">
									<div class="panel panel-info ">
										<div class="panel-heading panelHeadingStyle">
											<div class="row ">
												<label class="control-label col-sm-10">Update Customer</label>
											</div>
										</div>
										<!--  <div class="panel-body" ng-hide = "RateCardDetailsHidden"> -->
										<div class="panel-body">
											<div class="row marginBottom5px">
												<label
													class="control-label col-sm-2 textAlignRight required-Field">Customer</label>
												<div class="col-sm-3">
													<select id="ddlMasterCustomer" class="form-control"
														placeholder="Please select" name="ddlMasterCustomer"
														ng-model="MasterCustomer.customer"
														ng-change="putCustomerDetails(MasterCustomer.customer);"
														ng-options="cn as cn.customerName for cn in customer| orderBy:'customerName'"
														required
														ng-class="{true: 'ng-border'}[saved && MasterCustomer.ddlRateCardCustomer.$invalid]">
														<option value="" selected disabled>Please select</option>
													</select>
													<div class="error-messages" ng-if="saved"
														ng-messages="MasterCustomer.ddlRateCardCustomer.$error">
														<em class="error help-block has-error"
															ng-message="required">Please select Customer</em>
													</div>
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
														ng-model="MasterCustomer.updateonsiteWorkHoursmodel"
														ng-options="oi.hourId as oi.name for oi in onshoreHours"
														ng-model-options="{updateOn: 'blur'}" 
														ng-class="{true: 'ng-border'}[saved && MasterCustomer.txtRateCardOnsiteHoursPerDay.$invalid]"
														>
														<option value="" selected disabled>Please select</option>
													</select>
													<div class="error-messages" ng-if="saved"
														ng-messages="MasterCustomer.txtRateCardOnsiteHoursPerDay.$error">
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
														ng-model="MasterCustomer.updateoffshoreWorkHoursmodel"
														
														ng-options="offshoreHour.hourId as offshoreHour.name for offshoreHour in offshoreHours"
														ng-class="{true: 'ng-border'}[saved && MasterCustomer.txtRateCardOffshoreHoursPerDay.$invalid]"
														>
														<option value="" selected disabled>Please select</option>
													</select>
													<div class="error-messages" ng-if="saved"
														ng-messages="MasterCustomer.txtRateCardOffshoreHoursPerDay.$error">
														<em class="error help-block has-error"
															ng-message="required">Offshore hours Per day is
															required!</em>
													</div>
												</div>
											</div>
											
											<div class="row marginBottom5px">
												<label
													class="control-label col-sm-2 textAlignRight required-Field">CPC
													% (Rate Card)</label>
												<div class="col-sm-3">
													<input name="txtCPCCharges" type="text"
														class="form-control"
														id="txtCPCCharges"
														ng-model="MasterCustomer.updatecpcChargesmodel"
														ng-class="{true: 'ng-border'}[saved && MasterCustomer.txtCPCCharges.$invalid]"
														ng-pattern="/^[0-9]+(\.[0-9]{1,2})?$/"
														checklthundredpercente>
													<div class="error-messages" ng-if="saved"
														ng-messages="MasterCustomer.txtCPCCharges.$error">
														<em class="error help-block has-error"
															ng-message="required">Expected CPC % is
															required!</em> <em class="error help-block has-error"
															ng-message="pattern">Please enter numbers with
															maximum of two decimal.</em> <em
															class="error help-block has-error"
															ng-message="checklthundredpercente">Can not be
															greater than 100.</em>
													</div>
												</div>
											
												
												<label
													class="control-label col-sm-3 textAlignRight required-Field">CPC
													% (FP Deal)</label>
												<div class="col-sm-3">
													<input name="txtCPCCharges" type="text"
														class="form-control"
														id="txtCPCChargesFP"
														ng-model="MasterCustomer.updatecpcChargesmodelFP"
														ng-class="{true: 'ng-border'}[saved && MasterCustomer.txtCPCCharges.$invalid]"
														ng-pattern="/^[0-9]+(\.[0-9]{1,2})?$/"
														checklthundredpercente>
													<div class="error-messages" ng-if="saved"
														ng-messages="MasterCustomer.txtCPCCharges.$error">
														<em class="error help-block has-error"
															ng-message="required">Expected CPC % is
															required!</em> <em class="error help-block has-error"
															ng-message="pattern">Please enter numbers with
															maximum of two decimal.</em> <em
															class="error help-block has-error"
															ng-message="checklthundredpercente">Can not be
															greater than 100.</em>
													</div>
												</div>
										
										
											</div>
											<div class="row marginBottom5px">
											<label
													class="control-label col-sm-2 textAlignRight required-Field">Volume Discount
													%</label>
												<div class="col-sm-3">
													<input name="txtRateCardVolumeDiscount" type="text"
														class="form-control"
														id="txtRateCardVolumeDiscount"
														ng-model="MasterCustomer.updatevolumeDiscountmodel"
														ng-class="{true: 'ng-border'}[saved && MasterCustomer.txtRateCardVolumeDiscount.$invalid]"
														ng-pattern="/^[0-9]+(\.[0-9]{1,2})?$/"
														checklthundredpercente>
													<div class="error-messages" ng-if="saved"
														ng-messages="MasterCustomer.txtRateCardVolumeDiscount.$error">
														<em class="error help-block has-error"
															ng-message="required">Expected Volume Discount  % is
															required!</em> <em class="error help-block has-error"
															ng-message="pattern">Please enter numbers with
															maximum of two decimal.</em> <em
															class="error help-block has-error"
															ng-message="checklthundredpercente">Can not be
															greater than 100.</em>
													</div>
												</div>
												
												<label
													class="control-label col-sm-3 textAlignRight required-Field">IQN
													%</label>
												<div class="col-sm-3">
													<input name="txtIQNCharges" type="text"
														class="form-control"
														id="txtIQNCharges"
														ng-model="MasterCustomer.updateipcChargesmodel"
														ng-required="!isFirstTimePageLoaded"
														
														ng-class="{true: 'ng-border'}[saved && MasterCustomer.txtIQNCharges.$invalid]"
														ng-pattern="/^[0-9]+(\.[0-9]{1,2})?$/"
														checklthundredpercente>
													<div class="error-messages" ng-if="saved"
														ng-messages="MasterCustomer.txtIQNCharges.$error">
														<em class="error help-block has-error"
															ng-message="required">Expected IQN % is
															required!</em> <em class="error help-block has-error"
															ng-message="pattern">Please enter numbers with
															maximum of two decimal.</em> <em
															class="error help-block has-error"
															ng-message="checklthundredpercente">Can not be
															greater than 100.</em>
													</div>
												</div>
												</div>
															
					<div class="row text-center">
						<div class="col-sm-12">
							<button type="button" class="btn btn-primary btnSpace"
								id="btnDetailsSubmitToGFT" ng-show="showBtnSubmitTOGFt" ng-click="updateRateCard(MasterCustomer.rateCardId);">Submit To GFT</button>
							<button type="button" class="btn btn-primary btnSpace"
								id="btnUpdateUpdate"  ng-disabled="btnDisable"
								ng-click="onUpdateClick(MasterCustomer);">Save</button>
							<button type="button" class="btn btn-danger btnSpace"
								id="btnUpdateCancel" ng-click="cancelClickOnUpdate(MasterCustomer);">Cancel</button>
						</div>
					</div>
				</form>
			</div>
		</div>
	</fieldset>
	<div id="footer" ng-include="'${contextPath}/Portal/Footer.jsp'"></div>
	<div id="loader" ng-include="'${contextPath}/Portal/loader.jsp'"></div>
</body>
</html>
