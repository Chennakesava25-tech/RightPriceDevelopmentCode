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
	<script src="${contextPath}/resources/js/Factory/WebServiceFactory.js"></script>
    <script type="text/javascript">
    	
// 		var app = angular.module('FPDealCreationFPYearlySummaryApp', []);
		app.controller("FPDealCreationFPYearlySummaryController", ['$scope','$location','$anchorScroll','$http','$window','$sessionStorage','WebServiceFactory','$filter', function($scope,$location,$anchorScroll,$http,$window,$sessionStorage,WebServiceFactory,$filter,$index){

             $scope.moveTop = function()
			{
				$location.hash('PageHeading'); 
				$anchorScroll();
			};
			$scope.moveBottom = function()
			{
				$location.hash('includedFooter'); 
				$anchorScroll();
			};
			$scope.Prev = function() 
		    {   
		        window.location='FPDealCreationCostSummary';
		    };
		    $scope.Next = function() 
		    {   
		        window.location='FPDealCreationFinaliseDeal';
		    };
		    
		    var rpVrsId = $sessionStorage.rpDealVersionId;	
		    var getDealDetails = function(response) {				
		  		console.log(response);
		  		$scope.dealDetails = response.data;
		  		console.log("Approver Data")
		  		console.log($scope.dealDetails);
		  		console.log($scope.dealDetails[0].dealId);
		  		console.log($scope.dealDetails[0].customerId);
		  		console.log($scope.dealDetails[0].dealStartDate);
		  		console.log($scope.dealDetails[0].dealEndDate);
		  		console.log($scope.dealDetails[0].dealDescription);
		  		console.log($scope.dealDetails[0].dealStatus)
		  		console.log($scope.dealDetails[0].fpType=1? "Development" : "Maintenance");
		  		console.log(($scope.dealDetails[0].dealTypeId=1)? "Fixed Price" :"T & M");
		  		$scope.dealDetails[0].fpType = ($scope.dealDetails[0].fpType=1)? "Development" : "Maintenance";
		  		$scope.dealDetails[0].dealTypeId = ($scope.dealDetails[0].dealTypeId=1)? "Fixed Price" :"T & M";
		  		
		  		var dealEndDate  = $scope.dealDetails[0].dealEndDate;
		  		var date = new Date(dealEndDate.substring(0,10));
		  		console.log("End Date is......... " + date);
		  		var dateENd = $filter('date')(date,'dd/MM/yyyy');
		  		$scope.dealDetails[0].dealEndDate = dateENd;		  		
				console.log("dateEND     ...... "+dateENd)		  		
				var dealStartDate = $scope.dealDetails[0].dealStartDate;
				var date = new Date(dealStartDate.substring(0,10));
				var dateStart = $filter('date')(date,'dd/MM/yyyy');
				$scope.dealDetails[0].dealStartDate = dateStart;
				//alert('hi 66');
				$scope.dealDetails[0].rpVersionId = $sessionStorage.rpDealVersionId;	//arvind
				$scope.dealDetails[0].penaltyPercent = $scope.dealDetails[0].penaltyPercentage;	
				 var startDay = new Date(dateStart);
		          var endDay = new Date(dateENd);
		          var millisecondsPerDay = 1000 * 60 * 60 * 24;
		          var millisBetween =  endDay.getTime()-startDay.getTime() 
		          var days = millisBetween / millisecondsPerDay;
		          $scope.dealDetails[0].DealDuration = Math.floor(days);	
		  		console.log($scope.dealDetails[0].percentageClose);
		  		console.log($scope.dealDetails[0].currencyId);
		  		console.log($scope.dealDetails[0].dealDuration);
		  		console.log($scope.dealDetails[0].stageId);
			};
			WebServiceFactory.getDealDetails($sessionStorage.DealModel,rpVrsId).then(getDealDetails);
			
			
			var getVersionData =function(response)
			{
				console.log("Version Data");
				console.log(response);
				$scope.versionDetails = response.data;
				if($scope.versionDetails[0].dealcrmstagesdata2.dealStatusId==1)
					{
						$scope.versionDetails[0].dealStatus='Open';
					}
				else
					{
						$scope.versionDetails[0].dealStatus='Close';
					}
				
				if($scope.versionDetails[0].fpProjectTypeId==1)
					{
						$scope.versionDetails[0].projectType='Development - Fixed Price';
						$scope.masterRole=true;
					}
				else if($scope.versionDetails[0].fpProjectTypeId==2)
					{
						$scope.versionDetails[0].projectType='Development - Manage Capacity';
						$scope.masterRole=false;
					}
				else if($scope.versionDetails[0].fpProjectTypeId==3)
					{
						$scope.versionDetails[0].projectType='Maintenance - Fixed Price';
						$scope.masterRole=true;
					}
				else
					{
						$scope.versionDetails[0].projectType='Maintenance - Manage Capacity';
						$scope.masterRole=false;
					}
				
				
					
				
			};
			WebServiceFactory.getVersionData(rpVrsId).then(getVersionData);
		    
		}]);
    </script>
</head>
<body ng-app="RightPriceApp" ng-controller="FPDealCreationFPYearlySummaryController" >
    <div id="includedContent" ng-include="'${contextPath}/Portal/Header.jsp'"></div>
    <fieldset ng-disabled="loading || showLoader">
    <div class="container">
        <div class="divEmpty"></div>
        <div class="row marginBottom5px">
            <div class="col-sm-12">
                <h3 class="text-left" id="PageHeading">FP Deal Creation - Yearly Summary</h3>
            </div>
        </div>	
        <div>
            <form class="form-inline" role="form" name="RateCard" id="RateCard">
            	<div id="includedT&PStages" ng-include="'${contextPath}/Portal/FPdealCompletionStage.jsp'"></div>
                <div id="includedRateCardStages" ng-include="'${contextPath}/Rightprice/DealCreationInformationTable.jsp'"></div>
                <div class="row">
                    <div class="col-sm-12">
                        <div class="panel-group">
                            <div class="panel panel-info ">
                                <div class="panel-heading panelHeadingStyle">
                                    <div class="row ">
										<label class="control-label col-sm-10 "> FP Yearly Summary</label>
										<!-- <div class="col-sm-2 textAlignRight ">
											<a href="#" class="DownArrowColor" ng-click="ShowHideYearlySummary()"> &#9660;</a>
										</div> -->
									</div>
                                </div>
                                <div class="panel-body" >
									<div class="row marginBottom5px">
										<label
											class="control-label col-sm-2 textAlignRight required-Field">Country</label>
										<div class="col-sm-3">
											<select id="ddlCountry" class="form-control"
												placeholder="Please select" name="ddlCountry"
												ng-model="ddlCountryModel" required>
												<option value="" selected disabled>Please select</option>
												<option value="1">US</option>
												<option value="2">UK</option>
											</select>
										</div>
										<label
											class="control-label col-sm-3 textAlignRight required-Field">City</label>
										<div class="col-sm-3">
											<select id="ddlCity" class="form-control"
												placeholder="Please select" name="ddlCity"
												ng-model="ddlCityModel" required>
												<option value="" selected disabled>Please select</option>
												<option value="1">Mumbai</option>
												<option value="2">Pune</option>
												<option value="3">Chennai</option>
											</select>
										</div>
									</div>
									<div class="row marginBottom5px">
										<label
											class="control-label col-sm-2 textAlignRight required-Field">Tower</label>
										<div class="col-sm-3">
											<select id="ddlTower" class="form-control"
												placeholder="Please select" name="ddlTower"
												ng-model="ddlTowerModel" required>
												<option value="" selected disabled>Please select</option>
												<option value="1">Tower 1</option>
												<option value="2">Tower 2</option>
												<option value="3">Tower 3</option>
											</select>
										</div>
										<label
											class="control-label col-sm-3 textAlignRight required-Field">Version</label>
										<div class="col-sm-3">
											<select id="ddlVersion" class="form-control"
												placeholder="Please select" name="ddlVersion"
												ng-model="ddlVersionModel" required>
												<option value="" selected disabled>Please select</option>
												<option value="1">Version 1</option>
												<option value="2">Version 2</option>
												<option value="3">Version 3</option>
											</select>
										</div>
									</div>										
									<div class="row "><label class="control-label col-sm-10 redColor ">Note: All the values are in USD</label></div>
									<div class="row"><label class="control-label col-sm-10 "><h5><b>Revenue</b></h5></label></div>
									<div class="row marginBottom5px">
	                                    <div class="col-sm-12">
											<div class="table-responsive  " >
												<table 
													class="table clsTable table-striped table-bordered table-hover table-condensed "
													id="tblRevenue">
													<thead>
														<tr>
															<th class="width40per">Application Name</th>
															<th class="width20per">Stage Name</th>
															<th class="width20per">2017</th>
															<th class="width20per">Total</th>
														</tr>
													</thead>
													<tbody id="tBodyRevenue" >
														<tr>
															<td>MBUSA_DBPTS_04082017</td>
															<td>Steady</td>
															<td>837000.03</td>
															<td>837000.03</td>
														</tr>	
														<tr>
															<th colspan="2">Total Revenue</th>
															<td>837000.03</td>
															<td>837000.03</td>
														</tr>				
													</tbody>
												</table>
											</div>
										</div>
									</div>
									<div class="row">
					                	<label class="control-label col-sm-10 "><h5><b>Direct Cost</b></h5></label>
					                </div>
	                                <div class="row marginBottom5px">
	                                    <div class="col-sm-12">
											<div class="table-responsive  " >
												<table 
													class="table clsTable table-striped table-bordered table-hover table-condensed "
													id="tblDirectCost">
													<thead>
														<tr>
															<th class="width40per">Application Name</th>
															<th class="width20per">Stage Name</th>
															<th class="width20per">2017</th>
															<th class="width20per">Total</th>
														</tr>
													</thead>
													<tbody id="tBodyDirectCost" >
														<tr >
															<td>MBUSA_DBPTS_04082017</td>
															<td>Steady</td>
															<td>767998.04</td>
															<td>767998</td>
														</tr>
														<tr>
															<th colspan="2">Total Direct Cost</th>
															<td>767998.04</td>
															<td>767998</td>
														</tr>	
													</tbody>
												</table>
											</div>
										</div>
									</div>
									<div class="row">
					                	<label class="control-label col-sm-10 "><h5><b>PM%</b></h5></label>
					                </div>
	                                <div class="row marginBottom5px">
	                                    <div class="col-sm-12">
											<div class="table-responsive  " >
												<table 
													class="table clsTable table-striped table-bordered table-hover table-condensed "
													id="tblGMper">
													<thead>
														<tr>
															<th class="width40per">Application Name</th>
															<th class="width20per">Stage Name</th>
															<th class="width20per">2017</th>
															<th class="width20per">Total</th>
														</tr>
													</thead>
													<tbody id="tBodyGMper" >
														<tr>
															<td>MBUSA_DBPTS_04082017</td>
															<td>Steady</td>
															<td>8.24</td>
															<td>8.24</td>
														</tr>
														<tr>
															<th colspan="2">Overall</th>
															<td>8.24</td>
															<td>8.24</td>
														</tr>	
													</tbody>
												</table>
											</div>
										</div>
									</div>
									<div class="row">
					                	<label class="control-label col-sm-10 "><h5><b>Blended Rates</b></h5></label>
					                </div>
	                                <div class="row marginBottom5px">
	                                    <div class="col-sm-12">
											<div class="table-responsive  " >
												<table 
													class="table clsTable table-striped table-bordered table-hover table-condensed "
													id="tblBlendedRates">
													<thead>
														<tr>
															<th class="width40per">Application Name</th>
															<th class="width20per">Stage Name</th>
															<th class="width20per">2017</th>
															<th class="width20per">Total</th>
														</tr>
													</thead>
													<tbody id="tBodyBlendedRates" >
														<tr>
															<td>MBUSA_DBPTS_04082017</td>
															<td>Steady</td>
															<td>23.84</td>
															<td>23.84</td>
														</tr>
														<tr>
															<th colspan="2">Overall</th>
															<td>23.84</td>
															<td>23.84</td>
														</tr>	
													</tbody>
												</table>
											</div>
										</div>
									</div>
									<div class="row">
					                	<label class="control-label col-sm-10 "><h5><b>Efforts in person months</b></h5></label>
					                </div>
	                                <div class="row marginBottom5px">
	                                    <div class="col-sm-12">
											<div class="table-responsive  " >
												<table 
													class="table clsTable table-striped table-bordered table-hover table-condensed "
													id="tblEfforts">
													<thead>
														<tr>
															<th>Application Name</th>
															<th>Stage Name</th>
															<th>location</th>
															<th class="width20per">2017</th>
															<th class="width20per">Total</th>
														</tr>
													</thead>
													<tbody id="tBodyEfforts" >
														<tr>
															<td>MBUSA_DBPTS_04082017</td>
															<td>Steady</td>
															<th>Offshore</th>
															<td>167.05</td>
															<td>167.05</td>
														</tr>
														<tr>
															<td>MBUSA_DBPTS_04082017</td>
															<td>Steady</td>
															<th>Onsite</th>
															<td>25</td>
															<td>25</td>
														</tr>	
														<tr>
															<td>MBUSA_DBPTS_04082017</td>
															<td>Steady</td>
															<th>Offshore %</th>
															<td>86.98</td>
															<td>86.98</td>
														</tr>
														<tr>
															<td>MBUSA_DBPTS_04082017</td>
															<td>Steady</td>
															<th>Total Offshore</th>
															<td>167.05</td>
															<td>167.05</td>
														</tr>
														<tr>
															<td>MBUSA_DBPTS_04082017</td>
															<td>Steady</td>
															<th>Total Onsite</th>
															<td>25</td>
															<td>25</td>
														</tr>
														<tr>
															<td>MBUSA_DBPTS_04082017</td>
															<td>Steady</td>
															<th>Total</th>
															<td>192.05</td>
															<td>192.05</td>
														</tr>
														<tr>
															<td>MBUSA_DBPTS_04082017</td>
															<td>Steady</td>
															<th>Offshore %</th>
															<td>86.98</td>
															<td>86.98</td>
														</tr>
													</tbody>
												</table>
											</div>
										</div>
									</div>
									<div class="row">
					                	<label class="control-label col-sm-10 "><h5><b>Project specific SGA with Agile Cost</b></h5></label>
					                </div>
	                                <div class="row marginBottom5px">
	                                    <div class="col-sm-12">
											<div class="table-responsive  " >
												<table 
													class="table clsTable table-striped table-bordered table-hover table-condensed "
													id="tblProjectspecificSGA">
													<thead>
														<tr>
															<th class="width40per">Application Name</th>
															<th class="width20per">Stage Name</th>
															<th class="width20per">2017</th>
															<th class="width20per">Total </th>
														</tr>
													</thead>
													<tbody id="tBodyProjectspecificSGA" >
														<tr>
															<td>MBUSA_DBPTS_04082017</td>
															<td>Steady</td>
															<td>7442</td>
															<td>7442</td>
														</tr>
														<tr>
															<th colspan="2">Total SGA</th>
															<td>7442</td>
															<td>7442</td>
														</tr>	
													</tbody>
												</table>
											</div>
										</div>
									</div>
									<div class="divEmptyThrice"></div>
									<fieldset disabled>
									<div class="row marginBottom5px">
					                	<label class="control-label col-sm-2 ">Onsite hours per month :</label>
					                	<div class="col-sm-3">
					                		<input class="form-control" id="txtOnsiteHours" name="txtOnsiteHours" ng-model="txtOnsiteHoursModel" required>
					                	</div>
					                </div>
					                <div class="row ">
					                	<label class="control-label col-sm-2 ">Offshore hours per month :</label>
					                	<div class="col-sm-3">
					                		<input class="form-control" id="txtOffshoreHours" name="txtOffshoreHours" ng-model="txtOffshoreHoursModel" required>
					                	</div>
					                </div>
					                </fieldset>
					                <div class="divEmptyThrice"></div>
									<div class="row text-center">
										<div class="col-sm-12">
											<button type="button" class="btn btn-info btnSpace"id="btnClientPrev" ng-click="Prev()">Prev</button>
											<!-- <button type="button" class="btn btn-info btnSpace"id="btnClientNext" ng-click="Next()">Next</button> -->
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
