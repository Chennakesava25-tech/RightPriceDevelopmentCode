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
	<script src="${contextPath}/resources/js/angular.js"></script>
	<script src="${contextPath}/resources/js/angular-ui.min.js"></script>
	<script src="${contextPath}/resources/js/angular-messages.js"></script>
	<script src="${contextPath}/resources/js/ngStorage.js"></script>
	<script src="${contextPath}/resources/js/loader.js"></script>
    <script type="text/javascript">
    	
// 		var app = angular.module('LookupApp', []);
		app.controller("LookupController", ['$scope','$location','$anchorScroll','$http','$window', function($scope,$location,$anchorScroll,$http,$window,$index){
			 $scope.ViewHidden = true;
			 $scope.AddHidden = true;
			 $scope.UpdateHidden = true;
			 $scope.UploadHidden = true;
             $scope.ShowHideView = function () {
                $scope.ViewHidden = $scope.ViewHidden ? false : true ;
            };
             $scope.ShowHideAdd = function () {
                $scope.AddHidden = $scope.AddHidden ? false : true;
            };
             $scope.ShowHideUpdate = function () {
                $scope.UpdateHidden = $scope.UpdateHidden ? false : true;
            };
             $scope.ShowHideUpload = function () {
                $scope.UploadHidden = $scope.UploadHidden ? false : true;
            };
		    $scope.moveTop = function()
			{
				$location.hash('PageHeading'); 
				$anchorScroll();
			};
			$scope.moveBottom = function()
			{
				$location.hash('includedFooter'); 
				$anchorScroll();
			};   
		}]);
    </script>
</head>
<body ng-app="RightPriceApp" ng-controller="LookupController" >
     <div id="includedContent" ng-include="'${contextPath}/Portal/Header.jsp'"></div>
     <fieldset ng-disabled="loading || showLoader">
    <div class="container">
        <div class="divEmpty"></div>
        <div class="row marginBottom5px">
            <div class="col-sm-12">
                <h3 class="text-left" id="PageHeading">Look up</h3>
            </div>
        </div>	
        <div>
            <form class="form-inline" role="form" name="Master" id="Master">
            <div class="row">
					<div class="col-sm-12">
						<div class="panel-group">
							<div class="panel panel-info">
								<div class="panel-heading panelHeadingStyle" ng-click="ShowHideView()">
									<div class="row ">
										<label class="control-label col-sm-10">View Look up</label>
										<label class="DownArrowColor col-sm-2  textAlignRight"> &#9660;</label>
										<!-- <div class="col-sm-2 textAlignRight">
											<a href="#" class="DownArrowColor" ng-click="ShowHideView()"> &#9660;</a>
										</div> -->
									</div>
								</div>
								<div class="panel-body" ng-hide = "ViewHidden">
									<div class="row marginBottom5px">
										<div class="col-sm-2"></div>
                                        <label class="control-label col-sm-2 textAlignRight required-Field">Look up</label>
                                        <div class="col-sm-3">
                                           <select id="ddlViewLookup" class="form-control" placeholder="Please select" name="ddlViewLookup"
                                           		ng-model="ddlViewLookupModel" required>
												<option value="" selected disabled>Please select</option>
												<option value="1">1</option>
												<option value="2">2</option>
											</select>
                                        </div>
                                        <div class="col-sm-1">		
											<button type="button" class="btn btn-primary btnSpace" id="btnViewSearch">Search</button>	
										</div>
									</div>
									<!-- <div class="row text-center">
										<div class="col-sm-12">
											<button type="button" class="btn btn-primary btnSpace" id="btnViewSearch">Search</button>	
										</div>
									</div> -->
                                    <div class="divEmptyThrice"></div>
                                 	<div class="row">
                                 		<div class="col-sm-3"></div>
                                   		<div class="col-sm-6">
                                   			<div class="table-responsive ">
												<table
													class="table clsTable table-striped table-bordered table-hover table-condensed "
													id="tblViewLookUp">
													<thead>
														<tr>		
															<th>Practice Name</th>
														</tr>
													</thead>
													<tbody id="tBodyViewLookUp" >
														<tr >
															<td>Practice 1</td>
														</tr>
														<tr>
															<td>Practice 2</td>
														</tr>	
														<tr>
															<td>Practice 3</td>
														</tr>	
														<tr>
															<td>Practice 4</td>
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
                            <div class="panel panel-info ">
                                <div class="panel-heading panelHeadingStyle" ng-click="ShowHideAdd()">
                                    <div class="row ">
										<label class="control-label col-sm-10 ">Add Look up</label>
										<label class="DownArrowColor col-sm-2  textAlignRight"> &#9660;</label>
										<!-- <div class="col-sm-2 textAlignRight">
											<a href="#" class="DownArrowColor" ng-click="ShowHideAdd()"> &#9660;</a>
										</div> -->
									</div>
                                </div>
                                <div class="panel-body" ng-hide = "AddHidden">
                                    <div class="row marginBottom5px">
                                    	<label class="control-label col-sm-2 textAlignRight required-Field">Look up</label>
                                        <div class="col-sm-3">
                                           <select id="ddlAddLookup" class="form-control" placeholder="Please select" name="ddlAddLookup"
                                           		ng-model="ddlAddLookupModel" required>
												<option value="" selected disabled>Please select</option>
												<option value="1">1</option>
												<option value="2">2</option>
											</select>
                                        </div>
                                        <label class="control-label col-sm-3 textAlignRight required-Field">Practice Name</label>
                                        <div class="col-sm-3">
                                            <input name="txtAddPracticeName" type="text" class="form-control" id="txtAddPracticeName"
                                            	ng-model="txtAddPracticeNameModel" required>
                                        </div>
                                    </div>
									<div class="divEmptyThrice"></div>
                                    <div class="row text-center">
										<div class="col-sm-12">		
											<button type="button" class="btn btn-info btnSpace" id="btnAddAddNew">Add New</button>
											<button type="button" class="btn btn-primary btnSpace" id="btnAddSave">Save</button>						
											<button type="button" class="btn btn-danger" id="btnAddCancel">Cancel</button>
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
                            <div class="panel panel-info ">
                                <div class="panel-heading panelHeadingStyle" ng-click="ShowHideUpdate()">
                                    <div class="row ">
										<label class="control-label col-sm-10 ">Update Look up</label>
										<label class="DownArrowColor col-sm-2  textAlignRight"> &#9660;</label>
										<!-- <div class="col-sm-2 textAlignRight">
											<a href="#" class="DownArrowColor" ng-click="ShowHideUpdate()"> &#9660;</a>
										</div> -->
									</div>
                                </div>
                                <div class="panel-body" ng-hide = "UpdateHidden">
                                	 <div class="row marginBottom5px">
                                       <label class="control-label col-sm-2 textAlignRight required-Field">Look up</label>
                                        <div class="col-sm-3">
                                           <select id="ddlUpdateLookup" class="form-control" placeholder="Please select" name="ddlUpdateLookup"
                                           		ng-model="ddlUpdateLookupModel" required>
												<option value="" selected disabled>Please select</option>
												<option value="1">1</option>
												<option value="2">2</option>
											</select>
                                        </div>
                                        <label class="control-label col-sm-3 textAlignRight required-Field">Practice Name</label>
                                        <div class="col-sm-3">
                                           <select id="ddlUpdatePracticeName" class="form-control" placeholder="Please select" 
                                           		name="ddlUpdatePracticeName" ng-model="ddlUpdatePracticeNameModel" required>
												<option value="" selected disabled>Please select</option>
												<option value="1">1</option>
												<option value="2">2</option>
											</select>
                                        </div>
                                    </div>
                                    <div class="row marginBottom5px">
                                        <label class="control-label col-sm-2 textAlignRight required-Field">Is Active</label>
                                        <div class="col-sm-3">
                                        	<input type="checkbox" name="cbxUpdateIsActive" id="cbxUpdateIsActive" class="margingRightChkBx">
                                         </div>
                                    </div>
									<div class="divEmptyThrice"></div>
                                    <div class="row text-center">
										<div class="col-sm-12">		
											<button type="button" class="btn btn-primary btnSpace" id="btnUpdateUpdate">Update</button>						
											<button type="button" class="btn btn-danger" id="btnUpdateCancel">Cancel</button>
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
				            <div class="panel panel-info ">
	                            <div class="panel-heading panelHeadingStyle" ng-click="ShowHideUpload()">
	                                <div class="row ">
										<label class="control-label col-sm-10 ">Upload Look up</label>
										<label class="DownArrowColor col-sm-2  textAlignRight"> &#9660;</label>
										<!-- <div class="col-sm-2 textAlignRight">
											<a href="#" class="DownArrowColor" ng-click="ShowHideUpload()"> &#9660;</a>
										</div> -->
									</div>
	                            </div>
	                            <div class="panel-body" ng-hide = "UploadHidden">
		                             <div class="row marginBottom5px">
			                            <label class="control-label col-sm-2 textAlignRight required-Field">Upload File name</label>
			                            <div class="col-sm-3 ">
			                            	<input type="file" class="form-control" name="fuUploadFilename"
												id="fuUploadFilename" ng-model="fuUploadFilenameModel" required>
										</div>
										<label class="control-label col-sm-3 textAlignRight">Excel sheet</label>
			                            <div class="col-sm-3 ">
											<input type="button"  class="btn btn-primary" value="Download Template" id="btnUploadonExcelSheet" 
												name="btnUploadonExcelSheet">
										</div>
									</div>
									<div class="divEmptyThrice"></div>
									<div class="row text-center">
										<div class="col-sm-12">
											<button type="button" class="btn btn-primary btnSpace" id="btnUploadUpload">Upload</button>						
										</div>
									</div>
                            	</div>
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
