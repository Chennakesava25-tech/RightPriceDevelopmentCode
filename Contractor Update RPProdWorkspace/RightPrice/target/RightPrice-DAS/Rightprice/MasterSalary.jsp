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
 <script src="${contextPath}/resources/js/RightPrice/masterSalary.js"></script>
 <script src="${contextPath}/resources/js/Directives/angularCustomValidations.js"></script>
 <script src="${contextPath}/resources/js/Factory/WebServiceFactory.js"></script>
</head>
<body ng-app="RightPriceApp" ng-controller="MasterSalaryController" >
<div id="includedContent" ng-include="'${contextPath}/Portal/Header.jsp'"></div>
<fieldset ng-disabled="loading || showLoader">
    <div class="container">
        <div class="divEmpty"></div>
        <div class="row marginBottom5px">
            <div class="col-sm-12">
                <h3 class="text-left" id="PageHeading">Master - Salary</h3>
            </div>
        </div>	
        <div>
            <form class="form-inline" role="form" name="Master" id="Master">
            	<div class="row">
					<div class="col-sm-12">
						<div class="panel-group">
							<div class="panel panel-info">
							<ng-form name="viewSalaryForm" id="viewSalaryForm" >
								<div class="panel-heading panelHeadingStyle" ng-click="ShowHideView()">
									<div class="row ">
										<label class="control-label col-sm-10 ">View Salary</label>
										<label class="DownArrowColor col-sm-2  textAlignRight"> &#9660;</label>
									</div>
								</div>
								<div class="panel-body" ng-hide = "ViewHidden">
									<div class="row marginBottom5px">
                                        <label class="control-label col-sm-2 textAlignRight required-Field">Country</label>
                                        <div class="col-sm-3">
                                           <select id="ddlViewCountry" class="form-control" placeholder="Please select" name="ddlViewCountry"
                                          		   ng-model="viewSalaryForm.viewCountryModel" ng-options="cn.countryName for cn in country| orderBy:'countryName'"
                                           		   ng-class="{true: 'ng-border'} [(onViewSearch && viewSalaryForm.ddlViewCountry.$invalid)]"
                                           		   ng-change="getPractice(viewSalaryForm.viewCountryModel)"
                                           		   required>
												   <option value="" selected>Please select</option>
											</select>
											<div class="error-messages" ng-if="onViewSearch" ng-messages="viewSalaryForm.ddlViewCountry.$error">
									        	<em class="error help-block has-error" ng-message="required">Please select Country</em>
											</div>
                                        </div>
                                        <label class="control-label col-sm-3 textAlignRight">Practice</label>
                                        <div class="col-sm-3">
                                      		<select id="ddlViewPractice" class="form-control" placeholder="Please select" 
                                      		        name="ddlViewPractice" ng-model="viewSalaryForm.viewPracticeModel"
                                      		        ng-options="prc.practiceId as prc.practiceName for prc in practice| orderBy:'practiceName'"
                                      		        ng-class="{true: 'ng-border'} [(onViewSearch && viewSalaryForm.ddlViewPractice.$invalid)]"
                                      		        required>
												<option value="" selected disabled>Please select</option>
											</select>
											<div class="error-messages" ng-if="onViewSearch" ng-messages="viewSalaryForm.ddlViewPractice.$error">
									        	<em class="error help-block has-error" ng-message="required">Please select Practice</em>
											</div>
                                        </div>
									</div>
									<div class="row marginBottom5px">
                                        <label class="control-label col-sm-2 textAlignRight required-Field">Year</label>
                                        <div class="col-sm-3">
                                           <select id="ddlViewYear" class="form-control" placeholder="Please select" name="ddlViewYear"
                                           		   ng-model="viewSalaryForm.viewYearModel"
						 						   ng-class="{true: 'ng-border'} [(onViewSearch && viewSalaryForm.ddlViewYear.$invalid)]"
                                           		   required>
												   <option value="" selected>Please select</option>
												   <option ng-repeat="year in years">{{year}}</option>
											</select>
											<div class="error-messages" ng-if="onViewSearch" ng-messages="viewSalaryForm.ddlViewYear.$error">
									        	<em class="error help-block has-error" ng-message="required">Please select Year</em>
											</div>
                                        </div>
									</div>
                                    <div class="divEmptyThrice"></div>
                                    <div class="row text-center">
										<div class="col-sm-12">
											<button type="button" class="btn btn-primary btnSpace" id="btnViewSearch" ng-click="searchData(viewSalaryForm.$valid);">Search</button>
											<!-- <button type="button" class="btn btn-primary" id="btnDownloadViewSalary" ng-click ="getMasterSalExcel(); " ng-disabled="isDownload">Download Salary for Year</button> -->	
											<button type="button" class="btn btn-primary" id="btnDownloadViewSalary" ng-click ="exportToExcel('#tableToExport'); " ng-disabled="isDownload">Download Salary for Year</button>
										</div>
									</div>
									<div class="divEmptyThrice"></div>
                                    <div class="row">
	                                    <div class="col-sm-5 col-sm-offset-3">
											<div class="table-responsive  " >
												<table class="table clsTable table-striped table-bordered table-hover
												 	table-condensed" id="tblViewSalary">
													<thead>
														<tr>
															<th class="textAlignCenter">Designation</th>
															 <th class="textAlignCenter">GCM Level</th> 
															<th class="textAlignCenter">Annual Salary</th>
														</tr>
													</thead>
													<tbody id="tBodyViewSalary" >
														<tr id="{{'trallowancesView'+'_'+($index+1)}}" ng-repeat="salaryView in salaryViews ">
															<td class="tdTextAlignLeft">{{(salaryView.empDesg.desgDesc)}}</td>
															 <td class="tdTextAlignLeft">{{(salaryView.empDesg.gcmCODE)}}</td> 
															<td class="tdTextAlignRight">{{(salaryView.annualSalary)}}</td>
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
				
<!-- ---------------------------------------------------------------- Export excel -------------------------------------------------------- -->				

									 	<div class="table-responsive" id="tableToExport" style="display:none" >
					 					<table border="1" 
										class="table clsTable table-striped table-bordered table-hover table-condensed "
										id="tblRateCardDetails">
										<tr>
											<td align="Center" bgcolor="#CCFFFF" colspan=3><Strong>Master - Salary</Strong></td>
										</tr>
										</table>
										<table></table>

										<table border="1" 
											class="table clsTable table-striped table-bordered table-hover
																 	table-condensed"
											id="tblViewSalary">
											<thead>
												<tr>
													<th class="tdSyntelRole" bgcolor="#CCFFFF">Designation</th>
													 <th class="tdSyntelRole" bgcolor="#CCFFFF">GCM Level</th> 
													<th class="tdSyntelRole" bgcolor="#CCFFFF">Annual Salary</th>
												</tr>
											</thead>
											<tbody id="tBodyViewSalary">
												<tr id="{{'trallowancesView'+'_'+($index+1)}}"
													ng-repeat="salaryView in salaryViews ">
													<td class="tdTextAlignLeft">{{(salaryView.empDesg.desgDesc)}}</td>
													 <td class="tdTextAlignLeft">{{(salaryView.empDesg.gcmCODE)}} &#160;</td> 
													<td class="tdTextAlignRight">{{(salaryView.annualSalary)}}</td>
												</tr>
											</tbody>
										</table>
							</div>
							
<!-- ---------------------------------------------------------------- Export excel ends -------------------------------------------------------- -->		

						<div class="row">
                    <div class="col-sm-12">
                        <div class="panel-group">
                            <div class="panel panel-info ">
                            <ng-form name="addSalaryForm" id="addSalaryForm" >
                                <div class="panel-heading panelHeadingStyle" ng-click="ShowHideAdd()">
                                    <div class="row ">
										<label class="control-label col-sm-10 ">Add Salary</label>
										<label class="DownArrowColor col-sm-2  textAlignRight"> &#9660;</label>
									</div>
                                </div>
                                <div class="panel-body" ng-hide = "AddHidden">
                                   <div class="row marginBottom5px">
                                        <label class="control-label col-sm-2 textAlignRight required-Field">Country</label>
                                        <div class="col-sm-3">
                                           <select id="ddlAddCountry" class="form-control" placeholder="Please select" name="ddlAddCountry"
                                           		    ng-model="addSalaryForm.addCountryModel" ng-options="cn.countryName for cn in country| orderBy:'countryName'"
                                           		   ng-class="{true: 'ng-border'} [(onSearchAdd && addSalaryForm.ddlAddCountry.$invalid)]"
                                           		   ng-change="getPracticee(addSalaryForm.addCountryModel)"
                                           		   required>
												   <option value="" selected>Please select</option>
											</select>
											<div class="error-messages" ng-if="onSearchAdd" ng-messages="addSalaryForm.ddlAddCountry.$error">
									        	<em class="error help-block has-error" ng-message="required">Please select Country</em>
											</div>
                                        </div>
                                        <label class="control-label col-sm-3 textAlignRight required-Field">Practice</label>
                                        <div class="col-sm-3">
                                      		<select id="ddlAddPractice" class="form-control" placeholder="Please select" name="ddlAddPractice"
                                      				ng-model="addSalaryForm.ddlAddPracticeModel"  ng-options="prc.practiceId as prc.practiceName for prc in practicee| orderBy:'practiceName'"
                                           		   ng-class="{true: 'ng-border'} [(onSearchAdd && addSalaryForm.ddlAddPractice.$invalid)]"
                                           		   ng-change="clearSearchArray()"
                                           		   required>
												   <option value="" selected>Please select</option>
											</select>
											<div class="error-messages" ng-if="onSearchAdd" ng-messages="addSalaryForm.ddlAddPractice.$error">
									        	<em class="error help-block has-error" ng-message="required">Please select Practice</em>
											</div>
                                        </div>
									</div>
									<div class="row marginBottom5px">
                                        <label class="control-label col-sm-2 textAlignRight required-Field">Year</label>
                                        <div class="col-sm-3">
                                           <select id="ddlAddYear" class="form-control" placeholder="Please select" name="ddlAddYear"
                                           		   ng-model="addSalaryForm.ddlAddYearModel"  
                                           		   ng-class="{true: 'ng-border'} [(onSearchAdd && addSalaryForm.ddlAddYear.$invalid)]"
                                           		   required ng-change="clearSearchArray()">
												   <option value="" selected>Please select</option>
												   <option ng-repeat="year in years">{{year}}</option>
											</select>
											<div class="error-messages" ng-if="onSearchAdd" ng-messages="addSalaryForm.ddlAddYear.$error">
									        	<em class="error help-block has-error" ng-message="required">Please select Year</em>
											</div>
                                        </div>
									</div>
									<div class="divEmptyThrice"></div>
									<div class="row text-center">
										<div class="col-sm-12">
											<button type="button" class="btn btn-primary btnSpace" id="btnAddSearch" ng-click="addSalarySearch(addSalaryForm.$valid);">Search</button>
										</div>
									</div>
									<div class="divEmptyThrice"></div>	
                                    <div class="row ">
	                                    <div class="col-sm-12">
											<div class="table-responsive  " >
												<table class="table clsTable table-striped table-bordered table-hover 
												 	table-condensed" id="tblAddSalary">
													<thead>
														<tr>
															<th>Designation</th>
															 <th>GCM Level</th> 
															<th>Annual Salary</th>
														</tr>
													</thead>
													<tbody id="tBodyAddSalary" >
														<tr id="{{'trallowancesAdd'+'_'+($index+1)}}" ng-repeat="addSalary in addSalaryArray">
														<td class="tdVetAlignMiddle">{{addSalary.desgDesc}}</td>
														<td class="tdVetAlignMiddle">{{addSalary.gcmCODE}}</td> 
														<td>
															<input name="{{'txtaddSalary'+'_'+($index+1)}}" type="text" class="form-control" ng-model="addSalary.annualSalary" 
															ng-class="{true: 'ng-border'}[onAddSave && addSalaryForm.{{'txtaddSalary'+'_'+($index+1)}}.$invalid]" required ng-pattern="/^\d{1,7}(?:\.\d{0,2})?$/i">
															<div class="error-messages" ng-if="onAddSave" ng-messages="addSalaryForm['txtaddSalary'+'_'+($index+1)].$error">
																<em class="error help-block has-error" ng-message="required">Please enter Annual salary.</em>
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
											<button type="button" class="btn btn-primary btnSpace" id="btnAddSave" ng-click="insertSalary(addSalaryForm.$valid)" ng-disabled="!addSalaryArray.length">Save</button>						
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
                            <ng-form name="updateSalaryForm" id="updateSalaryForm" >
                                <div class="panel-heading panelHeadingStyle" ng-click="ShowHideUpdate()">
                                    <div class="row ">
										<label class="control-label col-sm-10 ">Update Salary</label>
										<label class="DownArrowColor col-sm-2  textAlignRight"> &#9660;</label>
									</div>
                                </div>
                                <div class="panel-body" ng-hide = "UpdateHidden">
                               		 <div class="row marginBottom5px">
                                        <label class="control-label col-sm-2 textAlignRight required-Field">Country</label>
                                        <div class="col-sm-3">
                                           <select id="ddlUpdateCountry" class="form-control" placeholder="Please select" name="ddlUpdateCountry"
                                           		   ng-model="updateSalaryForm.updateCountryModel" ng-options="cn.countryName for cn in country| orderBy:'countryName'"
                                           		   ng-class="{true:'ng-border'} [(onSearchUpdate && updateSalaryForm.ddlUpdateCountry.$invalid)]"
                                           		   ng-change="getPractice(updateSalaryForm.updateCountryModel)"
                                           		   required>
												   <option value="" selected>Please select</option>
											</select>
											<div class="error-messages" ng-if="onSearchUpdate" ng-messages="updateSalaryForm.ddlUpdateCountry.$error">
									        	<em class="error help-block has-error" ng-message="required">Please select Country</em>
											</div>
                                        </div>
                                        <label class="control-label col-sm-3 textAlignRight required-Field">Practice</label>
                                        <div class="col-sm-3">
                                      		<select id="ddlUpdatePractice" class="form-control" placeholder="Please select" name="ddlUpdatePractice"
                                      				ng-model="updateSalaryForm.updatePracticeModel" 
                                      				ng-options="prc.practiceId as prc.practiceName for prc in practice| orderBy:'practiceName'"
                                      				ng-class="{true:'ng-border'} [(onSearchUpdate && updateSalaryForm.ddlUpdatePractice.$invalid)]"
                                           		   required>
												   <option value="" selected>Please select</option>
											</select>
											<div class="error-messages" ng-if="onSearchUpdate" ng-messages="updateSalaryForm.ddlUpdatePractice.$error">
									        	<em class="error help-block has-error" ng-message="required">Please select Practice</em>
											</div>
                                        </div>
									</div>
									<div class="row marginBottom5px">
                                        <label class="control-label col-sm-2 textAlignRight required-Field">Year</label>
                                        <div class="col-sm-3">
                                           <select id="ddlUpdateYear" class="form-control" placeholder="Please select" name="ddlUpdateYear"
                                           			ng-model="updateSalaryForm.ddlUpdateYearModel" 
													ng-class="{true:'ng-border'} [(onSearchUpdate && updateSalaryForm.ddlUpdatePractice.$invalid)]"
                                           		   required>
												   <option value="" selected>Please select</option>
												   <option ng-repeat="year in years">{{year}}</option>
											</select>
											<div class="error-messages" ng-if="onSearchUpdate" ng-messages="updateSalaryForm.ddlUpdatePractice.$error">
									        	<em class="error help-block has-error" ng-message="required">Please select Practice</em>
											</div>
                                        </div>
                                        
									</div>
									<div class="divEmptyThrice"></div>
									<div class="row text-center">
										<div class="col-sm-12">
											<button type="button" class="btn btn-primary btnSpace" id="btnUpdateSearch" ng-click="updateSalarySearch(updateSalaryForm.$valid);">Search</button>
										</div>
									</div>
									<div class="divEmptyThrice"></div>	
                                   	<div class="row  marginBottom5px">
	                                    <div class="col-sm-12">
											<div class="table-responsive  " >
												<table class="table clsTable table-striped table-bordered table-hover 
													table-condensed" id="tblUpdateSalary">
													<thead>
														<tr>
															<th>Designation</th>
															 <th>GCM Level</th> 
															<th>Annual Salary</th>
														</tr>
													</thead>
													<tbody id="tBodyUpdateSalary" >
														<tr id="{{'trallowancesUpdate'+'_'+($index+1)}}" ng-repeat="updateSalary in updateSalaryArray">
														<td class="tdVetAlignMiddle">{{updateSalary.empDesg.desgDesc}}</td>
														 <td class="tdVetAlignMiddle">{{updateSalary.empDesg.gcmCODE}}</td> 
														<td>
															<input name="{{'txtupdateSalary'+'_'+($index+1)}}" type="text" class="form-control" ng-model="updateSalary.annualSalary" ng-pattern="/^\d{1,7}(?:\.\d{0,2})?$/i"
																ng-class="{true: 'ng-border'}[onUpdateSave && updateSalaryForm.{{'txtupdateSalary'+'_'+($index+1)}}.$invalid]" required>
															<div class="error-messages" ng-if="onUpdateSave" ng-messages="updateSalaryForm['txtupdateSalary'+'_'+($index+1)].$error">
																<em class="error help-block has-error" ng-message="required">Please enter Annual salary.</em>
																<em class="error help-block has-error" ng-message="pattern">Please enter numeric value max up to 7 digit and 2 decimal.</em>
									        				</div>
														</td>
														</tr>
													</tbody>
												</table>
											</div>
										</div>
									</div>
									<!-- <div class="row marginBottom5px">
                                        <label class="control-label col-sm-2 textAlignRight required-Field">IsActive</label>
                                        <div class="col-sm-3">
                                        	<input type="checkbox" name="cbxUpdateIsActive" id="cbxUpdateIsActive" class="margingRightChkBx">
                                         </div>
                                    </div> -->
									<div class="divEmptyThrice"></div>
                                    <div class="row text-center">
										<div class="col-sm-12">		
											<button type="button" class="btn btn-primary btnSpace" id="btnUpdateUpdate" ng-click="updateSalary(updateSalaryForm.$valid)" ng-disabled="!updateSalaryArray.length">Update</button>						
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
				            	<ng-form name="uploadMasterSal" id="uploadMasterSal">
		                            <div class="panel-heading panelHeadingStyle"  ng-click="ShowHideUpload()">
		                                <div class="row ">
											<label class="control-label col-sm-10 ">Upload Salary</label>
											<label class="DownArrowColor col-sm-2  textAlignRight"> &#9660;</label>
										</div>
		                            </div>
		                            <div class="panel-body" ng-hide = "UploadHidden">
			                             <div class="row">
			                             	<div class="col-sm-2 "></div>
				                            <label class="control-label col-sm-2 textAlignRight required-Field">Upload File name</label>
				                            <div class="col-sm-3 ">
				                            	<input type="file" class="form-control" name="fuUploadFilename"
													id="fuUploadFilename" ng-model="uploadMasterSal.fuUploadFilenameModel" required
													check-file-size="uploadMasterSal.fuUploadFilenameModel" 
													valid-File-Excel ng-class="{true: 'ng-border'} [(upload && uploadMasterSal.fuUploadFilename.$invalid)]">
												<div class="error-messages" ng-if="upload" ng-messages="uploadMasterSal.fuUploadFilename.$error">
											     <em class="error help-block has-error" ng-message="checkfilesize">File size is not valid for uploading!</em>
											     <em class="error help-block has-error" ng-message="extension">Incorrect file format</em> 
											     <em class="error help-block has-error" ng-message="required">Please select salary details file to upload.</em>
												</div>
											</div>
											<div class="col-sm-1">
												<button type="button" class="btn btn-primary btnSpace" id="btnUpload" ng-click="uploadData(uploadMasterSal);">Upload</button>						
											</div>
											<div class="divDownloadImg col-sm-4">
												<a href="${contextPath}/resources/Documents/Salary_Template.xls" target="_blank" class="anchorTrancColor" download="Salary_Template.xls"><span>Download Template</span></a>
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
