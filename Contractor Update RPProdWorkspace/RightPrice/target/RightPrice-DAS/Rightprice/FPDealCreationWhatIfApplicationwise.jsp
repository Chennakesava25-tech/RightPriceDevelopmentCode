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
	src="${contextPath}/resources/js/RightPrice/fpDealCreationWhatIfApplicationwise.js"></script>
<script src="${contextPath}/resources/js/Factory/WebServiceFactory.js"></script>
<script
	src="${contextPath}/resources/js/Directives/angularCustomValidations.js"></script>

</head>
<body ng-app="RightPriceApp"
	ng-controller="FPDealCreationWhatIfTowerwiseController">
	<div id="includedContent"
		ng-include="'${contextPath}/Portal/Header.jsp'"
		ng-init="getFPDealSummaryDetails('<%=session.getAttribute("username")%>');"></div>
<%-- 		ng-init="getFPDealSummaryDetails('<%=org.springframework.security.core.context.SecurityContextHolder.getContext().getAuthentication().getName()%>');"></div> --%>
	<fieldset ng-disabled="loading || showLoader">
		<div class="container">
			<div class="divEmpty"></div>
			<div class="row marginBottom5px">
				<div class="col-sm-12">
					<!-- <h3 class="text-left" id="PageHeading">FP Deal Creation - What If Analysis</h3> -->
					<h3 class="text-left" id="PageHeading">FP Deal Creation - Summary</h3>
				</div>
			</div>
			<div>
				<ng-form class="form-inline" role="form" name="whatifDeal" id="whatifDeal">
				<div id="includedT&PStages" ng-include="'${contextPath}/Portal/FPdealCompletionStage.jsp'"></div>
				<div id="includedRateCardStages" ng-include="'${contextPath}/Rightprice/DealCreationInformationTable.jsp'"></div>
				<div class="panel-body">

					<div class="row marginBottom5px">
						<div class="row marginBottom5px">
							<label
								class="control-label col-sm-2 textAlignRight required-Field">Input
								Type</label>
							<div class="col-sm-2">
								<select id="whatifinputtype" class="form-control"
									ng-options="it.id as it.name for it in selectInputType"
									ng-class="{true: 'ng-border'} [(update && whatifDeal.inputTypeModel.$invalid)]"
									ng-change="checkfpInputType(whatifDeal.inputTypeModel)"
									placeholder="Please select" name="whatifinputtype"
									ng-model="whatifDeal.inputTypeModel" required>
									<option value="" selected>Please select</option>
								</select>
							</div>
							<label
								class="control-label col-sm-2 textAlignRight required-Field">Enter Value for Final Price </label>
							<div class="col-sm-2">
								<input type="text" class="form-control control-label col-sm-2" name="txtRevenueRow1" id="txtRevenueRow1"
									ng-model="whatifDeal.userInputValue " ng-disabled="EnterValue" required>
							</div>
							<div class="col-sm-2">
								<button type="button" class="btn btn-primary btnSpace" id="btnClientSave" 
								ng-click="submitChange(whatifDeal.userInputValue)">Submit</button>
							</div>
						</div>
					</div>
				</div>
				<div class="panel-body">
					<div class="row marginBottom5px">
						<div>
							<label><i><font color="red">Note: All rates are in {{currency}} Unless otherwise stated</font></i></label>
						</div>
						<table>
							<tr>
								<td>
									<table width="750" class=" table-striped table-bordered table-hover table-condensed" id="tblParticulars">
										<!-- class="table clsTable table-striped table-bordered table-hover table-condensed "
													id="tblParticulars" -->
										<thead>
											<tr>
												<th class="firstColLeftAlign" width="20%">Particulars</th>
												<!-- input type="text" class="form-control" name="txtRevenueRow1"
															id="txtRevenueRow1" ng-model="txtRevenueRow1Model" required></td> -->
												<th width="18%">Enter Revenue For Final Price</th>
												<th width="18%">Threshold For BUH Approval</th>
												<th width="18%">Threshold For CEO Approval</th>
												<th width="18%">PM% as per Global Rate Card</th>
											</tr>
										</thead>
										<tbody id="tBodyParticulars">
											<tr ng-repeat="data in whatIfDetails">
												<td class="firstColLeftAlign">
													<span ng-if="data.indicatorFlag ==1 || data.indicatorFlag ==3 || data.indicatorFlag ==5">{{data.description}}</span>
													<span ng-if="data.indicatorFlag ==2 || data.indicatorFlag ==4 "><b>{{data.description}}</b></span>
												</td>
												<!-- <td class="firstColLeftAlign"><span ng-if="data.description !='REVENUE' && data.description !='Gross Margin %' ">{{data.userInputValue}}</span>        
														<span class="colmiddleempty" ng-if="data.description == 'REVENUE' || data.description == 'Gross Margin %' "><input type="text" class="form-control" name="txtRevenueRow1"
														id="txtRevenueRow1" ng-model="data.userInputValue" required> </span></td> -->
												<td class="tdTextAlignRight">
													<span ng-if="data.indicatorFlag ==1">{{data.userInputValue | number:0}}</span> 
													<span ng-if="data.indicatorFlag ==2"><b>{{data.userInputValue | number:0}}</b></span> 
													<span ng-if="data.indicatorFlag ==3">{{data.userInputValue*100 | number:2}}%</span> 
													<span ng-if="data.indicatorFlag ==4"><b>{{data.userInputValue*100 | number:2}}%</b></span>
													<span ng-if="data.indicatorFlag ==5">{{data.userInputValue | number:2}}</span>
												</td>
												<td class="tdTextAlignRight">
													<span ng-if="data.indicatorFlag ==1">{{data.buhThresholdValue | number:0}}</span> 
													<span ng-if="data.indicatorFlag ==2"><b>{{data.buhThresholdValue | number:0}}</b></span> 
													<span ng-if="data.indicatorFlag ==3">{{data.buhThresholdValue*100 | number:2}}%</span> 
													<span ng-if="data.indicatorFlag ==4"><b>{{data.buhThresholdValue*100 | number:2}}%</b></span>
													<span ng-if="data.indicatorFlag ==5">{{data.buhThresholdValue | number:2}}</span> 
												</td>
												<td class="tdTextAlignRight">
													<span ng-if="data.indicatorFlag ==1">{{data.ceoApprovalValue | number:0}}</span> 
													<span ng-if="data.indicatorFlag ==2"><b>{{data.ceoApprovalValue | number:0}}</b></span> 
													<span ng-if="data.indicatorFlag ==3">{{data.ceoApprovalValue*100 | number:2}}%</span>
													<span ng-if="data.indicatorFlag ==4"><b>{{data.ceoApprovalValue*100 | number:2}}%</b></span>
													<span ng-if="data.indicatorFlag ==5">{{data.ceoApprovalValue | number:2}}</span> 
												</td>
												<td class="tdTextAlignRight">
													<span ng-if="data.indicatorFlag ==1">{{data.globalRateCardValue | number:0}}</span> 
													<span ng-if="data.indicatorFlag ==2"><b>{{data.globalRateCardValue | number:0}}</b></span> 
													<span ng-if="data.indicatorFlag ==3">{{data.globalRateCardValue*100 | number:2}}%</span> 
													<span ng-if="data.indicatorFlag ==4"><b>{{data.globalRateCardValue*100 | number:2}}%</b></span>
													<span ng-if="data.indicatorFlag ==5">{{data.globalRateCardValue | number:2}}</span>
												</td>
											</tr>
										</tbody>
									</table>
									<div class="divEmptyThrice"></div>
									<div class="divEmptyThrice"></div>
									<div class="divEmptyThrice"></div>
									<div class="divEmptyThrice" ng-if="noOfYears!=5"></div>
									<table class="table clsTable table-striped table-bordered table-hover table-fixed table-condensed table-responsive" id="tblEffortsInPersonMonths">
										<thead>
											
											<!-- <tr ng-if="noOfYears==1" ng-repeat="Header in StaffingHeaderData">
												<th class="firstColLeftAlign" width="5%">Efforts in person months</th>
												<td class="thWidth4Per">YR-1</td>
											</tr>
											<tr ng-if="noOfYears==2">
												<th class="firstColLeftAlign" width="5%">Efforts in person months</th>
												<td class="thWidth4Per">YR-1</td>
												<td class="thWidth4Per">YR-2</td>
												<td class="thWidth4Per">Total</td>
											</tr>
											<tr ng-if="noOfYears==3">
												<th class="firstColLeftAlign" width="5%">Efforts in person months</th>
												<td class="thWidth4Per">YR-1</td>
												<td class="thWidth4Per">YR-2</td>
												<td class="thWidth4Per">YR-3</td>
												<td class="thWidth4Per">Total</td>
											</tr>
											<tr ng-if="noOfYears==4">
												<th class="firstColLeftAlign" width="5%">Efforts in person months</th>
												<td class="thWidth4Per">YR-1</td>
												<td class="thWidth4Per">YR-2</td>
												<td class="thWidth4Per">YR-3</td>
												<td class="thWidth4Per">YR-4</td>
												<td class="thWidth4Per">Total</td>
											</tr>
											<tr ng-if="noOfYears==5">
												<th class="firstColLeftAlign" width="5%">Efforts in person months</th>
												<td class="thWidth4Per">YR-1</td>
												<td class="thWidth4Per">YR-2</td>
												<td class="thWidth4Per">YR-3</td>
												<td class="thWidth4Per">YR-4</td>
												<td class="thWidth4Per">YR-5</td>
												<td class="thWidth4Per">Total</td>
											</tr>
											<tr ng-if="noOfYears==6">
												<th class="firstColLeftAlign" width="5%">Efforts in person months</th>
												<td class="thWidth4Per">YR-1</td>
												<td class="thWidth4Per">YR-2</td>
												<td class="thWidth4Per">YR-3</td>
												<td class="thWidth4Per">YR-4</td>
												<td class="thWidth4Per">YR-5</td>
												<td class="thWidth4Per">YR-6</td>
												<td class="thWidth4Per">Total</td>
											</tr>
											<tr ng-if="noOfYears==7">
												<th class="firstColLeftAlign" width="5%">Efforts in person months</th>
												<td class="thWidth4Per">YR-1</td>
												<td class="thWidth4Per">YR-2</td>
												<td class="thWidth4Per">YR-3</td>
												<td class="thWidth4Per">YR-4</td>
												<td class="thWidth4Per">YR-5</td>
												<td class="thWidth4Per">YR-6</td>
												<td class="thWidth4Per">YR-7</td>
												<td class="thWidth4Per">Total</td>
											</tr>
											<tr ng-if="noOfYears==8">
												<th class="firstColLeftAlign" width="5%">Efforts in person months</th>
												<td class="thWidth4Per">YR-1</td>
												<td class="thWidth4Per">YR-2</td>
												<td class="thWidth4Per">YR-3</td>
												<td class="thWidth4Per">YR-4</td>
												<td class="thWidth4Per">YR-5</td>
												<td class="thWidth4Per">YR-6</td>
												<td class="thWidth4Per">YR-7</td>
												<td class="thWidth4Per">YR-8</td>
												<td class="thWidth4Per">Total</td>
											</tr>
											<tr ng-if="noOfYears==9">
												<th class="firstColLeftAlign" width="5%">Efforts in person months</th>
												<td class="thWidth4Per">YR-1</td>
												<td class="thWidth4Per">YR-2</td>
												<td class="thWidth4Per">YR-3</td>
												<td class="thWidth4Per">YR-4</td>
												<td class="thWidth4Per">YR-5</td>
												<td class="thWidth4Per">YR-6</td>
												<td class="thWidth4Per">YR-7</td>
												<td class="thWidth4Per">YR-8</td>
												<td class="thWidth4Per">YR-9</td>
												<td class="thWidth4Per">Total</td>
											</tr> -->
											<tr>
												<th class="firstColLeftAlign" width="5%">Efforts In person months</th>
												<td class="thWidth4Per" ng-if="noOfYears>=1">{{StaffingHeaderData.monthYearHeader1}}</td>
												<td class="thWidth4Per" ng-if="noOfYears>=2">{{StaffingHeaderData.monthYearHeader2}}</td>
												<td class="thWidth4Per" ng-if="noOfYears>=3">{{StaffingHeaderData.monthYearHeader3}}</td>
												<td class="thWidth4Per" ng-if="noOfYears>=4">{{StaffingHeaderData.monthYearHeader4}}</td>
												<td class="thWidth4Per" ng-if="noOfYears>=5">{{StaffingHeaderData.monthYearHeader5}}</td>
												<td class="thWidth4Per" ng-if="noOfYears>=6">{{StaffingHeaderData.monthYearHeader6}}</td>
												<td class="thWidth4Per" ng-if="noOfYears>=7">{{StaffingHeaderData.monthYearHeader7}}</td>
												<td class="thWidth4Per" ng-if="noOfYears>=8">{{StaffingHeaderData.monthYearHeader8}}</td>
												<td class="thWidth4Per" ng-if="noOfYears>=9">{{StaffingHeaderData.monthYearHeader9}}</td>
												<td class="thWidth4Per" ng-if="noOfYears==10">{{StaffingHeaderData.monthYearHeader10}}</td>
												<td class="thWidth4Per" ng-if="noOfYears>1">Total</td>
											</tr>

										</thead>
										<tbody id="tBodyEffortsInPersonMonths">
											<tr ng-repeat="effortdata in whatIfEffortDetails">
												<td class="firstColLeftAlign"><b>{{effortdata.locationDescription}}</b></td>

												<td class="tdTextAlignRight" ng-if="noOfYears>=1">
												<span ng-if="effortdata.percentIndicator!=1">{{effortdata.year1Efforts}}</span>
												<span ng-if="effortdata.percentIndicator==1">{{effortdata.year1Efforts * 100 | number :2}}%</span>
												</td>
												<td class="tdTextAlignRight" ng-if="noOfYears>=2">
												<span ng-if="effortdata.percentIndicator!=1">{{effortdata.year2Efforts}}</span>
												<span ng-if="effortdata.percentIndicator==1">{{effortdata.year2Efforts * 100 | number :2}}%</span>
												</td>
												<td class="tdTextAlignRight" ng-if="noOfYears>=3">
												<span ng-if="effortdata.percentIndicator!=1">{{effortdata.year3Efforts}}</span>
												<span ng-if="effortdata.percentIndicator==1">{{effortdata.year3Efforts * 100 | number :2}}%</span>
												</td>
												<td class="tdTextAlignRight" ng-if="noOfYears>=4">
												<span ng-if="effortdata.percentIndicator!=1">{{effortdata.year4Efforts}}</span>
												<span ng-if="effortdata.percentIndicator==1">{{effortdata.year4Efforts * 100 | number :2}}%</span>
												</td>
												<td class="tdTextAlignRight" ng-if="noOfYears>=5">
												<span ng-if="effortdata.percentIndicator!=1">{{effortdata.year5Efforts}}</span>
												<span ng-if="effortdata.percentIndicator==1">{{effortdata.year5Efforts * 100 | number :2}}%</span>
												</td>
												<td class="tdTextAlignRight" ng-if="noOfYears>=6">
												<span ng-if="effortdata.percentIndicator!=1">{{effortdata.year6Efforts}}</span>
												<span ng-if="effortdata.percentIndicator==1">{{effortdata.year6Efforts * 100 | number :2}}%</span>
												</td>
												<td class="tdTextAlignRight" ng-if="noOfYears>=7">
												<span ng-if="effortdata.percentIndicator!=1">{{effortdata.year7Efforts}}</span>
												<span ng-if="effortdata.percentIndicator==1">{{effortdata.year7Efforts * 100 | number :2}}%</span>
												</td>
												<td class="tdTextAlignRight" ng-if="noOfYears>=8">
												<span ng-if="effortdata.percentIndicator!=1">{{effortdata.year8Efforts}}</span>
												<span ng-if="effortdata.percentIndicator==1">{{effortdata.year8Efforts * 100 | number :2}}%</span>
												</td>
												<td class="tdTextAlignRight" ng-if="noOfYears>=9">
												<span ng-if="effortdata.percentIndicator!=1">{{effortdata.year9Efforts}}</span>
												<span ng-if="effortdata.percentIndicator==1">{{effortdata.year9Efforts * 100 | number :2}}%</span>
												</td>
												<td class="tdTextAlignRight" ng-if="noOfYears>=10">
												<span ng-if="effortdata.percentIndicator!=1">{{effortdata.year10Efforts}}</span>
												<span ng-if="effortdata.percentIndicator==1">{{effortdata.year10Efforts * 100 | number :2}}%</span>
												</td>
												<td class="tdTextAlignRight" ng-if="noOfYears>1">
												<span ng-if="effortdata.percentIndicator!=1">{{effortdata.effortsTotal}}</span>
												<span ng-if="effortdata.percentIndicator==1">{{effortdata.effortsTotal * 100 | number:2}}%</span>
												</td>
												
											</tr>
										</tbody>

									</table>
								</td>
								<td>
									<!-- <table class="table clsTable table-striped table-bordered table-hover table-condensed marginLft" id="tblParticulars">
										class="table clsTable table-striped table-bordered table-hover table-condensed "
													id="tblParticulars"
										<thead>
											<tr>
												<th class="firstColLeftAlign" width=70%>REVENUE DIFFERENTIALS - IMPACT</th>
												<th width=30%>AMOUNT</th>
											</tr>
										</thead>

										<tbody id="tBodyParticulars" ng-repeat="whatrevenue in whatIfCalDetails">
											<tr ng-repeat="data in whatIfDetails">
												<td ng-if="data.description == 'REVENUE'"><b>Revenue as calculated per what if analysis</b></td>
												<td ng-if="data.description == 'REVENUE'"><b>Revenue as calculated per Summary</b></td>
												<td ng-if="data.description == 'REVENUE'">{{data.userInputValue | number:2}}</td>
												<td ng-if="data.description == 'REVENUE'">0</td>
											</tr>
											<tr>
												<td class="firstColLeftAlign"><b>Revenue of roles selected from Master</b></td>
												<td>{{whatrevenue.masterRevenue}}</td>
												<td>0</td>
											</tr>
											<tr>
												<td class="firstColLeftAlign"><b>Revenue of roles selected from rate card</b></td>
												<td>{{whatrevenue.rateCardRevenue}}</td>
												<td>0</td>
											</tr>
											<tr>
												<td class="firstColLeftAlign"><b>Revenue of contractor roles</b></td>
												<td>{{whatrevenue.contractorRevenue}}</td>
												<td>0</td>
											</tr>
											<tr>
												<td class="firstColLeftAlign"><b>Total revenue as per MSA</b></td>
												<td>{{whatrevenue.msaRevenue}}</td>
												<td>0</td>
											</tr>
											<tr>
														<td class="firstColLeftAlign"><b>Discount (-) / Premium (+) over GRC Revenue</b></td>
															<td></td>
														</tr>
											<tr ng-repeat="data in whatIfDetails">
												<td ng-if="data.description == 'REVENUE'"><b>Discount (-) / Premium (+)</b></td>
												<td ng-if="data.description == 'REVENUE'">{{ data.dispre | number:2}}</td>
													<td ng-if="data.description == 'REVENUE'">0</td>
											</tr>
											<tr ng-repeat="data in whatIfDetails">
												<td class="firstColLeftAlign" ng-if="data.description == 'REVENUE'"><b>Discount / Premium % </b></td>
												<td ng-if="data.description == 'REVENUE'">{{ data.diff | number:2}}%</td>
											<td ng-if="data.description == 'REVENUE'">0</td>
											</tr>
										</tbody>
									</table>  -->
									<div class="divEmptyWhatFirst" ng-if="noOfYears==1"></div>
									<div class="divEmptyWhatThrice" ng-if="noOfYears==3"></div>
									<div class="divEmptyWhatSecond" ng-if="noOfYears==2"></div>
									<div class="divEmptyWhatFourth" ng-if="noOfYears==4"></div>
									<div class="divEmptyWhatFifth" ng-if="noOfYears==5"></div>
									<div class="divEmptyWhatSixth" ng-if="noOfYears==6"></div>
									<div class="divEmptyWhatSeventh" ng-if="noOfYears==7"></div>

									<table class="table clsTable table-striped table-bordered table-hover table-condensed marginLft" id="tblParticulars">
										<!-- class="table clsTable table-striped table-bordered table-hover table-condensed "
													id="tblParticulars" -->
										<thead>
											<tr>
												<th class="firstColLeftAlign" width=70%>CONTRACTUAL TERMS</th>
												<th width=30%></th>
											</tr>
										</thead>

										<tbody id="tBodyParticulars">
											<tr>
												<td class="firstColLeftAlign"><b>A) Transition:</b></td>
												<td></td>
											</tr>
											<tr>
												<td class="firstColLeftAlign"><b>1) Is transition Chargeable or is it Free of Cost?</b></td>
												<td>
													<select id="tranCostType" class="form-control" 
														ng-options="ct.id as ct.name for ct in selectTranCostType"
														ng-class="{true: 'ng-border'} [(onSave && whatifDeal.tranCostTypeModel.$invalid)]" placeholder="Please select" 
														name="tranCostType" 
														ng-change="checktranCostType(whatifDeal.tranCostTypeModel)"
														ng-model="whatifDeal.tranCostTypeModel" required>
															<option value="" selected>Select</option>
													</select>
													<div class="error-messages" ng-if="onSave" ng-messages="whatifDeal.tranCostType.$error">
														<em class="error help-block has-error" ng-message="required">Please select</em>
													</div>
												</td>
											</tr>
											<tr>
												<td class="firstColLeftAlign"><b>2) If Chargeable, what % of it is so?</b></td>
												<td>
													<input type="text" class="form-control control-label col-sm-2" 
													name="tranChargeable" id="tranChargeable" 
													ng-class="{true: 'ng-border'} [(onSave && whatifDeal.tranChargeableModel.$invalid)]"
													ng-model="whatifDeal.tranChargeableModel" 
													ng-disabled="istranChargeable" required> 
													<div class="error-messages" ng-if="onSave" ng-messages="whatifDeal.tranChargeable.$error" >
														<em class="error help-block has-error" ng-message="required">Please select </em>
													</div>
												</td>
											</tr>
											<tr>
												<td class="firstColLeftAlign"><b>3) Have the efforts been considered for costing?</b></td>
												<td>
													<select id="tranConsiderCostType" class="form-control" ng-options="cct.id as cct.name for cct in selectTranConsiderCostType"
														ng-class="{true: 'ng-border'} [(onSave && whatifDeal.tranConsiderCostTypeModel.$invalid)]"
														placeholder="Please select" name="tranConsiderCostType" ng-model="whatifDeal.tranConsiderCostTypeModel" required disabled>
													</select>
													<div class="error-messages" ng-if="onSave" ng-messages="whatifDeal.tranConsiderCostType.$error">
														<em class="error help-block has-error" ng-message="required">Please select </em>
													</div>
												</td>
											</tr>
											<tr>
												<td class="firstColLeftAlign"><b>4) Total Efforts considered (in person months):</b></td>
												<td>
													<input type="text" class="form-control control-label col-sm-2" name="tranTotalEfforts"
														ng-class="{true: 'ng-border'} [(onSave && whatifDeal.tranTotalEfforts.$invalid)]" 
														ng-model="whatifDeal.tranTotalEffortsModel"id="tranTotalEfforts" required disabled> 
													<!--<div class="error-messages" ng-if="onSave"ng-messages="whatifDeal.tranTotalEfforts.$error">
														<em class="error help-block has-error"ng-message="required">Please select </em>
													</div> -->
												</td>
											</tr>
											<!-- <td class="firstColLeftAlign"><b></b></td>
											<td></td> -->
											<tr>
											<td class="firstColLeftAlign"><b>B) Warranty:</b></td>
											<td></td>
											<tr>
												<td class="firstColLeftAlign"><b>1) Is Warranty to be provided?</b></td>
												<td>
													<select id="warProvided" class="form-control" ng-options="ct.id as ct.name for ct in selectWarConsiderCostType"
														ng-class="{true: 'ng-border'} [(onSave && whatifDeal.warProvidedModel.$invalid)]" placeholder="Please select"
														ng-change="checkwarProvidedType(whatifDeal.warProvidedModel)" 
														name="warProvided" ng-model="whatifDeal.warProvidedModel" required>
															<option value="" selected>Select</option>
													</select>
													<div class="error-messages" ng-if="onSave" ng-messages="whatifDeal.warProvided.$error">
														<em class="error help-block has-error" ng-message="required">Please select </em>
													</div>
												</td>
											</tr>
											<tr>
												<td class="firstColLeftAlign"><b>2) If so, is it Chargeable or is Free of Cost?</b></td>
												<td>
													<select id="warConsiderCostType" class="form-control" ng-options="ct.id as ct.name for ct in selectWarCostType"
														ng-class="{true: 'ng-border'} [(onSave && whatifDeal.warConsiderCostType.$invalid)]"
														placeholder="Please select" name="warConsiderCostType" 
														ng-change="checkwarConsiderCostType(whatifDeal.warConsiderCostTypeModel)"
														ng-model="whatifDeal.warConsiderCostTypeModel" ng-disabled="iswarConsiderCostType" required>
															<option value="" selected>Select</option>
													</select>
													<div class="error-messages" ng-if="onSave" ng-messages="whatifDeal.warConsiderCostType.$error">
														<em class="error help-block has-error" ng-message="required">Please select </em>
													</div>
												</td>
											</tr>
											<tr>
												<td class="firstColLeftAlign"><b>3) If Chargeable, what % of it is so?</b></td>
												<td>
													<input type="text" class="form-control control-label col-sm-2"
														name="warrchargeable"ng-class="{true: 'ng-border'} [(onSave && whatifDeal.warrchargeable.$invalid)]"
														ng-model="whatifDeal.warrchargeableModel"id="warrchargeable" 
														ng-disabled="iswarrchargeable" required> 
													<div class="error-messages" ng-if="onSave" ng-messages="whatifDeal.warrchargeable.$error">
														<em class="error help-block has-error" ng-message="required">Please select </em>
													</div>
												</td>
											</tr>
											<tr>
												<td class="firstColLeftAlign"><b>4) Have the efforts been considered for costing?</b></td>
												<td>
													<select id="warrcostType" class="form-control"
													 	ng-options="ct.id as ct.name for ct in selectWarConsiderCostType"
														ng-class="{true: 'ng-border'} [(onSave && whatifDeal.warrcostType.$invalid)]" 
														placeholder="Please select" name="warrcostType" 
														ng-change="checkwarrcostType(whatifDeal.warrcostTypeModel)"
														ng-model="whatifDeal.warrcostTypeModel" required>
															<option value="" selected>Select</option>
													</select>
													<div class="error-messages" ng-if="onSave"ng-messages="whatifDeal.warrcostType.$error">
														<em class="error help-block has-error"ng-message="required">Please select </em>
													</div>
												</td>
											</tr>
											<tr>
												<td class="firstColLeftAlign"><b>5) Total Efforts considered (in person months):</b></td>
												<td>
													<input type="text" class="form-control control-label col-sm-2" name="warrTotalEffortsType"
														ng-class="{true: 'ng-border'} [(onSave && whatifDeal.warrTotalEffortsType.$invalid)]"
														ng-model="whatifDeal.warrTotalEffortsTypeModel" 
														ng-disabled="iswarrTotalEffortsType" id="warrTotalEffortsType" required>
													<div class="error-messages" ng-if="onSave"ng-messages="whatifDeal.warrTotalEffortsType.$error">
														<em class="error help-block has-error"ng-message="required">Please select </em>
													</div>
												</td>
											</tr>
										</tbody>
									</table>
								</td>
							</tr>
						</table>
					</div>
				</div>
				<!-- Excel Download for what if application wise AG5027026 -->
				
				<%-- <div class="divEmptyThrice"></div>
				<div class="row text-center">
					<div class="col-sm-12">
						<div class="table-responsive">
							<button type="button" class="btn btn-primary btnSpace" ng-click="exportToExcel('#tableToExport')"> <!-- ng-click="downloadWhatIfExcel();">-->
								<img src="${contextPath}/resources/Images/downloadexcel.png" alt="Snow"> Export to Excel </button>
						</div>
					</div>
				</div>
				<div class="divEmptyThrice"></div> --%>
				<div class="row">
					<div class="col-sm-12">
						<div class="table-responsive" id="tableToExport" style="display: none">
							<table border="1" width="750"
								class="table clsTable table-striped table-bordered table-hover table-condensed "
								id="tblRateCardDetails">
								<tbody id="tBodyRateCardDetails">
								<!-- <tr><th align="center" colspan="6">What If Analysis</th></tr> -->
								<tr><th align="center" colspan="6">Summary</th></tr>
									<tr ng-repeat="versionDetail in versionDetails">
										<th align="left" bgcolor="#CCFFFF">Deal Id</th>
										<td align="left">{{versionDetail.crmDealId}}</td>
										<th align="left" bgcolor="#CCFFFF">Deal Version (ID)</th>
										<td align="left">{{versionDetail.dealVersion}} ({{versionDetail.rpDealVersionId}})</td>
										<th align="left" bgcolor="#CCFFFF">Customer Name</th>
										<td align="left">{{versionDetail.dealcrmstagesdata2.customer.customerName}}</td>
									</tr>
									<tr ng-repeat="versionDetail in versionDetails">
										<th align="left" bgcolor="#CCFFFF">Deal Status</th>
										<td align="left">{{versionDetail.dealStatus}}</td>
										<th align="left" bgcolor="#CCFFFF">Project Type</th>
										<td align="left">{{versionDetail.projectType}}</td>
										<th align="left" bgcolor="#CCFFFF">Deal Description</th>
										<td align="left">{{versionDetail.dealcrmstagesdata2.dealDescription}}</td>
									</tr>
									<tr ng-repeat="dealDetailsData in dealDetails">
										<th align="left" bgcolor="#CCFFFF">Start Date
											(dd/mm/yyyy)</th>
										<td align="left">{{dealDetailsData.dealStartDate}}</td>
										<th align="left" bgcolor="#CCFFFF">End Date (dd/mm/yyyy)</th>
										<td align="left">{{dealDetailsData.dealEndDate}}</td>
										<th align="left" bgcolor="#CCFFFF">Percentage Close (%)</th>
										<td align="left">{{dealDetailsData.percentageClose/100}}</td>
									</tr>
									<tr ng-repeat="dealData in dealDetails">
										<th align="left" bgcolor="#CCFFFF">Deal TCV</th>
										<td align="left">{{dealData.dealTCV}}</td>
										<th align="left" bgcolor="#CCFFFF">Currency</th>
										<td align="left">{{dealData.currency}}</td>
										<th align="left" bgcolor="#CCFFFF">Penalty (%)</th>
										<td align="left">{{dealData.penaltyPercent}}</td>
									</tr>
								</tbody>
							</table>
							<table></table>
							<div>
								<label><i><font color="red">Note: All rates are in {{currency}} Unless otherwise stated</font></i></label>
							</div>
							<table></table>
							<table>
								<tr>
									<td>
										<table  border="1" width="750" class=" table-striped table-bordered table-hover table-condensed" id="tblParticulars">
											<thead>
												<tr>
													<th align="left" bgcolor="#CCFFFF" width="20%">Particulars</th>
													<th align="left" bgcolor="#CCFFFF" width="18%">Enter Revenue For Final Price</th>
													<th align="left" bgcolor="#CCFFFF" width="18%">Threshold For BUH Approval</th>
													<th align="left" bgcolor="#CCFFFF" width="18%">Threshold For CEO Approval</th>
													<th align="left" bgcolor="#CCFFFF" width="18%">PM% as per Global Rate Card</th>
												</tr>
											</thead>
											<tbody id="tBodyParticulars">
												<tr ng-repeat="data1 in whatIfDetails">
												<td class="firstColLeftAlign">
													<span ng-if="data1.indicatorFlag ==1 || data1.indicatorFlag ==3 || data1.indicatorFlag ==5">{{data1.description}}</span>
													<span ng-if="data1.indicatorFlag ==2 || data1.indicatorFlag ==4 "><b>{{data1.description}}</b></span>
												</td>
												<td class="tdTextAlignRight" >
													<span ng-if="data1.indicatorFlag ==1">{{data1.userInputValue | number:0}}</span> 
													<span ng-if="data1.indicatorFlag ==2"><b>{{data1.userInputValue | number:0}}</b></span> 
													<span ng-if="data1.indicatorFlag ==3">{{data1.userInputValue*100 | number:0}}%</span> 
													<span ng-if="data1.indicatorFlag ==4"><b>{{data1.userInputValue*100 | number:2}}%</b></span>
													<span ng-if="data1.indicatorFlag ==5">{{data1.userInputValue | number:2}}</span>
												</td>
												<td class="tdTextAlignRight">
													<span ng-if="data1.indicatorFlag ==1">{{data1.buhThresholdValue | number:0}}</span> 
													<span ng-if="data1.indicatorFlag ==2"><b>{{data1.buhThresholdValue | number:0}}</b></span> 
													<span ng-if="data1.indicatorFlag ==3">{{data1.buhThresholdValue*100 | number:0}}%</span> 
													<span ng-if="data1.indicatorFlag ==4"><b>{{data1.buhThresholdValue*100 | number:2}}%</b></span>
													<span ng-if="data1.indicatorFlag ==5">{{data1.buhThresholdValue | number:2}}</span> 
												</td>
												<td class="tdTextAlignRight">
													<span ng-if="data1.indicatorFlag ==1">{{data1.ceoApprovalValue | number:0}}</span> 
													<span ng-if="data1.indicatorFlag ==2"><b>{{data1.ceoApprovalValue | number:0}}</b></span> 
													<span ng-if="data1.indicatorFlag ==3">{{data1.ceoApprovalValue*100 | number:0}}%</span>
													<span ng-if="data1.indicatorFlag ==4"><b>{{data1.ceoApprovalValue*100 | number:2}}%</b></span>
													<span ng-if="data1.indicatorFlag ==5">{{data1.ceoApprovalValue | number:2}}</span> 
												</td>
												<td class="tdTextAlignRight">
													<span ng-if="data1.indicatorFlag ==1">{{data1.globalRateCardValue | number:0}}</span> 
													<span ng-if="data1.indicatorFlag ==2"><b>{{data1.globalRateCardValue | number:0}}</b></span> 
													<span ng-if="data1.indicatorFlag ==3">{{data1.globalRateCardValue*100 | number:0}}%</span> 
													<span ng-if="data1.indicatorFlag ==4"><b>{{data1.globalRateCardValue*100 | number:2}}%</b></span>
													<span ng-if="data1.indicatorFlag ==5">{{data1.globalRateCardValue | number:2}}</span>
												</td>
												</tr>
											</tbody>
										</table>
										<table></table>
										<div class="divEmptyThrice"></div>
										<div class="divEmptyThrice"></div>
										<div class="divEmptyThrice"></div>
										<div class="divEmptyThrice"></div>
										<table  border="1" class="table clsTable table-striped table-bordered table-hover table-condensed table-responsive"
											id="tblEffortsInPersonMonths">
											<thead>
												<tr ng-if="noOfYears==1">
													<th align="left" bgcolor="#CCFFFF" width="5%">Efforts in person months</th>
													<th align="left" bgcolor="#CCFFFF" class="thWidth4Per">YR-1</th>
												</tr>
												<tr ng-if="noOfYears==2">
													<th align="left" bgcolor="#CCFFFF" width="5%">Efforts in person months</th>
													<th align="left" bgcolor="#CCFFFF" class="thWidth4Per">YR-1</th>
													<th align="left" bgcolor="#CCFFFF" class="thWidth4Per">YR-2</th>
													<th align="left" bgcolor="#CCFFFF" class="thWidth4Per">Total</th>
												</tr>
												<tr ng-if="noOfYears==3">
													<th align="left" bgcolor="#CCFFFF" width="5%">Efforts in person months</th>
													<th align="left" bgcolor="#CCFFFF" class="thWidth4Per">YR-1</th>
													<th align="left" bgcolor="#CCFFFF" class="thWidth4Per">YR-2</th>
													<th align="left" bgcolor="#CCFFFF" class="thWidth4Per">YR-3</th>
													<th align="left" bgcolor="#CCFFFF" class="thWidth4Per">Total</th>
												</tr>
												<tr ng-if="noOfYears==4">
													<th align="left" bgcolor="#CCFFFF" width="5%">Efforts in person months</th>
													<th align="left" bgcolor="#CCFFFF" class="thWidth4Per">YR-1</th>
													<th align="left" bgcolor="#CCFFFF" class="thWidth4Per">YR-2</th>
													<th align="left" bgcolor="#CCFFFF" class="thWidth4Per">YR-3</th>
													<th align="left" bgcolor="#CCFFFF" class="thWidth4Per">YR-4</th>
													<th align="left" bgcolor="#CCFFFF" class="thWidth4Per">Total</th>
												</tr>
												<tr ng-if="noOfYears==5">
													<th align="left" bgcolor="#CCFFFF" width="5%">Efforts in person months</th>
													<th align="left" bgcolor="#CCFFFF" class="thWidth4Per">YR-1</th>
													<th align="left" bgcolor="#CCFFFF" class="thWidth4Per">YR-2</th>
													<th align="left" bgcolor="#CCFFFF" class="thWidth4Per">YR-3</th>
													<th align="left" bgcolor="#CCFFFF" class="thWidth4Per">YR-4</th>
													<th align="left" bgcolor="#CCFFFF" class="thWidth4Per">YR-5</th>
													<th align="left" bgcolor="#CCFFFF" class="thWidth4Per">Total</th>
												</tr>
												<tr ng-if="noOfYears==6">
													<th align="left" bgcolor="#CCFFFF" width="5%">Efforts in person months</th>
													<th align="left" bgcolor="#CCFFFF" class="thWidth4Per">YR-1</th>
													<th align="left" bgcolor="#CCFFFF" class="thWidth4Per">YR-2</th>
													<th align="left" bgcolor="#CCFFFF" class="thWidth4Per">YR-3</th>
													<th align="left" bgcolor="#CCFFFF" class="thWidth4Per">YR-4</th>
													<th align="left" bgcolor="#CCFFFF" class="thWidth4Pe r">YR-5</th>
													<th align="left" bgcolor="#CCFFFF" class="thWidth4Per">YR-6</th>
													<th align="left" bgcolor="#CCFFFF" class="thWidth4Per">Total</th>
												</tr>
												<tr ng-if="noOfYears==7">
													<th align="left" bgcolor="#CCFFFF" class="firstColLeftAlign" width="5%">Efforts in person months</th>
													<th align="left" bgcolor="#CCFFFF" class="thWidth4Per">YR-1</th>
													<th align="left" bgcolor="#CCFFFF" class="thWidth4Per">YR-2</th>
													<th align="left" bgcolor="#CCFFFF" class="thWidth4Per">YR-3</th>
													<th align="left" bgcolor="#CCFFFF" class="thWidth4Per">YR-4</th>
													<th align="left" bgcolor="#CCFFFF" class="thWidth4Per">YR-5</th>
													<th align="left" bgcolor="#CCFFFF" class="thWidth4Per">YR-6</th>
													<th align="left" bgcolor="#CCFFFF" class="thWidth4Per">YR-7</th>
													<th align="left" bgcolor="#CCFFFF" class="thWidth4Per">Total</th>
												</tr>
												<tr ng-if="noOfYears==8">
													<th align="left" bgcolor="#CCFFFF" width="5%">Efforts in person months</th>
													<th align="left" bgcolor="#CCFFFF" class="thWidth4Per">YR-1</th>
													<th align="left" bgcolor="#CCFFFF" class="thWidth4Per">YR-2</th>
													<th align="left" bgcolor="#CCFFFF" class="thWidth4Per">YR-3</th>
													<th align="left" bgcolor="#CCFFFF" class="thWidth4Per">YR-4</th>
													<th align="left" bgcolor="#CCFFFF" class="thWidth4Per">YR-5</th>
													<th align="left" bgcolor="#CCFFFF" class="thWidth4Per">YR-6</th>
													<th align="left" bgcolor="#CCFFFF" class="thWidth4Per">YR-7</th>
													<th align="left" bgcolor="#CCFFFF" class="thWidth4Per">YR-8</th>
													<th align="left" bgcolor="#CCFFFF" class="thWidth4Per">Total</th>
												</tr>
												<tr ng-if="noOfYears==9">
													<th align="left" bgcolor="#CCFFFF" width="5%">Efforts in person months</th>
													<th align="left" bgcolor="#CCFFFF" class="thWidth4Per">YR-1</th>
													<th align="left" bgcolor="#CCFFFF" class="thWidth4Per">YR-2</th>
													<th align="left" bgcolor="#CCFFFF" class="thWidth4Per">YR-3</th>
													<th align="left" bgcolor="#CCFFFF" class="thWidth4Per">YR-4</th>
													<th align="left" bgcolor="#CCFFFF" class="thWidth4Per">YR-5</th>
													<th align="left" bgcolor="#CCFFFF" class="thWidth4Per">YR-6</th>
													<th align="left" bgcolor="#CCFFFF" class="thWidth4Per">YR-7</th>
													<th align="left" bgcolor="#CCFFFF" class="thWidth4Per">YR-8</th>
													<th align="left" bgcolor="#CCFFFF" class="thWidth4Per">YR-9</th>
													<th align="left" bgcolor="#CCFFFF" class="thWidth4Per">Total</th>
												</tr>
												<tr ng-if="noOfYears==10">
													<th align="left" bgcolor="#CCFFFF" width="5%">Efforts In Person Months</th>
													<th align="left" bgcolor="#CCFFFF" class="thWidth4Per">YR-1</th>
													<th align="left" bgcolor="#CCFFFF" class="thWidth4Per">YR-2</th>
													<th align="left" bgcolor="#CCFFFF" class="thWidth4Per">YR-3</th>
													<th align="left" bgcolor="#CCFFFF" class="thWidth4Per">YR-4</th>
													<th align="left" bgcolor="#CCFFFF" class="thWidth4Per">YR-5</th>
													<th align="left" bgcolor="#CCFFFF" class="thWidth4Per">YR-6</th>
													<th align="left" bgcolor="#CCFFFF" class="thWidth4Per">YR-7</th>
													<th align="left" bgcolor="#CCFFFF" class="thWidth4Per">YR-8</th>
													<th align="left" bgcolor="#CCFFFF" class="thWidth4Per">YR-9</th>
													<th align="left" bgcolor="#CCFFFF" class="thWidth4Per">YR-10</th>
													<th align="left" bgcolor="#CCFFFF" class="thWidth4Per">Total</th>
												</tr>

											</thead>
											<tbody id="tBodyEffortsInPersonMonths">
												<tr ng-repeat="effortdata in whatIfEffortDetails">
													<td align="left"><b>{{effortdata.locationDescription}}</b></td>
	
													<td class="tdTextAlignRight" ng-if="noOfYears>=1">
														<span ng-if="effortdata.percentIndicator!=1">{{effortdata.year1Efforts}}</span>
														<span ng-if="effortdata.percentIndicator==1">{{effortdata.year1Efforts  * 100 | number :0}}%</span>
													</td>
													<td class="tdTextAlignRight" ng-if="noOfYears>=2">
														<span ng-if="effortdata.percentIndicator!=1">{{effortdata.year2Efforts}}</span>
														<span ng-if="effortdata.percentIndicator==1">{{effortdata.year2Efforts * 100 | number :0}}%</span>
													</td>
													<td class="tdTextAlignRight" ng-if="noOfYears>=3">
														<span ng-if="effortdata.percentIndicator!=1">{{effortdata.year3Efforts}}</span>
														<span ng-if="effortdata.percentIndicator==1">{{effortdata.year3Efforts * 100 | number :0}}%</span>
													</td>
													<td class="tdTextAlignRight" ng-if="noOfYears>=4">
														<span ng-if="effortdata.percentIndicator!=1">{{effortdata.year4Efforts}}</span>
														<span ng-if="effortdata.percentIndicator==1">{{effortdata.year4Efforts * 100 | number :0}}%</span>
													</td>
													<td class="tdTextAlignRight" ng-if="noOfYears>=5">
														<span ng-if="effortdata.percentIndicator!=1">{{effortdata.year5Efforts}}</span>
														<span ng-if="effortdata.percentIndicator==1">{{effortdata.year5Efforts * 100 | number :0}}%</span>
													</td>
													<td class="tdTextAlignRight" ng-if="noOfYears>=6">
														<span ng-if="effortdata.percentIndicator!=1">{{effortdata.year6Efforts}}</span>
														<span ng-if="effortdata.percentIndicator==1">{{effortdata.year6Efforts * 100 | number :0}}%</span>
													</td>
													<td class="tdTextAlignRight" ng-if="noOfYears>=7">
														<span ng-if="effortdata.percentIndicator!=1">{{effortdata.year7Efforts}}</span>
														<span ng-if="effortdata.percentIndicator==1">{{effortdata.year7Efforts * 100 | number :0}}%</span>
													</td>
													<td class="tdTextAlignRight" ng-if="noOfYears>=8">
														<span ng-if="effortdata.percentIndicator!=1">{{effortdata.year8Efforts}}</span>
														<span ng-if="effortdata.percentIndicator==1">{{effortdata.year8Efforts * 100 | number :0}}%</span>
													</td>
													<td class="tdTextAlignRight" ng-if="noOfYears>=9">
														<span ng-if="effortdata.percentIndicator!=1">{{effortdata.year9Efforts}}</span>
														<span ng-if="effortdata.percentIndicator==1">{{effortdata.year9Efforts * 100 | number :0}}%</span>
													</td>
													<td class="tdTextAlignRight" ng-if="noOfYears>=10">
														<span ng-if="effortdata.percentIndicator!=1">{{effortdata.year10Efforts}}</span>
														<span ng-if="effortdata.percentIndicator==1">{{effortdata.year10Efforts * 100 | number :0}}%</span>
													</td>
													<td class="tdTextAlignRight" ng-if="noOfYears>1">
														<span ng-if="effortdata.percentIndicator!=1">{{effortdata.effortsTotal}}</span>
														<span ng-if="effortdata.percentIndicator==1">{{effortdata.effortsTotal * 100 | number :0}}%</span>
													</td>
												</tr>
											</tbody>
										</table>
										<table></table>
										<table
											class="tdWidth5per table-bordered bordered table-striped table-condensed datatable "
											id="tblRateCardSummary" ui-jq="dataTable"
											ui-options="dataTableOpt" border="1";>
											<thead>
												<tr>
													<th align="left" bgcolor="#CCFFFF">FX Rates</th>
													<th align="left" bgcolor="#CCFFFF">Exchange Rates</th>
												</tr>
											</thead>
											<tbody>
												<tr ng-repeat="rateData in exchangedetails|unique: rateData.currencyId">
													<td class="colRightSection"><span>{{rateData.currencyCode}} to INR </span></td>
													<td class="tdTextAlignRight"><span>{{rateData.exchangeRate}}</span></td>
												</tr>

											</tbody>
										</table>
										</td>
										<td>
										<td>
										<div class="divEmptyThrice"></div>
										<div class="divEmptyThrice"></div>
										<div class="divEmptyThrice"></div>
										<div class="divEmptyThrice"></div>
										<table border="1" class="table clsTable table-striped table-bordered table-hover table-condensed marginLft" id="tblParticulars">
											<thead>
												<tr>
													<th align="left" bgcolor="#CCFFFF" width=70%>REVENUE DIFFERENTIALS - IMPACT</th>
													<th align="left" bgcolor="#CCFFFF" width=30%>AMOUNT</th>
												</tr>
											</thead>
											<tbody id="tBodyParticulars" ng-repeat="whatrevenue in whatIfCalDetails">
											<tr ng-repeat="row1 in whatIfDetails">
												<!-- <td ng-if="row1.description == 'REVENUE'"><b>Revenue as calculated per what if analysis</b></td> -->
												<td ng-if="row1.description == 'REVENUE'"><b>Revenue as calculated per Summary</b></td>
												<td ng-if="row1.description == 'REVENUE'">{{row1.userInputValue | number:2}}</td>
											</tr>
											<tr>
												<td class="firstColLeftAlign"><b>Revenue of roles selected from Master</b></td>
												<td>{{whatrevenue.masterRevenue}}</td>
											</tr>
											<tr>
												<td class="firstColLeftAlign"><b>Revenue of roles selected from rate card</b></td>
												<td>{{whatrevenue.rateCardRevenue}}</td>
											</tr>
											<tr>
												<td class="firstColLeftAlign"><b>Revenue of contractor roles</b></td>
												<td>{{whatrevenue.contractorRevenue}}</td>
											</tr>
											<tr>
												<td class="firstColLeftAlign"><b>Total revenue as per MSA</b></td>
												<td>{{whatrevenue.msaRevenue}}</td>
											</tr>
											<!-- <tr>
														<td class="firstColLeftAlign"><b>Discount (-) / Premium (+) over GRC Revenue</b></td>
															<td></td>
														</tr> -->
											<tr ng-repeat="row1 in whatIfDetails">
												<td ng-if="row1.description == 'REVENUE'"><b>Discount (-) / Premium (+)</b></td>
												<td ng-if="row1.description == 'REVENUE'">{{ row1.dispre | number:2}}</td>
											</tr>
											<tr ng-repeat="row1 in whatIfDetails">
												<td ng-if="row1.description == 'REVENUE'"><b>Discount / Premium % </b></td>
												<td ng-if="row1.description == 'REVENUE'">{{ row1.diff | number:2}}%</td>
											</tr>
										</tbody>
										</table>
										<table></table>
										<div class="divEmptyThrice"></div>
										<div class="divEmptyThrice"></div>
										<table  border="1" class="table clsTable table-striped table-bordered table-hover table-condensed marginLft" id="tblParticulars">
											<thead>
												<tr>
													<th align="left" bgcolor="#CCFFFF" width=70%>CONTRACTUAL TERMS</th>
													<th align="left" bgcolor="#CCFFFF" width=30%></th>
												</tr>
											</thead>
											<tbody id="tBodyParticulars">
												<tr>
													<td class="firstColLeftAlign"><b>A) Transition:</b></td>
													<td></td>
												</tr>
												<tr ng-repeat="row in whatIfContractDetails">
													<td class="firstColLeftAlign"><b>1) Is transition Chargeable or is it Free of Cost?</b></td>
													<td align="right" ng-if="row.isTransitionChargeable==1">Chargeable</td>
													<td align="right" ng-if="row.isTransitionChargeable==2">Free of Cost</td>
													<td align="right" ng-if="row.isTransitionChargeable==3">N/A</td>
												</tr>
												<tr ng-repeat="row in whatIfContractDetails">
													<td class="firstColLeftAlign"><b>2) If Chargeable, what % of it is so?</b></td>
													<td align="right">{{row.chargeableTransitionPercent}}</td>
												</tr>
												<tr ng-repeat="row in whatIfContractDetails">
													<td class="firstColLeftAlign"><b>3) Have the efforts been considered for costing?</b></td>
													<td align="right" ng-if="row.isEffortsConsideredForCosting==1">Yes</td>
													<td align="right" ng-if="row.isEffortsConsideredForCosting==2">No</td>
													<td align="right" ng-if="row.isEffortsConsideredForCosting==3">N/A</td>
												</tr>
												<tr ng-repeat="row in whatIfContractDetails">
													<td class="firstColLeftAlign"><b>4) Total Efforts considered (in person months):</b></td>
													<td align="right">{{row.totalTransitionEfforts}}</td>
												</tr>
												<tr>
													<td class="firstColLeftAlign"><b>B) Warranty:</b></td>
													<td></td>
												</tr>
												<tr ng-repeat="row in whatIfContractDetails">
													<td class="firstColLeftAlign"><b>1) Is Warranty to be provided?</b></td>
													<td align="right" ng-if="row.isWarrantyProvided==1">Yes</td>
													<td align="right" ng-if="row.isWarrantyProvided==2">No</td>
													<td align="right" ng-if="row.isWarrantyProvided==3">N/A</td>
												</tr>
												<tr ng-repeat="row in whatIfContractDetails">
													<td class="firstColLeftAlign"><b>2) If so, is it Chargeable or is Free of Cost?</b></td>
													<td align="right" ng-if="row.isWarrantyChargeable==1">Chargeable</td>
													<td align="right" ng-if="row.isWarrantyChargeable==2">Free of Cost</td>
													<td align="right" ng-if="row.isWarrantyChargeable==3">N/A</td>
												</tr>
												<tr ng-repeat="row in whatIfContractDetails">
													<td class="firstColLeftAlign"><b>3) If Chargeable, what % of it is so?</b></td>
													<td align="right">{{row.chargeableWarrantyPercent}}</td>
												</tr>
												<tr ng-repeat="row in whatIfContractDetails">
													<td class="firstColLeftAlign"><b>4) Have the efforts been considered for costing?</b></td>
													<td align="right" ng-if="row.isWarrentyEffortsConsideredForCosting==1">Yes</td>
													<td align="right" ng-if="row.isWarrentyEffortsConsideredForCosting==2">No</td>
													<td align="right" ng-if="row.isWarrentyEffortsConsideredForCosting==3">N/A</td>
												</tr>
												<tr ng-repeat="row in whatIfContractDetails">
													<td class="firstColLeftAlign"><b>5) Total Efforts considered (in person months):</b></td>
													<td align="right">{{row.totalWarrantyEfforts}}</td>
												</tr>
											</tbody>
										</table>
									</td>
								</tr>
							</table>
						</div>
					</div>
				</div>
			<table class="tdWidth5per table-bordered bordered table-striped table-condensed datatable " id="tblRateCardSummary" ui-jq="dataTable" ui-options="dataTableOpt" border="1";>
						<thead>
							<tr>
								<th  align="left" bgcolor="#CCFFFF">FX Rates</th>
								<th  align="left" bgcolor="#CCFFFF">Exchange Rates</th>
							</tr>
						</thead>
						<tbody>
							<tr ng-repeat="rateData in exchangedetails|unique: ratedata.currencyId"">
								<td class="colRightSection"><span>{{rateData.currencyCode}} to INR </span></td>
								<td class="tdTextAlignRight"><span>{{rateData.exchangeRate}}</span></td>
							</tr>
										
						</tbody>
					</table>
					<div class="divEmptyThrice"></div>
					<div class="table-responsive" ng-show="isApprovercomment">
					<label>Send versions for Approval</label>
												<table 
													class="table clsTable table-striped table-bordered table-hover table-condensed "
													id="tblRoleSelection1">
													<thead>
														<tr>
															<!-- <th>Source</th> -->
															<th>Select</th> 
															<th class="firstColLeftAlign">Deal Versions</th>
															<th>Onsite %</th>
															<th class="firstColLeftAlign">Offshore %</th>
															<th>No of Towers</th>
															<th>Revenue</th>
															<th>Direct Cost</th>
															<th>Project Margin</th>
															<th>Project specific Cost</th>
															<th>Operating Margin</th>
															<th>PM% before discount</th>
															<th>PM% after discount</th>
															<th>PM% after discount and incl. Risk</th>
															<th>Final Approval</th>
														</tr>
													</thead>
													<tbody id="tBodyRoleSelection1">
													 <tr ng-repeat="dealver in dealverResult">
													     <td>
													     <input type="checkbox" ng-model="dealver.selectedObj" ng-value="dealver" ng-change="addVersionData(dealver)">
													       
													      </td>
													      <td class="firstColLeftAlign tooltip1" ng-model="selectedObj.rpDealVersionId" ng-show="rpdv">{{dealver.rpDealVersionId}}</td>
														  <td class="firstColLeftAlign tooltip1">{{dealver.dealVersion}}</td>
														   <td>{{dealver.expectedOnsitePercentage}}% </td>
														  <td>{{dealver.expectedOffshorePercentage}}%</td>
														  <td>{{dealver.noOfTowers}}</td>
														  <td>{{dealver.estimatedRevenue | number:0}}</td>
														  <td>{{dealver.directCost | number:0 }}</td>
														  <td>{{dealver.projectMarginPercentage*100 | number:2 }}%</td>
														  <td>{{dealver.projectSpecificCost | number:0 }}</td>
														  <td>{{dealver.projectMargin | number:0}}</td>
														  <td>{{dealver.gmAfterProjectSpecificCost*100 | number:2}} %</td>
														  <td>{{dealver.gmAfterVolumeDiscount*100 | number:2 }} %</td>
														 <td>{{dealver.gmPercentage*100 | number:2 }}%</td>
														  <td>{{dealver.finalApproval}}</td>
														 </tr>
													</tbody>
													
												</table>
						</div>
					
					<div class="divEmptyThrice"></div>
					<div class="row">
						<div class="col-sm-12" ng-show="isOldApprovercomment && !isApprovercomment">
							<div class="panel-group">
								<div class="panel panel-info ">
									<div class="panel-heading panelHeadingStyle">
										<div class="row ">
											<label class="control-label col-sm-10 ">Approver
												Comments</label>
										</div>
									</div>
									<div class="panel-body">
										<div class="row marginBottom5px">
											<label
												class="control-label col-sm-2 textAlignRight required-Field">Comments</label>
											<div class="col-sm-10">
												<textarea style="resize: none" name="txtApproverComment"
													id="txtApproverComment" class="form-control" rows="3"
													ng-model="whatifDeal.approverCommentModel" required
													disabled></textarea>
											</div>
										</div>
									
								</div>
								</div>
							</div>
						</div>
						      <div class="row"  ng-hide="rainbowApprovalPanel">
                    <div class="col-sm-12">
                        <div class="panel-group">
                            <div class="panel panel-info ">
                                <div class="panel-heading panelHeadingStyle">
                                    <div class="row ">
										<label class="control-label col-sm-10 ">Rainbow Approval</label>
										
									</div>
                                </div>
                                <div class="panel-body" >
								   	<div class="row marginBottom5px" ng-model = "divRainbowApproval">
                                    	<label class="control-label col-sm-2 textAlignRight required-Field">Is Rainbow Approval Required</label>
                                        <div class="col-sm-2">
                                          <select id="ddlRainbowApproval" class="form-control" ng-model="whatifDeal.rainbowApprovalRequired"  placeholder="Please select" name="ddlRainbowApproval" ng-change = "checkApprovalLevel(whatifDeal.rainbowApprovalRequired)" ng-class="{true: 'ng-border'}[submitted && ddlRainbowApproval.$invalid]" required>
										<option value="" selected disabled>Please select</option>
										<option value="1">Yes</option>
										<option value="2">No</option>
									</select>
                                        </div>
                                     </div>	
                                     <div class="row marginBottom5px" ng-show = "isApprovalType">
                                    	<label class="control-label col-sm-2 textAlignRight required-Field">Approval Level</label>
                                        <div class="col-sm-2">
                                          <select id="ddlRainbowApprovalLevel" class="form-control" ng-model="whatifDeal.rainbowApprovalLevel"  placeholder="Please select" name="ddlRainbowApprovalLevel" ng-class="{true: 'ng-border'}[submitted && ddlRainbowApproval.$invalid]" 
                                           ng-options="rpc.id as rpc.name for rpc in rainbowApprovalLevel" required>
										<option value="" selected disabled>Please select</option>
										<!-- <option value="5">Level 1 Approval</option>
										<option value="6">Level 2 Approval</option> -->
									</select>
                                        </div>
                                     </div>	
                                </div>
                                
                        </div>
                    </div>
                    
				</div>
                </div>
						
						<div class="row" ng-show="isApprovercomment">
							<div class="col-sm-12">
								<div class="panel-group">
									<div class="panel panel-info ">
										<div class="panel-heading panelHeadingStyle">
											<div class="row">
												<label class="control-label col-sm-10 ">Approver
													Action</label>

											</div>
										</div>
										<div class="panel-body">
											<div class="row marginBottom5px">
												<label
													class="control-label col-sm-2 textAlignRight required-Field">Approver
													Action</label>
												<div class="col-sm-2">
													<select id="ddlApproverStatus" class="form-control"
														ng-model="whatifDeal.approvalStatus"
														placeholder="Please select" name="ddlApproverStatus"
														ng-change="getApprovalComments('<%=session.getAttribute("username")%>',whatifDeal.approvalStatus)"
<%-- 														ng-change="getApprovalComments('<%=org.springframework.security.core.context.SecurityContextHolder.getContext().getAuthentication().getName()%>',whatifDeal.approvalStatus)" --%>
														ng-class="{true: 'ng-border'}[submitted && whatifDeal.ddlApproverStatus.$invalid]"
														required>
														<option value="" selected disabled>Please select</option>
														<option value="3">Approve</option>
														<option value="4">Reject</option>
													</select>
												</div>
											</div>
											<div class="row marginBottom5px">
												<label
													class="control-label col-sm-2 textAlignRight required-Field">Comments</label>
												<div class="col-sm-10">
													<textarea style="resize: none" name="txtApproverComment"
														id="txtApproverComment" class="form-control" rows="3"
														ng-model="whatifDeal.approverCommentModel" required
														disabled></textarea>
												</div>
											</div>
											<div class="row marginBottom5px" ng-show="commentbox == true">
												<label
													class="control-label col-sm-2 textAlignRight required-Field">Enter
													your Comments</label>
												<div class="col-sm-10">
													<textarea style="resize: none"
														name="txtCurrentApproverComment"
														id="txtCurrentApproverComment" class="form-control"
														rows="3" ng-model="whatifDeal.currentApproverCommentModel"
														required></textarea>
												</div>
											</div>
										
										
										</div>
                   

									
										<div class="divEmptyThrice"></div>
										<div class="row text-center">
											<div class="col-sm-12">
												<button type="button" class="btn btn-primary btnSpace"
													id="btnSubmit"
													ng-click="submitApproverData('<%=session.getAttribute("username")%>');">Submit
<%-- 													ng-click="submitApproverData('<%=org.springframework.security.core.context.SecurityContextHolder.getContext().getAuthentication().getName()%>');">Submit --%>
													Approval</button>
											</div>
										</div>
										
										<div class="divEmptyThrice"></div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</ng-form>
				<div class="panel-body" ng-hide="AttachmentHidden">
										<div class="panel-body" ng-hide="AttachmentHide">
											<div class="col-sm-2 "></div>
											<label
												class="control-label col-sm-2 textAlignRight required-Field">
												Attachment </label>
											<div class="col-sm-3 ">
												<input type="file" class="form-control"
													name="fuAttachFilename" id="fuAttachFilename"
													ng-model="whatifDeal.fuAttachFilenameModel"
													check-file-size="whatifDeal.fuAttachFilenameModel"
													valid-file-rate-card
													ng-class="{true: 'ng-border'}[(upload) && whatifDeal.fuAttachFilename.$invalid]">
											</div>
											<div class="col-sm-1">
												<button type="button" class="btn btn-primary btnSpace"
													id="btnUpload"
													ng-click="uploadAttachmentPLData(whatifDeal);"
													ng-disabled="uploadBtnDisable">Upload</button>
											</div>
										</div>
										<fieldset ng-disabled="IsDisabled"
											ng-hide="AttachementData.length==0">
											<table
												class="	table clsTable table-striped table-bordered table-hover table-condensed "
												border="0" id="PolicyReportTbl">
												<thead>
													<tr>
														<th width="60%">File Name</th>
														<th width="25%">Date Of Upload [dd/mm/yyyy]</th>
														<th width="15%">Action</th>
													</tr>
												</thead>
												<tbody id="tBody">
													<tr id="Manualattachements"
														ng-repeat="row in AttachementData ">
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

				
				<div class="divEmptyThrice"></div>
				
				<div class="row text-center">
					<div class="col-sm-12">
					<div class="row text-center">
					
							<button type="button" class="btn btn-primary btnSpace" ng-click="exportToExcel('#tableToExport')"> <!-- ng-click="downloadWhatIfExcel();">-->
								<img src="${contextPath}/resources/Images/downloadexcel.png" alt="Snow"> Export to Excel </button>
						
						<button type="button" class="btn btn-primary btnSpace"
							id="btnsave" ng-disabled="isSaveDisabled"
							ng-click="save(whatifDeal);">Save</button>
						<button type="button" class="btn btn-primary btnSpace"
							id="btnsave" ng-disabled="isReadyToSubmit"
							ng-click="getApprovalMatrixWhatIf(whatifDeal);">Ready To Submit</button>
						<button type="button" class="btn btn-info btnSpace"
							id="btnClientPrev" ng-click="Prev()">Prev</button>
						<button type="button" class="btn btn-info" id="btnClientNext"
							ng-click="Next()">Next</button>
					</div>
				</div>
			</div>
		</div>
	</fieldset>
	<div id="footer" ng-include="'${contextPath}/Portal/Footer.jsp'"></div>
	<div id="Footer"
		ng-include="'${contextPath}/Portal/TopBottomNavigation.jsp'"></div>
	<div id="loader" ng-include="'${contextPath}/Portal/loader.jsp'"></div>
</body>
</html>