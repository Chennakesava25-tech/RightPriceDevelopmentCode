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
	<script src="${contextPath}/resources/js/RightPrice/MastersDesignation.js"></script>
	<script src="${contextPath}/resources/js/Directives/angularCustomValidations.js"></script>
	<script src="${contextPath}/resources/js/Factory/WebServiceFactory.js"></script>
</head>
<body ng-app="RightPriceApp" ng-controller="MastersDesignationController" >
 <div id="includedContent" ng-include="'${contextPath}/Portal/Header.jsp'"></div>
  <fieldset ng-disabled="loading || showLoader">
    <div class="container">
        <div class="divEmpty"></div>
        <div class="row marginBottom5px">
            <div class="col-sm-12">
                <h3 class="text-left" id="PageHeading">Master - Designation</h3>
            </div>
        </div>	
        <div>
            <form class="form-inline" role="form" name="frmMasterId" id="frmMasterId">
            <div class="row">
					<div class="col-sm-12">
						<div class="panel-group">
							<div class="panel panel-info">
								<div class="panel-heading panelHeadingStyle" ng-click="ShowHideView()">
									<div class="row ">
										<label class="control-label col-sm-10">View Designations</label>
										<label class="DownArrowColor col-sm-2  textAlignRight"> &#9660;</label>
									</div>
								</div>
								<div class="panel-body" ng-hide = "ViewHidden">
                                 	<div class="row">
                                   		<div class="col-sm-12">
                                   			<div class="table-responsive ">
												<table
													class="table clsTable table-striped table-bordered table-hover table-condensed "
													id="tblViewDesignations">
													<thead>
														<tr>
															<th>Designation</th>
															<th>Band</th>
															<th>Grade</th>
															<th>GCM Level</th>
															<th>Min Exp Years</th>
															<th>Max Exp Years</th>
														</tr>
													</thead>
													<tbody id="tBody" >
														<tr id="{{'trdesignationView'+'_'+($index+1)}}" ng-repeat="row in designation">
															<td class="tdTextAlignLeft">{{(row.empDesignationDescription)}}</td>
															<td>{{(row.band.description)}}</td>
															<td>{{(row.grade.description)}}</td>
															<td>{{(row.gcmCODE)}}</td>
															<td class="tdTextAlignRight">{{(row.minimumExperienceYears)}}</td>
															<td class="tdTextAlignRight">{{(row.maximumExperienceYears)}}</td>
														</tr>	
													</tbody>
												</table>
											</div>
										</div>
									</div>
									<div class="divEmptyThrice"></div>
                                    <div class="row text-center">
										<div class="col-sm-12">
										<!-- <button type="button" class="btn btn-primary btnSpace" id="btnDownloadViewDasignations" ng-click="getMasterDesigExcel();"> Download Designations</button> -->
										<button type="button" class="btn btn-primary btnSpace" id="btnDownloadViewDasignations" ng-click="exportToExcel('#tableToExport');"> Download Designations</button>		
										</div>
									</div>
								</div>
								
										<div class="table-responsive" id="tableToExport" style="display:none" >
					 					<table border="1" 
										class="table clsTable table-striped table-bordered table-hover table-condensed "
										id="tblRateCardDetails">
										<tr>
											<td align="Center" bgcolor="#CCFFFF" colspan=6><Strong>Master - Designation</Strong></td>
										</tr>
										</table>
										
										<table></table>
										
												<table border="1" 
													class="table clsTable table-striped table-bordered table-hover table-condensed "
													id="tblViewDesignations">
													<thead>
														<tr>
															<th class="tdSyntelRole" bgcolor="#CCFFFF">Designation</th>
															<th class="tdSyntelRole" bgcolor="#CCFFFF">Band</th>
															<th class="tdSyntelRole" bgcolor="#CCFFFF">Grade</th>
															<th class="tdSyntelRole" bgcolor="#CCFFFF">GCM Level</th>
															<th class="tdSyntelRole" bgcolor="#CCFFFF">Min Exp Years</th>
															<th class="tdSyntelRole" bgcolor="#CCFFFF">Max Exp Years</th>
														</tr>
													</thead>
													<tbody id="tBody" >
														<tr id="{{'trdesignationView'+'_'+($index+1)}}" ng-repeat="row in designation">
															<td class="tdTextAlignLeft">{{(row.empDesignationDescription)}}</td>
															<td>{{(row.band.description)}}</td>
															<td>{{(row.grade.description)}}</td>
															<td>{{(row.gcmCODE)}} &#160;</td>
															<td class="tdTextAlignRight">{{(row.minimumExperienceYears)}}</td>
															<td class="tdTextAlignRight">{{(row.maximumExperienceYears)}}</td>
														</tr>	
													</tbody>
												</table>
											</div>
								
								
								
							</div>
						</div>
					</div>
				</div>
                <div class="row">
                    <div class="col-sm-12">
                        <div class="panel-group">
                         <ng-form name="addMasterDesignationForm" id="addMasterDesignationForm" >
                            <div class="panel panel-info ">
                                <div class="panel-heading panelHeadingStyle" ng-click="ShowHideAdd()">
                                    <div class="row ">
										<label class="control-label col-sm-10 ">Add Designation</label>
										<label class="DownArrowColor col-sm-2  textAlignRight"> &#9660;</label>
									</div>
                                </div>
                                <div class="panel-body" ng-hide = "AddHidden">
                                	<div class="row marginBottom5px">
                                   		<label class="control-label col-sm-2 textAlignRight required-Field">Designation</label>
                                        <div class="col-sm-3">
                                        	<input name="txtAddDesignation" type="text" class="form-control" id="txtAddDesignation"
                                        			ng-model="addMasterDesignationForm.adddesignationmodel" required 
                                        			ng-class="{true: 'ng-border'}[(onSave && addMasterDesignationForm.txtAddDesignation.$invalid)]" ng-maxlength="50" ng-pattern="/^[a-z\d\_()\s]+$/i">
                                        	<div class="error-messages" ng-if= "onSave" ng-messages="addMasterDesignationForm.txtAddDesignation.$error">
									        	<em class="error help-block has-error" ng-message="required">Please enter Designation</em>
									        	<em class="error help-block has-error" ng-message="maxlength">Designation should not exceed 50 character</em>
									        	<em class="error help-block has-error" ng-message="pattern">Please enter character only</em>
									        </div>
                                        </div>
                                    </div> 
                                	<div class="row marginBottom5px">
                                    	<label class="control-label col-sm-2 textAlignRight required-Field">Band</label>
                                        <div class="col-sm-3">
                                           <select id="ddlAddBand" class="form-control" placeholder="Please select" name="ddlAddBand"
                                           			ng-model="addMasterDesignationForm.addbandmodel" required ng-options="bn.codeName as bn.description for bn in band| orderBy:'description'"
                                           			ng-class="{true: 'ng-border'}[(onSave && addMasterDesignationForm.ddlAddBand.$invalid)]">
												<option value="" selected disabled>Please select</option>
											</select>
											<div class="error-messages" ng-if= "onSave" ng-messages="addMasterDesignationForm.ddlAddBand.$error">
									        	<em class="error help-block has-error" ng-message="required">Please select Band</em>
									        </div>
                                        </div>
                                        <label class="control-label col-sm-3 textAlignRight required-Field">Grade</label>
                                        <div class="col-sm-3">
                                           <select id="ddlAddGrade" class="form-control" placeholder="Please select" name="ddlAddGrade"
                                           			ng-model="addMasterDesignationForm.addgrademodel" required ng-options="gr.codeName as gr.description for gr in grade| orderBy:'description'"
                                           			ng-class="{true: 'ng-border'}[(onSave && addMasterDesignationForm.ddlAddGrade.$invalid)]">
												<option value="" selected disabled>Please select</option>
											</select>
											<div class="error-messages" ng-if= "onSave" ng-messages="addMasterDesignationForm.ddlAddGrade.$error">
									        	<em class="error help-block has-error" ng-message="required">Please select Grade</em>
									        </div>
                                        </div>
                                   </div>
                                  <div class="row marginBottom5px">
                                    	<label class="control-label col-sm-2 textAlignRight">Minimum Experience (Years)</label>
                                        <div class="col-sm-3">
                                            <input name="txtAddMinimumExperience" type="text" class="form-control" id="txtAddMinimumExperience"
                                            	   ng-model="addMasterDesignationForm.addminimumexperiencemodel"  
                                            	   ng-class="{true: 'ng-border'}[(onSave && addMasterDesignationForm.txtAddMinimumExperience.$invalid)]" ng-pattern="numberRegex">
                                            <div class="error-messages" ng-if= "onSave" ng-messages="addMasterDesignationForm.txtAddMinimumExperience.$error">									        	
									        	<em class="error help-block has-error" ng-message="pattern">Please enter numeric value max up to 2 digit and 2 decimal.</em>
									        </div>
                                        </div>                                        
                                        <div class="col-sm-1"></div>
                                        <label class="control-label col-sm-2 textAlignRight">Maximum Experience (Years)</label>
                                        <div class="col-sm-3">
                                            <input name="txtAddMaximumExperience" type="text" class="form-control" id="txtAddMaximumExperience"
                                            	   ng-model="addMasterDesignationForm.addmaximumexperiencemodel"  
                                            	   ng-class="{true: 'ng-border'}[(onSave && addMasterDesignationForm.txtAddMaximumExperience.$invalid)]" ng-pattern="numberRegex"
                                            	   lowerthan='addMasterDesignationForm.addminimumexperiencemodel'>
                                            <div class="error-messages" ng-if= "onSave" ng-messages="addMasterDesignationForm.txtAddMaximumExperience.$error">
									        	<em class="error help-block has-error" ng-message="required">Please enter maximum experience (years)</em>
									        	<em class="error help-block has-error" ng-message="pattern">Please enter numeric value max up to 2 digit and 2 decimal.</em>
									        	<em class="error help-block has-error" ng-message="lowerthan">Maximum experience years should be greater then minimum experience years</em>
									        </div>
                                        </div>
                                    </div>
                                    <!--Start Date commented code removed from Add Designation-->
									<div class="divEmptyThrice"></div>
                                    <div class="row text-center">
										<div class="col-sm-12">
										<!-- Add Boutten Removed -->		
											<button type="button" class="btn btn-primary btnSpace" id="btnAddSave" ng-click="onSaveClick(addMasterDesignationForm);">Save</button>						
											<button type="button" class="btn btn-danger" id="btnAddCancel"ng-click="cancelClickOnAddPanal(addMasterDesignationForm);">Cancel</button>
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
                         <ng-form name="updateMasterDesignationForm" id="updateMasterDesignationForm" >
                            <div class="panel panel-info ">
                                <div class="panel-heading panelHeadingStyle"  ng-click="ShowHideUpdate()">
                                    <div class="row ">
										<label class="control-label col-sm-10 ">Update Designation</label>
										<label class="DownArrowColor col-sm-2  textAlignRight"> &#9660;</label>
									</div>
                                </div>
                                <div class="panel-body" ng-hide = "UpdateHidden">
                                	<div class="row marginBottom5px">
                                	 	<label class="control-label col-sm-2 textAlignRight required-Field">Designation</label>
                                        <div class="col-sm-3">
                                            <select id="ddlUpdateDesignation" class="form-control" placeholder="Please select" 
                                            		name="ddlUpdateDesignation" ng-model="updateMasterDesignationForm.updatedesignationmodel" required 
                                            		ng-options="dg as dg.empDesignationDescription for dg in designation| orderBy:'empDesignationDescription'" 
                                            		ng-change="putdesignationDetails(updateMasterDesignationForm.updatedesignationmodel);" 
                                            		ng-class="{true: 'ng-border'}[(onUpdate && updateMasterDesignationForm.ddlUpdateDesignation.$invalid)]" >
												<option value="" selected disabled>Please select</option>
											</select>
											<div class="error-messages" ng-if= "onUpdate" ng-messages="updateMasterDesignationForm.ddlUpdateDesignation.$error">
									        	<em class="error help-block has-error" ng-message="required">Please select Designation</em>
									        </div>
                                        </div>
                                	</div>
                                	<div class="row marginBottom5px">
                                    	<label class="control-label col-sm-2 textAlignRight required-Field">Band</label>
                                        <div class="col-sm-3">
                                           <select id="ddlUpdateBand" class="form-control" placeholder="Please select" name="ddlUpdateBand"
                                           		   ng-model="updateMasterDesignationForm.updatebandmodel" required ng-options="bn.codeName as bn.description for bn in band| orderBy:'description'" 
                                           		   ng-class="{true: 'ng-border'}[(onUpdate && updateMasterDesignationForm.ddlUpdateBand.$invalid)]">
												<option value="" selected disabled>Please select</option>
											</select>
											<div class="error-messages" ng-if= "onUpdate" ng-messages="updateMasterDesignationForm.ddlUpdateBand.$error">
									        	<em class="error help-block has-error" ng-message="required">Please select Band</em>
									        </div>
                                        </div>
                                        <label class="control-label col-sm-3 textAlignRight required-Field">Grade</label>
                                        <div class="col-sm-3">
                                           <select id="ddlUpdateGrade" class="form-control" placeholder="Please select" name="ddlUpdateGrade"
                                           		   ng-model="updateMasterDesignationForm.updategrademodel" required ng-options="gr.codeName as gr.description for gr in grade| orderBy:'description'"
                                           		   ng-class="{true: 'ng-border'}[(onUpdate && updateMasterDesignationForm.ddlUpdateGrade.$invalid)]">
												<option value="" selected disabled>Please select</option>
											</select>
											<div class="error-messages" ng-if= "onUpdate" ng-messages="updateMasterDesignationForm.ddlUpdateGrade.$error">
									        	<em class="error help-block has-error" ng-message="required">Please select Grade</em>
									        </div>
                                        </div>
                                    </div>
                                	 <div class="row marginBottom5px">
                                    	<label class="control-label col-sm-2 textAlignRight">Minimum Experience (Years)</label>
                                        <div class="col-sm-3">
                                            <input name="txtUpdateMinimumExperience" type="text" class="form-control" id="txtUpdateMinimumExperience"
                                            	   ng-model="updateMasterDesignationForm.updateminimumexperiencemodel" 
                                            	   ng-class="{true: 'ng-border'}[(onUpdate && updateMasterDesignationForm.txtUpdateMinimumExperience.$invalid)]" ng-pattern="numberRegex">
                                            <div class="error-messages" ng-if= "onUpdate" ng-messages="updateMasterDesignationForm.txtUpdateMinimumExperience.$error">
									        	<em class="error help-block has-error" ng-message="required">Please enter maximum experience (years)</em>
									        	<em class="error help-block has-error" ng-message="pattern">Please enter numeric value max up to 2 digit and 2 decimal.</em>
									        </div>
                                        </div>
                                        <div class="col-sm-1"></div>
                                        <label class="control-label col-sm-2 textAlignRight">Maximum Experience (Years)</label>
                                        <div class="col-sm-3">
                                            <input name="txtUpdateMaximumExperience" type="text" class="form-control" id="txtUpdateMaximumExperience"
                                            		ng-model="updateMasterDesignationForm.updatemaximumexperiencemodel" 
                                            		ng-class="{true: 'ng-border'}[(onUpdate && updateMasterDesignationForm.txtUpdateMaximumExperience.$invalid)]" ng-pattern="numberRegex"
                                            		lowerthan='updateMasterDesignationForm.updateminimumexperiencemodel'>
                                           <div class="error-messages" ng-if= "onUpdate" ng-messages="updateMasterDesignationForm.txtUpdateMaximumExperience.$error">
									        	<em class="error help-block has-error" ng-message="required">Please enter maximum experience (years)</em>
									        	<em class="error help-block has-error" ng-message="pattern">Please enter numeric value max up to 2 digit and 2 decimal.</em>
									        	<em class="error help-block has-error" ng-message="lowerthan">Maximum experience years should be greater then minimum experience years</em>
									        </div>
                                        </div>
                                    </div>
                                    <!--Start Date commented code removed from Update Designation-->
                                    <div class="row marginBottom5px">
                                    <!--Practice Name Commented Code Removed-->
                                        <label class="control-label col-sm-2 textAlignRight">Active / Inactive</label>
                                        <div class="col-sm-3">
                                        	<input type="checkbox" name="cbxUpdateActiveInactive" id="cbxUpdateActiveInactive" class="margingRightChkBx" 
                                        	ng-model = "updateMasterDesignationForm.cbxUpdateActiveInactive">
                                         </div>
                                    </div>
									<div class="divEmptyThrice"></div>
                                    <div class="row text-center">
										<div class="col-sm-12">		
											<button type="button" class="btn btn-primary btnSpace" id="btnUpdateUpdate"ng-click="onUpdateClick(updateMasterDesignationForm);">Update</button>						
											<button type="button" class="btn btn-danger" id="btnUpdateCancel"ng-click="cancelClickOnUpdatePanal(updateMasterDesignationForm);">Cancel</button>
										</div>
									</div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
				<!--  <div class="row">
				   <div class="col-sm-12">
				  		<div class="panel-group">
				            <div class="panel panel-info ">
	                            <div class="panel-heading panelHeadingStyle"  ng-click="ShowHideUpload()">
	                                <div class="row ">
										<label class="control-label col-sm-10 ">Upload Designation</label>
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
	<div id="footer" ng-include="'${contextPath}/Portal/TopBottomNavigation.jsp'"></div>
	<div id="loader" ng-include="'${contextPath}/Portal/loader.jsp'"></div>
</body>
</html>
