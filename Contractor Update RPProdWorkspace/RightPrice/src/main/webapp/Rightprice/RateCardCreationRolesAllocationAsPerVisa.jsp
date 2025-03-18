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
	<script src="${contextPath}/resources/js/jqueryValidations.js"></script>
    <link href="${contextPath}/resources/css/ie10-viewport-bug-workaround.css" rel="stylesheet" />
	<link href="${contextPath}/resources/css/sticky-footer-navbar.css" rel="stylesheet" />
	<script src="${contextPath}/resources/js/ie10-viewport-bug-workaround.js"></script>
	<script src="${contextPath}/resources/js/angular.js"></script>
	<script src="${contextPath}/resources/js/angular-ui.min.js"></script>
	<script src="${contextPath}/resources/js/angular-messages.js"></script>
	<script src="${contextPath}/resources/js/ngStorage.js"></script>
	<script src="${contextPath}/resources/js/loader.js"></script>
    <script type="text/javascript">
		var app = angular.module('RoleAlloactionForVisaTypesApp', []);
		app.controller("RoleAlloactionForVisaTypesController", ['$scope','$http','$window', function($scope,$http,$window,$index) {
			 $scope.RoleAlloactionHidden = true;
             $scope.ShowHideRoleAlloaction = function () {
                $scope.RoleAlloactionHidden = $scope.RoleAlloactionHidden ? false : true;
            };
		      
		}]);
    </script>
</head>
<body ng-app="RoleAlloactionForVisaTypesApp" ng-controller="RoleAlloactionForVisaTypesController" >
    <div id="includedContent" ng-include="'/RightPricePortal/Portal/Header.jsp'"></div>
    <div class="container">
        <div class="divEmpty"></div>
        <div class="row marginBottom5px">
            <div class="col-sm-12">
                <h3 class="text-left">Rate Card Creation - Roles Allocation As Per  Visa</h3>
            </div>
        </div>	
        <div>
            <form class="form-inline" role="form" name="Master" id="Master">
            	<div id="includedRateCardStages" ng-include="'/RightPricePortal/Portal/RateCardCompletionStage.jsp'"></div>
				<div class="row">
				   <div class="col-sm-12">
				  		<div class="panel-group">
				            <div class="panel panel-info ">
	                            <div class="panel-heading panelHeadingStyle">
	                                <div class="row ">
										<label class="control-label col-sm-10 ">Role Alloaction for  Visa Types</label>
										<div class="col-sm-2 textAlignRight">
											<a href="#" class="DownArrowColor" ng-click="ShowHideRoleAlloaction()"> &#9660;</a>
										</div>
									</div>
	                            </div>
	                            <div class="panel-body" ng-hide = "RoleAlloactionHidden">
									<div class="row">
	                                    <div class="col-sm-12">
											<div class="table-responsive  " >
												<table 
													class="table clsTable table-striped table-bordered table-hover table-condensed "
													id="tblRoleAlloaction">
													<thead>
														<tr>
															<th>Select ed Master Roles</th>
															<th>Onsite</th>
															<th>Offshore</th>
															<th>Domestic</th>
															<th>Deputed</th>
															<th>Short Term</th>
															<th>Offshore</th>
														</tr>
													</thead>
													<tbody id="tBodyRoleAlloaction" >
														<tr >
															<td>Cloud/Consulting/Cloud Consulting/Consultant/Junior-1 </td>
															<td><input type="checkbox" name="cbxOnsiteRow1" id="cbxOnsiteRow1" class="margingRightChkBx"></td>
															<td><input type="checkbox" name="cbxOffshoreRow1" id="cbxOffshoreRow1" class="margingRightChkBx"></td>
															<td><input type="checkbox" name="cbxDomesticRow1" id="cbxDomesticRow1" class="margingRightChkBx"></td>
															<td><input type="checkbox" name="cbxDeputedRow1" id="cbxDeputedRow1" class="margingRightChkBx"></td>
															<td><input type="checkbox" name="cbxShortTermRow1" id="cbxShortTermRow1" class="margingRightChkBx"></td>
															<td><input type="checkbox" name="cbxOffshore_2Row1" id="cbxOffshore_2Row1" class="margingRightChkBx"></td>
														</tr>
														<tr >
															<td>Cloud/Consulting/Cloud Consulting/Consultant/Junior-2 </td>
															<td><input type="checkbox" name="cbxOnsiteRow2" id="cbxOnsiteRow2" class="margingRightChkBx"></td>
															<td><input type="checkbox" name="cbxOffshoreRow2" id="cbxOffshoreRow2" class="margingRightChkBx"></td>
															<td><input type="checkbox" name="cbxDomesticRow2" id="cbxDomesticRow2" class="margingRightChkBx"></td>
															<td><input type="checkbox" name="cbxDeputedRow2" id="cbxDeputedRow2" class="margingRightChkBx"></td>
															<td><input type="checkbox" name="cbxShortTermRow2" id="cbxShortTermRow2" class="margingRightChkBx"></td>
															<td><input type="checkbox" name="cbxOffshore_2Row2" id="cbxOffshore_2Row2" class="margingRightChkBx"></td>
														</tr>	
														<tr >
															<td>Cloud/Consulting/Cloud Consulting/Consultant/Junior-3 </td>
															<td><input type="checkbox" name="cbxOnsiteRow3" id="cbxOnsiteRow3" class="margingRightChkBx"></td>
															<td><input type="checkbox" name="cbxOffshoreRow3" id="cbxOffshoreRow3" class="margingRightChkBx"></td>
															<td><input type="checkbox" name="cbxDomesticRow3" id="cbxDomesticRow3" class="margingRightChkBx"></td>
															<td><input type="checkbox" name="cbxDeputedRow3" id="cbxDeputedRow3" class="margingRightChkBx"></td>
															<td><input type="checkbox" name="cbxShortTermRow3" id="cbxShortTermRow3" class="margingRightChkBx"></td>
															<td><input type="checkbox" name="cbxOffshore_2Row3" id="cbxOffshore_2Row3" class="margingRightChkBx"></td>
														</tr>	
														<tr >
															<td>Cloud/Consulting/Cloud Consulting/Consultant/Junior-4 </td>
															<td><input type="checkbox" name="cbxOnsiteRow4" id="cbxOnsiteRow4" class="margingRightChkBx"></td>
															<td><input type="checkbox" name="cbxOffshoreRow4" id="cbxOffshoreRow4" class="margingRightChkBx"></td>
															<td><input type="checkbox" name="cbxDomesticRow4" id="cbxDomesticRow4" class="margingRightChkBx"></td>
															<td><input type="checkbox" name="cbxDeputedRow4" id="cbxDeputedRow4" class="margingRightChkBx"></td>
															<td><input type="checkbox" name="cbxShortTermRow4" id="cbxShortTermRow4" class="margingRightChkBx"></td>
															<td><input type="checkbox" name="cbxOffshore_2Row4" id="cbxOffshore_2Row4" class="margingRightChkBx"></td>
														</tr>
														<tr >
															<td>Cloud/Consulting/Cloud Consulting/Consultant/Junior-5 </td>
															<td><input type="checkbox" name="cbxOnsiteRow5" id="cbxOnsiteRow5" class="margingRightChkBx"></td>
															<td><input type="checkbox" name="cbxOffshoreRow5" id="cbxOffshoreRow5" class="margingRightChkBx"></td>
															<td><input type="checkbox" name="cbxDomesticRow5" id="cbxDomesticRow5" class="margingRightChkBx"></td>
															<td><input type="checkbox" name="cbxDeputedRow5" id="cbxDeputedRow5" class="margingRightChkBx"></td>
															<td><input type="checkbox" name="cbxShortTermRow5" id="cbxShortTermRow5" class="margingRightChkBx"></td>
															<td><input type="checkbox" name="cbxOffshore_2Row5" id="cbxOffshore_2Row5" class="margingRightChkBx"></td>
														</tr>
													</tbody>
												</table>
											</div>
										</div>
									</div>
                            		<div class="divEmptyThrice"></div>
                            		<div class="divEmptyThrice"></div>
                            		<div class="row text-center">
										<div class="col-sm-12">
											<button type="button" class="btn btn-primary btnSpace" id="btnMapClientSave">Save</button>
											<button type="button" class="btn btn-danger" id="btnMapClientCancel">Cancel</button>						
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
