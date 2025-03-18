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
<link href="${contextPath}/resources/css/SAPStyleSheet.css"	rel="stylesheet" />
<link href="${contextPath}/resources/css/loader.css" rel="stylesheet" />
<script src="${contextPath}/resources/js/jquery.min.js"></script>
<script src="${contextPath}/resources/js/bootstrap.js"></script>
<script src="${contextPath}/resources/js/jquery.serializeJSON.min.js"></script>
<script src="${contextPath}/resources/js/jquery-ui.js"></script>
<link href="${contextPath}/resources/css/jquery-ui.css" rel="stylesheet" />
<link href="${contextPath}/resources/css/bootstrap-dialog.css"	rel="stylesheet" />
<script src="${contextPath}/resources/js/bootstrap-dialog.js"></script>
<link href="${contextPath}/resources/css/ie10-viewport-bug-workaround.css"	rel="stylesheet" />
<link href="${contextPath}/resources/css/sticky-footer-navbar.css"	rel="stylesheet" />
<script src="${contextPath}/resources/js/ie10-viewport-bug-workaround.js"></script>
<script src="${contextPath}/resources/js/angular.js"></script>
<script src="${contextPath}/resources/js/angular-ui.min.js"></script>
<script src="${contextPath}/resources/js/angular-messages.js"></script>
<script src="${contextPath}/resources/js/ngStorage.js"></script>
<script src="${contextPath}/resources/js/loader.js"></script>
<script	src="${contextPath}/resources/js/RightPrice/ResourceForcastController.js"></script>
<script src="${contextPath}/resources/js/Factory/WebServiceFactory.js"></script>
<script
	src="${contextPath}/resources/js/Directives/angularCustomValidations.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		$(".customDate").datepicker({
			dateFormat : 'dd/mm/yy',
			changeMonth : true,
			changeYear : true
		});
	});
</script>
</head>
<body ng-app="RightPriceApp" ng-controller="ResourceForcastController"
	ng-focus="customDatePicker()" >
<%-- 	ng-focus="customDatePicker()" ng-init="currentUser('<%=org.springframework.security.core.context.SecurityContextHolder.getContext().getAuthentication().getName()%>')"> --%>
	<div id="includedContent"
		ng-include="'${contextPath}/Portal/Header.jsp'"></div>
	<fieldset ng-disabled="loading || showLoader">
		<div class="container">
			<div class="divEmpty"></div>
	<!-- 		<div class="row marginBottom5px">
				<div class="col-sm-12">
					<h3 class="text-left" id="PageHeading">Resource Forecasting</h3>
				</div>
			</div> -->
			<div>
<%-- 				<form class="form-inline" role="form" name="frmForecast"
					id="frmForecast" novalidate>
					<fieldset ng-disabled="isViewRequest">
						<div class="row">
							<div class="col-sm-12">
								<div class="panel-group">
									<div class="panel panel-info ">
										<div class="panel-heading panelHeadingStyle">
											<div class="row ">
												<label class="control-label col-sm-10">Search Deals</label>
											</div>
										</div>
										<!--  <div class="panel-body" ng-hide = "ForecastDetailsHidden"> -->
										<div class="panel-body">
											<div class="row marginBottom5px">
												<label
													class="control-label col-sm-2 textAlignRight ">Customer:</label>
												<div class="col-sm-3">
													<select id="ddlForecastCustomer" class="form-control"
														placeholder="Please select" name="ddlForecastCustomer"
														ng-model="frmForecast.customerModel"
														ng-options="cust.customerName for cust in customer | orderBy:'customerName'"
														ng-class="{true: 'ng-border'}[saved && frmForecast.ddlForecastCustomer.$invalid]">
														<option value="" selected disabled>Please select</option>
													</select>
													<div class="error-messages" ng-if="saved"
														ng-messages="frmForecast.ddlForecastCustomer.$error">
														<em class="error help-block has-error"
															ng-message="required">Please select Customer</em>
													</div>
												</div>
												<label
													class="control-label col-sm-2 textAlignRight ">Deal Id:</label>
												<div class="col-sm-3">
													<select id="ddlForecastDealid" class="form-control"
														placeholder="Please select" name="ddlForecastDealid"
														ng-options="deal.crmDealId for deal in dealData"
														ng-model="frmForecast.detailId"
														ng-class="{true: 'ng-border'}[saved && frmForecast.ddlForecastDealid.$invalid]">
															<option value="" selected  disabled\>Please select</option>
													</select>
													<div class="error-messages" ng-if="saved"
														ng-messages="frmForecast.ddlForecastDealid.$error">
														<em class="error help-block has-error"
															ng-message="required">Please select Action</em>
													</div>
												</div>
											</div>
											<div class="row text-center">
												<div class="col-sm-12">
													<button type="button" class="btn btn-primary btnSpace"
														id="btnDetailsSave" ng-disabled="btnDisable"
														ng-click="searchForecast(frmForecast);">Search</button>
													<button type="button" class="btn btn-primary btnSpace"
														id="btnDetailsCancel" ng-click="clear()">Clear</button>
												</div>
											</div>
										</div>
								</div>
							</div>
						</div>
					</fieldset>

					<fieldset ng-disabled="isViewRequest">
						<div class="row">
							<div class="col-sm-12">
								<div class="panel-group">
									<div class="panel panel-info ">
										<div class="panel-heading panelHeadingStyle">
											<div class="row ">
												<label class="control-label col-sm-10">My Task - Deals</label>
											</div>
										</div>
										<div class="panel-body">
										<div class="row marginBottom5px">
											<div class="col-sm-12">
												<div class="table-responsive  ">
													<table
														class="table clsTable table-striped table-bordered table-hover table-condensed "
														id="tblDashBoard">
														<thead>
															<tr>
																<th >Action</th>
																<th >Customer</th>
																<th >Deal Id</th>
																<th >Deal Description</th>
																<th >Deal Type</th>
																<th >Start Date<br>(dd/mm/yyyy)</th>
																<th >End Date<br>(dd/mm/yyyy)</th>
																<th >Industry</th>
															</tr>
														</thead>
														<tbody id="tBodyDashBoardr">
															<tr ng-repeat="row in forcastInfo">
																<td><a href="${contextPath}/RFOpportunity"  ng-click="passData(row);">Select</a></td>
																<td>{{row.customerName}}</td>
																<td>{{row.cRMDealId}}</td>
																<td>{{row.deal_Description}}</td>
																<td>{{row.deal_Type_Id}}</td>
																<td>{{row.deal_Start_Date}}</td>
																<td>{{row.deal_End_Date}}</td>
																<td>{{row.industry}}</td>
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
					</fieldset>



				</form> --%>
				<div class="divEmptyThrice"></div>
			</div>
		</div>
	</fieldset>
	<div id="footer" ng-include="'${contextPath}/Portal/Footer.jsp'"></div>
	<div id="Footer"
		ng-include="'${contextPath}/Portal/TopBottomNavigation.jsp'"></div>
	<div id="loader" ng-include="'${contextPath}/Portal/loader.jsp'"></div>
</body>
</html>
