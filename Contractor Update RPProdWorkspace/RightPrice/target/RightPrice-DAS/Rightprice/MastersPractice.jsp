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
	<script src="${contextPath}/resources/js/RightPrice/masterPractice.js"></script>
	<script src="${contextPath}/resources/js/Directives/angularCustomValidations.js"></script>
	<script src="${contextPath}/resources/js/Factory/WebServiceFactory.js"></script>
    
</head>
<body ng-app="RightPriceApp" ng-controller="MastersPracticeController" >
<div id="includedContent" ng-include="'${contextPath}/Portal/Header.jsp'"></div>
<fieldset ng-disabled="loading || showLoader">
    <div class="container">
        <div class="divEmpty"></div>
        <div class="row marginBottom5px">
            <div class="col-sm-12">
                <h3 class="text-left" id="PageHeading">Master - Practice</h3>
            </div>
        </div>	
        <div>
            <form class="form-inline" role="form" name="Master" id="Master">
            <div class="row">
					<div class="col-sm-12">
						<div class="panel-group">
							<div class="panel panel-info">
								<div class="panel-heading panelHeadingStyle"  ng-click="ShowHideView()">
									<div class="row ">
										<label class="control-label col-sm-10">Practice</label>
										<label class="DownArrowColor col-sm-2  textAlignRight"> &#9660;</label>
									</div>
								</div>
								<div class="panel-body" ng-hide = "ViewHidden">
									<div class="row">
                                   		<div class="col-sm-12">
                                   			<div class="table-responsive ">
												<table
													class="table clsTable table-striped table-bordered table-hover table-condensed "
													id="tblViewPracticeDetails">
													<thead>
														<tr>
															<th>Sr. No.</th>
															<th>Practice Description</th>
														</tr>
													</thead>
													<tbody id="tblViewPracticeDetails" >
														<tr id="{{'trPracticeView'+'_'+($index+1)}}" ng-repeat="row in practiceData">
														<td class="tdTextAlignLeft">{{$index+1}}</td>
														<td class="tdTextAlignLeft">{{row.practiceName}}</td>
														
														</tr>
														
													</tbody>
												</table>
											</div>
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
                            <div class="panel panel-info">
                            <ng-form name="addPracticeForm" id="addPracticeForm" >
                                <div class="panel-heading panelHeadingStyle" ng-click="ShowHideAdd()">
                                    <div class="row ">
										<label class="control-label col-sm-10 ">Add Practice</label>
										<label class="DownArrowColor col-sm-2  textAlignRight"> &#9660;</label>
									</div>
                                </div>
                                <div class="panel-body" ng-hide = "AddHidden">
                                	<div class="row marginBottom5px">
                                   		<label class="control-label col-sm-4 textAlignRight required-Field">Practice</label>
                                        <div class="col-sm-3">
											<input name="txtPracDesc" type="text" class="form-control" id="txtPracDesc" ng-model="addPracticeForm.practiceModelName"  required 
											ng-class="{true: 'ng-border'} [(onSubmit && addPracticeForm.txtPracDesc.$invalid)]" ng-maxlength="50" ng-pattern="/^[a-z\d\_\:\-'\s]+$/i">
											<div class="error-messages" ng-if="onSubmit" ng-messages="addPracticeForm.txtPracDesc.$error">
									        	<em class="error help-block has-error" ng-message="required">Please enter Practice Description</em>
									        	<em class="error help-block" ng-message="maxlength">Practice Description cannot exceed 50 characters</em>
									        	<em class="error help-block" ng-message="pattern">Please enter characters and spaces only</em>
											</div>
                                        </div>
                                        
                                    </div> 
									<div class="divEmptyThrice"></div>
                                    <div class="row text-center">
                                    <div class="col-sm-12">		
											<button type="button" class="btn btn-info btnSpace" id="btnAddAddNew" ng-click="save(addPracticeForm);">Save</button>
											<!-- <button type="button" class="btn btn-primary btnSpace" id="btnAddSave">Save</button> -->						
											<button type="button" class="btn btn-danger" id="btnAddCancel" ng-click="onCancel();">Cancel</button>
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
                            <ng-form name="updatePracticeForm" id="updatePracticeForm" >
                                <div class="panel-heading panelHeadingStyle" ng-click="ShowHideUpdate()">
                                    <div class="row ">
										<label class="control-label col-sm-10 ">Update Practice</label>
										<label class="DownArrowColor col-sm-2  textAlignRight"> &#9660;</label>
									</div>
                                    </div>
		                           <div class="panel-body" ng-hide = "UpdateHidden">
		                               	 <div class="row marginBottom5px">
		                                   	<label class="control-label col-sm-2 textAlignRight required-Field">Practice</label>
		                                       <div class="col-sm-3">
		                                           <select id="ddlUpdatePractice" class="form-control"
													ng-model="updatePracticeForm.practiceModel"
													placeholder="Please select" name="ddlUpdatePractice"
													ng-options="prc.practiceId as prc.practiceName for prc in practiceData| orderBy:'practiceName'"
													ng-class="{true: 'ng-border'} [(onUpdate && updatePracticeForm.ddlUpdatePractice.$invalid)]"
													ng-change="getPractice(updatePracticeForm.practiceModel);getPractice1()"
													required>
													<option value="" selected disabled>Please select</option>
												</select>
												<div class="error-messages" ng-if= "onUpdate" ng-messages="updatePracticeForm.ddlUpdatePractice.$error">
										        	<em class="error help-block has-error" ng-message="required">Please select Practice.</em>
										        </div>
		                                       </div>
		                                      <label class="control-label col-sm-3 textAlignRight">New Practice</label>
		                                      <div class="col-sm-3">
		                                  	 	<input name="txtUpdatepractice" type="text" class="form-control" id="txtUpdatepractice"
		                                      		ng-model="updatePracticeForm.UpdatepracticeModel" 
													ng-class="{true: 'ng-border'} [(onUpdate && updatePracticeForm.UpdatepracticeModel.$invalid)]" ng-maxlength="50" ng-pattern="/^[a-z\d\_\:\-'\s]+$/i">
											 <div class="error-messages" ng-if="onUpdate" ng-messages="updatePracticeForm.txtUpdatepractice.$error">
									        	<em class="error help-block" ng-message="maxlength">Practice Description cannot exceed 50 characters</em>
									        	<em class="error help-block" ng-message="pattern">Please enter characters and spaces only</em>
											</div>
                                     
		                                   
		                                   </div>
		                          		
		                          		</div>
		                          		<div class="row marginBottom5px">
		                           			<label class="control-label col-sm-2 textAlignRight required-Field">Is Active</label>
		                                   	<div class="col-sm-3">
		                                   		<input type="checkbox" ng-checked="ischange" name="cbxUpdateIsActive" id="cbxUpdateIsActive" class="margingRightChkBx"
		                                   		ng-model="updatePracticeForm.IsActive">
		                                   	</div>
		                          		</div>
										<div class="divEmptyThrice"></div>
		                                 <div class="row text-center">
											<div class="col-sm-12">		
												<button type="button" class="btn btn-primary btnSpace" id="btnUpdateUpdate" ng-click="onUpdateClick(updatePracticeForm)">Update</button>	
												<button type="button" class="btn btn-danger" id="btnUpdateCancel" ng-click="onCancelClick()">Cancel</button>
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
