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
	<script src="${contextPath}/resources/js/RightPrice/FPDealCreationContractualTerms.js"></script>
	<script src="${contextPath}/resources/js/Directives/angularCustomValidations.js"></script>
	<script src="${contextPath}/resources/js/Factory/WebServiceFactory.js"></script>
</head>
<body ng-app="RightPriceApp" ng-controller="FPDealCreationContractualTermsController">
	<div id="includedContent" ng-include="'${contextPath}/Portal/Header.jsp'"></div>
	<fieldset ng-disabled="loading || showLoader">
	<div class="container">
		<div class="divEmpty"></div>
		<div class="row marginBottom5px">
			<div class="col-sm-12">
				<h3 class="text-left" id="PageHeading">FP Deal Creation - Contractual Terms</h3>
			</div>
		</div>
		<div>
			<form class="form-inline" role="form" name="frmDealId" id="frmDealId">
				<div id="includedT&PStages"
					ng-include="'${contextPath}/Portal/FPdealCompletionStage.jsp'"></div>
				<div id="includedRateCardStages"
					ng-include="'${contextPath}/Rightprice/DealCreationInformationTable.jsp'"></div>
				<div class="row">
					<div class="col-sm-12">
						<div class="panel-group">
							<div class="panel panel-info ">
								<div class="panel-heading panelHeadingStyle">
									<div class="row ">
										<label class="control-label col-sm-10 ">Contractual Terms</label>
									</div>
								</div>
								<div class="panel-body">
									<div class="row marginBottom5px">
										<label class="control-label col-sm-2 textAlignLeft ">A. Transition :</label>
										<div class="col-sm-4"></div>
										<label class="control-label col-sm-3 textAlignLeft ">B. Warranty :</label>
									</div>
									<div class="divEmpty"></div>
									<div class="row marginBottom5px">
										<label class="control-label col-sm-2 textAlignLeft required-Field">1. Is transition to be provided ?</label>
										<div class="col-sm-3">
											<select name="ddlContractualTermsQueA1" class="form-control" id="ddlContractualTermsQueA1" 
											placeholder="Please Select" ng-model="contractualterms.contractualtermsquea1model" 
											ng-class="{true: 'ng-border'}[(onSave && frmDealId.ddlContractualTermsQueA1.$invalid)]"
											required>
												<option value="" selected disabled>Please select</option>
												<option value="1">Yes</option>
												<option value="0">No</option>
											</select>
											<div class="error-messages" ng-if= "onSave" ng-messages="frmDealId.ddlContractualTermsQueA1.$error">
									        	<em class="error help-block has-error" ng-message="required">Please Select Is Transition To Be Provided!</em>
									        </div>
										</div>
										<div class="col-sm-1"></div>
										<label class="control-label col-sm-2 textAlignLeft required-Field">1. Is warranty to be provided ?</label>
										<div class="col-sm-3">
											<select name="ddlContractualTermsQueB1" class="form-control" id="ddlContractualTermsQueB1" 
											placeholder="Please Select" ng-model="contractualterms.ContractualTermsQueB1Model" 
											ng-class="{true: 'ng-border'}[(onSave && frmDealId.ddlContractualTermsQueB1.$invalid)]"
											required>
												<option value="" selected disabled>Please select</option>
												<option value="1">Yes</option>
												<option value="0">No</option>
											</select>
											<div class="error-messages" ng-if= "onSave" ng-messages="frmDealId.ddlContractualTermsQueB1.$error">
									        	<em class="error help-block has-error" ng-message="required">Please Select Is Warranty To Be Provided!</em>
									        </div>
										</div>
									</div>
									<div class="row marginBottom5px">
										<label class="control-label col-sm-2 textAlignLeft required-Field lblPaddingRight5px">2. Is transition chargeable ?</label>
										<div class="col-sm-3">
											<select name="ddlContractualTermsQueA2" class="form-control" id="ddlContractualTermsQueA2" 
											placeholder="Please Select" ng-model="contractualterms.contractualtermsquea2model" ng-change="checkFlag();" 
											ng-class="{true: 'ng-border'}[(onSave && frmDealId.ddlContractualTermsQueA2.$invalid)]"
											required>
												<option value="" selected disabled>Please select</option>
												<option value="1">Yes</option>
												<option value="0">No</option>
											</select>
											<div class="error-messages" ng-if= "onSave" ng-messages="frmDealId.ddlContractualTermsQueA2.$error">
									        	<em class="error help-block has-error" ng-message="required">Please select Is Transition Chargeable!</em>
									        </div>
										</div>
										<div class="col-sm-1"></div>
										<label class="control-label col-sm-2 textAlignLeft required-Field">2. Is warranty chargeable ?</label>
										<div class="col-sm-3">
											<select name="ddlContractualTermsQueB2" class="form-control" id="ddlContractualTermsQueB2" 
											placeholder="Please Select" ng-model="contractualterms.contractualtermsqueb2model" ng-change="checkFlag();" 
											ng-class="{true: 'ng-border'}[(onSave && frmDealId.ddlContractualTermsQueB2.$invalid)]"
											required>
												<option value="" selected disabled>Please select</option>
												<option value="1">Yes</option>
												<option value="0">No</option>
											</select>
											<div class="error-messages" ng-if= "onSave" ng-messages="frmDealId.ddlContractualTermsQueB2.$error">
									        	<em class="error help-block has-error" ng-message="required">Please select Is Warranty Chargeable!</em>
									        </div>
										</div>
									</div>
									<div class="row marginBottom5px">
										<label class="control-label col-sm-2 textAlignLeft ">3. If chargeable, what percent ?</label>
										<div class="col-sm-3">
											<input name="txtContractualTermsQueA3" type="text" class="form-control" id="txtContractualTermsQueA3"
											 ng-model="contractualterms.contractualtermsquea3model" ng-disabled="isChargeablePercentDisable"
											 ng-class="{true: 'ng-border'}[(onSave && frmDealId.txtContractualTermsQueA3.$invalid)]"
											 ng-pattern="numberRegex" checklthundredpercente ng-required="!isChargeablePercentDisable"/>
											 <div class="error-messages" ng-if= "onSave" ng-messages="frmDealId.txtContractualTermsQueA3.$error">
									        	<em class="error help-block has-error" ng-message="required">Please Enter Chargeable Percent!</em>
									        	<em class="error help-block has-error" ng-message="pattern">Please Enter Numbers!</em>
												<em class="error help-block has-error" ng-message="checklthundredpercente">Percentage Should Not Exceed 100%!</em>
									        </div>
										</div>
										<div class="col-sm-1"></div>
										<label class="control-label col-sm-2 textAlignLeft ">3. If chargeable, what percent ?</label>
										<div class="col-sm-3">
											<input name="txtContractualTermsQueB3" type="text" class="form-control" id="txtContractualTermsQueB3" 
											ng-model="contractualterms.contractualtermsqueb3model" ng-disabled="isChargeablePercentDisable1" 
 											ng-class="{true: 'ng-border'}[(onSave && frmDealId.txtContractualTermsQueB3.$invalid)]"
											ng-pattern="numberRegex" checklthundredpercente ng-required="!isChargeablePercentDisable1"/>
											 <div class="error-messages" ng-if= "onSave" ng-messages="frmDealId.txtContractualTermsQueB3.$error">
									        	<em class="error help-block has-error" ng-message="required">Please Enter Chargeable Percent!</em>
									        	<em class="error help-block has-error" ng-message="pattern">Please Enter Numbers!</em>
												<em class="error help-block has-error" ng-message="checklthundredpercente">Percentage Should Not Exceed 100%!</em>
									        </div>
										</div>
									</div>
									<div class="row marginBottom5px">
										<label class="control-label col-sm-2 textAlignLeft ">4. Total Efforts considered(Man Months) :</label>
										<div class="col-sm-3">
											<input name="txtContractualTermsQueA4" type="text" class="form-control" id="txtContractualTermsQueA4" 
											ng-model="contractualterms.contractualtermsquea4model" 
											ng-class="{true: 'ng-border'}[(onSave && frmDealId.txtContractualTermsQueA4.$invalid)]"
											ng-pattern="effortRegex"  required/>
											 <div class="error-messages" ng-if= "onSave" ng-messages="frmDealId.txtContractualTermsQueA4.$error">
									        	<em class="error help-block has-error" ng-message="required">Please Enter Total Efforts Considered(Man Months)!</em>
									        	<em class="error help-block has-error" ng-message="pattern">Please Enter Numbers!</em>
									        </div>
										</div>
										<div class="col-sm-1"></div>
										<label class="control-label col-sm-2 textAlignLeft ">4. Total Efforts considered(Man Months) :</label>
										<div class="col-sm-3">
											<input name="txtContractualTermsQueB4" type="text" class="form-control" id="txtContractualTermsQueB4" 
											ng-model="contractualterms.contractualtermsqueb4model" 
											ng-class="{true: 'ng-border'}[(onSave && frmDealId.txtContractualTermsQueB4.$invalid)]"
											ng-pattern="effortRegex"  required/>
											 <div class="error-messages" ng-if= "onSave" ng-messages="frmDealId.txtContractualTermsQueB4.$error">
									        	<em class="error help-block has-error" ng-message="required">Please Enter Total Efforts Considered(Man Months)!</em>
									        	<em class="error help-block has-error" ng-message="pattern">Please Enter Numbers!</em>
									        </div>
										</div>
									</div>
									<div class="divEmptyThrice"></div>
									<div class="row text-center">
										<div class="col-sm-12">
											<button type="button" class="btn btn-primary btnSpace" id="btnSave" ng-click="onSaveClick(frmDealId);">Save</button>
											<button type="button" class="btn btn-danger btnSpace" id="btnCancel" ng-click="onCancelClick(frmDealId)">Cancel</button>
											<button type="button" class="btn btn-info btnSpace" id="btnClientPrev" ng-click="Prev()">Prev</button>
											<button type="button" class="btn btn-info" id="btnClientNext" ng-click="Next()">Next</button>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</form>
		</div>
	</div>
	 </fieldset>
	<div id="footer" ng-include="'${contextPath}/Portal/Footer.jsp'"></div>
	<div id="Footer" ng-include="'${contextPath}/Portal/TopBottomNavigation.jsp'"></div>
	<div id="loader" ng-include="'${contextPath}/Portal/loader.jsp'"></div>
</body>
</html>