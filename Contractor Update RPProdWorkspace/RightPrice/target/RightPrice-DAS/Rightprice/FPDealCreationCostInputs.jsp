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
	<link href="${contextPath}/resources/css/AngularCSS.css" rel="stylesheet" />
	<script src="${contextPath}/resources/js/angular.js"></script>
	<script src="${contextPath}/resources/js/angular-ui.min.js"></script>
	<script src="${contextPath}/resources/js/angular-messages.js"></script>
	<script src="${contextPath}/resources/js/ngStorage.js"></script>
	<script src="${contextPath}/resources/js/loader.js"></script>
	<script src="${contextPath}/resources/js/RightPrice/FPDealCreationPFCostInputsController.js"></script>
	<script src="${contextPath}/resources/js/Services/fpDealCostInputsService.js"></script>
	<script src="${contextPath}/resources/js/Factory/WebServiceFactory.js"></script>
	<script src="${contextPath}/resources/js/Directives/angularCustomValidations.js"></script>
	<script>
		$(document).ready(function () {
			   //var $a = $('#tblStaffing a');
			 $('#tblProjectCost').on('click', 'a', function() {
				 $('#tblProjectCost a').removeClass('active');
			     $(this).addClass('active'); 
			 }); 
		}); 
	</script> 
</head>
<body ng-app="RightPriceApp" ng-controller="FPDealCreationPFCostInputsController" >
  <div id="includedContent" ng-include="'${contextPath}/Portal/Header.jsp'"></div>
  <fieldset ng-disabled="loading || showLoader">
    <div class="container">
        <div class="divEmpty"></div>
        <div class="row marginBottom5px">
            <div class="col-sm-12">
                <h3 class="text-left">FP Deal Creation - Cost Inputs (Travel / Relocation)</h3>
            </div>
        </div>	
        <div>
            <form class="form-inline" role="form" name="frmFpCostInputs" id="frmFpCostInputs">
            <div id="includedT&PStages" ng-include="'${contextPath}/Portal/FPdealCompletionStage.jsp'"></div>
            <div id="includedRateCardStages" ng-include="'${contextPath}/Rightprice/DealCreationInformationTable.jsp'"></div>
            <div class="row">
					<div class="col-sm-12">
						<div class="panel-group">
							<div class="panel panel-info">
								<div class="panel-heading panelHeadingStyle ">
									<div class="row ">
										<label class="control-label col-sm-10 ">FP Deal Travel Relocation Cost on H1 and B1 staffing </label>
										<!-- <div class="col-sm-2 textAlignRight ">
											<a href="#" class="DownArrowColor" ng-click="ShowHideView()"> &#9660;</a>
										</div> -->
									</div>
								</div>
								<div class="panel-body">
								
									<div class="row marginBottom5px">
									<label class="control-label col-sm-1 textAlignRight required-Field">Tower</label>
                                       <div class="col-sm-2">
                                          <select id="ddlDealFpCostInputsTower" class="form-control" placeholder="Please select" name="ddlDealFpCostInputsTower"
                                          		ng-model="frmFpCostInputs.ddlTowerModel" 
                                          		ng-options="to as to.towerName for to in towerdetails"
                                          		ng-change="getTowerCountryCity(frmFpCostInputs.ddlTowerModel)"
                                          		required
                                          		ng-class="{true: 'ng-border'}[onSearch && frmFpCostInputs.ddlDealFpCostInputsTower.$invalid]">
											<option value="" selected disabled>Please select</option>
										</select>
										<div class="error-messages" ng-if="onSearch"
														ng-messages="frmFpCostInputs.ddlDealFpCostInputsTower.$error">
														<em class="error help-block has-error"
															ng-message="required">Please select Tower</em>
										</div>
                                	</div>
                                	<label class="control-label col-sm-1 textAlignRight required-Field">Country</label>
                                       <div class="col-sm-2">
                                          <input id="ddlDealFpCostInputsCountry" class="form-control" placeholder="Country" name="ddlDealFpCostInputsCountry"
                                          		ng-model="frmFpCostInputs.ddlCountryModel" 
                                          		disabled>
										</input>
                                       </div>
                                       <label class="control-label col-sm-1 textAlignRight required-Field">City</label>
                                       <div class="col-sm-2">
                                          <input id="ddlDealFpCostInputsCity" class="form-control" placeholder="City" name="ddlDealFpCostInputsCity"
                                          		ng-model="frmFpCostInputs.ddlCityModel" 
                                          		disabled>
										</input>
                                       </div>
                                         <label class="control-label col-sm-1 required-Field">City Categorization</label>
                                       <div class="col-sm-2">
                                          <input id="ddlDealRoleDetailsCityCat" class="form-control" name="ddlDealRoleDetailsCityCat"
                                          		ng-model="frmFpCostInputs.dealRoleDetailsCategorizationModel" 
                                          		disabled>
                                       </div>
									</div>
									<div class = "row marginBottom5px">
									<label class="control-label col-sm-1 textAlignRight required-Field">Cost Input Type</label>
									<div class="col-sm-3">
                                          <select id="ddlDealFpCostInputCostType" class="form-control" placeholder="Please select" name="ddlDealFpCostInputCostType"
                                          		ng-model="frmFpCostInputs.ddlCostTypeModel" 
                                          		ng-options="cost.id as cost.name for cost in costType"
                                          		ng-change="fpcostType(frmFpCostInputs.ddlCostTypeModel)"
                                          		required
                                          		ng-class="{true: 'ng-border'}[onSearch && frmFpCostInputs.ddlDealFpCostInputCostType.$invalid]">
											<option value="" selected disabled>Please select</option>
										</select>
										<div class="error-messages" ng-if="onSearch"
														ng-messages="frmFpCostInputs.ddlDealFpCostInputCostType.$error">
														<em class="error help-block has-error"
															ng-message="required">Please select Cost Type.</em>
										</div>
                                	</div>
									</div>
                                    <div class="divEmptyThrice"></div>
                                    <div class="row text-center">
										<div class="col-sm-12">
											<button type="button" class="btn btn-primary btnSpace" id="btnSearch" ng-click="getFpDealCostInputsData(frmFpCostInputs);">Search</button>
										</div>
									</div>
									<div class="divEmptyThrice"></div>
				                   	<div class="row marginBottom5px">
				                   		<div class="col-sm-12">
											<div class="table-responsive">
											 <p style="color:green"> Transistion Period =                                        
	                                       <input name="transGMPerModel" type="text"  ng-value="showTransValue" style =" width: 14px; border: none"
													id="transGMPerModel" ng-model="frmFpCostInputs.ddlTransactionMonth" readonly> months marked in Green Color
											</p>	
												<table class="table tblDealCre table-borderless table-condensed text-center" id="tblProjectCost">
													 <thead>
														<tr>
														<th class="tdWidthStage thBorder text-center">Sr.No</th>
															<th class="tdWidthPartiCulars thBorder text-center">Particulars</th>
															<th class="tdWidthStage thBorder text-center" ng-hide="showstanrates">Unit Of Measurement</th>
															<th class="tdWidthStage thBorder text-center" ng-show="showstanrates">Standard Rates</th>
															<th class="colRightSection" colspan="15">
					                                    	<a ng-repeat="year in yearHeader" class="btn btn-default" href="#"  style="margin-right:5px;" ng-click="setYearHeader(year)" 
					                                    		ng-show="year.year">{{year.year | limitTo:(year.year.length - (year.year.indexOf(","))-1)}}</a>	 
				                                    	 </th>
												    	</tr>	
												    	<tr ng-if="monthHeader.length > 0 &&  summaryFlag.length==0">
					                          				<td colspan="3"></td>
					                                    	<td class="thWidth4Per" ng-repeat="month in monthHeader" ng-style="{'color': colors[$index]}" style="color:#ffffff">{{month.month}}</td>
					                                    	<td class="thWidth4Per">Total</td>
				                                    	</tr>			
				                                    	
				                                    	 <tr ng-if="noOfYears==1 &&  summaryFlag.length == 1">
					                          			<td colspan="3"></td>                                    
					                                 
					                                   <td class="thWidth4Per">YR-1</td>	
					                                   <td></td><td></td> <td></td><td></td> <td></td><td></td> <td></td><td></td>	<td></td><td></td> <td></td>					                                  
					                                    <td class="thWidth4Per">Total</td>
				                                    </tr>
				                                     <tr ng-if="noOfYears==2 && summaryFlag.length==1">
					                          			<td colspan="3"></td>	                                    
					                                   <!--  <td class="thWidth4Per" ng-repeat="month in monthHeader">{{month.month}}</td> -->
					                                   <td class="thWidth4Per">YR-1</td>
					                                   <td  class="thWidth4Per">YR-2</td>
					                                   <td></td><td></td> <td></td><td></td> <td></td><td></td> <td></td><td></td>	<td></td><td></td> 					                                   
					                                    <td class="thWidth4Per">Total</td>
				                                    </tr>
				                                       <tr ng-if="noOfYears==3 && summaryFlag.length==1">
					                          			<td colspan="3"></td>	                                    
					                                   <!--  <td class="thWidth4Per" ng-repeat="month in monthHeader">{{month.month}}</td> -->
					                                   <td class="thWidth4Per">YR-1</td>
					                                   <td  class="thWidth4Per">YR-2</td>
					                                     <td  class="thWidth4Per">YR-3</td>	
					                                     <td></td><td></td> <td></td><td></td> <td></td><td></td> <td></td><td></td>	<td></td>					                                   
					                                    <td class="thWidth4Per">Total</td>
				                                    </tr>
				                                     <tr ng-if="noOfYears==4 && summaryFlag.length==1">
					                          			<td colspan="3"></td>	                                    
					                                   <!--  <td class="thWidth4Per" ng-repeat="month in monthHeader">{{month.month}}</td> -->
					                                   <td class="thWidth4Per">YR-1</td>
					                                   <td  class="thWidth4Per">YR-2</td>
					                                     <td  class="thWidth4Per">YR-3</td>
					                                      <td  class="thWidth4Per">YR-4</td>
					                                      <td></td><td></td> <td></td><td></td> <td></td><td></td> <td></td><td></td>								                                   
					                                    <td class="thWidth4Per">Total</td>
				                                    </tr>
				                                     <tr ng-if="noOfYears==5 && summaryFlag.length==1">
					                          			<td colspan="3"></td>	                                    
					                                   <!--  <td class="thWidth4Per" ng-repeat="month in monthHeader">{{month.month}}</td> -->
					                                   <td class="thWidth4Per">YR-1</td>
					                                   <td  class="thWidth4Per">YR-2</td>
					                                     <td  class="thWidth4Per">YR-3</td>
					                                      <td  class="thWidth4Per">YR-4</td>	
					                                       <td  class="thWidth4Per">YR-5</td>	
					                                         <td></td><td></td> <td></td><td></td> <td></td><td></td> 	<td></td>					                                   
					                                    <td class="thWidth4Per">Total</td>
				                                    </tr>
				                                      <tr ng-if="noOfYears==6 && summaryFlag.length==1">
					                          			<td colspan="3"></td>	                                    
					                                   <!--  <td class="thWidth4Per" ng-repeat="month in monthHeader">{{month.month}}</td> -->
					                                   <td class="thWidth4Per">YR-1</td>
					                                   <td  class="thWidth4Per">YR-2</td>
					                                     <td  class="thWidth4Per">YR-3</td>
					                                      <td  class="thWidth4Per">YR-4</td>	
					                                       <td  class="thWidth4Per">YR-5</td>	
					                                        <td class="thWidth4Per">YR-6</td>
					                                         <td></td> <td></td><td></td> <td></td><td></td> 	<td></td>					                                   
					                                    <td class="thWidth4Per">Total</td>
				                                    </tr>
				                                    
				                                     <tr ng-if="noOfYears==7 && summaryFlag.length==1">
					                          			<td colspan="3"></td>	                                    
					                                   <!--  <td class="thWidth4Per" ng-repeat="month in monthHeader">{{month.month}}</td> -->
					                                   <td class="thWidth4Per">YR-1</td>
					                                   <td  class="thWidth4Per">YR-2</td>
					                                     <td  class="thWidth4Per">YR-3</td>
					                                      <td  class="thWidth4Per">YR-4</td>	
					                                       <td  class="thWidth4Per">YR-5</td>	
					                                       <td  class="thWidth4Per">YR-6</td>	
					                                       <td  class="thWidth4Per">YR-7</td>	
					                                         <td></td><td></td> <td></td><td></td> 	<td></td>					                                   
					                                    <td class="thWidth4Per">Total</td>
				                                    </tr>
				                                    
				                                     <tr ng-if="noOfYears==8 && summaryFlag.length==1">
					                          			<td colspan="3"></td>	                                    
					                                   <!--  <td class="thWidth4Per" ng-repeat="month in monthHeader">{{month.month}}</td> -->
					                                   <td class="thWidth4Per">YR-1</td>
					                                   <td  class="thWidth4Per">YR-2</td>
					                                     <td  class="thWidth4Per">YR-3</td>
					                                      <td  class="thWidth4Per">YR-4</td>	
					                                       <td  class="thWidth4Per">YR-5</td>	
					                                        <td  class="thWidth4Per">YR-6</td>	
					                                        <td  class="thWidth4Per">YR-7</td>
					                                         <td  class="thWidth4Per">YR-8</td>
					                                        <td></td> <td></td><td></td> 	<td></td>					                                   
					                                    <td class="thWidth4Per">Total</td>
				                                    </tr>
				                                    
				                                     <tr ng-if="noOfYears==9 && summaryFlag.length==1">
					                          			<td colspan="3"></td>	                                    
					                                   <!--  <td class="thWidth4Per" ng-repeat="month in monthHeader">{{month.month}}</td> -->
					                                   <td class="thWidth4Per">YR-1</td>
					                                   <td  class="thWidth4Per">YR-2</td>
					                                     <td  class="thWidth4Per">YR-3</td>
					                                      <td  class="thWidth4Per">YR-4</td>	
					                                       <td  class="thWidth4Per">YR-5</td>
					                                       <td  class="thWidth4Per">YR-6</td>	
					                                        <td  class="thWidth4Per">YR-7</td>
					                                         <td  class="thWidth4Per">YR-8</td>	
					                                          <td  class="thWidth4Per">YR-9</td>	
					                                         <td></td><td></td> 	<td></td>					                                   
					                                    <td class="thWidth4Per">Total</td>
				                                    </tr>
				                                    
				                                    <tr ng-if="noOfYears==10 && summaryFlag.length==1">
					                          			<td colspan="3"></td>	                                    
					                                   <!--  <td class="thWidth4Per" ng-repeat="month in monthHeader">{{month.month}}</td> -->
					                                   <td class="thWidth4Per">YR-1</td>
					                                   <td  class="thWidth4Per">YR-2</td>
					                                     <td  class="thWidth4Per">YR-3</td>
					                                      <td  class="thWidth4Per">YR-4</td>	
					                                       <td  class="thWidth4Per">YR-5</td>	
					                                       <td  class="thWidth4Per">YR-6</td>	
					                                        <td  class="thWidth4Per">YR-7</td>
					                                         <td  class="thWidth4Per">YR-8</td>	
					                                          <td  class="thWidth4Per">YR-9</td>
					                                           <td  class="thWidth4Per">YR-10</td>
					                                        <td></td>		  	<td></td>					                                   
					                                    <td class="thWidth4Per">Total</td>
				                                    </tr>						                       
				                                    					                       
													</thead>
													<tbody>
													<tr  ng-show="summaryFlag.length==0" ng-repeat ="data  in costInputsData | orderBy :'indirectCostId'" ng-if="currentMonthYearHeader == data.monthYearHeader && data.inDirectCostmaster.displayIndicator == 1">
					                                    <td class="thWidth8Per colSection textAlignLeft">{{data.countIndex}}</td>
					                                    <td class="thWidth8Per colSection textAlignLeft">{{data.indirectCostDescription | underscoreless : "_" : " "}}</td>
					                                    <td class="thWidth8Per colSection" ng-hide="showstanrates"><input type="text" class="form-control"  ng-model="data.comment" disabled></td>
					                                    <td class="thWidth8Per colSection" ng-show="showstanrates"><input type="text" class="form-control"  ng-model="data.comment" disabled></td>
					                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control"  ng-if="noOfMonths>=1" ng-model="data.staffingFirstMonthCount" ng-blur="calculateRowTotal(data,$index);" ng-disabled="isSummaryFlag"></td>
					                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control"  ng-if="noOfMonths>=2" ng-model="data.staffingSecondMonthCount" ng-blur="calculateRowTotal(data,$index);" ng-disabled="isSummaryFlag"></td>
					                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control"  ng-if="noOfMonths>=3" ng-model="data.staffingThirdMonthCount" ng-blur="calculateRowTotal(data,$index);" ng-disabled="isSummaryFlag"></td>
					                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control"  ng-if="noOfMonths>=4" ng-model="data.staffingFourthMonthCount" ng-blur="calculateRowTotal(data,$index);" ng-disabled="isSummaryFlag"></td></td>
					                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control"  ng-if="noOfMonths>=5" ng-model="data.staffingFifthMonthCount" ng-blur="calculateRowTotal(data,$index);" ng-disabled="isSummaryFlag"></td>
					                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control"  ng-if="noOfMonths>=6" ng-model="data.staffingSixthMonthCount" ng-blur="calculateRowTotal(data,$index);" ng-disabled="isSummaryFlag"></td>
					                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control"  ng-if="noOfMonths>=7" ng-model="data.staffingSeventhMonthCount" ng-blur="calculateRowTotal(data,$index);" ng-disabled="isSummaryFlag"></td>
					                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control"  ng-if="noOfMonths>=8" ng-model="data.staffingEighthMonthCount" ng-blur="calculateRowTotal(data,$index);" ng-disabled="isSummaryFlag"></td>
					                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control"  ng-if="noOfMonths>=9" ng-model="data.staffingNinthMonthCount" ng-blur="calculateRowTotal(data,$index);" ng-disabled="isSummaryFlag"></td>
					                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control"  ng-if="noOfMonths>=10" ng-model="data.staffingTenthMonthCount" ng-blur="calculateRowTotal(data,$index);" ng-disabled="isSummaryFlag"></td>
					                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control"  ng-if="noOfMonths>=11" ng-model="data.staffingEleventhMonthCount" ng-blur="calculateRowTotal(data,$index);" ng-disabled="isSummaryFlag"></td>
					                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control"  ng-if="noOfMonths>=12" ng-model="data.staffingTwelthMonthCount" ng-blur="calculateRowTotal(data,$index);" ng-disabled="isSummaryFlag"></td>
					                              		<td class="colRightSection thWidth4Per"><input type="text" class="form-control" ng-model="data.yearlyTotal" disabled></td>
					                              	</tr>
					                              	
					                              	<tr  ng-show="summaryFlag.length==1" ng-repeat ="data  in costInputsData | orderBy :'indirectCostId'" ng-if="currentMonthYearHeader == data.monthYearHeader && data.inDirectCostmaster.displayIndicator == 1">
					                                    <td class="thWidth8Per colSection textAlignLeft">{{data.countIndex}}</td>
					                                    <td class="thWidth8Per colSection textAlignLeft">{{data.indirectCostDescription | underscoreless : "_" : " "}}</td>
					                                    <td class="thWidth8Per colSection" ng-hide="showstanrates"><input type="text" class="form-control"  ng-model="data.comment" disabled></td>
					                                    <td class="thWidth8Per colSection" ng-show="showstanrates"><input type="text" class="form-control"  ng-model="data.comment" disabled></td>
					                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" ng-if="noOfYears>=1" ng-model="data.staffingFirstMonthCount" ng-blur="calculateRowTotal(data,$index);" ng-disabled="isSummaryFlag"></td>
					                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" ng-if="noOfYears>=2" ng-model="data.staffingSecondMonthCount" ng-blur="calculateRowTotal(data,$index);" ng-disabled="isSummaryFlag"></td>
					                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" ng-if="noOfYears>=3" ng-model="data.staffingThirdMonthCount" ng-blur="calculateRowTotal(data,$index);" ng-disabled="isSummaryFlag"></td>
					                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" ng-if="noOfYears>=4" ng-model="data.staffingFourthMonthCount" ng-blur="calculateRowTotal(data,$index);" ng-disabled="isSummaryFlag"></td></td>
					                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" ng-if="noOfYears>=5" ng-model="data.staffingFifthMonthCount" ng-blur="calculateRowTotal(data,$index);" ng-disabled="isSummaryFlag"></td>
					                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" ng-if="noOfYears>=6" ng-model="data.staffingSixthMonthCount" ng-blur="calculateRowTotal(data,$index);" ng-disabled="isSummaryFlag"></td>
					                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" ng-if="noOfYears>=7" ng-model="data.staffingSeventhMonthCount" ng-blur="calculateRowTotal(data,$index);" ng-disabled="isSummaryFlag"></td>
					                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" ng-if="noOfYears>=8" ng-model="data.staffingEighthMonthCount" ng-blur="calculateRowTotal(data,$index);" ng-disabled="isSummaryFlag"></td>
					                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" ng-if="noOfYears>=9" ng-model="data.staffingNinthMonthCount" ng-blur="calculateRowTotal(data,$index);" ng-disabled="isSummaryFlag"></td>
					                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" ng-if="noOfYears>=10" ng-model="data.staffingTenthMonthCount" ng-blur="calculateRowTotal(data,$index);" ng-disabled="isSummaryFlag"></td>
					                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" ng-hide="true" ng-model="data.staffingEleventhMonthCount" ng-blur="calculateRowTotal(data,$index);" ng-disabled="isSummaryFlag"></td>
					                                    <td class="colRightSection thWidth4Per"><input type="text" class="form-control" ng-hide="true" ng-model="data.staffingTwelthMonthCount" ng-blur="calculateRowTotal(data,$index);" ng-disabled="isSummaryFlag"></td>
					                              		<td class="colRightSection thWidth4Per"><input type="text" class="form-control"  ng-model="data.yearlyTotal" disabled></td>
					                              	</tr>
													</tbody>
												</table>
											</div>
											<div class="divEmptyThrice"></div>
											<div>
											<label><i><font color="red">Note: In case of Other Cost please enter the cost directly in {{selectedTowerCurrency}} under each month.</font></i></label>
			  							</div>
										</div>
									</div>									
									<div class="divEmptyThrice"></div>
                                    <div class="row text-center">
										<div class="col-sm-12">
 											<button type="button" class="btn btn-primary btnSpace" ng-click="download(frmFpCostInputs)" ng-disabled="downloadEnabled">
											<img src="${contextPath}/resources/Images/downloadexcel.png" alt="Snow"> Export to Excel </button>	
											<button type="button" class="btn btn-primary btnSpace" id="btnSave" ng-disabled="isSaveDisabled" ng-click = "saveData(frmFpCostInputs);">Save</button>
<!-- 											<button type="button" class="btn btn-danger btnSpace" id="btnCancel">Cancel</button> -->
 											<button type="button" class="btn btn-info btnSpace"id="btnClientPrev" ng-click="Prev()">Prev</button>
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
