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
    	
// 		var app = angular.module('RateCardCreationAddContractorRoleApp', ['ngStorage']);
		app.controller("RateCardCreationAddContractorRoleController", ['$scope','$http','$filter','$location','$anchorScroll','$window','WebServiceFactory','$localStorage', '$sessionStorage', function($scope,$http,$filter,$location,$anchorScroll,$window,WebServiceFactory,$localStorage, $sessionStorage,$index) {
			
		     $scope.Prev = function()
		    {   
		        window.location='RateCardCreationRoleSelection';
		    }; 
		     $scope.Next = function()
		    {   
		        window.location='RateCardCreationRoleUtilizationAndRates';
		    };
		    if($localStorage.rcId != 0 && $localStorage.rcId != undefined) {
				var getRateCardInfo = function(response) {
			  		console.log(response);
			  		$scope.rateCardInfo = response.data;
			  		console.log("Master Data")
			  		console.log($scope.rateCardInfo);
			  		console.log("Start Date of the RC is");
			  		console.log($localStorage.rcId);
			  		
			  		var rcStartDate  = $scope.rateCardInfo[0].rcStartDate;
			  		var date = new Date(rcStartDate.substring(0,10));
			  		console.log("Rc Start Date is......... " + date);
			  		var rcStartDD = $filter('date')(date,'dd/MM/yyyy');
			  		$scope.rateCardInfo[0].rcStartDate = rcStartDD;
			  		
			  		var rcEndDate  = $scope.rateCardInfo[0].rcEndDate;
			  		var date = new Date(rcEndDate.substring(0,10));
			  		console.log("Rc End Date is......... " + date);
			  		var rcEndDateDD = $filter('date')(date,'dd/MM/yyyy');
			  		$scope.rateCardInfo[0].rcEndDate = rcEndDateDD;
				};
				WebServiceFactory.getRateCardInfo($localStorage.rcId).then(getRateCardInfo);
				}
				else {
					 BootstrapDialog.show({
							title : 'Rate Card Creation',
							type : BootstrapDialog.TYPE_DANGER,
							message : 'Please Create New Rate Card or Select Existing One.',
							closable : false,
							buttons : [ {
								label : 'OK',
								action : function(dialogRef) {
									dialogRef.close();
									//$window.location.reload();
									window.location="RateCardCreationDetails";
								}
							} ]
						});
				}
		}]);
    </script>
    <script src="${contextPath}/resources/js/Factory/WebServiceFactory.js"></script>
</head>
<body ng-app="RightPriceApp" ng-controller="RateCardCreationAddContractorRoleController" >
    <div id="includedContent" ng-include="'${contextPath}/Portal/Header.jsp'"></div>
    <fieldset ng-disabled="loading || showLoader">
	    <div class="container">
	        <div class="divEmpty"></div>
	        <div class="row marginBottom5px">
	            <div class="col-sm-12">
	                <h3 class="text-left">Rate Card Creation - Add Contractor Role</h3>
	            </div>
	        </div>	
	        <div>
            <form class="form-inline" role="form" name="RateCard" id="RateCard">
            	<div id="includedT&PStages" ng-include="'${contextPath}/Portal/RateCardCompletionStage.jsp'"></div>
				<div id="includedRateCardStages" ng-include="'${contextPath}/Rightprice/RateCardCreationInformationTable.jsp'"></div>
				<div class="row">
				   <div class="col-sm-12">
				  		<div class="panel-group">
				            <div class="panel panel-info ">
	                            <div class="panel-heading panelHeadingStyle">
	                                <div class="row ">
										<label class="control-label col-sm-10 ">Add Contractor Role</label>
										<!-- <div class="col-sm-2 textAlignRight">
											<a href="#" class="DownArrowColor" ng-click="ShowHideAddContractor()"> &#9660;</a>
										</div> -->
									</div>
	                            </div>
	                            <div class="panel-body">
									<div class="row marginBottom5px">
	                                    <div class="col-sm-12">
											<div class="table-responsive" >
												<table 
													class="table clsTable table-striped table-bordered table-hover table-condensed "
													id="tblAddContractor">
													<thead>
														<tr>
															<th>Sr.No.</th>
															<th>Customer Role</th>
															<th>Onsite Cost/Hr</th>
															<th>Offshore Cost/Hr</th>
															<th>Onsite Rate</th>
															<th>Offshore Rate</th>
															<th>Comments</th>
															<th>Remove</th>
														</tr>
													</thead>
													<tbody id="tBodyAddContractor" >
														<tr >
															<td>1</td>
															<td><input name="txtAddContractorCustomerRoleRow1" type="text" class="form-control" id="txtAddContractorCustomerRoleRow1"
                                            	   						ng-model="txtAddContractorCustomerRoleRow1Model" required></td>
															<td><input name="txtAddContractorOnsiteCostHrRow1" type="text" class="form-control" id="txtAddContractorOnsiteCostHrRow1"
                                            	   						ng-model="txtAddContractorOnsiteCostHrRow1Model" required></td>
															<td><input type="text" class="form-control" id="txtAddContractorOffshoreCostHrRow1" name="txtAddContractorOffshoreCostHrRow1" 
																	ng-model="txtAddContractorOffshoreCostHrRow1Model" required></td>
															<td><input name="txtAddContractorOnsiteRateRow1" type="text" class="form-control" id="txtAddContractorOnsiteRateRow1"
                                            	   						ng-model="txtAddContractorOnsiteRow1Model" required></td>
															<td><input type="text" class="form-control" id="txtAddContractorOffshoreRate1" name="txtAddContractorOffshoreRate1" 
																	ng-model="txtAddContractorOffshoreRateRow1Model" required></td>		
															<td><input name="txtAddContractorCommentsRow1" type="text" class="form-control" id="txtAddContractorCommentsRow1"
                                            	   						ng-model="txtAddContractorCommentsRow1Model" required></td>
															<td><button type="button"id="btnAddContractorRemoveRow1">Remove</button></td>
														</tr>
														<tr>															
															<td>2</td>
															<td><input name="txtAddContractorCustomerRoleRow2" type="text" class="form-control" id="txtAddContractorCustomerRoleRow2"
                                            	   						ng-model="txtAddContractorCustomerRoleRow2Model" required></td>
															<td><input name="txtAddContractorOnsiteCostHrRow2" type="text" class="form-control" id="txtAddContractorOnsiteCostHrRow2"
                                            	   						ng-model="txtAddContractorOnsiteCostHrRow2Model" required></td>
															<td><input type="text" class="form-control" id="txtAddContractorOffshoreCostHrRow2" name="txtAddContractorOffshoreCostHrRow2" 
																	ng-model="txtAddContractorOffshoreCostHrRow2Model" required></td>
															<td><input name="txtAddContractorOnsiteRateRow2" type="text" class="form-control" id="txtAddContractorOnsiteRateRow2"
                                            	   						ng-model="txtAddContractorOnsiteRow2Model" required></td>
															<td><input type="text" class="form-control" id="txtAddContractorOffshoreRateRow2" name="txtAddContractorOffshoreRateRow2" 
																	ng-model="txtAddContractorOffshoreRateRow2Model" required></td>
															<td><input name="txtAddContractorCommentsRow2" type="text" class="form-control" id="txtAddContractorCommentsRow2"
                                            	   						ng-model="txtAddContractorCommentsRow2Model" required></td>
															<td><button type="button"id="btnAddContractorRemoveRow2">Remove</button></td>
														</tr>			
													</tbody>
												</table>
											</div>
										</div>
									</div>
									<div class="divEmptyThrice"></div>
									<div class="row text-center">
										<div class="col-sm-12">
											<button type="button" class="btn btn-info btnSpace" id="btnAddContractorAdd">Add</button>
											<button type="button" class="btn btn-primary btnSpace" id="btnAddContractorSave">Save</button>
											<button type="button" class="btn btn-danger btnSpace" id="btnAddContractorCancel">Cancel</button>						
											<button type="button" class="btn btn-info btnSpace" id="btnClientPrev" ng-click="Prev()">Prev</button>
											<button type="button" class="btn btn-info" id="btnClientNext" ng-click="Next()">Next</button>
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
