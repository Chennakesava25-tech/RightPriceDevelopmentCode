<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
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
	<script src="${contextPath}/resources/js/RightPrice/MastersVertical.js"></script>
	<script src="${contextPath}/resources/js/Directives/angularCustomValidations.js"></script>
	<script src="${contextPath}/resources/js/Factory/WebServiceFactory.js"></script>
</head>
<body ng-app="RightPriceApp" ng-controller="MastersVerticalController" >
 <div id="includedContent" ng-include="'${contextPath}/Portal/Header.jsp'"></div>
 <fieldset ng-disabled="loading || showLoader">
    <div class="container">
        <div class="divEmpty"></div>
        <div class="row marginBottom5px">
            <div class="col-sm-12">
                <h3 class="text-left" id="PageHeading">Master - Vertical</h3>
            </div>
        </div>	
        <div>
            <form class="form-inline" role="form" name="Master" id="Master">
            <div class="row">
					<div class="col-sm-12">
						<div class="panel-group">
							<div class="panel panel-info">
								<div class="panel-heading panelHeadingStyle "  ng-click="ShowHideView()">
									<div class="row ">
										<label class="control-label col-sm-10 ">View Verticals</label>
										<label class="DownArrowColor col-sm-2  textAlignRight"> &#9660;</label>
									</div>
								</div>
								<div class="panel-body" ng-hide = "ViewHidden">
                                    <div class="row marginBottom5px">
	                                    <div class="col-sm-12">
											<div class="table-responsive  " >
												<table 
													class="table clsTable table-striped table-bordered table-hover table-condensed "
													id="tblViewVertical">
													<thead>
														<tr>
															<th>Vertical</th>
															<th>Delivery Head</th>
															<th>BU Head</th>
															<th>RiskManagers Employee Head</th>
														</tr>
													</thead>
													<tbody id="tBodyViewVertical" >
														<tr id="{{'trverticalView'+'_'+($index+1)}}" ng-repeat="row in vertical">
															<td >{{(row.verticalName)}}</td>
															<td>{{(row.duh)}}</td>
															<td>{{(row.buh)}}</td>
															<td>{{(row.RiskManagers)}}</td>
														</tr>				
													</tbody>
												</table>
											</div>
										</div>
									</div>
									<div class="divEmptyThrice"></div>
									<div class="row text-center">
										<div class="col-sm-12">		
											<button type="button" class="btn btn-primary btnSpace" id="btnDownloadViewVertical" ng-click = "getVerticalExcel();">Download Vertical</button>	
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
                <div class="row">
                    <div class="col-sm-12">
                        <div class="panel-group">
                        <ng-form name="addMasterVerticalForm" id="addMasterVerticalForm" >
                            <div class="panel panel-info ">
                                <div class="panel-heading panelHeadingStyle" ng-click="ShowHideAdd()">
                                    <div class="row ">
										<label class="control-label col-sm-10 ">Add Vertical</label>
										<label class="DownArrowColor col-sm-2  textAlignRight"> &#9660;</label>
									</div>
                                </div>
                                <div class="panel-body" ng-hide = "AddHidden">
                                     <div class="row marginBottom5px">
                                     <label class="control-label col-sm-2 textAlignRight required-Field">Vertical</label>
                                     <div class="col-sm-2">
                                            <select id="ddlAddVertical" class="form-control" placeholder="Please select"
                                           		   name="ddlAddVertical" ng-model="addmasterverticalform.addverticalmodel" 
                                           		   ng-options="vn as vn.verticalGrpDesc for vn in verticalgroup |orderBy:'verticalGrpDesc'" 
                                           		   ng-class="{true: 'ng-border'}[(onSave && addMasterVerticalForm.ddlAddVertical.$invalid)]"  required>
                                           		   <!-- ng-change="compaireVertical(addmasterverticalform.addverticalmodel);"  -->
												<option value="" selected disabled>Please select</option>
											</select>
											<div class="error-messages" ng-if= "onSave" ng-messages="addMasterVerticalForm.ddlAddVertical.$error">
									        	<em class="error help-block has-error" ng-message="required">Please Select Vertical!</em>
									        </div>
                                        </div>
                                    </div>
                                     <div class="row marginBottom5px">
                                     	<label class="control-label col-sm-2 textAlignRight required-Field">Delivery Head Employee ID</label>
                                        <div class="col-sm-2">
                                            <input name="txtAddDeliveryHeadEmpID" type="text" class="form-control" id="txtAddDeliveryHeadEmpID"
                                            		ng-model="addmasterverticalform.adddeliveryheadempidmodel"  
                                            		ng-change="getUserDetailsForAdd(addmasterverticalform.adddeliveryheadempidmodel);" ng-model-options="{updateOn: 'blur'}" 
                                            		ng-class="{true: 'ng-border'}[(onSave && addMasterVerticalForm.txtAddDeliveryHeadEmpID.$invalid)]" required>
                                            <div class="error-messages" ng-if= "onSave" ng-messages="addMasterVerticalForm.txtAddDeliveryHeadEmpID.$error">
									        	<em class="error help-block has-error" ng-message="required">Please Enter Delivery Head Employee ID</em>
									        </div>
                                        </div>
                                     	<label class="control-label col-sm-2 textAlignRight required-Field">Name</label>
                                        <div class="col-sm-2">
                                            <input name="txtAddDeliveryHeadName" type="text" class="form-control" id="txtAddDeliveryHeadName"
                                            		ng-model="addmasterverticalform.adddeliveryheadnamemodel"  readonly>
                                        </div>
                                      	<label class="control-label col-sm-2 textAlignRight required-Field">Email Id</label>
                                        <div class="col-sm-2">
                                            <input name="txtAddDeliveryHeadEmailId" type="text" class="form-control" id="txtAddDeliveryHeadEmailId"
                                            		ng-model="addmasterverticalform.adddeliveryheademailidmodel"  readonly>
                                        </div>  
                                    </div>
                                    <div class="row marginBottom5px">
                                     	<label class="control-label col-sm-2 textAlignRight required-Field">BUH Employee ID</label>
                                        <div class="col-sm-2">
                                            <input name="txtAddBUHEmpID" type="text" class="form-control" id="txtAddBUHEmpID"
                                            		ng-model="addmasterverticalform.addbuhempidmodel" 
                                            		ng-change="getUserDetailsForAdd(addmasterverticalform.addbuhempidmodel);" ng-model-options="{updateOn: 'blur'}" 
                                            		ng-class="{true: 'ng-border'}[(onSave && addMasterVerticalForm.txtAddBUHEmpID.$invalid)]" required>
                                            <div class="error-messages" ng-if= "onSave" ng-messages="addMasterVerticalForm.txtAddBUHEmpID.$error">
									        	<em class="error help-block has-error" ng-message="required">Please Enter BUH Employee ID</em>
									        </div>
                                        </div>
                                     	<label class="control-label col-sm-2 textAlignRight required-Field">Name</label>
                                        <div class="col-sm-2">
                                            <input name="txtAddBUHName" type="text" class="form-control" id="txtAddBUHName"
                                            		ng-model="addmasterverticalform.addbuhnamemodel"  readonly>
                                        </div>
                                      	<label class="control-label col-sm-2 textAlignRight required-Field">Email Id</label>
                                        <div class="col-sm-2">
                                            <input name="txtAddBUHEmailId" type="text" class="form-control" id="txtAddBUHEmailId"
                                            		ng-model="addmasterverticalform.addbuhemailidmodel" readonly>
                                        </div>  
                                    </div>
                                    <div class="row marginBottom5px">
                                     	<label class="control-label col-sm-2 textAlignRight required-Field">RiskManagers Employee ID</label>
                                        <div class="col-sm-2">
                                            <input name="txtAddRiskManagersEmpID" type="text" class="form-control" id="txtAddRiskManagersEmpID"
                                            		ng-model="addmasterverticalform.addRiskManagersempidmodel"
                                            		ng-change="getUserDetailsForAdd(addmasterverticalform.addRiskManagersempidmodel);" ng-model-options="{updateOn: 'blur'}" 
                                            		ng-class="{true: 'ng-border'}[(onSave && addMasterVerticalForm.txtAddRiskManagersEmpID.$invalid)]" required>
                                            <div class="error-messages" ng-if= "onSave" ng-messages="addMasterVerticalForm.txtAddRiskManagersEmpID.$error">
									        	<em class="error help-block has-error" ng-message="required">Please Enter RiskManagers Employee Head ID</em>
									        </div>
                                        </div>
                                     	<label class="control-label col-sm-2 textAlignRight required-Field">Name</label>
                                        <div class="col-sm-2">
                                            <input name="txtAddRiskManagersName" type="text" class="form-control" id="txtAddRiskManagersName"
                                            		ng-model="addmasterverticalform.addRiskManagersnamemodel"  readonly>
                                        </div>
                                      	<label class="control-label col-sm-2 textAlignRight required-Field">Email Id</label>
                                        <div class="col-sm-2">
                                            <input name="txtAddRiskManagersEmailId" type="text" class="form-control" id="txtAddRiskManagersEmailId"
                                            		ng-model="addmasterverticalform.addRiskManagersemailidmodel"  readonly>
                                        </div>  
                                    </div>
									<div class="divEmptyThrice"></div>
                                    <div class="row text-center">
										<div class="col-sm-12">		
											<button type="button" class="btn btn-primary btnSpace" id="btnAddSave" ng-click="onSaveClick(addMasterVerticalForm);">Save</button>						
											<button type="button" class="btn btn-danger" id="btnAddCancel" ng-click="cancelClickOnAdd(addMasterVerticalForm);">Cancel</button>
										</div>
									</div>
                                   	<div class="divEmptyThrice"></div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                 <div class="row">
                    <div class="col-sm-12">
                        <div class="panel-group">
                        <ng-form name="updateMasterVerticalForm" id="updateMasterVerticalForm" >
                            <div class="panel panel-info ">
                                <div class="panel-heading panelHeadingStyle" ng-click="ShowHideUpdate()">
                                    <div class="row ">
										<label class="control-label col-sm-10 ">Update Vertical</label>
										<label class="DownArrowColor col-sm-2  textAlignRight"> &#9660;</label>
									</div>
                                </div>
                                <div class="panel-body" ng-hide = "UpdateHidden">
                                	 <div class="row marginBottom5px">
                                     <label class="control-label col-sm-2 textAlignRight required-Field">Vertical</label>
                                        <div class="col-sm-2">
                                            <select id="ddlUpdateVertical" class="form-control" placeholder="Please select"
                                           		   name="ddlUpdateVertical" ng-model="updatemasterverticalform.updateverticalmodel" 
                                           		   ng-options="vn as vn.verticalName for vn in vertical" 
                                           		   ng-change="putverticalDetails(updatemasterverticalform.updateverticalmodel);"
                                           		   ng-class="{true: 'ng-border'}[(onUpdate && updateMasterVerticalForm.ddlUpdateVertical.$invalid)]" required>
												<option value="" selected disabled>Please select</option>
											</select>
											<div class="error-messages" ng-if= "onUpdate" ng-messages="updateMasterVerticalForm.ddlUpdateVertical.$error">
									        	<em class="error help-block has-error" ng-message="required">Please Select Vertical!</em>
									        </div>
                                        </div>
                                     </div>
                               		 <div class="row marginBottom5px">
                                     	<label class="control-label col-sm-2 textAlignRight required-Field">Delivery Head Employee ID</label>
                                        <div class="col-sm-2">
                                            <input name="txtUpdateDeliveryHeadEmpID" type="text" class="form-control" id="txtUpdateDeliveryHeadEmpID"
                                            		ng-model="updatemasterverticalform.updatedeliveryheadempidmodel"
                                            		ng-change="getUserDetailsForUpdate(updatemasterverticalform.updatedeliveryheadempidmodel);" ng-model-options="{updateOn: 'blur'}" 
                                            		 ng-class="{true: 'ng-border'}[(onUpdate && updateMasterVerticalForm.txtUpdateDeliveryHeadEmpID.$invalid)]" required>
                                            <div class="error-messages" ng-if= "onUpdate" ng-messages="updateMasterVerticalForm.txtUpdateDeliveryHeadEmpID.$error">
									        	<em class="error help-block has-error" ng-message="required">Please Enter Delivery Head Employee ID</em>
									        </div>
                                        </div>
                                     	<label class="control-label col-sm-2 textAlignRight required-Field">Name</label>
                                        <div class="col-sm-2">
                                            <input name="txtUpdateDeliveryHeadName" type="text" class="form-control" id="txtUpdateDeliveryHeadName"
                                            		ng-model="updatemasterverticalform.updatedeliveryheadnamemodel"  readonly>
                                        </div>
                                      	<label class="control-label col-sm-2 textAlignRight required-Field">Email Id</label>
                                        <div class="col-sm-2">
                                            <input name="txtUpdateDeliveryHeadEmailId" type="text" class="form-control" id="txtUpdateDeliveryHeadEmailId"
                                            		ng-model="updatemasterverticalform.updatedeliveryheademailidmodel"  readonly>
                                        </div>  
                                    </div>
                                    <div class="row marginBottom5px">
                                     	<label class="control-label col-sm-2 textAlignRight required-Field">BUH Employee ID</label>
                                        <div class="col-sm-2">
                                            <input name="txtUpdateBUHEmpID" type="text" class="form-control" id="txtUpdateBUHEmpID"
                                            		ng-model="updatemasterverticalform.updatebuhempidmodel" 
                                            		ng-change="getUserDetailsForUpdate(updatemasterverticalform.updatebuhempidmodel);" ng-model-options="{updateOn: 'blur'}" 
                                            		 ng-class="{true: 'ng-border'}[(onUpdate && updateMasterVerticalForm.txtUpdateBUHEmpID.$invalid)]" required>
                                             <div class="error-messages" ng-if= "onUpdate" ng-messages="updateMasterVerticalForm.txtUpdateBUHEmpID.$error">
									        	<em class="error help-block has-error" ng-message="required">Please Enter BUH Employee ID</em>
									        </div>
                                        </div>
                                     	<label class="control-label col-sm-2 textAlignRight required-Field">Name</label>
                                        <div class="col-sm-2">
                                            <input name="txtUpdateBUHName" type="text" class="form-control" id="txtUpdateBUHName"
                                            		ng-model="updatemasterverticalform.updatebuhnamemodel"  readonly>
                                        </div>
                                      	<label class="control-label col-sm-2 textAlignRight required-Field">Email Id</label>
                                        <div class="col-sm-2">
                                            <input name="txtUpdateBUHEmailId" type="text" class="form-control" id="txtUpdateBUHEmailId"
                                            		ng-model="updatemasterverticalform.updatebuhemailidmodel"  readonly>
                                        </div>  
                                    </div>
                                    <div class="row marginBottom5px">
                                     	<label class="control-label col-sm-2 textAlignRight required-Field">RiskManagers Employee ID</label>
                                        <div class="col-sm-2">
                                            <input name="txtUpdateRiskManagersEmpID" type="text" class="form-control" id="txtUpdateRiskManagersEmpID"
                                            		ng-model="updatemasterverticalform.updateRiskManagersempidmodel"
                                            		ng-change="getUserDetailsForUpdate(updatemasterverticalform.updateRiskManagersempidmodel);" ng-model-options="{updateOn: 'blur'}" 
                                            		 ng-class="{true: 'ng-border'}[(onUpdate && updateMasterVerticalForm.txtUpdateRiskManagersEmpID.$invalid)]" required>
                                             <div class="error-messages" ng-if= "onUpdate" ng-messages="updateMasterVerticalForm.txtUpdateRiskManagersEmpID.$error">
									        	<em class="error help-block has-error" ng-message="required">Please Enter RiskManagers Employee Head ID</em>
									        </div>
                                        </div>
                                     	<label class="control-label col-sm-2 textAlignRight required-Field">Name</label>
                                        <div class="col-sm-2">
                                            <input name="txtUpdateRiskManagersName" type="text" class="form-control" id="txtUpdateRiskManagersName"
                                                   ng-model="updatemasterverticalform.updateRiskManagersnamemodel"  readonly>
                                        </div>
                                      	<label class="control-label col-sm-2 textAlignRight required-Field">Email Id</label>
                                        <div class="col-sm-2">
                                            <input name="txtUpdateRiskManagersEmailId" type="text" class="form-control" id="txtUpdateRiskManagersEmailId"
                                            		ng-model="updatemasterverticalform.updateRiskManagersemailidmodel"  readonly>
                                        </div>  
                                    </div>
                                    <div class="row marginBottom5px">
										<label class="control-label col-sm-2 textAlignRight required-Field">Is Active</label>
										<div class="col-sm-2">
											<input type="checkbox" name="cbxUpdateIsActive" id="cbxUpdateIsActive" class="margingRightChkBx" 
											ng-model="updatemasterverticalform.cbxUpdateIsActive">
										 </div>
										 <label class="control-label col-sm-2 textAlignRight required-Field">Vertical Status </label>
										<div class="col-sm-2">
											<input type="checkbox" name="cbxUpdateVerticalStatus" id="cbxUpdateVerticalStatus" class="margingRightChkBx"
											ng-model="updatemasterverticalform.cbxUpdateVerticalStatus">
										 </div>
									</div>
									<div class="divEmptyThrice"></div>
                                    <div class="row text-center">
										<div class="col-sm-12">		
											<button type="button" class="btn btn-primary btnSpace" id="btnUpdateUpdate" ng-click="onUpdateClick(updateMasterVerticalForm);">Update</button>						
											<button type="button" class="btn btn-danger" id="btnUpdateCancel" ng-click="cancelClickOnUpdate(updateMasterVerticalForm);">Cancel</button>
										</div>
									</div>
                                   	<div class="divEmptyThrice"></div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
				<!--  <div class="row">
				   <div class="col-sm-12">
				  		<div class="panel-group">
				            <div class="panel panel-info ">
	                            <div class="panel-heading panelHeadingStyle">
	                                <div class="row ">
										<label class="control-label col-sm-10" ng-click="ShowHideUpload()">Upload Vertical</label>
										<label class="DownArrowColor col-sm-2  textAlignRight"> &#9660;</label>
									</div>
	                            </div>
	                            <div class="panel-body" ng-hide = "UploadHidden">
		                       		<div class="row">
		                             	<div class="col-sm-2 "></div>
			                            <label class="control-label col-sm-2 textAlignRight required-Field">Upload File name</label>
			                            <div class="col-sm-3 ">
			                            	<input type="file" class="form-control" name="fuUploadFilename"
												id="fuUploadFilename" ng-model="fuUploadFilenameModel" required>
										</div>
										<div class="col-sm-1">
											<button type="button" class="btn btn-primary btnSpace" id="btnUpload">Upload</button>						
										</div>
									</div>
									<div class="divEmptyThrice" ></div>
                            	</div>
                        	</div>  
						</div>
			       	</div>
				</div> -->
	          </form>
        </div>
    </div>
    </fieldset>
	<div id="footer" ng-include="'${contextPath}/Portal/Footer.jsp'"></div>
	<div id="Footer" ng-include="'${contextPath}/Portal/TopBottomNavigation.jsp'"></div>
	<div id="loader" ng-include="'${contextPath}/Portal/loader.jsp'"></div>
</body>
</html>
