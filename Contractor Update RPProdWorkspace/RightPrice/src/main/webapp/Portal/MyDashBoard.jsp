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
<script type="text/javascript">
var app = angular.module('MyDashBoardApp', []);
app.controller("MyDashBoardController", ['$scope','$http','$window', function($scope,$http,$window,$index) {
}]);
    </script>
</head>
<body ng-app="MyDashBoardApp" ng-controller="MyDashBoardController" >
    <div id="includedContent" ng-include="'${contextPath}/Portal/Header.jsp'"></div>
	    	<div class="container" ng-focus="setDashboardData()">
	        <div class="divEmpty"></div>
	        <div class="row marginBottom5px">
	            <div class="col-sm-12">
	                <h3 class="text-left">My Dash Board</h3>
	            </div>
	        </div>	
	        <div>
            <form class="form-inline" role="form" name="RateCard" id="RateCard">
				<div class="row">
				   <div class="col-sm-12">
				  		<div class="panel-group">
				            <div class="panel panel-info ">
	                            <div class="panel-heading panelHeadingStyle">
	                                <div class="row ">
										<label class="control-label col-sm-10 ">Dash Board</label>
										<!-- <div class="col-sm-2 textAlignRight">
											<a href="#" class="DownArrowColor" ng-click="ShowHideAddContractor()"> &#9660;</a>
										</div> -->
									</div>
	                            </div>
	                            <div class="panel-body">
									<div class="row marginBottom5px">
	                                    <div class="col-sm-6">
											<div class="table-responsive  " >
												<table 
													class="table clsTable table-striped table-bordered table-hover table-condensed "
													id="tblDashBoard">
													<thead>
														<tr>
															<th class="width10per">Status</th>
															<th class="width10per">Draft</th>
															<th class="width10per">BUH</th>
															<th class="width10per">RiskManagers</th>
															<th class="width10per">CDO</th>
															<th class="width10per">CEO</th>
															<th class="width10per">Won</th>
															<th class="width10per">Lost</th>
															<th class="width10per">Rejected</th>
															<th class="width10per">Completed</th>
														</tr>
													</thead>
													<tbody id="tBodyDashBoardr" >
														<!-- <tr >
															<td>Rate Cards</td>
															<td><input name="txtRateCardsBUHRow1" type="text" class="form-control" id="txtRateCardsBUHRow1"
                                            	   						ng-model="txtRateCardsBUHRow1Model" required></td>
															<td><input name="txtRateCardsRiskManagersRow1" type="text" class="form-control" id="txtRateCardsRiskManagersRow1"
                                            	   						ng-model="txtRateCardsRiskManagersRow1Model" required></td>
															<td><input name="txtRateCardsCDORow1" type="text" class="form-control" id="txtRateCardsCDORow1"
                                            	   						ng-model="txtRateCardsCDORow1Model" required></td>
															<td><input name="txtRateCardsCEORow1" type="text" class="form-control" id="txtRateCardsCEORow1"
                                            	   						ng-model="txtRateCardsCEORow1Model" required></td>
															<td><input name="txtRateCardsWonRow1" type="text" class="form-control" id="txtRateCardsWonRow1"
                                            	   						ng-model="txtRateCardsWonRow1Model" required></td>
															<td><input name="txtRateCardsLostRow1" type="text" class="form-control" id="txtRateCardsLostRow1"
                                            	   						ng-model="txtRateCardsLostRow1Model" required></td>
														</tr> -->
														<tr>															
															<td>Rate Cards</td>
															<td><button type="button" id="btnRateCardsDraft">4</button></td>
															<td><button type="button" id="btnRateCardsBUH">5</button></td>
															<td><button type="button" id="btnRateCardsRiskManagers">6</button></td>
															<td><button type="button" id="btnRateCardsCDO">3</button></td>
															<td><button type="button" id="btnRateCardsCEO">8</button></td>
															<td><button type="button" id="btnRateCardsWon">6</button></td>
															<td><button type="button" id="btnRateCardsLost">5</button></td>
															<td><button type="button" id="btnRateCardsRejected">3</button></td>
															<td><button type="button" id="btnRateCardsCompleted">5</button></td>
															
														</tr>	
														<tr>															
															<td>Deals</td>
															<td><button type="button" id="btnDealsDraft">4</button></td>
															<td><button type="button" id="btnDealsBUH">4</button></td>
															<td><button type="button" id="btnDealsRiskManagers">6</button></td>
															<td><button type="button" id="btnDealsCDO">7</button></td>
															<td><button type="button" id="btnDealsCEO">4</button></td>
															<td><button type="button" id="btnDealsWon">8</button></td>
															<td><button type="button" id="btnDealsLost">3</button></td>
															<td><button type="button" id="btnDealRejected">4</button></td>
															<td><button type="button" id="btnDealCompleted">5</button></td>
														</tr>	
													</tbody>
												</table>
											</div>
										</div>
									</div>
									<label class="control-label col-sm-10 "><h5><b>Rate Card Details - BUH</b></h5></label>
									<div class="row marginBottom5px">
	                                    <div class="col-sm-12">
											<div class="table-responsive">
												<table 
													class="table clsTable table-striped table-bordered table-hover table-condensed "
													id="tblRateCardDetails">
													<thead>
														<tr>
															<th class ="width12per">Rate Card Id</th>
															<th>Customer</th>
															<th>RC Name</th>
															<th>Start Date</th>
															<th>End Date</th>
															<th>Country</th>
															<th>City</th>
															<th>Sales Vol</th>
															<th>Industry</th>
															<th>Manual</th>
														</tr>
													</thead>
													<tbody id="tBodyRateCardDetails" >
														<tr >
															
															<td><a href="RateCardCreationDetails.jsp">RC10001</a></td>
															<td>Daimler Trucks North America LLC</td>
															<td>DTNA-AEM-Beeline-2017</td>
															<td>9/8/2017</td>
															<td>9/8/2019</td>
															<td>India</td>
															<td>Mumbai</td>
															<td>2500</td>
															<td>IT</td>
															<td>Yes</td>
														</tr>
														<tr>															
															<td><a href="RateCardCreationDetails.jsp">RC10002</a></td>
															<td>Daimler Trucks North America LLC</td>
															<td>2017 rib itt br test</td>
															<td>9/8/2017</td>
															<td>9/8/2019</td>
															<td>India</td>
															<td>Pune</td>
															<td>1500</td>
															<td>IT</td>
															<td>No</td>
														</tr>	
														<tr>															
															<td><a href="RateCardCreationDetails.jsp">RC10002</a></td>
															<td>American Express</td>
															<td>2017 rib itt brr</td>
															<td>9/8/2017</td>
															<td>9/8/2019</td>
															<td>India</td>
															<td>Mumbai</td>
															<td>1200</td>
															<td>IT</td>
															<td>Yes</td>
														</tr>	
													</tbody>
												</table>
											</div>
										</div>
									</div>
									<label class="control-label col-sm-10 "><h5><b>Deal Details - BUH</b></h5></label>
									<div class="row">
	                                    <div class="col-sm-12">
											<div class="table-responsive  " >
												<table 
													class="table clsTable table-striped table-bordered table-hover table-condensed "
													id="tblDealDetails">
													<thead>
														<tr>
															<th class ="width12per">Customer</th>
															<th>Deal Id</th>
															<th class="width15per">Deal Description</th>
															<th>Deal Type</th>
															<th class ="width12per">Onsite Facility</th>
															<th>Start Date</th>
															<th>End Date</th>
															<th>Sales Vol</th>
															<th>Deal Status</th>
														</tr>
													</thead>
													<tbody id="tBodyDealDetails">
														<tr >
															
															<td>Daimler Trucks North America LLC</td>
															<td><a href="#">933502</a></td>
															<td>Honda T&M engagement</td>
															<td>FP</td>
															<td>Client</td>
															<td>9/8/2017</td>
															<td>9/8/2019</td>
															<td>2500</td>
															<td>Open</td>
														</tr>
														<tr>															
															<td>American Express</td>
															<td><a href="#">933406</a></td>
															<td>Scripps - PMO Business Analyst - Sept 2017</td>
															<td>FP</td>
															<td></td>
															<td>29/8/2017</td>
															<td>11/8/2019</td>
															<td>2200</td>
															<td>Open</td>
														</tr>	
														<tr>															
															<td>Daimler Trucks North America LLC</td>
															<td><a href="#">933407</a></td>
															<td>DTNA-New-T&M-Future Billable-Campus Hires</td>
															<td>T&M</td>
															<td>Client</td>
															<td>12/8/2017</td>
															<td>19/8/2019</td>
															<td>1500</td>
															<td>Open</td>
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
				</div>	
	      	</form>
        </div>
    </div>
	<div id="footer" ng-include="'${contextPath}/Portal/Footer.jsp'"></div>
</body>
</html>
