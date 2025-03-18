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

<script
	src="${contextPath}/resources/js/crypto-js.min.js"></script>
<script
	src="${contextPath}/resources/js/aes.js"></script>
	<script
	src="${contextPath}/resources/js/core.min.js"></script>
	<script
	src="${contextPath}/resources/js/cipher-core.min.js"></script>
	<script
	src="${contextPath}/resources/js/mode-cfb.min.js"></script>
	<script
	src="${contextPath}/resources/js/pad-pkcs7.min.js"></script>
<script
	src="${contextPath}/resources/js/pbkdf2.js"></script>
	<script
	src="${contextPath}/resources/js/RightPrice/DeligateUserDetails.js"></script>
<script
	src="${contextPath}/resources/js/Directives/angularCustomValidations.js"></script>
<script src="${contextPath}/resources/js/Factory/WebServiceFactory.js"></script>
</head>
<body ng-app="RightPriceApp" ng-controller="DeligateUserController"
	ng-init="getDelicgateUserAccess('<%=session.getAttribute("user")%>');">
	<div id="includedContent"
		ng-include="'${contextPath}/Portal/Header.jsp'"></div>
	<fieldset ng-disabled="loading || showLoader">
		<div class="container">
			<div class="divEmpty"></div>
			<div class="row marginBottom5px">
				<div class="col-sm-12">
					<h3 class="text-left">Profile Selection</h3>
				</div>
			</div>
			<div>
				<form class="form-inline" role="form" name="deligateUserForm"
					id="deligateUserForm" novalidate>
					<div class="row">
						<div class="col-sm-12">
							<div class="panel-group">
								<div class="panel panel-info">
									<div class="panel-heading panelHeadingStyle"
										ng-click="ShowHideSearch()">
										<div class="row ">
											<label class="control-label col-sm-10 ">User
												Selection</label> <label
												class="DownArrowColor col-sm-2  textAlignRight">
												&#9660;</label>
										</div>
									</div>
									<div class="panel-body" ng-hide="SearchHidden">
										<div class="divEmptyThrice"></div>
										<div class="row marginBottom5px">
											<label
												class="control-label col-sm-2 col-sm-offset-3 textAlignRight required-Field">Select
												User</label>
											<div class="col-sm-3">
												<select id="ddlProxyUser" class="form-control"
													ng-model="deligateUserForm.proxyModel"
													placeholder="Please select" name="ddlProxyUser"
													ng-options="cou.id as cou.proxyADId for cou in deligateUser"
													ng-class="{true: 'ng-border'} [(onSubmit && deligateUserForm.ddlProxyUser.$invalid)]"
													required>
													<option value="" selected disabled>Please select</option>
												</select>
												<div class="error-messages" ng-if="onSubmit"
													ng-messages="deligateUserForm.ddlProxyUser.$error">
													<em class="error help-block has-error"
														ng-message="required">Please select required user.</em>
												</div>
											</div>
										</div>

										<div class="divEmptyThrice"></div>
										<div class="row text-center">
											<div class="col-sm-12">
												<button type="button" class="btn btn-primary btnSpace"
													id="btnSearchSearch"
													ng-click="validateForm(deligateUserForm.$valid)">Go</button>
											</div>
										</div>
										<div class="divEmptyThrice"></div>
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