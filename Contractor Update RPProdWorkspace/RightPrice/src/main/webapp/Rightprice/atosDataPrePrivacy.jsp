<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Atos  RightPrice Portal</title>
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
<script	src="${contextPath}/resources/js/RightPrice/atosDataPrePrivacy.js"></script>
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
<body ng-app="RightPriceApp" ng-controller="atosDataPrePrivacyController"
	ng-focus="customDatePicker()">
<%-- 	ng-focus="customDatePicker()" ng-init="currentUser('<%=org.springframework.security.core.context.SecurityContextHolder.getContext().getAuthentication().getName()%>')"> --%>
	<div id="includedContent"
		ng-include="'${contextPath}/Portal/atosHeaderLink.jsp'"></div>
<%-- 	<fieldset ng-disabled="loading || showLoader">
		
	      <div class="container">
         <form class="form-inline" role="form" name="atosLinkForm" id="atosLinkForm" ng-model="atosLinkForm">
				<div class="col-sm-6" align="center" style="width: 81%; height: 90%; padding: 20% 0% 0% 34%;">
					<!-- general form elements -->
					<div class="box box-solid box-info" align="center">
						<div class="box-header"></div>
						<!-- /.box-header -->
						<div class="box-body" align="center">
							<!-- <div class="para_format"> -->
							<div class="panel-group">
								<div class="panel panel-primary" style="width: 98%;">
									<div class="panel-body">
										<div class="row">
											<div class="col-md-12" style="font-family: Verdana; font-size: 10px;text-align: justify; 
												text-justify: inter-word; width: 98%; background-color: white; color: black" align="left">
												Remember, by accessing to this Atos-Syntel application, you are likely to process personal data of Atos' employees, client
												and partners. 
												<a href="${contextPath}/dataPrivacy" target="_blank" style="color: #0066a1"> 
												<u><b>Read the basic rules we follow as a duty to respect privacy</b></u></a><br>
											</div>
										</div>
										<div class="divEmptyThrice "></div>
										<div class="row">
											<div class="col-md-12" style="text-align:left;">
												<div class="checkbox">
											      	<label>
														<input type="checkbox" value="" id="data_check" name="data_check" value="acceptance"
															ng-model="selected" ng-change="isSelectedCheck(selected)"> 
															I understand, and I agree to proceed.
													</label>
											    </div>
    
    
												<!-- <input type="checkbox" id="data_check" name="data_check" value="acceptance" style="width: 17px; height: 16px;"
													ng-model="selected" ng-change="isSelectedCheck(selected)">
													<label for="data_check">I understand, and I agree to proceed.</label> -->
											</div>
										</div>
										<div class="divEmptyThrice "></div>
										<div class="row text-center">
											<div class="col-md-12">
												<button type="button" class="btn btn-primary btn-flat" style="background-color: #0066a1; color: white; font-size: 12px;"
													ng-click="authenticate()" ng-disabled="isSubmitDisabled" id="btnSub"> <b>Submit</b>
												</button>
											</div>
										</div>
										<div class="divEmptyThrice "></div>
										</div>
										<div class="row">
											<div class="col-md-12">
												<div class="atos-panel-heading">
													<b> In order to know more about your obligations
														regarding personal data of your colleagues, please,
														consult here </b><a
														href="https://wac.das.myatos.net/portal/pki.jsp?DXATargetMethod=GET&DXA-authn-method=Login+Selector&DXATargetUrl=https%3A%2F%2Fwac.das.myatos.net%3A80%2Fws2fed_idp%2Ffederation%3Fwtrealm%3Durn%253Asp2013.myatos.net%253Asharepoint%26wctx%3Dhttps%253A%252F%252Fsp2013.myatos.net%252Forg%252FLegal%252FCompliance%252F_layouts%252F15%252FAuthenticate.aspx%253FSource%253D%25252Forg%25252FLegal%25252FCompliance%25252FPages%25252FData%252520Protection%252520%25252D%252520All%252520Pages%25252FData%252520Protection%252520%25252D%252520Home%252520page%25252Easpx%26wa%3Dwsignin1.0%26wreply%3Dhttps%253A%252F%252Fsp2013.myatos.net%252F_trust%252Fdefault.aspx"
														target="_blank" style="color: #0066a1"><u><b>Data
																Protection SharePoint</b></u></a> <b>and the</b> <a
														href="https://wac.das.myatos.net/portal/pki.jsp?DXATargetMethod=GET&DXA-authn-method=Login+Selector&DXATargetUrl=https%3A%2F%2Fwac.das.myatos.net%3A80%2Fws2fed_idp%2Ffederation%3Fwtrealm%3Durn%253Asp2013.myatos.net%253Asharepoint%26wctx%3Dhttps%253A%252F%252Fsp2013.myatos.net%252Forg%252FLegal%252FCompliance%252F_layouts%252F15%252FAuthenticate.aspx%253FSource%253D%25252Forg%25252FLegal%25252FCompliance%25252FPages%25252FData%252520Protection%252520%25252D%252520All%252520Pages%25252FData%252520Protection%252520%25252D%252520Home%252520page%25252Easpx%26wa%3Dwsignin1.0%26wreply%3Dhttps%253A%252F%252Fsp2013.myatos.net%252F_trust%252Fdefault.aspx"
														target="_blank" style="color: #0066a1"><b><u>Standard
																Contractual Clauses</u></b></a> <b>concluded for the transfer of
														personal data between Atos and Syntel.</b>
												</div>
											</div>
										</div>
							</div>

							<br>

							</div> 
						</div>
					</div>
				</div>

			</form>
       </div>	
		
		
		
	</fieldset> --%>
	   	<fieldset ng-disabled="loading || showLoader">
		
	      <div class="container">

         <form class="form-inline" role="form" name="atosLinkForm" id="atosLinkForm" ng-model="atosLinkForm">
				<div class="col-sm-6" align="center" style="width: 81%; height: 90%; padding: 20% 0% 0% 34%;">
					<!-- general form elements -->
					<div class="box box-solid box-info" align="center">
						<div class="box-header"></div>
						<!-- /.box-header -->
						<div class="box-body" align="center">
							<!-- <div class="para_format"> -->
							<div class="panel-group">
								<div class="panel panel-primary" style="width: 98%;">
									<div class="panel-body">
										<div class="row">
											<div class="col-md-12" style="font-family: Verdana; font-size: 10.5px; 
												width: 100%; background-color: white; color: black" align="left">
												Remember, by accessing to this Atos-Syntel application, you are likely to process personal data of Atos' employees, client
												and partners. 
												<a href="${contextPath}/dataPrivacy" target="_blank" style="color: #0066a1"> 
												<u><b>Read the basic rules we follow as a duty to respect privacy</b></u></a><br>
											</div>
										</div>
										<div class="divEmptyThrice "></div>
										<div class="row">
											<div class="col-md-12" style="text-align:left;font-family: Verdana; font-size: 10.5px; 
												width: 100%;">
												<div class="checkbox">
											      	<label>
														<input type="checkbox" value="" id="data_check" name="data_check" value="acceptance"
															ng-model="selected" ng-change="isSelectedCheck(selected)"> 
															<b></>I understand, and I agree to proceed.</b>
													</label>
											    </div>
    
    
												<!-- <input type="checkbox" id="data_check" name="data_check" value="acceptance" style="width: 17px; height: 16px;"
													ng-model="selected" ng-change="isSelectedCheck(selected)">
													<label for="data_check">I understand, and I agree to proceed.</label> -->
											</div>
										</div>
										<div class="divEmptyThrice "></div>
										<div class="row text-center">
											<div class="col-md-12">
												<button type="button" class="btn btn-primary btn-flat" style="background-color: #0066a1; color: white; font-size: 12px;"
													ng-click="authenticate()" ng-disabled="isSubmitDisabled" id="btnSub"> <b>Submit</b>
												</button>
											</div>
										</div>
										<div class="divEmptyThrice "></div>
										</div>
										<div class="row">
											<div class="col-md-12" >
												<div class="atos-panel-heading" style="font-family: Verdana; font-size: 10.5px; 
												width: 100%; " align="left">
													    In order to know more about your obligations
														regarding personal data of your colleagues, please,
														consult here <a
														href="https://wac.das.myatos.net/portal/pki.jsp?DXATargetMethod=GET&DXA-authn-method=Login+Selector&DXATargetUrl=https%3A%2F%2Fwac.das.myatos.net%3A80%2Fws2fed_idp%2Ffederation%3Fwtrealm%3Durn%253Asp2013.myatos.net%253Asharepoint%26wctx%3Dhttps%253A%252F%252Fsp2013.myatos.net%252Forg%252FLegal%252FCompliance%252F_layouts%252F15%252FAuthenticate.aspx%253FSource%253D%25252Forg%25252FLegal%25252FCompliance%25252FPages%25252FData%252520Protection%252520%25252D%252520All%252520Pages%25252FData%252520Protection%252520%25252D%252520Home%252520page%25252Easpx%26wa%3Dwsignin1.0%26wreply%3Dhttps%253A%252F%252Fsp2013.myatos.net%252F_trust%252Fdefault.aspx"
														target="_blank" style="color: #0066a1"><u><b>Data
																Protection SharePoint</b></u></a> and the <a
														href="https://wac.das.myatos.net/portal/pki.jsp?DXATargetMethod=GET&DXA-authn-method=Login+Selector&DXATargetUrl=https%3A%2F%2Fwac.das.myatos.net%3A80%2Fws2fed_idp%2Ffederation%3Fwtrealm%3Durn%253Asp2013.myatos.net%253Asharepoint%26wctx%3Dhttps%253A%252F%252Fsp2013.myatos.net%252Forg%252FLegal%252FCompliance%252F_layouts%252F15%252FAuthenticate.aspx%253FSource%253D%25252Forg%25252FLegal%25252FCompliance%25252FPages%25252FData%252520Protection%252520%25252D%252520All%252520Pages%25252FData%252520Protection%252520%25252D%252520Home%252520page%25252Easpx%26wa%3Dwsignin1.0%26wreply%3Dhttps%253A%252F%252Fsp2013.myatos.net%252F_trust%252Fdefault.aspx"
														target="_blank" style="color: #0066a1"><b><u>Standard
																Contractual Clauses</u></b></a> concluded for the transfer of
														personal data between Atos and Syntel.
												</div>
											</div>
										</div>
							</div>

							<br>

							</div> 
						</div>
					</div>
				</div>

			</form>
       </div>	
		
		
		
	</fieldset>
	
	<div id="footer" ng-include="'${contextPath}/Portal/Footer.jsp'"></div>
	<div id="Footer"
		ng-include="'${contextPath}/Portal/TopBottomNavigation.jsp'"></div>
	<div id="loader" ng-include="'${contextPath}/Portal/loader.jsp'"></div>
</body>
</html>
