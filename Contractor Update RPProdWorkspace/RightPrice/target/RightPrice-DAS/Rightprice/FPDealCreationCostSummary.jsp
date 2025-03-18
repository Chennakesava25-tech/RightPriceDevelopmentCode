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
<script src="${contextPath}/resources/js/jquery.min.js"></script>
<script src="${contextPath}/resources/js/bootstrap.js"></script>
<script src="${contextPath}/resources/js/jquery.serializeJSON.min.js"></script>
<script src="${contextPath}/resources/js/jquery-ui.js"></script>
<link href="${contextPath}/resources/css/jquery-ui.css" rel="stylesheet" />
<link href="${contextPath}/resources/css/bootstrap-dialog.css"
	rel="stylesheet" />
<script src="${contextPath}/resources/js/bootstrap-dialog.js"></script>
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
<%-- <script
	src="${contextPath}/resources/js/Services/fpDealCostInputsService.js"></script> --%>
<script src="${contextPath}/resources/js/loader.js"></script>
<script src="${contextPath}/resources/js/Factory/WebServiceFactory.js"></script>
<script
	src="${contextPath}/resources/js/RightPrice/CostSummaryController.js"></script>
	<script
	src="${contextPath}/resources/js/Directives/angularCustomValidations.js"></script>
</head>
<body ng-app="RightPriceApp" ng-controller="CostSummaryController">
	<div id="includedContent"
		ng-include="'${contextPath}/Portal/Header.jsp'"></div>
	<fieldset ng-disabled="loading || showLoader">
		<div class="container">
			<div class="divEmpty"></div>
			<div class="row marginBottom5px">
				<div class="col-sm-12">
					<h3 class="text-left" id="PageHeading">FP Deal Creation - Cost
						Summary</h3>
				</div>
			</div>
			<div>
				<form class="form-inline" role="form" name="costBreakUp"
					id="costBreakUp" novalidate>
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
											<label class="control-label col-sm-10 "> Cost Summary</label>
											<!-- <div class="col-sm-2 textAlignRight ">
											<a href="#" class="DownArrowColor" ng-click="ShowHideCostSummary()"> &#9660;</a>
										</div> -->
										</div>
									</div>
									<div class="divEmptyThrice"></div>
										<div class="row marginBottom5px"><label class="control-label col-sm-10 redColor ">Note: All the values are in {{currency}}</label></div>
									<div class="row marginBottom5px">
										<div class="col-sm-12">
											<div class="table-responsive" id="tableToExport1">
												<table
													class="table tblDealCre table-borderless table-condensed"
													id="tblCostBreakup">
													<thead>
														<tr>
															<th class="width12per thBorder">Direct Cost Details
															</th>
															<th class="thColor tdTextAlignRight" colspan="1" ng-hide="hideTransition"><span
																ng-if="hideTransition!=true">Transition</span></th>
															<th class="thColor tdTextAlignRight" colspan="1"><span
																ng-if="yearsData[1]!=null">Year 1</span></th>
															<th class="thColor tdTextAlignRight" colspan="1"><span
																ng-if="yearsData[2]!=null">Year 2</span></th>
															<th class="thColor tdTextAlignRight" colspan="1"><span
																ng-if="yearsData[3]!=null">Year 3</span></th>
															<th class="thColor tdTextAlignRight" colspan="1"><span
																ng-if="yearsData[4]!=null">Year 4</span></th>
															<th class="thColor tdTextAlignRight" colspan="1"><span
																ng-if="yearsData[5]!=null">Year 5</span></th>
															<th class="thColor tdTextAlignRight" colspan="1"><span
																ng-if="yearsData[6]!=null">Year 6</span></th>
															<th class="thColor tdTextAlignRight" colspan="1"><span
																ng-if="yearsData[7]!=null">Year 7</span></th>
															<th class="thColor tdTextAlignRight" colspan="1"><span
																ng-if="yearsData[8]!=null">Year 8</span></th>
															<th class="thColor tdTextAlignRight" colspan="1"><span
																ng-if="yearsData[9]!=null">Year 9</span></th>
															<th class="thColor tdTextAlignRight" colspan="1"><span
																ng-if="yearsData[10]!=null">Year 10</span></th>
															<th class="thColor tdTextAlignRight" colspan="1" ng-hide="totalHide">Total</th>
														</tr>

													</thead>
													<tbody>
														<tr class="sectionHeading">
															<td colspan="17"><strong>Cost Breakup</strong></td>
														</tr>
													</tbody>
													<tbody  id="tBodyProjectCost">
														<tr align="RIGHT" ng-repeat="rowDet in costDataBuild | orderBy:'rowSequenceNumber'" 
														data-ng-class="{'boldCondition':{{rowDet.totalIndicator}} == 1}" ng-if="rowDet!=null" >
															<td class="colSection tdTextAlignLeft">{{rowDet.costType}}</td>
															<td class="colRightSection width5per" ng-hide="hideTransition">
															<div ng-if="rowDet.costCode!=101 && rowDet.costCode!=102" ng-model="rowDet.transistion" decimals="2" >{{rowDet.transistion}}</div>
															<div ng-if="rowDet.costCode==101">
															<div>  
																	 <input 
																		name="txtTransition" ng-model="rowDet.transistion"
																		type="text" class="form-control tdTextAlignRight"
																		id="txtTransition" required decimals="2" 
																		ng-disabled="rowDet.whatifRevenueType"
																		ng-class="{true: 'ng-border'}[saved && costBreakUp.txtTransition.$invalid]"
																		ng-pattern="numberRegex" 
																		ng-blur="calculateRowTotal(rowDet,$index);">
																		<!-- <div class="error-messages" ng-if="saved"
																			ng-messages="costBreakUp.txtTransition.$error">
																			<em class="error help-block has-error"
																				ng-message="required">Please Enter Year Transition
																				</em> <em class="error help-block has-error"
																				ng-message="pattern">Please enter numbers with
																				maximum of two decimal.</em>
																		</div> -->
																</div>
															</div>
															<div ng-if="rowDet.costCode==102">
															<div>  
																	 <input 
																		name="txtTransition" ng-model="rowDet.transistion"
																		type="text" class="form-control tdTextAlignRight"
																		id="txtTransition" required
																		ng-disabled="rowDet.whatifRevenueType" decimals="2" 
																		ng-class="{true: 'ng-border'}[saved && costBreakUp.txtTransition.$invalid]"
																		ng-pattern="numberRegex" ng-blur="calculateRowTotal(rowDet,$index);">
																		<!-- <div class="error-messages" ng-if="saved"
																			ng-messages="costBreakUp.txtTransition.$error">
																			<em class="error help-block has-error"
																				ng-message="required">Please Enter Transition
																				</em> <em class="error help-block has-error"
																				ng-message="pattern">Please enter numbers with
																				maximum of two decimal.</em>
																		</div> -->
																</div>
															</span>
															</td>
															<td class="colRightSection width5per">
															<div ng-if="rowDet.costCode!=101 && rowDet.costCode!=102 " decimals="2"  ng-model="rowDet.year_1">{{rowDet.year_1}}</div>
															<div ng-if="rowDet.costCode==101">
															<div ng-if="yearsData[1]!=null">
																	 <input 
																		name="txtyear1" ng-model="rowDet.year_1"
																		type="text" class="form-control tdTextAlignRight" id="txtyear1"
																		required ng-disabled=" rowDet.whatifRevenueType"
																		ng-blur="calculateRowTotal(rowDet,$index);" decimals="2" 
																		ng-class="{true: 'ng-border'}[saved && costBreakUp.txtyear1.$invalid]"
																		ng-pattern="numberRegex">

																		<!-- <div class="error-messages" ng-if="saved"
																			ng-messages="costBreakUp.txtyear1.$error">
																			<em class="error help-block has-error"
																				ng-message="required">Please Enter Year 1
																				</em> <em class="error help-block has-error"
																				ng-message="pattern">Please enter numbers with
																				maximum of two decimal.</em>
																		</div> -->
																	</div>
															</div>
															<div ng-if="rowDet.costCode==102">
															<div ng-if="yearsData[1]!=null">
																	 <input 
																		name="txtyear1" ng-model="rowDet.year_1"
																		decimals="2" 
																		type="text" class="form-control tdTextAlignRight" id="txtyear1"
																		required ng-disabled=" rowDet.whatifRevenueType"
																		ng-blur="calculateRowTotal(rowDet,$index);"  decimals="2" 
																		ng-class="{true: 'ng-border'}[saved && costBreakUp.txtyear1.$invalid]"
																		ng-pattern="numberRegex">

																	<!-- 	<div class="error-messages" ng-if="saved"
																			ng-messages="costBreakUp.txtyear1.$error">
																			<em class="error help-block has-error"
																				ng-message="required">Please Enter Year 1
																				</em> <em class="error help-block has-error"
																				ng-message="pattern">Please enter numbers with
																				maximum of two decimal.</em>
																		</div> -->
																	</div>
															</div>
															</td>
															<td class="colRightSection width5per">
															<div ng-if="rowDet.costCode!=101 && rowDet.costCode!=102" decimals="2" >{{rowDet.year_2}}</div>
															<div ng-if="rowDet.costCode==101">
															<div  ng-if="yearsData[2]!=null">
																	 <input ng-disabled="rowDet.whatifRevenueType""
																		name="txtyear2" ng-model="rowDet.year_2"
																		ng-blur="calculateRowTotal(rowDet,$index);" decimals="2" 
																		ng-disabled="rowDet.whatifRevenueType"
																		type="text" class="form-control tdTextAlignRight" id="txtyear2" required
																		ng-class="{true: 'ng-border'}[saved && costBreakUp.txtyear2.$invalid]"
																		ng-pattern="numberRegex">

																		<div class="error-messages" ng-if="saved"
																			ng-messages="costBreakUp.txtyear2.$error">
																			<em class="error help-block has-error"
																				ng-message="required">Please Enter Year 2
																				</em> <em class="error help-block has-error"
																				ng-message="pattern">Please enter numbers with
																				maximum of two decimal.</em>
																		</div>
																</div>
															</div>
															<div ng-if="rowDet.costCode==102">
															<div  ng-if="yearsData[2]!=null">
																	 <input ng-disabled="rowDet.whatifRevenueType""
																		name="txtyear2" ng-model="rowDet.year_2"
																		ng-blur="calculateRowTotal(rowDet,$index);"
																		ng-disabled="rowDet.whatifRevenueType" decimals="2" 
																		type="text" class="form-control tdTextAlignRight" id="txtyear2" required
																		ng-class="{true: 'ng-border'}[saved && costBreakUp.txtyear2.$invalid]"
																		ng-pattern="numberRegex">

																		<div class="error-messages" ng-if="saved"
																			ng-messages="costBreakUp.txtyear2.$error">
																			<em class="error help-block has-error"
																				ng-message="required">Please Enter Year 2
																				</em> <em class="error help-block has-error"
																				ng-message="pattern">Please enter numbers with
																				maximum of two decimal.</em>
																		</div>
																</div>
															</div></td>
															<td class="colRightSection width5per">
															<div ng-if="rowDet.costCode!=101 && rowDet.costCode!=102" decimals="2" >{{rowDet.year_3}}</div>
															<div ng-if="rowDet.costCode==101">
															<div ng-if="yearsData[3]!=null">
																	<input
																		name="txtyear3" ng-model="rowDet.year_3" decimals="2" 
																		type="text" class="form-control tdTextAlignRight" id="txtyear3" required 
																		ng-disabled="rowDet.whatifRevenueType" ng-blur="calculateRowTotal(rowDet,$index);"
																		ng-class="{true: 'ng-border'}[saved && costBreakUp.txtyear3.$invalid]"
																		ng-pattern="numberRegex">

																		<div class="error-messages" ng-if="saved"
																			ng-messages="costBreakUp.txtyear3.$error">
																			<em class="error help-block has-error"
																				ng-message="required">Please Enter Year 3
																				</em> <em class="error help-block has-error"
																				ng-message="pattern">Please enter numbers with
																				maximum of two decimal.</em>
																		</div>
																</div>
																</div>
																<div ng-if="rowDet.costCode==102">
																<div ng-if="yearsData[3]!=null">
																	<input
																		name="txtyear3" ng-model="rowDet.year_3" decimals="2" 
																		type="text" class="form-control tdTextAlignRight" id="txtyear3" required 
																		ng-disabled="rowDet.whatifRevenueType" ng-blur="calculateRowTotal(rowDet,$index);"
																		ng-class="{true: 'ng-border'}[saved && costBreakUp.txtyear3.$invalid]"
																		ng-pattern="numberRegex">

																		<div class="error-messages" ng-if="saved"
																			ng-messages="costBreakUp.txtyear3.$error">
																			<em class="error help-block has-error"
																				ng-message="required">Please Enter Year 3
																				</em> <em class="error help-block has-error"
																				ng-message="pattern">Please enter numbers with
																				maximum of two decimal.</em>
																		</div>
																</div>
																</div></td>
															<td class="colRightSection width5per">
															<div ng-if="rowDet.costCode!=101 && rowDet.costCode!=102" decimals="2" >{{rowDet.year_4}}</div>
															<div ng-if="rowDet.costCode==101">
															<div ng-if="yearsData[4]!=null">
																	 <input
																		name="txtyear4" ng-model="rowDet.year_4" decimals="2" 
																		type="text" class="form-control tdTextAlignRight" id="txtyear4" required
																		ng-disabled="rowDet.whatifRevenueType" ng-blur="calculateRowTotal(rowDet,$index);"
																		ng-class="{true: 'ng-border'}[saved && costBreakUp.txtyear4.$invalid]"
																		ng-pattern="numberRegex">

																		<div class="error-messages" ng-if="saved"
																			ng-messages="costBreakUp.txtyear4.$error">
																			<em class="error help-block has-error"
																				ng-message="required">Please Enter Year 4
																				</em> <em class="error help-block has-error"
																				ng-message="pattern">Please enter numbers with
																				maximum of two decimal.</em>
																		</div>
																</div>
																</div>
																<div ng-if="rowDet.costCode==102">
															<div ng-if="yearsData[4]!=null">
																	 <input
																		name="txtyear4" ng-model="rowDet.year_4" decimals="2" 
																		type="text" class="form-control tdTextAlignRight" id="txtyear4" required
																		ng-disabled="rowDet.whatifRevenueType" ng-blur="calculateRowTotal(rowDet,$index);"
																		ng-class="{true: 'ng-border'}[saved && costBreakUp.txtyear4.$invalid]"
																		ng-pattern="numberRegex">

																		<div class="error-messages" ng-if="saved"
																			ng-messages="costBreakUp.txtyear4.$error">
																			<em class="error help-block has-error"
																				ng-message="required">Please Enter Year 4
																				</em> <em class="error help-block has-error"
																				ng-message="pattern">Please enter numbers with
																				maximum of two decimal.</em>
																		</div>
																</div>
																</div></td>
															<td class="colRightSection width5per">
															<div ng-if="rowDet.costCode!=101 && rowDet.costCode!=102 " decimals="2" >{{rowDet.year_5}}</div>
															<div ng-if="rowDet.costCode==101">
															<div ng-if="yearsData[5]!=null">
																	 <input
																		name="txtyear5" ng-model="rowDet.year_5" decimals="2" 
																		type="text" class="form-control tdTextAlignRight" 
																		ng-disabled="rowDet.whatifRevenueType"
																		id="txtyear5" required ng-blur="calculateRowTotal(rowDet,$index);"
																		ng-class="{true: 'ng-border'}[saved && costBreakUp.txtyear5.$invalid]"
																		ng-pattern="numberRegex" ng-disabled="yearsData[6]==null" 
																		>

																		<div class="error-messages" ng-if="saved"
																			ng-messages="costBreakUp.txtyear5.$error">
																			<em class="error help-block has-error"
																				ng-message="required">Please Enter Year 5
																				</em> <em class="error help-block has-error"
																				ng-message="pattern">Please enter numbers with
																				maximum of two decimal.</em>
																		</div>
																</div>
																</div>
																<div ng-if="rowDet.costCode==102">
															<div ng-if="yearsData[5]!=null">
																	 <input
																		name="txtyear5" ng-model="rowDet.year_5"
																		type="text" class="form-control tdTextAlignRight" 
																		ng-disabled="rowDet.whatifRevenueType" decimals="2" 
																		id="txtyear5" required ng-blur="calculateRowTotal(rowDet,$index);"
																		ng-class="{true: 'ng-border'}[saved && costBreakUp.txtyear5.$invalid]"
																		ng-pattern="numberRegex" ng-disabled="yearsData[6]==null" 
																		>

																		<div class="error-messages" ng-if="saved"
																			ng-messages="costBreakUp.txtyear5.$error">
																			<em class="error help-block has-error"
																				ng-message="required">Please Enter Year 5
																				</em> <em class="error help-block has-error"
																				ng-message="pattern">Please enter numbers with
																				maximum of two decimal.</em>
																		</div>
																</div>
																</div></td>
															<td class="colRightSection width5per">
															<div ng-if="rowDet.costCode!=101 && rowDet.costCode!=102" decimals="2" >{{rowDet.year_6}}</div>
															<div ng-if="rowDet.costCode==101">
															<div ng-if="yearsData[6]!=null">
																	 <input
																		name="txtyear6" ng-model="rowDet.year_6" decimals="2" 
																		type="text" class="form-control tdTextAlignRight"
																		id="txtBillingSchedule" required
																		ng-disabled="rowDet.whatifRevenueType"
																		ng-blur="calculateRowTotal(rowDet,$index);"
																		ng-class="{true: 'ng-border'}[saved && costBreakUp.txtyear6.$invalid]"
																		ng-pattern="numberRegex" ng-disabled="yearsData[7]==null"
																		>
																		<div class="error-messages" ng-if="saved"
																			ng-messages="costBreakUp.txtyear6.$error">
																			<em class="error help-block has-error"
																				ng-message="required">Please Enter Year 6
																				</em> <em class="error help-block has-error"
																				ng-message="pattern">Please enter numbers with
																				maximum of two decimal.</em>
																		</div>
																</div>
																</div>
																<div ng-if="rowDet.costCode==102">
															<div ng-if="yearsData[6]!=null">
																	 <input
																		name="txtyear6" ng-model="rowDet.year_6" decimals="2" 
																		type="text" class="form-control tdTextAlignRight"
																		id="txtBillingSchedule" required
																		ng-disabled="rowDet.whatifRevenueType"
																		ng-blur="calculateRowTotal(rowDet,$index);"
																		ng-class="{true: 'ng-border'}[saved && costBreakUp.txtyear6.$invalid]"
																		ng-pattern="numberRegex" ng-disabled="yearsData[7]==null"
																		>
																		<div class="error-messages" ng-if="saved"
																			ng-messages="costBreakUp.txtyear6.$error">
																			<em class="error help-block has-error"
																				ng-message="required">Please Enter Year 6
																				</em> <em class="error help-block has-error"
																				ng-message="pattern">Please enter numbers with
																				maximum of two decimal.</em>
																		</div>
																</div>
																</div></td>
															<td class="colRightSection width5per">
															<div ng-if="rowDet.costCode!=101 && rowDet.costCode!=102" decimals="2" >{{rowDet.year_7}}</div>
															<div ng-if="rowDet.costCode==101">
															<div ng-if="yearsData[7]!=null">
																	<input
																		name="txtyear7" ng-model="rowDet.year_7" decimals="2" 
																		type="text" class="form-control tdTextAlignRight" id="txtyear7" required
																		ng-disabled="rowDet.whatifRevenueType"
																		ng-blur="calculateRowTotal(rowDet,$index);"
																		ng-class="{true: 'ng-border'}[saved && costBreakUp.txtyear7.$invalid]"
																		ng-pattern="numberRegex" ng-disabled="yearsData[8]==null"
																		>
 
																		<div class="error-messages" ng-if="saved"
																			ng-messages="costBreakUp.txtyear7.$error">
																			<em class="error help-block has-error"
																				ng-message="required">Please Enter Year 7																				
																				</em> <em class="error help-block has-error"
																				ng-message="pattern">Please enter numbers with
																				maximum of two decimal.</em>
																		</div>
																</div>
																</div>
																<div ng-if=" rowDet.costCode==102">
															<div ng-if="yearsData[7]!=null">
																	<input
																		name="txtyear7" ng-model="rowDet.year_7" decimals="2" 
																		type="text" class="form-control tdTextAlignRight" id="txtyear7" required
																		ng-disabled="rowDet.whatifRevenueType"
																		ng-blur="calculateRowTotal(rowDet,$index);"
																		ng-class="{true: 'ng-border'}[saved && costBreakUp.txtyear7.$invalid]"
																		ng-pattern="numberRegex" ng-disabled="yearsData[8]==null"
																		>
 
																		<div class="error-messages" ng-if="saved"
																			ng-messages="costBreakUp.txtyear7.$error">
																			<em class="error help-block has-error"
																				ng-message="required">Please Enter Year 7																				
																				</em> <em class="error help-block has-error"
																				ng-message="pattern">Please enter numbers with
																				maximum of two decimal.</em>
																		</div>
																</div>
																</div></td>
															<td class="colRightSection width5per">
															<div ng-if="rowDet.costCode!=101 && rowDet.costCode!=102" decimals="2" >{{rowDet.year_8}}</div>
															<div ng-if="rowDet.costCode==101">
															<div ng-if="yearsData[8]!=null">
																	 <input
																		name="txtyear8" ng-model="rowDet.year_8"
																		ng-disabled="rowDet.whatifRevenueType" decimals="2" 
																		type="text" class="form-control tdTextAlignRight" id="txtyear8" required
																		ng-blur="calculateRowTotal(rowDet,$index);"
																		ng-class="{true: 'ng-border'}[saved && costBreakUp.txtyear8.$invalid]"
																		ng-pattern="numberRegex" ng-disabled="yearsData[9]==null"
																		>
																		<div class="error-messages" ng-if="saved"
																			ng-messages="costBreakUp.txtyear8.$error">
																			<em class="error help-block has-error"
																				ng-message="required">Please Enter Year 8
																				</em> <em class="error help-block has-error"
																				ng-message="pattern">Please enter numbers with
																				maximum of two decimal.</em>
																		</div>
																</div>
																</div>
																<div ng-if="rowDet.costCode==102">
															<div ng-if="yearsData[8]!=null">
																	 <input
																		name="txtyear8" ng-model="rowDet.year_8" decimals="2" 
																		ng-disabled="rowDet.whatifRevenueType"
																		type="text" class="form-control tdTextAlignRight" id="txtyear8" required
																		ng-blur="calculateRowTotal(rowDet,$index);"
																		ng-class="{true: 'ng-border'}[saved && costBreakUp.txtyear8.$invalid]"
																		ng-pattern="numberRegex" ng-disabled="yearsData[9]==null"
																		>
																		<div class="error-messages" ng-if="saved"
																			ng-messages="costBreakUp.txtyear8.$error">
																			<em class="error help-block has-error"
																				ng-message="required">Please Enter Year 8
																				</em> <em class="error help-block has-error"
																				ng-message="pattern">Please enter numbers with
																				maximum of two decimal.</em>
																		</div>
																</div>
																</div></td>
															<td class="colRightSection width5per">
															<div ng-if="rowDet.costCode!=101 && rowDet.costCode!=102" decimals="2" >{{rowDet.year_9}}</div>
															<div ng-if="rowDet.costCode==101">
															<div ng-if="yearsData[9]!=null">
																	 <input
																		name="txtyear9" ng-model="rowDet.year_9" decimals="2" 
																		ng-disabled="rowDet.whatifRevenueType"
																		type="text" class="form-control tdTextAlignRight" id="txtyear9" required
																		ng-blur="calculateRowTotal(rowDet,$index);"
																		ng-class="{true: 'ng-border'}[saved && costBreakUp.txtyear9.$invalid]"
																		ng-pattern="numberRegex" ng-disabled="yearsData[10]==null">

																		<div class="error-messages" ng-if="saved"
																			ng-messages="costBreakUp.txtyear9.$error">
																			<em class="error help-block has-error"
																				ng-message="required">Please Enter Year 9
																				</em> <em class="error help-block has-error"
																				ng-message="pattern">Please enter numbers with
																				maximum of two decimal.</em>
																		</div>
																</div>
																</div>
																<div ng-if="rowDet.costCode==102">
															<div ng-if="yearsData[9]!=null">
																	 <input
																		name="txtyear9" ng-model="rowDet.year_9" decimals="2" 
																		ng-disabled="rowDet.whatifRevenueType"
																		type="text" class="form-control tdTextAlignRight" id="txtyear9" required
																		ng-blur="calculateRowTotal(rowDet,$index);"
																		ng-class="{true: 'ng-border'}[saved && costBreakUp.txtyear9.$invalid]"
																		ng-pattern="numberRegex" ng-disabled="yearsData[10]==null">

																		<div class="error-messages" ng-if="saved"
																			ng-messages="costBreakUp.txtyear9.$error">
																			<em class="error help-block has-error"
																				ng-message="required">Please Enter Year 9
																				</em> <em class="error help-block has-error"
																				ng-message="pattern">Please enter numbers with
																				maximum of two decimal.</em>
																		</div>
																</div>
																</div></td>
															<td class="colRightSection width5per">
															<div ng-if="rowDet.costCode!=101 && rowDet.costCode!=102" decimals="2" >{{rowDet.year_10}}</div>
															<div ng-if="rowDet.costCode==101">
															<div ng-if="yearsData[10]!=null">
																	 <input
																		name="txtyear10" ng-model="rowDet.year_10" decimals="2" 
																		ng-disabled="rowDet.whatifRevenueType" ng-blur="calculateRowTotal(rowDet,$index);"
																		type="text" class="form-control tdTextAlignRight" id="txtyear10" required disabled
																		ng-class="{true: 'ng-border'}[saved && costBreakUp.txtyear10.$invalid]"
																		ng-pattern="numberRegex">

																		<div class="error-messages" ng-if="saved"
																			ng-messages="costBreakUp.txtyear10.$error">
																			<em class="error help-block has-error"
																				ng-message="required">Please Enter Year 10
																				</em> <em class="error help-block has-error"
																				ng-message="pattern">Please enter numbers with
																				maximum of two decimal.</em>
																		</div>
																</div>
																</div>
																<div ng-if="rowDet.costCode==102">
															<div ng-if="yearsData[10]!=null">
																	 <input
																		name="txtyear10"  ng-model="rowDet.year_10" decimals="2" 
																		ng-disabled="rowDet.whatifRevenueType" ng-blur="calculateRowTotal(rowDet,$index);"
																		type="text" class="form-control tdTextAlignRight" id="txtyear10" required disabled
																		ng-class="{true: 'ng-border'}[saved && costBreakUp.txtyear10.$invalid]"
																		ng-pattern="numberRegex">

																		<div class="error-messages" ng-if="saved"
																			ng-messages="costBreakUp.txtyear10.$error">
																			<em class="error help-block has-error"
																				ng-message="required">Please Enter Year 10
																				</em> <em class="error help-block has-error"
																				ng-message="pattern">Please enter numbers with
																				maximum of two decimal.</em>
																		</div>
																</div>
																</div></td>
															<td class="colRightSection width5per">
																<div ng-if="rowDet.costCode!=104">{{rowDet.yearTotal| number :2}}</div>
																<div class="redColor" ng-if="rowDet.costCode==104">{{rowDet.yearTotal| number :2}}</div>
															</td>
															<!-- <div ng-if="rowDet.costCode==101">
																	<input name="txtyearTotal" disabled
																		ng-model="rowDet.yearTotal"
																		 type="text"
																		ng-blur="calculateRowTotal(rowDet,$index);"
																		class="form-control tdTextAlignRight" id="txtyearTotal" 
																		ng-class="{true: 'ng-border'}[saved && costBreakUp.txtyearTotal.$invalid]"
																		ng-pattern="numberRegex">

																	<div class="error-messages" ng-if="saved"
																		ng-messages="costBreakUp.txtyearTotal.$error">
																		<em class="error help-block has-error"
																			ng-message="required">Please Enter Total
																			</em> <em class="error help-block has-error"
																			ng-message="pattern">Please enter numbers with
																			maximum of two decimal.</em>
																	</div>
																</div> -->
																
																
															<!-- <div  ng-if="rowDet.costCode==102">
																	<input name="txtyearTotal" disabled
																		ng-model="rowDet.yearTotal"
																		 type="text"
																		ng-blur="calculateRowTotal(rowDet,$index);"
																		class="form-control tdTextAlignRight" id="txtyearTotal" 
																		ng-class="{true: 'ng-border'}[saved && costBreakUp.txtyearTotal.$invalid]"
																		ng-pattern="numberRegex">

																	<div class="error-messages" ng-if="saved"
																		ng-messages="costBreakUp.txtyearTotal.$error">
																		<em class="error help-block has-error"
																			ng-message="required">Please Enter Total
																			</em> <em class="error help-block has-error"
																			ng-message="pattern">Please enter numbers with
																			maximum of two decimal.</em>
																	</div>
																</div> -->
																</td>
														</tr>
													</tbody>
													<!-- <tbody id="tBodyProjectCost">
														<tr align="RIGHT"
															ng-repeat="row in costData | orderBy:'rowSequenceNumber'"
															data-ng-class="{'boldCondition':{{row.totalIndicator}} == 1}" ng-if="row!=null" >
															<td class="colSection tdTextAlignLeft">{{row.costType}}</td>
															<td class="colRightSection width5per "
																ng-hide="hideTransition"><span
																ng-if="hideTransition!=true">{{row.transistion}}</span></td>
															<td class="colRightSection width5per "><span
																ng-if="yearsData[1]!=null">{{row.year_1}}</span></td>
															<td class="colRightSection width5per"><span
																ng-if="yearsData[2]!=null">{{row.year_2}}</span></td>
															<td class="colRightSection width5per"><span
																ng-if="yearsData[3]!=null">{{row.year_3}}</span></td>
															<td class="colRightSection width5per"><span
																ng-if="yearsData[4]!=null">{{row.year_4}}</span></td>
															<td class="colRightSection width5per"><span
																ng-if="yearsData[5]!=null">{{row.year_5}}</span></td>
															<td class="colRightSection width5per"><span
																ng-if="yearsData[6]!=null">{{row.year_6}}</span></td>
															<td class="colRightSection width5per"><span
																ng-if="yearsData[7]!=null">{{row.year_7}}</span></td>
															<td class="colRightSection width5per"><span
																ng-if="yearsData[8]!=null">{{row.year_8}}</span></td>
															<td class="colRightSection width5per"><span
																ng-if="yearsData[9]!=null">{{row.year_9}}</span></td>
															<td class="colRightSection width5per"><span
																ng-if="yearsData[10]!=null">{{row.year_10}}</span></td>
															<td class="colRightSection width5per" ng-hide="totalHide">{{row.yearTotal}}</td>
														</tr>
														<tr>
															<td class="colSection ">
																<div>
																 <Label >Billing Schedule: </Label> <input
																		name="txtBillingSchedule" 
																		ng-model="costBreakUp.BillingScheduleModel"
																		type="text" class="costTextField"
																		id="txtBillingSchedule" 
																		ng-class="{true: 'ng-border'}[saved && costBreakUp.txtBilling.$invalid]"
																		ng-pattern="numberRegex" ng-hide=true>
																	<div class="error-messages" ng-if="saved"
																		ng-messages="costBreakUp.txtBilling.$error">
																		<em class="error help-block has-error"
																			ng-message="required">Please Enter 
																			</em> <em class="error help-block has-error"
																			ng-message="pattern">Please enter numbers with
																			maximum of two decimal.</em>
																	</div>
																</div>
															</td>
															<td class="colRightSection width5per"
																ng-hide="hideTransition"><div
																ng-if="hideTransition!=true">  
																	 <input 
																		name="txtTransition" ng-model="costBreakUp.TransitionModel"
																		type="text" class="form-control tdTextAlignRight"
																		id="txtTransition" required
																		ng-class="{true: 'ng-border'}[saved && costBreakUp.txtTransition.$invalid]"
																		ng-pattern="numberRegex" ng-change="calculas(costBreakUp,costBreakUp.yearTotalModel);">
																		<div class="error-messages" ng-if="saved"
																			ng-messages="costBreakUp.txtTransition.$error">
																			<em class="error help-block has-error"
																				ng-message="required">Please Enter Year 6
																				</em> <em class="error help-block has-error"
																				ng-message="pattern">Please enter numbers with
																				maximum of two decimal.</em>
																		</div>
																</div></td>

															<td class="colRightSection width5per">
															
																<div ng-if="yearsData[1]!=null">
																	 <input 
																		name="txtyear1" ng-model="costBreakUp.year1Model"
																		type="text" class="form-control tdTextAlignRight" id="txtyear1"
																		required ng-disabled="yearsData[2]==null"
																		ng-change="calculas(costBreakUp,costBreakUp.yearTotalModel);"
																		ng-class="{true: 'ng-border'}[saved && costBreakUp.txtyear1.$invalid]"
																		ng-pattern="numberRegex">

																		<div class="error-messages" ng-if="saved"
																			ng-messages="costBreakUp.txtyear1.$error">
																			<em class="error help-block has-error"
																				ng-message="required">Please Enter Year 1
																				</em> <em class="error help-block has-error"
																				ng-message="pattern">Please enter numbers with
																				maximum of two decimal.</em>
																		</div>
																	</div>
															
															</td>

															<td class="colRightSection width5per">
																<div  ng-if="yearsData[2]!=null">
																	 <input ng-disabled="yearsData[3]==null"
																		name="txtyear2" ng-model="costBreakUp.year2Model"
																		ng-change="calculas(costBreakUp,costBreakUp.yearTotalModel);"
																		type="text" class="form-control tdTextAlignRight" id="txtyear2" required
																		ng-class="{true: 'ng-border'}[saved && costBreakUp.txtyear2.$invalid]"
																		ng-pattern="numberRegex">

																		<div class="error-messages" ng-if="saved"
																			ng-messages="costBreakUp.txtyear2.$error">
																			<em class="error help-block has-error"
																				ng-message="required">Please Enter Year 2
																				</em> <em class="error help-block has-error"
																				ng-message="pattern">Please enter numbers with
																				maximum of two decimal.</em>
																		</div>
																</div>
															</td>

															<td class="colRightSection width5per">
															<div ng-if="yearsData[3]!=null">
																	<input
																		name="txtyear3" ng-model="costBreakUp.year3Model"
																		type="text" class="form-control tdTextAlignRight" id="txtyear3" required 
																		ng-disabled="yearsData[4]==null" ng-change="calculas(costBreakUp,costBreakUp.yearTotalModel);"
																		ng-class="{true: 'ng-border'}[saved && costBreakUp.txtyear3.$invalid]"
																		ng-pattern="numberRegex">

																		<div class="error-messages" ng-if="saved"
																			ng-messages="costBreakUp.txtyear3.$error">
																			<em class="error help-block has-error"
																				ng-message="required">Please Enter Year 3
																				</em> <em class="error help-block has-error"
																				ng-message="pattern">Please enter numbers with
																				maximum of two decimal.</em>
																		</div>
																</div></td>

															<td class="colRightSection width5per">
																<div ng-if="yearsData[4]!=null">
																	 <input
																		name="txtyear4" ng-model="costBreakUp.year4Model"
																		type="text" class="form-control tdTextAlignRight" id="txtyear4" required
																		ng-disabled="yearsData[5]==null" ng-change="calculas(costBreakUp,costBreakUp.yearTotalModel);"
																		ng-class="{true: 'ng-border'}[saved && costBreakUp.txtyear4.$invalid]"
																		ng-pattern="numberRegex">

																		<div class="error-messages" ng-if="saved"
																			ng-messages="costBreakUp.txtyear4.$error">
																			<em class="error help-block has-error"
																				ng-message="required">Please Enter Year 4
																				</em> <em class="error help-block has-error"
																				ng-message="pattern">Please enter numbers with
																				maximum of two decimal.</em>
																		</div>
																</div>
															</td>

															<td class="colRightSection width5per">
																<div ng-if="yearsData[5]!=null">
																	 <input
																		name="txtyear5" ng-model="costBreakUp.year5Model"
																		type="text" class="form-control tdTextAlignRight" id="txtyear5" required ng-change="calculas(costBreakUp,costBreakUp.yearTotalModel);"
																		ng-class="{true: 'ng-border'}[saved && costBreakUp.txtyear5.$invalid]"
																		ng-pattern="numberRegex" ng-disabled="yearsData[6]==null" 
																		>

																		<div class="error-messages" ng-if="saved"
																			ng-messages="costBreakUp.txtyear5.$error">
																			<em class="error help-block has-error"
																				ng-message="required">Please Enter Year 5
																				</em> <em class="error help-block has-error"
																				ng-message="pattern">Please enter numbers with
																				maximum of two decimal.</em>
																		</div>
																</div>
															</td>

															<td class="colRightSection width5per">
																<div ng-if="yearsData[6]!=null">
																	 <input
																		name="txtyear6" ng-model="costBreakUp.year6Model"
																		type="text" class="form-control tdTextAlignRight"
																		id="txtBillingSchedule" required
																		ng-change="calculas(costBreakUp,costBreakUp.yearTotalModel);"
																		ng-class="{true: 'ng-border'}[saved && costBreakUp.txtyear6.$invalid]"
																		ng-pattern="numberRegex" ng-disabled="yearsData[7]==null"
																		>
																		<div class="error-messages" ng-if="saved"
																			ng-messages="costBreakUp.txtyear6.$error">
																			<em class="error help-block has-error"
																				ng-message="required">Please Enter Year 6
																				</em> <em class="error help-block has-error"
																				ng-message="pattern">Please enter numbers with
																				maximum of two decimal.</em>
																		</div>
																</div>
															</td>

															<td class="colRightSection width5per">
																<div ng-if="yearsData[7]!=null">
																	<input
																		name="txtyear7" ng-model="costBreakUp.year7Model"
																		type="text" class="form-control tdTextAlignRight" id="txtyear7" required
																		ng-change="calculas(costBreakUp,costBreakUp.yearTotalModel);"
																		ng-class="{true: 'ng-border'}[saved && costBreakUp.txtyear7.$invalid]"
																		ng-pattern="numberRegex" ng-disabled="yearsData[8]==null"
																		>
 
																		<div class="error-messages" ng-if="saved"
																			ng-messages="costBreakUp.txtyear7.$error">
																			<em class="error help-block has-error"
																				ng-message="required">Please Enter Year 7																				
																				</em> <em class="error help-block has-error"
																				ng-message="pattern">Please enter numbers with
																				maximum of two decimal.</em>
																		</div>
																</div>
															</td>

															<td class="colRightSection width5per">
																<div ng-if="yearsData[8]!=null">
																	 <input
																		name="txtyear8" ng-model="costBreakUp.year8Model"
																		type="text" class="form-control tdTextAlignRight" id="txtyear8" required
																		ng-change="calculas(costBreakUp,costBreakUp.yearTotalModel);"
																		ng-class="{true: 'ng-border'}[saved && costBreakUp.txtyear8.$invalid]"
																		ng-pattern="numberRegex" ng-disabled="yearsData[9]==null"
																		>
																		<div class="error-messages" ng-if="saved"
																			ng-messages="costBreakUp.txtyear8.$error">
																			<em class="error help-block has-error"
																				ng-message="required">Please Enter Year 8
																				</em> <em class="error help-block has-error"
																				ng-message="pattern">Please enter numbers with
																				maximum of two decimal.</em>
																		</div>
																</div>
															</td>

															<td class="colRightSection width5per">
																<div ng-if="yearsData[9]!=null">
																	 <input
																		name="txtyear9" ng-model="costBreakUp.year9Model"
																		type="text" class="form-control tdTextAlignRight" id="txtyear9" required
																		ng-change="calculas(costBreakUp,costBreakUp.yearTotalModel); "
																		ng-class="{true: 'ng-border'}[saved && costBreakUp.txtyear9.$invalid]"
																		ng-pattern="numberRegex" ng-disabled="yearsData[10]==null">

																		<div class="error-messages" ng-if="saved"
																			ng-messages="costBreakUp.txtyear9.$error">
																			<em class="error help-block has-error"
																				ng-message="required">Please Enter Year 9
																				</em> <em class="error help-block has-error"
																				ng-message="pattern">Please enter numbers with
																				maximum of two decimal.</em>
																		</div>
																</div>
															</td>

															<td class="colRightSection width5per">
																<div ng-if="yearsData[10]!=null">
																	 <input
																		name="txtyear10" ng-model="costBreakUp.year10Model"
																		type="text" class="form-control tdTextAlignRight" id="txtyear10" required disabled
																		ng-class="{true: 'ng-border'}[saved && costBreakUp.txtyear10.$invalid]"
																		ng-pattern="numberRegex">

																		<div class="error-messages" ng-if="saved"
																			ng-messages="costBreakUp.txtyear10.$error">
																			<em class="error help-block has-error"
																				ng-message="required">Please Enter Year 10
																				</em> <em class="error help-block has-error"
																				ng-message="pattern">Please enter numbers with
																				maximum of two decimal.</em>
																		</div>
																</div>
															</td>

															<td class="colRightSection width5per" ng-hide="totalHide">
																<div>
																	<input name="txtyearTotal" disabled
																		ng-model="costBreakUp.yearTotalModel" type="text"
																		class="form-control tdTextAlignRight" id="txtyearTotal" 
																		ng-class="{true: 'ng-border'}[saved && costBreakUp.txtyearTotal.$invalid]"
																		ng-pattern="numberRegex">

																	<div class="error-messages" ng-if="saved"
																		ng-messages="costBreakUp.txtyearTotal.$error">
																		<em class="error help-block has-error"
																			ng-message="required">Please Enter Total
																			</em> <em class="error help-block has-error"
																			ng-message="pattern">Please enter numbers with
																			maximum of two decimal.</em>
																	</div>
																</div>
															</td>
														</tr>
													</tbody>
 -->												</table>
											</div>
										</div>
									</div>
									<div class="divEmptyThrice"></div>
										<table class="tdWidth5per table-bordered bordered table-striped table-condensed datatable " id="tblRateCardSummary" ui-jq="dataTable" ui-options="dataTableOpt" border="1";>
								      	<thead>
								      	<tr>
								        <th  align="left" bgcolor="#CCFFFF">FX Rates</th>
								        <th  align="left" bgcolor="#CCFFFF">Exchange Rates</th>
								      	</tr>
								      	</thead>
								      	<tbody>
								      	<tr ng-repeat="rateData in exchangedetails| unique : rateData.curencyId">
								      	<td class="colRightSection"><span>{{rateData.currencyCode}} to INR </span></td>
								      	<td class="tdTextAlignRight"><span>{{rateData.exchangeRate}}</span></td>
										</tr>
										
								      	</tbody>
								      </table>
									<div class="row text-center">
										<div class="col-sm-12">
											<button class="btn btn-primary btnSpace" type="button"
												ng-click="download(costBreakUp)" ng-disabled="isDownloadEnabled"> 
												<img src="${contextPath}/resources/Images/downloadexcel.png" alt="Snow"> Export
												to Excel</button>
											<button type="button" class="btn btn-primary btnSpace" ng-disabled= "isSaveDisabled"
											id="btnDetailsSave"ng-click="save(costBreakUp);">Save</button>
											<button type="button" class="btn btn-info btnSpace"
												id="btnClientPrev" ng-click="Prev()">Prev</button>
											<button type="button" class="btn btn-info" id="btnClientNext"
												ng-click="Next()">Next</button>
										</div>
									</div>
									<div class="divEmptyThrice"></div>
								</div>
							</div>
						</div>
					</div>
			</form>
			</div>
		</div>
	</fieldset>
	<div id="footer" ng-include="'${contextPath}/Portal/Footer.jsp'"></div>
	<div id="Footer"
		ng-include="'${contextPath}/Portal/TopBottomNavigation.jsp'"></div>
	<div id="loader" ng-include="'${contextPath}/Portal/loader.jsp'"></div>
</body>
</html>
