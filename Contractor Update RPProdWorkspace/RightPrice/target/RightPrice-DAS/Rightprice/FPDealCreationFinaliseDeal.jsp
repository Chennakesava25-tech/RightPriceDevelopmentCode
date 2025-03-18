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
	<script src="${contextPath}/resources/js/RightPrice/FPDealCreationFinaliseDeal.js"></script>
	<script src="${contextPath}/resources/js/Factory/WebServiceFactory.js"></script>
</head>
<body ng-app="RightPriceApp" ng-controller="FPDealCreationFinalizeDealController" >
    <div id="includedContent" ng-include="'${contextPath}/Portal/Header.jsp'"></div>
    	<fieldset ng-disabled="loading || showLoader">
    <div class="container">
        <div class="divEmpty"></div>
        <div class="row marginBottom5px">
            <div class="col-sm-12">
                <h3 class="text-left">FP Deal Creation - Finalise Deal</h3>
            </div>
        </div>	
        <div>
            <form class="form-inline" role="form" name="Master" id="Master">
				<div id="includedRateCardStages" ng-include="'${contextPath}/Portal/FPdealCompletionStage.jsp'"></div>
				<div id="includedRateCardStages" ng-include="'${contextPath}/Rightprice/DealCreationInformationTable.jsp'"></div>
				<div id="includedRateCardStages" ng-include="'${contextPath}/Rightprice/DealCreationFinaliseDeal.jsp'"></div>
	       	</form>
        </div>
    </div>
     </fieldset>
	<div id="footer" ng-include="'${contextPath}/Portal/Footer.jsp'"></div>
	<div id="Footer" ng-include="'${contextPath}/Portal/TopBottomNavigation.jsp'"></div>
	<div id="loader" ng-include="'${contextPath}/Portal/loader.jsp'"></div>
</body>
</html>
