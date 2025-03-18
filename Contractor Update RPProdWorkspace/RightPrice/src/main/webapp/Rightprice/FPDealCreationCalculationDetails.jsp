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
    <link href="${contextPath}/resources/css/bootstrap.css" rel="stylesheet"/>
    <link href="${contextPath}/resources/css/SAPStyleSheet.css" rel="stylesheet"/>
    <link href="${contextPath}/resources/css/loader.css" rel="stylesheet"/>
    <script src="${contextPath}/resources/js/jquery.min.js"></script>
    <script src="${contextPath}/resources/js/bootstrap.js"></script>
    <script src="${contextPath}/resources/js/jquery.serializeJSON.min.js"></script>
    <script src="${contextPath}/resources/js/jquery-ui.js"></script>
    <link href="${contextPath}/resources/css/jquery-ui.css" rel="stylesheet"/>
    <link href="${contextPath}/resources/css/bootstrap-dialog.css" rel="stylesheet"/>
    <script src="${contextPath}/resources/js/bootstrap-dialog.js"></script>
    <link href="${contextPath}/resources/css/ie10-viewport-bug-workaround.css" rel="stylesheet"/>
	<link href="${contextPath}/resources/css/sticky-footer-navbar.css" rel="stylesheet"/>
	<script src="${contextPath}/resources/js/ie10-viewport-bug-workaround.js"></script>
	<script src="${contextPath}/resources/js/angular.js"></script>
	<script src="${contextPath}/resources/js/angular-ui.min.js"></script>
	<script src="${contextPath}/resources/js/angular-messages.js"></script>
	<script src="${contextPath}/resources/js/ngStorage.js"></script>
	<script src="${contextPath}/resources/js/loader.js"></script>
	<script src="${contextPath}/resources/js/RightPrice/fPDealCreationCalculationDetails.js"></script>
	<script src="${contextPath}/resources/js/Services/fpDealCostInputsService.js"></script>
	<script src="${contextPath}/resources/js/Factory/WebServiceFactory.js"></script>
</head>
<body ng-app="RightPriceApp" ng-controller="FPDealCreationCostSummaryController" ng-init="setDealVersionId()">
   <div id="includedContent" ng-include="'${contextPath}/Portal/Header.jsp'"></div>
   <fieldset ng-disabled="loading || showLoader">
    <div class="container">
        <div class="divEmpty"></div>
        <div class="row marginBottom5px">
            <div class="col-sm-12">
                <h3 class="text-left" id="PageHeading">FP Deal Creation - Calculation Details
</h3>
            </div>
        </div>	
        <div>
            <form class="form-inline" role="form" name="frmDealCalculation" id="frmDealCalculation">
            	<div id="includedT&PStages" ng-include="'${contextPath}/Portal/FPdealCompletionStage.jsp'"></div>
                <div id="includedRateCardStages" ng-include="'${contextPath}/Rightprice/DealCreationInformationTable.jsp'"></div>
                <div class="row">
                    <div class="col-sm-12">
                        <div class="panel-group">
                            <div class="panel panel-info ">
                                <div class="panel-heading panelHeadingStyle">
                                    <div class="row ">
										<label class="control-label col-sm-10 "> FP Calculation Details</label>
									</div>
                                </div>
                                <div class="panel-body">
									<div class="row marginBottom5px">
									<label class="control-label col-sm-1 textAlignRight required-Field">Tower</label>
                                       <div class="col-sm-2">
                                          <select id="ddlDealFpCalculationDetailsTower" class="form-control" placeholder="Please select" name="ddlDealFpCalculationDetailsTower"
                                          		ng-model="frmDealCalculation.ddlTowerModel" 
                                          		ng-options="to as to.towerName for to in towerdetails"
                                          		ng-change="getTowerCountryCity(frmDealCalculation.ddlTowerModel)"
                                          		required
                                          		ng-class="{true: 'ng-border'}[onSearch && frmDealCalculation.ddlDealFpCostInputsTower.$invalid]">
											<option value="" selected disabled>Please select</option>
										</select>
										<div class="error-messages" ng-if="onSearch"
														ng-messages="frmDealCalculation.ddlDealFpCostInputsTower.$error">
														<em class="error help-block has-error"
															ng-message="required">Please select Tower</em>
										</div>
                                	</div>
                                	<label class="control-label col-sm-1 textAlignRight required-Field">Country</label>
                                       <div class="col-sm-2">
                                          <input id="ddlDealFpCostInputsCountry" class="form-control" placeholder="Country" name="ddlDealFpCostInputsCountry"
                                          		ng-model="frmDealCalculation.ddlCountryModel" 
                                          		disabled>
										</input>
                                       </div>
                                       <label class="control-label col-sm-1 textAlignRight required-Field">City</label>
                                       <div class="col-sm-2">
                                          <input id="ddlDealFpCostInputsCity" class="form-control" placeholder="City" name="ddlDealFpCostInputsCity"
                                          		ng-model="frmDealCalculation.ddlCityModel" 
                                          		disabled>
										</input>
                                       </div>
                                       <label class="control-label col-sm-1 required-Field">City Categorization</label>
                                       <div class="col-sm-2">
                                          <input id="ddlDealRoleDetailsCityCat" class="form-control" name="ddlDealRoleDetailsCityCat"
                                          		ng-model="frmDealCalculation.dealRoleDetailsCategorizationModel" 
                                          		disabled>
                                       </div>
									</div>
                                    <div class="divEmptyThrice"></div>
                                    <div class="row text-center">
										<div class="col-sm-12">
											<button type="button" class="btn btn-primary btnSpace" id="btnSearch" ng-click="getFpDealCalculationData(frmDealCalculation);">Search</button>
										</div>
									</div>
									<div class="divEmptyThrice"></div>
				                   	<div class="row marginBottom5px">
				                   		<div class="col-sm-12">
											<div class="table-responsive">
												<table class="table tblDealCre table-borderless table-condensed text-center" id="tblProjectCost">
													 <thead>
														<tr>
															<th class="tdWidthPartiCulars thBorder">Particulars</th>
															<th class="colRightSection" colspan="14">
					                                    	<a ng-repeat="year in yearHeader" class="btn btn-default" href="#"  style="margin-right:5px;" ng-click="setYearHeader(year)" 
					                                    		ng-show="year.year">{{year.year | limitTo:(year.year.length - (year.year.indexOf(","))-1)}}</a>	 
				                                    	 </th>
												    	</tr>	
												    	<tr ng-if="monthHeader.length > 0">
					                          				<td colspan="1"></td>	                                    
					                                    	<td class="thWidth4Per" ng-repeat="month in monthHeader">{{month.month}}</td>
					                                    	<td class="thWidth4Per">Total</td>
				                                    	</tr>	
				                                     <tr ng-if="noOfYears==1 &&  summaryFlag.length == 1">
					                          			<td colspan="1"></td>                                    
					                                 
					                                   <td class="thWidth4Per">YR-1</td>	
					                                   <td></td><td></td> <td></td><td></td> <td></td><td></td> <td></td><td></td>	<td></td><td></td> <td></td>					                                  
					                                    <td class="thWidth4Per">Total</td>
				                                    </tr>
				                                     <tr ng-if="noOfYears==2 && summaryFlag.length==1">
					                          			<td colspan="1"></td>	                                    
					                                   <!--  <td class="thWidth4Per" ng-repeat="month in monthHeader">{{month.month}}</td> -->
					                                   <td class="thWidth4Per">YR-1</td>
					                                   <td  class="thWidth4Per">YR-2</td>
					                                   <td></td><td></td> <td></td><td></td> <td></td><td></td> <td></td><td></td>	<td></td><td></td> 					                                   
					                                    <td class="thWidth4Per">Total</td>
				                                    </tr>
				                                       <tr ng-if="noOfYears==3 && summaryFlag.length==1">
					                          			<td colspan="1"></td>	                                    
					                                   <!--  <td class="thWidth4Per" ng-repeat="month in monthHeader">{{month.month}}</td> -->
					                                   <td class="thWidth4Per">YR-1</td>
					                                   <td  class="thWidth4Per">YR-2</td>
					                                     <td  class="thWidth4Per">YR-3</td>	
					                                     <td></td><td></td> <td></td><td></td> <td></td><td></td> <td></td><td></td>	<td></td>					                                   
					                                    <td class="thWidth4Per">Total</td>
				                                    </tr>
				                                     <tr ng-if="noOfYears==4 && summaryFlag.length==1">
					                          			<td colspan="1"></td>	                                    
					                                   <!--  <td class="thWidth4Per" ng-repeat="month in monthHeader">{{month.month}}</td> -->
					                                   <td class="thWidth4Per">YR-1</td>
					                                   <td  class="thWidth4Per">YR-2</td>
					                                     <td  class="thWidth4Per">YR-3</td>
					                                      <td  class="thWidth4Per">YR-4</td>
					                                      <td></td><td></td> <td></td><td></td> <td></td><td></td> <td></td><td></td>								                                   
					                                    <td class="thWidth4Per">Total</td>
				                                    </tr>
				                                     <tr ng-if="noOfYears==5 && summaryFlag.length==1">
					                          			<td colspan="1"></td>	                                    
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
					                          			<td colspan="1"></td>	                                    
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
					                          			<td colspan="1"></td>	                                    
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
					                          			<td colspan="1"></td>	                                    
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
					                          			<td colspan="1"></td>	                                    
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
					                          			<td colspan="1"></td>	                                    
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
													<tr  ng-repeat ="data  in calculationData | orderBy :['sectionId','rowSequenceNumber'] " ng-if="currentMonthYearHeader == data.monthYearHeader">
					                                    <td class="rcTdText colSection"><span ng-if="data.totalIndicator == 2"><b>{{data.costType}}</b></span>        
        												<span  ng-if="data.totalIndicator == 1">{{data.costType}}</span></td>
					                                    <td class="colRightSection textAlignRight"><span ng-if="data.totalIndicator == 2"><b>{{data.staffingFirstMonthCount | number : 0}}</b></span>        
        												<span  ng-if="data.totalIndicator == 1">{{data.staffingFirstMonthCount | number : 0}}</span></td>
        												
        												<td class="colRightSection textAlignRight"><span ng-if="data.totalIndicator == 2"><b>{{data.staffingSecondMonthCount | number : 0}}</b></span>        
        												<span  ng-if="data.totalIndicator == 1">{{data.staffingSecondMonthCount | number : 0}}</span></td>
        												
        												<td class="colRightSection textAlignRight"><span ng-if="data.totalIndicator == 2"><b>{{data.staffingThirdMonthCount | number : 0}}</b></span>        
        												<span  ng-if="data.totalIndicator == 1">{{data.staffingThirdMonthCount | number : 0}}</span></td>
        												
        												<td class="colRightSection textAlignRight"><span ng-if="data.totalIndicator == 2"><b>{{data.staffingFourthMonthCount | number : 0}}</b></span>        
        												<span  ng-if="data.totalIndicator == 1">{{data.staffingFourthMonthCount | number : 0}}</span></td>
        												
        												<td class="colRightSection textAlignRight"><span ng-if="data.totalIndicator == 2"><b>{{data.staffingFifthMonthCount | number : 0}}</b></span>        
        												<span  ng-if="data.totalIndicator == 1">{{data.staffingFifthMonthCount | number : 0}}</span></td>
        												
        												<td class="colRightSection textAlignRight"><span ng-if="data.totalIndicator == 2"><b>{{data.staffingSixthMonthCount | number : 0}}</b></span>        
        												<span  ng-if="data.totalIndicator == 1">{{data.staffingSixthMonthCount | number : 0}}</span></td>
        												
        												<td class="colRightSection textAlignRight"><span ng-if="data.totalIndicator == 2"><b>{{data.staffingSeventhMonthCount | number : 0}}</b></span>        
        												<span  ng-if="data.totalIndicator == 1">{{data.staffingSeventhMonthCount | number : 0}}</span></td>
        												
        												<td class="colRightSection textAlignRight"><span ng-if="data.totalIndicator == 2"><b>{{data.staffingEighthMonthCount | number : 0}}</b></span>        
        												<span  ng-if="data.totalIndicator == 1">{{data.staffingEighthMonthCount | number : 0}}</span></td>
        												
        												<td class="colRightSection textAlignRight"><span ng-if="data.totalIndicator == 2"><b>{{data.staffingNinthMonthCount | number : 0}}</b></span>        
        												<span  ng-if="data.totalIndicator == 1">{{data.staffingNinthMonthCount | number : 0}}</span></td>
        												
        												<td class="colRightSection textAlignRight"><span ng-if="data.totalIndicator == 2"><b>{{data.staffingTenthMonthCount | number : 0}}</b></span>        
        												<span  ng-if="data.totalIndicator == 1">{{data.staffingTenthMonthCount | number : 0}}</span></td>
        												
        												<td class="colRightSection textAlignRight"><span ng-if="data.totalIndicator == 2"><b>{{data.staffingEleventhMonthCount | number : 0}}</b></span>        
        												<span  ng-if="data.totalIndicator == 1">{{data.staffingEleventhMonthCount | number : 0}}</span></td>
        												
        												<td class="colRightSection textAlignRight"><span ng-if="data.totalIndicator == 2"><b>{{data.staffingTwelthMonthCount | number : 0}}</b></span>        
        												<span  ng-if="data.totalIndicator == 1">{{data.staffingTwelthMonthCount | number : 0}}</span></td>
					                              		<td class="colRightSection thWidth4Per textAlignRight"><b>{{data.yearlyTotal | number : 0}}</b></td>
					                              	</tr>
													</tbody>
												</table>
											</div>
										</div>
									</div>									
									<div class="divEmptyThrice"></div>
                                    <div class="row text-center">
										<div class="col-sm-12">
												<!-- <button type="button" class="btn btn-danger btnSpace" id="btnCancel">Cancel</button> -->
											<button type="button" class="btn btn-info btnSpace"id="btnClientPrev" ng-click="Prev()">Prev</button>
											<button type="button" class="btn btn-primary btnSpace" ng-click="download(frmDealCalculation)" ng-disabled="isDownloadEnabled">
											<img src="${contextPath}/resources/Images/downloadexcel.png" alt="Snow"> Export to Excel </button>
											<button type="button" class="btn btn-info" id="btnClientNext" ng-click="Next()">Next</button>
										</div>
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
