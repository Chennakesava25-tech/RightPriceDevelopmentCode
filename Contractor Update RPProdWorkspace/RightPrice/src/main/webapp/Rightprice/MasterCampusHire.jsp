<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>Eviden Syntel RightPrice Portal</title>
    <meta charset="utf-8">
<meta name="csrf-token" content="${_csrf.token}" />
<meta name="_csrf_header" content="${_csrf.headerName}" />
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="X-UA-Compatible" content="IE=Edge" />
   <link href="${contextPath}/resources/css/bootstrap.css" rel="stylesheet" />
    <link href="${contextPath}/resources/css/SAPStyleSheet.css" rel="stylesheet" />
    <link href="${contextPath}/resources/css/loader.css" rel="stylesheet" />
    <link href="${contextPath}/resources/css/AngularCSS.css" rel="stylesheet" />
    <script src="${contextPath}/resources/js/jquery.min.js"></script>
    <script src="${contextPath}/resources/js/bootstrap.js"></script>
    <script src="${contextPath}/resources/js/jquery.serializeJSON.min.js"></script>
    <script src="${contextPath}/resources/js/jquery-ui.js"></script> 	
    <link href="${contextPath}/resources/css/jquery-ui.css" rel="stylesheet" />
    <link href="${contextPath}/resources/css/bootstrap-dialog.css" rel="stylesheet" />
    <script src="${contextPath}/resources/js/bootstrap-dialog.js"></script>
    <script src="${contextPath}/resources/js/jquery.validate.js"></script>
    <script src="${contextPath}/resources/js/jqueryValidations.js"></script>
    <script src="${contextPath}/resources/js/additional-methods.js"></script>
    <script src="${contextPath}/resources/js/jqueryValidations.js"></script>
    <link href="${contextPath}/resources/css/ie10-viewport-bug-workaround.css" rel="stylesheet" />
	<link href="${contextPath}/resources/css/sticky-footer-navbar.css" rel="stylesheet" />
	<script src="${contextPath}/resources/js/ie10-viewport-bug-workaround.js"></script>
	<script src="${contextPath}/resources/js/angular.js"></script>
	<script src="${contextPath}/resources/js/angular-ui.min.js"></script>
	<script src="${contextPath}/resources/js/angular-messages.js"></script>
	<script src="${contextPath}/resources/js/ngStorage.js"></script>
	<script src="${contextPath}/resources/js/loader.js"></script>
	<script src="${contextPath}/resources/js/RightPrice/MasterCampusHire.js"></script>
	<script src="${contextPath}/resources/js/Directives/angularCustomValidations.js"></script>
	<script src="${contextPath}/resources/js/Factory/WebServiceFactory.js"></script>
</head>
<body ng-app="RightPriceApp" ng-controller="MasterCampusHireController">
    <div id="includedContent" ng-include="'${contextPath}/Portal/Header.jsp'"></div>
    <fieldset ng-disabled="loading || showLoader">
    <div class="container">
        <div class="divEmpty"></div>
        <div class="row marginBottom5px">
            <div class="col-sm-12">
                <h3 class="text-left">Master - Campus Hire</h3>
            </div>
        </div>	
        <div>
            <form class="form-inline" role="form" name="frmMasterID" id="frmMasterID">
            <div class="row">
					<div class="col-sm-12">
						<div class="panel-group">
							<div class="panel panel-info">
								<div class="panel-heading panelHeadingStyle ">
									<div class="row ">
										<label class="control-label col-sm-10 ">Select Deal</label>
									</div>
								</div>
								<div class="panel-body" >
									<div class="row marginBottom5px">
	                                    <div class="col-sm-6 col-sm-offset-3">
											<div class="table-responsive" >
												<table 
													class="table clsTable table-striped table-bordered table-hover"
													id="tblUpdateSalaryRate">
													<thead>
														<tr>
															<th>Deal Type</th>
															<th>Threshold in %</th>
														</tr>
													</thead>
													<tbody>
														<tr>
															<td>Fixed Price - Development</td>
															<td><input name="txtDevelopment" type="text" class="form-control" id="txtDevelopment" required 
															ng-model="mastercampushire.development" ng-class="{true: 'ng-border'}[(onSave && frmMasterID.txtDevelopment.$invalid)]" 
															ng-pattern="numberRegex" checklthundredpercente/>
																<div class="error-messages" ng-if= "onSave" ng-messages="frmMasterID.txtDevelopment.$error"> 
																	<em class="error help-block has-error" ng-message="required">Please Enter Value!</em>
																	<em class="error help-block has-error" ng-message="pattern">Please Enter Numbers upto 2 Decimal Places!</em>
																	<em class="error help-block has-error" ng-message="checklthundredpercente">Value Should Not Be More Than 100!</em>
																</div>
															</td>
														</tr>
														<tr>
															<td>Fixed Price - Maintenance</td>
															<td><input name="txtMaintenance" type="text" class="form-control" id="txtMaintenance" required ng-model="mastercampushire.maintenance" ng-class="{true: 'ng-border'}[(onSave && frmMasterID.txtMaintenance.$invalid)]" ng-pattern="numberRegex" checklthundredpercente/>
															<div class="error-messages" ng-if= "onSave" ng-messages="frmMasterID.txtMaintenance.$error">
																	<em class="error help-block has-error" ng-message="required">Please Enter Value!</em>
																	<em class="error help-block has-error" ng-message="pattern">Please Enter Numbers upto 2 Decimal Places!</em>
																	<em class="error help-block has-error" ng-message="checklthundredpercente">Value Should Not Be More Than 100!</em>
																</div>
															</td>
														</tr>	
														<tr>
															<td>Time & Material</td>
															<td><input name="txtTimeAndMeterial" type="text" class="form-control"  id="txtTimeAndMeterial" required ng-model="mastercampushire.timeandmeterial" ng-class="{true: 'ng-border'}[(onSave && frmMasterID.txtTimeAndMeterial.$invalid)]" ng-pattern="numberRegex" checklthundredpercente/>
															<div class="error-messages" ng-if= "onSave" ng-messages="frmMasterID.txtTimeAndMeterial.$error">
																	<em class="error help-block has-error" ng-message="required">Please Enter Value!</em>
																	<em class="error help-block has-error" ng-message="pattern">Please Enter Numbers upto 2 Decimal Places!</em>
																	<em class="error help-block has-error" ng-message="checklthundredpercente">Value Should Not Be More Than 100!</em>
																</div>
															</td>
														</tr>	
													</tbody>
												</table>
											</div>
										</div>
									</div>
                                    <div class="divEmptyThrice"></div>
                                    <div class="row text-center">
										<div class="col-sm-12">
											<button type="button" class="btn btn-primary btnSpace" id="btnSave" ng-click="onSaveClick(frmMasterID);">Save</button>
											<button type="button" class="btn btn-danger" id="btnCancel"ng-click="onCancelClick(frmMasterID);">Cancel</button>
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