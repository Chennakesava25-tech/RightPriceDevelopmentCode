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
	<script src="${contextPath}/resources/js/RightPrice/masterCountryForex.js"></script>
	<script src="${contextPath}/resources/js/Directives/angularCustomValidations.js"></script>
	<script src="${contextPath}/resources/js/Factory/WebServiceFactory.js"></script>
</head>
<body ng-app="RightPriceApp" ng-controller="MasterCountryForexController" >
   <div id="includedContent" ng-include="'${contextPath}/Portal/Header.jsp'"></div>
   <fieldset ng-disabled="loading || showLoader">
    <div class="container">
        <div class="divEmpty"></div>
        <div class="row marginBottom5px">
            <div class="col-sm-12">
                <h3 class="text-left" id="PageHeading">Master - Country Forex</h3>
            </div>
        </div>	
        <div>
            <form class="form-inline" role="form" name="frmMasterCountryForex" id="frmMasterCountryForex">
            <div class="row">
					<div class="col-sm-12">
						<div class="panel-group">
							<div class="panel panel-info">
								<div class="panel-heading panelHeadingStyle"  ng-click="ShowHideView()">
									<div class="row ">
										<label class="control-label col-sm-10">View Country Details</label>
										<label class="DownArrowColor col-sm-2  textAlignRight"> &#9660;</label>
									</div>
								</div>
								<div class="panel-body" ng-hide = "ViewHidden">
									<div class="row">
                                   		<div class="col-sm-12">
                                   			<div class="table-responsive ">
												<table
													class="table clsTable table-striped table-bordered table-hover table-condensed "
													id="tblViewCountryDetails">
													<thead>
														<tr>
															<th>Country</th>
															<th>Currency Code</th>
															<th>Exchange Rate (INR)</th>
															<th>Premium</th>
															<!-- <th>Is Offshore Site</th> -->
														</tr>
													</thead>
													<tbody id="tBodyViewCountryDetails" >
														<tr id="{{'trcountryForexView'+'_'+($index+1)}}" ng-repeat="row in countryData">
														
														<td class="tdTextAlignLeft">{{row.countryName}}</td>
														<td class="tdTextAlignLeft">{{row.currencyCode}}</td>
														<td class="tdTextAlignRight">{{row.exchangeRate}}</td>
														<td class="tdTextAlignRight">{{row.syntelFacilityCost}}</td>
														<!--<td ng-if="row.isOffshoreSite ==0">No</td>
														<td ng-if="row.isOffshoreSite ==1">Yes</td> -->
														</tr>
														
													</tbody>
												</table>
											</div>
										</div>
									</div>
									<div class="divEmptyThrice"></div>
                                    <div class="row text-center">
										<div class="col-sm-12">
											<button type="button" class="btn btn-primary btnSpace" id="btnDownloadViewCountryDetails" ng-click="getMasterCommonCostExcel();"> Download Country Details</button>	
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
                        <ng-form name="addCountryForm" id="addCountryForm" >
                            <div class="panel panel-info ">
                                <div class="panel-heading panelHeadingStyle"  ng-click="ShowHideAdd()">
                                    <div class="row ">
										<label class="control-label col-sm-10 ">Add Country</label>
										<label class="DownArrowColor col-sm-2  textAlignRight"> &#9660;</label>
									</div>
                                </div>
                                <div class="panel-body" ng-hide = "AddHidden">
                                	<div class="row marginBottom5px">
                                   		<label class="control-label col-sm-2 textAlignRight required-Field">Country</label>
                                        <div class="col-sm-3">
											<input name="txtCountry" type="text" class="form-control" id="txtCountry" ng-model="addCountryForm.countryModelName"  required 
											ng-class="{true: 'ng-border'} [(onSubmit && addCountryForm.txtCountry.$invalid)]" ng-maxlength="50" ng-pattern="/^[a-z\s]+$/i">
											<div class="error-messages" ng-if="onSubmit" ng-messages="addCountryForm.txtCountry.$error">
									        	<em class="error help-block has-error" ng-message="required">Please enter Country</em>
									        	<em class="error help-block" ng-message="maxlength">Country name cannot exceed 50 characters</em>
									        	<em class="error help-block" ng-message="pattern">Please enter characters and spaces only</em>
											</div>
                                        </div>
                                        
                                        <label class="control-label col-sm-3 textAlignRight required-Field">Country Code</label>
                                        <div class="col-sm-3">
											<input name="txtCountryCode" type="text" class="form-control" id="txtCountryCode" ng-model="addCountryForm.countryCodeModelName"  required 
											ng-class="{true: 'ng-border'} [(onSubmit && addCountryForm.txtCountry.$invalid)]" ng-maxlength="7" ng-pattern="/^[a-z]+$/i">
											<div class="error-messages" ng-if="onSubmit" ng-messages="addCountryForm.txtCountryCode.$error">
									        	<em class="error help-block has-error" ng-message="required">Please enter Country Code</em>
									        	<em class="error help-block" ng-message="maxlength">Country name cannot exceed 7 characters</em>
									        	<em class="error help-block" ng-message="pattern">Please enter characters only</em>
											</div>
                                        </div>
                                    </div> 
                                    <div class="row marginBottom5px">
                                    <label class="control-label col-sm-2 textAlignRight required-Field">Currency Name</label>
                                        <div class="col-sm-3">
                                            <input id="txtCurrencyName" class="form-control" name="txtCurrencyName" ng-model="addCountryForm.currencyNameModel" ng-pattern="/^[a-z]+$/i"
                                            ng-class="{true: 'ng-border'} [(onSubmit && addCountryForm.txtCurrencyName.$invalid)]" ng-maxlength="7" required />
											<div class="error-messages" ng-if="onSubmit" ng-messages="addCountryForm.txtCurrencyName.$error">
									        	<em class="error help-block has-error" ng-message="required">Please enter Currency</em>
									        	<em class="error help-block" ng-message="pattern">Please enter characters only</em>
									        	<em class="error help-block" ng-message="maxlength">Currency name cannot exceed 7 characters</em>
											</div>
                                        </div>
                                    	<label class="control-label col-sm-3 textAlignRight required-Field">Exchange Rate (INR)</label>
                                        <div class="col-sm-3">
                                        	<input name="txtExchangeRate" type="text" class="form-control" id="txtExchangeRate" ng-model="addCountryForm.exchangeRateModel" required positive
                                        	 ng-class="{true: 'ng-border'} [( onSubmit && addCountryForm.txtExchangeRate.$invalid)]" ng-pattern="/^\d{1,3}(?:\.\d{0,2})?$/i" ng-change="addCalcuPremium(addCountryForm.exchangeRateModel);">
                                        	 <div class="error-messages" ng-if="onSubmit" ng-messages="addCountryForm.txtExchangeRate.$error">
									        	<em class="error help-block has-error" ng-message="required">Please enter Exchange Rate</em>
									        	<em class="error help-block" ng-message="positive">Exchange Rate should be greater than 0</em>
									        	<em class="error help-block" ng-message="pattern">Please enter numeric value upto 3 digit and 2 decimal</em>
											</div>
                                        </div>
                                    </div> 
                                    <div class="row marginBottom5px">
	                           			<label class="control-label col-sm-2 textAlignRight">Only Manual RC/Deal</label>
	                                   	<div class="col-sm-3">
	                                   		<input type="checkbox" name="cbxAddManualRC_Deal" id="cbxUpdateIsActive" class="margingRightChkBx"
	                                   		ng-model="addCountryForm.addOnlyManRCDeal">
	                                   	</div>
	                                   	<label class="control-label col-sm-3 textAlignRight">Premium</label>
                                         <div class="col-sm-3">
                                            <input name="txtOnsiteFacilityCost" type="text" class="form-control" id="txtAddOnsiteFacilityCost" ng-model="addCountryForm.facilityCostModel" ng-disabled="true">
                                        </div>
	                          		</div>
                                    <!-- <div class="row marginBottom5px">
                                    <label class="control-label col-sm-2 textAlignRight required-Field">Syntel Onsite Facility Cost/Hr</label>
                                         <div class="col-sm-3">
                                            <input name="txtOnsiteFacilityCost" type="text" class="form-control" id="txtAddOnsiteFacilityCost" ng-model="addCountryForm.facilityCostModel" required positive
                                            ng-class="{true: 'ng-border'} [(onSubmit && addCountryForm.txtOnsiteFacilityCost.$invalid)]" ng-pattern="/^\d{1,5}(?:\.\d{0,2})?$/i">
                                            <div class="error-messages" ng-if="onSubmit" ng-messages="addCountryForm.txtOnsiteFacilityCost.$error">
									        	<em class="error help-block has-error" ng-message="required">Please enter Syntel Onsite Facility Cost/Hr</em>
									        	<em class="error help-block" ng-message="positive">Syntel Onsite Facility Cost/Hr should be greater than 0</em>
									        	<em class="error help-block" ng-message="pattern">Please enter numeric value upto 5 digit and 2 decimal</em>
											</div>
                                        </div>
                                    
                                   		<label class="control-label col-sm-3 textAlignRight required-Field">Is Offshore Site</label>
                                        <div class="col-sm-3">
                                        	<input type="checkbox" name="cbxIsOffshoreSite" id="cbxIsOffshoreSite" class="margingRightChkBx" ng-model="addCountryForm.isOffshoreSiteModel" >
                                        </div>
                                    </div> 
                                    <div class="row marginBottom5px">
                                    	<label class="control-label col-sm-2 textAlignRight required-Field">RP Currency ID</label>
                                    	<div class="col-sm-3">
                                    		<input name="txtRpCurrencyId" type="text" class="form-control" id="txtRpCurrencyId" ng-model="addCountryForm.rpCurrencyIdModel"  required positive
                                    		ng-class="{true: 'ng-border'} [(onSubmit && addCountryForm.txtRpCurrencyId.$invalid)]" ng-maxlength="2" onkeypress='return event.charCode >= 48 && event.charCode <= 57'>
                                    		<div class="error-messages" ng-if="onSubmit" ng-messages="addCountryForm.txtRpCurrencyId.$error">
									        	<em class="error help-block has-error" ng-message="required">Please enter RP currency Id</em>
									        	<em class="error help-block" ng-message="positive">RP currency Id should be greater than 0</em>
									        	<em class="error help-block" ng-message="maxlength">Please enter numeric value up to 2 digit </em>
											</div>
                                    	</div>
                                    </div>-->
                                     <!--  <div class="row marginBottom5px">
	                           			<div class="row">
                                 		<div class="col-sm-3"></div>
                                   		<div class="col-sm-6">
                                   			<div class="table-responsive ">
												<table
													class="table clsTable table-striped table-bordered table-hover table-condensed "
													id="tblViewVisa">
													<thead>
														<tr>
														   <th>Staffing</th>
															<th>Visa</th>
															<th>Visa Labels</th>
															
														</tr>
													</thead>
													<tbody id="tBodyViewVisa" >
													<tr id="{{'trCountryFrorexData'+'_'+($index+1)}}" ng-repeat="row in visa">
													                <td> 
													                   <input type="checkbox" name="{{'cbxAddIsActive'+'_'+($index+1)}}" id= "{{'cbxAddIsActive'+'_'+($index+1)}}" class="margingRightChkBx"
	                                   		                           ng-model="addCountryForm.addVisaType[$index+1]"  ng-change="LabelRqd(row,addCountryForm.addVisaType[$index+1],row.visaLabels,row.visaName,$index);" ng-checked="false">
	                                   		                       </td>
													                <td>{{row.visaName}}</td>	
																   <td>
																     <input name="{{'trCountryFrorexLabel'+'_'+($index+1)}}" type="text" class="form-control" id="{{'trCountryFrorexLabel'+'_'+($index+1)}}" 
																	  ng-model="row.visaLabels"  ng-required="row.isRequired" ng-disabled="row.isDisabled"
																	  ng-class="{true: 'ng-border'} [( onSubmit && addCountryForm.{{'trCountryFrorexLabel'+'_'+($index+1)}}.$invalid)]" 
																	  ng-minlength="3" ng-pattern="/^[a-z]+$/i">
																     <div class="error-messages" ng-if="onSubmit" ng-messages="addCountryForm['trCountryFrorexLabel'+'_'+($index+1)].$error">
															        	<em class="error help-block has-error" ng-message="required">Please enter Label Name</em>
															        	<em class="error help-block" ng-message="minlength">Label Name should be greater than 3</em>
															        	<em class="error help-block" ng-message="pattern">Please enter character only</em>
																	</div>
																   </td>
														</tr>	 	
													</tbody>
												</table>
											</div>
										</div>
									</div>
	                          		</div> -->
									<div class="divEmptyThrice"></div>
                                    <div class="row text-center">
										<div class="col-sm-12">		
											<button type="button" class="btn btn-info btnSpace" id="btnAddAddNew" ng-click="save(addCountryForm);">Save</button>
											<!-- <button type="button" class="btn btn-primary btnSpace" id="btnAddSave">Save</button> -->						
											<button type="button" class="btn btn-danger" id="btnAddCancel" ng-click="cancel();">Cancel</button>
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
                            <div class="panel panel-info ">
                            <ng-form name="updateCountryForm" id="updateCountryForm" >
                                <div class="panel-heading panelHeadingStyle"  ng-click="ShowHideUpdate()">
                                    <div class="row ">
										<label class="control-label col-sm-10 ">Update Country</label>
										<label class="DownArrowColor col-sm-2  textAlignRight"> &#9660;</label>
									</div>
                                </div>
                                <div class="panel-body" ng-hide = "UpdateHidden">
                                	<div class="row">
                                   		<div class="col-sm-12">
                                   			<div class="table-responsive ">
												<table
													class="table clsTable table-striped table-bordered table-hover table-condensed "
													id="tblUpdateCountryDetails">
													<thead>
														<tr>
															<th>Country</th>
															<th>Currency Code</th>
															<th>Exchange Rate  (INR)</th>
															<th>Premium</th>
															<th>Only Manual RC/Deal</th>
															<th>Active</th>
														</tr>
													</thead>
													<tbody id="tBodyUpdateCountryDetails" >
														<tr id="{{'trUpdateCountry'+'_'+($index+1)}}" ng-repeat="data in countryDetails">
														
															<!-- <td>{{data.countryName}}</td> ng-maxlength="3" -->
															<td>
																<input name="{{'txtUpdateCountry'+'_'+($index+1)}}" type="text" class="form-control" id="{{'txtUpdateCountry'+'_'+($index+1)}}" 
																	ng-model="data.countryName"  required ng-class="{true: 'ng-border'} [(update && updateCountryForm.{{'txtUpdateCountry'+'_'+($index+1)}}.$invalid)]" 
																	 ng-pattern="/^[a-z() ]+$/i">
																<div class="error-messages" ng-if="update" ng-messages="updateCountryForm['txtUpdateCountry'+'_'+($index+1)].$error">
														        	<em class="error help-block has-error" ng-message="required">Please enter Country</em>
														        	<!-- <em class="error help-block" ng-message="maxlength">Country code cannot exceed 3 characters</em> -->
														        	<em class="error help-block" ng-message="pattern">Please enter only characters</em>
																</div>
															</td>
															<td>
																<input name="{{'txtUpdateCountryCode'+'_'+($index+1)}}" type="text" class="form-control" id="{{'txtUpdateCountryCode'+'_'+($index+1)}}" 
																	ng-model="data.currencyCode"  required ng-class="{true: 'ng-border'} [(update && updateCountryForm.{{'txtUpdateCountryCode'+'_'+($index+1)}}.$invalid)]" 
																	ng-maxlength="7" ng-pattern="/^[a-z]+$/i">
																<div class="error-messages" ng-if="update" ng-messages="updateCountryForm['txtUpdateCountryCode'+'_'+($index+1)].$error">
														        	<em class="error help-block has-error" ng-message="required">Please enter Country Code</em>
														        	<em class="error help-block" ng-message="maxlength">Country code cannot exceed 7 characters</em>
														        	<em class="error help-block" ng-message="pattern">Please enter only characters</em>
																</div>
															</td>
															<td>
																 <input name="{{'txtUpdateexcCode'+'_'+($index+1)}}" type="text" class="form-control" id="{{'txtUpdateexcCode'+'_'+($index+1)}}" 
																	ng-model="data.exchangeRate" required positive ng-change="calUpdatePremium(data,data.exchangeRate,$index);"
					                                        		ng-class="{true: 'ng-border'} [(update && updateCountryForm.{{'txtUpdateexcCode'+'_'+($index+1)}}.$invalid)]" 
					                                        		 ng-pattern="/^\d{1,3}(?:\.\d{0,2})?$/i"> 
				                                       			<div class="error-messages" ng-if="update" ng-messages="updateCountryForm['txtUpdateexcCode'+'_'+($index+1)].$error" >
														        	<em class="error help-block has-error" ng-message="required">Please enter Exchange Rate</em>
														        	<em class="error help-block" ng-message="positive">Exchange Rate should be greater than 0</em>
														        	<em class="error help-block" ng-message="pattern">Please enter numeric value up to 3 digit and 2 decimal</em>
																</div>
															</td>
															<td>
																<input name="{{'txtUpdateExchangeRate'+'_'+($index+1)}}" type="text" class="form-control" id="{{'txtUpdateExchangeRate'+'_'+($index+1)}}" 
																	ng-model="data.syntelFacilityCost" ng-change="calUpdateSyntelPremium(data);" ng-disabled="data.isdis" >
															</td>
															<td class="text-center">
																<input type="checkbox" name="{{'cbxUpdateIsOnlyManualRC_Deal'+'_'+($index+1)}}" id="{{'cbxUpdateIsOnlyManualRC_Deal'+'_'+($index+1)}}" 
																class="margingRightChkBx text-center" ng-model="data.isOnlyManRCDeal" >
															</td>
															<td class="text-center">
																<input type="checkbox" name="{{'cbxUpdateIsActive_Inactive'+'_'+($index+1)}}" id="{{'cbxUpdateIsActive_Inactive'+'_'+($index+1)}}" 
																class="margingRightChkBx text-center" ng-model="data.isActive" >
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
											<button type="button" class="btn btn-primary btnSpace" id="btnUpdateUpdate" ng-click="updateData(updateCountryForm);">Update</button>						
											<button type="button" class="btn btn-danger" id="btnUpdateCancel" ng-click="clear();">Cancel</button>
										</div>
									</div>
                                </div>
                                </ng-form>
                            </div>
                        </div>
                    </div>
                </div>
				<!-- <div class="row">
				   <div class="col-sm-12">
				  		<div class="panel-group">
				            <div class="panel panel-info ">
				            <ng-form name="uploadCountryForm" id="uploadContryForm" >
	                            <div class="panel-heading panelHeadingStyle" ng-click="ShowHideUpload()">
	                                <div class="row ">
										<label class="control-label col-sm-10 ">Upload Country</label>
										<label class="DownArrowColor col-sm-2  textAlignRight"> &#9660;</label>
									</div>
	                            </div>
	                            <div class="panel-body" ng-hide = "UploadHidden">
		                             <div class="row">
		                             	<div class="col-sm-2 "></div>
			                            <label class="control-label col-sm-2 textAlignRight required-Field">Upload File Name</label>
			                            <div class="col-sm-3 ">
		                            	<input type="file" class="form-control" name="fuUploadFilename"
										id="fuUploadFilename" ng-model="uploadCountryForm.fuUploadFilenameModel" required
										 valid-File-Excel check-file-size="uploadCountryForm.fuUploadFilenameModel" 
										ng-class="{true: 'ng-border'} [(upload && uploadCountryForm.fuUploadFilename.$invalid)]">
									<div class="error-messages" ng-if="upload" ng-messages="uploadCountryForm.fuUploadFilename.$error">
									     <em class="error help-block has-error" ng-message="checkfilesize">File size is not valid for uploading!</em>
									     <em class="error help-block has-error" ng-message="extension">File format is not valid for uploading!</em> 
									     <em class="error help-block has-error" ng-message="required">Country Details File is required!!</em>
										</div>
									</div>
										<div class="col-sm-1">
											<button type="button" class="btn btn-primary btnSpace" id="btnUpload" ng-click="uploadData(uploadCountryForm);">Upload</button>						
										</div>
									</div>
                            	</div>
                            	</ng-form>
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
