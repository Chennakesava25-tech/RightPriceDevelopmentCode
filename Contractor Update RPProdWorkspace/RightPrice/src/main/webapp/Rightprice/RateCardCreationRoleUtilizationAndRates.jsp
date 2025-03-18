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
<script src="${contextPath}/resources/js/RightPrice/RateCardCreationRoleUtilizationAndRatesController.js"></script>
<script src="${contextPath}/resources/js/Directives/angularCustomValidations.js"></script>
<script src="${contextPath}/resources/js/Factory/WebServiceFactory.js"></script>

<script>
$(document).ready(function () {
	$('#tblRoleUtilization').on('click', 'a', function() {
	     $('#tblRoleUtilization').find('a').each(function() {
		 	$(this).removeClass('active');
		});
    	$(this).addClass('active');
	});
});

</script>
</head>
<body ng-app="RightPriceApp" ng-controller="RoleUtilizationAndRatesController" >
<div id="includedContent" ng-include="'${contextPath}/Portal/Header.jsp'"></div>
<fieldset ng-disabled="loading || showLoader">
    <div class="container">
        <div class="divEmpty"></div>
        <div class="row marginBottom5px">
            <div class="col-sm-12">
                <h3 class="text-left" id="PageHeading">Rate Card Creation - Role Utilization and Rates</h3>
            </div>
        </div>	
        <div>
            <form class="form-inline" role="form" name="roleUtilizationAndRates" id="roleUtilizationAndRates"  novalidate>
            	<div id="includedRateCardStages" ng-if="autosidebar"
						ng-include="'${contextPath}/Portal/RateCardCompletionStage.jsp'"></div>
				<div id="includedRateCardStages" ng-if="manualsidebar"
						ng-include="'${contextPath}/Portal/RateCardManualCompletionStage.jsp'"></div>
				<div id="includedRateCardStages" ng-if="hybridsidebar"
						ng-include="'${contextPath}/Portal/RateCardHybridCompletionStage.jsp'"></div>
            	<div id="includedRateCardStages" ng-include="'${contextPath}/Rightprice/RateCardCreationInformationTable.jsp'"></div>
				<div class="divEmptyThrice"></div>
				
				<div class="row marginBottom5px">
                 	<label class="control-label col-sm-2 textAlignRight required-Field">Country</label>
                 	<div class="col-sm-3">
             			<select id="ddlCountry" class="form-control" placeholder="Please select" name="ddlCountry"
                         		ng-model="roleUtilizationAndRates.ddlCountryModel" 
                         		ng-class="{true: 'ng-border'}[onSearch && roleUtilizationAndRates.ddlCountryModel.$invalid]"
                         		ng-options="con.countryId as con.countryName for con in country" 
                         		ng-change="getCity(roleUtilizationAndRates.ddlCountryModel);getVisaLabels(roleUtilizationAndRates.ddlCountryModel)" required>
								<option value="" selected disabled>Please select</option>
						</select>
						<div class="error-messages" ng-if="onSearch"
							ng-messages="roleUtilizationAndRates.ddlCountry.$error">
							<em class="error help-block has-error"
								ng-message="required">Please select Country</em>
						</div>
                	</div>	
                 	<label class="control-label col-sm-3 textAlignRight required-Field">City</label>
                  	<div class="col-sm-3">
                    	<select id="ddlCity" class="form-control" placeholder="Please select" name="ddlCity"
                          		ng-model="roleUtilizationAndRates.ddlCityModel" 
                          		ng-class="{true: 'ng-border'}[onSearch && roleUtilizationAndRates.ddlCityModel.$invalid]"
                          		ng-options="ci.cityId as ci.cityName for ci in city" required>
								<option value="" selected disabled>Please select</option>
						</select>
						<div class="error-messages" ng-if="onSearch"
							ng-messages="roleUtilizationAndRates.ddlCity.$error">
							<em class="error help-block has-error"
								ng-message="required">Please select city</em>
						</div>
                	</div>	
				</div>
				<div class="divEmptyThrice"></div>
				<div class="row text-center">
					<div class="col-sm-12">
						<button type="button" class="btn btn-primary btnSpace" id="btnSearch" ng-click="onSearchUtilization(roleUtilizationAndRates);getVisaLabels(roleUtilizationAndRates.ddlCountryModel)">Search</button>
					
					</div>
					<!-- <div>Hello 	{{rateCardInfo[0].expectedOnsiteResourcePercentage}}</div> -->
				</div>
				<br><br>
				<div class="divEmptyThrice"></div>
				<div class="row">
					<!-- <pre>{{selectededRolesArr}}</pre> -->
					<div class="col-sm-12">
						<div class="table-responsive">
							<button type="button" rclass="btn btn-link" class="btn btn-primary btnSpace" ng-click="exportToExcel(false)">
								<img src="${contextPath}/resources/Images/downloadexcel.png" alt="Snow"> Export in Local Currency 
									<span ng-repeat="item in country" ng-if="item.countryId==roleUtilizationAndRates.ddlCountryModel">
										<span >({{item.currencyCode}})</span>
									</span>
							</button>
							<button type="button" rclass="btn btn-link" class="btn btn-primary btnSpace" ng-click="exportToExcel(true)">
								<img src="${contextPath}/resources/Images/downloadexcel.png" alt="Snow"> Export in Billing Currency ({{currencyName}})
							</button>
						</div>
					</div>
				</div>
				<div class="divEmptyThrice"></div>
				<div class="row">
					<!-- <pre>{{selectededRolesArr}}</pre> -->
					<div class="col-sm-12">
						<label><i><font color="red">Note: All rates are in <span ng-model="fetchCurrency"></span> 
							<span ng-repeat="item in country" ng-if="item.countryId==roleUtilizationAndRates.ddlCountryModel"> {{item.currencyCode}} </span>
							</font></i>
						</label>
					</div>
				</div>
				<div class="divEmptyThrice" ></div>
                <div class="row">
        			<div class="col-sm-12">
						<div class="table-responsive" >
							<table  class="table tblRateCardCre table-striped table-hover table-borderless table-condensed" id="tblRoleUtilization">
								<thead>
									<tr>
										<th class="tdWidth5Per"></th>
										<th class="tdWidth5Per"></th>
										<th class="width12per"></th>
										<th class="width12per"></th>
                                    	<th class="colRightSection" colspan="12" >
                                    		<a  ng-repeat = "year in yearList" class="btn btn-default" href="#" style="margin-right: 5px; margin-bottom:5px" ng-click="yearData(year[0])">
                                    			{{year[1] | limitTo:(year[1].length - (year[1].indexOf(","))-1)}}<br>{{year[1].substring(year[1].indexOf(","),year[1].length)| headerFilter : "," : " - "}}
                                   			</a>
                               			</th>
                               				<th class="colRightSection"  ng-hide="yearList">
                                   			<a class="btn btn-default" href="#" tabindex="2"></a>
                               			</th>
                           			</tr>
									<tr class="sectionHeading">
									    <th colspan="3"class="headerBgColor tdRightBorder"></th>
									    <th colspan="7" class="headerBgColorDarkGray tdAlignCenter tdRightBorder">Utilization Onsite</th>
									    <th colspan="6" class="headerBgColor tdAlignCenter">Utilization Offshore</th>
									</tr>
								</thead>
								<tbody>
									<tr class="tlbHeader">
										<th class="firstColLeftAlign rcRoleDesc ">Skill/Role Description</th><!-- Role Utilization and Rates -->
										<!-- <th class="tdCommonRightSec">Syntel Band Grade</th> -->
										<th class="tdCommonRightSec">GCM Level</th>
										<th class="tdCustRole tdRightBorder">Customer Role</th>
										<th class="tdminWidth">Local<br>(%)</th>  <!--Utilization  -->
										<!-- <th class="tdCommonRightSec">Deputed, (H1/WP)(%)</th> -->
										<th class="tdminWidth"> Usage<br>(%)</th><!--Onsite-->
										<th class="tdminWidth">Master Rate</th>
										<th class="tdminWidth">Master Rate PM (%)</th>
										<th class="tdCommonRightSec">Proposed Client Rate</th>
										<th class="tdCommonRightSec">Discount (-) / Premium (+)</th>
										<th class="tdminWidth tdRightBorder">PM (%)</th>
										<th class="thSepBackCol tdminWidth">Usage (%)</th><!-- Offshore -->
										<th class="tdminWidth thSepBackCol">Master Rate</th>
										<th class="tdminWidth thSepBackCol">Master Rate PM (%)</th>
										<th class="tdCommonRightSec thSepBackCol">Proposed Client Rate</th>
										<th class="tdCommonRightSec thSepBackCol">Discount (-) / Premium (+)</th>
										<th class="tdminWidth thSepBackCol">PM (%)</th>
									</tr>
								</tbody>
								<tbody id="tBodyRoleUtilization" >
									<tr ng-repeat="rateData in tableDetails">
										<td class="grayBgColor colSection firstColLeftAlign tooltip1 tdWordBreak">{{rateData.RoleDesc}}
										 <span class="tooltiptext">{{rateData.MasterRoleId}}</span>
										</td>
										<!-- <td class="grayBgColor colSection tdWordBreak">{{rateData.BandGrade}}</td> -->
										<td class="grayBgColor colSection tdWordBreak">{{rateData.gcmCODE}}</td>
										<td class="grayBgColor colSection tdWordBreak tdRightBorder">{{rateData.CustRole}}</td>
										<td class="colRightSection tableInputColor">
										
										<input ng-model="rateData.LocalUti" name="{{'txtUtilizationLocalRow'+'_'+($index+1)}}" type="text" class="form-control" 
											id="{{'txtUtilizationLocalRow'+'_'+($index+1)}}" ng-blur="calculateDeputedMargin($index,rateData.LocalUti);calculateOnsiteGMPercent($index,rateData.OnsitePro);"  ng-disabled= "isOnsiteUtilization || isGermanyRC || isDomestic || isDeputed" 
											ng-pattern="/^[0-9]+(\.[0-9]{1,2})?$/" checklthundredpercente
											ng-class="{true: 'ng-border'}[saved && roleUtilizationAndRates.{{'txtUtilizationLocalRow'+'_'+($index+1)}}.$invalid]">
											<div class="error-messages" ng-if="onSave" ng-messages="roleUtilizationAndRates['txtUtilizationLocalRow'+'_'+($index+1)].$error">
									        	<em class="error help-block has-error" ng-message="pattern">Please enter numbers with maximum of two decimal.</em>
									        	<em class="error help-block has-error" ng-message="checklthundredpercente">Can not be greater than 100.</em>
											</div>
										</td>										
										<!-- <td class="colRightSection"><input ng-model="rateData.DepuUti" name="{{'txtUtilizationDeputedRow'+'_'+($index+1)}}" type="text" class="form-control" id="{{'txtUtilizationDeputedRow'+'_'+($index+1)}}" ng-disabled="true"></td> -->
										
										<td class="colRightSection tableInputColor"><input ng-model="rateData.OnsiteUsa" name="{{'txtOnsiteUsageRow'+'_'+($index+1)}}" type="text" class="form-control" 
										id="{{'txtOnsiteUsageRow'+'_'+($index+1)}}" ng-blur="calculateOnsiteUsageSubTotal($index,rateData.OnsiteUsa)"  ng-disabled= "isOnsiteUtilization" 
										ng-pattern="/^[0-9]+(\.[0-9]{1,2})?$/" checklthundredpercente
										ng-class="{true: 'ng-border'}[saved && roleUtilizationAndRates.{{'txtOnsiteUsageRow'+'_'+($index+1)}}.$invalid]">
											<div class="error-messages" ng-if="onSave" ng-messages="roleUtilizationAndRates['txtOnsiteUsageRow'+'_'+($index+1)].$error">
									        	<em class="error help-block has-error" ng-message="pattern">Please enter numbers with maximum of two decimal.</em>
									        	<em class="error help-block has-error" ng-message="checklthundredpercente">Can not be greater than 100.</em>
											</div>
										</td>
										
										<td class="colRightSection"><input ng-model="rateData.OnsiteMasRate" name="{{'txtOnsiteMasRate'+'_'+($index+1)}}" type="text" class="form-control" id="{{'txtOnsiteMasRate'+'_'+($index+1)}}" ng-disabled="true"></td>
										
										<td class="colRightSection"><input ng-model="rateData.OnsiteMasterRateGM" name="{{'txtOnsiteMasterRateGM'+'_'+($index+1)}}" type="text" class="form-control" id="{{'txtOnsiteMasterRateGM'+'_'+($index+1)}}" ng-disabled="true"  ></td>
										
										<td class="colRightSection tableInputColor"><input ng-model="rateData.OnsitePro" name="{{'txtOnsiteProposedClientRateRow'+'_'+($index+1)}}" type="text" class="form-control" id="{{'txtOnsiteProposedClientRateRow'+'_'+($index+1)}}" ng-blur="calculateDiscount(rateData.OnsitePro,rateData.OnsiteMasRate,$index);calculateOnsiteGMPercent($index,rateData.OnsitePro);"  ng-disabled= "isOnsiteUtilization" decimals="2"></td>
										
										<td class="colRightSection"><input ng-model="rateData.OnsiteDisPremium" name="{{'txtOnsiteDisPremium'+'_'+($index+1)}}" type="text" class="form-control" id="{{'txtOnsiteDisPremium'+'_'+($index+1)}}" ng-disabled="true"></td>
										
										<td class="colRightSection tdRightBorder"><input ng-model="rateData.OnsiteGmPer" name="{{'txtOnsiteGmPer'+'_'+($index+1)}}" type="text" class="form-control" id="{{'txtOnsiteGmPer'+'_'+($index+1)}}" ng-disabled="true"></td>
										
										<td class="colRightSection tableInputColor"><input ng-model="rateData.OffshoreUsa" name="{{'txtOffshoreUsageRow'+'_'+($index+1)}}" type="text" class="form-control" id="{{'txtOffshoreUsageRow'+'_'+($index+1)}}" ng-blur="calculateOffshoreUsageSubTotal($index,rateData.OffshoreUsa)" ng-disabled="isOffshoreUtilization"
											ng-pattern="/^[0-9]+(\.[0-9]{1,2})?$/" checklthundredpercente
											ng-class="{true: 'ng-border'}[saved && roleUtilizationAndRates.{{'txtOffshoreUsageRow'+'_'+($index+1)}}.$invalid]">
											<div class="error-messages" ng-if="onSave" ng-messages="roleUtilizationAndRates['txtOffshoreUsageRow'+'_'+($index+1)].$error">
									        	<em class="error help-block has-error" ng-message="pattern">Please enter numbers with maximum of two decimal.</em>
									        	<em class="error help-block has-error" ng-message="checklthundredpercente">Can not be greater than 100.</em>
											</div>
										</td>
										
										<td class="colRightSection"><input ng-model="rateData.OffshoreMasRate" name="{{'txtOffshoreMasRate'+'_'+($index+1)}}" type="text" class="form-control" id="{{'txtOffshoreMasRate'+'_'+($index+1)}}" ng-disabled="true"></td>
										
										<td class="colRightSection"><input ng-model="rateData.OffshoreMasterRateGM" name="{{'txtOffshoreMasterRateGM'+'_'+($index+1)}}" type="text" class="form-control" id="{{'txtOffshoreMasterRateGM'+'_'+($index+1)}}" ng-disabled="true"></td>
										
										<td class="colRightSection tableInputColor"><input ng-model="rateData.OffshorePro" name="{{'txtOffshoreProposedClientRateRow'+'_'+($index+1)}}" type="text" class="form-control" id="{{'txtOffshoreProposedClientRateRow'+'_'+($index+1)}}" ng-blur="calculateDiscountOffshore(rateData.OffshorePro,rateData.OffshoreMasRate,$index);calculateOffshoreGMPercent($index,rateData.OffshorePro);"  ng-disabled="isOffshoreUtilization" decimals = "2"></td>
										
										<td class="colRightSection"><input ng-model="rateData.OffshoreDisPremium" name="{{'txtOffshoreDisPremium'+'_'+($index+1)}}" type="text" class="form-control" id="{{'txtOffshoreDisPremium'+'_'+($index+1)}}" ng-disabled="true"></td>
										
										<td class="colRightSection"><input ng-model="rateData.OffshoreGmPer" name="{{'txtOffshoreGmPer'+'_'+($index+1)}}" type="text" class="form-control" id="{{'txtOffshoreGmPer'+'_'+($index+1)}}" ng-disabled="true"></td>
									</tr>
									<tr for="roleUtilizationSubmit">
										
										<td class="firstColLeftAlign tdSubTotal" colspan="2"><input type="text" class="form-control inputFontBold" readonly value="Sub Total"></td>
										<!-- <td></td> -->
										<td class="tdRightBorder"></td>
										<td></td>
									<!-- 	<td></td> -->
										<td><input ng-model="roleUtilizationAndRates.totalOnsiteUsage" name="txtTotalOnsiteUsage" type="text" class="form-control" id="txtTotalOnsiteUsage" ng-disabled="true"  checkhundredpercente ng-class="{true: 'ng-border'} [(onSave && isOnsiteUtilization && roleUtilizationAndRates.txtTotalOnsiteUsage.$invalid)]">
										<div class="error-messages" ng-if="onSave && !isOnsiteUtilization" ng-messages="roleUtilizationAndRates.txtTotalOnsiteUsage.$error">
									        	<em class="error help-block has-error" ng-message="checkhundredpercente">Onsite Usage should be 100%</em>
										</div>
										</td>
										<td><input ng-model="roleUtilizationAndRates.totalOnsiteMasterRate" name="txtTotalOnsiteMasterRate" type="text" class="form-control" id="txtTotalOnsiteMasterRate" ng-disabled="true"></td>
										<td><input ng-model="roleUtilizationAndRates.totalOnsiteMasterGMPercent" name="totalOnsiteMasterGM" type="text" class="form-control" id="totalOnsiteMasterGM" ng-disabled="true"></td>
										<td><input ng-model="roleUtilizationAndRates.totalOnsiteProposedRate" name="txtTotalOnsiteProposedRate" type="text" class="form-control" id="txtTotalOnsiteProposedRate" ng-disabled="true"></td>
										<td><input ng-model="roleUtilizationAndRates.totalOnsiteDiscount"  name="txtTotalOnsiteDiscount" type="text" class="form-control" id="txtTotalOnsiteDiscount" ng-disabled="true"></td>
										<td class="tdRightBorder"><input ng-model="roleUtilizationAndRates.totalOnsiteGMPercent" name="txtTotalOnsiteGMPercent" type="text" class="form-control" id="txtTotalOnsiteGMPercent" ng-disabled="true"></td>
										<td><input ng-model="roleUtilizationAndRates.totalOffshoreUsage" name="txtTotalOffshoreUsage" type="text" class="form-control" id="txtTotalOffshoreUsage" ng-disabled="true"  checkoffshorehundredpercente ng-class="{true: 'ng-border'} [(onSave && isOffshoreUtilization && roleUtilizationAndRates.txtTotalOffshoreUsage.$invalid)]">
										<div class="error-messages" ng-if="onSave && !isOffshoreUtilization" ng-messages="roleUtilizationAndRates.txtTotalOffshoreUsage.$error" >
									        	<em class="error help-block has-error" ng-message="checkoffshorehundredpercente">Offshore Usage should be 100%</em>
										</div>
										</td>
										<td><input ng-model="roleUtilizationAndRates.totalOffshoreMasterRate" name="txtTotalOffshoreMasterRate" type="text" class="form-control" id="txtTotalOffshoreMasterRate" ng-disabled="true"></td>
										<td><input ng-model="roleUtilizationAndRates.totalOffshoreMasterGMPercent" name="txtTotalOffshoreMasterGMPercent" type="text" class="form-control" id="txtTotalOffshoreMasterGMPercent" ng-disabled="true"></td>
										<td><input ng-model="roleUtilizationAndRates.totalOffshoreProposedRate" name="txtTotalOffshoreProposedRate" type="text" class="form-control" id="txtTotalOffshoreProposedRate" ng-disabled="true"></td>
										<td><input ng-model="roleUtilizationAndRates.totaloffshoreDiscount" name="totalOffshoreDiscount" type="text" class="form-control" id="totalOffshoreDiscount" ng-disabled="true"></td>
										<td><input ng-model="roleUtilizationAndRates.totalOffshoreGMPercent" name="txtTotalOffshoreGMPercent" type="text" class="form-control" id="txtTotalOffshoreGMPercent" ng-disabled="true"></td>
									</tr>			
								</tbody>
							</table>
						</div>
					</div>
				</div>
				<div class="divEmptyThrice" ></div>
                <div class="row">
        			<div class="col-sm-12">		
						<div class="table-responsive" id="tableToExport" style="display:none;">
							<table border="1" class="table tblBorder tblRateCardCre table-striped table-hover table-borderless table-condensed" id="tblRoleUtilization">
								<thead>
									<tr>
										<th class="tdWidth5Per"></th>
										<th class="tdWidth5Per"></th>
										<th class="tdWidth5Per"></th>
										<th class="tdWidth5Per"></th>
										<th class="width12per"></th>
										<th class="width12per"></th>
										<!-- <th class="colRightSection" colspan="1"></th> -->
                                    	<th class="colRightSection" colspan="{{colspanArray[$index]}}"  ng-repeat = "year in yearList">
                                    		<a class="btn btn-default" href="#" tabindex="1" ng-click="yearData(year[0])">
                                    			{{year[1] | headerFilter : "," : " - "}}
                                   			</a>
                               			</th>
                                   		<th class="colRightSection" colspan="13" ng-hide="yearList">
                                   			<a class="btn btn-default" href="#" tabindex="2"></a>
                               			</th>
                           			</tr>
									<tr class="sectionHeading">
									    <th colspan="5"class="headerBgColor tdRightBorder"></th>
									    <th ng-hide="isOffshoreVisible" colspan="8" class="headerBgColorDarkGray tdAlignCenter tdRightBorder">Utilization Onsite</th>
									    <th ng-hide="isOnsiteVisible" colspan="6" class="headerBgColor tdAlignCenter">Utilization Offshore</th>									    
									</tr>
								</thead>
								<tbody>
									<tr class="tlbHeader">
										<th class="firstColLeftAlign">Sub Practice</th>
										<th class="firstColLeftAlign">Practice</th>
										<th class="firstColLeftAlign rcRoleDesc ">Skill/Role Description</th>
										<!-- <th class="tdCommonRightSec">Syntel Band Grade</th> -->
										<th class="tdCommonRightSec">GCM Level</th>
										<th class="tdCustRole tdRightBorder">Customer Role</th>
										<th ng-hide="isOffshoreVisible" class="tdminWidth">Local (%)</th> <!--  Utilization --> 
										<!-- <th ng-hide="isOffshoreVisible" class="tdCommonRightSec">Deputed, (H1/WP)(%)</th> -->
										<th ng-hide="isOffshoreVisible" class="tdminWidth">Onsite Usage (%)</th><!-- Onsite -->
										<th ng-hide="isOffshoreVisible" class="tdminWidth">Onsite Master Rate</th>
										<th ng-hide="isOffshoreVisible" class="tdminWidth">Onsite Master Rate PM (%)</th>
										<th ng-hide="isOffshoreVisible" class="tdCommonRightSec">Onsite Proposed Client Rate</th>
										<th ng-hide="isOffshoreVisible" class="tdCommonRightSec">Discount (-) / Premium (+)</th>
										<th ng-hide="isOffshoreVisible" class="tdminWidth tdRightBorder">Onsite PM (%)</th>
										
										<th ng-hide="isOnsiteVisible" class="thSepBackCol tdminWidth">Offshore Usage (%)</th><!-- Offshore -->
										<th ng-hide="isOnsiteVisible" class="tdminWidth thSepBackCol">Offshore Master Rate</th>
										<th ng-hide="isOnsiteVisible" class="tdminWidth thSepBackCol">Offshore Master Rate PM (%)</th>
										<th ng-hide="isOnsiteVisible" class="tdCommonRightSec thSepBackCol">Offshore Proposed Client Rate</th>
										<th ng-hide="isOnsiteVisible" class="tdCommonRightSec thSepBackCol">Discount (-) / Premium (+)</th>
										<th ng-hide="isOnsiteVisible" class="tdminWidth thSepBackCol">Offshore PM (%)</th>
									</tr>
								</tbody>
								<tbody id="tBodyRoleUtilization" >
									<tr ng-repeat="rateData in tableDetails">
										<td class="grayBgColor colSection tdWordBreak"><span>{{rateData.SubPracticeName}}</span></td>
										<td class="grayBgColor colSection tdWordBreak"><span>{{rateData.PracticeName}}</span></td>
										<td class="grayBgColor colSection firstColLeftAlign tooltip1 tdWordBreak"><span>{{rateData.RoleDesc}}</span>
										 <span class="tooltiptext">{{rateData.MasterRoleId}}</span></td>
										<!-- <td class="grayBgColor colSection tdWordBreak"><span>{{rateData.BandGrade}}</span></td> -->
										<td class="grayBgColor colSection tdWordBreak"><span>{{rateData.gcmCODE}}</span></td>
										<td class="grayBgColor colSection tdWordBreak tdRightBorder"><span>{{rateData.CustRole}}</span></td>
										<td ng-hide="isOffshoreVisible" class="colRightSection tableInputColor"><span>{{rateData.LocalUti}}</span></td>										
										<!-- <td ng-hide="isOffshoreVisible" class="colRightSection"><span>{{rateData.DepuUti}}</span></td> -->
										<td ng-hide="isOffshoreVisible" class="colRightSection tableInputColor"><span>{{rateData.OnsiteUsa}}</span></td>
										<td ng-hide="isOffshoreVisible" class="colRightSection"><span>{{rateData.OnsiteMasRate}}</span></td>
										<td ng-hide="isOffshoreVisible" class="colRightSection"><span>{{rateData.OnsiteMasterRateGM}}</span></td>
										<td ng-hide="isOffshoreVisible" class="colRightSection tableInputColor"><span>{{rateData.OnsitePro}}</span></td>
										<td ng-hide="isOffshoreVisible" class="colRightSection"><span>{{rateData.OnsiteDisPremium}}</span></td>
										<td ng-hide="isOffshoreVisible" class="colRightSection tdRightBorder"><span>{{rateData.OnsiteGmPer}}</span></td>
										
										<td ng-hide="isOnsiteVisible" class="colRightSection tableInputColor"><span>{{rateData.OffshoreUsa}}</span></td>
										<td ng-hide="isOnsiteVisible" class="colRightSection"><span>{{rateData.OffshoreMasRate}}</span></td>
										<td ng-hide="isOnsiteVisible" class="colRightSection"><span>{{rateData.OffshoreMasterRateGM}}</span></td>
										<td ng-hide="isOnsiteVisible" class="colRightSection tableInputColor"><span>{{rateData.OffshorePro}}</span></td>
										<td ng-hide="isOnsiteVisible" class="colRightSection"><span>{{rateData.OffshoreDisPremium}}</span></td>
										<td ng-hide="isOnsiteVisible" class="colRightSection"><span>{{rateData.OffshoreGmPer}}</span></td>
									</tr>
									<tr for="roleUtilizationSubmit">
										<td class="firstColLeftAlign tdSubTotal" colspan="4">Sub Total</td>
										<td class="tdRightBorder"></td>
										<td ng-hide="isOffshoreVisible"></td>
										<td ng-hide="isOffshoreVisible"></td>
										<td ng-hide="isOffshoreVisible"><span>{{roleUtilizationAndRates.totalOnsiteUsage}}</span></td>
										<td ng-hide="isOffshoreVisible"><span>{{roleUtilizationAndRates.totalOnsiteMasterRate}}</span></td>
										<td ng-hide="isOffshoreVisible">
											<span ng-if="roleUtilizationAndRates.totalOnsiteMasterGMPercent">
												{{roleUtilizationAndRates.totalOnsiteMasterGMPercent}}
											</span>
											<span ng-if="!roleUtilizationAndRates.totalOnsiteMasterGMPercent"></span>
											
										</td>
										<td ng-hide="isOffshoreVisible"><span>{{roleUtilizationAndRates.totalOnsiteProposedRate}}</span></td>
										<td ng-hide="isOffshoreVisible">
											<span  ng-if="roleUtilizationAndRates.totalOnsiteDiscount">{{roleUtilizationAndRates.totalOnsiteDiscount}}</span>
											<span  ng-if="!roleUtilizationAndRates.totalOnsiteDiscount"></span>
										</td>
										<td ng-hide="isOffshoreVisible" class="tdRightBorder">
											<span ng-if="roleUtilizationAndRates.totalOnsiteGMPercent">{{roleUtilizationAndRates.totalOnsiteGMPercent}}</span>
											<span ng-if="!roleUtilizationAndRates.totalOnsiteGMPercent"></span>
										</td>
										<td ng-hide="isOnsiteVisible"><span>{{roleUtilizationAndRates.totalOffshoreUsage}}</span></td>
										<td ng-hide="isOnsiteVisible"><span>{{roleUtilizationAndRates.totalOffshoreMasterRate}}</span></td>
										<td ng-hide="isOnsiteVisible">
											<span ng-if="roleUtilizationAndRates.totalOffshoreMasterGMPercent">{{roleUtilizationAndRates.totalOffshoreMasterGMPercent}}</span>
											<span ng-if="!roleUtilizationAndRates.totalOffshoreMasterGMPercent"></span>
										</td>
										<td ng-hide="isOnsiteVisible"><span>{{roleUtilizationAndRates.totalOffshoreProposedRate}}</span></td>
										<td ng-hide="isOnsiteVisible">
											<span ng-if="roleUtilizationAndRates.totaloffshoreDiscount">{{roleUtilizationAndRates.totaloffshoreDiscount}}</span>
											<span ng-if="!roleUtilizationAndRates.totaloffshoreDiscount"></span>
										</td>
										<td ng-hide="isOnsiteVisible">
											<span ng-if="roleUtilizationAndRates.totalOffshoreGMPercent">{{roleUtilizationAndRates.totalOffshoreGMPercent}}</span>
											<span ng-if="!roleUtilizationAndRates.totalOffshoreGMPercent"></span>
										</td>
									</tr>			
								</tbody>
							</table>
						</div>
						
					</div>
				</div>
				<div class="divEmptyThrice" ></div>
                <div class="row">
        			<div class="col-sm-12">
        				<div class="table-responsive" id="tableToExportinNew"  style="display: none">
							<table border="1" class="table tblRateCardCre table-striped table-hover table-borderless table-condensed" id="tblRoleUtilization">
								<thead class="">
									<tr>Note: All rates are in {{newCurrencyCode}} </tr>
									<tr>
											<th class="tdWidth5Per"></th>
											<th class="tdWidth5Per"></th>
											<th class="width12per"></th>
											<th class="width12per"></th>
											<!-- <th class="colRightSection" colspan="1"></th> -->
											<th class="colRightSection"
												colspan="{{colspanArray[$index]}}"
												ng-repeat="year in yearList">
												<a class="btn btn-default"
													href="#" tabindex="1" ng-click="yearData(year[0])">{{year[1]
													| limitTo:(year[1].length - (year[1].indexOf(","))-1)}}<br>{{year[1].substring(year[1].indexOf(","),year[1].length)|
													headerFilter : "," : " - "}}
												</a>
											</th>
											<th class="colRightSection" colspan="13" ng-hide="yearList">
												<a class="btn btn-default" href="#" tabindex="2"></a>
											</th>
										</tr>
									<tr class="sectionHeading">
										<th colspan="4" class="headerBgColor tdRightBorder"></th>
										<th colspan="7" class="headerBgColorDarkGray tdAlignCenter tdRightBorder">Utilization Onsite</th>
										<th colspan="6" class="headerBgColor tdAlignCenter">Utilization Offshore</th>
									</tr>
								</thead>
								<tbody>
									<tr class="tlbHeader">
										<th class="firstColLeftAlign rcRoleDesc ">RoleId</th>
										<th class="firstColLeftAlign rcRoleDesc ">Skill/Role Description</th>
										<!-- Role Utilization and Rates -->
										<!-- <th class="tdCommonRightSec">Syntel Band Grade</th> -->
										<th class="tdCommonRightSec">GCM Level</th>
										<th class="tdCustRole tdRightBorder">Customer Role</th>
										<th class="tdminWidth">Local<br>(%)
										</th>
										<!--Utilization  -->
										<!-- <th class="tdCommonRightSec">Deputed, (H1/WP)(%)</th> -->
										<th class="tdminWidth">Onsite Usage<br>(%)
										</th>
										<!--Onsite-->
										<th class="tdminWidth">Onsite Master Rate</th>
										<th class="tdminWidth">Onsite Master Rate PM (%)</th>
										<th class="tdCommonRightSec">Onsite Proposed Client Rate</th>
										<th class="tdCommonRightSec">Discount (-) / Premium (+)</th>
										<th class="tdminWidth tdRightBorder">Onsite PM (%)</th>
										<th class="thSepBackCol tdminWidth">Offshore Usage (%)</th>
										<!-- Offshore -->
										<th class="tdminWidth thSepBackCol">Offshore Master Rate</th>
										<th class="tdminWidth thSepBackCol">Offshore Master Rate PM (%)</th>
										<th class="tdCommonRightSec thSepBackCol">Offshore Proposed
											Client Rate</th>
										<th class="tdCommonRightSec thSepBackCol">Discount (-) /
											Premium (+)</th>
										<th class="tdminWidth thSepBackCol">Offshore PM (%)</th>
									</tr>
								</tbody>
								<tbody id="tBodyRoleUtilization">
									<tr ng-repeat="rateData in tableDetails">
										<td class="grayBgColor colSection tdWordBreak">{{rateData.MasterRoleId}}</td>
										<td class="grayBgColor colSection firstColLeftAlign tooltip1 tdWordBreak">{{rateData.RoleDesc}}</td>
										<!-- <td class="grayBgColor colSection tdWordBreak">{{rateData.BandGrade}}</td> -->
										<td class="grayBgColor colSection tdWordBreak">{{rateData.gcmCODE}}&#160;&nbsp; </td>
										<td class="grayBgColor colSection tdWordBreak tdRightBorder">{{rateData.CustRole}}</td>
										<td class="colRightSection tableInputColor"><span>{{rateData.LocalUti}}</span></td>
										<!-- <td class="colRightSection"><span>{{rateData.DepuUti}}</span></td> -->
										<td class="colRightSection tableInputColor"><span>{{rateData.OnsiteUsa}}</span></td>
										<td class="colRightSection"><span>{{rateData.OnsiteMasRate*multiply}}</span></td>
										<td class="colRightSection"><span>{{rateData.OnsiteMasterRateGM}}</span></td>
										<td class="colRightSection tableInputColor"><span>{{rateData.OnsitePro*multiply}}</span></td>
										<td class="colRightSection"><span>{{rateData.OnsiteDisPremium}}</span></td>
										<td class="colRightSection tdRightBorder"><span>{{rateData.OnsiteGmPer}}</span></td>
										<td class="colRightSection tableInputColor"><span>{{rateData.OffshoreUsa}}</span></td>
										<td class="colRightSection"><span>{{rateData.OffshoreMasRate*multiply}}</span></td>
										<td class="colRightSection"><span>{{rateData.OffshoreMasterRateGM}}</span></td>
										<td class="colRightSection tableInputColor"><span>{{rateData.OffshorePro*multiply}}</span></td>
										<td class="colRightSection"><span>{{rateData.OffshoreDisPremium}}</span></td>
										<td class="colRightSection"><span>{{rateData.OffshoreGmPer}}</span></td>
									</tr>
									<tr for="roleUtilizationSubmit">
											<td class="firstColLeftAlign tdSubTotal" colspan="3"><span>Sub Total</span></td>
											<!-- <td></td> -->
										<td class="tdRightBorder"></td>
										<td></td>
										<td></td>
										<td></td>
										<td></td>
										<td><span>{{roleUtilizationAndRates.totalOnsiteUsage}}</span></td>
										<td><span>{{roleUtilizationAndRates.totalOnsiteMasterRate*multiply}}</span></td>
										<td><span>{{roleUtilizationAndRates.totalOnsiteMasterGMPercent}}</span></td>
										<td><span>{{roleUtilizationAndRates.totalOnsiteProposedRate*multiply}}</span></td>
										<td><span>{{roleUtilizationAndRates.totalOnsiteDiscount}}</span></td>
										<td class="tdRightBorder"><span>{{roleUtilizationAndRates.totalOnsiteGMPercent}}</span></td>
										<td><span>{{roleUtilizationAndRates.totalOffshoreUsage}}</span></td>
										<td><span>{{roleUtilizationAndRates.totalOffshoreMasterRate*multiply}}</span></td>
										<td><span>{{roleUtilizationAndRates.totalOffshoreMasterGMPercent}}</span></td>
										<td><span>{{roleUtilizationAndRates.totalOffshoreProposedRate*multiply}}</span></td>
										<td><span>{{roleUtilizationAndRates.totaloffshoreDiscount}}</span></td>
										<td><span>{{roleUtilizationAndRates.totalOffshoreGMPercent}}</span></td>
									</tr>
								</tbody>
							</table>
						</div>
        			</div>
				</div>
				</form>
				
								<ng-form class="form-inline" role="form" name="frmRCRoleUtilizationUpload" id="frmRCRoleUtilizationUpload">
						<div class="row">
						<div class="col-sm-12">
							<div class="panel-group" ng-hide="UploadHidden">
								<div class="panel panel-info ">
									<div class="panel-heading panelHeadingStyle"
											ng-click="ShowHideTeamDetails()">
											<div class="row ">
												<label class="control-label col-sm-10 ">Upload File</label>
												<div class="col-sm-2 textAlignRight">
													<label class="DownArrowColor textAlignRight">
														&#9660;</label>
												</div>
											</div>
										</div>
									
									<div class="panel-body" ng-hide="TeamDetailsHidden">
									<div class="panel-body">
										<div class="row">
											<div class="col-sm-2 "></div>
											<label
												class="control-label col-sm-2 textAlignRight">Upload
												File name</label>
											<div class="col-sm-3 ">
												<input type="file" class="form-control"
													name="fuUploadFilename" id="fuUploadFilename"
													ng-model="frmRCRoleUtilizationUpload.fuUploadFilenameModel"
													check-file-size="frmRCRoleUtilizationUpload.fuUploadFilenameModel"
													valid-File required
													ng-class="{true: 'ng-border'}[(onUplaod) && frmRCRoleUtilizationUpload.fuUploadFilename.$invalid]"
													>
												<div class="error-messages" ng-if="(onUplaod)"
													ng-messages="frmRCRoleUtilizationUpload.fuUploadFilename.$error">
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
													ng-click="uploadData(frmRCRoleUtilizationUpload);" ng-disabled = "uploadBtnDisable">Upload</button>
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
								<div  ng-show="tableHide">
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
											<tr id="ManualDealattachements"
												ng-repeat="row in MDAttachementData | orderBy : row.createdOn ">
												<td width="60%"><a href="#" id="Attach_Download"
													class="control-label  textAlignLeft"
													ng-click="downloadUploadedFile(row.dealAttachmentId);"><label>{{row.fileName}}</label></a></td>
												<td width="25%"><label>{{row.createdOn}}</label></td>
												<td width="15%"><a href="#" id="Attach_Delete"
													class="control-label  textAlignLeft"
													ng-click="deleteUploadedFile(row.dealAttachmentId);"><label>Delete</label></a>
												</td>
											</tr>
											<tr>
											</tr>
										</tbody>
									</table>
								</div>
								</div>
								</div>
							</div>
							</div>
						</div>
					</div>
				</ng-form>
				
				<div class="divEmptyThrice"></div>
				
				<div class="row text-center">
					<div class="col-sm-12">
					<!-- <button type="button" class="btn btn-primary btnSpace" id="btnCalculate">Calculate</button> -->
						<button type="button" class="btn btn-primary btnSpace" id="btnSave" ng-disabled="isSaveDisabled" ng-click="saveData(roleUtilizationAndRates);">Save</button>	
						<button type="button" class="btn btn-primary btnSpace" id="btnSubmit" ng-disabled="isDisabled" ng-click="submit(roleUtilizationAndRates)" >Submit</button>	
						<button type="button" class="btn btn-info btnSpace" id="btnClientPrev" ng-click="Prev()">Prev</button>
						<button type="button" class="btn btn-info" id="btnClientNext" ng-click="Next()">Next</button>
					</div>
				</div>

        </div>
    </div>
    </fieldset>
	<div id="footer" ng-include="'${contextPath}/Portal/Footer.jsp'"></div>
	<div id="loader" ng-include="'${contextPath}/Portal/loader.jsp'"></div>
</body>
</html>