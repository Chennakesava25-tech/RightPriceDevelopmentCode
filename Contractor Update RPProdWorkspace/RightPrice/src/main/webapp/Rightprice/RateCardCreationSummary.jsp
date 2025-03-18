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
    <script src="${contextPath}/resources/js/jquery.min.js"></script>
    <script src="${contextPath}/resources/js/bootstrap.js"></script>
    <script src="${contextPath}/resources/js/jquery.serializeJSON.min.js"></script>
    <script src="${contextPath}/resources/js/jquery-ui.js"></script>
    <link href="${contextPath}/resources/css/jquery-ui.css" rel="stylesheet" />
    <link href="${contextPath}/resources/css/bootstrap-dialog.css" rel="stylesheet" />
    <script src="${contextPath}/resources/js/bootstrap-dialog.js"></script>
    <link href="${contextPath}/resources/css/ie10-viewport-bug-workaround.css" rel="stylesheet" />
	<link href="${contextPath}/resources/css/sticky-footer-navbar.css" rel="stylesheet" />
	<script src="${contextPath}/resources/js/ie10-viewport-bug-workaround.js"></script>
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
	<script src="${contextPath}/resources/js/RightPrice/rateCardCreationSummary.js"></script>
	<script src="${contextPath}/resources/js/Factory/WebServiceFactory.js"></script>

</head>
<body ng-app="RightPriceApp" ng-controller="RateCardSummaryController" ng-init="getVerticalDeta();getSummaryDetails('<%=session.getAttribute("user")%>');">
<%-- <body ng-app="RightPriceApp" ng-controller="RateCardSummaryController" ng-init="getSummaryDetails('<%=org.springframework.security.core.context.SecurityContextHolder.getContext().getAuthentication().getName()%>');"> --%>
     <div id="includedContent" ng-include="'${contextPath}/Portal/Header.jsp'"></div>
    <fieldset ng-disabled="loading || showLoader">
    <div class="container">
        <div class="divEmpty"></div>
        <div class="row marginBottom5px">
            <div class="col-sm-12">
                <h3 class="text-left" id="PageHeading">Rate Card Creation - Summary</h3>
            </div> 
        </div>	
        <div >
            <form class="form-inline" role="form" name="frmRateCardCreation" id="frmRateCardCreation">
            	<div id="includedRateCardStages" ng-if="autosidebar"
				ng-include="'${contextPath}/Portal/RateCardCompletionStage.jsp'"></div>
				<div id="includedRateCardStages" ng-if="manualsidebar"
				ng-include="'${contextPath}/Portal/RateCardManualCompletionStage.jsp'"></div>
				<div id="includedRateCardStages" ng-if="hybridsidebar"
				ng-include="'${contextPath}/Portal/RateCardHybridCompletionStage.jsp'"></div>
				<div id="includedRateCardStages" ng-include="'${contextPath}/Rightprice/RateCardCreationInformationTable.jsp'"></div>
            	<div class="row" ng-show="tableDisplay == true && tableIT == true">
                    <div class="col-sm-12">
                        <div class="panel-group">
                            <div class="panel panel-info ">
                                <div class="panel-heading panelHeadingStyle">
                                    <div class="row ">
										<label class="control-label col-sm-10 ">Rate Card Summary</label>
										<!-- <div class="col-sm-2 textAlignRight">
											<a href="#" class="DownArrowColor" ng-click="ShowHideDetails()"> &#9660;</a>
										</div> -->
									</div>
                                </div>
                               <!--  <div class="panel-body" >
							        <div class="row">
										<label class="control-label col-sm-10 "><h5>
												<b>Rate Card Details</b>
											</h5></label>
									</div>
									<div class="divEmptyThrice"></div>
									<div class="row marginBottom5px">
									<label class="control-label col-sm-2 textAlignRight required-Field">Currency</label>
					                 	 <div class="col-sm-3">
					             			<select id="ddlCurrency" class="form-control" placeholder="Please select" name="ddlCurrency" ng-options="c.name for c in currency" 
					                         		ng-model="frmRateCardCreation.ddlCurrencyModel" required>ng-change="checkDropdown()"
													<option value="" selected >Please select</option>
													
											</select>
					                	</div>	
					                 	<label class="control-label col-sm-1 textAlignRight">Country</label>
					                 	<div class="col-sm-3">
					             			<select id="ddlCountry" class="form-control" placeholder="Please select" name="ddlCountry" ng-options="con.countryId as con.countryName for con in country" 
					                         		ng-model="frmRateCardCreation.ddlCountryModel" ng-change="checkDropdown()" >ng-disabled="isDisabled" ng-change="checkDropdown()"
													<option value="" selected >Summary</option>
													<option value="1">US</option>
													<option value="2">UK</option>
											</select>
					                	</div>	
					                	<div class="row text-center">
					                	<button type="button" class="btn btn-primary btnSpace" id="btnSearch">Search</button>
											<div class="col-sm-12">
												
											</div>
										</div>
					                	<div class="divEmptyThrice"></div>
										
					                	<div class="col-sm-3">
					                	<button type="button" class="btn btn-primary btnSpace " id="btnSearch" ng-click="getCountryWiseData(frmRateCardCreation.ddlCountryModel)">Search</button>
					                	</div>
					                 	<label class="control-label col-sm-3 textAlignRight required-Field">City</label>
					                  	<div class="col-sm-3">
					                    	<select id="ddlCity" class="form-control" placeholder="Please select" name="ddlCity"
					                          		ng-model="ddlCityModel" required>
													<option value="" selected disabled>Please select</option>
													<option value="1">New York</option>
													<option value="2">Boston</option>
											</select>
					                	</div>	
									</div> -->
									<!-- <div class="divEmptyThrice"></div>
									<div class="row text-center">
										<div class="col-sm-12">
											<button type="button" class="btn btn-primary btnSpace" id="btnSearch">Search</button>
										</div>
									</div> -->
									<div class="divEmptyThrice"></div>
							        <div class="row">
										<label class="control-label col-sm-10 "><h5>
												<b>Over All Summary</b>
											</h5></label>
									</div>
									<div>
									<label><i><font color="red">All rates are in {{currencyName}}</font></i></label>
									</div>
									<div class="row marginBottom5px">
	                                    <div class="col-sm-12">
											<div class="table-responsive" >
<table 
													class="table clsTable table-striped table-bordered table-hover table-condensed "
													id="tblOverAllSummary" border="1">
													<tbody id="tBodyOverAllSummary" >
														<tr>
															<th class="thWidth15Per"></th>
															<td ng-repeat="row in summaryDetail" class="tdbgColor" ng-if="row.recordType== 1  && !row.year1BR==0" Strong><strong>Year-1</strong></td>
															<td ng-repeat="row in summaryDetail" class="tdbgColor" ng-if="row.recordType== 1 && !row.year2BR==0" Strong><strong>Year-2</strong></td>
															<td ng-repeat="row in summaryDetail" class="tdbgColor" ng-if="row.recordType== 1 && !row.year3BR==0"Strong><strong>Year-3</strong></td>
															<td ng-repeat="row in summaryDetail" class="tdbgColor" ng-if="row.recordType== 1 && !row.year4BR==0" Strong><strong>Year-4</strong></td>
															<td ng-repeat="row in summaryDetail" class="tdbgColor" ng-if="row.recordType== 1 && !row.year5BR==0" Strong><strong>Year-5</strong></td>
															<td ng-repeat="row in summaryDetail" class="tdbgColor" ng-if="row.recordType== 1  && !row.year6BR==0" Strong><strong>Year-6</strong></td>
															<td ng-repeat="row in summaryDetail" class="tdbgColor" ng-if="row.recordType== 1 && !row.year7BR==0" Strong><strong>Year-7</strong></td>
															<td ng-repeat="row in summaryDetail" class="tdbgColor" ng-if="row.recordType== 1 && !row.year8BR==0"Strong><strong>Year-8</strong></td>
															<td ng-repeat="row in summaryDetail" class="tdbgColor" ng-if="row.recordType== 1 && !row.year9BR==0" Strong><strong>Year-9</strong></td>
															<td ng-repeat="row in summaryDetail" class="tdbgColor" ng-if="row.recordType== 1 && !row.year10BR==0" Strong><strong>Year-10</strong></td>
															<td ng-repeat="row in summaryDetail" class="tdbgColor" ng-if="row.recordType == 1 && !row.averageBR==0" Strong><B>Sum Total (Average)</B></td>
														</tr>
														<tr>
															<th style="text-align: left;">Blended Rate/Hr</th>
															<td  ng-repeat="row in summaryDetail" class="thWidth18Per" ng-if="row.recordType== 1  && !row.year1BR==0"> {{row.year1BR | number :2}}</td>
															<td  ng-repeat="row in summaryDetail" class="thWidth18Per" ng-if="row.recordType== 1 && !row.year2BR==0"> {{row.year2BR | number :2}}</td>
															<td  ng-repeat="row in summaryDetail" class="thWidth18Per" ng-if="row.recordType== 1 && !row.year3BR==0"> {{row.year3BR | number :2}}</td>
															<td  ng-repeat="row in summaryDetail" class="thWidth18Per" ng-if="row.recordType== 1 && !row.year4BR==0"> {{row.year4BR | number :2}}</td>
															<td  ng-repeat="row in summaryDetail" class="thWidth18Per" ng-if="row.recordType== 1 && !row.year5BR==0"> {{row.year5BR | number :2}}</td>
															<td  ng-repeat="row in summaryDetail" class="thWidth18Per" ng-if="row.recordType== 1  && !row.year6BR==0"> {{row.year6BR | number :2}}</td>
															<td  ng-repeat="row in summaryDetail" class="thWidth18Per" ng-if="row.recordType== 1 && !row.year7BR==0"> {{row.year7BR | number :2}}</td>
															<td  ng-repeat="row in summaryDetail" class="thWidth18Per" ng-if="row.recordType== 1 && !row.year8BR==0"> {{row.year8BR | number :2}}</td>
															<td  ng-repeat="row in summaryDetail" class="thWidth18Per" ng-if="row.recordType== 1 && !row.year9BR==0"> {{row.year9BR | number :2}}</td>
															<td  ng-repeat="row in summaryDetail" class="thWidth18Per" ng-if="row.recordType== 1 && !row.year10BR==0"> {{row.year10BR | number :2}}</td>
															<td  ng-repeat="row in summaryDetail" class="thWidth18Per"  ng-if="row.recordType == 1 && !row.averageBR==0"> {{row.averageBR | number :2}}</td>
														</tr>
														<tr>	
															<th style="text-align: left;">Blended Cost/Hr</th>
															<td  ng-repeat="row in summaryDetail" class="thWidth18Per" ng-if="row.recordType== 1 && !row.year1BC==0"> {{row.year1BC | number :2}}</td>
															<td  ng-repeat="row in summaryDetail" class="thWidth18Per" ng-if="row.recordType== 1 && !row.year2BC==0"> {{row.year2BC | number :2}}</td>
															<td  ng-repeat="row in summaryDetail" class="thWidth18Per" ng-if="row.recordType== 1 && !row.year3BC==0"> {{row.year3BC | number :2}}</td>
															<td  ng-repeat="row in summaryDetail" class="thWidth18Per" ng-if="row.recordType== 1 && !row.year4BC==0"> {{row.year4BC | number :2}}</td>
															<td  ng-repeat="row in summaryDetail" class="thWidth18Per" ng-if="row.recordType== 1 && !row.year5BC==0"> {{row.year5BC | number :2}}</td>
															<td  ng-repeat="row in summaryDetail" class="thWidth18Per" ng-if="row.recordType== 1 && !row.year6BC==0"> {{row.year6BC | number :2}}</td>
															<td  ng-repeat="row in summaryDetail" class="thWidth18Per" ng-if="row.recordType== 1 && !row.year7BC==0"> {{row.year7BC | number :2}}</td>
															<td  ng-repeat="row in summaryDetail" class="thWidth18Per" ng-if="row.recordType== 1 && !row.year8BC==0"> {{row.year8BC | number :2}}</td>
															<td  ng-repeat="row in summaryDetail" class="thWidth18Per" ng-if="row.recordType== 1 && !row.year9BC==0"> {{row.year9BC | number :2}}</td>
															<td  ng-repeat="row in summaryDetail" class="thWidth18Per" ng-if="row.recordType== 1 && !row.year10BC==0"> {{row.year10BC | number :2}}</td>
															<td  ng-repeat="row in summaryDetail" class="thWidth18Per"  ng-if="row.recordType == 1 && !row.averageBC==0"> {{row.averageBC | number :2}}</td>
														</tr>	
														<tr>
															<th style="text-align: left;">PM%</th>
															
															<td  ng-repeat="row in summaryDetail" class="thWidth18Per" ng-if="row.recordType== 1 && !row.year1beforeGM==0"> {{row.year1beforeGM | number :2}}</td>
															<td  ng-repeat="row in summaryDetail" class="thWidth18Per" ng-if="row.recordType== 1 && !row.year2beforeGM==0"> {{row.year2beforeGM | number :2}}</td>
															<td  ng-repeat="row in summaryDetail" class="thWidth18Per" ng-if="row.recordType== 1 && !row.year3beforeGM==0"> {{row.year3beforeGM | number :2}}</td>
															<td  ng-repeat="row in summaryDetail" class="thWidth18Per" ng-if="row.recordType== 1 && !row.year4beforeGM==0"> {{row.year4beforeGM | number :2}}</td>
															<td  ng-repeat="row in summaryDetail" class="thWidth18Per" ng-if="row.recordType== 1 && !row.year5beforeGM==0"> {{row.year5beforeGM | number :2}}</td>
															<td  ng-repeat="row in summaryDetail" class="thWidth18Per" ng-if="row.recordType== 1 && !row.year6beforeGM==0"> {{row.year6beforeGM | number :2}}</td>
															<td  ng-repeat="row in summaryDetail" class="thWidth18Per" ng-if="row.recordType== 1 && !row.year7beforeGM==0"> {{row.year7beforeGM | number :2}}</td>
															<td  ng-repeat="row in summaryDetail" class="thWidth18Per" ng-if="row.recordType== 1 && !row.year8beforeGM==0"> {{row.year8beforeGM | number :2}}</td>
															<td  ng-repeat="row in summaryDetail" class="thWidth18Per" ng-if="row.recordType== 1 && !row.year9beforeGM==0"> {{row.year9beforeGM | number :2}}</td>
															<td  ng-repeat="row in summaryDetail" class="thWidth18Per" ng-if="row.recordType== 1 && !row.year10beforeGM==0"> {{row.year10beforeGM | number :2}}</td>
															<td  ng-repeat="row in summaryDetail" class="thWidth18Per" ng-if="row.recordType== 1 && !row.averagebeforeGM==0"> {{row.averagebeforeGM | number :2}}</td>
														</tr>
														<!-- <tr>	
															 <th style="text-align: left;">Discount %</th>
															<td ng-repeat="row in summaryDetail" class="thWidth18Per" ng-if="row.recordType== 1  && !row.year1BR==0" Strong>{{row.volumeDiscount}}</td>
															<td ng-repeat="row in summaryDetail" class="thWidth18Per" ng-if="row.recordType== 1 && !row.year2BR==0" Strong>{{row.volumeDiscount}}</td>
															<td ng-repeat="row in summaryDetail" class="thWidth18Per" ng-if="row.recordType== 1 && !row.year3BR==0"Strong>{{row.volumeDiscount}}</td>
															<td ng-repeat="row in summaryDetail" class="thWidth18Per" ng-if="row.recordType== 1 && !row.year4BR==0" Strong>{{row.volumeDiscount}}</td>
															<td ng-repeat="row in summaryDetail" class="thWidth18Per" ng-if="row.recordType== 1 && !row.year5BR==0" Strong>{{row.volumeDiscount}}</td>
															<td ng-repeat="row in summaryDetail" class="thWidth18Per" ng-if="row.recordType== 1  && !row.year6BR==0" Strong>{{row.volumeDiscount}}</td>
															<td ng-repeat="row in summaryDetail" class="thWidth18Per" ng-if="row.recordType== 1 && !row.year7BR==0" Strong>{{row.volumeDiscount}}</td>
															<td ng-repeat="row in summaryDetail" class="thWidth18Per" ng-if="row.recordType== 1 && !row.year8BR==0"Strong>{{row.volumeDiscount}}</td>
															<td ng-repeat="row in summaryDetail" class="thWidth18Per" ng-if="row.recordType== 1 && !row.year9BR==0" Strong>{{row.volumeDiscount}}</td>
															<td ng-repeat="row in summaryDetail" class="thWidth18Per" ng-if="row.recordType== 1 && !row.year10BR==0" Strong>{{row.volumeDiscount}}</td>
															
															<!-- <td  ng-repeat ="row in summaryDetail" class="thWidth18Per" ng-if="row.recordType== 0"> {{row.volumeDiscount}}</td> 
															<td  ng-repeat ="row in summaryDetail" class="thWidth18Per" ng-if="row.recordType== 1"> {{row.avgVolumeDiscount}}</td>	
														</tr >-->
														<tr>	
															<th style="text-align: left;">Volume Discount %</th>
															<td ng-repeat="row in summaryDetail" class="thWidth18Per" ng-if="row.recordType== 1  && !row.year1BR==0" Strong>{{row.volumeDiscount}}</td>
															<td ng-repeat="row in summaryDetail" class="thWidth18Per" ng-if="row.recordType== 1 && !row.year2BR==0" Strong>{{row.volumeDiscount}}</td>
															<td ng-repeat="row in summaryDetail" class="thWidth18Per" ng-if="row.recordType== 1 && !row.year3BR==0"Strong>{{row.volumeDiscount}}</td>
															<td ng-repeat="row in summaryDetail" class="thWidth18Per" ng-if="row.recordType== 1 && !row.year4BR==0" Strong>{{row.volumeDiscount}}</td>
															<td ng-repeat="row in summaryDetail" class="thWidth18Per" ng-if="row.recordType== 1 && !row.year5BR==0" Strong>{{row.volumeDiscount}}</td>
															<td ng-repeat="row in summaryDetail" class="thWidth18Per" ng-if="row.recordType== 1  && !row.year6BR==0" Strong>{{row.volumeDiscount}}</td>
															<td ng-repeat="row in summaryDetail" class="thWidth18Per" ng-if="row.recordType== 1 && !row.year7BR==0" Strong>{{row.volumeDiscount}}</td>
															<td ng-repeat="row in summaryDetail" class="thWidth18Per" ng-if="row.recordType== 1 && !row.year8BR==0"Strong>{{row.volumeDiscount}}</td>
															<td ng-repeat="row in summaryDetail" class="thWidth18Per" ng-if="row.recordType== 1 && !row.year9BR==0" Strong>{{row.volumeDiscount}}</td>
															<td ng-repeat="row in summaryDetail" class="thWidth18Per" ng-if="row.recordType== 1 && !row.year10BR==0" Strong>{{row.volumeDiscount}}</td>
															
															<!-- <td  ng-repeat ="row in summaryDetail" class="thWidth18Per" ng-if="row.recordType== 0"> {{row.volumeDiscount}}</td> -->
															<td  ng-repeat ="row in summaryDetail" class="thWidth18Per" ng-if="row.recordType== 1"> {{row.avgVolumeDiscount}}</td>	
														</tr>
														<tr>
															<th style="text-align: left;">PM% Post Discount</th>
															<td  ng-repeat="row in summaryDetail" class="thWidth18Per" ng-if="row.recordType== 1  && !row.year1GM==0"> {{row.year1GM | number :2}}</td>
															<td  ng-repeat="row in summaryDetail" class="thWidth18Per" ng-if="row.recordType== 1 && !row.year2GM==0"> {{row.year2GM | number :2}}</td>
															<td  ng-repeat="row in summaryDetail" class="thWidth18Per" ng-if="row.recordType== 1 && !row.year3GM==0"> {{row.year3GM | number :2}}</td>
															<td  ng-repeat="row in summaryDetail" class="thWidth18Per" ng-if="row.recordType== 1 && !row.year4GM==0"> {{row.year4GM | number :2}}</td>
															<td  ng-repeat="row in summaryDetail" class="thWidth18Per" ng-if="row.recordType== 1 && !row.year5GM==0"> {{row.year5GM | number :2}}</td>
															<td  ng-repeat="row in summaryDetail" class="thWidth18Per" ng-if="row.recordType== 1  && !row.year6GM==0"> {{row.year6GM | number :2}}</td>
															<td  ng-repeat="row in summaryDetail" class="thWidth18Per" ng-if="row.recordType== 1 && !row.year7GM==0"> {{row.year7GM | number :2}}</td>
															<td  ng-repeat="row in summaryDetail" class="thWidth18Per" ng-if="row.recordType== 1 && !row.year8GM==0"> {{row.year8GM | number :2}}</td>
															<td  ng-repeat="row in summaryDetail" class="thWidth18Per" ng-if="row.recordType== 1 && !row.year9GM==0"> {{row.year9GM | number :2}}</td>
															<td  ng-repeat="row in summaryDetail" class="thWidth18Per" ng-if="row.recordType== 1 && !row.year10GM==0"> {{row.year10GM | number :2}}</td>
															<td  ng-repeat="row in summaryDetail" class="thWidth18Per"  ng-if="row.recordType == 1 && !row.averageGM==0"> {{row.averageGM | number :2}}</td>
														</tr>
														<!-- <tr>
															<th style="text-align: left;">Total Risk </br>(FX+Contingency+SLA)</th>
															<td  ng-repeat="row in summaryDetail" class="thWidth18Per" ng-if="row.recordType== 1  && !row.year1beforeMGM==0"> {{row.totalriskamount | number :2}}</td>
															<td  ng-repeat="row in summaryDetail" class="thWidth18Per" ng-if="row.recordType== 1 && !row.year2beforeMGM==0"> {{row.totalriskamount | number :2}}</td>
															<td  ng-repeat="row in summaryDetail" class="thWidth18Per" ng-if="row.recordType== 1 && !row.year3beforeMGM==0"> {{row.totalriskamount | number :2}}</td>
															<td  ng-repeat="row in summaryDetail" class="thWidth18Per" ng-if="row.recordType== 1 && !row.year4beforeMGM==0"> {{row.totalriskamount | number :2}}</td>
															<td  ng-repeat="row in summaryDetail" class="thWidth18Per" ng-if="row.recordType== 1 && !row.year5beforeMGM==0"> {{row.totalriskamount | number :2}}</td>
															<td  ng-repeat="row in summaryDetail" class="thWidth18Per" ng-if="row.recordType== 1  && !row.year6beforeMGM==0"> {{row.totalriskamount | number :2}}</td>
															<td  ng-repeat="row in summaryDetail" class="thWidth18Per" ng-if="row.recordType== 1 && !row.year7beforeMGM==0"> {{row.totalriskamount | number :2}}</td>
															<td  ng-repeat="row in summaryDetail" class="thWidth18Per" ng-if="row.recordType== 1 && !row.year8beforeMGM==0"> {{row.totalriskamount | number :2}}</td>
															<td  ng-repeat="row in summaryDetail" class="thWidth18Per" ng-if="row.recordType== 1 && !row.year9beforeMGM==0"> {{row.totalriskamount | number :2}}</td>
															<td  ng-repeat="row in summaryDetail" class="thWidth18Per" ng-if="row.recordType== 1 && !row.year10beforeMGM==0"> {{row.totalriskamount | number :2}}</td>
															<td  ng-repeat="row in summaryDetail" class="thWidth18Per"  ng-if="row.recordType == 1 && !row.averagebeforeMGM==0"> {{row.totalriskamount | number :2}}</td>
														</tr> -->
														<!-- <tr>
															<th style="text-align: left;">RightPrice PM%</th>
															<td  ng-repeat="row in summaryDetail" class="thWidth18Per" ng-if="row.recordType== 1  && !row.year1beforeMGM==0"> {{row.year1beforeMGM | number :2}}</td>
															<td  ng-repeat="row in summaryDetail" class="thWidth18Per" ng-if="row.recordType== 1 && !row.year2beforeMGM==0"> {{row.year2beforeMGM | number :2}}</td>
															<td  ng-repeat="row in summaryDetail" class="thWidth18Per" ng-if="row.recordType== 1 && !row.year3beforeMGM==0"> {{row.year3beforeMGM | number :2}}</td>
															<td  ng-repeat="row in summaryDetail" class="thWidth18Per" ng-if="row.recordType== 1 && !row.year4beforeMGM==0"> {{row.year4beforeMGM | number :2}}</td>
															<td  ng-repeat="row in summaryDetail" class="thWidth18Per" ng-if="row.recordType== 1 && !row.year5beforeMGM==0"> {{row.year5beforeMGM | number :2}}</td>
															<td  ng-repeat="row in summaryDetail" class="thWidth18Per" ng-if="row.recordType== 1  && !row.year6beforeMGM==0"> {{row.year6beforeMGM | number :2}}</td>
															<td  ng-repeat="row in summaryDetail" class="thWidth18Per" ng-if="row.recordType== 1 && !row.year7beforeMGM==0"> {{row.year7beforeMGM | number :2}}</td>
															<td  ng-repeat="row in summaryDetail" class="thWidth18Per" ng-if="row.recordType== 1 && !row.year8beforeMGM==0"> {{row.year8beforeMGM | number :2}}</td>
															<td  ng-repeat="row in summaryDetail" class="thWidth18Per" ng-if="row.recordType== 1 && !row.year9beforeMGM==0"> {{row.year9beforeMGM | number :2}}</td>
															<td  ng-repeat="row in summaryDetail" class="thWidth18Per" ng-if="row.recordType== 1 && !row.year10beforeMGM==0"> {{row.year10beforeMGM | number :2}}</td>
															<td  ng-repeat="row in summaryDetail" class="thWidth18Per"  ng-if="row.recordType == 1 && !row.averagebeforeMGM==0"> {{row.averagebeforeMGM | number :2}}</td>
														</tr>-->
														<!--  <tr>
															<th style="text-align: left;">RightPrice PM% </br>Post Discount</th>
															<td  ng-repeat="row in summaryDetail" class="thWidth18Per" ng-if="row.recordType== 1  && !row.year1MGM==0"> {{row.year1MGM | number :2}}</td>
															<td  ng-repeat="row in summaryDetail" class="thWidth18Per" ng-if="row.recordType== 1 && !row.year2MGM==0"> {{row.year2MGM | number :2}}</td>
															<td  ng-repeat="row in summaryDetail" class="thWidth18Per" ng-if="row.recordType== 1 && !row.year3MGM==0"> {{row.year3MGM | number :2}}</td>
															<td  ng-repeat="row in summaryDetail" class="thWidth18Per" ng-if="row.recordType== 1 && !row.year4MGM==0"> {{row.year4MGM | number :2}}</td>
															<td  ng-repeat="row in summaryDetail" class="thWidth18Per" ng-if="row.recordType== 1 && !row.year5MGM==0"> {{row.year5MGM | number :2}}</td>
															<td  ng-repeat="row in summaryDetail" class="thWidth18Per" ng-if="row.recordType== 1  && !row.year6MGM==0"> {{row.year6MGM | number :2}}</td>
															<td  ng-repeat="row in summaryDetail" class="thWidth18Per" ng-if="row.recordType== 1 && !row.year7MGM==0"> {{row.year7MGM | number :2}}</td>
															<td  ng-repeat="row in summaryDetail" class="thWidth18Per" ng-if="row.recordType== 1 && !row.year8MGM==0"> {{row.year8MGM | number :2}}</td>
															<td  ng-repeat="row in summaryDetail" class="thWidth18Per" ng-if="row.recordType== 1 && !row.year9MGM==0"> {{row.year9MGM | number :2}}</td>
															<td  ng-repeat="row in summaryDetail" class="thWidth18Per" ng-if="row.recordType== 1 && !row.year10MGM==0"> {{row.year10MGM | number :2}}</td>
															<td  ng-repeat="row in summaryDetail" class="thWidth18Per"  ng-if="row.recordType == 1 && !row.averageMGM==0"> {{row.averageMGM | number :2}}</td>
														</tr>-->
														<tr>
															<th style="text-align: left;">PM% Incl. Risk and VD.</th>
															<td  ng-repeat="row in summaryDetail" class="thWidth18Per" ng-if="row.recordType== 1  && !row.year1GM==0"> {{row.year1AfterRiskVD| number :2}}</td>
															<td  ng-repeat="row in summaryDetail" class="thWidth18Per" ng-if="row.recordType== 1 && !row.year2GM==0"> {{row.year2AfterRiskVD | number :2}}</td>
															<td  ng-repeat="row in summaryDetail" class="thWidth18Per" ng-if="row.recordType== 1 && !row.year3GM==0"> {{row.year3AfterRiskVD | number :2}}</td>
															<td  ng-repeat="row in summaryDetail" class="thWidth18Per" ng-if="row.recordType== 1 && !row.year4GM==0"> {{row.year4AfterRiskVD | number :2}}</td>
															<td  ng-repeat="row in summaryDetail" class="thWidth18Per" ng-if="row.recordType== 1 && !row.year5GM==0"> {{row.year5AfterRiskVD | number :2}}</td>
															<td  ng-repeat="row in summaryDetail" class="thWidth18Per" ng-if="row.recordType== 1  && !row.year6GM==0"> {{row.year6AfterRiskVD | number :2}}</td>
															<td  ng-repeat="row in summaryDetail" class="thWidth18Per" ng-if="row.recordType== 1 && !row.year7GM==0"> {{row.year7AfterRiskVD | number :2}}</td>
															<td  ng-repeat="row in summaryDetail" class="thWidth18Per" ng-if="row.recordType== 1 && !row.year8GM==0"> {{row.year8AfterRiskVD | number :2}}</td>
															<td  ng-repeat="row in summaryDetail" class="thWidth18Per" ng-if="row.recordType== 1 && !row.year9GM==0"> {{row.year9AfterRiskVD | number :2}}</td>
															<td  ng-repeat="row in summaryDetail" class="thWidth18Per" ng-if="row.recordType== 1 && !row.year10GM==0"> {{row.year10AfterRiskVD | number :2}}</td>
															<td  ng-repeat="row in summaryDetail" class="thWidth18Per"  ng-if="row.recordType == 1 && !row.averageGM==0"> {{row.averageAfterRiskVD | number :2}}</td>
														</tr>
														<!-- <tr>
															<th style="text-align: left;">Difference</th>
															<td ng-repeat="row in summaryDetail" class="thWidth18Per" ng-if="row.recordType == 1 && !row.year1GM==0 && !row.year1MGM==0"> {{row.year1GM - row.year1MGM | number :2}}</td>
															<td ng-repeat="row in summaryDetail" class="thWidth18Per" ng-if="row.recordType == 1 && !row.year2GM==0 && !row.year2MGM==0"> {{row.year2GM - row.year2MGM | number :2}}</td>
															<td ng-repeat="row in summaryDetail" class="thWidth18Per" ng-if="row.recordType == 1 && !row.year3GM==0 && !row.year3MGM==0"> {{row.year3GM - row.year3MGM | number :2}}</td>
															<td ng-repeat="row in summaryDetail" class="thWidth18Per" ng-if="row.recordType == 1 && !row.year4GM==0 && !row.year4MGM==0"> {{row.year4GM - row.year4MGM | number :2}}</td>
															<td ng-repeat="row in summaryDetail" class="thWidth18Per" ng-if="row.recordType == 1 && !row.year5GM==0 && !row.year5MGM==0"> {{row.year5GM - row.year5MGM | number :2}}</td>
															<td ng-repeat="row in summaryDetail" class="thWidth18Per" ng-if="row.recordType == 1 && !row.year6GM==0 && !row.year6MGM==0"> {{row.year6GM - row.year6MGM | number :2}}</td>
															<td ng-repeat="row in summaryDetail" class="thWidth18Per" ng-if="row.recordType == 1 && !row.year7GM==0 && !row.year7MGM==0"> {{row.year7GM - row.year7MGM | number :2}}</td>
															<td ng-repeat="row in summaryDetail" class="thWidth18Per" ng-if="row.recordType == 1 && !row.year8GM==0 && !row.year8MGM==0"> {{row.year8GM - row.year8MGM | number :2}}</td>
															<td ng-repeat="row in summaryDetail" class="thWidth18Per" ng-if="row.recordType == 1 && !row.year9GM==0 && !row.year9MGM==0"> {{row.year9GM - row.year9MGM | number :2}}</td>
															<td ng-repeat="row in summaryDetail" class="thWidth18Per" ng-if="row.recordType == 1 && !row.year10GM==0 &&!row.year10MGM==0"> {{row.year10GM - row.year10MGM | number :2}}</td>
															<td ng-repeat="row in summaryDetail" class="thWidth18Per" ng-if="row.recordType == 1 && !row.averageGM==0 && !row.averageMGM==0"> {{row.averageGM - row.averageMGM | number :2}}</td>

														</tr>-->
													</tbody>
												</table>
											</div>
										</div>
									</div>
								 </div>
                            </div>
                        </div>
                    </div>
                    <div class="divEmptyThrice"></div>
                   
      				<div class="table-responsive" ng-show="multiCountryTable == true && tableIT == true">
     						 <div class="panel-heading panelHeadingStyle">
        							 <div class="row ">
											<label class="control-label col-sm-10 ">Rate Card Summary</label>
									 </div>
      				</div> 
       				<div class="divEmptyThrice"></div>
									<label class="control-label textAlignLeft redColor"><Strong>Note:
									All the rates are in Billing Currency - {{currencyName}} </Strong></label> 
	<div class="divEmptyThrice"></div>
	<div class="table-responsive">
      <table class="table table-bordered bordered table-striped table-condensed datatable " id="tblRateCardSummaryPer" ui-jq="dataTable" ui-options="dataTableOpt" border="1">
      <thead>
        <tr ng-if="yoytableDisplay == true">
          <th class="thWidth15PerSum tdpercAlignCenterper tdRightBorder" colspan="3">Onsite YOY Rate Increase %</th>
          <th class="colRightSection"></th>
          <th  ng-repeat="yoy in yoyIncrement" ng-if="yoy.stepYear == 1" class="colRightSection" >{{yoy.incrementPercentOnsite | number :2}}%</th>
          <th  ng-repeat="yoy in yoyIncrement" ng-if="yoy.stepYear == 2" class="colRightSection">{{yoy.incrementPercentOnsite | number :2}}%</th>
          <th  ng-repeat="yoy in yoyIncrement" ng-if="yoy.stepYear == 3" class="colRightSection">{{yoy.incrementPercentOnsite | number :2}}%</th>
          <th  ng-repeat="yoy in yoyIncrement" ng-if="yoy.stepYear == 4" class="colRightSection">{{yoy.incrementPercentOnsite | number :2}}%</th>    
           <th  ng-repeat="yoy in yoyIncrement" ng-if="yoy.stepYear == 5" class="colRightSection" >{{yoy.incrementPercentOnsite | number :2}}%</th>
          <th  ng-repeat="yoy in yoyIncrement" ng-if="yoy.stepYear == 6" class="colRightSection">{{yoy.incrementPercentOnsite | number :2}}%</th>
          <th  ng-repeat="yoy in yoyIncrement" ng-if="yoy.stepYear == 7" class="colRightSection">{{yoy.incrementPercentOnsite | number :2}}%</th>
          <th  ng-repeat="yoy in yoyIncrement" ng-if="yoy.stepYear == 8" class="colRightSection">{{yoy.incrementPercentOnsite | number :2}}%</th>
          <th  ng-repeat="yoy in yoyIncrement" ng-if="yoy.stepYear == 9"class="colRightSection">{{yoy.incrementPercentOffshore | number :2}}%</th>
          <th  ng-repeat="yoy in yoyIncrement" ng-if="yoy.stepYear == 10"class="colRightSection">{{yoy.incrementPercentOffshore | number :2}}%</th>
          </tr>
          <tr ng-if="yoytableDisplay == true">
          <th class="thWidth15PerSum tdpercAlignCenterper tdRightBorder"colspan="3">Offshore YOY Rate Increase %</th>
          <th class="colRightSection"></th>
          <th  ng-repeat="yoy in yoyIncrement" ng-if="yoy.stepYear == 1"class="colRightSection">{{yoy.incrementPercentOffshore | number :2}}%</th>
          <th  ng-repeat="yoy in yoyIncrement" ng-if="yoy.stepYear == 2"class="colRightSection">{{yoy.incrementPercentOffshore | number :2}}%</th>
          <th  ng-repeat="yoy in yoyIncrement" ng-if="yoy.stepYear == 3"class="colRightSection">{{yoy.incrementPercentOffshore | number :2}}%</th>
          <th  ng-repeat="yoy in yoyIncrement" ng-if="yoy.stepYear == 4"class="colRightSection">{{yoy.incrementPercentOffshore | number :2}}%</th>
           <th  ng-repeat="yoy in yoyIncrement" ng-if="yoy.stepYear == 5"class="colRightSection">{{yoy.incrementPercentOffshore | number :2}}%</th>
          <th  ng-repeat="yoy in yoyIncrement" ng-if="yoy.stepYear == 6"class="colRightSection">{{yoy.incrementPercentOffshore | number :2}}%</th>
          <th  ng-repeat="yoy in yoyIncrement" ng-if="yoy.stepYear == 7"class="colRightSection">{{yoy.incrementPercentOffshore | number :2}}%</th>
          <th  ng-repeat="yoy in yoyIncrement" ng-if="yoy.stepYear == 8"class="colRightSection">{{yoy.incrementPercentOffshore | number :2}}%</th>
          <th  ng-repeat="yoy in yoyIncrement" ng-if="yoy.stepYear == 9"class="colRightSection">{{yoy.incrementPercentOffshore | number :2}}%</th>
          <th  ng-repeat="yoy in yoyIncrement" ng-if="yoy.stepYear == 10"class="colRightSection">{{yoy.incrementPercentOffshore | number :2}}%</th>
          
          
          
          </tr>  
        <tr>
        <th scope="col" colspan="3">Summarized Blended Margins:</th>
			<td class="tdAlignCenterper tdRightBorder" ng-if="yearCount >= 1"><strong>Year-1</strong></td>
            <td class="tdAlignCenterper tdRightBorder" ng-if="yearCount >=2"><strong>Year-2</strong></td>
            <td class="tdAlignCenterper tdRightBorder" ng-if="yearCount >= 3"><strong>Year-3</strong></td>
           <td class="tdAlignCenterper tdRightBorder" ng-if="yearCount >= 4"><strong>Year-4</strong></td>
           <td class="tdAlignCenterper tdRightBorder" ng-if="yearCount >= 5"><strong>Year-5</strong></td>	
           <td class="tdAlignCenterper tdRightBorder" ng-if="yearCount >= 6"><strong>Year-6</strong></td>
            <td class="tdAlignCenterper tdRightBorder" ng-if="yearCount >= 7"><strong>Year-7</strong></td>
            <td class="tdAlignCenterper tdRightBorder" ng-if="yearCount >= 8"><strong>Year-8</strong></td>
           <td class="tdAlignCenterper tdRightBorder" ng-if="yearCount >= 9"><strong>Year-9</strong></td>
           <td class="tdAlignCenterper tdRightBorder" ng-if="yearCount == 10"><strong>Year-10</strong></td>										
		  <!--<td ng-repeat="row in summaryDetail" class="tdbgColor" ng-if="row.summaryType == 2 && frmRateCardCreation.ddlCountryModel == null" Strong>{{row.monthYearHeader | headerFilter : "," : " - "}}</td> -->
          <th rowspan=2 class="tdAlignCenterper tdRightBorder" style="vertical-align:middle;">Average Blended Rate</th>
           <td class="tdAlignCenterper tdRightBorder" ng-if="yearCount >= 1"><strong>Year-1</strong></td>
            <td class="tdAlignCenterper tdRightBorder" ng-if="yearCount >=2"><strong>Year-2</strong></td>
            <td class="tdAlignCenterper tdRightBorder" ng-if="yearCount >= 3"><strong>Year-3</strong></td>
           <td class="tdAlignCenterper tdRightBorder" ng-if="yearCount >= 4"><strong>Year-4</strong></td>
           <td class="tdAlignCenterper tdRightBorder" ng-if="yearCount >= 5"><strong>Year-5</strong></td>	
           <td class="tdAlignCenterper tdRightBorder" ng-if="yearCount >= 6"><strong>Year-6</strong></td>
            <td class="tdAlignCenterper tdRightBorder" ng-if="yearCount >= 7"><strong>Year-7</strong></td>
            <td class="tdAlignCenterper tdRightBorder" ng-if="yearCount >= 8"><strong>Year-8</strong></td>
           <td class="tdAlignCenterper tdRightBorder" ng-if="yearCount >= 9"><strong>Year-9</strong></td>
           <td class="tdAlignCenterper tdRightBorder" ng-if="yearCount == 10"><strong>Year-10</strong></td>
          <!-- <td ng-repeat="row in summaryDetail" class="tdbgColor" ng-if="row.summaryType == 2 && frmRateCardCreation.ddlCountryModel == null" Strong>{{row.monthYearHeader | headerFilter : "," : " - "}}</td> -->
          <th rowspan=2 class="tdAlignCenterper tdRightBorder" style="vertical-align:middle;">Average PM %</th>     
          <th rowspan=2 class="tdAlignCenterper tdRightBorder" style="vertical-align:middle;">Volume Discount</th>       
          <th rowspan=2 class="tdAlignCenterper tdRightBorder" style="vertical-align:middle;">Average PM % Post VR</th>       
         <!--  <th rowspan=2 class="tdAlignCenterper tdRightBorder" style="vertical-align:middle;">RightPrice PM % After VR</th>
           <th rowspan=2 class="tdAlignCenterper tdRightBorder" style="vertical-align:middle;">Diff%</th>       
          <th rowspan=2 class="tdAlignCenterper tdRightBorder" style="vertical-align:middle;">Total Risk </br>(FX+Contingency+SLA)</th>  -->
          <th rowspan=2 class="tdAlignCenterper tdRightBorder" style="vertical-align:middle;">PM% Incl. Risk and VD.</th> 
                   
        </tr>
        <tr class="tdheight3px">
        <th>Sr.</th>
        <th>Location</th>
        <th>Utilization Mix</th>
        <th colspan={{yearCount}} class="tdAlignCenter tdRightBorder" style="vertical-align:middle;">Blended Rate</th>
        <th colspan={{yearCount}} class="tdAlignCenter tdRightBorder" style="vertical-align:middle;">PM %</th>
        </tr>
      </thead>	
        <tbody id="tBodyRoleUtilization" >
									<tr ng-repeat="rateData in summaryDetail"  ng-if="rateData.recordType == 0">
										<!-- <td class="grayBgColor colSection firstColLeftAlign tooltip1 tdWordBreak">{{rateData.MasterRoleId}}
										 <span class="tooltiptext">{{rateData.RoleDescLong}}</span>
										</td> -->
										<td class="colRightSection">{{$index+1}}
										</td>
										<td class="colRightSection" ><span ng-if="!rateData.rateCardProcessingType">{{rateData.location}}</span>
										<span class="colRightSection" ng-if="rateData.rateCardProcessingType == 'M'">{{rateData.location}} (M) </span></td>
										<td class="colRightSection">{{rateData.utilizationMix | number :2}}%</td>	
										<td class="colRightSection" ng-if="yearCount >= 1"><span ng-if="!rateData.year1BR == 0 ">{{rateData.year1BR | number :2}}</span>
										<span class="colmiddleempty" ng-if="rateData.year1BR == 0 || rateData.year1BR == null"> - </span></td>
								<td class="colRightSection" ng-if="yearCount >= 2"><span ng-if="!rateData.year2BR == 0 ">{{rateData.year2BR | number :2}}</span>        
        								<span class="colmiddleempty" ng-if="rateData.year2BR == 0 || rateData.year2BR == null"> - </span></td>
										<td class="colRightSection" ng-if="yearCount >= 3"><span ng-if="!rateData.year3BR == 0 ">{{rateData.year3BR | number :2}}</span>        
        								<span class="colmiddleempty" ng-if="rateData.year3BR == 0 || rateData.year3BR == null"> - </span></td>
										<td class="colRightSection" ng-if="yearCount >= 4"><span ng-if="!rateData.year4BR == 0 ">{{rateData.year4BR | number :2}}</span>        
        								<span class="colmiddleempty" ng-if="rateData.year4BR == 0 || rateData.year4BR == null"> - </span></td>
										<td class="colRightSection" ng-if="yearCount >= 5"><span ng-if="!rateData.year5BR == 0 ">{{rateData.year5BR | number :2}}</span>        
        								<span class="colmiddleempty" ng-if="rateData.year5BR == 0 || rateData.year5BR == null"> - </span></td>
        								<td class="colRightSection" ng-if="yearCount >= 6"><span ng-if="!rateData.year6BR == 0 ">{{rateData.year6BR | number :2}}</span>
										<span class="colmiddleempty" ng-if="rateData.year6BR == 0 || rateData.year6BR == null"> - </span></td>
										<td class="colRightSection" ng-if="yearCount >= 7"><span ng-if="!rateData.year7BR == 0">{{rateData.year7BR | number :2}}</span>        
        								<span class="colmiddleempty" ng-if="rateData.year7BR == 0 || rateData.year7BR == null"> - </span></td>
										<td class="colRightSection" ng-if="yearCount >= 8"><span ng-if="!rateData.year8BR == 0 ">{{rateData.year8BR | number :2}}</span>        
        								<span class="colmiddleempty" ng-if="rateData.year8BR == 0 || rateData.year8BR == null"> - </span></td>
										<td class="colRightSection" ng-if="yearCount >= 9"><span ng(-if="!rateData.year9BR == 0  ">{{rateData.year9BR | number :2}}</span>        
        								<span class="colmiddleempty" ng-if="rateData.year9BR == 0 || rateData.year9BR == null"> - </span></td>
										<td class="colRightSection" ng-if="yearCount == 10"><span ng-if="!rateData.year10BR == 0">{{rateData.year10BR | number :2}}</span>        
        								<span class="colmiddleempty" ng-if="rateData.year10BR == 0 || rateData.year10BR == null"> - </span></td>
        								<td class="colRightSection"><span ng-if="!rateData.averageBR == 0">{{rateData.averageBR | number :2}}</span>        
        								<span class="colmiddleempty" ng-if="rateData.averageBR == 0 || rateData.averageBR == null"> - </span></td> 
										<td class="colRightSection" ng-if="yearCount >= 1"><span ng-if="!rateData.year1beforeGM == 0">{{rateData.year1beforeGM | number :2}}%</span>
										<span class="colmiddleempty" ng-if="rateData.year1beforeGM == 0 || rateData.year1beforeGM == null"> - </span></td>
										<td class="colRightSection" ng-if="yearCount >= 2"><span ng-if="!rateData.year2beforeGM == 0 ">{{rateData.year2beforeGM | number :2}}%</span>        
        								<span class="colmiddleempty" ng-if="rateData.year2beforeGM == 0 || rateData.year2beforeGM == null"> - </span></td>
										<td class="colRightSection" ng-if="yearCount >= 3"><span ng-if="!rateData.year3beforeGM == 0 ">{{rateData.year3beforeGM | number :2}}%</span>        
        								<span class="colmiddleempty" ng-if="rateData.year3beforeGM == 0 || rateData.year3beforeGM == null"> - </span></td>
										<td class="colRightSection" ng-if="yearCount >= 4"><span ng-if="!rateData.year4beforeGM == 0 ">{{rateData.year4beforeGM | number :2}}%</span>        
        								<span class="colmiddleempty" ng-if="rateData.year4beforeGM == 0 || rateData.year4beforeGM == null" > - </span></td>
        								<td class="colRightSection" ng-if="yearCount >= 5"><span ng-if="!rateData.year5beforeGM == 0 ">{{rateData.year5beforeGM | number :2}}%</span>        
        								<span class="colmiddleempty" ng-if="rateData.year5beforeGM == 0 || rateData.year5beforeGM == null" > - </span></td>
        								<td class="colRightSection" ng-if="yearCount >= 6"><span ng-if="!rateData.year6beforeGM == 0 ">{{rateData.year6beforeGM | number :2}}%</span>
										<span class="colmiddleempty" ng-if="rateData.year6beforeGM == 0 || rateData.year6beforeGM == null"> - </span></td>
										<td class="colRightSection" ng-if="yearCount >= 7"><span ng-if="!rateData.year7beforeGM == 0 ">{{rateData.year7beforeGM | number :2}}%</span>        
        								<span class="colmiddleempty" ng-if="rateData.year7beforeGM == 0 || rateData.year7beforeGM == null"> - </span></td>
										<td class="colRightSection" ng-if="yearCount >= 8"><span ng-if="!rateData.year8beforeGM == 0 ">{{rateData.year8beforeGM | number :2}}%</span>        
        								<span class="colmiddleempty" ng-if="rateData.year8beforeGM == 0 || rateData.year8beforeGM == null"> - </span></td>
										<td class="colRightSection" ng-if="yearCount >= 9"><span ng-if="!rateData.year9beforeGM == 0 ">{{rateData.year9beforeGM | number :2}}%</span>        
        								<span class="colmiddleempty" ng-if="rateData.year9beforeGM == 0 || rateData.year9beforeGM == null" > - </span></td>
        								<td class="colRightSection" ng-if="yearCount == 10"><span ng-if="!rateData.year10beforeGM == 0">{{rateData.year10beforeGM | number :2}}%</span>        
        								<span class="colmiddleempty" ng-if="rateData.year10beforeGM == 0 || rateData.year10beforeGM == null" > - </span></td>
										<td class="colRightSection"><span ng-if="!rateData.averagebeforeGM == 0 ">{{rateData.averagebeforeGM | number :2}}%</span>        
        								<span class="colmiddleempty" ng-if="rateData.averagebeforeGM == 0 || rateData.averagebeforeGM == null"> - </span></td>
        								<td ng-repeat ="row in rateCardInfo" class="colRightSection"><span ng-if="!row.volumeDiscount == 0">{{row.volumeDiscount | number :2}}%</span>        
        								<span class="colmiddleempty" ng-if="row.volumeDiscount == 0 || row.volumeDiscount == null"> - </span></td>
        								<td class="colRightSection"><span ng-if="!rateData.averageGM == 0">{{rateData.averageGM | number :2}}%</span>        
        								<!-- <span class="colmiddleempty" ng-if="rateData.averageGM == 0 || rateData.averageGM == null"> - </span></td>
        								<td class="colRightSection"><span ng-if="!rateData.averageMGM == 0">{{rateData.averageMGM | number :2}}%</span>        
        								<span class="colmiddleempty" ng-if="rateData.averageMGM == 0 || rateData.averageMGM == null"> - </span></td>
										<td class="colRightSection">{{rateData.averageGM - rateData.averageMGM | number :2}}%</td>
										<span class="colmiddleempty" ng-if="rateData.averageMGM == 0 || rateData.averageMGM == null"> - </span></td>
										<td class="colRightSection">{{rateData.totalriskamount  | number :2}}</td> -->
										<span class="colmiddleempty" ng-if="rateData.averageMGM == 0 || rateData.averageMGM == null"> - </span></td>
										<td class="colRightSection">{{rateData.averageAfterRiskVD  | number :2}}%</td>
										
									</tr>
							
									<tr ng-repeat="rateData in summaryDetail" ng-if="rateData.recordType == 1" style="font-weight:bold;">
										<td class="tdAlignCenter tdSubTotal" colspan="2" ><strong>Consolidated</strong></td>
										<td>{{rateData.utilizationMix | number :2}}%</td>
										<td class="colRightSection" ng-if="yearCount >= 1"><span ng-if="!rateData.year1BR == 0">{{rateData.year1BR | number :2}}</span>        
        								<span class="colmiddleempty" ng-if="rateData.year1BR == 0"> - </span></td>
        								<td class="colRightSection" ng-if="yearCount >= 2"><span ng-if="!rateData.year2BR == 0">{{rateData.year2BR | number :2}}</span>        
        								<span class="colmiddleempty" ng-if="rateData.year2BR == 0"> - </span></td>
        								<td class="colRightSection" ng-if="yearCount >= 3"><span ng-if="!rateData.year3BR == 0">{{rateData.year3BR | number :2}}</span>        
        								<span class="colmiddleempty" ng-if="rateData.year3BR == 0"> - </span></td>
        								<td class="colRightSection" ng-if="yearCount >= 4"><span ng-if="!rateData.year4BR == 0">{{rateData.year4BR | number :2}}</span>        
        								<span class="colmiddleempty" ng-if="rateData.year4BR == 0"> - </span></td>
        								<td class="colRightSection" ng-if="yearCount >= 5"><span ng-if="!rateData.year5BR == 0">{{rateData.year5BR | number :2}}</span>        
        								<span class="colmiddleempty" ng-if="rateData.year5BR == 0"> - </span></td>
        								<td class="colRightSection" ng-if="yearCount >= 6"><span ng-if="!rateData.year6BR == 0">{{rateData.year6BR | number :2}}</span>        
        								<span class="colmiddleempty" ng-if="rateData.year6BR == 0"> - </span></td>
        								<td class="colRightSection" ng-if="yearCount >= 7"><span ng-if="!rateData.year7BR == 0">{{rateData.year7BR | number :2}}</span>        
        								<span class="colmiddleempty" ng-if="rateData.year7BR == 0"> - </span></td>
        								<td class="colRightSection" ng-if="yearCount >= 8"><span ng-if="!rateData.year8BR == 0">{{rateData.year8BR | number :2}}</span>        
        								<span class="colmiddleempty" ng-if="rateData.year8BR == 0"> - </span></td>
        								<td class="colRightSection" ng-if="yearCount >= 9"><span ng-if="!rateData.year9BR == 0">{{rateData.year9BR | number :2}}</span>        
        								<span class="colmiddleempty" ng-if="rateData.year9BR == 0"> - </span></td>
        								<td class="colRightSection" ng-if="yearCount == 10"><span ng-if="!rateData.year10BR == 0">{{rateData.year10BR | number :2}}</span>        
        								<span class="colmiddleempty" ng-if="rateData.year10BR == 0"> - </span></td>
        								<td class="colRightSection"><span ng-if="!rateData.year1BR == 0">{{rateData.averageBR | number :2}}</span>        
        								<span class="colmiddleempty" ng-if="rateData.year1BR == 0"> - </span></td>
        								<td class="colRightSection" ng-if="yearCount >= 1"><span ng-if="!rateData.year1beforeGM == 0">{{rateData.year1beforeGM | number :2}}%</span>        
        								<span class="colmiddleempty"  ng-if="rateData.year1beforeGM == 0"> - </span></td>
        								<td class="colRightSection" ng-if="yearCount >= 2"><span ng-if="!rateData.year2beforeGM == 0">{{rateData.year2beforeGM | number :2}}%</span>        
        								<span class="colmiddleempty" ng-if="rateData.year2beforeGM == 0"> - </span></td>
        								<td class="colRightSection" ng-if="yearCount >= 3"><span ng-if="!rateData.year3beforeGM == 0">{{rateData.year3beforeGM | number :2}}%</span>        
        								<span class="colmiddleempty" ng-if="rateData.year3beforeGM == 0"> - </span></td>
        								<td class="colRightSection" ng-if="yearCount >= 4"><span ng-if="!rateData.year4beforeGM == 0">{{rateData.year4beforeGM | number :2}}%</span>        
        								<span class="colmiddleempty" ng-if="rateData.year4beforeGM == 0"> - </span></td>
        								<td class="colRightSection" ng-if="yearCount >= 5"><span ng-if="!rateData.year5beforeGM == 0">{{rateData.year5beforeGM | number :2}}%</span>        
        								<span class="colmiddleempty" ng-if="rateData.year5beforeGM == 0"> - </span></td>
        								<td class="colRightSection" ng-if="yearCount >= 6"><span ng-if="!rateData.year6beforeGM == 0">{{rateData.year6beforeGM | number :2}}%</span>        
        								<span class="colmiddleempty"  ng-if="rateData.year6beforeGM == 0"> - </span></td>
        								<td class="colRightSection" ng-if="yearCount >= 7"><span ng-if="!rateData.year7beforeGM == 0">{{rateData.year7beforeGM | number :2}}%</span>        
        								<span class="colmiddleempty" ng-if="rateData.year7beforeGM == 0"> - </span></td>
        								<td class="colRightSection" ng-if="yearCount >= 8"><span ng-if="!rateData.year8beforeGM == 0">{{rateData.year8beforeGM | number :2}}%</span>        
        								<span class="colmiddleempty" ng-if="rateData.year8beforeGM == 0"> - </span></td>
        								<td class="colRightSection" ng-if="yearCount >= 9"><span ng-if="!rateData.year9beforeGM == 0">{{rateData.year9beforeGM | number :2}}%</span>        
        								<span class="colmiddleempty" ng-if="rateData.year9beforeGM == 0"> - </span></td>
        								<td class="colRightSection" ng-if="yearCount == 10"><span ng-if="!rateData.year10beforeGM == 0">{{rateData.year10beforeGM | number :2}}%</span>        
        								<span class="colmiddleempty" ng-if="rateData.year10beforeGM == 0"> - </span></td>
        								<td class="colRightSection" ><span ng-if="!rateData.averagebeforeGM == 0">{{rateData.averagebeforeGM | number :2}}%</span>        
        								<span class="colmiddleempty" ng-if="rateData.averagebeforeGM == 0"> - </span></td>
        								<td ng-repeat ="row in rateCardInfo" class="colRightSection"><span ng-if="!row.volumeDiscount == 0">{{row.volumeDiscount | number :2}}%</span>        
        								<span class="colmiddleempty" ng-if="row.volumeDiscount == 0"> - </span></td>
        								<td class="colRightSection"><span ng-if="!rateData.averageGM == 0">{{rateData.averageGM | number :2}}%</span>        
        								<!-- <span class="colmiddleempty" ng-if="rateData.averageGM == 0"> - </span></td>
        								<td class="colRightSection"><span ng-if="!rateData.averageMGM == 0">{{rateData.averageMGM | number :2}}%</span>        
        								<span class="colmiddleempty" ng-if="rateData.averageMGM == 0"> - </span></td>
        								<td class="colRightSection">{{rateData.averageGM - rateData.averageMGM | number :2}}%</td>     
										<span class="colmiddleempty" ng-if="rateData.averageMGM == 0"> - </span></td>
        								<td class="colRightSection">{{rateData.totalriskamount | number :2}}</td> --> 
        								<span class="colmiddleempty" ng-if="rateData.averageMGM == 0"> - </span></td>
        								<td class="colRightSection">{{rateData.averageAfterRiskVD | number :2}}%</td> 						
									</tr>			
								</tbody>
    </table>
  </div>
    </div>

  <div class="divEmptyThrice"></div>
		<label class="control-label textAlignLeft redColor" ng-show="multiCountryTable == true && tableIT == true"><Strong>Note:
							M indicates Manual </Strong></label>
  <div class="divEmptyThrice" ></div>
  <div class="table-responsive"  ng-show="multiCountryTable == true && tableIT == true">
      <table class="tdWidth5per table-bordered bordered table-striped table-condensed datatable " id="tblRateCardSummary" ui-jq="dataTable" ui-options="dataTableOpt">
      	<thead>
      	<tr>
        <th>FX Rates</th>
      	</tr>
      	</thead>
      	<tbody>
      	<tr ng-repeat="rateData in trimmedValuefx">
      	<td class="class="colRightSection"><span>{{rateData}}</span></td>
		</tr>
		
      	</tbody>
      </table>
   </div>

   
   <div class="divEmptyThrice"></div>
                   
      				<div class="table-responsive" ng-show="multiCountryTable == true && tableKPO == true">
     						 <div class="panel-heading panelHeadingStyle">
        							 <div class="row ">
											<label class="control-label col-sm-10 ">Rate Card Summary</label>
									 </div>
      				</div> 
       				<div class="divEmptyThrice"></div>
									<label class="control-label textAlignLeft redColor"><Strong>Note:
									All the rates are in Billing Currency - {{currencyName}} </Strong></label>
	<div class="divEmptyThrice"></div>
	 <div class="table-responsive" >
      <table class="table table-bordered bordered table-striped table-condensed datatable " id="tblRateCardSummaryPer" ui-jq="dataTable" ui-options="dataTableOpt" border="1">
      <thead>
        <tr class="tdheight3px">
        <th style="vertical-align:middle;">Sr.</th>
        <th style="vertical-align:middle;">Location</th>
        <th style="vertical-align:middle;">Blended Rate</th>
        <th style="vertical-align:middle;">Blended Cost</th>
        <th  style="vertical-align:middle;">Average PM %</th>     
        <th  style="vertical-align:middle;">Average PM % Post VR</th>       
      </thead>	
        <tbody id="tBodyRoleUtilization" >
			<tr ng-repeat="rateData in summaryDetail">
				<td class="colRightSection">{{$index+1}}</td>
				<td class="colRightSection"><span ng-if="!rateData.rateCardProcessingType">{{rateData.location}}</span>
					<span class="colRightSection" ng-if="rateData.rateCardProcessingType == 'M'">{{rateData.location}} (M) </span></td>
			
				<td class="colRightSection"><span ng-if="!rateData.year1BR == 0 ">{{rateData.year1BR | number :2}}</span>        
					<span class="colmiddleempty" ng-if="rateData.year1BR == 0 || rateData.year1BR == null"> - </span></td>
				
				<td class="colRightSection"><span ng-if="!rateData.year1BC== 0 ">{{rateData.year1BC | number :2}}</span>        
					<span class="colmiddleempty" ng-if="rateData.year1BC == 0 || rateData.year1BC == null"> - </span></td>
				
				<td class="colRightSection"><span ng-if="!rateData.averagebeforeGM == 0 ">{{rateData.averagebeforeGM | number :2}}%</span>        
					<span class="colmiddleempty" ng-if="rateData.averagebeforeGM == 0 || rateData.averagebeforeGM == null"> - </span></td>
					
				<td class="colRightSection"><span ng-if="!rateData.averageGM == 0">{{rateData.averageGM | number :2}}%</span>        
					<span class="colmiddleempty" ng-if="rateData.averageGM == 0 || rateData.averageGM == null"> - </span></td>
  	   </tbody>
    </table>
      </div>
        </div>
 
  <div class="divEmptyThrice"></div>
									<label class="control-label textAlignLeft redColor" ng-show="multiCountryTable == true && tableKPO == true"><Strong>Note:
									M indicates Manual </Strong></label>
  <div class="divEmptyThrice" ></div>
  <div class="table-responsive"  ng-show="multiCountryTable == true && tableKPO == true">
      <table class="tdWidth5per table-bordered bordered table-striped table-condensed datatable " id="tblRateCardSummary" ui-jq="dataTable" ui-options="dataTableOpt">
      	<thead>
      	<tr>
        <th>FX Rates</th>
      	</tr>
      	</thead>
      	<tbody>
      	<tr ng-repeat="rateData in trimmedValuefx">
      	<td class="class="colRightSection"><span>{{rateData}}</span></td>
		</tr>	
      	</tbody>
      </table>
   </div>
 
   
 <div class="divEmptyThrice" ></div>
 <div class="row" ng-show="showaprovercoment == true">
                    <div class="col-sm-12">
                        <div class="panel-group">
                            <div class="panel panel-info ">
                                <div class="panel-heading panelHeadingStyle">
                                    <div class="row ">
										<label class="control-label col-sm-10 ">Approver Comments</label>
									</div>
                                </div>
                                <div class="panel-body" >
								 <div class="row marginBottom5px">
	                                	<label class="control-label col-sm-2 textAlignRight required-Field">Comments</label>
	                                	<div class="col-sm-10">
	                                		<textarea style="resize:none" name="txtApproverComment" id="txtApproverComment" class="form-control" rows="3"
	                                			 ng-model="frmRateCardCreation.approverCommentModel" required disabled></textarea>
	                             		</div>
                 					</div>
                               	
                                
									<div class="panel-body" ng-hide="AttachmentHide">
										<div class="col-sm-2 "></div>
										<label
											class="control-label col-sm-2 textAlignRight required-Field">
											Attachment </label>
										<div class="col-sm-3 ">
											<input type="file" class="form-control"
												name="fuAttachFilename" id="fuAttachFilename"
												ng-model="frmRateCardCreation.fuAttachFilenameModel"
												check-file-size="frmRateCardCreation.fuAttachFilenameModel"
												valid-file-rate-card
												ng-class="{true: 'ng-border'}[(upload) && frmRateCardCreation.fuAttachFilename.$invalid]">
										</div>
										<div class="col-sm-1">
											<button type="button" class="btn btn-primary btnSpace"
												id="btnUpload"
												ng-click="uploadAttachmentPLData(frmRateCardCreation);"
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
														ng-click="deleteFile(row.objectid);"
														ng-disabled="deleteBtnDisable"><label>Delete</label></a></td>
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
                                          <select id="ddlRainbowApproval" class="form-control" ng-model="frmRateCardCreation.rainbowApprovalRequired"  placeholder="Please select" name="ddlRainbowApproval" ng-change = "checkApprovalLevel(frmRateCardCreation.rainbowApprovalRequired)" ng-class="{true: 'ng-border'}[submitted && ddlRainbowApproval.$invalid]" 
                                           ng-options="raf.id as raf.name for raf in rainbowApprovalFlag" required>
										<option value="" selected disabled>Please select</option>
										<option value="1">Yes</option>
										<option value="2">No</option>
									</select>
                                        </div>
                                     </div>	
                                     <div class="row marginBottom5px" ng-show = "isApprovalType">
                                    	<label class="control-label col-sm-2 textAlignRight required-Field">Approval Level</label>
                                        <div class="col-sm-2">
                                          <select id="ddlRainbowApprovalLevel" class="form-control" ng-model="frmRateCardCreation.rainbowApprovalLevel"  placeholder="Please select" name="ddlRainbowApprovalLevel" ng-class="{true: 'ng-border'}[submitted && ddlRainbowApproval.$invalid]" 
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
              
                <div class="row" ng-hide="checkApprover">
                    <div class="col-sm-12">
                        <div class="panel-group">
                            <div class="panel panel-info ">
                                <div class="panel-heading panelHeadingStyle">
                                    <div class="row ">
										<label class="control-label col-sm-10 ">Approver Action</label>
										
									</div>
                                </div>
                                <div class="panel-body" >
									<div class="row marginBottom5px">
                                    	<label class="control-label col-sm-2 textAlignRight required-Field">Approver Action</label>
                                        <div class="col-sm-2">
                                          <select id="ddlApproverStatus" class="form-control" ng-model="frmRateCardCreation.approvalStatus"  placeholder="Please select" name="ddlApproverStatus" ng-change="getApprovalComments('<%=session.getAttribute("user")%>')" ng-class="{true: 'ng-border'}[submitted && frmPolicyApproval.ddlApproverStatus.$invalid]" required>
										<option value="" selected disabled>Please select</option>
										<option value="3">Approve</option>
										<option value="4">Reject</option>
									</select>
                                        </div>
                                     </div>	
                                     <div class="row marginBottom5px">
	                                	<label class="control-label col-sm-2 textAlignRight required-Field">Comments</label>
	                                	<div class="col-sm-10">
	                                		<textarea style="resize:none" name="txtApproverComment" id="txtApproverComment" class="form-control" rows="3"
	                                			 ng-model="frmRateCardCreation.approverCommentModel" required disabled></textarea>
	                             		</div>
                               		 </div>
                               		 <div class="row marginBottom5px" ng-show="commentbox == true">
	                                	<label class="control-label col-sm-2 textAlignRight required-Field">Enter your Comments</label>
	                                	<div class="col-sm-10">
	                                		<textarea style="resize:none" name="txtCurrentApproverComment" id="txtCurrentApproverComment" class="form-control" rows="3"
	                                			 ng-model="frmRateCardCreation.currentApproverCommentModel" required></textarea>
	                             		</div>
	                             		<div class="error-messages" ng-if="onAddRow" ng-messages="frmRateCardCreation.txtCurrentApproverComment.$error">
										        	<em class="error help-block has-error" ng-message="required">Please enter comments</em>
										</div>
                               		 </div>
                               		 
                               		<!--  <input type="hidden"  name="txtCurrentApproverComment" id="txtCurrentApproverComment" class="form-control" 
	                                			 ng-model="frmRateCardCreation.currentApproverCommentModel" disabled> -->
                               		 
                               		 <div class="row text-center">
										<div class="col-sm-12">		
											<button type="button" class="btn btn-primary btnSpace" id="btnAddSave" ng-click="saveApprovalData('<%=session.getAttribute("user")%>',frmRateCardCreation.approvalStatus,frmRateCardCreation.currentApproverCommentModel)" ng-disabled="isDisable">Save</button>						
											<!-- <button type="button" class="btn btn-danger btnSpace" id="btnAddCancel">Cancel</button> -->
											<!-- <button type="button" class="btn btn-info btnSpace" id="btnPrev" ng-click="Prev()">Prev</button>
											<button type="button" class="btn btn-info" id="btnNext" ng-click="Next()">Next</button> -->
										</div>
                           	 		</div>
                                </div>
                                
                        </div>
                    </div>
                    
				</div>
                </div> 
			  <div class="row text-center">
				<div class="col-sm-12">	
				         <button class="btn btn-info btnSpace" type="button" ng-click="exportToExcelSummary('#tableToExportSummary')">
										     <img src="${contextPath}/resources/Images/downloadexcel.png" alt="Snow"> Export to Excel </button>
					<button type="button" class="btn btn-info btnSpace" id="btnPrev" ng-click="Prev()">Prev</button>
					<button type="button" class="btn btn-info" id="btnNext" ng-click="Next()">Next</button>
				</div>
           	 </div>
                	 		
                	 		
                	 	<!--  ------------------------- Download Functionality by AS5045662  ----------------------   -->
                	 	
       <div class="table-responsive" id="tableToExportSummary" style="display: none;">
       
        <table class="table table-bordered bordered table-striped table-condensed datatable " id="tblRateCardSummaryPer" ui-jq="dataTable" ui-options="dataTableOpt" border="1">
       <tr><td align="center" bgcolor="#CCFFFF" colspan="19" ><Strong>
       Rate Card Creation - Summary
       </Strong></td></tr>
       </table>
       
       <table></table>
       
       <table border="1" 
				class="table table-striped table-bordered table-hover table-condensed "
				id="tblRateCardDetails">
				<tbody id="tBodyRateCardDetails" >
					<tr ng-repeat ="row in rateCardInfo">
						<th align="left" bgcolor="#CCFFFF"  >Rate Card Id</th>
						<td class="thWidth18Per" align="left" >{{row.rcId}}</td>
						<th class="thWidth18Per" align="left" bgcolor="#CCFFFF" >Name</th>
						<td class="thWidth18Per" align="left">{{row.rcName}}</td>
						<th class="thWidth15Per" align="left" bgcolor="#CCFFFF" >Approver Status</th>
						<td class="thWidth18Per" align="left">{{status}}</td>
					</tr>
					<tr ng-repeat ="row in rateCardInfo">
						<th align="left" bgcolor="#CCFFFF" >Start Date (dd/mm/yyyy)</th>
						<td class="thWidth18Per" align="left">{{row.rcStartDate }}</td>
						<th class="thWidth18Per" align="left" bgcolor="#CCFFFF" >Expected End Date (dd/mm/yyyy)</th>
						<td class="thWidth18Per" align="left">{{row.expectedRCEndDate | date:'dd/mmm/yyyy'}}</td>
						<th class="thWidth15Per" align="left" bgcolor="#CCFFFF" >Applicable Months</th>
						<td class="thWidth18Per" align="left">{{row.applicableMonths}}</td>
					</tr>
					<tr ng-repeat ="row in rateCardInfo">
						<th align="left" bgcolor="#CCFFFF" >Customer</th>
						<td class="thWidth18Per" align="left">{{row.customerVerticalMapping.customer.customerName}}</td>
						<th class="thWidth18Per" align="left" bgcolor="#CCFFFF" >Expected TCV</th>
						<td class="thWidth18Per" align="left">{{row.tvc}} - {{currencyName}}</td>
						<th class="thWidth15Per" align="left" bgcolor="#CCFFFF" >Discount</th>
						<td class="thWidth18Per" align="left">{{row.volumeDiscount}}%</td>
					</tr>
					<tr ng-repeat ="row in rateCardInfo">
						<th align="left" bgcolor="#CCFFFF" >Onsite Utilization %</th>
						<td class="thWidth18Per" align="left">{{row.expectedOnsiteResourcePercentage}}%</td>
						<th class="thWidth18Per" align="left" bgcolor="#CCFFFF" >Offshore Utilization %</th>
						<td class="thWidth18Per" align="left">{{row.expectedOffshoreResourcePercentage}}%</td>
						<th class="thWidth15Per" align="left" bgcolor="#CCFFFF" >PM% Post VR and Risk</th>
						<td class="thWidth18Per" align="left"><span ng-if="!row.calculatedGMPercentagePostDiscount == 0 || !row.calculatedGMPercentagePostDiscount == null">{{row.calculatedGMPercentagePostDiscount| number :2}}%</span>
						<span ng-if="row.calculatedGMPercentagePostDiscount == 0 || row.calculatedGMPercentagePostDiscount == null"> - </span></td>
					</tr>	
					<tr ng-repeat ="row in rateCardInfo">
						<th align="left" bgcolor="#CCFFFF" >Onsite hours/day</th>
						<td class="thWidth18Per" align="left">{{row.onsiteHoursPerDay}}</td>
						<th class="thWidth18Per" align="left" bgcolor="#CCFFFF" >Offshore hours/day</th>
						<td class="thWidth18Per" align="left">{{row.offshoreHoursPerDay}}</td>
						<th class="thWidth15Per" align="left" bgcolor="#CCFFFF" >Billing Currency</th>
						<td class="thWidth18Per" align="left">{{currencyName}}</td>
					</tr>	
				</tbody>
			</table>
       <table></table>
       <div class="row" ng-If="tableDisplay == true && tableIT == true">
                    <div class="col-sm-12">
                        <div class="panel-group">
                            <div class="panel panel-info ">
                                <div class="panel-heading panelHeadingStyle">
                                    <div class="row ">
										<table class="table table-bordered bordered table-striped table-condensed datatable "
												id="tblRateCardSummaryPer" ui-jq="dataTable"
												ui-options="dataTableOpt" border="1">
												<tr>
														<td align="center" bgcolor="#CCFFFF" colspan="19"><Strong>
																Rate Card Summary </Strong></td>
													</tr>
												</table>
												<table></table>
												<div class="divEmptyThrice"></div>
							        <div class="row">
										<table> <tr><td colspan="19" > <Strong> Over All Summary </Strong></td></tr></table>
									<table></table> 
									</div>
									<div>
									 <table> <tr><td colspan="19" style="color: red;"> <Strong>Note: All the rates are in Billing Currency - {{currencyName}} </Strong></td></tr></table>
									<table></table>
									</div>
									</div>
									</div>
									<div class="row marginBottom5px">
	                                    <div class="col-sm-12">
											<div class="table-responsive" >
												<table 
													class="table clsTable table-striped table-bordered table-hover table-condensed "
													id="tblOverAllSummary" border="1">
													<tbody id="tBodyOverAllSummary" >
														<tr>
															<th class="thWidth15Per" align="left" bgcolor="#CCFFFF"></th>
															<td ng-repeat="row in summaryDetail" class="tdbgColor" ng-if="row.recordType== 1  && !row.year1BR==0" Strong align="left" bgcolor="#CCFFFF"><strong>Year-1</strong></td>
															<td ng-repeat="row in summaryDetail" class="tdbgColor" ng-if="row.recordType== 1 && !row.year2BR==0" Strong align="left" bgcolor="#CCFFFF"><strong>Year-2</strong></td>
															<td ng-repeat="row in summaryDetail" class="tdbgColor" ng-if="row.recordType== 1 && !row.year3BR==0"Strong align="left" bgcolor="#CCFFFF"><strong>Year-3</strong></td>
															<td ng-repeat="row in summaryDetail" class="tdbgColor" ng-if="row.recordType== 1 && !row.year4BR==0" Strong align="left" bgcolor="#CCFFFF"><strong>Year-4</strong></td>
															<td ng-repeat="row in summaryDetail" class="tdbgColor" ng-if="row.recordType== 1 && !row.year5BR==0" Strong align="left" bgcolor="#CCFFFF"><strong>Year-5</strong></td>
															<td ng-repeat="row in summaryDetail" class="tdbgColor" ng-if="row.recordType== 1  && !row.year6BR==0" Strong align="left" bgcolor="#CCFFFF"><strong>Year-6</strong></td>
															<td ng-repeat="row in summaryDetail" class="tdbgColor" ng-if="row.recordType== 1 && !row.year7BR==0" Strong align="left" bgcolor="#CCFFFF"><strong>Year-7</strong></td>
															<td ng-repeat="row in summaryDetail" class="tdbgColor" ng-if="row.recordType== 1 && !row.year8BR==0"Strong align="left" bgcolor="#CCFFFF"><strong>Year-8</strong></td>
															<td ng-repeat="row in summaryDetail" class="tdbgColor" ng-if="row.recordType== 1 && !row.year9BR==0" Strong align="left" bgcolor="#CCFFFF"><strong>Year-9</strong></td>
															<td ng-repeat="row in summaryDetail" class="tdbgColor" ng-if="row.recordType== 1 && !row.year10BR==0" Strong align="left" bgcolor="#CCFFFF"><strong>Year-10</strong></td>
															
															<td ng-repeat="row in summaryDetail" class="tdbgColor" ng-if="row.recordType == 1 && !row.averageBR==0" Strong align="left" bgcolor="#CCFFFF"><B>Sum Total (Average)</B></td>
														</tr>
														<tr>
															<th style="text-align: left;" bgcolor="#CCFFFF">Blended Rate/Hr</th>
															<td  ng-repeat="row in summaryDetail" class="thWidth18Per" ng-if="row.recordType== 1  && !row.year1BR==0"> {{row.year1BR | number :2}}</td>
															<td  ng-repeat="row in summaryDetail" class="thWidth18Per" ng-if="row.recordType== 1 && !row.year2BR==0"> {{row.year2BR | number :2}}</td>
															<td  ng-repeat="row in summaryDetail" class="thWidth18Per" ng-if="row.recordType== 1 && !row.year3BR==0"> {{row.year3BR | number :2}}</td>
															<td  ng-repeat="row in summaryDetail" class="thWidth18Per" ng-if="row.recordType== 1 && !row.year4BR==0"> {{row.year4BR | number :2}}</td>
															<td  ng-repeat="row in summaryDetail" class="thWidth18Per" ng-if="row.recordType== 1 && !row.year5BR==0"> {{row.year5BR | number :2}}</td>
															<td  ng-repeat="row in summaryDetail" class="thWidth18Per" ng-if="row.recordType== 1  && !row.year6BR==0"> {{row.year6BR | number :2}}</td>
															<td  ng-repeat="row in summaryDetail" class="thWidth18Per" ng-if="row.recordType== 1 && !row.year7BR==0"> {{row.year7BR | number :2}}</td>
															<td  ng-repeat="row in summaryDetail" class="thWidth18Per" ng-if="row.recordType== 1 && !row.year8BR==0"> {{row.year8BR | number :2}}</td>
															<td  ng-repeat="row in summaryDetail" class="thWidth18Per" ng-if="row.recordType== 1 && !row.year9BR==0"> {{row.year9BR | number :2}}</td>
															<td  ng-repeat="row in summaryDetail" class="thWidth18Per" ng-if="row.recordType== 1 && !row.year10BR==0"> {{row.year10BR | number :2}}</td>
															<td  ng-repeat="row in summaryDetail" class="thWidth18Per"  ng-if="row.recordType == 1 && !row.averageBR==0"> {{row.averageBR | number :2}}</td>
														</tr>
														<tr>	
															<th style="text-align: left;"  bgcolor="#CCFFFF">Blended Cost/Hr</th>
															<td  ng-repeat="row in summaryDetail" class="thWidth18Per" ng-if="row.recordType== 1  && !row.year1BC==0"> {{row.year1BC | number :2}}</td>
															<td  ng-repeat="row in summaryDetail" class="thWidth18Per" ng-if="row.recordType== 1 && !row.year2BC==0"> {{row.year2BC | number :2}}</td>
															<td  ng-repeat="row in summaryDetail" class="thWidth18Per" ng-if="row.recordType== 1 && !row.year3BC==0"> {{row.year3BC | number :2}}</td>
															<td  ng-repeat="row in summaryDetail" class="thWidth18Per" ng-if="row.recordType== 1 && !row.year4BC==0"> {{row.year4BC | number :2}}</td>
															<td  ng-repeat="row in summaryDetail" class="thWidth18Per" ng-if="row.recordType== 1 && !row.year5BC==0"> {{row.year5BC | number :2}}</td>
															<td  ng-repeat="row in summaryDetail" class="thWidth18Per" ng-if="row.recordType== 1  && !row.year6BC==0"> {{row.year6BC | number :2}}</td>
															<td  ng-repeat="row in summaryDetail" class="thWidth18Per" ng-if="row.recordType== 1 && !row.year7BC==0"> {{row.year7BC | number :2}}</td>
															<td  ng-repeat="row in summaryDetail" class="thWidth18Per" ng-if="row.recordType== 1 && !row.year8BC==0"> {{row.year8BC | number :2}}</td>
															<td  ng-repeat="row in summaryDetail" class="thWidth18Per" ng-if="row.recordType== 1 && !row.year9BC==0"> {{row.year9BC | number :2}}</td>
															<td  ng-repeat="row in summaryDetail" class="thWidth18Per" ng-if="row.recordType== 1 && !row.year10BC==0"> {{row.year10BC | number :2}}</td>
															
															<td  ng-repeat="row in summaryDetail" class="thWidth18Per"  ng-if="row.recordType == 1 && !row.averageBC==0"> {{row.averageBC | number :2}}</td>
														</tr>	
														<tr>
															<th style="text-align: left;"  bgcolor="#CCFFFF">PM%</th>
															<td  ng-repeat="row in summaryDetail" class="thWidth18Per" ng-if="row.recordType== 1  && !row.year1beforeGM==0"> {{row.year1beforeGM | number :2}}</td>
															<td  ng-repeat="row in summaryDetail" class="thWidth18Per" ng-if="row.recordType== 1 && !row.year2beforeGM==0"> {{row.year2beforeGM | number :2}}</td>
															<td  ng-repeat="row in summaryDetail" class="thWidth18Per" ng-if="row.recordType== 1 && !row.year3beforeGM==0"> {{row.year3beforeGM | number :2}}</td>
															<td  ng-repeat="row in summaryDetail" class="thWidth18Per" ng-if="row.recordType== 1 && !row.year4beforeGM==0"> {{row.year4beforeGM | number :2}}</td>
															<td  ng-repeat="row in summaryDetail" class="thWidth18Per" ng-if="row.recordType== 1 && !row.year5beforeGM==0"> {{row.year5beforeGM | number :2}}</td>
															<td  ng-repeat="row in summaryDetail" class="thWidth18Per" ng-if="row.recordType== 1  && !row.year6beforeGM==0"> {{row.year6beforeGM | number :2}}</td>
															<td  ng-repeat="row in summaryDetail" class="thWidth18Per" ng-if="row.recordType== 1 && !row.year7beforeGM==0"> {{row.year7beforeGM | number :2}}</td>
															<td  ng-repeat="row in summaryDetail" class="thWidth18Per" ng-if="row.recordType== 1 && !row.year8beforeGM==0"> {{row.year8beforeGM | number :2}}</td>
															<td  ng-repeat="row in summaryDetail" class="thWidth18Per" ng-if="row.recordType== 1 && !row.year9beforeGM==0"> {{row.year9beforeGM | number :2}}</td>
															<td  ng-repeat="row in summaryDetail" class="thWidth18Per" ng-if="row.recordType== 1 && !row.year10beforeGM==0"> {{row.year10beforeGM | number :2}}</td>
															<td  ng-repeat="row in summaryDetail" class="thWidth18Per"  ng-if="row.recordType == 1 && !row.averagebeforeGM==0"> {{row.averagebeforeGM | number :2}}</td>
														</tr>
														<tr>	
															<th style="text-align: left;"  bgcolor="#CCFFFF">Discount %</th>
															<td ng-repeat="row in summaryDetail" class="thWidth18Per" ng-if="row.recordType== 1  && !row.year1BR==0" Strong>{{row.volumeDiscount}}</td>
															<td ng-repeat="row in summaryDetail" class="thWidth18Per" ng-if="row.recordType== 1 && !row.year2BR==0" Strong>{{row.volumeDiscount}}</td>
															<td ng-repeat="row in summaryDetail" class="thWidth18Per" ng-if="row.recordType== 1 && !row.year3BR==0"Strong>{{row.volumeDiscount}}</td>
															<td ng-repeat="row in summaryDetail" class="thWidth18Per" ng-if="row.recordType== 1 && !row.year4BR==0" Strong>{{row.volumeDiscount}}</td>
															<td ng-repeat="row in summaryDetail" class="thWidth18Per" ng-if="row.recordType== 1 && !row.year5BR==0" Strong>{{row.volumeDiscount}}</td>
															<td ng-repeat="row in summaryDetail" class="thWidth18Per" ng-if="row.recordType== 1  && !row.year6BR==0" Strong>{{row.volumeDiscount}}</td>
															<td ng-repeat="row in summaryDetail" class="thWidth18Per" ng-if="row.recordType== 1 && !row.year7BR==0" Strong>{{row.volumeDiscount}}</td>
															<td ng-repeat="row in summaryDetail" class="thWidth18Per" ng-if="row.recordType== 1 && !row.year8BR==0"Strong>{{row.volumeDiscount}}</td>
															<td ng-repeat="row in summaryDetail" class="thWidth18Per" ng-if="row.recordType== 1 && !row.year9BR==0" Strong>{{row.volumeDiscount}}</td>
															<td ng-repeat="row in summaryDetail" class="thWidth18Per" ng-if="row.recordType== 1 && !row.year10BR==0" Strong>{{row.volumeDiscount}}</td>
															
															<!-- <td  ng-repeat ="row in summaryDetail" class="thWidth18Per" ng-if="row.recordType== 0"> {{row.volumeDiscount}}</td> -->
															<td  ng-repeat ="row in summaryDetail" class="thWidth18Per" ng-if="row.recordType== 1"> {{row.avgVolumeDiscount}}</td>	
														</tr>
														<tr>
															<th style="text-align: left;"  bgcolor="#CCFFFF">PM% Post Discount</th>
															<td  ng-repeat="row in summaryDetail" class="thWidth18Per" ng-if="row.recordType== 1  && !row.year1GM==0"> {{row.year1GM | number :2}}</td>
															<td  ng-repeat="row in summaryDetail" class="thWidth18Per" ng-if="row.recordType== 1 && !row.year2GM==0"> {{row.year2GM | number :2}}</td>
															<td  ng-repeat="row in summaryDetail" class="thWidth18Per" ng-if="row.recordType== 1 && !row.year3GM==0"> {{row.year3GM | number :2}}</td>
															<td  ng-repeat="row in summaryDetail" class="thWidth18Per" ng-if="row.recordType== 1 && !row.year4GM==0"> {{row.year4GM | number :2}}</td>
															<td  ng-repeat="row in summaryDetail" class="thWidth18Per" ng-if="row.recordType== 1 && !row.year5GM==0"> {{row.year5GM | number :2}}</td>
															<td  ng-repeat="row in summaryDetail" class="thWidth18Per" ng-if="row.recordType== 1  && !row.year6GM==0"> {{row.year6GM | number :2}}</td>
															<td  ng-repeat="row in summaryDetail" class="thWidth18Per" ng-if="row.recordType== 1 && !row.year7GM==0"> {{row.year7GM | number :2}}</td>
															<td  ng-repeat="row in summaryDetail" class="thWidth18Per" ng-if="row.recordType== 1 && !row.year8GM==0"> {{row.year8GM | number :2}}</td>
															<td  ng-repeat="row in summaryDetail" class="thWidth18Per" ng-if="row.recordType== 1 && !row.year9GM==0"> {{row.year9GM | number :2}}</td>
															<td  ng-repeat="row in summaryDetail" class="thWidth18Per" ng-if="row.recordType== 1 && !row.year10GM==0"> {{row.year10GM | number :2}}</td>
															
															<td  ng-repeat="row in summaryDetail" class="thWidth18Per"  ng-if="row.recordType == 1 && !row.averageGM==0"> {{row.averageGM | number :2}}</td>
														</tr>
														
														<!-- <tr>
															<th style="text-align: left;"bgcolor="#CCFFFF">Total Risk </br>(FX+Contingency+SLA)</th>
															<td  ng-repeat="row in summaryDetail" class="thWidth18Per" ng-if="row.recordType== 1  && !row.year1beforeMGM==0"> {{row.totalriskamount | number :2}}</td>
															<td  ng-repeat="row in summaryDetail" class="thWidth18Per" ng-if="row.recordType== 1 && !row.year2beforeMGM==0"> {{row.totalriskamount | number :2}}</td>
															<td  ng-repeat="row in summaryDetail" class="thWidth18Per" ng-if="row.recordType== 1 && !row.year3beforeMGM==0"> {{row.totalriskamount | number :2}}</td>
															<td  ng-repeat="row in summaryDetail" class="thWidth18Per" ng-if="row.recordType== 1 && !row.year4beforeMGM==0"> {{row.totalriskamount | number :2}}</td>
															<td  ng-repeat="row in summaryDetail" class="thWidth18Per" ng-if="row.recordType== 1 && !row.year5beforeMGM==0"> {{row.totalriskamount | number :2}}</td>
															<td  ng-repeat="row in summaryDetail" class="thWidth18Per" ng-if="row.recordType== 1  && !row.year6beforeMGM==0"> {{row.totalriskamount | number :2}}</td>
															<td  ng-repeat="row in summaryDetail" class="thWidth18Per" ng-if="row.recordType== 1 && !row.year7beforeMGM==0"> {{row.totalriskamount | number :2}}</td>
															<td  ng-repeat="row in summaryDetail" class="thWidth18Per" ng-if="row.recordType== 1 && !row.year8beforeMGM==0"> {{row.totalriskamount | number :2}}</td>
															<td  ng-repeat="row in summaryDetail" class="thWidth18Per" ng-if="row.recordType== 1 && !row.year9beforeMGM==0"> {{row.totalriskamount | number :2}}</td>
															<td  ng-repeat="row in summaryDetail" class="thWidth18Per" ng-if="row.recordType== 1 && !row.year10beforeMGM==0"> {{row.totalriskamount | number :2}}</td>
															<td  ng-repeat="row in summaryDetail" class="thWidth18Per"  ng-if="row.recordType == 1 && !row.averagebeforeMGM==0"> {{row.totalriskamount | number :2}}</td>
														</tr> -->
														
														
														
														<tr>
															<th style="text-align: left;"bgcolor="#CCFFFF">PM% Incl. Risk and VD.</th>
															<td  ng-repeat="row in summaryDetail" class="thWidth18Per" ng-if="row.recordType== 1  && !row.year1GM==0"> {{row.year1AfterRiskVD| number :2}}</td>
															<td  ng-repeat="row in summaryDetail" class="thWidth18Per" ng-if="row.recordType== 1 && !row.year2GM==0"> {{row.year2AfterRiskVD | number :2}}</td>
															<td  ng-repeat="row in summaryDetail" class="thWidth18Per" ng-if="row.recordType== 1 && !row.year3GM==0"> {{row.year3AfterRiskVD | number :2}}</td>
															<td  ng-repeat="row in summaryDetail" class="thWidth18Per" ng-if="row.recordType== 1 && !row.year4GM==0"> {{row.year4AfterRiskVD | number :2}}</td>
															<td  ng-repeat="row in summaryDetail" class="thWidth18Per" ng-if="row.recordType== 1 && !row.year5GM==0"> {{row.year5AfterRiskVD | number :2}}</td>
															<td  ng-repeat="row in summaryDetail" class="thWidth18Per" ng-if="row.recordType== 1  && !row.year6GM==0"> {{row.year6AfterRiskVD | number :2}}</td>
															<td  ng-repeat="row in summaryDetail" class="thWidth18Per" ng-if="row.recordType== 1 && !row.year7GM==0"> {{row.year7AfterRiskVD | number :2}}</td>
															<td  ng-repeat="row in summaryDetail" class="thWidth18Per" ng-if="row.recordType== 1 && !row.year8GM==0"> {{row.year8AfterRiskVD | number :2}}</td>
															<td  ng-repeat="row in summaryDetail" class="thWidth18Per" ng-if="row.recordType== 1 && !row.year9GM==0"> {{row.year9AfterRiskVD | number :2}}</td>
															<td  ng-repeat="row in summaryDetail" class="thWidth18Per" ng-if="row.recordType== 1 && !row.year10GM==0"> {{row.year10AfterRiskVD | number :2}}</td>
															<td  ng-repeat="row in summaryDetail" class="thWidth18Per"  ng-if="row.recordType == 1 && !row.averageGM==0"> {{row.averageAfterRiskVD | number :2}}</td>
														</tr>
														
														
														<!-- <tr>
															<th style="text-align: left;"  bgcolor="#CCFFFF">RightPrice PM%</th>
															<td  ng-repeat="row in summaryDetail" class="thWidth18Per" ng-if="row.recordType== 1  && !row.year1beforeMGM==0"> {{row.year1beforeMGM | number :2}}</td>
															<td  ng-repeat="row in summaryDetail" class="thWidth18Per" ng-if="row.recordType== 1 && !row.year2beforeMGM==0"> {{row.year2beforeMGM | number :2}}</td>
															<td  ng-repeat="row in summaryDetail" class="thWidth18Per" ng-if="row.recordType== 1 && !row.year3beforeMGM==0"> {{row.year3beforeMGM | number :2}}</td>
															<td  ng-repeat="row in summaryDetail" class="thWidth18Per" ng-if="row.recordType== 1 && !row.year4beforeMGM==0"> {{row.year4beforeMGM | number :2}}</td>
															<td  ng-repeat="row in summaryDetail" class="thWidth18Per" ng-if="row.recordType== 1 && !row.year5beforeMGM==0"> {{row.year5beforeMGM | number :2}}</td>
															<td  ng-repeat="row in summaryDetail" class="thWidth18Per" ng-if="row.recordType== 1  && !row.year6beforeMGM==0"> {{row.year6beforeMGM | number :2}}</td>
															<td  ng-repeat="row in summaryDetail" class="thWidth18Per" ng-if="row.recordType== 1 && !row.year7beforeMGM==0"> {{row.year7beforeMGM | number :2}}</td>
															<td  ng-repeat="row in summaryDetail" class="thWidth18Per" ng-if="row.recordType== 1 && !row.year8beforeMGM==0"> {{row.year8beforeMGM | number :2}}</td>
															<td  ng-repeat="row in summaryDetail" class="thWidth18Per" ng-if="row.recordType== 1 && !row.year9beforeMGM==0"> {{row.year9beforeMGM | number :2}}</td>
															<td  ng-repeat="row in summaryDetail" class="thWidth18Per" ng-if="row.recordType== 1 && !row.year10beforeMGM==0"> {{row.year10beforeMGM | number :2}}</td>
															
															<td  ng-repeat="row in summaryDetail" class="thWidth18Per"  ng-if="row.recordType == 1 && !row.averagebeforeMGM==0"> {{row.averagebeforeMGM | number :2}}</td>
														</tr>
														<tr>
															<th style="text-align: left;"  bgcolor="#CCFFFF">RightPrice PM% </br>Post Discount</th>
															<td  ng-repeat="row in summaryDetail" class="thWidth18Per" ng-if="row.recordType== 1  && !row.year1MGM==0"> {{row.year1MGM | number :2}}</td>
															<td  ng-repeat="row in summaryDetail" class="thWidth18Per" ng-if="row.recordType== 1 && !row.year2MGM==0"> {{row.year2MGM | number :2}}</td>
															<td  ng-repeat="row in summaryDetail" class="thWidth18Per" ng-if="row.recordType== 1 && !row.year3MGM==0"> {{row.year3MGM | number :2}}</td>
															<td  ng-repeat="row in summaryDetail" class="thWidth18Per" ng-if="row.recordType== 1 && !row.year4MGM==0"> {{row.year4MGM | number :2}}</td>
															<td  ng-repeat="row in summaryDetail" class="thWidth18Per" ng-if="row.recordType== 1 && !row.year5MGM==0"> {{row.year5MGM | number :2}}</td>
															<td  ng-repeat="row in summaryDetail" class="thWidth18Per" ng-if="row.recordType== 1  && !row.year6MGM==0"> {{row.year6MGM | number :2}}</td>
															<td  ng-repeat="row in summaryDetail" class="thWidth18Per" ng-if="row.recordType== 1 && !row.year7MGM==0"> {{row.year7MGM | number :2}}</td>
															<td  ng-repeat="row in summaryDetail" class="thWidth18Per" ng-if="row.recordType== 1 && !row.year8MGM==0"> {{row.year8MGM | number :2}}</td>
															<td  ng-repeat="row in summaryDetail" class="thWidth18Per" ng-if="row.recordType== 1 && !row.year9MGM==0"> {{row.year9MGM | number :2}}</td>
															<td  ng-repeat="row in summaryDetail" class="thWidth18Per" ng-if="row.recordType== 1 && !row.year10MGM==0"> {{row.year10MGM | number :2}}</td>
															<td  ng-repeat="row in summaryDetail" class="thWidth18Per"  ng-if="row.recordType == 1 && !row.averageMGM==0"> {{row.averageMGM | number :2}}</td>
														</tr>
														<tr>
															<th style="text-align: left;"  bgcolor="#CCFFFF">Difference</th>
															<td ng-repeat="row in summaryDetail" class="thWidth18Per" ng-if="row.recordType == 1 && !row.year1GM==0 && !row.year1MGM==0"> {{row.year1GM - row.year1MGM | number :2}}</td>
															<td ng-repeat="row in summaryDetail" class="thWidth18Per" ng-if="row.recordType == 1 && !row.year2GM==0 && !row.year2MGM==0"> {{row.year2GM - row.year2MGM | number :2}}</td>
															<td ng-repeat="row in summaryDetail" class="thWidth18Per" ng-if="row.recordType == 1 && !row.year3GM==0 && !row.year3MGM==0"> {{row.year3GM - row.year3MGM | number :2}}</td>
															<td ng-repeat="row in summaryDetail" class="thWidth18Per" ng-if="row.recordType == 1 && !row.year4GM==0 && !row.year4MGM==0"> {{row.year4GM - row.year4MGM | number :2}}</td>
															<td ng-repeat="row in summaryDetail" class="thWidth18Per" ng-if="row.recordType == 1 && !row.year5GM==0 && !row.year5MGM==0"> {{row.year5GM - row.year5MGM | number :2}}</td>
															<td ng-repeat="row in summaryDetail" class="thWidth18Per" ng-if="row.recordType == 1 && !row.year6GM==0 && !row.year6MGM==0"> {{row.year6GM - row.year6MGM | number :2}}</td>
															<td ng-repeat="row in summaryDetail" class="thWidth18Per" ng-if="row.recordType == 1 && !row.year7GM==0 && !row.year7MGM==0"> {{row.year7GM - row.year7MGM | number :2}}</td>
															<td ng-repeat="row in summaryDetail" class="thWidth18Per" ng-if="row.recordType == 1 && !row.year8GM==0 && !row.year8MGM==0"> {{row.year8GM - row.year8MGM | number :2}}</td>
															<td ng-repeat="row in summaryDetail" class="thWidth18Per" ng-if="row.recordType == 1 && !row.year9GM==0 && !row.year9MGM==0"> {{row.year9GM - row.year9MGM | number :2}}</td>
															<td ng-repeat="row in summaryDetail" class="thWidth18Per" ng-if="row.recordType == 1 && !row.year10GM==0 && !row.year10MGM==0"> {{row.year10GM - row.year105MGM | number :2}}</td>
															<td ng-repeat="row in summaryDetail" class="thWidth18Per" ng-if="row.recordType == 1 && !row.averageGM==0 && !row.averageMGM==0"> {{row.averageGM - row.averageMGM | number :2}}</td>

														</tr> -->
													</tbody>
												</table>
												<table></table>
											</div>
										</div>
									</div>
								 </div>
                            </div>
                        </div>
                    </div>
                    
                    <div class="table-responsive" ng-If="multiCountryTable == true && tableIT == true">
     						 <div class="panel-heading panelHeadingStyle">
        							 <div class="row ">
        							 	<table class="table table-bordered bordered table-striped table-condensed datatable "
												id="tblRateCardSummaryPer" ui-jq="dataTable"
												ui-options="dataTableOpt" border="1">
												<tr>
														<td align="center" bgcolor="#CCFFFF" colspan="19"><Strong>
																Rate Card Summary</Strong></td>
													</tr>
												</table>
												<table></table>
									 </div>
      				</div> 
       				<div class="divEmptyThrice"></div>
									 <table> <tr><td colspan="19" style="color: red;"> <Strong>Note: All the rates are in Billing Currency - {{currencyName}} </Strong></td></tr></table>
									<table></table> 
	<div class="divEmptyThrice"></div>
	<div class="table-responsive">
      <table class="table table-bordered bordered table-striped table-condensed datatable " id="tblRateCardSummaryPer" ui-jq="dataTable" ui-options="dataTableOpt" border="1" >
      <thead>
              <tr ng-if="yoytableDisplay == true">
          <th class="thWidth15PerSum tdpercAlignCenterper tdRightBorder" align="left" bgcolor="#CCFFFF" colspan="3">Onsite YOY Rate Increase %</th>
          <th class="colRightSection" bgcolor="#9d9d9d"></th>
          <th  ng-repeat="yoy in yoyIncrement" ng-if="yoy.stepYear == 1" class="colRightSection" bgcolor="#9d9d9d" align="Right">{{yoy.incrementPercentOnsite | number :2}}%</th>
          <th  ng-repeat="yoy in yoyIncrement" ng-if="yoy.stepYear == 2" class="colRightSection" bgcolor="#9d9d9d" align="Right">{{yoy.incrementPercentOnsite | number :2}}%</th>
          <th  ng-repeat="yoy in yoyIncrement" ng-if="yoy.stepYear == 3" class="colRightSection" bgcolor="#9d9d9d" align="Right">{{yoy.incrementPercentOnsite | number :2}}%</th>
          <th  ng-repeat="yoy in yoyIncrement" ng-if="yoy.stepYear == 4" class="colRightSection" bgcolor="#9d9d9d" align="Right">{{yoy.incrementPercentOnsite | number :2}}%</th> 
          <th  ng-repeat="yoy in yoyIncrement" ng-if="yoy.stepYear == 5" class="colRightSection" bgcolor="#9d9d9d" align="Right">{{yoy.incrementPercentOnsite | number :2}}%</th>
          <th  ng-repeat="yoy in yoyIncrement" ng-if="yoy.stepYear == 6" class="colRightSection" bgcolor="#9d9d9d" align="Right">{{yoy.incrementPercentOnsite | number :2}}%</th>
          <th  ng-repeat="yoy in yoyIncrement" ng-if="yoy.stepYear == 7" class="colRightSection" bgcolor="#9d9d9d" align="Right">{{yoy.incrementPercentOnsite | number :2}}%</th>
          <th  ng-repeat="yoy in yoyIncrement" ng-if="yoy.stepYear == 8" class="colRightSection" bgcolor="#9d9d9d" align="Right">{{yoy.incrementPercentOnsite | number :2}}%</th>
          <th  ng-repeat="yoy in yoyIncrement" ng-if="yoy.stepYear == 9"class="colRightSection"  bgcolor="#9d9d9d" align="Right">{{yoy.incrementPercentOnsite | number :2}}%</th>
          <th  ng-repeat="yoy in yoyIncrement" ng-if="yoy.stepYear == 10"class="colRightSection" bgcolor="#9d9d9d" align="Right">{{yoy.incrementPercentOnsite | number :2}}%</th>
             
          </tr>
          <tr ng-if="yoytableDisplay == true">
          <th class="thWidth15PerSum tdpercAlignCenterper tdRightBorder" align="left" bgcolor="#CCFFFF" colspan="3">Offshore YOY Rate Increase %</th>
          <th class="colRightSection" bgcolor="#9d9d9d" ></th>
          <th  ng-repeat="yoy in yoyIncrement" ng-if="yoy.stepYear == 1"class="colRightSection" bgcolor="#9d9d9d" align="Right">{{yoy.incrementPercentOffshore | number :2}}%</th>
          <th  ng-repeat="yoy in yoyIncrement" ng-if="yoy.stepYear == 2"class="colRightSection" bgcolor="#9d9d9d" align="Right">{{yoy.incrementPercentOffshore | number :2}}%</th>
          <th  ng-repeat="yoy in yoyIncrement" ng-if="yoy.stepYear == 3"class="colRightSection" bgcolor="#9d9d9d" align="Right">{{yoy.incrementPercentOffshore | number :2}}%</th>
          <th  ng-repeat="yoy in yoyIncrement" ng-if="yoy.stepYear == 4"class="colRightSection" bgcolor="#9d9d9d" align="Right">{{yoy.incrementPercentOffshore | number :2}}%</th>
          <th  ng-repeat="yoy in yoyIncrement" ng-if="yoy.stepYear == 5"class="colRightSection" bgcolor="#9d9d9d" align="Right">{{yoy.incrementPercentOffshore | number :2}}%</th>
          <th  ng-repeat="yoy in yoyIncrement" ng-if="yoy.stepYear == 6"class="colRightSection" bgcolor="#9d9d9d" align="Right">{{yoy.incrementPercentOffshore | number :2}}%</th>
          <th  ng-repeat="yoy in yoyIncrement" ng-if="yoy.stepYear == 7"class="colRightSection" bgcolor="#9d9d9d" align="Right">{{yoy.incrementPercentOffshore | number :2}}%</th>
          <th  ng-repeat="yoy in yoyIncrement" ng-if="yoy.stepYear == 8"class="colRightSection" bgcolor="#9d9d9d" align="Right">{{yoy.incrementPercentOffshore | number :2}}%</th>
          <th  ng-repeat="yoy in yoyIncrement" ng-if="yoy.stepYear == 9"class="colRightSection" bgcolor="#9d9d9d" align="Right">{{yoy.incrementPercentOffshore | number :2}}%</th>
          <th  ng-repeat="yoy in yoyIncrement" ng-if="yoy.stepYear == 10"class="colRightSection" bgcolor="#9d9d9d" align="Right">{{yoy.incrementPercentOffshore | number :2}}%</th>
          </tr>
       </thead>
       </table>
      <table class="table table-bordered bordered table-striped table-condensed datatable " id="tblRateCardSummaryPer" ui-jq="dataTable" ui-options="dataTableOpt" border="1">
      <thead>
        <tr>
        <th scope="col" align="left" bgcolor="#CCFFFF" colspan="3">Summarized Blended Margins:</th>
			<td class="tdAlignCenterper tdRightBorder" align="left" bgcolor="#CCFFFF"><strong>Year-1</strong></td>
            <td class="tdAlignCenterper tdRightBorder" align="left" bgcolor="#CCFFFF"><strong>Year-2</strong></td>
            <td class="tdAlignCenterper tdRightBorder" align="left" bgcolor="#CCFFFF"><strong>Year-3</strong></td>
           <td class="tdAlignCenterper tdRightBorder" align="left" bgcolor="#CCFFFF"><strong>Year-4</strong></td>
           <td class="tdAlignCenterper tdRightBorder" align="left" bgcolor="#CCFFFF"><strong>Year-5</strong></td>
           <td class="tdAlignCenterper tdRightBorder" align="left" bgcolor="#CCFFFF"><strong>Year-6</strong></td>
           <td class="tdAlignCenterper tdRightBorder" align="left" bgcolor="#CCFFFF"><strong>Year-7</strong></td>
           <td class="tdAlignCenterper tdRightBorder" align="left" bgcolor="#CCFFFF"><strong>Year-8</strong></td>
           <td class="tdAlignCenterper tdRightBorder" align="left" bgcolor="#CCFFFF"><strong>Year-9</strong></td>
           <td class="tdAlignCenterper tdRightBorder" align="left" bgcolor="#CCFFFF"><strong>Year-10</strong></td>
              											
		  <!--<td ng-repeat="row in summaryDetail" class="tdbgColor" ng-if="row.summaryType == 2 && frmRateCardCreation.ddlCountryModel == null" Strong>{{row.monthYearHeader | headerFilter : "," : " - "}}</td> -->
          <th rowspan=2 class="tdAlignCenterper tdRightBorder" align="left" bgcolor="#CCFFFF" style="vertical-align:middle;">Average Blended Rate</th>
           <td class="tdAlignCenterper tdRightBorder" align="left" bgcolor="#CCFFFF"><strong>Year-1</strong></td>
           <td class="tdAlignCenterper tdRightBorder" align="left" bgcolor="#CCFFFF"><strong>Year-2</strong></td>
           <td class="tdAlignCenterper tdRightBorder" align="left" bgcolor="#CCFFFF"><strong>Year-3</strong></td>
           <td class="tdAlignCenterper tdRightBorder" align="left" bgcolor="#CCFFFF"><strong>Year-4</strong></td>
           <td class="tdAlignCenterper tdRightBorder" align="left" bgcolor="#CCFFFF"><strong>Year-5</strong></td>
           <td class="tdAlignCenterper tdRightBorder" align="left" bgcolor="#CCFFFF"><strong>Year-6</strong></td>
           <td class="tdAlignCenterper tdRightBorder" align="left" bgcolor="#CCFFFF"><strong>Year-7</strong></td>
           <td class="tdAlignCenterper tdRightBorder" align="left" bgcolor="#CCFFFF"><strong>Year-8</strong></td>
           <td class="tdAlignCenterper tdRightBorder" align="left" bgcolor="#CCFFFF"><strong>Year-9</strong></td>
           <td class="tdAlignCenterper tdRightBorder" align="left" bgcolor="#CCFFFF"><strong>Year-10</strong></td>
           
          <!-- <td ng-repeat="row in summaryDetail" class="tdbgColor" ng-if="row.summaryType == 2 && frmRateCardCreation.ddlCountryModel == null" Strong>{{row.monthYearHeader | headerFilter : "," : " - "}}</td> -->
          <th rowspan=2 class="tdAlignCenterper tdRightBorder" style="vertical-align:middle;" align="left" bgcolor="#CCFFFF">Average PM %</th>     
          <th rowspan=2 class="tdAlignCenterper tdRightBorder" style="vertical-align:middle;" align="left" bgcolor="#CCFFFF">Volume Discount</th>       
          <th rowspan=2 class="tdAlignCenterper tdRightBorder" style="vertical-align:middle;" align="left" bgcolor="#CCFFFF">Average PM % Post VR</th>       
         <!--  <th rowspan=2 class="tdAlignCenterper tdRightBorder" style="vertical-align:middle;" align="left" bgcolor="#CCFFFF">RightPrice PM % After VR</th>       
          <th rowspan=2 class="tdAlignCenterper tdRightBorder" style="vertical-align:middle;" align="left" bgcolor="#CCFFFF">Diff</th>
           <th rowspan=2 class="tdAlignCenterper tdRightBorder" style="vertical-align:middle;" align="left" bgcolor="#CCFFFF">Total Risk </br>(FX+Contingency+SLA)</th> -->
           <th rowspan=2 class="tdAlignCenterper tdRightBorder" style="vertical-align:middle;" align="left" bgcolor="#CCFFFF">PM% Incl. Risk and VD.</th>
                                   
        </tr>
        <tr class="tdheight3px">
        <th align="left" bgcolor="#CCFFFF">Sr.</th>
        <th align="left" bgcolor="#CCFFFF">Location</th>
        <th align="left" bgcolor="#CCFFFF">Utilization Mix</th>
        <th colspan=10 class="tdAlignCenter tdRightBorder" align="left" bgcolor="#CCFFFF" style="vertical-align:middle;">Blended Rate</th>
        <th colspan=10 class="tdAlignCenter tdRightBorder"  align="left" bgcolor="#CCFFFF" style="vertical-align:middle;">PM %</th>
        </tr>
      </thead>	
        <tbody id="tBodyRoleUtilization" >
									<tr ng-repeat="rateData in summaryDetail"  ng-if="rateData.recordType == 0">
										<!-- <td class="grayBgColor colSection firstColLeftAlign tooltip1 tdWordBreak">{{rateData.MasterRoleId}}
										 <span class="tooltiptext">{{rateData.RoleDescLong}}</span>
										</td> -->
										<td class="colRightSection">{{$index+1}}
										</td>
										<td class="colRightSection"><span ng-if="!rateData.rateCardProcessingType">{{rateData.location}}</span>
										<span class="colRightSection" ng-if="rateData.rateCardProcessingType == 'M'">{{rateData.location}} (M) </span></td>
										<td class="colRightSection">{{rateData.utilizationMix | number :2}}%</td>	
										<td class="colRightSection"><span ng-if="!rateData.year1BR == 0">{{rateData.year1BR | number :2}}</span>
										<span class="colmiddleempty" ng-if="rateData.year1BR == 0 || rateData.year1BR == null"> - </span></td>
										<td class="colRightSection"><span ng-if="!rateData.year2BR == 0">{{rateData.year2BR | number :2}}</span>        
        								<span class="colmiddleempty" ng-if="rateData.year2BR == 0 || rateData.year2BR == null"> - </span></td>
										<td class="colRightSection"><span ng-if="!rateData.year3BR == 0">{{rateData.year3BR | number :2}}</span>        
        								<span class="colmiddleempty" ng-if="rateData.year3BR == 0 || rateData.year3BR == null"> - </span></td>
										<td class="colRightSection"><span ng-if="!rateData.year4BR == 0">{{rateData.year4BR | number :2}}</span>        
        								<span class="colmiddleempty" ng-if="rateData.year4BR == 0 || rateData.year4BR == null"> - </span></td>
										<td class="colRightSection"><span ng-if="!rateData.year5BR == 0">{{rateData.year5BR | number :2}}</span>        
        								<span class="colmiddleempty" ng-if="rateData.year5BR == 0 || rateData.year5BR == null"> - </span></td>
        								<td class="colRightSection"><span ng-if="!rateData.year6BR == 0">{{rateData.year6BR | number :2}}</span>        
        								<span class="colmiddleempty" ng-if="rateData.year6BR == 0 || rateData.year6BR == null"> - </span></td>
        								<td class="colRightSection"><span ng-if="!rateData.year7BR == 0">{{rateData.year7BR | number :2}}</span>        
        								<span class="colmiddleempty" ng-if="rateData.year7BR == 0 || rateData.year7BR == null"> - </span></td>
        								<td class="colRightSection"><span ng-if="!rateData.year8BR == 0">{{rateData.year8BR | number :2}}</span>        
        								<span class="colmiddleempty" ng-if="rateData.year8BR == 0 || rateData.year8BR == null"> - </span></td>
        								<td class="colRightSection"><span ng-if="!rateData.year9BR == 0">{{rateData.year9BR | number :2}}</span>        
        								<span class="colmiddleempty" ng-if="rateData.year9BR == 0 || rateData.year9BR == null"> - </span></td>
        								<td class="colRightSection"><span ng-if="!rateData.year10BR == 0">{{rateData.year10BR | number :2}}</span>        
        								<span class="colmiddleempty" ng-if="rateData.year10BR == 0 || rateData.year10BR == null"> - </span></td>
        								
        								
        								<td class="colRightSection"><span ng-if="!rateData.averageBR == 0">{{rateData.averageBR | number :2}}</span>        
        								<span class="colmiddleempty" ng-if="rateData.averageBR == 0 || rateData.averageBR == null"> - </span></td> 
										<td class="colRightSection"><span ng-if="!rateData.year1beforeGM == 0">{{rateData.year1beforeGM | number :2}}%</span>
										<span class="colmiddleempty" ng-if="rateData.year1beforeGM == 0 || rateData.year1beforeGM == null"> - </span></td>
										<td class="colRightSection"><span ng-if="!rateData.year2beforeGM == 0">{{rateData.year2beforeGM | number :2}}%</span>        
        								<span class="colmiddleempty" ng-if="rateData.year2beforeGM == 0 || rateData.year2beforeGM == null"> - </span></td>
										<td class="colRightSection"><span ng-if="!rateData.year3beforeGM == 0">{{rateData.year3beforeGM | number :2}}%</span>        
        								<span class="colmiddleempty" ng-if="rateData.year3beforeGM == 0 || rateData.year3beforeGM == null"> - </span></td>
										<td class="colRightSection"><span ng-if="!rateData.year4beforeGM == 0">{{rateData.year4beforeGM | number :2}}%</span>        
        								<span class="colmiddleempty" ng-if="rateData.year4beforeGM == 0 || rateData.year4beforeGM == null" > - </span></td>
        								<td class="colRightSection"><span ng-if="!rateData.year5beforeGM == 0">{{rateData.year5beforeGM | number :2}}%</span>        
        								<span class="colmiddleempty" ng-if="rateData.year5beforeGM == 0 || rateData.year5beforeGM == null" > - </span></td>
        								<td class="colRightSection"><span ng-if="!rateData.year6beforeGM == 0">{{rateData.year6beforeGM | number :2}}%</span>        
        								<span class="colmiddleempty" ng-if="rateData.year6beforeGM == 0 || rateData.year6beforeGM == null" > - </span></td>
        								<td class="colRightSection"><span ng-if="!rateData.year7beforeGM == 0">{{rateData.year7beforeGM | number :2}}%</span>        
        								<span class="colmiddleempty" ng-if="rateData.year7beforeGM == 0 || rateData.year7beforeGM == null" > - </span></td>
        								<td class="colRightSection"><span ng-if="!rateData.year8beforeGM == 0">{{rateData.year8beforeGM | number :2}}%</span>        
        								<span class="colmiddleempty" ng-if="rateData.year8beforeGM == 0 || rateData.year8beforeGM == null" > - </span></td>
        								<td class="colRightSection"><span ng-if="!rateData.year9beforeGM == 0">{{rateData.year9beforeGM | number :2}}%</span>        
        								<span class="colmiddleempty" ng-if="rateData.year9beforeGM == 0 || rateData.year9beforeGM == null" > - </span></td>
        								<td class="colRightSection"><span ng-if="!rateData.year10beforeGM == 0">{{rateData.year10beforeGM | number :2}}%</span>        
        								<span class="colmiddleempty" ng-if="rateData.year10beforeGM == 0 || rateData.year10beforeGM == null" > - </span></td>
        								
        								
										<td class="colRightSection"><span ng-if="!rateData.averagebeforeGM == 0 ">{{rateData.averagebeforeGM | number :2}}%</span>        
        								<span class="colmiddleempty" ng-if="rateData.averagebeforeGM == 0 || rateData.averagebeforeGM == null"> - </span></td>
        								<td ng-repeat ="row in rateCardInfo" class="colRightSection"><span ng-if="!row.volumeDiscount == 0">{{row.volumeDiscount | number :2}}%</span>        
        								<span class="colmiddleempty" ng-if="row.volumeDiscount == 0 || row.volumeDiscount == null"> - </span></td>
        								<td class="colRightSection"><span ng-if="!rateData.averageGM == 0">{{rateData.averageGM | number :2}}%</span>        
        								 <span class="colmiddleempty" ng-if="rateData.averageGM == 0 || rateData.averageGM == null"> - </span></td>
        								<!--<td class="colRightSection">
        								<span ng-if="!rateData.averageMGM == 0">{{rateData.averageMGM | number :2}}%</span>        
        								<span class="colmiddleempty" ng-if="rateData.averageMGM == 0 || rateData.averageMGM == null"> - </span>
        								</td>
										<td class="colRightSection">{{rateData.averageGM - rateData.averageMGM | number :2}}%</td>
										
										<td class="colRightSection">
										<span ng-if="!rateData.year1BR == 0">{{rateData.totalriskamount | number :2}}</span>
										<span class="colmiddleempty" ng-if="rateData.year1BR == 0 || rateData.year1BR == null"> - </span>
										</td> -->
										<td class="colRightSection">
										<span ng-if="!rateData.year1BR == 0">{{rateData.averageAfterRiskVD | number :2}}</span>
										<span class="colmiddleempty" ng-if="rateData.year1BR == 0 || rateData.year1BR == null"> - </span>
										</td>
										
										
										
									</tr>
							
									<tr ng-repeat="rateData in summaryDetail" ng-if="rateData.recordType == 1" style="font-weight:bold;">
										<td class="tdAlignCenter tdSubTotal" colspan="2" ><strong>Consolidated</strong></td>
										<td>{{rateData.utilizationMix | number :2}}%</td>
										<td class="colRightSection"><span ng-if="!rateData.year1BR == 0">{{rateData.year1BR | number :2}}</span>        
        								<span class="colmiddleempty" ng-if="rateData.year1BR == 0"> - </span></td>
        								<td class="colRightSection"><span ng-if="!rateData.year2BR == 0">{{rateData.year2BR | number :2}}</span>        
        								<span class="colmiddleempty" ng-if="rateData.year2BR == 0"> - </span></td>
        								<td class="colRightSection"><span ng-if="!rateData.year3BR == 0">{{rateData.year3BR | number :2}}</span>        
        								<span class="colmiddleempty" ng-if="rateData.year3BR == 0"> - </span></td>
        								<td class="colRightSection"><span ng-if="!rateData.year4BR == 0">{{rateData.year4BR | number :2}}</span>        
        								<span class="colmiddleempty" ng-if="rateData.year4BR == 0"> - </span></td>
        								<td class="colRightSection"><span ng-if="!rateData.year5BR == 0">{{rateData.year5BR | number :2}}</span>        
        								<span class="colmiddleempty" ng-if="rateData.year5BR == 0"> - </span></td>
        									<td class="colRightSection"><span ng-if="!rateData.year6BR == 0">{{rateData.year6BR | number :2}}</span>        
        								<span class="colmiddleempty" ng-if="rateData.year6BR == 0"> - </span></td>
        									<td class="colRightSection"><span ng-if="!rateData.year7BR == 0">{{rateData.year7BR | number :2}}</span>        
        								<span class="colmiddleempty" ng-if="rateData.year7BR == 0"> - </span></td>
        									<td class="colRightSection"><span ng-if="!rateData.year8BR == 0">{{rateData.year8BR | number :2}}</span>        
        								<span class="colmiddleempty" ng-if="rateData.year8BR == 0"> - </span></td>
        									<td class="colRightSection"><span ng-if="!rateData.year9BR == 0">{{rateData.year9BR | number :2}}</span>        
        								<span class="colmiddleempty" ng-if="rateData.year9BR == 0"> - </span></td>
        									<td class="colRightSection"><span ng-if="!rateData.year10BR == 0">{{rateData.year10BR | number :2}}</span>        
        								<span class="colmiddleempty" ng-if="rateData.year10BR == 0"> - </span></td>
        								
        								<td class="colRightSection"><span ng-if="!rateData.year1BR == 0">{{rateData.averageBR | number :2}}</span>        
        								<span class="colmiddleempty" ng-if="rateData.year1BR == 0"> - </span></td>
        								<td class="colRightSection"><span ng-if="!rateData.year1beforeGM == 0">{{rateData.year1beforeGM | number :2}}%</span>        
        								<span class="colmiddleempty"  ng-if="rateData.year1beforeGM == 0"> - </span></td>
        								<td class="colRightSection"><span ng-if="!rateData.year2beforeGM == 0">{{rateData.year2beforeGM | number :2}}%</span>        
        								<span class="colmiddleempty" ng-if="rateData.year2beforeGM == 0"> - </span></td>
        								<td class="colRightSection"><span ng-if="!rateData.year3beforeGM == 0">{{rateData.year3beforeGM | number :2}}%</span>        
        								<span class="colmiddleempty" ng-if="rateData.year3beforeGM == 0"> - </span></td>
        								<td class="colRightSection"><span ng-if="!rateData.year4beforeGM == 0">{{rateData.year4beforeGM | number :2}}%</span>        
        								<span class="colmiddleempty" ng-if="rateData.year4beforeGM == 0"> - </span></td>
        								<td class="colRightSection"><span ng-if="!rateData.year5beforeGM == 0">{{rateData.year5beforeGM | number :2}}%</span>        
        								<span class="colmiddleempty" ng-if="rateData.year5beforeGM == 0"> - </span></td>
        								<td class="colRightSection"><span ng-if="!rateData.year6beforeGM == 0">{{rateData.year6beforeGM | number :2}}%</span>        
        								<span class="colmiddleempty" ng-if="rateData.year6beforeGM == 0"> - </span></td>
        								<td class="colRightSection"><span ng-if="!rateData.year7beforeGM == 0">{{rateData.year7beforeGM | number :2}}%</span>        
        								<span class="colmiddleempty" ng-if="rateData.year7beforeGM == 0"> - </span></td>
        								<td class="colRightSection"><span ng-if="!rateData.year8beforeGM == 0">{{rateData.year8beforeGM | number :2}}%</span>        
        								<span class="colmiddleempty" ng-if="rateData.year8beforeGM == 0"> - </span></td>
        								<td class="colRightSection"><span ng-if="!rateData.year9beforeGM == 0">{{rateData.year9beforeGM | number :2}}%</span>        
        								<span class="colmiddleempty" ng-if="rateData.year9beforeGM == 0"> - </span></td>
        								<td class="colRightSection"><span ng-if="!rateData.year10beforeGM == 0">{{rateData.year10beforeGM | number :2}}%</span>        
        								<span class="colmiddleempty" ng-if="rateData.year10beforeGM == 0"> - </span></td>
        								
        								<td class="colRightSection"><span ng-if="!rateData.averagebeforeGM == 0">{{rateData.averagebeforeGM | number :2}}%</span>        
        								<span class="colmiddleempty" ng-if="rateData.averagebeforeGM == 0"> - </span></td>
        								<td ng-repeat ="row in rateCardInfo" class="colRightSection"><span ng-if="!row.volumeDiscount == 0">{{row.volumeDiscount | number :2}}%</span>        
        								<span class="colmiddleempty" ng-if="row.volumeDiscount == 0"> - </span></td>
        								<td class="colRightSection"><span ng-if="!rateData.averageGM == 0">{{rateData.averageGM | number :2}}%</span>        
        								 <span class="colmiddleempty" ng-if="rateData.averageGM == 0"> - </span></td>
        								<!--<td class="colRightSection"><span ng-if="!rateData.averageMGM == 0">{{rateData.averageMGM | number :2}}%</span>        
        								<span class="colmiddleempty" ng-if="rateData.averageMGM == 0"> - </span></td>
        								<td class="colRightSection">{{rateData.averageGM - rateData.averageMGM | number :2}}%</td>     
        								<td class="colRightSection"><span ng-if="!rateData.year1BR == 0">{{rateData.totalriskamount | number :2}}</span>        
        								<span class="colmiddleempty" ng-if="rateData.year1BR == 0"> - </span></td> -->
        								<td class="colRightSection"><span ng-if="!rateData.year1BR == 0">	{{rateData.averageAfterRiskVD | number :2}}</span>        
        								<span class="colmiddleempty" ng-if="rateData.year1BR == 0"> - </span></td>
        													
									</tr>			
								</tbody>
    </table>
							<table></table>
			  </div>
			    </div>

					
						<div class="divEmptyThrice" ></div>
			  <div class="table-responsive"  ng-If="multiCountryTable == true && tableIT == true">
			  						<table>
							<tr>
								<td colspan="19" style="color: red;"><Strong> M
										indicates Manual </Strong></td>
							</tr>
						</table>
							<table></table>
			     <table class="tdWidth5per table-bordered bordered table-striped table-condensed datatable " id="tblRateCardSummary" ui-jq="dataTable" ui-options="dataTableOpt" border="1";>
								      	<thead>
								      	<tr>
								        <th  align="left" bgcolor="#CCFFFF">FX Rates</th>
								      	</tr>
								      	</thead>
								      	<tbody>
								      	<tr ng-repeat="rateData in trimmedValuefx">
								      	<td class="class="colRightSection"><span>{{rateData}}</span></td>
										</tr>
										
								      	</tbody>
								      </table>
								      <table></table>
			   </div>
			   
			         				<div class="table-responsive" ng-If="multiCountryTable == true && tableKPO == true">
			     						 <div class="panel-heading panelHeadingStyle">
			        							 <div class="row ">
													<table class="table table-bordered bordered table-striped table-condensed datatable "
												id="tblRateCardSummaryPer" ui-jq="dataTable"
												ui-options="dataTableOpt" border="1">
												<tr>
														<td align="center" bgcolor="#CCFFFF" colspan="19"><Strong>
																Rate Card Summary</Strong></td>
													</tr>
												</table>
												<table></table>
												 </div>
			      				</div> 
			       				<div class="divEmptyThrice"></div>
												 <table> <tr><td colspan="19" style="color: red;"> <Strong>Note: All the rates are in Billing Currency - {{currencyName}} </Strong></td></tr></table>
									<table></table> 
				<div class="divEmptyThrice"></div>
				 <div class="table-responsive" >
			      <table class="table table-bordered bordered table-striped table-condensed datatable " id="tblRateCardSummaryPer" ui-jq="dataTable" ui-options="dataTableOpt" border="1">
			      <thead>
			        <tr class="tdheight3px" >
			        <th style="vertical-align:middle;"  align="left" bgcolor="#CCFFFF">Sr.</th>
			        <th style="vertical-align:middle;"  align="left" bgcolor="#CCFFFF">Location</th>
			        <th style="vertical-align:middle;"  align="left" bgcolor="#CCFFFF">Blended Rate</th>
			        <th style="vertical-align:middle;"  align="left" bgcolor="#CCFFFF">Blended Cost</th>
			        <th  style="vertical-align:middle;"  align="left" bgcolor="#CCFFFF">Average PM %</th>     
			        <th  style="vertical-align:middle;"  align="left" bgcolor="#CCFFFF">Average PM % Post VR</th>       
			      </thead>	
			        <tbody id="tBodyRoleUtilization" >
						<tr ng-repeat="rateData in summaryDetail">
							<td class="colRightSection">{{$index+1}}</td>
							<td class="colRightSection"><span ng-if="!rateData.rateCardProcessingType">{{rateData.location}}</span>
								<span class="colRightSection" ng-if="rateData.rateCardProcessingType == 'M'">{{rateData.location}} (M) </span></td>
						
							<td class="colRightSection"><span ng-if="!rateData.year1BR == 0 ">{{rateData.year1BR | number :2}}</span>        
								<span class="colmiddleempty" ng-if="rateData.year1BR == 0 || rateData.year1BR == null"> - </span></td>
							
							<td class="colRightSection"><span ng-if="!rateData.year1BC== 0 ">{{rateData.year1BC | number :2}}</span>        
								<span class="colmiddleempty" ng-if="rateData.year1BC == 0 || rateData.year1BC == null"> - </span></td>
							
							<td class="colRightSection"><span ng-if="!rateData.averagebeforeGM == 0 ">{{rateData.averagebeforeGM | number :2}}%</span>        
								<span class="colmiddleempty" ng-if="rateData.averagebeforeGM == 0 || rateData.averagebeforeGM == null"> - </span></td>
								
							<td class="colRightSection"><span ng-if="!rateData.averageGM == 0">{{rateData.averageGM | number :2}}%</span>        
								<span class="colmiddleempty" ng-if="rateData.averageGM == 0 || rateData.averageGM == null"> - </span></td>
			  	   </tbody>
			    </table>
			    <table></table>
			      </div>
			        </div>

			  <div class="divEmptyThrice" ></div>
			  <div class="table-responsive"  ng-If="multiCountryTable == true && tableKPO == true">
			  			      <table>
							<tr>
								<td colspan="19" style="color: red;"><Strong> M
										indicates Manual </Strong></td>
							</tr>
						</table>
						<table></table>
			 	     <table class="tdWidth5per table-bordered bordered table-striped table-condensed datatable " id="tblRateCardSummary" ui-jq="dataTable" ui-options="dataTableOpt" border="1";>
								      	<thead>
								      	<tr>
								        <th  align="left" bgcolor="#CCFFFF">FX Rates</th>
								      	</tr>
								      	</thead>
								      	<tbody>
								      	<tr ng-repeat="rateData in trimmedValuefx">
								      	<td class="class="colRightSection"><span>{{rateData}}</span></td>
										</tr>
										
								      	</tbody>
								      </table>
								      <table></table>
			   </div>
			 
      
  </div>	<!--  -->
                	 		
	         	
	     	</form>
	     	        </div>
    </div>
    </fieldset>
	<div id="footer" ng-include="'${contextPath}/Portal/Footer.jsp'"></div>
	<div id="Footer" ng-include="'${contextPath}/Portal/TopBottomNavigation.jsp'"></div>
	<div id="loader" ng-include="'${contextPath}/Portal/loader.jsp'"></div>
</body>
</html>
