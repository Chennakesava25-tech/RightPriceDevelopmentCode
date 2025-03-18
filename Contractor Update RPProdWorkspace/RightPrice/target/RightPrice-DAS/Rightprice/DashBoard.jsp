<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>Eviden  RightPrice Portal</title>
    <meta charset="utf-8">
    <meta name="csrf-token" content="${_csrf.token}" />
    <meta name="_csrf_header" content="${_csrf.headerName}" />
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="X-UA-Compatible" content="IE=Edge" />
    <link href="${pageContext.request.contextPath}/Support/CSS/bootstrap.css" rel="stylesheet" />
    <link href="${pageContext.request.contextPath}/Support/CSS/SAPStyleSheet.css" rel="stylesheet" />
    <link href="${pageContext.request.contextPath}/Support/CSS/loader.css" rel="stylesheet" />
    <script src="${pageContext.request.contextPath}/Support/js/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/Support/js/bootstrap.js"></script>
    <script src="${pageContext.request.contextPath}/Support/js/jquery.serializeJSON.min.js"></script>
    <script src="${pageContext.request.contextPath}/Support/js/jquery-ui.js"></script>
    <link href="${pageContext.request.contextPath}/Support/CSS/jquery-ui.css" rel="stylesheet" />
    <link href="${pageContext.request.contextPath}/Support/CSS/bootstrap-dialog.css" rel="stylesheet" />
    <script src="${pageContext.request.contextPath}/Support/js/bootstrap-dialog.js"></script>
    <link href="${pageContext.request.contextPath}/Support/CSS/ie10-viewport-bug-workaround.css" rel="stylesheet" />
	<link href="${pageContext.request.contextPath}/Support/CSS/sticky-footer-navbar.css" rel="stylesheet" />
	<script src="${pageContext.request.contextPath}/Support/js/ie10-viewport-bug-workaround.js"></script>
	<script src="${pageContext.request.contextPath}/Support/js/angular.js"></script>
	<script src="${pageContext.request.contextPath}/Support/js/jqueryValidations.js"></script>
    <script type="text/javascript">
    	
		var app = angular.module('MyDashBoardApp', []);
		app.controller("MyDashBoardController", ['$scope','$http','$window', function($scope,$http,$window,$index) {
			
		      
		}]);
    </script>
</head>
<body ng-app="MyDashBoardApp" ng-controller="MyDashBoardController" >
    <div id="includedContent" ng-include="'/RightPricePortal/Portal/Header.jsp'"></div>
	    <div class="container">
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
									<label class="control-label col-sm-10 "><h5><b>Rate Card Pending Delivery</b></h5></label>
									<div class="row text-center">
										<div class="col-sm-12">		
											<button type="button" class="btn btn-primary btnSpace" id="btnViewRateCardspendingDelivery">View Rate Cards pending Delivery</button>
											<button type="button" class="btn btn-primary btnSpace" id="btnCreateNewRateCard">Create New Rate Card</button>
											<button type="button" class="btn btn-primary btnSpace" id="btnCreateNewDeal">Create New Deal</button>
										</div>
									</div>
									<div class="divEmptyThrice"></div>
									<div class="row marginBottom5px">
	                                    <div class="col-sm-12">
											<div class="table-responsive">
												<table 
													class="table clsTable table-striped table-bordered table-hover table-condensed "
													id="tblRateCardDetails">
													<thead>
														<tr>
															<th>Action</th>
															<th>Rate Card Id</th>
															<th>Client</th>
															<th>Rate Card Name</th>
															<th>Start Date</th>
															<th>End Date</th>
															<th>Country</th>
															<th>City</th>
															<th>Sales Volume</th>
															<th>Industry</th>
														</tr>
													</thead>
													<tbody id="tBodyRateCardDetails" >
														<tr >
															<td><a href="#">Select</</a></td>
															<td></td>
															<td></td>
															<td></td>
															<td></td>
															<td></td>
															<td></td>
															<td></td>
															<td></td>
															<td></td>
														</tr>
														<tr>															
															<td><a href="#">Select</</a></td>
															<td></td>
															<td></td>
															<td></td>
															<td></td>
															<td></td>
															<td></td>
															<td></td>
															<td></td>
															<td></td>
														</tr>	
														<tr>															
															<td><a href="#">Select</</a></td>
															<td></td>
															<td></td>
															<td></td>
															<td></td>
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
									<div class="row marginBottom5px">
										<label class="control-label col-sm-10 "><h5><b>Rate Card - Pending Approvals</b></h5></label>
									</div>
									<div class="row marginBottom5px">
									<label class="control-label col-sm-2 textAlignRight">Approver</label>
									<div class="col-sm-3">
								 		<select Name="ddlPendingApprovalsApprover" id="ddlPendingApprovalsApprover" 
								 			class="form-control ng-pristine ng-invalid ng-invalid-required ng-touched" placeholder="Please select"
								 			ng-model="ddlPendingApprovalsApproverModel" required="">
								 			<option value="" selected=" disabled">Please select</option>
											<option value="1">1</option>
											<option value="2">2</option>
								 		</select>
									</div>
									</div>
									<div class="row marginBottom5px">
	                                    <div class="col-sm-12">
											<div class="table-responsive  " >
												<table 
													class="table clsTable table-striped table-bordered table-hover table-condensed "
													id="tblPendingApprovals">
													<thead>
														<tr>
															<th>Rate Card Id</th>
															<th>Deal Id</th>
															<th>Deal Description</th>
															<th>Deal Type</th>
															<th>Onsite Facility</th>
															<th>Start Date</th>
															<th>End Date</th>
															<th>Sales Vol</th>
															<th>Deal Status</th>
														</tr>
													</thead>
													<tbody id="tBodyPendingApprovals">
														<tr >
															
															<td>Rate card 1</td>
															<td></td>
															<td></td>
															<td></td>
															<td></td>
															<td></td>
															<td></td>
															<td></td>
															<td></td>
														</tr>
														<tr>															
															<td>Rate card 2</td>
															<td></td>
															<td></td>
															<td></td>
															<td></td>
															<td></td>
															<td></td>
															<td></td>
															<td></td>
														</tr>	
														<tr>															
															<td>Rate card 3</td>
															<td></td>
															<td></td>
															<td></td>
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
									<div class="row marginBottom5px">
										<label class="control-label col-sm-10 "><h5><b>Approved Rate Cards</b></h5></label>
									</div>
									<div class="row marginBottom5px">
										<label class="control-label col-sm-2 textAlignRight">Customer</label>
										<div class="col-sm-3">
									 		<select Name="ddlApprovedRateCardsCustomer" id="ddlApprovedRateCardsCustomer" 
									 			class="form-control ng-pristine ng-invalid ng-invalid-required ng-touched" placeholder="Please select"
									 			ng-model="ddlApprovedRateCardsCustomerModel" required="">
									 			<option value="" selected=" disabled">Please select</option>
												<option value="1">Customer A</option>
												<option value="2">Customer B</option>
									 		</select>
										</div>
									</div>
									<div class="row marginBottom5px">
	                                    <div class="col-sm-12">
											<div class="table-responsive  " >
												<table 
													class="table clsTable table-striped table-bordered table-hover table-condensed "
													id="tblPendingApprovals">
													<thead>
														<tr>
															<th class ="width12per">Customer</th>
															<th>Deal Id</th>
															<th>Deal Description</th>
															<th>Deal Type</th>
															<th>Onsite Facility</th>
															<th>Start Date</th>
															<th>End Date</th>
															<th>Sales Vol</th>
															<th>Deal Status</th>
														</tr>
													</thead>
													<tbody id="tBodyPendingApprovals">
														<tr >
															<td><input name="txtCustomerRow1" type="text" class="form-control" id="txtCustomerRow1"
                                            	   						ng-model="txtCustomerRow1Model" required></td>
															<td>12</td>
															<td>1</td>
															<td>0.5</td>
															<td>123</td>
															<td>12</td>
															<td>0.5</td>
															<td>123</td>
															<td>12</td>
														</tr>
														<tr>															
															<td><input name="txtCustomerRow2" type="text" class="form-control" id="txtCustomerRow2"
                                            	   						ng-model="txtCustomerRow2Model" required></td>
															<td>12</td>
															<td>1</td>
															<td>0.5</td>
															<td>123</td>
															<td>12</td>
															<td>0.5</td>
															<td>123</td>
															<td>12</td>
														</tr>	
														<tr>															
															<td><input name="txtCustomerRow3" type="text" class="form-control" id="txtCustomerRow3"
                                            	   						ng-model="txtCustomerRow3Model" required></td>
															<td>12</td>
															<td>1</td>
															<td>0.5</td>
															<td>123</td>
															<td>12</td>
															<td>0.5</td>
															<td>123</td>
															<td>12</td>
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
	      		</form>
        	</div>
    	</div>
		<div id="footer" ng-include="'/RightPricePortal/Portal/Footer.jsp'"></div>
		<div id="Footer" ng-include="'/RightPricePortal/Portal/TopBottomNavigation.jsp'"></div>
	</body>
</html>
