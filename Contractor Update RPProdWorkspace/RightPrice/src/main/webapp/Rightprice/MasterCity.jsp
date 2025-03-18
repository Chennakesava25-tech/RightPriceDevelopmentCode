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
	<script src="${contextPath}/resources/js/RightPrice/MasterCity.js"></script>
	<script src="${contextPath}/resources/js/Directives/angularCustomValidations.js"></script>
	<script src="${contextPath}/resources/js/Factory/WebServiceFactory.js"></script>
</head>
<body ng-app="RightPriceApp" ng-controller="MasterCityController" >
     <div id="includedContent" ng-include="'${contextPath}/Portal/Header.jsp'"></div>
     <fieldset ng-disabled="loading || showLoader">
    <div class="container">
        <div class="divEmpty"></div>
        <div class="row marginBottom5px">
            <div class="col-sm-12">
                <h3 class="text-left" id="PageHeading">Master - City</h3>
            </div>
        </div>	
        <div>
	      	<form class="form-inline" role="form" name="Master" id="Master">
	            <div class="row">
					<div class="col-sm-12">
						<div class="panel-group">
							<ng-form name="searchCityForm" id="searchCityForm" >
								<div class="panel panel-info">
									<div class="panel-heading panelHeadingStyle" ng-click="ShowHideView()">
										<div class="row ">
											<label class="control-label col-sm-10 ">View Cities</label>
											<label class="DownArrowColor col-sm-2  textAlignRight"> &#9660;</label>
										</div>
									</div>
									<div class="panel-body" ng-hide = "ViewHidden">
										<div class="row marginBottom5px">
	                                        <label class="control-label col-sm-2 textAlignRight required-Field">Country</label>
	                                        <div class="col-sm-3">
	                                           	<select id="ddlViewCountry" class="form-control" placeholder="Please select" name="ddlViewCountry"
	                                           		ng-model="searchCityForm.ViewCountryModel" required ng-options="cn.countryName for cn in country| orderBy:'countryName'"	
	                                           		ng-class="{true: 'ng-border'} [(onSearch && searchCityForm.ddlViewCountry.$invalid)]"
	                                           		ng-change="getCity(searchCityForm.ViewCountryModel.countryId);disableDownload();" >
													<option value="" selected >Please select</option>
												</select>
												<div class="error-messages" ng-if="onSearch" ng-messages="searchCityForm.ddlViewCountry.$error">
										        	<em class="error help-block has-error" ng-message="required">Please select Country</em>
												</div>
	                                        </div>
	                                        <label class="control-label col-sm-3 textAlignRight">City</label>
	                                        <div class="col-sm-3">
	                                           <select id="ddlViewCity" class="form-control" placeholder="Please select" name="ddlViewCity"
	                                           		ng-model="searchCityForm.ViewCityModel"  ng-options="ct.cityName for ct in city| orderBy:'cityName'"
	                                           		ng-change="disableDownload();">
													<option value="" selected >Please select</option>
												</select>
	                                        </div>
										</div>
	                                    <div class="divEmptyThrice"></div>
	                                    <div class="row text-center">
											<div class="col-sm-12">		
												<button type="button" class="btn btn-primary btnSpace" id="btnViewSearch" ng-click="searchData(searchCityForm);">Search</button>
												<button type="button" class="btn btn-primary btnSpace" id="btnDownloadViewCities" ng-disabled=downloadBtn ng-click="getCityExcelData();" >Download Cities</button>	
											</div>
										</div>
										<div class="divEmptyThrice"></div>
	                                    <div class="row">
		                                    <div class="col-sm-12">
												<div class="table-responsive  " >
													<table 
														class="table clsTable table-striped table-bordered table-hover table-condensed "
														id="tblViewCities">
														<thead>
															<tr>
																<th class="tdWidthCityView">Country</th>
																<th class="tdWidthCityView">City</th>
																<th class="tdWidthCityView">City Categorization</th>
																<th class="tdWidthCityView">Cola Value</th>
															</tr>
														</thead>
														<tbody id="tBody" >
															<tr id="{{'trViewCityData'+'_'+($index+1)}}" ng-repeat="row in viewDataReport |orderBy:'cityName'">
																<td>{{row.countryName}}</td>
																<td>{{row.cityName}}</td>
																<td>{{row.description}}</td>
																<td>{{row.colaValue}}</td>
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
	                    	<ng-form name="addCityForm" id="addCityForm" >
		                         <div class="panel panel-info">
		                             <div class="panel-heading panelHeadingStyle" ng-click="ShowHideAdd()">
		                             	<div class="row ">
											<label class="control-label col-sm-10 ">Add City</label>
											<label class="DownArrowColor col-sm-2  textAlignRight"> &#9660;</label>
										</div>
		                             </div>
		                             <div class="panel-body" ng-hide = "AddHidden">
		                             	<div class="row marginBottom5px">
		                                 	<label class="control-label col-sm-2 textAlignRight required-Field">Country</label>
		                                    <div class="col-sm-3">
			                                    <select id="ddlAddCountry" class="form-control" placeholder="Please select" name="ddlAddCountry"
			                                    		ng-model="addCityForm.AddCountryModel" required
			                                    		ng-class="{true: 'ng-border'} [(onSave && addCityForm.ddlAddCountry.$invalid)]"
			                                    		ng-options="cn.countryName for cn in country| orderBy:'countryName'">
													<option value="" selected disabled>Please select</option>
												</select>
												<div class="error-messages" ng-if="onSave" ng-messages="addCityForm.ddlAddCountry.$error">
										        	<em class="error help-block has-error" ng-message="required">Please select Country</em>
												</div>
		                                 	</div>
		                                 	<label class="control-label col-sm-3 textAlignRight required-Field">City</label>
		                                    <div class="col-sm-3">
		                                     	<input name="txtAddCity" type="text" class="form-control" id="txtAddCity" ng-model="addCityForm.AddCityModel"  required 
												ng-class="{true: 'ng-border'} [(onSave && addCityForm.txtAddCity.$invalid)]" ng-maxlength="50" ng-pattern="/^[a-z\s]+$/i">
												<div class="error-messages" ng-if="onSave" ng-messages="addCityForm.txtAddCity.$error">
									        		<em class="error help-block has-error" ng-message="required">Please enter City</em>
									        		<em class="error help-block" ng-message="maxlength">city name cannot exceed 50 characters</em>
									        		<em class="error help-block" ng-message="pattern">City name cannot contain numeric value or special character</em>
												</div>
		                                 	</div>
		                             	</div>
		                                <div class="row marginBottom5px">
		                                 	<label class="control-label col-sm-2 textAlignRight required-Field">City Categorization</label>
		                                   	<div class="col-sm-3">
		                                         <select id="ddlAddCityCategorization" class="form-control" placeholder="Please select" ng-change="onChangeValueForAddCity()"
		                                         			name="ddlAddCityCategorization" ng-model="addCityForm.AddCityCategorizationModel" required 
		                                         			ng-class="{true: 'ng-border'} [(onSave && addCityForm.ddlAddCityCategorization.$invalid)]"
		                                         			ng-options="cc.codeName as cc.description  for cc in citycategorization">
													<option value="" selected disabled>Please select</option>
												</select>
												<div class="error-messages" ng-if="onSave" ng-messages="addCityForm.ddlAddCityCategorization.$error">
										        	<em class="error help-block has-error" ng-message="required">Please select City Categorization</em>
												</div>
		                                  	</div>
		                                   	<label class="control-label col-sm-3 textAlignRight">Cola Value</label>
		                                    	<div class="col-sm-3">
		                                        	<input name="txtAddColaValue" type="text" class="form-control" id="txtAddColaValue"
		                                          	ng-model="addCityForm.AddColaValueModel" ng-disabled="addCityForm.AddCityCategorizationModel.citycategorization != 'VHIGH'"
												   	ng-class="{true: 'ng-border'} [(onSave && addCityForm.txtAddColaValue.$invalid)]" ng-maxlength="50" ng-pattern="/^\d{1,8}(?:\.\d{0,2})?$/i">
													<div class="error-messages" ng-if="onSave" ng-messages="addCityForm.txtAddColaValue.$error">
										        		<em class="error help-block has-error" ng-message="required">Please enter cola value</em>
										        		<em class="error help-block" ng-message="maxlength">Please enter numeric value</em>
										        		<em class="error help-block" ng-message="pattern">Please enter numeric value</em>
													</div>
												</div>
									 	</div>
										<div class="divEmptyThrice"></div>
		                                <div class="row text-center">
											<div class="col-sm-12">	
												<button type="button" class="btn btn-primary btnSpace" id="btnAddSave"ng-click="addNew(addCityForm);">Save</button>						
												<button type="button" class="btn btn-danger" id="btnAddCancel"  ng-click="cancel();">Cancel</button>
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
	                   		<ng-form name="updateCityForm" id="updateCityForm" >
	                       		<div class="panel panel-info ">
	                           		<div class="panel-heading panelHeadingStyle" ng-click="ShowHideUpdate()">
		                               	<div class="row ">
											<label class="control-label col-sm-10 ">Update City</label>
											<label class="DownArrowColor col-sm-2  textAlignRight"> &#9660;</label>
										</div>
		                           	</div>
	                           		<div class="panel-body" ng-hide = "UpdateHidden">
		                          		<div class="row marginBottom5px">
		                              		<label class="control-label col-sm-2 textAlignRight required-Field">Country</label>
		                                 	<div class="col-sm-3">
		                                    	<select id="ddlUpdateCountry" class="form-control" placeholder="Please select" name="ddlUpdateCountry"
		                                      		ng-model="updateCityForm.UpdateCountryModel" required
		                                      		ng-class="{true: 'ng-border'} [(update && updateCityForm.ddlUpdateCountry.$invalid)]"
		                                      		ng-options="ucn.countryName for ucn in country| orderBy:'countryName'"
		                                      		ng-change="getCity(updateCityForm.UpdateCountryModel.countryId)">
													<option value="" selected disabled>Please select</option>
												</select>
												<div class="error-messages" ng-if="update" ng-messages="updateCityForm.ddlUpdateCountry.$error">
									        		<em class="error help-block has-error" ng-message="required">Please select Country</em>
												</div>
		                                   	</div>
		                               		<label class="control-label col-sm-3 textAlignRight required-Field">City</label>
		                                   	<div class="col-sm-3">
		                                    	<select id="ddlUpdateCity" class="form-control" placeholder="Please select" name="ddlUpdateCity"
		                                      		ng-model="updateCityForm.UpdateCityModel" required 
		                                      		ng-class="{true: 'ng-border'} [(update && updateCityForm.ddlUpdateCity.$invalid)]"
		                                      		ng-options="uct.cityName for uct in city| orderBy:'cityName'"
		                                      		required ng-change="putCityDetails()">
													<option value="" selected disabled>Please select</option>
												</select>
												<div class="error-messages" ng-if="update" ng-messages="updateCityForm.ddlUpdateCity.$error">
										        	<em class="error help-block has-error" ng-message="required">Please select City</em>
												</div>
		                                   	</div>
		                               </div>
		                               	<div class="row marginBottom5px">
		                                	<label class="control-label col-sm-2 textAlignRight required-Field">City Categorization</label>
		                                   	<div class="col-sm-3">
		                                      	<select id="ddlUpdateCityCategorization" class="form-control" placeholder="Please select"  
		                                   		   	name="ddlUpdateCityCategorization" ng-model="updateCityForm.UpdateCityCategorizationModel" required 
		                                   		    ng-class="{true: 'ng-border'} [(update && updateCityForm.ddlUpdateCityCategorization.$invalid)]"
		                                   		    ng-options="cc.codeName as cc.description for cc in citycategorization"
		                                   		    ng-change="onChangeValue()">
													<option value="" selected disabled>Please select</option>
												</select>
												<div class="error-messages" ng-if="update" ng-messages="updateCityForm.ddlUpdateCityCategorization.$error">
										        	<em class="error help-block has-error" ng-message="required">Please select City Categorization</em>
												</div>
		                                   </div>
		                                   <label class="control-label col-sm-3 textAlignRight">Cola Value</label>
		                                   <div class="col-sm-3">
		                                  	 	<input name="txtUpdateColaValue" type="text" class="form-control" id="txtUpdateColaValue"
		                                      		ng-model="updateCityForm.UpdateColaValueModel" 
													ng-class="{true: 'ng-border'} [(update && updateCityForm.txtUpdateColaValue.$invalid)]" ng-maxlength="50" ng-pattern="/^\d{1,8}(?:\.\d{0,2})?$/i">
												<div class="error-messages" ng-if="update" ng-messages="updateCityForm.txtUpdateColaValue.$error">
										        	<em class="error help-block has-error" ng-message="required">Please enter cola value</em>
										        	<em class="error help-block" ng-message="maxlength">Please enter numeric value</em>
										        	<em class="error help-block" ng-message="pattern">Please enter numeric value</em>
												</div>
		                                   </div>
		                               </div>
		                              	<div class="row marginBottom5px">
		                           			<label class="control-label col-sm-2 textAlignRight required-Field">Is Active</label>
		                                   	<div class="col-sm-3">
		                                   		<input type="checkbox" name="cbxUpdateIsActive" id="cbxUpdateIsActive" class="margingRightChkBx"
		                                   		ng-model="updateCityForm.IsActive">
		                                   	</div>
		                          		</div>
										<div class="divEmptyThrice"></div>
		                               	<div class="row text-center">
											<div class="col-sm-12">		
												<button type="button" class="btn btn-primary btnSpace" id="btnUpdateUpdate" ng-click="updateData(updateCityForm);">Update</button>						
												<button type="button" class="btn btn-danger" id="btnUpdateCancel" ng-click="cancel();">Cancel</button>
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
					            <ng-form name="uploadCityForm" id="uploadCityForm" >
		                            <div class="panel-heading panelHeadingStyle" ng-click="ShowHideUpload()">
		                                <div class="row ">
											<label class="control-label col-sm-10 ">Upload Cities</label>
											<label class="DownArrowColor col-sm-2  textAlignRight"> &#9660;</label>
										</div>
		                            </div>
		                            <div class="panel-body" ng-hide = "UploadHidden">
			                             <div class="row">
			                             	<div class="col-sm-2 "></div>
				                            <label class="control-label col-sm-2 textAlignRight required-Field">Upload File Name</label>
				                            <div class="col-sm-3 ">
			                            		<input type="file" class="form-control" name="fuUploadFilename"
												id="fuUploadFilename" ng-model="uploadCityForm.fuUploadFilenameModel" required
											 	valid-File-Excel check-file-size="uploadCityForm.fuUploadFilenameModel" 
												ng-class="{true: 'ng-border'} [(upload && uploadCityForm.fuUploadFilename.$invalid)]">
												<div class="error-messages" ng-if="upload" ng-messages="uploadCityForm.fuUploadFilename.$error">
												     <em class="error help-block has-error" ng-message="checkfilesize">File size is not valid for uploading!</em>
												     <em class="error help-block has-error" ng-message="extension">Incorrect file format</em> 
												     <em class="error help-block has-error" ng-message="required">City Details File is required!!</em>
												</div>
											</div>
											<div class="col-sm-1">
												<button type="button" class="btn btn-primary btnSpace" id="btnUpload" ng-click="uploadData(uploadCityForm);">Upload</button>						
											</div>
											<div class="divDownloadImg col-sm-4">
												<a href="${contextPath}/resources/Documents/City_Template.xls" target="_blank" class="anchorTrancColor" download="City_Template.xls"><span>Download Template</span></a>
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
