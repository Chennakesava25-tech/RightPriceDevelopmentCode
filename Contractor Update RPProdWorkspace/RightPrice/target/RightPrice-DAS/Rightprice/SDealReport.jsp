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
	<script src="${contextPath}/resources/js/RightPrice/SDealReport.js"></script>
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
<body ng-app="RightPriceApp" ng-controller="SDealReportController" >
     <div id="includedContent" ng-include="'${contextPath}/Portal/Header.jsp'"></div>
     <fieldset ng-disabled="loading || showLoader">
    <div class="container">
        <div class="divEmpty"></div>
        <div class="row marginBottom5px">
            <div class="col-sm-12">
                <h3 class="text-left" id="PageHeading">Deal Summary Report</h3>
            </div>
        </div>	
        <div>
	      	<form class="form-inline" role="form" name="dealSummery" id="dealSummery">
	         	<div class="row">
						<div class="col-sm-12">
							<div class="panel-group">
								<div class="panel panel-info ">
									<div class="panel-heading panelHeadingStyle">
										<div class="row ">
											<label class="control-label col-sm-10">Deal Summary Details</label>
										</div>
									</div>
										<div class="panel-body">
											<div class="row marginBottom5px">
												<label
													class="control-label col-sm-2 textAlignRight ">Deal Status</label>
												<div class="col-sm-3">
													<select id="ddlStatus" class="form-control"
														placeholder="Please select" name="ddlStatus"
														ng-model="dealSummery.status"
														ng-options="st as st.description for st in status| orderBy:'ID'"
														ng-class="{true: 'ng-border'}[saved && dealSummery.ddlStatus.$invalid]">
														<option value="" disabled>Please select</option>
														
													</select>
<!-- 													<div class="error-messages" ng-if="saved"
														ng-messages="dealSummery.ddlStatus.$error">
														<em class="error help-block has-error"
															ng-message="required">Please select Customer</em>
													</div> -->
												</div>
												<label
													class="control-label col-sm-3 textAlignRight ">Vertical Name</label>
												<div class="col-sm-3 ">
													<select id="ddlddlVertical" class="form-control"
														placeholder="Please select" name="ddlddlVertical"
														ng-model="dealSummery.vertical"
														ng-options="vcl as vcl.verticalName for vcl in vertical| orderBy:'verticalName'"
														ng-change=""
														ng-class="{true: 'ng-border'}[cityAdd && dealSummery.ddlddlVertical.$invalid]">
														<option value="" disabled>Please select</option>
													</select>
													<div class="error-messages" ng-if="cityAdd"
														ng-messages="dealSummery.ddlddlVertical.$error">
														<em class="error help-block has-error"
															ng-message="required">Please select Country</em>
													</div>
												</div>
											</div>
										
										<div class="row marginBottom5px">
												<label
													class="control-label col-sm-2 textAlignRight ">
													As On Date<br>(dd/mm/yyyy) </label>
												<div class="col-sm-3">
													<input type="text" class="form-control customDate"
														placeholder="dd/mm/yyyy" id="txtRateCardStartDate"
														ng-disabled="isFirstTimePageLoaded"
														name="txtRateCardStartDate"
														ng-model="dealSummery.startdate"
														ng-class="{true: 'ng-border'}[saved && dealSummery.txtRateCardStartDate.$invalid]"
														ng-pattern="/^(((0[1-9]|[12]\d|3[01])\/(0[13578]|1[02])\/((19|[2-9]\d)\d{2}))|((0[1-9]|[12]\d|30)\/(0[13456789]|1[012])\/((19|[2-9]\d)\d{2}))|((0[1-9]|1\d|2[0-8])\/02\/((19|[2-9]\d)\d{2}))|(29\/02\/((1[6-9]|[2-9]\d)(0[48]|[2468][048]|[13579][26])|((16|[2468][048]|[3579][26])00))))$/i">
												</div>
											</div>
										</div>
									<div class="divEmptyThrice"></div>
									<!--   Table to display data   -->
									<div class="row text-center">
										<div class="col-sm-12">
											<button type="button" class="btn btn-primary btnSpace"
												id="btnDetailsSave"
												ng-click="exportRateCardData(dealSummery)">
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

	<div class="divEmptyThrice"></div>
     </fieldset>
	<div id="footer" ng-include="'${contextPath}/Portal/Footer.jsp'"></div>
	<div id="Footer" ng-include="'${contextPath}/Portal/TopBottomNavigation.jsp'"></div>
	<div id="loader" ng-include="'${contextPath}/Portal/loader.jsp'"></div>
</body>
</html>
