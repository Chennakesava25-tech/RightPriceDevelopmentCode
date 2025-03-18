<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
    <title>Atos RightPrice Portal</title>
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
<script src="${contextPath}/resources/js/RightPrice/PreSalesView.js"></script>
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

<body ng-app="RightPriceApp" ng-controller="PreSalesViewController"
ng-init="getPreSalesData()"
	ng-focus="customDatePicker()">
	<div id="includedContent"
		ng-include="'${contextPath}/Portal/Header.jsp'"></div>
	<fieldset ng-disabled="loading || showLoader">
		<div class="container">
			<div class="divEmpty"></div>
			<div class="row marginBottom5px">
			</div>
			<div>
				<form>
				<div class="row">
					<div class="col-md-12">
					<div class="panel-group">
								<div class="panel panel-info ">
									<div class="panel-heading panelHeadingStyle">
										<div class="row ">
											<label class="control-label col-sm-10">View Entries</label>
										</div>
									</div>
							<div class="card-body">
								<div class="table-responsive">
									<table
										class="table clsTable table-striped table-bordered table-hover table-sm table-striped table-hover tableHeader"
										border="1" id="requestViewTbl">
										<thead>
											<tr>
												<th class="tdMinWidth67">Sr.No.</th>
												<th class="tdMinWidth120">Opportunity Id</th>
												<th class="tdMinWidth120">Customer Name</th>
												<th class="tdMinWidth120">Deal Category</th>
												<th class="tdMinWidth120">Deal Status</th>
												<th class="tdMinWidth67 text-center">Action</th>
											</tr>
										</thead>
										<tbody id="tBody">
											<tr ng-repeat="row in PreSalesViewRequestData">
												<td>{{($index+1)}}</td>
												<td>{{row.opportunityId}}</td>
												<td>{{row.customer_Name}}</td>
												<td ng-if="row.dealType == '1'">T&M</td>
												<td ng-if="row.dealType != '1'">FP</td>
												<td ng-if="row.dealStatus == '0'">Open</td>
												<td ng-if="row.dealStatus == '1'">Won</td>
												<td ng-if="row.dealStatus == '2'">Lost</td>
												<td class="cellStyleCenter" ng-if="row.status != '1'"><button
																ng-click="updateRequest(row)">View</button></td>
												<td class="cellStyleCenter" ng-if="row.status == '1'"><button
																ng-click="viewRequest(row)">Update</button></td>
										</tbody>
									</table>
								</div>
							</div>
							<div class="row" id="eReport"  style="display: none;">
						<div class="row marginBottom5px">
							<div class="col-md-12" style="color:#6ab5d5;">
								<h3 class="text-center">View Entries</h3>
							</div>
						</div>
							<div class="col-md-12">
							<div id="tblDetails" class="table-responsive">
								<table
									class="table clsTable table-striped table-bordered table-hover table-condensed tableHeader"
									border="1" id="PendingOrderTbl">
									<thead>
										<tr style="background-color: #6ab5d5;color:white;font-weight: bold;">
											<th class="tdMinWidth67">Sr.No.</th>
												<th class="tdMinWidth120">Opportunity Id</th>
												<th class="tdMinWidth120">Opportunity Description</th>
												<th class="tdMinWidth120">Deal Type</th>
												<th class="tdMinWidth120">Deal Category</th>
												<th class="tdMinWidth120">Deal Start Date</th>
												<th class="tdMinWidth67">Deal End Date</th>
												<th class="tdMinWidth120">CRM Customer Name ID</th>
												<th class="tdMinWidth120">Customer Name</th>
												<th class="tdMinWidth120">Finance Customer</th>
												<th class="tdMinWidth120">IRIS Code</th>
												<th class="tdMinWidth120">Vertical </th>
												<th class="tdMinWidth67">GBU</th>
												<th class="tdMinWidth120">Presales WBS Number</th>
												<th class="tdMinWidth120">Currency Code</th>
												<th class="tdMinWidth120">Total TCV </th>
												<th class="tdMinWidth120">Bid spend Start date</th>
												<th class="tdMinWidth120">Bid spend End date</th>
												<th class="tdMinWidth120">B&PS TCV</th>
												<th class="tdMinWidth120">B&PS-BID Budget</th>
												<th class="tdMinWidth120">B&PS-BID Budget in Euro</th>
												<th class="tdMinWidth120">B&PS TCV in Euro</th>
												<th class="tdMinWidth120">% of B&PS TCV on Total TCV</th>
												<th class="tdMinWidth67">Pre sales Cost against WBS</th>
												<th class="tdMinWidth120">Pre sales Cost against WBS in Euro</th>
												<th class="tdMinWidth120">Balance</th>
												<th class="tdMinWidth120">Balance  in Euro</th>
										</tr>
									</thead>
									<tbody id="tBody">
										<tr ng-repeat="row in PreSalesViewRequestData">
											<td>{{($index+1)}}</td>
												<td>{{row.opportunityId}}</td>
												<td>{{row.opportunityDescription}}</td>
												<td ng-if="row.dealType == '1'">T&M</td>
												<td ng-if="row.dealType == '2'">FM</td>
												<td ng-if="row.dealStatus == '0'">Open</td>
												<td ng-if="row.dealStatus == '1'">Won</td>
												<td ng-if="row.dealStatus == '2'">Lost</td>
												<td>{{row.dealStartDate}}</td>
												<td>{{row.dealEndDate}}</td>
												<td>{{row.cRMCustomerId}}</td>
												<td>{{row.customer_Name}}</td>
												<td>{{row.financeCustomer}}</td>
												<td>{{row.iRISCode	}}</td>
												<td>{{row.vertical}}</td>
												<td>{{row.gBU}}</td>
												<td>{{row.presalesWBSNumber}}</td>
												<td>{{row.dealCurrencyCode}}</td>
												<td>{{row.totalTCV}}</td>
												<td>{{row.bidStartDate}}</td>
												<td>{{row.bidEndDate}}</td>
												<td>{{row.bPSTCV}}</td>
												<td>{{row.bPSBIDBudget}}</td>
												<td>{{row.bPSBIDBudgetINEuro}}</td>
												<td>{{row.bPSTCVINEuro}}</td>
												<td>{{row.percentageofBPSTCVonTotalTCV}}</td>
												<td>{{row.preSalesCostAgainstWBS}}</td>
												<td>{{row.preSalesCostAgainstWBSInEuro}}</td>
												<td>{{row.balance}}</td>
												<td>{{row.balanceInEuro}}</td>
										</tr>
									</tbody>
								</table>
							</div>
						</div>
						</div>	
						</div>
						<div class="divEmptyThrice"></div>
						<div class="divEmptyThrice"></div>
						<div class="divEmptyThrice"></div>
						<div class="row text-center">
										<div class="col-sm-12">
											<button type="button" class="btn btn-primary btnSpace"
												id="btnDetailsSave"
												ng-click="exportToExcel('#eReport')">
												<img src="/RightPrice/resources/Images/downloadexcel.png" alt="Snow" > Export to Excel</button>
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
