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
    <script type="text/javascript">
    	
// 		var app = angular.module('MasterRightPriceAuditApp', []);
		app.controller("MasterRightPriceAuditController", ['$scope','$http','$window', function($scope,$http,$window,$index) {
			/*  $scope.ViewHidden = true;
             $scope.ShowHideView = function () {
                $scope.ViewHidden = $scope.ViewHidden ? false : true ;
            }; */
		}]);
    </script>
</head>
<body ng-app="RightPriceApp" ng-controller="MasterRightPriceAuditController" >
  <div id="includedContent" ng-include="'${contextPath}/Portal/Header.jsp'"></div>
    <fieldset ng-disabled="loading || showLoader">
    <div class="container">
        <div class="divEmpty"></div>
        <div class="row marginBottom5px">
            <div class="col-sm-12">
                <h3 class="text-left">Master - Right Price Audit</h3>
            </div>
        </div>	
        <div>
            <form class="form-inline" role="form" name="Master" id="Master">
            <div class="row">
					<div class="col-sm-12">
						<div class="panel-group">
							<div class="panel panel-info">
								<div class="panel-heading panelHeadingStyle ">
									<div class="row ">
										<label class="control-label col-sm-10 ">View Master Rate Audit - Trail</label>
										<!-- <div class="col-sm-2 textAlignRight ">
											<a href="#" class="DownArrowColor" ng-click="ShowHideView()"> &#9660;</a>
										</div> -->
									</div>
								</div>
								<div class="panel-body">
									<div class="row marginBottom5px">
                                        <label class="control-label col-sm-2 textAlignRight required-Field">Country</label>
                                        <div class="col-sm-3">
                                           <select id="ddlViewCountry" class="form-control" placeholder="Please select" name="ddlViewCountry"
                                           		ng-model="ddlViewCountryModel" required>
												<option value="" selected disabled>Please select</option>
												<option value="1">India</option>
												<option value="2">US</option>
												<option value="3">UK</option>
											</select>
                                        </div>
                                        <label class="control-label col-sm-3 textAlignRight required-Field">Catgegory</label>
                                        <div class="col-sm-3">
                                           <select id="ddlViewCatgegory" class="form-control" placeholder="Please select" name="ddlViewCatgegory"
                                           		ng-model="ddlViewCatgegoryModel" required>
												<option value="" selected disabled>Please select</option>
												<option value="1">A</option>
												<option value="2">B</option>
												<option value="3">C</option>
											</select>
                                        </div>
									</div>
                                    <div class="divEmptyThrice"></div>
                                    <div class="row text-center">
										<div class="col-sm-12">
											<button type="button" class="btn btn-primary btnSpace" id="btnViewSearch">Search</button>
											<button type="button" class="btn btn-primary btnSpace" id="btnDownloadViewAuditTrail">Download Audit Trail</button>
										</div>
										<!-- <div class="col-sm-2">	
											<button type="button" class="btn btn-primary btnSpace" id="btnViewViewHistory">View History</button>
										</div> -->
									</div>
									<div class="divEmptyThrice"></div>
                                    <div class="row ">
	                                    <div class="col-sm-12">
											<div class="table-responsive  " >
												<table 
													class="table clsTable table-striped table-bordered table-hover table-condensed "
													id="tblViewBillRate">
													<thead>
														<tr>
															<th>Category</th>
															<th>Band Grade</th>
															<th>Low</th>
															<th>Medium</th>
															<th>High</th>
															<th>Very High</th>
															<!-- <th>Start Date</th>
															<th>End Date</th> -->
														</tr>
													</thead>
													<tbody id="tBodyViewBillRate" >
														<tr >
															<td>A</td>
															<td></td>
															<td></td>
															<td></td>
															<td></td>
															<td></td>
														</tr>
														<tr>
															<td>B</td>
															<td></td>
															<td></td>
															<td></td>
															<td></td>
															<td></td>
														</tr>	
														<tr>
															<td>C</td>
															<td></td>
															<td></td>
															<td></td>
															<td></td>
															<td></td>
														</tr>	
														<tr>
															<td>D</td>
															<td></td>
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
<div id="loader" ng-include="'${contextPath}/Portal/loader.jsp'"></div>
</body>
</html>
