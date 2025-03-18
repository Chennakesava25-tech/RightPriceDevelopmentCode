<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>Eviden RightPrice Portal</title>
    <meta charset="utf-8">
    <meta name="csrf-token" content="${_csrf.token}" />
    <meta name="_csrf_header" content="${_csrf.headerName}" />
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="X-UA-Compatible" content="IE=Edge" />
    <link href="${pageContext.request.contextPath}/Support/CSS/bootstrap.css" rel="stylesheet"/>
    <link href="${pageContext.request.contextPath}/Support/CSS/SAPStyleSheet.css" rel="stylesheet"/>
    <link href="${pageContext.request.contextPath}/Support/CSS/loader.css" rel="stylesheet"/>
    <script src="${pageContext.request.contextPath}/Support/js/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/Support/js/bootstrap.js"></script>
    <script src="${pageContext.request.contextPath}/Support/js/jquery.serializeJSON.min.js"></script>
    <script src="${pageContext.request.contextPath}/Support/js/jquery-ui.js"></script>
    <link href="${pageContext.request.contextPath}/Support/CSS/jquery-ui.css" rel="stylesheet"/>
    <link href="${pageContext.request.contextPath}/Support/CSS/bootstrap-dialog.css" rel="stylesheet"/>
    <script src="${pageContext.request.contextPath}/Support/js/bootstrap-dialog.js"></script>
    <link href="${pageContext.request.contextPath}/Support/CSS/ie10-viewport-bug-workaround.css" rel="stylesheet"/>
	<link href="${pageContext.request.contextPath}/Support/CSS/sticky-footer-navbar.css" rel="stylesheet"/>
	<script src="${pageContext.request.contextPath}/Support/js/ie10-viewport-bug-workaround.js"></script>
	<script src="${pageContext.request.contextPath}/Support/js/angular.js"></script>
	<script src="${pageContext.request.contextPath}/Support/js/jqueryValidations.js"></script>
    <script type="text/javascript">
    	
		var app = angular.module('DealCreationWhatIfMainApp', []);
		app.controller("DealCreationWhatIfMainController", ['$scope','$http','$window', function($scope,$http,$window,$index) {
			 $scope.ApplicationWiseHidden = true;
             $scope.ShowHideApplicationWise = function () {
                $scope.ApplicationWiseHidden = $scope.ApplicationWiseHidden ? false : true;
            };
		}]);
    </script>
</head>
<body ng-app="DealCreationWhatIfMainApp" ng-controller="DealCreationWhatIfMainController" >
    <div id="includedContent" ng-include="'/RightPricePortal/Portal/Header.jsp'"></div>
    <div class="container">
        <div class="divEmpty"></div>
        <div class="row marginBottom5px">
            <div class="col-sm-12">
                <h3 class="text-left">Deal Creation - What If Main</h3>
            </div>
        </div>	
        <div>
            <form class="form-inline" role="form" name="RateCard" id="RateCard">
            	<div id="includedT&PStages" ng-include="'/RightPricePortal/Portal/T&MdealCompletionStage.jsp'"></div>
                <div class="row">
                    <div class="col-sm-12">
                        <div class="panel-group">
                            <div class="panel panel-info ">
                                <div class="panel-heading panelHeadingStyle">
                                    <div class="row ">
										<label class="control-label col-sm-10 ">What If Main</label>
										<div class="col-sm-2 textAlignRight ">
											<a href="#" class="DownArrowColor" ng-click="ShowHideApplicationWise()"> &#9660;</a>
										</div>
									</div>
                                </div>
                                <div class="panel-body" ng-hide = "ApplicationWiseHidden">
									<div class="row marginBottom5px">
	                                    <div class="col-sm-12">
											<div class="table-responsive  " >
												<table 
													class="table clsTable table-striped table-bordered table-hover table-condensed "
													id="tblParticulars">
													<thead>
														<tr>
															<th>Particulars</th>
															<th>Enter Revenue For Final Price </th>
															<th>Threshold For BUH Approval</th>
															<th>Threshold For CDO Approval </th>
															<th>Threshold For CEO Approval</th>
														</tr>
													</thead>
													<tbody id="tBodyParticulars" >
														<tr >
															<td>Revenue</td>
															<td><input type="text" class="form-control" id="txtRevenueFinalPrice" name="txtRevenueFinalPrice" ng-model="txtRevenueFinalPricemodel" required></td>
															<td><input type="text" class="form-control" id="txtRevenueBUHApproval" name="txtRevenueBUHApproval" ng-model="txtRevenueBUHApprovalmodel" required></td>
															<td><input type="text" class="form-control" id="txtRevenueCDOApproval" name="txtRevenueCDOApproval" ng-model="txtRevenueCDOApprovalmodel" required></td>
															<td><input type="text" class="form-control" id="txtRevenueCEOApproval" name="txtRevenueCEOApproval" ng-model="txtRevenueCEOApprovalmodel" required></td>
														</tr>
														<tr>
															<td>Project Margin %</td>
															<td></td>
															<td></td>
															<td></td>
															<td></td>
														</tr>	
														<tr>
															<td>Direct Cost</td>
															<td></td>
															<td></td>
															<td></td>
															<td></td>
														</tr>	
														<tr>
															<td>Project Margin</td>
															<td></td>
															<td></td>
															<td></td>
															<td></td>
														</tr>
														<tr>
															<td>Project Specific SGA</td>
															<td></td>
															<td></td>
															<td></td>
															<td></td>
														</tr>
														<tr>
															<td>Operating Margin</td>
															<td></td>
															<td></td>
															<td></td>
															<td></td>
														</tr>
														<tr>
															<td>Operating Margin %</td>
															<td></td>
															<td></td>
															<td></td>
															<td></td>
														</tr>
														<tr>
															<td>Volume Discount + Penalty (%)</td>
															<td></td>
															<td></td>
															<td></td>
															<td></td>
														</tr>
														<tr>
															<td>Margin After Discount</td>
															<td></td>
															<td></td>
															<td></td>
															<td></td>
														</tr>		
														<tr>
															<td>OM% after Volume Discount</td>
															<td></td>
															<td></td>
															<td></td>
															<td></td>
														</tr>		
														<tr>
															<td>Blended Rate / Hr</td>
															<td></td>
															<td></td>
															<td></td>
															<td></td>
														</tr>		
														<tr>
															<td>Derived Onsite Rate / Hr</td>
															<td></td>
															<td></td>
															<td></td>
															<td></td>
														</tr>	
														<tr>
															<td>Derived Offshore Rate / Hr</td>
															<td></td>
															<td></td>
															<td></td>
															<td></td>
														</tr>								
													</tbody>
												</table>
											</div>
										</div>
									</div>
									 <div class="row marginBottom5px">
					                	<label class="control-label col-sm-10 "><h5><b>Revenue entered must be between (xxxx) and (yyyy)</b></h5></label>
					                </div>
	                                <div class="row marginBottom5px">
	                                    <div class="col-sm-12">
											<div class="table-responsive  " >
												<table 
													class="table clsTable table-striped table-bordered table-hover table-condensed "
													id="tblEfforts">
													<thead>
														<tr>
															<th>Efforts_in_person_months</th>
															<th>Yr_X</th>
															<th>Yr_X+ 1</th>
															<th>Total</th>
														</tr>
													</thead>
													<tbody id="tBodyEfforts" >
														<tr >
															<td>INC Efforts</td>
															<td>20.5</td>
															<td>20.5</td>
															<td>41</td>
														</tr>
														<tr>
															<td>L1 Efforts</td>
															<td>0</td>
															<td>0</td>
															<td>0</td>
														</tr>	
														<tr>
															<td>H1 Efforts</td>
															<td>0</td>
															<td>0</td>
															<td>0</td>
														</tr>	
														<tr>
															<td>B1 Efforts</td>
															<td>4.5</td>
															<td>4.5</td>
															<td>9</td>
														</tr>
														<tr>
															<td>Onsite Efforts</td>
															<td>25</td>
															<td>25</td>
															<td>50</td>
														<tr>
															<td>Offshore Efforts</td>
															<td>167.05</td>
															<td>167.05</td>
															<td>334.1</td>
														</tr>
														<tr>
															<td>Total Efforts</td>
															<td>192.05</td>
															<td>192.05</td>
															<td>384.1</td>
														<tr>
															<td>INC/Onsite %</td>
															<td>82</td>
															<td>82</td>
															<td>164</td>
														</tr>
														<tr>
															<td>L1/Deputed %</td>
															<td>0</td>
															<td>0</td>
															<td>0</td>
														</tr>
														<tr>
															<td>Offshore %</td>
															<td>86.98</td>
															<td>86.98</td>
															<td>173.96</td>
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
	<div id="footer" ng-include="'/RightPricePortal/Portal/Footer.jsp'"></div>
	<div id="Footer" ng-include="'/RightPricePortal/Portal/TopBottomNavigation.jsp'"></div>
</body>
</html>
