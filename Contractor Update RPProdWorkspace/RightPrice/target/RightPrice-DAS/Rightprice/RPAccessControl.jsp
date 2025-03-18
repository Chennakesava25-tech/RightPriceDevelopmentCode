\<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
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
	<script src="${contextPath}/resources/js/RightPrice/RPAccessControl.js"></script>
	<script src="${contextPath}/resources/js/Directives/angularCustomValidations.js"></script>
	<script src="${contextPath}/resources/js/Factory/WSAngularService.js"></script>
	<script src="${contextPath}/resources/js/Factory/WebServiceFactory.js"></script>
</head>
<body ng-app="RightPriceApp" ng-controller="AccessControlController" >
 <div id="includedContent" ng-include="'${contextPath}/Portal/Header.jsp'"></div>
 <fieldset ng-disabled="loading || showLoader">
    <div class="container">
        <div class="divEmpty"></div>
        <div class="row marginBottom5px">
            <div class="col-sm-12">
                <h3 class="text-left" id="PageHeading">RP - AccessControl</h3>
            </div>
        </div>	
        <div>
            <form class="form-inline" role="form" name="Master" id="Master">
            <div class="row">
                    <div class="col-sm-12">
                        <div class="panel-group">
                        <ng-form name="rpAccessControlForm" id="rpAccessControlForm" >
                            <div class="panel panel-info ">
                                <div class="panel-heading panelHeadingStyle">
                                    <div class="row ">
										<label class="control-label col-sm-10 ">Access Control</label>
									</div>
                                </div>
                                <div class="panel-body">
                                 <!-- <div class="row marginBottom5px">
                                 <label class="control-label col-sm-2 textAlignRight">GFT User</label>
                                        <div class="col-sm-3">
                                        	<input type="checkbox" name="IsGftUser" id="IsGftUser" class="margingRightChkBx"
                                        		ng-model="rpAccessControlForm.isgftuser">
                                        </div>
                                 </div> -->
                                     <div class="row marginBottom5px">
                                     	<label class="control-label col-sm-2 textAlignRight required-Field">DAS ID</label>
                                        <div class="col-sm-3">
                                            <input name="txtEmpID" type="text" class="form-control" id="txtEmpID"
                                            		ng-model="rpAccessControlForm.empidmodel"  
                                            		ng-change="getUserDetails(rpAccessControlForm.empidmodel);" ng-model-options="{updateOn: 'blur'}" 
                                            		ng-class="{true: 'ng-border'}[(onSave && addMasterVerticalForm.txtAddDeliveryHeadEmpID.$invalid)]" required>
                                            <div class="error-messages" ng-if= "onSave" ng-messages="addMasterVerticalForm.txtAddDeliveryHeadEmpID.$error">
									        	<em class="error help-block has-error" ng-message="required">Please Enter DAS ID</em>
									        </div>
                                        </div>
                                     	<label class="control-label col-sm-3 textAlignRight required-Field">Vertical</label>
                                        <div class="col-sm-3">
                                            <input name="txtVerticalName" type="text" class="form-control" id="txtVerticalName" ng-model-options="{updateOn: 'blur'}"
                                            		ng-model="rpAccessControlForm.verticalnamemodel" readonly>
                                        </div>
                                     </div>
                                     <div class="row marginBottom5px">
                                     <label class="control-label col-sm-2 textAlignRight required-Field">Employee Name</label>
                                        <div class="col-sm-3">
                                            <input name="txtEmployeeName" type="text" class="form-control" id="txtEmployeeName" ng-model-options="{updateOn: 'blur'}"
                                            		ng-model="rpAccessControlForm.employeenamemodel" ng-change="getUserDetails(rpAccessControlForm.empidmodel);" readonly>
                                        </div>
                                    </div>
                                    <div class="divEmptyThrice"></div>
                                    <div id="divRoleDetails" class="row marginBottom5px" >
                                     	<label class="control-label col-sm-1 textAlignRight required-Field">Customer Name</label>
                                        <div class="col-sm-4">
                                        <select class="form-control" name="ddlRolesSelectFrom" id="ddlRolesSelectFrom" ng-model="selectedAvailItems"
										    	multiple size="10" ng-options="(emd.customer.customerName +'  ( '+ emd.customer.customerId + ' ) ') for emd in customes | orderBy:'customer.customerName'">									    	
										    </select>
                                        </div>
                                     	<div class="col-sm-3 text-center" style="padding:50px;">
										   <button type="button" class="btn btn-primary btnWidth79px" id="btnAdd" ng-click="btnRight($index);" ng-disabled="!selectedAvailItems.length">Add</button>
										     <div class="divEmpty"></div>
										    <button type="button" class="btn btn-primary btnWidth79px" id="btnRemove" ng-click="btnLeft($index);" ng-disabled="!selectedSelectedItemList.length">Remove</button>
									 	</div> 
									 	<div class="col-sm-4 col-xs-4">
										    <select  class="form-control" name="ddlRolesSelectTo" id="ddlRolesSelectTo"ng-model="selectedSelectedItemList" 
										    multiple size="10" ng-options="sil.customer.customerName for sil in selectedItemList | orderBy:'customer.customerName'">
										    </select>
										 </div> 
                                    </div>
									<div class="divEmptyThrice"></div>
                                    <div class="row text-center">
										<div class="col-sm-12">		
											<button type="button" class="btn btn-primary btnSpace" id="btnAddSave" ng-disabled="saveDisable" ng-click="addRPAccessControlData();">Save</button>						
											<button type="button" class="btn btn-danger" id="btnAddCancel" ng-click="clickCancle(rpAccessControlForm);">Cancel</button>
										</div>
									</div>
                                   	<div class="divEmptyThrice"></div>
                                </div>
                            </div>
                            </ng-form>
                        </div>
                    </div>
                </div>
           </form>
        </div>
    </div>
    </fieldset>
	<div id="footer" ng-include="'${contextPath}/Portal/Footer.jsp'"></div>
	<div id="Footer" ng-include="'${contextPath}/Portal/TopBottomNavigation.jsp'"></div>
	<div id="loader" ng-include="'${contextPath}/Portal/loader.jsp'"></div>
</body>
</html>
