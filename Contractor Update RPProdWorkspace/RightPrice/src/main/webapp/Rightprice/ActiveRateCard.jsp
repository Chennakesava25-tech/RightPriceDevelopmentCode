<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Eviden  RightPrice Portal</title>
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
<script src="${contextPath}/resources/js/RightPrice/ActiveRateCard.js"></script>
<script src="${contextPath}/resources/js/Factory/WebServiceFactory.js"></script>
</head>
<body ng-app="RightPriceApp" ng-controller="ActiveRateCardController" >
     <div id="includedContent" ng-include="'${contextPath}/Portal/Header.jsp'"></div>
     <fieldset ng-disabled="loading || showLoader">
    <div class="container">
        <div class="divEmpty"></div>
        <div class="row marginBottom5px">
            <div class="col-sm-12">
                <h3 class="text-left" id="PageHeading">Active Rate Cards</h3>
            </div>
        </div>	
        <div>
	      	<form class="form-inline" role="form" name="Master" id="Master">
	         	<div class="row">
						<div class="col-sm-12">
							<div class="panel-group">
								<div class="panel panel-info ">
									<div class="panel-heading panelHeadingStyle">
										<div class="row ">
											<label class="control-label col-sm-10">Rate Card Details</label>
										</div>
									</div>
									<div class="row marginBottom5px">
										<div class="col-sm-12">
											<div class="table-responsive panel-body">
	<!-- 											<table style="height:1000px overflow-y:auto"
													class="table clsTable table-striped table-bordered table-hover table-condensed "
													id="tblRateCardDetails" infinite-scroll="increaseLimit()" infinite-scroll-container='".constrained"'>
													<tbody id="tBodyRateCardDetails">
														<tr>
															<th><div align="center">Vertical Name</div></th>
															<th><div align="center">Master Role Name</div></th>
															<th><div align="center">Rate Card Id</div></th>
															<th><div align="center">Rate Card Name</div></th>
															<th><div align="center">Customer Id</div></th>
															<th><div align="center">Customer Name</div></th>
															<th><div align="center">Onsite Percentage</div></th>
															<th><div align="center">Offshore Percentage</div></th>
															<th><div align="center">Volume Discount</div></th>
															<th><div align="center">TCV</div></th>
															<th><div align="center">Practice Name</div></th>
															<th><div align="center">Sub Practice Name</div></th>
															<th><div align="center">Proficiency Level Name</div></th>
															<th><div align="center">Client Role</div></th>
															<th><div align="center">Syntel Role Description</div></th>
															<th><div align="center">Onsite Usage</div></th>
															<th><div align="center">Offshore Usage</div></th>
															<th><div align="center">Onsite Proposed Client Rate</div></th>
															<th><div align="center">Offshore Proposed Client Rate</div></th>
															<th><div align="center">Rate Card Start Date<br>(dd/mm/yyyy)</div></th>
															<th><div align="center">Rate Card End Date<br>(dd/mm/yyyy)</div></th>
															<th><div align="center">Country Name</div></th>
															<th><div align="center">City Name</div></th>
															<th><div align="center">Categorization Id</div></th>
															<th><div align="center">Categorization Name</div></th>

														</tr>
														<tr ng-repeat="row in activeRC "
															id="{{'ResourceData'+'_'+($index+1)}}">
															<td ><div align="left">{{row.vertical}}</div></td>
															<td ><div align="left">{{row.masterRole}}</div></td>
															<td ><div align="left">{{row.rcId}}</div></td>
															<td ><div align="left">{{row.rcname}}</div></td>
															<td ><div align="left">{{row.customerId}}</div></td>
															<td ><div align="left">{{row.customerName}}</div></td>
															<td ><div align="left">{{row.onsitePer}}</div></td>
															<td ><div align="left">{{row.offshorePer}}</div></td>
															<td ><div align="left">{{row.volumeDiscount}}</div></td>
															<td ><div align="left">{{row.tcv}}</div></td>
															<td ><div align="left">{{row.practiceName}}</div></td>
															<td ><div align="left">{{row.subPracticeName}}</div></td>
															<td ><div align="left">{{row.proficiencyLevelName}}</div></td>
															<td ><div align="left">{{row.client_Role}}</div></td>
															<td ><div align="left">{{row.syntelRoleDescription}}</div></td>
															<td ><div align="left">{{row.onsiteUsage}}</div></td>
															<td ><div align="left">{{row.offshoreUsage}}</div></td>
															<td ><div align="left">{{row.onsiteProposedClientRate}}</div></td>
															<td ><div align="left">{{row.offshoreProposedClientRate}}</div></td>
															<td ><div align="left">{{row.rcStartDate}}</div></td>
															<td ><div align="left">{{row.rcEndDate}}</div></td>
															<td ><div align="left">{{row.countryName}}</div></td>
															<td ><div align="left">{{row.cityName}}</div></td>
															<td ><div align="left">{{row.categorizationId}}</div></td>
															<td ><div align="left">{{row.categorization_Name}}</div></td>
														</tr>
													</tbody>
												</table> -->
											</div>
										</div>
									</div>

									<!--   Table to display data   -->
									<div class="row text-center">
										<div class="col-sm-12">
											<button type="button" class="btn btn-primary btnSpace"
												id="btnDetailsSave"
												ng-click="exportRateCardData(Master,'#tableToExport');">
												<img src="${contextPath}/resources/Images/downloadexcel.png" alt="Snow" > Export to Excel</button>
										</div>
									</div>
						<div class="divEmptyThrice"></div>
				<!-- ============================== download functionality ============================ -->
								</div>
							</div>
						</div>
					</div>
	         </form>
        </div>
			<div style="display:None"  id="tableToExport" >
				<table border="1" 
					class="table clsTable table-striped table-bordered table-hover table-condensed "
					id="tblRateCardDetails">
					<tbody id="tBodyRateCardDetails1">
						<tr>
							<td colspan="6" align="Center" bgcolor="#CCFFFF"><Strong>Master
									- Eviden Rate Card</Strong></td>
						</tr>
					</tbody>
				</table>

				<table></table>

				<table border="1"
					class="table clsTable table-striped table-bordered table-hover table-condensed "
					id="tblRateCardDetails2">
					<tbody id="tBodyRateCardDetails">
						<tr>
							<td colspan="6" align="Center" bgcolor="#CCFFFF"><Strong>Rate Card Details</Strong></td>
						</tr>
					</tbody>
				</table>

				<table></table>
				
				<table border="1" 
					class="table  "
					id="tblRateCardDetails3">
	<tbody id="tBodyRateCardDetails">
														<tr>
															<th bgcolor="#CCFFFF"><div align="center">Vertical Name</div></th>
															<th bgcolor="#CCFFFF"><div align="center">Master Role Name</div></th>
															<th bgcolor="#CCFFFF"><div align="center">Rate Card Id</div></th>
															<th bgcolor="#CCFFFF"><div align="center">Rate Card Name</div></th>
															<th bgcolor="#CCFFFF"><div align="center">Customer Id</div></th>
															<th bgcolor="#CCFFFF"><div align="center">Customer Name</div></th>
															<th bgcolor="#CCFFFF"><div align="center">Onsite Percentage</div></th>
															<th bgcolor="#CCFFFF"><div align="center">Offshore Percentage</div></th>
															<th bgcolor="#CCFFFF"><div align="center">Volume Discount</div></th>
															<th bgcolor="#CCFFFF"><div align="center">TCV</div></th>
															<th bgcolor="#CCFFFF"><div align="center">Practice Name</div></th>
															<th bgcolor="#CCFFFF"><div align="center">Sub Practice Name</div></th>
															<th bgcolor="#CCFFFF"><div align="center">Proficiency Level Name</div></th>
															<th bgcolor="#CCFFFF"><div align="center">Client Role</div></th>
															<th bgcolor="#CCFFFF"><div align="center">Syntel Role Description</div></th>
															<th bgcolor="#CCFFFF"><div align="center">Onsite Usage</div></th>
															<th bgcolor="#CCFFFF"><div align="center">Offshore Usage</div></th>
															<th bgcolor="#CCFFFF"><div align="center">Onsite Proposed Client Rate</div></th>
															<th bgcolor="#CCFFFF"><div align="center">Offshore Proposed Client Rate</div></th>
															<th bgcolor="#CCFFFF"><div align="center">Rate Card Start Date<br>(dd/mm/yyyy)</div></th>
															<th bgcolor="#CCFFFF"><div align="center">Rate Card End Date<br>(dd/mm/yyyy)</div></th>
															<th bgcolor="#CCFFFF"><div align="center">Country Name</div></th>
															<th bgcolor="#CCFFFF"><div align="center">City Name</div></th>
															<th bgcolor="#CCFFFF"><div align="center">Categorization Id</div></th>
															<th bgcolor="#CCFFFF"><div align="center">Categorization Name</div></th>

														</tr>
														<tr ng-repeat="row in activeRC"
															id="{{'ResourceData'+'_'+($index+1)}}">
															<td ><div align="left">{{row.vertical}}</div></td>
															<td ><div align="left">{{row.masterRole}}</div></td>
															<td ><div align="left">{{row.rcId}}</div></td>
															<td ><div align="left">{{row.rcname}}</div></td>
															<td ><div align="left">{{row.customerId}}</div></td>
															<td ><div align="left">{{row.customerName}}</div></td>
															<td ><div align="left">{{row.onsitePer}}</div></td>
															<td ><div align="left">{{row.offshorePer}}</div></td>
															<td ><div align="left">{{row.volumeDiscount}}</div></td>
															<td ><div align="left">{{row.tcv}}</div></td>
															<td ><div align="left">{{row.practiceName}}</div></td>
															<td ><div align="left">{{row.subPracticeName}}</div></td>
															<td ><div align="left">{{row.proficiencyLevelName}}</div></td>
															<td ><div align="left">{{row.client_Role}}</div></td>
															<td ><div align="left">{{row.syntelRoleDescription}}</div></td>
															<td ><div align="left">{{row.onsiteUsage}}</div></td>
															<td ><div align="left">{{row.offshoreUsage}}</div></td>
															<td ><div align="left">{{row.onsiteProposedClientRate}}</div></td>
															<td ><div align="left">{{row.offshoreProposedClientRate}}</div></td>
															<td ><div align="left">{{row.rcstartDate}}</div></td>
															<td ><div align="left">{{row.rcendDate}}</div></td>
															<td ><div align="left">{{row.countryName}}</div></td>
															<td ><div align="left">{{row.cityName}}</div></td>
															<td ><div align="left">{{row.categorizationId}}</div></td>
															<td ><div align="left">{{row.categorization_Name}}</div></td>
														</tr>
													</tbody>
				</table>
			</div>
		</div>

								<div class="divEmptyThrice"></div>
     </fieldset>
	<div id="footer" ng-include="'${contextPath}/Portal/Footer.jsp'"></div>
	<div id="loader" ng-include="'${contextPath}/Portal/loader.jsp'"></div>
</body>
</html>
