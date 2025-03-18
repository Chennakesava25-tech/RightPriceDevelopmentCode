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
 	<script src="${contextPath}/resources/js/RightPrice/masterCommonCostParameters.js"></script>
 	<script src="${contextPath}/resources/js/Directives/angularCustomValidations.js"></script>
	<script src="${contextPath}/resources/js/Factory/WebServiceFactory.js"></script>
</head>
<body ng-app="RightPriceApp" ng-controller="MasterCommonCostParametersController" >
    <div id="includedContent" ng-include="'${contextPath}/Portal/Header.jsp'"></div>
    <fieldset ng-disabled="loading || showLoader">
    <div class="container">
        <div class="divEmpty"></div>
        <div class="row marginBottom5px">
            <div class="col-sm-12">
                <h3 class="text-left" id="PageHeading">Master - Common Cost Parameters</h3>
            </div>
        </div>	
        <div>
            <form class="form-inline" role="form" name="MasterCommonCostParameters" id="MasterCommonCostParameters">
            <div class="row">
					<div class="col-sm-12">
						<div class="panel-group">
							<div class="panel panel-info">
							<ng-form name="viewParameterForm" id="viewParameterForm" >
								<div class="panel-heading panelHeadingStyle"  ng-click="ShowHideViewCommonCostParameters()">
									<div class="row ">
										<label class="control-label col-sm-10 ">View Common Cost Parameter</label>
										<label class="DownArrowColor col-sm-2  textAlignRight"> &#9660;</label>
									</div>
								</div>
								<div class="panel-body" ng-hide = "ViewCommonCostParametersHidden">
                                    <div class="row marginBottom5px">
                                        <label class="control-label col-sm-2 textAlignRight required-Field">Country</label>
                                        <div class="col-sm-3">
                                         <select id="ddlViewCountry" class="form-control"
												ng-model="viewParameterForm.countryModel"
												placeholder="Please select" name="ddlViewCountry"
												ng-options="cou.countryId as cou.countryName for cou in country| orderBy:'countryName'"
												ng-class="{true: 'ng-border'} [(onViewSearch && viewParameterForm.ddlViewCountry.$invalid)]"
												required>
												<option value="" selected disabled>Please select</option>
											</select>
											<div class="error-messages" ng-if= "onViewSearch" ng-messages="viewParameterForm.ddlViewCountry.$error">
									        	<em class="error help-block has-error" ng-message="required">Please select Country.</em>
									        </div>
                                        </div>
                                        <label class="control-label col-sm-3 textAlignRight required-Field">Year</label>
                                        <div class="col-sm-3">
											<select id="ddlViewYear" class="form-control" 
                                                placeholder="Please select" name="ddlViewYear"
                                           		ng-model="viewParameterForm.ddlSearchYearModel" 
                                           		ng-class="{true: 'ng-border'} [(onViewSearch && viewParameterForm.ddlViewYear.$invalid)]"
                                           		required>
												<option value="" selected disabled>Please select</option>
												<option ng-repeat="year in years">{{year}}</option>
											</select>
											<div class="error-messages" ng-if= "onViewSearch" ng-messages="viewParameterForm.ddlViewYear.$error">
									        	<em class="error help-block has-error" ng-message="required">Please select Year.</em>
									        </div>
                                        </div>
									</div>
									<div class="divEmptyThrice"></div>
                                    <div class="row text-center">
										<div class="col-sm-12">
											<button type="button" class="btn btn-primary btnSpace" id="btnViewCommonCostSearch" ng-click="onViewParameterForm(viewParameterForm.$valid,viewParameterForm.countryModel)">Search</button>	
<!-- 											<button type="button" class="btn btn-primary" id="btnDownloadViewCostParameter" ng-click = "getMasterCommonCostExcel();" ng-disabled= downloadBtn>Download Cost Parameter</button> -->
										</div>
									</div>
                                    <div class="divEmptyThrice"></div>
									<div class="row">
	                                    <div class="col-sm-12">
											<div >
												<table
													class="table clsTable table-striped table-bordered table-hover table-condensed"
													id="tblViewCommonCostParameter">
													<thead>
														<tr>
															<th>Parameter Name</th>
															<th>Domestic</th>
															<th>Deputed</th>
															<th>Short Term</th>
															<th>Offshore</th>
															<!-- <th>Percentage<br>Value</th> -->
															<!-- <th>Start Date</th>
															<th>End Date</th> -->
														</tr>
													</thead>
													<tbody id="tBodytblViewCommonCostParamSection" >
														<tr id="{{'trccParamView'+'_'+($index+1)}}" ng-repeat="commonCostParamView in commonCostParamViewArray">
															<td class="tdTextAlignLeft">{{commonCostParamView.paramName}}</td>
															<td class="tdTextAlignRight">{{commonCostParamView.domestic}}</td>
															<td class="tdTextAlignRight">{{commonCostParamView.deputed}}</td>
															<td class="tdTextAlignRight">{{commonCostParamView.shortTerm}}</td>
															<td class="tdTextAlignRight">{{commonCostParamView.offshore}}</td>
															<!-- <td>{{(((commonCostParamView.domestic) + (commonCostParamView.deputed) + (commonCostParamView.shortTerm) + (commonCostParamView.offshore))/4) | number: 4}} </td> -->
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
                             <ng-form name="updateParameterForm" id="updateParameterForm" >
                                <div class="panel-heading panelHeadingStyle" ng-click="ShowHideUpdateCommonCostParameter()">
                                    <div class="row ">
										<label class="control-label col-sm-10 ">Update Common Cost Parameter</label>
										<label class="DownArrowColor col-sm-2  textAlignRight"> &#9660;</label>
									</div>
                                </div>
                                <div class="panel-body" ng-hide = "UpdateCommonCostParameterHidden">
                                	<div class="row marginBottom5px">
                                        <label class="control-label col-sm-2 textAlignRight required-Field">Country</label>
                                        <div class="col-sm-3">
                                           <select id="ddlUpdateCountry" class="form-control" placeholder="Please select" name="ddlUpdateCountry"
                                           		   ng-model="updateParameterForm.updateCountryModel" 
                                           		   ng-class="{true: 'ng-border'} [(onUpdateSearch && updateParameterForm.ddlUpdateCountry.$invalid)]"
                                           		   ng-options="cou.countryId as cou.countryName for cou in country| orderBy:'countryName'" required
                                           		   ng-change= "getVisaTypes(updateParameterForm.updateCountryModel)">
												<option value="" selected disabled>Please select</option>
											</select>
											<div class="error-messages" ng-if= "onUpdateSearch" ng-messages="updateParameterForm.ddlUpdateCountry.$error">
									        	<em class="error help-block has-error" ng-message="required">Please select Country.</em>
									        </div>
                                        </div>
                                        <label class="control-label col-sm-3 textAlignRight required-Field">Year</label>
                                        <div class="col-sm-3">
											<select id="ddlAddCCPYear" class="form-control" 
                                                placeholder="Please select" name="ddlAddCCPYear"
                                           		ng-model="updateParameterForm.updateCCPYear" 
                                           		ng-class="{true: 'ng-border'} [(onUpdateSearch && updateParameterForm.ddlAddCCPYear.$invalid)]"
                                           		required>
												<option value="" selected disabled>Please select</option>
												<option ng-repeat="year in years">{{year}}</option>
											</select>
                                        	<div class="error-messages" ng-if= "onUpdateSearch" ng-messages="updateParameterForm.ddlAddCCPYear.$error">
									        	<em class="error help-block has-error" ng-message="required">Please select Year.</em>
									        </div>
									     </div>
                                    </div>
                                    <div class="row marginBottom5px">
										<label class="control-label col-sm-2 textAlignRight required-Field">Visa Type</label>
                                        <div class="col-sm-3">
                                         <select id="ddlVisaType" class="form-control"
												ng-model="updateParameterForm.visaTypeModel"
												placeholder="Please select" name="ddlVisaType"
												ng-options="vst.visaTypeId as vst.visaLabel for vst in visaTypeDetails"
												ng-class="{true: 'ng-border'} [(onUpdateSearch && updateParameterForm.ddlVisaType.$invalid)]"
												ng-change="GetValue()"
												required>
												<option value="" selected disabled>Please select</option>
											</select>
											<div class="error-messages" ng-if="onUpdateSearch" ng-messages="updateParameterForm.ddlVisaType.$error">
									        	<em class="error help-block has-error" ng-message="required">Please select Visa Type.</em>
									        </div>
                                        </div>
									</div>
                                    <div class="divEmptyThrice"></div>
                                    <div class="row text-center">
										<div class="col-sm-12">
											<button type="button" class="btn btn-primary btnSpace" id="btnViewCommonCostSearch" ng-click="onUpdateCCPSearch(updateParameterForm.$valid)">Search</button>	
										</div>
									</div>
									<div class="divEmptyThrice"></div>
                                    <div class="row">
	                                    <div class="col-sm-12">
											<div >
												<table class="table clsTable table-striped table-bordered table-hover table-condensed">
													<thead>
														<tr>
															<th>Parameter Name</th>
															<th>{{visaTypeName}}</th>															
														</tr>
													</thead>
													<tbody>
														<tr id="{{'trAParamView'+'_'+($index+1)}}" ng-repeat="CCParamView in arrCCParam">
															<td class="tdTextAlignLeft">{{CCParamView.deductionTypeMap.paramName}}</td>
															<td>
																<input name="{{'txtaddParcentageDed'+'_'+($index+1)}}" ng-model="CCParamView.parDeduction" 
																	type="text" class="form-control" value="{{$index+1}}" ng-pattern= "/^[0-9]+(\.[0-9]{1,2})?$/" 
																	ng-class="{true: 'ng-border'}[onUpdateClick && updateParameterForm.{{'txtaddParcentageDed'+'_'+($index+1)}}.$invalid]">
																<div class="error-messages" ng-if="onUpdateClick" ng-messages="updateParameterForm['txtaddParcentageDed'+'_'+($index+1)].$error">
							        								<em class="error help-block has-error" ng-message="pattern">Please enter numeric value max up to 3 digit and 2 decimal.</em>
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
											<button type="button" class="btn btn-primary btnSpace" id="btnUpdateUpdate" ng-click="updateData(updateParameterForm.$valid)" ng-disabled="!arrCCParam.length" >Update</button>		
											<button type="button" class="btn btn-danger" id="btnUpdateCancel" ng-click="onCancelClickUpdate()">Cancel</button>
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
<%-- 	<div id="Footer" ng-include="'${contextPath}/Portal/TopBottomNavigation.jsp'"></div> --%>
	<div id="loader" ng-include="'${contextPath}/Portal/loader.jsp'"></div>
</body>
</html>
