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
    <script src="${contextPath}/resources/js/jquery.jcryption.3.0.1.js"></script>
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
	<script src="${contextPath}/resources/js/RightPrice/MasterLOB.js"></script>
	<script src="${contextPath}/resources/js/Factory/WebServiceFactory.js"></script>
	<script src="${contextPath}/resources/js/Directives/angularCustomValidations.js"></script>
	
<script>
		$.ajaxSetup({
			headers : {
				'X-CSRF-TOKEN' : $('meta[name="csrf-token"]').attr('content')
			}
	});
		
		$(document).ready(function() {
			$(".customDate").datepicker({
				dateFormat : 'dd/mm/yy',
				changeMonth : true,
				changeYear : true
			});
		});
</script>
</head>
<body ng-app="RightPriceApp" ng-controller="MastersLOBController"  ng-focus="customDatePicker()">
 <div id="includedContent" ng-include="'${contextPath}/Portal/Header.jsp'"></div>
 <fieldset ng-disabled="loading || showLoader">
    <div class="container">
        <div class="divEmpty"></div>
        <div class="row marginBottom5px">
            <div class="col-sm-12">
                <h3 class="text-left" id="PageHeading">Master - LOB</h3>
            </div>
        </div>	
        <div>
            <form class="form-inline" role="form" name="masterLobForm" id="masterLobForm" ng-model="masterLobModel">
            <div class="row">
					<div class="col-sm-12">
						<div class="panel-group">
							<div class="panel panel-info">
								<div class="panel-heading panelHeadingStyle" ng-click="ShowHideView()">
									<div class="row ">
										<label class="control-label col-sm-10">View LOB</label>
										<label class="DownArrowColor col-sm-2  textAlignRight"> &#9660;</label>
									</div>
								</div>
								<div class="panel-body" ng-hide = "ViewHidden">
                                 	<div class="row">
                                 		<div class="col-sm-1"></div>
                                   		<div class="col-sm-10">
                                   			<div class="table-responsive ">
												<table
													class="table clsTable table-striped table-bordered table-hover table-condensed "
													id="tblViewLOB">
													<thead>
														<tr>
															<th>LOB Code</th>
															<th>LOB Description</th>
														</tr>
													</thead>
													<tbody id="tBodyViewLOB" >
														<tr ng-repeat ="row in masterLob | orderBy:'isActive'">
														<td>{{row.lobCode}}</td>
														<td class="tdTextAlignLeft">{{row.lobDescription}}</td>
														</tr>
													</tbody>
												</table>
											</div>
										</div>
									</div>
									<div class="divEmptyThrice"></div>
									<div class="row text-center">
										<div class="col-sm-12">		
											<button type="button" class="btn btn-primary btnSpace" id="btnDownloadViewLOB" ng-click = "getLobExcel();">Download LOB</button>	
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
                        <ng-form name="addMasterLobForm" id="addMasterLobForm" >
                            <div class="panel panel-info ">
                                <div class="panel-heading panelHeadingStyle" ng-click="ShowHideAdd()">
                                    <div class="row ">
										<label class="control-label col-sm-10 ">Add LOB</label>
										<label class="DownArrowColor col-sm-2  textAlignRight"> &#9660;</label>
									</div>
                                </div>
                                <div class="panel-body" ng-hide = "AddHidden">
                                   <div class="row marginBottom5px">
                                    	<label class="control-label col-sm-2 textAlignRight required-Field">Name</label>
                                        <div class="col-sm-3">
                                            <input name="txtAddName" type="text" class="form-control" id="txtAddName"
                                            	   ng-model="addMasterLobForm.txtAddNameModel" ng-pattern= "/^[a-zA-Z]+$/" required
                                            	   ng-class="{true: 'ng-border'}[(onSubmit && addMasterLobForm.txtAddStartDate.$invalid)]" ng-maxlength="5">
                                           	 <div class="error-messages" ng-if= "onSubmit" ng-messages="addMasterLobForm.txtAddName.$error">
											<em class="error help-block has-error" ng-message="required">Please enter the Name.</em> 
											<em class="error help-block has-error" ng-message="maxlength">Name should not exceed 5 characters.</em>
											<em class="error help-block has-error" ng-message="pattern">Please enter characters Only.</em>
											</div>
                                        </div> 
                                        
                                        <label class="control-label col-sm-3 textAlignRight required-Field">Description</label>
                                        <div class="col-sm-3">
                                            <input name="txtAddDescription" type="text" class="form-control" id="txtAddDescription"
                                            	   ng-model="addMasterLobForm.txtAddDescriptionModel" ng-pattern= "/^[a-zA-Z ]+$/" required
                                            	   ng-class="{true: 'ng-border'}[(onSubmit && addMasterLobForm.txtAddStartDate.$invalid)]" ng-maxlength="100">
                                            <div class="error-messages" ng-if= "onSubmit" ng-messages="addMasterLobForm.txtAddDescription.$error">
											<em class="error help-block has-error" ng-message="required">Please enter the Description.</em>
											<em class="error help-block has-error" ng-message="pattern">Please enter characters Only.</em> 
											<em class="error help-block has-error" ng-message="maxlength">Description should not exceed 100 characters.</em>
											</div>
                                        </div>
                                    </div>
                                    <div class="row marginBottom5px">
                                        <label class="control-label col-sm-2 textAlignRight required-Field">Start Date</label>
                                        <div class="col-sm-3">
                                            <input type="text" class="form-control customDate"
												placeholder="dd/mm/yyyy" id="txtAddStartDate"
												name="txtAddStartDate" ng-model="addMasterLobForm.txtAddStartDateModel" ng-pattern="/^(((0[1-9]|[12]\d|3[01])\/(0[13578]|1[02])\/((19|[2-9]\d)\d{2}))|((0[1-9]|[12]\d|30)\/(0[13456789]|1[012])\/((19|[2-9]\d)\d{2}))|((0[1-9]|1\d|2[0-8])\/02\/((19|[2-9]\d)\d{2}))|(29\/02\/((1[6-9]|[2-9]\d)(0[48]|[2468][048]|[13579][26])|((16|[2468][048]|[3579][26])00))))$/i" required
												ng-class="{true: 'ng-border'}[(onSubmit && addMasterLobForm.txtAddStartDate.$invalid)]">
												<div class="error-messages" ng-if= "onSubmit" ng-messages="addMasterLobForm.txtAddStartDate.$error">
												<em class="error help-block has-error" ng-message="required">Please select Start Date.</em> 
												<em class="error help-block has-error" ng-message="pattern">Start Date is invalid.</em>
												</div>
                                        </div>
                                        <label class="control-label col-sm-3 textAlignRight required-Field">End Date</label>
                                        <div class="col-sm-3">
                                            <input type="text" class="form-control customDate"
												placeholder="dd/mm/yyyy" id="txtAddEndDate"
												name="txtAddEndDate" ng-model="addMasterLobForm.txtAddEndDateModel" date-compare="addMasterLobForm.txtAddStartDateModel" 
												ng-pattern="/^(((0[1-9]|[12]\d|3[01])\/(0[13578]|1[02])\/((19|[2-9]\d)\d{2}))|((0[1-9]|[12]\d|30)\/(0[13456789]|1[012])\/((19|[2-9]\d)\d{2}))|((0[1-9]|1\d|2[0-8])\/02\/((19|[2-9]\d)\d{2}))|(29\/02\/((1[6-9]|[2-9]\d)(0[48]|[2468][048]|[13579][26])|((16|[2468][048]|[3579][26])00))))$/i" required
												ng-class="{true: 'ng-border'}[(onSubmit && addMasterLobForm.txtAddEndDate.$invalid)]">
												<div class="error-messages" ng-if= "onSubmit" ng-messages="addMasterLobForm.txtAddEndDate.$error">
												<em class="error help-block has-error" ng-message="required">Please select End Date.</em>
												<em class="error help-block has-error" ng-message="pattern">End Date is invalid.</em> 
												<em class="error help-block has-error" ng-message="dateCompare">Start Date should be less than End Date.</em>
												</div>
                                        </div>
                                    </div>
									<div class="divEmptyThrice"></div>
                                    <div class="row text-center">
										<div class="col-sm-12">		
											<button type="button" class="btn btn-primary btnSpace" id="btnAddSave"  ng-click="addNew(addMasterLobForm);">Save</button>						
											<button type="button" class="btn btn-danger" id="btnAddCancel" ng-click = "cancel();">Cancel</button>
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
                         <ng-form name="updateMasterLobForm" id="updateMasterLobForm" >
                            <div class="panel panel-info ">
                                <div class="panel-heading panelHeadingStyle" ng-click="ShowHideUpdate()">
                                    <div class="row ">
										<label class="control-label col-sm-10 ">Update LOB</label>
										<label class="DownArrowColor col-sm-2  textAlignRight"> &#9660;</label>
									</div>
                                </div>
                                <div class="panel-body" ng-hide = "UpdateHidden">
                                	<div class="row marginBottom5px">
                                    	<label class="control-label col-sm-2 textAlignRight required-Field">Name</label>
                                        <div class="col-sm-3">
                                            <select id="ddlUpdateName" class="form-control" placeholder="Please select" name="ddlUpdateName"
                                           			ng-model="updateMasterLobForm.ddlUpdateNameModel" ng-options="lob as lob.lobCode for lob in masterLob| orderBy:'lobCode'" ng-change="putLobDetails(updateMasterLobForm.ddlUpdateNameModel);" required
                                           			ng-class="{true: 'ng-border'}[(onUpdate && updateMasterLobForm.ddlUpdateName.$invalid)]" >
												<option value="" selected disabled>Please select</option>
											</select>
											<div class="error-messages" ng-if= "onUpdate" ng-messages="updateMasterLobForm.ddlUpdateName.$error">
									        	<em class="error help-block has-error" ng-message="required">Please select Name.</em>
									        </div>
                                        </div>
                                        <label class="control-label col-sm-3 textAlignRight required-Field">Description</label>
                                        <div class="col-sm-3">
                                            <input name="txtUpdateDescription" type="text" class="form-control" id="txtUpdateDescription"
                                            	   ng-model="updateMasterLobForm.txtUpdateDescriptionModel" ng-pattern= "/^[a-zA-Z ]+$/" required 
                                            	    ng-class="{true: 'ng-border'}[(onUpdate && updateMasterLobForm.txtUpdateDescription.$invalid)]" ng-maxlength="100">
                                            <div class="error-messages" ng-if= "onUpdate" ng-messages="updateMasterLobForm.txtUpdateDescription.$error">
												<em class="error help-block has-error" ng-message="required">Please enter the Description.</em>
												<em class="error help-block has-error" ng-message="pattern">Please enter characters Only.</em> 
												<em class="error help-block has-error" ng-message="maxlength">Description should not exceed 100 characters.</em>
											</div>
                                        </div>
                                    </div>
                                    <div class="row marginBottom5px">
                                        <label class="control-label col-sm-2 textAlignRight required-Field">Start Date</label>
                                        <div class="col-sm-3">
                                            <input type="text" class="form-control customDate"
												placeholder="dd/mm/yyyy" id="txtUpdateStartDate"
												name="txtUpdateStartDate" ng-model="updateMasterLobForm.txtUpdateStartDateModel"
												ng-pattern="/^(((0[1-9]|[12]\d|3[01])\/(0[13578]|1[02])\/((19|[2-9]\d)\d{2}))|((0[1-9]|[12]\d|30)\/(0[13456789]|1[012])\/((19|[2-9]\d)\d{2}))|((0[1-9]|1\d|2[0-8])\/02\/((19|[2-9]\d)\d{2}))|(29\/02\/((1[6-9]|[2-9]\d)(0[48]|[2468][048]|[13579][26])|((16|[2468][048]|[3579][26])00))))$/i" required
												ng-class="{true: 'ng-border'}[(onUpdate && updateMasterLobForm.txtUpdateStartDate.$invalid)]">
												<div class="error-messages" ng-if= "onUpdate" ng-messages="updateMasterLobForm.txtUpdateStartDate.$error">
												<em class="error help-block has-error" ng-message="required">Please select Start Date.</em>
												<em class="error help-block has-error" ng-message="pattern">Start Date is invalid.</em> 
												</div>
                                        </div>
                                        <label class="control-label col-sm-3 textAlignRight required-Field">End Date</label>
                                        <div class="col-sm-3">
                                            <input type="text" class="form-control customDate"
												placeholder="dd/mm/yyyy" id="txtUpdateEndDate"
												name="txtUpdateEndDate" ng-model="updateMasterLobForm.txtUpdateEndDateModel" date-compare="updateMasterLobForm.txtUpdateStartDateModel" 
												ng-pattern="/^(((0[1-9]|[12]\d|3[01])\/(0[13578]|1[02])\/((19|[2-9]\d)\d{2}))|((0[1-9]|[12]\d|30)\/(0[13456789]|1[012])\/((19|[2-9]\d)\d{2}))|((0[1-9]|1\d|2[0-8])\/02\/((19|[2-9]\d)\d{2}))|(29\/02\/((1[6-9]|[2-9]\d)(0[48]|[2468][048]|[13579][26])|((16|[2468][048]|[3579][26])00))))$/i" required
												ng-class="{true: 'ng-border'}[(onUpdate && updateMasterLobForm.txtUpdateEndDate.$invalid)]">
												<div class="error-messages" ng-if= "onUpdate" ng-messages="updateMasterLobForm.txtUpdateEndDate.$error">
												<em class="error help-block has-error" ng-message="required">Please select End Date.</em>
												<em class="error help-block has-error" ng-message="pattern">End Date is invalid.</em> 
												<em class="error help-block has-error" ng-message="dateCompare">Start Date should be less than End Date.</em>
												</div>
                                        </div>
                                    </div>
                                    <div class="row marginBottom5px">
                                        <label class="control-label col-sm-2 textAlignRight required-Field">Active / Inactive</label>
                                        <div class="col-sm-3">
                                        	<input type="checkbox" name="cbxUpdateActiveInactive" id="cbxUpdateActiveInactive" class="margingRightChkBx"
                                        	ng-model = "updateMasterLobForm.cbxUpdateActiveInactive">
                                         </div>
                                    </div>
									<div class="divEmptyThrice"></div>
                                    <div class="row text-center">
										<div class="col-sm-12">		
											<button type="button" class="btn btn-primary btnSpace" id="btnUpdateUpdate" ng-model="updateMasterLobForm.btnUpdate" ng-click="updateDetails(updateMasterLobForm);">Update</button>						
											<button type="button" class="btn btn-danger" id="btnUpdateCancel" ng-model="updateMasterLobForm.btnCancel" ng-click = "clear();">Cancel</button>
										</div>
									</div>
                                </div>
                            </div>
                            </ng-form>
                        </div>
                    </div>
                </div>
				<!--  <div class="row">
				   <div class="col-sm-12">
				  		<div class="panel-group">
				            <div class="panel panel-info ">
	                            <div class="panel-heading panelHeadingStyle">
	                                <div class="row ">
										<label class="control-label col-sm-10 "  ng-click="ShowHideUpload()">Upload LOB</label>
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
