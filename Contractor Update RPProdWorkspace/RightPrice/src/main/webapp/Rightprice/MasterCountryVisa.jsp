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
	<script src="${contextPath}/resources/js/RightPrice/masterCountryVisa.js"></script>
	<script src="${contextPath}/resources/js/Directives/angularCustomValidations.js"></script>
	<script src="${contextPath}/resources/js/Factory/WebServiceFactory.js"></script> 
</head>
<body ng-app="RightPriceApp" ng-controller="MasterCountryVisaController" >
   <div id="includedContent" ng-include="'${contextPath}/Portal/Header.jsp'"></div>
   <fieldset ng-disabled="loading || showLoader">
    <div class="container">
        <div class="divEmpty"></div>
        <div class="row marginBottom5px">
            <div class="col-sm-12">
                <h3 class="text-left" id="PageHeading">Master - Country Visa</h3>
            </div>
        </div>	
        <div>
            <form class="form-inline" role="form" name="Master" id="Master">
            <div class="row">
					<div class="col-sm-12">
						<div class="panel-group">
						<ng-form name="viewVisaForm" id="viewVisaForm" >
							<div class="panel panel-info">
								<div class="panel-heading panelHeadingStyle" ng-click="ShowHideView()">
									<div class="row ">
										<label class="control-label col-sm-10">View Visa Labels</label>
										<label class="DownArrowColor col-sm-2  textAlignRight"> &#9660;</label>
									</div>
								</div>
								<div class="panel-body" ng-hide = "ViewHidden">
									<div class="row marginBottom5px">
										<div class="col-sm-3"></div>
                                        <label class="control-label col-sm-1 textAlignRight required-Field">Country</label>
                                        <div class="col-sm-3">
	                                           	<select id="ddlViewCountry" class="form-control" placeholder="Please select" name="ddlViewCountry"
	                                           		ng-model="viewVisaForm.ViewCountryModel" required  
	                                           		ng-class="{true: 'ng-border'} [(onSearch && viewVisaForm.ddlViewCountry.$invalid)]"
	                                           		 ng-options="cn.countryName for cn in country| orderBy:'countryName'">
													<option value="" selected >Please select</option>
												</select>
												<div class="error-messages" ng-if="onSearch" ng-messages="viewVisaForm.ddlViewCountry.$error">
										        	<em class="error help-block has-error" ng-message="required">Please select Country</em>
												</div>
	                                        </div>
                                        <div class="col-sm-4">		
											<button type="button" class="btn btn-primary btnSpace" id="btnViewSearch" ng-click="searchData(viewVisaForm)">Search</button>
											<!-- <button type="button" class="btn btn-primary" id="btnDownloadViewVisaLabels" ng-disabled ="isDownload">Download Visa Labels</button> -->	
										</div>
									</div>                                   	
									<div class="divEmptyThrice"></div>
                                 	<div class="row">
                                 		<div class="col-sm-3"></div>
                                   		<div class="col-sm-6">
                                   			<div class="table-responsive ">
												<table
													class="table clsTable table-striped table-bordered table-hover table-condensed "
													id="tblViewVisa">
													<thead>
														<tr>
															<th>Visa</th>
															<th>Visa Labels</th>
															<th>Staffing</th>
														</tr>
													</thead>
													<tbody id="tBodyViewVisa" >
													<tr id="{{'trViewCityData'+'_'+($index+1)}}" ng-repeat="row in viewVisaData">
																<td>{{row.visaType.description}}</td>
																<td>{{row.visaLabel}}</td>
																<td>{{row.isStaffing ==1 ? 'Yes' : 'No'}}</td>
														<!-- 
															<td >H1</td>
															<td></td>
														</tr>
														<tr>
															<td>L1</td>
															<td></td>
														</tr>	
														<tr>
															<td>H1</td>
															<td></td>
														</tr>	
														<tr>
															<td>H1</td>
															<td></td> -->
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
                        <ng-form name="AddVisaForm" id="AddVisaForm" >
                            <div class="panel panel-info ">
                                <div class="panel-heading panelHeadingStyle" ng-click="ShowHideAdd()">
                                    <div class="row ">
										<label class="control-label col-sm-10 ">Add Visa label</label>
										<label class="DownArrowColor col-sm-2  textAlignRight"> &#9660;</label>
									</div>
                                </div>
                                <div class="panel-body" ng-hide = "AddHidden">
                                	<div class="row marginBottom5px">
                                   		<label class="control-label col-sm-2 textAlignRight required-Field">Country</label>
                                        <div class="col-sm-3">
                                            <select id="ddlAddCountry" class="form-control" placeholder="Please select" name="ddlAddCountry"
                                            		ng-model="AddVisaForm.AddCountryModel" required 
                                            		ng-class="{true: 'ng-border'} [(onSave && AddVisaForm.ddlAddCountry.$invalid)]"
                                            		ng-options="cn.countryName for cn in country| orderBy:'countryName'">
												<option value="" selected disabled>Please select</option>
												<option value="1">India</option>
												<option value="2">US</option>
												<option value="3">UK</option>
											</select>
											<div class="error-messages" ng-if="onSave" ng-messages="AddVisaForm.ddlAddCountry.$error">
										    <em class="error help-block has-error" ng-message="required">Please select Country</em>
											</div>
                                        </div>
                                    	<label class="control-label col-sm-3 textAlignRight required-Field">Visa</label>
                                        <div class="col-sm-3">
                                           <select id="ddlAddVisa" class="form-control" placeholder="Please select" name="ddlAddVisa"
                                           			ng-model="AddVisaForm.AddVisaModel" required 
                                           			ng-change="visaAlreadyAdded(AddVisaForm.AddCountryModel.countryId,AddVisaForm.AddVisaModel.visaId);"
                                           			ng-class="{true: 'ng-border'} [(onSave && AddVisaForm.ddlAddVisa.$invalid)]"
                                           			ng-options="vn.visaName for vn in visa">
												<option value="" selected disabled>Please select</option>
												<option value="1">1</option>
												<option value="2">2</option>
												<option value="3">3</option>
											</select>
											<div class="error-messages" ng-if="onSave" ng-messages="AddVisaForm.ddlAddVisa.$error">
										        	<em class="error help-block has-error" ng-message="required">Please select Visa</em>
											</div>
                                        </div>
                                        
                                      </div> 
                                	<div class="row marginBottom5px"> 
                                		<label class="control-label col-sm-2 textAlignRight required-Field">Visa Label</label>
                                        <div class="col-sm-3">
                                            <input name="txtAddVisaLabel" type="text" class="form-control" id="txtAddVisaLabel"
                                            		ng-model="AddVisaForm.AddVisaLabelModel"  required 
													ng-class="{true: 'ng-border'} [(onSave && AddVisaForm.txtAddVisaLabel.$invalid)]" ng-maxlength="50" 
													ng-pattern="/^[a-zA-Z0-9._-]+$/i">
													<div class="error-messages" ng-if="onSave" ng-messages="AddVisaForm.txtAddVisaLabel.$error">
											        	<em class="error help-block has-error" ng-message="required">Please enter Visa Label</em>
											        	<em class="error help-block" ng-message="maxlength">Visa Label cannot exceed 50 characters</em>
											        	<em class="error help-block" ng-message="pattern">Visa Label cannot contain special character</em>
													</div>
                                        </div>
                                        <label class="control-label col-sm-3 textAlignRight required-Field">Staffing</label>
                                        <div class="col-sm-3">
	                                   		<input type="checkbox" name="cbxAddStaffing" id="cbxStaffing" class="margingRightChkBx"
	                                   		ng-model="AddVisaForm.addStaffingChecked">
	                                   	</div>
                                        
                                        
                                    </div>
									<div class="divEmptyThrice"></div>
                                    <div class="row text-center">
										<div class="col-sm-12">		
											<button type="button" class="btn btn-primary btnSpace" id="btnAddSave" ng-click="saveData(AddVisaForm)">Save</button>						
											<button type="button" class="btn btn-danger" id="btnAddCancel" ng-click="cancel()">Cancel</button>
										</div>
									</div>
                                </div>
                                
                                
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
                                
                                
                            </div>
                        </ng-form>
                        </div>
                    </div>
                </div>
                 <div class="row">
                    <div class="col-sm-12">
                        <div class="panel-group">
                        <ng-form name="UpdateVisaForm" id="UpdateVisaForm" >
                            <div class="panel panel-info ">
                                <div class="panel-heading panelHeadingStyle" ng-click="ShowHideUpdate()">
                                    <div class="row ">
										<label class="control-label col-sm-10 ">Update Visa label</label>
										<label class="DownArrowColor col-sm-2  textAlignRight"> &#9660;</label>
									</div>
                                </div>
                                <div class="panel-body" ng-hide = "UpdateHidden">
                                	<div class="row marginBottom5px">
                                	 	<label class="control-label col-sm-2 textAlignRight required-Field">Country</label>
                                        <div class="col-sm-3">
                                            <select id="ddlUpdateCountry" class="form-control" placeholder="Please select" name="ddlUpdateCountry"
                                            		ng-model="UpdateVisaForm.UpdateCountryModel" required 
                                            		ng-change="getVisaLabel(UpdateVisaForm.UpdateCountryModel.countryId , UpdateVisaForm.UpdateVisaModel.visaId)"
                                            		ng-class="{true: 'ng-border'} [(onUpdate && UpdateVisaForm.ddlUpdateCountry.$invalid)]"
                                            		ng-options="cn.countryName for cn in country| orderBy:'countryName'">
												<option value="" selected disabled>Please select</option>
												<option value="1">India</option>
												<option value="2">US</option>
												<option value="3">UK</option>
											</select>
											<div class="error-messages" ng-if="onUpdate" ng-messages="UpdateVisaForm.ddlUpdateCountry.$error">
										        	<em class="error help-block has-error" ng-message="required">Please select Country</em>
											</div>
                                        </div>
                                        <label class="control-label col-sm-3 textAlignRight required-Field">Visa</label>
                                        <div class="col-sm-3">
                                           <select id="ddlUpdateVisa" class="form-control" placeholder="Please select" name="ddlUpdateVisa"
                                           			ng-model="UpdateVisaForm.UpdateVisaModel" required 
                                           			ng-change="getVisaLabel(UpdateVisaForm.UpdateCountryModel.countryId , UpdateVisaForm.UpdateVisaModel.visaId)"
                                           			ng-class="{true: 'ng-border'}  [(onUpdate && UpdateVisaForm.ddlUpdateVisa.$invalid)]"
                                           			ng-options="vn.visaName for vn in visa">
												<option value="" selected disabled>Please select</option>
												<option value="1">1</option>
												<option value="2">2</option>
												<option value="3">3</option>
											</select>
											<div class="error-messages" ng-if="onUpdate" ng-messages="UpdateVisaForm.ddlUpdateVisa.$error">
										        	<em class="error help-block has-error" ng-message="required">Please select Visa</em>
											</div>
                                        </div>
	                                </div>
	                                <div class="row marginBottom5px"> 
	                                		<label class="control-label col-sm-2 textAlignRight required-Field">Visa Label</label>
	                                        <div class="col-sm-3">
	                                            <input name="txtUpdateVisaLabel" type="text" class="form-control" id="txtUpdateVisaLabel"
	                                            		ng-model="UpdateVisaForm.UpdateVisaLabelModel" required
	                                            		ng-class="{true: 'ng-border'}  [(onUpdate && UpdateVisaForm.txtUpdateVisaLabel.$invalid)]" ng-maxlength="50" 
														ng-pattern="/^[a-zA-Z0-9._-]+$/i">
													<div class="error-messages" ng-if="onUpdate" ng-messages="UpdateVisaForm.txtUpdateVisaLabel.$error">
											        	<em class="error help-block has-error" ng-message="required">Please enter Visa Label</em>
											        	<em class="error help-block" ng-message="maxlength">Visa Label cannot exceed 50 characters</em>
											        	<em class="error help-block" ng-message="pattern">Visa Label cannot contain special character</em>
													</div>
	                                        </div>
	                                         <label class="control-label col-sm-3 textAlignRight required-Field">Staffing</label>
		                                        <div class="col-sm-3">
			                                   		<input type="checkbox" name="cbxUpdateStaffing" id="cbxupdateStaffing" class="margingRightChkBx"
			                                   		ng-model="UpdateVisaForm.updateStaffingChecked">
			                                   	</div>
	                                 </div>
									<div class="divEmptyThrice"></div>
                                    <div class="row text-center">
										<div class="col-sm-12">		
											<button type="button" class="btn btn-primary btnSpace" id="btnUpdateUpdate" ng-click="updateData(UpdateVisaForm);">Update</button>						
											<button type="button" class="btn btn-danger" id="btnUpdateCancel" ng-click="cancel();">Cancel</button>
										</div>
									</div>
                                </div>
                            </div>
                        </ng-form>
                        </div>
                    </div>
                </div>
				 <!-- <div class="row">
				   <div class="col-sm-12">
				  		<div class="panel-group">
				            <div class="panel panel-info ">
	                            <div class="panel-heading panelHeadingStyle" ng-click="ShowHideUpload()">
	                                <div class="row ">
										<label class="control-label col-sm-10 ">Upload Visa label</label>
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
