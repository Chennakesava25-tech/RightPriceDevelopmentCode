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
 	<script src="${contextPath}/resources/js/RightPrice/mastersAssumptionsPrameter.js"></script>
 	<script src="${contextPath}/resources/js/Directives/angularCustomValidations.js"></script>
	<script src="${contextPath}/resources/js/Factory/WebServiceFactory.js"></script>
</head>
<body ng-app="RightPriceApp" ng-controller="MasterAssumptionsParametersController" >
    <div id="includedContent" ng-include="'${contextPath}/Portal/Header.jsp'"></div>
    <fieldset ng-disabled="loading || showLoader">
    <div class="container">
        <div class="divEmpty"></div>
        <div class="row marginBottom5px">
            <div class="col-sm-12">
                <h3 class="text-left" id="PageHeading">Master - Assumption Parameters</h3>
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
										<label class="control-label col-sm-10 ">View Assumption Parameter</label>
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
											<button type="button" class="btn btn-primary btnSpace" id="btnViewCommonCostSearch" ng-click="onAssumptionsViewParameterForm(viewParameterForm.$valid,viewParameterForm.countryModel)">Search</button>	
<!-- 											<button type="button" class="btn btn-primary" id="btnDownloadViewCostParameter" ng-click = "getMasterAssumptionsExcel();" ng-disabled= downloadBtn>Download Cost Parameter</button> -->
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
															<th>Domestic %</th>
															<th>Deputed %</th>
															<th>Short Term %</th>
															<th>Offshore %</th>
															<!-- <th>Percentage<br>Value</th> -->
															<!-- <th>Start Date</th>
															<th>End Date</th> -->
														</tr>
													</thead>
													<tbody id="tBodytblViewCommonCostParamSection" >
														<tr id="{{'trccParamView'+'_'+($index+1)}}" ng-repeat="commonCostParamView in commonCostParamViewArray">
															<td class="tdTextAlignLeft">{{commonCostParamView.description}}</td>
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
                            <ng-form name="addParameterForm" id="addParameterForm" >
                                <div class="panel-heading panelHeadingStyle"  ng-click="ShowHideAddCommonCostParameter()">
                                    <div class="row ">
										<label class="control-label col-sm-10">Update Assumption Parameter</label>
										<label class="DownArrowColor col-sm-2  textAlignRight"> &#9660;</label>
									</div>
                                </div>
                                <div class="panel-body" ng-hide = "AddCommonCostParameterHidden">
                                    <div class="row marginBottom5px">
                                        <label class="control-label col-sm-2 textAlignRight required-Field">Country</label>
                                        <div class="col-sm-3">
                                         	<select id="ddlViewCountry" class="form-control"
												ng-model="addParameterForm.countryModel"
												placeholder="Please select" name="ddlAddCCPCountry"
												ng-options="cou.countryId as cou.countryName for cou in country| orderBy:'countryName'"
												ng-class="{true: 'ng-border'} [(onSaveSearch && addParameterForm.ddlAddCCPCountry.$invalid)]"
												ng-change="getVisaTypes(addParameterForm.countryModel)"
												required>
												<option value="" selected disabled>Please select</option>
											</select>
											<div class="error-messages" ng-if="onSaveSearch" ng-messages="addParameterForm.ddlAddCCPCountry.$error">
									        	<em class="error help-block has-error" ng-message="required">Please select Country.</em>
									        </div>
                                        </div>
                                        <label class="control-label col-sm-3 textAlignRight required-Field">Year</label>
                                        <div class="col-sm-3">
											<select id="ddlAddCCPYear" class="form-control" 
                                                placeholder="Please select" name="ddlAddCCPYear"
                                           		ng-model="addParameterForm.addCCPYear" 
                                           		ng-class="{true: 'ng-border'} [(onSaveSearch && addParameterForm.ddlAddCCPYear.$invalid)]"
                                           		required>
												<option value="" selected disabled>Please select</option>
												<option ng-repeat="year in years">{{year}}</option>
											</select>
											<div class="error-messages" ng-if= "onSaveSearch" ng-messages="addParameterForm.ddlAddCCPYear.$error">
									        	<em class="error help-block has-error" ng-message="required">Please select Year.</em>
									        </div>
                                        </div>
									</div>
									<div class="row marginBottom5px">
										<label class="control-label col-sm-2 textAlignRight required-Field">Visa Type</label>
                                        <div class="col-sm-3">
                                         <select id="ddlVisaType" class="form-control"
												ng-model="addParameterForm.visaTypeModel"
												placeholder="Please select" name="ddlVisaType"
												ng-options="vst.visaTypeId as vst.visaLabel for vst in visaTypeDetails"
												ng-class="{true: 'ng-border'} [(onSaveSearch && addParameterForm.ddlVisaType.$invalid)]"
												ng-change="GetValue()"
												required>
												<option value="" selected disabled>Please select</option>
											</select>
											<div class="error-messages" ng-if="onSaveSearch" ng-messages="addParameterForm.ddlVisaType.$error">
									        	<em class="error help-block has-error" ng-message="required">Please select Visa Type.</em>
									        </div>
                                        </div>
									</div>
									<div class="divEmptyThrice"></div>
                                    <div class="row text-center">
										<div class="col-sm-12">
											<button type="button" class="btn btn-primary btnSpace" id="btnViewCommonCostSearch" ng-click="onAddAPSearch(addParameterForm.$valid)">Search</button>	
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
														<tr id="{{'trAParamView'+'_'+($index+1)}}" ng-repeat="assumptionParamView in arrAssumptionParam">
															<td class="tdTextAlignLeft">{{assumptionParamView.assuMap.description}}</td>
															<td>
																<input name="{{'txtaddParcentageDed'+'_'+($index+1)}}" ng-model="assumptionParamView.parDeduction" 
																	type="text" class="form-control" value="{{$index+1}}" ng-pattern= "/^[0-9]+(\.[0-9]{1,2})?$/" 
																	ng-class="{true: 'ng-border'}[onAddSave && addParameterForm.{{'txtaddParcentageDed'+'_'+($index+1)}}.$invalid]">
																<div class="error-messages" ng-if="onAddSave" ng-messages="addParameterForm['txtaddParcentageDed'+'_'+($index+1)].$error">
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
											<button type="button" class="btn btn-primary btnSpace" id="btnAddSave" ng-disabled="!arrAssumptionParam.length" ng-click="updateData(addParameterForm.$valid);">Update</button>						
											<button type="button" class="btn btn-danger" id="btnAddCancel" ng-click="onCancelClickAdd()">Cancel</button>
										</div>
									</div>
                                </div>
                                
                                </ng-form>
                            </div>
                        </div>
                    </div>
                </div>
                
                
                                        		<!------------------------------------------ assemption parameter update Functionality ----------------------------------------------->

    <div class="row">
					<div class="col-sm-12">
						<div class="panel-group">
							<div class="panel panel-info">
							<ng-form name="viewUpdatManualParameterForm" id="viewUpdatManualParameterForm" >
							
								<div class="panel-heading panelHeadingStyle"  ng-click="ShowHideUpdateManuallyCommonCostParameters()">
									<div class="row ">
										<label class="control-label col-sm-10 ">Update Assumption Parameter Manually</label>
										<label class="DownArrowColor col-sm-2  textAlignRight"> &#9660;</label>
									</div>
								</div>
								<div class="panel-body" ng-hide = "ViewUpdateManuallyCostParametersHidden">
                                    <div class="row marginBottom5px">
                                        <label class="control-label col-sm-2 textAlignRight required-Field">Country</label>
                                        <div class="col-sm-3">
                                         <select id="ddlUpdatManualCountry" class="form-control"
												ng-model="viewUpdatManualParameterForm.countryUpdatManualModel"
												placeholder="Please select" name="ddlUpdatManualCountry"
												ng-options="cou.countryId as cou.countryName for cou in country| orderBy:'countryName'"
												ng-class="{true: 'ng-border'} [(onViewSearchManual && viewUpdatManualParameterForm.ddlUpdatManualCountry.$invalid)]"
												required>
												<option value="" selected disabled>Please select</option>
											</select>
											<div class="error-messages" ng-if= "onViewSearchManual" ng-messages="viewUpdatManualParameterForm.ddlUpdatManualCountry.$error">
									        	<em class="error help-block has-error" ng-message="required">Please select Country.</em>
									        </div>
                                        </div>
                                        
                                        
                                        <label class="control-label col-sm-3 textAlignRight">Year</label>
                                        <div class="col-sm-3">
											<select id="ddlViewYear" class="form-control" 
                                                placeholder="2019" name="ddlViewYear"
                                           		disabled 
                                           		>
												<option value="" selected disabled>2019</option>
												
											</select>
                                        </div>
									</div>
									
								
								<div class="row marginBottom5px">
                                        <label class="control-label col-sm-2 textAlignRight required-Field">Deduction Category Id</label>
                                        <div class="col-sm-3">
                                         <select id="ddlDeductionCategoryId" class="form-control"
												ng-model="viewUpdatManualParameterForm.DeductionCategoryIdModel"
												placeholder="Please select" name="ddlDeductionCategoryId"
												ng-options=" de.DeductionCategoryName for de in DeductionCategoryDetails| orderBy:'DeductionCategoryId'"
												ng-class="{true: 'ng-border'} [(onViewSearchManual && viewUpdatManualParameterForm.ddlDeductionCategoryId.$invalid)]"
												required>
												<option value="" selected disabled>Please select</option>
											</select>
											<div class="error-messages" ng-if= "onViewSearchManual" ng-messages="viewUpdatManualParameterForm.ddlDeductionCategoryId.$error">
									        	<em class="error help-block has-error" ng-message="required">Please select Deduction Category Id.</em>
									        </div>
                                        </div>
                                        
                                        
                                         <label class="control-label col-sm-3 textAlignRight required-Field">Visa Type</label>
                                        <div class="col-sm-3">
                                         <select id="ddlVisaType" class="form-control"
												ng-model="viewUpdatManualParameterForm.VisaTypeModel"
												placeholder="Please select" name="ddlVisaType"
												ng-options="visa.visaId as visa.visaName for visa in VisaDetails| orderBy:'visaId'"
												ng-class="{true: 'ng-border'} [(onViewSearchManual&& viewUpdatManualParameterForm.ddlVisaType.$invalid)]"
												required>
												<option value="" selected disabled>Please select</option>
											</select>
											<div class="error-messages" ng-if= "onViewSearchManual" ng-messages="viewUpdatManualParameterForm.ddlVisaType.$error">
									        	<em class="error help-block has-error" ng-message="required">Please select Deduction Type.</em>
									        </div>
                                        </div>
                                        
									</div>
									
									
									<div class="divEmptyThrice"></div>
                                    <div class="row text-center">
										<div class="col-sm-12">
											<button type="button" class="btn btn-primary btnSpace" id="btnViewCommonCostSearch" ng-click="onAssumptionsViewUpdatManualParameterForm(viewUpdatManualParameterForm.$valid,viewUpdatManualParameterForm.countryUpdatManualModel)">Search</button>	
<!-- 											<button type="button" class="btn btn-primary" id="btnDownloadViewCostParameter" ng-click = "getMasterAssumptionsExcel();" ng-disabled= downloadBtn>Download Cost Parameter</button> -->
									<div class="divEmptyThrice"></div>
									<div class="divEmptyThrice"></div>	
										
										
										
																								<table border="1" ng-hide = 'DataPresent'
															class="table clsTable table-striped table-bordered table-hover table-condensed"
															id="tblRoleSelection2">
															<thead>
																<tr>
																
																	<th class="tdVetAlignMiddle">Select <span class="margin10px"></span> 
																	<input type="checkbox"
																	name="{{'cbxItem'+'_'+($index+1)}}"
																	id="{{'cbxItem'+'_'+($index+1)}}"
																	class="margingRightChkBx "
																	ng-model="viewUpdatManualParameterForm.selectAllCheckBox"
																	ng-change=addAllCheckedItems(viewUpdatManualParameterForm,selectAllCheckBox)>
																	</th>
																	
																	<th class="firstColLeftAlign tdMasterRoleCode" bgcolor="#CCFFFF">Parameter Description</th>
																	<th class="tdSyntelRole" bgcolor="#CCFFFF">Fixed Deduction</th>
																	<th class="tdSyntelRole" bgcolor="#CCFFFF">Percent Deduction</th>
																	

																</tr>
															</thead>

															<tbody id="tBodytblRoleSelection2">

																<tr id="{{'trroleSelection'+'_'+($index+1)}}"
																	ng-repeat="currentRole in DeducData|orderBy:'roleId'">
																	
																	<td><input type="checkbox"
																		name="{{'cbxItem'+'_'+($index+1)}}"
																		id="{{'cbxItem'+'_'+($index+1)}}"
																		class="margingRightChkBx"
																		ng-model="chkBoxStatus"
																		ng-change=addChkItem(viewUpdatManualParameterForm,chkBoxStatus,$index)></td>
																	
																	<td><span ng-model="viewUpdatManualParameterForm.paramNameModel" >{{currentRole.deductionTypeMap.paramName}}</span></td>
																	
																	<td>
																		<input
																		name="{{'txtFixedDeduction'+'_'+($index+1)}}" type="text"
																		class="form-control" placeholder = {{currentRole.codeName}}
																		id="{{'txtFixedDeduction'+'_'+($index+1)}}"
																		ng-model="currentRole.FixedDeductionModel"
																		ng-class="{true: 'ng-border'}[onUpdateManual && viewUpdatManualParameterForm.{{'txtFixedDeduction'+'_'+($index+1)}}.$invalid]"
																		>
																		<div class="error-messages" ng-if="onUpdateManual"
																			ng-messages="viewUpdatManualParameterForm['FixedDeductionModel'+'_'+($index+1)].$error">
																			<em class="error help-block has-error"
																				ng-message="required">Please enter customer
																				role.</em>
																		</div>
																		
																		
																		</td>
																		
																		<td><input
																		name="{{'txtPercentDeduction'+'_'+($index+1)}}" type="text"
																		class="form-control" placeholder = {{currentRole.parDeduction}}
																		id="{{'txtPercentDeduction'+'_'+($index+1)}}"
																		ng-model="currentRole.PercentDeductionModel"
																		ng-class="{true: 'ng-border'}[onUpdateManual && viewUpdatManualParameterForm.{{'txtPercentDeduction'+'_'+($index+1)}}.$invalid]"
																		>
																		<div class="error-messages" ng-if="onUpdateManual"
																			ng-messages="roleSelectionForm['txtPercentDeduction'+'_'+($index+1)].$error">
																			<em class="error help-block has-error"
																				ng-message="required">Please enter percent Deduction
																				role.</em>
																		</div></td>
																</tr>
															</tbody>
														</table>
										
										
  <div class="divEmptyThrice" ng-hide = 'DataPresent'></div>
                                    <div class="row text-center" ng-hide = 'DataPresent'>
										<div class="col-sm-12">		
											<button type="button" class="btn btn-primary btnSpace" id="btnAddSave" 
											ng-click="updateManualData(viewUpdatManualParameterForm.$valid);">Update</button>						
											<button type="button" class="btn btn-danger" id="btnAddCancel" ng-click="onCancelClickAdd()">Cancel</button>
										</div>
									</div>
                                </div>										
										
										
										</div>
									</div>
									</ng-form>
                                    <div class="divEmptyThrice"></div>
								</div>
								
							</div>
						</div>
					</div>
				
				<!------------------------------------------ File Upload Functionality ends ----------------------------------------------->
                
                
	          </form>
        </div>
    </div>
    </fieldset>
	<div id="footer" ng-include="'${contextPath}/Portal/Footer.jsp'"></div>
<%-- 	<div id="Footer" ng-include="'${contextPath}/Portal/TopBottomNavigation.jsp'"></div> --%>
	<div id="loader" ng-include="'${contextPath}/Portal/loader.jsp'"></div>
</body>
</html>
