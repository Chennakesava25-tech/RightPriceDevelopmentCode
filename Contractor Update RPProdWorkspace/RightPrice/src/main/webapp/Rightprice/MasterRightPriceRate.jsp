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
    <script src="${contextPath}/resources/js/RightPrice/masterRightPriceRate.js"></script>
 	<script src="${contextPath}/resources/js/Directives/angularCustomValidations.js"></script>
 	<script src="${contextPath}/resources/js/Factory/WebServiceFactory.js"></script>
</head>
<body ng-app="RightPriceApp" ng-controller="MasterRightPriceRateController" >
	<div id="includedContent" ng-include="'${contextPath}/Portal/Header.jsp'"></div>
	<fieldset ng-disabled="loading || showLoader">
	    <div class="container">
	        <div class="divEmpty"></div>
	        <div class="row marginBottom5px">
	            <div class="col-sm-12">
	                <h3 class="text-left" id="PageHeading">Master - Right Price Rate</h3>
	            </div>
	        </div>	
	        <div>
	            <form class="form-inline" role="form" name="Master" id="Master">
	            <div class="row">
						<div class="col-sm-12">
							<div class="panel-group">
								<div class="panel panel-info">
									<ng-form name="viewRateForm" id="viewRateForm" >
									<div class="panel-heading panelHeadingStyle" ng-click="ShowHideView()">
										<div class="row ">
											<label class="control-label col-sm-10 ">View Master Rate</label>
											<label class="DownArrowColor col-sm-2  textAlignRight"> &#9660;</label>
										</div>
									</div>
									<div class="panel-body" ng-hide = "ViewHidden">
										<div class="row marginBottom5px">
	                                        <label class="control-label col-sm-2 textAlignRight required-Field">Country</label>
	                                        <div class="col-sm-3">
	                                          <select id="ddlSearchCountry" class="form-control"
													ng-model="viewRateForm.countryModel"
													placeholder="Please select" name="ddlSearchCountry"
													ng-options="cou.countryId as cou.countryName for cou in country| orderBy:'countryName'"
													ng-class="{true: 'ng-border'} [(onViewSearch && viewRateForm.ddlSearchCountry.$invalid)]"
													ng-change="getCurrencyCode(viewRateForm.countryModel);" required>
													<option value="" selected disabled>Please select</option>
												</select>
												<div class="error-messages" ng-if= "onViewSearch" ng-messages="viewRateForm.ddlSearchCountry.$error">
										        	<em class="error help-block has-error" ng-message="required">Please select Country.</em>
										        </div>
	                                        </div>
	                                        <label class="control-label col-sm-3 textAlignRight required-Field">Currency</label>
	                                        <div class="col-sm-3">
												<input name="txtCurrency" type="text" class="form-control"  id="txtCurrency" 
												 ng-model="viewRateForm.currency" disabled/>
	                                        </div>
										</div>
										<div class="row marginBottom5px">	                                        
	                                        <label class="control-label col-sm-2 textAlignRight required-Field">Year</label>
	                                        <div class="col-sm-3">
												<select id="ddlSearchYear" class="form-control" 
	                                                placeholder="Please select" name="ddlSearchYear"
	                                           		ng-model="viewRateForm.ddlSearchYearModel " 
	                                           		ng-class="{true: 'ng-border'} [(onViewSearch && viewRateForm.ddlSearchYear.$invalid)]"
	                                           		required>
													<option value="" selected disabled>Please select</option>
													<option ng-repeat="year in years">{{year}}</option>
												</select>
												<div class="error-messages" ng-if= "onViewSearch" ng-messages="viewRateForm.ddlSearchYear.$error">
										        	<em class="error help-block has-error" ng-message="required">Please select Year.</em>
										        </div>
	                                        </div>
										</div>
	                                    <div class="divEmptyThrice"></div>
	                                    <div class="row text-center">
											 <div class="col-sm-12">
												<button type="button" class="btn btn-primary btnSpace" id="btnViewSearch" ng-click="onViewMasterRate(viewRateForm.$valid)">Search</button>
												<button type="button" class="btn btn-primary btnSpace" id="btnDownloadViewMasterRate" ng-click ="exportToExcel('#tableToExport');" ng-disabled="isDownload"> Download Master Rate</button>	
											</div> 
										</div>
										<div class="divEmptyThrice"></div>
	                                    <div class="row">
		                                    <div class="col-sm-12">
												<div class="table-responsive  " >
													<table 
														class="table clsTable table-striped table-bordered table-hover table-condensed "
														id="tblViewBillRate">
														<thead>
															<tr>
																<th>Role Code</th>
																<th>Master Roles</th>
																<th>Low</th>
																<th>Medium</th>
																<th>High</th>
																<th>VHigh</th>
																<th>Offshore</th>
															</tr>
														</thead>
														<tbody id="tBodyViewBillRate" >
															<tr id="{{'trRateView'+'_'+($index+1)}}" ng-repeat="rateView in rateViews">
																<td class="tdTextAlignLeft">{{(rateView.masterRole.masterRoleName)}}</td>
																<td class="tdTextAlignLeft">{{(rateView.masterRole.masterRoleLongDescription)}}</td>
																<td class="tdTextAlignRight">{{rateView.rateLow}}</td>
																<td class="tdTextAlignRight">{{(rateView.rateMedium)}}</td>
																<td class="tdTextAlignRight">{{(rateView.rateHigh)}}</td>
																<td class="tdTextAlignRight">{{(rateView.rateVHigh)}}</td>
																<td class="tdTextAlignRight">{{(rateView.rateOffshore)}}</td>
															</tr>
														</tbody>
													</table>
												</div>
												
							<!-------------------------------------------- tabl export begin ------------------------------------------>					
												<!-- id="tableToExport" style="display:none" -->
												<div class="table-responsive"id="tableToExport"  style="display:none">
										
										<table border="1" 
										class="table clsTable table-striped table-bordered table-hover table-condensed "
										id="tblRateCardDetails">
										<tr>
											<td align="Center" bgcolor="#CCFFFF" colspan=7><Strong>Master - Right Price Rate</Strong></td>
										</tr>
										</table>
										
										<table></table>
										
										<table border = 1
											class="table clsTable table-striped table-bordered table-hover table-condensed "
											id="tblViewBillRate">
											<thead>
												<tr>
													<th bgcolor="#CCFFFF">Country :</th>
													<th bgcolor="#CCFFFF">Currency :</th>
													<th bgcolor="#CCFFFF">Year :</th>
												</tr>
											</thead>
											<tbody id="tBodyViewBillRate" >
												<tr id="{{'trRateView'+'_'+($index+1)}}">
													<td class="tdTextAlignRight">{{countryNameModelete}}</td>
													<td class="tdTextAlignLeft">{{viewRateForm.currency}}</td>
													<td class="tdTextAlignLeft"> {{viewRateForm.ddlSearchYearModel}}</td>
												</tr>
											</tbody>
										</table>
										<table></table>
													
					<!-------------------------------------------- tabl export  ------------------------------------------>
													<table border = 1
														class="table clsTable table-striped table-bordered table-hover table-condensed "
														id="tblViewBillRate">
														<thead>
															<tr>
																<th bgcolor="#CCFFFF">Role Code</th>
																<th bgcolor="#CCFFFF">Master Roles</th>
																<th bgcolor="#CCFFFF">Low</th>
																<th bgcolor="#CCFFFF">Medium</th>
																<th bgcolor="#CCFFFF">High</th>
																<th bgcolor="#CCFFFF">VHigh</th>
																<th bgcolor="#CCFFFF">Offshore</th>
															</tr>
														</thead>
														<tbody id="tBodyViewBillRate" >
														
															<tr id="{{'trRateView'+'_'+($index+1)}}" ng-repeat="rateView in rateViews">
																<td class="tdTextAlignLeft">{{(rateView.masterRole.masterRoleName)}}</td>
																<td class="tdTextAlignLeft">{{(rateView.masterRole.masterRoleLongDescription)}}</td>
																<td class="tdTextAlignRight">{{rateView.rateLow}}</td>
																<td class="tdTextAlignRight">{{(rateView.rateMedium)}}</td>
																<td class="tdTextAlignRight">{{(rateView.rateHigh)}}</td>
																<td class="tdTextAlignRight">{{(rateView.rateVHigh)}}</td>
																<td class="tdTextAlignRight">{{(rateView.rateOffshore)}}</td>
															</tr>
														</tbody>
													</table>
												</div>
												
											</div>
										</div>
									</div>
									</ng-form>
								</div>
							</div>
						</div>
					</div>
	                <div class="row">
	                    <div class="col-sm-12">
	                        <div class="panel-group">
	                            <div class="panel panel-info ">
	                             <ng-form name="addRateForm" id="addRateForm" >
	                                <div class="panel-heading panelHeadingStyle"  ng-click="ShowHideAdd()">
	                                    <div class="row ">
											<label class="control-label col-sm-10 ">Add Master Rate</label>
											<label class="DownArrowColor col-sm-2  textAlignRight"> &#9660;</label>
										</div>
	                                </div>
	                                <div class="panel-body" ng-hide = "AddHidden">
	                                    <div class="row marginBottom5px">
	                                        <label class="control-label col-sm-2 textAlignRight required-Field">Country</label>
	                                        <div class="col-sm-3">
	                                           <select id="ddlAddCountry" class="form-control" placeholder="Please select" 
	                                           		ng-options="cou.countryId as cou.countryName for cou in country| orderBy:'countryName'" name="ddlAddCountry" 
	                                           		ng-class="{true: 'ng-border'} [(onSaveSearch && addRateForm.ddlAddCountry.$invalid)]"
	                                           		ng-model="addRateForm.ddlAddCountryModel" required
	                                           		ng-change="clearSearchArray()">
												<option value="" selected disabled>Please select</option>
												</select>
												<div class="error-messages" ng-if= "onSaveSearch" ng-messages="addRateForm.ddlAddCountry.$error">
										        	<em class="error help-block has-error" ng-message="required">Please select Country.</em>
										        </div>
	                                        </div>
	                                        <label class="control-label col-sm-3 textAlignRight required-Field">Year</label>
	                                        <div class="col-sm-3">
	                                           <select id="ddlSearchYear" class="form-control" 
	                                                placeholder="Please select" name="ddlSearchYear"
	                                           		ng-model="addRateForm.ddlSearchYearModel" 
	                                           		ng-class="{true: 'ng-border'} [(onSaveSearch && addRateForm.ddlSearchYear.$invalid)]"
	                                           		required ng-change="clearSearchArray()">
													<option value="" selected disabled>Please select</option>
													<option ng-repeat="year in years">{{year}}</option>
												</select>
												<div class="error-messages" ng-if= "onSaveSearch" ng-messages="addRateForm.ddlSearchYear.$error">
										        	<em class="error help-block has-error" ng-message="required">Please select Year.</em>
										        </div>
	                                        </div> 
										</div>
										<div class="row marginBottom5px">
	                                        <label class="control-label col-sm-2 textAlignRight required-Field">Role Code</label>
	                                        <div class="col-sm-3">
	                                           <select id="ddlAddRoleCode" class="form-control" placeholder="Please select"  name="ddlAddRoleCode"
	                                           		ng-options="mas.masterRoleId as mas.masterRoleName for mas in masterRole| orderBy:'masterRoleName'" 
	                                           		ng-model="addRateForm.ddlAddRoleCodeModel"  required 
	                                           		ng-change="setMasterRole(addRateForm.ddlAddRoleCodeModel)"
	                                           		ng-class="{true: 'ng-border'} [(onSaveSearch && addRateForm.ddlAddRoleCode.$invalid)]">
												<option value="" selected disabled>Please select</option>
												</select> 
												<div class="error-messages" ng-if="onSaveSearch" ng-messages="addRateForm.ddlAddRoleCode.$error">
										        	<em class="error help-block has-error" ng-message="required">Please select Role Code.</em>
										        </div>
	                                        </div>
	                                        <label class="control-label col-sm-3 textAlignRight required-Field">Master Role</label>
	                                        <div class="col-sm-3">
	                                          <select id="ddlMasterRolee" class="form-control" 
	                                                placeholder="Please select" name="ddlMasterRolee"
	                                           		ng-model="addRateForm.ddlMasterRole" 
	                                           		ng-options="mas.masterRoleId as mas.masterRoleLongDescription for mas in masterRole| orderBy:'masterRoleLongDescription'"
	                                           		ng-class="{true: 'ng-border'} [(onSaveSearch && addRateForm.ddlMasterRolee.$invalid)]"
	                                           		ng-change="setMasterRoleCode(addRateForm.ddlMasterRole);">
													<option value="" selected disabled>Please select</option>
												</select>
												<div class="error-messages" ng-if="onSaveSearch" ng-messages="addRateForm.ddlMasterRolee.$error">
										        	<em class="error help-block has-error" ng-message="required">Please select Role.</em>
										        </div>
	                                        </div> 
										</div>										
										<div class="divEmptyThrice"></div>
										<div class="row text-center">
											<div class="col-sm-12">
												<button type="button" class="btn btn-primary btnSpace" id="btnSearchSearch" ng-click="onMasterRateSearch(addRateForm.$valid)">Search</button>
											</div>
										</div>
										<div class="divEmptyThrice"></div>
	                                    <div class="row marginBottom5px">
		                                    <div class="col-sm-12">
												<div class="table-responsive  " >
													<table 
														class="table clsTable table-striped table-bordered table-hover table-condensed "
														id="tblAddMasterRate">
														 <thead>
															<tr>
																<th>Low</th>
																<th>Medium</th>
																<th>High</th>
																<th>VHigh</th>
																<th>Offshore</th>
															</tr>
														</thead> 
														<tbody id="tBodyViewBillRate" >
															<tr id="{{'traddRate'+'_'+($index+1)}}" ng-repeat="masterRate in masterRateArray">
																<td>
																	<input name="{{'traddLowRate'+'_'+($index+1)}}" ng-model="masterRate.lowRate" type="text" class="form-control" value="{{$index+1}}" 
																	ng-pattern="/^\d{1,7}(?:\.\d{0,2})?$/i" required ng-class="{true: 'ng-border'}[onAddSave && addRateForm.{{'traddLowRate'+'_'+($index+1)}}.$invalid]">
																	<div class="error-messages" ng-if="onAddSave" ng-messages="addRateForm['traddLowRate'+'_'+($index+1)].$error">
																		<em class="error help-block has-error" ng-message="required">Please enter Low Rate.</em>
																		<em class="error help-block has-error" ng-message="pattern">Please enter numeric value max up to 7 digit and 2 decimal.</em>
											        				</div>
																</td>
																<td>
																	<input name="{{'traddMediumRate'+'_'+($index+1)}}" ng-model="masterRate.mediumRate" type="text" class="form-control" 
																	ng-pattern="/^\d{1,7}(?:\.\d{0,2})?$/i" required ng-class="{true: 'ng-border'}[onAddSave && addRateForm.{{'traddMediumRate'+'_'+($index+1)}}.$invalid]">
																	<div class="error-messages" ng-if="onAddSave" ng-messages="addRateForm['traddMediumRate'+'_'+($index+1)].$error">
																		<em class="error help-block has-error" ng-message="required">Please enter Medium Rate.</em>
																		<em class="error help-block has-error" ng-message="pattern">Please enter numeric value max up to 7 digit and 2 decimal.</em>
											        				</div>
																</td>
																<td>
																	<input name="{{'txtaddHighRate'+'_'+($index+1)}}" ng-model="masterRate.highRate" type="text" class="form-control" 
																	ng-pattern="/^\d{1,7}(?:\.\d{0,2})?$/i" required ng-class="{true: 'ng-border'}[onAddSave && addRateForm.{{'txtaddHighRate'+'_'+($index+1)}}.$invalid]">
																	<div class="error-messages" ng-if="onAddSave" ng-messages="addRateForm['txtaddHighRate'+'_'+($index+1)].$error">
																		<em class="error help-block has-error" ng-message="required">Please enter High Rate.</em>
																		<em class="error help-block has-error" ng-message="pattern">Please enter numeric value max up to 7 digit and 2 decimal.</em>
											        				</div>
																</td>
																<td>
																	<input name="{{'txtaddVHighRate'+'_'+($index+1)}}" ng-model="masterRate.vhighRate" type="text" class="form-control" required 
																	ng-pattern="/^\d{1,7}(?:\.\d{0,2})?$/i" ng-class="{true: 'ng-border'}[onAddSave && addRateForm.{{'txtaddVHighRate'+'_'+($index+1)}}.$invalid]">
																	<div class="error-messages" ng-if="onAddSave" ng-messages="addRateForm['txtaddVHighRate'+'_'+($index+1)].$error">
																		<em class="error help-block has-error" ng-message="required">Please enter Very High Rate.</em>
																		<em class="error help-block has-error" ng-message="pattern">Please enter numeric value max up to 7 digit and 2 decimal.</em>
											        				</div>
																</td>
																<td>
																	<input name="{{'txtaddOffShoreRate'+'_'+($index+1)}}" ng-model="masterRate.offShoreRate" type="text" class="form-control" required 
																	ng-pattern="/^\d{1,7}(?:\.\d{0,2})?$/i" ng-class="{true: 'ng-border'}[onAddSave && addRateForm.{{'txtaddOffShoreRate'+'_'+($index+1)}}.$invalid]">
																	<div class="error-messages" ng-if="onAddSave" ng-messages="addRateForm['txtaddOffShoreRate'+'_'+($index+1)].$error">
																		<em class="error help-block has-error" ng-message="required">Please enter Offshore Rate.</em>
																		<em class="error help-block has-error" ng-message="pattern">Please enter numeric value max up to 7 digit and 2 decimal.</em>
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
												<button type="button" class="btn btn-primary btnSpace" id="btnAddSave" ng-click="onAddMasterRate(addRateForm.$valid)" ng-disabled="!masterRateArray.length">Save</button>						
												<button type="button" class="btn btn-danger" id="btnAddCancel" ng-click="onCancelClick()">Cancel</button>
											</div>
										</div>
	                                </div>
	                                </ng-form>
	                            </div>
	                        </div>
	                    </div>
	                </div>
	                 <div class="row">
	                    <div class="col-sm-12">
	                        <div class="panel-group">
	                            <div class="panel panel-info ">
	                            <ng-form name="updateRateForm" id="updateRateForm" >
	                                <div class="panel-heading panelHeadingStyle" ng-click="ShowHideUpdate()">
	                                    <div class="row ">
											<label class="control-label col-sm-10 ">Update Master Rate</label>
											<label class="DownArrowColor col-sm-2  textAlignRight"> &#9660;</label>
										</div>
	                                </div>
	                                <div class="panel-body" ng-hide = "UpdateHidden">
	                               		<div class="row marginBottom5px">
	                                   		<label class="control-label col-sm-2 textAlignRight required-Field">Country</label>
	                                        <div class="col-sm-3">
                                           		<select id="ddlUpdateCountry" class="form-control" placeholder="Please select" name="ddlUpdateCountry"
	                                           		ng-options="cou.countryId as cou.countryName for cou in country| orderBy:'countryName'"
	                                           		ng-class="{true: 'ng-border'} [(onUpdateSearch && updateRateForm.ddlUpdateCountry.$invalid)]"
                                       			 	ng-model="updateRateForm.ddlUpdateCountryModel" required
                                       			 	ng-change="getMasterRateRoles(updateRateForm.ddlUpdateCountryModel)"> 
													<option value="" selected disabled>Please select</option>
												</select>
												<div class="error-messages" ng-if="onUpdateSearch" ng-messages="updateRateForm.ddlUpdateCountry.$error">
										        	<em class="error help-block has-error" ng-message="required">Please select Country.</em>
										        </div>
	                                        </div>
	                                         <label class="control-label col-sm-3 textAlignRight required-Field">Year</label>
	                                        <div class="col-sm-3">
	                                           <select id="ddlSearchYear" class="form-control" 
	                                                placeholder="Please select" name="ddlSearchYear"
	                                           		ng-model="updateRateForm.ddlSearchYearModel" 
	                                           		ng-class="{true: 'ng-border'} [(onUpdateSearch && updateRateForm.ddlSearchYear.$invalid)]"
	                                           		required>
													<option value="" selected disabled>Please select</option>
													<option ng-repeat="year in years">{{year}}</option>
												</select>
												<div class="error-messages" ng-if="onUpdateSearch" ng-messages="updateRateForm.ddlSearchYear.$error">
										        	<em class="error help-block has-error" ng-message="required">Please select Year.</em>
										        </div>
	                                        </div>
	                                    </div>
	                                    <div class="row marginBottom5px">
	                                   		<label class="control-label col-sm-2 textAlignRight required-Field">Role Code</label>
	                                        <div class="col-sm-3">
                                           		<select id="ddlUpdateSearchRoleCode" class="form-control" placeholder="Please select" name="ddlUpdateSearchRoleCode"
                                       			 	ng-model="updateRateForm.ddlUpdateSearchRoleCodeModel" required
	                                           		ng-options="mas.masterRoleId as mas.masterRole.masterRoleName for mas in masterRateRoles| orderBy:'masterRole.masterRoleName'" 
	                                           		ng-class="{true: 'ng-border'} [(onUpdateSearch && updateRateForm.ddlUpdateSearchRoleCode.$invalid)]"
                                       			 	ng-change="setUpdateMasterRole(updateRateForm.ddlUpdateSearchRoleCodeModel)"> 
													<option value="" selected disabled>Please select</option>
												</select>
												<div class="error-messages" ng-if="onUpdateSearch" ng-messages="updateRateForm.ddlUpdateSearchRoleCode.$error">
										        	<em class="error help-block has-error" ng-message="required">Please select Role Code.</em>
										        </div>
	                                        </div>
	                                        <label class="control-label col-sm-3 textAlignRight required-Field">Master Role</label>
	                                        <div class="col-sm-3">
	                                          <select id="ddlSearchRole" class="form-control" 
	                                                placeholder="Please select" name="ddlSearchRole"
	                                           		ng-model="updateRateForm.ddlRateModel" 
	                                           		ng-options="mas.masterRoleId as mas.masterRole.masterRoleLongDescription for mas in masterRateRoles| orderBy:'masterRole.masterRoleLongDescription'"
	                                           		ng-class="{true: 'ng-border'} [(onUpdateSearch && updateRateForm.ddlSearchRole.$invalid)]"
	                                           		ng-change="setUpdateMasterRoleCode(updateRateForm.ddlRateModel);" >
													<option value="" selected disabled>Please select</option>
												</select>
												<div class="error-messages" ng-if="onUpdateSearch" ng-messages="updateRateForm.ddlSearchRole.$error">
										        	<em class="error help-block has-error" ng-message="required">Please select Role.</em>
										        </div>
	                                        </div> 
	                                    </div>
	                                    <div class="row marginBottom5px">
	                                       
										</div>
										<div class="divEmptyThrice"></div>
										<div class="row text-center">
											<div class="col-sm-12">
												<button type="button" class="btn btn-primary btnSpace" id="btnSearchSearch" ng-click="onUpdateMasterRateSearch(updateRateForm.$valid)">Search</button>
											</div>
										</div>
	                                    <div class="divEmptyThrice"></div>
	                                   	<div class="row">
		                                    <div class="col-sm-12">
												<div class="table-responsive  " >
													<table 
														class="table clsTable table-striped table-bordered table-hover table-condensed "
														id="tblUpdateMaster">
														<thead>
															<tr>
																<th>Low</th>
																<th>Medium</th>
																<th>High</th>
																<th>VHigh</th>
																<th>Offshore</th>
															</tr>
														</thead> 
														<tbody id="tBodyViewBillRate" ng-if="isSelected">
															<tr>
																<td>
																	<input name="txtUpdateB1" type="text" class="form-control" id="txtUpdateDomestic"
																    	ng-pattern="/^\d{1,7}(?:\.\d{0,2})?$/i" ng-model="updateRateForm.updateLowRate" required 
																    	ng-class="{true: 'ng-border'} [(onUpdateClick && updateParameterForm.txtUpdateB1.$invalid)]">
															       <div class="error-messages" ng-if= "onUpdateClick" ng-messages="updateRateForm.txtUpdateB1.$error">
								        								<em class="error help-block has-error" ng-message="required">Please enter Low rate.</em>
								        								<em class="error help-block has-error" ng-message="pattern">Please enter numeric value max up to 7 digit and 2 decimal.</em>
								        							</div>
																</td>
																<td>
																	<input name="txtUpdateH1" type="text" class="form-control" id="txtUpdateH1"
																		ng-pattern="/^\d{1,7}(?:\.\d{0,2})?$/i" ng-model="updateRateForm.updateMediumRate" required 
																		ng-class="{true: 'ng-border'} [(onUpdateClick && updateRateForm.txtUpdateH1.$invalid)]">
																	<div class="error-messages" ng-if= "onUpdateClick" ng-messages="updateRateForm.txtUpdateH1.$error">
								        								<em class="error help-block has-error" ng-message="required">Please enter Medium rate.</em>
								        								<em class="error help-block has-error" ng-message="pattern">Please enter numeric value max up to 7 digit and 2 decimal.</em>
								        							</div>
																</td>
																<td>
																	<input name="txtUpdateL1" type="text" class="form-control" id="txtUpdateL1"
																		ng-pattern="/^\d{1,7}(?:\.\d{0,2})?$/i" ng-model="updateRateForm.updateHighRate" required 
																		ng-class="{true: 'ng-border'} [(onUpdateClick && updateRateForm.txtUpdateL1.$invalid)]">
																	<div class="error-messages" ng-if= "onUpdateClick" ng-messages="updateRateForm.txtUpdateL1.$error">
								        								<em class="error help-block has-error" ng-message="required">Please enter High rate.</em>
								        								<em class="error help-block has-error" ng-message="pattern">Please enter numeric value max up to 7 digit and 2 decimal.</em>
								        							</div>		   
																</td>
																<td>
																	<input name="txtUpdateVhigh" type="text" class="form-control" id="txtUpdateVhigh"
																		ng-pattern="/^\d{1,7}(?:\.\d{0,2})?$/i" ng-model="updateRateForm.updateVHighRate" required 
																		ng-class="{true: 'ng-border'} [(onUpdateClick && updateRateForm.txtUpdateVhigh.$invalid)]">
																	<div class="error-messages" ng-if= "onUpdateClick" ng-messages="updateRateForm.txtUpdateVhigh.$error">
								        								<em class="error help-block has-error" ng-message="required">Please enter Very High rate.</em>
								        								<em class="error help-block has-error" ng-message="pattern">Please enter numeric value max up to 7 digit and 2 decimal.</em>
								        							</div>
																</td>
																<td>
																	<input name="txtUpdateOthers" type="text" class="form-control" id="txtUpdateOthers"
																		ng-pattern="/^\d{1,7}(?:\.\d{0,2})?$/i" ng-model="updateRateForm.updateOffShore" required 
																		ng-class="{true: 'ng-border'} [(onUpdateClick && updateRateForm.txtUpdateOthers.$invalid)]">
																	<div class="error-messages" ng-if= "onUpdateClick" ng-messages="updateRateForm.txtUpdateOthers.$error">
								        								<em class="error help-block has-error" ng-message="required">Please enter Offshore rate.</em>
								        								<em class="error help-block has-error" ng-message="pattern">Please enter numeric value max up to 7 digit and 2 decimal.</em>
								        							</div>
																</td>
															</tr>
															</tbody>
													</table>
												</div>
											</div>
										</div>
										<div class="divEmptyThrice"></div>
										<div class="divEmptyThrice"></div>
	                                    <div class="row text-center">
											<div class="col-sm-12">		
												<button type="button" class="btn btn-primary btnSpace" id="btnUpdateUpdate" ng-click="onUpdateMasterRate(updateRateForm.$valid)" ng-disabled="isUpdate">Update</button>						
												<button type="button" class="btn btn-danger" id="btnUpdateCancel" ng-click="onCancelClick()">Cancel</button>
											</div>
										</div>
	                                </div>
	                                </ng-form>
	                            </div>
	                        </div>
	                    </div>
	                </div>
					 <div class="row">
					   <div class="col-sm-12">
					  		<div class="panel-group">
					            <div class="panel panel-info ">
					            	<ng-form name="uploadRPRate" id="uploadRPRate">
			                            <div class="panel-heading panelHeadingStyle" ng-click="ShowHideUpload()">
			                                <div class="row ">
												<label class="control-label col-sm-10 ">Upload Master Rate</label>
												<label class="DownArrowColor col-sm-2  textAlignRight"> &#9660;</label>
											</div>
			                            </div>
			                            <div class="panel-body" ng-hide = "UploadHidden">
											<div class="row">
				                             	<div class="col-sm-2 "></div>
					                            <label class="control-label col-sm-2 textAlignRight required-Field">Upload File name</label>
					                            <div class="col-sm-3 ">
					                            	<input type="file" class="form-control" name="fuUploadFilename"
														id="fuUploadFilename" ng-model="uploadRPRate.fuUploadFilenameModel" required
														check-file-size="uploadRPRate.fuUploadFilenameModel" 
														valid-File-Excel ng-class="{true: 'ng-border'} [(upload && uploadRPRate.fuUploadFilename.$invalid)]">
												
													<div class="error-messages" ng-if="upload" ng-messages="uploadRPRate.fuUploadFilename.$error">
												     	<em class="error help-block has-error" ng-message="checkfilesize">File size is not valid for uploading!</em>
												    	 <em class="error help-block has-error" ng-message="extension">Incorrect file format</em> 
												    	 <em class="error help-block has-error" ng-message="required">Please select Right Price Rate details file to upload.</em>
													</div>
												</div>
												<div class="col-sm-1">
													<button type="button" class="btn btn-primary btnSpace" id="btnUpload" ng-click="uploadData(uploadRPRate);">Upload</button>						
												</div>
												<div class="divDownloadImg col-sm-4">
													<a href="${contextPath}/resources/Documents/Right Price Rate_Template.xls" target="_blank" class="anchorTrancColor" download="Right Price Rate_Template.xls"><span>Download Template</span></a>
										 		</div>												
											</div>
											<div  class="row">
												<div class="col-sm-4 col-sm-offset-8">
													<span class="inputFontBold">Note: </span>  
													<span>Enter data in the highlighted fields of the template and then Upload.</span>
												</div>
											</div>
		                            	</div>
	                            	</ng-form>
	                        	</div>  
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
