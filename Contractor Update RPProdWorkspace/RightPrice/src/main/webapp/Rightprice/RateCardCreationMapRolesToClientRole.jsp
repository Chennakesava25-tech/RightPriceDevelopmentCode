<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
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
    <script src="${contextPath}/resources/js/jqueryValidations.js"></script>
    <link href="${contextPath}/resources/css/ie10-viewport-bug-workaround.css" rel="stylesheet" />
	<link href="${contextPath}/resources/css/sticky-footer-navbar.css" rel="stylesheet" />
	<script src="${contextPath}/resources/js/ie10-viewport-bug-workaround.js"></script>
	<script src="${contextPath}/resources/js/angular.js"></script>
	<script src="${contextPath}/resources/js/angular-ui.min.js"></script>
	<script src="${contextPath}/resources/js/angular-messages.js"></script>
	<script src="${contextPath}/resources/js/ngStorage.js"></script>
	<script src="${contextPath}/resources/js/loader.js"></script>
    <script type="text/javascript">
		var app = angular.module('MapRolesToClientRoleApp', []);
		app.controller("MapRolesToClientRoleController", ['$scope','$http','$window', function($scope,$http,$window,$index) {
			 $scope.MapHidden = true;
             $scope.ShowHideMap = function () {
                $scope.MapHidden = $scope.MapHidden ? false : true;
            };
		      
		}]);
    </script>
</head>
<body ng-app="MapRolesToClientRoleApp" ng-controller="MapRolesToClientRoleController" >
    <div id="includedContent" ng-include="'/RightPricePortal/Portal/Header.jsp'"></div>
    <div class="container">
        <div class="divEmpty"></div>
        <div class="row marginBottom5px">
            <div class="col-sm-12">
                <h3 class="text-left">Rate Card Creation - Map Roles to Client Role</h3>
            </div>
        </div>	
        <div>
            <form class="form-inline" role="form" name="Master" id="Master">
				<div id="includedRateCardStages" ng-include="'/RightPricePortal/Portal/RateCardCompletionStage.jsp'"></div>
				<div class="row">
				   <div class="col-sm-12">
				  		<div class="panel-group">
				            <div class="panel panel-info ">
	                            <div class="panel-heading panelHeadingStyle">
	                                <div class="row ">
										<label class="control-label col-sm-10 ">Map Client Role to Syntel Role</label>
										<div class="col-sm-2 textAlignRight">
											<a href="#" class="DownArrowColor" ng-click="ShowHideMap()"> &#9660;</a>
										</div>
									</div>
	                            </div>
	                            <div class="panel-body" ng-hide = "MapHidden">
									<div class="row">
	                                    <div class="col-sm-12">
											<div class="table-responsive  " >
												<table 
													class="table clsTable table-striped table-bordered table-hover table-condensed "
													id="tblMapClientRole">
													<thead>
														<tr>
															<th>Master Role Code</th>
															<th>Practice</th>
															<th>Sub Practice</th>
															<th>Syntel Role</th>
															<th>Profficieny</th>
															<th>Syntel Band/ Grade</th>
															<th>Client Role</th>
															<th>Comments</th>
														</tr>
													</thead>
													<tbody id="tBodyMapClientRole" >
														<tr >
															<td>Practice/SubPractice/Skills/X0 Skills /X0 SkillElement/X0Knowledge Area </td>
															<td> </td>
															<td> </td>
															<td> </td>
															<td> </td>
															<td> </td>
															<td><input name="txtMapClientRoleRow1" type="text" class="form-control" id="txtMapClientRoleRow1" 
																	   ng-model="txtMapClientRoleRow1Model" required> </td>
															<td><input name="txtMapCommentsRow1" type="text" class="form-control" id="txtMapCommentsRow1" 
																	   ng-model="txtMapCommentsRow1Model" required> </td>
														</tr>
														<tr>
															<td>Practice/SubPractice/Skills/X0 Skills /X0 SkillElement/X0Knowledge Area </td>
															<td> </td>
															<td> </td>
															<td> </td>
															<td> </td>
															<td> </td>
															<td><input name="txtMapClientRoleRow2" type="text" class="form-control" id="txtMapClientRoleRow2" 
																	   ng-model="txtMapClientRoleRow2Model" required> </td>
															<td><input name="txtMapCommentsRow2" type="text" class="form-control" id="txtMapCommentsRow2" 
																	   ng-model="txtMapCommentsRow2Model" required> </td>
														</tr>	
														<tr>
															<td>Practice/SubPractice/Skills/X0 Skills /X0 SkillElement/X0Knowledge Area </td>
															<td> </td>
															<td> </td>
															<td> </td>
															<td> </td>
															<td> </td>
															<td><input name="txtMapClientRoleRow3" type="text" class="form-control" id="txtMapClientRoleRow3" 
																	   ng-model="txtMapClientRoleRow3Model" required> </td>
															<td><input name="txtMapCommentsRow3" type="text" class="form-control" id="txtMapCommentsRow3" 
																	   ng-model="txtMapCommentsRow3Model" required> </td>
														</tr>	
														<tr>
															<td>Practice/SubPractice/Skills/X0 Skills /X0 SkillElement/X0Knowledge Area </td>
															<td> </td>
															<td> </td>
															<td> </td>
															<td> </td>
															<td> </td>
															<td><input name="txtMapClientRoleRow4" type="text" class="form-control" id="txtMapClientRoleRow4" 
																	   ng-model="txtMapClientRoleRow4Model" required> </td>
															<td><input name="txtMapCommentsRow4" type="text" class="form-control" id="txtMapCommentsRow4" 
																	   ng-model="txtMapCommentsRow4Model" required> </td>
														</tr>
														<!-- <tr>
															<td>Practice/SubPractice/Skills/X0 Skills /X0 SkillElement/X0Knowledge Area </td>
															<td> </td>
															<td> </td>
															<td> </td>
															<td> </td>
															<td> </td>
															<td><input name="txtMapClientRoleRow5" type="text" class="form-control" id="txtMapClientRoleRow5" 
																	   ng-model="txtMapClientRoleRow5Model" required> </td>
															<td><input name="txtMapCommentsRow5" type="text" class="form-control" id="txtMapCommentsRow5" 
																	   ng-model="txtMapCommentsRow5Model" required> </td>
														</tr>
														<tr>
															<td>Practice/SubPractice/Skills/X0 Skills /X0 SkillElement/X0Knowledge Area </td>
															<td> </td>
															<td> </td>
															<td> </td>
															<td> </td>
															<td> </td>
															<td><input name="txtMapClientRoleRow6" type="text" class="form-control" id="txtMapClientRoleRow6" 
																	   ng-model="txtMapClientRoleRow6Model" required> </td>
															<td><input name="txtMapCommentsRow6" type="text" class="form-control" id="txtMapCommentsRow6" 
																	   ng-model="txtMapCommentsRow6Model" required> </td>
														</tr> -->				
													</tbody>
												</table>
											</div>
										</div>
									</div>
                            		<div class="divEmptyThrice"></div>
                            		<div class="divEmptyThrice"></div>
                            		<div class="row text-center">
										<div class="col-sm-12">
											<button type="button" class="btn btn-primary btnSpace" id="btnMapClientSave">Save</button>
											<button type="button" class="btn btn-danger" id="btnMapClientCancel">Cancel</button>						
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
	<div id="footer" ng-include="'/RightPricePortal/Portal/Footer.jsp'"></div>
	<div id="Footer" ng-include="'/RightPricePortal/Portal/TopBottomNavigation.jsp'"></div>
</body>
</html>
