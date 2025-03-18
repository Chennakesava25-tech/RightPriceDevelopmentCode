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
	<script src="${contextPath}/resources/js/RightPrice/MastersMiscellaneousCost.js"></script>
	<script src="${contextPath}/resources/js/Directives/angularCustomValidations.js"></script>
	<script src="${contextPath}/resources/js/Factory/WebServiceFactory.js"></script>
    <script type="text/javascript">
    </script>
</head>
<body ng-app="RightPriceApp" ng-controller="MiscellaneousCostController" >
<div id="includedContent" ng-include="'${contextPath}/Portal/Header.jsp'"></div>
<fieldset ng-disabled="loading || showLoader">
    <div class="container">
        <div class="divEmpty"></div>
        <div class="row" id="parentHelpDiv">
            <div class="col-sm-2 col-sm-offset-10">
                <!-- <a href="../Support/Documents/User_Manual_Client_Visit_Approval.pdf" target="_blank">Help</a> -->
            </div>
        </div>
        <div class="row marginBottom5px">
            <div class="col-sm-12">
                <h3 class="text-left" id="PageHeading">Master - Shift Cost</h3>
            </div>
        </div>	
        <div>
            <form class="form-inline" role="form" name="Master" id="Master">
            	<div class="row">
					<div class="col-sm-12">
						<div class="panel-group">
							<ng-form name="searchMiscellaneousCostForm" id="searchMiscellaneousCostForm" >
								<div class="panel panel-info">
									<div class="panel-heading panelHeadingStyle " ng-click="ShowHideView()"	>
										<div class="row ">
											<label class="control-label col-sm-10 ">View Offshore Shift Cost</label>
											<label class="DownArrowColor col-sm-2  textAlignRight"> &#9660;</label>
										</div>
									</div>
									<div class="panel-body" ng-hide = "ViewHidden">
										<div class="row marginBottom5px">
	                                        <label class="control-label col-sm-2 textAlignRight required-Field">City</label>
	                                        <div class="col-sm-3">
	                                           <select id="ddlViewCity" class="form-control" placeholder="Please select" name="ddlViewCity"
	                                           			ng-model="searchMiscellaneousCostForm.ViewCityModel" required 
	                                           			ng-class="{true: 'ng-border'} [(onSearch && searchMiscellaneousCostForm.ddlViewCity.$invalid)]">
													<option value="" selected>Please select</option>
													 <option value="1">Pune</option>
													<option value="2">Others</option>
												</select>
												<div class="error-messages" ng-if="onSearch" ng-messages="searchMiscellaneousCostForm.ddlViewCity.$error">
										        	<em class="error help-block has-error" ng-message="required">Please select City</em>
												</div>
	                                        </div>
										</div>
	                                    <div class="divEmptyThrice"></div>
	                                    <div class="row text-center">
											<div class="col-sm-12">		
												<button type="button" class="btn btn-primary btnSpace" id="btnViewSearch" ng-click="searchData(searchMiscellaneousCostForm);">Search</button>
											</div>
										</div>
										<div class="divEmptyThrice"></div>
	                                    <div class="row">
		                                    <div class="col-sm-12">
												<div class="table-responsive  " >
													<table 
														class="table clsTable table-striped table-bordered table-hover table-condensed "
														id="tblViewOffshore">
														<thead>
															<tr>
																<th>Night Shift cost</th>
																<th>Night Shift Transport cost</th>
																<th>Second Shift cost</th>
																<th>Second Shift Transport cost</th>
															</tr>
														</thead>
														<tbody id="tBodyViewOffshore" >
															<tr id="{{'trViewCityData'+'_'+($index+1)}}" ng-repeat="row in viewMiscellaneousCostData" >
																<td class="tdTextAlignRight">{{row.nightShiftCost}}</td>
																<td class="tdTextAlignRight">{{row.nightShiftTransportCost}}</td>
																<td class="tdTextAlignRight">{{row.secondShiftCost}}</td>
																<td class="tdTextAlignRight">{{row.secondShiftTransportCost}}</td>
															</tr>
														</tbody>
													</table>
												</div>
											</div>
										</div>
									</div>
								</div>
							</ng-form>
						</div>
					</div>
				</div>
                 <div class="row">
                    <div class="col-sm-12">
                        <div class="panel-group">
                        <ng-form name="updateMiscellaneousCostForm" id="updateMiscellaneousCostForm" >
                            <div class="panel panel-info ">
                                <div class="panel-heading panelHeadingStyle" ng-click="ShowHideUpdate()">
                                    <div class="row ">
										<label class="control-label col-sm-10 ">Update Offshore Shift Cost</label>
										<label class="DownArrowColor col-sm-2  textAlignRight"> &#9660;</label>
									</div>
                                </div>
                                <div class="panel-body" ng-hide = "UpdateHidden">
                               		<div class="row marginBottom5px">
                                    <label class="control-label col-sm-2 textAlignRight required-Field">City</label>
                                        <div class="col-sm-3">
                                           <select id="ddlUpdateCity" class="form-control" placeholder="Please select" name="ddlUpdateCity"
                                           			ng-model="updateMiscellaneousCostForm.UpdateCityModel"  
                                           			ng-class="{true: 'ng-border'} [(onUpdate && updateMiscellaneousCostForm.ddlUpdateCity.$invalid)]"
	                                           		required ng-change="getUpdateMiscellaneousCost(updateMiscellaneousCostForm.UpdateCityModel)">
	                                           		<option value="" selected disabled>Please select</option>
	                                           		<option value="1">Pune</option>
													<option value="2">Others</option>
											</select>
											<div class="error-messages" ng-if="onUpdate" ng-messages="updateMiscellaneousCostForm.ddlUpdateCity.$error">
										        <em class="error help-block has-error" ng-message="required">Please select City</em>
											</div>
                                        </div>
                                    </div>
                                    <div class="row marginBottom5px">
                                    	<label class="control-label col-sm-2 textAlignRight required-Field">Night Shift Cost</label>
                                        <div class="col-sm-3">
                                            <input name="txtUpdateNightShiftCost" type="text" class="form-control" id="txtUpdateNightShiftCost"
                                            	 ng-model="updateMiscellaneousCostForm.UpdateNightShiftCost"  required 
													ng-class="{true: 'ng-border'} [(onUpdate && updateMiscellaneousCostForm.txtUpdateNightShiftCost.$invalid)]"
													ng-pattern="/^\d{1,7}(?:\.\d{0,2})?$/i">
											<div class="error-messages" ng-if="onUpdate" ng-messages="updateMiscellaneousCostForm.txtUpdateNightShiftCost.$error">
									        	<em class="error help-block has-error" ng-message="required">Please enter night shift cost</em>
									        	<em class="error help-block" ng-message="pattern">Please enter numeric value max up to 7 digit and 2 decimal.</em>
											</div>		
                                        </div>
                                    	<label class="control-label col-sm-3 textAlignRight required-Field">Second Shift Cost</label>
                                        <div class="col-sm-3">
                                        	<input name="txtUpdateSecondShiftCost" type="text" class="form-control" id="txtUpdateSecondShiftCost" 
                                        		ng-model=" updateMiscellaneousCostForm.UpdateSecondShiftCost"  required 
												ng-class="{true: 'ng-border'} [(onUpdate && updateMiscellaneousCostForm.txtUpdateSecondShiftCost.$invalid)]"
												ng-pattern="/^\d{1,7}(?:\.\d{0,2})?$/i">
											<div class="error-messages" ng-if="onUpdate" ng-messages="updateMiscellaneousCostForm.txtUpdateSecondShiftCost.$error">
									        	<em class="error help-block has-error" ng-message="required">Please enter second shift cost</em>
										       	<em class="error help-block" ng-message="pattern">Please enter numeric value max up to 7 digit and 2 decimal.</em>
											</div>
                                        </div>
                                    </div>
                                    <div class="row marginBottom5px">
                                    	<label class="control-label col-sm-2 textAlignRight required-Field">Night Shift Transport Cost</label>
                                        <div class="col-sm-3">
                                            <input name="txtUpdateNightShiftTransportCost" type="text" class="form-control" id="txtUpdateNightShiftTransportCost"
                                            		ng-model="updateMiscellaneousCostForm.UpdateNightShiftTransportCostModel" required
                                            		ng-class="{true: 'ng-border'} [(onUpdate && updateMiscellaneousCostForm.txtUpdateNightShiftTransportCost.$invalid)]"
												 	ng-pattern="/^\d{1,7}(?:\.\d{0,2})?$/i">
											 	<div class="error-messages" ng-if="onUpdate" ng-messages="updateMiscellaneousCostForm.txtUpdateNightShiftTransportCost.$error">
										        	<em class="error help-block has-error" ng-message="required">Please enter night shift transport cost</em>
											       	<em class="error help-block" ng-message="pattern">Please enter numeric value max up to 7 digit and 2 decimal.</em>
												</div>
                                        </div>
                                     	<label class="control-label col-sm-3 textAlignRight required-Field">Second Shift Transport Cost</label>
                                        <div class="col-sm-3">
                                            <input name="txtUpdateSecondShiftTransportCost" type="text" class="form-control" id="txtUpdateSecondShiftTransportCost"
                                            		ng-model="updateMiscellaneousCostForm.UpdateSecondShiftTransportCost" required
                                            		ng-class="{true: 'ng-border'} [(onUpdate && updateMiscellaneousCostForm.txtUpdateSecondShiftTransportCost.$invalid)]"
												 	ng-pattern="/^\d{1,7}(?:\.\d{0,2})?$/i">
												<div class="error-messages" ng-if="onUpdate" ng-messages="updateMiscellaneousCostForm.txtUpdateSecondShiftTransportCost.$error">
										        	<em class="error help-block has-error" ng-message="required">Please enter second shift transport cost</em>
											       	<em class="error help-block" ng-message="pattern">Please enter numeric value max up to 7 digit and 2 decimal.</em>
												</div>
                                        </div>
                                    </div>
									<div class="divEmptyThrice"></div>
                                    <div class="row text-center">
										<div class="col-sm-12">		
											<button type="button" class="btn btn-primary btnSpace" id="btnUpdateUpdate" ng-click="updateData(updateMiscellaneousCostForm);">Update</button>						
											<button type="button" class="btn btn-danger" id="btnUpdateCancel"  ng-click="cancel();">Cancel</button>
										</div>
									</div>
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
