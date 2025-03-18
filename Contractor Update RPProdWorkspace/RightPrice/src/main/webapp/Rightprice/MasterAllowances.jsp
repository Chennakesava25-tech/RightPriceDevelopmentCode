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
 	<script src="${contextPath}/resources/js/RightPrice/mastersAllownces.js"></script>
 	<script src="${contextPath}/resources/js/Directives/angularCustomValidations.js"></script>
 	<script src="${contextPath}/resources/js/Factory/WebServiceFactory.js"></script>
</head>
<body ng-app="RightPriceApp" ng-controller="MasterAllowancesController" >
    <div id="includedContent" ng-include="'${contextPath}/Portal/Header.jsp'"></div>
    <fieldset ng-disabled="loading || showLoader">
    <div class="container">
        <div class="divEmpty"></div>
        <div class="row marginBottom5px">
            <div class="col-sm-12">
                <h3 class="text-left" id="PageHeading">Master - Allowances</h3>
            </div>
        </div>	
        <div>
            <form class="form-inline" role="form" name="Master" id="Master">
            	<div class="row">
					<div class="col-sm-12">
						<div class="panel-group">
							<div class="panel panel-info">
							<ng-form name="viewAllowancesForm" id="viewAllowancesForm" >
								<div class="panel-heading panelHeadingStyle" ng-click="ShowHideSearch()">
									<div class="row ">
										<label class="control-label col-sm-10 ">Search  Allowances</label>
										<label class="DownArrowColor col-sm-2  textAlignRight"> &#9660;</label>
									</div>
								</div>
								<div class="panel-body" ng-hide = "SearchHidden">
									<div class="row marginBottom5px">
                                        <label class="control-label col-sm-2 textAlignRight required-Field">Country</label>
                                        <div class="col-sm-3">
                                          <select id="ddlSearchCountry" class="form-control"
												ng-model="viewAllowancesForm.countryModel"
												placeholder="Please select" name="ddlSearchCountry"
												ng-options="cou.countryId as cou.countryName for cou in country| orderBy:'countryName'"
												ng-class="{true: 'ng-border'} [(onViewSearch && viewAllowancesForm.ddlSearchCountry.$invalid)]"
												required>
												<option value="" selected disabled>Please select</option>
											</select>
											<div class="error-messages" ng-if= "onViewSearch" ng-messages="viewAllowancesForm.ddlSearchCountry.$error">
									        	<em class="error help-block has-error" ng-message="required">Please select Country.</em>
									        </div>
                                        </div>
                                        <label class="control-label col-sm-3 textAlignRight required-Field">Visa</label>
                                        <div class="col-sm-3">
											<select id="ddlSearchVisa" class="form-control"
												ng-model="viewAllowancesForm.ddlSearchVisaModel"
												placeholder="Please select" name="ddlSearchVisa"
												ng-options="vi.id as vi.description for vi in visa"
												ng-class="{true: 'ng-border'} [(onViewSearch && viewAllowancesForm.ddlSearchVisa.$invalid)]"
												required>
												<option value="" selected disabled>Please select</option>
											</select>
											<div class="error-messages" ng-if= "onViewSearch" ng-messages="viewAllowancesForm.ddlSearchVisa.$error">
									        	<em class="error help-block has-error" ng-message="required">Please select Visa.</em>
									        </div>
                                        </div>
									</div>
									<div class="row marginBottom5px">
                                        <label class="control-label col-sm-2 textAlignRight required-Field">Year</label>
                                        <div class="col-sm-3">
                                           <select id="ddlSearchYear" class="form-control" 
                                                placeholder="Please select" name="ddlSearchYear"
                                           		ng-model="viewAllowancesForm.ddlSearchYearModel" 
                                           		ng-class="{true: 'ng-border'} [(onViewSearch && viewAllowancesForm.ddlSearchYear.$invalid)]"
                                           		required>
												<option value="" selected disabled>Please select</option>
												<option ng-repeat="year in years">{{year}}</option>
											</select>
											<div class="error-messages" ng-if= "onViewSearch" ng-messages="viewAllowancesForm.ddlSearchYear.$error">
									        	<em class="error help-block has-error" ng-message="required">Please select Year.</em>
									        </div>
                                        </div>
									</div>
                                    <div class="divEmptyThrice"></div>
                                    <div class="row text-center">
										<div class="col-sm-12">
											<button type="button" class="btn btn-primary btnSpace" id="btnSearchSearch" ng-click="viewAllowances(viewAllowancesForm.$valid)">Search</button>
											<!-- <button type="button" class="btn btn-primary btnSpace" id="btnDownloadSearchAllowances" ng-click ="getMasterAllowancesExcel(viewAllowancesForm);" ng-disabled=downloadBtn >Download Allowances</button> -->
										<button type="button" class="btn btn-primary btnSpace" id="btnDownloadSearchAllowances" ng-click ="exportToExcel('#tableToExport');" ng-disabled=downloadBtn >Download Allowances</button>
										</div>
									</div>
									<div class="divEmptyThrice"></div>
                                    <div class="row">
	                                    <div class="col-sm-12  ">
											<div class="table-responsive" >
												<table 
													class="table clsTable table-striped table-bordered table-hover table-condensed"
													id="tblSearchAllowances" >
													<thead>
														<tr>
															<th>Designation</th>
															<th>Band/Grade</th>
															<th>GCM Level</th>
															<th>Low</th>
															<th>Medium</th>
															<th>High</th>
															<th>Very High</th>
														</tr>
													</thead>
													<tbody id="tBodySearchAllowances" >
														<tr id="{{'trallowancesView'+'_'+($index+1)}}" ng-repeat="allowancesView in allowancesViews">
															<td class="tdTextAlignLeft">{{(allowancesView.empDesgmap.desgDesc)}}</td>
															<td class="tdTextAlignLeft">{{allowancesView.empDesgmap.bandmap.description}}-{{allowancesView.empDesgmap.grademap.description}}</td>
															<td class="tdTextAlignLeft">{{allowancesView.empDesgmap.gcmCODE}}</td>
															<td class="tdTextAlignRight">{{(allowancesView.lowallowance)}}</td>
															<td class="tdTextAlignRight">{{(allowancesView.mediumallowance)}}</td>
															<td class="tdTextAlignRight">{{(allowancesView.highallowance)}}</td>
															<td class="tdTextAlignRight">{{(allowancesView.veryhighallowance)}}</td>
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
				
	<!-- -------------------------------------------------------Export excel -------------------------------------------------------------------------------- -->				
				
									 	<div class="table-responsive" id="tableToExport" style="display:none" >
					 					<table border="1" 
										class="table clsTable table-striped table-bordered table-hover table-condensed "
										id="tblRateCardDetails">
										<tr>
											<td align="Center" bgcolor="#CCFFFF" colspan=7><Strong>Master - Allowances</Strong></td>
										</tr>
										</table>
										<table></table>
									 	
										<table border="1" 
										class="table clsTable table-striped table-bordered table-hover table-condensed "
										id="tblRateCardDetails">
													<thead>
														<tr>
															<th class="tdSyntelRole" bgcolor="#CCFFFF">Designation</th>
															<th class="tdSyntelRole" bgcolor="#CCFFFF">Band/Grade</th>
															<th class="tdSyntelRole" bgcolor="#CCFFFF">GCM Level</th>
															<th class="tdSyntelRole" bgcolor="#CCFFFF">Low</th>
															<th class="tdSyntelRole" bgcolor="#CCFFFF">Medium</th>
															<th class="tdSyntelRole" bgcolor="#CCFFFF">High</th>
															<th class="tdSyntelRole" bgcolor="#CCFFFF">Very High</th>
														</tr>
													</thead>
													<tbody id="tBodySearchAllowances" >
														<tr id="{{'trallowancesView'+'_'+($index+1)}}" ng-repeat="allowancesView in allowancesViews">
															<td class="tdTextAlignLeft">{{(allowancesView.empDesgmap.desgDesc)}}</td>
															<td class="tdTextAlignLeft">{{allowancesView.empDesgmap.bandmap.description}}-{{allowancesView.empDesgmap.grademap.description}}</td>
															<td class="tdTextAlignLeft">{{allowancesView.empDesgmap.gcmCODE}}&#160;</td> 
															<td class="tdTextAlignRight">{{(allowancesView.lowallowance)}}</td>
															<td class="tdTextAlignRight">{{(allowancesView.mediumallowance)}}</td>
															<td class="tdTextAlignRight">{{(allowancesView.highallowance)}}</td>
															<td class="tdTextAlignRight">{{(allowancesView.veryhighallowance)}}</td>
														</tr>
													</tbody>
												</table>
											</div>
				
                <div class="row">
                    <div class="col-sm-12">
                        <div class="panel-group">
                            <div class="panel panel-info ">
                            <ng-form name="addAllowancesForm" id="addAllowancesForm" >
                                <div class="panel-heading panelHeadingStyle" ng-click="ShowHideAdd()">
                                    <div class="row ">
										<label class="control-label col-sm-10 ">Add Annual Allowances</label>
										<label class="DownArrowColor col-sm-2  textAlignRight"> &#9660;</label>
									</div>
                                </div>
                                <div class="panel-body" ng-hide = "AddHidden">
                                   <div class="row marginBottom5px">
                                        <label class="control-label col-sm-2 textAlignRight required-Field">Country</label>
                                        <div class="col-sm-3">
                                          <select id="ddlAddCountry" class="form-control"
												ng-model="addAllowancesForm.countryModel"
												placeholder="Please select" name="ddlAddCountry"
												ng-options="cou.countryId as cou.countryName for cou in country| orderBy:'countryName'" 
												ng-change="clearSearchArray()"												
												ng-class="{true: 'ng-border'} [(onAddSearch && addAllowancesForm.ddlAddCountry.$invalid)]"
												required>
												<option value="" selected disabled>Please select</option>
											</select>
											<div class="error-messages" ng-if= "onAddSearch" ng-messages="addAllowancesForm.ddlAddCountry.$error">
									        	<em class="error help-block has-error" ng-message="required">Please select Country.</em>
									        </div>
                                        </div>
                                        <label class="control-label col-sm-3 textAlignRight required-Field">Visa</label>
                                        <div class="col-sm-3">
											<select id="ddlAddVisa" class="form-control"
												ng-model="addAllowancesForm.ddlSearchVisaModel"
												placeholder="Please select" name="ddlAddVisa"
												ng-options="vi.id as vi.description for vi in visa"
												ng-change="clearSearchArray()"
												required
												ng-class="{true: 'ng-border'} [(onAddSearch && addAllowancesForm.ddlAddVisa.$invalid)]">
												<option value="" selected disabled>Please select</option>
											</select>
											<div class="error-messages" ng-if= "onAddSearch" ng-messages="addAllowancesForm.ddlAddVisa.$error">
									        	<em class="error help-block has-error" ng-message="required">Please select Visa.</em>
									        </div>
                                        </div>
									</div>
									<div class="row marginBottom5px">
                                        <label class="control-label col-sm-2 textAlignRight required-Field">Year</label>
                                        <div class="col-sm-3">
                                           <select id="ddlAddYear" class="form-control" placeholder="Please select" name="ddlAddYear"
                                           		ng-model="addAllowancesForm.ddlAddYearModel" required
                                           		ng-change="clearSearchArray()"
                                           		ng-class="{true: 'ng-border'} [(onAddSearch && addAllowancesForm.ddlAddYear.$invalid)]">
												<option value="" selected disabled>Please select</option>
												<option ng-repeat="year in years">{{year}}</option>
											</select>
                                        
                                        <div class="error-messages" ng-if= "onAddSearch" ng-messages="addAllowancesForm.ddlAddYear.$error">
									        	<em class="error help-block has-error" ng-message="required">Please select Year.</em>
									     </div>
									     </div>
									</div>
									<div class="divEmptyThrice"></div>
                                    <div class="row text-center">
										<div class="col-sm-12">
											<button type="button" class="btn btn-primary btnSpace" id="btnSearchSearch" ng-click="checkAnnualAllownces(addAllowancesForm.$valid)">Search</button>
										</div>
									</div>
									<div class="divEmptyThrice"></div>	
                                    <div class="row">
	                                    <div class="col-sm-12 ">
											<div class="table-responsive" >
												<table 
													class="table clsTable table-striped table-bordered table-hover table-condensed "
													id="tblAddAnnualAllowncesSection">
													<thead>
														<tr>
															<th>Designation</th>
															<th>Band/Grade</th>
															<th>GCM Level</th>
															<th>Low</th>
															<th>Medium</th>
															<th>High</th>
															<th>Very High</th>
														</tr>
													</thead>
													<tbody id="tBodytblAddAnnualAllowncesSection" >
														<tr id="{{'trallowancesAdd'+'_'+($index+1)}}" ng-repeat="allowancesAdd in allowancesAddArray">
															<td class="tdVetAlignMiddle">{{allowancesAdd.desgDesc}}</td>
															<td class="tdVetAlignMiddle">{{allowancesAdd.bandmap.description}}-{{allowancesAdd.grademap.description}}</td>
															<td class="tdVetAlignMiddle">{{allowancesAdd.gcmCODE}}</td>
															<td>
																<input name="{{'txtaddlowallowance'+'_'+($index+1)}}" type="text" class="form-control" ng-model="allowancesAdd.addlowallowance" 
																	ng-pattern="/^\d{1,7}(?:\.\d{0,2})?$/i" required ng-class="{true: 'ng-border'}[onAddSave && addAllowancesForm.{{'txtaddlowallowance'+'_'+($index+1)}}.$invalid]">
																<div class="error-messages" ng-if="onAddSave" ng-messages="addAllowancesForm['txtaddlowallowance'+'_'+($index+1)].$error">
																	<em class="error help-block has-error" ng-message="required">Please enter value for low allowance.</em>
																	<em class="error help-block has-error" ng-message="pattern">Please enter numeric value max up to 7 digit and 2 decimal.</em>
									        					</div>
									        				</td>
															<td><input name="{{'txtaddmediumallowance'+'_'+($index+1)}}" type="text" class="form-control" ng-model="allowancesAdd.addmediumallowance" 
																ng-pattern="/^\d{1,7}(?:\.\d{0,2})?$/i" required ng-class="{true: 'ng-border'}[onAddSave && addAllowancesForm.{{'txtaddmediumallowance'+'_'+($index+1)}}.$invalid]">
																<div class="error-messages" ng-if="onAddSave" ng-messages="addAllowancesForm['txtaddmediumallowance'+'_'+($index+1)].$error">
									        						<em class="error help-block has-error" ng-message="required">Please enter value for medium allowance.</em>
									        						<em class="error help-block has-error" ng-message="pattern">Please enter numeric value max up to 7 digit and 2 decimal.</em>
									        					</div>
															</td>
															<td><input name="{{'txtaddhighallowance'+'_'+($index+1)}}" type="text" class="form-control" ng-model="allowancesAdd.addhighallowance" 
																ng-pattern="/^\d{1,7}(?:\.\d{0,2})?$/i" required ng-class="{true: 'ng-border'}[onAddSave && addAllowancesForm.{{'txtaddhighallowance'+'_'+($index+1)}}.$invalid]">
																<div class="error-messages" ng-if="onAddSave" ng-messages="addAllowancesForm['txtaddhighallowance'+'_'+($index+1)].$error">
									        						<em class="error help-block has-error" ng-message="required">Please enter value for high allowance.</em>
									        						<em class="error help-block has-error" ng-message="pattern">Please enter numeric value max up to 7 digit and 2 decimal.</em>
									        					</div>
															</td>
															<td><input name="{{'txtaddveryhighallowance'+'_'+($index+1)}}" type="text" class="form-control" ng-model="allowancesAdd.addveryhighallowance" 
																ng-pattern="/^\d{1,7}(?:\.\d{0,2})?$/i" required ng-class="{true: 'ng-border'}[onAddSave && addAllowancesForm.{{'txtaddveryhighallowance'+'_'+($index+1)}}.$invalid]" >
																<div class="error-messages" ng-if="onAddSave" ng-messages="addAllowancesForm['txtaddveryhighallowance'+'_'+($index+1)].$error">
									        						<em class="error help-block has-error" ng-message="required">Please enter value for very high allowance.</em>
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
											<button type="button" class="btn btn-primary btnSpace" id="btnAddSave" ng-click="onSaveClick(addAllowancesForm.$valid)" ng-disabled="!allowancesAddArray.length">Save</button>						
											<button type="button" class="btn btn-danger" id="btnAddCancel" ng-click="onCancelClickAdd()">Cancel</button>
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
                            <ng-form name="updateAllowancesForm" id="updateAllowancesForm" >
                                <div class="panel-heading panelHeadingStyle" ng-click="ShowHideUpdate()">
                                    <div class="row ">
										<label class="control-label col-sm-10 ">Update Annual Allowances</label>
										<label class="DownArrowColor col-sm-2  textAlignRight"> &#9660;</label>
									</div>
                                </div>
                                <div class="panel-body" ng-hide = "UpdateHidden">
                               		 <div class="row marginBottom5px">
                                        <label class="control-label col-sm-2 textAlignRight required-Field">Country</label>
                                        <div class="col-sm-3">
                                          <select id="ddlUpdateCountry" class="form-control"
												ng-model="updateAllowancesForm.countryModel"
												placeholder="Please select" name="ddlUpdateCountry"
												ng-options="cou.countryId as cou.countryName for cou in country| orderBy:'countryName'"												
												ng-class="{true: 'ng-border'} [(onUpdateSearch && updateAllowancesForm.ddlUpdateCountry.$invalid)]"
												required>
												<option value="" selected disabled>Please select</option>
											</select>
											<div class="error-messages" ng-if= "onUpdateSearch" ng-messages="updateAllowancesForm.ddlUpdateCountry.$error">
									        	<em class="error help-block has-error" ng-message="required">Please select Country.</em>
									        </div>
                                        </div>
                                        <label class="control-label col-sm-3 textAlignRight required-Field">Visa</label>
                                        <div class="col-sm-3">
											<select id="ddlUpdateVisa" class="form-control"
												ng-model="updateAllowancesForm.ddlUpdateVisaModel"
												placeholder="Please select" name="ddlAddVisa"
												ng-options="vi.id as vi.description for vi in visa"
												ng-class="{true: 'ng-border'} [(onUpdateSearch && updateAllowancesForm.ddlAddVisa.$invalid)]"
												required>
												<option value="" selected disabled>Please select</option>
											</select>
											<div class="error-messages" ng-if= "onUpdateSearch" ng-messages="updateAllowancesForm.ddlAddVisa.$error">
									        	<em class="error help-block has-error" ng-message="required">Please select Visa.</em>
									        </div>
                                        </div>
									</div>
									<div class="row marginBottom5px">
                                        <label class="control-label col-sm-2 textAlignRight required-Field">Year</label>
                                        <div class="col-sm-3">
                                           <select id="ddlUpdateYea" class="form-control" placeholder="Please select" name="ddlUpdateYea"
                                           		ng-model="updateAllowancesForm.ddlUpdateYear" 
                                           		ng-class="{true: 'ng-border'} [(onUpdateSearch && updateAllowancesForm.ddlUpdateYea.$invalid)]"
                                           		required>
												<option value="" selected disabled>Please select</option>
												<option ng-repeat="year in years">{{year}}</option>
											</select>
											<div class="error-messages" ng-if= "onUpdateSearch" ng-messages="updateAllowancesForm.ddlUpdateYea.$error">
									        	<em class="error help-block has-error" ng-message="required">Please select Year.</em>
									        </div>
                                        </div>
									</div>
									<div class="divEmptyThrice"></div>
									<div class="row text-center">
										<div class="col-sm-12">
											<button type="button" class="btn btn-primary btnSpace" id="btnUpdateSearch" ng-click="getUpdateAllownces(updateAllowancesForm.$valid)" >Search</button>
										</div>
									</div>
									<div class="divEmptyThrice"></div>	
                                   	<div class="row marginBottom5px">
	                                    <div class="col-sm-12  ">
											<div class="table-responsive" >
												<table 
													class="table clsTable table-striped table-bordered table-hover table-condensed "
													id="tblUpdateSalaryRate">
													<thead>
														<tr>
															<th>Designation</th>
															<th>Band/Grade</th>
															<th>GCM Level</th>
															<th>Low</th>
															<th>Medium</th>
															<th>High</th>
															<th>Very High</th>
														</tr>
													</thead>
													<tbody id="tBodyUpdateSalaryRate" >
														<tr id="{{'trallowancesUpdate'+'_'+($index+1)}}" ng-repeat="allowancesUpdate in allowancesUpdateArray">
															<td>{{allowancesUpdate.designation}}</td>
															<td>{{allowancesUpdate.empDesgmap.bandmap.description}}-{{allowancesUpdate.empDesgmap.grademap.description}}</td>
															<td>{{allowancesUpdate.empDesgmap.gcmCODE}}</td>
															<td>
																<input name="{{'txtUpdatelowallowance'+'_'+($index+1)}}" type="text" class="form-control" ng-model="allowancesUpdate.addlowallowance" 
																ng-pattern="/^\d{1,7}(?:\.\d{0,2})?$/i" required ng-class="{true: 'ng-border'}[onUpdateAllownce && updateAllowancesForm.{{'txtUpdatelowallowance'+'_'+($index+1)}}.$invalid]">
																<div class="error-messages" ng-if="onUpdateAllownce" ng-messages="updateAllowancesForm['txtUpdatelowallowance'+'_'+($index+1)].$error">
										        					<em class="error help-block has-error" ng-message="required">Please enter low allowance.</em>
										        					<em class="error help-block has-error" ng-message="pattern">Please enter numeric value max up to 7 digit and 2 decimal.</em>
										        				</div>
									        				</td>
															<td>
																<input name="{{'txtUpdatemediumallowance'+'_'+($index+1)}}" type="text" class="form-control" ng-model="allowancesUpdate.addmediumallowance" 
																ng-pattern="/^\d{1,7}(?:\.\d{0,2})?$/i" required ng-class="{true: 'ng-border'}[onUpdateAllownce && updateAllowancesForm.{{'txtUpdatemediumallowance'+'_'+($index+1)}}.$invalid]" >
																<div class="error-messages" ng-if="onUpdateAllownce" ng-messages="updateAllowancesForm['txtUpdatemediumallowance'+'_'+($index+1)].$error">
									        						<em class="error help-block has-error" ng-message="required">Please enter medium allowance.</em>
									        						<em class="error help-block has-error" ng-message="pattern">Please enter numeric value max up to 7 digit and 2 decimal.</em>
									        					</div>
															</td>
															<td>
																<input name="{{'txtUpdatehighallowance'+'_'+($index+1)}}" type="text" class="form-control" ng-model="allowancesUpdate.addhighallowance" 
																ng-pattern="/^\d{1,7}(?:\.\d{0,2})?$/i" required ng-class="{true: 'ng-border'}[onUpdateAllownce && updateAllowancesForm.{{'txtUpdatehighallowance'+'_'+($index+1)}}.$invalid]">
																<div class="error-messages" ng-if="onUpdateAllownce" ng-messages="updateAllowancesForm['txtUpdatehighallowance'+'_'+($index+1)].$error">
									        						<em class="error help-block has-error" ng-message="required">Please enter high allowance.</em>
									        						<em class="error help-block has-error" ng-message="pattern">Please enter numeric value max up to 7 digit and 2 decimal.</em>
									        					</div>
															</td>
															<td>
																<input name="{{'txtUpdateveryhighallowance'+'_'+($index+1)}}" type="text" class="form-control" ng-model="allowancesUpdate.addveryhighallowance" 
																ng-pattern="/^\d{1,7}(?:\.\d{0,2})?$/i" required ng-class="{true: 'ng-border'}[onUpdateAllownce && updateAllowancesForm.{{'txtUpdateveryhighallowance'+'_'+($index+1)}}.$invalid]">
																<div class="error-messages" ng-if="onUpdateAllownce" ng-messages="updateAllowancesForm['txtUpdateveryhighallowance'+'_'+($index+1)].$error">
									        						<em class="error help-block has-error" ng-message="required">Please enter very high allowance.</em>
									        						<em class="error help-block has-error" ng-message="pattern">Please enter numeric value max up to 7 digit and 2 decimal.</em>
									        				</div>
															</td>
														</tr>
													</tbody>
												</table>
											</div>
										</div>
									</div>
									<div class="row marginBottom5px" ng-show ="isActiveEnabled">
                                        <label class="control-label col-sm-2 textAlignRight required-Field">Is Active</label>
                                        <div class="col-sm-3">
                                        	<input type="checkbox" name="cbxUpdateIsActive" id="cbxUpdateIsActive" class="margingRightChkBx" ng-model="updateAllowancesForm.UpdateIsActive">
                                         </div>
                                    </div>
									<div class="divEmptyThrice"></div>
                                    <div class="row text-center">
										<div class="col-sm-12">		
											<button type="button" class="btn btn-primary btnSpace" id="btnUpdateUpdate" ng-click="onUpdateAnnualAllownceClick(updateAllowancesForm.$valid)" ng-disabled="!allowancesUpdateArray.length">Update</button>						
											<button type="button" class="btn btn-danger" id="btnUpdateCancel" ng-click="onCancelClickUpdate()">Cancel</button>
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
				            	<ng-form name="uploadAllowance" id="uploadAllowance">
		                            <div class="panel-heading panelHeadingStyle"  ng-click="ShowHideUpload()">
		                                <div class="row ">
											<label class="control-label col-sm-10 ">Upload Annual Allowances</label>
											<label class="DownArrowColor col-sm-2  textAlignRight"> &#9660;</label>
										</div>
		                            </div>
		                            <div class="panel-body" ng-hide = "UploadHidden">
			                             <div class="row">
			                             	<div class="col-sm-2 "></div>
				                            <label class="control-label col-sm-2 textAlignRight required-Field">Upload File name</label>
				                            <div class="col-sm-3">
				                            	<input type="file" class="form-control" name="fuUploadFilename"
													id="fuUploadFilename" ng-model="uploadAllowance.fuUploadFilenameModel" required
													check-file-size="uploadAllowance.fuUploadFilenameModel" 
													valid-File-Excel ng-class="{true: 'ng-border'} [(upload && uploadAllowance.fuUploadFilename.$invalid)]">
												<div class="error-messages" ng-if="upload" ng-messages="uploadAllowance.fuUploadFilename.$error">
												     <em class="error help-block has-error" ng-message="checkfilesize">File size is not valid for uploading!</em>
												     <em class="error help-block has-error" ng-message="extension">Incorrect file format</em> 
												     <em class="error help-block has-error" ng-message="required">Please select Allowance details file to upload.</em>
												</div>
											</div>
											<div class="col-sm-1">
												<button type="button" class="btn btn-primary btnSpace" id="btnUpload" ng-click="uploadData(uploadAllowance);">Upload</button>
											</div>											
											<div class="divDownloadImg col-sm-4">
												<a href="${contextPath}/resources/Documents/Allowance_Template.xls" target="_blank" class="anchorTrancColor" download="Allowance_Template.xls"><span>Download Template</span></a>
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
