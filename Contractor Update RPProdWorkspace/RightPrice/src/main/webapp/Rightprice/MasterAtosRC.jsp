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
	<link href="${contextPath}/resources/css/AngularCSS.css" rel="stylesheet" />
	<script src="${contextPath}/resources/js/angular.js"></script>
	<script src="${contextPath}/resources/js/angular-ui.min.js"></script>
	<script src="${contextPath}/resources/js/angular-messages.js"></script>
	<script src="${contextPath}/resources/js/ngStorage.js"></script>
	<script src="${contextPath}/resources/js/loader.js"></script>
	<script src="${contextPath}/resources/js/RightPrice/MasterAtosRC.js"></script>
	<script src="${contextPath}/resources/js/Directives/angularCustomValidations.js"></script>
	<script type="text/javascript">
	$(document).ready(function() {
		$(".customDate").datepicker({
			dateFormat : 'dd/mm/yy',
			changeMonth : true,
			changeYear : true
		});
	});
</script>
	<script src="${contextPath}/resources/js/Factory/WebServiceFactory.js"></script>
</head>
<body ng-app="RightPriceApp" ng-controller="MasterAtosRCController" >
     <div id="includedContent" ng-include="'${contextPath}/Portal/Header.jsp'"></div>
     <fieldset ng-disabled="loading || showLoader">
    <div class="container">
        <div class="divEmpty"></div>
        <div class="row marginBottom5px">
            <div class="col-sm-12">
                <h3 class="text-left" id="PageHeading">Eviden Rate Card</h3>
            </div>
        </div>	
        <div>
	      	<form class="form-inline" role="form" name="Master" id="Master" novalidate>
					<div class="row">
						<div class="col-sm-12">
							<div class="panel-group">
								<div class="panel panel-info ">
									<div class="panel-heading panelHeadingStyle">
										<div class="row ">
											<label class="control-label col-sm-10">Search Criteria</label>
										</div>
									</div>
									<div class="panel-body">
										<div class="row marginBottom5px">
												<label
													class="control-label col-sm-2 textAlignRight ">Start
													Date <br>(dd/mm/yyyy) </label>
												<div class="col-sm-3">
													<input type="text" class="form-control customDate"
														placeholder="dd/mm/yyyy" id="txtRateCardStartDate"
														ng-disabled="isFirstTimePageLoaded"
														name="txtRateCardStartDate"
														ng-model="Master.startdate"
														ng-class="{true: 'ng-border'}[saved && Master.txtRateCardStartDate.$invalid]"
														ng-pattern="/^(((0[1-9]|[12]\d|3[01])\/(0[13578]|1[02])\/((19|[2-9]\d)\d{2}))|((0[1-9]|[12]\d|30)\/(0[13456789]|1[012])\/((19|[2-9]\d)\d{2}))|((0[1-9]|1\d|2[0-8])\/02\/((19|[2-9]\d)\d{2}))|(29\/02\/((1[6-9]|[2-9]\d)(0[48]|[2468][048]|[13579][26])|((16|[2468][048]|[3579][26])00))))$/i">
													<div class="error-messages" ng-if="saved"
														ng-messages="Master.txtRateCardStartDate.$error">
														<em class="error help-block has-error"
															ng-message="required">Start Date is required!</em> <em
															class="error help-block has-error" ng-message="pattern">Start
															Date is invalid!</em>
													</div>
												</div>
												<label 
													class="control-label col-sm-3 textAlignRight required-Field">End
													Date<br>(dd/mm/yyyy) </label>
												<div class="col-sm-3">
													<input type="text" class="form-control customDate"
														placeholder="dd/mm/yyyy" id="txtRateCardEndDate"
														name="txtRateCardEndDate"
														ng-required="Master.startdate"
														ng-model="Master.enddate"
														ng-change="calApplicableYears(Master.startdate,Master.enddate);"
														ng-class="{true: 'ng-border'}[saved && Master.txtRateCardEndDate.$invalid]"
														 end-date-compare="Master.startdate"
														ng-pattern="/^(((0[1-9]|[12]\d|3[01])\/(0[13578]|1[02])\/((19|[2-9]\d)\d{2}))|((0[1-9]|[12]\d|30)\/(0[13456789]|1[012])\/((19|[2-9]\d)\d{2}))|((0[1-9]|1\d|2[0-8])\/02\/((19|[2-9]\d)\d{2}))|(29\/02\/((1[6-9]|[2-9]\d)(0[48]|[2468][048]|[13579][26])|((16|[2468][048]|[3579][26])00))))$/i">
													<div class="error-messages" ng-if="saved"
														ng-messages="Master.txtRateCardEndDate.$error">
														<em class="error help-block has-error"
															ng-message="required">End Date is required!</em> <em
															class="error help-block has-error" ng-message="pattern">End
															Date is invalid!</em> <em class="error help-block has-error"
															ng-message="endDateCompare">End Date should be
															greater than Start Date</em>
													</div>
												</div>
											</div>
											<div class="row marginBottom5px">
												<label
													class="control-label col-sm-2 textAlignRight ">Rate Card Status</label>
												<div class="col-sm-3">
													<select id="ddlForecastCustomer" class="form-control"
														placeholder="Please select" name="ddlForecastCustomer"
														ng-model="Master.RateCardAction"
														ng-class="{true: 'ng-border'}[saved && Master.ddlForecastCustomer.$invalid]">
														<option value="" selected disabled>Please select</option>
														<option value="0" selected >Approved</option>
														<option value="1" selected >Active</option>
														<option value="2" selected >Expired</option>
														
													</select>
													<div class="error-messages" ng-if="saved"
														ng-messages="Master.ddlForecastCustomer.$error">
														<em class="error help-block has-error"
															ng-message="required">Please select Customer</em>
													</div>
												</div>
												<label
													class="control-label col-sm-3 textAlignRight ">Country</label>
												<div class="col-sm-3 ">
													<select id="ddlAddLocationCountry" class="form-control"
														placeholder="Please select" name="ddlAddLocationCountry"
														ng-model="Master.country"
														ng-options="cn as cn.countryName for cn in country| orderBy:'countryName'"
														ng-change="getCities(Master.country.countryId)"
														ng-disabled="isFirstTimePageLoaded || disableInputs"
														ng-class="{true: 'ng-border'}[cityAdd && Master.ddlAddLocationCountry.$invalid]">
														<option value="" disabled>Please select</option>
													</select>
													<div class="error-messages" ng-if="cityAdd"
														ng-messages="Master.ddlAddLocationCountry.$error">
														<em class="error help-block has-error"
															ng-message="required">Please select Country</em>
													</div>
												</div>
											</div>
											<div class="divEmptyThrice"></div>
											<div class="row text-center">
											
											<div class="col-sm-12">		
												<button type="button" class="btn btn-primary btnSpace" id="btnViewSearch" ng-disabled="btnDisable" ng-click="searchData(Master);">Search</button>
												<button type="button" class="btn btn-primary btnSpace"id="btnDetailsCancel" ng-click="clear(Master)">Clear</button>
											</div>
							<!-- 					<div class="col-sm-12">
													<button type="button" class="btn btn-primary btnSpace"
														id="btnDetailsSave" ng-disabled="btnDisable"
														ng-click="searchForecast(Master);">Search</button>
													<button type="button" class="btn btn-primary btnSpace"
														id="btnDetailsCancel" ng-click="clear()">Clear</button>
												</div> -->
											</div>
										</div>
								</div>
							</div>
						</div>
					</div>
	      	
	      	
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
												<!-- class="clsTable table-striped table-bordered table-hover table-condensed " id ="tblViewVertical" --> 
												<table class ="table clsTable table-bordered table-striped table-hover table-condensed" style="" ng-hide="uploadHide">
													 <thead>
														<tr>
															<th  width = "7.3%"><div align="center">Rate Card ID</div></th>
															<th class="tdWordBreak" width = "6.7%" ><div align="center" style="word-wrap: break-word">Rate Card <br> Name</div></th>
															<th class="tdWordBreak"  width ="10%"><div align="center">Customer <br> Name</div></th>
															<th class="tdWordBreak" width ="30%"><div align="center">Vertical Name</div></th>
															<th class="tdWordBreak" width = "8%"><div align="center">Start Date<br>(mm/dd/yy)</div></th>
															<th class="tdWordBreak" width = "8%"><div align="center">End Date<br>(mm/dd/yy)</div></th>
															<th  width = "7%"><div align="center">Onsite Location</div></th>
															<th class="tdWordBreak" width = "5.5%"><div align="center">Onsite <br> %</div></th>
															<th class="tdWordBreak" width = "6.5%"><div align="center">Offshore <br> %</div></th>
															<th class="tdWordBreak" width = "6%"><div align="center">PM%</div></th>
															<th width = "5%"><div align="center">Download</div></th>
														</tr>
														</thead>
													<tbody>
														<tr ng-repeat="row in AtosRC" id="{{'ResourceData'+'_'+($index+1)}}">
															<td class="tdWordBreak" width = "4%" ng-click="showAtosRateCardDetails(row.rcId, row.currentApproverId, row.currentApprovalStatus, row.currentApproverRoleId)"><a>{{row.rcId}}</a></td><div align="left">{{row.rcId}}</div></td>
															<td class="tdWordBreak" width = "10%"  style="word-wrap: break-word">{{row.rcName}}</td>
															<td class="tdWordBreak" width = "10%">{{row.customerName}}</td>
															<td class="tdWordBreak" width = "30%">{{row.verticalName}}</td>
															<td class="tdWordBreak" width = "8%"><div align="left">{{row.rcStartDate1}}</div></td>
															<td class="tdWordBreak" width = "8%"><div align="left">{{row.rcEndDate1}}</div></td>
														 	<td width = "10%" class = "white-space-pre-line" >{{row.loc}}</td> 
															<!-- <td ><div ng-repeat = "loc in row.loc1" >{{loc}}</div></td> -->
															<td class="tdWordBreak" width = "5%"><div align="right">{{row.onsitePer}}%</div></td>
															<td class="tdWordBreak" width = "5%"><div align="right">{{row.offshorePer}}%</div></td>
															<td class="tdWordBreak" width = "5%"><div align="right">{{row.calGMPercentage}}%</div></td>
															<td width = "5%"><button type="button" style = "border: none; background-color: Transparent; cursor:pointer;overflow: hidden; background-repeat:no-repeat; color: blue; outline: none;" 
															class="btn btn-primary btnSpace" id="btnViewSearch" ng-disabled="btnDisable" 
															ng-click="downloadFile(row.attachmentID);"><img src="/RightPrice/resources/Images/download.png" alt="Snow" height="20" width="20" ></button></td>
														</tr>
													</tbody>
												</table>
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
							<td colspan="10" align="Center" bgcolor="#CCFFFF"><Strong>Rate Card Details</Strong></td>
						</tr>
					</tbody>
				</table>

				<table></table>
				
				<table border="1" 
					class="table  "
					id="tblRateCardDetails3">
					<tbody id="tBodyRateCardDetails">
						<tr>
							<th align="left" bgcolor="#CCFFFF">Rate Card ID</th>
							<th  align="left" bgcolor="#CCFFFF">Rate Card Name</th>
							<th  align="left" bgcolor="#CCFFFF">Customer Name</th>
							<th  align="left" bgcolor="#CCFFFF"> Vertical Name</th>
							<th align="left" bgcolor="#CCFFFF" ><div>Rate Card Start Date</div><div align="right">(mm/dd/yyyy)</div></th>
							<th  align="left" bgcolor="#CCFFFF"><div>Rate Card End Date</div><div align="right">(mm/dd/yyyy)</div></th>
							<th  align="left" bgcolor="#CCFFFF" >Onsite Location</th>
							<th align="left" bgcolor="#CCFFFF">Onsite%</th>
							<th  align="left" bgcolor="#CCFFFF">Offshore%</th>
							<th align="left" bgcolor="#CCFFFF">PM%</th>
						</tr>
						<tr ng-repeat="row in AtosRC"
							id="{{'ResourceData'+'_'+($index+1)}}">
							<td><div align="right">{{row.rcId}}</div></td>
							<td >{{row.rcName}}</td>
							<td>{{row.customerName}}</td>
							<td>{{row.verticalName}}</td>
							<td><div align="right">{{row.rcStartDate}}</div></td>
							<td><div align="right">{{row.rcEndDate}}</div></td>
						<!-- 	<td ><div class = "white-space-pre-line">{{row.loc}}</div></td> -->
							<td  ><div ng-repeat = "loc1 in row.loc1 track by $index" >{{loc1}}</div></td>
							<td><div align="right">{{row.onsitePer}}%</div></td>
							<td><div align="right">{{row.offshorePer}}%</div></td>
							<td><div align="right">{{row.calGMPercentage}}%</div></td>
						</tr>
					</tbody>
				</table>
			</div>
		</div>

								<div class="divEmptyThrice"></div>
     </fieldset>
	<div id="footer" ng-include="'${contextPath}/Portal/Footer.jsp'"></div>
	<div id="Footer" ng-include="'${contextPath}/Portal/TopBottomNavigation.jsp'"></div>
	<div id="loader" ng-include="'${contextPath}/Portal/loader.jsp'"></div>
</body>
</html>
