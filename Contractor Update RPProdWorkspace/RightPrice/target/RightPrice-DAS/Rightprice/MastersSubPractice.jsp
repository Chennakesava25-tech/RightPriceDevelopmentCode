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
	<script src="${contextPath}/resources/js/RightPrice/mastersSubPractice.js"></script>
	<script src="${contextPath}/resources/js/Directives/angularCustomValidations.js"></script>
	<script src="${contextPath}/resources/js/Factory/WebServiceFactory.js"></script>
    
</head>
<body ng-app="RightPriceApp" ng-controller="MastersSubPracticeController" >
<div id="includedContent" ng-include="'${contextPath}/Portal/Header.jsp'"></div>
<fieldset ng-disabled="loading || showLoader">
    <div class="container">
        <div class="divEmpty"></div>
        <div class="row marginBottom5px">
            <div class="col-sm-12">
                <h3 class="text-left" id="PageHeading">Master - Sub Practice</h3>
            </div>
        </div>	
        <div>
            <form class="form-inline" role="form" name="Master" id="Master">
            <div class="row">
					<div class="col-sm-12">
						<div class="panel-group">
							<div class="panel panel-info">
								<ng-form name="viewSubPracticeForm" id="viewSubPracticeForm" >
								<div class="panel-heading panelHeadingStyle "  ng-click="ShowHideView()">
									<div class="row ">
										<label class="control-label col-sm-10">View Sub Practices</label>
										<label class="DownArrowColor col-sm-2  textAlignRight"> &#9660;</label>
									</div>
								</div>
								<div class="panel-body" ng-hide="ViewHidden">
									<div class="row marginBottom5px">
										<div class="col-sm-2"></div>
										<label class="control-label col-sm-1 textAlignRight required-Field">Practice</label>
										<div class="col-sm-3">
											<select id="ddlAddPractice" class="form-control"
												ng-model="viewSubPracticeForm.practiceModel"
												placeholder="Please select" name="ddlViewPractice"
												ng-options="prc.practiceId as prc.practiceName for prc in practice| orderBy:'practiceName'"
												ng-class="{true: 'ng-border'} [(onSearch && viewSubPracticeForm.ddlViewPractice.$invalid)]"
												required>
												<option value="" selected disabled>Please select</option>
											</select>
											<div class="error-messages" ng-if= "onSearch" ng-messages="addSubPracticeForm.ddlAddPractice.$error">
									        	<em class="error help-block has-error" ng-message="required">Please select Practice.</em>
									        </div>
										</div>
										<div class="col-sm-4">
											<button type="button" class="btn btn-primary btnSpace" id="btnViewSearch" ng-click="onSearchClick(viewSubPracticeForm)">Search</button>
											<button type="button" class="btn btn-primary btnSpace"
												id="btnDownloadViewSubPractices" ng-click=getSubPracticeExcel(); ng-disabled=downloadBtn>Download Sub Practices</button>
										</div>
									</div>
									<div class="divEmptyThrice"></div>
									<div class="row">
										<div class="col-sm-2"></div>
										<div class="col-sm-8">
											<div class="table-responsive ">
												<table
													class="table clsTable table-striped table-bordered table-hover table-condensed tableHeader"
													border="1" id="ViewSubPracticesTbl">
													<thead>
														<tr>
															<th>Sub Practice Name</th>
															<!-- <th>Active</th> -->
														</tr>
													</thead>
													<tbody id="tBody">
														<tr id="{{'trsubpracView'+'_'+($index+1)}}"
															ng-repeat="row in subpracView">
															<td>{{(row.subpractice)}}</td>
															<!-- <td ng-if="row.isActive == 1">Active</td> -->
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
                            <div class="panel panel-info">
                            <ng-form name="addSubPracticeForm" id="addSubPracticeForm" >
                                <div class="panel-heading panelHeadingStyle" ng-click="ShowHideAdd()">
                                    <div class="row ">
										<label class="control-label col-sm-10 ">Add Sub Practice</label>
										<label class="DownArrowColor col-sm-2  textAlignRight"> &#9660;</label>
									</div>
                                </div>
                                <div class="panel-body" ng-hide = "AddHidden">
                                   <div class="row marginBottom5px">
                                    	<label class="control-label col-sm-2 textAlignRight required-Field">Practice</label>
                                        <div class="col-sm-3">
                                            <select id="ddlAddPractice" class="form-control"
												ng-model="addSubPracticeForm.practiceModel"
												placeholder="Please select" name="ddlAddPractice"
												ng-options="prc.practiceId as prc.practiceName for prc in practice| orderBy:'practiceName'"
												ng-class="{true: 'ng-border'} [(onSave && addSubPracticeForm.ddlAddPractice.$invalid)]"
												required>
												<option value="" selected disabled>Please select</option>
											</select>
											<div class="error-messages" ng-if= "onSave" ng-messages="addSubPracticeForm.ddlAddPractice.$error">
									        	<em class="error help-block has-error" ng-message="required">Please select Practice.</em>
									        </div>
                                        </div>
                                        <label class="control-label col-sm-3 textAlignRight required-Field">Sub Practice</label>
                                        <div class="col-sm-3">
											<input name="txtAddSubPractice" type="text" class="form-control" id="txtAddSubPractice"
													required
                                            		ng-model="addSubPracticeForm.subPracticemodel" 
                                            		ng-class="{true: 'ng-border'} [(onSave && addSubPracticeForm.txtAddSubPractice.$invalid)]"
                                            		ng-maxlength="50" ng-pattern="alphanumaric">
                                            <div class="error-messages" ng-if= "onSave" ng-messages="addSubPracticeForm.txtAddSubPractice.$error">
									        	<em class="error help-block has-error" ng-message="required">Please enter Sub-Practice.</em>
									        	<em class="error help-block" ng-message="pattern">Sub-Practice can contain only ( ) / _ - special character</em>
									        	<em class="error help-block" ng-message="maxlength">Sub-Practice cannot exceed 50 characters</em>
									        </div>
                                        </div>
                                    </div>
									<div class="divEmptyThrice"></div>
                                    <div class="row text-center">
										<div class="col-sm-12">		
											<button type="button" class="btn btn-primary btnSpace" id="btnAddSave" ng-click="onSaveClick(addSubPracticeForm)">Save</button>							
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
                            <ng-form name="updateSubPracticeForm" id="updateSubPracticeForm" >
                                <div class="panel-heading panelHeadingStyle" ng-click="ShowHideUpdate()">
                                    <div class="row ">
										<label class="control-label col-sm-10 ">Update Sub Practice</label>
										<label class="DownArrowColor col-sm-2  textAlignRight"> &#9660;</label>
									</div>
                                </div>
                                <div class="panel-body" ng-hide = "UpdateHidden">
                                	 <div class="row marginBottom5px">
                                    	<label class="control-label col-sm-2 textAlignRight required-Field">Practice</label>
                                        <div class="col-sm-3">
                                            <select id="ddlUpdatePractice" class="form-control"
												ng-model="updateSubPracticeForm.practiceModel"
												placeholder="Please select" name="ddlUpdatePractice"
												ng-options="prc.practiceId as prc.practiceName for prc in practice| orderBy:'practiceName'"
												ng-class="{true: 'ng-border'} [(onUpdate && updateSubPracticeForm.ddlUpdatePractice.$invalid)]"
												ng-change="getSubPractice(updateSubPracticeForm.practiceModel)"
												required>
												<option value="" selected disabled>Please select</option>
											</select>
											<div class="error-messages" ng-if= "onUpdate" ng-messages="updateSubPracticeForm.ddlUpdatePractice.$error">
									        	<em class="error help-block has-error" ng-message="required">Please select Practice.</em>
									        </div>
                                        </div>
                                        <label class="control-label col-sm-3 textAlignRight required-Field">Sub Practice</label>
                                        <div class="col-sm-3">
                                            <select id="ddlUpdateSubPractice" class="form-control"
												ng-model="updateSubPracticeForm.subpracticeModel"
												placeholder="Please select" name="ddlUpdateSubPractice"
												ng-options="subprc.subpracticeName for subprc in subpractice| orderBy:'subpracticeName'"
												ng-class="{true: 'ng-border'} [(onUpdate && updateSubPracticeForm.ddlUpdateSubPractice.$invalid)]"
												ng-change="getStatus()"
												required>
												<option value="" selected disabled>Please select</option>
											</select>
											<!-- ng-options="subprc.subpracticeId as subprc.subpracticeName for subprc in subpractice" -->
											<div class="error-messages" ng-if= "onUpdate" ng-messages="updateSubPracticeForm.ddlUpdateSubPractice.$error">
									        	<em class="error help-block has-error" ng-message="required">Please select SubPractice.</em>
									        </div>
                                        </div>
                                    </div>
                                    <div class="row marginBottom5px">
                                        <label class="control-label col-sm-2 textAlignRight required-Field">Active / Inactive</label>
                                        <div class="col-sm-3">
                                        	<input type="checkbox" name="cbxUpdateActiveInactive" id="cbxUpdateActiveInactive" 
                                        	class="margingRightChkBx" ng-model="updateSubPracticeForm.StatusModel">
                                        </div>
                                    </div>
									<div class="divEmptyThrice"></div>
                                    <div class="row text-center">
										<div class="col-sm-12">		
											<button type="button" class="btn btn-primary btnSpace" id="btnUpdateUpdate" ng-click="onUpdateClick(updateSubPracticeForm)">Update</button>	
											<button type="button" class="btn btn-danger" id="btnUpdateCancel" ng-click="onCancelClick()">Cancel</button>
										</div>
									</div>
                                </div>
                                </ng-form>
                            </div>
                        </div>
                    </div>
                </div>
				<!--  <div class="row">
				   <div class="col-sm-12">
				  		<div class="panel-group">
				            <div class="panel panel-info ">
	                            <div class="panel-heading panelHeadingStyle" ng-click="ShowHideUpload()">
	                                <div class="row ">
										<label class="control-label col-sm-10 ">Upload Sub Practice</label>
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
